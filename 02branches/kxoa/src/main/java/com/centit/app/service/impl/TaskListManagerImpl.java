package com.centit.app.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import com.centit.app.dao.TaskListAnnexDao;
import com.centit.app.dao.TaskListDao;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.TaskCatalog;
import com.centit.app.po.TaskList;
import com.centit.app.po.TaskListAnnex;
import com.centit.app.service.IToDoMatter;
import com.centit.app.service.IToDoMatterData;
import com.centit.app.service.TaskListAnnexManager;
import com.centit.app.service.TaskListManager;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;

public class TaskListManagerImpl extends BaseEntityManagerImpl<TaskList> implements TaskListManager, IToDoMatter {
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(TaskListManager.class);

	// private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private Map<String, IToDoMatter> toDoMatter = new HashMap<String, IToDoMatter>();

	private TaskListDao taskListDao;

	@Resource
	private TaskListAnnexDao taskListAnnexDao;

	private FunctionManager functionManager;
    private TaskListAnnexManager taskListAnnexManager;

    
	public void setTaskListAnnexManager(TaskListAnnexManager taskListAnnexManager) {
        this.taskListAnnexManager = taskListAnnexManager;
    }

	public void setToDoMatter(Map<String, IToDoMatter> toDoMatter) {
		this.toDoMatter = toDoMatter;
	}

	public void setFunctionManager(FunctionManager functionManager) {
		this.functionManager = functionManager;
	}

	public void setTaskListDao(TaskListDao baseDao) {
		this.taskListDao = baseDao;
		setBaseDao(this.taskListDao);
	}

	public void initToDoMatter() {
		toDoMatter.put("taskListManager", this);
	}

	@Override
	public String getTaskListData(Map<String, String> params, TaskList object) {
		String ac = params.get("ac");
		if ("getCalendar".equals(ac)) {

			return getUserTaskList(params, object);
		} else if ("quick".equals(ac) || "edit".equals(ac)) {
			return saveObject(params, object);
		} else if ("getDate".equals(ac)) {
			return getUserTaskListById(params, object);
		}
		return null;
	}

	@Override
	public String getUserTaskList(Map<String, String> params, TaskList object) {
		Calendar start = Calendar.getInstance();
		start.setTime(new Date(Long.parseLong(params.get("start")) * 1000));
		Calendar end = Calendar.getInstance();
		end.setTime(new Date(Long.parseLong(params.get("end")) * 1000 - 1000));

		List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();

		// 单个模块嵌入式调用此块功能
		if (StringUtils.hasText(params.get("instance")) && toDoMatter.containsKey(params.get("instance"))) {
			getJsonResult(params, start, end, jsonResult, params.get("instance"),
					toDoMatter.get(params.get("instance")));
		} else {
			for (Entry<String, IToDoMatter> entry : toDoMatter.entrySet()) {
				getJsonResult(params, start, end, jsonResult, entry.getKey(), entry.getValue());
			}
		}

		return JSONArray.fromObject(jsonResult).toString();
	}

	private void getJsonResult(Map<String, String> params, Calendar start, Calendar end,
			List<Map<String, Object>> jsonResult, String key, IToDoMatter value) {
		List<? extends IToDoMatterData> tasklist = value.getUserTaskList(params.get("usercode"),
				params.get("tasktype"), start.getTime(), end.getTime(), null);
		// 添加实例，操作相关实现类接口
		List<Map<String, Object>> result = putDataToMap(tasklist, params.get("contextPath"));

		putParams(result, "instance", key);
		putParams(result, "taskCatalog", JSONObject.fromObject(value.getTaskCatalog()).toString());
		jsonResult.addAll(result);
	}

	/**
	 * 将数据转换为日历控件所需要的Json数据格式
	 * 
	 * @param tasklist
	 * @return
	 */
	@SuppressWarnings("static-method")
	private List<Map<String, Object>> putDataToMap(List<? extends IToDoMatterData> tasklist, String contextPath) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (IToDoMatterData data : tasklist) {
			Map<String, Object> task = new HashMap<String, Object>();

			task.put("id", data.getId());
			task.put("title", data.getTitle());
			task.put("start", DatetimeOpt.convertDateToString(data.getStart(), "yyyy-MM-dd HH:mm:ss"));
			task.put("end", DatetimeOpt.convertDateToString(data.getEnd(), "yyyy-MM-dd HH:mm:ss"));

			// 显示标记颜色
			task.put("tasktag", data.getTasktag());

			// 可编辑
			task.put("editable", data.isEditable());

			task.put("allDay", data.isAllDay());

			if (StringUtils.hasText(data.getUrl())) {
				task.put("url", contextPath + data.getUrl());
			}
			result.add(task);
		}

		return result;
	}

	@SuppressWarnings("static-method")
	private void putParams(List<Map<String, Object>> result, String key, String value) {
		for (Map<String, Object> map : result) {
			map.put(key, value);
		}

	}

	@Override
	public String getUserTaskListById(Map<String, String> params, TaskList object) {
		TaskList taskList = getObject(object);

		String isallday = null;
		if (StringUtils.hasText(taskList.getTaskvalue())) {
			JSONObject jsonObject = JSONObject.fromObject(taskList.getTaskvalue());
			if (jsonObject.containsKey("isallday")) {
				isallday = jsonObject.getString("isallday");
			}
		} else {
			isallday = "0";
		}
		JSONObject jsonObject = JSONObject.fromObject(taskList);

		jsonObject.remove("taskvalue");
		jsonObject.remove("planbegintime");
		jsonObject.remove("planendtime");
		jsonObject.remove("created");

		jsonObject.put("isallday", isallday);

		String startd = DatetimeOpt.convertDateToString(taskList.getPlanbegintime(), "yyyy-MM-dd");
		String startt = DatetimeOpt.convertDateToString(taskList.getPlanbegintime(), "HH:mm:ss");
		String endd = DatetimeOpt.convertDateToString(taskList.getPlanendtime(), "yyyy-MM-dd");
		String endt = DatetimeOpt.convertDateToString(taskList.getPlanendtime(), "HH:mm:ss");

		jsonObject.put("startd", startd);
		jsonObject.put("startt", startt);
		jsonObject.put("endd", endd);
		jsonObject.put("endt", endt);

		jsonObject.put("begintime", DatetimeOpt.convertDateToString(taskList.getBegintime(), "yyyy-MM-dd HH:mm:ss"));
		jsonObject.put("endtime", DatetimeOpt.convertDateToString(taskList.getEndtime(), "yyyy-MM-dd HH:mm:ss"));

		jsonObject.put("editable", Boolean.parseBoolean(params.get("editable")));

		return jsonObject.toString();
	}

	@Override
	public String saveObject(Map<String, String> params, TaskList object) {
		String usercode = params.get("usercode");
		String doit = params.get("do");
		// 是否贯穿全天
		String isallday = params.get("isallday");

		String taskvalue = "{\"isallday\" : \"" + isallday + "\"}";
		if ("add".equals(doit)) {
			object.setTaskowner(usercode);
			object.setTasktag("N");
			object.setTaskrank("N");
			object.setTaskstatus("Cr");
			object.setTasktype("P");
			object.setOptid("TASKLIST");

			object.setCreator(usercode);
			object.setCreated(new Date());

			object.setTaskvalue(taskvalue);
			super.saveObject(object);

		} else if ("resize".equals(doit)) {
			// 结束时间更改
			TaskList taskList = getObject(object);

			String minuteDelta = params.get("minuteDelta");

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(taskList.getPlanendtime());

			calendar.add(Calendar.MINUTE, Integer.parseInt(minuteDelta));
			taskList.setPlanendtime(calendar.getTime());

			super.saveObject(taskList);
		} else if ("del".equals(doit)) {

			deleteObject(object);
		} else if ("closure".equals(doit)) {
			// 待办任务完成

			toDoMatter.get(params.get("instance")).closureUserTask(params.get("taskid"));

		} else if ("drop".equals(doit)) {
			// 开始，结束时间均修改
			TaskList taskList = getObject(object);

			// 移动天数
			int dayDelta = Integer.parseInt(params.get("dayDelta"));
			// 移动分钟数
			int minuteDelta = Integer.parseInt(params.get("minuteDelta"));

			Calendar beginCalendar = Calendar.getInstance();
			beginCalendar.setTime(taskList.getPlanbegintime());
			if (0 != dayDelta) {
				beginCalendar.add(Calendar.DATE, dayDelta);
			}
			beginCalendar.add(Calendar.MINUTE, minuteDelta);
			taskList.setPlanbegintime(beginCalendar.getTime());

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(taskList.getPlanendtime());
			if (0 != dayDelta) {
				endCalendar.add(Calendar.DATE, dayDelta);
			}
			endCalendar.add(Calendar.MINUTE, minuteDelta);
			taskList.setPlanendtime(endCalendar.getTime());

			super.saveObject(taskList);
		} else if ("edit".equals(params.get("ac"))) {
			TaskList taskList = getObject(object);
			taskList.copyNotNullProperty(object);

			object = taskList;

			String planbegintime = params.get("startd") + " " + params.get("startt");
			String planendtime = params.get("endd") + " " + params.get("endt");

			object.setPlanbegintime(DatetimeOpt.convertStringToDate(planbegintime, "yyyy-MM-dd HH:mm:ss"));
			object.setPlanendtime(DatetimeOpt.convertStringToDate(planendtime, "yyyy-MM-dd HH:mm:ss"));

			object.setTaskvalue(taskvalue);

			// 填写实际开始时间后，状态更新为处理中
			if (null != object.getBegintime() && "Cr".equals(object.getTaskstatus())) {
				object.setTaskstatus("P");
			}

			super.saveObject(object);

			Map<String, String> jsonResult = new HashMap<String, String>();
			jsonResult.put("info", "");
			jsonResult.put("status", "y");

			return JSONObject.fromObject(jsonResult).toString();
		}

		return null;

	}

	@Override
	public List<? extends IToDoMatterData> getUserTaskList(String usercode, Date start, Date end) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("taskowner", usercode);
		filterMap.put("planbegintimeStr", DatetimeOpt.convertDateToString(start, "yyyy-MM-dd HH:mm:ss"));
		filterMap.put("planendtimeStr", DatetimeOpt.convertDateToString(end, "yyyy-MM-dd HH:mm:ss"));
		filterMap.put("taskstatusNot", "C");
		return listObjects(filterMap);
	}

	@Override
	public List<? extends IToDoMatterData> getUserTaskList(String usercode, String tasktype, Date start, Date end, PageDesc pageDesc) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Map<String, String>> taskListOwner = getTaskListOwner(usercode);

		List<String> usercodes = new ArrayList<String>();
		if ("O".equals(tasktype)) {
			Map<String, String> offices = taskListOwner.get("Offices");
			usercodes.addAll(offices.keySet());
		} else if ("L".equals(tasktype)) {
			Map<String, String> leadership = taskListOwner.get("Leadership");
			usercodes.addAll(leadership.keySet());
		} else if("P".equals(tasktype)){
			usercodes.add(usercode);
		} else {
		    Map<String, String> offices = taskListOwner.get("Offices");
            usercodes.addAll(offices.keySet());
            
            Map<String, String> leadership = taskListOwner.get("Leadership");
            usercodes.addAll(leadership.keySet());
            
            usercodes.add(usercode);
		}

		filterMap.put("usercodes", usercodes);
		filterMap.put("planbegintime", start);
		filterMap.put("planendtime", end);
		filterMap.put("taskstatus", "C");

		return taskListDao.listAll(filterMap, pageDesc);
	}

	@Override
	public boolean closureUserTask(Serializable id) {
		taskListDao.deleteObjectById(Long.parseLong(String.valueOf(id)));
		return true;
	}

	@Override
	public void createUserTask(String usercode, String title, Date start, Date end, boolean allday) {
		TaskList object = new TaskList();
		object.setTaskowner(usercode);
		object.setTasktitle(title);
		object.setTasktag("N");
		object.setTaskrank("N");
		object.setTaskstatus("Cr");
		object.setTasktype("P");
		object.setOptid("TASKLIST");

		object.setPlanbegintime(start);
		object.setPlanendtime(end);

		object.setCreator(usercode);
		object.setCreated(new Date());

		String taskvalue = "{\"isallday\" : " + allday + "}";
		object.setTaskvalue(taskvalue);
		super.saveObject(object);

	}

	@Override
	public boolean updateUserTaskToResize(Serializable id, int dayDelta, int minuteDelta) {
		TaskList taskList = getObjectById(Long.parseLong(String.valueOf(id)));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(taskList.getPlanendtime());

		calendar.add(Calendar.MINUTE, minuteDelta);
		taskList.setPlanendtime(calendar.getTime());

		super.saveObject(taskList);
		return true;
	}

	@Override
	public boolean updateUserTaskToDrop(Serializable id, int dayDelta, int minuteDelta) {
		TaskList taskList = getObjectById(Long.parseLong(String.valueOf(id)));

		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(taskList.getPlanbegintime());
		if (0 != dayDelta) {
			beginCalendar.add(Calendar.DATE, dayDelta);
		}
		beginCalendar.add(Calendar.MINUTE, minuteDelta);
		taskList.setPlanbegintime(beginCalendar.getTime());

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(taskList.getPlanendtime());
		if (0 != dayDelta) {
			endCalendar.add(Calendar.DATE, dayDelta);
		}
		endCalendar.add(Calendar.MINUTE, minuteDelta);
		taskList.setPlanendtime(endCalendar.getTime());

		super.saveObject(taskList);

		return true;
	}

	@Override
	public TaskCatalog getTaskCatalog() {

		return new TaskCatalog("taskListManager", "个人待办事项", "/app/taskList!edit.do?taskid=", "change");
	}

	@Override
	public Map<String, Map<String, String>> getTaskListOwner(String usercode) {
		// List<FDatadictionary> tasktypes = CodeRepositoryUtil.getDictionary("TASKTYPE");
		Map<String, Map<String, String>> tasktypes = new HashMap<String, Map<String, String>>();

		Map<String, String> personal = new HashMap<String, String>();

		FUserinfo userinfo = CodeRepositoryUtil.getUserInfoByCode(usercode);

		personal.put(usercode, userinfo.getUsername());
		tasktypes.put("Personal", personal);

		Set<FUserinfo> unituserinfos = CodeRepositoryUtil.getUnitUsers(userinfo.getPrimaryUnit());

		Map<String, String> offices = new HashMap<String, String>();

		for (FUserinfo u : unituserinfos) {
			offices.put(u.getUsercode(), u.getUsername());
		}
		tasktypes.put("Offices", offices);
		//领导人员数据
		Map<String, String> leadership = new HashMap<String, String>();
		
		
		leadership.put(usercode, userinfo.getUsername());
		tasktypes.put("Leadership", leadership);

		return tasktypes;
	}

	@Override
	public String saveObject(Map<String, String> params, TaskList object, List<String> filecodes) {
		String result = saveObject(params, object);

		// saveAnnex(object, filecodes);
		return result;
	}

	@Override
	public void saveObject(TaskList object, List<String> filecodes) {

		List<TaskListAnnex> annexList = getAnnexList(object, filecodes);
		if (!CollectionUtils.isEmpty(annexList)) {
			object.getTaskListAnnexs().addAll(annexList);
		}

		saveTask(object);

	}

	private List<TaskListAnnex> getAnnexList(TaskList object, List<String> filecodes) {
		List<TaskListAnnex> taskListAnnexs = new ArrayList<TaskListAnnex>();
		if (!CollectionUtils.isEmpty(filecodes)) {

			for (String filecode : filecodes) {
				if (!StringUtils.hasText(filecode)) {
					continue;
				}
				taskListAnnexs.add(new TaskListAnnex(null, object, new FileinfoFs(filecode)));
			}

		}

		return taskListAnnexs;
	}

	@Override
	public void saveTask(TaskList object) {
		object.setTasktag("N");
		object.setTaskrank("N");
		object.setTaskstatus("Cr");
		// object.setTasktype("P");
		object.setOptid("TASKLIST");

		object.setCreated(new Date());

		String taskvalue = "{\"isallday\" : " + false + "}";
		object.setTaskvalue(taskvalue);
		super.saveObject(object);

	}

    @Override
    public void deleteObject(TaskList o) {
        for (TaskListAnnex annex : o.getTaskListAnnexs()) {
            taskListAnnexManager.deleteObjectById(annex.getTlaid());
        }
        super.deleteObject(o);
    }
	
}
