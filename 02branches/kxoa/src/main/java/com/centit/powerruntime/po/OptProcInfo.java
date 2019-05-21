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

public class OptProcInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long nodeInstId;

    private String djId;
    private String nodename;
    private String nodeinststate;
    //内容
    private String transcontent;
    //结果
    private String ideacode;
    private String ishq;
    private String iswbhq;
    private String ishbhq;
    private String transidea;
    private String permitLevel;
    private Date transdate;
    private String usercode;
    private String unitcode;
    private String istrans;
    private String optcode;
    private String isrisk;
    private String risktype;
    private String riskdesc;
    private String riskresult;
    private String recordId;// 模版编号
    private String archiveType;//文书类别
    private String cfType;//处罚决定类型
    private String nodeCode;
    private String flowPhase;
    
    private String isSendMessage;
    
    
    public String getFlowPhase() {
        return flowPhase;
    }

    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
    }

    private Set<OptStuffInfo> optStuffInfos = null;// new
                                                   // ArrayList<OptStuffInfo>();
    private Set<OptIdeaInfo> optIdeaInfos = null;// new
                                                 // ArrayList<OptIdeaInfo>();
    private OptProcAttention optProcAttention;// 关注信息
    
    
    private Long isdisplay;
    private Long orderno;
    private String url;
    private String intalNodeName;
    public OptProcAttention getOptProcAttention() {
        return optProcAttention;
    }

    public void setOptProcAttention(OptProcAttention optProcAttention) {
        this.optProcAttention = optProcAttention;
    }

    // Constructors
    /** default constructor */
    public OptProcInfo() {
    }

    /** minimal constructor */
    public OptProcInfo(Long nodeInstId, String djId) {

        this.nodeInstId = nodeInstId;

        this.djId = djId;
    }

    
    
    public String getCfType() {
        return cfType;
    }

    public String getIntalNodeName() {
        return intalNodeName;
    }

    public void setIntalNodeName(String intalNodeName) {
        this.intalNodeName = intalNodeName;
    }

    public void setCfType(String cfType) {
        this.cfType = cfType;
    }

    /** full constructor */
    public OptProcInfo(Long nodeInstId, String djId, String nodename,
            String nodeinststate, String transcontent, String ideacode,
            String transidea, Date transdate, String usercode, String unitcode,
            String istrans, String optcode, String isrisk, String risktype,
            String riskdesc, String riskresult,String recordId) {

        this.nodeInstId = nodeInstId;

        this.djId = djId;
        this.nodename = nodename;
        this.nodeinststate = nodeinststate;
        this.transcontent = transcontent;
        this.ideacode = ideacode;
        this.transidea = transidea;
        this.transdate = transdate;
        this.usercode = usercode;
        this.unitcode = unitcode;
        this.istrans = istrans;
        this.optcode = optcode;
        this.isrisk = isrisk;
        this.risktype = risktype;
        this.riskdesc = riskdesc;
        this.riskresult = riskresult;
        this.recordId = recordId;
    }

    public Long getNodeInstId() {
        return this.nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    // Property accessors

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getNodename() {
        return this.nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getNodeinststate() {
        return this.nodeinststate;
    }

    public void setNodeinststate(String nodeinststate) {
        this.nodeinststate = nodeinststate;
    }

    public String getTranscontent() {
        return this.transcontent;
    }

    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }

    public String getIdeacode() {
        return this.ideacode;
    }

    public void setIdeacode(String ideacode) {
        this.ideacode = ideacode;
    }

    public String getTransidea() {
        return this.transidea;
    }

    public void setTransidea(String transidea) {
        this.transidea = transidea;
    }

    public Date getTransdate() {
        return this.transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getIstrans() {
        return this.istrans;
    }

    public void setIstrans(String istrans) {
        this.istrans = istrans;
    }

    public String getOptcode() {
        return this.optcode;
    }

    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }

    public String getIsrisk() {
        return this.isrisk;
    }

    public void setIsrisk(String isrisk) {
        this.isrisk = isrisk;
    }

    public String getRisktype() {
        return this.risktype;
    }

    public void setRisktype(String risktype) {
        this.risktype = risktype;
    }

    public String getRiskdesc() {
        return this.riskdesc;
    }

    public void setRiskdesc(String riskdesc) {
        this.riskdesc = riskdesc;
    }

    public String getRiskresult() {
        return this.riskresult;
    }

    public void setRiskresult(String riskresult) {
        this.riskresult = riskresult;
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

        res.setNodeInstId(this.getNodeInstId());

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

    public Set<OptIdeaInfo> getOptIdeaInfos() {
        if (this.optIdeaInfos == null)
            this.optIdeaInfos = new HashSet<OptIdeaInfo>();
        return this.optIdeaInfos;
    }

    public void setOptIdeaInfos(Set<OptIdeaInfo> optIdeaInfos) {
        this.optIdeaInfos = optIdeaInfos;
    }

    public void addOptIdeaInfo(OptIdeaInfo optIdeaInfo) {
        if (this.optIdeaInfos == null)
            this.optIdeaInfos = new HashSet<OptIdeaInfo>();
        this.optIdeaInfos.add(optIdeaInfo);
    }

    public void removeOptIdeaInfo(OptIdeaInfo optIdeaInfo) {
        if (this.optIdeaInfos == null)
            return;
        this.optIdeaInfos.remove(optIdeaInfo);
    }

    public OptIdeaInfo newOptIdeaInfo() {
        OptIdeaInfo res = new OptIdeaInfo();

        res.setNodeInstId(this.getNodeInstId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replaceOptIdeaInfos(List<OptIdeaInfo> optIdeaInfos) {
        List<OptIdeaInfo> newObjs = new ArrayList<OptIdeaInfo>();
        for (OptIdeaInfo p : optIdeaInfos) {
            if (p == null)
                continue;
            OptIdeaInfo newdt = newOptIdeaInfo();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OptIdeaInfo> oldObjs = new HashSet<OptIdeaInfo>();
        oldObjs.addAll(getOptIdeaInfos());

        for (Iterator<OptIdeaInfo> it = oldObjs.iterator(); it.hasNext();) {
            OptIdeaInfo odt = it.next();
            found = false;
            for (OptIdeaInfo newdt : newObjs) {
                if (odt.getProcid().equals(newdt.getProcid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOptIdeaInfo(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OptIdeaInfo newdt : newObjs) {
            found = false;
            for (Iterator<OptIdeaInfo> it = getOptIdeaInfos().iterator(); it
                    .hasNext();) {
                OptIdeaInfo odt = it.next();
                if (odt.getProcid().equals(newdt.getProcid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOptIdeaInfo(newdt);
        }
    }

    public void copy(OptProcInfo other) {

        this.setNodeInstId(other.getNodeInstId());

        this.djId = other.getDjId();
        this.nodename = other.getNodename();
        this.nodeinststate = other.getNodeinststate();
        this.transcontent = other.getTranscontent();
        this.ideacode = other.getIdeacode();
        this.transidea = other.getTransidea();
        this.ishq = other.getIshq();
        this.iswbhq = other.getIswbhq();
        this.ishbhq = other.getIshbhq();
        this.transdate = other.getTransdate();
        this.usercode = other.getUsercode();
        this.unitcode = other.getUnitcode();
        this.istrans = other.getIstrans();
        this.optcode = other.getOptcode();
        this.isrisk = other.getIsrisk();
        this.risktype = other.getRisktype();
        this.riskdesc = other.getRiskdesc();
        this.riskresult = other.getRiskresult();
        this.recordId = other.getRecordId();
        this.optStuffInfos = other.getOptStuffInfos();
        this.archiveType = other.getArchiveType();
        this.optIdeaInfos = other.getOptIdeaInfos();
        this.nodeCode = other.getNodeCode();
        this.flowPhase = other.getFlowPhase();
    }

    public void copyNotNullProperty(OptProcInfo other) {

        if (other.getNodeInstId() != null)
            this.setNodeInstId(other.getNodeInstId());

        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getNodename() != null)
            this.nodename = other.getNodename();
        if (other.getNodeinststate() != null)
            this.nodeinststate = other.getNodeinststate();
        if (other.getTranscontent() != null)
            this.transcontent = other.getTranscontent();
        if (other.getIdeacode() != null)
            this.ideacode = other.getIdeacode();
        if (other.getTransidea() != null)
            this.transidea = other.getTransidea();
        if (other.getTransdate() != null)
            this.transdate = other.getTransdate();
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if(other.getIshq() != null)
            this.ishq = other.getIshq();
        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getIstrans() != null)
            this.istrans = other.getIstrans();
        if (other.getOptcode() != null)
            this.optcode = other.getOptcode();
        if (other.getIsrisk() != null)
            this.isrisk = other.getIsrisk();
        if (other.getRisktype() != null)
            this.risktype = other.getRisktype();
        if (other.getRiskdesc() != null)
            this.riskdesc = other.getRiskdesc();
        if (other.getRiskresult() != null)
            this.riskresult = other.getRiskresult();
        if( other.getRecordId() !=null){
            this.recordId = other.getRecordId();
        }
        if (other.getFlowPhase() != null)
            this.flowPhase = other.getFlowPhase();
        if( other.getArchiveType() != null){
            this.archiveType = other.getArchiveType();
        }
        
        if(other.getNodeCode() != null)
            this.nodeCode = other.getNodeCode();
        if(other.getIswbhq() != null)
            this.iswbhq = other.getIswbhq();
        if( other.getIshbhq() != null)
        this.ishbhq = other.getIshbhq();

        this.optStuffInfos = other.getOptStuffInfos();
        this.optIdeaInfos = other.getOptIdeaInfos();
    }

    public void clearProperties() {

        this.djId = null;
        this.nodename = null;
        this.nodeinststate = null;
        this.transcontent = null;
        this.ideacode = null;
        this.transidea = null;
        this.ishq = null;
        this.iswbhq = null;
        this.ishbhq = null;
        this.transdate = null;
        this.usercode = null;
        this.unitcode = null;
        this.istrans = null;
        this.optcode = null;
        this.isrisk = null;
        this.risktype = null;
        this.riskdesc = null;
        this.riskresult = null;
        this.recordId = null;
        this.archiveType = null;
        this.optStuffInfos = new HashSet<OptStuffInfo>();
        this.optIdeaInfos = new HashSet<OptIdeaInfo>();
        this.nodeCode = null;
        this.flowPhase = null;
    }

    public String getPermitLevel() {
        return permitLevel;
    }

    public void setPermitLevel(String permitLevel) {
        this.permitLevel = permitLevel;
    }

    public String getRecordId() {
        
        return recordId;
    }

    public void setRecordId(String recordId) {
        
        if(recordId != null && recordId.indexOf(',') > 0){
            String[] sTemp =  recordId.split("[,]");
            recordId = sTemp[0];
        }
        this.recordId = recordId;
    }

    public Long getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Long isdisplay) {
        this.isdisplay = isdisplay;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }

    public String getIshq() {
        return ishq;
    }

    public void setIshq(String ishq) {
        this.ishq = ishq;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        if (null != nodeCode && (0 == nodeCode.indexOf("P_") || 0 == nodeCode.indexOf("N_"))) {
            nodeCode = nodeCode.substring(2);
        }
        this.nodeCode = nodeCode;
    }

    public String getIswbhq() {
        return iswbhq;
    }

    public void setIswbhq(String iswbhq) {
        this.iswbhq = iswbhq;
    }

    public String getIshbhq() {
        return ishbhq;
    }

    public void setIshbhq(String ishbhq) {
        this.ishbhq = ishbhq;
    }
    
    /**
     * 获取业务类型
     * @param djId
     * @return
     */
   
    public int getBizType(String djId) {
        String type = djId.replaceAll("\\d+", "");
        if("FW".equals(type)){
            return 1;
        }
        if("SW".equals(type)){
            return 2;
        }
        //可以补充
        return 0;
    }

    public String getIsSendMessage() {
        return isSendMessage;
    }

    public void setIsSendMessage(String isSendMessage) {
        this.isSendMessage = isSendMessage;
    }
    
}
