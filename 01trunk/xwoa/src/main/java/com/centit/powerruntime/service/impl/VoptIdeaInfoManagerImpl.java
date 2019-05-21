package com.centit.powerruntime.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.VoptIdeaInfoDao;
import com.centit.powerruntime.po.VoptIdeaInfo;
import com.centit.powerruntime.service.VoptIdeaInfoManager;

public class VoptIdeaInfoManagerImpl extends BaseEntityManagerImpl<VoptIdeaInfo> 
                                implements VoptIdeaInfoManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VoptIdeaInfoManager.class);
    private VoptIdeaInfoDao vOptIdeaInfoDao;
    public void setvOptIdeaInfoDao(VoptIdeaInfoDao baseDao) {
        this.vOptIdeaInfoDao = baseDao;
        setBaseDao(this.vOptIdeaInfoDao);
    }
   

}
