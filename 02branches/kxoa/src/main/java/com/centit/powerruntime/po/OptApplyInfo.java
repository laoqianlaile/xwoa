package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptApplyInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private Date applyDate;
    private String proposerName;
    private String applyItemType;
    private String applyReason;
    private String applyWay;
    private String proposerType;
    private String proposerPaperType;
    private String proposerPaperCode;
    private String proposerPhone;
    private String proposerMobile;
    private String proposerAddr;
    private String proposerZipcode;
    private String proposerEmail;
    private String proposerUnitcode;
    private String agentName;
    private String agentPaperType;
    private String agentPaperCode;
    private String agentPhone;
    private String agentMobile;
    private String agentAddr;
    private String agentZipcode;
    private String agentEmail;
    private String agentUnitcode;
    private String legal_person;
    private String applyMemo;
    private Date acceptDate;
    private Date auditingDate;
    private String headUsercode;
    private String headAuditingIdea;
    private Date checkIdeaDate;
    private String checkUsercode;
    private String checkIdea;
    private String checkDetail;
    private String checkAddr;
    private Date checkDate;
    private String checkMemo;
    private Date bookDate;

    private OptBaseInfo optBaseInfo;
    private String channel_lable;
    private String recievedepno;
    private Long flowInstId;//流程实例编号
    
    public String getChannel_lable() {
        return channel_lable;
    }



    public String getRecievedepno() {
        return recievedepno;
    }



    public void setRecievedepno(String recievedepno) {
        this.recievedepno = recievedepno;
    }



    public void setChannel_lable(String channel_lable) {
        this.channel_lable = channel_lable;
    }



    public OptApplyInfo(String djId, String channelName, String channelLevel,
            String elevationSystem, Date applyDate, String proposerName,
            String applyItemType, String applyReason, String applyWay,
            String proposerType, String proposerPaperType,
            String proposerPaperCode, String proposerPhone,
            String proposerMobile, String proposerAddr, String proposerZipcode,
            String proposerEmail, String proposerUnitcode, String agentName,
            String agentPaperType, String agentPaperCode, String agentPhone,
            String agentMobile, String agentAddr, String agentZipcode,
            String agentEmail, String agentUnitcode, String applyMemo,
            Date acceptDate, Date auditingDate, String headUsercode,
            String headAuditingIdea, Date checkIdeaDate, String checkUsercode,
            String checkIdea, String checkDetail, String checkAddr,
            Date checkDate, String checkMemo, Date bookDate,
            OptBaseInfo optBaseInfo,String recievedepno
            ) {
        super();
        this.djId = djId;
        this.applyDate = applyDate;
        this.proposerName = proposerName;
        this.applyItemType = applyItemType;
        this.applyReason = applyReason;
        this.applyWay = applyWay;
        this.proposerType = proposerType;
        this.proposerPaperType = proposerPaperType;
        this.proposerPaperCode = proposerPaperCode;
        this.proposerPhone = proposerPhone;
        this.proposerMobile = proposerMobile;
        this.proposerAddr = proposerAddr;
        this.proposerZipcode = proposerZipcode;
        this.proposerEmail = proposerEmail;
        this.proposerUnitcode = proposerUnitcode;
        this.agentName = agentName;
        this.agentPaperType = agentPaperType;
        this.agentPaperCode = agentPaperCode;
        this.agentPhone = agentPhone;
        this.agentMobile = agentMobile;
        this.agentAddr = agentAddr;
        this.agentZipcode = agentZipcode;
        this.agentEmail = agentEmail;
        this.agentUnitcode = agentUnitcode;
        this.applyMemo = applyMemo;
        this.acceptDate = acceptDate;
        this.auditingDate = auditingDate;
        this.headUsercode = headUsercode;
        this.headAuditingIdea = headAuditingIdea;
        this.checkIdeaDate = checkIdeaDate;
        this.checkUsercode = checkUsercode;
        this.checkIdea = checkIdea;
        this.checkDetail = checkDetail;
        this.checkAddr = checkAddr;
        this.checkDate = checkDate;
        this.checkMemo = checkMemo;
        this.bookDate = bookDate;
        this.optBaseInfo = optBaseInfo;
        this.recievedepno = recievedepno;
    }



    public Date getBookDate() {
        if(this.bookDate==null){
            this.bookDate=new Date(System.currentTimeMillis());  
        }
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
   
    // Constructors
    /** default constructor */
    public OptApplyInfo() {
    }

    /** minimal constructor */
    public OptApplyInfo(String djId) {

        this.djId = djId;

    }

    /** full constructor */
    public OptApplyInfo(String djId, String channelName, String channelLevel,
            String elevationSystem, Date applyDate, String proposerName,
            String applyItemType, String applyReason, String applyWay,
            String proposerType, String proposerPaperType,
            String proposerPaperCode, String proposerPhone,
            String proposerMobile, String proposerAddr, String proposerZipcode,
            String proposerEmail, String proposerUnitcode, String agentName,
            String agentPaperType, String agentPaperCode, String agentPhone,
            String agentMobile, String agentAddr, String agentZipcode,
            String agentEmail, String agentUnitcode, String applyMemo,
            Date acceptDate, Date auditingDate, String headUsercode,
            String headAuditingIdea, Date checkIdeaDate, String checkUsercode,
            String checkIdea, String checkDetail, String checkAddr,
            Date checkDate, String checkMemo,String recievedepno) {

        this.djId = djId;

        this.applyDate = applyDate;
        this.proposerName = proposerName;
        this.applyItemType = applyItemType;
        this.applyReason = applyReason;
        this.applyWay = applyWay;
        this.proposerType = proposerType;
        this.proposerPaperType = proposerPaperType;
        this.proposerPaperCode = proposerPaperCode;
        this.proposerPhone = proposerPhone;
        this.proposerMobile = proposerMobile;
        this.proposerAddr = proposerAddr;
        this.proposerZipcode = proposerZipcode;
        this.proposerEmail = proposerEmail;
        this.proposerUnitcode = proposerUnitcode;
        this.agentName = agentName;
        this.agentPaperType = agentPaperType;
        this.agentPaperCode = agentPaperCode;
        this.agentPhone = agentPhone;
        this.agentMobile = agentMobile;
        this.agentAddr = agentAddr;
        this.agentZipcode = agentZipcode;
        this.agentEmail = agentEmail;
        this.agentUnitcode = agentUnitcode;
        this.applyMemo = applyMemo;
        this.acceptDate = acceptDate;
        this.auditingDate = auditingDate;
        this.headUsercode = headUsercode;
        this.headAuditingIdea = headAuditingIdea;
        this.checkIdeaDate = checkIdeaDate;
        this.checkUsercode = checkUsercode;
        this.checkIdea = checkIdea;
        this.checkDetail = checkDetail;
        this.checkAddr = checkAddr;
        this.checkDate = checkDate;
        this.checkMemo = checkMemo;
        this.recievedepno =recievedepno;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors


    public Date getApplyDate() {
        if(this.applyDate==null){
            this.applyDate=new Date(System.currentTimeMillis());  
        }
        return this.applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getProposerName() {
        return this.proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public String getApplyItemType() {
        return this.applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getApplyReason() {
        return this.applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getApplyWay() {
        return this.applyWay;
    }

    public void setApplyWay(String applyWay) {
        this.applyWay = applyWay;
    }

    public String getProposerType() {
        return this.proposerType;
    }

    public void setProposerType(String proposerType) {
        this.proposerType = proposerType;
    }

    public String getProposerPaperType() {
        return this.proposerPaperType;
    }

    public void setProposerPaperType(String proposerPaperType) {
        this.proposerPaperType = proposerPaperType;
    }

    public String getProposerPaperCode() {
        return this.proposerPaperCode;
    }

    public void setProposerPaperCode(String proposerPaperCode) {
        this.proposerPaperCode = proposerPaperCode;
    }

    public String getProposerPhone() {
        return this.proposerPhone;
    }

    public void setProposerPhone(String proposerPhone) {
        this.proposerPhone = proposerPhone;
    }

    public String getProposerMobile() {
        return this.proposerMobile;
    }

    public void setProposerMobile(String proposerMobile) {
        this.proposerMobile = proposerMobile;
    }

    public String getProposerAddr() {
        return this.proposerAddr;
    }

    public void setProposerAddr(String proposerAddr) {
        this.proposerAddr = proposerAddr;
    }

    public String getProposerZipcode() {
        return this.proposerZipcode;
    }

    public void setProposerZipcode(String proposerZipcode) {
        this.proposerZipcode = proposerZipcode;
    }

    public String getProposerEmail() {
        return this.proposerEmail;
    }

    public void setProposerEmail(String proposerEmail) {
        this.proposerEmail = proposerEmail;
    }

    public String getProposerUnitcode() {
        return this.proposerUnitcode;
    }

    public void setProposerUnitcode(String proposerUnitcode) {
        this.proposerUnitcode = proposerUnitcode;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPaperType() {
        return this.agentPaperType;
    }

    public void setAgentPaperType(String agentPaperType) {
        this.agentPaperType = agentPaperType;
    }

    public String getAgentPaperCode() {
        return this.agentPaperCode;
    }

    public void setAgentPaperCode(String agentPaperCode) {
        this.agentPaperCode = agentPaperCode;
    }

    public String getAgentPhone() {
        return this.agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getAgentMobile() {
        return this.agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    public String getAgentAddr() {
        return this.agentAddr;
    }

    public void setAgentAddr(String agentAddr) {
        this.agentAddr = agentAddr;
    }

    public String getAgentZipcode() {
        return this.agentZipcode;
    }

    public void setAgentZipcode(String agentZipcode) {
        this.agentZipcode = agentZipcode;
    }

    public String getAgentEmail() {
        return this.agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentUnitcode() {
        return this.agentUnitcode;
    }

    public void setAgentUnitcode(String agentUnitcode) {
        this.agentUnitcode = agentUnitcode;
    }

    public String getApplyMemo() {
        return this.applyMemo;
    }

    public void setApplyMemo(String applyMemo) {
        this.applyMemo = applyMemo;
    }

    public Date getAcceptDate() {
        return this.acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getAuditingDate() {
        return this.auditingDate;
    }

    public void setAuditingDate(Date auditingDate) {
        this.auditingDate = auditingDate;
    }

    public String getHeadUsercode() {
        return this.headUsercode;
    }

    public void setHeadUsercode(String headUsercode) {
        this.headUsercode = headUsercode;
    }

    public String getHeadAuditingIdea() {
        return this.headAuditingIdea;
    }

    public void setHeadAuditingIdea(String headAuditingIdea) {
        this.headAuditingIdea = headAuditingIdea;
    }

    public Date getCheckIdeaDate() {
        return this.checkIdeaDate;
    }

    public void setCheckIdeaDate(Date checkIdeaDate) {
        this.checkIdeaDate = checkIdeaDate;
    }

    public String getCheckUsercode() {
        return this.checkUsercode;
    }

    public void setCheckUsercode(String checkUsercode) {
        this.checkUsercode = checkUsercode;
    }

    public String getCheckIdea() {
        return this.checkIdea;
    }

    public void setCheckIdea(String checkIdea) {
        this.checkIdea = checkIdea;
    }

    public String getCheckDetail() {
        return this.checkDetail;
    }

    public void setCheckDetail(String checkDetail) {
        this.checkDetail = checkDetail;
    }

    public String getCheckAddr() {
        return this.checkAddr;
    }

    public void setCheckAddr(String checkAddr) {
        this.checkAddr = checkAddr;
    }

    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckMemo() {
        return this.checkMemo;
    }

    public void setCheckMemo(String checkMemo) {
        this.checkMemo = checkMemo;
    }


    public void copy(OptApplyInfo other) {

        this.setDjId(other.getDjId());

        this.applyDate = other.getApplyDate();
        this.proposerName = other.getProposerName();
        this.applyItemType = other.getApplyItemType();
        this.applyReason = other.getApplyReason();
        this.applyWay = other.getApplyWay();
        this.proposerType = other.getProposerType();
        this.proposerPaperType = other.getProposerPaperType();
        this.proposerPaperCode = other.getProposerPaperCode();
        this.proposerPhone = other.getProposerPhone();
        this.proposerMobile = other.getProposerMobile();
        this.proposerAddr = other.getProposerAddr();
        this.proposerZipcode = other.getProposerZipcode();
        this.proposerEmail = other.getProposerEmail();
        this.proposerUnitcode = other.getProposerUnitcode();
        this.agentName = other.getAgentName();
        this.agentPaperType = other.getAgentPaperType();
        this.agentPaperCode = other.getAgentPaperCode();
        this.agentPhone = other.getAgentPhone();
        this.agentMobile = other.getAgentMobile();
        this.agentAddr = other.getAgentAddr();
        this.agentZipcode = other.getAgentZipcode();
        this.agentEmail = other.getAgentEmail();
        this.agentUnitcode = other.getAgentUnitcode();
        this.applyMemo = other.getApplyMemo();
        this.acceptDate = other.getAcceptDate();
        this.auditingDate = other.getAuditingDate();
        this.headUsercode = other.getHeadUsercode();
        this.headAuditingIdea = other.getHeadAuditingIdea();
        this.checkIdeaDate = other.getCheckIdeaDate();
        this.checkUsercode = other.getCheckUsercode();
        this.checkIdea = other.getCheckIdea();
        this.checkDetail = other.getCheckDetail();
        this.checkAddr = other.getCheckAddr();
        this.checkDate = other.getCheckDate();
        this.checkMemo = other.getCheckMemo();
        this.optBaseInfo = other.getOptBaseInfo();
        this.legal_person=other.getLegal_person();
        this.recievedepno =other.getRecievedepno();
    }

    public void copyNotNullProperty(OptApplyInfo other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getApplyDate() != null)
            this.applyDate = other.getApplyDate();
        if (other.getProposerName() != null)
            this.proposerName = other.getProposerName();
        if (other.getApplyItemType() != null)
            this.applyItemType = other.getApplyItemType();
        if (other.getApplyReason() != null)
            this.applyReason = other.getApplyReason();
        if (other.getApplyWay() != null)
            this.applyWay = other.getApplyWay();
        if (other.getProposerType() != null)
            this.proposerType = other.getProposerType();
        if (other.getProposerPaperType() != null)
            this.proposerPaperType = other.getProposerPaperType();
        if (other.getProposerPaperCode() != null)
            this.proposerPaperCode = other.getProposerPaperCode();
        if (other.getProposerPhone() != null)
            this.proposerPhone = other.getProposerPhone();
        if (other.getProposerMobile() != null)
            this.proposerMobile = other.getProposerMobile();
        if (other.getProposerAddr() != null)
            this.proposerAddr = other.getProposerAddr();
        if (other.getProposerZipcode() != null)
            this.proposerZipcode = other.getProposerZipcode();
        if (other.getProposerEmail() != null)
            this.proposerEmail = other.getProposerEmail();
        if (other.getProposerUnitcode() != null)
            this.proposerUnitcode = other.getProposerUnitcode();
        if (other.getAgentName() != null)
            this.agentName = other.getAgentName();
        if (other.getAgentPaperType() != null)
            this.agentPaperType = other.getAgentPaperType();
        if (other.getAgentPaperCode() != null)
            this.agentPaperCode = other.getAgentPaperCode();
        if (other.getAgentPhone() != null)
            this.agentPhone = other.getAgentPhone();
        if (other.getAgentMobile() != null)
            this.agentMobile = other.getAgentMobile();
        if (other.getAgentAddr() != null)
            this.agentAddr = other.getAgentAddr();
        if (other.getAgentZipcode() != null)
            this.agentZipcode = other.getAgentZipcode();
        if (other.getAgentEmail() != null)
            this.agentEmail = other.getAgentEmail();
        if (other.getAgentUnitcode() != null)
            this.agentUnitcode = other.getAgentUnitcode();
        if (other.getApplyMemo() != null)
            this.applyMemo = other.getApplyMemo();
        if (other.getAcceptDate() != null)
            this.acceptDate = other.getAcceptDate();
        if (other.getAuditingDate() != null)
            this.auditingDate = other.getAuditingDate();
        if (other.getHeadUsercode() != null)
            this.headUsercode = other.getHeadUsercode();
        if (other.getHeadAuditingIdea() != null)
            this.headAuditingIdea = other.getHeadAuditingIdea();
        if (other.getCheckIdeaDate() != null)
            this.checkIdeaDate = other.getCheckIdeaDate();
        if (other.getCheckUsercode() != null)
            this.checkUsercode = other.getCheckUsercode();
        if (other.getCheckIdea() != null)
            this.checkIdea = other.getCheckIdea();
        if (other.getCheckDetail() != null)
            this.checkDetail = other.getCheckDetail();
        if (other.getCheckAddr() != null)
            this.checkAddr = other.getCheckAddr();
        if (other.getCheckDate() != null)
            this.checkDate = other.getCheckDate();
        if (other.getCheckMemo() != null)
            this.checkMemo = other.getCheckMemo();
        if (other.getLegal_person() != null)
            this.legal_person = other.getLegal_person();
        if (other.getRecievedepno() != null)
            this.recievedepno = other.getRecievedepno();

        this.optBaseInfo = other.getOptBaseInfo();
    }

    public void clearProperties() {

        this.applyDate = null;
        this.proposerName = null;
        this.applyItemType = null;
        this.applyReason = null;
        this.applyWay = null;
        this.proposerType = null;
        this.proposerPaperType = null;
        this.proposerPaperCode = null;
        this.proposerPhone = null;
        this.proposerMobile = null;
        this.proposerAddr = null;
        this.proposerZipcode = null;
        this.proposerEmail = null;
        this.proposerUnitcode = null;
        this.agentName = null;
        this.agentPaperType = null;
        this.agentPaperCode = null;
        this.agentPhone = null;
        this.agentMobile = null;
        this.agentAddr = null;
        this.agentZipcode = null;
        this.agentEmail = null;
        this.agentUnitcode = null;
        this.applyMemo = null;
        this.acceptDate = null;
        this.auditingDate = null;
        this.headUsercode = null;
        this.headAuditingIdea = null;
        this.checkIdeaDate = null;
        this.checkUsercode = null;
        this.checkIdea = null;
        this.checkDetail = null;
        this.checkAddr = null;
        this.checkDate = null;
        this.checkMemo = null;
        this.legal_person=null;
        this.recievedepno =null;
        this.optBaseInfo = new OptBaseInfo();
    }

    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }

    public OptApplyInfo(String djId, String channelName, String channelLevel,
            String elevationSystem, Date applyDate, String proposerName,
            String applyItemType, String applyReason, String applyWay,
            String proposerType, String proposerPaperType,
            String proposerPaperCode, String proposerPhone,
            String proposerMobile, String proposerAddr, String proposerZipcode,
            String proposerEmail, String proposerUnitcode, String agentName,
            String agentPaperType, String agentPaperCode, String agentPhone,
            String agentMobile, String agentAddr, String agentZipcode,
            String agentEmail, String agentUnitcode, String legal_person,
            String applyMemo, Date acceptDate, Date auditingDate,
            String headUsercode, String headAuditingIdea, Date checkIdeaDate,
            String checkUsercode, String checkIdea, String checkDetail,
            String checkAddr, Date checkDate, String checkMemo, Date bookDate,
            OptBaseInfo optBaseInfo,String recievedepno) {
        super();
        this.djId = djId;
        this.applyDate = applyDate;
        this.proposerName = proposerName;
        this.applyItemType = applyItemType;
        this.applyReason = applyReason;
        this.applyWay = applyWay;
        this.proposerType = proposerType;
        this.proposerPaperType = proposerPaperType;
        this.proposerPaperCode = proposerPaperCode;
        this.proposerPhone = proposerPhone;
        this.proposerMobile = proposerMobile;
        this.proposerAddr = proposerAddr;
        this.proposerZipcode = proposerZipcode;
        this.proposerEmail = proposerEmail;
        this.proposerUnitcode = proposerUnitcode;
        this.agentName = agentName;
        this.agentPaperType = agentPaperType;
        this.agentPaperCode = agentPaperCode;
        this.agentPhone = agentPhone;
        this.agentMobile = agentMobile;
        this.agentAddr = agentAddr;
        this.agentZipcode = agentZipcode;
        this.agentEmail = agentEmail;
        this.agentUnitcode = agentUnitcode;
        this.legal_person = legal_person;
        this.applyMemo = applyMemo;
        this.acceptDate = acceptDate;
        this.auditingDate = auditingDate;
        this.headUsercode = headUsercode;
        this.headAuditingIdea = headAuditingIdea;
        this.checkIdeaDate = checkIdeaDate;
        this.checkUsercode = checkUsercode;
        this.checkIdea = checkIdea;
        this.checkDetail = checkDetail;
        this.checkAddr = checkAddr;
        this.checkDate = checkDate;
        this.checkMemo = checkMemo;
        this.bookDate = bookDate;
        this.optBaseInfo = optBaseInfo;
        this.recievedepno=recievedepno;
    }



    public OptApplyInfo(String djId, String channelName, String channelLevel,
            String elevationSystem, Date applyDate, String proposerName,
            String applyItemType, String applyReason, String applyWay,
            String proposerType, String proposerPaperType,
            String proposerPaperCode, String proposerPhone,
            String proposerMobile, String proposerAddr, String proposerZipcode,
            String proposerEmail, String proposerUnitcode, String agentName,
            String agentPaperType, String agentPaperCode, String agentPhone,
            String agentMobile, String agentAddr, String agentZipcode,
            String agentEmail, String agentUnitcode, String legal_person,
            String applyMemo, Date acceptDate, Date auditingDate,
            String headUsercode, String headAuditingIdea, Date checkIdeaDate,
            String checkUsercode, String checkIdea, String checkDetail,
            String checkAddr,Date checkDate, String checkMemo, Date bookDate) {
        super();
        this.djId = djId;
        this.applyDate = applyDate;
        this.proposerName = proposerName;
        this.applyItemType = applyItemType;
        this.applyReason = applyReason;
        this.applyWay = applyWay;
        this.proposerType = proposerType;
        this.proposerPaperType = proposerPaperType;
        this.proposerPaperCode = proposerPaperCode;
        this.proposerPhone = proposerPhone;
        this.proposerMobile = proposerMobile;
        this.proposerAddr = proposerAddr;
        this.proposerZipcode = proposerZipcode;
        this.proposerEmail = proposerEmail;
        this.proposerUnitcode = proposerUnitcode;
        this.agentName = agentName;
        this.agentPaperType = agentPaperType;
        this.agentPaperCode = agentPaperCode;
        this.agentPhone = agentPhone;
        this.agentMobile = agentMobile;
        this.agentAddr = agentAddr;
        this.agentZipcode = agentZipcode;
        this.agentEmail = agentEmail;
        this.agentUnitcode = agentUnitcode;
        this.legal_person = legal_person;
        this.applyMemo = applyMemo;
        this.acceptDate = acceptDate;
        this.auditingDate = auditingDate;
        this.headUsercode = headUsercode;
        this.headAuditingIdea = headAuditingIdea;
        this.checkIdeaDate = checkIdeaDate;
        this.checkUsercode = checkUsercode;
        this.checkIdea = checkIdea;
        this.checkDetail = checkDetail;
        this.checkAddr = checkAddr;
        this.checkDate = checkDate;
        this.checkMemo = checkMemo;
        this.bookDate = bookDate;
    }



    public String getLegal_person() {
        return legal_person;
    }



    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }



    public Long getFlowInstId() {
        return flowInstId;
    }



    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }



    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
}
