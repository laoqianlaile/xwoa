package com.centit.dispatchdoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.IODocArchiveNoDao;
import com.centit.dispatchdoc.po.OptZwh;
import com.centit.dispatchdoc.service.IODocArchiveNoManager;

public class IODocArchiveNoManagerImpl extends BaseEntityManagerImpl<OptZwh>
        implements IODocArchiveNoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(IODocArchiveNoManager.class);

    private IODocArchiveNoDao ioDocArchiveNoDao;

    public void setIoDocArchiveNoDao(IODocArchiveNoDao baseDao) {
        this.ioDocArchiveNoDao = baseDao;
        setBaseDao(this.ioDocArchiveNoDao);
    }

}
