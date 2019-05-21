package com.centit.powerruntime.po;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class SuppowerstuffgroupId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String groupId;

	private String itemId;

	// Constructors
	/** default constructor */
	public SuppowerstuffgroupId() {
	}
	/** full constructor */
	public SuppowerstuffgroupId(String groupId, String itemId) {

		this.groupId = groupId;
		this.itemId = itemId;	
	}

  
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
  
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SuppowerstuffgroupId))
			return false;
		
		SuppowerstuffgroupId castOther = (SuppowerstuffgroupId) other;
		boolean ret = true;
  
		ret = ret && ( this.getGroupId() == castOther.getGroupId() ||
					   (this.getGroupId() != null && castOther.getGroupId() != null
							   && this.getGroupId().equals(castOther.getGroupId())));
  
		ret = ret && ( this.getItemId() == castOther.getItemId() ||
					   (this.getItemId() != null && castOther.getItemId() != null
							   && this.getItemId().equals(castOther.getItemId())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getGroupId() == null ? 0 :this.getGroupId().hashCode());
  
		result = 37 * result +
		 	(this.getItemId() == null ? 0 :this.getItemId().hashCode());
	
		return result;
	}
}
