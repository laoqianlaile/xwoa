package com.centit.dispatchdoc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class IncomeDoc implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;
    private String prevDjId;
    private String nextDjId;
    
    private String internalNo;
    private String itemId;
    private String acceptNo;
    private String itemSource;// 申报来源
    private String applyItemType;
    private String projectName;
    private String applyDemo;
    private String incomeDocNo;
    private String incomeDocTitle;
    private String incomeDeptType;
    private String provincialGovDocNo;
    private String incomeDeptName;
    private String applyOrgCode;
    private Date docCreateDate;
    private Date incomeDate;
    private String secretDegree;
    private String contactName;
    private String contactPhone;
    private String applyUser;
    private String applyUserPhone;
    private String applyUserAddr;
    private String applyUserZip;
    private String incomeDocFileName;
    private byte[] incomeDocFile;
    private String operateState;
    private String syncErrorDesc;
    private Date createDate;
    private Date updateDate;
    private Date syncDate;
    private String syncSign;
    private Date caseAcceptDate;
    private Date signIssueDate;

    private String criticalLevel;// 缓急程度

    private String incomeDocType;// 来文文件分类

    private OptBaseInfo optBaseInfo;
    private IncomeProject incomeProject;
    private List<IncomeDoc> docList = new ArrayList<IncomeDoc>();
    private Set<DocRelative> docRelatives = null;// new
                                                 // ArrayList<DocRelative>();
    //private OptLeaderShip ship;

    private Date toBeanfinishedDate;// 办结截止时间 为后续办结时限提醒 界限
    
    private String overdueType; // 临时存放该办件是否超期——N：未超期，I：即将超期，O：已超期

    // Constructors
    /** default constructor */
    public IncomeDoc() {
    }

    /** minimal constructor */
    public IncomeDoc(String djId, String internalNo, String itemId) {

        this.djId = djId;

        this.internalNo = internalNo;
        this.itemId = itemId;
    }

    /** full constructor */

    public IncomeDoc(String djId, String internalNo, String itemId,
            String acceptNo, String itemSource, String applyItemType,
            String projectName, String applyDemo, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String provincialGovDocNo, String incomeDeptName,
            String applyOrgCode, Date docCreateDate, Date incomeDate,
            String secretDegree, String contactName, String contactPhone,
            String applyUser, String applyUserPhone, String applyUserAddr,
            String applyUserZip, String incomeDocFileName,
            byte[] incomeDocFile, String operateState, String syncErrorDesc,
            Date createDate, Date updateDate, Date syncDate, String syncSign,
            Date caseAcceptDate, Date signIssueDate) {

        this.djId = djId;

        this.internalNo = internalNo;
        this.itemId = itemId;
        this.acceptNo = acceptNo;
        this.itemSource = itemSource;
        this.applyItemType = applyItemType;
        this.projectName = projectName;
        this.applyDemo = applyDemo;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.provincialGovDocNo = provincialGovDocNo;
        this.incomeDeptName = incomeDeptName;
        this.applyOrgCode = applyOrgCode;
        this.docCreateDate = docCreateDate;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.applyUser = applyUser;
        this.applyUserPhone = applyUserPhone;
        this.applyUserAddr = applyUserAddr;
        this.applyUserZip = applyUserZip;
        this.incomeDocFileName = incomeDocFileName;
        this.incomeDocFile = incomeDocFile;
        this.operateState = operateState;
        this.syncErrorDesc = syncErrorDesc;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.caseAcceptDate = caseAcceptDate;
        this.signIssueDate = signIssueDate;
    }

    public IncomeDoc(String djId, String internalNo, String itemId,
            String acceptNo, String itemSource, String applyItemType,
            String projectName, String applyDemo, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String provincialGovDocNo, String incomeDeptName,
            String applyOrgCode, Date docCreateDate, Date incomeDate,
            String secretDegree, String contactName, String contactPhone,
            String applyUser, String applyUserPhone, String applyUserAddr,
            String applyUserZip, String incomeDocFileName,
            byte[] incomeDocFile, String operateState, String syncErrorDesc,
            Date createDate, Date updateDate, Date syncDate, String syncSign,
            Date caseAcceptDate, Date signIssueDate, String criticalLevel,
            String incomeDocType, Date toBeanfinishedDate) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.acceptNo = acceptNo;
        this.itemSource = itemSource;
        this.applyItemType = applyItemType;
        this.projectName = projectName;
        this.applyDemo = applyDemo;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.provincialGovDocNo = provincialGovDocNo;
        this.incomeDeptName = incomeDeptName;
        this.applyOrgCode = applyOrgCode;
        this.docCreateDate = docCreateDate;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.applyUser = applyUser;
        this.applyUserPhone = applyUserPhone;
        this.applyUserAddr = applyUserAddr;
        this.applyUserZip = applyUserZip;
        this.incomeDocFileName = incomeDocFileName;
        this.incomeDocFile = incomeDocFile;
        this.operateState = operateState;
        this.syncErrorDesc = syncErrorDesc;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.caseAcceptDate = caseAcceptDate;
        this.signIssueDate = signIssueDate;
        this.criticalLevel = criticalLevel;
        this.incomeDocType = incomeDocType;
        this.toBeanfinishedDate = toBeanfinishedDate;
    }

    public IncomeDoc(String djId, String internalNo, String itemId,
            String acceptNo, String itemSource, String applyItemType,
            String projectName, String applyDemo, String incomeDocNo,
            String incomeDocTitle, String incomeDeptType,
            String provincialGovDocNo, String incomeDeptName,
            String applyOrgCode, Date docCreateDate, Date incomeDate,
            String secretDegree, String contactName, String contactPhone,
            String applyUser, String applyUserPhone, String applyUserAddr,
            String applyUserZip, String incomeDocFileName,
            byte[] incomeDocFile, String operateState, String syncErrorDesc,
            Date createDate, Date updateDate, Date syncDate, String syncSign,
            Date caseAcceptDate, Date signIssueDate, String criticalLevel,
            String incomeDocType) {
        super();
        this.djId = djId;
        this.internalNo = internalNo;
        this.itemId = itemId;
        this.acceptNo = acceptNo;
        this.itemSource = itemSource;
        this.applyItemType = applyItemType;
        this.projectName = projectName;
        this.applyDemo = applyDemo;
        this.incomeDocNo = incomeDocNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptType = incomeDeptType;
        this.provincialGovDocNo = provincialGovDocNo;
        this.incomeDeptName = incomeDeptName;
        this.applyOrgCode = applyOrgCode;
        this.docCreateDate = docCreateDate;
        this.incomeDate = incomeDate;
        this.secretDegree = secretDegree;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.applyUser = applyUser;
        this.applyUserPhone = applyUserPhone;
        this.applyUserAddr = applyUserAddr;
        this.applyUserZip = applyUserZip;
        this.incomeDocFileName = incomeDocFileName;
        this.incomeDocFile = incomeDocFile;
        this.operateState = operateState;
        this.syncErrorDesc = syncErrorDesc;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.caseAcceptDate = caseAcceptDate;
        this.signIssueDate = signIssueDate;
        this.criticalLevel = criticalLevel;
        this.incomeDocType = incomeDocType;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public String getInternalNo() {
        return this.internalNo;
    }

   

    public Date getToBeanfinishedDate() {
        return toBeanfinishedDate;
    }

    public void setToBeanfinishedDate(Date toBeanfinishedDate) {
        this.toBeanfinishedDate = toBeanfinishedDate;
    }

    public void setInternalNo(String internalNo) {
        this.internalNo = internalNo;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAcceptNo() {
        return this.acceptNo;
    }

    public void setAcceptNo(String acceptNo) {
        this.acceptNo = acceptNo;
    }

    public String getItemSource() {
        return this.itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource;
    }

    public String getApplyItemType() {
        return this.applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplyDemo() {
        return this.applyDemo;
    }

    public void setApplyDemo(String applyDemo) {
        this.applyDemo = applyDemo;
    }

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

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public String getApplyOrgCode() {
        return this.applyOrgCode;
    }

    public void setApplyOrgCode(String applyOrgCode) {
        this.applyOrgCode = applyOrgCode;
    }

    public Date getDocCreateDate() {
        return this.docCreateDate;
    }

    public void setDocCreateDate(Date docCreateDate) {
        this.docCreateDate = docCreateDate;
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

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getApplyUser() {
        return this.applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUserPhone() {
        return this.applyUserPhone;
    }

    public void setApplyUserPhone(String applyUserPhone) {
        this.applyUserPhone = applyUserPhone;
    }

    public String getApplyUserAddr() {
        return this.applyUserAddr;
    }

    public void setApplyUserAddr(String applyUserAddr) {
        this.applyUserAddr = applyUserAddr;
    }

    public String getApplyUserZip() {
        return this.applyUserZip;
    }

    public void setApplyUserZip(String applyUserZip) {
        this.applyUserZip = applyUserZip;
    }

    public String getIncomeDocFileName() {
        return this.incomeDocFileName;
    }

    public void setIncomeDocFileName(String incomeDocFileName) {
        this.incomeDocFileName = incomeDocFileName;
    }

    public byte[] getIncomeDocFile() {
        return incomeDocFile;
    }

    public void setIncomeDocFile(byte[] incomeDocFile) {
        this.incomeDocFile = incomeDocFile;
    }

    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }

    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }

    public IncomeProject getIncomeProject() {
        return incomeProject;
    }

    public void setIncomeProject(IncomeProject incomeProject) {
        this.incomeProject = incomeProject;
    }

    public List<IncomeDoc> getDocList() {
        return docList;
    }

    public void setDocList(List<IncomeDoc> docList) {
        this.docList = docList;
    }

    public String getOperateState() {
        return this.operateState;
    }

    public void setOperateState(String operateState) {
        this.operateState = operateState;
    }

    public String getSyncErrorDesc() {
        return this.syncErrorDesc;
    }

    public void setSyncErrorDesc(String syncErrorDesc) {
        this.syncErrorDesc = syncErrorDesc;
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

    public Date getSyncDate() {
        return this.syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public String getSyncSign() {
        return this.syncSign;
    }

    public void setSyncSign(String syncSign) {
        this.syncSign = syncSign;
    }

    public String getProvincialGovDocNo() {
        return provincialGovDocNo;
    }

    public void setProvincialGovDocNo(String provincialGovDocNo) {
        this.provincialGovDocNo = provincialGovDocNo;
    }

    public Date getCaseAcceptDate() {
        return caseAcceptDate;
    }

    public void setCaseAcceptDate(Date caseAcceptDate) {
        this.caseAcceptDate = caseAcceptDate;
    }

    public Date getSignIssueDate() {
        return signIssueDate;
    }

    public void setSignIssueDate(Date signIssueDate) {
        this.signIssueDate = signIssueDate;
    }

    public Set<DocRelative> getDocRelatives() {
        if (this.docRelatives == null)
            this.docRelatives = new HashSet<DocRelative>();
        return this.docRelatives;
    }

    public void setDocRelatives(Set<DocRelative> docRelatives) {
        this.docRelatives = docRelatives;
    }

    public void addDocRelative(DocRelative docRelative) {
        if (this.docRelatives == null)
            this.docRelatives = new HashSet<DocRelative>();
        this.docRelatives.add(docRelative);
    }

    public void removeDocRelative(DocRelative docRelative) {
        if (this.docRelatives == null)
            return;
        this.docRelatives.remove(docRelative);
    }

    public DocRelative newDocRelative() {
        DocRelative res = new DocRelative();

        res.setIncomeNo(this.getDjId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceDocRelatives(List<DocRelative> docRelatives) {
        List<DocRelative> newObjs = new ArrayList<DocRelative>();
        for (DocRelative p : docRelatives) {
            if (p == null)
                continue;
            DocRelative newdt = newDocRelative();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<DocRelative> oldObjs = new HashSet<DocRelative>();
        oldObjs.addAll(getDocRelatives());

        for (Iterator<DocRelative> it = oldObjs.iterator(); it.hasNext();) {
            DocRelative odt = it.next();
            found = false;
            for (DocRelative newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeDocRelative(odt);
        }
        oldObjs.clear();
        // insert or update
        for (DocRelative newdt : newObjs) {
            found = false;
            for (Iterator<DocRelative> it = getDocRelatives().iterator(); it
                    .hasNext();) {
                DocRelative odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addDocRelative(newdt);
        }
    }

    public void copy(IncomeDoc other) {

        this.setDjId(other.getDjId());

        this.internalNo = other.getInternalNo();
        this.itemId = other.getItemId();
        this.acceptNo = other.getAcceptNo();
        this.itemSource = other.getItemSource();
        this.applyItemType = other.getApplyItemType();
        this.projectName = other.getProjectName();
        this.applyDemo = other.getApplyDemo();
        this.incomeDocNo = other.getIncomeDocNo();
        this.incomeDocTitle = other.getIncomeDocTitle();
        this.incomeDeptType = other.getIncomeDeptType();
        this.provincialGovDocNo = other.getProvincialGovDocNo();
        this.incomeDeptName = other.getIncomeDeptName();
        this.applyOrgCode = other.getApplyOrgCode();
        this.docCreateDate = other.getDocCreateDate();
        this.incomeDate = other.getIncomeDate();
        this.secretDegree = other.getSecretDegree();
        this.contactName = other.getContactName();
        this.contactPhone = other.getContactPhone();
        this.applyUser = other.getApplyUser();
        this.applyUserPhone = other.getApplyUserPhone();
        this.applyUserAddr = other.getApplyUserAddr();
        this.applyUserZip = other.getApplyUserZip();
        this.incomeDocFileName = other.getIncomeDocFileName();
        this.incomeDocFile = other.getIncomeDocFile();
        this.operateState = other.getOperateState();
        this.syncErrorDesc = other.getSyncErrorDesc();
        this.createDate = other.getCreateDate();
        this.updateDate = other.getUpdateDate();
        this.syncDate = other.getSyncDate();
        this.syncSign = other.getSyncSign();
        this.caseAcceptDate = other.getCaseAcceptDate();
        this.signIssueDate = other.getSignIssueDate();

        this.docRelatives = other.getDocRelatives();
        this.criticalLevel = other.getCriticalLevel();
        this.incomeDocType = other.getIncomeDocType();
        this.toBeanfinishedDate = other.getToBeanfinishedDate();
    }

    public void copyNotNullProperty(IncomeDoc other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getInternalNo() != null)
            this.internalNo = other.getInternalNo();
        if (other.getItemId() != null)
            this.itemId = other.getItemId();
        if (other.getAcceptNo() != null)
            this.acceptNo = other.getAcceptNo();
        if (other.getItemSource() != null)
            this.itemSource = other.getItemSource();
        if (other.getApplyItemType() != null)
            this.applyItemType = other.getApplyItemType();
        if (other.getProjectName() != null)
            this.projectName = other.getProjectName();
        if (other.getApplyDemo() != null)
            this.applyDemo = other.getApplyDemo();
        if (other.getIncomeDocNo() != null)
            this.incomeDocNo = other.getIncomeDocNo();
        if (other.getIncomeDocTitle() != null)
            this.incomeDocTitle = other.getIncomeDocTitle();
        if (other.getIncomeDeptType() != null)
            this.incomeDeptType = other.getIncomeDeptType();
        if (other.getProvincialGovDocNo() != null)
            this.provincialGovDocNo = other.getProvincialGovDocNo();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getApplyOrgCode() != null)
            this.applyOrgCode = other.getApplyOrgCode();
        if (other.getDocCreateDate() != null)
            this.docCreateDate = other.getDocCreateDate();
        if (other.getIncomeDate() != null)
            this.incomeDate = other.getIncomeDate();
        if (other.getSecretDegree() != null)
            this.secretDegree = other.getSecretDegree();
        if (other.getContactName() != null)
            this.contactName = other.getContactName();
        if (other.getContactPhone() != null)
            this.contactPhone = other.getContactPhone();
        if (other.getApplyUser() != null)
            this.applyUser = other.getApplyUser();
        if (other.getApplyUserPhone() != null)
            this.applyUserPhone = other.getApplyUserPhone();
        if (other.getApplyUserAddr() != null)
            this.applyUserAddr = other.getApplyUserAddr();
        if (other.getApplyUserZip() != null)
            this.applyUserZip = other.getApplyUserZip();
        if (other.getIncomeDocFileName() != null)
            this.incomeDocFileName = other.getIncomeDocFileName();
        if (other.getIncomeDocFile() != null)
            this.incomeDocFile = other.getIncomeDocFile();
        if (other.getOperateState() != null)
            this.operateState = other.getOperateState();
        if (other.getSyncErrorDesc() != null)
            this.syncErrorDesc = other.getSyncErrorDesc();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getUpdateDate() != null)
            this.updateDate = other.getUpdateDate();
        if (other.getSyncDate() != null)
            this.syncDate = other.getSyncDate();
        if (other.getSyncSign() != null)
            this.syncSign = other.getSyncSign();
        if (other.getCaseAcceptDate() != null)
            this.caseAcceptDate = other.getCaseAcceptDate();
        if (other.getSignIssueDate() != null)
            this.signIssueDate = other.getSignIssueDate();
        this.docRelatives = other.getDocRelatives();
        if (other.getCriticalLevel() != null)
            this.criticalLevel = other.getCriticalLevel();
        if (other.getIncomeDocType() != null)
            this.incomeDocType = other.getIncomeDocType();
        if (other.getToBeanfinishedDate() != null)
            this.toBeanfinishedDate = other.getToBeanfinishedDate();
    }

    public String getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public void clearProperties() {

        this.internalNo = null;
        this.itemId = null;
        this.acceptNo = null;
        this.itemSource = null;
        this.applyItemType = null;
        this.projectName = null;
        this.applyDemo = null;
        this.incomeDocNo = null;
        this.incomeDocTitle = null;
        this.incomeDeptType = null;
        this.provincialGovDocNo = null;
        this.incomeDeptName = null;
        this.applyOrgCode = null;
        this.docCreateDate = null;
        this.incomeDate = null;
        this.secretDegree = null;
        this.contactName = null;
        this.contactPhone = null;
        this.applyUser = null;
        this.applyUserPhone = null;
        this.applyUserAddr = null;
        this.applyUserZip = null;
        this.incomeDocFileName = null;
        this.incomeDocFile = null;
        this.operateState = null;
        this.syncErrorDesc = null;
        this.createDate = null;
        this.updateDate = null;
        this.syncDate = null;
        this.syncSign = null;
        this.caseAcceptDate = null;
        this.signIssueDate = null;

        this.docRelatives = new HashSet<DocRelative>();

        this.criticalLevel = null;
        this.incomeDocType = null;
        this.toBeanfinishedDate = null;
    }

    public String getIncomeDocType() {
        return incomeDocType;
    }

    public void setIncomeDocType(String incomeDocType) {
        this.incomeDocType = incomeDocType;
    }

    public String getOverdueType() {
        return overdueType;
    }

    public void setOverdueType(String overdueType) {
        this.overdueType = overdueType;
    }

    public String getPrevDjId() {
        return prevDjId;
    }

    public void setPrevDjId(String prevDjId) {
        this.prevDjId = prevDjId;
    }

    public String getNextDjId() {
        return nextDjId;
    }

    public void setNextDjId(String nextDjId) {
        this.nextDjId = nextDjId;
    }
    
}
