package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OaFwdepmange;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.sys.po.FDatadictionary;

public interface OaFwdepmangeManager extends BaseEntityManager<OaFwdepmange> 
{
    /**
     * 依据部门信息获取文书情况
     * @param primaryUnit
     * @return
     */
    List<OaFwdepmange> getDocManageListByUnit(String primaryUnit);
    
    /**
     * 部门已配置的文书列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    List<TemplateFile> getDocListByUnit(String primaryUnit);
    /**
     * 依据部门信息获取置文号规则情况
     * @param primaryUnit
     * @return
     */
    List<OaFwdepmange> getZWHManageListByUnit(String primaryUnit);
    
    /**
     * 依据部门信息获取置文号规则情况
     * 
     */
    List<FDatadictionary> getZWHListByUnit(String primaryUnit);
    
    /**
     * 依据部门信息获取配置情况
     * @param primaryUnit
     * @return
     */
    List<OaFwdepmange> getManageListByUnit(String primaryUnit);
    
    /**
     * 获取文书情况
     * @param primaryUnit
     * @return
     */
    List<OaFwdepmange> getAllDocManageList();
    
    /**
     * 置文号规则情况
     * @param primaryUnit
     * @return
     */
    List<OaFwdepmange> getAllZWHManageList();
    
    /**待选
     * 新增文书列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    List<TemplateFile> selectDocList(String s_unitcode, Map<String, Object> filterMap, PageDesc pageDesc);
    
    
    /**待选
     * 新增文号规则列表
     * 
     */
    List<FDatadictionary> selectZWHList(String s_unitcode, Map<String, Object> filterMap, PageDesc pageDesc);
    
    /**
     * 保存文书配置
     * @param s_unitcode
     * @param s_templates
     */
    void saveSelectDocList(String s_unitcode, String s_templates);
    
    /**
     * 保存文号配置
     * @param s_unitcode
     * @param s_templates
     */
    void saveSelectZWhList(String s_unitcode, String s_templates);
    
    /**
     * 获取序列
     * @return
     */
    String getNextKey();
}
