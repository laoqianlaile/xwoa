package com.centit.oa.action;

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
import com.centit.oa.po.OaBuffetapply;
import com.centit.oa.service.OaBuffetapplyManager;
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
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
	

public class OaBuffetapplyAction  extends OACommonBizAction<OaBuffetapply>  {
	private static final Log log = LogFactory.getLog(OaBuffetapplyAction.class);
	private static final long serialVersionUID = 1L;
	private OaBuffetapplyManager oaBuffetapplyMag;
	public void setOaBuffetapplyManager(OaBuffetapplyManager basemgr)
	{
		oaBuffetapplyMag = basemgr;
		this.setBaseEntityManager(oaBuffetapplyMag);
	}

	private VsuppowerinusingManager vsuppowerinusingManager;
    private OptApplyManager optApplyManager;
    private FlowDefine flowDefine;
    private String flowCode;
    private String itemId;
    private String s_itemtype;
    private String callback;// 返回按钮
    private String s_applyItemType;

    
    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    private IdeaTempletManager ideaTempletManager;
    
    // add by lq 权力登记与人员关联
    private VPowerUserInfo vPowerUserInfo;
    private VPowerUserInfoManager vPowerUserInfoManager;

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
        objList = oaBuffetapplyMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return LIST;
    }

    @Override
    public String edit() {
        super.edit();
        
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(optApplyManager.getNextDjId("SQ","OaBuffetapply"));
            
        }
        if(StringUtils.isBlank(object.getCreater())){
         // 新添加申请时，默认将系统登录人作为申请人
            FUserDetail user = (FUserDetail) getLoginUser();
            if(StringUtils.isBlank(object.getCreater())){
                object.setCreater(user.getUsercode());
            }
        }
        //如果没有期号则自动查询
        if(StringUtils.isBlank(object.getLayoutno())){
            object.setLayoutno(oaBuffetapplyMag.getNextKeyMax());
        }
        
        //准备通用配置数据
        if(null!=itemId){
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(itemId);
            if(null!=vPowerUserInfo && "T".equals(vPowerUserInfo.getIsgeneralmodule())){
                curdjId=object.getDjId();
                moduleCode=vPowerUserInfo.getGeneralmodulecode();
                this.initSQModuleData(vPowerUserInfo.getGeneralmodulecode());   
            }
           
        }
             
        return EDIT;
    }

    @Override
    public String view() {
        // TODO Auto-generated method stub
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
            object.setOptId(flowDescribe.getOptId());
            object.setFlowcode(flowCode);
            object.setBiztype("F");//
            object.setBizstate("F");
            object.setCreatertime(DatetimeOpt.currentUtilDate());
            // 申请state=6标记为暂存
            oaBuffetapplyMag.saveObject(object);

            object = oaBuffetapplyMag.getObjectById(object.getDjId());

            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());

                // 添加申请名称
                optBaseInfo.setTransaffairname(object.getTransaffairname());
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
                optBaseInfo.setTransaffairname(object.getTransaffairname());
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
        OaBuffetapply OaBuffetapply = oaBuffetapplyMag
                .getObjectById(object.getDjId());
        if (OaBuffetapply != null) {
            oaBuffetapplyMag.copyObject(OaBuffetapply, object);
            object = OaBuffetapply;
        }
        object.setCreater(fuser.getUsercode());

        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        object.setOptId(flowDescribe.getOptId());
        object.setBiztype("F");//
        object.setBizstate("F");
        object.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        object.setFlowcode(flowCode);
        object.setCreatertime(DatetimeOpt.currentUtilDate());
        // 申请isuse状态为1

        oaBuffetapplyMag.saveObject(object);
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(object.getDjId());

            optBaseInfo.setTransaffairname(object.getTransaffairname());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
            optBaseInfo.setOrgname(fuser.getUnitName());
//            optBaseInfo.setCriticalLevel(object.getOptBaseInfo().getCriticalLevel());
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                    .findB_PowerByItemId(itemId);
            optBaseInfo.setPowername(vsuppowerinusing.getItemName());

            optBaseInfo.setTransAffairNo(object.getDjId());
            optBaseInfo.setCreatedate(object.getCreatertime());
            optBaseInfo.setCreateuser(object.getCreater());
            optBaseInfo.setPowerid(itemId);
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (object != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    object.getTransaffairname(), object.getDjId(),
                    fuser.getUsercode(), fuser.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;
            curFlowInstId = flowInstId;

            object.setFlowInstId(flowInstId);
            object.setBiztype("W");// 等待审核
            object.setBizstate("W");
            oaBuffetapplyMag.saveObject(object);

            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setTransaffairname(object.getTransaffairname());
            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fuser.getUsercode(), "登记人员");
            // 将登记部门纳入权限引擎
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "DJBM",
                    fuser.getPrimaryUnit(), "产业服务中心自助餐申请单登记部门");
            
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
          
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "产业服务中心自助餐申请单部门");
            
            this.saveIdeaInfo("产业服务中心自助餐申请单登记", 0L);
            
        }
        
        //保存通用配置数据
        if(null!=optBaseInfo.getDjId()){
              this.saveSQModuleData();   
        }
        
        createPDF();
        
        
        return "nextList";
    }

    /**
     * 产业服务中心自助餐申请单
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
                     optPdfInfoManager.createPDF(object.getDjId(), "产业服务中心自助餐申请单", 0L,userCode,exePath,formHtmlUrl);
                 }
             });
         }
     }
    
    
    @Override
    public String delete() {
        if (StringUtils.isNotBlank(object.getDjId())) {
            optApplyManager.deleteObjectById(object.getDjId());
            optBaseInfoManager.deleteObjectById(object.getDjId());
            oaBuffetapplyMag.deleteObjectById(object.getDjId());
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
        OaBuffetapply oaBuffetapply = oaBuffetapplyMag
                .getObjectById(object.getDjId());
        if (oaBuffetapply != null) {
            oaBuffetapplyMag.copyObjectNotNullProperty(
                    oaBuffetapply, object);
            object = oaBuffetapply;
        }
        oaBuffetapplyMag.saveObject(object);

        // 同步基本信息
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        optBaseInfo.setTransaffairname(object.getTransaffairname());
        optBaseInfoManager.saveObject(optBaseInfo);
        
        createPDF();
        
        return "nextList";
    }

    public String submitReditSQ() {
        this.saveReditSQ();
        
        saveSQModuleData();
        
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

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
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

    public VPowerUserInfoManager getvPowerUserInfoManager() {
        return vPowerUserInfoManager;
    }

    public void setvPowerUserInfoManager(VPowerUserInfoManager vPowerUserInfoManager) {
        this.vPowerUserInfoManager = vPowerUserInfoManager;
    }
	
		
}
