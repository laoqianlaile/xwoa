package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaDriverInfo;

public interface OaDriverInfoManager extends BaseEntityManager<OaDriverInfo> 
{

    /**
     * 获取车辆主键策略
     * @return
     */
    public String getNextKey();

}
