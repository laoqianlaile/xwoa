package com.centit.app.action;

import com.centit.app.po.ThreadAnnex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

	

import com.centit.core.action.BaseEntityDwzAction;

		

import com.centit.app.service.ThreadAnnexManager;
	

public class ThreadAnnexAction  extends BaseEntityDwzAction<ThreadAnnex>  {
	private static final Log log = LogFactory.getLog(ThreadAnnexAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private ThreadAnnexManager threadAnnexMag;
	public void setThreadAnnexManager(ThreadAnnexManager basemgr)
	{
		threadAnnexMag = basemgr;
		this.setBaseEntityManager(threadAnnexMag);
	}

	
	
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
