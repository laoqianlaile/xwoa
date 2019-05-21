package com.centit.powerruntime.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;
import org.springframework.core.task.TaskExecutor;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.oa.action.OACommonBizAction;
import com.centit.oa.action.OaBizBindInfoAction;
import com.centit.oa.action.OaSuperviseAction;
import com.centit.oa.po.OaAssetinformationBond;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.po.VoaBizBindInfo;
import com.centit.oa.service.OaAssetinformationBondManager;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.oa.service.VoaBizBindInfoManager;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.VPowerUserInfoId;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.PowerOptInfo;
import com.centit.powerruntime.po.RiskInfo;
import com.centit.powerruntime.po.VOptApplyInfo;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptWritdefManager;
import com.centit.powerruntime.service.PowerOptInfoManager;
import com.centit.powerruntime.service.RiskInfoManager;
import com.centit.powerruntime.service.VPowerOrgInfoManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;

/**
 * 内部事权
 * 
 * @author 黄雄
 * @create 2015-1-19
 * @version
 */
public class OptApplyAction extends OACommonBizAction<OptApplyInfo> implements
        ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private OptApplyManager optApplyManager;
    private FunctionManager functionManager;
    private String optId;
    private String flowCode;
    private OptJspInfo jspInfo;
    private OptBaseInfoManager optBaseInfoManager;
//    private String moduleCode;
    private String nodeCode;// 环节代码
    private String itemId;// 权力事项编码
    private String isapplyuser;// 开启申请者信息开关
    private String documentTemplateIds;
    @SuppressWarnings("unused")
    private OptNewlyIdeaManager optNewlyIdeaManager;
    private OaSuperviseManager oaSuperviseManager;
    private Vsuppowerinusing suppowerinusing;// 权力信息
    private List<OaSupervise> oasuplist;
    private OaBizBindInfoManager oaBizBindInfoManager;
    private VoaBizBindInfoManager voaBizBindInfoManager;
    private VsuppowerinusingManager vsuppowerinusingManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    
    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    private IdeaTempletManager ideaTempletManager;
    
   
    
    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }

    private VOptBaseListManager vOptBaseListManager;
   

    private List<VOptBaseList> voptBaseLists;
    

   

    private String startDjId;// 发起djId
    private Long nodeId;// 发起节点
    private String djid;//资产代码
    private List<VoaBizBindInfo> vbizBindInfos;
    private String unitValue;// 用于编辑事权时显示多个部门名称
//    private SysUnitManager sysUnitManager;
    private String unitsJson;
    private String parentunit;
    private String s_bizstate;// 办件状态
    private List<VPowerUserInfo> userPowerList;
    private String isDept;//判断查看页面显示查看还是关闭按钮
    
    private String queryUnderUnit;//(列表类别)按职务等级查询列表
    private FUserunit userUnit;// 用户单位
    private String userRank=null;
    
    public String getS_nodeInstId() {
        return s_nodeInstId;
    }

    public void setS_nodeInstId(String s_nodeInstId) {
        this.s_nodeInstId = s_nodeInstId;
    }

    private String s_nodeInstId;
    
    
    /**
     * 发文关联action需要的参数
     */
    private String d_djId;
    private String d_nodeInstId;
    private String d_itemtype;
    public String getD_nodeInstId() {
        return d_nodeInstId;
    }
    public void setD_nodeInstId(String d_nodeInstId) {
        this.d_nodeInstId = d_nodeInstId;
    }
    public String getD_djId() {
        return d_djId;
    }
    public void setD_djId(String d_djId) {
        this.d_djId = d_djId;
    }
    public String getD_itemtype() {
        return d_itemtype;
    }
    public void setD_itemtype(String d_itemtype) {
        this.d_itemtype = d_itemtype;
    }
    

    /**
     * RTX相关
     */
    private RtxInfoManager rtxInfoManager;    

//    private GeneralModuleParam moduleParam;
    private OptProcInfoManager optProcInfoManager;
    private List<VOptApplyInfo> srPermitApplyList;
//    private SysUserManager sysUserManager;
    private RiskInfoManager riskInfoManager;
    private String optBaseInfoJson;
    private SuppowerManager suppowerManager;
    private PowerOptInfoManager powerOptInfoManager;
    private PowerOptInfo powerOptInfo = null;
    private VPowerOrgInfo vPowerOrgInfo = null;
    @SuppressWarnings("unused")
    private OptWritdefManager optWritdefManager;
    private GeneralModuleParamManager generalModuleParamMag;
    private VPowerOrgInfoManager vPowerOrgInfoManager;
    private OaAssetinformationBondManager oaAssetinformationBondManager;
    private List<FUnitinfo> unitList;
    private List<OptNewlyIdea> optNewlyIdeaList = new ArrayList<OptNewlyIdea>();
    private String curUrl;
    private String flag;// 标记类型
    private String s_itemtype;// QB 签报

    // add by lq 权力登记与人员关联
    private VPowerUserInfo vPowerUserInfo;
    private VPowerUserInfoManager vPowerUserInfoManager;

    private List<OptBaseInfo> optBaseInfos;// 办件删除使用
    
    private List<VOptBaseList>  vOptBaseList;
    

  
    public String edit() {

        // 根据登记编号查看许可信息
        this.getPermitInfo();

        return EDIT;
    }

    /**
     * 用于办件删除的删除 查看使用
     * 
     * @return
     */
    public String listXforDelete() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            PageDesc pageDesc = makePageDesc();
            optBaseInfos = optBaseInfoManager.listOptBaseInfo(filterMap,
                    pageDesc);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            return "listXforDelete";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

    }

    /**
     * 用于办件删除的删除使用
     * 
     * @return
     */
    private String resetUsers;

    public String deleteCase() {
        if (StringUtils.isNotBlank(resetUsers)) {
            String ar[] = resetUsers.split(",");
            for (String a : ar) {
                deleteObjectBanInfo(a);
            }
        }
        return this.listXforDelete();
    }

    public void deleteObjectBanInfo(String djId) {
        optApplyManager.deleteObjectBanInfo(djId);
    }

    /**
     * 事项登记Form 签报登记 登记时候需要选择办理人，在登记时候初始化
     * 
     * @return
     */
    public String editDoOrRead() {

        object = optApplyManager.getObjectById(object.getDjId());
        FUserDetail fuser = ((FUserDetail) getLoginUser());
       
        
        String startDjId = (String) request.getParameter("startDjId");
        if (StringUtils.isNotBlank(startDjId)) {
            String nodeInstId = (String) request.getParameter("nodeId");
            Long nodeId = Long.parseLong(nodeInstId);
            request.setAttribute("nodeId", nodeId);
            request.setAttribute("startDjId", startDjId);
        }
        if (object != null) {
            optBaseInfoJson = optApplyManager.getJSONDocumentNames(object
                    .getDjId());
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            vPowerOrgInfo = vPowerOrgInfoManager.getSupPowerInfo(
                    optBaseInfo.getPowerid(), fuser.getPrimaryUnit());
            isapplyuser = vPowerOrgInfo.getIsapplyuser();
            object.setOptBaseInfo(optBaseInfo);
            String pattern = "\\s*,\\s*";
            if (StringUtils.isNotBlank(object.getRecievedepno())) {
                String[] cc = object.getRecievedepno().trim().split(pattern);
                StringBuilder sb = new StringBuilder();
                if (cc != null && cc.length > 0) {
                    for (String ev : cc) {
                        if("QB".equals(s_itemtype)){
                            sb.append(CodeRepositoryUtil.getValue("usercode", ev)
                                    + ",");
                        }else{
                            sb.append(CodeRepositoryUtil.getValue("unitcode", ev)
                                    + ",");
                        }
                    }
                }// 去掉最后一个逗号
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    unitValue = sb.toString();
                }
            }
            optId = optBaseInfo.getOptId();

            // 页面文书模版对应申请事项
            if (StringUtils.isNotBlank(optBaseInfo.getPowerid())) {
                // 到业务配置表里查询对应的模版编码
                powerOptInfo = powerOptInfoManager
                        .getObjectByItemID(optBaseInfo.getPowerid());
                if (powerOptInfo == null) {
                    powerOptInfo = new PowerOptInfo();
                }
            }
        } else {
            vPowerOrgInfo = vPowerOrgInfoManager.getSupPowerInfo(itemId,
                    fuser.getPrimaryUnit());
            object = new OptApplyInfo();
            // 生成登记编号
            String ev = request.getParameter("s_itemtype");
            object.setDjId(optApplyManager.getNextDjId(ev));
            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setPowername(vPowerOrgInfo.getItemName());
            optBase.setPowerid(vPowerOrgInfo.getItemId());
            optBase.setOptId(optId);
            optBase.setOrgcode(vPowerOrgInfo.getUnitcode());
            object.setOptBaseInfo(optBase);
        }
        request.setAttribute("vPowerOrgInfo", vPowerOrgInfo);
        flowCode = vPowerOrgInfo.getWfcode();
        optId = vPowerOrgInfo.getOptid();
        // 初始化待选人员
        //initUsers();
        moduleParam=generalModuleParamMag.getObjectById(vPowerOrgInfo.getGeneralmodulecode());
        super.initTeamUsersConfig();
        //如果从首页进入，用dashboard标记
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        return "editDoOrRead";
    }

    /*
     * 修改签报
     */
    public String editQB() {
        String dj_id = request.getParameter("dj_id");
        if(StringUtils.isNotBlank(dj_id)){
            OptBaseInfo optbase = optBaseInfoManager.getObjectById(dj_id);
            object = optApplyManager.getObjectById(dj_id);
            object.setOptBaseInfo(optbase);
        }
        return "editNew";
    }
    
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
    

    /** 事项登记入口 （事权与人员关联）beg */
    public String listSQ() {
        list();
        //return "listSQ";
        return "listSQV2";
    }

    public String editDoOrReadSQ() {

        object = optApplyManager.getObjectById(object.getDjId());
        // FUserDetail fuser = ((FUserDetail) getLoginUser());
        // 生成部门树
        unitsJson = sysUnitManager.getAllUnitsJSON("1");
        String startDjId = (String) request.getParameter("startDjId");
        if (StringUtils.isNotBlank(startDjId)) {
            String nodeInstId = (String) request.getParameter("nodeId");
            Long nodeId = Long.parseLong(nodeInstId);
            request.setAttribute("nodeId", nodeId);
            request.setAttribute("startDjId", startDjId);
        }
        if (object != null) {
            optBaseInfoJson = optApplyManager.getJSONDocumentNames(object
                    .getDjId());
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            isapplyuser = vPowerUserInfo.getIsapplyuser();
            object.setOptBaseInfo(optBaseInfo);
            String pattern = "\\s*,\\s*";
            if (StringUtils.isNotBlank(object.getRecievedepno())) {
                String[] cc = object.getRecievedepno().trim().split(pattern);
                StringBuilder sb = new StringBuilder();
                if (cc != null && cc.length > 0) {
                    for (String ev : cc) {
                        sb.append(CodeRepositoryUtil.getValue("unitcode", ev)
                                + ",");
                    }
                }// 去掉最后一个逗号
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    unitValue = sb.toString();
                }
            }
            // 如果没有选部门时候，默认给出接收部门
            if (StringUtils.isBlank(unitValue)) {
                object.setRecievedepno(vPowerUserInfo.getUnitcode());
                unitValue = CodeRepositoryUtil.getValue("unitcode",
                        vPowerUserInfo.getUnitcode());
            }

            optId = optBaseInfo.getOptId();

            // 页面文书模版对应申请事项
            if (StringUtils.isNotBlank(optBaseInfo.getPowerid())) {
                // 到业务配置表里查询对应的模版编码
                powerOptInfo = powerOptInfoManager
                        .getObjectByItemID(optBaseInfo.getPowerid());
                if (powerOptInfo == null) {
                    powerOptInfo = new PowerOptInfo();
                }
            }
        } else {
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(itemId);
            // vPowerOrgInfo=vPowerOrgInfoManager.getSupPowerInfo(itemId,
            // fuser.getPrimaryUnit());
            object = new OptApplyInfo();
            // 生成登记编号
            String ev =request.getParameter("s_itemtype");//事项登记链接带过来的参数s_itemtype
            //判断是否是业务登记
            String applyItemType =(String)request.getParameter("applyItemType");
            if(StringUtils.isNotBlank(applyItemType)){
                applyItemType= applyItemType.split("\\/")[1];
            }
            object.setDjId(optApplyManager.getNextDjId(ev,applyItemType));
            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setPowername(vPowerUserInfo.getItemName());
            optBase.setPowerid(vPowerUserInfo.getItemId());
            optBase.setOptId(optId);

            optBase.setOrgcode(vPowerUserInfo.getUnitcode());// 部门编码

            object.setOptBaseInfo(optBase);

            // 如果没有选部门时候，默认给出接收部门
            if (StringUtils.isBlank(unitValue)) {
                object.setRecievedepno(optBase.getOrgcode());
                unitValue = CodeRepositoryUtil.getValue("unitcode",
                        optBase.getOrgcode());
            }
        }
        request.setAttribute("vPowerUserInfo", vPowerUserInfo);
        request.setAttribute("itemType", vPowerUserInfo.getItemType());// 标记itemType
        flowCode = vPowerUserInfo.getWfcode();
        optId = vPowerUserInfo.getOptid();

      //加载配置项
        initModuleParam();
        return "editDoOrRead";
    }

    /**
     * 文书保存后，初始化页面显示文书标题信息
     * 
     * @return
     */
    public String getTransaffairnameByDjId() {
        String result = "";
        try {
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            result = (null == optBaseInfo ? "" : optBaseInfo
                    .getTransaffairname());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            this.ajaxResponseText(response, result);
        }

        return null;
    }

    /**
     * 简单操作的ajax方法，使用文本作为数据传输（主要在需要同步时使用）
     * 
     * @param response
     * @param text
     */
    public void ajaxResponseText(HttpServletResponse response, String text) {
        // response 返回页面信息
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = null;

        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            writer = response.getWriter();
            writer.write(text); // 发送json数据
        } catch (Exception e) {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            try {
                writer = response.getWriter();
                writer.write(text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * @return
     */
    public String saveAndSubmitPermitSQ() {

        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        if (optBaseInfo.getFlowInstId() == null
                || "".equals(optBaseInfo.getFlowInstId().toString())) {

            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());

            flowCode = vPowerUserInfo.getWfcode();

            /*
             * 之前是根据权力所属部门的，这个不适用用于新版事权，故被舍弃 FlowInstance flowInst =
             * flowEngine.createInstance(flowCode,
             * optBaseInfo.getTransaffairname(), optBaseInfo.getTransAffairNo(),
             * fuser.getUsercode(), vPowerUserInfo.getUnitcode());
             */
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    optBaseInfo.getTransaffairname(), optBaseInfo.getDjId(),
                    fuser.getUsercode(), fuser.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            // object.setFlowInstId(flowInstId);
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            optBaseInfo.setBizstate("W");
            optBaseInfo.setBiztype("W");
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fuser.getUsercode(), "事权登记人员");
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "DJBM", fuser.getPrimaryUnit(), "事权登记部门");
        }
        // 将接收部门纳入权限引擎
        if (StringUtils.isNotBlank(object.getRecievedepno())) {
            String pattern = "\\s*,\\s*";
            String[] cc = object.getRecievedepno().trim().split(pattern);
            for (String ev : cc) {
                flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                        "JSBM", ev, "事权登记接收部门");
            }
        }else{
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "事权登记接收部门");
        }
        savePermitReg();
        saveIdeaInfo();
        if (startDjId != null && nodeId != null) {
            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            OaBizBindInfo oaBizBindInfo = new OaBizBindInfo();
            oaBizBindInfo.setStartDjid(startDjId);
            oaBizBindInfo.setBizType(optBaseInfo.getBiztype());
            oaBizBindInfo.setCreatetime(optBaseInfo.getCreatedate());
            oaBizBindInfo.setCreateuser(optBaseInfo.getCreateuser());
            oaBizBindInfo.setNo(oaBizBindInfoManager.getNextNO("GL"));
            oaBizBindInfo.setNodeinstid(nodeId);
            NodeInstance nodeInst = flowEngine.getNodeInstById(nodeId);
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            String nodeName = nodeInfo.getNodeName();
            oaBizBindInfo.setNodename(nodeName);
            oaBizBindInfo.setEndDjid(object.getDjId());
            oaBizBindInfoManager.saveObject(oaBizBindInfo);
            //跳转至已关联列表，dk 2015-12—25
            request.setAttribute("nodeInstId",nodeId );
            request.setAttribute("startDjId", startDjId);
            request.setAttribute("itemtype", s_itemtype);
            return startEntrance();
        }
        
        saveModuleParam();
        
        //向Rtx推送消息
        List<VuserTaskListOA> list=optBaseInfoManager.getTasksByNodeInstId(curNodeInstId);
        this.SendRtxMsn(object.getDjId(),list);        
        return "refreshTasks";
    }

    /*** 事项登记入口 （事权与人员关联） end */
    
    /**
     * 初始化通用配置
     */
  private void  initModuleParam(){
      
      // 到业务配置表里查询对应的模版编码
      powerOptInfo = powerOptInfoManager
              .getObjectByItemID(itemId);
      
      if(null!=powerOptInfo&&"T".equals(powerOptInfo.getIsGeneralModule())){
          moduleCode=powerOptInfo.getGeneralModuleCode();
          super.moduleParam = generalModuleParamMag.getObjectById(moduleCode);
          /**
           * 获得办件角色人名单,根据参数是否需要 办件人员
           */
          super.initTeamUsersConfig();
          /**
           * 权限引擎
           */
          super.initEngineUsersConfig();
          /**
           * 批分功能配置
           */
          if ("T".equals(moduleParam.getHasOrgRole())) {

              initPFUnit(moduleParam.getXbOrgRoleCode(), nodeCode);
              initXbOrgConfig();
          }
      }
      
  }
  /**
   * 保存流程办理人员，机构信息
   */
 private void saveModuleParam(){
     OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
             .getDjId());
     if(baseInfo!=null){
         this.setFlowInstId(baseInfo.getFlowInstId());
         
         super.saveTeamRolepeople();// 保存角色
         super.saveEngineRolepeople();// 保存权限引擎角色
         // 添加保存操作
         FUserDetail loginInfo = (FUserDetail) getLoginUser();
         if(null!=loginInfo){
          // 保存主办单位
             if (StringUtils.isNotBlank(zbOrgRoleCode)
                     && StringUtils.isNotBlank(zbOrgCode)) {
                 flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                         zbOrgRoleCode, loginInfo.getUsercode());
                 flowEngine.assignFlowOrganize(super.getFlowInstId(),
                         zbOrgRoleCode, zbOrgCode, loginInfo.getUsercode());
                 Set<String> sValues = new HashSet<String>();
                 sValues.add(zbOrgCode);
                 flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                         zbOrgRoleCode, sValues);
             }

             // 保存协办单位
             if (StringUtils.isNotBlank(xbOrgCodes)) {
                 flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                         xbOrgRoleCode, loginInfo.getUsercode());
                 String[] orgCodes = xbOrgCodes.split("[,]");
                 if (orgCodes != null && orgCodes.length > 0) {
                     flowEngine.assignFlowOrganize(super.getFlowInstId(),
                             xbOrgRoleCode,
                             new HashSet<String>(Arrays.asList(orgCodes)),
                             loginInfo.getUsercode());
                     flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                             xbOrgRoleCode,
                             new HashSet<String>(Arrays.asList(orgCodes)));
                 }
             }  
         }
          
     }
 }
    /**
     * 向RTX信息表插入待发送的rtx待办信息
     * @param djId
     * @param list
     */
    private boolean SendRtxMsn(String djId, List<VuserTaskListOA> list) {
            boolean returnvalue=true;
            if(list!=null && list.size()>0){
            //只有在现场环境才发送消息
            String messwitch = CodeRepositoryUtil.getValue("SYSPARAM",
                    "MESSWITCH");    
            if(StringUtils.isBlank(messwitch) || "F".equals(messwitch)){
                returnvalue=false;
            }
            if (returnvalue) {

                FUserDetail loginInfo = (FUserDetail) getLoginUser();
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());          

                String url = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + request.getContextPath();
                url += "/page/rtx/signauth.jsp?url=dispatchdoc/vuserTaskListOA!list.do";

                for (VuserTaskListOA task : list) {
                    FUserinfo userInfo = sysUserManager.getObjectById(task
                            .getUserCode());
                    String loginName = userInfo.getLoginname();

                    RtxInfo rtxInfo = new RtxInfo();
                    rtxInfo.setNo(rtxInfoManager.getNextkey());
                    rtxInfo.setDjId(object.getDjId());
                    rtxInfo.setNodeId(task.getNodeInstId());
                    rtxInfo.setCreateDate(new Date(System.currentTimeMillis()));
                    rtxInfo.setCreateUserCode(loginInfo.getUsercode());
                    rtxInfo.setCreateUserName(loginInfo.getUsername());
                    rtxInfo.setInfoContent("[" + optBaseInfo.getTransaffairname() + "|"
                            + url + "]");
                    rtxInfo.setReceiveUserCode(task.getUserCode());
                    rtxInfo.setReceiveUserName(loginName);
                    rtxInfo.setIsSend("0");
                    // sender.sendMessage("9999999"/*系统管理员*/,
                    // loginName, "您有一个新待办：",
                    // "["+optBaseInfo.getTransaffairname()+"|"+url+"]");
                    rtxInfoManager.saveObject(rtxInfo);
                }         
            }
            }
            return returnvalue;
    }
    /**
     * 事项启动入口
     */
    public String startEntrance() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        if(loginInfo==null){
        	return "login";
        }
        String nodeInstId = (String) request.getParameter("nodeInstId");
        String itemtype = (String) request.getParameter("itemtype");
        //String s_itemtype=(String) request.getParameter("s_itemtype");
        String startDjId=(String)request.getParameter("startDjId");
        if(StringUtils.isNotBlank(d_djId)){
            itemtype=d_itemtype;
            startDjId=d_djId;
            nodeInstId=d_nodeInstId;
        }
        if (StringUtils.isNotBlank(nodeInstId)) {
            Long nodeId = Long.parseLong(nodeInstId);
            request.setAttribute("nodeInstId", nodeId);
        }
        String dj=object.getDjId();
        if(StringUtils.isNotBlank(startDjId))
        {
            request.setAttribute("startDjId", startDjId);
            request.setAttribute("djId", startDjId);
            dj=startDjId;
        }
        
        request.setAttribute("itemtype", itemtype);
        vbizBindInfos = voaBizBindInfoManager.listNotVoaBizBindInfo(
                dj, itemtype,loginInfo.getUsercode());
        totalRows = (vbizBindInfos!=null &&  vbizBindInfos.size()>0)?vbizBindInfos.size():0;
      
        return "startEntrance";
    }

    /**
     * 供启动页面删除使用
     * 
     * @return
     */
    public String delete4tab() {
        String end_djId = (String) request.getParameter("end_djId");
        String itemtype=(String)request.getAttribute("itemtype");
        Long nodeInstId=(Long)request.getAttribute("nodeInstId");
        //Long nodeId = Long.parseLong(nodeInstId);
        request.setAttribute("itemtype", itemtype);
        request.setAttribute("nodeInstId", nodeInstId);
        if (StringUtils.isNotBlank(end_djId)) {
            oaBizBindInfoManager.deleteStartObjectById(end_djId);
        }
        return this.startEntrance();
    }

    /**
     * 查询许可登记信息
     */
    private void getPermitInfo() {

        object = optApplyManager.getObjectById(object.getDjId());

        if (object != null) {
            optBaseInfoJson = optApplyManager.getJSONDocumentNames(object
                    .getDjId());
            // System.out.println(optBaseInfoJson);
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            object.setOptBaseInfo(optBaseInfo);

            optId = optBaseInfo.getOptId();

            // 页面文书模版对应申请事项
            if (StringUtils.isNotBlank(optBaseInfo.getPowerid())) {
                // 到业务配置表里查询对应的模版编码
                powerOptInfo = powerOptInfoManager
                        .getObjectByItemID(optBaseInfo.getPowerid());
                if (powerOptInfo == null) {
                    powerOptInfo = new PowerOptInfo();
                }
            }
        } else {
            object = new OptApplyInfo();
            // 生成登记编号
            String ev = request.getParameter("flag");
            object.setDjId(optApplyManager.getNextDjId(ev));

            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setOptId(optId);
            // 生成办件编号：编号规则以JTHD打头+时间戳
            optBase.setTransAffairNo("JS0113-"
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(
                            System.currentTimeMillis())));
            object.setOptBaseInfo(optBase);
        }
        /*
         * List<SeaRouteInfo> rrList = new ArrayList<SeaRouteInfo>(); rrList =
         * seaRouteInfoManager.listObjects(); this.genSelectList(sriList,
         * rrList, 28);
         */
        // 根据业务编码，获取流程编码
        FOptinfo optInfo = functionManager.getObjectById(optId);
        if (optInfo != null) {
            flowCode = optInfo.getWfcode();
        }
    }

    /**
     * 保存业务数据
     * 
     * @return
     */
    public String savePermit() {

        OptBaseInfo optBaseInfo = object.getOptBaseInfo();

        OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (baseInfo == null) {
            String djId = String.valueOf(System.currentTimeMillis());
            optBaseInfo.setDjId(djId);
            optBaseInfo.setFlowInstId(super.getFlowInstId());
            optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            optBaseInfo.setCreateuser(((FUserDetail) getLoginUser())
                    .getUsercode());
            optBaseInfo.setBizstate("F");
            object.setDjId(djId);
        }
        return "";
    }

    /**
     * 保存基础业务数据:行政许可业务数据\行政基础信息表\7类申请事项
     * 
     * @return
     */
    private String proposerPaperTypes;

    public String savePermitReg() {
        try {

            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());

            if (baseInfo == null) {
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setFlowInstId(super.getFlowInstId());
                if (optBaseInfo.getFlowInstId() == null
                        || "".equals(optBaseInfo.getFlowInstId().toString())) {
                    optBaseInfo.setBiztype("F");
                    optBaseInfo.setBizstate("F");
                } else {
                    optBaseInfo.setBizstate("T");
                }
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                optBaseInfo.setCreateuser(((FUserDetail) getLoginUser())
                        .getUsercode());
                // object.setDjId(djId);

            } else {
                OptApplyInfo applyInfo = optApplyManager.getObjectById(object
                        .getDjId());
                optApplyManager.copyObjectNotNullProperty(applyInfo, object);
                object = applyInfo;
                optBaseInfoManager.copyObjectNotNullProperty(baseInfo,
                        optBaseInfo);
                optBaseInfo = baseInfo;
            }
            object.setBookDate(new Date(System.currentTimeMillis()));// 设置申请登记时间（当前系统时间）
            if ("02".equals(object.getProposerType())) {
                object.setProposerPaperType(proposerPaperTypes);// 区别页面自然人和法人重名
            }

            optBaseInfo.setFlowCode(flowCode);
            optApplyManager.saveObject(object);
            optBaseInfoManager.saveObject(optBaseInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        if("QB".equals(s_itemtype)){
            return this.list();
        }
        return this.listSQ();//
    }

    @Override
    public String delete() {
        super.delete();
        return this.list();
    }

    /**
     * 根据文书模版格式组装返回文书案号
     * 
     * @param writcodemodel
     * @return
     */
    @SuppressWarnings("unused")
    private String getWritCodeByWritcodemodel(String writcodemodel) {
        String writCode = "";
        if (StringUtils.isNotBlank(writcodemodel)) {
            StringBuffer sb = new StringBuffer();

            writCode = writcodemodel;

            String codemodelLike = "";// 用于查询
            if (writCode.indexOf("$year$") != -1) {
                int first = writCode.indexOf("$year$");
                sb.append(writCode.substring(0, first));
                sb.append(DatetimeOpt.convertDateToString(
                        new Date(System.currentTimeMillis()), "").substring(0,
                        4));
                sb.append(writCode.substring(first + 6, writCode.length()));
                writCode = sb.toString();
                sb.delete(0, sb.length());
            }
            if (writCode.indexOf("$Y2$") != -1) {
                int first = writCode.indexOf("$Y2$");
                sb.append(writCode.substring(0, first));
                sb.append(DatetimeOpt
                        .convertDateToString(
                                new Date(System.currentTimeMillis()), "")
                        .substring(0, 4).substring(2));
                sb.append(writCode.substring(first + 4, writCode.length()));
                writCode = sb.toString();
                sb.delete(0, sb.length());
            }

            if (writCode.indexOf("$N") != -1) {
                int firstBegin = writCode.indexOf("$N");
                int firstEnd = firstBegin + 2;
                int secondBegin = writCode.indexOf("$", firstEnd);
                int nunber = Integer.parseInt(writCode.substring(firstEnd,
                        secondBegin));
                sb.append(writCode.substring(0, firstBegin));
                String zero = "";
                String model = "";
                for (int i = 0; i < nunber; i++) {
                    if (StringUtils.isBlank(model)) {
                        model = "_";
                    } else {
                        model = model + "_";
                    }
                    if (StringUtils.isBlank(zero)) {
                        zero = "0";
                    } else {
                        zero = zero + "0";
                    }
                }
                codemodelLike = sb.toString();
                // String codeModel = sb.toString()+ model+
                // writCode.substring(secondBegin + 1, writCode.length());
                int index = optBaseInfoManager.getNumOfsameModel(
                        codemodelLike,
                        DatetimeOpt.convertDateToString(
                                new Date(System.currentTimeMillis()), "")
                                .substring(0, 4)) + 1;
                sb.append(this.endReplace(zero, String.valueOf(index))).append(
                        writCode.substring(secondBegin + 1, writCode.length()));
                writCode = sb.toString();
            }

        }
        return writCode;

    }

    private String endReplace(String str, String replace) {
        return replace == null || str == null ? str : str.substring(0,
                str.length() - replace.length())
                + replace;
    }

    /**
     * 保存过程日志信息
     */
    public void saveIdeaInfo() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        /** 获得用户所在部门 start */
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(loginInfo.getPrimaryUnit());
        fuerunit.setUserCode(loginInfo.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        /** 获得用户所在部门 end */

        optIdeaInfo.setUnitname(fuerunit.getUnitName());
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(0L);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename("登记");
        procInfo.setTransidea("提交部门核稿");
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTranscontent("登记");
        //if (nodeId == null) {
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if(!"QB".equals(s_itemtype)){
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            procInfo.setRecordId(vPowerUserInfo.getRecordid());
            procInfo.setArchiveType(vPowerUserInfo.getArchiveType());
            }else{
                vPowerOrgInfo = vPowerOrgInfoManager.getSupPowerInfo(
                        optBaseInfo.getPowerid(), loginInfo.getPrimaryUnit());
                procInfo.setRecordId(vPowerOrgInfo.getRecordid());
                procInfo.setArchiveType(vPowerOrgInfo.getArchiveType());
            }
            procInfo.setTranscontent(optBaseInfo.getContent());
           // }
        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }
    /**
     * 跳转到业务办理查询页面
     */
    public String saveCommon(){
        String applyItemType =(String)request.getParameter("applyItemType");
        if(StringUtils.isNotBlank(applyItemType)){
            applyItemType =applyItemType.substring(3);
        }
        OptApplyInfo optApplyInfo = optApplyManager.getObjectById(object.getDjId());
        if(optApplyInfo!=null){
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));  
            optApplyInfo.setProposerName(((FUserDetail) getLoginUser()).getUsername());
        }else{
            optApplyInfo = new OptApplyInfo();
            optApplyInfo.setDjId(object.getDjId());
            optApplyInfo.setApplyItemType(applyItemType);
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setProposerName(((FUserDetail) getLoginUser()).getUsername());
        }
       
        optApplyManager.saveObject(optApplyInfo);
        
        String returnUrl="";
        //SQ业务模块跳转控制
        if("SQ".equals(s_itemtype)){
            String subType =(String)request.getParameter("subType");
            if("SAVE".equals(subType)){
                returnUrl ="refreshTaskSQ";
            }
            if("SUB".equals(subType)){
                returnUrl ="refreshTasks";   
            }
        }else{
            returnUrl= "refreshTasks";
        }
        return returnUrl;
    }

    /**
     * 保存并提交基础业务数据:行政许可业务数据\行政基础信息表\7类申请事项
     * 
     * @return
     */
    public String saveAndSubmitPermit() {

        FUserDetail fuser = ((FUserDetail) getLoginUser());
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        Long flowInstId = optBaseInfo.getFlowInstId();
        if (optBaseInfo.getFlowInstId() == null
                || "".equals(optBaseInfo.getFlowInstId().toString())) {
           
              flowCode = suppowerManager.getFlowCodeByOrgItem(
              optBaseInfo.getPowerid(), fuser.getPrimaryUnit());     
           

            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    optBaseInfo.getTransaffairname(),
                    optBaseInfo.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            // object.setFlowInstId(flowInstId);
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            optBaseInfo.setBizstate("T");
            optBaseInfo.setBiztype("T");
            moduleParam =generalModuleParamMag.getObjectById(moduleCode);
            //记录人员权限
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fuser.getUsercode(), "登记人员");

            //将人员纳入流程工作小组 注意这边虽然是部门名称，但是存的是人员的，在页面转换时候需要注意
            if (StringUtils.isNotBlank(object.getRecievedepno())) {
                String pattern = "\\s*,\\s*";
                String[] cc = object.getRecievedepno().trim().split(pattern);
                for (String ev : cc) {
                    // 签报
                    flowEngine.assignFlowWorkTeam(optBaseInfo.getFlowInstId(),
                            (moduleParam==null|| moduleParam!=null&&StringUtils.isBlank(moduleParam.getTeamRoleCode()))?"zbcbr":moduleParam.getTeamRoleCode(), ev, "签报办理人员");
                }
            }
        }
        savePermitReg();
        saveIdeaInfo();
        if (startDjId != null && nodeId != null) {
            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            OaBizBindInfo oaBizBindInfo = new OaBizBindInfo();
            oaBizBindInfo.setStartDjid(startDjId);
            oaBizBindInfo.setBizType(oaBizBindInfoManager.initReturnBiztype(
                    optBaseInfo.getDjId(), startDjId));
            oaBizBindInfo.setCreatetime(optBaseInfo.getCreatedate());
            oaBizBindInfo.setCreateuser(optBaseInfo.getCreateuser());
            oaBizBindInfo.setNo(oaBizBindInfoManager.getNextNO("GL"));
            oaBizBindInfo.setNodeinstid(nodeId);
            NodeInstance nodeInst = flowEngine.getNodeInstById(nodeId);
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            String nodeName = nodeInfo.getNodeName();
            oaBizBindInfo.setNodename(nodeName);
            oaBizBindInfo.setEndDjid(object.getDjId());
            oaBizBindInfoManager.saveObject(oaBizBindInfo);
            //跳转已关联事项列表 dk 2015-12-25
            request.setAttribute("nodeInstId",nodeId );
            request.setAttribute("startDjId", startDjId);
            request.setAttribute("itemtype", s_itemtype);
            return startEntrance();
        }
      //向Rtx推送消息
        List<VuserTaskListOA> list=optApplyManager.getuserbysql(curNodeInstId);
        this.SendRtxMsn(object.getDjId(),list);
        
        //开启一个线程
        if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
            final String userCode = fuser.getUsercode();
            final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
            //"***/anonymous***"代表匿名访问
            String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
            if(contextPath.endsWith("/")){
                contextPath = contextPath.substring(0, contextPath.length()-1);
            }
            contextPath = contextPath + request.getContextPath();
            String djId=null==optBaseInfo.getDjId()?object.getDjId():optBaseInfo.getDjId();
            final String formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(contextPath,userCode, djId);
            
            taskExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    createPDF(object, "签报发起", 0L,userCode,exePath,formHtmlUrl);
                }
            });
        }
        
        return "refreshTasks";
    }
    
    
    public void saveQB(){
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        OptBaseInfo opt =optBaseInfoManager.getObjectById(object.getOptBaseInfo().getDjId());
        opt.setTransaffairname(optBaseInfo.getTransaffairname());
        opt.setContent(optBaseInfo.getContent());
        opt.setCriticalLevel(optBaseInfo.getCriticalLevel());
        optBaseInfoManager.saveObject(opt);
    }
    
    private void createPDF(OptApplyInfo optApplyInfo,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        OptPdfInfo optPdfInfo = null;
        try {
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<签报登记办件djId:"+optApplyInfo.getDjId()+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(optApplyInfo.getDjId(), nodeInstId);
           File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(optApplyInfo.getDjId(),nodeInstId,formHtmlUrl);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
            //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(optApplyInfo.getDjId())));
           optPdfInfo.setNodeName(nodeName);
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<签报登记办件djId:"+optApplyInfo.getDjId()+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        } catch (Exception e) {
            log.error("生成PDF异常："+e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
    }

    /**
     * 权力许可通用业务框架属性
     * 
     * @return
     */
    public String generalOpt() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        optBaseInfoJson = optApplyManager.getJSONDocumentNames(optBaseInfo
                .getDjId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");

        transFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!doOpt.do?djId="
                        + optBaseInfo.getDjId() + "&flowInstId="
                        + optBaseInfo.getFlowInstId() + "&nodeInstId="
                        + getNodeInstId() + "&moduleCode=" + moduleCode
                        + "&documentTemplateIds=" + documentTemplateIds);

        // 将配置信息放在主框架上
        moduleParam = generalModuleParamMag.getObjectById(moduleCode);

        transFrameInfo.setFrameHeight("220px");

        frameList.add(transFrameInfo);

        // frameList.add(getViewFrame(optBaseInfo.getDjId()));

      /*  frameList.add(GeneralOperatorAction.getIdeaListFrame(optBaseInfo
                .getDjId()));

        frameList.add(GeneralOperatorAction.getStuffListFrame(optBaseInfo
                .getDjId()));*/

        jspInfo = new OptJspInfo();
        jspInfo.setTitle("登记办理");
        jspInfo.setFrameList(frameList);

        return "optframe";
    }

    /**
     * 权力许可通用业务框架属性 办件信息查看
     * 
     * @return
     */
    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
     
        
        OptApplyInfo optApplyInfo = optApplyManager.getObjectById(object.getDjId());
        String applyItemType =(optApplyInfo==null?"":optApplyInfo.getApplyItemType());
        
        if(StringUtils.isNotBlank(applyItemType)){
            frameList.add(getBizYWViewFrame(object.getDjId(),applyItemType,"0"));
        }else{
            //查看办件基本数据信息
            frameList.add(getBizDataViewFrame(object.getDjId()));
        }
        frameList.add(super.getCommonStuffFrame(object.getDjId(),
                null, null));// 附件
        // 用于展示查看详细信息Lab标签内容 原先tab方式显示
//        frameList.add(this.getAllViewFrame(object.getDjId()));

        // frameList 页面列表显示 新的
        // frameList=getAllInfoListFrame(frameList,object.getDjId());
        jspInfo = new OptJspInfo();
        // jspInfo.setTitle("许可办理查看");
        jspInfo.setFrameList(frameList);
        request.setAttribute("applyItemType", applyItemType);
        request.setAttribute("startDjid", request.getParameter("startDjid"));
        //从首页进入
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        if(super.getFlowInstId()==null){
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
           request.setAttribute("flowInstId", optBaseInfo.getFlowInstId());
        }
        return "generalOptView";
    }

    @SuppressWarnings("unused")
    private List<OptHtmlFrameInfo> getAllInfoListFrame(
            List<OptHtmlFrameInfo> frameList, String djId) {
        frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
        frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
        // 查关联的源信息
        frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId, "S",
                "nodelete"));// 主动发起的关联
        frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId, "E",
                "nodelete"));// 被其他事项关联
        // 督办催办信息
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.clear();
        filterMap.put("supDjid", object.getDjId());
        oasuplist = oaSuperviseManager.listObjects(filterMap);
        if (null != oasuplist && oasuplist.size() > 0) {
            frameList.add(OaSuperviseAction.getSupListFrame(object.getDjId()));
        }
        String startdjid=request.getParameter("startDjid");
        if(StringUtils.isNotBlank(startdjid)){
            request.setAttribute("startDjid", startdjid);
        }
        return frameList;
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getAllViewFrame(String djid) {
        String itemType = null;
        if(StringUtils.isNotEmpty(djid)){
            itemType = djid.replaceAll("[0-9]+", "");
        }
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("AllInfoFrame");
        stuffsFrameInfo
                .setFrameSrc("/powerruntime/optApply!getAllCaseView.do?djId="
                        + djid + "&nodeInstId=" + curNodeInstId
                        + "&itemType="+itemType);
        stuffsFrameInfo.setFrameHeight("300px");
        return stuffsFrameInfo;
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @return
     */
    public String getAllCaseView() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(fuser.getUsercode());

        VPowerOrgInfo l = vPowerOrgInfoManager.getSupPowerInfo(
                optBaseInfo.getPowerid(), dept.getUnitcode());
        isapplyuser = l.getIsapplyuser();

        if (StringUtils.isEmpty(isapplyuser)) {
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            isapplyuser = vPowerUserInfo.getIsapplyuser();
        }

        optNewlyIdeaList.add(optNewly_viewItemFrame(optBaseInfo.getDjId()));// 业务信息查看

        // 用于初始化查看页面默认显示
        curUrl = "/powerruntime/generalOperator!listIdeaLogs.do?djId="
                + object.getDjId();

        if (null == optBaseInfo.getFlowInstId()) {
            super.setFlowInstId((long) 9999999);
        } else {
            super.setFlowInstId(optBaseInfo.getFlowInstId());
        }
        boolean flag = this.getOaBizInfolist(object.getDjId());
        if (flag) {
            optNewlyIdeaList.add(optNewly_genOaBizBindInfoFrame(object
                    .getDjId()));
        }
        request.setAttribute("flowInstId", super.getFlowInstId());
        return "sxinfoView";
    }

    /**
     * 封装关联业务信息
     */
    private OptNewlyIdea optNewly_genOaBizBindInfoFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 4);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("关联业务信息");
        newlyIdea.setUrl("/oa/oaBizBindInfo!listbiz4tab.do?djid=" + djId);
        return newlyIdea;
    }

    /**
     * 供通用页面办理查看使用
     */
    @SuppressWarnings("unchecked")
    public Boolean getOaBizInfolist(String djId) {
        List<OaBizBindInfo> sobjectlist = new ArrayList<OaBizBindInfo>();// 主动关联
        List<OaBizBindInfo> eobjectlist = new ArrayList<OaBizBindInfo>();// 被动关联
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if (StringUtils.isNotBlank(djId)) {// s_startDjid非空时，查相关主动关联的事项
            filterMap.put("startDjid", djId);
            PageDesc pageDesc = makePageDesc();
            sobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
            filterMap.remove("startDjid");
            filterMap.put("endDjid", djId);
            eobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
        }
        if ((sobjectlist != null && sobjectlist.size() >= 1)
                || (eobjectlist != null && eobjectlist.size() >= 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * TAB属性配置
     * 
     * @param djId
     * @param string
     * @return
     */
    private OptNewlyIdea optNewly_viewItemFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 1);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("事项信息");
        newlyIdea.setUrl("/powerruntime/optApply!view.do?djId=" + djId
                + "&showback=TRUE");
        return newlyIdea;
    }
 
   /* *//**
     * 办件基本数据查看
     * 
     * @param djid
     * @return
     *//*
    public static OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        
        // 事项登记查看申请人信息
        if (djid.indexOf("SQ") != -1){
            viewFrameInfo.setFrameLegend("事项信息"); 
        }
        if(djid.indexOf("QB") != -1){
            viewFrameInfo.setFrameLegend("签报信息"); 
        }
        
        viewFrameInfo.setFrameId("viewFrame");
        
         * viewFrameInfo .setFrameSrc("/wwd/srPermitApply!viewItem.do?djId=" +
         * djid);
         
        viewFrameInfo.setFrameSrc("/powerruntime/optApply!viewInfo.do?djId="
                + djid);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
    }*/

    private String itemType = "";

    public String viewInfo() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
      //登录用户信息
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        //该参数用来从容器之外的请求访问的，请求不是从浏览器发出来的，虽然我们可以劫持session，但是webservice访问时，
        //不知道哪个session是有效的，所以这里还是手动配置权限框架不拦截1、spring security 2、AccessFilter
       String usercode = request.getParameter("usercode");
       if(fuser==null && StringUtils.isBlank(usercode)){
           return "login";
       }else{
           if(StringUtils.isBlank(usercode)){
               usercode = fuser.getUsercode();    
           }
       }
        // FUserunit dept =
        // sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
        // optBaseInfo.getPowerid()获取itemType
        // VPowerOrgInfo l
        // =vPowerOrgInfoManager.getSupPowerInfo(optBaseInfo.getPowerid(),
        // dept.getUnitcode());
        request.setAttribute("optBaseInfo", optBaseInfo);
        object.setFlowInstId(optBaseInfo.getFlowInstId());
        // request.setAttribute("vPowerOrgInfo", l);

        // 根据ItemId获取在用权力信息 获取itemType
        suppowerinusing = vsuppowerinusingManager
                .findB_PowerByItemId(optBaseInfo.getPowerid());

        if (suppowerinusing != null && suppowerinusing.getItemType() != null) {
            itemType = suppowerinusing.getItemType();
        } else {
            itemType = initReturn(optBaseInfo.getDjId());
        }

        request.setAttribute("itemType", itemType);
        
        //查看权限验证 false 不验证 true 验证 
        boolean isVailViewPower=vOptBaseListManager.isVailViewPower(object.getDjId(), usercode);
        //动态加载意见
        writeIdeaModuleToRequestAttribute(object.getDjId(),isVailViewPower);
        return "viewInfo";
    }
    
    private void writeIdeaModuleToRequestAttribute(String djId,boolean needValid){
        String isPdf = request.getParameter("isPdf");
        boolean showIdeaContent = false;
        //如果需要验证，那么检测是否有查看意见的权限  
        boolean hasViewPower = CodeRepositoryUtil.checkUserOptPower("DOCXZ","DocViewAll");
        if(!needValid || (needValid && hasViewPower)){
            showIdeaContent = true;
        }
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
        //合并分管领导和主要领导意见
        String zycontent = null;
        for(OptIdeaInfo idea:ideas){
            if("领导批示".equals(idea.getTransidea())){
                zycontent = idea.getTranscontent();
            }
        }
        for(OptIdeaInfo idea:ideas){
            if("主要领导批示".equals(idea.getTransidea())){
                idea.setTranscontent(idea.getTranscontent() + zycontent);
            }
        }
        
        request.setAttribute("ideas", ideas);
    }
    
    /**
     * 根据DJID返回列表参数itemType
     */
    private String initReturn(String djid) {
        if (djid.indexOf("QB") != -1) {
            // 和菜单查询条件保持一致
            itemType = "QB";
        }
        // HYSQ CARSQ与SQ区别 如果要检索的字符串值没有出现，则该方法返回 -1。
        else if (djid.indexOf("SQ") != -1 && djid.indexOf("CARSQ") == -1
                && djid.indexOf("HYSQ") == -1) {
            itemType = "SQ";
        }
        return itemType;
    }

    @Override
    public String view() {
        // TODO Auto-generated method stub
        super.view();
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        optBaseInfo.setItemtype(BizCommUtil.getPrefix4Biz(optBaseInfo.getDjId(), 1));
        object.setOptBaseInfo(optBaseInfo);
        // 显示接收部门
        String pattern = "\\s*,\\s*";
        if (StringUtils.isNotBlank(object.getRecievedepno())) {
            String[] cc = object.getRecievedepno().trim().split(pattern);
            StringBuilder sb = new StringBuilder();
            if (cc != null && cc.length > 0) {
                for (String ev : cc) {
                    if("QB".equals(optBaseInfo.getItemtype())){
                        sb.append(CodeRepositoryUtil.getValue("usercode", ev) + ",");
                    }else{
                     sb.append(CodeRepositoryUtil.getValue("unitcode", ev) + ",");
                     }
                }
            }// 去掉最后一个逗号
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                unitValue = sb.toString();
            }
        }
        return VIEW;
    }

    // 加载风险点信息:在申请时调用
    public String forwardRisk() {
        String riskId = request.getParameter("riskId");
        if (StringUtils.isBlank(riskId))
            return "riskFrame";

        RiskInfo riskInfo = riskInfoManager.getObjectById(Long
                .parseLong(riskId));
        if (riskInfo != null) {
            OptBaseInfo optBaseInfo = new OptBaseInfo();
            optBaseInfo.setRiskType(riskInfo.getRisktype());
            optBaseInfo.setRiskDesc(riskInfo.getRiskdes());
            optBaseInfo.setRiskResult(riskInfo.getRiskdeal());

            object.setOptBaseInfo(optBaseInfo);
        }
        // 跳转forward
        return "riskFrame";
    }

    // 加载风险点信息:在编辑时且之前已经保存过风险点时加载
    public String editRisk() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        object.setOptBaseInfo(optBaseInfo);
        // 跳转forward
        return "riskFrame";
    }

    public String saveBaseInfoOfRisk() {

        return "";
    }

    /**
     * 行政许可登记加载
     * 
     * @return
     */
    public String permitReg() {
        return "permitReg";
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }

    public String getParentunit() {
        return parentunit;
    }

    public void setParentunit(String parentunit) {
        this.parentunit = parentunit;
    }
    List<String> bondlist = new ArrayList<String>();
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
//            filterMap.put("usercode", fuser.getUsercode());
            djid=(String)request.getParameter("djid");
            if(StringUtils.isNotBlank(djid)){
                List<OaAssetinformationBond> list = oaAssetinformationBondManager.listOaAssetinformation(djid);
                if (list != null && list.size() >= 1){
                    for(OaAssetinformationBond s : list){
                        bondlist.add(s.getNo());
                    }
                  //将bondlist转成字符串
                    StringBuffer sb = new StringBuffer();
                    if( bondlist!= null && bondlist.size() >= 1){
                        int size = bondlist.size();
                        for (int i=0; i<size; i++) {
                            if (i > 0) {
                                sb.append(",");
                            }
                            sb.append("'"+bondlist.get(i)+"'");
                        }
                    }
                    String params = sb.toString();
                    filterMap.put("params", params);
                }
            }
          //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begincreateDate"))&&StringUtils.isBlank((String)filterMap.get("endcreateDate"))){
                filterMap.put("begincreateDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endcreateDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            
          
            
            userRank=getUserRankByUsercode(fuser.getUsercode());//控制页面显示内容:全机构(部门下拉框)、全科室（checkboox）
            //列表页面添加职务分类查看 ：处长查看机构所有查看，科长查看本科室
            if(StringUtils.isNotBlank(queryUnderUnit)){//(列表类别)按职务等级查询列表--默认显示自己的
                
                userUnit =sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
                if(StringUtils.equals(userRank, "CZ")){
                    filterMap.put("queryUnderUnit","true");//页面传参
                    filterMap.put("unitcode",userUnit.getUnitcode());
                }else  if(StringUtils.equals(userRank, "TZ")){
                    filterMap.put("queryUnderUnit","true");//部门编码从页面传入queryUnderUnit
//                  filterMap.put("unitcode",userUnit.getUnitcode());//部门编码从页面传入s_unitcode
                }
                
                
            }else{
                filterMap.put("usercode", fuser.getUsercode());
            }
            
            srPermitApplyList =optApplyManager.listOptApplyInfo(filterMap,
                    pageDesc); 
            
            
//            totalRows = (srPermitApplyList!=null &&srPermitApplyList.size()>0)?srPermitApplyList.size():0;
            totalRows = pageDesc.getTotalRows();
            
            FUserunit dept = sysUserManager.getUserPrimaryUnit(fuser
                    .getUsercode());
            unitsJson = sysUnitManager.getAllSubUnitsJSON(dept.getUnitcode());
            parentunit = sysUnitManager.getObjectById(dept.getUnitcode())
                    .getParentunit();
            unitList=unitList();//科级部门

            setbackSearchColumn(filterMap);
            //塞入办理步骤，用于查看流程图  by 2016-4-1 dk
            if(srPermitApplyList!=null&&srPermitApplyList.size()>0){
                for(VOptApplyInfo o:srPermitApplyList){
                    
                    if(super.getFlowInstId()==null){
                        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(o
                                .getDjId());
                        o.setFlowInstId(optBaseInfo.getFlowInstId());
                    }
                }
            }
            
            //return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 根据usercode 获取用户行政职务
     * @param usercode
     * @return
     */
    private String getUserRankByUsercode(String  usercode){
        
        
        userUnit =sysUserManager.getUserPrimaryUnit(usercode);
        
        if(null==userUnit){
            userRank=null;
        }else
        {
            String userUnitRank=userUnit.getUserrank();
            if("ZT".equals(userUnitRank)||"FT".equals(userUnitRank)){
            userRank="TZ";//厅长级别
            }else if("ZC".equals(userUnitRank)||"FC".equals(userUnitRank)){
            userRank="CZ";//处长级别
            }else{
            userRank=null;//其他（科员，办事员等）--/科长
            }
        }
        return userRank;
        
    }

    /**
     *  //只获取科室一级的部门
     * @return
     */
    private List<FUnitinfo> unitList(){
        List<FUnitinfo> unitlist = sysUnitManager.getAllSubUnits("1");
        List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
        // 剔除非科室的部门
        if (unitlist != null && unitlist.size() > 0) {
            for (FUnitinfo fUnitinfo : unitlist) {
                if ("1".equals(fUnitinfo.getParentunit())
                        || "1".equals(fUnitinfo.getUnitcode())) {
                    continue;
                } else {
                    delList.add(fUnitinfo);
                }
            }
            unitlist.removeAll(delList);
        }
        return unitlist;
    }
    
    

    /**
     * 提交维修登记-节点信息
     * 
     * @return
     */
    public String submitReditSQ() {

        // 保存流程组织机构
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        OptApplyInfo optApplyInfo = optApplyManager.getObjectById(object
                .getDjId());
        if (StringUtils.isNotBlank(optApplyInfo.getRecievedepno())) {
            String pattern = "\\s*,\\s*";
            String[] cc = object.getRecievedepno().trim().split(pattern);
            for (String ev : cc) {
                flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                        "JSBM", ev, "登记");
            }
        }
        this.saveReditSQ();
        // 保存过程日志信息
        saveIdeaInfo("登记");
        submitNode();
        return "refreshTasks";
    }

    /**
     * 保存回退登记骤过程日志信息
     */
    public void saveIdeaInfo(String nodename) {
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
        procInfo.setNodeInstId(curNodeInstId);
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

    /**
     * 保存维修登记-节点信息
     * 
     * @return
     */
    public String saveReditSQ() {
        OptApplyInfo optApplyInfo = optApplyManager.getObjectById(object
                .getDjId());
        if (optApplyInfo != null) {
            optApplyInfo.setRecievedepno(object.getRecievedepno());
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo != null) {
                optBaseInfo.setTransaffairname(object.getOptBaseInfo()
                        .getTransaffairname());
                optBaseInfo.setContent(object.getOptBaseInfo().getContent());
            }
            optBaseInfoManager.saveObject(optBaseInfo);
            optApplyManager.saveObject(optApplyInfo);
        }
        return "refreshTasks";
    }

    /**
     * 维修登记-节点信息
     * 
     * @return
     */
    public String editOptBaseSQ() {
        String itemId = (String) request.getParameter("itemId");
        FUserDetail user = ((FUserDetail) getLoginUser());
        VPowerUserInfoId o = new VPowerUserInfoId();
        o.setItemId(itemId);
        o.setUsercode(user.getUsercode());
        VPowerUserInfo vPowerUserInfo = vPowerUserInfoManager.getObjectById(o);
        request.setAttribute("vPowerUserInfo", vPowerUserInfo);
        unitsJson = sysUnitManager.getAllUnitsJSON("1");
        // 用于展示报修登记页面的接受部门
        object = optApplyManager.getObjectById(object.getDjId());
        String pattern = "\\s*,\\s*";
        if (StringUtils.isNotBlank(object.getRecievedepno())) {
            String[] cc = object.getRecievedepno().trim().split(pattern);
            StringBuilder sb = new StringBuilder();
            if (cc != null && cc.length > 0) {
                for (String ev : cc) {
                    sb.append(CodeRepositoryUtil.getValue("unitcode", ev) + ",");
                }
            }// 去掉最后一个逗号
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                unitValue = sb.toString();
            }
        }

        this.edit();
        this.generalOpt();
        return "optApplyEditOptBaseSQ";
    }
    /**
     * 办件业务数据查看
     * 
     * @param djid
     * @return
     */
    public static OptHtmlFrameInfo getBizYWViewFrame(String djid,String moduleType,String isFlow) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameLegend("业务信息");
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo
        .setFrameSrc("/oa/"+moduleType+"!view.do?isFlow=" + isFlow + "&djId=" + djid);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
    }
    
    /**
     * 办件业务数据查看编辑页面
     * 
     * @param djid
     * @return
     */
    public static OptHtmlFrameInfo getBizEditViewFrame(String djid,String moduleType) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameLegend("业务信息");
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameSrc("/oa/"+moduleType+"!view.do?djId=" + djid+"&canEdit=T");
     
        return viewFrameInfo;
    }

    /**
     * 办件基本数据查看
     * 
     * @param djid
     * @return
     */
    public static OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        
        // 事项登记查看申请人信息
        if (djid.indexOf("SQ") != -1){
            viewFrameInfo.setFrameLegend("事项信息"); 
        }
        if(djid.indexOf("QB") != -1){
            viewFrameInfo.setFrameLegend("签报信息"); 
        }
        
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameSrc("/powerruntime/optApply!viewInfo.do?djId="
                + djid);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
    }

    /*************** 保存业务数据 ************************/

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public List<VOptApplyInfo> getSrPermitApplyList() {
        return srPermitApplyList;
    }

    public void setSrPermitApplyList(List<VOptApplyInfo> srPermitApplyList) {
        this.srPermitApplyList = srPermitApplyList;
    }

    public void setRiskInfoManager(RiskInfoManager riskInfoManager) {
        this.riskInfoManager = riskInfoManager;
    }

    public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public GeneralModuleParam getModuleParam() {
        return moduleParam;
    }

    public void setModuleParam(GeneralModuleParam moduleParam) {
        this.moduleParam = moduleParam;
    }

    public String getProposerPaperTypes() {
        return proposerPaperTypes;
    }

    public void setProposerPaperTypes(String proposerPaperTypes) {
        this.proposerPaperTypes = proposerPaperTypes;
    }

    public String getOptBaseInfoJson() {
        return optBaseInfoJson;
    }

    public void setOptBaseInfoJson(String optBaseInfoJson) {
        this.optBaseInfoJson = optBaseInfoJson;
    }

    HttpServletResponse response;

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }

    public void setPowerOptInfoManager(PowerOptInfoManager powerOptInfoManager) {
        this.powerOptInfoManager = powerOptInfoManager;
    }

    public PowerOptInfo getPowerOptInfo() {
        return powerOptInfo;
    }

    public void setPowerOptInfo(PowerOptInfo powerOptInfo) {
        this.powerOptInfo = powerOptInfo;
    }

    public void setOptWritdefManager(OptWritdefManager optWritdefManager) {
        this.optWritdefManager = optWritdefManager;
    }

    public VPowerOrgInfo getvPowerOrgInfo() {
        return vPowerOrgInfo;
    }

    public void setvPowerOrgInfo(VPowerOrgInfo vPowerOrgInfo) {
        this.vPowerOrgInfo = vPowerOrgInfo;
    }

    public void setGeneralModuleParamMag(
            GeneralModuleParamManager generalModuleParamMag) {
        this.generalModuleParamMag = generalModuleParamMag;
    }

    public List<FUnitinfo> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public OptApplyManager getOptApplyManager() {
        return optApplyManager;
    }

    public void setOptApplyManager(OptApplyManager optApplyManager) {
        this.optApplyManager = optApplyManager;
        this.setBaseEntityManager(optApplyManager);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIsapplyuser() {
        return isapplyuser;
    }

    public void setIsapplyuser(String isapplyuser) {
        this.isapplyuser = isapplyuser;
    }

    public void setvPowerOrgInfoManager(
            VPowerOrgInfoManager vPowerOrgInfoManager) {
        this.vPowerOrgInfoManager = vPowerOrgInfoManager;
    }

    public VPowerOrgInfoManager getvPowerOrgInfoManager() {
        return vPowerOrgInfoManager;
    }

    public List<OptNewlyIdea> getOptNewlyIdeaList() {
        return optNewlyIdeaList;
    }

    public void setOptNewlyIdeaList(List<OptNewlyIdea> optNewlyIdeaList) {
        this.optNewlyIdeaList = optNewlyIdeaList;
    }

    public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
        this.optNewlyIdeaManager = optNewlyIdeaManager;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getS_itemtype() {
        return s_itemtype;
    }

    public void setS_itemtype(String s_itemtype) {
        this.s_itemtype = s_itemtype;
    }

    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public String getStartDjId() {
        return startDjId;
    }

    public void setStartDjId(String startDjId) {
        this.startDjId = startDjId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public void setOaBizBindInfoManager(
            OaBizBindInfoManager oaBizBindInfoManager) {
        this.oaBizBindInfoManager = oaBizBindInfoManager;
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

    public void setvPowerUserInfoManager(
            VPowerUserInfoManager vPowerUserInfoManager) {
        this.vPowerUserInfoManager = vPowerUserInfoManager;
    }

    public VsuppowerinusingManager getVsuppowerinusingManager() {
        return vsuppowerinusingManager;
    }

    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.vsuppowerinusingManager = vsuppowerinusingManager;
    }

    public Vsuppowerinusing getSuppowerinusing() {
        return suppowerinusing;
    }

    public void setSuppowerinusing(Vsuppowerinusing suppowerinusing) {
        this.suppowerinusing = suppowerinusing;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public void setUserPowerList(List<VPowerUserInfo> userPowerList) {
        this.userPowerList = userPowerList;
    }

    public List<VPowerUserInfo> getUserPowerList() {
        return userPowerList;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public List<OptBaseInfo> getOptBaseInfos() {
        return optBaseInfos;
    }

    public void setOptBaseInfos(List<OptBaseInfo> optBaseInfos) {
        this.optBaseInfos = optBaseInfos;
    }

    public String getResetUsers() {
        return resetUsers;
    }

    public void setResetUsers(String resetUsers) {
        this.resetUsers = resetUsers;
    }

    public String getS_bizstate() {
        return s_bizstate;
    }

    public void setS_bizstate(String s_bizstate) {
        this.s_bizstate = s_bizstate;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }

    public void setOaAssetinformationBondManager(
            OaAssetinformationBondManager oaAssetinformationBondManager) {
        this.oaAssetinformationBondManager = oaAssetinformationBondManager;
    }

    public String getIsDept() {
        return isDept;
    }

    public void setIsDept(String isDept) {
        this.isDept = isDept;
    }

    public String getQueryUnderUnit() {
        return queryUnderUnit;
    }

    public void setQueryUnderUnit(String queryUnderUnit) {
        this.queryUnderUnit = queryUnderUnit;
    }

    public FUserunit getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(FUserunit userUnit) {
        this.userUnit = userUnit;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }
    public void setVoaBizBindInfoManager(
            VoaBizBindInfoManager voaBizBindInfoManager) {
        this.voaBizBindInfoManager = voaBizBindInfoManager;
    }

    public List<VoaBizBindInfo> getVbizBindInfos() {
        return vbizBindInfos;
    }
    
    
    public List<VOptBaseList> getVoptBaseLists() {
        return voptBaseLists;
    }

   

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    
    public void setVoptBaseLists(List<VOptBaseList> voptBaseLists) {
        this.voptBaseLists = voptBaseLists;
    }

    public void setVbizBindInfos(List<VoaBizBindInfo> vbizBindInfos) {
        this.vbizBindInfos = vbizBindInfos;
    }

    public List<OaSupervise> getOasuplist() {
        return oasuplist;
    }

    public void setOasuplist(List<OaSupervise> oasuplist) {
        this.oasuplist = oasuplist;
    }

    public String getDocumentTemplateIds() {
        return documentTemplateIds;
    }

    public void setDocumentTemplateIds(String documentTemplateIds) {
        this.documentTemplateIds = documentTemplateIds;
    }

    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
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
    
    public List<VOptBaseList> getvOptBaseList() {
        return vOptBaseList;
    }

    public void setvOptBaseList(List<VOptBaseList> vOptBaseList) {
        this.vOptBaseList = vOptBaseList;
    }


    
}
