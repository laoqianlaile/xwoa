package com.centit.sys.po;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.centit.sys.service.CodeRepositoryUtil;

/**
 * FUserinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
// 系统用户信息表
public class FUserinfo implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = -1753127177790732963L;

    private String usercode; // 用户代码
    private String userpin; // 用户密码
    private String isvalid; // 状态
    private String loginname; // 用户登录名
    private String username; // 用户姓名

    private String userdesc; // 用户描述
    private Long logintimes; // 登录次数
    private Long sortid;//排序字段

    private Date activetime; // 最后一次登录时间
    private String loginip; // 登录地址
    private String usernamepinyin; //
    private Long addrbookid; // 通讯id
    private String regemail; // 注册email

    private String primaryUnit; // 用户的主机构，只有在数据字典中有效
    private List<FUserunit> userUnits;
    private Long userorder;     //用户排序

    // add by dl 网上申报
    private String userpinTwo;// 确认密码
    private String unitName;// 单位名称
    private String orgCode;// 组织机构代码
    private String unitAddress;// 单位地址
    private String unitZip;// 单位邮编
    private String areaCode;// 所在地区
    private String unitType;// 单位类型
    private String contact;// 联系人
    private String contactCodeType;// 联系人证件类型
    private String contactCode;// 联系人证件号码
    private String contactDep;// 联系人部门
    private String officePhone;// 办公电话
    private String contactPhone;// 联系人手机
    private String contactFax;// 联系人传真
    private String userState;// 0-离线 1-在线 2-离开
    private String userType;// 用户类型
    private String userkind; // 0系统用户 1申报用户
    private String datasource; // 数据来源系统:0内网 1外网 2纵向
    private String isupload; // 是否已同步
    private String isDefaultPwd ;
    
    private Date createDate = new Date();
    private Date lastModifyDate;
    public Long getSortid() {
        return sortid;
    }

    public void setSortid(Long sortid) {
        this.sortid = sortid;
    }
    public String getIsupload() {
        return isupload;
    }

    public void setIsupload(String isupload) {
        this.isupload = isupload;
    }

    public String getUserpinTwo() {
        if (userpinTwo == null) {
            this.userpinTwo = userpin;
        }
        return userpinTwo;
    }

    public void setUserpinTwo(String userpinTwo) {
        this.userpinTwo = userpinTwo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitZip() {
        return unitZip;
    }

    public void setUnitZip(String unitZip) {
        this.unitZip = unitZip;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactCodeType() {
        return contactCodeType;
    }

    public void setContactCodeType(String contactCodeType) {
        this.contactCodeType = contactCodeType;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public String getContactDep() {
        return contactDep;
    }

    public void setContactDep(String contactDep) {
        this.contactDep = contactDep;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getUserState() {
        if (userState == null) {
            return "0";
        }
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserkind() {
        return userkind;
    }

    public void setUserkind(String userkind) {
        this.userkind = userkind;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    /** default constructor */
    public FUserinfo() {
        userUnits = null;
        primaryUnit = null;
    }

    /** minimal constructor */
    public FUserinfo(String usercode, String userstate, String loginname,
            String username) {
        this.usercode = usercode;
        this.isvalid = userstate;
        this.username = username;
        this.loginname = loginname;
        userUnits = null;
        primaryUnit = null;
    }

    /** full constructor */
    public FUserinfo(String usercode, String userpin, String userstate,
            String loginname, String username, String userdesc,
            Long logintimes, Date activeime, String loginip, Long addrbookid,Long sortid,String isDefaultPwd) {
        this.usercode = usercode;
        this.userpin = userpin;
        this.isvalid = userstate;
        this.username = username;

        this.userdesc = userdesc;
        this.logintimes = logintimes;
        this.activetime = activeime;
        this.loginip = loginip;
        this.loginname = loginname;
        this.addrbookid = addrbookid;
        // userUnits=null;
        primaryUnit = null;
        this.sortid=sortid;
        this.isDefaultPwd = isDefaultPwd;
    }

    // Property accessors
    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserpin() {
        return this.userpin;
    }

    public void setUserpin(String userpin) {
        this.userpin = userpin;
    }

    /**
     * T:生效 F:无效
     * 
     * @return
     */
    public String getIsvalid() {
        if (this.isvalid == null)
            return "F";
        return this.isvalid;
    }

    /**
     * 
     * @param userstate
     *            T:生效 F:无效
     */
    public void setIsvalid(String userstate) {
        this.isvalid = userstate;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("FUserinfo");
        sb.append("{usercode='").append(usercode).append('\'');
        sb.append(", userpin='").append(userpin).append('\'');
        sb.append(", isvalid='").append(isvalid).append('\'');
        sb.append(", loginname='").append(loginname).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", userdesc='").append(userdesc).append('\'');
        sb.append(", logintimes=").append(logintimes);
        sb.append(", activetime=").append(activetime);
        sb.append(", loginip='").append(loginip).append('\'');
        sb.append(", usernamepinyin='").append(usernamepinyin).append('\'');
        sb.append(", addrbookid=").append(addrbookid);
        sb.append(", regemail='").append(regemail).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserdesc() {
        return this.userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public Long getLogintimes() {
        return this.logintimes;
    }

    public void setLogintimes(Long logintimes) {
        this.logintimes = logintimes;
    }

    public String getLoginip() {
        return this.loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public boolean isEnabled() {
        return "T".equals(isvalid);
    }

    public String getLoginname() {
        if (loginname == null)
            return "";
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Date getActivetime() {
        return activetime;
    }

    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    public String getUsernamepinyin() {
        return usernamepinyin;
    }

    public void setUsernamepinyin(String usernamepinyin) {
        this.usernamepinyin = usernamepinyin;
    }

    public Long getAddrbookid() {
        return addrbookid;
    }

    public void setAddrbookid(Long addrbookid) {
        this.addrbookid = addrbookid;
    }

    public void setRegemail(String regemail) {
        this.regemail = regemail;
    }

    public String getRegemail() {
        return regemail;
    }

    public String getPrimaryUnit() {
        return primaryUnit;
    }

    public void setPrimaryUnit(String primaryUnit) {
        this.primaryUnit = primaryUnit;
    }

    public List<FUserunit> getUserUnits() {
        if (userUnits == null)
            userUnits = new ArrayList<FUserunit>();
        return userUnits;
    }

    public void setUserUnits(List<FUserunit> userUnits) {
        this.userUnits = userUnits;
        if(userUnits!=null){
            for(FUserunit uu : userUnits){
                if(getPrimaryUnit() == null || "T".equals(uu.getIsprimary())){
                    setPrimaryUnit(uu.getUnitcode()); 
                 }
            }
        }
    }

    public void copy(FUserinfo other) {
        this.usercode = other.getUsercode();
        this.userpin = other.getUserpin();
        this.isvalid = other.getIsvalid();
        this.loginname = other.getLoginname();
        this.username = other.getUsername();
        this.userdesc = other.getUserdesc();
        this.logintimes = other.getLogintimes();
        this.activetime = other.getActivetime();
        this.loginip = other.getLoginip();
        this.addrbookid = other.getAddrbookid();
        this.regemail = other.getRegemail();
        this.userpinTwo = other.getUserpinTwo();
        this.unitName = other.getUnitName();
        this.orgCode = other.getOrgCode();
        this.unitAddress = other.getUnitAddress();
        this.unitZip = other.getUnitZip();
        this.areaCode = other.getAreaCode();
        this.unitType = other.getUnitType();
        this.contact = other.getContact();
        this.contactCodeType = other.getContactCodeType();
        this.contactCode = other.getContactCode();
        this.contactDep = other.getContactDep();
        this.officePhone = other.getOfficePhone();
        this.contactPhone = other.getContactPhone();
        this.contactFax = other.getContactFax();
        this.userState = other.getUserState();
        this.userType = other.getUserType();
        this.userkind = other.getUserkind();
        this.datasource = other.getDatasource();
        this.isupload = other.getIsupload();
        this.userorder = other.getUserorder();
        this.sortid=other.getSortid();
        this.isDefaultPwd=other.getIsDefaultPwd();
    }

    public void copyNotNullProperty(FUserinfo other) {
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getUserpin() != null)
            this.userpin = other.getUserpin();
        if (other.getIsvalid() != null)
            this.isvalid = other.getIsvalid();
        if (other.getLoginname() != null)
            this.loginname = other.getLoginname();
        if (other.getUsername() != null)
            this.username = other.getUsername();
        if (other.getUserdesc() != null)
            this.userdesc = other.getUserdesc();
        if (other.getLogintimes() != null)
            this.logintimes = other.getLogintimes();
        if (other.getActivetime() != null)
            this.activetime = other.getActivetime();
        if (other.getLoginip() != null)
            this.loginip = other.getLoginip();
        if (other.getAddrbookid() != null)
            this.addrbookid = other.getAddrbookid();
        if (other.getRegemail() != null)
            this.regemail = other.getRegemail();
        if (other.getUserpinTwo() != null)
            this.userpinTwo = other.getUserpinTwo();
        if (other.getUnitName() != null)
            this.unitName = other.getUnitName();
        if (other.getOrgCode() != null)
            this.orgCode = other.getOrgCode();
        if (other.getUnitAddress() != null)
            this.unitAddress = other.getUnitAddress();
        if (other.getUnitZip() != null)
            this.unitZip = other.getUnitZip();
        if (other.getAreaCode() != null)
            this.areaCode = other.getAreaCode();
        if (other.getUnitType() != null)
            this.unitType = other.getUnitType();
        if (other.getContact() != null)
            this.contact = other.getContact();
        if (other.getContactCodeType() != null)
            this.contactCodeType = other.getContactCodeType();
        if (other.getContactCode() != null)
            this.contactCode = other.getContactCode();
        if (other.getContactDep() != null)
            this.contactDep = other.getContactDep();
        if (other.getOfficePhone() != null)
            this.officePhone = other.getOfficePhone();
        if (other.getContactPhone() != null)
            this.contactPhone = other.getContactPhone();
        if (other.getContactFax() != null)
            this.contactFax = other.getContactFax();
        if (other.getUserState() != null)
            this.userState = other.getUserState();
        if (other.getUserType() != null)
            this.userType = other.getUserType();
        if (other.getUserkind() != null)
            this.userkind = other.getUserkind();
        if (other.getDatasource() != null)
            this.datasource = other.getDatasource();
        if (other.getIsupload() != null)
            this.isupload = other.getIsupload();
        if(other.getUserorder()!= null )
            this.userorder = other.getUserorder();
        if(other.getSortid()!=null)
            this.sortid=other.getSortid();
        if(other.getIsDefaultPwd()!=null)
            this.isDefaultPwd=other.getIsDefaultPwd();
    }

    public void clearProperties() {
        this.usercode = null;
        this.userpin = null;
        this.isvalid = null;
        this.loginname = null;
        this.username = null;
        this.userdesc = null;
        this.logintimes = null;
        this.activetime = null;
        this.loginip = null;
        this.addrbookid = null;
        this.regemail = null;
        this.userpinTwo = null;
        this.unitName = null;
        this.orgCode = null;
        this.unitAddress = null;
        this.unitZip = null;
        this.areaCode = null;
        this.unitType = null;
        this.contact = null;
        this.contactCodeType = null;
        this.contactCode = null;
        this.contactDep = null;
        this.officePhone = null;
        this.contactPhone = null;
        this.contactFax = null;
        this.userState = null;
        this.userType = null;
        this.userkind = null;
        this.datasource = null;
        this.userorder = null;
        this.sortid=null;
        this.isDefaultPwd=null;
    }

    public static String[] field2Name(Field[] f) {
        String[] name = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            name[i] = f[i].getName();
        }
        return name;
    }

    public static Object[] field2Value(Field[] f, FUserinfo o)
            throws IllegalArgumentException, IllegalAccessException {
        Object[] value = new Object[f.length];
        for (int i = 0; i < f.length; i++) {
            value[i] = f[i].get(o);
        }
        return value;
    }

    public Long getUserorder() {
        //if(userorder ==null)
            //return 10000l;
        return userorder;
    }

    public void setUserorder(Long userorder) {
        this.userorder = userorder;
    }
    
    public String display(){
        StringBuilder sb = new StringBuilder();
        sb.append("用户信息:[");
        sb.append("登陆名=").append(loginname);
        sb.append(", 用户名=").append(CodeRepositoryUtil.getValue("usercode", this.usercode));
        sb.append(", 用户描述=").append(null == userdesc ? "" : userdesc);
        sb.append(", isvalid=").append("T".equals(isvalid) ? "启用" : "禁用");
        sb.append("]");
        return sb.toString();
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getIsDefaultPwd() {
        return isDefaultPwd;
    }

    public void setIsDefaultPwd(String isDefaultPwd) {
        this.isDefaultPwd = isDefaultPwd;
    }
    
}
