package com.centit.sys.po;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.centit.core.utils.LabelValueBean;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * FUnitinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
// 机构信息表
public class FUnitinfo implements java.io.Serializable {

    // Fields

    private static final long serialVersionUID = -2538006375160615889L;

    private String unitcode; // 机构代码
    private String parentunit; // 上级机构代码
    private String unittype; // 机构类别
    private String isvalid; // 状态
    private String unitname;// 机构名称
    private String unitshortname;
    private String unitdesc; // 机构描述
    private String depno;
    private Long addrbookid; // 通讯主体id
    private Long unitorder; // 用户排序
    private String prefix;// 前缀
    private String unitdazt;

    private Date createDate = new Date();
    private Date lastModifyDate;
    /**
     * 这个变量只在系统数据字典缓存中有用
     */
    private Set<String> subUnits; // 所有子机构代码集合
    // private Set<String> subUsers; //所有下属用户代码集合
    private List<FUserunit> userUnits;

    // Constructors

    /** default constructor */
    public FUnitinfo() {
        subUnits = null;
        userUnits = null;
    }

    /** minimal constructor */
    public FUnitinfo(String unitcode, String unitstate, String unitname) {
        this.unitcode = unitcode;
        this.isvalid = unitstate;
        this.unitname = unitname;
        subUnits = null;
        userUnits = null;
    }

    public FUnitinfo(String unitcode, String parentunit, String unittype,
            String isvalid, String unitname, String unitshortname,
            String unitdesc, String depno, Long addrbookid, Long unitorder,
            String prefix, String unitdazt, Date createDate, Date lastModifyDate) {
        super();
        this.unitcode = unitcode;
        this.parentunit = parentunit;
        this.unittype = unittype;
        this.isvalid = isvalid;
        this.unitname = unitname;
        this.unitshortname = unitshortname;
        this.unitdesc = unitdesc;
        this.depno = depno;
        this.addrbookid = addrbookid;
        this.unitorder = unitorder;
        this.prefix = prefix;
        this.unitdazt = unitdazt;
        this.createDate = createDate;
        this.lastModifyDate = lastModifyDate;
    }

    /** full constructor */
    public FUnitinfo(String unitcode, String parentunit, String unittype,
            String unitstate, String unitname, String unitdesc,
            Long addrbookid, String unitshortname) {
        this.unitcode = unitcode;
        this.parentunit = parentunit;
        this.unittype = unittype;
        this.isvalid = unitstate;
        this.unitname = unitname;
        this.unitdesc = unitdesc;
        this.addrbookid = addrbookid;
        this.unitshortname = unitshortname;
        subUnits = null;
        userUnits = null;
    }

    // Property accessors
    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUnitshortname() {
        return unitshortname;
    }

    public void setUnitshortname(String unitshortname) {
        this.unitshortname = unitshortname;
    }

    public String getParentunit() {
        return this.parentunit;
    }

    public void setParentunit(String parentunit) {
        this.parentunit = parentunit;
    }

    public String getUnittype() {
        return this.unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getIsvalid() {
        return this.isvalid;
    }

    public void setIsvalid(String unitstate) {
        this.isvalid = unitstate;
    }

    public String getUnitname() {
        return this.unitname;
    }

    public String toString() {
        return this.unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitdesc() {
        return this.unitdesc;
    }

    public void setUnitdesc(String unitdesc) {
        this.unitdesc = unitdesc;
    }

    public Long getAddrbookid() {
        return addrbookid;
    }

    public void setAddrbookid(Long addrbookid) {
        this.addrbookid = addrbookid;
    }

    public Set<String> getSubUnits() {
        if (subUnits == null)
            subUnits = new HashSet<String>();
        return subUnits;
    }

    public void setSubUnits(Set<String> subUnits) {
        this.subUnits = subUnits;
    }

    public List<FUserunit> getSubUserUnits() {
        if (userUnits == null)
            userUnits = new ArrayList<FUserunit>();
        return userUnits;
    }

    public void setSubUserUnits(List<FUserunit> SUs) {
        this.userUnits = SUs;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public void copy(FUnitinfo other) {

        this.parentunit = other.getParentunit();
        this.unittype = other.getUnittype();
        this.isvalid = other.getIsvalid();
        this.unitname = other.getUnitname();
        this.unitshortname = other.getUnitshortname();
        this.unitdesc = other.getUnitdesc();
        this.addrbookid = other.getAddrbookid();
        this.depno = other.getDepno();
        this.prefix = other.getPrefix();
        this.unitorder = other.getUnitorder();
    }

    public void copyNotNullProperty(FUnitinfo other) {

        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getParentunit() != null)
            this.parentunit = other.getParentunit();
        if (other.getUnittype() != null)
            this.unittype = other.getUnittype();
        if (other.getIsvalid() != null)
            this.isvalid = other.getIsvalid();
        if (other.getUnitname() != null)
            this.unitname = other.getUnitname();
        if (other.getUnitdesc() != null)
            this.unitdesc = other.getUnitdesc();
        if (other.getAddrbookid() != null)
            this.addrbookid = other.getAddrbookid();
        if (other.getUnitshortname() != null)
            this.unitshortname = other.getUnitshortname();
        if (other.getDepno() != null)
            this.depno = other.getDepno();
        if (other.getPrefix() != null)
            this.prefix = other.getPrefix();
        if (other.getUnitorder() != null)
            this.unitorder = other.getUnitorder();
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

    public Long getUnitorder() {
        return unitorder;
    }

    public void setUnitorder(Long unitorder) {
        this.unitorder = unitorder;
    }

    public String display() {
        String unittype = this.unittype;
        List<LabelValueBean> labelValueBeans = CodeRepositoryUtil
                .getLabelValueBeans("UnitType");
        for (LabelValueBean labelValueBean : labelValueBeans) {
            if (labelValueBean.getValue().equals(unittype)) {
                unittype = labelValueBean.getLabel();
            }

            break;
        }

        return "部门信息 [部门名称="
                + CodeRepositoryUtil.getValue("unitcode", this.unitcode)
                + ", 上级部门="
                + CodeRepositoryUtil.getValue("unitcode", this.parentunit)
                + ", 部门类型=" + unittype + ", 部门状态="
                + ("T".equals(isvalid) ? "启用" : "禁用") + ", 部门备注="
                + (null == unitdesc ? "" : unitdesc) + "]";
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUnitdazt() {
        return unitdazt;
    }

    public void setUnitdazt(String unitdazt) {
        this.unitdazt = unitdazt;

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

}
