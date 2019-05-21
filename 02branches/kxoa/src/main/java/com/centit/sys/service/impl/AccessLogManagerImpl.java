package com.centit.sys.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.AccessLogDao;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.service.AccessLogManager;

public class AccessLogManagerImpl extends BaseEntityManagerImpl<FAccessLog>
        implements AccessLogManager{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private AccessLogDao accessLogDao;
    
    public void setAccessLogDao(AccessLogDao accessLogDao) {
        this.accessLogDao = accessLogDao;
        setBaseDao(accessLogDao);
    }

    @Override
    public Long getNextLogId() {
        return accessLogDao.getNextLongSequence("S_ACCESSLOG");
    }

    @Override
    public int getUserCountOnline() {
        return accessLogDao.getUserCountOnline();
    }

    @Override
    public List<FAccessLog> findAccessLogUndo(int limitMinutes) {
        return accessLogDao.findAccessLogUndo(limitMinutes);
    }
}
