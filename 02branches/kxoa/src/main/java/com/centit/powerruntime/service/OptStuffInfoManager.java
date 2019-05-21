package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptStuffInfo;

public interface OptStuffInfoManager extends BaseEntityManager<OptStuffInfo> {

    /**
     * 根据业务编号和排序号获取材料信息
     * @param djId
     * @param sortId
     * @return
     */
    public OptStuffInfo getObjectById_SortId(String djId, String sortId);
    
    public OptStuffInfo getStuffInfoByArchiveType(String djId, String archiveType) ;
    
    /**
     * 正文材料文件查询
     * @param djId
     * @return
     */
    public List<OptStuffInfo> getZwfjStuffInfo(String djId);
    
    /**
     *  根据业务编号获取材料信息
     * @param djId
     * @return
     */
    public List<OptStuffInfo> getStuffInfoList(String djId);
   
}
