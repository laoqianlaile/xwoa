package com.centit.app.service;

import java.util.List;
import java.util.Map;

import com.centit.app.po.TaskList;
import com.centit.core.service.BaseEntityManager;

public interface TaskListManager extends BaseEntityManager<TaskList> {
	/**
	 * 获取桌面所需要的数据
	 * 
	 * @param usercode
	 * @return
	 */
	String getTaskListData(Map<String, String> params, TaskList object);

	/**
	 * 获取当前用户待办事项
	 * 
	 * @param params
	 * @return
	 */
	String getUserTaskList(Map<String, String> params, TaskList object);
	
	/**
	 * 获取当前用户待办事项
	 * 
	 * @param params
	 * @return
	 */
	String getUserTaskListById(Map<String, String> params, TaskList object);

	
	String saveObject(Map<String, String> params, TaskList object);
	
	
	void saveTask(TaskList object);
	
	
	void saveObject(TaskList object, List<String> filecodes);
	
	
	String saveObject(Map<String, String> params, TaskList object, List<String> filecodes);
}
