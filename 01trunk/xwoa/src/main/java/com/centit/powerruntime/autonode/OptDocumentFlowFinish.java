/**
 * 
 */
package com.centit.powerruntime.autonode;

import java.util.Date;
import java.util.List;

import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.WfTeam;

/**
 * 
 * 
 * 公文业务自动执行节点
 * @version
 */
public class OptDocumentFlowFinish implements NodeEventSupport {

    private OptBaseInfoManager optBaseInfoManager;
    private OptProcInfoManager optProcInfoManager;
    
    protected FlowEngine flowEngine;

    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst
                .getFlowInstId());
        OptProcInfo procInfo = optProcInfoManager
                .getObjectByNodeInstId(nodeInst.getPrevNodeInstId());

        if (procInfo == null) {
            procInfo = new OptProcInfo();
        }
        // 更新业务基本信息表状态
     
            bean.setBizstate("C");
            bean.setBiztype("C");
            bean.setSolvetime(new Date(System.currentTimeMillis()));
            bean.setSolvenote(procInfo.getTransidea());
            bean.setSolvestatus(procInfo.getIdeacode());
            optBaseInfoManager.saveObject(bean);
  

    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst
                .getFlowInstId());
        OptProcInfo procInfo = optProcInfoManager
                .getObjectByNodeInstId(nodeInst.getPrevNodeInstId());
       
        // 收文批阅
         if (null!=procInfo&&"NJSW_PY".equals(procInfo.getNodeCode())) {
            List<WfTeam> t = flowEngine.viewFlowWorkTeamList(
                    flowInst.getFlowInstId(), "swjbr");
            if (null != t && t.size() > 0) {

            } else {
                bean.setBizstate("C");
                bean.setBiztype("C");
                bean.setSolvetime(new Date(System.currentTimeMillis()));
                bean.setSolvenote(procInfo.getTransidea());
                bean.setSolvestatus(procInfo.getIdeacode());
                optBaseInfoManager.saveObject(bean);
            }
        } 
         
         
         
         
         
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode) {
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        // 收文传阅--归档时判断传阅经办人是否已全部选择归档 WF_TEAM swjbr
        if ("NJSW_GD".equals(nodeInfo.getNodeCode())) {

            List<WfTeam> t = flowEngine.viewFlowWorkTeamList(
                    flowInst.getFlowInstId(), "swjbr");
            if (null != t && t.size() > 0) {
                // 自动回退到传阅节点
                // flowManager.rollbackOpt(nodeInst.getNodeInstId(),optUserCode);
            }
            return null == t ? true : false;
        }

        return true;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public OptBaseInfoManager getOptBaseInfoManager() {
        return optBaseInfoManager;
    }

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

   
  


}
