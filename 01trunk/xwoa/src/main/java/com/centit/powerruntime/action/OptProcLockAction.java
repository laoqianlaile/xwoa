package com.centit.powerruntime.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.OptProcLock;
import com.centit.powerruntime.service.OptProcLockManager;

public class OptProcLockAction extends BaseEntityExtremeAction<OptProcLock> {
    private static final Log log = LogFactory.getLog(OptProcLockAction.class);
    private static final long serialVersionUID = 1L;
    private OptProcLockManager optProcLockMag;

    public void setOptProcLockManager(OptProcLockManager basemgr) {
        optProcLockMag = basemgr;
        this.setBaseEntityManager(optProcLockMag);
    }

}
