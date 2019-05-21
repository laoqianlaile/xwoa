package com.centit.powerruntime.po;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class GeneralModuleParam implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String moduleCode;

    private String hasIdea;
    private String ideaLabel;
    private String ideaCatalog;
    private String ideaContent;
    private String hasAttention;
    private String attentionLabel;
    private String attentionFilter;
    private String hasStuff;
    private String stuffGroupId;
    private String hasDocument;
    private String documentLabel;
    private String documentType;
    private String documentTemplateIds;
    private String documentTemplateNames;
    private String canDefer;
    private String canRollback;
    private String canClose;
    private Long riskId;
    private String assignTeamRole;
    private String teamRoleCode;
    private String teamRoleName;
    private String teamRoleFilter;
    
    private String assignEngineRole;
    private String engineRoleCode;
    private String engineRoleName;
    private String engineRoleFilter;
    
    private RiskInfo riskInfo;
    private String isInUse;
    private String hasOrgRole;
    private String zbOrgRoleCode;
    private String xbOrgRoleCode;
    private String xbOrgRoleName;
    private String xbOrgRoleFilter;
    private String docReadOnly;
    private String hasPreIdea;

    private String nodeName;// 节点名称 add by HX 2013-04-15

    private Date lastUpdateTime;
    private String isTeamRoleCheck;
    private String isQuickContent;
    private String quickContentResult;
    
    private String hasZbUser;
    private String zbUserRoleCode;
    private String zbUserLabel;
    private String zbUserFilter;
    
    private String hasXbUser;
    private String xbUserRoleCode;
    private String xbUserLabel;
    private String xbUserFilter;
    private String bookMarkType;
    
    private String hasSq;//是否启动事权
    private String hasFw;//是否启动发文
    private String caneditopt;//是否编辑业务信息
    private String canphoneopt;//是否支持终端办理
    
    private String hasQb;
    private String hasJZ;//是否兼职或者分管部门
    private String hasDb;//是否开放督办权限
   

    private String isShowInNode;//是否显示环节意见
    private String nodeLabel;//环节意见标签
    private Long nodeOrder;//环节意见排序
    
    /*
    * ideaModule {"type":"显示条数",""  ,"dataModel":" <>请 <>  办理人员   日期","":"日期格式化格式"}
    * type : sigle 单一 取最新数据 |all 多条数据（待补充）
    * dataModel :数据模板 user 办理人员| detail 详细办理信息
    * 扩充
    */
    private String ideaModule;//显示意见模板--存放描述模板的json（）
   
    
    private String canSendMessage;//是否可以发送短信
    private String tips;//温馨提示
    
    private String btIdea;//意见是否必填
    
   
    public String getBookMarkType() {
        return bookMarkType;
    }

    public void setBookMarkType(String bookMarkType) {
        this.bookMarkType = bookMarkType;
    }

    public GeneralModuleParam(String moduleCode, String hasIdea,
            String ideaLabel, String ideaCatalog, String ideaContent,
            String hasAttention, String attentionLabel, String attentionFilter,
            String hasStuff, String stuffGroupId, String hasDocument,
            String documentLabel, String documentType,
            String documentTemplateIds, String documentTemplateNames,
            String canDefer, String canRollback, String canClose, Long riskId,
            String assignTeamRole, String teamRoleCode, String teamRoleName,
            String teamRoleFilter, String assignEngineRole,
            String engineRoleCode, String engineRoleName,
            String engineRoleFilter, RiskInfo riskInfo, String isInUse,
            String hasOrgRole, String zbOrgRoleCode, String xbOrgRoleCode,
            String xbOrgRoleName, String xbOrgRoleFilter, String docReadOnly,
            String hasPreIdea, String nodeName, Date lastUpdateTime,
            String isTeamRoleCheck, String isQuickContent,
            String quickContentResult, String hasZbUser, String zbUserRoleCode,
            String zbUserLabel, String zbUserFilter, String hasXbUser,
            String xbUserRoleCode, String xbUserLabel, String xbUserFilter,
            String bookMarkType, String hasSq, String hasFw, String hasQb,
            String hasJZ, String isShowInNode, String nodeLabel,
            Long nodeOrder, String ideaModule,String canSendMessage,String tips,String btIdea,String canphoneopt,String hasDb) {
        super();
        this.moduleCode = moduleCode;
        this.hasIdea = hasIdea;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.assignEngineRole = assignEngineRole;
        this.engineRoleCode = engineRoleCode;
        this.engineRoleName = engineRoleName;
        this.engineRoleFilter = engineRoleFilter;
        this.riskInfo = riskInfo;
        this.isInUse = isInUse;
        this.hasOrgRole = hasOrgRole;
        this.zbOrgRoleCode = zbOrgRoleCode;
        this.xbOrgRoleCode = xbOrgRoleCode;
        this.xbOrgRoleName = xbOrgRoleName;
        this.xbOrgRoleFilter = xbOrgRoleFilter;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
        this.nodeName = nodeName;
        this.lastUpdateTime = lastUpdateTime;
        this.isTeamRoleCheck = isTeamRoleCheck;
        this.isQuickContent = isQuickContent;
        this.quickContentResult = quickContentResult;
        this.hasZbUser = hasZbUser;
        this.zbUserRoleCode = zbUserRoleCode;
        this.zbUserLabel = zbUserLabel;
        this.zbUserFilter = zbUserFilter;
        this.hasXbUser = hasXbUser;
        this.xbUserRoleCode = xbUserRoleCode;
        this.xbUserLabel = xbUserLabel;
        this.xbUserFilter = xbUserFilter;
        this.bookMarkType = bookMarkType;
        this.hasSq = hasSq;
        this.hasFw = hasFw;
        this.hasQb = hasQb;
        this.hasJZ = hasJZ;
        this.isShowInNode = isShowInNode;
        this.nodeLabel = nodeLabel;
        this.nodeOrder = nodeOrder;
        this.ideaModule = ideaModule;
        this.canSendMessage=canSendMessage;
        this.tips=tips;
        this.btIdea=btIdea;
        this.hasDb=hasDb;
    }

    public GeneralModuleParam(String moduleCode, String hasIdea,
            String ideaLabel, String ideaCatalog, String ideaContent,
            String hasAttention, String attentionLabel, String attentionFilter,
            String hasStuff, String stuffGroupId, String hasDocument,
            String documentLabel, String documentType,
            String documentTemplateIds, String documentTemplateNames,
            String canDefer, String canRollback, String canClose, Long riskId,
            String assignTeamRole, String teamRoleCode, String teamRoleName,
            String teamRoleFilter, String assignEngineRole,
            String engineRoleCode, String engineRoleName,
            String engineRoleFilter, RiskInfo riskInfo, String isInUse,
            String hasOrgRole, String zbOrgRoleCode, String xbOrgRoleCode,
            String xbOrgRoleName, String xbOrgRoleFilter, String docReadOnly,
            String hasPreIdea, String nodeName, Date lastUpdateTime,
            String isTeamRoleCheck, String isQuickContent,
            String quickContentResult, String hasZbUser, String zbUserRoleCode,
            String zbUserLabel, String zbUserFilter, String hasXbUser,
            String xbUserRoleCode, String xbUserLabel, String xbUserFilter,
            String bookMarkType, String powerId, String isShowInNode,String nodeLabel,Long nodeOrder,String canphoneopt) {
        super();
        this.moduleCode = moduleCode;
        this.hasIdea = hasIdea;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.assignEngineRole = assignEngineRole;
        this.engineRoleCode = engineRoleCode;
        this.engineRoleName = engineRoleName;
        this.engineRoleFilter = engineRoleFilter;
        this.riskInfo = riskInfo;
        this.isInUse = isInUse;
        this.hasOrgRole = hasOrgRole;
        this.zbOrgRoleCode = zbOrgRoleCode;
        this.xbOrgRoleCode = xbOrgRoleCode;
        this.xbOrgRoleName = xbOrgRoleName;
        this.xbOrgRoleFilter = xbOrgRoleFilter;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
        this.nodeName = nodeName;
        this.lastUpdateTime = lastUpdateTime;
        this.isTeamRoleCheck = isTeamRoleCheck;
        this.isQuickContent = isQuickContent;
        this.quickContentResult = quickContentResult;
        this.hasZbUser = hasZbUser;
        this.zbUserRoleCode = zbUserRoleCode;
        this.zbUserLabel = zbUserLabel;
        this.zbUserFilter = zbUserFilter;
        this.hasXbUser = hasXbUser;
        this.xbUserRoleCode = xbUserRoleCode;
        this.xbUserLabel = xbUserLabel;
        this.xbUserFilter = xbUserFilter;
        this.bookMarkType = bookMarkType;
        this.powerId = powerId;
        
        this.isShowInNode=isShowInNode;
        this.nodeLabel=nodeLabel;
        this.nodeOrder=nodeOrder;
        this.ideaModule=ideaModule;
        this.canSendMessage=canSendMessage;
        this.tips=tips;
        this.btIdea=btIdea;
                
    }

    public GeneralModuleParam(String moduleCode, String hasIdea,
            String ideaLabel, String ideaCatalog, String ideaContent,
            String hasAttention, String attentionLabel, String attentionFilter,
            String hasStuff, String stuffGroupId, String hasDocument,
            String documentLabel, String documentType,
            String documentTemplateIds, String documentTemplateNames,
            String canDefer, String canRollback, String canClose, Long riskId,
            String assignTeamRole, String teamRoleCode, String teamRoleName,
            String teamRoleFilter, String assignEngineRole,
            String engineRoleCode, String engineRoleName,
            String engineRoleFilter, RiskInfo riskInfo, String isInUse,
            String hasOrgRole, String zbOrgRoleCode, String xbOrgRoleCode,
            String xbOrgRoleName, String xbOrgRoleFilter, String docReadOnly,
            String hasPreIdea, String nodeName, Date lastUpdateTime,
            String isTeamRoleCheck, String isQuickContent,
            String quickContentResult, String powerId,String canphoneopt) {
        super();
        this.moduleCode = moduleCode;
        this.hasIdea = hasIdea;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.assignEngineRole = assignEngineRole;
        this.engineRoleCode = engineRoleCode;
        this.engineRoleName = engineRoleName;
        this.engineRoleFilter = engineRoleFilter;
        this.riskInfo = riskInfo;
        this.isInUse = isInUse;
        this.hasOrgRole = hasOrgRole;
        this.zbOrgRoleCode = zbOrgRoleCode;
        this.xbOrgRoleCode = xbOrgRoleCode;
        this.xbOrgRoleName = xbOrgRoleName;
        this.xbOrgRoleFilter = xbOrgRoleFilter;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
        this.nodeName = nodeName;
        this.lastUpdateTime = lastUpdateTime;
        this.isTeamRoleCheck = isTeamRoleCheck;
        this.isQuickContent = isQuickContent;
        this.quickContentResult = quickContentResult;
        this.powerId = powerId;
    }

    public String getIsQuickContent() {
        return isQuickContent;
    }

    public void setIsQuickContent(String isQuickContent) {
        this.isQuickContent = isQuickContent;
    }

    public String getQuickContentResult() {
        return quickContentResult;
    }

    public void setQuickContentResult(String quickContentResult) {
        this.quickContentResult = quickContentResult;
    }

    public String getIsTeamRoleCheck() {
        return isTeamRoleCheck;
    }

    public void setIsTeamRoleCheck(String isTeamRoleCheck) {
        this.isTeamRoleCheck = isTeamRoleCheck;
    }

    public String getIsInUse() {
        if (StringUtils.isBlank(this.isInUse)) {
            this.isInUse = "T";
        }
        return isInUse;
    }

    public void setIsInUse(String isInUse) {
        this.isInUse = isInUse;
    }

    public String getHasJZ() {
        if (StringUtils.isBlank(this.hasJZ)) {
            this.hasJZ = "F";
        }
        return hasJZ;
    }

    public void setHasJZ(String hasJZ) {
        this.hasJZ = hasJZ;
    }

    // 页面操作对象，非配置属性
    private String powerId;

    public RiskInfo getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(RiskInfo riskInfo) {
        this.riskInfo = riskInfo;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    // Constructors
    /** default constructor */
    public GeneralModuleParam() {
    }

    /** minimal constructor */
    public GeneralModuleParam(String moduleCode) {

        this.moduleCode = moduleCode;

    }

    public GeneralModuleParam(String moduleCode, String ideaLabel,
            String ideaCatalog, String ideaContent, String hasAttention,
            String attentionLabel, String attentionFilter, String hasStuff,
            String stuffGroupId, String hasDocument, String documentLabel,
            String documentType, String documentTemplateIds,
            String documentTemplateNames, String canDefer, String canRollback,
            String canClose, Long riskId, String assignTeamRole,
            String teamRoleCode, String teamRoleName, String teamRoleFilter,
            String isInUse, String nodeName, String powerId, String hasOrgRole,
            String docReadOnly, String hasPreIdea,String caneditopt,String canphoneopt) {
        super();
        this.moduleCode = moduleCode;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.isInUse = isInUse;
        this.nodeName = nodeName;
        this.powerId = powerId;
        this.hasOrgRole = hasOrgRole;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
    }

    /** full constructor */
    public GeneralModuleParam(String moduleCode, String ideaLabel,
            String ideaCatalog, String ideaContent, String hasAttention,
            String attentionLabel, String attentionFilter, String hasStuff,
            String stuffGroupId, String hasDocument, String documentLabel,
            String documentType, String documentTemplateIds,
            String documentTemplateNames, String canDefer, String canRollback,
            String canClose, Long riskId, String assignTeamRole,
            String teamRoleCode, String teamRoleName, String teamRoleFilter,
            String hasOrgRole, String docReadOnly, String hasPreIdea,String caneditopt,String canphoneopt) {

        this.moduleCode = moduleCode;

        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.hasOrgRole = hasOrgRole;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    // Property accessors

    public GeneralModuleParam(String moduleCode, String hasIdea,
            String ideaLabel, String ideaCatalog, String ideaContent,
            String hasAttention, String attentionLabel, String attentionFilter,
            String hasStuff, String stuffGroupId, String hasDocument,
            String documentLabel, String documentType,
            String documentTemplateIds, String documentTemplateNames,
            String canDefer, String canRollback, String canClose, Long riskId,
            String assignTeamRole, String teamRoleCode, String teamRoleName,
            String teamRoleFilter, RiskInfo riskInfo, String isInUse,
            String powerId, String hasOrgRole, String docReadOnly, String hasPreIdea,String caneditopt,String canphoneopt) {
        super();
        this.hasIdea = hasIdea;
        this.moduleCode = moduleCode;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.riskInfo = riskInfo;
        this.isInUse = isInUse;
        this.powerId = powerId;
        this.hasOrgRole = hasOrgRole;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
    }

    public GeneralModuleParam(String moduleCode, String hasIdea,
            String ideaLabel, String ideaCatalog, String ideaContent,
            String hasAttention, String attentionLabel, String attentionFilter,
            String hasStuff, String stuffGroupId, String hasDocument,
            String documentLabel, String documentType,
            String documentTemplateIds, String documentTemplateNames,
            String canDefer, String canRollback, String canClose, Long riskId,
            String assignTeamRole, String teamRoleCode, String teamRoleName,
            String teamRoleFilter, String assignEngineRole,
            String engineRoleCode, String engineRoleName,
            String engineRoleFilter, RiskInfo riskInfo, String isInUse,
            String hasOrgRole, String zbOrgRoleCode, String xbOrgRoleCode,
            String xbOrgRoleName, String xbOrgRoleFilter, String docReadOnly,
            String hasPreIdea, String nodeName, Date lastUpdateTime,
            String isTeamRoleCheck, String isQuickContent,
            String quickContentResult, String hasZbUser, String zbUserRoleCode,
            String zbUserLabel, String zbUserFilter, String hasXbUser,String caneditopt,
            String xbUserRoleCode, String xbUserLabel, String xbUserFilter,String canphoneopt,
            String bookMarkType, String hasSq, String hasFw,String hasQb, String powerId, String isShowInNode,String nodeLabel,Long nodeOrder) {
        super();
        this.moduleCode = moduleCode;
        this.hasIdea = hasIdea;
        this.ideaLabel = ideaLabel;
        this.ideaCatalog = ideaCatalog;
        this.ideaContent = ideaContent;
        this.hasAttention = hasAttention;
        this.attentionLabel = attentionLabel;
        this.attentionFilter = attentionFilter;
        this.hasStuff = hasStuff;
        this.stuffGroupId = stuffGroupId;
        this.hasDocument = hasDocument;
        this.documentLabel = documentLabel;
        this.documentType = documentType;
        this.documentTemplateIds = documentTemplateIds;
        this.documentTemplateNames = documentTemplateNames;
        this.canDefer = canDefer;
        this.caneditopt = caneditopt;
        this.canphoneopt = canphoneopt;
        this.canRollback = canRollback;
        this.canClose = canClose;
        this.riskId = riskId;
        this.assignTeamRole = assignTeamRole;
        this.teamRoleCode = teamRoleCode;
        this.teamRoleName = teamRoleName;
        this.teamRoleFilter = teamRoleFilter;
        this.assignEngineRole = assignEngineRole;
        this.engineRoleCode = engineRoleCode;
        this.engineRoleName = engineRoleName;
        this.engineRoleFilter = engineRoleFilter;
        this.riskInfo = riskInfo;
        this.isInUse = isInUse;
        this.hasOrgRole = hasOrgRole;
        this.zbOrgRoleCode = zbOrgRoleCode;
        this.xbOrgRoleCode = xbOrgRoleCode;
        this.xbOrgRoleName = xbOrgRoleName;
        this.xbOrgRoleFilter = xbOrgRoleFilter;
        this.docReadOnly = docReadOnly;
        this.hasPreIdea = hasPreIdea;
        this.nodeName = nodeName;
        this.lastUpdateTime = lastUpdateTime;
        this.isTeamRoleCheck = isTeamRoleCheck;
        this.isQuickContent = isQuickContent;
        this.quickContentResult = quickContentResult;
        this.hasZbUser = hasZbUser;
        this.zbUserRoleCode = zbUserRoleCode;
        this.zbUserLabel = zbUserLabel;
        this.zbUserFilter = zbUserFilter;
        this.hasXbUser = hasXbUser;
        this.xbUserRoleCode = xbUserRoleCode;
        this.xbUserLabel = xbUserLabel;
        this.xbUserFilter = xbUserFilter;
        this.bookMarkType = bookMarkType;
        this.hasSq = hasSq;
        this.hasFw = hasFw;
        
        this.hasQb=hasQb;
        
        this.powerId = powerId;
        
        this.isShowInNode=isShowInNode;
        this.nodeLabel=nodeLabel;
        this.nodeOrder=nodeOrder;
        this.ideaModule=ideaModule;
        this.canSendMessage=canSendMessage;
        this.tips=tips;
        this.btIdea=btIdea;       
                
    }

    public String getIdeaLabel() {
        return this.ideaLabel;
    }

    public void setIdeaLabel(String ideaLabel) {
        this.ideaLabel = ideaLabel;
    }

    public String getIdeaCatalog() {
        return this.ideaCatalog;
    }

    public void setIdeaCatalog(String ideaCatalog) {
        this.ideaCatalog = ideaCatalog;
    }

    public String getIdeaContent() {
        return this.ideaContent;
    }

    public void setIdeaContent(String ideaContent) {
        this.ideaContent = ideaContent;
    }

    public String getHasAttention() {
        if (StringUtils.isBlank(this.hasAttention)) {
            this.hasAttention = "F";
        }
        return this.hasAttention;
    }

    public void setHasAttention(String hasAttention) {
        this.hasAttention = hasAttention;
    }

    public String getAttentionLabel() {
        return this.attentionLabel;
    }

    public void setAttentionLabel(String attentionLabel) {
        this.attentionLabel = attentionLabel;
    }

    public String getAttentionFilter() {
        return this.attentionFilter;
    }

    public void setAttentionFilter(String attentionFilter) {
        this.attentionFilter = attentionFilter;
    }

    public String getHasStuff() {
        if (StringUtils.isBlank(this.hasStuff)) {
            this.hasStuff = "F";
        }
        return this.hasStuff;
    }

    public void setHasStuff(String hasStuff) {
        this.hasStuff = hasStuff;
    }

    public String getStuffGroupId() {
        return this.stuffGroupId;
    }

    public void setStuffGroupId(String stuffGroupId) {
        this.stuffGroupId = stuffGroupId;
    }

    public String getHasDocument() {
        if (StringUtils.isBlank(this.hasDocument)) {
            this.hasDocument = "F";
        }
        return this.hasDocument;
    }

    public void setHasDocument(String hasDocument) {
        this.hasDocument = hasDocument;
    }

    public String getDocumentLabel() {
        return this.documentLabel;
    }

    public void setDocumentLabel(String documentLabel) {
        this.documentLabel = documentLabel;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentTemplateIds() {
        return this.documentTemplateIds;
    }

    public void setDocumentTemplateIds(String documentTemplateIds) {
        this.documentTemplateIds = documentTemplateIds;
    }

    public String getCanDefer() {
        if (StringUtils.isBlank(this.canDefer)) {
            this.canDefer = "F";
        }
        return this.canDefer;
    }

    public void setCanDefer(String canDefer) {
        this.canDefer = canDefer;
    }
    

    public String getCaneditopt() {
        if (StringUtils.isBlank(this.caneditopt)) {
            this.caneditopt = "F";
        }
        return this.caneditopt;
    }

    public void setCaneditopt(String caneditopt) {
        this.caneditopt = caneditopt;
    }

    public String getCanphoneopt() {
        if (StringUtils.isBlank(this.canphoneopt)) {
            this.canphoneopt = "T";
        }
        return canphoneopt;
    }

    public void setCanphoneopt(String canphoneopt) {
        this.canphoneopt = canphoneopt;
    }

    public String getCanRollback() {
        if (StringUtils.isBlank(this.canRollback)) {
            this.canRollback = "F";
        }
        return this.canRollback;
    }

    public void setCanRollback(String canRollback) {
        this.canRollback = canRollback;
    }

    public String getCanClose() {
        if (StringUtils.isBlank(this.canClose)) {
            this.canClose = "F";
        }
        return this.canClose;
    }

    public void setCanClose(String canClose) {
        this.canClose = canClose;
    }

    public Long getRiskId() {
        return this.riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getAssignTeamRole() {
        if (StringUtils.isBlank(this.assignTeamRole)) {
            this.assignTeamRole = "F";
        }
        return this.assignTeamRole;
    }

    public void setAssignTeamRole(String assignTeamRole) {
        this.assignTeamRole = assignTeamRole;
    }

    public String getTeamRoleCode() {
        return this.teamRoleCode;
    }

    public void setTeamRoleCode(String teamRoleCode) {
        this.teamRoleCode = teamRoleCode;
    }

    public String getTeamRoleName() {
        return this.teamRoleName;
    }

    public void setTeamRoleName(String teamRoleName) {
        this.teamRoleName = teamRoleName;
    }

    public String getTeamRoleFilter() {
        return this.teamRoleFilter;
    }

    public void setTeamRoleFilter(String teamRoleFilter) {
        this.teamRoleFilter = teamRoleFilter;
    }

    public void copy(GeneralModuleParam other) {

        this.setModuleCode(other.getModuleCode());

        this.hasIdea = other.getHasIdea();
        this.ideaLabel = other.getIdeaLabel();
        this.ideaCatalog = other.getIdeaCatalog();
        this.ideaContent = other.getIdeaContent();
        this.hasAttention = other.getHasAttention();
        this.attentionLabel = other.getAttentionLabel();
        this.attentionFilter = other.getAttentionFilter();
        this.hasStuff = other.getHasStuff();
        this.stuffGroupId = other.getStuffGroupId();
        this.hasDocument = other.getHasDocument();
        this.documentLabel = other.getDocumentLabel();
        this.documentType = other.getDocumentType();
        this.documentTemplateIds = other.getDocumentTemplateIds();
        this.documentTemplateNames = other.getDocumentTemplateNames();
        this.canDefer = other.getCanDefer();
        this.caneditopt = other.getCaneditopt();
        this.canphoneopt = other.getCanphoneopt();
        this.canRollback = other.getCanRollback();
        this.canClose = other.getCanClose();
        this.riskId = other.getRiskId();
        this.assignTeamRole = other.getAssignTeamRole();
        this.teamRoleCode = other.getTeamRoleCode();
        this.teamRoleName = other.getTeamRoleName();
        this.teamRoleFilter = other.getTeamRoleFilter();
        this.nodeName = other.getNodeName();
        this.lastUpdateTime = other.getLastUpdateTime();
        this.hasOrgRole = other.getHasOrgRole();
        this.zbOrgRoleCode = other.getZbOrgRoleCode();
        this.xbOrgRoleCode = other.getXbOrgRoleCode();
        this.xbOrgRoleName = other.getXbOrgRoleName();
        this.xbOrgRoleFilter = other.getXbOrgRoleFilter();
        
        this.docReadOnly = other.getDocReadOnly();
        this.hasPreIdea = other.getHasPreIdea();
        this.assignEngineRole = other.getAssignEngineRole();
        this.engineRoleCode = other.getEngineRoleCode();
        this.engineRoleName = other.getEngineRoleName();
        this.engineRoleFilter = other.getEngineRoleFilter();
        this.isQuickContent = other.getIsQuickContent();
        this.quickContentResult = other.getQuickContentResult();
        
        this.hasZbUser = other.getHasZbUser();
        this.zbUserRoleCode = other.getZbUserRoleCode();
        this.zbUserLabel = other.getZbUserLabel();
        this.zbUserFilter = other.getZbUserFilter();
        
        this.hasXbUser = other.getHasXbUser();
        this.xbUserRoleCode = other.getXbUserRoleCode();
        this.xbUserLabel = other.getXbUserLabel();
        this.xbUserFilter = other.getXbUserFilter();
        this.bookMarkType = other.getBookMarkType();
        this.hasSq=other.getHasSq();
        this.hasFw=other.getHasFw();
        this.hasQb=other.getHasQb();
        this.isShowInNode=other.getIsShowInNode();
        this.nodeLabel=other.getNodeLabel();
        this.nodeOrder=other.getNodeOrder();
        this.ideaModule=other.getIdeaModule();
        this.hasJZ=other.getHasJZ();
        this.canSendMessage=other.getCanSendMessage();
        this.tips=other.getTips();
        this.btIdea=other.getBtIdea();
        this.hasDb=other.getHasDb();
}

    public void copyNotNullProperty(GeneralModuleParam other) {

        if (other.getModuleCode() != null)
            this.setModuleCode(other.getModuleCode());

        if (other.getHasIdea() != null)
            this.hasIdea = other.getHasIdea();
        if (other.getIdeaLabel() != null)
            this.ideaLabel = other.getIdeaLabel();
        if (other.getIdeaCatalog() != null)
            this.ideaCatalog = other.getIdeaCatalog();
        if (other.getIdeaContent() != null)
            this.ideaContent = other.getIdeaContent();
        if (other.getHasAttention() != null)
            this.hasAttention = other.getHasAttention();
        if (other.getAttentionLabel() != null)
            this.attentionLabel = other.getAttentionLabel();
        if (other.getAttentionFilter() != null)
            this.attentionFilter = other.getAttentionFilter();
        if (other.getHasStuff() != null)
            this.hasStuff = other.getHasStuff();
        if (other.getStuffGroupId() != null)
            this.stuffGroupId = other.getStuffGroupId();
        if (other.getHasDocument() != null)
            this.hasDocument = other.getHasDocument();
        if (other.getDocumentLabel() != null)
            this.documentLabel = other.getDocumentLabel();
        if (other.getDocumentType() != null)
            this.documentType = other.getDocumentType();
        if (other.getDocumentTemplateIds() != null)
            this.documentTemplateIds = other.getDocumentTemplateIds();
        if (other.getDocumentTemplateNames() != null) {
            this.documentTemplateNames = other.getDocumentTemplateNames();
        }
        if (other.getCanDefer() != null)
            this.canDefer = other.getCanDefer();
        if (other.getCaneditopt() !=null)
            this.caneditopt = other.getCaneditopt();
        if (other.getCanphoneopt() !=null)
            this.canphoneopt = other.getCanphoneopt();
        if (other.getCanRollback() != null)
            this.canRollback = other.getCanRollback();
        if (other.getCanClose() != null)
            this.canClose = other.getCanClose();
        if (other.getRiskId() != null)
            this.riskId = other.getRiskId();
        if (other.getAssignTeamRole() != null)
            this.assignTeamRole = other.getAssignTeamRole();
        if (other.getTeamRoleCode() != null)
            this.teamRoleCode = other.getTeamRoleCode();
        if (other.getTeamRoleName() != null)
            this.teamRoleName = other.getTeamRoleName();
        if (other.getTeamRoleFilter() != null)
            this.teamRoleFilter = other.getTeamRoleFilter();
        if (other.getNodeName() != null)
            this.nodeName = other.getNodeName();
        if (other.getLastUpdateTime() != null)
            this.lastUpdateTime = other.getLastUpdateTime();
        if (other.getHasOrgRole() != null)
            this.hasOrgRole = other.getHasOrgRole();
        if(other.getZbOrgRoleCode() != null)
            this.zbOrgRoleCode = other.getZbOrgRoleCode();
        if (other.getXbOrgRoleCode() != null)
            this.xbOrgRoleCode = other.getXbOrgRoleCode();
        if (other.getXbOrgRoleName() != null)
            this.xbOrgRoleName = other.getXbOrgRoleName();
        if (other.getXbOrgRoleFilter() != null)
            this.xbOrgRoleFilter = other.getXbOrgRoleFilter();
        
        if (other.getDocReadOnly() != null)
            this.docReadOnly = other.getDocReadOnly();
        if (other.getHasPreIdea() != null)
            this.hasPreIdea = other.getHasPreIdea();
        
        if (other.getAssignEngineRole() != null)
            this.assignEngineRole = other.getAssignEngineRole();
        if (other.getEngineRoleCode() != null)
            this.engineRoleCode = other.getEngineRoleCode();
        if (other.getEngineRoleName() != null)
            this.engineRoleName = other.getEngineRoleName();
        if (other.getEngineRoleFilter() != null)
            this.engineRoleFilter = other.getEngineRoleFilter();
        if (other.getIsQuickContent() != null)
            this.isQuickContent = other.getIsQuickContent();
        if (other.getQuickContentResult() != null)
            this.quickContentResult = other.getQuickContentResult();
        if(other.getHasZbUser() != null)
            this.hasZbUser = other.getHasZbUser();
        if(other.getZbOrgRoleCode() != null )
            this.zbUserRoleCode = other.getZbUserRoleCode();
        if(other.getZbUserLabel() != null)
            this.zbUserLabel = other.getZbUserLabel();
        if(other.getZbUserFilter() != null)
            this.zbUserFilter = other.getZbUserFilter();
        
        if(other.getHasXbUser() != null)
            this.hasXbUser = other.getHasXbUser();
        if(other.getXbOrgRoleCode() != null )
            this.xbUserRoleCode = other.getXbUserRoleCode();
        if(other.getXbUserLabel() != null)
            this.xbUserLabel = other.getXbUserLabel();
        if(other.getXbUserFilter() != null)
            this.xbUserFilter = other.getXbUserFilter();
        if(other.getBookMarkType() != null)
            this.bookMarkType = other.getBookMarkType();
        if(other.getHasSq() != null)
            this.hasSq = other.getHasSq();
        if(other.getHasFw()!= null)
            this.hasFw = other.getHasFw();
        
        if(other.getHasQb()!= null)
            this.hasQb = other.getHasQb();
        
        
        if(other.getIsShowInNode()!=null)
            this.isShowInNode=other.getIsShowInNode();
        if(other.getNodeLabel()!=null)
            this.nodeLabel=other.getNodeLabel();
        if(other.getNodeOrder()!=null)
            this.nodeOrder=other.getNodeOrder();
        if(other.getIdeaModule()!=null)
            this.ideaModule=other.getIdeaModule();
        if(other.getHasJZ()!=null)
        this.hasJZ=other.getHasJZ();
        
        if(other.getCanSendMessage()!=null)
            this.canSendMessage=other.getCanSendMessage();
        if(other.getTips()!=null)
            this.tips=other.getTips();
        if(other.getBtIdea()!=null)
            this.btIdea=other.getBtIdea();
        
        if(other.getHasDb() !=null)
            this.hasDb=other.getHasDb();
    }

    public void clearProperties() {
        this.hasIdea = null;
        this.ideaLabel = null;
        this.ideaCatalog = null;
        this.ideaContent = null;
        this.hasAttention = null;
        this.attentionLabel = null;
        this.attentionFilter = null;
        this.hasStuff = null;
        this.stuffGroupId = null;
        this.hasDocument = null;
        this.documentLabel = null;
        this.documentType = null;
        this.documentTemplateIds = null;
        this.documentTemplateNames = null;
        this.canDefer = null;
        this.caneditopt = null;
        this.canphoneopt = null;
        this.canRollback = null;
        this.canClose = null;
        this.riskId = null;
        this.assignTeamRole = null;
        this.teamRoleCode = null;
        this.teamRoleName = null;
        this.teamRoleFilter = null;
        
        this.assignEngineRole = null;
        this.engineRoleCode = null;
        this.engineRoleFilter = null;
        this.engineRoleName = null;
        
        this.nodeName = null;
        this.lastUpdateTime = null;
        this.hasOrgRole = null;
        this.zbOrgRoleCode = null;
        this.xbOrgRoleCode = null;
        this.xbOrgRoleName = null;
        this.xbOrgRoleFilter = null;
        this.docReadOnly = null;
        this.hasPreIdea = null;
        this.isQuickContent = null;
        this.quickContentResult = null;
        
        this.hasZbUser = null;
        this.zbUserRoleCode = null;
        this.zbUserLabel = null;
        this.zbUserFilter = null;
        
        this.hasXbUser = null;
        this.xbUserRoleCode = null;
        this.xbUserLabel = null;
        this.xbUserFilter = null;
        this.bookMarkType = null;
        
        this.hasSq=null;
        this.hasFw=null;
        this.hasQb=null;
        this.isShowInNode=null;
        this.nodeLabel=null;
        this.nodeOrder=null;
        this.ideaModule=null;
        
        this.hasJZ=null;
        
        this.canSendMessage=null;
        this.tips=null;
        this.btIdea=null;
        
        this.hasDb=null;
    }

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

    public String getDocumentTemplateNames() {
        return documentTemplateNames;
    }

    public void setDocumentTemplateNames(String documentTemplateNames) {
        this.documentTemplateNames = documentTemplateNames;
    }

    public String getHasIdea() {
        if (StringUtils.isBlank(this.hasIdea)) {
            this.hasIdea = "T";
        }
        return hasIdea;
    }

    public void setHasIdea(String hasIdea) {
        this.hasIdea = hasIdea;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getHasOrgRole() {
        if (StringUtils.isBlank(this.hasOrgRole)) {
            this.hasOrgRole = "F";
        }
        return hasOrgRole;
    }

    public void setHasOrgRole(String hasOrgRole) {
        this.hasOrgRole = hasOrgRole;
    }

    public String getDocReadOnly() {
        if (StringUtils.isBlank(this.docReadOnly)) {
            this.docReadOnly = "F";
        }
        return docReadOnly;
    }

    public void setDocReadOnly(String docReadOnly) {
        this.docReadOnly = docReadOnly;
    }

    public String getHasPreIdea() {
        if (StringUtils.isBlank(this.hasPreIdea)) {
            this.hasPreIdea = "F";
        }
        return hasPreIdea;
    }

    public void setHasPreIdea(String hasPreIdea) {
        this.hasPreIdea = hasPreIdea;
    }

    public String getXbOrgRoleCode() {
        return xbOrgRoleCode;
    }

    public void setXbOrgRoleCode(String xbOrgRoleCode) {
        this.xbOrgRoleCode = xbOrgRoleCode;
    }

    public String getXbOrgRoleName() {
        return xbOrgRoleName;
    }

    public void setXbOrgRoleName(String xbOrgRoleName) {
        this.xbOrgRoleName = xbOrgRoleName;
    }

    public String getXbOrgRoleFilter() {
        return xbOrgRoleFilter;
    }

    public void setXbOrgRoleFilter(String xbOrgRoleFilter) {
        this.xbOrgRoleFilter = xbOrgRoleFilter;
    }

    public String getZbOrgRoleCode() {
        return zbOrgRoleCode;
    }

    public void setZbOrgRoleCode(String zbOrgRoleCode) {
        this.zbOrgRoleCode = zbOrgRoleCode;
    }

    public void setAssignEngineRole(String assignEngineRole) {
        this.assignEngineRole = assignEngineRole;
    }

    public String getAssignEngineRole() {
        if (StringUtils.isBlank(this.assignEngineRole)) {
            this.assignEngineRole = "F";
        }
        return this.assignEngineRole;
    }
    
    public String getEngineRoleCode() {
        return engineRoleCode;
    }

    public void setEngineRoleCode(String engineRoleCode) {
        this.engineRoleCode = engineRoleCode;
    }

    public String getEngineRoleName() {
        return engineRoleName;
    }

    public void setEngineRoleName(String engineRoleName) {
        this.engineRoleName = engineRoleName;
    }

    public String getEngineRoleFilter() {
        return engineRoleFilter;
    }

    public void setEngineRoleFilter(String engineRoleFilter) {
        this.engineRoleFilter = engineRoleFilter;
    }

    public String getZbUserRoleCode() {
       
        return zbUserRoleCode;
    }

    public void setZbUserRoleCode(String zbUserRoleCode) {
        this.zbUserRoleCode = zbUserRoleCode;
    }

    public String getZbUserLabel() {
        return zbUserLabel;
    }

    public void setZbUserLabel(String zbUserLabel) {
        this.zbUserLabel = zbUserLabel;
    }

    public String getZbUserFilter() {
        return zbUserFilter;
    }

    public void setZbUserFilter(String zbUserFilter) {
        this.zbUserFilter = zbUserFilter;
    }

    public String getHasZbUser() {
        
        if (StringUtils.isBlank(this.hasZbUser)) {
            this.hasZbUser = "F";
        }
        return this.hasZbUser;
    }

    public void setHasZbUser(String hasZbUser) {
        this.hasZbUser = hasZbUser;
    }

    public String getHasXbUser() {
        if (StringUtils.isBlank(this.hasXbUser)) {
            this.hasXbUser = "F";
        }
        return hasXbUser;
    }

    public void setHasXbUser(String hasXbUser) {
        this.hasXbUser = hasXbUser;
    }

    public String getXbUserRoleCode() {
        return xbUserRoleCode;
    }

    public void setXbUserRoleCode(String xbUserRoleCode) {
        this.xbUserRoleCode = xbUserRoleCode;
    }

    public String getXbUserLabel() {
        return xbUserLabel;
    }

    public void setXbUserLabel(String xbUserLabel) {
        this.xbUserLabel = xbUserLabel;
    }

    public String getXbUserFilter() {
        return xbUserFilter;
    }

    public String getHasSq() {
        if (StringUtils.isBlank(this.hasSq)) {
            this.hasSq = "F";
        }
        return hasSq;
    }

    public void setHasSq(String hasSq) {
        this.hasSq = hasSq;
    }

    public String getHasFw() {
        if (StringUtils.isBlank(this.hasFw)) {
            this.hasFw = "F";
        }
        return hasFw;
    }

    public void setHasFw(String hasFw) {
        this.hasFw = hasFw;
    }

    public void setXbUserFilter(String xbUserFilter) {
        this.xbUserFilter = xbUserFilter;
    }

    public String getIsShowInNode() {
        if (StringUtils.isBlank(this.isShowInNode)) {
            this.isShowInNode = "F";
        }
        return isShowInNode;
    }
    
    public String getHasQb() {
        if (StringUtils.isBlank(this.hasQb)) {
            this.hasQb = "F";
        }
        return hasQb;
    }

    public void setHasQb(String hasQb) {
        this.hasQb = hasQb;
    }
    public void setIsShowInNode(String isShowInNode) {
        this.isShowInNode = isShowInNode;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public Long getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Long nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public String getIdeaModule() {
        return ideaModule;
    }

    public void setIdeaModule(String ideaModule) {
        this.ideaModule = ideaModule;
    }

    
    public String getCanSendMessage() {
        if (StringUtils.isBlank(this.canSendMessage)) {
            this.canSendMessage = "F";
        }
        return canSendMessage;
    }

    public void setCanSendMessage(String canSendMessage) {
        this.canSendMessage = canSendMessage;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBtIdea() {
        return btIdea;
    }

    public void setBtIdea(String btIdea) {
        this.btIdea = btIdea;
    }

    public String getHasDb() {
        if (StringUtils.isBlank(this.hasDb)) {
            this.hasDb = "F";
        }
        return hasDb;
    }

    public void setHasDb(String hasDb) {
        this.hasDb = hasDb;
    }
    
    
    
}
