package com.centit.powerruntime.po;

import java.util.Date;

import com.centit.powerbase.po.PowerOrgInfoId;

/**
 * 
 * TODO Class description should be added
 * 
 * @author hx
 * @create 2012-12-7
 * @version
 */
public class VPowerOrgInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private PowerOrgInfoId cid;

    private String itemName;
    private String itemType;
    private String optid;
    private String applyItemType;
    private String orgId;
    private String depid;
    private String groupid;
    private String isapplyuser;
    private String recordid;
    private String archiveType;// 文书类型
    private String docFileName;// 文书名称 

    private String wfcode;
    private String setoperator;
    private Date setime;
    private String item_type;
    private String wfName;
    private String unitName;
    
    private String oaModuleType;//事项配置模块类别
    
    private String isgeneralmodule;
    private String generalmodulecode;//配置流程外通用配置
    
    private String agencyName;//发文机关名义
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getOptid() {
        return optid;
    }

    public void setOptid(String optid) {
        this.optid = optid;
    }

    public String getApplyItemType() {
        return applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getIsapplyuser() {
        return isapplyuser;
    }

    public void setIsapplyuser(String isapplyuser) {
        this.isapplyuser = isapplyuser;
    }

    // Constructors
    /** default constructor */
    public VPowerOrgInfo() {
    }

    /** minimal constructor */
    public VPowerOrgInfo(PowerOrgInfoId id

    ) {
        this.cid = id;

    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public VPowerOrgInfo(PowerOrgInfoId cid, String itemName, String itemType,
            String optid, String applyItemType, String orgId, String depid,
            String groupid, String isapplyuser, String recordid,
            String archiveType, String docFileName, String wfcode,
            String setoperator, Date setime, String item_type, String wfName,
            String unitName, String generalmodulecode) {
        super();
        this.cid = cid;
        this.itemName = itemName;
        this.itemType = itemType;
        this.optid = optid;
        this.applyItemType = applyItemType;
        this.orgId = orgId;
        this.depid = depid;
        this.groupid = groupid;
        this.isapplyuser = isapplyuser;
        this.recordid = recordid;
        this.archiveType = archiveType;
        this.docFileName = docFileName;
        this.wfcode = wfcode;
        this.setoperator = setoperator;
        this.setime = setime;
        this.item_type = item_type;
        this.wfName = wfName;
        this.unitName = unitName;
        this.generalmodulecode = generalmodulecode;
    }

    /** full constructor */
    public VPowerOrgInfo(PowerOrgInfoId id

    , String wfcode, String setoperator, Date setime, String itemType,
            String optid, String applyItemType, String orgId, String depid,
            String isapplyuser, String groupid) {
        this.cid = id;

        this.wfcode = wfcode;
        this.setoperator = setoperator;
        this.setime = setime;
        this.itemType = itemType;
        this.optid = optid;
        this.applyItemType = applyItemType;
        this.orgId = orgId;
        this.depid = depid;
        this.groupid = groupid;
        this.isapplyuser = isapplyuser;
    }

    public PowerOrgInfoId getCid() {
        return this.cid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
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

    public void setCid(PowerOrgInfoId id) {
        this.cid = id;
    }

    public String getItemId() {
        if (this.cid == null)
            this.cid = new PowerOrgInfoId();
        return this.cid.getItemId();
    }

    public void setItemId(String itemId) {
        if (this.cid == null)
            this.cid = new PowerOrgInfoId();
        this.cid.setItemId(itemId);
    }

    public String getUnitcode() {
        if (this.cid == null)
            this.cid = new PowerOrgInfoId();
        return this.cid.getUnitcode();
    }

    public void setUnitcode(String unitcode) {
        if (this.cid == null)
            this.cid = new PowerOrgInfoId();
        this.cid.setUnitcode(unitcode);
    }

    // Property accessors

    public String getWfcode() {
        return this.wfcode;
    }

    public void setWfcode(String wfcode) {
        this.wfcode = wfcode;
    }

    public String getSetoperator() {
        return this.setoperator;
    }

    public void setSetoperator(String setoperator) {
        this.setoperator = setoperator;
    }

    public Date getSetime() {
        return this.setime;
    }

    public void setSetime(Date setime) {
        this.setime = setime;
    }

    public void copy(VPowerOrgInfo other) {

        this.setItemId(other.getItemId());
        this.setUnitcode(other.getUnitcode());

        this.wfcode = other.getWfcode();
        this.setoperator = other.getSetoperator();
        this.setime = other.getSetime();
        this.itemType = other.getItemType();
        this.optid = other.getOptid();
        this.applyItemType = other.getApplyItemType();
        this.orgId = other.getOrgId();
        this.isapplyuser = other.getIsapplyuser();
        this.depid = other.getDepid();
        this.groupid = other.getGroupid();
        this.recordid=other.getRecordid();
        this.archiveType=other.getArchiveType();
        this.docFileName=other.getDocFileName();

    }

    public VPowerOrgInfo(PowerOrgInfoId cid, String itemName, String wfcode,
            String setoperator, Date setime, String itemType, String wfName,
            String unitName, String optid, String applyItemType, String orgId,
            String depid, String isapplyuser, String groupid) {
        super();
        this.cid = cid;
        this.itemName = itemName;
        this.wfcode = wfcode;
        this.setoperator = setoperator;
        this.setime = setime;
        this.wfName = wfName;
        this.unitName = unitName;
        this.itemType = itemType;
        this.optid = optid;
        this.applyItemType = applyItemType;
        this.orgId = orgId;
        this.depid = depid;
        this.groupid = groupid;
        this.isapplyuser = isapplyuser;
    }

    public void copyNotNullProperty(VPowerOrgInfo other) {

        if (other.getItemId() != null)
            this.setItemId(other.getItemId());
        if (other.getUnitcode() != null)
            this.setUnitcode(other.getUnitcode());

        if (other.getWfcode() != null)
            this.wfcode = other.getWfcode();
        if (other.getSetoperator() != null)
            this.setoperator = other.getSetoperator();
        if (other.getSetime() != null)
            this.setime = other.getSetime();
        if (other.getItemType() != null)
            this.itemType = other.getItemType();
        if (other.getOptid() != null)
            this.optid = other.getOptid();
        if (other.getApplyItemType() != null)
            this.applyItemType = other.getApplyItemType();
        if (other.getOrgId() != null)
            this.orgId = other.getOrgId();
        if (other.getDepid() != null)
            this.depid = other.getDepid();
        if (other.getGroupid() != null)
            this.groupid = other.getGroupid();
        if (other.getIsapplyuser() != null)
            this.isapplyuser = other.getIsapplyuser();
        if (other.getRecordid() != null)
        this.recordid=other.getRecordid();
        if (other.getArchiveType() != null)
        this.archiveType=other.getArchiveType();
        if (other.getDocFileName() != null)
        this.docFileName=other.getDocFileName();
    }

    public void clearProperties() {

        this.wfcode = null;
        this.setoperator = null;
        this.setime = null;
        this.itemType = null;
        this.optid = null;
        this.itemType = null;
        this.optid = null;
        this.applyItemType = null;
        this.orgId = null;
        this.depid = null;
        this.groupid = null;
        this.isapplyuser = null;
        this.recordid=null;
        this.archiveType=null;
        this.docFileName=null;
    }
    
    public String getGeneralmodulecode() {
        return generalmodulecode;
    }

    public void setGeneralmodulecode(String generalmodulecode) {
        this.generalmodulecode = generalmodulecode;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
}
