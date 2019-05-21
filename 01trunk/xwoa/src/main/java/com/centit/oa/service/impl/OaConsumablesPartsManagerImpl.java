package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaConsumablesParts;
import com.centit.oa.dao.OaConsumablesPartsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaConsumablesPartsManager;

public class OaConsumablesPartsManagerImpl extends BaseEntityManagerImpl<OaConsumablesParts>
	implements OaConsumablesPartsManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaConsumablesPartsManager.class);

	private OaConsumablesPartsDao oaConsumablesPartsDao ;
	public void setOaConsumablesPartsDao(OaConsumablesPartsDao baseDao)
	{
		this.oaConsumablesPartsDao = baseDao;
		setBaseDao(this.oaConsumablesPartsDao);
	}
	
}

