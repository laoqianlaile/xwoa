package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaKqreception;
import com.centit.oa.dao.OaKqreceptionDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaKqreceptionManager;

public class OaKqreceptionManagerImpl extends BaseEntityManagerImpl<OaKqreception>
	implements OaKqreceptionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaKqreceptionManager.class);

	private OaKqreceptionDao oaKqreceptionDao ;
	public void setOaKqreceptionDao(OaKqreceptionDao baseDao)
	{
		this.oaKqreceptionDao = baseDao;
		setBaseDao(this.oaKqreceptionDao);
	}
	
}

