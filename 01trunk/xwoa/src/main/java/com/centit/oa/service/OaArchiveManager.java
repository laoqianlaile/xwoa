package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaArchive;

public interface OaArchiveManager extends BaseEntityManager<OaArchive> 
{
    //获取主键
    public String getNextKey();
    //更改件号
    public void updateTatanic(String duration);
    /**
     * 根据当前归档年度获取最大件号
     * @return
     */
    public Long getNextMaxKey();
    
    /**
     * 
     * 根据当前归档年度及当前部门 获取最大件号
     * @return
     */
    public Long getNextTitanic(String  filingannual, String  belongUnitcode );
   
    /**
     * 根据归档年度和件号反查归档信息是否存在
     * @param filingannual
     * @param string
     * @return
     */
    public OaArchive getObjectByIds(Long filingannual, String string,String  belongUnitcode);
    /**
     * 获取历年归档年度
     * @return
     */
    public List<OaArchive> listNdList();

    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId);

}
