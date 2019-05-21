package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.dao.OaOnlineItemsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaOnlineItemsManager;

public class OaOnlineItemsManagerImpl extends BaseEntityManagerImpl<OaOnlineItems>
	implements OaOnlineItemsManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaOnlineItemsManager.class);

	private OaOnlineItemsDao oaOnlineItemsDao ;
	public void setOaOnlineItemsDao(OaOnlineItemsDao baseDao)
	{
		this.oaOnlineItemsDao = baseDao;
		setBaseDao(this.oaOnlineItemsDao);
	}

}

