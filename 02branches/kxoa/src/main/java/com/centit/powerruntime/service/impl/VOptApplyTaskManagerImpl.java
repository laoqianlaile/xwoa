package com.centit.powerruntime.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.VOptApplyTaskDao;
import com.centit.powerruntime.po.VOptApplyTaskList;
import com.centit.powerruntime.service.VOptApplyTaskManager;

public class VOptApplyTaskManagerImpl extends
        BaseEntityManagerImpl<VOptApplyTaskList> implements
        VOptApplyTaskManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VOptApplyTaskManagerImpl.class);
    private VOptApplyTaskDao vOptApplyTaskDao;

    public void setVOptApplyTaskDao(VOptApplyTaskDao vOptApplyTaskDao) {
        this.vOptApplyTaskDao = vOptApplyTaskDao;
        setBaseDao(vOptApplyTaskDao);
    }

    public VOptApplyTaskDao getvOptApplyTaskDao() {
        return vOptApplyTaskDao;
    }

}
