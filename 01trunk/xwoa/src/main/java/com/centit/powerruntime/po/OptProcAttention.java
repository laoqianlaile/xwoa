package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptProcAttention implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OptProcAttentionId cid;


	private Date  attSetTime;
	private String  attSetUser;
	private String  attType;
	private Long nodeInstId;
	private String isAtt;//0，未提出（关注意见状态），1 已经提出
	
	/**
	 * 关注意见，其他页面对象元素
	 */
	private String attIdea;

	// Constructors
	/** default constructor */
	public OptProcAttention() {
	}
	/** minimal constructor */
	public OptProcAttention(OptProcAttentionId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public OptProcAttention(OptProcAttentionId id
			
	,Date  attsettime,String  attsetuser,Long nodeInstId,String isAtt) {
		this.cid = id; 
			
	
		this.attSetTime= attsettime;
		this.attSetUser= attsetuser;	
		this.nodeInstId= nodeInstId;
		this.isAtt = isAtt;
	}

	public OptProcAttentionId getCid() {
		return this.cid;
	}
	
	public void setCid(OptProcAttentionId id) {
		this.cid = id;
	}
  
	public String getDjId() {
		if(this.cid==null)
			this.cid = new OptProcAttentionId();
		return this.cid.getDjId();
	}
	
	public void setDjId(String djId) {
		if(this.cid==null)
			this.cid = new OptProcAttentionId();
		this.cid.setDjId(djId);
	}
  
	public String getUserCode() {
		if(this.cid==null)
			this.cid = new OptProcAttentionId();
		return this.cid.getUserCode();
	}
	
	public void setUserCode(String usercode) {
		if(this.cid==null)
			this.cid = new OptProcAttentionId();
		this.cid.setUserCode(usercode);
	}
	
	

	// Property accessors
  
	public Date getAttSetTime() {
		return this.attSetTime;
	}
	
	public void setAttSetTime(Date attsettime) {
		this.attSetTime = attsettime;
	}
  
	public String getAttSetUser() {
		return this.attSetUser;
	}
	
	public void setAttSetUser(String attsetuser) {
		this.attSetUser = attsetuser;
	}



	public void copy(OptProcAttention other){
  
		this.setDjId(other.getDjId());  
		this.setUserCode(other.getUserCode());
  
		this.attSetTime= other.getAttSetTime();  
		this.attSetUser= other.getAttSetUser();
		 this.nodeInstId = other.getNodeInstId();
		 this.isAtt = other.getIsAtt();

	}
	
	public void copyNotNullProperty(OptProcAttention other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());  
	if( other.getUserCode() != null)
		this.setUserCode(other.getUserCode());
  
		if( other.getAttSetTime() != null)
			this.attSetTime= other.getAttSetTime();  
		if( other.getAttSetUser() != null)
			this.attSetUser= other.getAttSetUser();
		if(other.getNodeInstId() != null)
		    this.nodeInstId = other.getNodeInstId();
		if(other.getIsAtt() != null)
		    this.isAtt = other.getIsAtt();
	}
	
	public void clearProperties(){
  
		this.attSetTime= null;  
		this.attSetUser= null;
		this.nodeInstId = null;
		this.isAtt = null;
	}
    public String getAttType() {
        return attType;
    }
    public void setAttType(String attType) {
        this.attType = attType;
    }
    public Long getNodeInstId() {
        return nodeInstId;
    }
    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }
    public String getAttIdea() {
        return attIdea;
    }
    public void setAttIdea(String attIdea) {
        this.attIdea = attIdea;
    }
    public String getIsAtt() {
        return isAtt;
    }
    public void setIsAtt(String isAtt) {
        this.isAtt = isAtt;
    }
}
