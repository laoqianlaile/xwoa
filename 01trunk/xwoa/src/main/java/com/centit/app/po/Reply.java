package com.centit.app.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class Reply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long replyid;

//	private Long  threadid;

    private Thread thread;

	private String  reply;
	private Date  replytime = new Date();
	private String  userid;
	private String  username;
	private Long  annexnum;
	private Set<ReplyAnnex> replyAnnexs = null;// new ArrayList<ReplyAnnex>();

	// Constructors
	/** default constructor */
	public Reply() {
	}
	/** minimal constructor */
	public Reply(
		Long replyid		
		) {
	
	
		this.replyid = replyid;		
			
	}

/** full constructor */
	public Reply(
	 Long replyid		
	,Long  threadid,String  reply,Date  replytime,String  userid,String  username,Long  annexnum) {
	
	
		this.replyid = replyid;		
	
//		this.threadid= threadid;
		this.reply= reply;
		this.replytime= replytime;
		this.userid= userid;
		this.username= username;
		this.annexnum= annexnum;		
	}
	

  
	public Long getReplyid() {
		return this.replyid;
	}

	public void setReplyid(Long replyid) {
		this.replyid = replyid;
	}
	// Property accessors
  
//	public Long getThreadid() {
//		return this.threadid;
//	}
//
//	public void setThreadid(Long threadid) {
//		this.threadid = threadid;
//	}
  
	public String getReply() {
		return this.reply;
	}
	
	public void setReply(String reply) {
		this.reply = reply;
	}
  
	public Date getReplytime() {
		return this.replytime;
	}
	
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}
  
	public String getUserid() {
		return this.userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
  
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
  
	public Long getAnnexnum() {
		return this.annexnum;
	}
	
	public void setAnnexnum(Long annexnum) {
		this.annexnum = annexnum;
	}

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Set<ReplyAnnex> getReplyAnnexs(){
		if(this.replyAnnexs==null)
			this.replyAnnexs = new HashSet<ReplyAnnex>();
		return this.replyAnnexs;
	}

	public void setReplyAnnexs(Set<ReplyAnnex> replyAnnexs) {
		this.replyAnnexs = replyAnnexs;
	}	

	public void addReplyAnnex(ReplyAnnex replyAnnex ){
		if (this.replyAnnexs==null)
			this.replyAnnexs = new HashSet<ReplyAnnex>();
		this.replyAnnexs.add(replyAnnex);
	}
	
	public void removeReplyAnnex(ReplyAnnex replyAnnex ){
		if (this.replyAnnexs==null)
			return;
		this.replyAnnexs.remove(replyAnnex);
	}
	
	public ReplyAnnex newReplyAnnex(){
		ReplyAnnex res = new ReplyAnnex();
  
		res.setReplyid(this.getReplyid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceReplyAnnexs(List<ReplyAnnex> replyAnnexs) {
		List<ReplyAnnex> newObjs = new ArrayList<ReplyAnnex>();
		for(ReplyAnnex p :replyAnnexs){
			if(p==null)
				continue;
			ReplyAnnex newdt = newReplyAnnex();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<ReplyAnnex> oldObjs = new HashSet<ReplyAnnex>();
		oldObjs.addAll(getReplyAnnexs());
		
		for(Iterator<ReplyAnnex> it=oldObjs.iterator(); it.hasNext();){
			ReplyAnnex odt = it.next();
			found = false;
			for(ReplyAnnex newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeReplyAnnex(odt);
		}
		oldObjs.clear();
		//insert or update
		for(ReplyAnnex newdt :newObjs){
			found = false;
			for(Iterator<ReplyAnnex> it=getReplyAnnexs().iterator();
			 it.hasNext();){
				ReplyAnnex odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addReplyAnnex(newdt);
		} 	
	}	


	public void copy(Reply other){
  
		this.setReplyid(other.getReplyid());
  
//		this.threadid= other.getThreadid();
		this.reply= other.getReply();  
		this.replytime= other.getReplytime();  
		this.userid= other.getUserid();  
		this.username= other.getUsername();  
		this.annexnum= other.getAnnexnum();
	
		this.replyAnnexs = other.getReplyAnnexs();
	}
	
	public void copyNotNullProperty(Reply other){
  
	if( other.getReplyid() != null)
		this.setReplyid(other.getReplyid());
  
//		if( other.getThreadid() != null)
//			this.threadid= other.getThreadid();
		if( other.getReply() != null)
			this.reply= other.getReply();  
		if( other.getReplytime() != null)
			this.replytime= other.getReplytime();  
		if( other.getUserid() != null)
			this.userid= other.getUserid();  
		if( other.getUsername() != null)
			this.username= other.getUsername();  
		if( other.getAnnexnum() != null)
			this.annexnum= other.getAnnexnum();
	
		this.replyAnnexs = other.getReplyAnnexs();
	}
	
	public void clearProperties(){
  
//		this.threadid= null;
		this.reply= null;  
		this.replytime= null;  
		this.userid= null;  
		this.username= null;  
		this.annexnum= null;
	
		this.replyAnnexs = new HashSet<ReplyAnnex>();
	}
}
