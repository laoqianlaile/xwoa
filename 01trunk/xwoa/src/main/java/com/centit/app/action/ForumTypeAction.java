package com.centit.app.action;

import com.centit.app.po.ForumType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

	

import com.centit.core.action.BaseEntityDwzAction;

		

import com.centit.app.service.ForumTypeManager;
	

public class ForumTypeAction  extends BaseEntityDwzAction<ForumType>  {
	private static final Log log = LogFactory.getLog(ForumTypeAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private ForumTypeManager forumTypeMag;
	public void setForumTypeManager(ForumTypeManager basemgr)
	{
		forumTypeMag = basemgr;
		this.setBaseEntityManager(forumTypeMag);
	}

	
	
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
