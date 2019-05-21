package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VStuffTransaffair implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String optId;
    private String flowCode;
    private Long flowInstId;
    private String transAffairNo;
    private String transaffairname;
    private String transAffairQueryKey;
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
    private String caseNo;
    private String sendArchiveNo;
    private String acceptArchiveNo;
    private String riskType;
    private String riskDesc;
    private String riskResult;
    private String isUpload;
    private String subItemType;
    private String incomeDocNo;
    private String incomeDeptType;
    private String incomeDeptName;
    private String zbUnitName;
    private String zbUnitShortName;
    private String hbUnitNames;
    private String hbUnitShortNames;
    private String docTitle;
    private String fwh;
    private String wjlx;
    

    // Constructors
    /** default constructor */
    public VStuffTransaffair() {
    }

    /** minimal constructor */
    public VStuffTransaffair(String djId) {

        this.djId = djId;

    }

    /** full constructor */
    public VStuffTransaffair(String djId, String optId,
            String flowCode, Long flowInstId, String transAffairNo,
            String transaffairname, String transAffairQueryKey,
            String bizstate, String biztype, String orgcode, String orgname,
            String headunitcode, String headusercode, String content,
            String powerid, String powername, String solvestatus,
            Date solvetime, String solvenote, String createuser,
            Date createdate, String caseNo, String sendArchiveNo,
            String acceptArchiveNo, String riskType, String riskDesc,
            String riskResult, String isUpload, 
            String subItemType, String incomeDocNo, String incomeDeptType,
            String incomeDeptName, String zbUnitName, String zbUnitShortName,
            String hbUnitNames, String hbUnitShortNames, String docTitle, 
            String fwh, String wjlx) {

        this.djId = djId;
        
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transAffairNo = transAffairNo;
        this.transaffairname = transaffairname;
        this.transAffairQueryKey = transAffairQueryKey;
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
        this.caseNo = caseNo;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.riskType = riskType;
        this.riskDesc = riskDesc;
        this.riskResult = riskResult;
        this.isUpload = isUpload;
        this.subItemType = subItemType;
        this.incomeDocNo = incomeDocNo;
        this.incomeDeptType = incomeDeptType;
        this.incomeDeptName = incomeDeptName;
        this.zbUnitName = zbUnitName;
        this.zbUnitShortName = zbUnitShortName;
        this.hbUnitNames = hbUnitNames;
        this.hbUnitShortNames = hbUnitShortNames;
        this.docTitle = docTitle;
        this.fwh = fwh;
        this.wjlx = wjlx;
                
    }

    // Property accessors

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getOptId() {
        return this.optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getFlowCode() {
        return this.flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getFlowInstId() {
        return this.flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getTransAffairNo() {
        return this.transAffairNo;
    }

    public void setTransAffairNo(String transAffairNo) {
        this.transAffairNo = transAffairNo;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getTransAffairQueryKey() {
        return this.transAffairQueryKey;
    }

    public void setTransAffairQueryKey(String transAffairQueryKey) {
        this.transAffairQueryKey = transAffairQueryKey;
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

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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

    public String getRiskType() {
        return this.riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
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

    public String getIsUpload() {
        return this.isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getSubItemType() {
        return this.subItemType;
    }

    public void setSubItemType(String subItemType) {
        this.subItemType = subItemType;
    }

    public String getIncomeDocNo() {
        return this.incomeDocNo;
    }

    public void setIncomeDocNo(String incomeDocNo) {
        this.incomeDocNo = incomeDocNo;
    }

    public String getIncomeDeptType() {
        return this.incomeDeptType;
    }

    public void setIncomeDeptType(String incomeDeptType) {
        this.incomeDeptType = incomeDeptType;
    }

    public String getIncomeDeptName() {
        return this.incomeDeptName;
    }

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public String getZbUnitName() {
        return this.zbUnitName;
    }

    public void setZbUnitName(String zbUnitName) {
        this.zbUnitName = zbUnitName;
    }

    public String getZbUnitShortName() {
        return this.zbUnitShortName;
    }

    public void setZbUnitShortName(String zbUnitShortName) {
        this.zbUnitShortName = zbUnitShortName;
    }

    public String getHbUnitNames() {
        return this.hbUnitNames;
    }

    public void setHbUnitNames(String hbUnitNames) {
        this.hbUnitNames = hbUnitNames;
    }

    public String getHbUnitShortNames() {
        return this.hbUnitShortNames;
    }

    public void setHbUnitShortNames(String hbUnitShortNames) {
        this.hbUnitShortNames = hbUnitShortNames;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getFwh() {
        return fwh;
    }

    public void setFwh(String fwh) {
        this.fwh = fwh;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public void copy(VUserTransaffair other) {

        this.setDjId(other.getDjId());

        this.optId = other.getOptId();
        this.flowCode = other.getFlowCode();
        this.flowInstId = other.getFlowInstId();
        this.transAffairNo = other.getTransAffairNo();
        this.transaffairname = other.getTransaffairname();
        this.transAffairQueryKey = other.getTransAffairQueryKey();
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
        this.caseNo = other.getCaseNo();
        this.sendArchiveNo = other.getSendArchiveNo();
        this.acceptArchiveNo = other.getAcceptArchiveNo();
        this.riskType = other.getRiskType();
        this.riskDesc = other.getRiskDesc();
        this.riskResult = other.getRiskResult();
        this.isUpload = other.getIsUpload();
        this.subItemType = other.getSubItemType();
        this.incomeDocNo = other.getIncomeDocNo();
        this.incomeDeptType = other.getIncomeDeptType();
        this.incomeDeptName = other.getIncomeDeptName();
        this.zbUnitName = other.getZbUnitName();
        this.zbUnitShortName = other.getZbUnitShortName();
        this.hbUnitNames = other.getHbUnitNames();
        this.hbUnitShortNames = other.getHbUnitShortNames();
        this.docTitle = other.getDocTitle();
        this.fwh = other.getFwh();
        this.wjlx = other.getWjlx();
    }

    public void copyNotNullProperty(VUserTransaffair other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());
        
        if (other.getOptId() != null)
            this.optId = other.getOptId();
        if (other.getFlowCode() != null)
            this.flowCode = other.getFlowCode();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getTransAffairNo() != null)
            this.transAffairNo = other.getTransAffairNo();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getTransAffairQueryKey() != null)
            this.transAffairQueryKey = other.getTransAffairQueryKey();
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
        if (other.getCaseNo() != null)
            this.caseNo = other.getCaseNo();
        if (other.getSendArchiveNo() != null)
            this.sendArchiveNo = other.getSendArchiveNo();
        if (other.getAcceptArchiveNo() != null)
            this.acceptArchiveNo = other.getAcceptArchiveNo();
        if (other.getRiskType() != null)
            this.riskType = other.getRiskType();
        if (other.getRiskDesc() != null)
            this.riskDesc = other.getRiskDesc();
        if (other.getRiskResult() != null)
            this.riskResult = other.getRiskResult();
        if (other.getIsUpload() != null)
            this.isUpload = other.getIsUpload();
        if (other.getSubItemType() != null)
            this.subItemType = other.getSubItemType();
        if (other.getIncomeDocNo() != null)
            this.incomeDocNo = other.getIncomeDocNo();
        if (other.getIncomeDeptType() != null)
            this.incomeDeptType = other.getIncomeDeptType();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getZbUnitName() != null)
            this.zbUnitName = other.getZbUnitName();
        if (other.getZbUnitShortName() != null)
            this.zbUnitShortName = other.getZbUnitShortName();
        if (other.getHbUnitNames() != null)
            this.hbUnitNames = other.getHbUnitNames();
        if (other.getHbUnitShortNames() != null)
            this.hbUnitShortNames = other.getHbUnitShortNames();
        if (other.getDocTitle() != null)
            this.docTitle = other.getDocTitle();
        if (other.getFwh() != null)
            this.fwh = other.getFwh();
        if (other.getWjlx() != null)
            this.wjlx = other.getWjlx();
    }

    public void clearProperties() {

        this.optId = null;
        this.flowCode = null;
        this.flowInstId = null;
        this.transAffairNo = null;
        this.transaffairname = null;
        this.transAffairQueryKey = null;
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
        this.caseNo = null;
        this.sendArchiveNo = null;
        this.acceptArchiveNo = null;
        this.riskType = null;
        this.riskDesc = null;
        this.riskResult = null;
        this.isUpload = null;
        this.subItemType = null;
        this.incomeDocNo = null;
        this.incomeDeptType = null;
        this.incomeDeptName = null;
        this.zbUnitName = null;
        this.zbUnitShortName = null;
        this.hbUnitNames = null;
        this.hbUnitShortNames = null;
        this.docTitle = null;
        this.fwh = null;
        this.wjlx = null;

    }
}
