package com.centit.powerruntime.po;


/**
 * FAddressBook entity.
 * 
 * @author hx
 */ 

public class OptNewlyIdeaId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djId;

	private Long nodeid;

	// Constructors
	/** default constructor */
	public OptNewlyIdeaId() {
	}
	/** full constructor */
	public OptNewlyIdeaId(String djId, Long nodeid) {

		this.djId = djId;
		this.nodeid = nodeid;	
	}

  
	public String getDjId() {
		return this.djId;
	}

	
  
	public void setDjId(String djId) {
        this.djId = djId;
    }
    public Long getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OptNewlyIdeaId))
			return false;
		
		OptNewlyIdeaId castOther = (OptNewlyIdeaId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjId() == castOther.getDjId() ||
					   (this.getDjId() != null && castOther.getDjId() != null
							   && this.getDjId().equals(castOther.getDjId())));
  
		ret = ret && ( this.getNodeid() == castOther.getNodeid() ||
					   (this.getNodeid() != null && castOther.getNodeid() != null
							   && this.getNodeid().equals(castOther.getNodeid())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjId() == null ? 0 :this.getDjId().hashCode());
  
		result = 37 * result +
		 	(this.getNodeid() == null ? 0 :this.getNodeid().hashCode());
	
		return result;
	}
}
