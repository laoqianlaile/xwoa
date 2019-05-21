package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OaUnitsLeaderLog;

public interface OaUnitsLeaderLogManager extends BaseEntityManager<OaUnitsLeaderLog> 
{

    /**
     * 根据序列获取主键策略
     * @return
     */
    public String getnextKey();

}
