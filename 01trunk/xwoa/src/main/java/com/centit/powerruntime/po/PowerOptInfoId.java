package com.centit.powerruntime.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class PowerOptInfoId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String itemId;

	private String wfcode;

	// Constructors
	/** default constructor */
	public PowerOptInfoId() {
	}
	/** full constructor */
	public PowerOptInfoId(String itemId, String wfcode) {

		this.itemId = itemId;
		this.wfcode = wfcode;	
	}

  
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
  
	public String getWfcode() {
		return this.wfcode;
	}

	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PowerOptInfoId))
			return false;
		
		PowerOptInfoId castOther = (PowerOptInfoId) other;
		boolean ret = true;
  
		ret = ret && ( this.getItemId() == castOther.getItemId() ||
					   (this.getItemId() != null && castOther.getItemId() != null
							   && this.getItemId().equals(castOther.getItemId())));
  
		ret = ret && ( this.getWfcode() == castOther.getWfcode() ||
					   (this.getWfcode() != null && castOther.getWfcode() != null
							   && this.getWfcode().equals(castOther.getWfcode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getItemId() == null ? 0 :this.getItemId().hashCode());
  
		result = 37 * result +
		 	(this.getWfcode() == null ? 0 :this.getWfcode().hashCode());
	
		return result;
	}
}
