package com.centit.powerbase.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class VsuppowerinusingId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String itemId;

	private Long version;

	// Constructors
	/** default constructor */
	public VsuppowerinusingId() {
	}
	/** full constructor */
	public VsuppowerinusingId(String itemId, Long version) {

		this.itemId = itemId;
		this.version = version;	
	}

  
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
  
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VsuppowerinusingId))
			return false;
		
		VsuppowerinusingId castOther = (VsuppowerinusingId) other;
		boolean ret = true;
  
		ret = ret && ( this.getItemId() == castOther.getItemId() ||
					   (this.getItemId() != null && castOther.getItemId() != null
							   && this.getItemId().equals(castOther.getItemId())));
  
		ret = ret && ( this.getVersion() == castOther.getVersion() ||
					   (this.getVersion() != null && castOther.getVersion() != null
							   && this.getVersion().equals(castOther.getVersion())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getItemId() == null ? 0 :this.getItemId().hashCode());
  
		result = 37 * result +
		 	(this.getVersion() == null ? 0 :this.getVersion().hashCode());
	
		return result;
	}
}
