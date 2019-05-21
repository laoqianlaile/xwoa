package com.centit.powerruntime.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.MipLog;
import com.centit.powerruntime.service.MipLogManager;
	

public class MipLogAction  extends BaseEntityExtremeAction<MipLog>  {
	private static final Log log = LogFactory.getLog(MipLogAction.class);
	private static final long serialVersionUID = 1L;
	private MipLogManager mipLogMag;
	public void setMipLogManager(MipLogManager basemgr)
	{
		mipLogMag = basemgr;
		this.setBaseEntityManager(mipLogMag);
	}

	
	
		
}
