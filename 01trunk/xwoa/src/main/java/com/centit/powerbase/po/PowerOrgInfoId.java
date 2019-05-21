package com.centit.powerbase.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class PowerOrgInfoId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String itemId;

	private String unitcode;

	// Constructors
	/** default constructor */
	public PowerOrgInfoId() {
	}
	/** full constructor */
	public PowerOrgInfoId(String itemId, String unitcode) {

		this.itemId = itemId;
		this.unitcode = unitcode;	
	}

  
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
  
	public String getUnitcode() {
		return this.unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PowerOrgInfoId))
			return false;
		
		PowerOrgInfoId castOther = (PowerOrgInfoId) other;
		boolean ret = true;
  
		ret = ret && ( this.getItemId() == castOther.getItemId() ||
					   (this.getItemId() != null && castOther.getItemId() != null
							   && this.getItemId().equals(castOther.getItemId())));
  
		ret = ret && ( this.getUnitcode() == castOther.getUnitcode() ||
					   (this.getUnitcode() != null && castOther.getUnitcode() != null
							   && this.getUnitcode().equals(castOther.getUnitcode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getItemId() == null ? 0 :this.getItemId().hashCode());
  
		result = 37 * result +
		 	(this.getUnitcode() == null ? 0 :this.getUnitcode().hashCode());
	
		return result;
	}
}
