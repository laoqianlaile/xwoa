package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.OptLeaderShip;

public interface OptLeaderShipManager extends BaseEntityManager<OptLeaderShip> 
{
    public String getNextkey();
}
