package com.centit.sys.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class UserbizoptLogId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String id;

	private String bizname;

	private String biztype;

	private String djId;

	private Date createtime;

	private String createuser;

	private String createrip;

	// Constructors
	/** default constructor */
	public UserbizoptLogId() {
	}
	/** full constructor */
	public UserbizoptLogId(String id, String bizname, String biztype, String djId, Date createtime, String createuser, String createrip) {

		this.id = id;
		this.bizname = bizname;
		this.biztype = biztype;
		this.djId = djId;
		this.createtime = createtime;
		this.createuser = createuser;
		this.createrip = createrip;	
	}
	public UserbizoptLogId(String bizname,  String djId ) {
        this.bizname = bizname;
        this.djId = djId;
    }

  
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
  
	public String getBizname() {
		return this.bizname;
	}

	public void setBizname(String bizname) {
		this.bizname = bizname;
	}
  
	public String getBiztype() {
		return this.biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
  
	public String getCreaterip() {
		return this.createrip;
	}

	public void setCreaterip(String createrip) {
		this.createrip = createrip;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserbizoptLogId))
			return false;
		
		UserbizoptLogId castOther = (UserbizoptLogId) other;
		boolean ret = true;
  
		ret = ret && ( this.getId() == castOther.getId() ||
					   (this.getId() != null && castOther.getId() != null
							   && this.getId().equals(castOther.getId())));
  
		ret = ret && ( this.getBizname() == castOther.getBizname() ||
					   (this.getBizname() != null && castOther.getBizname() != null
							   && this.getBizname().equals(castOther.getBizname())));
  
		ret = ret && ( this.getBiztype() == castOther.getBiztype() ||
					   (this.getBiztype() != null && castOther.getBiztype() != null
							   && this.getBiztype().equals(castOther.getBiztype())));
  
		ret = ret && ( this.getDjId() == castOther.getDjId() ||
					   (this.getDjId() != null && castOther.getDjId() != null
							   && this.getDjId().equals(castOther.getDjId())));
  
		ret = ret && ( this.getCreatetime() == castOther.getCreatetime() ||
					   (this.getCreatetime() != null && castOther.getCreatetime() != null
							   && this.getCreatetime().equals(castOther.getCreatetime())));
  
		ret = ret && ( this.getCreateuser() == castOther.getCreateuser() ||
					   (this.getCreateuser() != null && castOther.getCreateuser() != null
							   && this.getCreateuser().equals(castOther.getCreateuser())));
  
		ret = ret && ( this.getCreaterip() == castOther.getCreaterip() ||
					   (this.getCreaterip() != null && castOther.getCreaterip() != null
							   && this.getCreaterip().equals(castOther.getCreaterip())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getId() == null ? 0 :this.getId().hashCode());
  
		result = 37 * result +
		 	(this.getBizname() == null ? 0 :this.getBizname().hashCode());
  
		result = 37 * result +
		 	(this.getBiztype() == null ? 0 :this.getBiztype().hashCode());
  
		result = 37 * result +
		 	(this.getDjId() == null ? 0 :this.getDjId().hashCode());
  
		result = 37 * result +
		 	(this.getCreatetime() == null ? 0 :this.getCreatetime().hashCode());
  
		result = 37 * result +
		 	(this.getCreateuser() == null ? 0 :this.getCreateuser().hashCode());
  
		result = 37 * result +
		 	(this.getCreaterip() == null ? 0 :this.getCreaterip().hashCode());
	
		return result;
	}
}
