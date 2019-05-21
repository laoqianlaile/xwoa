package com.centit.sys.action;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.po.OptRunRec;
import com.centit.sys.service.OptRunRecManager;
	

public class OptRunRecAction  extends BaseEntityExtremeAction<OptRunRec>  {
	private static final long serialVersionUID = 1L;
	private OptRunRecManager optRunRecMag;
	public void setOptRunRecManager(OptRunRecManager basemgr)
	{
		optRunRecMag = basemgr;
		this.setBaseEntityManager(optRunRecMag);
	}

	
	
		
}
