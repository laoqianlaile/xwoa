package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.VSwSearch;
import com.centit.dispatchdoc.dao.VSwSearchDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.VSwSearchManager;

public class VSwSearchManagerImpl extends BaseEntityManagerImpl<VSwSearch>
        implements VSwSearchManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VSwSearchManager.class);

    private VSwSearchDao VSwSearchDao;

    public void setVSwSearchDao(VSwSearchDao baseDao) {
        this.VSwSearchDao = baseDao;
        setBaseDao(this.VSwSearchDao);
    }

}
