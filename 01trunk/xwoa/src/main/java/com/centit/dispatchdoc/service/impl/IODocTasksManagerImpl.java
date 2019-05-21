package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.dao.CommonDocTaskDao;
import com.centit.dispatchdoc.dao.DispatchDocTaskDao;
import com.centit.dispatchdoc.dao.IncomeDocTaskDao;
import com.centit.dispatchdoc.po.CommonDocTask;
import com.centit.dispatchdoc.po.DispatchDocTask;
import com.centit.dispatchdoc.po.IncomeDocTask;
import com.centit.dispatchdoc.service.IODocTasksManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;

public class IODocTasksManagerImpl implements IODocTasksManager {
    
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IODocTasksManager.class);

    private DispatchDocTaskDao dispatchDocTaskDao;
    private IncomeDocTaskDao incomeDocTaskDao;
    private CommonDocTaskDao commonDocTaskDao;
    
    private WfActionTaskDao actionTaskDao;
    private OaSmssendManager oaSmssendManager;

    public void setCommonDocTaskDao(CommonDocTaskDao commonDocTaskDao) {
        this.commonDocTaskDao = commonDocTaskDao;
    }

    public List<IncomeDocTask> listIncomeDocTask(Map<String, Object> filterMap,
            PageDesc pageDesc) {

        return incomeDocTaskDao.listObjects(filterMap, pageDesc);
    }
    
    public List<IncomeDocTask> listIncomeDocTask(Map<String, Object> filterMap) {

        return incomeDocTaskDao.listObjects(filterMap);
    }

    public List<DispatchDocTask> listDispatchDocTask(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return dispatchDocTaskDao.listObjects(filterMap, pageDesc);
    }

    public List<CommonDocTask> listCommonDocTask(Map<String,Object> filterMap, PageDesc pageDesc){
        return commonDocTaskDao.listIncomeDocTask_Common(filterMap, pageDesc);
    }
    
    public List<CommonDocTask> listCommonDocTask(Map<String,Object> filterMap){
        return commonDocTaskDao.listObjects(filterMap);
    }
    
    public void setIncomeDocTaskDao(IncomeDocTaskDao baseDao) {
        this.incomeDocTaskDao = baseDao;
    }

    public void setDispatchDocTaskDao(DispatchDocTaskDao baseDao) {
        this.dispatchDocTaskDao = baseDao;
    }
    
    public IncomeDocTask getIncomeDocTaskByDjId(String djID){
        return incomeDocTaskDao.getObjectByDjId(djID);
    }

    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

    @Override
    public List<IncomeDocTask> listIncomeDocTask_GW(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return incomeDocTaskDao.listIncomeDocTask_GW(filterMap,pageDesc);
    }
    
    /**
     * 发送短信
     * @param isSendMessage
     * @param senderCode
     * @param nextNode
     *//*
     
    public void sendMsgs(String isSendMessage, String senderCode, Set<Long> nextNode) {

        if ("T".equals(isSendMessage) && nextNode != null && !nextNode.isEmpty()) {
         
            for (Long nodeActive : nextNode) {
                List<VUserTaskList> tasks = actionTaskDao
                        .getTasksByNodeInstId(nodeActive);
                for(VUserTaskList task : tasks){
                    
                    String djId = task.getFlowOptTag();
                    String bjType = null;   // 短信来源
                    if(StringUtils.isNotBlank(djId)){
                        bjType = djId.substring(0,2);                        
                    }
                    // 通用内容
                    String comContent = CodeRepositoryUtil.getValue("sendMSgMod", "com"); //
                    // 短信内容
                    String content = CodeRepositoryUtil.getValue("sendMSgMod", bjType);
                    
                    if(null != bjType && bjType.equals(content)){
                        content = comContent;
                    }
                    comContent = StringUtils.replace(comContent, "{username}", CodeRepositoryUtil.getValue("usercode", task.getUserCode()));
                    comContent = StringUtils.replace(comContent, "{event}", task.getFlowOptName());
                    oaSmssendManager.saveMsgs(task.getUserCode(), senderCode, comContent, bjType);
                }
            }
        }
    }*/
}
