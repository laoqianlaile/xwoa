package com.centit.oa.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.service.OaOnlineItemsManager;

public class OaOnlineItemsAction  extends BaseEntityExtremeAction<OaOnlineItems>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaOnlineItemsAction.class);
	private static final long serialVersionUID = 1L;
	private OaOnlineItemsManager oaOnlineItemsMag;
	public void setOaOnlineItemsManager(OaOnlineItemsManager basemgr)
	{
		oaOnlineItemsMag = basemgr;
		this.setBaseEntityManager(oaOnlineItemsMag);
	}

	private List<OaSurveydetail> oaSurveydetails;
	public List<OaSurveydetail> getNewOaSurveydetails() {
		return this.oaSurveydetails;
	}
	public void setNewOaSurveydetails(List<OaSurveydetail> oaSurveydetails) {
		this.oaSurveydetails = oaSurveydetails;
	}
	
	
		
	public String save(){
		object.replaceOaSurveydetails( oaSurveydetails);
		
		return super.save();
	}
		
}
