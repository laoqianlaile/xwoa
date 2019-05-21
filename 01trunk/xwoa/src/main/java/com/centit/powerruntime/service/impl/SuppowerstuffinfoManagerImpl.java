package com.centit.powerruntime.service.impl;


import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.SuppowerstuffinfoDao;
import com.centit.powerruntime.po.Suppowerstuffinfo;
import com.centit.powerruntime.service.SuppowerstuffinfoManager;

public class SuppowerstuffinfoManagerImpl extends BaseEntityManagerImpl<Suppowerstuffinfo>  implements SuppowerstuffinfoManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private SuppowerstuffinfoDao suppowerstuffinfoDao;

    public void setSuppowerstuffinfoDao(SuppowerstuffinfoDao suppowerstuffinfoDao) {
        this.suppowerstuffinfoDao = suppowerstuffinfoDao;
        setBaseDao(suppowerstuffinfoDao);
    }

    @Override
    public List<Suppowerstuffinfo> getinfosByGroupId(String groupid) {
     
        return suppowerstuffinfoDao.getinfosByGroupId(groupid);
    }

   
    public String getNextKey(){
        return suppowerstuffinfoDao.getNextKeyBySequence("S_STUFFINFO", 10);
    }

    @Override
    public void deleteObjectByGroupId(String groupId) {
        // TODO Auto-generated method stub
         suppowerstuffinfoDao.deleteObjectByGroupId(groupId);
    }

}
