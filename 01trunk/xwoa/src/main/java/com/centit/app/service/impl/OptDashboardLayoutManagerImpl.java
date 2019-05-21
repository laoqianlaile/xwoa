package com.centit.app.service.impl;

import com.centit.app.dao.OptDashboardLayoutDao;
import com.centit.app.po.OptDashboardLayout;
import com.centit.app.service.OptDashboardLayoutManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class OptDashboardLayoutManagerImpl extends BaseEntityManagerImpl<OptDashboardLayout> 
         implements OptDashboardLayoutManager  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardLayoutDao optDashboardLayoutDao;

    public OptDashboardLayoutDao getOptDashboardLayoutDao() {
        return optDashboardLayoutDao;
    }
    
    public void setOptDashboardLayoutDao(
            OptDashboardLayoutDao optDashboardLayouteDao) {
        this.optDashboardLayoutDao = optDashboardLayouteDao;
        this.setBaseDao(optDashboardLayouteDao);
    }

    @Override
    public Long getNextSequence() {
        return optDashboardLayoutDao.getNextLongSequence("SEQ_OPT_DASHBOARD_LAYOUT");
    }
    
    
}
