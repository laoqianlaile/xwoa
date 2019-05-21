package com.centit.app.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class ForumStaffId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private Long forumid;

	private String usercode;

	// Constructors
	/** default constructor */
	public ForumStaffId() {
	}
	/** full constructor */
	public ForumStaffId(Long forumid, String usercode) {

		this.forumid = forumid;
		this.usercode = usercode;	
	}

  
	public Long getForumid() {
		return this.forumid;
	}

	public void setForumid(Long forumid) {
		this.forumid = forumid;
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
		if (!(other instanceof ForumStaffId))
			return false;
		
		ForumStaffId castOther = (ForumStaffId) other;
		boolean ret = true;
  
		ret = ret && ( this.getForumid() == castOther.getForumid() ||
					   (this.getForumid() != null && castOther.getForumid() != null
							   && this.getForumid().equals(castOther.getForumid())));
  
		ret = ret && ( this.getUsercode() == castOther.getUsercode() ||
					   (this.getUsercode() != null && castOther.getUsercode() != null
							   && this.getUsercode().equals(castOther.getUsercode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getForumid() == null ? 0 :this.getForumid().hashCode());
  
		result = 37 * result +
		 	(this.getUsercode() == null ? 0 :this.getUsercode().hashCode());
	
		return result;
	}
}
