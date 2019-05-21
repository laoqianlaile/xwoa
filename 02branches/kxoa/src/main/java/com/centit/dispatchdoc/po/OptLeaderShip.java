package com.centit.dispatchdoc.po;

import java.util.Date;

public class OptLeaderShip implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private String no;
    private String djId;
    private String leaderNo;
    private String leaderName;
    private String unitCode;
    private String unitName;
    private String leaderNote;
    private String createUserCode;
    private Date createDate;
    private Date shipDate;
    
    
    public Date getShipDate() {
        return shipDate;
    }
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
    public OptLeaderShip() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public OptLeaderShip(String no, String djId, String leaderNo,
            String leaderName, String unitCode, String unitName,
            String leaderNote, String createUserCode, Date createDate,
            Date shipDate) {
        super();
        this.no = no;
        this.djId = djId;
        this.leaderNo = leaderNo;
        this.leaderName = leaderName;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.leaderNote = leaderNote;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
        this.shipDate = shipDate;
    }
    public OptLeaderShip(String no, String djId, String leaderNo,
            String leaderName, String unitCode, String unitName,
            String leaderNote, String createUserCode, Date createDate) {
        super();
        this.no = no;
        this.djId = djId;
        this.leaderNo = leaderNo;
        this.leaderName = leaderName;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.leaderNote = leaderNote;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getLeaderNo() {
        return leaderNo;
    }
    public void setLeaderNo(String leaderNo) {
        this.leaderNo = leaderNo;
    }
    public String getLeaderName() {
        return leaderName;
    }
    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getLeaderNote() {
        return leaderNote;
    }
    public void setLeaderNote(String leaderNote) {
        this.leaderNote = leaderNote;
    }
    public String getCreateUserCode() {
        return createUserCode;
    }
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public void copy(OptLeaderShip other) {
        this.djId = other.getDjId();
        this.no = other.getNo();
        this.createDate = other.getCreateDate();
        this.createUserCode = other.getCreateUserCode();
        this.leaderName = other.getLeaderName();
        this.leaderNo = other.getLeaderNo();
        this.leaderNote = other.getLeaderNote();
        this.unitCode = other.getUnitCode();
        this.unitName = other.getUnitName();
        this.shipDate = other.getShipDate();
    }
    public void copyNotNullProperty(OptLeaderShip other) {
        if(other.getDjId()!= null)
            this.djId = other.getDjId();
        if(other.getNo()!= null)
            this.no = other.getNo();
        if(other.getCreateDate()!= null)
            this.createDate = other.getCreateDate();
        if(other.getCreateUserCode()!= null)
            this.createUserCode = other.getCreateUserCode();
        if(other.getLeaderName()!= null)
            this.leaderName = other.getLeaderName();
        if(other.getLeaderNo()!= null)
            this.leaderNo = other.getLeaderNo();
        if(other.getLeaderNote()!= null)
            this.leaderNote = other.getLeaderNote();
        if(other.getUnitCode()!= null)
            this.unitCode = other.getUnitCode();
        if(other.getUnitName()!= null)
            this.unitName = other.getUnitName();
        if(other.getShipDate()!= null)
            this.shipDate = other.getShipDate();
    }
    public void clearProperties() {

        this.djId = null;
        this.no = null;
        this.createDate = null;
        this.createUserCode = null;
        this.leaderName = null;
        this.leaderNo = null;
        this.leaderNote = null;
        this.unitCode = null;
        this.unitName = null;
        this.shipDate = null;
       
    }
}
