package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaCostInfo;

public interface OaCostInfoManager extends BaseEntityManager<OaCostInfo> 
{

   public String  getNextKey();

}
