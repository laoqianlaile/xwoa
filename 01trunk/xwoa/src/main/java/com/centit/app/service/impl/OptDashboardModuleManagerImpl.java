package com.centit.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.dao.OptDashboardModuleDao;
import com.centit.app.po.OptDashboardModule;
import com.centit.app.service.OptDashboardModuleManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class OptDashboardModuleManagerImpl extends BaseEntityManagerImpl<OptDashboardModule> 
         implements OptDashboardModuleManager  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardModuleDao optDashboardModuleDao;

    public OptDashboardModuleDao getOptDashboardModuleDao() {
        return optDashboardModuleDao;
    }

    public void setOptDashboardModuleDao(OptDashboardModuleDao optDashboardModuleDao) {
        this.optDashboardModuleDao = optDashboardModuleDao;
        super.setBaseDao(optDashboardModuleDao);
    }

    @Override
    public Long getNextSequence() {
        return optDashboardModuleDao.getNextLongSequence("SEQ_OPT_DASHBOARD_MODULE");
    }

    @Override
    public OptDashboardModule findByModuleCode(String moduleCode) {
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("moduleCode", moduleCode);
        List<OptDashboardModule> moduleList = optDashboardModuleDao.listObjects(filterMap);
        if(moduleList!=null){
            return moduleList.get(0);
        }
        return null;
    }
    
    
}
