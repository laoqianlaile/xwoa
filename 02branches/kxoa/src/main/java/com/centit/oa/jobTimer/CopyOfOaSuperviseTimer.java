package com.centit.oa.jobTimer;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.IQuartzJobBean;
import com.centit.sys.service.SchedulerManager;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;




public class CopyOfOaSuperviseTimer implements  IQuartzJobBean {
    

    private OaSuperviseManager oaSuperviseManager;
    private OaRemindInformationManager oaRemindInformationManager;
    private FlowEngine flowEngine;
    @SuppressWarnings("unused")
    private FlowManager flowManager;
    private OptBaseInfoManager optBaseInfoManager;
    
    private SchedulerManager schedulerManager;
    
    

    public void setSchedulerExpandManager(
            SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }


    @Override
    public void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
 

          try {
              //查询取出督办已发起但未回复的list,state=0为督办发起
              String state="0";
              List<OaSupervise> list=oaSuperviseManager.getSuplist(state);
              
              if(list!=null&&list.size()>0){
                  //将未回复的督办件的回复期限与当前日期进行比较。
                  //若回复期限小于当前日期则进行记录，发起二次督办
                  for(int i=0;i<list.size();i++){
                      OaSupervise supervise=list.get(i);
                      if(supervise!=null){
                      Date timelimt=supervise.getLimittime();
                      //获取当前系统时间
                      if(timelimt!=null){
                      Date nowtime=(new Date(System.currentTimeMillis()));
                      //比较日期，
                      boolean flag = timelimt.before(nowtime);
                      
                      //和基本表比对
                   
                      OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(supervise.getDjId());
                     String dbType=optBaseInfo.getDbType();
                      //超过限定期限，发起二次督办
                          if(flag&&!"2".equals(dbType)){
                              //保存业务数据
                               //1.update 基本信息表
                              optBaseInfo.setDbType("2");
                            //-----  optBaseInfoManager.saveObject(optBaseInfo);
                              
                              String djid=oaSuperviseManager.getNextKey();                           
                              //2、新增二次督办
                              OaSupervise info=new OaSupervise();
                              info.setDjId(djid);
                              info.setCreatertime(new Date(System.currentTimeMillis()));
                              info.setCreater(supervise.getCreater());
                              info.setSuperviseDepno(supervise.getSuperviseDepno());                
                              info.setOptid(supervise.getOptid());
                              info.setBiztype("W");//
                              info.setBizstate("W");
                              info.setFlowcode(supervise.getFlowcode());
                              // 督办发起 状态置为0
                              info.setState("0");
                              info.setSuperviseType(supervise.getSuperviseType());
                              info.setTitle( "关于【"
                              + supervise.getTitle() + "】的二次督办");
                              //字典项 督办二次时限一致
                            if(StringUtils.isEmpty(CodeRepositoryUtil.getValue("dbtimelimt","1"))){
                                  //若未设字典项则取默认时间
                                  info.setLimittime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), 7));          
                              }else{
                                  info.setLimittime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), Integer.parseInt(CodeRepositoryUtil.getValue("dbtimelimt","1"))));          
                              }
                             //--------- oaSuperviseManager.saveObject(info);
                              //3、发起二次督办流程
                        
                              FlowInstance flowInst=flowEngine.createInstance(supervise.getFlowcode(), "关于【"
                              + supervise.getTitle() + "】的二次督办", djid,supervise.getCreater(), 
                              supervise.getSuperviseDepno());  
                          
                        
                          //将督办业务的环节操作人员塞进流程工作小组最为督办回复人员
                          String[] values = supervise.getSuperviseUsers().split(",");// 分割字符串
                              for (int j = 0; j < values.length; i++) {
                               
                                  flowEngine.assignFlowWorkTeam(flowInst.getFlowInstId(), "dbhf",values[i] );
                              }  
                              
                              //1、新增基本信息
                              OptBaseInfo optinfo=new OptBaseInfo();
                              optinfo.setDjId(djid);
                              optinfo.setFlowInstId(flowInst.getFlowInstId());
                              optinfo.setDbType("1");
                              optinfo.setTransaffairname("关于【" + supervise.getTitle()
                                      + "】的二次督办");
                              optinfo.setOptId(supervise.getOptid());
                              optinfo.setFlowCode(supervise.getFlowcode());
                              optinfo.setBiztype("W");// 等待审核
                              optinfo.setBizstate("W");
                              optinfo.setOrgname(supervise.getSuperviseDepno());
                              optinfo.setTransAffairNo(djid);
                              optinfo.setCreatedate(new Date(System.currentTimeMillis()));
                              optinfo.setCreateuser(supervise.getCreater());
                              optBaseInfoManager.saveObject(optinfo);
                          
                              //向提醒表送数据
                              OaRemindInformation oaRemindInformation=new OaRemindInformation();
                               String no=oaRemindInformationManager.getNextkey();
                               oaRemindInformation.setNo(no);
                               oaRemindInformation.setTitle("关于【"
                                       + supervise.getTitle() + "】的二次督办");
                               oaRemindInformation.setUsercode(supervise.getSuperviseUsers());
                               oaRemindInformation.setDjid(supervise.getDjId()) ;
                               oaRemindInformation.setReType(supervise.getSuperviseType());
                               oaRemindInformation.setBegtime(new Date(System.currentTimeMillis()));
                               //字典项 提醒结束时间和督办二次时限一致
                               if(StringUtils.isEmpty(CodeRepositoryUtil.getValue("dbtimelimt","1"))){
                                   //若未设字典项则取默认时间
                                   oaRemindInformation.setEndtime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), 7));
                                  
                               }else{
                                   oaRemindInformation.setEndtime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), Integer.parseInt(CodeRepositoryUtil.getValue("dbtimelimt","1"))));          
                               }
                               oaRemindInformation.setRemark(supervise.getRemark());
                               oaRemindInformation.setCreater("系统发起");
                               oaRemindInformation.setCreatetime(new Date(System.currentTimeMillis()));
                               oaRemindInformationManager.saveObject(oaRemindInformation);
                          }
                      }
                  }
           
              }
           
              }
//              else{
//                  
//                  
//              }
              
              
            //pacheckupparamManager.insertsupsperformance(year, month);
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    @Override
    public void initTimerTask() {
        JobDetail jobDetail = schedulerManager.getJobDetail("JOB_EVERYDAY", "JOB_EVERYDAY");
        
        jobDetail.getJobDataMap().put(IQuartzJobBean.QUARTZ_JOB_BEAN_KEY, this);
        
        schedulerManager.schedule(jobDetail, "Trigger_ExchangeTask_", "Trigger_ExchangeTask_", "0 */1 * ? * * *");
    }

}
