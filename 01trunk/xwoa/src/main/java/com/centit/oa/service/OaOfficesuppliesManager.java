package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaOfficesupplies;

public interface OaOfficesuppliesManager extends BaseEntityManager<OaOfficesupplies> 
{
  /**
   * 查询期号
   */
    
   String getNextKeyMax();
}
