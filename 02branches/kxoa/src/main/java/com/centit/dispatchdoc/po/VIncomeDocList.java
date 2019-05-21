package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VIncomeDocList implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;
    private String usercode;
    private String incomeDocNo;
    private String incomeDocTitle;
    private String incomeDeptType;
    private String incomeDeptName;
    private Date incomeDate;
    private String secretDegree;
    private String operateState;
    private Date createDate;
    private Date updateDate;
    private String optId;
    private String flowCode;
    private Long flowInstId;
    private String transaffairname;
    private String bizstate;
    private String biztype;
    private String orgcode;
    private String orgname;
    private String powerid;
    private String powername;
    private String solvestatus;
    private Date solvetime;
    private String solvenote;
    private Date createdate;
    private String acceptArchiveNo;
    private String nodename;//查看页面 办理步骤
    private String bizusernames;//查看页面 办理人员
    
    private String overdueType; // 临时存放该办件是否超期——N：未超期，I：即将超期，O：已超期
    
    private String warntype;
    
    private String belongUnitcode;
    // Constructors
    /** default constructor */
    public VIncomeDocList() {
    }

    /** minimal constructor */
    public VIncomeDocList(String djId) {

        this.djId = djId;
    }
    public String getDjId() {
        return this.djId;
    }
    
    public VIncomeDocList(String djId, String usercode, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String incomeDeptName, Date incomeDate, String secretDegree,
            String operateState, Date createDate, Date updateDate,
            String optId, String flowCode, Long flowInstId,
            String transaffairname, String bizstate, String biztype,
            String orgcode, String orgname, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate2, String acceptArchiveNo, String nodename,
            String bizusernames) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.incomeDeptName = incomeDeptName;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.operateState = operateState;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        createdate = createdate2;
        this.acceptArchiveNo = acceptArchiveNo;
        this.nodename = nodename;
        this.bizusernames = bizusernames;
    }
    public VIncomeDocList(String djId, String usercode, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String incomeDeptName, Date incomeDate, String secretDegree,
            String operateState, Date createDate, Date updateDate,
            String optId, String flowCode, Long flowInstId,
            String transaffairname, String bizstate, String biztype,
            String orgcode, String orgname, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate2, String acceptArchiveNo, String nodename,
            String bizusernames,String belongUnitcode) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.incomeDeptName = incomeDeptName;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.operateState = operateState;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        createdate = createdate2;
        this.acceptArchiveNo = acceptArchiveNo;
        this.nodename = nodename;
        this.bizusernames = bizusernames;
        this.belongUnitcode=belongUnitcode;
    }

    /** full constructor */
    public VIncomeDocList(String djId, String usercode, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String incomeDeptName, Date incomeDate, String secretDegree,
            String operateState, Date createDate, Date updateDate,
            String optId, String flowCode, Long flowInstId,
            String transaffairname, String bizstate, String biztype,
            String orgcode, String orgname, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate, String acceptArchiveNo) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.incomeDeptName = incomeDeptName;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.operateState = operateState;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createdate = createdate;
        this.acceptArchiveNo = acceptArchiveNo;
    }
    /** full constructor */
    public VIncomeDocList(String djId, String usercode, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String incomeDeptName, Date incomeDate, String secretDegree,
            String operateState, Date createDate, Date updateDate,
            String optId, String flowCode, Long flowInstId,
            String transaffairname, String bizstate, String biztype,
            String orgcode, String orgname, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate, String acceptArchiveNo,String belongUnitcode) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.incomeDeptName = incomeDeptName;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.operateState = operateState;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createdate = createdate;
        this.acceptArchiveNo = acceptArchiveNo;
        this.belongUnitcode=belongUnitcode;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public String getIncomeDocNo() {
        return this.incomeDocNo;
    }

    public void setIncomeDocNo(String incomeDocNo) {
        this.incomeDocNo = incomeDocNo;
    }

    public String getIncomeDocTitle() {
        return this.incomeDocTitle;
    }

    public void setIncomeDocTitle(String incomeDocTitle) {
        this.incomeDocTitle = incomeDocTitle;
    }

    public String getIncomeDeptType() {
        return this.incomeDeptType;
    }

    public void setIncomeDeptType(String incomeDeptType) {
        this.incomeDeptType = incomeDeptType;
    }

    public String getIncomeDeptName() {
        return this.incomeDeptName;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public Date getIncomeDate() {
        return this.incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getSecretDegree() {
        return this.secretDegree;
    }

    public void setSecretDegree(String secretDegree) {
        this.secretDegree = secretDegree;
    }

    public String getOperateState() {
        return this.operateState;
    }

    public void setOperateState(String operateState) {
        this.operateState = operateState;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOptId() {
        return this.optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getFlowCode() {
        return this.flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getFlowInstId() {
        return this.flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getBizstate() {
        return this.bizstate;
    }

    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }

    public String getBiztype() {
        return this.biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public String getOrgcode() {
        return this.orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return this.orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPowerid() {
        return this.powerid;
    }

    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

    public String getPowername() {
        return this.powername;
    }

    public void setPowername(String powername) {
        this.powername = powername;
    }

    public String getSolvestatus() {
        return this.solvestatus;
    }

    public void setSolvestatus(String solvestatus) {
        this.solvestatus = solvestatus;
    }

    public Date getSolvetime() {
        return this.solvetime;
    }

    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }

    public String getSolvenote() {
        return this.solvenote;
    }

    public void setSolvenote(String solvenote) {
        this.solvenote = solvenote;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getAcceptArchiveNo() {
        return this.acceptArchiveNo;
    }

    public void setAcceptArchiveNo(String acceptArchiveNo) {
        this.acceptArchiveNo = acceptArchiveNo;
    }

    public void copy(VIncomeDocList other) {

        this.setDjId(other.getDjId());

        this.incomeDocNo = other.getIncomeDocNo();
        this.incomeDocTitle = other.getIncomeDocTitle();
        this.incomeDeptType = other.getIncomeDeptType();
        this.incomeDeptName = other.getIncomeDeptName();
        this.incomeDate = other.getIncomeDate();
        this.secretDegree = other.getSecretDegree();
        this.operateState = other.getOperateState();
        this.createDate = other.getCreateDate();
        this.updateDate = other.getUpdateDate();
        this.optId = other.getOptId();
        this.flowCode = other.getFlowCode();
        this.flowInstId = other.getFlowInstId();
        this.transaffairname = other.getTransaffairname();
        this.bizstate = other.getBizstate();
        this.biztype = other.getBiztype();
        this.orgcode = other.getOrgcode();
        this.orgname = other.getOrgname();
        this.powerid = other.getPowerid();
        this.powername = other.getPowername();
        this.solvestatus = other.getSolvestatus();
        this.solvetime = other.getSolvetime();
        this.solvenote = other.getSolvenote();
        this.createdate = other.getCreatedate();
        this.acceptArchiveNo = other.getAcceptArchiveNo();
        this.usercode = other.getUsercode();
        this.nodename=other.getNodename();
        this.bizusernames=other.getBizusernames();
        this.belongUnitcode=other.getBelongUnitcode();
    }

    public void copyNotNullProperty(VIncomeDocList other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getIncomeDocNo() != null)
            this.incomeDocNo = other.getIncomeDocNo();
        if (other.getIncomeDocTitle() != null)
            this.incomeDocTitle = other.getIncomeDocTitle();
        if (other.getIncomeDeptType() != null)
            this.incomeDeptType = other.getIncomeDeptType();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getIncomeDate() != null)
            this.incomeDate = other.getIncomeDate();
        if (other.getSecretDegree() != null)
            this.secretDegree = other.getSecretDegree();
        if (other.getOperateState() != null)
            this.operateState = other.getOperateState();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getUpdateDate() != null)
            this.updateDate = other.getUpdateDate();
        if (other.getOptId() != null)
            this.optId = other.getOptId();
        if (other.getFlowCode() != null)
            this.flowCode = other.getFlowCode();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getBiztype() != null)
            this.biztype = other.getBiztype();
        if (other.getOrgcode() != null)
            this.orgcode = other.getOrgcode();
        if (other.getOrgname() != null)
            this.orgname = other.getOrgname();
        if (other.getPowerid() != null)
            this.powerid = other.getPowerid();
        if (other.getPowername() != null)
            this.powername = other.getPowername();
        if (other.getSolvestatus() != null)
            this.solvestatus = other.getSolvestatus();
        if (other.getSolvetime() != null)
            this.solvetime = other.getSolvetime();
        if (other.getSolvenote() != null)
            this.solvenote = other.getSolvenote();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getAcceptArchiveNo() != null)
            this.acceptArchiveNo = other.getAcceptArchiveNo();
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getNodename() != null)
            this.nodename=other.getNodename();
        if (other.getBizusernames() != null)
            this.bizusernames=other.getBizusernames();
        if(other.getBelongUnitcode()!=null)
            this.belongUnitcode=other.getBelongUnitcode();
    }

    public void clearProperties() {

        this.incomeDocNo = null;
        this.incomeDocTitle = null;
        this.incomeDeptType = null;
        this.incomeDeptName = null;
        this.incomeDate = null;
        this.secretDegree = null;
        this.operateState = null;
        this.createDate = null;
        this.updateDate = null;
        this.optId = null;
        this.flowCode = null;
        this.flowInstId = null;
        this.transaffairname = null;
        this.bizstate = null;
        this.biztype = null;
        this.orgcode = null;
        this.orgname = null;
        this.powerid = null;
        this.powername = null;
        this.solvestatus = null;
        this.solvetime = null;
        this.solvenote = null;
        this.createdate = null;
        this.acceptArchiveNo = null;
        this.usercode =null;
        this.nodename=null;
        this.bizusernames=null;
        this.belongUnitcode=null;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getBizusernames() {
        return bizusernames;
    }

    public void setBizusernames(String bizusernames) {
        this.bizusernames = bizusernames;
    }

    public String getOverdueType() {
        return overdueType;
    }

    public void setOverdueType(String overdueType) {
        this.overdueType = overdueType;
    }

    public String getWarntype() {
        return warntype;
    }

    public void setWarntype(String warntype) {
        this.warntype = warntype;
    }

    public String getBelongUnitcode() {
        return belongUnitcode;
    }

    public void setBelongUnitcode(String belongUnitcode) {
        this.belongUnitcode = belongUnitcode;
    }
    
    
}
