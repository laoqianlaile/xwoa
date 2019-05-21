package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.OptProcLock;
import com.centit.powerruntime.dao.OptProcLockDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerruntime.service.OptProcLockManager;

public class OptProcLockManagerImpl extends BaseEntityManagerImpl<OptProcLock>
        implements OptProcLockManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptProcLockManager.class);

    private OptProcLockDao optProcLockDao;

    public void setOptProcLockDao(OptProcLockDao baseDao) {
        this.optProcLockDao = baseDao;
        setBaseDao(this.optProcLockDao);
    }

    @Override
    public OptProcLock getOptProcLock(String djId, String islocked) {
        // TODO Auto-generated method stub
        return optProcLockDao.getOptProcLock(djId, islocked);
    }

    @Override
    public OptProcLock getOptProcLock(String djId) {
        // TODO Auto-generated method stub
        return optProcLockDao.getOptProcLock(djId);
    }

}
