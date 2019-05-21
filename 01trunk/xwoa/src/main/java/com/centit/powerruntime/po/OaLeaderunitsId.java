package com.centit.powerruntime.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaLeaderunitsId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String leadercode;

	private String unitcode;

	// Constructors
	/** default constructor */
	public OaLeaderunitsId() {
	}
	/** full constructor */
	public OaLeaderunitsId(String leadercode, String unitcode) {

		this.leadercode = leadercode;
		this.unitcode = unitcode;	
	}

  
	public String getLeadercode() {
		return this.leadercode;
	}

	public void setLeadercode(String leadercode) {
		this.leadercode = leadercode;
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
		if (!(other instanceof OaLeaderunitsId))
			return false;
		
		OaLeaderunitsId castOther = (OaLeaderunitsId) other;
		boolean ret = true;
  
		ret = ret && ( this.getLeadercode() == castOther.getLeadercode() ||
					   (this.getLeadercode() != null && castOther.getLeadercode() != null
							   && this.getLeadercode().equals(castOther.getLeadercode())));
  
		ret = ret && ( this.getUnitcode() == castOther.getUnitcode() ||
					   (this.getUnitcode() != null && castOther.getUnitcode() != null
							   && this.getUnitcode().equals(castOther.getUnitcode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getLeadercode() == null ? 0 :this.getLeadercode().hashCode());
  
		result = 37 * result +
		 	(this.getUnitcode() == null ? 0 :this.getUnitcode().hashCode());
	
		return result;
	}
}
