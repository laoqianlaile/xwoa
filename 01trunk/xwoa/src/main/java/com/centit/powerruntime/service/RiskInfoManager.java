package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.RiskInfo;

public interface RiskInfoManager extends BaseEntityManager<RiskInfo>{

   public  Long getNextRiskPK();

}
