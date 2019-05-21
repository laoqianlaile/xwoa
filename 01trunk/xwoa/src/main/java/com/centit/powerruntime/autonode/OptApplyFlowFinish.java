/**
 * 
 */
package com.centit.powerruntime.autonode;

import java.util.Date;
import java.util.List;

import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.VProcTransUsersManager;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserManager;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.WfFlowInstance;
import com.centit.workflow.sample.po.WfNodeInstance;
import com.centit.workflow.sample.po.WfTeam;

/**
 * 
 * 
 * @author hx
 * @create 2016-03-17
 * @version
 */
public class OptApplyFlowFinish implements NodeEventSupport {

    private OptBaseInfoManager optBaseInfoManager;
    private OptProcInfoManager optProcInfoManager;
    private OptStuffInfoManager optStuffInfoManager;
    private OaMeetApplyManager oaMeetApplyManager;
    private OaCarApplyManager oaCarApplyManager;
    private FlowManager flowManager;
    protected FlowEngine flowEngine;
    private SysUserManager sysUserManager;

    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst.getFlowInstId());
        OptProcInfo procInfo = optProcInfoManager.getObjectByNodeInstId(nodeInst.getPrevNodeInstId());
        if (procInfo == null) {
            procInfo = new OptProcInfo();
        }
        // 更新业务基本信息表状态
        // 会议室申请流程 会议安排节点hys_ap，如果不同意安排，需将会议室申请状态改为不同意；
        if ("hys_ap".equals(procInfo.getNodeCode())) {
            if ("F".equals(procInfo.getIdeacode())) {
                OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(bean
                        .getDjId());
                oaMeetApply.setState("4");// 不同意
                oaMeetApply.setBizstate("C");// 业务状态
                oaMeetApply.setBiztype("C");// 业务类别
                oaMeetApply.setSolvestatus("F");// 申请状态-不同意
                oaMeetApplyManager.saveObject(oaMeetApply);
            }
        }
        //车辆申请流程 会议安排节点hys_ap，如果不同意安排，需将会议室申请状态改为不同意；
        if ("car_sh".equals(procInfo.getNodeCode())||"car_ap".equals(procInfo.getNodeCode())) {
            if ("F".equals(procInfo.getIdeacode())) {
                OaCarApply oaCarApply = oaCarApplyManager.getObjectById(bean.getDjId());
                oaCarApply.setState("4");// 不同意
                oaCarApply.setBizstate("C");// 业务状态
                oaCarApply.setBiztype("C");// 业务类别
                oaCarApply.setSolvestatus("F");// 申请状态-不同意
                oaCarApplyManager.saveObject(oaCarApply);
            }
        }else if ("sw_yd".equals(procInfo.getNodeCode())|| "SW_LDYJSY".equals(procInfo.getNodeCode())||"sw_py".equals(procInfo.getNodeCode())) {
            if ("B".equals(procInfo.getIdeacode())) {// 部门收文环节
                bean.setBizstate("C");
                bean.setBiztype("C");
                bean.setSolvetime(new Date(System.currentTimeMillis()));
                bean.setSolvenote(procInfo.getTransidea());
                bean.setSolvestatus(procInfo.getIdeacode());
                optBaseInfoManager.saveObject(bean);

            }

        } /*else if(optParam!=null && optParam.contains("xwsw_wcyb")) {//收文办结节点特殊处理
            List<NodeInstance> wfids= flowManager.listFlowInstNodes(flowInst.getFlowInstId());
            boolean flag=true;
            for(int i=0;i<wfids.size();i++) {//循环
               if(!"完成阅办".equals(wfids.get(i).getNodeName())) {
                    if(!"C".equals(wfids.get(i).getNodeState())) {//nodestate=c表示已完成
                        flag=false;
                        break;
                    }
                }
            }
            if(flag==true) {//不存在非完成阅办
                bean.setBizstate("C");
                bean.setBiztype("C");
                bean.setSolvetime(new Date(System.currentTimeMillis()));
                bean.setSolvenote(procInfo.getTransidea());
                bean.setSolvestatus(procInfo.getIdeacode());
                optBaseInfoManager.saveObject(bean);
                flowManager.stopInstance(nodeInst.getFlowInstId(), "99999999",null);
            }            
        }*/else {
            bean.setBizstate("C");
            bean.setBiztype("C");
            bean.setSolvetime(new Date(System.currentTimeMillis()));
            bean.setSolvenote(procInfo.getTransidea());
            bean.setSolvestatus(procInfo.getIdeacode());
            optBaseInfoManager.saveObject(bean);
        }

    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(flowInst.getFlowInstId());
        OptProcInfo procInfo = optProcInfoManager.getObjectByNodeInstId(nodeInst.getPrevNodeInstId());
         // 收文批阅
         if (null!=procInfo && "NJSW_PY".equals(procInfo.getNodeCode())) {
            List<WfTeam> t = flowEngine.viewFlowWorkTeamList(flowInst.getFlowInstId(), "swjbr");
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


    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }


    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }


    public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
        this.oaMeetApplyManager = oaMeetApplyManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public OaCarApplyManager getOaCarApplyManager() {
        return oaCarApplyManager;
    }

    public void setOaCarApplyManager(OaCarApplyManager oaCarApplyManager) {
        this.oaCarApplyManager = oaCarApplyManager;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

}
