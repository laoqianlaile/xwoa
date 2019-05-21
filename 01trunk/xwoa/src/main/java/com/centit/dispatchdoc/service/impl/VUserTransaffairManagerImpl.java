package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VUserTransaffair;
import com.centit.dispatchdoc.dao.VUserTransaffairDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.VUserTransaffairManager;

public class VUserTransaffairManagerImpl extends
        BaseEntityManagerImpl<VUserTransaffair> implements
        VUserTransaffairManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VUserTransaffairManager.class);

    private VUserTransaffairDao VUserTransaffairDao;

    public void setVUserTransaffairDao(VUserTransaffairDao baseDao) {
        this.VUserTransaffairDao = baseDao;
        setBaseDao(this.VUserTransaffairDao);
    }
    
    public List<VUserTransaffair> listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        return VUserTransaffairDao.listUserTransaffair(filterMap, pageDesc);
    }
}
