package com.centit.bbs.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaBbsAttentionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String themeno;

	private String usercode;
	
	private String  laytype;

	// Constructors
	/** default constructor */
	public OaBbsAttentionId() {
	}
	/** full constructor */
	public OaBbsAttentionId(String themeno, String usercode,String laytype) {

		this.themeno = themeno;
		this.usercode = usercode;	
		this.laytype=laytype;
	}

  
	public String getThemeno() {
		return this.themeno;
	}

	public void setThemeno(String themeno) {
		this.themeno = themeno;
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
		if (!(other instanceof OaBbsAttentionId))
			return false;
		
		OaBbsAttentionId castOther = (OaBbsAttentionId) other;
		boolean ret = true;
  
		ret = ret && ( this.getThemeno() == castOther.getThemeno() ||
					   (this.getThemeno() != null && castOther.getThemeno() != null
							   && this.getThemeno().equals(castOther.getThemeno())));
  
		ret = ret && ( this.getUsercode() == castOther.getUsercode() ||
					   (this.getUsercode() != null && castOther.getUsercode() != null
							   && this.getUsercode().equals(castOther.getUsercode())));
		
		ret = ret && ( this.getLaytype()== castOther.getLaytype() ||
                (this.getLaytype() != null && castOther.getLaytype() != null
                        && this.getLaytype().equals(castOther.getLaytype())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getThemeno() == null ? 0 :this.getThemeno().hashCode());
  
		result = 37 * result +
		 	(this.getUsercode() == null ? 0 :this.getUsercode().hashCode());
		
		result = 37 * result +
	            (this.getLaytype() == null ? 0 :this.getLaytype().hashCode());
	
		return result;
	}
    public String getLaytype() {
        return laytype;
    }
    public void setLaytype(String laytype) {
        this.laytype = laytype;
    }
}
