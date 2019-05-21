package com.centit.app.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class ForumTypeId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String type;

	private Long forumid;

	// Constructors
	/** default constructor */
	public ForumTypeId() {
	}
	/** full constructor */
	public ForumTypeId(String type, Long forumid) {

		this.type = type;
		this.forumid = forumid;	
	}

  
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
  
	public Long getForumid() {
		return this.forumid;
	}

	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ForumTypeId))
			return false;
		
		ForumTypeId castOther = (ForumTypeId) other;
		boolean ret = true;
  
		ret = ret && ( this.getType() == castOther.getType() ||
					   (this.getType() != null && castOther.getType() != null
							   && this.getType().equals(castOther.getType())));
  
		ret = ret && ( this.getForumid() == castOther.getForumid() ||
					   (this.getForumid() != null && castOther.getForumid() != null
							   && this.getForumid().equals(castOther.getForumid())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getType() == null ? 0 :this.getType().hashCode());
  
		result = 37 * result +
		 	(this.getForumid() == null ? 0 :this.getForumid().hashCode());
	
		return result;
	}
}
