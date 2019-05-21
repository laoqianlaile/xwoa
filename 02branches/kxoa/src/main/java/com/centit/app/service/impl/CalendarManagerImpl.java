package com.centit.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.app.po.TaskList;
import com.centit.app.service.CalendarManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.app.service.IToDoMatter;
import com.centit.app.service.IToDoMatterData;

public class CalendarManagerImpl implements CalendarManager {

	private Map<String, IToDoMatter> toDoMatter = new HashMap<String, IToDoMatter>();

	public void setToDoMatter(Map<String, IToDoMatter> toDoMatter) {
		this.toDoMatter = toDoMatter;
	}
	

	@Override
	public Map<String, IToDoMatter> getToDoMatter() {
		return toDoMatter;
	}


	@Override
	public String getTaskListData(Map<String, String> params) {
		String ac = params.get("ac");
		if ("getCalendar".equals(ac)) {

			return getUserTaskList(params);
		} else if ("quick".equals(ac) || "edit".equals(ac)) {
			String doit = params.get("do");
			String instance = params.get("instance");
			String id = params.get("id");
			String dayDelta = params.get("dayDelta");
			String minuteDelta = params.get("minuteDelta");
			
			// 快捷创建
			if ("add".equals(doit)) {
				Date start = DatetimeOpt.convertStringToDate(params.get("start"), "yyyy-MM-dd HH:mm:ss");
				Date end = DatetimeOpt.convertStringToDate(params.get("end"), "yyyy-MM-dd HH:mm:ss");

				//单个实例进行创建
				if(StringUtils.hasText(instance) && toDoMatter.containsKey(instance)){
					toDoMatter.get(params.get("instance")).createUserTask(params.get("usercode"), params.get("title"), start, end,
							"1".equals(params.get("isallday")));
				} else {
					for (Entry<String, IToDoMatter> entry : toDoMatter.entrySet()) {
						entry.getValue().createUserTask(params.get("usercode"), params.get("title"), start, end,
								"1".equals(params.get("isallday")));
					}
				}
				
				
			} else if ("resize".equals(doit)) {
				toDoMatter.get(instance).updateUserTaskToResize(id, Integer.parseInt(dayDelta),
						Integer.parseInt(minuteDelta));
			} else if ("drop".equals(doit)) {
				toDoMatter.get(instance).updateUserTaskToDrop(id, Integer.parseInt(dayDelta),
						Integer.parseInt(minuteDelta));
			} else if("del".equals(doit)) {
				if(StringUtils.hasText(instance) && toDoMatter.containsKey(instance)){
					toDoMatter.get(params.get("instance")).closureUserTask(id);
				} else {
					for (Entry<String, IToDoMatter> entry : toDoMatter.entrySet()) {
						entry.getValue().closureUserTask(id);
					}
				}
			}
		}
		/*else if ("getDate".equals(ac)) {
			// return getUserTaskListById(params);
		}*/
		return null;
	}

	@Override
	public String getUserTaskList(Map<String, String> params) {
	    //TODO 山东无需在日历上显示数据，直接返回空值
	    if(!CollectionUtils.isEmpty(params)) {
	        return "";
	    }
		Calendar start = Calendar.getInstance();
		start.setTime(new Date(Long.parseLong(params.get("start")) * 1000));
		Calendar end = Calendar.getInstance();
		end.setTime(new Date(Long.parseLong(params.get("end")) * 1000 - 1000));

		List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
		
		String instance = params.get("instance");
		String[] instances = instance.split(",");

		//单个模块嵌入式调用此块功能
		if(!ArrayUtils.isEmpty(instances)) {
			for (String s : instances) {
				getJsonResult(params, start, end, jsonResult, s, toDoMatter.get(s));
			}
		} else {
			for (Entry<String, IToDoMatter> entry : toDoMatter.entrySet()) {
				getJsonResult(params, start, end, jsonResult, entry.getKey(), entry.getValue());
			}
		}
		
		
//		if(StringUtils.hasText(params.get("instance")) && toDoMatter.containsKey(params.get("instance"))){
//			getJsonResult(params, start, end, jsonResult, params.get("instance"), toDoMatter.get(params.get("instance")));
//		} else {
//			
//		}

		return JSONArray.fromObject(jsonResult).toString();
	}
	
	private void getJsonResult(Map<String, String> params, Calendar start, Calendar end,
			List<Map<String, Object>> jsonResult, String key, IToDoMatter value) {
		List<? extends IToDoMatterData> tasklist = value.getUserTaskList(params.get("usercode"), params.get("tasktype"),
				start.getTime(), end.getTime(), null);
		// 添加实例，操作相关实现类接口
		List<Map<String, Object>> result = putDataToMap(tasklist, params);

		putParams(result, "instance", key);
		putParams(result, "taskCatalog", value.getTaskCatalog());
		jsonResult.addAll(result);
	}

	/**
	 * 将数据转换为日历控件所需要的Json数据格式
	 * 
	 * @param tasklist
	 * @return
	 */
	@SuppressWarnings("static-method")
	private List<Map<String, Object>> putDataToMap(List<? extends IToDoMatterData> tasklist, Map<String, String> params) {
		String contextPath = params.get("contextPath");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (IToDoMatterData data : tasklist) {
			Map<String, Object> task = new HashMap<String, Object>();
			//用户owner
			
			TaskList object = (TaskList)data;
			
			String owner = CodeRepositoryUtil.getValue("usercode", object.getTaskowner());

			task.put("id", data.getId());
			
			String title = '[' + owner + "] " + data.getTitle();
			if(15 < title.length()) {
				title = title.substring(0, 15) + "...";
			}
			
			task.put("title", title);
			task.put("start", DatetimeOpt.convertDateToString(data.getStart(), "yyyy-MM-dd HH:mm:ss"));
			task.put("end", DatetimeOpt.convertDateToString(data.getEnd(), "yyyy-MM-dd HH:mm:ss"));
			
			

			// 显示标记颜色
			if (StringUtils.hasText(data.getTasktag())) {
				task.put("tasktag", CodeRepositoryUtil.getDataPiece("TASKTAG", data.getTasktag()).getExtracode());
			}

			// 可编辑
			task.put("editable", data.isEditable());

			task.put("allDay", data.isAllDay());

			if (StringUtils.hasText(data.getUrl())) {
				StringBuilder sb = new StringBuilder();
				List<String> ignore = Arrays.asList("contextPath", "usercode");
				for (Entry<String, String> entry : params.entrySet()) {
					if(ignore.contains(entry.getKey())) {
						continue;
					}
					sb.append('&' + entry.getKey() + '=' + entry.getValue());
				}
				task.put("url", contextPath + data.getUrl() + sb);
			}
			result.add(task);
		}

		return result;
	}

	@SuppressWarnings("static-method")
	private void putParams(List<Map<String, Object>> result, String key, Object value) {
		for (Map<String, Object> map : result) {
			map.put(key, value);
		}

	}

}
