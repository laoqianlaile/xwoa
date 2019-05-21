package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaLeavereported;
import com.centit.oa.dao.OaLeavereportedDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaLeavereportedManager;

public class OaLeavereportedManagerImpl extends BaseEntityManagerImpl<OaLeavereported>
	implements OaLeavereportedManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaLeavereportedManager.class);

	private OaLeavereportedDao oaLeavereportedDao ;
	public void setOaLeavereportedDao(OaLeavereportedDao baseDao)
	{
		this.oaLeavereportedDao = baseDao;
		setBaseDao(this.oaLeavereportedDao);
	}
	
}

