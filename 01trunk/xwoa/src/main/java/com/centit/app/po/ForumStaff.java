package com.centit.app.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class ForumStaff implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private ForumStaffId cid;


	private String  userrole;
	private Date  jointime;

	// Constructors
	/** default constructor */
	public ForumStaff() {
	}
	/** minimal constructor */
	public ForumStaff(ForumStaffId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public ForumStaff(ForumStaffId id
			
	,String  userrole,Date  jointime) {
		this.cid = id; 
			
	
		this.userrole= userrole;
		this.jointime= jointime;		
	}

	public ForumStaffId getCid() {
		return this.cid;
	}
	
	public void setCid(ForumStaffId id) {
		this.cid = id;
	}
  
	public Long getForumid() {
		if(this.cid==null)
			this.cid = new ForumStaffId();
		return this.cid.getForumid();
	}
	
	public void setForumid(Long forumid) {
		if(this.cid==null)
			this.cid = new ForumStaffId();
		this.cid.setForumid(forumid);
	}
  
	public String getUsercode() {
		if(this.cid==null)
			this.cid = new ForumStaffId();
		return this.cid.getUsercode();
	}
	
	public void setUsercode(String usercode) {
		if(this.cid==null)
			this.cid = new ForumStaffId();
		this.cid.setUsercode(usercode);
	}
	
	

	// Property accessors
  
	public String getUserrole() {
		return this.userrole;
	}
	
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
  
	public Date getJointime() {
		return this.jointime;
	}
	
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}



	public void copy(ForumStaff other){
  
		this.setForumid(other.getForumid());  
		this.setUsercode(other.getUsercode());
  
		this.userrole= other.getUserrole();  
		this.jointime= other.getJointime();

	}
	
	public void copyNotNullProperty(ForumStaff other){
  
	if( other.getForumid() != null)
		this.setForumid(other.getForumid());  
	if( other.getUsercode() != null)
		this.setUsercode(other.getUsercode());
  
		if( other.getUserrole() != null)
			this.userrole= other.getUserrole();  
		if( other.getJointime() != null)
			this.jointime= other.getJointime();

	}
	
	public void clearProperties(){
  
		this.userrole= null;  
		this.jointime= null;

	}

    @Override
    public int hashCode() {
        return cid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ForumStaff)) {
            return false;
        }
        return cid.equals(((ForumStaff)obj).getCid());
    }
}
