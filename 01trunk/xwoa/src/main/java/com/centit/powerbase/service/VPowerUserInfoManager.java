package com.centit.powerbase.service;


import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.VPowerUserInfo;

public interface VPowerUserInfoManager extends BaseEntityManager<VPowerUserInfo> 
{
    /**
     * 根据权力编码,其中带着版本
     * @param item_id
     * @param orgid
     * @return
     */
    public VPowerUserInfo getPowerInfo(String item_id);  
    
}
