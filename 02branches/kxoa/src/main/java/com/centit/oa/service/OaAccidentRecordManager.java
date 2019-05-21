package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaAccidentRecord;

public interface OaAccidentRecordManager extends BaseEntityManager<OaAccidentRecord> 
{

  public  String getNextKey();

}
