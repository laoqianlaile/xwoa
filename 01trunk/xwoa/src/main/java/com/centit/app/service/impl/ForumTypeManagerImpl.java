package com.centit.app.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.app.po.ForumType;
import com.centit.app.dao.ForumTypeDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.service.ForumTypeManager;

public class ForumTypeManagerImpl extends BaseEntityManagerImpl<ForumType>
	implements ForumTypeManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ForumTypeManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private ForumTypeDao forumTypeDao ;
	public void setForumTypeDao(ForumTypeDao baseDao)
	{
		this.forumTypeDao = baseDao;
		setBaseDao(this.forumTypeDao);
	}
	
}

