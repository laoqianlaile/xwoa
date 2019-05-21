package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VProcAttention implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private VProcAttentionId cid;


	private Date  attSetTime;
	private String  attSetUser;
	private String  attType;
	private Long flowInstId;
	private String transaffairname;
	private String isAtt;

	// Constructors
	/** default constructor */
	public VProcAttention() {
	}
	/** minimal constructor */
	public VProcAttention(VProcAttentionId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public VProcAttention(VProcAttentionId id
			
	,Date  attsettime,String  attsetuser) {
		this.cid = id; 
			
	
		this.attSetTime= attsettime;
		this.attSetUser= attsetuser;		
	}

	public VProcAttentionId getCid() {
		return this.cid;
	}
	
	public void setCid(VProcAttentionId id) {
		this.cid = id;
	}
  
	public String getDjId() {
		if(this.cid==null)
			this.cid = new VProcAttentionId();
		return this.cid.getDjId();
	}
	
	public void setDjId(String djId) {
		if(this.cid==null)
			this.cid = new VProcAttentionId();
		this.cid.setDjId(djId);
	}
  
	public String getUserCode() {
		if(this.cid==null)
			this.cid = new VProcAttentionId();
		return this.cid.getUserCode();
	}
	
	public void setUserCode(String usercode) {
		if(this.cid==null)
			this.cid = new VProcAttentionId();
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



	public void copy(VProcAttention other){
  
		this.setDjId(other.getDjId());  
		this.setUserCode(other.getUserCode());
  
		this.attSetTime= other.getAttSetTime();  
		this.attSetUser= other.getAttSetUser();

	}
	
	public void copyNotNullProperty(VProcAttention other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());  
	if( other.getUserCode() != null)
		this.setUserCode(other.getUserCode());
  
		if( other.getAttSetTime() != null)
			this.attSetTime= other.getAttSetTime();  
		if( other.getAttSetUser() != null)
			this.attSetUser= other.getAttSetUser();

	}
	
	public void clearProperties(){
  
		this.attSetTime= null;  
		this.attSetUser= null;

	}
    public String getAttType() {
        return attType;
    }
    public void setAttType(String attType) {
        this.attType = attType;
    }
    public String getTransaffairname() {
        return transaffairname;
    }
    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }
    public Long getFlowInstId() {
        return flowInstId;
    }
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }
    public String getIsAtt() {
        return isAtt;
    }
    public void setIsAtt(String isAtt) {
        this.isAtt = isAtt;
    }
}
