package com.centit.powerruntime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.PowerOptInfoDao;
import com.centit.powerruntime.po.PowerOptInfo;
import com.centit.powerruntime.service.PowerOptInfoManager;

public class PowerOptInfoManagerImpl extends
        BaseEntityManagerImpl<PowerOptInfo> implements PowerOptInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(PowerOptInfoManager.class);

    private PowerOptInfoDao powerOptInfoDao;

    public void setPowerOptInfoDao(PowerOptInfoDao baseDao) {
        this.powerOptInfoDao = baseDao;
        setBaseDao(this.powerOptInfoDao);
    }

    @Override
    public List<PowerOptInfo> getObjectsByItemID(String itemId) {
        return powerOptInfoDao.getObjectsByItemID(itemId);
    }

    @Override
    public PowerOptInfo getObjectByItemID(String itemId) {
        return powerOptInfoDao.getObjectByItemID(itemId);
    }

    @Override
    public void savePowerOptInfo(PowerOptInfo object) {
        List<PowerOptInfo> powerOptInfoList = new ArrayList<PowerOptInfo>();
        powerOptInfoList.add(object);
        powerOptInfoDao.savePowerOptInfo(powerOptInfoList);

    }

}
