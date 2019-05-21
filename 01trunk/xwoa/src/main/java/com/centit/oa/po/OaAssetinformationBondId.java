package com.centit.oa.po;


/**
 * FAddressBook entity.
 * 
 * @author
 */ 

public class OaAssetinformationBondId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djid;

	private String no;

	// Constructors
	/** default constructor */
	public OaAssetinformationBondId() {
	}
	/** full constructor */
	public OaAssetinformationBondId(String djid, String no) {

		this.djid = djid;
		this.no = no;	
	}

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OaAssetinformationBondId))
			return false;
		
		OaAssetinformationBondId castOther = (OaAssetinformationBondId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjid() == castOther.getDjid() ||
					   (this.getDjid() != null && castOther.getDjid() != null
							   && this.getDjid().equals(castOther.getDjid())));
  
		ret = ret && ( this.getNo() == castOther.getNo() ||
					   (this.getNo() != null && castOther.getNo() != null
							   && this.getNo().equals(castOther.getNo())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjid() == null ? 0 :this.getDjid().hashCode());
  
		result = 37 * result +
		 	(this.getNo() == null ? 0 :this.getNo().hashCode());
	
		return result;
	}
}
