package com.centit.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.dao.TaskListAnnexDao;
import com.centit.app.po.TaskListAnnex;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.TaskListAnnexManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class TaskListAnnexManagerImpl extends BaseEntityManagerImpl<TaskListAnnex> implements TaskListAnnexManager {
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(TaskListAnnexManager.class);
	private TaskListAnnexDao taskListAnnexDao;
	
	
	private FileinfoFsManager fileinfoFsManager;
	
	
    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
		this.fileinfoFsManager = fileinfoFsManager;
	}





	public void setTaskListAnnexDao(TaskListAnnexDao taskListAnnexDao) {
		this.taskListAnnexDao = taskListAnnexDao;
		baseDao = taskListAnnexDao;
	}





	@Override
    public void saveBatch(List<TaskListAnnex> taskListAnnexs) {
        taskListAnnexDao.saveBatch(taskListAnnexs);
        
    }





	@Override
	public void deleteObjectById(Serializable id) {
		TaskListAnnex taskListAnnex = getObjectById(Long.valueOf((String)id));
		super.deleteObject(taskListAnnex);
		fileinfoFsManager.deleteObjectById(taskListAnnex.getFileinfoFs().getFilecode());
		
	}



}
