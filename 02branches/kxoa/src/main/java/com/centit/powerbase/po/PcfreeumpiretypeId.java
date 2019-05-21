package com.centit.powerbase.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class PcfreeumpiretypeId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

//	private String punishclassid;

	private Long degreeno;

	private String punishtypeid;

	// Constructors
	/** default constructor */
	public PcfreeumpiretypeId() {
	}
	/** full constructor */
	public PcfreeumpiretypeId(Long degreeno, String punishtypeid) {

	
		this.degreeno = degreeno;
		this.punishtypeid = punishtypeid;	
	}

  
	
	public Long getDegreeno() {
		return this.degreeno;
	}

	public void setDegreeno(Long degreeno) {
		this.degreeno = degreeno;
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
		if (!(other instanceof PcfreeumpiretypeId))
			return false;
		
		PcfreeumpiretypeId castOther = (PcfreeumpiretypeId) other;
		boolean ret = true;
  
		
  
		ret = ret && ( this.getDegreeno() == castOther.getDegreeno() ||
					   (this.getDegreeno() != null && castOther.getDegreeno() != null
							   && this.getDegreeno().equals(castOther.getDegreeno())));
  
		ret = ret && ( this.getPunishtypeid() == castOther.getPunishtypeid() ||
					   (this.getPunishtypeid() != null && castOther.getPunishtypeid() != null
							   && this.getPunishtypeid().equals(castOther.getPunishtypeid())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		
  
		result = 37 * result +
		 	(this.getDegreeno() == null ? 0 :this.getDegreeno().hashCode());
  
		result = 37 * result +
		 	(this.getPunishtypeid() == null ? 0 :this.getPunishtypeid().hashCode());
	
		return result;
	}
}
