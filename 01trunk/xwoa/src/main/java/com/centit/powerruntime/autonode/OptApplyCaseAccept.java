/**
 * 
 */
package com.centit.powerruntime.autonode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.sys.service.SysUserManager;
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
public class OptApplyCaseAccept implements NodeEventSupport {

    private OptBaseInfoManager optBaseInfoManager;
    private IncomeDocManager incomeDocManager;
    private OptProcInfoManager optProcInfoManager;
    private SysUserManager sysUserManager;
    private OptFlowNoInfoManager optFlowNoInfoManager;
    
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
        OptBaseInfo baseInfo = null;
        if (null == baseInfoList || baseInfoList.isEmpty()) {
            djId = flowInst.getFlowOptTag();
        } else {
            djId = baseInfoList.get(0).getDjId();
            baseInfo = baseInfoList.get(0);
        }
        
        IncomeDoc incomeDoc = incomeDocManager.getObjectById(djId);
        
        if (null != incomeDoc) {
            incomeDoc.setCaseAcceptDate(DatetimeOpt.currentUtilDate());
            
            incomeDocManager.saveObject(incomeDoc);
            
            if (null != baseInfo && StringUtils.isNotBlank(incomeDoc.getApplyItemType()) 
                    && StringUtils.isBlank(baseInfo.getAcceptArchiveNo())) { // 如果是审批类型，并且受理号为空
                OptProcInfo procInfo = optProcInfoManager.getObjectById(nodeInst.getNodeInstId());
                
                if (null != procInfo && "T".equalsIgnoreCase(procInfo.getIdeacode())) { // 如果办理流程为同意受理，则生成受理号并保存
                    FUserunit userUnit = sysUserManager.getUserPrimaryUnit(optUserCode);
                    String acceptArchiveNo = optFlowNoInfoManager.genFWH("DJH", "1", null, userUnit.getUnitcode(), null);
                    baseInfo.setAcceptArchiveNo(acceptArchiveNo);
                    
                    optBaseInfoManager.saveObject(baseInfo);
                }
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

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setOptFlowNoInfoManager(OptFlowNoInfoManager optFlowNoInfoManager) {
        this.optFlowNoInfoManager = optFlowNoInfoManager;
    }
    
}
