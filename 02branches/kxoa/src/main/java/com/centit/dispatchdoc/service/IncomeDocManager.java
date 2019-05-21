package com.centit.dispatchdoc.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.IncomeDoc;

public interface IncomeDocManager extends BaseEntityManager<IncomeDoc> 
{
    /**
     * 登记编号
     * @param zt 编号字头
     * @return
     */
    public String getNextkey(String zt);
    
    public IncomeDoc getIncomeDoc(String internalNo, String itemId);
    
    public String getJSONDocumentNames(String djId);
    
    /**
     * 获取系统内的发文机关名称
     * @return
     */
    public List<String> getIncomeDeptNames();
    
    /**
     * 获取系统内的来文单位分类
     * @return
     */
    public List<String> getIncomeAcceptNos();
}
