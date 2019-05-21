package com.centit.powerruntime.autonode;

import java.util.Collections;
import java.util.List;

import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.WorkflowException;

/**
 * 
 * 
 * 领导签发节点 将签发人更新到m_dispatch_doc表中的dispatch_user字段
 * @author lay
 * @create 2015年11月17日
 * @version
 */
public class OptLeaderSignIssue implements NodeEventSupport{
    private OptBaseInfoManager optBaseInfoManager;
    private OptProcInfoManager optProcInfoManager;
    private OptIdeaInfoDao optIdeaInfoDao;
    private FlowEngine flowEngine;
    
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
        
        OptProcInfo procInfo=optProcInfoManager.getObjectByNodeInstId(nodeInst.getNodeInstId());
        //领导签发回退--发文部处长审核、分管主席会签
        if("FW_CSSH".equals(procInfo.getNodeCode())||"FW_FGLDHQ".equals(procInfo.getNodeCode())){
            flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "rollBack",procInfo.getNodeCode());//按最后提交意见决定走向
        }
        
        if("FW_FGLDHQ".equals(procInfo.getNodeCode())){
            
            
            //分管领导审核节点 多人操作  有一人决定办理意见为ZWH 流程流转至置文号节点  同时将该记录置为签发信息
            OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst
                    .getFlowInstId());
           
            List<OptIdeaInfo> hqlist = optProcInfoManager.listIdeaLogsByDjIdAndNodeCode( bean.getDjId(),"FW_FGLDHQ");
            Collections.reverse(hqlist);
            OptIdeaInfo  qfidea=new OptIdeaInfo(); //签发 若分管领导直接置文号  该领导意见作为签发意见
            OptIdeaInfo  idea=new OptIdeaInfo(); //最新提交意见
            int ZWhnum = 0;
           
            if (null != hqlist && hqlist.size() > 0) {  
                
                for (OptIdeaInfo info : hqlist) {
                    if ("ZWH".equals(info.getIdeacode())) {
                        ZWhnum = ZWhnum + 1;
                        qfidea=info;
                    }else{
                        idea=info;
                    } 
                }
                if(ZWhnum>0){
                    flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "ideacode_fgld","ZWH");//提交置文号
                    qfidea.setModuleCode("FW_LDQF");//领导签发环节
                    optIdeaInfoDao.saveObject(qfidea);
                } else{//维持原来走向
                    flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "ideacode_fgld",idea.getIdeacode());//按最后提交意见决定走向
                }
            }  
        }
    
        
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode)
            throws WorkflowException {
        return false;
    }

    public OptBaseInfoManager getOptBaseInfoManager() {
        return optBaseInfoManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

   
    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }
    public void setOptIdeaInfoDao(OptIdeaInfoDao baseDao)
    {
        this.optIdeaInfoDao = baseDao;
    }
}
