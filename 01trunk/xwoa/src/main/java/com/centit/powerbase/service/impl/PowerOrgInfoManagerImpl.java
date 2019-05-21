package com.centit.powerbase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.dao.PowerOrgInfoDao;
import com.centit.powerbase.po.PowerOrgInfo;
import com.centit.powerbase.po.PowerOrgInfoId;
import com.centit.powerbase.service.PowerOrgInfoManager;
import com.centit.powerruntime.dao.VPowerOrgInfoDao;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.sys.po.FUnitinfo;

public class PowerOrgInfoManagerImpl extends
        BaseEntityManagerImpl<PowerOrgInfo> implements PowerOrgInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(PowerOrgInfoManager.class);

    private PowerOrgInfoDao powerOrgInfoDao;
    private VPowerOrgInfoDao vPowerOrgInfoDao;
    
    public void setPowerOrgInfoDao(PowerOrgInfoDao baseDao) {
        this.powerOrgInfoDao = baseDao;
        setBaseDao(this.powerOrgInfoDao);
    }

    public void setvPowerOrgInfoDao(VPowerOrgInfoDao vPowerOrgInfoDao) {
        this.vPowerOrgInfoDao = vPowerOrgInfoDao;
    }

    @Override
    public List<VPowerOrgInfo> listOfReadyPowerOrgInfoList(PowerOrgInfo o) {
        return vPowerOrgInfoDao.listOfReadyPowerOrgInfoList(o);
    }

    @Override
    public List<VPowerOrgInfo> listOfAllPowerOrgInfoList(PowerOrgInfo o) {
        return vPowerOrgInfoDao.listOfAllPowerOrgInfoList(o);
    }

    @Override
    public void saveObjects(PowerOrgInfo object, String retValue,
            String unitcode) {

        List<PowerOrgInfo> powerOrgInfos = new ArrayList<PowerOrgInfo>();
        String[] returnValue = retValue.split(",");

        if (returnValue.length > 0) {
            for (String itemid : returnValue) {
                if (StringUtils.isNotBlank(itemid)) {
                    object.setItemId(itemid);
                    PowerOrgInfo pi = new PowerOrgInfo(new PowerOrgInfoId(
                            itemid, unitcode));
                    pi.setSetime(object.getSetime());
                    pi.setSetoperator(object.getSetoperator());
                    powerOrgInfos.add(pi);
                }
            }
            savePowerOrgInfos(powerOrgInfos);
        }

    }

    private void savePowerOrgInfos(List<PowerOrgInfo> powerOrgInfos) {
        powerOrgInfoDao.savePowerOrgInfos(powerOrgInfos);

    }

    @Override
    public List<VPowerOrgInfo> listOfPowerOrgInfoList(PowerOrgInfo object) {
        return vPowerOrgInfoDao.listOfPowerOrgInfoList(object);
    }

    @Override
    public List<FUnitinfo> listUnitList(String itemId) {
        return powerOrgInfoDao.listUnitList(itemId);
    }

    @Override
    public void saveObjects(PowerOrgInfo object, String retValue) {

        List<PowerOrgInfo> powerOrgInfos = new ArrayList<PowerOrgInfo>();
        String[] returnValue = retValue.split(",");

        if (returnValue.length > 0) {
            for (String unitcode : returnValue) {
                if (StringUtils.isNotBlank(unitcode)) {                    
                    PowerOrgInfo pi = new PowerOrgInfo(new PowerOrgInfoId(
                            object.getItemId(), unitcode));
                    pi.setSetime(object.getSetime());
                    pi.setSetoperator(object.getSetoperator());
                    pi.setWfcode(object.getWfcode());
                    powerOrgInfos.add(pi);
                }
            }
            savePowerOrgs(powerOrgInfos);
        }
        
    }

    private void savePowerOrgs(List<PowerOrgInfo> powerOrgInfos) {
        powerOrgInfoDao.savePowerOrgInfos(powerOrgInfos);
        
    }

    @Override
    public List<VPowerOrgInfo> listPowerOrgInfoList(String unitcode,
            String itemType) {
        // TODO Auto-generated method stub
        return vPowerOrgInfoDao.listPowerOrgInfoList(unitcode, itemType);
    }

}
