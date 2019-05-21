package com.centit.app.po;


/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class ForumType implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private ForumTypeId cid;

	private Forum forum;

	// Constructors
	/** default constructor */
	public ForumType() {
	}
	/** minimal constructor */
	public ForumType(ForumTypeId id 
				
		) {
		this.cid = id; 
			
			
	}


	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public ForumTypeId getCid() {
		return this.cid;
	}
	
	public void setCid(ForumTypeId id) {
		this.cid = id;
	}
  
	public String getType() {
		if(this.cid==null)
			this.cid = new ForumTypeId();
		return this.cid.getType();
	}
	
	public void setType(String type) {
		if(this.cid==null)
			this.cid = new ForumTypeId();
		this.cid.setType(type);
	}
  
	public Long getForumid() {
		if(this.cid==null)
			this.cid = new ForumTypeId();
		return this.cid.getForumid();
	}
	
	public void setForumid(Long forumid) {
		if(this.cid==null)
			this.cid = new ForumTypeId();
		this.cid.setForumid(forumid);
	}
	
	

	// Property accessors



	public void copy(ForumType other){
  
		this.setType(other.getType());  
		this.setForumid(other.getForumid());


	}
	
	public void copyNotNullProperty(ForumType other){
  
	if( other.getType() != null)
		this.setType(other.getType());  
	if( other.getForumid() != null)
		this.setForumid(other.getForumid());


	}
	
	public void clearProperties(){


	}
}
