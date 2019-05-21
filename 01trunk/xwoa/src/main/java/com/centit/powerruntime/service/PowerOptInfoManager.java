package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.PowerOptInfo;

public interface PowerOptInfoManager extends BaseEntityManager<PowerOptInfo> 
{

    public List<PowerOptInfo> getObjectsByItemID(String itemId);

    public PowerOptInfo getObjectByItemID(String itemId);

    public void savePowerOptInfo(PowerOptInfo object);

}
