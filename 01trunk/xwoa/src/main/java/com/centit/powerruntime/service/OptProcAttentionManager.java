package com.centit.powerruntime.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.VProcAttention;

public interface OptProcAttentionManager extends BaseEntityManager<OptProcAttention> 
{
    public List<VProcAttention> listProcAttention(
            Map<String, Object> filterMap, PageDesc pageDesc);

    /**
     * 添加关注
     * @param optProcAttention
     */
    public void saveAtt(OptProcAttention optProcAttention);
    
    /**
     * 保存关注意见信息
     * @param ideaInfo
     */
    public void saveOptIdeaInfo(OptIdeaInfo ideaInfo);
    
    /**
     * 根据登记标号查找关注人员
     * @param djId
     * @return
     */
    public List<OptProcAttention> getAttentionsByDjId(String djId);
    
    
    /**
     * 删除一个流程的所有关注
     */
    public void deleteProcAttention(String djId);
    /**
     * 获得一个流程的所有关注
     */
    public List<OptProcAttention> listAttentionByFlowInstId(String djId ,String optUser);
    
    public void deleteFlowAttentionByOptUser(String djId ,String optUser);
}