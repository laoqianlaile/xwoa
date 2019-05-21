package com.centit.oa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaServiceentrance;
import com.centit.oa.service.OaServiceentranceManager;
	

public class OaServiceentranceAction  extends BaseEntityExtremeAction<OaServiceentrance>  {
	private static final Log log = LogFactory.getLog(OaServiceentranceAction.class);
	private static final long serialVersionUID = 1L;
	private OaServiceentranceManager oaServiceentranceMag;
	public void setOaServiceentranceManager(OaServiceentranceManager basemgr)
	{
		oaServiceentranceMag = basemgr;
		this.setBaseEntityManager(oaServiceentranceMag);
	}

	
	
		
}
