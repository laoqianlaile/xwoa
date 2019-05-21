package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.ApplyUnitInfo;
import com.centit.dispatchdoc.dao.ApplyUnitInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.ApplyUnitInfoManager;

public class ApplyUnitInfoManagerImpl extends
        BaseEntityManagerImpl<ApplyUnitInfo> implements ApplyUnitInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ApplyUnitInfoManager.class);

    private ApplyUnitInfoDao applyUnitInfoDao;

    public void setApplyUnitInfoDao(ApplyUnitInfoDao baseDao) {
        this.applyUnitInfoDao = baseDao;
        setBaseDao(this.applyUnitInfoDao);
    }

}
