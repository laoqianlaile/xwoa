package com.centit.oa.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;
import org.springframework.core.task.TaskExecutor;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.oa.po.OaBudgetstravel;
import com.centit.oa.service.OaBudgetstravelManager;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
	

public class OaBudgetstravelAction  extends OACommonBizAction<OaBudgetstravel>  {
	private static final Log log = LogFactory.getLog(OaBudgetstravelAction.class);
	private static final long serialVersionUID = 1L;
	private OaBudgetstravelManager oaBudgetstravelMag;
	public void setOaBudgetstravelManager(OaBudgetstravelManager basemgr)
	{
		oaBudgetstravelMag = basemgr;
		this.setBaseEntityManager(oaBudgetstravelMag);
	}

	 private VsuppowerinusingManager vsuppowerinusingManager;
     private OptApplyManager optApplyManager;
     private String flowCode;
     private String itemId;
     private String s_itemtype;
     private String callback;// 返回按钮
     private String s_applyItemType;

     
     private OptPdfInfoManager optPdfInfoManager;
     private TaskExecutor taskExecutor;
     private IdeaTempletManager ideaTempletManager;
     
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
         this.edit();
         this.generalOpt();
         return "editOptBaseBIZ";
     }

     /**
      * 查看车辆维修列表
      */
     @Override
     public String list() {
         @SuppressWarnings("unchecked")
         Map<Object, Object> paramMap = request.getParameterMap();
         resetPageParam(paramMap);
         Map<String, Object> filterMap = convertSearchColumn(paramMap);
         Limit limit = ExtremeTableUtils.getLimit(request);
         PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
         // 解决参数出现中文乱码导致查询错误
         if ("T".equals(callback)) {
             String content;
             try {
                 content = new String(request.getParameter("s_cardNo").getBytes(
                         "ISO-8859-1"), "UTF-8");
                 filterMap.put("cardNo", content);
                 request.setAttribute("s_cardNo", content);
             } catch (UnsupportedEncodingException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
         }
         objList = oaBudgetstravelMag.listObjects(filterMap, pageDesc);
         totalRows = pageDesc.getTotalRows();
         return LIST;
     }

     @Override
     public String edit() {
         
         super.edit();
         
         if (StringUtils.isBlank(object.getDjId())) {
             object.setDjId(optApplyManager.getNextDjId("SQ","OaBudgetstravel"));
          // 新添加申请时，默认将系统登录人作为申请人
             FUserDetail user = (FUserDetail) getLoginUser();
             object.setCreater(user.getUsercode());
             object.setCreatetime(new Date());
         }
       //准备通用配置数据
         if(null!=itemId){
             super.initSQModuleDataByItemId(itemId);
         }
         return EDIT;
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
             object.setOptid(flowDescribe.getOptId());
             object.setFlowcode(flowCode);
             object.setBiztype("F");//
             object.setBizstate("F");
             object.setCreatetime(DatetimeOpt.currentUtilDate());
             // 申请state=6标记为暂存
             oaBudgetstravelMag.saveObject(object);

             object = oaBudgetstravelMag.getObjectById(object.getDjId());

             OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                     .getDjId());
             if (optBaseInfo == null) {
                 optBaseInfo = new OptBaseInfo();
                 optBaseInfo.setDjId(object.getDjId());

                 // 添加申请名称
                 optBaseInfo.setTransaffairname(object.getTitle());
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
                 optBaseInfo.setTransaffairname(object.getTitle());
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
         OaBudgetstravel OaBudgetstravel = oaBudgetstravelMag
                 .getObjectById(object.getDjId());
         if (OaBudgetstravel != null) {
             oaBudgetstravelMag.copyObject(OaBudgetstravel, object);
             object = OaBudgetstravel;
         }
         object.setCreater(fuser.getUsercode());

         FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
         object.setOptid(flowDescribe.getOptId());
         object.setBiztype("F");//
         object.setBizstate("F");
         object.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
         object.setFlowcode(flowCode);
         object.setCreatetime(DatetimeOpt.currentUtilDate());
         // 申请isuse状态为1

         oaBudgetstravelMag.saveObject(object);
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                 .getDjId());
         if (optBaseInfo == null) {
             optBaseInfo = new OptBaseInfo();
             optBaseInfo.setDjId(object.getDjId());

             optBaseInfo.setTransaffairname(object.getTitle());
             optBaseInfo.setOptId(flowDescribe.getOptId());
             optBaseInfo.setFlowCode(flowCode);
             optBaseInfo.setBiztype("F");//
             optBaseInfo.setBizstate("F");
             optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
             optBaseInfo.setOrgname(fuser.getUnitName());
            // optBaseInfo.setCriticalLevel(object.getOptBaseInfo().getCriticalLevel());
             Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                     .findB_PowerByItemId(itemId);
             optBaseInfo.setPowername(vsuppowerinusing.getItemName());

             optBaseInfo.setTransAffairNo(object.getDjId());
             optBaseInfo.setCreatedate(object.getCreatetime());
             optBaseInfo.setCreateuser(object.getCreater());
             optBaseInfo.setPowerid(itemId);
             optBaseInfoManager.saveObject(optBaseInfo);
         }
         if (object != null) {
             FlowInstance flowInst = flowEngine.createInstance(flowCode,
                     object.getTitle(), object.getDjId(),
                     fuser.getUsercode(), fuser.getPrimaryUnit());

             long flowInstId = flowInst.getFlowInstId();
             long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
             this.setFlowInstId(flowInstId);
             curNodeInstId = nodeInstId;
             curFlowInstId = flowInstId;

             object.setFlowInstId(flowInstId);
             object.setBiztype("W");// 等待审核
             object.setBizstate("W");
             oaBudgetstravelMag.saveObject(object);

             optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
             optBaseInfo.setFlowInstId(flowInstId);

             optBaseInfo.setTransaffairname(object.getTitle());
             optBaseInfo.setBiztype("W");// 等待审核
             optBaseInfo.setBizstate("W");
             optBaseInfoManager.saveObject(optBaseInfo);
             // 将登记人员纳入办件角色
             flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                     fuser.getUsercode(), "登记人员");
             // 将登记部门纳入权限引擎
             flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "DJBM",
                     fuser.getPrimaryUnit(), "出差经费登记部门");
             
             // 获取事权对应的信息
             vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                     .getPowerid());
             
             flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                     "JSBM", vPowerUserInfo.getUnitcode(), "出差经费接收部门");
             
             this.saveIdeaInfo("出差经费登记", 0L);
             
             
             
         }
         
         createPDF();
         
         
         return "nextList";
     }

     /**
      * 客情
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
                      optPdfInfoManager.createPDF(object.getDjId(), "客情通报", 0L,userCode,exePath,formHtmlUrl);
                  }
              });
          }
      }
     
     
     @Override
     public String delete() {
         if (StringUtils.isNotBlank(object.getDjId())) {
             optApplyManager.deleteObjectById(object.getDjId());
             optBaseInfoManager.deleteObjectById(object.getDjId());
             oaBudgetstravelMag.deleteObjectById(object.getDjId());
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
         OaBudgetstravel OaBudgetstravel = oaBudgetstravelMag
                 .getObjectById(object.getDjId());
         if (OaBudgetstravel != null) {
             oaBudgetstravelMag.copyObjectNotNullProperty(
                     OaBudgetstravel, object);
             object = OaBudgetstravel;
         }
         oaBudgetstravelMag.saveObject(object);

         // 同步基本信息
         OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                 .getDjId());
         optBaseInfo.setTransaffairname(object.getTitle());
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

	
	
		
}
