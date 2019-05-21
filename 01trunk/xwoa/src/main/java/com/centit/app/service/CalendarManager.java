package com.centit.app.service;

import java.util.Map;

import com.centit.app.service.IToDoMatter;

public interface CalendarManager {
	/**
	 * 获取桌面所需要的数据
	 * 
	 * @param usercode
	 * @return
	 */
	String getTaskListData(Map<String, String> params);

	/**
	 * 获取当前用户待办事项
	 * 
	 * @param params
	 * @return
	 */
	String getUserTaskList(Map<String, String> params);
	
	Map<String, IToDoMatter> getToDoMatter();
}
