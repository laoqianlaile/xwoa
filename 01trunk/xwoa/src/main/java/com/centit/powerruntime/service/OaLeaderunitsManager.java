package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OaLeaderunits;

public interface OaLeaderunitsManager extends BaseEntityManager<OaLeaderunits> 
{

    public void deleteObjects(String leadercode);

}
