package com.centit.app.action;

import com.centit.app.po.ReplyAnnex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

	

import com.centit.core.action.BaseEntityDwzAction;

		

import com.centit.app.service.ReplyAnnexManager;
	

public class ReplyAnnexAction  extends BaseEntityDwzAction<ReplyAnnex>  {
	private static final Log log = LogFactory.getLog(ReplyAnnexAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private ReplyAnnexManager replyAnnexMag;
	public void setReplyAnnexManager(ReplyAnnexManager basemgr)
	{
		replyAnnexMag = basemgr;
		this.setBaseEntityManager(replyAnnexMag);
	}

	
	
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
