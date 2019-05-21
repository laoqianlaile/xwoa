package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VPowerUserInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private VPowerUserInfoId cid;
    private Long version;
    private Date beginTime;
    private Date endTime;
    private String orgId;
    private String itemName;
    private String itemStaName;
    private String itemType;
    private Long timeLimit;
    private String isNetwork;
    private String isFormula;
    private String phone;
    private String address;
    private Date lastmodifytime;
    private String auditSign;
    private String monitorPhone;
    private String acceptLink;
    private Long legalTimeLimit;
    private String charge;
    private String formName;
    private String fileName;
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
    private String optFlowCode;
    private String group_id;
    private String recordid;
    private String archiveType;//文书类型
    private String docFileName;//文书名称
    private Long riskid;
    private String oaModuleType;//事项配置模块类别

    private String optid;
    private String isapplyuser;
    private String wfcode;
    private Long wfVersion;// 流程版本
    
    private String isgeneralmodule;
    private String generalmodulecode;

    private String unitcode;

    public String getIsapplyuser() {
        return isapplyuser;
    }

    public void setIsapplyuser(String isapplyuser) {
        this.isapplyuser = isapplyuser;
    }

    public String getWfcode() {
        return wfcode;
    }

    public void setWfcode(String wfcode) {
        this.wfcode = wfcode;
    }

    // Constructors
    /** default constructor */
    public VPowerUserInfo() {
    }

    /** minimal constructor */
    public VPowerUserInfo(VPowerUserInfoId id

    ) {
        this.cid = id;

    }

    /** full constructor */
    public VPowerUserInfo(VPowerUserInfoId id

    , Date beginTime, Date endTime, String orgId, String itemName,
            String itemStaName, String itemType, Long timeLimit,
            String isNetwork, String isFormula, String phone, String address,
            Date lastmodifytime, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String fileName, String inFlowImgName,
            String ischarge, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String qlDepid,
            String qlDepstate, String entrustName, String qlState,
            String applyItemType, String optFlowCode, String group_id,
            String recordid, Long riskid, String optid, String isapplyuser,
            String wfcode, String unitcode,String oaModuleType) {
        this.cid = id;

        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.lastmodifytime = lastmodifytime;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.fileName = fileName;
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
        this.optFlowCode = optFlowCode;
        this.group_id = group_id;
        this.recordid = recordid;
        this.riskid = riskid;

        this.optid = optid;
        this.isapplyuser = isapplyuser;
        this.wfcode = wfcode;

        this.unitcode = unitcode;
        this.oaModuleType=oaModuleType;

    }

    public VPowerUserInfo(VPowerUserInfoId cid, Long version, Date beginTime,
            Date endTime, String orgId, String itemName, String itemStaName,
            String itemType, Long timeLimit, String isNetwork,
            String isFormula, String phone, String address,
            Date lastmodifytime, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String fileName, String inFlowImgName,
            String ischarge, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String qlDepid,
            String qlDepstate, String entrustName, String qlState,
            String applyItemType, String optFlowCode, String group_id,
            String recordid, Long riskid, String optid, String isapplyuser,
            String wfcode, Long wfVersion, String unitcode,String oaModuleType) {
        super();
        this.cid = cid;
        this.version = version;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.lastmodifytime = lastmodifytime;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.fileName = fileName;
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
        this.optFlowCode = optFlowCode;
        this.group_id = group_id;
        this.recordid = recordid;
        this.riskid = riskid;
        this.optid = optid;
        this.isapplyuser = isapplyuser;
        this.wfcode = wfcode;
        this.wfVersion = wfVersion;
        this.unitcode = unitcode;
        this.oaModuleType=oaModuleType;
    }

    public VPowerUserInfo(VPowerUserInfoId cid, Long version, Date beginTime,
            Date endTime, String orgId, String itemName, String itemStaName,
            String itemType, Long timeLimit, String isNetwork,
            String isFormula, String phone, String address,
            Date lastmodifytime, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String fileName, String inFlowImgName,
            String ischarge, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String qlDepid,
            String qlDepstate, String entrustName, String qlState,
            String applyItemType, String optFlowCode, String group_id,
            String recordid, Long riskid, String optid, String isapplyuser,
            String wfcode, String unitcode,String oaModuleType) {
        super();
        this.cid = cid;
        this.version = version;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.lastmodifytime = lastmodifytime;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.fileName = fileName;
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
        this.optFlowCode = optFlowCode;
        this.group_id = group_id;
        this.recordid = recordid;
        this.riskid = riskid;
        this.optid = optid;
        this.isapplyuser = isapplyuser;
        this.wfcode = wfcode;
        this.unitcode = unitcode;
        this.oaModuleType=oaModuleType;
    }

    public VPowerUserInfo(VPowerUserInfoId cid, Long version, Date beginTime,
            Date endTime, String orgId, String itemName, String itemStaName,
            String itemType, Long timeLimit, String isNetwork,
            String isFormula, String phone, String address,
            Date lastmodifytime, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String fileName, String inFlowImgName,
            String ischarge, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String qlDepid,
            String qlDepstate, String entrustName, String qlState,
            String applyItemType, String optFlowCode, String group_id,
            String recordid, String archiveType, Long riskid, String optid,
            String isapplyuser, String wfcode, Long wfVersion, String unitcode,String oaModuleType) {
        super();
        this.cid = cid;
        this.version = version;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.lastmodifytime = lastmodifytime;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.fileName = fileName;
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
        this.optFlowCode = optFlowCode;
        this.group_id = group_id;
        this.recordid = recordid;
        this.archiveType = archiveType;
        this.riskid = riskid;
        this.optid = optid;
        this.isapplyuser = isapplyuser;
        this.wfcode = wfcode;
        this.wfVersion = wfVersion;
        this.unitcode = unitcode;
        this.oaModuleType=oaModuleType;
    }

    public VPowerUserInfo(VPowerUserInfoId cid, Long version, Date beginTime,
            Date endTime, String orgId, String itemName, String itemStaName,
            String itemType, Long timeLimit, String isNetwork,
            String isFormula, String phone, String address,
            Date lastmodifytime, String auditSign, String monitorPhone,
            String acceptLink, Long legalTimeLimit, String charge,
            String formName, String fileName, String inFlowImgName,
            String ischarge, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String qlDepid,
            String qlDepstate, String entrustName, String qlState,
            String applyItemType, String optFlowCode, String group_id,
            String recordid, String archiveType, String docFileName,
            Long riskid, String optid, String isapplyuser, String wfcode,
            Long wfVersion, String unitcode,String oaModuleType) {
        super();
        this.cid = cid;
        this.version = version;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.phone = phone;
        this.address = address;
        this.lastmodifytime = lastmodifytime;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.acceptLink = acceptLink;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.fileName = fileName;
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
        this.optFlowCode = optFlowCode;
        this.group_id = group_id;
        this.recordid = recordid;
        this.archiveType = archiveType;
        this.docFileName = docFileName;
        this.riskid = riskid;
        this.optid = optid;
        this.isapplyuser = isapplyuser;
        this.wfcode = wfcode;
        this.wfVersion = wfVersion;
        this.unitcode = unitcode;
        this.oaModuleType=oaModuleType;
    }

    public VPowerUserInfoId getCid() {
        return this.cid;
    }

    public void setCid(VPowerUserInfoId id) {
        this.cid = id;
    }

    public String getItemId() {
        if (this.cid == null)
            this.cid = new VPowerUserInfoId();
        return this.cid.getItemId();
    }

    public void setItemId(String itemId) {
        if (this.cid == null)
            this.cid = new VPowerUserInfoId();
        this.cid.setItemId(itemId);
    }

    // public Long getVersion() {
    // if (this.cid == null)
    // this.cid = new VPowerUserInfoId();
    // return this.cid.getVersion();
    // }
    //
    // public void setVersion(Long version) {
    // if (this.cid == null)
    // this.cid = new VPowerUserInfoId();
    // this.cid.setVersion(version);
    // }

    // Property accessors

    public Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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

    public Date getLastmodifytime() {
        return this.lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
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

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getApplyItemType() {
        return applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getOptFlowCode() {
        return optFlowCode;
    }

    public void setOptFlowCode(String optFlowCode) {
        this.optFlowCode = optFlowCode;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Long getRiskid() {
        return riskid;
    }

    public void setRiskid(Long riskid) {
        this.riskid = riskid;
    }

    public void copy(VPowerUserInfo other) {

        this.setItemId(other.getItemId());
        // this.setVersion(other.getVersion());
        this.beginTime = other.getBeginTime();
        this.endTime = other.getEndTime();
        this.orgId = other.getOrgId();
        this.itemName = other.getItemName();
        this.itemStaName = other.getItemStaName();
        this.itemType = other.getItemType();
        this.timeLimit = other.getTimeLimit();
        this.isNetwork = other.getIsNetwork();
        this.isFormula = other.getIsFormula();
        this.phone = other.getPhone();
        this.address = other.getAddress();
        this.lastmodifytime = other.getLastmodifytime();
        this.auditSign = other.getAuditSign();
        this.monitorPhone = other.getMonitorPhone();
        this.acceptLink = other.getAcceptLink();
        this.legalTimeLimit = other.getLegalTimeLimit();
        this.charge = other.getCharge();
        this.formName = other.getFormName();
        this.fileName = other.getFileName();
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
        this.applyItemType = other.getApplyItemType();
        this.optFlowCode = other.getOptFlowCode();
        this.group_id = other.getGroup_id();
        this.recordid = other.getRecordid();
        this.riskid = other.getRiskid();
        this.version = other.getVersion();
        this.archiveType=other.getArchiveType(); 
        this.docFileName=other.getDocFileName();
        this.oaModuleType=other.getOaModuleType();
    }

    public void copyNotNullProperty(VPowerUserInfo other) {

        if (other.getItemId() != null)
            this.setItemId(other.getItemId());
        // if (other.getVersion() != null)
        // this.setVersion(other.getVersion());

        if (other.getBeginTime() != null)
            this.beginTime = other.getBeginTime();
        if (other.getEndTime() != null)
            this.endTime = other.getEndTime();
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
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
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
        if (other.getFileName() != null)
            this.fileName = other.getFileName();
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
        if (other.getApplyItemType() != null)
            this.applyItemType = other.getApplyItemType();
        if (other.getOptFlowCode() != null)
            this.optFlowCode = other.getOptFlowCode();
        if (other.getGroup_id() != null)
            this.group_id = other.getGroup_id();
        if (other.getRecordid() != null)
            this.recordid = other.getRecordid();
        if (other.getRiskid() != null)
            this.riskid = other.getRiskid();
        if (other.getVersion() != null)
            this.version = other.getVersion();
        if (other.getWfVersion() != null)
            this.wfVersion = other.getWfVersion();
        if(other.getArchiveType()!=null)
          this.archiveType=other.getArchiveType();  
        if(other.getDocFileName()!=null)
        this.docFileName=other.getDocFileName();
        if(other.getOaModuleType()!=null)
            this.oaModuleType=other.getOaModuleType();
    }

    public void clearProperties() {

        this.beginTime = null;
        this.endTime = null;
        this.orgId = null;
        this.itemName = null;
        this.itemStaName = null;
        this.itemType = null;
        this.timeLimit = null;
        this.isNetwork = null;
        this.isFormula = null;
        this.phone = null;
        this.address = null;
        this.lastmodifytime = null;
        this.auditSign = null;
        this.monitorPhone = null;
        this.acceptLink = null;
        this.legalTimeLimit = null;
        this.charge = null;
        this.formName = null;
        this.fileName = null;
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
        this.applyItemType = null;
        this.optFlowCode = null;
        this.group_id = null;
        this.recordid = null;
        this.archiveType=null;
        this.riskid = null;
        this.version = null;
        this.wfVersion = null;
        this.docFileName=null;
        this.oaModuleType=null;
    }

    public String getOptid() {
        return optid;
    }

    public void setOptid(String optid) {
        this.optid = optid;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public Long getWfVersion() {
        return wfVersion;
    }

    public void setWfVersion(Long wfVersion) {
        this.wfVersion = wfVersion;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public String getOaModuleType() {
        return oaModuleType;
    }

    public void setOaModuleType(String oaModuleType) {
        this.oaModuleType = oaModuleType;
    }

    public String getIsgeneralmodule() {
        return isgeneralmodule;
    }

    public void setIsgeneralmodule(String isgeneralmodule) {
        this.isgeneralmodule = isgeneralmodule;
    }

    public String getGeneralmodulecode() {
        return generalmodulecode;
    }

    public void setGeneralmodulecode(String generalmodulecode) {
        this.generalmodulecode = generalmodulecode;
    }

}
