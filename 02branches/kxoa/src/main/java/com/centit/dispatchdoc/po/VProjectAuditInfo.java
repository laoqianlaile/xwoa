package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VProjectAuditInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private Long projectId;
    private String transaffairname;
    private String projectUnitName;
    private String projectName;
    private String registeredAddr;
    private String eiaApprovalType;
    private String buildLegal;
    private String industryField;
    private String buildContent;
    private Double totalInvestment;
    private String projectType;
    private String auditUnit;
    private String sendArchiveNo;
    private Date solvetime;
    private String solvetimeStr;
    private String contactName;
    private String contactPhone;
    private String powerid;
    private String projectAreaCode;
    private String bizstate;
    private String incomeDeptName;
    private String isUpload;
    private Date acceptDate;
    private String officialTotal;
    

    public VProjectAuditInfo(String djId, Long projectId,
            String transaffairname, String projectUnitName, String projectName,
            String registeredAddr, String eiaApprovalType, String buildLegal,
            String industryField, String buildContent, Double totalInvestment,
            String projectType, String auditUnit, String sendArchiveNo,
            Date solvetime, String solvetimeStr, String contactName,
            String contactPhone, String powerid, String projectAreaCode,
            String bizstate, String incomeDeptName, String isUpload,
            Date acceptDate, String officialTotal) {
        super();
        this.djId = djId;
        this.projectId = projectId;
        this.transaffairname = transaffairname;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.registeredAddr = registeredAddr;
        this.eiaApprovalType = eiaApprovalType;
        this.buildLegal = buildLegal;
        this.industryField = industryField;
        this.buildContent = buildContent;
        this.totalInvestment = totalInvestment;
        this.projectType = projectType;
        this.auditUnit = auditUnit;
        this.sendArchiveNo = sendArchiveNo;
        this.solvetime = solvetime;
        this.solvetimeStr = solvetimeStr;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.powerid = powerid;
        this.projectAreaCode = projectAreaCode;
        this.bizstate = bizstate;
        this.incomeDeptName = incomeDeptName;
        this.isUpload = isUpload;
        this.acceptDate = acceptDate;
        this.officialTotal = officialTotal;
    }

    public String getOfficialTotal() {
        return officialTotal;
    }

    public void setOfficialTotal(String officialTotal) {
        this.officialTotal = officialTotal;
    }

    // Constructors
    /** default constructor */
    public VProjectAuditInfo() {
    }

    /** minimal constructor */
    public VProjectAuditInfo(String djId) {

        this.djId = djId;

    }

    /** full constructor */
    public VProjectAuditInfo(String djId, Long projectId,
            String transaffairname, String projectUnitName, String projectName,
            String registeredAddr, String eiaApprovalType, String buildLegal,
            String industryField, String buildContent, Double totalInvestment,
            String projectType, String auditUnit, String sendArchiveNo,
            Date solvetime, String contactName, String contactPhone, String powerid,
            String projectAreaCode, String bizstate, String incomeDeptName, 
            String isUpload, Date acceptDate) {

        this.djId = djId;

        this.projectId = projectId;
        this.transaffairname = transaffairname;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.registeredAddr = registeredAddr;
        this.eiaApprovalType = eiaApprovalType;
        this.buildLegal = buildLegal;
        this.industryField = industryField;
        this.buildContent = buildContent;
        this.totalInvestment = totalInvestment;
        this.projectType = projectType;
        this.auditUnit = auditUnit;
        this.sendArchiveNo = sendArchiveNo;
        this.solvetime = solvetime;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.powerid = powerid;
        this.projectAreaCode = projectAreaCode;
        this.bizstate = bizstate;
        this.incomeDeptName = incomeDeptName;
        this.isUpload = isUpload;
        this.acceptDate = acceptDate;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getProjectUnitName() {
        return this.projectUnitName;
    }

    public void setProjectUnitName(String projectUnitName) {
        this.projectUnitName = projectUnitName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegisteredAddr() {
        return this.registeredAddr;
    }

    public void setRegisteredAddr(String registeredAddr) {
        this.registeredAddr = registeredAddr;
    }

    public String getEiaApprovalType() {
        return this.eiaApprovalType;
    }

    public void setEiaApprovalType(String eiaApprovalType) {
        this.eiaApprovalType = eiaApprovalType;
    }

    public String getBuildLegal() {
        return this.buildLegal;
    }

    public void setBuildLegal(String buildLegal) {
        this.buildLegal = buildLegal;
    }

    public String getIndustryField() {
        return this.industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getBuildContent() {
        return this.buildContent;
    }

    public void setBuildContent(String buildContent) {
        this.buildContent = buildContent;
    }

    public Double getTotalInvestment() {
        return this.totalInvestment;
    }

    public void setTotalInvestment(Double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getProjectType() {
        return this.projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getAuditUnit() {
        return this.auditUnit;
    }

    public void setAuditUnit(String auditUnit) {
        this.auditUnit = auditUnit;
    }

    public String getSendArchiveNo() {
        return this.sendArchiveNo;
    }

    public void setSendArchiveNo(String sendArchiveNo) {
        this.sendArchiveNo = sendArchiveNo;
    }

    public Date getSolvetime() {
        return this.solvetime;
    }

    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSolvetimeStr() {
        return solvetimeStr;
    }

    public void setSolvetimeStr(String solvetimeStr) {
        this.solvetimeStr = solvetimeStr;
    }

    public String getPowerid() {
        return powerid;
    }

    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

    public String getProjectAreaCode() {
        return projectAreaCode;
    }

    public void setProjectAreaCode(String projectAreaCode) {
        this.projectAreaCode = projectAreaCode;
    }

    public String getBizstate() {
        return bizstate;
    }

    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }

    public String getIncomeDeptName() {
        return incomeDeptName;
    }

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public void copy(VProjectAuditInfo other) {

        this.setDjId(other.getDjId());

        this.projectId = other.getProjectId();
        this.transaffairname = other.getTransaffairname();
        this.projectUnitName = other.getProjectUnitName();
        this.projectName = other.getProjectName();
        this.registeredAddr = other.getRegisteredAddr();
        this.eiaApprovalType = other.getEiaApprovalType();
        this.buildLegal = other.getBuildLegal();
        this.industryField = other.getIndustryField();
        this.buildContent = other.getBuildContent();
        this.totalInvestment = other.getTotalInvestment();
        this.projectType = other.getProjectType();
        this.auditUnit = other.getAuditUnit();
        this.sendArchiveNo = other.getSendArchiveNo();
        this.solvetime = other.getSolvetime();
        this.contactName = other.getContactName();
        this.contactPhone = other.getContactPhone();
        this.powerid = other.getPowerid();
        this.projectAreaCode = other.getProjectAreaCode();
        this.bizstate = other.getBizstate();
        this.incomeDeptName = other.getIncomeDeptName();
        this.isUpload = other.getIsUpload();
        this.acceptDate = other.getAcceptDate();
        this.officialTotal = other.getOfficialTotal();
    }

    public void copyNotNullProperty(VProjectAuditInfo other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getProjectId() != null)
            this.projectId = other.getProjectId();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getProjectUnitName() != null)
            this.projectUnitName = other.getProjectUnitName();
        if (other.getProjectName() != null)
            this.projectName = other.getProjectName();
        if (other.getRegisteredAddr() != null)
            this.registeredAddr = other.getRegisteredAddr();
        if (other.getEiaApprovalType() != null)
            this.eiaApprovalType = other.getEiaApprovalType();
        if (other.getBuildLegal() != null)
            this.buildLegal = other.getBuildLegal();
        if (other.getIndustryField() != null)
            this.industryField = other.getIndustryField();
        if (other.getBuildContent() != null)
            this.buildContent = other.getBuildContent();
        if (other.getTotalInvestment() != null)
            this.totalInvestment = other.getTotalInvestment();
        if (other.getProjectType() != null)
            this.projectType = other.getProjectType();
        if (other.getAuditUnit() != null)
            this.auditUnit = other.getAuditUnit();
        if (other.getSendArchiveNo() != null)
            this.sendArchiveNo = other.getSendArchiveNo();
        if (other.getSolvetime() != null)
            this.solvetime = other.getSolvetime();
        if (other.getContactName() != null)
            this.contactName = other.getContactName();
        if (other.getContactPhone() != null)
            this.contactPhone = other.getContactPhone();
        if (other.getPowerid() != null)
            this.powerid = other.getPowerid();
        if (other.getProjectAreaCode() != null)
            this.projectAreaCode = other.getProjectAreaCode();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getIsUpload() != null)
            this.isUpload = other.getIsUpload();
        if (other.getAcceptDate() != null)
            this.acceptDate = other.getAcceptDate();
        if (other.getOfficialTotal() != null)
            this.officialTotal = other.getOfficialTotal();
    }

    public void clearProperties() {

        this.projectId = null;
        this.transaffairname = null;
        this.projectUnitName = null;
        this.projectName = null;
        this.registeredAddr = null;
        this.eiaApprovalType = null;
        this.buildLegal = null;
        this.industryField = null;
        this.buildContent = null;
        this.totalInvestment = null;
        this.projectType = null;
        this.auditUnit = null;
        this.sendArchiveNo = null;
        this.solvetime = null;
        this.contactName = null;
        this.contactPhone = null;
        this.powerid = null;
        this.projectAreaCode = null;
        this.bizstate = null;
        this.incomeDeptName = null;
        this.isUpload = null;
        this.acceptDate = null;
        this.totalInvestment = null;
        this.officialTotal = null;
    }
}
