package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptIdeaInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long procid;

    private Long nodeInstId;
    private String optcode;
    private String djId;
    private String unitcode;
    private String unitname;
    private String usercode;
    private String username;
    private Date transdate;
    private String ideacode;
    private String transidea;
    private String transcontent;
    private String nodeinststate;
    private String nodename;
    private String flowPhase;
    private Long remainTime;
    private String nodeCode;
    private String isUpload;
    
    private  String moduleCode;//模块代码(控制环节办理意见是否显示)
    
    private String isPC; //是否pc端办理，不是为F，对应附件id存放在TRANSCONTENT ，非
    
    private String warntype;//预报警标识0预警1超时
    private String warntotal;//办件超时标志

    // Constructors
    /** default constructor */
    public OptIdeaInfo() {
    }

    /** minimal constructor */
    public OptIdeaInfo(Long procid, String djId) {

        this.procid = procid;

        this.djId = djId;
    }

    /** full constructor */
    public OptIdeaInfo(Long procid, Long nodeInstId, String optcode,
            String djId, String unitcode, String unitname, String usercode,
            String username, Date transdate, String ideacode, String transidea,
            String transcontent, String nodeinststate, String nodename,
            String flowPhase, Long remainTime, String nodeCode, String isUpload ,String moduleCode,String isPC) {

        this.procid = procid;

        this.nodeInstId = nodeInstId;
        this.optcode = optcode;
        this.djId = djId;
        this.unitcode = unitcode;
        this.unitname = unitname;
        this.usercode = usercode;
        this.username = username;
        this.transdate = transdate;
        this.ideacode = ideacode;
        this.transidea = transidea;
        this.transcontent = transcontent;
        this.nodeinststate = nodeinststate;
        this.nodename = nodename;
        this.flowPhase = flowPhase;
        this.remainTime = remainTime;
        this.nodeCode = nodeCode;
        this.isUpload = isUpload;
        this.moduleCode=moduleCode;
        this.isPC=isPC;
    }

    public Long getProcid() {
        return this.procid;
    }

    public void setProcid(Long procid) {
        this.procid = procid;
    }

    // Property accessors

    public Long getNodeInstId() {
        return this.nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getOptcode() {
        return this.optcode;
    }

    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUnitname() {
        return this.unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTransdate() {
        return this.transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
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

    public String getTranscontent() {
        return this.transcontent;
    }

    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }

    public String getNodeinststate() {
        return this.nodeinststate;
    }

    public void setNodeinststate(String nodeinststate) {
        this.nodeinststate = nodeinststate;
    }

    public String getNodename() {
        return this.nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public void copy(OptIdeaInfo other) {

        this.setProcid(other.getProcid());

        this.nodeInstId = other.getNodeInstId();
        this.optcode = other.getOptcode();
        this.djId = other.getDjId();
        this.unitcode = other.getUnitcode();
        this.unitname = other.getUnitname();
        this.usercode = other.getUsercode();
        this.username = other.getUsername();
        this.transdate = other.getTransdate();
        this.ideacode = other.getIdeacode();
        this.transidea = other.getTransidea();
        this.transcontent = other.getTranscontent();
        this.nodeinststate = other.getNodeinststate();
        this.nodename = other.getNodename();
        this.flowPhase = other.getFlowPhase();
        this.remainTime = other.getRemainTime();
        this.nodeCode = other.getNodeCode();
        this.isUpload = other.getIsUpload();
        this.moduleCode=other.getModuleCode();
        this.isPC=other.getIsPC();
    }

    public void copyNotNullProperty(OptIdeaInfo other) {

        if (other.getProcid() != null)
            this.setProcid(other.getProcid());

        if (other.getNodeInstId() != null)
            this.nodeInstId = other.getNodeInstId();
        if (other.getOptcode() != null)
            this.optcode = other.getOptcode();
        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getUnitname() != null)
            this.unitname = other.getUnitname();
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getUsername() != null)
            this.username = other.getUsername();
        if (other.getTransdate() != null)
            this.transdate = other.getTransdate();
        if (other.getIdeacode() != null)
            this.ideacode = other.getIdeacode();
        if (other.getTransidea() != null)
            this.transidea = other.getTransidea();
        if (other.getTranscontent() != null)
            this.transcontent = other.getTranscontent();
        if (other.getNodeinststate() != null)
            this.nodeinststate = other.getNodeinststate();
        if (other.getNodename() != null)
            this.nodename = other.getNodename();
        if (other.getFlowPhase() != null)
            this.flowPhase = other.getFlowPhase();
        if (other.getRemainTime() != null)
            this.remainTime = other.getRemainTime();
        if (other.getNodeCode() != null)
            this.nodeCode = other.getNodeCode();
        if (other.getIsUpload() != null)
            this.isUpload = other.getIsUpload();
        if(other.getModuleCode()!=null)
            this.moduleCode=other.getModuleCode();
        if(other.getIsPC()!=null)
            this.isPC=other.getIsPC();
    }

    public void clearProperties() {

        this.nodeInstId = null;
        this.optcode = null;
        this.djId = null;
        this.unitcode = null;
        this.unitname = null;
        this.usercode = null;
        this.username = null;
        this.transdate = null;
        this.ideacode = null;
        this.transidea = null;
        this.transcontent = null;
        this.nodeinststate = null;
        this.nodename = null;
        this.flowPhase = null;
        this.remainTime = null;
        this.nodeCode = null;
        this.isUpload = null;
        this.moduleCode=null;
        this.isPC=null;
    }

    public String getFlowPhase() {
        return flowPhase;
    }

    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
    }

    public Long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Long remainTime) {
        this.remainTime = remainTime;
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

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getIsPC() {
        return isPC;
    }

    public void setIsPC(String isPC) {
        this.isPC = isPC;
    }

    public String getWarntype() {
        return warntype;
    }

    public void setWarntype(String warntype) {
        this.warntype = warntype;
    }

    public String getWarntotal() {
        return warntotal;
    }

    public void setWarntotal(String warntotal) {
        this.warntotal = warntotal;
    }
    
    
}
