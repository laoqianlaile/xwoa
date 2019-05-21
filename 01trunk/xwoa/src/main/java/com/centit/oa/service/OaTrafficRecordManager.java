package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaTrafficRecord;

public interface OaTrafficRecordManager extends BaseEntityManager<OaTrafficRecord> 
{

   public String getNextKey();

}
