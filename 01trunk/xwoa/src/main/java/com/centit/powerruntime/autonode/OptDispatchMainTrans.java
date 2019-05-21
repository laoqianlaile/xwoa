/**
 * 
 */
package com.centit.powerruntime.autonode;

import java.util.List;

import com.centit.sys.po.FUnitinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfOrganizeDao;

/**
 * 主办分发
 * 
 * @author JDN
 * @create 2014-2-12
 * @version 
 */
public class OptDispatchMainTrans implements NodeEventSupport{

    private SysUnitManager sysUnitManager;
    
    private FlowEngine flowEngine;
    
    private WfOrganizeDao wfOrganizeDao;
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
       
    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean runAutoOperator(FlowInstance flowInst,
            NodeInstance nodeInst, String optParam, String optUserCode) {
        List<FUnitinfo> unitsList = CodeRepositoryUtil.getUnitList(nodeInst
                .getUnitCode());
        
        FlowNodeInfo nodeInfo =  flowEngine.getNodeInfoById(nodeInst.getNodeId());
        String nodeCode = nodeInfo.getNodeCode();
        
        
        if (unitsList != null && unitsList.size() > 1) {
            flowEngine.saveFlowNodeVariable(nodeInst.getNodeInstId(), "isWb",
                    "1");
            /*
            WfOrganizeId orgId = new WfOrganizeId();
            orgId.setFlowInstId(flowInst.getFlowInstId());
            orgId.setUnitCode(nodeInst.getUnitCode());
            if(nodeCode != null && nodeCode.equals("zbff")){
                orgId.setRoleCode("xtczb");
               if( wfOrganizeDao.getObjectById(orgId) == null ){
                   flowEngine.assignFlowOrganize(flowInst.getFlowInstId(), "xtczb", nodeInst.getUnitCode()); 
               }
            }
            
            if(nodeCode != null && nodeCode.equals("hbff")){
                orgId.setRoleCode("xtcfb");
                if( wfOrganizeDao.getObjectById(orgId) == null ){
                //flowEngine.deleteFlowOrganize(flowInst.getFlowInstId(), "xtcfb", nodeInst.getUnitCode()); 
                flowEngine.assignFlowOrganize(flowInst.getFlowInstId(), "xtcfb", nodeInst.getUnitCode()); 
                }
            }
            */
        }else{
            flowEngine.saveFlowNodeVariable(nodeInst.getNodeInstId(), "isWb","0");
            if(nodeCode != null && nodeCode.equals("hbff")){
                flowEngine.saveFlowVariable(nodeInst.getFlowInstId(), "ishq", "T");
                flowEngine.deleteFlowOrganize(flowInst.getFlowInstId(), "ptcsfb", nodeInst.getUnitCode()); 
                flowEngine.assignFlowOrganize(flowInst.getFlowInstId(), "ptcsfb", nodeInst.getUnitCode()); 
            }
        }
        
        if(nodeCode != null && nodeCode.equals("zbff")){
            flowEngine.saveFlowVariable(flowInst.getFlowInstId(), "iswbhq","F");
        }
        
        return true;
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public WfOrganizeDao getWfOrganizeDao() {
        return wfOrganizeDao;
    }

    public void setWfOrganizeDao(WfOrganizeDao wfOrganizeDao) {
        this.wfOrganizeDao = wfOrganizeDao;
    }

}
