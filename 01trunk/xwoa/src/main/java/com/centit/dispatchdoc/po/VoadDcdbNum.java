package com.centit.dispatchdoc.po;

import java.util.Date;


/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VoadDcdbNum implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



    private Long  flowInstId;


	private String  userCode;

	private String  flowOptName;
	private String  nodeName;

	private Long  timeLimit;

	private String  nodeCode;
	
	private Date createtime;
	
	private Date lastupdatetime;
	public Long getFlowInstId() {
        return flowInstId;
    }
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getFlowOptName() {
        return flowOptName;
    }
    public void setFlowOptName(String flowOptName) {
        this.flowOptName = flowOptName;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public Long getTimeLimit() {
        return timeLimit;
    }
    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }
    public String getNodeCode() {
        return nodeCode;
    }
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
    private String  djId;
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getLastupdatetime() {
        return lastupdatetime;
    }
    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
    


}
