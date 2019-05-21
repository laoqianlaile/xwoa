package com.centit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.OptLogDao;
import com.centit.sys.po.OptLog;
import com.centit.sys.service.OptLogManager;

public class OptLogManagerImpl extends BaseEntityManagerImpl<OptLog> implements
        OptLogManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptLogManager.class);

    private OptLogDao optLogDao;

    public void setOptLogDao(OptLogDao baseDao) {
        this.optLogDao = baseDao;
        setBaseDao(this.optLogDao);
    }

    @Override
    public void saveObject(OptLog o) {
        o.setLogid(Long.parseLong(this.optLogDao
                .getNextValueOfSequence("S_SYS_LOG")));
        super.saveObject(o);
    }

    @Override
    public void deleteByCheckUp(Date date) {
        
        this.optLogDao.checkUp(date);
    }

    @Override
    public List<String> listOptIds() {
        return this.optLogDao.listOptIds();
    }
}
