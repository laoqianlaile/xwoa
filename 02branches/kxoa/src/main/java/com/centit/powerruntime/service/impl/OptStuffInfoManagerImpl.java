package com.centit.powerruntime.service.impl;


import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;

public class OptStuffInfoManagerImpl extends BaseEntityManagerImpl<OptStuffInfo>  implements OptStuffInfoManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private OptStuffInfoDao optStuffInfoDao;

    public void setOptStuffInfoDao(OptStuffInfoDao optStuffInfoDao) {
        this.optStuffInfoDao = optStuffInfoDao;
        setBaseDao(optStuffInfoDao);
    }

    @Override
    public OptStuffInfo getObjectById_SortId(String djId, String sortId) {
        // TODO Auto-generated method stub
        return optStuffInfoDao.getObjectById_SortId(djId,sortId);
    }
    public OptStuffInfo getStuffInfoByArchiveType(String djId, String archiveType){
        
        return optStuffInfoDao.getStuffInfoByArchiveType(djId,archiveType);
    }

   public List<OptStuffInfo> getZwfjStuffInfo(String djId){
        
        return optStuffInfoDao.listZwclStuffs(djId);
    }
public List<OptStuffInfo> getStuffInfoList(String djId){
       
       return optStuffInfoDao.getStuffInfoListByFileType(djId,null);
   }
}
