package com.centit.powerbase.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerstatechglog;

public interface SuppowerstatechglogManager extends BaseEntityManager<Suppowerstatechglog> 
{
    public String generateNextPunishClassID();
    public Suppowerstatechglog  getObjectByIdAndVersion(String itemId, Long version);
    public void saveObject(Suppowerstatechglog log);
}
