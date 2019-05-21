package com.centit.dispatchdoc.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.SubprocessProject;
import com.centit.dispatchdoc.dao.SubprocessProjectDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.SubprocessProjectManager;

public class SubprocessProjectManagerImpl extends BaseEntityManagerImpl<SubprocessProject>
	implements SubprocessProjectManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(SubprocessProjectManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private SubprocessProjectDao subprocessProjectDao ;
	public void setSubprocessProjectDao(SubprocessProjectDao baseDao)
	{
		this.subprocessProjectDao = baseDao;
		setBaseDao(this.subprocessProjectDao);
	}
	 public String generateNextDjId() {
	        return subprocessProjectDao.genNextDjID();
	    }
    public List<String> getOptBaseNotOverList(String djId) {
	     return subprocessProjectDao.getOptBaseNotOverList(djId);
	 }
}

