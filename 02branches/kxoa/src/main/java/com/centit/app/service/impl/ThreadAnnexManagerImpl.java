package com.centit.app.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.dao.ThreadAnnexDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.service.ThreadAnnexManager;

public class ThreadAnnexManagerImpl extends BaseEntityManagerImpl<ThreadAnnex>
        implements ThreadAnnexManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ThreadAnnexManager.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private ThreadAnnexDao threadAnnexDao;

    public void setThreadAnnexDao(ThreadAnnexDao baseDao) {
        this.threadAnnexDao = baseDao;
        setBaseDao(this.threadAnnexDao);
    }

}

