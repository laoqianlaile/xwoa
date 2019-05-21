package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaLeaveapply;
import com.centit.oa.dao.OaLeaveapplyDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaLeaveapplyManager;

public class OaLeaveapplyManagerImpl extends BaseEntityManagerImpl<OaLeaveapply>
	implements OaLeaveapplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaLeaveapplyManager.class);

	private OaLeaveapplyDao oaLeaveapplyDao ;
	public void setOaLeaveapplyDao(OaLeaveapplyDao baseDao)
	{
		this.oaLeaveapplyDao = baseDao;
		setBaseDao(this.oaLeaveapplyDao);
	}
	
}

