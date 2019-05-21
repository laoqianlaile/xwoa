package com.centit.app.action;

import com.centit.app.po.OptDashboardActive;
import com.centit.app.service.OptDashboardActiveManager;
import com.centit.core.action.BaseEntityDwzAction;

public class OptDashboardActiveAction extends BaseEntityDwzAction<OptDashboardActive>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardActiveManager optDashboardActiveManager;
    
     public OptDashboardActiveManager getOptDashboardActiveManager() {
        return optDashboardActiveManager;
    }

    public void setOptDashboardActiveManager(
            OptDashboardActiveManager optDashboardActiveManager) {
        this.optDashboardActiveManager = optDashboardActiveManager;
        super.setBaseEntityManager(optDashboardActiveManager);
    }

   
}
