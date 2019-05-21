package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.VSubprocessProjectTaskList;
import com.centit.dispatchdoc.dao.VSubprocessProjectTaskListDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.VSubprocessProjectTaskListManager;

public class VSubprocessProjectTaskListManagerImpl extends BaseEntityManagerImpl<VSubprocessProjectTaskList>
	implements VSubprocessProjectTaskListManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VSubprocessProjectTaskListManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private VSubprocessProjectTaskListDao VSubprocessProjectTaskListDao ;
	public void setVSubprocessProjectTaskListDao(VSubprocessProjectTaskListDao baseDao)
	{
		this.VSubprocessProjectTaskListDao = baseDao;
		setBaseDao(this.VSubprocessProjectTaskListDao);
	}
	
}

