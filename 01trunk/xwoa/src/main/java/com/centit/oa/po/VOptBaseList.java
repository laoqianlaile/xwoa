package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VOptBaseList implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String usercode;
    private String nodename;
    private String optId;
    private String flowCode;
    private Long flowInstId;
    private String transAffairNo;
    private String transaffairname;
    private String transAffairQueryKey;
    private String bizstate;
    private String biztype;
    private String orgcode;
    private String orgname;
    private String headunitcode;
    private String headusercode;
    private String powerid;
    private String powername;
    private String solvestatus;
    private Date solvetime;
    private String solvenote;
    private Date createdate;
    private String creater;
    private String sendArchiveNo;
    private String acceptArchiveNo;

    private String applyItemType;
    private String itemType;
    private String unitname;
    private String bizusernames;// 当前办理人员
    private String bizusercodes;// 当前办理人员usercode
    private String itemTypeName;//类型名字
    
    private String gwNature;//公文性质 上级部门01 本部门02
    
    private String flowSupervise;//是否督办件
    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    // Constructors
    /** default constructor */
    public VOptBaseList() {
    }

    /** minimal constructor */
    public VOptBaseList(String djId) {

        this.djId = djId;

    }
    public VOptBaseList(String djId, String usercode, String nodename,
            String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate, String creater, String sendArchiveNo,
            String acceptArchiveNo, String applyItemType, String itemType,
            String bizusernames, String bizusercodes) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.nodename = nodename;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transAffairNo = transAffairNo;
        this.transaffairname = transaffairname;
        this.transAffairQueryKey = transAffairQueryKey;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.headunitcode = headunitcode;
        this.headusercode = headusercode;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createdate = createdate;
        this.creater = creater;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.applyItemType = applyItemType;
        this.itemType = itemType;
        this.bizusernames = bizusernames;
        this.bizusercodes = bizusercodes;
    }
    
    public VOptBaseList(String djId, String usercode, String nodename,
            String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate, String creater, String sendArchiveNo,
            String acceptArchiveNo, String applyItemType, String itemType,
            String unitname) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.nodename = nodename;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transAffairNo = transAffairNo;
        this.transaffairname = transaffairname;
        this.transAffairQueryKey = transAffairQueryKey;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.headunitcode = headunitcode;
        this.headusercode = headusercode;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createdate = createdate;
        this.creater = creater;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.applyItemType = applyItemType;
        this.itemType = itemType;
        this.unitname = unitname;
    }

    public VOptBaseList(String djId, String nodename, String transaffairname,
            String bizstate, Date createdate, String creater, String itemType) {
        super();
        this.djId = djId;
        this.nodename = nodename;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.createdate = createdate;
        this.creater = creater;
        this.itemType = itemType;
    }

    /** full constructor */
    public VOptBaseList(String djId, String usercode, String optId,
            String flowCode, Long flowInstId, String transAffairNo,
            String transaffairname, String transAffairQueryKey,
            String bizstate, String biztype, String orgcode, String orgname,
            String headunitcode, String headusercode, String powerid,
            String powername, String solvestatus, Date solvetime,
            String solvenote, Date createdate, String sendArchiveNo,
            String acceptArchiveNo, String creater, String nodename,
            String applyItemType, String itemType) {

        this.djId = djId;

        this.usercode = usercode;
        this.optId = optId;
        this.flowCode = flowCode;
        this.flowInstId = flowInstId;
        this.transAffairNo = transAffairNo;
        this.transaffairname = transaffairname;
        this.transAffairQueryKey = transAffairQueryKey;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.headunitcode = headunitcode;
        this.headusercode = headusercode;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createdate = createdate;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;

        this.creater = creater;
        this.nodename = nodename;

        this.applyItemType = applyItemType;
        this.itemType = itemType;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public String getUsercode() {
        return this.usercode;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getBizusernames() {
        return bizusernames;
    }

    public void setBizusernames(String bizusernames) {
        this.bizusernames = bizusernames;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getTransAffairNo() {
        return this.transAffairNo;
    }

    public void setTransAffairNo(String transAffairNo) {
        this.transAffairNo = transAffairNo;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getTransAffairQueryKey() {
        return this.transAffairQueryKey;
    }

    public void setTransAffairQueryKey(String transAffairQueryKey) {
        this.transAffairQueryKey = transAffairQueryKey;
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

    public String getHeadunitcode() {
        return this.headunitcode;
    }

    public void setHeadunitcode(String headunitcode) {
        this.headunitcode = headunitcode;
    }

    public String getHeadusercode() {
        return this.headusercode;
    }

    public void setHeadusercode(String headusercode) {
        this.headusercode = headusercode;
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

    public String getSendArchiveNo() {
        return this.sendArchiveNo;
    }

    public void setSendArchiveNo(String sendArchiveNo) {
        this.sendArchiveNo = sendArchiveNo;
    }

    public String getAcceptArchiveNo() {
        return this.acceptArchiveNo;
    }

    public void setAcceptArchiveNo(String acceptArchiveNo) {
        this.acceptArchiveNo = acceptArchiveNo;
    }
    
    
    
    public String getBizusercodes() {
        return bizusercodes;
    }

    public void setBizusercodes(String bizusercodes) {
        this.bizusercodes = bizusercodes;
    }

    public void copy(VOptBaseList other) {

        this.setDjId(other.getDjId());

        this.usercode = other.getUsercode();
        this.optId = other.getOptId();
        this.flowCode = other.getFlowCode();
        this.flowInstId = other.getFlowInstId();
        this.transAffairNo = other.getTransAffairNo();
        this.transaffairname = other.getTransaffairname();
        this.transAffairQueryKey = other.getTransAffairQueryKey();
        this.bizstate = other.getBizstate();
        this.biztype = other.getBiztype();
        this.orgcode = other.getOrgcode();
        this.orgname = other.getOrgname();
        this.headunitcode = other.getHeadunitcode();
        this.headusercode = other.getHeadusercode();
        this.powerid = other.getPowerid();
        this.powername = other.getPowername();
        this.solvestatus = other.getSolvestatus();
        this.solvetime = other.getSolvetime();
        this.solvenote = other.getSolvenote();
        this.createdate = other.getCreatedate();
        this.sendArchiveNo = other.getSendArchiveNo();
        this.acceptArchiveNo = other.getAcceptArchiveNo();
        this.unitname = other.getUnitname();
        this.bizusernames=other.getBizusernames();
    }

    public void copyNotNullProperty(VOptBaseList other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getOptId() != null)
            this.optId = other.getOptId();
        if (other.getFlowCode() != null)
            this.flowCode = other.getFlowCode();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getTransAffairNo() != null)
            this.transAffairNo = other.getTransAffairNo();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getTransAffairQueryKey() != null)
            this.transAffairQueryKey = other.getTransAffairQueryKey();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getBiztype() != null)
            this.biztype = other.getBiztype();
        if (other.getOrgcode() != null)
            this.orgcode = other.getOrgcode();
        if (other.getOrgname() != null)
            this.orgname = other.getOrgname();
        if (other.getHeadunitcode() != null)
            this.headunitcode = other.getHeadunitcode();
        if (other.getHeadusercode() != null)
            this.headusercode = other.getHeadusercode();
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
        if (other.getSendArchiveNo() != null)
            this.sendArchiveNo = other.getSendArchiveNo();
        if (other.getAcceptArchiveNo() != null)
            this.acceptArchiveNo = other.getAcceptArchiveNo();
        if (other.getUnitname() != null)
            this.unitname = other.getUnitname();
        if (other.getBizusernames() != null)
        this.bizusernames=other.getBizusernames();
    }

    public void clearProperties() {

        this.usercode = null;
        this.optId = null;
        this.flowCode = null;
        this.flowInstId = null;
        this.transAffairNo = null;
        this.transaffairname = null;
        this.transAffairQueryKey = null;
        this.bizstate = null;
        this.biztype = null;
        this.orgcode = null;
        this.orgname = null;
        this.headunitcode = null;
        this.headusercode = null;
        this.powerid = null;
        this.powername = null;
        this.solvestatus = null;
        this.solvetime = null;
        this.solvenote = null;
        this.createdate = null;
        this.sendArchiveNo = null;
        this.acceptArchiveNo = null;
        this.unitname = null;
        this.bizusernames=null;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getApplyItemType() {
        return applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getGwNature() {
        return gwNature;
    }

    public void setGwNature(String gwNature) {
        this.gwNature = gwNature;
    }

    public String getFlowSupervise() {
        return flowSupervise;
    }

    public void setFlowSupervise(String flowSupervise) {
        this.flowSupervise = flowSupervise;
    }

}
