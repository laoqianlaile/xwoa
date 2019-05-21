package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaKqnotification;

public interface OaKqnotificationManager extends BaseEntityManager<OaKqnotification> 
{

   /**
   * 查询期号
   */
    
   String getNextKeyMax();

}
