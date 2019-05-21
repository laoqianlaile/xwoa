/**
 * 
 */
package com.centit.powerruntime.autonode;

import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;

/**
 * 主办分发
 * 
 * @author JDN
 * @create 2014-2-12
 * @version 
 */
public class OptSecondDispatchTrans implements NodeEventSupport{

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
        //System.out.println(nodeInst.getUnitCode()+ CodeRepositoryUtil.getValue("unitcode", nodeInst.getUnitCode()));
        return true;
    }

}
