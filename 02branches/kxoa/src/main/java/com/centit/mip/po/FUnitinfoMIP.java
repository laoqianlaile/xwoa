package com.centit.mip.po;

import com.centit.sys.po.FUnitinfo;
import com.centit.webservice.util.JsonUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * FUnitinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
// 机构信息表
public class FUnitinfoMIP implements java.io.Serializable {


    private static final long serialVersionUID = -2538006375160615889L;
    @Expose
    @Since(2.0)
    private String unitcode; // 机构代码
    @Expose
    @Since(2.0)
    private String parentunit; // 上级机构代码
    @Expose
    @Since(2.0)
    private String unittype; // 机构类别
    @Expose
    @Since(2.0)
    private String isvalid; // 状态
    @Expose
    @Since(2.0)
    private String unitname;// 机构名称
    @Expose
    @Since(2.0)
    private String unitdesc; // 机构描述
    @Expose
    @Since(2.0)
    private String unitshortname;
    @Expose
    @Since(2.0)
    private String depno;
    @Expose
    @Since(2.0)
    private Long unitorder; // 用户排序
  
    
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

   

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

   

    public Long getUnitorder() {
        return unitorder;
    }

    public void setUnitorder(Long unitorder) {
        this.unitorder = unitorder;
    }
    public void copyNotNullProperty(FUnitinfo other) {
        this.setUnitcode(JsonUtil.replaceNullString(other.getUnitcode()));
        this.setParentunit(JsonUtil
                .replaceNullString(other.getParentunit()));
        this.setUnittype(JsonUtil
                .replaceNullString(other.getUnittype()));
        this.setIsvalid(JsonUtil
                .replaceNullString(other.getIsvalid()));
        this.setUnitname(JsonUtil
                .replaceNullString(other.getUnitname()));
        this.setUnitdesc(JsonUtil
                .replaceNullString(other.getUnitdesc()));
        this.setUnitshortname(JsonUtil
                .replaceNullString(other.getUnitshortname()));
        this.setDepno(JsonUtil
                .replaceNullString(other.getDepno()));
        this.setUnitorder(other.getUnitorder());
    }
  
}
