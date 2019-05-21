package com.centit.app.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class ReplyAnnexId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private Long replyid;

	private String filecode;

	// Constructors
	/** default constructor */
	public ReplyAnnexId() {
	}
	/** full constructor */
	public ReplyAnnexId(Long replyid, String filecode) {

		this.replyid = replyid;
		this.filecode = filecode;	
	}

  
	public Long getReplyid() {
		return this.replyid;
	}

	public void setReplyid(Long replyid) {
		this.replyid = replyid;
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
		if (!(other instanceof ReplyAnnexId))
			return false;
		
		ReplyAnnexId castOther = (ReplyAnnexId) other;
		boolean ret = true;
  
		ret = ret && ( this.getReplyid() == castOther.getReplyid() ||
					   (this.getReplyid() != null && castOther.getReplyid() != null
							   && this.getReplyid().equals(castOther.getReplyid())));
  
		ret = ret && ( this.getFilecode() == castOther.getFilecode() ||
					   (this.getFilecode() != null && castOther.getFilecode() != null
							   && this.getFilecode().equals(castOther.getFilecode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getReplyid() == null ? 0 :this.getReplyid().hashCode());
  
		result = 37 * result +
		 	(this.getFilecode() == null ? 0 :this.getFilecode().hashCode());
	
		return result;
	}
}
