package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaBuffetapply;

public interface OaBuffetapplyManager extends BaseEntityManager<OaBuffetapply> 
{
    /**
     * 查询期号
     */
      
     String getNextKeyMax();
}
