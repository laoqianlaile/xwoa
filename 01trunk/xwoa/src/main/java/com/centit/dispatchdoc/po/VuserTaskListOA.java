package com.centit.dispatchdoc.po;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.centit.core.structs2.OptDesc;
import com.centit.core.structs2.Struts2UrlParser;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VuserTaskListOA implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long taskId;

	private Long  nodeInstId;
	private String  unitCode;
	private String  userCode;
	private String  roleType;
	private String  roleCode;
	private String  optId;
	private Long  flowInstId;
	private String  flowOptName;
	
	private String transaffairname;
	
	private String  flowOptTag;
	private String  authDesc;
	private String  nodeName;
	private String  nodeType;
	private String  nodeOptType;
	private String  optName;
	private String  methodName;
	private String  optUrl;
	private String  optMethod;
	private String  optDesc;
	private String  optCode;
	private String  optParam;
	private String  inststate;
	private Date  nodeCreateTime;
	private Long  expireOptSign;
	private String  expireOpt;
	private String  grantor;
	private Long  timeLimit;
	private String  lastUpdateUser;
	private Date  lastUpdateTime;
	private String  flowPhase;
	private String  nodeCode;
	private String  djId;
	private String  powerid;
	private String  powername;
	private String  flowCode;
	private String  createuser;
	private Date  createdate;
	private String  caseNo;
	private String  sendArchiveNo;
	private String  acceptArchiveNo;
    private String itemtype;
    private String supdjid;  //与督办表关联后的 督办业务流水号
    private String warnType;  //督办提醒类型
    private String isSuprised; //待办是否被督办
    private String bizstate;//标志办件状态
    private String readstate;//阅读状态
    
    private String overdueType; // 临时存放该办件是否超期——N：未超期，I：即将超期，O：已超期
    private String itemTypeName;// 临时存放办件类型名称
    
    private String newwarn; //1超时0预警
    
    private String criticalLevel;//缓急程度

    private Integer remainingDays;//剩余天数
    
    private String gwNature;//公文性质 上级部门01 本部门02
    
    private String flowSupervise;//是否督办件
   
    
    public String getNewwarn() {
        return newwarn;
    }
    public void setNewwarn(String newwarn) {
        this.newwarn = newwarn;
    }
    public String getReadstate() {
        return readstate;
    }
    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }
    // Constructors
	/** default constructor */
	public VuserTaskListOA() {
	}
	/** minimal constructor */
	public VuserTaskListOA(
		Long taskId		
		) {
	
	
		this.taskId = taskId;		
			
	}

/** full constructor */
	public VuserTaskListOA(
	 Long taskId		
	,Long  nodeInstId,String  unitCode,String  userCode,String  roleType,String  roleCode,String  optId,Long  flowInstId,String  flowOptName,String  flowOptTag,String  authDesc,String  nodeName,String  nodeType,String  nodeOptType,String  optName,String  methodName,String  optUrl,String  optMethod,String  optDesc,String  optCode,String  optParam,String  inststate,Date  nodeCreateTime,Long  expireOptSign,String  expireOpt,String  grantor,Long  timeLimit,String  lastUpdateUser,Date  lastUpdateTime,String  flowPhase,String  nodeCode,String  djId,String  powerid,String  powername,String  flowCode,String  createuser,Date  createdate,String  caseNo,String  sendArchiveNo,String  acceptArchiveNo,String itemtype,String transaffairname) {
	
	
		this.taskId = taskId;		
	
		this.nodeInstId= nodeInstId;
		this.unitCode= unitCode;
		this.userCode= userCode;
		this.roleType= roleType;
		this.roleCode= roleCode;
		this.optId= optId;
		this.flowInstId= flowInstId;
		this.flowOptName= flowOptName;
		this.flowOptTag= flowOptTag;
		this.authDesc= authDesc;
		this.nodeName= nodeName;
		this.nodeType= nodeType;
		this.nodeOptType= nodeOptType;
		this.optName= optName;
		this.methodName= methodName;
		this.optUrl= optUrl;
		this.optMethod= optMethod;
		this.optDesc= optDesc;
		this.optCode= optCode;
		this.optParam= optParam;
		this.inststate= inststate;
		this.nodeCreateTime= nodeCreateTime;
		this.expireOptSign= expireOptSign;
		this.expireOpt= expireOpt;
		this.grantor= grantor;
		this.timeLimit= timeLimit;
		this.lastUpdateUser= lastUpdateUser;
		this.lastUpdateTime= lastUpdateTime;
		this.flowPhase= flowPhase;
		this.nodeCode= nodeCode;
		this.djId= djId;
		this.powerid= powerid;
		this.powername= powername;
		this.flowCode= flowCode;
		this.createuser= createuser;
		this.createdate= createdate;
		this.caseNo= caseNo;
		this.sendArchiveNo= sendArchiveNo;
		this.acceptArchiveNo= acceptArchiveNo;		
		this.itemtype=itemtype;
		this.transaffairname = transaffairname;
	}
	

  
	public VuserTaskListOA(Long taskId, Long nodeInstId, String unitCode,
        String userCode, String roleType, String roleCode, String optId,
        Long flowInstId, String flowOptName, String flowOptTag,
        String authDesc, String nodeName, String nodeType, String nodeOptType,
        String optName, String methodName, String optUrl, String optMethod,
        String optDesc, String optCode, String optParam, String inststate,
        Date nodeCreateTime, Long expireOptSign, String expireOpt,
        String grantor, Long timeLimit, String lastUpdateUser,
        Date lastUpdateTime, String flowPhase, String nodeCode, String djId,
        String powerid, String powername, String flowCode, String createuser,
        Date createdate, String caseNo, String sendArchiveNo,
        String acceptArchiveNo, String itemtype, String supdjid,
        String warnType, String bizstate, String transaffairname) {
    super();
    this.taskId = taskId;
    this.nodeInstId = nodeInstId;
    this.unitCode = unitCode;
    this.userCode = userCode;
    this.roleType = roleType;
    this.roleCode = roleCode;
    this.optId = optId;
    this.flowInstId = flowInstId;
    this.flowOptName = flowOptName;
    this.flowOptTag = flowOptTag;
    this.authDesc = authDesc;
    this.nodeName = nodeName;
    this.nodeType = nodeType;
    this.nodeOptType = nodeOptType;
    this.optName = optName;
    this.methodName = methodName;
    this.optUrl = optUrl;
    this.optMethod = optMethod;
    this.optDesc = optDesc;
    this.optCode = optCode;
    this.optParam = optParam;
    this.inststate = inststate;
    this.nodeCreateTime = nodeCreateTime;
    this.expireOptSign = expireOptSign;
    this.expireOpt = expireOpt;
    this.grantor = grantor;
    this.timeLimit = timeLimit;
    this.lastUpdateUser = lastUpdateUser;
    this.lastUpdateTime = lastUpdateTime;
    this.flowPhase = flowPhase;
    this.nodeCode = nodeCode;
    this.djId = djId;
    this.powerid = powerid;
    this.powername = powername;
    this.flowCode = flowCode;
    this.createuser = createuser;
    this.createdate = createdate;
    this.caseNo = caseNo;
    this.sendArchiveNo = sendArchiveNo;
    this.acceptArchiveNo = acceptArchiveNo;
    this.itemtype = itemtype;
    this.supdjid = supdjid;
    this.warnType = warnType;
    this.bizstate = bizstate;
    this.transaffairname = transaffairname;
}
    public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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
  
	public String getFlowOptName() {
		return this.flowOptName;
	}
	
	public void setFlowOptName(String flowOptName) {
		this.flowOptName = flowOptName;
	}
  
	public String getFlowOptTag() {
		return this.flowOptTag;
	}
	
	public void setFlowOptTag(String flowOptTag) {
		this.flowOptTag = flowOptTag;
	}
  
	public String getAuthDesc() {
		return this.authDesc;
	}
	
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
  
	public String getNodeName() {
		return this.nodeName;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
  
	public String getNodeType() {
		return this.nodeType;
	}
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
  
	public String getNodeOptType() {
		return this.nodeOptType;
	}
	
	public void setNodeOptType(String nodeOptType) {
		this.nodeOptType = nodeOptType;
	}
  
	public String getOptName() {
		return this.optName;
	}
	
	public void setOptName(String optName) {
		this.optName = optName;
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
  
	public String getOptDesc() {
		return this.optDesc;
	}
	
	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
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
  
	public Date getNodeCreateTime() {
		return this.nodeCreateTime;
	}
	
	public void setNodeCreateTime(Date nodeCreateTime) {
		this.nodeCreateTime = nodeCreateTime;
	}
  
	public Long getExpireOptSign() {
		return this.expireOptSign;
	}
	
	public void setExpireOptSign(Long expireOptSign) {
		this.expireOptSign = expireOptSign;
	}
  
	public String getExpireOpt() {
		return this.expireOpt;
	}
	
	public void setExpireOpt(String expireOpt) {
		this.expireOpt = expireOpt;
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
  
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}
	
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
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
  
	public String getNodeCode() {
		return this.nodeCode;
	}
	
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
  
	public String getDjId() {
		return this.djId;
	}
	
	public void setDjId(String djId) {
		this.djId = djId;
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
  
	public String getFlowCode() {
		return this.flowCode;
	}
	
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
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
    public String getWarnType() {
        return warnType;
    }
    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }
    public String getSupdjid() {
        return supdjid;
    }
    public void setSupdjid(String supdjid) {
        this.supdjid = supdjid;
    }
    public String getItemtype() {
        return itemtype;
    }
    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }


	public void copy(VuserTaskListOA other){
  
		this.setTaskId(other.getTaskId());
  
		this.nodeInstId= other.getNodeInstId();  
		this.unitCode= other.getUnitCode();  
		this.userCode= other.getUserCode();  
		this.roleType= other.getRoleType();  
		this.roleCode= other.getRoleCode();  
		this.optId= other.getOptId();  
		this.flowInstId= other.getFlowInstId();  
		this.flowOptName= other.getFlowOptName();  
		this.flowOptTag= other.getFlowOptTag();  
		this.authDesc= other.getAuthDesc();  
		this.nodeName= other.getNodeName();  
		this.nodeType= other.getNodeType();  
		this.nodeOptType= other.getNodeOptType();  
		this.optName= other.getOptName();  
		this.methodName= other.getMethodName();  
		this.optUrl= other.getOptUrl();  
		this.optMethod= other.getOptMethod();  
		this.optDesc= other.getOptDesc();  
		this.optCode= other.getOptCode();  
		this.optParam= other.getOptParam();  
		this.inststate= other.getInststate();  
		this.nodeCreateTime= other.getNodeCreateTime();  
		this.expireOptSign= other.getExpireOptSign();  
		this.expireOpt= other.getExpireOpt();  
		this.grantor= other.getGrantor();  
		this.timeLimit= other.getTimeLimit();  
		this.lastUpdateUser= other.getLastUpdateUser();  
		this.lastUpdateTime= other.getLastUpdateTime();  
		this.flowPhase= other.getFlowPhase();  
		this.nodeCode= other.getNodeCode();  
		this.djId= other.getDjId();  
		this.powerid= other.getPowerid();  
		this.powername= other.getPowername();  
		this.flowCode= other.getFlowCode();  
		this.createuser= other.getCreateuser();  
		this.createdate= other.getCreatedate();  
		this.caseNo= other.getCaseNo();  
		this.sendArchiveNo= other.getSendArchiveNo();  
		this.acceptArchiveNo= other.getAcceptArchiveNo();
		this.itemtype=other.getItemtype();
		this.bizstate=other.getBizstate();
		this.transaffairname = other.getTransaffairname();
	}
	
	public void copyNotNullProperty(VuserTaskListOA other){
  
	if( other.getTaskId() != null)
		this.setTaskId(other.getTaskId());
  
		if( other.getNodeInstId() != null)
			this.nodeInstId= other.getNodeInstId();  
		if( other.getUnitCode() != null)
			this.unitCode= other.getUnitCode();  
		if( other.getUserCode() != null)
			this.userCode= other.getUserCode();  
		if( other.getRoleType() != null)
			this.roleType= other.getRoleType();  
		if( other.getRoleCode() != null)
			this.roleCode= other.getRoleCode();  
		if( other.getOptId() != null)
			this.optId= other.getOptId();  
		if( other.getFlowInstId() != null)
			this.flowInstId= other.getFlowInstId();  
		if( other.getFlowOptName() != null)
			this.flowOptName= other.getFlowOptName();  
		if( other.getFlowOptTag() != null)
			this.flowOptTag= other.getFlowOptTag();  
		if( other.getAuthDesc() != null)
			this.authDesc= other.getAuthDesc();  
		if( other.getNodeName() != null)
			this.nodeName= other.getNodeName();  
		if( other.getNodeType() != null)
			this.nodeType= other.getNodeType();  
		if( other.getNodeOptType() != null)
			this.nodeOptType= other.getNodeOptType();  
		if( other.getOptName() != null)
			this.optName= other.getOptName();  
		if( other.getMethodName() != null)
			this.methodName= other.getMethodName();  
		if( other.getOptUrl() != null)
			this.optUrl= other.getOptUrl();  
		if( other.getOptMethod() != null)
			this.optMethod= other.getOptMethod();  
		if( other.getOptDesc() != null)
			this.optDesc= other.getOptDesc();  
		if( other.getOptCode() != null)
			this.optCode= other.getOptCode();  
		if( other.getOptParam() != null)
			this.optParam= other.getOptParam();  
		if( other.getInststate() != null)
			this.inststate= other.getInststate();  
		if( other.getNodeCreateTime() != null)
			this.nodeCreateTime= other.getNodeCreateTime();  
		if( other.getExpireOptSign() != null)
			this.expireOptSign= other.getExpireOptSign();  
		if( other.getExpireOpt() != null)
			this.expireOpt= other.getExpireOpt();  
		if( other.getGrantor() != null)
			this.grantor= other.getGrantor();  
		if( other.getTimeLimit() != null)
			this.timeLimit= other.getTimeLimit();  
		if( other.getLastUpdateUser() != null)
			this.lastUpdateUser= other.getLastUpdateUser();  
		if( other.getLastUpdateTime() != null)
			this.lastUpdateTime= other.getLastUpdateTime();  
		if( other.getFlowPhase() != null)
			this.flowPhase= other.getFlowPhase();  
		if( other.getNodeCode() != null)
			this.nodeCode= other.getNodeCode();  
		if( other.getDjId() != null)
			this.djId= other.getDjId();  
		if( other.getPowerid() != null)
			this.powerid= other.getPowerid();  
		if( other.getPowername() != null)
			this.powername= other.getPowername();  
		if( other.getFlowCode() != null)
			this.flowCode= other.getFlowCode();  
		if( other.getCreateuser() != null)
			this.createuser= other.getCreateuser();  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();  
		if( other.getCaseNo() != null)
			this.caseNo= other.getCaseNo();  
		if( other.getSendArchiveNo() != null)
			this.sendArchiveNo= other.getSendArchiveNo();  
		if( other.getAcceptArchiveNo() != null)
			this.acceptArchiveNo= other.getAcceptArchiveNo();
		if( other.getItemtype() != null)
            this.itemtype= other.getItemtype();
		if(other.getBizstate()!=null)
		    this.bizstate=other.getBizstate();
		if( other.getTransaffairname() != null)
		    this.transaffairname = other.getTransaffairname();
		if( other.getCriticalLevel() != null)
            this.criticalLevel =other.getCriticalLevel();
		if( other.getRemainingDays() != null)
		    this.remainingDays = other.getRemainingDays();
	}
	
	public void clearProperties(){
  
		this.nodeInstId= null;  
		this.unitCode= null;  
		this.userCode= null;  
		this.roleType= null;  
		this.roleCode= null;  
		this.optId= null;  
		this.flowInstId= null;  
		this.flowOptName= null;  
		this.flowOptTag= null;  
		this.authDesc= null;  
		this.nodeName= null;  
		this.nodeType= null;  
		this.nodeOptType= null;  
		this.optName= null;  
		this.methodName= null;  
		this.optUrl= null;  
		this.optMethod= null;  
		this.optDesc= null;  
		this.optCode= null;  
		this.optParam= null;  
		this.inststate= null;  
		this.nodeCreateTime= null;  
		this.expireOptSign= null;  
		this.expireOpt= null;  
		this.grantor= null;  
		this.timeLimit= null;  
		this.lastUpdateUser= null;  
		this.lastUpdateTime= null;  
		this.flowPhase= null;  
		this.nodeCode= null;  
		this.djId= null;  
		this.powerid= null;  
		this.powername= null;  
		this.flowCode= null;  
		this.createuser= null;  
		this.createdate= null;  
		this.caseNo= null;  
		this.sendArchiveNo= null;  
		this.acceptArchiveNo= null;
		this.itemtype=null;
		this.bizstate=null;
		this.transaffairname = null;
		this.criticalLevel = null;
		this.remainingDays = null;
	}
	   
    public String getNodeOptUrl() {
        if(optUrl == null){
            return null;
        }
 
        OptDesc optDesc= Struts2UrlParser.parseUrl(optUrl);
        optDesc.setMethod(optMethod);
        String url = optDesc.getOptUrl() +
                "?nodeInstId="+getNodeInstId()+"&flowInstId="+flowInstId+"&flowPhase="+ flowPhase+"&nodeCode="+getNodeCode();
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
    public String getIsSuprised() {
        return isSuprised;
    }
    public void setIsSuprised(String isSuprised) {
        this.isSuprised = isSuprised;
    }
    public String getBizstate() {
        return bizstate;
    }
    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }
    public String getTransaffairname() {
        return transaffairname;
    }
    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }
    public String getOverdueType() {
        return overdueType;
    }
    public void setOverdueType(String overdueType) {
        this.overdueType = overdueType;
    }
    public String getItemTypeName() {
        return itemTypeName;
    }
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
    public String getCriticalLevel() {
        return criticalLevel;
    }
    public void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }
    public Integer getRemainingDays() {
        return remainingDays;
    }
    public void setRemainingDays(Integer remainingDays) {
        this.remainingDays = remainingDays;
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
