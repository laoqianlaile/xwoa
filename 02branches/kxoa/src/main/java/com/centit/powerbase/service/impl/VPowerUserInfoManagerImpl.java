package com.centit.powerbase.service.impl;


import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.dao.VPowerUserInfoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerbase.service.PowerUserInfoManager;
import com.centit.powerbase.service.VPowerUserInfoManager;

public class VPowerUserInfoManagerImpl extends BaseEntityManagerImpl<VPowerUserInfo>
	implements VPowerUserInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(PowerUserInfoManager.class);

	private VPowerUserInfoDao vpowerUserInfoDao ;

	public void setVPowerUserInfoDao(VPowerUserInfoDao baseDao)
    {
        this.vpowerUserInfoDao = baseDao;
        setBaseDao(this.vpowerUserInfoDao);
    }

    @Override
    public VPowerUserInfo getPowerInfo(String item_id) {
        // TODO Auto-generated method stub
        return vpowerUserInfoDao.getPowerInfo(item_id);
    }
	
}

