package com.centit.powerbase.po;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class PcpunishStandardId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

//	private String punishclassid;

	private String punishtypeid;
	
	 private String itemId;
	 private String version ; 

	public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    // Constructors
	/** default constructor */
	public PcpunishStandardId() {
	}
	/** full constructor */
	public PcpunishStandardId( String punishtypeid,String itemId, String version) {

		
		this.punishtypeid = punishtypeid;	
		this.itemId = itemId;
		this.version = version;
	}

  
	
  
	public String getPunishtypeid() {
		return this.punishtypeid;
	}

	public void setPunishtypeid(String punishtypeid) {
		this.punishtypeid = punishtypeid;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PcpunishStandardId))
			return false;
		
		PcpunishStandardId castOther = (PcpunishStandardId) other;
		boolean ret = true;
  
	
  
		ret = ret && ( this.getPunishtypeid() == castOther.getPunishtypeid() ||
					   (this.getPunishtypeid() != null && castOther.getPunishtypeid() != null
							   && this.getPunishtypeid().equals(castOther.getPunishtypeid())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
	
		result = 37 * result +
		 	(this.getPunishtypeid() == null ? 0 :this.getPunishtypeid().hashCode());
	
		return result;
	}
}
