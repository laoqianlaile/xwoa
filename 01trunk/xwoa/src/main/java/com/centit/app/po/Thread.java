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

public class Thread implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long threadid;

//	private Long  forumid;
    private Forum forum;
	private String  titol;
	private String  content;
	private String  wirterid;
	private String  wirter;
	private Date  posttime;
	private Long  viewnum;
	private Long  replnum;
	private Long  annexnum;
	
	//新闻版块特定标记
	private String threadType;
	private String threadTag;
	private String threadLock;
	private String threadReply;
	
	private Set<Reply> replys = null;// new ArrayList<Reply>();
	private Set<ThreadAnnex> threadAnnexs = null;// new ArrayList<ThreadAnnex>();
	
	
	
	private String summary;

	// Constructors
	/** default constructor */
	public Thread() {
	}
	/** minimal constructor */
	public Thread(
		Long threadid		
		) {
	
	
		this.threadid = threadid;		
			
	}

/** full constructor */
	public Thread(
	 Long threadid		
	,Long  forumid,String  titol,String  content,String  wirterid,String  wirter,Date  posttime,Long  viewnum,Long  replnum,Long  annexnum) {
	
	
		this.threadid = threadid;		
	
//		this.forumid= forumid;
		this.titol= titol;
		this.content= content;
		this.wirterid= wirterid;
		this.wirter= wirter;
		this.posttime= posttime;
		this.viewnum= viewnum;
		this.replnum= replnum;
		this.annexnum= annexnum;		
	}
	

  
	public Long getThreadid() {
		return this.threadid;
	}

	public void setThreadid(Long threadid) {
		this.threadid = threadid;
	}
	// Property accessors
  
//	public Long getForumid() {
//		return this.forumid;
//	}
//
//	public void setForumid(Long forumid) {
//		this.forumid = forumid;
//	}
  
	public String getTitol() {
		return this.titol;
	}
	
	public String getThreadType() {
		return threadType;
	}
	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}
	public String getThreadTag() {
		return threadTag;
	}
	public void setThreadTag(String threadTag) {
		this.threadTag = threadTag;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
  
	public String getThreadLock() {
		return threadLock;
	}
	public void setThreadLock(String threadLock) {
		this.threadLock = threadLock;
	}
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
  
	public String getThreadReply() {
		return threadReply;
	}
	public void setThreadReply(String threadReply) {
		this.threadReply = threadReply;
	}
	public String getWirterid() {
		return this.wirterid;
	}
	
	public void setWirterid(String wirterid) {
		this.wirterid = wirterid;
	}
  
	public String getWirter() {
		return this.wirter;
	}
	
	public void setWirter(String wirter) {
		this.wirter = wirter;
	}
  
	public Date getPosttime() {
		return this.posttime;
	}
	
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
  
	public Long getViewnum() {
		return this.viewnum;
	}
	
	public void setViewnum(Long viewnum) {
		this.viewnum = viewnum;
	}
  
	public Long getReplnum() {
		return this.replnum;
	}
	
	public void setReplnum(Long replnum) {
		this.replnum = replnum;
	}
  
	public Long getAnnexnum() {
		return this.annexnum;
	}
	
	public void setAnnexnum(Long annexnum) {
		this.annexnum = annexnum;
	}


    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Set<Reply> getReplys(){
		if(this.replys==null)
			this.replys = new HashSet<Reply>();
		return this.replys;
	}

	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}	

	public void addReply(Reply reply ){
		if (this.replys==null)
			this.replys = new HashSet<Reply>();
		this.replys.add(reply);
	}
	
	public void removeReply(Reply reply ){
		if (this.replys==null)
			return;
		this.replys.remove(reply);
	}
	
	public Reply newReply(){
		Reply res = new Reply();
  
		res.setThread(this);

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceReplys(List<Reply> replys) {
		List<Reply> newObjs = new ArrayList<Reply>();
		for(Reply p :replys){
			if(p==null)
				continue;
			Reply newdt = newReply();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<Reply> oldObjs = new HashSet<Reply>();
		oldObjs.addAll(getReplys());
		
		for(Iterator<Reply> it=oldObjs.iterator(); it.hasNext();){
			Reply odt = it.next();
			found = false;
			for(Reply newdt :newObjs){
				if(odt.getReplyid().equals( newdt.getReplyid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeReply(odt);
		}
		oldObjs.clear();
		//insert or update
		for(Reply newdt :newObjs){
			found = false;
			for(Iterator<Reply> it=getReplys().iterator();
			 it.hasNext();){
				Reply odt = it.next();
				if(odt.getReplyid().equals( newdt.getReplyid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addReply(newdt);
		} 	
	}	

	public Set<ThreadAnnex> getThreadAnnexs(){
		if(this.threadAnnexs==null)
			this.threadAnnexs = new HashSet<ThreadAnnex>();
		return this.threadAnnexs;
	}

	public void setThreadAnnexs(Set<ThreadAnnex> threadAnnexs) {
		this.threadAnnexs = threadAnnexs;
	}	

	public void addThreadAnnex(ThreadAnnex threadAnnex ){
		if (this.threadAnnexs==null)
			this.threadAnnexs = new HashSet<ThreadAnnex>();
		this.threadAnnexs.add(threadAnnex);
	}
	
	public void removeThreadAnnex(ThreadAnnex threadAnnex ){
		if (this.threadAnnexs==null)
			return;
		this.threadAnnexs.remove(threadAnnex);
	}
	
	public ThreadAnnex newThreadAnnex(){
		ThreadAnnex res = new ThreadAnnex();
  
		res.setThreadid(this.getThreadid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceThreadAnnexs(List<ThreadAnnex> threadAnnexs) {
		List<ThreadAnnex> newObjs = new ArrayList<ThreadAnnex>();
		for(ThreadAnnex p :threadAnnexs){
			if(p==null)
				continue;
			ThreadAnnex newdt = newThreadAnnex();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<ThreadAnnex> oldObjs = new HashSet<ThreadAnnex>();
		oldObjs.addAll(getThreadAnnexs());
		
		for(Iterator<ThreadAnnex> it=oldObjs.iterator(); it.hasNext();){
			ThreadAnnex odt = it.next();
			found = false;
			for(ThreadAnnex newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeThreadAnnex(odt);
		}
		oldObjs.clear();
		//insert or update
		for(ThreadAnnex newdt :newObjs){
			found = false;
			for(Iterator<ThreadAnnex> it=getThreadAnnexs().iterator();
			 it.hasNext();){
				ThreadAnnex odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addThreadAnnex(newdt);
		} 	
	}	


	public void copy(Thread other){
  
		this.setThreadid(other.getThreadid());
  
//		this.forumid= other.getForumid();
		this.titol= other.getTitol();  
		this.content= other.getContent();  
		this.wirterid= other.getWirterid();  
		this.wirter= other.getWirter();  
		this.posttime= other.getPosttime();  
		this.viewnum= other.getViewnum();  
		this.replnum= other.getReplnum();  
		this.annexnum= other.getAnnexnum();
	
		this.replys = other.getReplys();	
		this.threadAnnexs = other.getThreadAnnexs();
	}
	
	public void copyNotNullProperty(Thread other){
  
	if( other.getThreadid() != null)
		this.setThreadid(other.getThreadid());
  
//		if( other.getForumid() != null)
//			this.forumid= other.getForumid();
		if( other.getTitol() != null)
			this.titol= other.getTitol();  
		if( other.getContent() != null)
			this.content= other.getContent();  
		if( other.getWirterid() != null)
			this.wirterid= other.getWirterid();  
		if( other.getWirter() != null)
			this.wirter= other.getWirter();  
		if( other.getPosttime() != null)
			this.posttime= other.getPosttime();  
		if( other.getViewnum() != null)
			this.viewnum= other.getViewnum();  
		if( other.getReplnum() != null)
			this.replnum= other.getReplnum();  
		if( other.getAnnexnum() != null)
			this.annexnum= other.getAnnexnum();
	
		this.replys = other.getReplys();	
		this.threadAnnexs = other.getThreadAnnexs();
	}
	
	public void clearProperties(){
  
//		this.forumid= null;
		this.titol= null;  
		this.content= null;  
		this.wirterid= null;  
		this.wirter= null;  
		this.posttime= null;  
		this.viewnum= null;  
		this.replnum= null;  
		this.annexnum= null;
	
		this.replys = new HashSet<Reply>();	
		this.threadAnnexs = new HashSet<ThreadAnnex>();
	}
}
