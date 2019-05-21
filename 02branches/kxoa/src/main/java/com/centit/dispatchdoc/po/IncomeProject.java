package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class IncomeProject implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String projectUnitName;
    private String projectUnitType;
    private String projectOrgCode;
    private String economicType;
    private String industryField;
    private String membership;
    private String businessScope;
    private Double registeredCapital;
    private String countryArea;
    private String registeredAddr;
    private String buildLegal;
    private String adminAreaCode;
    private String adminAreaZip;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String projectName;
    private String projectBuildAddr;
    private String projectAreaCode;
    private String projectIndustryField;
    private String projectBuildType;
    private String projectMembership;
    private String projectType;
    private String projectNature; // 项目属性，暂时取消
    private String projectBuildLand;
    private Date buildBeginDate;
    private String industryMinistries;
    private String buildContent;
    private Double totalInvestment;
    private String allowType;
    private String allowDocNo;
    private Date allowTime;
    private String allowUnit;
    private String auditType;
    private Date auditTime;
    private String auditDocNo;
    private String auditUnit;
    private String eiaType;
    private String eiaDocNo;
    private Date eiaTime;
    private String eiaUnit;
    private String officialType;
    private String officialDocNo;
    private Date officialTime;
    private String officialUnit;
    private String officialBuildContent;
    private Double officialTotalInvestment;
    private Double centreInvestment;
    private Double provinceInvestment;
    private Double cityInvestment;
    private Double countyInvestment;
    private Double townInvestment;
    private Double foreignInvestment;
    private Double unitSelfRaise;
    private Double unitSelfOwner;
    private Double bankAdvance;
    private Double otherAdvance;
    private Double otherSources;
    private String eiaApprovalType;
    private String eiaApprovalDocNo;
    private Date eiaApprovalTime;
    private String eiaApprovalUnit;
    
    
    // Constructors
    /** default constructor */
    public IncomeProject() {
    }

    /** minimal constructor */
    public IncomeProject(String djId) {

        this.djId = djId;

    }

    /** full constructor */
    public IncomeProject(String djId, String projectUnitName, String projectUnitType,
            String projectOrgCode, String economicType, String industryField,
            String membership, String businessScope, Double registeredCapital,
            String countryArea, String registeredAddr, String buildLegal,
            String adminAreaCode, String adminAreaZip, String contactName,
            String contactPhone, String contactEmail, String projectName,
            String projectBuildAddr, String projectAreaCode,
            String projectIndustryField, String projectBuildType,
            String projectMembership, String projectType, String projectNature,
            String projectBuildLand, Date buildBeginDate,
            String industryMinistries, String buildContent,
            Double totalInvestment, String allowType, String allowDocNo,
            Date allowTime, String allowUnit, String auditType, Date auditTime,
            String auditDocNo, String auditUnit, String eiaType,
            String eiaDocNo, Date eiaTime, String eiaUnit, String officialType,
            String officialDocNo, Date officialTime, String officialUnit,
            String officialBuildContent, Double officialTotalInvestment,
            Double centreInvestment, Double provinceInvestment,
            Double cityInvestment, Double countyInvestment,
            Double townInvestment, Double foreignInvestment,
            Double unitSelfRaise, Double unitSelfOwner, Double bankAdvance,
            Double otherAdvance, Double otherSources, String eiaApprovalType,
            String eiaApprovalDocNo, Date eiaApprovalTime, String eiaApprovalUnit) {

        this.djId = djId;

        this.projectUnitName = projectUnitName;
        this.projectUnitType = projectUnitType;
        this.projectOrgCode = projectOrgCode;
        this.economicType = economicType;
        this.industryField = industryField;
        this.membership = membership;
        this.businessScope = businessScope;
        this.registeredCapital = registeredCapital;
        this.countryArea = countryArea;
        this.registeredAddr = registeredAddr;
        this.buildLegal = buildLegal;
        this.adminAreaCode = adminAreaCode;
        this.adminAreaZip = adminAreaZip;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.projectName = projectName;
        this.projectBuildAddr = projectBuildAddr;
        this.projectAreaCode = projectAreaCode;
        this.projectIndustryField = projectIndustryField;
        this.projectBuildType = projectBuildType;
        this.projectMembership = projectMembership;
        this.projectType = projectType;
        this.projectNature = projectNature;
        this.projectBuildLand = projectBuildLand;
        this.buildBeginDate = buildBeginDate;
        this.industryMinistries = industryMinistries;
        this.buildContent = buildContent;
        this.totalInvestment = totalInvestment;
        this.allowType = allowType;
        this.allowDocNo = allowDocNo;
        this.allowTime = allowTime;
        this.allowUnit = allowUnit;
        this.auditType = auditType;
        this.auditTime = auditTime;
        this.auditDocNo = auditDocNo;
        this.auditUnit = auditUnit;
        this.eiaType = eiaType;
        this.eiaDocNo = eiaDocNo;
        this.eiaTime = eiaTime;
        this.eiaUnit = eiaUnit;
        this.officialType = officialType;
        this.officialDocNo = officialDocNo;
        this.officialTime = officialTime;
        this.officialUnit = officialUnit;
        this.officialBuildContent = officialBuildContent;
        this.officialTotalInvestment = officialTotalInvestment;
        this.centreInvestment = centreInvestment;
        this.provinceInvestment = provinceInvestment;
        this.cityInvestment = cityInvestment;
        this.countyInvestment = countyInvestment;
        this.townInvestment = townInvestment;
        this.foreignInvestment = foreignInvestment;
        this.unitSelfRaise = unitSelfRaise;
        this.unitSelfOwner = unitSelfOwner;
        this.bankAdvance = bankAdvance;
        this.otherAdvance = otherAdvance;
        this.otherSources = otherSources;
        this.eiaApprovalType = eiaApprovalType;
        this.eiaApprovalDocNo = eiaApprovalDocNo;
        this.eiaApprovalTime = eiaApprovalTime;
        this.eiaApprovalUnit = eiaApprovalUnit;
    }

    // Property accessors

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getProjectUnitName() {
        return this.projectUnitName;
    }

    public void setProjectUnitName(String projectUnitName) {
        this.projectUnitName = projectUnitName;
    }

    public String getProjectUnitType() {
        return projectUnitType;
    }

    public void setProjectUnitType(String projectUnitType) {
        this.projectUnitType = projectUnitType;
    }

    public String getProjectOrgCode() {
        return this.projectOrgCode;
    }

    public void setProjectOrgCode(String projectOrgCode) {
        this.projectOrgCode = projectOrgCode;
    }

    public String getEconomicType() {
        return this.economicType;
    }

    public void setEconomicType(String economicType) {
        this.economicType = economicType;
    }

    public String getIndustryField() {
        return this.industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getMembership() {
        return this.membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getBusinessScope() {
        return this.businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public Double getRegisteredCapital() {
        return this.registeredCapital;
    }

    public void setRegisteredCapital(Double registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getCountryArea() {
        return this.countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getRegisteredAddr() {
        return this.registeredAddr;
    }

    public void setRegisteredAddr(String registeredAddr) {
        this.registeredAddr = registeredAddr;
    }

    public String getBuildLegal() {
        return this.buildLegal;
    }

    public void setBuildLegal(String buildLegal) {
        this.buildLegal = buildLegal;
    }

    public String getAdminAreaCode() {
        return this.adminAreaCode;
    }

    public void setAdminAreaCode(String adminAreaCode) {
        this.adminAreaCode = adminAreaCode;
    }

    public String getAdminAreaZip() {
        return this.adminAreaZip;
    }

    public void setAdminAreaZip(String adminAreaZip) {
        this.adminAreaZip = adminAreaZip;
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

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectBuildAddr() {
        return this.projectBuildAddr;
    }

    public void setProjectBuildAddr(String projectBuildAddr) {
        this.projectBuildAddr = projectBuildAddr;
    }

    public String getProjectAreaCode() {
        return this.projectAreaCode;
    }

    public void setProjectAreaCode(String projectAreaCode) {
        this.projectAreaCode = projectAreaCode;
    }

    public String getProjectIndustryField() {
        return this.projectIndustryField;
    }

    public void setProjectIndustryField(String projectIndustryField) {
        this.projectIndustryField = projectIndustryField;
    }

    public String getProjectBuildType() {
        return this.projectBuildType;
    }

    public void setProjectBuildType(String projectBuildType) {
        this.projectBuildType = projectBuildType;
    }

    public String getProjectMembership() {
        return this.projectMembership;
    }

    public void setProjectMembership(String projectMembership) {
        this.projectMembership = projectMembership;
    }

    public String getProjectType() {
        return this.projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectNature() {
        return this.projectNature;
    }

    public void setProjectNature(String projectNature) {
        this.projectNature = projectNature;
    }

    public String getProjectBuildLand() {
        return this.projectBuildLand;
    }

    public void setProjectBuildLand(String projectBuildLand) {
        this.projectBuildLand = projectBuildLand;
    }

    public Date getBuildBeginDate() {
        return this.buildBeginDate;
    }

    public void setBuildBeginDate(Date buildBeginDate) {
        this.buildBeginDate = buildBeginDate;
    }

    public String getIndustryMinistries() {
        return this.industryMinistries;
    }

    public void setIndustryMinistries(String industryMinistries) {
        this.industryMinistries = industryMinistries;
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

    public String getAllowType() {
        return this.allowType;
    }

    public void setAllowType(String allowType) {
        this.allowType = allowType;
    }

    public String getAllowDocNo() {
        return this.allowDocNo;
    }

    public void setAllowDocNo(String allowDocNo) {
        this.allowDocNo = allowDocNo;
    }

    public Date getAllowTime() {
        return this.allowTime;
    }

    public void setAllowTime(Date allowTime) {
        this.allowTime = allowTime;
    }

    public String getAllowUnit() {
        return this.allowUnit;
    }

    public void setAllowUnit(String allowUnit) {
        this.allowUnit = allowUnit;
    }

    public String getAuditType() {
        return this.auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditDocNo() {
        return this.auditDocNo;
    }

    public void setAuditDocNo(String auditDocNo) {
        this.auditDocNo = auditDocNo;
    }

    public String getAuditUnit() {
        return this.auditUnit;
    }

    public void setAuditUnit(String auditUnit) {
        this.auditUnit = auditUnit;
    }

    public String getEiaType() {
        return this.eiaType;
    }

    public void setEiaType(String eiaType) {
        this.eiaType = eiaType;
    }

    public String getEiaDocNo() {
        return this.eiaDocNo;
    }

    public void setEiaDocNo(String eiaDocNo) {
        this.eiaDocNo = eiaDocNo;
    }

    public Date getEiaTime() {
        return this.eiaTime;
    }

    public void setEiaTime(Date eiaTime) {
        this.eiaTime = eiaTime;
    }

    public String getEiaUnit() {
        return this.eiaUnit;
    }

    public void setEiaUnit(String eiaUnit) {
        this.eiaUnit = eiaUnit;
    }

    public String getOfficialType() {
        return this.officialType;
    }

    public void setOfficialType(String officialType) {
        this.officialType = officialType;
    }

    public String getOfficialDocNo() {
        return this.officialDocNo;
    }

    public void setOfficialDocNo(String officialDocNo) {
        this.officialDocNo = officialDocNo;
    }

    public Date getOfficialTime() {
        return this.officialTime;
    }

    public void setOfficialTime(Date officialTime) {
        this.officialTime = officialTime;
    }

    public String getOfficialUnit() {
        return this.officialUnit;
    }

    public void setOfficialUnit(String officialUnit) {
        this.officialUnit = officialUnit;
    }

    public String getOfficialBuildContent() {
        return this.officialBuildContent;
    }

    public void setOfficialBuildContent(String officialBuildContent) {
        this.officialBuildContent = officialBuildContent;
    }

    public Double getOfficialTotalInvestment() {
        return this.officialTotalInvestment;
    }

    public void setOfficialTotalInvestment(Double officialTotalInvestment) {
        this.officialTotalInvestment = officialTotalInvestment;
    }

    public Double getCentreInvestment() {
        return this.centreInvestment;
    }

    public void setCentreInvestment(Double centreInvestment) {
        this.centreInvestment = centreInvestment;
    }

    public Double getProvinceInvestment() {
        return this.provinceInvestment;
    }

    public void setProvinceInvestment(Double provinceInvestment) {
        this.provinceInvestment = provinceInvestment;
    }

    public Double getCityInvestment() {
        return this.cityInvestment;
    }

    public void setCityInvestment(Double cityInvestment) {
        this.cityInvestment = cityInvestment;
    }

    public Double getCountyInvestment() {
        return this.countyInvestment;
    }

    public void setCountyInvestment(Double countyInvestment) {
        this.countyInvestment = countyInvestment;
    }

    public Double getTownInvestment() {
        return this.townInvestment;
    }

    public void setTownInvestment(Double townInvestment) {
        this.townInvestment = townInvestment;
    }

    public Double getForeignInvestment() {
        return this.foreignInvestment;
    }

    public void setForeignInvestment(Double foreignInvestment) {
        this.foreignInvestment = foreignInvestment;
    }

    public Double getUnitSelfRaise() {
        return this.unitSelfRaise;
    }

    public void setUnitSelfRaise(Double unitSelfRaise) {
        this.unitSelfRaise = unitSelfRaise;
    }

    public Double getUnitSelfOwner() {
        return this.unitSelfOwner;
    }

    public void setUnitSelfOwner(Double unitSelfOwner) {
        this.unitSelfOwner = unitSelfOwner;
    }

    public Double getBankAdvance() {
        return this.bankAdvance;
    }

    public void setBankAdvance(Double bankAdvance) {
        this.bankAdvance = bankAdvance;
    }

    public Double getOtherAdvance() {
        return this.otherAdvance;
    }

    public void setOtherAdvance(Double otherAdvance) {
        this.otherAdvance = otherAdvance;
    }
    
    public Double getOtherSources() {
        return otherSources;
    }

    public void setOtherSources(Double otherSources) {
        this.otherSources = otherSources;
    }

    public String getEiaApprovalType() {
        return eiaApprovalType;
    }

    public void setEiaApprovalType(String eiaApprovalType) {
        this.eiaApprovalType = eiaApprovalType;
    }

    public String getEiaApprovalDocNo() {
        return eiaApprovalDocNo;
    }

    public void setEiaApprovalDocNo(String eiaApprovalDocNo) {
        this.eiaApprovalDocNo = eiaApprovalDocNo;
    }

    public Date getEiaApprovalTime() {
        return eiaApprovalTime;
    }

    public void setEiaApprovalTime(Date eiaApprovalTime) {
        this.eiaApprovalTime = eiaApprovalTime;
    }

    public String getEiaApprovalUnit() {
        return eiaApprovalUnit;
    }

    public void setEiaApprovalUnit(String eiaApprovalUnit) {
        this.eiaApprovalUnit = eiaApprovalUnit;
    }

    public void copy(IncomeProject other) {

        this.setDjId(other.getDjId());

        this.projectUnitName = other.getProjectUnitName();
        this.projectUnitType = other.getProjectUnitType();
        this.projectOrgCode = other.getProjectOrgCode();
        this.economicType = other.getEconomicType();
        this.industryField = other.getIndustryField();
        this.membership = other.getMembership();
        this.businessScope = other.getBusinessScope();
        this.registeredCapital = other.getRegisteredCapital();
        this.countryArea = other.getCountryArea();
        this.registeredAddr = other.getRegisteredAddr();
        this.buildLegal = other.getBuildLegal();
        this.adminAreaCode = other.getAdminAreaCode();
        this.adminAreaZip = other.getAdminAreaZip();
        this.contactName = other.getContactName();
        this.contactPhone = other.getContactPhone();
        this.contactEmail = other.getContactEmail();
        this.projectName = other.getProjectName();
        this.projectBuildAddr = other.getProjectBuildAddr();
        this.projectAreaCode = other.getProjectAreaCode();
        this.projectIndustryField = other.getProjectIndustryField();
        this.projectBuildType = other.getProjectBuildType();
        this.projectMembership = other.getProjectMembership();
        this.projectType = other.getProjectType();
        this.projectNature = other.getProjectNature();
        this.projectBuildLand = other.getProjectBuildLand();
        this.buildBeginDate = other.getBuildBeginDate();
        this.industryMinistries = other.getIndustryMinistries();
        this.buildContent = other.getBuildContent();
        this.totalInvestment = other.getTotalInvestment();
        this.allowType = other.getAllowType();
        this.allowDocNo = other.getAllowDocNo();
        this.allowTime = other.getAllowTime();
        this.allowUnit = other.getAllowUnit();
        this.auditType = other.getAuditType();
        this.auditTime = other.getAuditTime();
        this.auditDocNo = other.getAuditDocNo();
        this.auditUnit = other.getAuditUnit();
        this.eiaType = other.getEiaType();
        this.eiaDocNo = other.getEiaDocNo();
        this.eiaTime = other.getEiaTime();
        this.eiaUnit = other.getEiaUnit();
        this.officialType = other.getOfficialType();
        this.officialDocNo = other.getOfficialDocNo();
        this.officialTime = other.getOfficialTime();
        this.officialUnit = other.getOfficialUnit();
        this.officialBuildContent = other.getOfficialBuildContent();
        this.officialTotalInvestment = other.getOfficialTotalInvestment();
        this.centreInvestment = other.getCentreInvestment();
        this.provinceInvestment = other.getProvinceInvestment();
        this.cityInvestment = other.getCityInvestment();
        this.countyInvestment = other.getCountyInvestment();
        this.townInvestment = other.getTownInvestment();
        this.foreignInvestment = other.getForeignInvestment();
        this.unitSelfRaise = other.getUnitSelfRaise();
        this.unitSelfOwner = other.getUnitSelfOwner();
        this.bankAdvance = other.getBankAdvance();
        this.otherAdvance = other.getOtherAdvance();
        this.otherSources = other.getOtherSources();
        this.eiaApprovalType = other.getEiaApprovalType();
        this.eiaApprovalDocNo = other.getEiaApprovalDocNo();
        this.eiaApprovalTime = other.getEiaApprovalTime();
        this.eiaApprovalUnit = other.getEiaApprovalUnit();
    }

    public void copyNotNullProperty(IncomeProject other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getProjectUnitName() != null)
            this.projectUnitName = other.getProjectUnitName();
        if (other.getProjectUnitType() != null)
            this.projectUnitType = other.getProjectUnitType();
        if (other.getProjectOrgCode() != null)
            this.projectOrgCode = other.getProjectOrgCode();
        if (other.getEconomicType() != null)
            this.economicType = other.getEconomicType();
        if (other.getIndustryField() != null)
            this.industryField = other.getIndustryField();
        if (other.getMembership() != null)
            this.membership = other.getMembership();
        if (other.getBusinessScope() != null)
            this.businessScope = other.getBusinessScope();
        if (other.getRegisteredCapital() != null)
            this.registeredCapital = other.getRegisteredCapital();
        if (other.getCountryArea() != null)
            this.countryArea = other.getCountryArea();
        if (other.getRegisteredAddr() != null)
            this.registeredAddr = other.getRegisteredAddr();
        if (other.getBuildLegal() != null)
            this.buildLegal = other.getBuildLegal();
        if (other.getAdminAreaCode() != null)
            this.adminAreaCode = other.getAdminAreaCode();
        if (other.getAdminAreaZip() != null)
            this.adminAreaZip = other.getAdminAreaZip();
        if (other.getContactName() != null)
            this.contactName = other.getContactName();
        if (other.getContactPhone() != null)
            this.contactPhone = other.getContactPhone();
        if (other.getContactEmail() != null)
            this.contactEmail = other.getContactEmail();
        if (other.getProjectName() != null)
            this.projectName = other.getProjectName();
        if (other.getProjectBuildAddr() != null)
            this.projectBuildAddr = other.getProjectBuildAddr();
        if (other.getProjectAreaCode() != null)
            this.projectAreaCode = other.getProjectAreaCode();
        if (other.getProjectIndustryField() != null)
            this.projectIndustryField = other.getProjectIndustryField();
        if (other.getProjectBuildType() != null)
            this.projectBuildType = other.getProjectBuildType();
        if (other.getProjectMembership() != null)
            this.projectMembership = other.getProjectMembership();
        if (other.getProjectType() != null)
            this.projectType = other.getProjectType();
        if (other.getProjectNature() != null)
            this.projectNature = other.getProjectNature();
        if (other.getProjectBuildLand() != null)
            this.projectBuildLand = other.getProjectBuildLand();
        if (other.getBuildBeginDate() != null)
            this.buildBeginDate = other.getBuildBeginDate();
        if (other.getIndustryMinistries() != null)
            this.industryMinistries = other.getIndustryMinistries();
        if (other.getBuildContent() != null)
            this.buildContent = other.getBuildContent();
        if (other.getTotalInvestment() != null)
            this.totalInvestment = other.getTotalInvestment();
        if (other.getAllowType() != null)
            this.allowType = other.getAllowType();
        if (other.getAllowDocNo() != null)
            this.allowDocNo = other.getAllowDocNo();
        if (other.getAllowTime() != null)
            this.allowTime = other.getAllowTime();
        if (other.getAllowUnit() != null)
            this.allowUnit = other.getAllowUnit();
        if (other.getAuditType() != null)
            this.auditType = other.getAuditType();
        if (other.getAuditTime() != null)
            this.auditTime = other.getAuditTime();
        if (other.getAuditDocNo() != null)
            this.auditDocNo = other.getAuditDocNo();
        if (other.getAuditUnit() != null)
            this.auditUnit = other.getAuditUnit();
        if (other.getEiaType() != null)
            this.eiaType = other.getEiaType();
        if (other.getEiaDocNo() != null)
            this.eiaDocNo = other.getEiaDocNo();
        if (other.getEiaTime() != null)
            this.eiaTime = other.getEiaTime();
        if (other.getEiaUnit() != null)
            this.eiaUnit = other.getEiaUnit();
        if (other.getOfficialType() != null)
            this.officialType = other.getOfficialType();
        if (other.getOfficialDocNo() != null)
            this.officialDocNo = other.getOfficialDocNo();
        if (other.getOfficialTime() != null)
            this.officialTime = other.getOfficialTime();
        if (other.getOfficialUnit() != null)
            this.officialUnit = other.getOfficialUnit();
        if (other.getOfficialBuildContent() != null)
            this.officialBuildContent = other.getOfficialBuildContent();
        if (other.getOfficialTotalInvestment() != null)
            this.officialTotalInvestment = other.getOfficialTotalInvestment();
        if (other.getCentreInvestment() != null)
            this.centreInvestment = other.getCentreInvestment();
        if (other.getProvinceInvestment() != null)
            this.provinceInvestment = other.getProvinceInvestment();
        if (other.getCityInvestment() != null)
            this.cityInvestment = other.getCityInvestment();
        if (other.getCountyInvestment() != null)
            this.countyInvestment = other.getCountyInvestment();
        if (other.getTownInvestment() != null)
            this.townInvestment = other.getTownInvestment();
        if (other.getForeignInvestment() != null)
            this.foreignInvestment = other.getForeignInvestment();
        if (other.getUnitSelfRaise() != null)
            this.unitSelfRaise = other.getUnitSelfRaise();
        if (other.getUnitSelfOwner() != null)
            this.unitSelfOwner = other.getUnitSelfOwner();
        if (other.getBankAdvance() != null)
            this.bankAdvance = other.getBankAdvance();
        if (other.getOtherAdvance() != null)
            this.otherAdvance = other.getOtherAdvance();
        if (other.getOtherSources() != null)
            this.otherSources = other.getOtherSources();
        if (other.getEiaApprovalType() != null)
            this.eiaApprovalType = other.getEiaApprovalType();
        if (other.getEiaApprovalDocNo() != null)
            this.eiaApprovalDocNo = other.getEiaApprovalDocNo();
        if (other.getEiaApprovalTime() != null)
            this.eiaApprovalTime = other.getEiaApprovalTime();
        if (other.getEiaApprovalUnit() != null)
            this.eiaApprovalUnit = other.getEiaApprovalUnit();
    }

    public void clearProperties() {

        this.projectUnitName = null;
        this.projectUnitType = null;
        this.projectOrgCode = null;
        this.economicType = null;
        this.industryField = null;
        this.membership = null;
        this.businessScope = null;
        this.registeredCapital = null;
        this.countryArea = null;
        this.registeredAddr = null;
        this.buildLegal = null;
        this.adminAreaCode = null;
        this.adminAreaZip = null;
        this.contactName = null;
        this.contactPhone = null;
        this.contactEmail = null;
        this.projectName = null;
        this.projectBuildAddr = null;
        this.projectAreaCode = null;
        this.projectIndustryField = null;
        this.projectBuildType = null;
        this.projectMembership = null;
        this.projectType = null;
        this.projectNature = null;
        this.projectBuildLand = null;
        this.buildBeginDate = null;
        this.industryMinistries = null;
        this.buildContent = null;
        this.totalInvestment = null;
        this.allowType = null;
        this.allowDocNo = null;
        this.allowTime = null;
        this.allowUnit = null;
        this.auditType = null;
        this.auditTime = null;
        this.auditDocNo = null;
        this.auditUnit = null;
        this.eiaType = null;
        this.eiaDocNo = null;
        this.eiaTime = null;
        this.eiaUnit = null;
        this.officialType = null;
        this.officialDocNo = null;
        this.officialTime = null;
        this.officialUnit = null;
        this.officialBuildContent = null;
        this.officialTotalInvestment = null;
        this.centreInvestment = null;
        this.provinceInvestment = null;
        this.cityInvestment = null;
        this.countyInvestment = null;
        this.townInvestment = null;
        this.foreignInvestment = null;
        this.unitSelfRaise = null;
        this.unitSelfOwner = null;
        this.bankAdvance = null;
        this.otherAdvance = null;
        this.otherSources = null;
        this.eiaApprovalType = null;
        this.eiaApprovalDocNo = null;
        this.eiaApprovalTime = null;
        this.eiaApprovalUnit = null;
        
    }
}
