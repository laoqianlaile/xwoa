package com.centit.app.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.app.po.TaskCatalog;
import com.centit.core.utils.PageDesc;

/**
 * 
 * 待办事项数据获取接口
 * 
 * @author sx
 * @create 2013-8-13
 * @version
 */
public interface IToDoMatter {

	/**
	 * 待办事项信息
	 * 
	 * @return
	 */
	TaskCatalog getTaskCatalog();

	/**
	 * 用户待办事项
	 * 
	 * @param usercode
	 *            用户代码
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	List<? extends IToDoMatterData> getUserTaskList(String usercode, Date start, Date end);
	/**
	 * 用户待办事项
	 * 
	 * @param usercode
	 *            用户代码
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	List<? extends IToDoMatterData> getUserTaskList(String usercode, String tasktype, Date start, Date end, PageDesc pageDesc);
	
	/**
	 * 快捷创建待办事项
	 * @param usercode 用户代码
	 * @param title 标题
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param allday 是否整天
	 * @return
	 */
	void createUserTask(String usercode, String title, Date start, Date end, boolean allday);
	
	/**
	 * 更新用户待办事件
	 * 时间整体偏移
	 * @param id 主键
	 * @param dayDelta 天数偏移量
	 * @param minuteDelta 分钟数偏移量
	 * @return
	 */
	boolean updateUserTaskToResize(Serializable id, int dayDelta, int minuteDelta);
	
	/**
	 * 更新用户待办事件
	 * 结束时间偏移
	 * @param id 主键
	 * @param dayDelta 天数偏移量
	 * @param minuteDelta 分钟数偏移量
	 * @return
	 */
	boolean updateUserTaskToDrop(Serializable id, int dayDelta, int minuteDelta);

	/**
	 * 完成待办事项
	 * 
	 * @param id
	 *            主键
	 * @return true,完成 false,未完成
	 */
	boolean closureUserTask(Serializable id);

	/**
	 * 不同的任务类别 
	 * 任务属主信息[key=usercode,value=username]
	 * @param usercode 当前登录用户代码
	 * @return
	 */
	Map<String, Map<String, String>> getTaskListOwner(String usercode);
}
