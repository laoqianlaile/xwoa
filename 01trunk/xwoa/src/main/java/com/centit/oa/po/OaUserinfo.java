package com.centit.oa.po;

import java.util.Date;

import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaUserinfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String usercode;

    private String username;
    private String headpicturename;
    private byte[] headpicture;
    private String sex;
    private String placeofbirth;
    private String tolive;
    private String telephone;
    private String mobilephone;
    private String qq;
    private String msn;
    private String email;
    private String school;
    private String record;
    private String documenttype; // 字典项 DocumentType
    private String idno;
    private String mailingaddress;
    private String personalsignature;
    private String introduce;
    private String hobbies;
    private Date lastmodifydate;
    private Date createdate;
    private String userlevel;
    private Long levelnum;
    private String remark;
    private String picturename;
    private byte[] pictureim;
    private String workplace;
    private Date birthday;
    private String otherlinks;
    private FUserinfo fUserinfo;

    private String no;// 导出编号

    private String isusepicture;
    private String isshowsex;
    private String isshowplaceofbirth;
    private String isshowtolive;
    private String isshowtelephone;
    private String isshowmobilephone;
    private String isshowschool;
    private String isshowemail;
    private String isshowmsn;
    private String isshowqq;
    private String isshowintroduce;
    private String isshowhobbies;
    private String isshowidno;
    private String isshowrecord;

    private String party;
    private Date inpartytime;
    
    private FUserunit fUserunit;

    // Constructors
    /** default constructor */
    public OaUserinfo() {
    }

    /** minimal constructor */
    public OaUserinfo(String usercode, String username) {

        this.usercode = usercode;

        this.username = username;
    }

    /** full constructor */
    public OaUserinfo(String usercode, String username, String headpicturename,
            byte[] headpicture, String sex, String placeofbirth, String tolive,
            String telephone, String mobilephone, String qq, String msn,
            String email, String school, String record, String documenttype,
            String idno, String mailingaddress, String personalsignature,
            String introduce, String hobbies, Date lastmodifydate,
            Date createdate, String userlevel, Long levelnum, String remark,
            String picturename, byte[] pictureim, String workplace,
            Date birthday, String otherlinks, String isusepicture,
            String isshowsex, String isshowplaceofbirth, String isshowtolive,
            String isshowtelephone, String isshowmobilephone,
            String isshowschool, String isshowemail, String isshowmsn,
            String isshowqq, String isshowintroduce, String isshowhobbies,
            String isshowidno, String isshowrecord, String party,
            Date inpartytime) {

        this.usercode = usercode;

        this.username = username;
        this.headpicturename = headpicturename;
        this.headpicture = headpicture;
        this.sex = sex;
        this.placeofbirth = placeofbirth;
        this.tolive = tolive;
        this.telephone = telephone;
        this.mobilephone = mobilephone;
        this.qq = qq;
        this.msn = msn;
        this.email = email;
        this.school = school;
        this.record = record;
        this.documenttype = documenttype;
        this.idno = idno;
        this.mailingaddress = mailingaddress;
        this.personalsignature = personalsignature;
        this.introduce = introduce;
        this.hobbies = hobbies;
        this.lastmodifydate = lastmodifydate;
        this.createdate = createdate;
        this.userlevel = userlevel;
        this.levelnum = levelnum;
        this.remark = remark;
        this.picturename = picturename;
        this.pictureim = pictureim;
        this.workplace = workplace;
        this.birthday = birthday;
        this.otherlinks = otherlinks;
        this.isusepicture = isusepicture;
        this.isshowsex = isshowsex;
        this.isshowemail = isshowemail;
        this.isshowhobbies = isshowhobbies;
        this.isshowidno = isshowidno;
        this.isshowintroduce = isshowintroduce;
        this.isshowmobilephone = isshowmobilephone;
        this.isshowmsn = isshowmsn;
        this.isshowplaceofbirth = isshowplaceofbirth;
        this.isshowqq = isshowqq;
        this.isshowrecord = isshowrecord;
        this.isshowschool = isshowschool;
        this.isshowtelephone = isshowtelephone;
        this.isshowtolive = isshowtolive;
        this.party = party;
        this.inpartytime = inpartytime;
    }

    public OaUserinfo(String usercode, String username, String headpicturename,
            byte[] headpicture, String sex, String placeofbirth, String tolive,
            String telephone, String mobilephone, String qq, String msn,
            String email, String school, String record, String documenttype,
            String idno, String mailingaddress, String personalsignature,
            String introduce, String hobbies, Date lastmodifydate,
            Date createdate, String userlevel, Long levelnum, String remark,
            String picturename, byte[] pictureim, String workplace,
            Date birthday, String otherlinks, String isusepicture,
            String isshowsex, String isshowplaceofbirth, String isshowtolive,
            String isshowtelephone, String isshowmobilephone,
            String isshowschool, String isshowemail, String isshowmsn,
            String isshowqq, String isshowintroduce, String isshowhobbies,
            String isshowidno, String isshowrecord) {

        this.usercode = usercode;

        this.username = username;
        this.headpicturename = headpicturename;
        this.headpicture = headpicture;
        this.sex = sex;
        this.placeofbirth = placeofbirth;
        this.tolive = tolive;
        this.telephone = telephone;
        this.mobilephone = mobilephone;
        this.qq = qq;
        this.msn = msn;
        this.email = email;
        this.school = school;
        this.record = record;
        this.documenttype = documenttype;
        this.idno = idno;
        this.mailingaddress = mailingaddress;
        this.personalsignature = personalsignature;
        this.introduce = introduce;
        this.hobbies = hobbies;
        this.lastmodifydate = lastmodifydate;
        this.createdate = createdate;
        this.userlevel = userlevel;
        this.levelnum = levelnum;
        this.remark = remark;
        this.picturename = picturename;
        this.pictureim = pictureim;
        this.workplace = workplace;
        this.birthday = birthday;
        this.otherlinks = otherlinks;
        this.isusepicture = isusepicture;
        this.isshowsex = isshowsex;
        this.isshowemail = isshowemail;
        this.isshowhobbies = isshowhobbies;
        this.isshowidno = isshowidno;
        this.isshowintroduce = isshowintroduce;
        this.isshowmobilephone = isshowmobilephone;
        this.isshowmsn = isshowmsn;
        this.isshowplaceofbirth = isshowplaceofbirth;
        this.isshowqq = isshowqq;
        this.isshowrecord = isshowrecord;
        this.isshowschool = isshowschool;
        this.isshowtelephone = isshowtelephone;
        this.isshowtolive = isshowtolive;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getInpartytime() {
        return inpartytime;
    }

    public FUserunit getfUserunit() {
        return fUserunit;
    }

    public void setfUserunit(FUserunit fUserunit) {
        this.fUserunit = fUserunit;
    }

    public void setInpartytime(Date inpartytime) {
        this.inpartytime = inpartytime;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    // Property accessors

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadpicturename() {
        return this.headpicturename;
    }

    public void setHeadpicturename(String headpicturename) {
        this.headpicturename = headpicturename;
    }

    public byte[] getHeadpicture() {
        return this.headpicture;
    }

    public void setHeadpicture(byte[] headpicture) {
        this.headpicture = headpicture;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPlaceofbirth() {
        return this.placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getTolive() {
        return this.tolive;
    }

    public void setTolive(String tolive) {
        this.tolive = tolive;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return this.mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return this.msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getRecord() {
        return this.record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDocumenttype() {
        return this.documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getIdno() {
        return this.idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMailingaddress() {
        return this.mailingaddress;
    }

    public void setMailingaddress(String mailingaddress) {
        this.mailingaddress = mailingaddress;
    }

    public String getPersonalsignature() {
        return this.personalsignature;
    }

    public void setPersonalsignature(String personalsignature) {
        this.personalsignature = personalsignature;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHobbies() {
        return this.hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Date getLastmodifydate() {
        return this.lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUserlevel() {
        return this.userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public Long getLevelnum() {
        return this.levelnum;
    }

    public void setLevelnum(Long levelnum) {
        this.levelnum = levelnum;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicturename() {
        return this.picturename;
    }

    public FUserinfo getfUserinfo() {
        return fUserinfo;
    }

    public void setfUserinfo(FUserinfo fUserinfo) {
        this.fUserinfo = fUserinfo;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public byte[] getPictureim() {
        return this.pictureim;
    }

    public void setPictureim(byte[] pictureim) {
        this.pictureim = pictureim;
    }

    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getOtherlinks() {
        return this.otherlinks;
    }

    public void setOtherlinks(String otherlinks) {
        this.otherlinks = otherlinks;
    }

    public void copy(OaUserinfo other) {

        this.setUsercode(other.getUsercode());

        this.username = other.getUsername();
        this.headpicturename = other.getHeadpicturename();
        this.headpicture = other.getHeadpicture();
        this.sex = other.getSex();
        this.placeofbirth = other.getPlaceofbirth();
        this.tolive = other.getTolive();
        this.telephone = other.getTelephone();
        this.mobilephone = other.getMobilephone();
        this.qq = other.getQq();
        this.msn = other.getMsn();
        this.email = other.getEmail();
        this.school = other.getSchool();
        this.record = other.getRecord();
        this.documenttype = other.getDocumenttype();
        this.idno = other.getIdno();
        this.mailingaddress = other.getMailingaddress();
        this.personalsignature = other.getPersonalsignature();
        this.introduce = other.getIntroduce();
        this.hobbies = other.getHobbies();
        this.lastmodifydate = other.getLastmodifydate();
        this.createdate = other.getCreatedate();
        this.userlevel = other.getUserlevel();
        this.levelnum = other.getLevelnum();
        this.remark = other.getRemark();
        this.picturename = other.getPicturename();
        this.pictureim = other.getPictureim();
        this.workplace = other.getWorkplace();
        this.birthday = other.getBirthday();
        this.otherlinks = other.getOtherlinks();
        this.isusepicture = other.getIsusepicture();
        this.isshowemail = other.getIsshowemail();
        this.isshowhobbies = other.getIsshowhobbies();
        this.isshowidno = other.getIsshowidno();
        this.isshowintroduce = other.getIsshowintroduce();
        this.isshowmobilephone = other.getIsshowmobilephone();
        this.isshowmsn = other.getIsshowmsn();
        this.isshowplaceofbirth = other.getIsshowplaceofbirth();
        this.isshowqq = other.getIsshowqq();
        this.isshowrecord = other.getIsshowrecord();
        this.isshowschool = other.getIsshowschool();
        this.isshowsex = other.getIsshowsex();
        this.isshowtelephone = other.getIsshowtelephone();
        this.isshowtolive = other.getIsshowtolive();
        this.party = other.getParty();
        this.inpartytime = other.getInpartytime();

    }

    public void copyNotNullProperty(OaUserinfo other) {

        if (other.getUsercode() != null)
            this.setUsercode(other.getUsercode());

        if (other.getUsername() != null)
            this.username = other.getUsername();
        if (other.getHeadpicturename() != null)
            this.headpicturename = other.getHeadpicturename();
        if (other.getHeadpicture() != null)
            this.headpicture = other.getHeadpicture();
        if (other.getSex() != null)
            this.sex = other.getSex();
        if (other.getPlaceofbirth() != null)
            this.placeofbirth = other.getPlaceofbirth();
        if (other.getTolive() != null)
            this.tolive = other.getTolive();
        if (other.getTelephone() != null)
            this.telephone = other.getTelephone();
        if (other.getMobilephone() != null)
            this.mobilephone = other.getMobilephone();
        if (other.getQq() != null)
            this.qq = other.getQq();
        if (other.getMsn() != null)
            this.msn = other.getMsn();
        if (other.getEmail() != null)
            this.email = other.getEmail();
        if (other.getSchool() != null)
            this.school = other.getSchool();
        if (other.getRecord() != null)
            this.record = other.getRecord();
        if (other.getDocumenttype() != null)
            this.documenttype = other.getDocumenttype();
        if (other.getIdno() != null)
            this.idno = other.getIdno();
        if (other.getMailingaddress() != null)
            this.mailingaddress = other.getMailingaddress();
        if (other.getPersonalsignature() != null)
            this.personalsignature = other.getPersonalsignature();
        if (other.getIntroduce() != null)
            this.introduce = other.getIntroduce();
        if (other.getHobbies() != null)
            this.hobbies = other.getHobbies();
        if (other.getLastmodifydate() != null)
            this.lastmodifydate = other.getLastmodifydate();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getUserlevel() != null)
            this.userlevel = other.getUserlevel();
        if (other.getLevelnum() != null)
            this.levelnum = other.getLevelnum();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getPicturename() != null)
            this.picturename = other.getPicturename();
        if (other.getPictureim() != null)
            this.pictureim = other.getPictureim();
        if (other.getWorkplace() != null)
            this.workplace = other.getWorkplace();
        if (other.getBirthday() != null)
            this.birthday = other.getBirthday();
        if (other.getOtherlinks() != null)
            this.otherlinks = other.getOtherlinks();
        if (other.getIsusepicture() != null)
            this.isusepicture = other.getIsusepicture();
        if (other.getIsshowtolive() != null)
            this.isshowtolive = other.getIsshowtolive();
        if (other.getIsshowtelephone() != null)
            this.isshowtelephone = other.getIsshowtelephone();
        if (other.getIsshowemail() != null)
            this.isshowemail = other.getIsshowemail();
        if (other.getIsshowhobbies() != null)
            this.isshowhobbies = other.getIsshowhobbies();
        if (other.getIsshowidno() != null)
            this.isshowidno = other.getIsshowidno();
        if (other.getIsshowintroduce() != null)
            this.isshowintroduce = other.getIsshowintroduce();
        if (other.getIsshowmobilephone() != null)
            this.isshowmobilephone = other.getIsshowmobilephone();
        if (other.getIsshowmsn() != null)
            this.isshowmsn = other.getIsshowmsn();
        if (other.getIsshowplaceofbirth() != null)
            this.isshowplaceofbirth = other.getIsshowplaceofbirth();
        if (other.getIsshowqq() != null)
            this.isshowqq = other.getIsshowqq();
        if (other.getIsshowrecord() != null)
            this.isshowrecord = other.getIsshowrecord();
        if (other.getIsshowschool() != null)
            this.isshowschool = other.getIsshowschool();
        if (other.getIsshowsex() != null)
            this.isshowsex = other.getIsshowsex();
        if (other.getParty() != null)
            this.party = other.getParty();
        if (other.getInpartytime() != null)
            this.inpartytime = other.getInpartytime();
    }

    public void clearProperties() {

        this.username = null;
        this.headpicturename = null;
        this.headpicture = null;
        this.sex = null;
        this.placeofbirth = null;
        this.tolive = null;
        this.telephone = null;
        this.mobilephone = null;
        this.qq = null;
        this.msn = null;
        this.email = null;
        this.school = null;
        this.record = null;
        this.documenttype = null;
        this.idno = null;
        this.mailingaddress = null;
        this.personalsignature = null;
        this.introduce = null;
        this.hobbies = null;
        this.lastmodifydate = null;
        this.createdate = null;
        this.userlevel = null;
        this.levelnum = null;
        this.remark = null;
        this.picturename = null;
        this.pictureim = null;
        this.workplace = null;
        this.birthday = null;
        this.otherlinks = null;
        this.isshowemail = null;
        this.isshowhobbies = null;
        this.isshowidno = null;
        this.isshowintroduce = null;
        this.isshowmobilephone = null;
        this.isshowmsn = null;
        this.isshowplaceofbirth = null;
        this.isshowqq = null;
        this.isshowrecord = null;
        this.isshowschool = null;
        this.isshowsex = null;
        this.isshowtelephone = null;
        this.isshowtolive = null;
        this.isusepicture = null;
        this.party = null;
        this.inpartytime = null;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIsusepicture() {
        return isusepicture;
    }

    public void setIsusepicture(String isusepicture) {
        this.isusepicture = isusepicture;
    }

    public String getIsshowsex() {
        return isshowsex;
    }

    public void setIsshowsex(String isshowsex) {
        this.isshowsex = isshowsex;
    }

    public String getIsshowplaceofbirth() {
        return isshowplaceofbirth;
    }

    public void setIsshowplaceofbirth(String isshowplaceofbirth) {
        this.isshowplaceofbirth = isshowplaceofbirth;
    }

    public String getIsshowtolive() {
        return isshowtolive;
    }

    public void setIsshowtolive(String isshowtolive) {
        this.isshowtolive = isshowtolive;
    }

    public String getIsshowtelephone() {
        return isshowtelephone;
    }

    public void setIsshowtelephone(String isshowtelephone) {
        this.isshowtelephone = isshowtelephone;
    }

    public String getIsshowmobilephone() {
        return isshowmobilephone;
    }

    public void setIsshowmobilephone(String isshowmobilephone) {
        this.isshowmobilephone = isshowmobilephone;
    }

    public String getIsshowschool() {
        return isshowschool;
    }

    public void setIsshowschool(String isshowschool) {
        this.isshowschool = isshowschool;
    }

    public String getIsshowemail() {
        return isshowemail;
    }

    public void setIsshowemail(String isshowemail) {
        this.isshowemail = isshowemail;
    }

    public String getIsshowmsn() {
        return isshowmsn;
    }

    public void setIsshowmsn(String isshowmsn) {
        this.isshowmsn = isshowmsn;
    }

    public String getIsshowqq() {
        return isshowqq;
    }

    public void setIsshowqq(String isshowqq) {
        this.isshowqq = isshowqq;
    }

    public String getIsshowintroduce() {
        return isshowintroduce;
    }

    public void setIsshowintroduce(String isshowintroduce) {
        this.isshowintroduce = isshowintroduce;
    }

    public String getIsshowhobbies() {
        return isshowhobbies;
    }

    public void setIsshowhobbies(String isshowhobbies) {
        this.isshowhobbies = isshowhobbies;
    }

    public String getIsshowidno() {
        return isshowidno;
    }

    public void setIsshowidno(String isshowidno) {
        this.isshowidno = isshowidno;
    }

    public String getIsshowrecord() {
        return isshowrecord;
    }

    public void setIsshowrecord(String isshowrecord) {
        this.isshowrecord = isshowrecord;
    }

}
