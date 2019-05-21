package com.centit.oa.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaMeetMinutesId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djid;

	private String version;

	// Constructors
	/** default constructor */
	public OaMeetMinutesId() {
	}
	/** full constructor */
	public OaMeetMinutesId(String djid, String version) {

		this.djid = djid;
		this.version = version;	
	}

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OaMeetMinutesId))
			return false;
		
		OaMeetMinutesId castOther = (OaMeetMinutesId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjid() == castOther.getDjid() ||
					   (this.getDjid() != null && castOther.getDjid() != null
							   && this.getDjid().equals(castOther.getDjid())));
  
		ret = ret && ( this.getVersion() == castOther.getVersion() ||
					   (this.getVersion() != null && castOther.getVersion() != null
							   && this.getVersion().equals(castOther.getVersion())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjid() == null ? 0 :this.getDjid().hashCode());
  
		result = 37 * result +
		 	(this.getVersion() == null ? 0 :this.getVersion().hashCode());
	
		return result;
	}
}
