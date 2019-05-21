package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaEquipmentpurchase;
import com.centit.oa.dao.OaEquipmentpurchaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaEquipmentpurchaseManager;

public class OaEquipmentpurchaseManagerImpl extends BaseEntityManagerImpl<OaEquipmentpurchase>
	implements OaEquipmentpurchaseManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaEquipmentpurchaseManager.class);

	private OaEquipmentpurchaseDao oaEquipmentpurchaseDao ;
	public void setOaEquipmentpurchaseDao(OaEquipmentpurchaseDao baseDao)
	{
		this.oaEquipmentpurchaseDao = baseDao;
		setBaseDao(this.oaEquipmentpurchaseDao);
	}
	
}

