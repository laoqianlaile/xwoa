package com.centit.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.dao.OptDashboardActiveDao;
import com.centit.app.po.OptDashboardActive;
import com.centit.app.service.OptDashboardActiveManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class OptDashboardActiveManagerImpl extends BaseEntityManagerImpl<OptDashboardActive> implements OptDashboardActiveManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardActiveDao optDashboardActiveDao;
    
    
    public OptDashboardActiveDao getOptDashboardActiveDao() {
        return optDashboardActiveDao;
    }

    public void setOptDashboardActiveDao(OptDashboardActiveDao optDashboardActiveDao) {
        this.optDashboardActiveDao = optDashboardActiveDao;
        super.setBaseDao(optDashboardActiveDao);
    }

    @Override
    public Long getNextSequence() {
        return null;
    }

    @Override
    public OptDashboardActive findActive(String usercode) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", usercode);
        List<OptDashboardActive> objList = optDashboardActiveDao.listObjects(filterMap);
        if(objList!=null && objList.size()>0){
            return objList.get(0);
        }
        return null;
    }
}
