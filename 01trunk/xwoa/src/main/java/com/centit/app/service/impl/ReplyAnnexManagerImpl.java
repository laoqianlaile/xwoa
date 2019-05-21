package com.centit.app.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.app.po.ReplyAnnex;
import com.centit.app.dao.ReplyAnnexDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.service.ReplyAnnexManager;

public class ReplyAnnexManagerImpl extends BaseEntityManagerImpl<ReplyAnnex>
	implements ReplyAnnexManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ReplyAnnexManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private ReplyAnnexDao replyAnnexDao ;
	public void setReplyAnnexDao(ReplyAnnexDao baseDao)
	{
		this.replyAnnexDao = baseDao;
		setBaseDao(this.replyAnnexDao);
	}
	
}

