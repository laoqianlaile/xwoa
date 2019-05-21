package com.centit.powerruntime.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerruntime.po.OaLeaderunits;
import com.centit.powerruntime.service.OaLeaderunitsManager;
import com.centit.workflow.sample.optmodel.BaseWFEntityAction;
	

public class OaLeaderunitsAction  extends BaseWFEntityAction<OaLeaderunits>  {
	private static final Log log = LogFactory.getLog(OaLeaderunitsAction.class);
	private static final long serialVersionUID = 1L;
	private OaLeaderunitsManager oaLeaderunitsMag;
	public void setOaLeaderunitsManager(OaLeaderunitsManager basemgr)
	{
		oaLeaderunitsMag = basemgr;
		this.setBaseEntityManager(oaLeaderunitsMag);
	}

	
	
		
}
