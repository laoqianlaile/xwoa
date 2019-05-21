package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaFeedback;
import com.centit.oa.dao.OaFeedbackDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaFeedbackManager;

public class OaFeedbackManagerImpl extends BaseEntityManagerImpl<OaFeedback>
	implements OaFeedbackManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaFeedbackManager.class);

	private OaFeedbackDao oaFeedbackDao ;
	public void setOaFeedbackDao(OaFeedbackDao baseDao)
	{
		this.oaFeedbackDao = baseDao;
		setBaseDao(this.oaFeedbackDao);
	}
    @Override
    public String getNextKey() {
 
        return "QKFK"+oaFeedbackDao.getNextKeyBySequence("s_OaFeedback", 12);
    }
	
}

