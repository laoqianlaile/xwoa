/**
 * 
 */
package com.centit.sys.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.core.utils.WorkTimeSpan;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.MessageSender;
import com.centit.sys.service.WorkCalendar;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.dao.WfFlowDefineDao;
import com.centit.workflow.sample.dao.WfFlowInstanceDao;
import com.centit.workflow.sample.dao.WfNodeDao;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfFlowDefine;
import com.centit.workflow.sample.po.WfFlowInstance;
import com.centit.workflow.sample.po.WfNode;
import com.centit.workflow.sample.po.WfNodeInstance;

/**
 * @author hx
 * 
 */
public class SampleFlowCornTask extends  QuartzJobBean{


    private static final Log log = LogFactory.getLog(SampleFlowCornTask.class);
    private String sConTimeDay="1990-10-10";
    private long    nConTime=0l;
    
    private WfFlowInstanceDao flowInstanceDao;
    private WfNodeInstanceDao nodeInstanceDao;
	private WfNodeDao flowNodeDao;
	private WfFlowDefineDao flowDefDao;
    private WfActionTaskDao actionTaskDao;
    private Map<String,MessageSender> msgSenders;
    private WorkCalendar workCalendar;

 
    @Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {		
	        /**这部分内容 也可以放到后台 通过数据库来执行，在程序中执行 如果服务器停止则计时会不正确，
	          *  并且如果部署到多个应用服务器 会出现重复扣除时间的问题
	          *  在数据库中执行 复杂在要重新实现 当前时间是否是工作时间的问题
	          *  TODO 这个是应该要在数据库中实现的
	          */
    	Date runTime=new Date();
	       /* if(workCalendar.isWorkTime(runTime)){
	            String sSonsumeTime = CodeRepositoryUtil.getValue("WFNotice", "consumeTime");
	            long consumeTime = (sSonsumeTime==null || "consumeTime".equals(sSonsumeTime)|| "".equals(sSonsumeTime) ) ?10l:Long.valueOf(sSonsumeTime);
	            String sNow =  DatetimeOpt.convertDateToString(runTime);
	            if(! sConTimeDay.equals(sNow)){
	                sConTimeDay = sNow;
	                nConTime = 0l;
	            }
	            if(nConTime<480l){
	                consumeLifeTime( consumeTime);
	                //nodeInstanceDao.updateTimeConsume(consumeTime);
	                //flowInstanceDao.updateTimeConsume(consumeTime);
	                log.info(runTime.toString() + "工作时间，各个在办件减少一个即时周期"+consumeTime+"分钟。");
	                nConTime += consumeTime;
	            }
	        }      */  
	        submitFlowExpireTime(runTime);
	        submitNodeExpireTime(runTime);
	}
    public void setMsgSenders(Map<String,MessageSender> senders ){
        msgSenders = senders;
    }
    
    public void setFlowInstanceDao(WfFlowInstanceDao flowInstanceDao) {
        this.flowInstanceDao = flowInstanceDao;
    }

    public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
        this.nodeInstanceDao = nodeInstanceDao;
    }
    public void setFlowNodeDao(WfNodeDao flowNodeDao) {
        this.flowNodeDao = flowNodeDao;
    }
    
    public void setFlowDefDao(WfFlowDefineDao flowDefDao) {
        this.flowDefDao = flowDefDao;
    }
    
    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }
    
    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }  
  
  
   
    private void consumeLifeTime(long consumeTime){
        List<WfFlowInstance> activeFlows = flowInstanceDao.listAllActiveTimerFlowInst();
        if(activeFlows==null || activeFlows.size()<1)
            return ;
        for(WfFlowInstance flowInst:activeFlows){
            List<WfNodeInstance> nodeList =  nodeInstanceDao.listObjects("From WfNodeInstance where flowInstId=? and nodeState in('N','W')" ,flowInst.getFlowInstId()) ;
            if(nodeList==null || nodeList.size()<1)
                continue;
            //boolean consume = true;
            boolean flowconsume = false;
            // T 计时、有期限   F 不计时   H仅环节计时  、暂停P   
            for(WfNodeInstance nodeInst : nodeList){ 
                WfNode node = flowNodeDao.getObjectById(nodeInst.getNodeId());
                if(node==null)
                    continue;

                if ( ("T".equals( nodeInst.getIsTimer() ) || "H".equals( nodeInst.getIsTimer() )) &&
                    ( nodeInst.getTimeLimit() != null) ){
                    nodeInst.setTimeLimit(  nodeInst.getTimeLimit() -consumeTime );
                    
                    nodeInstanceDao.saveObject(nodeInst);
                }
                
                if("T".equals( nodeInst.getIsTimer() ) && "T".equals( StringUtils.isBlank(node.getIsTrunkLine())?"T":node.getIsTrunkLine()))
                    flowconsume = true;
            }

            if( flowconsume && /*"T".equals(flowInst.getIsTimer()) && */ flowInst.getTimeLimit() != null){
                flowInst.setTimeLimit(  flowInst.getTimeLimit() - consumeTime );
                flowInstanceDao.saveObject(flowInst);
            }
      
        }
    }
        
    public void saveNotice(String noticeType,List<VUserTaskList> tasks){
        MessageSender sender = msgSenders.get(noticeType);
        if(sender==null){
            log.error("找不到消息发送器，请检查Spring中的配置和数据字典 WFNotice中的配置是否一致。");            
            return;
        }
        for(VUserTaskList task : tasks){
            sender.sendMessage("9999999",
                    task.getUserCode(), "任务即将到期："+task.getFlowInstId()+"-"+ task.getOptName(), 
                    "任务即将到期："+task.getNodeInstId()+"-"+task.getNodeName() +
                    "<a href='"+task.getNodeOptUrl()+"' target='_blank'> 进入查看</a>");
        }
    }
    
    private void submitFlowExpireTime(Date runTime)
    {
        String expireNoticeTime = CodeRepositoryUtil.getValue("WFNotice", "timelimit");
        String expireNoticeType = CodeRepositoryUtil.getValue("WFNotice", "type");

        long leaveTime = (new WorkTimeSpan(expireNoticeTime)).toNumber();
        List<WfFlowInstance> expireFlows = flowInstanceDao.listNearExpireFlowInstance(leaveTime);
        //N：通知， O:不处理 ，X：挂起，E：终止（流程,暂时和 X一样处理）
        int nOptFlow=0;
        if(expireFlows !=null && expireFlows.size()>0)
            for(WfFlowInstance  flowInst:  expireFlows){
                WfFlowDefine flowdef = flowDefDao.getFlowDefineByID( flowInst.getFlowCode(),flowInst.getVersion());
                String opt = flowdef.getExpireOpt();
                if("X".equals(opt) || "E".equals(opt)){//X：挂起
                    if(flowInst.getTimeLimit() != null &&  flowInst.getTimeLimit()< 0 ){
                        //flowManager.expireInstance(flowInst.getFlowInstId());
                        flowInst.setInstState("X"); 
                        
                        flowInst.setExpireOptSign(7L);
                        log.info("流程实例("+flowInst.getFlowInstId()+")因超时被挂起。");
                    }else if(flowInst.getExpireOptSign() < 1){//通知
                        List<VUserTaskList> tasks = actionTaskDao.getTasksByFlowid(flowInst.getFlowInstId());
                        saveNotice(expireNoticeType,tasks);
                        flowInst.setExpireOptSign(1L);
                    }
                    
                }else if("N".equals(opt)){//X：通知
                    if(flowInst.getExpireOptSign() < 1){//通知
                        List<VUserTaskList> tasks = actionTaskDao.getTasksByFlowid(flowInst.getFlowInstId());
                        saveNotice(expireNoticeType,tasks);
                        flowInst.setExpireOptSign(1L);
                    }
                    
                }else{// if("O".equals(opt)){//不处理
                       flowInst.setExpireOptSign(6L);
                }
                nOptFlow++;
                flowInstanceDao.saveObject(flowInst);    
            }
        log.info(runTime.toString() + "一共检测到"+nOptFlow+"个超期和即将超期的流程。");
    }
    
    private void submitNodeExpireTime(Date runTime)
    {
        String expireNoticeTime = CodeRepositoryUtil.getValue("WFNotice", "timelimit");
        String expireNoticeType = CodeRepositoryUtil.getValue("WFNotice", "type");

        long leaveTime = (new WorkTimeSpan(expireNoticeTime)).toNumber();
        List<WfNodeInstance> expireNodes = nodeInstanceDao.listNearExpireNodeInstance(leaveTime);
        int nOptNode=0;
        if(expireNodes !=null && expireNodes.size()>0)
        //N：通知， O:不处理 ，X：挂起，E：终止（流程）， C：完成（强制提交,提交失败就挂起）
            for(WfNodeInstance  nodeInst:  expireNodes){
                WfNode node = flowNodeDao.getObjectById(nodeInst.getNodeId());
                String opt = node.getExpireOpt();
                if("X".equals(opt) || "E".equals(opt) || "C".equals(opt) ){//X：挂起   E：终止（流程）， C：完成（强制提交,提交失败就挂起）
                    if(nodeInst.getTimeLimit() != null &&  nodeInst.getTimeLimit()< 0 ){
                        //flowManager.expireInstance(flowInst.getFlowInstId());
                        // flowInstanceDao.getObjectById(nodeInst.getFlowInstId());
                        nodeInst.setNodeState("X");                  
                        nodeInst.setExpireOptSign(7L);
                        log.info("流程节点("+nodeInst.getNodeInstId()+")因超时被挂起。");
                    }else if(nodeInst.getExpireOptSign() < 1){//通知
                        List<VUserTaskList> tasks = actionTaskDao.getTasksByNodeInstId(nodeInst.getNodeInstId());
                        saveNotice(expireNoticeType,tasks);
                        nodeInst.setExpireOptSign(1L);
                    } 
                    
                }else if("N".equals(opt)){//X：通知
                    if(nodeInst.getExpireOptSign() < 1){//通知
                        List<VUserTaskList> tasks = actionTaskDao.getTasksByNodeInstId(nodeInst.getNodeInstId());
                        saveNotice(expireNoticeType,tasks);
                        nodeInst.setExpireOptSign(1L);
                    }
                    
                }else{// if("O".equals(opt)){//不处理
                    nodeInst.setExpireOptSign(6L);
                    
                }
                nOptNode++;
                nodeInst.setLastUpdateTime(new Date(System.currentTimeMillis()));
                nodeInst.setLastUpdateUser("99999999");
                nodeInstanceDao.saveObject(nodeInst);    
            }
        log.info(runTime.toString() + "一共检测到"+nOptNode+"个超期和即将超期的节点。");
    }

	

}
