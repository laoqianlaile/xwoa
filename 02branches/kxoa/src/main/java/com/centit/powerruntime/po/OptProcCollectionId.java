package com.centit.powerruntime.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OptProcCollectionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djId;

	private String usercode;

	// Constructors
	/** default constructor */
	public OptProcCollectionId() {
	}
	/** full constructor */
	public OptProcCollectionId(String djId, String usercode) {

		this.djId = djId;
		this.usercode = usercode;	
	}

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OptProcCollectionId))
			return false;
		
		OptProcCollectionId castOther = (OptProcCollectionId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjId() == castOther.getDjId() ||
					   (this.getDjId() != null && castOther.getDjId() != null
							   && this.getDjId().equals(castOther.getDjId())));
  
		ret = ret && ( this.getUsercode() == castOther.getUsercode() ||
					   (this.getUsercode() != null && castOther.getUsercode() != null
							   && this.getUsercode().equals(castOther.getUsercode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjId() == null ? 0 :this.getDjId().hashCode());
  
		result = 37 * result +
		 	(this.getUsercode() == null ? 0 :this.getUsercode().hashCode());
	
		return result;
	}
}
