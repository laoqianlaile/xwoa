package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.VDispatchDocList;

public interface DispatchDocManager extends BaseEntityManager<DispatchDoc> 
{
    /**
     * 获取主键
     * @return
     */
    public String getNextkey();
    
    public DispatchDoc getDispatchDoc(String internalNo, String itemId);
    
    
    /**
     *  查询视图v_dispatchdoc(未提交发文/已办结发文)
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VDispatchDocList> getVDispatchDocList(Map<String, Object> filterMap,PageDesc pageDesc);
    
    /**
     * 根据业务流水号获得行政基础信息JSON
     * 流程图操作参数有文书情况
     * @param djId
     * @return
     */
    public JSONObject getOptBaseInfoJSONByDjId(String djId);
    /**
     * 根据业务流水号获得行政基础信息JSON
     * 流程图操作参数有文书情况
     * @param djId
     * @param documentTemplateIds 
     * @return
     */
    public JSONObject getOptBaseInfoJSONByDjId(String djId,String documentTemplateIds,String primaryUnit);
    
    /**
     * 根据业务流水号获得行政许可业务信息JSON
     * @param djId
     * @return
     */
    public JSONObject getOptApplyInfoJSONByDjId(String djId);
    
    public JSONObject getOptProcInfoJSONByDjId(String djId);
    
    /**
     * 获取付文号书签
     * @param djId
     * @return
     */
    public JSONObject getOptSLHJSONByDjId(String djId);
    /**
     * 根据登记id和文书类型组（支持多个附件类型）判断文书是否保存
     * @param djId
     * @param documentTemplateIds
     * @return 当已保存文书类型组中任意一个文书时返回true；一个没存时返回false
     */
    public boolean isHaveWordByDjidAndTemplateIds(String djId,String documentTemplateIds);
    
}
