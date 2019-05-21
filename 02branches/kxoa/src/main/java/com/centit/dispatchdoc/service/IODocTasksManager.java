package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.CommonDocTask;
import com.centit.dispatchdoc.po.DispatchDocTask;
import com.centit.dispatchdoc.po.IncomeDocTask;


public interface IODocTasksManager 
{
    /**
     * 收文待办事项查询
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<IncomeDocTask> listIncomeDocTask(Map<String,Object> filterMap, PageDesc pageDesc);
    
    public List<IncomeDocTask> listIncomeDocTask(Map<String,Object> filterMap);
    
    /**
     * 发文待办事项
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<DispatchDocTask> listDispatchDocTask(Map<String,Object> filterMap, PageDesc pageDesc);
    
    /**
     * 收发文待办事项
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<CommonDocTask> listCommonDocTask(Map<String,Object> filterMap, PageDesc pageDesc);
    
    public List<CommonDocTask> listCommonDocTask(Map<String,Object> filterMap);
    /**
     * 
     * @param djID
     * @return
     */
    public IncomeDocTask getIncomeDocTaskByDjId(String djID);

    /**
     * 公文类的待办事项
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<IncomeDocTask> listIncomeDocTask_GW(
            Map<String, Object> filterMap, PageDesc pageDesc);

}
