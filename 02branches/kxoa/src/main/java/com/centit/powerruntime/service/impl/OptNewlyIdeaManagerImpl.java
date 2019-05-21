package com.centit.powerruntime.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.OptNewlyIdeaDao;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.service.OptNewlyIdeaManager;

public class OptNewlyIdeaManagerImpl extends
        BaseEntityManagerImpl<OptNewlyIdea> implements OptNewlyIdeaManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptNewlyIdeaManager.class);

    private OptNewlyIdeaDao optNewlyIdeaDao;

    public void setOptNewlyIdeaDao(OptNewlyIdeaDao baseDao) {
        this.optNewlyIdeaDao = baseDao;
        setBaseDao(this.optNewlyIdeaDao);
    }

}
