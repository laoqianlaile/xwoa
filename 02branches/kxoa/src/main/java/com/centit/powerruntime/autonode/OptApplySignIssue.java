/**
 * 
 */
package com.centit.powerruntime.autonode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;

/**
 * 
 * 
 * @author ljy
 * @create 2013-11-14
 * @version 
 */
public class OptApplySignIssue implements NodeEventSupport {

    private OptBaseInfoManager optBaseInfoManager;
    private IncomeDocManager incomeDocManager;
    
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        
    }

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("flowInstId", flowInst.getFlowInstId());
        List<OptBaseInfo> baseInfoList = optBaseInfoManager.listObjects(paramMap);

        String djId = "";
        if (null == baseInfoList || baseInfoList.isEmpty()) {
            djId = flowInst.getFlowOptTag();
        } else {
            djId = baseInfoList.get(0).getDjId();
        }
        
        IncomeDoc incomeDoc = incomeDocManager.getObjectById(djId);
        
        if (null != incomeDoc) {
            incomeDoc.setSignIssueDate(DatetimeOpt.currentUtilDate());
            
            incomeDocManager.saveObject(incomeDoc);
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

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }

}
