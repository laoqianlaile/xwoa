package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VSwSearch implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long taskId;

    private Long nodeInstId;
    private String unitCode;
    private String userCode;
    private String roleType;
    private String roleCode;
    private String optId;
    private Long flowInstId;
    private String flowOptName;
    private String flowOptTag;
    private String authDesc;
    private String nodeName;
    private String nodeType;
    private String nodeOptType;
    private String optName;
    private String methodName;
    private String optUrl;
    private String optMethod;
    private String optDesc;
    private String optCode;
    private String optParam;
    private String inststate;
    private Date nodeCreateTime;
    private Long expireOptSign;
    private String expireOpt;
    private String grantor;
    private Long timeLimit;
    private String lastUpdateUser;
    private Date lastUpdateTime;
    private String flowPhase;
    private String djId;
    private String transaffairname;
    private String bizstate;
    private String biztype;
    private String orgcode;
    private String orgname;
    private String headunitcode;
    private String headusercode;
    private String content;
    private String powerid;
    private String powername;
    private String solvestatus;
    private Date solvetime;
    private String solvenote;
    private String createuser;
    private Date createdate;
    private String riskType;
    private String sendArchiveNo;
    private String acceptArchiveNo;
    private String riskDesc;
    private String riskResult;
    private String transAffairNo;
    private String transAffairQueryKey;
    private String nodeCode;
    private String flowCode;
    private String incomeDocNo;
    private String incomeDocTitle;
    private String incomeDeptName;
    private Date incomeDate;
    private String secretDegree;
    private String transcontent;
    private Date transdate;

    // Constructors
    /** default constructor */
    public VSwSearch() {
    }

    /** minimal constructor */
    public VSwSearch(Long taskId) {

        this.taskId = taskId;

    }

    /** full constructor */
    public VSwSearch(Long taskId, Long nodeInstId, String unitCode,
            String userCode, String roleType, String roleCode, String optId,
            Long flowInstId, String flowOptName, String flowOptTag,
            String authDesc, String nodeName, String nodeType,
            String nodeOptType, String optName, String methodName,
            String optUrl, String optMethod, String optDesc, String optCode,
            String optParam, String inststate, Date nodeCreateTime,
            Long expireOptSign, String expireOpt, String grantor,
            Long timeLimit, String lastUpdateUser, Date lastUpdateTime,
            String flowPhase, String djId, String transaffairname,
            String bizstate, String biztype, String orgcode, String orgname,
            String headunitcode, String headusercode, String content,
            String powerid, String powername, String solvestatus,
            Date solvetime, String solvenote, String createuser,
            Date createdate, String riskType, String sendArchiveNo,
            String acceptArchiveNo, String riskDesc, String riskResult,
            String transAffairNo, String transAffairQueryKey, String nodeCode,
            String flowCode, String incomeDocNo, String incomeDocTitle,
            String incomeDeptName, Date incomeDate, String secretDegree,
            String transcontent, Date transdate) {

        this.taskId = taskId;

        this.nodeInstId = nodeInstId;
        this.unitCode = unitCode;
        this.userCode = userCode;
        this.roleType = roleType;
        this.roleCode = roleCode;
        this.optId = optId;
        this.flowInstId = flowInstId;
        this.flowOptName = flowOptName;
        this.flowOptTag = flowOptTag;
        this.authDesc = authDesc;
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.nodeOptType = nodeOptType;
        this.optName = optName;
        this.methodName = methodName;
        this.optUrl = optUrl;
        this.optMethod = optMethod;
        this.optDesc = optDesc;
        this.optCode = optCode;
        this.optParam = optParam;
        this.inststate = inststate;
        this.nodeCreateTime = nodeCreateTime;
        this.expireOptSign = expireOptSign;
        this.expireOpt = expireOpt;
        this.grantor = grantor;
        this.timeLimit = timeLimit;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateTime = lastUpdateTime;
        this.flowPhase = flowPhase;
        this.djId = djId;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.headunitcode = headunitcode;
        this.headusercode = headusercode;
        this.content = content;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createuser = createuser;
        this.createdate = createdate;
        this.riskType = riskType;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.riskDesc = riskDesc;
        this.riskResult = riskResult;
        this.transAffairNo = transAffairNo;
        this.transAffairQueryKey = transAffairQueryKey;
        this.nodeCode = nodeCode;
        this.flowCode = flowCode;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptName = incomeDeptName;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.transcontent = transcontent;
        this.transdate = transdate;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    // Property accessors

    public Long getNodeInstId() {
        return this.nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getOptId() {
        return this.optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public Long getFlowInstId() {
        return this.flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getFlowOptName() {
        return this.flowOptName;
    }

    public void setFlowOptName(String flowOptName) {
        this.flowOptName = flowOptName;
    }

    public String getFlowOptTag() {
        return this.flowOptTag;
    }

    public void setFlowOptTag(String flowOptTag) {
        this.flowOptTag = flowOptTag;
    }

    public String getAuthDesc() {
        return this.authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public String getOptName() {
        return this.optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOptUrl() {
        return this.optUrl;
    }

    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    public String getOptMethod() {
        return this.optMethod;
    }

    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }

    public String getOptDesc() {
        return this.optDesc;
    }

    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }

    public String getOptCode() {
        return this.optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptParam() {
        return this.optParam;
    }

    public void setOptParam(String optParam) {
        this.optParam = optParam;
    }

    public String getInststate() {
        return this.inststate;
    }

    public void setInststate(String inststate) {
        this.inststate = inststate;
    }

    public Date getNodeCreateTime() {
        return this.nodeCreateTime;
    }

    public void setNodeCreateTime(Date nodeCreateTime) {
        this.nodeCreateTime = nodeCreateTime;
    }

    public Long getExpireOptSign() {
        return this.expireOptSign;
    }

    public void setExpireOptSign(Long expireOptSign) {
        this.expireOptSign = expireOptSign;
    }

    public String getExpireOpt() {
        return this.expireOpt;
    }

    public void setExpireOpt(String expireOpt) {
        this.expireOpt = expireOpt;
    }

    public String getGrantor() {
        return this.grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public Long getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getLastUpdateUser() {
        return this.lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFlowPhase() {
        return this.flowPhase;
    }

    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getBizstate() {
        return this.bizstate;
    }

    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }

    public String getBiztype() {
        return this.biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public String getOrgcode() {
        return this.orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return this.orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getHeadunitcode() {
        return this.headunitcode;
    }

    public void setHeadunitcode(String headunitcode) {
        this.headunitcode = headunitcode;
    }

    public String getHeadusercode() {
        return this.headusercode;
    }

    public void setHeadusercode(String headusercode) {
        this.headusercode = headusercode;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPowerid() {
        return this.powerid;
    }

    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

    public String getPowername() {
        return this.powername;
    }

    public void setPowername(String powername) {
        this.powername = powername;
    }

    public String getSolvestatus() {
        return this.solvestatus;
    }

    public void setSolvestatus(String solvestatus) {
        this.solvestatus = solvestatus;
    }

    public Date getSolvetime() {
        return this.solvetime;
    }

    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }

    public String getSolvenote() {
        return this.solvenote;
    }

    public void setSolvenote(String solvenote) {
        this.solvenote = solvenote;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getRiskType() {
        return this.riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getSendArchiveNo() {
        return this.sendArchiveNo;
    }

    public void setSendArchiveNo(String sendArchiveNo) {
        this.sendArchiveNo = sendArchiveNo;
    }

    public String getAcceptArchiveNo() {
        return this.acceptArchiveNo;
    }

    public void setAcceptArchiveNo(String acceptArchiveNo) {
        this.acceptArchiveNo = acceptArchiveNo;
    }

    public String getRiskDesc() {
        return this.riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public String getRiskResult() {
        return this.riskResult;
    }

    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }

    public String getTransAffairNo() {
        return this.transAffairNo;
    }

    public void setTransAffairNo(String transAffairNo) {
        this.transAffairNo = transAffairNo;
    }

    public String getTransAffairQueryKey() {
        return this.transAffairQueryKey;
    }

    public void setTransAffairQueryKey(String transAffairQueryKey) {
        this.transAffairQueryKey = transAffairQueryKey;
    }

    public String getNodeCode() {
        return this.nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        if (null != nodeCode && (0 == nodeCode.indexOf("P_") || 0 == nodeCode.indexOf("N_"))) {
            nodeCode = nodeCode.substring(2);
        }
        this.nodeCode = nodeCode;
    }

    public String getFlowCode() {
        return this.flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getIncomeDocNo() {
        return this.incomeDocNo;
    }

    public void setIncomeDocNo(String incomeDocNo) {
        this.incomeDocNo = incomeDocNo;
    }

    public String getIncomeDocTitle() {
        return this.incomeDocTitle;
    }

    public void setIncomeDocTitle(String incomeDocTitle) {
        this.incomeDocTitle = incomeDocTitle;
    }

    public String getIncomeDeptName() {
        return this.incomeDeptName;
    }

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public Date getIncomeDate() {
        return this.incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getSecretDegree() {
        return this.secretDegree;
    }

    public void setSecretDegree(String secretDegree) {
        this.secretDegree = secretDegree;
    }

    public String getTranscontent() {
        return this.transcontent;
    }

    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }
    
    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public void copy(VSwSearch other) {

        this.setTaskId(other.getTaskId());

        this.nodeInstId = other.getNodeInstId();
        this.unitCode = other.getUnitCode();
        this.userCode = other.getUserCode();
        this.roleType = other.getRoleType();
        this.roleCode = other.getRoleCode();
        this.optId = other.getOptId();
        this.flowInstId = other.getFlowInstId();
        this.flowOptName = other.getFlowOptName();
        this.flowOptTag = other.getFlowOptTag();
        this.authDesc = other.getAuthDesc();
        this.nodeName = other.getNodeName();
        this.nodeType = other.getNodeType();
        this.nodeOptType = other.getNodeOptType();
        this.optName = other.getOptName();
        this.methodName = other.getMethodName();
        this.optUrl = other.getOptUrl();
        this.optMethod = other.getOptMethod();
        this.optDesc = other.getOptDesc();
        this.optCode = other.getOptCode();
        this.optParam = other.getOptParam();
        this.inststate = other.getInststate();
        this.nodeCreateTime = other.getNodeCreateTime();
        this.expireOptSign = other.getExpireOptSign();
        this.expireOpt = other.getExpireOpt();
        this.grantor = other.getGrantor();
        this.timeLimit = other.getTimeLimit();
        this.lastUpdateUser = other.getLastUpdateUser();
        this.lastUpdateTime = other.getLastUpdateTime();
        this.flowPhase = other.getFlowPhase();
        this.djId = other.getDjId();
        this.transaffairname = other.getTransaffairname();
        this.bizstate = other.getBizstate();
        this.biztype = other.getBiztype();
        this.orgcode = other.getOrgcode();
        this.orgname = other.getOrgname();
        this.headunitcode = other.getHeadunitcode();
        this.headusercode = other.getHeadusercode();
        this.content = other.getContent();
        this.powerid = other.getPowerid();
        this.powername = other.getPowername();
        this.solvestatus = other.getSolvestatus();
        this.solvetime = other.getSolvetime();
        this.solvenote = other.getSolvenote();
        this.createuser = other.getCreateuser();
        this.createdate = other.getCreatedate();
        this.riskType = other.getRiskType();
        this.sendArchiveNo = other.getSendArchiveNo();
        this.acceptArchiveNo = other.getAcceptArchiveNo();
        this.riskDesc = other.getRiskDesc();
        this.riskResult = other.getRiskResult();
        this.transAffairNo = other.getTransAffairNo();
        this.transAffairQueryKey = other.getTransAffairQueryKey();
        this.nodeCode = other.getNodeCode();
        this.flowCode = other.getFlowCode();
        this.incomeDocNo = other.getIncomeDocNo();
        this.incomeDocTitle = other.getIncomeDocTitle();
        this.incomeDeptName = other.getIncomeDeptName();
        this.incomeDate = other.getIncomeDate();
        this.secretDegree = other.getSecretDegree();
        this.transcontent = other.getTranscontent();
        this.transdate = other.getTransdate();
    }

    public void copyNotNullProperty(VSwSearch other) {

        if (other.getTaskId() != null)
            this.setTaskId(other.getTaskId());

        if (other.getNodeInstId() != null)
            this.nodeInstId = other.getNodeInstId();
        if (other.getUnitCode() != null)
            this.unitCode = other.getUnitCode();
        if (other.getUserCode() != null)
            this.userCode = other.getUserCode();
        if (other.getRoleType() != null)
            this.roleType = other.getRoleType();
        if (other.getRoleCode() != null)
            this.roleCode = other.getRoleCode();
        if (other.getOptId() != null)
            this.optId = other.getOptId();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getFlowOptName() != null)
            this.flowOptName = other.getFlowOptName();
        if (other.getFlowOptTag() != null)
            this.flowOptTag = other.getFlowOptTag();
        if (other.getAuthDesc() != null)
            this.authDesc = other.getAuthDesc();
        if (other.getNodeName() != null)
            this.nodeName = other.getNodeName();
        if (other.getNodeType() != null)
            this.nodeType = other.getNodeType();
        if (other.getNodeOptType() != null)
            this.nodeOptType = other.getNodeOptType();
        if (other.getOptName() != null)
            this.optName = other.getOptName();
        if (other.getMethodName() != null)
            this.methodName = other.getMethodName();
        if (other.getOptUrl() != null)
            this.optUrl = other.getOptUrl();
        if (other.getOptMethod() != null)
            this.optMethod = other.getOptMethod();
        if (other.getOptDesc() != null)
            this.optDesc = other.getOptDesc();
        if (other.getOptCode() != null)
            this.optCode = other.getOptCode();
        if (other.getOptParam() != null)
            this.optParam = other.getOptParam();
        if (other.getInststate() != null)
            this.inststate = other.getInststate();
        if (other.getNodeCreateTime() != null)
            this.nodeCreateTime = other.getNodeCreateTime();
        if (other.getExpireOptSign() != null)
            this.expireOptSign = other.getExpireOptSign();
        if (other.getExpireOpt() != null)
            this.expireOpt = other.getExpireOpt();
        if (other.getGrantor() != null)
            this.grantor = other.getGrantor();
        if (other.getTimeLimit() != null)
            this.timeLimit = other.getTimeLimit();
        if (other.getLastUpdateUser() != null)
            this.lastUpdateUser = other.getLastUpdateUser();
        if (other.getLastUpdateTime() != null)
            this.lastUpdateTime = other.getLastUpdateTime();
        if (other.getFlowPhase() != null)
            this.flowPhase = other.getFlowPhase();
        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getBiztype() != null)
            this.biztype = other.getBiztype();
        if (other.getOrgcode() != null)
            this.orgcode = other.getOrgcode();
        if (other.getOrgname() != null)
            this.orgname = other.getOrgname();
        if (other.getHeadunitcode() != null)
            this.headunitcode = other.getHeadunitcode();
        if (other.getHeadusercode() != null)
            this.headusercode = other.getHeadusercode();
        if (other.getContent() != null)
            this.content = other.getContent();
        if (other.getPowerid() != null)
            this.powerid = other.getPowerid();
        if (other.getPowername() != null)
            this.powername = other.getPowername();
        if (other.getSolvestatus() != null)
            this.solvestatus = other.getSolvestatus();
        if (other.getSolvetime() != null)
            this.solvetime = other.getSolvetime();
        if (other.getSolvenote() != null)
            this.solvenote = other.getSolvenote();
        if (other.getCreateuser() != null)
            this.createuser = other.getCreateuser();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getRiskType() != null)
            this.riskType = other.getRiskType();
        if (other.getSendArchiveNo() != null)
            this.sendArchiveNo = other.getSendArchiveNo();
        if (other.getAcceptArchiveNo() != null)
            this.acceptArchiveNo = other.getAcceptArchiveNo();
        if (other.getRiskDesc() != null)
            this.riskDesc = other.getRiskDesc();
        if (other.getRiskResult() != null)
            this.riskResult = other.getRiskResult();
        if (other.getTransAffairNo() != null)
            this.transAffairNo = other.getTransAffairNo();
        if (other.getTransAffairQueryKey() != null)
            this.transAffairQueryKey = other.getTransAffairQueryKey();
        if (other.getNodeCode() != null)
            this.nodeCode = other.getNodeCode();
        if (other.getFlowCode() != null)
            this.flowCode = other.getFlowCode();
        if (other.getIncomeDocNo() != null)
            this.incomeDocNo = other.getIncomeDocNo();
        if (other.getIncomeDocTitle() != null)
            this.incomeDocTitle = other.getIncomeDocTitle();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getIncomeDate() != null)
            this.incomeDate = other.getIncomeDate();
        if (other.getSecretDegree() != null)
            this.secretDegree = other.getSecretDegree();
        if (other.getTranscontent() != null)
            this.transcontent = other.getTranscontent();
        if (other.getTransdate() != null)
            this.transdate = other.getTransdate();
    }

    public void clearProperties() {

        this.nodeInstId = null;
        this.unitCode = null;
        this.userCode = null;
        this.roleType = null;
        this.roleCode = null;
        this.optId = null;
        this.flowInstId = null;
        this.flowOptName = null;
        this.flowOptTag = null;
        this.authDesc = null;
        this.nodeName = null;
        this.nodeType = null;
        this.nodeOptType = null;
        this.optName = null;
        this.methodName = null;
        this.optUrl = null;
        this.optMethod = null;
        this.optDesc = null;
        this.optCode = null;
        this.optParam = null;
        this.inststate = null;
        this.nodeCreateTime = null;
        this.expireOptSign = null;
        this.expireOpt = null;
        this.grantor = null;
        this.timeLimit = null;
        this.lastUpdateUser = null;
        this.lastUpdateTime = null;
        this.flowPhase = null;
        this.djId = null;
        this.transaffairname = null;
        this.bizstate = null;
        this.biztype = null;
        this.orgcode = null;
        this.orgname = null;
        this.headunitcode = null;
        this.headusercode = null;
        this.content = null;
        this.powerid = null;
        this.powername = null;
        this.solvestatus = null;
        this.solvetime = null;
        this.solvenote = null;
        this.createuser = null;
        this.createdate = null;
        this.riskType = null;
        this.sendArchiveNo = null;
        this.acceptArchiveNo = null;
        this.riskDesc = null;
        this.riskResult = null;
        this.transAffairNo = null;
        this.transAffairQueryKey = null;
        this.nodeCode = null;
        this.flowCode = null;
        this.incomeDocNo = null;
        this.incomeDocTitle = null;
        this.incomeDeptName = null;
        this.incomeDate = null;
        this.secretDegree = null;
        this.transcontent = null;
        this.transdate = null;
    }
}
