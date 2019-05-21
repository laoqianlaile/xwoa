package com.centit.oa.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.oa.po.OaTripPlan;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaTripPlanManager;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
    

public class OaTripPlanAction  extends OACommonBizAction<OaTripPlan>  {
    private static final Log log = LogFactory.getLog(OaTripPlanAction.class);
    private static final long serialVersionUID = 1L;
    private OaTripPlanManager oaTripPlanManager;
    public void setOaTripPlanManager(OaTripPlanManager oa)
    {
        oaTripPlanManager = oa;
        this.setBaseEntityManager(oaTripPlanManager);
    }

     private VsuppowerinusingManager vsuppowerinusingManager;
     private OptApplyManager optApplyManager;
     private String flowCode;
     private String itemId;
     private String s_itemtype;
     private String callback;// 返回按钮
     private String s_applyItemType;
     private String mark;
     private String userRank;
     private OptPdfInfoManager optPdfInfoManager;
     private TaskExecutor taskExecutor;
     private IdeaTempletManager ideaTempletManager;
     private String s_powerid;
     // add by lq 权力登记与人员关联
     private VPowerUserInfo vPowerUserInfo;
     private VPowerUserInfoManager vPowerUserInfoManager;
     private List<OptStuffInfo> stfflist;
     private OptStuffInfoManager optStuffInfoManager;
     protected OaPowerrolergroupManager oaPowerrolergroupManager;
    private String unrank;
     
     public void setVsuppowerinusingManager(
             VsuppowerinusingManager vsuppowerinusingManager) {
         this.vsuppowerinusingManager = vsuppowerinusingManager;
     }

     public void setOptApplyManager(OptApplyManager optApplyManager) {
         this.optApplyManager = optApplyManager;
     }

     /**
      * 
      * @return
      */
     public String editOptBaseSQ() {
         FUserDetail user = ((FUserDetail) getLoginUser());
         if(StringUtils.isNotBlank(object.getDjId())){
             object = oaTripPlanManager.getObjectById(object.getDjId());
         }
         this.generalOpt();
         return "editOptBaseBIZ";
     }
     
     public String isCheck() {
         String djId = request.getParameter("djId");
         String ideacode = request.getParameter("ideacode");
         
         if(StringUtils.isNotBlank(djId)){
             OaTripPlan oa = oaTripPlanManager.getObjectById(djId);
             userRank = getUserRankByUsercode(oa.getCreater());
             if("Y".equals(ideacode) && ("FT".equals(userRank) || "ZC".equals(userRank))){
                 mark ="2";
             }else if("T".equals(ideacode) && !("FT".equals(userRank) || "ZC".equals(userRank))){
                 mark ="3";
             }else{
                 mark ="1";
             }
         }
         
         return "getprocessinfo";
     }
     
     private String getUserRankByUsercode(String usercode) {
         FUserunit userUnit =sysUserManager.getUserPrimaryUnit(usercode);
         if(null==userUnit){
             userRank=null;
         }else{
             String userUnitRank=userUnit.getUserrank();
             List<FRoleinfo> roleInfos=sysUserManager.getSysRolesByUsid(usercode);
             if("ZT".equals(userUnitRank)){
                 userRank="ZT";//主要领导,杨主任
             }else if("FT".equals(userUnitRank) || "FXSY".equals(userUnitRank) || "JGWSJ".equals(userUnitRank)){
                 userRank="FT";//分管领导
             }else if("ZC".equals(userUnitRank)){
                 userRank="ZC";//处室负责人,处长，副处长
             }else{
                 userRank=null;//其他（科员，办事员等）--科长
             }
         }
         return userRank;
     }
     
     public String save() {
         // 保存
         FUserDetail user = ((FUserDetail) getLoginUser());
         if(StringUtils.isNotBlank(object.getDjId())){
             if(StringUtils.isBlank(object.getDeptno())){
                 object.setDeptno(user.getPrimaryUnit());
                    }
             OaTripPlan oaTripPlan = oaTripPlanManager.getObjectById(object.getDjId());
             if (oaTripPlan != null) {
                 oaTripPlanManager.copyObjectNotNullProperty(oaTripPlan, object);
                 object = oaTripPlan;
             }
             if(StringUtils.isNotBlank(object.getTransport())){
                 object.setTransport(object.getTransport().substring(0, object.getTransport().length()-1));
             }
             oaTripPlanManager.saveObject(object);
         }
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
         optBaseInfo.setTransaffairname(object.getTripPlanName());
         optBaseInfoManager.saveObject(optBaseInfo);
         return "nextList";
     }
     
     @Override
     public String edit() {
         FUserDetail user = ((FUserDetail) getLoginUser());
         //super.edit();
         
         if (StringUtils.isBlank(object.getDjId())) {
             object.setDjId(optApplyManager.getNextDjId("SQ","OaTripPlan"));
             
         }
             
         if(StringUtils.isBlank(object.getCreater())){
         // 新添加申请时，默认将系统登录人作为申请人
             if(StringUtils.isBlank(object.getCreater())){
                 object.setCreater(user.getUsercode());
             }
                 
             if(null==object.getCreateDate()){
                object.setCreateDate(new Date());
                     
             }
             if(StringUtils.isBlank(object.getDeptno())){
               object.setDeptno(user.getPrimaryUnit());
                  }
             }  
             
             //3.准备通用配置数据
             itemId = request.getParameter("itemId");
             if(null!=itemId){
                 // 获取事权对应的信息
                 vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(itemId);
                 if(null!=vPowerUserInfo && "T".equals(vPowerUserInfo.getIsgeneralmodule())){
                     curdjId=object.getDjId();
                     moduleCode=vPowerUserInfo.getGeneralmodulecode();
                     this.initSQModuleData(vPowerUserInfo.getGeneralmodulecode());   
                 }
                
             }
         String unrerk = getUserRankByUsercode(user.getUsercode());
         object.setUnrank(unrerk);
         initUsers1();
         return EDIT;
     }
     
     public void initUsers1() {
         JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
         request.setAttribute("userjson", userjson);
     }
     
     @Override
     public String view() {
         super.view();
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                 .getDjId());
         object.setOptBaseInfo(optBaseInfo);
         
         //动态加载意见
         writeIdeaModuleToRequestAttribute(object.getDjId(),false);
         return VIEW;
     }

     
     private void writeIdeaModuleToRequestAttribute(String djId,boolean needValid){
         String isPdf = request.getParameter("isPdf");
         boolean showIdeaContent = false;
         if(!needValid){
             showIdeaContent = true;
         }
         //如果需要验证，那么检测是否有查看意见的权限  
         List<OptIdeaInfo> ideas= ideaTempletManager.loadAllAvailableIdeas(djId,showIdeaContent);
        
    //     如果是生成PDF    保留 SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);//内网地址 
    //                  否则  只保留上下文 /oa_szgh
         String relaceTag=SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
         String contextPath="T".equals(isPdf)?relaceTag:"";
         if(null!=ideas&&ideas.size()>0){
             for(OptIdeaInfo idea:ideas){
                     idea.setTranscontent( idea.getTranscontent().replace(relaceTag, contextPath)) ;
             }
         }
         request.setAttribute("ideas", ideas);
     }
     
     /**
      * 保存业务数据
      * 
      * @return
      */
     public String savePermitReg() {
         try {
             
             // 保存
             FUserDetail fuser = ((FUserDetail) getLoginUser());

             object.setCreater(fuser.getUsercode());
             FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
             object.setFlowcode(flowCode);
             object.setBizState("F");
             object.setCreateDate(DatetimeOpt.currentUtilDate());
             // 申请state=6标记为暂存
             oaTripPlanManager.saveObject(object);

             object = oaTripPlanManager.getObjectById(object.getDjId());

             OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                     .getDjId());
             if (optBaseInfo == null) {
                 optBaseInfo = new OptBaseInfo();
                 optBaseInfo.setDjId(object.getDjId());

                 // 添加申请名称
                 optBaseInfo.setTransaffairname(object.getTripPlanName());
                 optBaseInfo.setOptId(flowDescribe.getOptId());
                 optBaseInfo.setBiztype("F");//
                 optBaseInfo.setBizstate("F");
                 optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                 optBaseInfo.setOrgname(fuser.getUnitName());
                 optBaseInfo.setPowerid(itemId);
                 optBaseInfo.setFlowCode(flowCode);
                 optBaseInfo.setTransAffairNo(object.getDjId());
                 optBaseInfo.setCreatedate(DatetimeOpt.currentUtilDate());
                 Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                         .findB_PowerByItemId(itemId);
                 optBaseInfo.setPowername(vsuppowerinusing.getItemName());
                 optBaseInfo.setCreateuser(object.getCreater());

             }
             //保存在编辑、回退修改 同步基本信息
             if(optBaseInfo!=null){
                 optBaseInfo.setTransaffairname(object.getTripPlanName());
             }
             optBaseInfoManager.saveObject(optBaseInfo);
             

         } catch (Exception e) {
             log.error(e.getMessage());
             e.printStackTrace();
         }
         return "nextList";
     }

     public String saveAndSubmit() {
         // 保存
         FUserDetail fuser = ((FUserDetail) getLoginUser());
         itemId = request.getParameter("itemId");
         OaTripPlan oaTripPlan = oaTripPlanManager.getObjectById(object.getDjId());
         if (oaTripPlan != null) {
             oaTripPlanManager.copyObject(oaTripPlan, object);
             object = oaTripPlan;
         }
         object.setCreater(fuser.getUsercode());
         FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
         object.setBizState("F");
         object.setFlowcode(flowCode);
         object.setCreateDate(DatetimeOpt.currentUtilDate());
         object.setTripPlanName(fuser.getUsername() + "出差申请单");
         // 申请isuse状态为1
         if(StringUtils.isBlank(object.getDeptno())){
             object.setDeptno(fuser.getPrimaryUnit());
          }
         oaTripPlanManager.saveObject(object);
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
         if (optBaseInfo == null) {
             optBaseInfo = new OptBaseInfo();
             optBaseInfo.setDjId(object.getDjId());
             optBaseInfo.setTransaffairname(object.getTripPlanName());
             optBaseInfo.setOptId(flowDescribe.getOptId());
             optBaseInfo.setFlowCode(flowCode);
             optBaseInfo.setBiztype("F");//
             optBaseInfo.setBizstate("F");
             optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
             optBaseInfo.setOrgname(fuser.getUnitName());
             Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager.findB_PowerByItemId(itemId);
             optBaseInfo.setPowername(vsuppowerinusing.getItemName());
             optBaseInfo.setTransAffairNo(object.getDjId());
             optBaseInfo.setCreatedate(object.getCreateDate());
             optBaseInfo.setCreateuser(object.getCreater());
             optBaseInfo.setPowerid(itemId);
             optBaseInfoManager.saveObject(optBaseInfo);
         }
         if (object != null) {
             FlowInstance flowInst = flowEngine.createInstance(flowCode,
                     object.getTripPlanName(), object.getDjId(),
                     fuser.getUsercode(), fuser.getPrimaryUnit());
             long flowInstId = flowInst.getFlowInstId();
             long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
             this.setFlowInstId(flowInstId);
             curNodeInstId = nodeInstId;
             curFlowInstId = flowInstId;
             object.setFlowInstId(flowInstId);
             if(StringUtils.isNotBlank(object.getTransport())){
                 object.setTransport(object.getTransport().substring(0, object.getTransport().length()-1));
             }
             oaTripPlanManager.saveObject(object);
             optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
             optBaseInfo.setFlowInstId(flowInstId);

             optBaseInfo.setTransaffairname(object.getTripPlanName());
             optBaseInfo.setBiztype("W");// 等待审核
             optBaseInfo.setBizstate("W");
             optBaseInfoManager.saveObject(optBaseInfo);
             
             flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                     fuser.getUsercode(), "登记人员");
             // 将登记部门纳入权限引擎
             flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "DJBM",
                     fuser.getPrimaryUnit(), "出差登记");

             // 获取事权对应的信息
             vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                     .getPowerid());
             
             flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                     "JSBM", vPowerUserInfo.getUnitcode(), "出差登记");
             
             this.saveIdeaInfo("出差登记", 0L);
             
         }
         //保存通用配置数据
         if(null!=optBaseInfo.getDjId()){
               this.saveSQModuleData();   
         }
         createPDF();
         s_itemtype = "SQ";
         s_powerid ="WPSL-SQ-002";
         
         return "nextList";
     }
     
     private String getUserRankByCode(String  usercode){
         FUserunit Unit =sysUserManager.getUserPrimaryUnit(usercode);
         if(null==Unit){
             userRank=null;
         }else{
             String userUnitRank=Unit.getUserrank();
             List<FRoleinfo> roleInfos=sysUserManager.getSysRolesByUsid(usercode);
             if("ZT".equals(userUnitRank)){
                 userRank="ZT";//主要领导
             }else if("FT".equals(userUnitRank) || "FXSY".equals(userUnitRank) || "JGWSJ".equals(userUnitRank)){
                 userRank="FT";//分管领导
             }else if("ZC".equals(userUnitRank)){
                 userRank="ZC";//处室负责人
             }else{
                 userRank=null;//其他（科员，办事员等）--/科长
             }
         }
         return userRank;
         
     }
     
     /**
      * 出差经费预算
      */
      private  void createPDF(){
          //生成pdf
          if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
              String moduleType =optBaseInfoManager.getApplyItemType(object.getDjId());
              final String userCode = ((FUserDetail) getLoginUser()).getUsercode();
              final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
            //"***/anonymous***"代表匿名访问
              String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
              if(contextPath.endsWith("/")){
                  contextPath = contextPath.substring(0, contextPath.length()-1);
              }
              contextPath = contextPath + request.getContextPath();
              
              final String formHtmlUrl = optPdfInfoManager.getSQFormHtmlUrl(contextPath,userCode, moduleType,object.getDjId());
              taskExecutor.execute(new Runnable(){
                  @Override
                  public void run() {
                      optPdfInfoManager.createPDF(object.getDjId(), "出差经费预算", 0L,userCode,exePath,formHtmlUrl);
                  }
              });
          }
      }
     
     
     @Override
     public String delete() {
         if (StringUtils.isNotBlank(object.getDjId())) {
             optApplyManager.deleteObjectById(object.getDjId());
             optBaseInfoManager.deleteObjectById(object.getDjId());
             oaTripPlanManager.deleteObjectById(object.getDjId());
         }
         return "nextList";
     }

     private String nodeCode;

     /**
      * 流程办理——保存业务重新登记信息
      * 
      * @return
      */
     public String saveReditSQ() {
         OaTripPlan oaTripPlan = oaTripPlanManager
                 .getObjectById(object.getDjId());
         if (oaTripPlan != null) {
             oaTripPlanManager.copyObjectNotNullProperty(
                     oaTripPlan, object);
             object = oaTripPlan;
         }
         oaTripPlanManager.saveObject(object);

         // 同步基本信息
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                 .getDjId());
         optBaseInfo.setTransaffairname(object.getTripPlanName());
         optBaseInfoManager.saveObject(optBaseInfo);
         
         createPDF();
         
         return "nextList";
     }

     public String submitReditSQ() {
         this.saveReditSQ();
         // 保存过程日志信息
         saveIdeaInfo("回退修改提交", null);
         submitNode();

         return "nextList";
     }

     // TODO Auto-generated method stub
     public void saveIdeaInfo(String nodename, Long NodeInstId) {

         Long thisNodeInstId = NodeInstId;
         if (NodeInstId == null) {
             thisNodeInstId = curNodeInstId;
         }
         FUserDetail loginInfo = (FUserDetail) getLoginUser();

         OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
         optIdeaInfo.setUsername(loginInfo.getUsername());

         FUnitinfo fUnitinfo = sysUnitManager.getObjectById(loginInfo
                 .getPrimaryUnit().trim());
         if (fUnitinfo == null) {
             fUnitinfo = new FUnitinfo();
         }
         optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

         optIdeaInfo.setTransidea(nodename);

         OptProcInfo procInfo = new OptProcInfo();
         procInfo.setNodeInstId(thisNodeInstId);
         procInfo.setDjId(object.getDjId());
         procInfo.setNodename(nodename);
         procInfo.setTransdate(new Date(System.currentTimeMillis()));
         procInfo.setNodeinststate("N");
         procInfo.setUnitcode(loginInfo.getPrimaryUnit());
         procInfo.setUsercode(loginInfo.getUsercode());
         procInfo.setTransidea(nodename);
         procInfo.setNodeCode(nodeCode);
         optProcInfoManager.saveObject(procInfo);
         optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

     }
     
   

     public String getNodeCode() {
         return nodeCode;
     }

     public void setNodeCode(String nodeCode) {
         this.nodeCode = nodeCode;
     }

     public String getFlowCode() {
         return flowCode;
     }

     public void setFlowCode(String flowCode) {
         this.flowCode = flowCode;
     }

     public String getItemId() {
         return itemId;
     }

     public void setItemId(String itemId) {
         this.itemId = itemId;
     }

     public String getS_itemtype() {
         return s_itemtype;
     }

     public void setS_itemtype(String s_itemtype) {
         this.s_itemtype = s_itemtype;
     }

     public String getCallback() {
         return callback;
     }

     public void setCallback(String callback) {
         this.callback = callback;
     }

     public OptPdfInfoManager getOptPdfInfoManager() {
         return optPdfInfoManager;
     }

     public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
         this.optPdfInfoManager = optPdfInfoManager;
     }

     public TaskExecutor getTaskExecutor() {
         return taskExecutor;
     }

     public void setTaskExecutor(TaskExecutor taskExecutor) {
         this.taskExecutor = taskExecutor;
     }

     public IdeaTempletManager getIdeaTempletManager() {
         return ideaTempletManager;
     }

     public void setIdeaTempletManager(IdeaTempletManager ideaTempletManager) {
         this.ideaTempletManager = ideaTempletManager;
     }

     public String getS_applyItemType() {
         return s_applyItemType;
     }

     public void setS_applyItemType(String s_applyItemType) {
         this.s_applyItemType = s_applyItemType;
     }

     public VPowerUserInfo getvPowerUserInfo() {
         return vPowerUserInfo;
     }

     public void setvPowerUserInfo(VPowerUserInfo vPowerUserInfo) {
         this.vPowerUserInfo = vPowerUserInfo;
     }


     public void setvPowerUserInfoManager(VPowerUserInfoManager vPowerUserInfoManager) {
         this.vPowerUserInfoManager = vPowerUserInfoManager;
     }

    public List<OptStuffInfo> getStfflist() {
        return stfflist;
    }

    public void setStfflist(List<OptStuffInfo> stfflist) {
        this.stfflist = stfflist;
    }

    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public VPowerUserInfoManager getvPowerUserInfoManager() {
        return vPowerUserInfoManager;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getS_powerid() {
        return s_powerid;
    }

    public void setS_powerid(String s_powerid) {
        this.s_powerid = s_powerid;
    }

        
}
