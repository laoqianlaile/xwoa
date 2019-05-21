package com.centit.app.service;

import java.io.Serializable;
import java.util.Date;

public interface IToDoMatterData {
	/**
	 * 数据库主键
	 * @return
	 */
	Serializable getId();
	
	/**
	 * 标题
	 * @return
	 */
	String getTitle();
	
	/**
	 * 待办事项开始时间 
	 * @return
	 */
	Date getStart();
	
	/**
	 * 待办事项结束时间 
	 * @return
	 */
	Date getEnd();
	
	/**
	 * 待办事项级别标记,可选颜色为 数据字典中TASKTAG，extracode字段
	 * @return
	 */
	String getTasktag();
	
	/**
	 * 开始结束日期的跨度是否为整天，是，True，否，False
	 * @return
	 */
	boolean isAllDay();
	
	/**
	 * 是否可以拖动日历控件进行编辑
	 * @return
	 */
	boolean isEditable();
	
	/**
	 * 跳转至当前待办业务的url，例：/sys/userDef!view.do?a=1&b=2
	 * @return
	 */
	String getUrl();
	/**
	 * 持有人
	 * @return
	 */
	String getOwner();
}
