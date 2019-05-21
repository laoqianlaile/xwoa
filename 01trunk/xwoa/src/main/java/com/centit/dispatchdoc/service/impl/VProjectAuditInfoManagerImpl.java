package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.VProjectAuditInfo;
import com.centit.dispatchdoc.dao.VProjectAuditInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.VProjectAuditInfoManager;

public class VProjectAuditInfoManagerImpl extends
        BaseEntityManagerImpl<VProjectAuditInfo> implements
        VProjectAuditInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VProjectAuditInfoManager.class);

    private VProjectAuditInfoDao VProjectAuditInfoDao;

    public void setVProjectAuditInfoDao(VProjectAuditInfoDao baseDao) {
        this.VProjectAuditInfoDao = baseDao;
        setBaseDao(this.VProjectAuditInfoDao);
    }

}
