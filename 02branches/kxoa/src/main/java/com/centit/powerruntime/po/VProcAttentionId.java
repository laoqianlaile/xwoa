package com.centit.powerruntime.po;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class VProcAttentionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djId;

	private String userCode;

	// Constructors
	/** default constructor */
	public VProcAttentionId() {
	}
	/** full constructor */
	public VProcAttentionId(String djId, String usercode) {

		this.djId = djId;
		this.userCode = usercode;	
	}

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String usercode) {
		this.userCode = usercode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VProcAttentionId))
			return false;
		
		VProcAttentionId castOther = (VProcAttentionId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjId() == castOther.getDjId() ||
					   (this.getDjId() != null && castOther.getDjId() != null
							   && this.getDjId().equals(castOther.getDjId())));
  
		ret = ret && ( this.getUserCode() == castOther.getUserCode() ||
					   (this.getUserCode() != null && castOther.getUserCode() != null
							   && this.getUserCode().equals(castOther.getUserCode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjId() == null ? 0 :this.getDjId().hashCode());
  
		result = 37 * result +
		 	(this.getUserCode() == null ? 0 :this.getUserCode().hashCode());
	
		return result;
	}
}
