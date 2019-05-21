package com.centit.powerruntime.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.RiskInfoDao;
import com.centit.powerruntime.po.RiskInfo;
import com.centit.powerruntime.service.RiskInfoManager;


public class RiskInfoManagerImpl extends BaseEntityManagerImpl<RiskInfo>
        implements RiskInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(RiskInfoManager.class);

    private RiskInfoDao riskInfoDao;

    public void setRiskInfoDao(RiskInfoDao baseDao) {
        this.riskInfoDao = baseDao;
        setBaseDao(this.riskInfoDao);
    }

    @Override
    public Long getNextRiskPK() {
        String sNo = riskInfoDao.getNextValueOfSequence("S_RISK_INFO");
        return Long.valueOf(sNo);
    }

}
