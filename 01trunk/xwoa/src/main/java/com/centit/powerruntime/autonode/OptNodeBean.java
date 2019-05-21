package com.centit.powerruntime.autonode;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.SysUserManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;

public class OptNodeBean implements NodeEventSupport {
    private FlowEngine flowEngine;
    private FlowManager flowManager;
    private GeneralModuleParamManager generalModuleParamManager;
    private OptProcInfoManager optProcInfoManager;
    private SysUserManager sysUserManager;
    /**
     * 节点创建成功之后执行该方法
     */
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        if (StringUtils.isNotBlank(optParam)) {
        if (optParam.contains("pfjs")) {
            String unitCode="001801";
            FUserunit userUnit = sysUserManager.getUserPrimaryUnit(nodeInst.getUnitCode());
            if(userUnit!=null){
                unitCode=userUnit.getUnitcode();
                flowManager.assignTask(nodeInst.getNodeInstId(),nodeInst.getUnitCode(), "99999999", null, "系统指定");
                flowManager.saveNodeInstUnit(nodeInst.getNodeInstId(),unitCode, "99999999");
            }
        }
        }
    }

    private String getModuleCode(String param) {
        if (StringUtils.isBlank(param))
            return null;
        String[] params = param.split("&");
        for (String p : params) {
            String[] codes = p.split("=");
            if (codes.length > 1) {
                if ("moduleCode".equals(codes[0]))
                    return codes[1];
                else if ("moduleCode".equals(codes[1]))
                    return codes[0];
            }
        }
        return null;
    }
    /**
     * 节点创建之前执行该方法
     */
    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,String optParam, String optUserCode) {
        String moduleCode = getModuleCode(optParam);
        GeneralModuleParam model = generalModuleParamManager.getObjectById(moduleCode);
        if (StringUtils.isNotBlank(optParam)) {
            if (optParam.contains("setZrs")) {
                flowEngine.deleteFlowOrganize(flowInst.getFlowInstId(), "zrs");
                Set<String> zrs = flowEngine.viewFlowWorkTeam( flowInst.getFlowInstId(), model.getTeamRoleCode());
                if(zrs!=null && zrs.size()>0){
                    flowEngine.assignFlowOrganize(flowInst.getFlowInstId(), "zrs",zrs);
                }
            }
        }
    
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode) {
        return false;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public GeneralModuleParamManager getGeneralModuleParamManager() {
        return generalModuleParamManager;
    }

    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public FlowManager getFlowManager() {
        return flowManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

}
