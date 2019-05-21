package com.centit.dispatchdoc.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.DocRelative;
import com.centit.dispatchdoc.dao.DocRelativeDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.DocRelativeManager;

public class DocRelativeManagerImpl extends BaseEntityManagerImpl<DocRelative>
        implements DocRelativeManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DocRelativeManager.class);

    private DocRelativeDao docRelativeDao;

    public void setDocRelativeDao(DocRelativeDao baseDao) {
        this.docRelativeDao = baseDao;
        setBaseDao(this.docRelativeDao);
    }

    public List<DocRelative> getObjectsByDispatchNo(String dispatchNo) {
        return docRelativeDao.getObjectsByDispatchNo(dispatchNo);
    }
    
    public List<DocRelative> getObjectsByIncomeNo(String incomeNo) {
        return docRelativeDao.getObjectsByIncomeNo(incomeNo);
    }
}
