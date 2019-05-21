package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaLeaveReply;
import com.centit.oa.dao.OaLeaveReplyDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaLeaveReplyManager;

public class OaLeaveReplyManagerImpl extends BaseEntityManagerImpl<OaLeaveReply>
	implements OaLeaveReplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaLeaveReplyManager.class);

	private OaLeaveReplyDao oaLeaveReplyDao ;
	public void setOaLeaveReplyDao(OaLeaveReplyDao baseDao)
	{
		this.oaLeaveReplyDao = baseDao;
		setBaseDao(this.oaLeaveReplyDao);
	}
	
}

