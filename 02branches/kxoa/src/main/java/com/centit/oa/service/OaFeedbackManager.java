package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaFeedback;

public interface OaFeedbackManager extends BaseEntityManager<OaFeedback> 
{

 public String   getNextKey();

}
