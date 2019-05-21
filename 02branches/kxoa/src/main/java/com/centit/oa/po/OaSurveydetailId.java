package com.centit.oa.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaSurveydetailId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String itemid;//序号

	private String creater;//投票者

	// Constructors
	/** default constructor */
	public OaSurveydetailId() {
	}
	/** full constructor */
	public OaSurveydetailId(String itemid, String creater) {

		this.itemid = itemid;
		this.creater = creater;	
	}

  
	public String getItemid() {
		return this.itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
  
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OaSurveydetailId))
			return false;
		
		OaSurveydetailId castOther = (OaSurveydetailId) other;
		boolean ret = true;
  
		ret = ret && ( this.getItemid() == castOther.getItemid() ||
					   (this.getItemid() != null && castOther.getItemid() != null
							   && this.getItemid().equals(castOther.getItemid())));
  
		ret = ret && ( this.getCreater() == castOther.getCreater() ||
					   (this.getCreater() != null && castOther.getCreater() != null
							   && this.getCreater().equals(castOther.getCreater())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getItemid() == null ? 0 :this.getItemid().hashCode());
  
		result = 37 * result +
		 	(this.getCreater() == null ? 0 :this.getCreater().hashCode());
	
		return result;
	}
}
