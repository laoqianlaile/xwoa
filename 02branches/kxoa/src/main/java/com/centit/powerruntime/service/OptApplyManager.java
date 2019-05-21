package com.centit.powerruntime.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.VOptApplyInfo;

public interface OptApplyManager extends BaseEntityManager<OptApplyInfo> {
    public String generateNextDjId();

    /**
     * 查询保存但未提交的许可业务数据
     * 
     * @param filterMap
     *            查询条件
     * @param pageDesc
     *            排序方式
     * @return
     */
    public List<VOptApplyInfo> listOptApplyInfo(Map<String,Object> filterMap, PageDesc pageDesc);

    /**
     * 获取申请者JSON数据
     * @return
     */
   // public String getJSONProposerNames();
    
    /**
     * 根据登记编号获取办件信息JSON数据
     * @param djId
     * @return
     */
    //public String getApplyInfoJsonByDjId(String djId);

    public String getJSONDocumentNames(String dj_id);
    
    public String getNextDjId(String ev);
    //支持业务删除办件使用
    public void deleteObjectBanInfo(String djId);

    /**
     * 根据业务类别生成对应流水号
     * @param ev
     * @param moduleType
     * @return
     */
    public String getNextDjId(String ev, String moduleType);

    /**
     * 根据当前流程实例流水号获取待办人员列表
     * @param curNodeInstId
     * @return
     */
    public List<VuserTaskListOA> getuserbysql(Long curNodeInstId);

}
