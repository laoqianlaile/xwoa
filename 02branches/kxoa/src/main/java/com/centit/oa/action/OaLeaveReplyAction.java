package com.centit.oa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaLeaveReply;
import com.centit.oa.service.OaLeaveReplyManager;
	

public class OaLeaveReplyAction  extends BaseEntityExtremeAction<OaLeaveReply>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaLeaveReplyAction.class);
	private static final long serialVersionUID = 1L;
	private OaLeaveReplyManager oaLeaveReplyMag;
	public void setOaLeaveReplyManager(OaLeaveReplyManager basemgr)
	{
		oaLeaveReplyMag = basemgr;
		this.setBaseEntityManager(oaLeaveReplyMag);
	}

	
	
		
}
