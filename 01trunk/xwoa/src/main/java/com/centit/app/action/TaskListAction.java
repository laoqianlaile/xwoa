package com.centit.app.action;

import java.io.IOException;
import java.util.Arrays;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import com.centit.app.po.TaskList;
import com.centit.app.service.IToDoMatter;
import com.centit.app.service.TaskListAnnexManager;
import com.centit.app.service.TaskListManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;

public class TaskListAction extends BaseEntityDwzAction<TaskList> {
	private static final Log log = LogFactory.getLog(TaskListAction.class);

	// private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");

	private static final long serialVersionUID = 1L;
	private TaskListManager taskListMag;
	
	private IToDoMatter toDoMatter;
	
	
	//@Resource
	//@NotNull
	private TaskListAnnexManager taskListAnnexManager;

	
    public void setTaskListAnnexManager(TaskListAnnexManager taskListAnnexManager) {
		this.taskListAnnexManager = taskListAnnexManager;
	}

	public void setTaskListManager(TaskListManager basemgr) {
		taskListMag = basemgr;
		this.setBaseEntityManager(taskListMag);
	}
	
	

	public void setToDoMatter(IToDoMatter toDoMatter) {
		this.toDoMatter = toDoMatter;
	}

	@Override
	public String edit() {
		object = taskListMag.getObject(object);

		request.setAttribute("isallday", false);
		if (null != object) {
			if (StringUtils.hasText(object.getTaskvalue())) {
				request.setAttribute("isallday", JSONObject.fromObject(object.getTaskvalue()).getBoolean("isallday"));
			}
		}
		
		request.setAttribute("instance", "taskListManager");
		return EDIT;
	}

	@Override
	public String save() {
		TaskList tasklist = baseEntityManager.getObject(object);
		
		
		if(null != tasklist) {
			tasklist.copyNotNullProperty(object);
			object = tasklist;
			
		}
		
		
		String isallday = request.getParameter("isallday");
		if("0".equals(isallday)){
			object.setPlanbegintime(DatetimeOpt.convertStringToDate(DatetimeOpt.convertDateToString(object.getPlanbegintime(), "yyyy-MM-dd") + " " + request.getParameter("startt"), "yyyy-MM-dd HH:mm:ss"));
			object.setPlanendtime(DatetimeOpt.convertStringToDate(DatetimeOpt.convertDateToString(object.getPlanendtime(), "yyyy-MM-dd") + " " + request.getParameter("endt"), "yyyy-MM-dd HH:mm:ss"));
		}
		
		object.setTaskvalue("{\"isallday\" : "+ ("1".equals(isallday)) +"}");
		
		String filecodes = WebUtils.findParameterValue(request, "filecodes");
		
		String tasktype = WebUtils.findParameterValue(request, "tasktype");
		
		FUserinfo userinfo = (FUserinfo)getLoginUser();
		String usercode = null;
		if("L".equals(tasktype) || "O".equals(tasktype)) {
			usercode = WebUtils.findParameterValue(request, "usercode");
		} else {
			usercode = userinfo.getUsercode();
		}
		
		object.setTaskowner(usercode);
		object.setCreator(usercode);
		
		taskListMag.saveObject(object, Arrays.asList(filecodes.split(",")));
		
		return SUCCESS;
	}
	
	public void deleteTasksAnnex() throws IOException {
		
		
		
		taskListAnnexManager.deleteObjectById(WebUtils.findParameterValue(request, "tlaid"));
		
		ServletActionContext.getResponse().getWriter().println(true);
	}

    @Override
    public String delete() {
        
        taskListMag.deleteObject(object);
        
        return SUCCESS;
    }

    @Override
    public String view() {
        object = taskListMag.getObject(object);
        
        request.setAttribute("instance", "taskListManager");
        return VIEW;
    }
}
