package com.centit.app.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class ThreadAnnexId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private Long threadid;

	private String filecode;

	// Constructors
	/** default constructor */
	public ThreadAnnexId() {
	}
	/** full constructor */
	public ThreadAnnexId(Long threadid, String filecode) {

		this.threadid = threadid;
		this.filecode = filecode;	
	}

  
	public Long getThreadid() {
		return this.threadid;
	}

	public void setThreadid(Long threadid) {
		this.threadid = threadid;
	}
  
	public String getFilecode() {
		return this.filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ThreadAnnexId))
			return false;
		
		ThreadAnnexId castOther = (ThreadAnnexId) other;
		boolean ret = true;
  
		ret = ret && ( this.getThreadid() == castOther.getThreadid() ||
					   (this.getThreadid() != null && castOther.getThreadid() != null
							   && this.getThreadid().equals(castOther.getThreadid())));
  
		ret = ret && ( this.getFilecode() == castOther.getFilecode() ||
					   (this.getFilecode() != null && castOther.getFilecode() != null
							   && this.getFilecode().equals(castOther.getFilecode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getThreadid() == null ? 0 :this.getThreadid().hashCode());
  
		result = 37 * result +
		 	(this.getFilecode() == null ? 0 :this.getFilecode().hashCode());
	
		return result;
	}
}
