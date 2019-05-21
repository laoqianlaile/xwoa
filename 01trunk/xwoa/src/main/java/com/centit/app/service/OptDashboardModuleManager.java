package com.centit.app.service;

import com.centit.app.po.OptDashboardModule;
import com.centit.core.service.BaseEntityManager;

public interface OptDashboardModuleManager extends BaseEntityManager<OptDashboardModule>{
   public Long getNextSequence();
   /**
    * 
    * @param moduleCode
    * @return
    */
   public OptDashboardModule findByModuleCode(String moduleCode);
}
