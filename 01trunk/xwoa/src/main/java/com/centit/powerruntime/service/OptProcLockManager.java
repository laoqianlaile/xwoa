package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptProcLock;

public interface OptProcLockManager extends BaseEntityManager<OptProcLock> {

    /**
     * 获取办件的锁定状态
     * djId
     * optbaseinfo中锁定字段
     * @return
     */
    public OptProcLock getOptProcLock(String djId, String islocked);
    /**
     * 获取办件的锁定状态
     * djId
     * @return
     */
    public  OptProcLock getOptProcLock(String djId);

}
