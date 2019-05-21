package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaNetapplication;
import com.centit.oa.dao.OaNetapplicationDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaNetapplicationManager;

public class OaNetapplicationManagerImpl extends BaseEntityManagerImpl<OaNetapplication>
	implements OaNetapplicationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaNetapplicationManager.class);

	private OaNetapplicationDao oaNetapplicationDao ;
	public void setOaNetapplicationDao(OaNetapplicationDao baseDao)
	{
		this.oaNetapplicationDao = baseDao;
		setBaseDao(this.oaNetapplicationDao);
	}
	
}

