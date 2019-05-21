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
    
    /**
     * 自动生成编号
     * 这个需要创建序列，在Sequences新建一个序列即可
     */
    public String getNewCode();
    
    /**
     * 自动生成编号
     * 安监局收文
     */
    public String getNewCodeByAJJ();
    /**
     * 自动生成编号
     * 安委会收文
     */
    public String getNewCodeByAJ();
    /**
     * 自动生成编号
     *环保局收文
     */
    public String getNewCodeByHBJ();
    /**
     * 自动生成编号
     * 党群收文
     */
    public String getNewCodeByDQ();
    /**
     * 自动生成编号
     * 规划建设局收文
     */
    public String getNewCodeByGHJSQ();
    /**
     * 自动生成编号
     * 通用收文
     */
    public String getNewCodeByTY();
    /**
     * 自动生成编号
     * 国资办收文
     */
    public String getNewCodeByGZB();
}
