package com.centit.app.service;

import com.centit.app.po.OptDashboardActive;
import com.centit.core.service.BaseEntityManager;

public interface OptDashboardActiveManager extends BaseEntityManager<OptDashboardActive>{
   public Long getNextSequence();
   /**
    * 获取当前激活的排版
    * @param usercode
    * @return
    */
   public OptDashboardActive findActive(String usercode);
}
