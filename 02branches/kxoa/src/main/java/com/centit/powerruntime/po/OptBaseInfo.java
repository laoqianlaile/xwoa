package com.centit.powerruntime.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptBaseInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

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
    private String content;
    private String powerid;
    private String powername;
    private String solvestatus;
    private Date solvetime;
    private String solvenote;
    private String createuser;
    private Date createdate;
    private String caseNo;
    private String sendArchiveNo;
    private String acceptArchiveNo;
    private String riskType;
    private String riskDesc;
    private String riskResult;
    private String isUpload;
    private String recievedepno;
    
    private Set<OptProcInfo> optProcInfos = null;// new
                                                 // ArrayList<OptProcInfo>();
    private Set<OptProcAttention> optProcAttentions = null;// new
                                                           // ArrayList<OptProcAttention>();
    private Set<OptStuffInfo> optStuffInfos = null;// new
                                                   // ArrayList<OptStuffInfo>();

    private String dbType;//督办状态
    private String itemtype;
    
    private String criticalLevel;//缓急程度
    
    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    // Constructors
    /** default constructor */
    public OptBaseInfo() {
    }

    /** minimal constructor */
    public OptBaseInfo(String djId) {

        this.djId = djId;

    }

    public String getRecievedepno() {
        return recievedepno;
    }

    public void setRecievedepno(String recievedepno) {
        this.recievedepno = recievedepno;
    }

    /** full constructor */
    public OptBaseInfo(String djId, String optId, String flowCode,
            Long flowInstId, String transAffairNo, String transaffairname,
            String transAffairQueryKey, String bizstate, String biztype,
            String orgcode, String orgname, String headunitcode,
            String headusercode, String content, String powerid,
            String powername, String solvestatus, Date solvetime,
            String solvenote, String createuser, Date createdate,
            String caseNo, String sendArchiveNo, String acceptArchiveNo,
            String riskType, String riskDesc, String riskResult, String isUpload) {

        this.djId = djId;

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
        this.content = content;
        this.powerid = powerid;
        this.powername = powername;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.createuser = createuser;
        this.createdate = createdate;
        this.caseNo = caseNo;
        this.sendArchiveNo = sendArchiveNo;
        this.acceptArchiveNo = acceptArchiveNo;
        this.riskType = riskType;
        this.riskDesc = riskDesc;
        this.riskResult = riskResult;
        this.isUpload = isUpload;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

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

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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

    public String getRiskType() {
        return this.riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getRiskDesc() {
        return this.riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public String getRiskResult() {
        return this.riskResult;
    }

    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public Set<OptProcInfo> getOptProcInfos() {
        if (this.optProcInfos == null)
            this.optProcInfos = new HashSet<OptProcInfo>();
        return this.optProcInfos;
    }

    public void setOptProcInfos(Set<OptProcInfo> optProcInfos) {
        this.optProcInfos = optProcInfos;
    }

    public void addOptProcInfo(OptProcInfo optProcInfo) {
        if (this.optProcInfos == null)
            this.optProcInfos = new HashSet<OptProcInfo>();
        this.optProcInfos.add(optProcInfo);
    }

    public void removeOptProcInfo(OptProcInfo optProcInfo) {
        if (this.optProcInfos == null)
            return;
        this.optProcInfos.remove(optProcInfo);
    }

    public OptProcInfo newOptProcInfo() {
        OptProcInfo res = new OptProcInfo();

        res.setDjId(this.getDjId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceOptProcInfos(List<OptProcInfo> optProcInfos) {
        List<OptProcInfo> newObjs = new ArrayList<OptProcInfo>();
        for (OptProcInfo p : optProcInfos) {
            if (p == null)
                continue;
            OptProcInfo newdt = newOptProcInfo();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OptProcInfo> oldObjs = new HashSet<OptProcInfo>();
        oldObjs.addAll(getOptProcInfos());

        for (Iterator<OptProcInfo> it = oldObjs.iterator(); it.hasNext();) {
            OptProcInfo odt = it.next();
            found = false;
            for (OptProcInfo newdt : newObjs) {
                if (odt.getNodeInstId().equals(newdt.getNodeInstId())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOptProcInfo(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OptProcInfo newdt : newObjs) {
            found = false;
            for (Iterator<OptProcInfo> it = getOptProcInfos().iterator(); it
                    .hasNext();) {
                OptProcInfo odt = it.next();
                if (odt.getNodeInstId().equals(newdt.getNodeInstId())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOptProcInfo(newdt);
        }
    }

    public Set<OptProcAttention> getOptProcAttentions() {
        if (this.optProcAttentions == null)
            this.optProcAttentions = new HashSet<OptProcAttention>();
        return this.optProcAttentions;
    }

    public void setOptProcAttentions(Set<OptProcAttention> optProcAttentions) {
        this.optProcAttentions = optProcAttentions;
    }

    public void addOptProcAttention(OptProcAttention optProcAttention) {
        if (this.optProcAttentions == null)
            this.optProcAttentions = new HashSet<OptProcAttention>();
        this.optProcAttentions.add(optProcAttention);
    }

    public void removeOptProcAttention(OptProcAttention optProcAttention) {
        if (this.optProcAttentions == null)
            return;
        this.optProcAttentions.remove(optProcAttention);
    }

    public OptProcAttention newOptProcAttention() {
        OptProcAttention res = new OptProcAttention();

        res.setDjId(this.getDjId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceOptProcAttentions(
            List<OptProcAttention> optProcAttentions) {
        List<OptProcAttention> newObjs = new ArrayList<OptProcAttention>();
        for (OptProcAttention p : optProcAttentions) {
            if (p == null)
                continue;
            OptProcAttention newdt = newOptProcAttention();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OptProcAttention> oldObjs = new HashSet<OptProcAttention>();
        oldObjs.addAll(getOptProcAttentions());

        for (Iterator<OptProcAttention> it = oldObjs.iterator(); it.hasNext();) {
            OptProcAttention odt = it.next();
            found = false;
            for (OptProcAttention newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOptProcAttention(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OptProcAttention newdt : newObjs) {
            found = false;
            for (Iterator<OptProcAttention> it = getOptProcAttentions()
                    .iterator(); it.hasNext();) {
                OptProcAttention odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOptProcAttention(newdt);
        }
    }

    public Set<OptStuffInfo> getOptStuffInfos() {
        if (this.optStuffInfos == null)
            this.optStuffInfos = new HashSet<OptStuffInfo>();
        return this.optStuffInfos;
    }

    public void setOptStuffInfos(Set<OptStuffInfo> optStuffInfos) {
        this.optStuffInfos = optStuffInfos;
    }

    public void addOptStuffInfo(OptStuffInfo optStuffInfo) {
        if (this.optStuffInfos == null)
            this.optStuffInfos = new HashSet<OptStuffInfo>();
        this.optStuffInfos.add(optStuffInfo);
    }

    public void removeOptStuffInfo(OptStuffInfo optStuffInfo) {
        if (this.optStuffInfos == null)
            return;
        this.optStuffInfos.remove(optStuffInfo);
    }

    public OptStuffInfo newOptStuffInfo() {
        OptStuffInfo res = new OptStuffInfo();

        res.setDjId(this.getDjId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceOptStuffInfos(List<OptStuffInfo> optStuffInfos) {
        List<OptStuffInfo> newObjs = new ArrayList<OptStuffInfo>();
        for (OptStuffInfo p : optStuffInfos) {
            if (p == null)
                continue;
            OptStuffInfo newdt = newOptStuffInfo();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OptStuffInfo> oldObjs = new HashSet<OptStuffInfo>();
        oldObjs.addAll(getOptStuffInfos());

        for (Iterator<OptStuffInfo> it = oldObjs.iterator(); it.hasNext();) {
            OptStuffInfo odt = it.next();
            found = false;
            for (OptStuffInfo newdt : newObjs) {
                if (odt.getStuffid().equals(newdt.getStuffid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOptStuffInfo(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OptStuffInfo newdt : newObjs) {
            found = false;
            for (Iterator<OptStuffInfo> it = getOptStuffInfos().iterator(); it
                    .hasNext();) {
                OptStuffInfo odt = it.next();
                if (odt.getStuffid().equals(newdt.getStuffid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOptStuffInfo(newdt);
        }
    }

    public void copy(OptBaseInfo other) {

        this.setDjId(other.getDjId());

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
        this.content = other.getContent();
        this.powerid = other.getPowerid();
        this.powername = other.getPowername();
        this.solvestatus = other.getSolvestatus();
        this.solvetime = other.getSolvetime();
        this.solvenote = other.getSolvenote();
        this.createuser = other.getCreateuser();
        this.createdate = other.getCreatedate();
        this.caseNo = other.getCaseNo();
        this.sendArchiveNo = other.getSendArchiveNo();
        this.acceptArchiveNo = other.getAcceptArchiveNo();
        this.riskType = other.getRiskType();
        this.riskDesc = other.getRiskDesc();
        this.riskResult = other.getRiskResult();
        this.isUpload = other.getIsUpload();

        this.optProcInfos = other.getOptProcInfos();
        this.optProcAttentions = other.getOptProcAttentions();
        this.optStuffInfos = other.getOptStuffInfos();
        this.criticalLevel = other.getCriticalLevel();
    }

    public void copyNotNullProperty(OptBaseInfo other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

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
        if (other.getContent() != null)
            this.content = other.getContent();
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
        if (other.getCreateuser() != null)
            this.createuser = other.getCreateuser();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getCaseNo() != null)
            this.caseNo = other.getCaseNo();
        if (other.getSendArchiveNo() != null)
            this.sendArchiveNo = other.getSendArchiveNo();
        if (other.getAcceptArchiveNo() != null)
            this.acceptArchiveNo = other.getAcceptArchiveNo();
        if (other.getRiskType() != null)
            this.riskType = other.getRiskType();
        if (other.getRiskDesc() != null)
            this.riskDesc = other.getRiskDesc();
        if (other.getRiskResult() != null)
            this.riskResult = other.getRiskResult();
        if (other.getIsUpload() != null)
            this.isUpload = other.getIsUpload();

        this.optProcInfos = other.getOptProcInfos();
        this.optProcAttentions = other.getOptProcAttentions();
        this.optStuffInfos = other.getOptStuffInfos();
        if(other.getCriticalLevel() != null)
            this.criticalLevel = other.getCriticalLevel();
    }

    public void clearProperties() {

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
        this.content = null;
        this.powerid = null;
        this.powername = null;
        this.solvestatus = null;
        this.solvetime = null;
        this.solvenote = null;
        this.createuser = null;
        this.createdate = null;
        this.caseNo = null;
        this.sendArchiveNo = null;
        this.acceptArchiveNo = null;
        this.riskType = null;
        this.riskDesc = null;
        this.riskResult = null;
        this.isUpload = null;
        this.criticalLevel = null;

        this.optProcInfos = new HashSet<OptProcInfo>();
        this.optProcAttentions = new HashSet<OptProcAttention>();
        this.optStuffInfos = new HashSet<OptStuffInfo>();
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
    
    public String getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }
    
}
