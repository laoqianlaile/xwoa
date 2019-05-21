package com.centit.sys.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VHiunitinfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String unitcode;

    private String topunitcode;
    private String unittype;
    private String parentunit;
    private String isvalid;
    private String unitname;
    private String unitdesc;
    private String unitshortname;
    private String addrbookid;
    private String depno;
    private String unitorder;
    private String unitword;
    private String unitgrade;
    private String unitdazt;
    private String prefix;
    private String hi_level;
    
    private String topunitno;

    public String getTopunitno() {
        return topunitno;
    }

    public void setTopunitno(String topunitno) {
        this.topunitno = topunitno;
    }

    // Constructors
    /** default constructor */
    public VHiunitinfo() {
    }

    /** minimal constructor */
    public VHiunitinfo(String unitcode) {

        this.unitcode = unitcode;

    }

    /** full constructor */
    public VHiunitinfo(String unitcode, String topunitcode, String unittype,
            String parentunit, String isvalid, String unitname,
            String unitdesc, String unitshortname, String addrbookid,
            String depno, String unitorder, String unitword, String unitgrade,
            String unitdazt, String prefix, String hi_level) {

        this.unitcode = unitcode;

        this.topunitcode = topunitcode;
        this.unittype = unittype;
        this.parentunit = parentunit;
        this.isvalid = isvalid;
        this.unitname = unitname;
        this.unitdesc = unitdesc;
        this.unitshortname = unitshortname;
        this.addrbookid = addrbookid;
        this.depno = depno;
        this.unitorder = unitorder;
        this.unitword = unitword;
        this.unitgrade = unitgrade;
        this.unitdazt = unitdazt;
        this.prefix = prefix;
        this.hi_level = hi_level;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    // Property accessors

    public String getTopunitcode() {
        return this.topunitcode;
    }

    public void setTopunitcode(String topunitcode) {
        this.topunitcode = topunitcode;
    }

    public String getUnittype() {
        return this.unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getParentunit() {
        return this.parentunit;
    }

    public void setParentunit(String parentunit) {
        this.parentunit = parentunit;
    }

    public String getIsvalid() {
        return this.isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getUnitname() {
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

    public String getUnitshortname() {
        return this.unitshortname;
    }

    public void setUnitshortname(String unitshortname) {
        this.unitshortname = unitshortname;
    }

    public String getAddrbookid() {
        return this.addrbookid;
    }

    public void setAddrbookid(String addrbookid) {
        this.addrbookid = addrbookid;
    }

    public String getDepno() {
        return this.depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public String getUnitorder() {
        return this.unitorder;
    }

    public void setUnitorder(String unitorder) {
        this.unitorder = unitorder;
    }

    public String getUnitword() {
        return this.unitword;
    }

    public void setUnitword(String unitword) {
        this.unitword = unitword;
    }

    public String getUnitgrade() {
        return this.unitgrade;
    }

    public void setUnitgrade(String unitgrade) {
        this.unitgrade = unitgrade;
    }

    public String getUnitdazt() {
        return this.unitdazt;
    }

    public void setUnitdazt(String unitdazt) {
        this.unitdazt = unitdazt;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHi_level() {
        return this.hi_level;
    }

    public void setHi_level(String hi_level) {
        this.hi_level = hi_level;
    }

    public void copy(VHiunitinfo other) {

        this.setUnitcode(other.getUnitcode());

        this.topunitcode = other.getTopunitcode();
        this.unittype = other.getUnittype();
        this.parentunit = other.getParentunit();
        this.isvalid = other.getIsvalid();
        this.unitname = other.getUnitname();
        this.unitdesc = other.getUnitdesc();
        this.unitshortname = other.getUnitshortname();
        this.addrbookid = other.getAddrbookid();
        this.depno = other.getDepno();
        this.unitorder = other.getUnitorder();
        this.unitword = other.getUnitword();
        this.unitgrade = other.getUnitgrade();
        this.unitdazt = other.getUnitdazt();
        this.prefix = other.getPrefix();
        this.hi_level = other.getHi_level();

    }

    public void copyNotNullProperty(VHiunitinfo other) {

        if (other.getUnitcode() != null)
            this.setUnitcode(other.getUnitcode());

        if (other.getTopunitcode() != null)
            this.topunitcode = other.getTopunitcode();
        if (other.getUnittype() != null)
            this.unittype = other.getUnittype();
        if (other.getParentunit() != null)
            this.parentunit = other.getParentunit();
        if (other.getIsvalid() != null)
            this.isvalid = other.getIsvalid();
        if (other.getUnitname() != null)
            this.unitname = other.getUnitname();
        if (other.getUnitdesc() != null)
            this.unitdesc = other.getUnitdesc();
        if (other.getUnitshortname() != null)
            this.unitshortname = other.getUnitshortname();
        if (other.getAddrbookid() != null)
            this.addrbookid = other.getAddrbookid();
        if (other.getDepno() != null)
            this.depno = other.getDepno();
        if (other.getUnitorder() != null)
            this.unitorder = other.getUnitorder();
        if (other.getUnitword() != null)
            this.unitword = other.getUnitword();
        if (other.getUnitgrade() != null)
            this.unitgrade = other.getUnitgrade();
        if (other.getUnitdazt() != null)
            this.unitdazt = other.getUnitdazt();
        if (other.getPrefix() != null)
            this.prefix = other.getPrefix();
        if (other.getHi_level() != null)
            this.hi_level = other.getHi_level();

    }

    public void clearProperties() {

        this.topunitcode = null;
        this.unittype = null;
        this.parentunit = null;
        this.isvalid = null;
        this.unitname = null;
        this.unitdesc = null;
        this.unitshortname = null;
        this.addrbookid = null;
        this.depno = null;
        this.unitorder = null;
        this.unitword = null;
        this.unitgrade = null;
        this.unitdazt = null;
        this.prefix = null;
        this.hi_level = null;

    }
}
