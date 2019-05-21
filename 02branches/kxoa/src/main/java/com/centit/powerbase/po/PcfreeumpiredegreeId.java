package com.centit.powerbase.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 
 
public class PcfreeumpiredegreeId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

//	private String punishclassid;

	private Long degreeno;

	// Constructors
	/** default constructor */
	public PcfreeumpiredegreeId() {
	}
	/** full constructor */
	public PcfreeumpiredegreeId( Long degreeno) {

		this.degreeno = degreeno;	
	}

  
	public Long getDegreeno() {
		return this.degreeno;
	}

	public void setDegreeno(Long degreeno) {
		this.degreeno = degreeno;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PcfreeumpiredegreeId))
			return false;
		
		PcfreeumpiredegreeId castOther = (PcfreeumpiredegreeId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDegreeno() == castOther.getDegreeno() ||
					   (this.getDegreeno() != null && castOther.getDegreeno() != null
							   && this.getDegreeno().equals(castOther.getDegreeno())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
  
		result = 37 * result +
		 	(this.getDegreeno() == null ? 0 :this.getDegreeno().hashCode());
	
		return result;
	}
}
