package com.centit.powerruntime.po;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.centit.core.structs2.OptDesc;
import com.centit.core.structs2.Struts2UrlParser;

public class VOptApplyTaskList implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long taskId;
    
    private Long nodeInstId;

    private String unitCode;

    private String userCode;

    
    private Long  flowInstId;
    private String  authDesc;
    private String  flowOptName;
    private String  flowOptTag;
    private String  nodeName;
    private String  nodeType;
    private String  nodeOptType;
    private String  optName;
    private String  methodName;
    private String optId;
    private String  optParam;
    private String  optUrl;
    private String  optMethod;
    private String  optDesc;
    private String  optCode;
    private Date  createTime;
    private String  expireOpt;
    private Date  lastUpdateTime;
    private String lastUpdateUser;
    private Long  promiseTime;    
    private Long  timeLimit;
    private String grantor;
    private String roleType;
    private String roleCode;    
    private String inststate;
    private Date nodeCreateTime;
    private Date nodeExpireTime;
    private Long expireOptSign;
    private Date nodeLastUpdateTime;
    private Date flowExpireTime;
    private Long flowTimeLimit;
    private String djId;
    private String  transaffairname;
    private String  bizstate;
    private String  biztype;//F未提交，W等待受理，T办理中，C办结
    private String  orgcode;
    private String  orgname;
    private String  headunitcode;
    private String  headusercode;
    private String  content;
    private String  powerid;
    private String  powername;
    private String  solvestatus;
    private Date  solvetime;
    private String  solvenote;
    private String  createuser;
    private String  riskType;
    private String  riskDesc;
    private String  riskResult;
    private String  transAffairNo;//办件编号
    private String  transAffairQueryKey;//办件查询密码
    private Date  createdate;
    private  String sendArchiveNo;//发文号
    private String acceptArchiveNo;//收文号
    private String flowPhase;
    
    public String getFlowPhase() {
        return flowPhase;
    }
    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
    }
    public VOptApplyTaskList(){
        
    }
    public VOptApplyTaskList(Long taskId){
        this.taskId=taskId;        
    }
    
    public VOptApplyTaskList(
            Long nodeInstId,

            String unitCode,
            String flowPhase,

            String userCode,
           
            Long  flowInstId,
            String  authDesc,
            String  flowOptName,
            String  flowOptTag,
            String  nodeName,
            String  nodeType,
            String  nodeOptType,
            String  optName,
            String  methodName,
            String optId,
            String  optParam,
            String  optUrl,
            String  optMethod,
            String  optDesc,
            String  optCode,
            Date  createTime,
            String  expireOpt,
            Date  lastUpdateTime,
            String lastUpdateUser,
            Long  promiseTime,    
            Long  timeLimit,
            String grantor,
            String roleType,
            String roleCode,    
            String inststate,
            Date nodeCreateTime,
            Date nodeExpireTime,
            Long expireOptSign,
            Date nodeLastUpdateTime,
            Date flowExpireTime,
            Long flowTimeLimit,
            String djId,
            String  transaffairname,
            String  bizstate,
            String  biztype,
            String  orgcode,
            String  orgname,
            String  headunitcode,
            String  headusercode,
            String  content,
            String  powerid,
            String  powername,
            String  solvestatus,
            Date  solvetime,
            String  solvenote,
            String  createuser,
            String  riskType,
            String  riskDesc,
            String  riskResult,
            String  transAffairNo,
            String  transAffairQueryKey,
            Date  createdate,
             String sendArchiveNo,
            String acceptArchiveNo, Long taskId){
                this.taskId=taskId;

                this.nodeInstId=nodeInstId;
                this.flowPhase=flowPhase;

                this.unitCode=unitCode;

                this.userCode=userCode;

                this.flowInstId=flowInstId;
                this.authDesc=authDesc;
                this.flowOptName=flowOptName;
                this.flowOptTag=flowOptTag;
                this.nodeName=nodeName;
                this.nodeType=nodeType;
                this.nodeOptType=nodeOptType;
                this.optName=optName;
                this.methodName=methodName;
                this.optId=optId;
                this.optParam=optParam;
                this.optUrl=optUrl;
                this.optMethod=optMethod;
                this.optDesc=optDesc;
                this.optCode=optCode;
                this.createTime=createTime;
                this.expireOpt=expireOpt;
                this.lastUpdateTime=lastUpdateTime;
                this.lastUpdateUser=lastUpdateUser;
                this.promiseTime=promiseTime;
                this.timeLimit=timeLimit;
                this.grantor=grantor;
                this.roleType=roleType;
                this.roleCode=roleCode;
                this.inststate=inststate;
                this.nodeCreateTime=nodeCreateTime;
                this.nodeExpireTime=nodeExpireTime;
                this.expireOptSign=expireOptSign;
                this.nodeLastUpdateTime=nodeLastUpdateTime;
                this.flowExpireTime=flowExpireTime;
                this.flowTimeLimit=flowTimeLimit;
                this.djId=djId;
                this.transaffairname=transaffairname;
                this.bizstate=bizstate;
                this.biztype=biztype;//F未提交，W等待受理，T办理中，C办结
                this.orgcode=orgcode;
                this.orgname=orgname;
                this.headunitcode=headunitcode;
                this.headusercode=headusercode;
                this.content=content;
                this.powerid=powerid;
                this.powername=powername;
                this.solvestatus=solvestatus;
                this.solvetime=solvetime;
                this.solvenote=solvenote;
                this.createuser=createuser;
                this.riskType=riskType;
                this.riskDesc=riskDesc;
                this.riskResult=riskResult;
                this.transAffairNo=transAffairNo;//办件编号
                this.transAffairQueryKey=transAffairQueryKey;//办件查询密码
                this.createdate=createdate;
                this.sendArchiveNo=sendArchiveNo;//发文号
                this.acceptArchiveNo=acceptArchiveNo;//收文号
        
    }
    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public Long getNodeInstId() {
        return nodeInstId;
    }
    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public Long getFlowInstId() {
        return flowInstId;
    }
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }
    public String getAuthDesc() {
        return authDesc;
    }
    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }
    public String getFlowOptName() {
        return flowOptName;
    }
    public void setFlowOptName(String flowOptName) {
        this.flowOptName = flowOptName;
    }
    public String getFlowOptTag() {
        return flowOptTag;
    }
    public void setFlowOptTag(String flowOptTag) {
        this.flowOptTag = flowOptTag;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getNodeType() {
        return nodeType;
    }
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
    public String getNodeOptType() {
        return nodeOptType;
    }
    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }
    public String getOptName() {
        return optName;
    }
    public void setOptName(String optName) {
        this.optName = optName;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getOptId() {
        return optId;
    }
    public void setOptId(String optId) {
        this.optId = optId;
    }
    public String getOptParam() {
        return optParam;
    }
    public void setOptParam(String optParam) {
        this.optParam = optParam;
    }
    public String getOptUrl() {
        return optUrl;
    }
    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }
    public String getOptMethod() {
        return optMethod;
    }
    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }
    public String getOptDesc() {
        return optDesc;
    }
    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }
    public String getOptCode() {
        return optCode;
    }
    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getExpireOpt() {
        return expireOpt;
    }
    public void setExpireOpt(String expireOpt) {
        this.expireOpt = expireOpt;
    }
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
    public Long getPromiseTime() {
        return promiseTime;
    }
    public void setPromiseTime(Long promiseTime) {
        this.promiseTime = promiseTime;
    }
    public Long getTimeLimit() {
        return timeLimit;
    }
    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }
    public String getGrantor() {
        return grantor;
    }
    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }
    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public String getInststate() {
        return inststate;
    }
    public void setInststate(String inststate) {
        this.inststate = inststate;
    }
    public Date getNodeCreateTime() {
        return nodeCreateTime;
    }
    public void setNodeCreateTime(Date nodeCreateTime) {
        this.nodeCreateTime = nodeCreateTime;
    }
    public Date getNodeExpireTime() {
        return nodeExpireTime;
    }
    public void setNodeExpireTime(Date nodeExpireTime) {
        this.nodeExpireTime = nodeExpireTime;
    }
    public Long getExpireOptSign() {
        return expireOptSign;
    }
    public void setExpireOptSign(Long expireOptSign) {
        this.expireOptSign = expireOptSign;
    }
    public Date getNodeLastUpdateTime() {
        return nodeLastUpdateTime;
    }
    public void setNodeLastUpdateTime(Date nodeLastUpdateTime) {
        this.nodeLastUpdateTime = nodeLastUpdateTime;
    }
    public Date getFlowExpireTime() {
        return flowExpireTime;
    }
    public void setFlowExpireTime(Date flowExpireTime) {
        this.flowExpireTime = flowExpireTime;
    }
    public Long getFlowTimeLimit() {
        return flowTimeLimit;
    }
    public void setFlowTimeLimit(Long flowTimeLimit) {
        this.flowTimeLimit = flowTimeLimit;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getTransaffairname() {
        return transaffairname;
    }
    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }
    public String getBizstate() {
        return bizstate;
    }
    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }
    public String getBiztype() {
        return biztype;
    }
    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }
    public String getOrgcode() {
        return orgcode;
    }
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
    public String getOrgname() {
        return orgname;
    }
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    public String getHeadunitcode() {
        return headunitcode;
    }
    public void setHeadunitcode(String headunitcode) {
        this.headunitcode = headunitcode;
    }
    public String getHeadusercode() {
        return headusercode;
    }
    public void setHeadusercode(String headusercode) {
        this.headusercode = headusercode;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPowerid() {
        return powerid;
    }
    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }
    public String getPowername() {
        return powername;
    }
    public void setPowername(String powername) {
        this.powername = powername;
    }
    public String getSolvestatus() {
        return solvestatus;
    }
    public void setSolvestatus(String solvestatus) {
        this.solvestatus = solvestatus;
    }
    public Date getSolvetime() {
        return solvetime;
    }
    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }
    public String getSolvenote() {
        return solvenote;
    }
    public void setSolvenote(String solvenote) {
        this.solvenote = solvenote;
    }
    public String getCreateuser() {
        return createuser;
    }
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }
    public String getRiskType() {
        return riskType;
    }
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }
    public String getRiskDesc() {
        return riskDesc;
    }
    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }
    public String getRiskResult() {
        return riskResult;
    }
    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }
    public String getTransAffairNo() {
        return transAffairNo;
    }
    public void setTransAffairNo(String transAffairNo) {
        this.transAffairNo = transAffairNo;
    }
    public String getTransAffairQueryKey() {
        return transAffairQueryKey;
    }
    public void setTransAffairQueryKey(String transAffairQueryKey) {
        this.transAffairQueryKey = transAffairQueryKey;
    }
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getSendArchiveNo() {
        return sendArchiveNo;
    }
    public void setSendArchiveNo(String sendArchiveNo) {
        this.sendArchiveNo = sendArchiveNo;
    }
    public String getAcceptArchiveNo() {
        return acceptArchiveNo;
    }
    public void setAcceptArchiveNo(String acceptArchiveNo) {
        this.acceptArchiveNo = acceptArchiveNo;
    }
    public String getNodeOptUrl() {
        if(optUrl == null){
            return null;
        }
 
        OptDesc optDesc= Struts2UrlParser.parseUrl(optUrl);
        optDesc.setMethod(optMethod);
        String url = optDesc.getOptUrl() +
                "?nodeInstId="+getNodeInstId()+"&flowInstId="+flowInstId+"&flowPhase="+ flowPhase;
        //System.out.println(url);
        if(grantor !=null && !"".equals(grantor) ){//&& !grantor.equals(userCode)){
            url = url+"&isGrantor=yes&grantor="+grantor;
        }
        if(optParam!=null && !"".equals(optParam)) 
            url = url+"&"+optParam;
          
        try {
            url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
     
        return "/sampleflow/flowUserTask!todoWork.do?nodeInstId="+getNodeInstId()+"&flowInstId="+flowInstId+"&nodeOptUrl="+url;
   }
    

}
