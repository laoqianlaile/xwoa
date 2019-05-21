package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.SuppowerstuffgroupDao;
import com.centit.powerruntime.po.Suppowerstuffgroup;
import com.centit.powerruntime.service.SuppowerstuffgroupManager;

public class SuppowerstuffgroupManagerImpl  extends BaseEntityManagerImpl<Suppowerstuffgroup> implements SuppowerstuffgroupManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private SuppowerstuffgroupDao suppowerstuffgroupDao;

    public void setSuppowerstuffgroupDao(SuppowerstuffgroupDao suppowerstuffgroupDao) {
        this.suppowerstuffgroupDao = suppowerstuffgroupDao;
        setBaseDao(this.suppowerstuffgroupDao);
    }


    public String getNextGroupCode() {
        return suppowerstuffgroupDao.getNextGroupCode();
    }

    

}
