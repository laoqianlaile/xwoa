package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.dao.VStuffTransaffairDao;
import com.centit.dispatchdoc.po.VStuffTransaffair;
import com.centit.dispatchdoc.service.VStuffTransaffairManager;

public class VStuffTransaffairManagerImpl extends
        BaseEntityManagerImpl<VStuffTransaffair> implements
        VStuffTransaffairManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VStuffTransaffairManager.class);

    private VStuffTransaffairDao VStuffTransaffairDao;

    public void setVStuffTransaffairDao(VStuffTransaffairDao baseDao) {
        this.VStuffTransaffairDao = baseDao;
        setBaseDao(this.VStuffTransaffairDao);
    }

    public List<VStuffTransaffair> listStuffTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        return VStuffTransaffairDao.listStuffTransaffair(filterMap, pageDesc);
    }
}
