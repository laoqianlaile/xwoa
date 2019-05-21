package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Dataenterprise implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long enterpriseid;

    private String applicant;
    private String applicantPaperType;
    private String applicantPaperNumber;
    private String applicantPhone;
    private String applicantMobile;
    private String applicantAddress;
    private String applicantZipcode;
    private String applicantEmail;
    private String unitType;
    private String corpDomain;
    private String regType;
    private String fax;
    private String linkman;
    private String linkmanName;
    private String linkmanPaperType;
    private String linkmanPaperCode;
    private String linkmanPhone;
    private String linkmanMobile;
    private String linkmanAddress;
    private String linkmanZipcode;
    private String linkmanEmail;
    private String isInuse;
    private Date lastModdate;

    // Constructors
    /** default constructor */
    public Dataenterprise() {
    }

    /** minimal constructor */
    public Dataenterprise(Long enterpriseid, String applicant,
            String applicantPaperType, String applicantPaperNumber,
            String linkmanName, String linkmanPaperType,
            String linkmanPaperCode, String linkmanPhone, String isInuse,
            Date lastModdate) {

        this.enterpriseid = enterpriseid;

        this.applicant = applicant;
        this.applicantPaperType = applicantPaperType;
        this.applicantPaperNumber = applicantPaperNumber;
        this.linkmanName = linkmanName;
        this.linkmanPaperType = linkmanPaperType;
        this.linkmanPaperCode = linkmanPaperCode;
        this.linkmanPhone = linkmanPhone;
        this.isInuse = isInuse;
        this.lastModdate = lastModdate;
    }

    /** full constructor */
    public Dataenterprise(Long enterpriseid, String applicant,
            String applicantPaperType, String applicantPaperNumber,
            String applicantPhone, String applicantMobile,
            String applicantAddress, String applicantZipcode,
            String applicantEmail, String unitType, String corpDomain,
            String regType, String fax, String linkman, String linkmanName,
            String linkmanPaperType, String linkmanPaperCode,
            String linkmanPhone, String linkmanMobile, String linkmanAddress,
            String linkmanZipcode, String linkmanEmail, String isInuse,
            Date lastModdate) {

        this.enterpriseid = enterpriseid;

        this.applicant = applicant;
        this.applicantPaperType = applicantPaperType;
        this.applicantPaperNumber = applicantPaperNumber;
        this.applicantPhone = applicantPhone;
        this.applicantMobile = applicantMobile;
        this.applicantAddress = applicantAddress;
        this.applicantZipcode = applicantZipcode;
        this.applicantEmail = applicantEmail;
        this.unitType = unitType;
        this.corpDomain = corpDomain;
        this.regType = regType;
        this.fax = fax;
        this.linkman = linkman;
        this.linkmanName = linkmanName;
        this.linkmanPaperType = linkmanPaperType;
        this.linkmanPaperCode = linkmanPaperCode;
        this.linkmanPhone = linkmanPhone;
        this.linkmanMobile = linkmanMobile;
        this.linkmanAddress = linkmanAddress;
        this.linkmanZipcode = linkmanZipcode;
        this.linkmanEmail = linkmanEmail;
        this.isInuse = isInuse;
        this.lastModdate = lastModdate;
    }

    public Long getEnterpriseid() {
        return this.enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    // Property accessors

    public String getApplicant() {
        return this.applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantPaperType() {
        return this.applicantPaperType;
    }

    public void setApplicantPaperType(String applicantPaperType) {
        this.applicantPaperType = applicantPaperType;
    }

    public String getApplicantPaperNumber() {
        return this.applicantPaperNumber;
    }

    public void setApplicantPaperNumber(String applicantPaperNumber) {
        this.applicantPaperNumber = applicantPaperNumber;
    }

    public String getApplicantPhone() {
        return this.applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantMobile() {
        return this.applicantMobile;
    }

    public void setApplicantMobile(String applicantMobile) {
        this.applicantMobile = applicantMobile;
    }

    public String getApplicantAddress() {
        return this.applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getApplicantZipcode() {
        return this.applicantZipcode;
    }

    public void setApplicantZipcode(String applicantZipcode) {
        this.applicantZipcode = applicantZipcode;
    }

    public String getApplicantEmail() {
        return this.applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getUnitType() {
        return this.unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getCorpDomain() {
        return this.corpDomain;
    }

    public void setCorpDomain(String corpDomain) {
        this.corpDomain = corpDomain;
    }

    public String getRegType() {
        return this.regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLinkman() {
        return this.linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanName() {
        return this.linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPaperType() {
        return this.linkmanPaperType;
    }

    public void setLinkmanPaperType(String linkmanPaperType) {
        this.linkmanPaperType = linkmanPaperType;
    }

    public String getLinkmanPaperCode() {
        return this.linkmanPaperCode;
    }

    public void setLinkmanPaperCode(String linkmanPaperCode) {
        this.linkmanPaperCode = linkmanPaperCode;
    }

    public String getLinkmanPhone() {
        return this.linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanMobile() {
        return this.linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile;
    }

    public String getLinkmanAddress() {
        return this.linkmanAddress;
    }

    public void setLinkmanAddress(String linkmanAddress) {
        this.linkmanAddress = linkmanAddress;
    }

    public String getLinkmanZipcode() {
        return this.linkmanZipcode;
    }

    public void setLinkmanZipcode(String linkmanZipcode) {
        this.linkmanZipcode = linkmanZipcode;
    }

    public String getLinkmanEmail() {
        return this.linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

    public String getIsInuse() {
        return this.isInuse;
    }

    public void setIsInuse(String isInuse) {
        this.isInuse = isInuse;
    }

    public Date getLastModdate() {
        return this.lastModdate;
    }

    public void setLastModdate(Date lastModdate) {
        this.lastModdate = lastModdate;
    }

    public void copy(Dataenterprise other) {

        this.setEnterpriseid(other.getEnterpriseid());

        this.applicant = other.getApplicant();
        this.applicantPaperType = other.getApplicantPaperType();
        this.applicantPaperNumber = other.getApplicantPaperNumber();
        this.applicantPhone = other.getApplicantPhone();
        this.applicantMobile = other.getApplicantMobile();
        this.applicantAddress = other.getApplicantAddress();
        this.applicantZipcode = other.getApplicantZipcode();
        this.applicantEmail = other.getApplicantEmail();
        this.unitType = other.getUnitType();
        this.corpDomain = other.getCorpDomain();
        this.regType = other.getRegType();
        this.fax = other.getFax();
        this.linkman = other.getLinkman();
        this.linkmanName = other.getLinkmanName();
        this.linkmanPaperType = other.getLinkmanPaperType();
        this.linkmanPaperCode = other.getLinkmanPaperCode();
        this.linkmanPhone = other.getLinkmanPhone();
        this.linkmanMobile = other.getLinkmanMobile();
        this.linkmanAddress = other.getLinkmanAddress();
        this.linkmanZipcode = other.getLinkmanZipcode();
        this.linkmanEmail = other.getLinkmanEmail();
        this.isInuse = other.getIsInuse();
        this.lastModdate = other.getLastModdate();

    }

    public void copyNotNullProperty(Dataenterprise other) {

        if (other.getEnterpriseid() != null)
            this.setEnterpriseid(other.getEnterpriseid());

        if (other.getApplicant() != null)
            this.applicant = other.getApplicant();
        if (other.getApplicantPaperType() != null)
            this.applicantPaperType = other.getApplicantPaperType();
        if (other.getApplicantPaperNumber() != null)
            this.applicantPaperNumber = other.getApplicantPaperNumber();
        if (other.getApplicantPhone() != null)
            this.applicantPhone = other.getApplicantPhone();
        if (other.getApplicantMobile() != null)
            this.applicantMobile = other.getApplicantMobile();
        if (other.getApplicantAddress() != null)
            this.applicantAddress = other.getApplicantAddress();
        if (other.getApplicantZipcode() != null)
            this.applicantZipcode = other.getApplicantZipcode();
        if (other.getApplicantEmail() != null)
            this.applicantEmail = other.getApplicantEmail();
        if (other.getUnitType() != null)
            this.unitType = other.getUnitType();
        if (other.getCorpDomain() != null)
            this.corpDomain = other.getCorpDomain();
        if (other.getRegType() != null)
            this.regType = other.getRegType();
        if (other.getFax() != null)
            this.fax = other.getFax();
        if (other.getLinkman() != null)
            this.linkman = other.getLinkman();
        if (other.getLinkmanName() != null)
            this.linkmanName = other.getLinkmanName();
        if (other.getLinkmanPaperType() != null)
            this.linkmanPaperType = other.getLinkmanPaperType();
        if (other.getLinkmanPaperCode() != null)
            this.linkmanPaperCode = other.getLinkmanPaperCode();
        if (other.getLinkmanPhone() != null)
            this.linkmanPhone = other.getLinkmanPhone();
        if (other.getLinkmanMobile() != null)
            this.linkmanMobile = other.getLinkmanMobile();
        if (other.getLinkmanAddress() != null)
            this.linkmanAddress = other.getLinkmanAddress();
        if (other.getLinkmanZipcode() != null)
            this.linkmanZipcode = other.getLinkmanZipcode();
        if (other.getLinkmanEmail() != null)
            this.linkmanEmail = other.getLinkmanEmail();
        if (other.getIsInuse() != null)
            this.isInuse = other.getIsInuse();
        if (other.getLastModdate() != null)
            this.lastModdate = other.getLastModdate();

    }

    public void clearProperties() {

        this.applicant = null;
        this.applicantPaperType = null;
        this.applicantPaperNumber = null;
        this.applicantPhone = null;
        this.applicantMobile = null;
        this.applicantAddress = null;
        this.applicantZipcode = null;
        this.applicantEmail = null;
        this.unitType = null;
        this.corpDomain = null;
        this.regType = null;
        this.fax = null;
        this.linkman = null;
        this.linkmanName = null;
        this.linkmanPaperType = null;
        this.linkmanPaperCode = null;
        this.linkmanPhone = null;
        this.linkmanMobile = null;
        this.linkmanAddress = null;
        this.linkmanZipcode = null;
        this.linkmanEmail = null;
        this.isInuse = null;
        this.lastModdate = null;

    }
}
