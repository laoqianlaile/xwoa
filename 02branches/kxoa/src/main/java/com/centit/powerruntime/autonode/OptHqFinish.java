package com.centit.powerruntime.autonode;

import java.util.List;

import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;

/*
 * 发文分管领导会签，决定流程走向
 */
public class OptHqFinish implements NodeEventSupport {
    private OptBaseInfoManager optBaseInfoManager;
    private OptProcInfoManager optProcInfoManager;
    private FlowEngine flowEngine;

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {

    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst
                .getFlowInstId());
        List<OptProcInfo> hqlist = optProcInfoManager.getObjectsByNodeCode(
                bean.getDjId(),"FW_LDHQ");
        int Bnum = 0;// ideacode_B
        int Tnum = 0;// ideacode_T
        if (null != hqlist && hqlist.size() > 0) {            
            for (OptProcInfo info : hqlist) {
                if ("T".equals(info.getIdeacode())) {
                    Tnum = Tnum + 1;
                } else if ("B".equals(info.getIdeacode())) {
                    Bnum = Bnum + 1;
                }
            }
            
          //汇总意见，决定走退回还是提交领导签发的操作
            if(Tnum>=Bnum){
                flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "ideacode","T");//提交领导签发
            }else{
                flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "ideacode","B");//提交秘书办理
            } 
        }
        
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode) {
        return true;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

}
