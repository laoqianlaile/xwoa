package com.centit.powerruntime.autonode;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeEventSupport;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.WorkflowException;

/**
 *  置文号 环节将签发人更新到m_dispatch_doc表中的dispatch_user字段
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年4月22日
 * @version
 */
public class OptFWZWH implements NodeEventSupport{
    private OptBaseInfoManager optBaseInfoManager;
    private DispatchDocManager dispatchDocMgr;
    @Override
    public void runAfterCreate(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("flowInstId", flowInst.getFlowInstId());
        List<OptBaseInfo> baseInfoList = optBaseInfoManager.listObjects(paramMap);
        
        String djId = "";
        if (null == baseInfoList || baseInfoList.isEmpty()) {
            djId = flowInst.getFlowOptTag();
        } else {
            djId = baseInfoList.get(0).getDjId();
        }
        //获取一个状态为持久的发文对象
        DispatchDoc obj = dispatchDocMgr.getObjectById(djId);
        if(obj != null){
          //在持久状态下只要改变了属性的值，事务提交后hibernate会自动更新到数据库，不需要我们手动去调用update方法
          obj.setDispatchUser(optUserCode);
        //记录发文时间
          obj.setDispatchdate(new Date(System.currentTimeMillis()));
        }
    }
    

    @Override
    public void runBeforeSubmit(FlowInstance flowInst, NodeInstance nodeInst,
            String optParam, String optUserCode) throws WorkflowException {
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

    public DispatchDocManager getDispatchDocMgr() {
        return dispatchDocMgr;
    }

    public void setDispatchDocMgr(DispatchDocManager dispatchDocMgr) {
        this.dispatchDocMgr = dispatchDocMgr;
    }
    
    
}
