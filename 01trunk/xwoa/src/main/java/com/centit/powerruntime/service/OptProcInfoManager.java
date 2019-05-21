package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptShowIdeaInfo;
import com.centit.powerruntime.po.VOptStuffInfo;
import com.centit.powerruntime.po.VoptIdeaInfo;
import com.centit.workflow.sample.po.WfNodeInstance;
/**
 * 
 * 通用业务过程处理类
 * 
 * @author codefan
 * @create 2012-6-27
 * @version
 */
public interface OptProcInfoManager extends BaseEntityManager<OptProcInfo> 
{
    /**
     * 获取业务办件的所有过程信息
     * @param djId
     * @return
     */
    public List<OptProcInfo> listProcessByDjId(String djId);

    /**
     * 获取过程信息
     * @param nodeInstId
     * @return
     */
    public OptProcInfo getObjectByNodeInstId(long nodeInstId);
    /**
     * 获取过程信息
     * @param nodeInstId
     * @return
     */
    public OptIdeaInfo getOptIdeaInfoByNodeInstId(long nodeInstId);
    /**
     * 根据Id获取一个具体的附件
     * @param id
     * @return
     */
    public OptStuffInfo getStuffById(String id);
    /**
     * 保存附件
     * @param id
     * @return
     */
    public void saveStuffInfo(OptStuffInfo o);
    
    /**
     * 获取办件的所有附件
     * @param djId
     * @return
     */
    public List<OptStuffInfo> listStuffsByDjId(String djId);
    
    /**
     * 分组Stuffname
     * @param djId
     * @return
     */
    public List<Object[]> listStuffsByDjIdGroupByStuffname0(String djId);
    
    /**
     * 分组Stuffname
     * @param djId
     * @return
     */
    public List<OptStuffInfo> listStuffsByDjIdGroupByStuffname1(String djId);
    
    
    /**
     * 获取办件的所有附件
     * @param djId
     * @return
     */
    public List<VOptStuffInfo> getStuffsByDjId(String djId);
    
    /**
     * 分组Stuffname
     * @param djId
     * @return
     */
    public List<VOptStuffInfo> getStuffsByDjIdGroupByStuffname1(String djId);
    /**
     * 获取某个节点上传的所有附件
     * @param nodeInstId
     * @return
     */
    public List<OptStuffInfo> listStuffsByNode(Long nodeInstId);
    /**
     * 获取某个类型附件
     * @param nodeInstId
     * @return
     */
    public List<OptStuffInfo> listStuffsByType(String djId ,String fileType);

    /**
     * 删除附件
     * @param o
     */
    public void deleteStuffInfo(OptStuffInfo o);
    
    
    public void deleteStuffByiszhi(String sortId);

    /**
     * 删除附件
     * @param id
     */
    public void deleteOptStuffInfoById(String id);
    
    /**
     * 获取所有的操作日志
     * @param djid
     * @return
     */
    public List<OptIdeaInfo>  listIdeaLogsByDjId(String djid);
    
    /**
     * 获取某一个节点的操作日志
     * @param djid
     * @param nodeInstId
     * @return
     */
    public List<OptIdeaInfo>  listIdeaLogs(String djid,Long nodeInstId);
    
    /**
     * 获取通用模块配置需要显示的操作日志
     * @param djid
     * @return
     */
    public List<VOptShowIdeaInfo>  listShowIdeaLogsByDjId(String djid);
    
    /**
     * 是否显示办理意见iframe
     * @param djId
     * @return
     */
    public  boolean isShowIdeaLogs(String djid) ;
    /**
     * 获取阶段的所有操作日志
     * @param djid
     * @param flowPhase
     * @return
     */
    public List<OptIdeaInfo>  listIdeaLogsByDjIdAndFlowPhase(String djid,String flowPhase);
    /**
     * 保存操作日志
     * @param o
     */
    public void saveIdeaInfo(OptIdeaInfo o);
    /**
     * 保存操作日志
     * @param optIdeaInfo
     * @param procInfo
     */
    public void saveIdeaInfo(OptIdeaInfo optIdeaInfo,OptProcInfo procInfo);

    /**
     * 删除操作日志
     * @param o
     */
    public void deleteIdeaInfo(OptIdeaInfo o);
    /**
     * 删除操作日志
     * @param id
     */
    public void deleteIdeaInfoById(String id);

    /**
     * 列举所有的关注
     * @param djid
     * @return
     */
    public List<OptProcAttention>  listProcAttentionsByDjId(String djid);
    /**
     * 添加关注
     * @param o
     */
    public void saveProcAttention(OptProcAttention o);
    /**
     * 删除关注
     * @param o
     */
    public void deleteProcAttention(OptProcAttention o);
    /**
     * 删除关注
     * @param djid
     * @param usercode
     */
    public void deleteProcAttentionById(String djid,String usercode);
    /**
     * 清除所有关注
     * @param djid
     */
    public void deleteProcAttentionByDjId(String djid);
    
 
    /**保存关注  */
    public void saveAtt(OptProcAttention optProcAttention);
    
   
    public void isInuse(String djid);
    /**获取各步骤附件信息  */
    public List<OptStuffInfo> getStuffInfoList(OptProcInfo opi);

      /**
     * 获取某个文书类型附件
     * @param nodeInstId
     * @return
     */
    public List<OptStuffInfo> listStuffsByArchiveType(String djId ,String archiveType);
 
    /**
     * 根据业务主键、环节代码获取过程日志信息
     * @param djId
     * @param nodeCode
     * @return
     */
    public List<OptProcInfo> getObjectsByNodeCode(String djId,String nodeCode);
    /**
     * 根据业务主键、环节代码获取过程日志信息
     * @param djId
     * @param nodeCode
     * @return
     */
    public List<OptProcInfo> getObjectsByNodeCode(String djId, String[] nodeCode);

    /**
     * 获取流程外过程日志信息
     * @param nodeInstId
     * @param djId
     * @return
     */
    public OptProcInfo getObjectByNodeInstIdAndDjId(Long nodeInstId, String djId);

    /**
     * 根据业务主键 获取过程日志信息中有文书的节点
     * @param djId
     * @return
     */
    public List<OptProcInfo> getObjectOfRecordIdById(String djId);
    /**
     *  根据业务编号、流程环节代码获取流程办理日志信息
     * @param djId
     * @param nodeCode
     * @return
     */
    public List<OptIdeaInfo> listIdeaLogsByDjIdAndNodeCode(String djId,
            String nodeCode);
    
    /**
     *  根据业务编号、流程环节代码获取流程办理日志信息
     * @param djId
     * @param nodeCodes
     * @return
     */
    public List<OptIdeaInfo> listIdeaLogsByDjIdAndNodeCodes(String djId,
            String nodeCodes);

    /**
     *  根据通用模块配置、流程环节代码获取流程办理日志信息
     * @param djId
     * @param nodeCodes
     * @return
     */
    public List<OptIdeaInfo> listIdeaLogsByDjIdAndModuleCode(String djId,
            String ModuleCode);
    
    public List<OptProcInfo> getObjectOfRecordIdById(String djId,
            String documentType);
    
    /**
     * 获取节点的操作日志
     * @param nodeInstId
     * @param djid
     * @return
     */
    public List<OptIdeaInfo>  listIdeaLogsByNodeInstIdAndDjId(Long nodeInstId,String djid);
    
    /**
     * 根据业务主键,节点，用户 获取过程日志信息
     * @param djId
     * @param nodeInstId
     * @param usercode
     * @return
     */
    public OptProcInfo getObjectByDjidAndNodeInstId(String djId,Long nodeInstId,String usercode);
    
    /**
     * 获取当天的操作日志
     * @return
     */
    public List<VoptIdeaInfo>  listIdeaLogsBytoday();

     /**
     *  根据通用模块配置、流程环节代码获取流程办理日志信息
     * @param djId
     * @param nodeCodes
     * @return
     */
    public List<OptIdeaInfo> listIdeaLogsByDjIdAndModuleCodeXW(String djId,
            String ModuleCode,String unitcode,String flag);
    
    
    /**
     * 删除某个实例流程的所有leaderorder
     */
   public void deleteLeaderOder(Long flowInstId);
   
   /**
    * 通过最后处理人获取流程节点
    * @param djId
    * @return
    */
   List<WfNodeInstance> listfNodeInstanceByUser(String userCode);
}
