package com.centit.oa.jobTimer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;




public class OaSuperviseTimer extends  QuartzJobBean {
    

    private OaSuperviseManager oaSuperviseManager;
    private OaRemindInformationManager oaRemindInformationManager;
    private FlowEngine flowEngine;
    private OptBaseInfoManager optBaseInfoManager;
    private OaMeetApplyManager oaMeetApplyManager;

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
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
 

          try {
              //督办相关
              this.toDoOaSupervise();
              //会议室使用状态相关
              this.toDoOaMeetApply();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }

    /**
     * 处理会议申请使用相关
     */
    private void toDoOaMeetApply() {
        //查询状态为已安排的会议申请记录，作为待处理信息；
        //meetState：1申请中2已安排3已使用4不同意5被抢占6暂存
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("state", "2");
        List<OaMeetApply> oaMeetApplys=oaMeetApplyManager.listObjects(paramMap);
        if(oaMeetApplys!=null && oaMeetApplys.size()>0){
            Date nowtime=(new Date(System.currentTimeMillis()));//获取当前时间
            for(OaMeetApply bean:oaMeetApplys){
                Date timelimt=bean.getDoEndTime();//安排使用截止时间
              //比较日期，
                boolean flag = timelimt.before(nowtime);
                if(flag){
                    bean.setState("3"); //标记为已使用;
                    oaMeetApplyManager.saveObject(bean);
                }
            }
        }
        
    }

    /**
     * 处理督办相关
     */
    private void toDoOaSupervise() {
        // TODO Auto-generated method stub
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
             
                OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(supervise.getSupDjid());
               
                String dbType=(optBaseInfo==null || StringUtils.isBlank(optBaseInfo.getDbType()))?"1":optBaseInfo.getDbType();
                //超过限定期限，发起二次督办
                    if(flag&&!"2".equals(dbType)){
                        //保存业务数据
                         //1.update 基本信息表
                        optBaseInfo.setDbType("2");
                        optBaseInfoManager.saveObject(optBaseInfo);
                        //2、新增二次督办
                        String djid=oaSuperviseManager.getNextKey(); 
                        this.saveSecondDB(supervise,djid);
                      
                        //3、发起二次督办流程
                        this.createInstanceSupervise(supervise,djid); 
                        //向提醒表送数据
                        this.saveOaRemindInformation(supervise);
                    }
                }
            }
     
        }
     
        }
    }

    /**
     * 发起二次督办流程
     * @param supervise
     * @param djid
     */
    private void createInstanceSupervise(OaSupervise supervise, String djid) {
        // TODO Auto-generated method stub
        //发起二次督办流程
        FlowInstance flowInst=flowEngine.createInstance(supervise.getFlowcode(), "关于【"
                + supervise.getTitle() + "】的二次督办", djid,supervise.getCreater(), 
                supervise.getSuperviseDepno());  
            
          
            //将督办业务的环节操作人员塞进流程工作小组最为督办回复人员
          String[] values = supervise.getSuperviseUsers().split(",");// 分割字符串
                for (int j = 0; j <= values.length-1; j++) {
                 
                    flowEngine.assignFlowWorkTeam(flowInst.getFlowInstId(), "dbhf",values[j] );
                }  
                
                //1、新增二次督办基本信息
                OptBaseInfo optinfo=optBaseInfoManager.getObjectById(djid);
                if(optinfo==null){
                    optinfo=new OptBaseInfo();
                }
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
           
                //保存二次督办流程信息
                supervise=oaSuperviseManager.getObjectById(supervise.getDjId());
                supervise.setFlowInstId(flowInst.getFlowInstId());
                oaSuperviseManager.saveObject(supervise);
                
    }

    /**
     * 新增二次督办
     * @param supervise
     * @param djid
     */
    private void saveSecondDB(OaSupervise supervise,String djid) {
        // TODO Auto-generated method stub
                                  
        
        OaSupervise info=new OaSupervise();
        info.setDjId(djid);
        info.setSupDjid(supervise.getSupDjid());
        info.setNodecode(supervise.getNodecode());
        
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
      if(StringUtils.isEmpty(CodeRepositoryUtil.getDataPiece("SYSPARAM",
              "dbtimelimt").getDatavalue())){
            //若未设字典项则取默认时间
            info.setLimittime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), 7));          
        }else{
            info.setLimittime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), Integer.parseInt(CodeRepositoryUtil.getDataPiece("SYSPARAM",
                    "dbtimelimt").getDatavalue())));          
        }
       oaSuperviseManager.saveObject(info);
    }

    /**
     * 向提醒表送数据
     * @param supervise
     */
    private void saveOaRemindInformation(OaSupervise supervise) {
        // TODO Auto-generated method stub
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

        if(StringUtils.isEmpty(CodeRepositoryUtil.getDataPiece("SYSPARAM",
                "dbtimelimt").getDatavalue())){
            //若未设字典项则取默认时间
            oaRemindInformation.setEndtime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), 7));
           
        }else{
            oaRemindInformation.setEndtime(DatetimeOpt.addDays(new Date(System.currentTimeMillis()), Integer.parseInt(CodeRepositoryUtil.getDataPiece("SYSPARAM",
                    "dbtimelimt").getDatavalue())));          
        }
        oaRemindInformation.setRemark(supervise.getRemark());
        oaRemindInformation.setCreater("系统发起");
        oaRemindInformation.setCreatetime(new Date(System.currentTimeMillis()));
        oaRemindInformationManager.saveObject(oaRemindInformation);
    }

    public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
        this.oaMeetApplyManager = oaMeetApplyManager;
    }


}
