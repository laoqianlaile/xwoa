package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.VPowerOrgInfo;

public interface VPowerOrgInfoManager extends BaseEntityManager<VPowerOrgInfo> {
    /**
     * 根据权力编码和部门获取权力信息,其中带着版本
     * @param item_id
     * @param orgid
     * @return
     */
    public VPowerOrgInfo getSupPowerInfo(String item_id,String orgid);
}
