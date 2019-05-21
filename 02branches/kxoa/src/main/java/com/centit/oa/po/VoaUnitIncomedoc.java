package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */

public class VoaUnitIncomedoc implements java.io.Serializable {
    private static final long serialVersionUID = -5489303426894156607L;
    private VoaUnitIncomedocId cid;


    private String  incomedDocTitle;
    private Date  incomeDate;
    private String  acceptarchiveno;
    private String  incomeDeptName;
    private String  incomeDocNo;
    private String  id;
    private String  unitcode;
    private String  isopen;
    private Date  createtime;
    private Date  lastmodifytime;
    private String  updateuser;
    private String incomeDeptType;
    private String criticalLevel;//缓急程度    
    private String incomeDocType;//来文文件分类
    
    public VoaUnitIncomedoc() {
    }
    
    public VoaUnitIncomedoc(VoaUnitIncomedocId cid, String incomedDocTitle,
            Date incomeDate, String acceptarchiveno, String incomeDeptName,
            String incomeDocNo, String id, String unitcode, String isopen,
            Date createtime, Date lastmodifytime, String updateuser) {
        super();
        this.cid = cid;
        this.incomedDocTitle = incomedDocTitle;
        this.incomeDate = incomeDate;
        this.acceptarchiveno = acceptarchiveno;
        this.incomeDeptName = incomeDeptName;
        this.incomeDocNo = incomeDocNo;
        this.id = id;
        this.unitcode = unitcode;
        this.isopen = isopen;
        this.createtime = createtime;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
    }

    public VoaUnitIncomedocId getCid() {
        return cid;
    }
    public String getNo() {
        if(this.cid==null)
            this.cid = new VoaUnitIncomedocId();
        return this.cid.getNo();
    }
    
    public void setNo(String no) {
        if(this.cid==null)
            this.cid = new VoaUnitIncomedocId();
        this.cid.setNo(no);
    }
  
    public String getUsercode() {
        if(this.cid==null)
            this.cid = new VoaUnitIncomedocId();
        return this.cid.getUsercode();
    }
    
    public void setUsercode(String usercode) {
        if(this.cid==null)
            this.cid = new VoaUnitIncomedocId();
        this.cid.setUsercode(usercode);
    }
    public void setCid(VoaUnitIncomedocId cid) {
        this.cid = cid;
    }
    public String getIncomedDocTitle() {
        return incomedDocTitle;
    }
    public void setIncomedDocTitle(String incomedDocTitle) {
        this.incomedDocTitle = incomedDocTitle;
    }
    public Date getIncomeDate() {
        return incomeDate;
    }
    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }
    public String getAcceptarchiveno() {
        return acceptarchiveno;
    }
    public void setAcceptarchiveno(String acceptarchiveno) {
        this.acceptarchiveno = acceptarchiveno;
    }
    public String getIncomeDeptName() {
        return incomeDeptName;
    }
    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }
    public String getIncomeDocNo() {
        return incomeDocNo;
    }
    public void setIncomeDocNo(String incomeDocNo) {
        this.incomeDocNo = incomeDocNo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUnitcode() {
        return unitcode;
    }
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    public String getIsopen() {
        return isopen;
    }
    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getLastmodifytime() {
        return lastmodifytime;
    }
    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
    public String getUpdateuser() {
        return updateuser;
    }
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }
    public String getIncomeDeptType() {
        return incomeDeptType;
    }

    public void setIncomeDeptType(String incomeDeptType) {
        this.incomeDeptType = incomeDeptType;
    }

    public String getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public String getIncomeDocType() {
        return incomeDocType;
    }

    public void setIncomeDocType(String incomeDocType) {
        this.incomeDocType = incomeDocType;
    }

    public VoaUnitIncomedoc(VoaUnitIncomedocId cid, String incomedDocTitle,
            Date incomeDate, String acceptarchiveno, String incomeDeptName,
            String incomeDocNo, String id, String unitcode, String isopen,
            Date createtime, Date lastmodifytime, String updateuser,
            String incomeDeptType, String criticalLevel, String incomeDocType) {
        super();
        this.cid = cid;
        this.incomedDocTitle = incomedDocTitle;
        this.incomeDate = incomeDate;
        this.acceptarchiveno = acceptarchiveno;
        this.incomeDeptName = incomeDeptName;
        this.incomeDocNo = incomeDocNo;
        this.id = id;
        this.unitcode = unitcode;
        this.isopen = isopen;
        this.createtime = createtime;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.incomeDeptType = incomeDeptType;
        this.criticalLevel = criticalLevel;
        this.incomeDocType = incomeDocType;
    }

    public void copy(VoaUnitIncomedoc other){
        
        this.setNo(other.getNo());  
        this.setUsercode(other.getUsercode());
  
        this.incomedDocTitle = other.getIncomedDocTitle();
        this.incomeDate = other.getIncomeDate();
        this.acceptarchiveno = other.getAcceptarchiveno();
        this.incomeDeptName = other.getIncomeDeptName();
        this.incomeDocNo = other.getIncomeDocNo();
        this.id = other.getId();
        this.unitcode = other.getUnitcode();
        this.isopen = other.getIsopen();
        this.createtime = other.getCreatetime();
        this.lastmodifytime = other.getLastmodifytime();
        this.updateuser = other.getUpdateuser();
        this.incomeDeptType=other.getIncomeDeptType();
        this.incomeDocType=other.getIncomeDocType();
        this.criticalLevel=other.getCriticalLevel();

    }
    public void clearProperties(){
        
        this.incomedDocTitle = null;
        this.incomeDate = null;
        this.acceptarchiveno = null;
        this.incomeDeptName = null;
        this.incomeDocNo = null;
        this.id = null;
        this.unitcode = null;
        this.isopen = null;
        this.createtime = null;
        this.lastmodifytime = null;
        this.updateuser = null;
        this.incomeDeptType=null;
        this.incomeDocType=null;
        this.criticalLevel=null;

    }
   
}
