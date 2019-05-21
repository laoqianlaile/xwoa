package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VIncomeDocList;

public interface VIncomeDocListManager extends BaseEntityManager<VIncomeDocList> 
{
    public List<VIncomeDocList> getDocRelativeList(String dispatchNo);
    
    /**
     * 收文列表（可根据部门编号）
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VIncomeDocList> listIncomeDocList(
            Map<String, Object> filterMap, PageDesc pageDesc);
    /**
     * 收文列表 单位/部门
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VIncomeDocList> listIncomeDocListV2(
            Map<String, Object> filterMap, PageDesc pageDesc);
    
    /**
     * 获取相邻的djId
     * @param params
     * @return
     */
    public List<String> findNeighbouringDjId(Map<String,Object> params,String currDjId); 
    
    public List<String> findNeighbouringDjId2(Map<String,Object> params,String currDjId); 
    
    /**
     * 收文列表（可根据部门编号）(去除分页)
     * @param filterMap
     * @return
     */
    public List<VIncomeDocList> listIncomeDocForExcel(
            Map<String, Object> filterMap);
}
