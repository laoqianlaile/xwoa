package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VSupPowerWithoutLob implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String itemId;

    private String orgId;
    private String itemName;
    private String itemStaName;
    private String itemType;
    private Long timeLimit;
    private String isNetwork;
    private String isFormula;
    private String phone;
    private String address;
    private String isinuse;
    private Date lastmodifytime;
    private String ischange;
    private String fileName;
    private String auditSign;
    private String monitorPhone;
    private String acceptLink;
    private Long legalTimeLimit;
    private String charge;
    private String formName;
    private String inFlowImgName;
    private String ischarge;
    private String punishClass;
    private String transactDepname;
    private String promiseType;
    private String anticipateType;
    private String qlDepid;
    private String qlDepstate;
    private String entrustName;
    private String qlState;
    private String applyItemType;
    
    private String riskType;
    private String riskDesc;
    private String riskResult;
    
    private String groupId;
    private Long riskId;
    private String flowCode;

    // Constructors
    /** default constructor */
    public VSupPowerWithoutLob() {
    }

    /** minimal constructor */
    public VSupPowerWithoutLob(String itemId, String orgId, String itemName,
            String itemType) {

        this.itemId = itemId;

        this.orgId = orgId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    /** full constructor */
    public VSupPowerWithoutLob(String itemId, String orgId, String itemName,
            String itemStaName, String itemType, Long timeLimit,
            String isNetwork, String isFormula, String phone, String address,
            String isinuse, Date lastmodifytime, String ischange,
            String fileName, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String inFlowImgName, String ischarge,
            String punishClass, String transactDepname, String promiseType,
            String anticipateType, String qlDepid, String qlDepstate,
            String entrustName, String qlState,String applyItemType) {

        this.itemId = itemId;

        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.isinuse = isinuse;
        this.lastmodifytime = lastmodifytime;
        this.ischange = ischange;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.applyItemType =applyItemType;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    // Property accessors

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStaName() {
        return this.itemStaName;
    }

    public void setItemStaName(String itemStaName) {
        this.itemStaName = itemStaName;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getIsNetwork() {
        return this.isNetwork;
    }

    public void setIsNetwork(String isNetwork) {
        this.isNetwork = isNetwork;
    }

    public String getIsFormula() {
        return this.isFormula;
    }

    public void setIsFormula(String isFormula) {
        this.isFormula = isFormula;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsinuse() {
        return this.isinuse;
    }

    public void setIsinuse(String isinuse) {
        this.isinuse = isinuse;
    }

    public Date getLastmodifytime() {
        return this.lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getIschange() {
        return this.ischange;
    }

    public void setIschange(String ischange) {
        this.ischange = ischange;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuditSign() {
        return this.auditSign;
    }

    public void setAuditSign(String auditSign) {
        this.auditSign = auditSign;
    }

    public String getMonitorPhone() {
        return this.monitorPhone;
    }

    public void setMonitorPhone(String monitorPhone) {
        this.monitorPhone = monitorPhone;
    }

    public String getAcceptLink() {
        return this.acceptLink;
    }

    public void setAcceptLink(String acceptLink) {
        this.acceptLink = acceptLink;
    }

    public Long getLegalTimeLimit() {
        return this.legalTimeLimit;
    }

    public void setLegalTimeLimit(Long legalTimeLimit) {
        this.legalTimeLimit = legalTimeLimit;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getInFlowImgName() {
        return this.inFlowImgName;
    }

    public void setInFlowImgName(String inFlowImgName) {
        this.inFlowImgName = inFlowImgName;
    }

    public String getIscharge() {
        return this.ischarge;
    }

    public void setIscharge(String ischarge) {
        this.ischarge = ischarge;
    }

    public String getPunishClass() {
        return this.punishClass;
    }

    public void setPunishClass(String punishClass) {
        this.punishClass = punishClass;
    }

    public String getTransactDepname() {
        return this.transactDepname;
    }

    public void setTransactDepname(String transactDepname) {
        this.transactDepname = transactDepname;
    }

    public String getPromiseType() {
        return this.promiseType;
    }

    public void setPromiseType(String promiseType) {
        this.promiseType = promiseType;
    }

    public String getAnticipateType() {
        return this.anticipateType;
    }

    public void setAnticipateType(String anticipateType) {
        this.anticipateType = anticipateType;
    }

    public String getQlDepid() {
        return this.qlDepid;
    }

    public void setQlDepid(String qlDepid) {
        this.qlDepid = qlDepid;
    }

    public String getQlDepstate() {
        return this.qlDepstate;
    }

    public void setQlDepstate(String qlDepstate) {
        this.qlDepstate = qlDepstate;
    }

    public String getEntrustName() {
        return this.entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public String getQlState() {
        return this.qlState;
    }

    public void setQlState(String qlState) {
        this.qlState = qlState;
    }

    public void copy(VSupPowerWithoutLob other) {

        this.setItemId(other.getItemId());

        this.orgId = other.getOrgId();
        this.itemName = other.getItemName();
        this.itemStaName = other.getItemStaName();
        this.itemType = other.getItemType();
        this.timeLimit = other.getTimeLimit();
        this.isNetwork = other.getIsNetwork();
        this.isFormula = other.getIsFormula();
        this.phone = other.getPhone();
        this.address = other.getAddress();
        this.isinuse = other.getIsinuse();
        this.lastmodifytime = other.getLastmodifytime();
        this.ischange = other.getIschange();
        this.fileName = other.getFileName();
        this.auditSign = other.getAuditSign();
        this.monitorPhone = other.getMonitorPhone();
        this.acceptLink = other.getAcceptLink();
        this.legalTimeLimit = other.getLegalTimeLimit();
        this.charge = other.getCharge();
        this.formName = other.getFormName();
        this.inFlowImgName = other.getInFlowImgName();
        this.ischarge = other.getIscharge();
        this.punishClass = other.getPunishClass();
        this.transactDepname = other.getTransactDepname();
        this.promiseType = other.getPromiseType();
        this.anticipateType = other.getAnticipateType();
        this.qlDepid = other.getQlDepid();
        this.qlDepstate = other.getQlDepstate();
        this.entrustName = other.getEntrustName();
        this.qlState = other.getQlState();

    }

    public void copyNotNullProperty(VSupPowerWithoutLob other) {

        if (other.getItemId() != null)
            this.setItemId(other.getItemId());

        if (other.getOrgId() != null)
            this.orgId = other.getOrgId();
        if (other.getItemName() != null)
            this.itemName = other.getItemName();
        if (other.getItemStaName() != null)
            this.itemStaName = other.getItemStaName();
        if (other.getItemType() != null)
            this.itemType = other.getItemType();
        if (other.getTimeLimit() != null)
            this.timeLimit = other.getTimeLimit();
        if (other.getIsNetwork() != null)
            this.isNetwork = other.getIsNetwork();
        if (other.getIsFormula() != null)
            this.isFormula = other.getIsFormula();
        if (other.getPhone() != null)
            this.phone = other.getPhone();
        if (other.getAddress() != null)
            this.address = other.getAddress();
        if (other.getIsinuse() != null)
            this.isinuse = other.getIsinuse();
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
        if (other.getIschange() != null)
            this.ischange = other.getIschange();
        if (other.getFileName() != null)
            this.fileName = other.getFileName();
        if (other.getAuditSign() != null)
            this.auditSign = other.getAuditSign();
        if (other.getMonitorPhone() != null)
            this.monitorPhone = other.getMonitorPhone();
        if (other.getAcceptLink() != null)
            this.acceptLink = other.getAcceptLink();
        if (other.getLegalTimeLimit() != null)
            this.legalTimeLimit = other.getLegalTimeLimit();
        if (other.getCharge() != null)
            this.charge = other.getCharge();
        if (other.getFormName() != null)
            this.formName = other.getFormName();
        if (other.getInFlowImgName() != null)
            this.inFlowImgName = other.getInFlowImgName();
        if (other.getIscharge() != null)
            this.ischarge = other.getIscharge();
        if (other.getPunishClass() != null)
            this.punishClass = other.getPunishClass();
        if (other.getTransactDepname() != null)
            this.transactDepname = other.getTransactDepname();
        if (other.getPromiseType() != null)
            this.promiseType = other.getPromiseType();
        if (other.getAnticipateType() != null)
            this.anticipateType = other.getAnticipateType();
        if (other.getQlDepid() != null)
            this.qlDepid = other.getQlDepid();
        if (other.getQlDepstate() != null)
            this.qlDepstate = other.getQlDepstate();
        if (other.getEntrustName() != null)
            this.entrustName = other.getEntrustName();
        if (other.getQlState() != null)
            this.qlState = other.getQlState();

    }

    public void clearProperties() {

        this.orgId = null;
        this.itemName = null;
        this.itemStaName = null;
        this.itemType = null;
        this.timeLimit = null;
        this.isNetwork = null;
        this.isFormula = null;
        this.phone = null;
        this.address = null;
        this.isinuse = null;
        this.lastmodifytime = null;
        this.ischange = null;
        this.fileName = null;
        this.auditSign = null;
        this.monitorPhone = null;
        this.acceptLink = null;
        this.legalTimeLimit = null;
        this.charge = null;
        this.formName = null;
        this.inFlowImgName = null;
        this.ischarge = null;
        this.punishClass = null;
        this.transactDepname = null;
        this.promiseType = null;
        this.anticipateType = null;
        this.qlDepid = null;
        this.qlDepstate = null;
        this.entrustName = null;
        this.qlState = null;

    }

    public VSupPowerWithoutLob(String itemId, String orgId, String itemName,
            String itemStaName, String itemType, Long timeLimit,
            String isNetwork, String isFormula, String phone, String address,
            String isinuse, Date lastmodifytime, String ischange,
            String fileName, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String inFlowImgName, String ischarge,
            String punishClass, String transactDepname, String promiseType,
            String anticipateType, String qlDepid, String qlDepstate,
            String entrustName, String qlState, String applyItemType,
            String riskType, String riskDesc, String riskResult,
            String groupId, Long riskId, String flowCode) {
        super();
        this.itemId = itemId;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.isinuse = isinuse;
        this.lastmodifytime = lastmodifytime;
        this.ischange = ischange;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.applyItemType = applyItemType;
        this.riskType = riskType;
        this.riskDesc = riskDesc;
        this.riskResult = riskResult;
        this.groupId = groupId;
        this.riskId = riskId;
        this.flowCode = flowCode;
    }

    public String getApplyItemType() {
        return applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public String getRiskResult() {
        return riskResult;
    }

    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }
}
