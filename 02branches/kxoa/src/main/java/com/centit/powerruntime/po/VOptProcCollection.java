package com.centit.powerruntime.po;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.centit.core.structs2.OptDesc;
import com.centit.core.structs2.Struts2UrlParser;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VOptProcCollection implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

//    private String djId;
    private OptProcCollectionId cid;
    
    private Long nodeInstId;
    
    private String unitCode;
    private String userCode;
    private String roleType;
    private String roleCode;
    private String optId;
    private Long flowInstId;
    private String flowOptTag;
    private String nodeName;
    private String methodName;
    private String optUrl;
    private String optMethod;
    private String optCode;
    private String optParam;
    private String inststate;
    private String grantor;
    private Long timeLimit;
    private Date lastUpdateTime;
    private String flowPhase;
    private String transaffairname;
    private String bizstate;
    private Date createdate;
    private String transAffairNo;
    private String atttype;
    private Date attsettime;
    private String isatt;
    private Date removesettime;
    private String nodeCode;
    private String isPower;

    private Date  begTime;//开始时间   
    private Date  endTime;//结束时间
    // Constructors
    /** default constructor */
    public VOptProcCollection() {
    }

    /** minimal constructor */
    public VOptProcCollection(OptProcCollectionId id, String atttype, Date attsettime,
            String isatt) {

        this.cid = id;

        this.atttype = atttype;
        this.attsettime = attsettime;
        this.isatt = isatt;
    }

    /** full constructor */
    public VOptProcCollection(OptProcCollectionId id , Long nodeInstId, String unitCode,
            String userCode, String roleType, String roleCode, String optId,
            Long flowInstId, String flowOptTag, String nodeName,
            String methodName, String optUrl, String optMethod, String optCode,
            String optParam, String inststate, String grantor, Long timeLimit,
            Date lastUpdateTime, String flowPhase, String transaffairname,
            String bizstate, Date createdate, String transAffairNo,
            String atttype, Date attsettime, String isatt, Date removesettime) {

        this.cid = id;

        this.nodeInstId = nodeInstId;
        this.unitCode = unitCode;
        this.userCode = userCode;
        this.roleType = roleType;
        this.roleCode = roleCode;
        this.optId = optId;
        this.flowInstId = flowInstId;
        this.flowOptTag = flowOptTag;
        this.nodeName = nodeName;
        this.methodName = methodName;
        this.optUrl = optUrl;
        this.optMethod = optMethod;
        this.optCode = optCode;
        this.optParam = optParam;
        this.inststate = inststate;
        this.grantor = grantor;
        this.timeLimit = timeLimit;
        this.lastUpdateTime = lastUpdateTime;
        this.flowPhase = flowPhase;
        this.transaffairname = transaffairname;
        this.bizstate = bizstate;
        this.createdate = createdate;
        this.transAffairNo = transAffairNo;
        this.atttype = atttype;
        this.attsettime = attsettime;
        this.isatt = isatt;
        this.removesettime = removesettime;
    }

   

    // Property accessors

    public Long getNodeInstId() {
        return this.nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getOptId() {
        return this.optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public Long getFlowInstId() {
        return this.flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getFlowOptTag() {
        return this.flowOptTag;
    }

    public void setFlowOptTag(String flowOptTag) {
        this.flowOptTag = flowOptTag;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOptUrl() {
        return this.optUrl;
    }

    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    public String getOptMethod() {
        return this.optMethod;
    }

    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }

    public String getOptCode() {
        return this.optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptParam() {
        return this.optParam;
    }

    public void setOptParam(String optParam) {
        this.optParam = optParam;
    }

    public String getInststate() {
        return this.inststate;
    }

    public void setInststate(String inststate) {
        this.inststate = inststate;
    }

    public String getGrantor() {
        return this.grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public Long getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFlowPhase() {
        return this.flowPhase;
    }

    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
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

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getTransAffairNo() {
        return this.transAffairNo;
    }

    public void setTransAffairNo(String transAffairNo) {
        this.transAffairNo = transAffairNo;
    }

    public String getAtttype() {
        return this.atttype;
    }

    public void setAtttype(String atttype) {
        this.atttype = atttype;
    }

    public Date getAttsettime() {
        return this.attsettime;
    }

    public void setAttsettime(Date attsettime) {
        this.attsettime = attsettime;
    }

    public String getIsatt() {
        return this.isatt;
    }

    public void setIsatt(String isatt) {
        this.isatt = isatt;
    }

    public Date getRemovesettime() {
        return this.removesettime;
    }

    public void setRemovesettime(Date removesettime) {
        this.removesettime = removesettime;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getIsPower() {
        return isPower;
    }

    public void setIsPower(String isPower) {
        this.isPower = isPower;
    }

    public void copy(VOptProcCollection other) {

        this.setDjId(other.getDjId());

        this.nodeInstId = other.getNodeInstId();
        this.unitCode = other.getUnitCode();
        this.userCode = other.getUserCode();
        this.roleType = other.getRoleType();
        this.roleCode = other.getRoleCode();
        this.optId = other.getOptId();
        this.flowInstId = other.getFlowInstId();
        this.flowOptTag = other.getFlowOptTag();
        this.nodeName = other.getNodeName();
        this.methodName = other.getMethodName();
        this.optUrl = other.getOptUrl();
        this.optMethod = other.getOptMethod();
        this.optCode = other.getOptCode();
        this.optParam = other.getOptParam();
        this.inststate = other.getInststate();
        this.grantor = other.getGrantor();
        this.timeLimit = other.getTimeLimit();
        this.lastUpdateTime = other.getLastUpdateTime();
        this.flowPhase = other.getFlowPhase();
        this.transaffairname = other.getTransaffairname();
        this.bizstate = other.getBizstate();
        this.createdate = other.getCreatedate();
        this.transAffairNo = other.getTransAffairNo();
        this.atttype = other.getAtttype();
        this.attsettime = other.getAttsettime();
        this.isatt = other.getIsatt();
        this.removesettime = other.getRemovesettime();
        this.nodeCode = other.getNodeCode();

    }

    public void copyNotNullProperty(VOptProcCollection other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getNodeInstId() != null)
            this.nodeInstId = other.getNodeInstId();
        if (other.getUnitCode() != null)
            this.unitCode = other.getUnitCode();
        if (other.getUserCode() != null)
            this.userCode = other.getUserCode();
        if (other.getRoleType() != null)
            this.roleType = other.getRoleType();
        if (other.getRoleCode() != null)
            this.roleCode = other.getRoleCode();
        if (other.getOptId() != null)
            this.optId = other.getOptId();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getFlowOptTag() != null)
            this.flowOptTag = other.getFlowOptTag();
        if (other.getNodeName() != null)
            this.nodeName = other.getNodeName();
        if (other.getMethodName() != null)
            this.methodName = other.getMethodName();
        if (other.getOptUrl() != null)
            this.optUrl = other.getOptUrl();
        if (other.getOptMethod() != null)
            this.optMethod = other.getOptMethod();
        if (other.getOptCode() != null)
            this.optCode = other.getOptCode();
        if (other.getOptParam() != null)
            this.optParam = other.getOptParam();
        if (other.getInststate() != null)
            this.inststate = other.getInststate();
        if (other.getGrantor() != null)
            this.grantor = other.getGrantor();
        if (other.getTimeLimit() != null)
            this.timeLimit = other.getTimeLimit();
        if (other.getLastUpdateTime() != null)
            this.lastUpdateTime = other.getLastUpdateTime();
        if (other.getFlowPhase() != null)
            this.flowPhase = other.getFlowPhase();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getTransAffairNo() != null)
            this.transAffairNo = other.getTransAffairNo();
        if (other.getAtttype() != null)
            this.atttype = other.getAtttype();
        if (other.getAttsettime() != null)
            this.attsettime = other.getAttsettime();
        if (other.getIsatt() != null)
            this.isatt = other.getIsatt();
        if (other.getRemovesettime() != null)
            this.removesettime = other.getRemovesettime();
        if (other.getNodeCode() != null)
            this.nodeCode = other.getNodeCode();
    }

    public void clearProperties() {

        this.nodeInstId = null;
        this.unitCode = null;
        this.userCode = null;
        this.roleType = null;
        this.roleCode = null;
        this.optId = null;
        this.flowInstId = null;
        this.flowOptTag = null;
        this.nodeName = null;
        this.methodName = null;
        this.optUrl = null;
        this.optMethod = null;
        this.optCode = null;
        this.optParam = null;
        this.inststate = null;
        this.grantor = null;
        this.timeLimit = null;
        this.lastUpdateTime = null;
        this.flowPhase = null;
        this.transaffairname = null;
        this.bizstate = null;
        this.createdate = null;
        this.transAffairNo = null;
        this.atttype = null;
        this.attsettime = null;
        this.isatt = null;
        this.removesettime = null;
        this.nodeCode = null;
    }

    public String getSuperUrl() {
        return CodeRepositoryUtil.getValue("optType", StringUtils.substringBefore(getDjId(), "0"))+"!"+BizCommUtil.getViewMethod(getDjId())+"?djId=" + getDjId();
    }

    public String getNodeOptUrl() {
        if (optUrl == null) {
            return null;
        }

        OptDesc optDesc = Struts2UrlParser.parseUrl(optUrl);
        optDesc.setMethod(optMethod);
        String url = optDesc.getOptUrl() + "?nodeInstId=" + getNodeInstId()
                + "&flowInstId=" + flowInstId + "&flowPhase=" + flowPhase
                + "&nodeCode=" + getNodeCode();
        if (grantor != null && !"".equals(grantor)) {// &&
                                                     // !grantor.equals(userCode)){
            url = url + "&isGrantor=yes&grantor=" + grantor;
        }
        if (optParam != null && !"".equals(optParam))
            url = url + "&" + optParam;

        try {
            url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "/sampleflow/flowUserTask!todoWork.do?nodeInstId="
                + getNodeInstId() + "&flowInstId=" + flowInstId
                + "&nodeOptUrl=" + url;
    }
    public OptProcCollectionId getCid() {
        return this.cid;
    }
    
    public void setCid(OptProcCollectionId id) {
        this.cid = id;
    }
    
    public String getDjId() {
        if(this.cid==null)
            this.cid = new OptProcCollectionId();
        return this.cid.getDjId();
    }
    
    public Date getBegTime() {
        return begTime;
    }

    public void setBegTime(Date begTime) {
        this.begTime = begTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setDjId(String djId) {
        if(this.cid==null)
            this.cid = new OptProcCollectionId();
        this.cid.setDjId(djId);
    }
}
