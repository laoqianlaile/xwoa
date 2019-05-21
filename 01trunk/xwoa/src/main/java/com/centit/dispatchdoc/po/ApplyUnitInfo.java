package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class ApplyUnitInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String unitcode;

    private String loginname;
    private String isvalid;
    private String auditor;
    private Date auditDate;
    private String auditstate;
    private String contactemail;
    private String contactfax;
    private String contactphone;
    private String officephone;
    private String contactdep;
    private String contactcode;
    private String contactcodetype;
    private String contact;
    private String inchargeunit;
    private String unitzip;
    private String unitaddress;
    private String unittype;
    private String orgcode;
    private String unitname;
    private String areacode;
    private String unitarea;
    private Date registeTime;
    private String openusertype;
    private String usertype;
    private Date lastmodifydate;
    private String userword;
    private Long userorder;
    private String regcellphone;
    private String regemail;
    private String loginip;
    private String userdesc;
    private String username;

    // Constructors
    /** default constructor */
    public ApplyUnitInfo() {
    }

    /** minimal constructor */
    public ApplyUnitInfo(String unitcode, String isvalid, String username) {

        this.unitcode = unitcode;

        this.isvalid = isvalid;
        this.username = username;
    }

    /** full constructor */
    public ApplyUnitInfo(String unitcode, String loginname, String isvalid,
            String auditor, Date auditDate, String auditstate,
            String contactemail, String contactfax, String contactphone,
            String officephone, String contactdep, String contactcode,
            String contactcodetype, String contact, String inchargeunit,
            String unitzip, String unitaddress, String unittype,
            String orgcode, String unitname, String areacode, String unitarea,
            Date registeTime, String openusertype, String usertype,
            Date lastmodifydate, String userword, Long userorder,
            String regcellphone, String regemail, String loginip,
            String userdesc, String username) {

        this.unitcode = unitcode;

        this.loginname = loginname;
        this.isvalid = isvalid;
        this.auditor = auditor;
        this.auditDate = auditDate;
        this.auditstate = auditstate;
        this.contactemail = contactemail;
        this.contactfax = contactfax;
        this.contactphone = contactphone;
        this.officephone = officephone;
        this.contactdep = contactdep;
        this.contactcode = contactcode;
        this.contactcodetype = contactcodetype;
        this.contact = contact;
        this.inchargeunit = inchargeunit;
        this.unitzip = unitzip;
        this.unitaddress = unitaddress;
        this.unittype = unittype;
        this.orgcode = orgcode;
        this.unitname = unitname;
        this.areacode = areacode;
        this.unitarea = unitarea;
        this.registeTime = registeTime;
        this.openusertype = openusertype;
        this.usertype = usertype;
        this.lastmodifydate = lastmodifydate;
        this.userword = userword;
        this.userorder = userorder;
        this.regcellphone = regcellphone;
        this.regemail = regemail;
        this.loginip = loginip;
        this.userdesc = userdesc;
        this.username = username;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    // Property accessors

    public String getLoginname() {
        return this.loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIsvalid() {
        return this.isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getAuditor() {
        return this.auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditDate() {
        return this.auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditstate() {
        return this.auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate;
    }

    public String getContactemail() {
        return this.contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getContactfax() {
        return this.contactfax;
    }

    public void setContactfax(String contactfax) {
        this.contactfax = contactfax;
    }

    public String getContactphone() {
        return this.contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getOfficephone() {
        return this.officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    public String getContactdep() {
        return this.contactdep;
    }

    public void setContactdep(String contactdep) {
        this.contactdep = contactdep;
    }

    public String getContactcode() {
        return this.contactcode;
    }

    public void setContactcode(String contactcode) {
        this.contactcode = contactcode;
    }

    public String getContactcodetype() {
        return this.contactcodetype;
    }

    public void setContactcodetype(String contactcodetype) {
        this.contactcodetype = contactcodetype;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInchargeunit() {
        return this.inchargeunit;
    }

    public void setInchargeunit(String inchargeunit) {
        this.inchargeunit = inchargeunit;
    }

    public String getUnitzip() {
        return this.unitzip;
    }

    public void setUnitzip(String unitzip) {
        this.unitzip = unitzip;
    }

    public String getUnitaddress() {
        return this.unitaddress;
    }

    public void setUnitaddress(String unitaddress) {
        this.unitaddress = unitaddress;
    }

    public String getUnittype() {
        return this.unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getOrgcode() {
        return this.orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getUnitname() {
        return this.unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getAreacode() {
        return this.areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getUnitarea() {
        return this.unitarea;
    }

    public void setUnitarea(String unitarea) {
        this.unitarea = unitarea;
    }

    public Date getRegisteTime() {
        return this.registeTime;
    }

    public void setRegisteTime(Date registeTime) {
        this.registeTime = registeTime;
    }

    public String getOpenusertype() {
        return this.openusertype;
    }

    public void setOpenusertype(String openusertype) {
        this.openusertype = openusertype;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Date getLastmodifydate() {
        return this.lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public String getUserword() {
        return this.userword;
    }

    public void setUserword(String userword) {
        this.userword = userword;
    }

    public Long getUserorder() {
        return this.userorder;
    }

    public void setUserorder(Long userorder) {
        this.userorder = userorder;
    }

    public String getRegcellphone() {
        return this.regcellphone;
    }

    public void setRegcellphone(String regcellphone) {
        this.regcellphone = regcellphone;
    }

    public String getRegemail() {
        return this.regemail;
    }

    public void setRegemail(String regemail) {
        this.regemail = regemail;
    }

    public String getLoginip() {
        return this.loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getUserdesc() {
        return this.userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void copy(ApplyUnitInfo other) {

        this.setUnitcode(other.getUnitcode());

        this.loginname = other.getLoginname();
        this.isvalid = other.getIsvalid();
        this.auditor = other.getAuditor();
        this.auditDate = other.getAuditDate();
        this.auditstate = other.getAuditstate();
        this.contactemail = other.getContactemail();
        this.contactfax = other.getContactfax();
        this.contactphone = other.getContactphone();
        this.officephone = other.getOfficephone();
        this.contactdep = other.getContactdep();
        this.contactcode = other.getContactcode();
        this.contactcodetype = other.getContactcodetype();
        this.contact = other.getContact();
        this.inchargeunit = other.getInchargeunit();
        this.unitzip = other.getUnitzip();
        this.unitaddress = other.getUnitaddress();
        this.unittype = other.getUnittype();
        this.orgcode = other.getOrgcode();
        this.unitname = other.getUnitname();
        this.areacode = other.getAreacode();
        this.unitarea = other.getUnitarea();
        this.registeTime = other.getRegisteTime();
        this.openusertype = other.getOpenusertype();
        this.usertype = other.getUsertype();
        this.lastmodifydate = other.getLastmodifydate();
        this.userword = other.getUserword();
        this.userorder = other.getUserorder();
        this.regcellphone = other.getRegcellphone();
        this.regemail = other.getRegemail();
        this.loginip = other.getLoginip();
        this.userdesc = other.getUserdesc();
        this.username = other.getUsername();

    }

    public void copyNotNullProperty(ApplyUnitInfo other) {

        if (other.getUnitcode() != null)
            this.setUnitcode(other.getUnitcode());

        if (other.getLoginname() != null)
            this.loginname = other.getLoginname();
        if (other.getIsvalid() != null)
            this.isvalid = other.getIsvalid();
        if (other.getAuditor() != null)
            this.auditor = other.getAuditor();
        if (other.getAuditDate() != null)
            this.auditDate = other.getAuditDate();
        if (other.getAuditstate() != null)
            this.auditstate = other.getAuditstate();
        if (other.getContactemail() != null)
            this.contactemail = other.getContactemail();
        if (other.getContactfax() != null)
            this.contactfax = other.getContactfax();
        if (other.getContactphone() != null)
            this.contactphone = other.getContactphone();
        if (other.getOfficephone() != null)
            this.officephone = other.getOfficephone();
        if (other.getContactdep() != null)
            this.contactdep = other.getContactdep();
        if (other.getContactcode() != null)
            this.contactcode = other.getContactcode();
        if (other.getContactcodetype() != null)
            this.contactcodetype = other.getContactcodetype();
        if (other.getContact() != null)
            this.contact = other.getContact();
        if (other.getInchargeunit() != null)
            this.inchargeunit = other.getInchargeunit();
        if (other.getUnitzip() != null)
            this.unitzip = other.getUnitzip();
        if (other.getUnitaddress() != null)
            this.unitaddress = other.getUnitaddress();
        if (other.getUnittype() != null)
            this.unittype = other.getUnittype();
        if (other.getOrgcode() != null)
            this.orgcode = other.getOrgcode();
        if (other.getUnitname() != null)
            this.unitname = other.getUnitname();
        if (other.getAreacode() != null)
            this.areacode = other.getAreacode();
        if (other.getUnitarea() != null)
            this.unitarea = other.getUnitarea();
        if (other.getRegisteTime() != null)
            this.registeTime = other.getRegisteTime();
        if (other.getOpenusertype() != null)
            this.openusertype = other.getOpenusertype();
        if (other.getUsertype() != null)
            this.usertype = other.getUsertype();
        if (other.getLastmodifydate() != null)
            this.lastmodifydate = other.getLastmodifydate();
        if (other.getUserword() != null)
            this.userword = other.getUserword();
        if (other.getUserorder() != null)
            this.userorder = other.getUserorder();
        if (other.getRegcellphone() != null)
            this.regcellphone = other.getRegcellphone();
        if (other.getRegemail() != null)
            this.regemail = other.getRegemail();
        if (other.getLoginip() != null)
            this.loginip = other.getLoginip();
        if (other.getUserdesc() != null)
            this.userdesc = other.getUserdesc();
        if (other.getUsername() != null)
            this.username = other.getUsername();

    }

    public void clearProperties() {

        this.loginname = null;
        this.isvalid = null;
        this.auditor = null;
        this.auditDate = null;
        this.auditstate = null;
        this.contactemail = null;
        this.contactfax = null;
        this.contactphone = null;
        this.officephone = null;
        this.contactdep = null;
        this.contactcode = null;
        this.contactcodetype = null;
        this.contact = null;
        this.inchargeunit = null;
        this.unitzip = null;
        this.unitaddress = null;
        this.unittype = null;
        this.orgcode = null;
        this.unitname = null;
        this.areacode = null;
        this.unitarea = null;
        this.registeTime = null;
        this.openusertype = null;
        this.usertype = null;
        this.lastmodifydate = null;
        this.userword = null;
        this.userorder = null;
        this.regcellphone = null;
        this.regemail = null;
        this.loginip = null;
        this.userdesc = null;
        this.username = null;

    }
}
