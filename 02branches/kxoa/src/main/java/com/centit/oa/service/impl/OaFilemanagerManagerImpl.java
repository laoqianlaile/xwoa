package com.centit.oa.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaFilemanagerDao;
import com.centit.oa.po.OaFilemanager;
import com.centit.oa.service.OaFilemanagerManager;


public class OaFilemanagerManagerImpl extends BaseEntityManagerImpl<OaFilemanager>
    implements OaFilemanagerManager{
    
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaFilemanagerManager.class);
    
    private OaFilemanagerDao oaFilemanagerDao ;
    
    public void setOaFilemanagerDao(OaFilemanagerDao oaFilemanagerDao) {
        this.oaFilemanagerDao = oaFilemanagerDao;
        setBaseDao(this.oaFilemanagerDao);
    }
    @Override
    public String getNextkey(String tag) {
        // TODO Auto-generated method stub
        return "WJGL"+oaFilemanagerDao.getNextKeyBySequence("S_OAFILEMANAGER",12);
    }
    @Override
    public void autoInvalidated() {
        // TODO Auto-generated method stub
        oaFilemanagerDao.autoInvalidated();
    }

}