package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.IncomeProject;
import com.centit.dispatchdoc.dao.IncomeProjectDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.IncomeProjectManager;

public class IncomeProjectManagerImpl extends
        BaseEntityManagerImpl<IncomeProject> implements IncomeProjectManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IncomeProjectManager.class);

    private IncomeProjectDao incomeProjectDao;

    public void setIncomeProjectDao(IncomeProjectDao baseDao) {
        this.incomeProjectDao = baseDao;
        setBaseDao(this.incomeProjectDao);
    }

}
