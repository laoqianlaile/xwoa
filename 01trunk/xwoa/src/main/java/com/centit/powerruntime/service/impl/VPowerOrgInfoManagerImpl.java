package com.centit.powerruntime.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.VPowerOrgInfoDao;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.powerruntime.service.VPowerOrgInfoManager;

public class VPowerOrgInfoManagerImpl extends BaseEntityManagerImpl<VPowerOrgInfo>
implements VPowerOrgInfoManager{
private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VPowerOrgInfoManager.class);
   
    private VPowerOrgInfoDao vPowerOrgInfoDao ;
    public void setVPowerOrgInfoDao(VPowerOrgInfoDao baseDao)
    {
        this.vPowerOrgInfoDao = baseDao;
        setBaseDao(this.vPowerOrgInfoDao);
    }
    @Override
    public VPowerOrgInfo getSupPowerInfo(String item_id, String unitcode) {
        // TODO Auto-generated method stub
        return vPowerOrgInfoDao.getSupPowerInfo(item_id,unitcode);
    }
}
