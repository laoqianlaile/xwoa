package com.centit.powerbase.po;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.centit.powerruntime.po.PowerOptInfo;
import com.centit.powerruntime.po.Suppowerstuffgroup;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Suppower implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

//    private String itemId;
//    private String version ;  
    private SuppowerId cid;
    private Date beginTime ;     
    private Date endTime  ;    

    private String orgId;
    private String itemName;
    private String itemStaName;
    private String itemType;
    private Long timeLimit;
    private String isNetwork;
    private String isFormula;
    private byte[] processDesc;
    private String phone;
    private String address;
    private String zfAccording;
    private String xwAccording;
    private Date lastmodifytime;
    private String fileName;
    private String auditSign;
    private String monitorPhone;
    private String srvDirectory;
    private String acceptLink;
    private byte[] applyForm;
    private String question;
    private String remark;
    private Long legalTimeLimit;
    private String charge;
    private String formName;
    private String freeJudge;
    private String levyUpon;
    private String condition;
    private String inFlowInfo;
    private byte[] inFlowImg;
    private String inFlowImgName;
    private String ischarge;
    private String chargeBasis;
    private String chargeStandard;
    private String punishClass;
    private String transactDepname;
    private String promiseType;
    private String anticipateType;
    private String acceptCondition;
    private String qlDepid;
    private String qlDepstate;
    private String entrustName;
    private String qlState;
    
    private Long  punishobject;
    private String  punishbasiscontent;
    private String  punishbasis;
    private String  islawsuit;
    private String  isreconsider;
    private String  dis_standard;
    private String  dis_detail;
    
    private String  acceptDepname;
    private String  compatibleType;
    private String  acceptTimeScope;
    private String  processWorkDesc;
    private byte[]  applyFormDemo;
    private String  stateQueryUrl;
    private String  resultQueryUrl;
    private String  regulatoryMeasures;
    private String  hasItem;
    private byte[]  srvDirectoryStuff;
    
    private String itemClass;
    private String applyMaterial;
    private String risk;
    private String outItemId;
    private String optItemType;//事项类型
    private String optOpenStyle;//公开方式
    private String actualDependent;
    
    public Suppower(SuppowerId cid, Date beginTime, Date endTime, String orgId,
            String itemName, String itemStaName, String itemType,
            Long timeLimit, String isNetwork, String isFormula,
            byte[] processDesc, String phone, String address,
            String zfAccording, String xwAccording, Date lastmodifytime,
            String fileName, String auditSign, String monitorPhone,
            String srvDirectory, String acceptLink, byte[] applyForm,
            String question, String remark, Long legalTimeLimit, String charge,
            String formName, String freeJudge, String levyUpon,
            String condition, String inFlowInfo, byte[] inFlowImg,
            String inFlowImgName, String ischarge, String chargeBasis,
            String chargeStandard, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String acceptCondition,
            String qlDepid, String qlDepstate, String entrustName,
            String qlState, Long punishobject, String punishbasiscontent,
            String punishbasis, String islawsuit, String isreconsider,
            String dis_standard, String dis_detail, String acceptDepname,
            String compatibleType, String acceptTimeScope,
            String processWorkDesc, byte[] applyFormDemo, String stateQueryUrl,
            String resultQueryUrl, String regulatoryMeasures, String hasItem,
            byte[] srvDirectoryStuff, String itemClass, String applyMaterial,
            String risk, String outItemId, PowerOptInfo powerOptInfo,
            Set<PowerOptInfo> powerOptInfos, String optItemType, String optOpenStyle,String actualDependent,
            Set<Suppowerstuffgroup> suppowerstuffgroups) {
        super();
        this.cid = cid;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.processDesc = processDesc;
        this.phone = phone;
        this.address = address;
        this.zfAccording = zfAccording;
        this.xwAccording = xwAccording;
        this.lastmodifytime = lastmodifytime;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.srvDirectory = srvDirectory;
        this.acceptLink = acceptLink;
        this.applyForm = applyForm;
        this.question = question;
        this.remark = remark;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.freeJudge = freeJudge;
        this.levyUpon = levyUpon;
        this.condition = condition;
        this.inFlowInfo = inFlowInfo;
        this.inFlowImg = inFlowImg;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.chargeBasis = chargeBasis;
        this.chargeStandard = chargeStandard;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.acceptCondition = acceptCondition;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.punishobject = punishobject;
        this.punishbasiscontent = punishbasiscontent;
        this.punishbasis = punishbasis;
        this.islawsuit = islawsuit;
        this.isreconsider = isreconsider;
        this.dis_standard = dis_standard;
        this.dis_detail = dis_detail;
        this.acceptDepname = acceptDepname;
        this.compatibleType = compatibleType;
        this.acceptTimeScope = acceptTimeScope;
        this.processWorkDesc = processWorkDesc;
        this.applyFormDemo = applyFormDemo;
        this.stateQueryUrl = stateQueryUrl;
        this.resultQueryUrl = resultQueryUrl;
        this.regulatoryMeasures = regulatoryMeasures;
        this.hasItem = hasItem;
        this.srvDirectoryStuff = srvDirectoryStuff;
        this.itemClass = itemClass;
        this.applyMaterial = applyMaterial;
        this.risk = risk;
        this.outItemId = outItemId;
        this.powerOptInfo = powerOptInfo;
        this.powerOptInfos = powerOptInfos;
        this.suppowerstuffgroups = suppowerstuffgroups;
        this.optOpenStyle=optOpenStyle;
        this.optItemType = optItemType;
        this.actualDependent=actualDependent;
    }

    public String getOutItemId() {
        return outItemId;
    }

    public String getActualDependent() {
        return actualDependent;
    }

    public void setActualDependent(String actualDependent) {
        this.actualDependent = actualDependent;
    }

    public void setOutItemId(String outItemId) {
        this.outItemId = outItemId;
    }
    
    public String getOptItemType() {
        return optItemType;
    }

    public void setOptItemType(String optItemType) {
        this.optItemType = optItemType;
    }

    public String getOptOpenStyle() {
        return optOpenStyle;
    }

    public void setOptOpenStyle(String optOpenStyle) {
        this.optOpenStyle = optOpenStyle;
    }

    public Suppower(SuppowerId cid, Date beginTime, Date endTime, String orgId,
            String itemName, String itemStaName, String itemType,
            Long timeLimit, String isNetwork, String isFormula,
            byte[] processDesc, String phone, String address,
            String zfAccording, String xwAccording, Date lastmodifytime,
            String fileName, String auditSign, String monitorPhone,
            String srvDirectory, String acceptLink, byte[] applyForm,
            String question, String remark, Long legalTimeLimit, String charge,
            String formName, String freeJudge, String levyUpon,
            String condition, String inFlowInfo, byte[] inFlowImg,
            String inFlowImgName, String ischarge, String chargeBasis,
            String chargeStandard, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String acceptCondition,
            String qlDepid, String qlDepstate, String entrustName,
            String qlState, Long punishobject, String punishbasiscontent,
            String punishbasis, String islawsuit, String isreconsider,
            String dis_standard, String dis_detail, String acceptDepname,
            String compatibleType, String acceptTimeScope,
            String processWorkDesc, byte[] applyFormDemo, String stateQueryUrl,
            String resultQueryUrl, String regulatoryMeasures, String hasItem,
            byte[] srvDirectoryStuff, String itemClass, String applyMaterial,
            String risk, PowerOptInfo powerOptInfo,
            Set<PowerOptInfo> powerOptInfos,String optItemType, String optOpenStyle,String actualDependent,
            Set<Suppowerstuffgroup> suppowerstuffgroups) {
        super();
        this.cid = cid;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.processDesc = processDesc;
        this.phone = phone;
        this.address = address;
        this.zfAccording = zfAccording;
        this.xwAccording = xwAccording;
        this.lastmodifytime = lastmodifytime;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.srvDirectory = srvDirectory;
        this.acceptLink = acceptLink;
        this.applyForm = applyForm;
        this.question = question;
        this.remark = remark;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.freeJudge = freeJudge;
        this.levyUpon = levyUpon;
        this.condition = condition;
        this.inFlowInfo = inFlowInfo;
        this.inFlowImg = inFlowImg;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.chargeBasis = chargeBasis;
        this.chargeStandard = chargeStandard;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.acceptCondition = acceptCondition;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.punishobject = punishobject;
        this.punishbasiscontent = punishbasiscontent;
        this.punishbasis = punishbasis;
        this.islawsuit = islawsuit;
        this.isreconsider = isreconsider;
        this.dis_standard = dis_standard;
        this.dis_detail = dis_detail;
        this.acceptDepname = acceptDepname;
        this.compatibleType = compatibleType;
        this.acceptTimeScope = acceptTimeScope;
        this.processWorkDesc = processWorkDesc;
        this.applyFormDemo = applyFormDemo;
        this.stateQueryUrl = stateQueryUrl;
        this.resultQueryUrl = resultQueryUrl;
        this.regulatoryMeasures = regulatoryMeasures;
        this.hasItem = hasItem;
        this.srvDirectoryStuff = srvDirectoryStuff;
        this.itemClass = itemClass;
        this.applyMaterial = applyMaterial;
        this.risk = risk;
        this.powerOptInfo = powerOptInfo;
        this.powerOptInfos = powerOptInfos;
        this.suppowerstuffgroups = suppowerstuffgroups;
        this.optOpenStyle=optOpenStyle;
        this.optItemType = optItemType;
        this.actualDependent=actualDependent;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getApplyMaterial() {
        return applyMaterial;
    }

    public void setApplyMaterial(String applyMaterial) {
        this.applyMaterial = applyMaterial;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Suppower(SuppowerId cid, Date beginTime, Date endTime, String orgId,
            String itemName, String itemStaName, String itemType,
            Long timeLimit, String isNetwork, String isFormula,
            byte[] processDesc, String phone, String address,
            String zfAccording, String xwAccording, Date lastmodifytime,
            String fileName, String auditSign, String monitorPhone,
            String srvDirectory, String acceptLink, byte[] applyForm,
            String question, String remark, Long legalTimeLimit, String charge,
            String formName, String freeJudge, String levyUpon,
            String condition, String inFlowInfo, byte[] inFlowImg,
            String inFlowImgName, String ischarge, String chargeBasis,
            String chargeStandard, String punishClass, String transactDepname,
            String promiseType, String anticipateType, String acceptCondition,
            String qlDepid, String qlDepstate, String entrustName,
            String qlState, Long punishobject, String punishbasiscontent,
            String punishbasis, String islawsuit, String isreconsider,
            String dis_standard, String dis_detail, String acceptDepname,
            String compatibleType, String acceptTimeScope,
            String processWorkDesc, byte[] applyFormDemo, String stateQueryUrl,
            String resultQueryUrl, String regulatoryMeasures, String hasItem,
            byte[] srvDirectoryStuff, PowerOptInfo powerOptInfo,
            Set<PowerOptInfo> powerOptInfos,String optItemType, String optOpenStyle,String actualDependent,
            Set<Suppowerstuffgroup> suppowerstuffgroups) {
        super();
        this.cid = cid;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.processDesc = processDesc;
        this.phone = phone;
        this.address = address;
        this.zfAccording = zfAccording;
        this.xwAccording = xwAccording;
        this.lastmodifytime = lastmodifytime;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.srvDirectory = srvDirectory;
        this.acceptLink = acceptLink;
        this.applyForm = applyForm;
        this.question = question;
        this.remark = remark;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.freeJudge = freeJudge;
        this.levyUpon = levyUpon;
        this.condition = condition;
        this.inFlowInfo = inFlowInfo;
        this.inFlowImg = inFlowImg;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.chargeBasis = chargeBasis;
        this.chargeStandard = chargeStandard;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.acceptCondition = acceptCondition;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.punishobject = punishobject;
        this.punishbasiscontent = punishbasiscontent;
        this.punishbasis = punishbasis;
        this.islawsuit = islawsuit;
        this.isreconsider = isreconsider;
        this.dis_standard = dis_standard;
        this.dis_detail = dis_detail;
        this.acceptDepname = acceptDepname;
        this.compatibleType = compatibleType;
        this.acceptTimeScope = acceptTimeScope;
        this.processWorkDesc = processWorkDesc;
        this.applyFormDemo = applyFormDemo;
        this.stateQueryUrl = stateQueryUrl;
        this.resultQueryUrl = resultQueryUrl;
        this.regulatoryMeasures = regulatoryMeasures;
        this.hasItem = hasItem;
        this.srvDirectoryStuff = srvDirectoryStuff;
        this.powerOptInfo = powerOptInfo;
        this.powerOptInfos = powerOptInfos;
        this.suppowerstuffgroups = suppowerstuffgroups;
        this.optOpenStyle=optOpenStyle;
        this.optItemType = optItemType;
        this.actualDependent=actualDependent;
    }

    public String getAcceptDepname() {
        return acceptDepname;
    }

    public void setAcceptDepname(String acceptDepname) {
        this.acceptDepname = acceptDepname;
    }

    public String getCompatibleType() {
        return compatibleType;
    }

    public void setCompatibleType(String compatibleType) {
        this.compatibleType = compatibleType;
    }

    public String getAcceptTimeScope() {
        return acceptTimeScope;
    }

    public void setAcceptTimeScope(String acceptTimeScope) {
        this.acceptTimeScope = acceptTimeScope;
    }

    public String getProcessWorkDesc() {
        return processWorkDesc;
    }

    public void setProcessWorkDesc(String processWorkDesc) {
        this.processWorkDesc = processWorkDesc;
    }

    public byte[] getApplyFormDemo() {
        return applyFormDemo;
    }

    public void setApplyFormDemo(byte[] applyFormDemo) {
        this.applyFormDemo = applyFormDemo;
    }

    public String getStateQueryUrl() {
        return stateQueryUrl;
    }

    public void setStateQueryUrl(String stateQueryUrl) {
        this.stateQueryUrl = stateQueryUrl;
    }

    public String getResultQueryUrl() {
        return resultQueryUrl;
    }

    public void setResultQueryUrl(String resultQueryUrl) {
        this.resultQueryUrl = resultQueryUrl;
    }

    public String getRegulatoryMeasures() {
        return regulatoryMeasures;
    }

    public void setRegulatoryMeasures(String regulatoryMeasures) {
        this.regulatoryMeasures = regulatoryMeasures;
    }

    public String getHasItem() {
        return hasItem;
    }

    public void setHasItem(String hasItem) {
        this.hasItem = hasItem;
    }

    public byte[] getSrvDirectoryStuff() {
        return srvDirectoryStuff;
    }

    public void setSrvDirectoryStuff(byte[] newSrvDirectoryStuff) {
        if (null == newSrvDirectoryStuff) {
            this.srvDirectoryStuff = ArrayUtils.EMPTY_BYTE_ARRAY;
        } else {
            this.srvDirectoryStuff = Arrays.copyOf(newSrvDirectoryStuff, newSrvDirectoryStuff.length);
        }
//        this.srvDirectoryStuff = srvDirectoryStuff;
    }
   
    public String getDis_standard() {
        return dis_standard;
    }

    public void setDis_standard(String dis_standard) {
        this.dis_standard = dis_standard;
    }

    public String getDis_detail() {
        return dis_detail;
    }

    public void setDis_detail(String dis_detail) {
        this.dis_detail = dis_detail;
    }

    public Long getPunishobject() {
        return punishobject;
    }

    public void setPunishobject(Long punishobject) {
        this.punishobject = punishobject;
    }

    public String getPunishbasiscontent() {
        return punishbasiscontent;
    }

    public void setPunishbasiscontent(String punishbasiscontent) {
        this.punishbasiscontent = punishbasiscontent;
    }

    public String getPunishbasis() {
        return punishbasis;
    }

    public void setPunishbasis(String punishbasis) {
        this.punishbasis = punishbasis;
    }

    public String getIslawsuit() {
        return islawsuit;
    }

    public void setIslawsuit(String islawsuit) {
        this.islawsuit = islawsuit;
    }

    public String getIsreconsider() {
        return isreconsider;
    }

    public void setIsreconsider(String isreconsider) {
        this.isreconsider = isreconsider;
    }
    private PowerOptInfo powerOptInfo;
    private Set<PowerOptInfo> powerOptInfos = null;// new
                                                   // ArrayList<PowerOptInfo>();
    private Set<Suppowerstuffgroup> suppowerstuffgroups = null;// new
                                                               // ArrayList<Suppowerstuffgroup>();
    

   
    // Constructors
    /** default constructor */
    public Suppower() {
    }

    /** minimal constructor */
    public Suppower(SuppowerId id,Date beginTime,Date endTime,
            String orgId, String itemName,
            String itemStaName, String itemType, Long timeLimit,
            String isNetwork, String isFormula,  byte[] processDesc,
            String phone, String address,  String zfAccording,
            String xwAccording,  Date lastmodifytime,
             String fileName, String auditSign,
            String monitorPhone, String srvDirectory, String acceptLink,
            byte[] applyForm, String question, String remark,
            Long legalTimeLimit, String charge, String formName,
            String freeJudge, String levyUpon, String condition,
            String inFlowInfo, byte[] inFlowImg, String inFlowImgName,
            String ischarge, String chargeBasis, String chargeStandard,
            String punishClass, String transactDepname, String promiseType,
            String anticipateType, String acceptCondition, String qlDepid,
            String qlDepstate, String entrustName, String qlState,String optItemType, String optOpenStyle,String actualDependent,
            Long  punishobject,String  punishbasiscontent,String  punishbasis,String  islawsuit,String  isreconsider,
            
            
            Set<PowerOptInfo> powerOptInfos,
            Set<Suppowerstuffgroup> suppowerstuffgroups) {
        super();
//        this.itemId = itemId;
//        this.version = version ;   
        this.cid = id;
        this.beginTime = beginTime ;     
        this.endTime = endTime  ; 
        
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.processDesc = processDesc;
        this.phone = phone;
        this.address = address;
        this.zfAccording = zfAccording;
        this.xwAccording = xwAccording;
        this.lastmodifytime = lastmodifytime;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.srvDirectory = srvDirectory;
        this.acceptLink = acceptLink;
        this.applyForm = applyForm;
        this.question = question;
        this.remark = remark;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.freeJudge = freeJudge;
        this.levyUpon = levyUpon;
        this.condition = condition;
        this.inFlowInfo = inFlowInfo;
        this.inFlowImg = inFlowImg;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.chargeBasis = chargeBasis;
        this.chargeStandard = chargeStandard;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.acceptCondition = acceptCondition;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.punishobject= punishobject;
        this.punishbasiscontent= punishbasiscontent;
        this.punishbasis= punishbasis;
        this.islawsuit= islawsuit;
        this.isreconsider= isreconsider;    
        this.powerOptInfos = powerOptInfos;
        this.suppowerstuffgroups = suppowerstuffgroups;
        this.optOpenStyle=optOpenStyle;
        this.optItemType = optItemType;
        this.actualDependent=actualDependent;
    }

    public Suppower(SuppowerId id, String orgId, String itemName, String itemType) {

//        this.itemId = itemId;
        this.cid = id;
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    /** full constructor */
    public Suppower(SuppowerId id,Date beginTime,Date endTime,
            String orgId, String itemName,
            String itemStaName, String itemType, Long timeLimit,
            String isNetwork, String isFormula,  byte[] processDesc,
            String phone, String address,  String zfAccording,
            String xwAccording,Date lastmodifytime,
            String ischange, String fileName, String auditSign,
            String monitorPhone,  String srvDirectory,  String acceptLink,
            byte[] applyForm, String question, String remark,
            Long legalTimeLimit, String charge, String formName,
            String freeJudge, String levyUpon, String condition,
            String inFlowInfo, byte[] inFlowImg,String inFlowImgName,
            String ischarge, String chargeBasis, String chargeStandard,
            String punishClass, String transactDepname, String promiseType,
            String anticipateType, String acceptCondition, String qlDepid,
            String qlDepstate, String entrustName, String qlState,String optItemType, String optOpenStyle,String actualDependent,
            Long  punishobject,String  punishbasiscontent,String  punishbasis,String  islawsuit,String  isreconsider
            ,String dis_standard, String dis_detail) {

//        this.itemId = itemId;
//        this.version = version ; 
        this.cid = id;
        this.beginTime = beginTime ;     
        this.endTime = endTime  ; 
        
        this.orgId = orgId;
        this.itemName = itemName;
        this.itemStaName = itemStaName;
        this.itemType = itemType;
        this.timeLimit = timeLimit;
        this.isNetwork = isNetwork;
        this.isFormula = isFormula;
        this.processDesc = processDesc;
        this.phone = phone;
        this.address = address;
        this.zfAccording = zfAccording;
        this.xwAccording = xwAccording;
        this.lastmodifytime = lastmodifytime;
        this.fileName = fileName;
        this.auditSign = auditSign;
        this.monitorPhone = monitorPhone;
        this.srvDirectory = srvDirectory;
        this.acceptLink = acceptLink;
        this.applyForm = applyForm;
        this.question = question;
        this.remark = remark;
        this.legalTimeLimit = legalTimeLimit;
        this.charge = charge;
        this.formName = formName;
        this.freeJudge = freeJudge;
        this.levyUpon = levyUpon;
        this.condition = condition;
        this.inFlowInfo = inFlowInfo;
        this.inFlowImg = inFlowImg;
        this.inFlowImgName = inFlowImgName;
        this.ischarge = ischarge;
        this.chargeBasis = chargeBasis;
        this.chargeStandard = chargeStandard;
        this.punishClass = punishClass;
        this.transactDepname = transactDepname;
        this.promiseType = promiseType;
        this.anticipateType = anticipateType;
        this.acceptCondition = acceptCondition;
        this.qlDepid = qlDepid;
        this.qlDepstate = qlDepstate;
        this.entrustName = entrustName;
        this.qlState = qlState;
        this.punishobject= punishobject;
        this.punishbasiscontent= punishbasiscontent;
        this.punishbasis= punishbasis;
        this.islawsuit= islawsuit;
        this.isreconsider= isreconsider;   
        this.dis_standard = dis_standard;
        this.dis_detail = dis_detail;
        this.optOpenStyle=optOpenStyle;
        this.optItemType = optItemType;
        this.actualDependent=actualDependent;
    }

    public void setCid(SuppowerId cid) {
        this.cid = cid;
    }

    public SuppowerId getCid() {
        return cid;
    }

    public String getItemId() {
        if(this.cid==null)
            this.cid = new SuppowerId();
        return this.cid.getItemId();
    }
    
    public void setItemId(String itemId) {
        if(this.cid==null)
            this.cid = new SuppowerId();
        this.cid.setItemId(itemId);
    }

    public Long getVersion() {
        if(this.cid==null)
            this.cid = new SuppowerId();
        return this.cid.getVersion();
    }

    public void setVersion(Long version) {
        if(this.cid==null)
            this.cid = new SuppowerId();
        this.cid.setVersion(version);
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    // Property accessors

    public PowerOptInfo getPowerOptInfo() {
        return powerOptInfo;
    }

    public void setPowerOptInfo(PowerOptInfo powerOptInfo) {
        this.powerOptInfo = powerOptInfo;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStaName() {
        return this.itemStaName;
    }

    public void setItemStaName(String itemStaName) {
        this.itemStaName = itemStaName;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getIsNetwork() {
        if(StringUtils.isBlank(this.isNetwork)){
            this.isNetwork="0";
        }
        return this.isNetwork;
    }

    public void setIsNetwork(String isNetwork) {
        this.isNetwork = isNetwork;
    }

    public String getIsFormula() {
        return this.isFormula;
    }

    public void setIsFormula(String isFormula) {
        this.isFormula = isFormula;
    }


    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Date getLastmodifytime() {
        if (this.lastmodifytime == null) {
            this.lastmodifytime = new Date(System.currentTimeMillis());
        }
        return this.lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }


    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuditSign() {
        return this.auditSign;
    }

    public void setAuditSign(String auditSign) {
        this.auditSign = auditSign;
    }

    public String getMonitorPhone() {
        return this.monitorPhone;
    }

    public void setMonitorPhone(String monitorPhone) {
        this.monitorPhone = monitorPhone;
    }


    public String getAcceptLink() {
        return this.acceptLink;
    }

    public void setAcceptLink(String acceptLink) {
        this.acceptLink = acceptLink;
    }



    public Long getLegalTimeLimit() {
        return this.legalTimeLimit;
    }

    public void setLegalTimeLimit(Long legalTimeLimit) {
        this.legalTimeLimit = legalTimeLimit;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }




    public String getInFlowImgName() {
        return this.inFlowImgName;
    }

    public void setInFlowImgName(String inFlowImgName) {
        this.inFlowImgName = inFlowImgName;
    }

    public String getIscharge() {
        return this.ischarge;
    }

    public void setIscharge(String ischarge) {
        this.ischarge = ischarge;
    }

    public String getPunishClass() {
        return this.punishClass;
    }

    public void setPunishClass(String punishClass) {
        this.punishClass = punishClass;
    }

    public String getTransactDepname() {
        return this.transactDepname;
    }

    public void setTransactDepname(String transactDepname) {
        this.transactDepname = transactDepname;
    }

    public String getPromiseType() {
        return this.promiseType;
    }

    public void setPromiseType(String promiseType) {
        this.promiseType = promiseType;
    }

    public String getAnticipateType() {
        return this.anticipateType;
    }

    public void setAnticipateType(String anticipateType) {
        this.anticipateType = anticipateType;
    }


    public String getQlDepid() {
        return this.qlDepid;
    }

    public void setQlDepid(String qlDepid) {
        this.qlDepid = qlDepid;
    }

    public String getQlDepstate() {
        return this.qlDepstate;
    }

    public void setQlDepstate(String qlDepstate) {
        this.qlDepstate = qlDepstate;
    }

    public String getEntrustName() {
        return this.entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public String getQlState() {
        return this.qlState;
    }

    public void setQlState(String qlState) {
        this.qlState = qlState;
    }

    public byte[] getProcessDesc() {
        return processDesc;
    }

    public void setProcessDesc(byte[] processDesc) {
        this.processDesc = processDesc;
    }

    public String getZfAccording() {
        return zfAccording;
    }

    public void setZfAccording(String zfAccording) {
        this.zfAccording = zfAccording;
    }

    public String getXwAccording() {
        return xwAccording;
    }

    public void setXwAccording(String xwAccording) {
        this.xwAccording = xwAccording;
    }

    public String getSrvDirectory() {
        return srvDirectory;
    }

    public void setSrvDirectory(String srvDirectory) {
        this.srvDirectory = srvDirectory;
    }

    public byte[] getApplyForm() {
        return applyForm;
    }

    public void setApplyForm(byte[] applyForm) {
        this.applyForm = applyForm;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFreeJudge() {
        return freeJudge;
    }

    public void setFreeJudge(String freeJudge) {
        this.freeJudge = freeJudge;
    }

    public String getLevyUpon() {
        return levyUpon;
    }

    public void setLevyUpon(String levyUpon) {
        this.levyUpon = levyUpon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInFlowInfo() {
        return inFlowInfo;
    }
    
    public String getInFlowXml() {
        if(inFlowInfo != null && !"".equals(inFlowInfo)){
            int start = inFlowInfo.indexOf("<flow>");
            if(start < 0)
                start = inFlowInfo.indexOf("<FLOW>");
            String flowXml;
            if(start>0)
                flowXml = inFlowInfo.substring(start).trim();
            else
                flowXml = inFlowInfo.trim();
            return flowXml.replaceAll("\n","").replaceAll("\r","").replaceAll("\"","'").toLowerCase();
        }else{
            return "";
        }
   }
    
    public void setInFlowInfo(String inFlowInfo) {
        this.inFlowInfo = inFlowInfo;
    }

    public byte[] getInFlowImg() {
        return inFlowImg;
    }

    public void setInFlowImg(byte[] inFlowImg) {
        this.inFlowImg = inFlowImg;
    }

    public String getChargeBasis() {
        return chargeBasis;
    }

    public void setChargeBasis(String chargeBasis) {
        this.chargeBasis = chargeBasis;
    }

    public String getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(String chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public String getAcceptCondition() {
        return acceptCondition;
    }

    public void setAcceptCondition(String acceptCondition) {
        this.acceptCondition = acceptCondition;
    }

    public Set<PowerOptInfo> getPowerOptInfos() {
        if (this.powerOptInfos == null)
            this.powerOptInfos = new HashSet<PowerOptInfo>();
        return this.powerOptInfos;
    }

    public void setPowerOptInfos(Set<PowerOptInfo> powerOptInfos) {
        this.powerOptInfos = powerOptInfos;
    }

    public void addPowerOptInfo(PowerOptInfo powerOptInfo) {
        if (this.powerOptInfos == null)
            this.powerOptInfos = new HashSet<PowerOptInfo>();
        this.powerOptInfos.add(powerOptInfo);
    }

    public void removePowerOptInfo(PowerOptInfo powerOptInfo) {
        if (this.powerOptInfos == null)
            return;
        this.powerOptInfos.remove(powerOptInfo);
    }

    public PowerOptInfo newPowerOptInfo() {
        PowerOptInfo res = new PowerOptInfo();

        res.setItemId(this.getItemId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replacePowerOptInfos(Collection <PowerOptInfo> powerOptInfos) {
        List<PowerOptInfo> newObjs = new ArrayList<PowerOptInfo>();
        for (PowerOptInfo p : powerOptInfos) {
            if (p == null)
                continue;
            PowerOptInfo newdt = newPowerOptInfo();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<PowerOptInfo> oldObjs = new HashSet<PowerOptInfo>();
        oldObjs.addAll(getPowerOptInfos());

        for (Iterator<PowerOptInfo> it = oldObjs.iterator(); it.hasNext();) {
            PowerOptInfo odt = it.next();
            found = false;
            for (PowerOptInfo newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removePowerOptInfo(odt);
        }
        oldObjs.clear();
        // insert or update
        for (PowerOptInfo newdt : newObjs) {
            found = false;
            for (Iterator<PowerOptInfo> it = getPowerOptInfos().iterator(); it
                    .hasNext();) {
                PowerOptInfo odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addPowerOptInfo(newdt);
        }
    }

    public Set<Suppowerstuffgroup> getSuppowerstuffgroups() {
        if (this.suppowerstuffgroups == null)
            this.suppowerstuffgroups = new HashSet<Suppowerstuffgroup>();
        return this.suppowerstuffgroups;
    }

    public void setSuppowerstuffgroups(
            Set<Suppowerstuffgroup> suppowerstuffgroups) {
        this.suppowerstuffgroups = suppowerstuffgroups;
    }

    public void addSuppowerstuffgroup(Suppowerstuffgroup suppowerstuffgroup) {
        if (this.suppowerstuffgroups == null)
            this.suppowerstuffgroups = new HashSet<Suppowerstuffgroup>();
        this.suppowerstuffgroups.add(suppowerstuffgroup);
    }

    public void removeSuppowerstuffgroup(Suppowerstuffgroup suppowerstuffgroup) {
        if (this.suppowerstuffgroups == null)
            return;
        this.suppowerstuffgroups.remove(suppowerstuffgroup);
    }

    public Suppowerstuffgroup newSuppowerstuffgroup() {
        Suppowerstuffgroup res = new Suppowerstuffgroup();

        //res.setItemId(this.getItemId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceSuppowerstuffgroups(
            Collection <Suppowerstuffgroup> suppowerstuffgroups) {
        List<Suppowerstuffgroup> newObjs = new ArrayList<Suppowerstuffgroup>();
        for (Suppowerstuffgroup p : suppowerstuffgroups) {
            if (p == null)
                continue;
            Suppowerstuffgroup newdt = newSuppowerstuffgroup();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<Suppowerstuffgroup> oldObjs = new HashSet<Suppowerstuffgroup>();
        oldObjs.addAll(getSuppowerstuffgroups());

        for (Iterator<Suppowerstuffgroup> it = oldObjs.iterator(); it.hasNext();) {
            Suppowerstuffgroup odt = it.next();
            found = false;
            for (Suppowerstuffgroup newdt : newObjs) {
                if (odt.getGroupId().equals(newdt.getGroupId())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeSuppowerstuffgroup(odt);
        }
        oldObjs.clear();
        // insert or update
        for (Suppowerstuffgroup newdt : newObjs) {
            found = false;
            for (Iterator<Suppowerstuffgroup> it = getSuppowerstuffgroups()
                    .iterator(); it.hasNext();) {
                Suppowerstuffgroup odt = it.next();
                if (odt.getGroupId().equals(newdt.getGroupId())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addSuppowerstuffgroup(newdt);
        }
    }

    public void copy(Suppower other) {

        this.setItemId(other.getItemId());
        this.setVersion(other.getVersion());
//        this.version = other.getVersion() ;      
        this.beginTime = other.getBeginTime() ;     
        this.endTime = other.getEndTime()  ;
        
        this.orgId = other.getOrgId();
        this.itemName = other.getItemName();
        this.itemStaName = other.getItemStaName();
        this.itemType = other.getItemType();
        this.timeLimit = other.getTimeLimit();
        this.isNetwork = other.getIsNetwork();
        this.isFormula = other.getIsFormula();
        this.processDesc = other.getProcessDesc();
        this.phone = other.getPhone();
        this.address = other.getAddress();
        this.zfAccording = other.getZfAccording();
        this.xwAccording = other.getXwAccording();
        this.lastmodifytime = other.getLastmodifytime();
        this.fileName = other.getFileName();
        this.auditSign = other.getAuditSign();
        this.monitorPhone = other.getMonitorPhone();
        this.srvDirectory = other.getSrvDirectory();
        this.acceptLink = other.getAcceptLink();
        this.applyForm = other.getApplyForm();
        this.question = other.getQuestion();
        this.remark = other.getRemark();
        this.legalTimeLimit = other.getLegalTimeLimit();
        this.charge = other.getCharge();
        this.formName = other.getFormName();
        this.freeJudge = other.getFreeJudge();
        this.levyUpon = other.getLevyUpon();
        this.condition = other.getCondition();
        this.inFlowInfo = other.getInFlowInfo();
        this.inFlowImg = other.getInFlowImg();
        this.inFlowImgName = other.getInFlowImgName();
        this.ischarge = other.getIscharge();
        this.chargeBasis = other.getChargeBasis();
        this.chargeStandard = other.getChargeStandard();
        this.punishClass = other.getPunishClass();
        this.transactDepname = other.getTransactDepname();
        this.promiseType = other.getPromiseType();
        this.anticipateType = other.getAnticipateType();
        this.acceptCondition = other.getAcceptCondition();
        this.qlDepid = other.getQlDepid();
        this.qlDepstate = other.getQlDepstate();
        this.entrustName = other.getEntrustName();
        this.qlState = other.getQlState();
        this.punishobject= other.getPunishobject();  
        this.punishbasiscontent= other.getPunishbasiscontent();  
        this.punishbasis= other.getPunishbasis();  
        this.islawsuit= other.getIslawsuit();  
        this.isreconsider= other.getIsreconsider();
        
        this.powerOptInfos = other.getPowerOptInfos();
        this.suppowerstuffgroups = other.getSuppowerstuffgroups();
        this.dis_standard = other.getDis_standard();
        this.dis_detail = other.getDis_detail();
        
        this.acceptDepname = other.getAcceptDepname();
        this.compatibleType = other.getCompatibleType();
        this.acceptTimeScope = other.getAcceptTimeScope();
        this.processWorkDesc = other.getProcessWorkDesc();
        this.applyFormDemo = other.getApplyFormDemo();
        this.stateQueryUrl = other.getStateQueryUrl();
        this.resultQueryUrl = other.getResultQueryUrl();
        this.regulatoryMeasures = other.getRegulatoryMeasures();
        this.hasItem = other.getHasItem();
        this.srvDirectoryStuff = other.getSrvDirectoryStuff();

        this.itemClass = other.getItemClass();
  
        this.applyMaterial = other.getApplyMaterial();

        this.risk = other.getRisk();
        this.outItemId = other.getOutItemId();
        this.replacePowerOptInfos( other.getPowerOptInfos());
        this.replaceSuppowerstuffgroups(other.getSuppowerstuffgroups());
        this.optItemType =other.getOptItemType();
        this.optOpenStyle=other.getOptOpenStyle();
        this.actualDependent=other.getActualDependent();
                
    }

    public void copyNotNullProperty(Suppower other) {

        if (other.getItemId() != null)
            this.setItemId(other.getItemId());
        if (other.getVersion() != null)
            this.setVersion(other.getVersion());

        if (other.getOrgId() != null)
            this.orgId = other.getOrgId();
        
//        if (other.getVersion() != null)
//            this.version = other.getVersion();
        if (other.getBeginTime() != null)
            this.beginTime = other.getBeginTime();
        if (other.getEndTime() != null)
            this.endTime = other.getEndTime();
        
        if (other.getItemName() != null)
            this.itemName = other.getItemName();
        if (other.getItemStaName() != null)
            this.itemStaName = other.getItemStaName();
        if (other.getItemType() != null)
            this.itemType = other.getItemType();
        if (other.getTimeLimit() != null)
            this.timeLimit = other.getTimeLimit();
        if (other.getIsNetwork() != null)
            this.isNetwork = other.getIsNetwork();
        if (other.getIsFormula() != null)
            this.isFormula = other.getIsFormula();
        if (other.getProcessDesc() != null)
            this.processDesc = other.getProcessDesc();
        if (other.getPhone() != null)
            this.phone = other.getPhone();
        if (other.getAddress() != null)
            this.address = other.getAddress();
        if (other.getZfAccording() != null)
            this.zfAccording = other.getZfAccording();
        if (other.getXwAccording() != null)
            this.xwAccording = other.getXwAccording();
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
        if (other.getFileName() != null)
            this.fileName = other.getFileName();
        if (other.getAuditSign() != null)
            this.auditSign = other.getAuditSign();
        if (other.getMonitorPhone() != null)
            this.monitorPhone = other.getMonitorPhone();
        if (other.getSrvDirectory() != null)
            this.srvDirectory = other.getSrvDirectory();
        if (other.getAcceptLink() != null)
            this.acceptLink = other.getAcceptLink();
        if (other.getApplyForm() != null)
            this.applyForm = other.getApplyForm();
        if (other.getQuestion() != null)
            this.question = other.getQuestion();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getLegalTimeLimit() != null)
            this.legalTimeLimit = other.getLegalTimeLimit();
        if (other.getCharge() != null)
            this.charge = other.getCharge();
        if (other.getFormName() != null)
            this.formName = other.getFormName();
        if (other.getFreeJudge() != null)
            this.freeJudge = other.getFreeJudge();
        if (other.getLevyUpon() != null)
            this.levyUpon = other.getLevyUpon();
        if (other.getCondition() != null)
            this.condition = other.getCondition();
        if (other.getInFlowInfo() != null)
            this.inFlowInfo = other.getInFlowInfo();
        if (other.getInFlowImg() != null)
            this.inFlowImg = other.getInFlowImg();
        if (other.getInFlowImgName() != null)
            this.inFlowImgName = other.getInFlowImgName();
        if (other.getIscharge() != null)
            this.ischarge = other.getIscharge();
        if (other.getChargeBasis() != null)
            this.chargeBasis = other.getChargeBasis();
        if (other.getChargeStandard() != null)
            this.chargeStandard = other.getChargeStandard();
        if (other.getPunishClass() != null)
            this.punishClass = other.getPunishClass();
        if (other.getTransactDepname() != null)
            this.transactDepname = other.getTransactDepname();
        if (other.getPromiseType() != null)
            this.promiseType = other.getPromiseType();
        if (other.getAnticipateType() != null)
            this.anticipateType = other.getAnticipateType();
        if (other.getAcceptCondition() != null)
            this.acceptCondition = other.getAcceptCondition();
        if (other.getQlDepid() != null)
            this.qlDepid = other.getQlDepid();
        if (other.getQlDepstate() != null)
            this.qlDepstate = other.getQlDepstate();
        if (other.getEntrustName() != null)
            this.entrustName = other.getEntrustName();
        if (other.getQlState() != null)
            this.qlState = other.getQlState();
        if( other.getPunishobject() != null)
            this.punishobject= other.getPunishobject();  
        if( other.getPunishbasiscontent() != null)
            this.punishbasiscontent= other.getPunishbasiscontent();  
        if( other.getPunishbasis() != null)
            this.punishbasis= other.getPunishbasis();  
        if( other.getIslawsuit() != null)
            this.islawsuit= other.getIslawsuit();  
        if( other.getIsreconsider() != null)
            this.isreconsider= other.getIsreconsider();
        if( other.getDis_standard() != null)
            this.dis_standard= other.getDis_standard();  
        if( other.getDis_detail() != null)
            this.dis_detail= other.getDis_detail();
        
        if( other.getAcceptDepname() != null)
            this.acceptDepname = other.getAcceptDepname();
        if( other.getCompatibleType() != null)
            this.compatibleType = other.getCompatibleType();
        if( other.getAcceptTimeScope() != null)
            this.acceptTimeScope = other.getAcceptTimeScope();
        if( other.getProcessWorkDesc() != null)
            this.processWorkDesc = other.getProcessWorkDesc();
        if( other.getApplyFormDemo() != null)
            this.applyFormDemo = other.getApplyFormDemo();
        if( other.getStateQueryUrl() != null)
            this.stateQueryUrl = other.getStateQueryUrl();
        if( other.getResultQueryUrl() != null)
            this.resultQueryUrl = other.getResultQueryUrl();
        if( other.getRegulatoryMeasures() != null)
            this.regulatoryMeasures = other.getRegulatoryMeasures();
        if( other.getHasItem() != null)
            this.hasItem = other.getHasItem();
        if( other.getSrvDirectoryStuff() != null)
            this.srvDirectoryStuff = other.getSrvDirectoryStuff();
        if( other.getItemClass() != null)
            this.itemClass = other.getItemClass();
        if( other.getApplyMaterial() != null)
            this.applyMaterial = other.getApplyMaterial();
        if( other.getRisk() != null)
            this.risk = other.getRisk();
        if(other.getOptItemType()!=null)
            this.optItemType = other.getOptItemType();
        if(other.getOptOpenStyle()!=null)
            this.optOpenStyle = other.getOptOpenStyle();
        if( other.getOutItemId() != null)
            this.outItemId = other.getOutItemId();
        if( other.getActualDependent() != null)
            this.actualDependent = other.getActualDependent();
        this.replacePowerOptInfos( other.getPowerOptInfos());
        this.replaceSuppowerstuffgroups(other.getSuppowerstuffgroups());
    }

    public void clearProperties() {
//        this.itemId=null;
//        this.version=null;
        this.beginTime=null;
        this.endTime=null;
        this.orgId = null;
        this.itemName = null;
        this.itemStaName = null;
        this.itemType = null;
        this.timeLimit = null;
        this.isNetwork = null;
        this.isFormula = null;
        this.processDesc = null;
        this.phone = null;
        this.address = null;
        this.zfAccording = null;
        this.xwAccording = null;
        this.lastmodifytime = null;
        this.fileName = null;
        this.auditSign = null;
        this.monitorPhone = null;
        this.srvDirectory = null;
        this.acceptLink = null;
        this.applyForm = null;
        this.question = null;
        this.remark = null;
        this.legalTimeLimit = null;
        this.charge = null;
        this.formName = null;
        this.freeJudge = null;
        this.levyUpon = null;
        this.condition = null;
        this.inFlowInfo = null;
        this.inFlowImg = null;
        this.inFlowImgName = null;
        this.ischarge = null;
        this.chargeBasis = null;
        this.chargeStandard = null;
        this.punishClass = null;
        this.transactDepname = null;
        this.promiseType = null;
        this.anticipateType = null;
        this.acceptCondition = null;
        this.qlDepid = null;
        this.qlDepstate = null;
        this.entrustName = null;
        this.qlState = null;
        this.punishobject= null;  
        this.punishbasiscontent= null;  
        this.punishbasis= null;  
        this.islawsuit= null;  
        this.isreconsider= null;
        this.dis_standard = null;
        this.dis_detail = null;
        this.acceptDepname = null;
        this.compatibleType = null;
        this.acceptTimeScope = null;
        this.processWorkDesc = null;
        this.applyFormDemo = null;
        this.stateQueryUrl = null;
        this.resultQueryUrl = null;
        this.regulatoryMeasures = null;
        this.hasItem = null;
        this.srvDirectoryStuff = null;
        this.itemClass = null;
        this.applyMaterial = null;
        this.risk = null;
        this.outItemId = null;
        this.optOpenStyle=null;
        this.optItemType=null;
        this.actualDependent=null;
    }
    
    public String display() {
        return "权力信息 [权力编码=" + this.cid.getItemId() 
                + ", 权力版本=" + this.cid.getVersion()
                + ", 权力名称=" + this.itemName
                + ", 权力类型=" + CodeRepositoryUtil.getValue("ITEM_TYPE", String.valueOf(this.itemType))
                +"]";
    }
}
