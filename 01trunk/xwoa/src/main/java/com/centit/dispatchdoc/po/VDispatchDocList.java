package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VDispatchDocList implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String internalNo;
    private String itemId;
    private String dispatchDocNo;
    private String dispatchDocTitle;
    private String dispatchFileType;
    private String dispatchDocType;
    private String dispatchCanOpen;
    private String dispatchOpenType;
    private String isUnionDispatch;
    private String unionOthers;
    private String isCountersign;
    private String dispatchDocSummary;
    private Date draftDate;
    private String optUnitName;
    private String draftUserName;
    private String secretsDegree;
    private Long dispatchCopies;
    private String mainNotifyUnit;
    private String otherUnits;
    private Date createDate;
    private Date updateDate;
    private String criticalLevel;
    private String emergencyDegree;
    private String commissionCanOpen;
    private String unionDispatchUnits;
    private String recordId;
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
    private String usercode;
    private Date createdate;
    private String sendArchiveNo;
    private String acceptArchiveNo;
    private String dispatchUser;
    
    private String nodename;//查看页面 办理步骤
    private String bizusernames;//查看页面 办理人员
    private String unitcode;
    
    private String overdueType; // 临时存放该办件是否超期——N：未超期，I：即将超期，O：已超期
    
    private String warntype;
    
    private String username;
    
    private Date dispatchDate;

   

    // Constructors
    /** default constructor */
    public VDispatchDocList() {
    }

    /** minimal constructor */
    public VDispatchDocList(String djId) {

        this.djId = djId;

    }
    public String getDjId() {
        return this.djId;
    }
    
    public VDispatchDocList(String djId, String internalNo, String itemId,
            String dispatchDocNo, String dispatchDocTitle,
            String dispatchFileType, String dispatchDocType,
            String dispatchCanOpen, String dispatchOpenType,
            String isUnionDispatch, String unionOthers, String isCountersign,
            String dispatchDocSummary, Date draftDate, String optUnitName,
            String draftUserName, String secretsDegree, Long dispatchCopies,
            String mainNotifyUnit, String otherUnits, Date createDate,
            Date updateDate, String criticalLevel, String emergencyDegree,
            String commissionCanOpen, String unionDispatchUnits,
            String recordId, String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate2, String sendArchiveNo, String acceptArchiveNo,
            String dispatchUser, String nodename, String bizusernames,String username) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId = recordId;
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
        createdate = createdate2;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.dispatchUser = dispatchUser;
        this.nodename = nodename;
        this.bizusernames = bizusernames;
        this.username = username;
    }

    public VDispatchDocList(String djId, String internalNo, String itemId,
            String dispatchDocNo, String dispatchDocTitle,
            String dispatchFileType, String dispatchDocType,
            String dispatchCanOpen, String dispatchOpenType,
            String isUnionDispatch, String unionOthers, String isCountersign,
            String dispatchDocSummary, Date draftDate, String optUnitName,
            String draftUserName, String secretsDegree, Long dispatchCopies,
            String mainNotifyUnit, String otherUnits, Date createDate,
            Date updateDate, String criticalLevel, String emergencyDegree,
            String commissionCanOpen, String unionDispatchUnits,
            String recordId, String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            String usercode, Date createdate2, String sendArchiveNo,
            String acceptArchiveNo, String dispatchUser, String nodename,
            String bizusernames,String username) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId = recordId;
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
        this.usercode = usercode;
        createdate = createdate2;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.dispatchUser = dispatchUser;
        this.nodename = nodename;
        this.bizusernames = bizusernames;
        this.username = username;
     
    }

    public VDispatchDocList(String djId, String internalNo, String itemId,
            String dispatchDocNo, String dispatchDocTitle,
            String dispatchFileType, String dispatchDocType,
            String dispatchCanOpen, String dispatchOpenType,
            String isUnionDispatch, String unionOthers, String isCountersign,
            String dispatchDocSummary, Date draftDate, String optUnitName,
            String draftUserName, String secretsDegree, Long dispatchCopies,
            String mainNotifyUnit, String otherUnits, Date createDate,
            Date updateDate, String criticalLevel, String emergencyDegree,
            String commissionCanOpen, String unionDispatchUnits,
            String recordId, String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            String usercode, Date createdate2, String sendArchiveNo,
            String acceptArchiveNo,String dispatchUser,Date dispatchDate) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId = recordId;
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
        this.usercode = usercode;
        createdate = createdate2;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.dispatchUser=dispatchUser;
        this.dispatchDate=dispatchDate;
        
    }

    /** full constructor */
    public VDispatchDocList(String djId, String internalNo, String itemId,
            String dispatchDocNo, String dispatchDocTitle,
            String dispatchFileType, String dispatchDocType,
            String dispatchCanOpen, String dispatchOpenType,
            String isUnionDispatch, String unionOthers, String isCountersign,
            String dispatchDocSummary, Date draftDate, String optUnitName,
            String draftUserName, String secretsDegree, Long dispatchCopies,
            String mainNotifyUnit, String otherUnits, Date createDate,
            Date updateDate, String criticalLevel, String emergencyDegree,
            String commissionCanOpen, String unionDispatchUnits,
            String recordId, String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            String usercode, Date createdate, String sendArchiveNo,
            String acceptArchiveNo,Date dispatchDate) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId = recordId;
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
        this.usercode = usercode;
        this.createdate = createdate;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.dispatchDate=dispatchDate;
      
    }
    
    public VDispatchDocList(String djId,String itemId, String dispatchDocNo,String dispatchDocTitle,
            String dispatchFileType,String dispatchDocType,String dispatchCanOpen,String dispatchOpenType,
            String isUnionDispatch,String unionOthers,String isCountersign,String dispatchDocSummary,
            Date draftDate,String optUnitName,String draftUserName,String secretsDegree,
            Long dispatchCopies,String mainNotifyUnit,String otherUnits,
            Date createDate,Date updateDate,String criticalLevel,String emergencyDegree,
            String commissionCanOpen,String unionDispatchUnits,String recordId,String optId,
            Long flowInstId,String flowCode,String transAffairNo,String transaffairname,
            String transAffairQueryKey,String bizstate,String biztype,String orgcode,String orgname,
            String headunitcode,String headusercode,String powerid,String powername,String solvestatus,
            Date solvetime,String solvenote,Date createdate,String sendArchiveNo,String acceptArchiveNo,
            String dispatchUser,String nodename,String bizusernames,String username,Date dispatchDate){
        this.djId=djId;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId=recordId;
        this.optId=optId;
        this.flowInstId=flowInstId;
        this.flowCode=flowCode;
        this.transAffairNo=transAffairNo;
        this.transaffairname = transaffairname;
        this.transAffairQueryKey=transAffairQueryKey;
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
        this.sendArchiveNo=sendArchiveNo;
        this.acceptArchiveNo=acceptArchiveNo;
        this.dispatchUser=dispatchUser;
        this.nodename=nodename;
        this.bizusernames=bizusernames;
        this.username=username;
        this.dispatchDate=dispatchDate;
       
    }
    public VDispatchDocList(String djId, String internalNo, String itemId,
            String dispatchDocNo, String dispatchDocTitle,
            String dispatchFileType, String dispatchDocType,
            String dispatchCanOpen, String dispatchOpenType,
            String isUnionDispatch, String unionOthers, String isCountersign,
            String dispatchDocSummary, Date draftDate, String optUnitName,
            String draftUserName, String secretsDegree, Long dispatchCopies,
            String mainNotifyUnit, String otherUnits, Date createDate,
            Date updateDate, String criticalLevel, String emergencyDegree,
            String commissionCanOpen, String unionDispatchUnits,
            String recordId, String optId, String flowCode, Long flowInstId,
            String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String powerid, String powername,
            String solvestatus, Date solvetime, String solvenote,
            Date createdate, String sendArchiveNo,
            String acceptArchiveNo,String dispatchUser,String nodename,String bizusernames,String unitcode,String username,Date dispatchDate){
        this.djId=djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.dispatchDocNo = dispatchDocNo;
        this.dispatchDocTitle = dispatchDocTitle;
        this.dispatchFileType = dispatchFileType;
        this.dispatchDocType = dispatchDocType;
        this.dispatchCanOpen = dispatchCanOpen;
        this.dispatchOpenType = dispatchOpenType;
        this.isUnionDispatch = isUnionDispatch;
        this.unionOthers = unionOthers;
        
        this.isCountersign = isCountersign;
        this.dispatchDocSummary = dispatchDocSummary;
        this.draftDate = draftDate;
        this.optUnitName = optUnitName;
        this.draftUserName = draftUserName;
        
        this.secretsDegree = secretsDegree;
        this.dispatchCopies = dispatchCopies;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createDate = createDate;
        this.updateDate = updateDate;
        
        this.criticalLevel = criticalLevel;
        this.emergencyDegree = emergencyDegree;
        this.commissionCanOpen = commissionCanOpen;
        this.unionDispatchUnits = unionDispatchUnits;
        this.recordId = recordId;
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
        this.dispatchUser=dispatchUser;
        this.nodename=nodename;
        this.bizusernames=bizusernames;
        this.unitcode=unitcode;
        this.username=username;
        this.dispatchDate=dispatchDate;
    }
    
    public VDispatchDocList(String djId,String usercode,String dispatchDocNo,String dispatchDocTitle,
            Date createDate,Date updateDate,
            Long flowInstId,String flowCode,String transaffairname,String bizstate,String biztype,
            String solvenote,Date createdate,Date draftDate,String unitcode){
        super();
        this.djId=djId;
        this.usercode=usercode;
        this.dispatchDocNo=dispatchDocNo;
        this.dispatchDocTitle=dispatchDocTitle;
        this.createDate=createDate;
        this.updateDate=updateDate;
        this.flowInstId=flowInstId;
        this.flowCode=flowCode;
        this.transaffairname=transaffairname;
        this.bizstate=bizstate;
        this.biztype=biztype;
        this.solvenote=solvenote;
        this.createdate=createdate;
        this.unitcode=unitcode;
        this.draftDate=draftDate;
    }
    
    
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    // Property accessors

    public String getInternalNo() {
        return this.internalNo;
    }

    public void setInternalNo(String internalNo) {
        this.internalNo = internalNo;
    }

    public String getItemId() {
        return this.itemId;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDispatchDocNo() {
        return this.dispatchDocNo;
    }

    public void setDispatchDocNo(String dispatchDocNo) {
        this.dispatchDocNo = dispatchDocNo;
    }

    public String getDispatchDocTitle() {
        return this.dispatchDocTitle;
    }

    public void setDispatchDocTitle(String dispatchDocTitle) {
        this.dispatchDocTitle = dispatchDocTitle;
    }

    public String getDispatchFileType() {
        return this.dispatchFileType;
    }

    public void setDispatchFileType(String dispatchFileType) {
        this.dispatchFileType = dispatchFileType;
    }

    public String getDispatchDocType() {
        return this.dispatchDocType;
    }

    public void setDispatchDocType(String dispatchDocType) {
        this.dispatchDocType = dispatchDocType;
    }

    public String getDispatchCanOpen() {
        return this.dispatchCanOpen;
    }

    public void setDispatchCanOpen(String dispatchCanOpen) {
        this.dispatchCanOpen = dispatchCanOpen;
    }

    public String getDispatchOpenType() {
        return this.dispatchOpenType;
    }

    public void setDispatchOpenType(String dispatchOpenType) {
        this.dispatchOpenType = dispatchOpenType;
    }

    public String getIsUnionDispatch() {
        return this.isUnionDispatch;
    }

    public void setIsUnionDispatch(String isUnionDispatch) {
        this.isUnionDispatch = isUnionDispatch;
    }

    public String getUnionOthers() {
        return this.unionOthers;
    }

    public void setUnionOthers(String unionOthers) {
        this.unionOthers = unionOthers;
    }

    public String getIsCountersign() {
        return this.isCountersign;
    }

    public void setIsCountersign(String isCountersign) {
        this.isCountersign = isCountersign;
    }

    public String getDispatchDocSummary() {
        return this.dispatchDocSummary;
    }

    public void setDispatchDocSummary(String dispatchDocSummary) {
        this.dispatchDocSummary = dispatchDocSummary;
    }

    public Date getDraftDate() {
        return this.draftDate;
    }

    public void setDraftDate(Date draftDate) {
        this.draftDate = draftDate;
    }

    public String getOptUnitName() {
        return this.optUnitName;
    }

    public void setOptUnitName(String optUnitName) {
        this.optUnitName = optUnitName;
    }

    public String getDraftUserName() {
        return this.draftUserName;
    }

    public void setDraftUserName(String draftUserName) {
        this.draftUserName = draftUserName;
    }

    public String getSecretsDegree() {
        return this.secretsDegree;
    }

    public void setSecretsDegree(String secretsDegree) {
        this.secretsDegree = secretsDegree;
    }

    public Long getDispatchCopies() {
        return this.dispatchCopies;
    }

    public void setDispatchCopies(Long dispatchCopies) {
        this.dispatchCopies = dispatchCopies;
    }

    public String getMainNotifyUnit() {
        return this.mainNotifyUnit;
    }

    public void setMainNotifyUnit(String mainNotifyUnit) {
        this.mainNotifyUnit = mainNotifyUnit;
    }

    public String getOtherUnits() {
        return this.otherUnits;
    }

    public void setOtherUnits(String otherUnits) {
        this.otherUnits = otherUnits;
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

    public String getCriticalLevel() {
        return this.criticalLevel;
    }

    public void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public String getEmergencyDegree() {
        return this.emergencyDegree;
    }

    public void setEmergencyDegree(String emergencyDegree) {
        this.emergencyDegree = emergencyDegree;
    }

    public String getCommissionCanOpen() {
        return this.commissionCanOpen;
    }

    public void setCommissionCanOpen(String commissionCanOpen) {
        this.commissionCanOpen = commissionCanOpen;
    }

    public String getUnionDispatchUnits() {
        return this.unionDispatchUnits;
    }

    public void setUnionDispatchUnits(String unionDispatchUnits) {
        this.unionDispatchUnits = unionDispatchUnits;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public String getDispatchUser() {
        return dispatchUser;
    }

    public void setDispatchUser(String dispatchUser) {
        this.dispatchUser = dispatchUser;
    }

    public String getAcceptArchiveNo() {
        return this.acceptArchiveNo;
    }

    public void setAcceptArchiveNo(String acceptArchiveNo) {
        this.acceptArchiveNo = acceptArchiveNo;
    }
    public void copy(VDispatchDocList other) {

        this.setDjId(other.getDjId());

        this.internalNo = other.getInternalNo();
        this.itemId = other.getItemId();
        this.dispatchDocNo = other.getDispatchDocNo();
        this.dispatchDocTitle = other.getDispatchDocTitle();
        this.dispatchFileType = other.getDispatchFileType();
        this.dispatchDocType = other.getDispatchDocType();
        this.dispatchCanOpen = other.getDispatchCanOpen();
        this.dispatchOpenType = other.getDispatchOpenType();
        this.isUnionDispatch = other.getIsUnionDispatch();
        this.unionOthers = other.getUnionOthers();
        this.isCountersign = other.getIsCountersign();
        this.dispatchDocSummary = other.getDispatchDocSummary();
        this.draftDate = other.getDraftDate();
        this.optUnitName = other.getOptUnitName();
        this.draftUserName = other.getDraftUserName();
        this.secretsDegree = other.getSecretsDegree();
        this.dispatchCopies = other.getDispatchCopies();
        this.mainNotifyUnit = other.getMainNotifyUnit();
        this.otherUnits = other.getOtherUnits();
        this.createDate = other.getCreateDate();
        this.updateDate = other.getUpdateDate();
        this.criticalLevel = other.getCriticalLevel();
        this.emergencyDegree = other.getEmergencyDegree();
        this.commissionCanOpen = other.getCommissionCanOpen();
        this.unionDispatchUnits = other.getUnionDispatchUnits();
        this.recordId = other.getRecordId();
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
        this.dispatchUser=other.getDispatchUser();
        this.nodename=other.getNodename();
        this.bizusernames=other.getBizusernames();
        this.unitcode=other.getUnitcode();
        this.username=other.getUsername();
    }

    public void copyNotNullProperty(VDispatchDocList other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getInternalNo() != null)
            this.internalNo = other.getInternalNo();
        if (other.getItemId() != null)
            this.itemId = other.getItemId();
        if (other.getDispatchDocNo() != null)
            this.dispatchDocNo = other.getDispatchDocNo();
        if (other.getDispatchDocTitle() != null)
            this.dispatchDocTitle = other.getDispatchDocTitle();
        if (other.getDispatchFileType() != null)
            this.dispatchFileType = other.getDispatchFileType();
        if (other.getDispatchDocType() != null)
            this.dispatchDocType = other.getDispatchDocType();
        if (other.getDispatchCanOpen() != null)
            this.dispatchCanOpen = other.getDispatchCanOpen();
        if (other.getDispatchOpenType() != null)
            this.dispatchOpenType = other.getDispatchOpenType();
        if (other.getIsUnionDispatch() != null)
            this.isUnionDispatch = other.getIsUnionDispatch();
        if (other.getUnionOthers() != null)
            this.unionOthers = other.getUnionOthers();
        if (other.getIsCountersign() != null)
            this.isCountersign = other.getIsCountersign();
        if (other.getDispatchDocSummary() != null)
            this.dispatchDocSummary = other.getDispatchDocSummary();
        if (other.getDraftDate() != null)
            this.draftDate = other.getDraftDate();
        if (other.getOptUnitName() != null)
            this.optUnitName = other.getOptUnitName();
        if (other.getDraftUserName() != null)
            this.draftUserName = other.getDraftUserName();
        if (other.getSecretsDegree() != null)
            this.secretsDegree = other.getSecretsDegree();
        if (other.getDispatchCopies() != null)
            this.dispatchCopies = other.getDispatchCopies();
        if (other.getMainNotifyUnit() != null)
            this.mainNotifyUnit = other.getMainNotifyUnit();
        if (other.getOtherUnits() != null)
            this.otherUnits = other.getOtherUnits();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getUpdateDate() != null)
            this.updateDate = other.getUpdateDate();
        if (other.getCriticalLevel() != null)
            this.criticalLevel = other.getCriticalLevel();
        if (other.getEmergencyDegree() != null)
            this.emergencyDegree = other.getEmergencyDegree();
        if (other.getCommissionCanOpen() != null)
            this.commissionCanOpen = other.getCommissionCanOpen();
        if (other.getUnionDispatchUnits() != null)
            this.unionDispatchUnits = other.getUnionDispatchUnits();
        if (other.getRecordId() != null)
            this.recordId = other.getRecordId();
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
        if (other.getDispatchUser() != null)
        this.dispatchUser=other.getDispatchUser();
        if (other.getNodename() != null)
        this.nodename=other.getNodename();
        if (other.getBizusernames() != null)
        this.bizusernames=other.getBizusernames();
        if(other.getUnitcode()!=null)
            this.unitcode=other.getUnitcode();
        if( other.getUsername() != null)
            this.username=other.getUsername();
    }

    public void clearProperties() {

        this.internalNo = null;
        this.itemId = null;
        this.dispatchDocNo = null;
        this.dispatchDocTitle = null;
        this.dispatchFileType = null;
        this.dispatchDocType = null;
        this.dispatchCanOpen = null;
        this.dispatchOpenType = null;
        this.isUnionDispatch = null;
        this.unionOthers = null;
        this.isCountersign = null;
        this.dispatchDocSummary = null;
        this.draftDate = null;
        this.optUnitName = null;
        this.draftUserName = null;
        this.secretsDegree = null;
        this.dispatchCopies = null;
        this.mainNotifyUnit = null;
        this.otherUnits = null;
        this.createDate = null;
        this.updateDate = null;
        this.criticalLevel = null;
        this.emergencyDegree = null;
        this.commissionCanOpen = null;
        this.unionDispatchUnits = null;
        this.recordId = null;
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
        this.dispatchUser=null;
        this.nodename=null;
        this.bizusernames=null;
        this.unitcode=null;
        this.username=null;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }
   
   
    
    
}
