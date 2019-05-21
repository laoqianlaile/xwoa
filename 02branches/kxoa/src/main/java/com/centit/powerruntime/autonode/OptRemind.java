package com.centit.powerruntime.autonode;

import com.centit.oa.service.OaRemindInformationManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.WorkflowException;

public class OptRemind implements NodeEventSupport {
    private OptBaseInfoManager optBaseInfoManager;
    private OaRemindInformationManager oaRemindInformationManager;
    private OptProcInfoManager optProcInfoManager;
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
        // TODO Auto-generated method stub

    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
        // TODO Auto-generated method stub
        //发送提醒，根据环节是否需要发送提醒定制个性提醒；
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst.getFlowInstId());
        OptProcInfo prevprocInfo=optProcInfoManager.getObjectByNodeInstId(nodeInst.getPrevNodeInstId());
        OptProcInfo procInfo=optProcInfoManager.getObjectByNodeInstId(nodeInst.getNodeInstId());
        //会议流程-会议保障部门 有部门办理了 需要发出
        if("hys_APFW".equals(procInfo.getNodeCode())){
            String remark="";
            remark=procInfo.getNodename()+"["+CodeRepositoryUtil.getValue("unitcode",procInfo.getUnitcode())+"]"+CodeRepositoryUtil.getValue("usercode",procInfo.getUsercode())+"在"+DatetimeOpt.convertDateToString(procInfo.getTransdate(),"yyyy年MM月dd日 HH:mm")+"做了办理，办理内容为："+procInfo.getTranscontent();
            oaRemindInformationManager.sendOaRemindInformation(bean.getDjId(), optUserCode, prevprocInfo.getUsercode(),"["+CodeRepositoryUtil.getDataPiece("oa_ITEM_TYPE",BizCommUtil.getPrefix4Biz(bean.getDjId(),1))+"]"+bean.getTransaffairname()+"的提醒",remark);
        }
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode)
            throws WorkflowException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }

}
