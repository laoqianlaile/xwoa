package com.centit.oa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.service.OaPowergroupDetailManager;
	

public class OaPowergroupDetailAction  extends BaseEntityExtremeAction<OaPowergroupDetail>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaPowergroupDetailAction.class);
	private static final long serialVersionUID = 1L;
	private OaPowergroupDetailManager oaPowergroupDetailMag;
	public void setOaPowergroupDetailManager(OaPowergroupDetailManager basemgr)
	{
		oaPowergroupDetailMag = basemgr;
		this.setBaseEntityManager(oaPowergroupDetailMag);
	}

	
	
		
}
