package com.centit.app.service.impl;

import com.centit.app.dao.OptLayoutMethodDao;
import com.centit.app.po.OptLayoutMethod;
import com.centit.app.service.OptLayoutMethodManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class OptLayoutMethodManagerImpl extends BaseEntityManagerImpl<OptLayoutMethod> 
         implements OptLayoutMethodManager  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptLayoutMethodDao optLayoutMethodDao;

    

    public OptLayoutMethodDao getOptLayoutMethodDao() {
        return optLayoutMethodDao;
    }



    public void setOptLayoutMethodDao(OptLayoutMethodDao optLayoutMethodDao) {
        this.optLayoutMethodDao = optLayoutMethodDao;
        super.setBaseDao(optLayoutMethodDao);
    }



    @Override
    public Long getNextSequence() {
        return optLayoutMethodDao.getNextLongSequence("SEQ_OPT_LAYOUT_METHOD");
    }
    
    
}
