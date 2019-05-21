package com.centit.dispatchdoc.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.CommonDocTask;
import com.centit.dispatchdoc.po.DispatchDocTask;
import com.centit.dispatchdoc.po.IncomeDocTask;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IODocTasksManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.sys.security.FUserDetail;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;

public class IODocTasksAction extends IODocCommonBizAction<OptProcInfo>
                 implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private IODocTasksManager ioDocTasksManager;
    private DispatchDocManager dispatchDocManager;
    private OptBaseInfoManager optBaseInfoManager;
    private FlowDefine flowDefine;

    private List<IncomeDocTask> incomeDocList;
    private List<CommonDocTask> commonDocList;
    private List<DispatchDocTask> dispatchDocList;
    private List<FlowDescribe> flowList;
    
    private Long flowInstId;
    private int totalRows;
    private String flowCode;
    

    public String refreshTasks() {
        OptBaseInfo optBase = optBaseInfoManager.getOptBaseByFlowId(flowInstId);

        String djId = "";
        String item_ID = "";
        if (optBase != null) {
            djId = optBase.getDjId();
            flowCode = optBase.getFlowCode();
            item_ID = optBase.getPowerid();
        }
        if (djId.indexOf("WT") != -1) {
            return "list";
        } else if (item_ID.indexOf("GW") != -1) {
            return listIncomeDocTasks_Common();// 公文
        } else {
            return djId.indexOf("FW") != -1 ? listDispatchDocTasks()
                    : listIncomeDocTasks();
        }
    }

    /**
     * 收文待办查询
     * 
     * @return
     */
    public String listIncomeDocTasks() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);

            filterMap.put("flowCode", flowCode);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            incomeDocList = ioDocTasksManager.listIncomeDocTask(filterMap,
                    pageDesc);
            totalRows = pageDesc.getTotalRows();
            return "IncomeDocTask";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

    }

    /**
     * 办(阅办)件 待办查询
     * 
     * @return
     */
    public String listIncomeDocTasks_by() {
        this.listIncomeDocTasks();
        return "listIncomeDocTasks_by";
    }

    /**
     * 公文待办——所有待办都在这边
     * 
     * @return
     */
    public String listIncomeDocTasks_gw() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("userCode", userCode);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        incomeDocList = ioDocTasksManager.listIncomeDocTask_GW(filterMap,
                pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listIncomeDocTasks_gw";
    }

    /**
     * 
     * @return
     */
    public String listIncomeDocTasks_Common() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("userCode", userCode);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        commonDocList = ioDocTasksManager
                .listCommonDocTask(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listIncomeDocTasks_common";
    }

    public String listIncomeDocTasks_dc() {
        return listIncomeDocTasks_by();
    }

    /**
     * 发文待办查询
     * 
     * @return
     */
    public String listDispatchDocTasks() {
        //  发文
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);
            filterMap.put("flowCode", flowCode);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            dispatchDocList = ioDocTasksManager.listDispatchDocTask(filterMap,
                    pageDesc);
            totalRows = pageDesc.getTotalRows();
            return "DispatchDocTask";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 选择流程
     * 
     * @return
     */
    public String ioSelectFlow() {
        try {
            flowList = flowDefine.getFlowsByOptId("IO_DOC");

            savedMessage();
            return "ioSelectFlow";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            saveError(e.getMessage());
            return ERROR;
        }
    }

    public void setIoDocTasksManager(IODocTasksManager ioDocTasksManager) {
        this.ioDocTasksManager = ioDocTasksManager;
    }

    public List<IncomeDocTask> getIncomeDocList() {
        return incomeDocList;
    }

    public void setIncomeDocList(List<IncomeDocTask> incomeDocList) {
        this.incomeDocList = incomeDocList;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<DispatchDocTask> getDispatchDocList() {
        return dispatchDocList;
    }

    public void setDispatchDocList(List<DispatchDocTask> dispatchDocList) {
        this.dispatchDocList = dispatchDocList;
    }

    public DispatchDocManager getDispatchDocManager() {
        return dispatchDocManager;
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public List<FlowDescribe> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<FlowDescribe> flowList) {
        this.flowList = flowList;
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }
    
    public List<CommonDocTask> getCommonDocList() {
        return commonDocList;
    }

    public void setCommonDocList(List<CommonDocTask> commonDocList) {
        this.commonDocList = commonDocList;
    }

}
