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

public class Forum implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	
	public static final String INFO_RELEASE = "INFO_REL";


	private Long forumid;

	private String  boardcode;
	private String  forumname;
	private String  forumpic;
	private String  announcement;
	private Integer  joinright;
	private Integer  viewright;
	private Integer  postright;
	private Integer  replyright;
	private Integer  isforumer;
	private Date  createtime;
	private Long  mebernum;
	private Long  threadnum;
	private Long  replynum;
	private String  forumstate;
	private Set<Thread> threads = null;// new ArrayList<Thread>();
	private Set<ForumStaff> forumStaffs = null;// new ArrayList<ForumStaff>();
	private Set<ForumType> forumTypes = null;// new ArrayList<ForumType>();
	
	
	
	private List<Thread> threadList;
	
	//存放标识：ectable页面不支持map.key取值
	private int flag;

	public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    // Constructors
	/** default constructor */
	public Forum() {
	}
	/** minimal constructor */
	public Forum(
		Long forumid		
		) {
	
	
		this.forumid = forumid;		
			
	}

/** full constructor */
	public Forum(
	 Long forumid		
	,String  boardcode,String  forumname,String  forumpic,String  announcement,Integer  joinright,Integer  viewright,Integer  postright,Integer  replyright,Integer  isforumer,Date  createtime,Long  mebernum,Long  threadnum,Long  replynum,String  forumstate) {
	
	
		this.forumid = forumid;		
	
		this.boardcode= boardcode;
		this.forumname= forumname;
		this.forumpic= forumpic;
		this.announcement= announcement;
		this.joinright= joinright;
		this.viewright= viewright;
		this.postright= postright;
		this.replyright= replyright;
		this.isforumer= isforumer;
		this.createtime= createtime;
		this.mebernum= mebernum;
		this.threadnum= threadnum;
		this.replynum= replynum;
		this.forumstate= forumstate;		
	}
	
  
	public List<Thread> getThreadList() {
		return threadList;
	}
	public void setThreadList(List<Thread> threadList) {
		this.threadList = threadList;
	}
	public Long getForumid() {
		return this.forumid;
	}

	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}
	// Property accessors
  
	public String getBoardcode() {
		return this.boardcode;
	}
	
	public void setBoardcode(String boardcode) {
		this.boardcode = boardcode;
	}
  
	public String getForumname() {
		return this.forumname;
	}
	
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
  
	public String getForumpic() {
		return this.forumpic;
	}
	
	public void setForumpic(String forumpic) {
		this.forumpic = forumpic;
	}
  
	public String getAnnouncement() {
		return this.announcement;
	}
	
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
  
	public Integer getJoinright() {
		return this.joinright;
	}
	
	public void setJoinright(Integer joinright) {
		this.joinright = joinright;
	}
  
	public Integer getViewright() {
		return this.viewright;
	}
	
	public void setViewright(Integer viewright) {
		this.viewright = viewright;
	}
  
	public Integer getPostright() {
		return this.postright;
	}
	
	public void setPostright(Integer postright) {
		this.postright = postright;
	}
  
	public Integer getReplyright() {
		return this.replyright;
	}
	
	public void setReplyright(Integer replyright) {
		this.replyright = replyright;
	}
  
	public Integer getIsforumer() {
		return this.isforumer;
	}
	
	public void setIsforumer(Integer isforumer) {
		this.isforumer = isforumer;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public Long getMebernum() {
		return this.mebernum;
	}
	
	public void setMebernum(Long mebernum) {
		this.mebernum = mebernum;
	}
  
	public Long getThreadnum() {
		return this.threadnum;
	}
	
	public void setThreadnum(Long threadnum) {
		this.threadnum = threadnum;
	}
  
	public Long getReplynum() {
		return this.replynum;
	}
	
	public void setReplynum(Long replynum) {
		this.replynum = replynum;
	}
  
	public String getForumstate() {
		return this.forumstate;
	}
	
	public void setForumstate(String forumstate) {
		this.forumstate = forumstate;
	}


	public Set<Thread> getThreads(){
		if(this.threads==null)
			this.threads = new HashSet<Thread>();
		return this.threads;
	}

	public void setThreads(Set<Thread> threads) {
		this.threads = threads;
	}	

	public void addThread(Thread thread ){
		if (this.threads==null)
			this.threads = new HashSet<Thread>();
		this.threads.add(thread);
	}
	
	public void removeThread(Thread thread ){
		if (this.threads==null)
			return;
		this.threads.remove(thread);
	}
	
	public Thread newThread(){
		Thread res = new Thread();
  
//		res.setForumid(this.getForumid());

        res.setForum(this);
		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceThreads(List<Thread> threads) {
		List<Thread> newObjs = new ArrayList<Thread>();
		for(Thread p :threads){
			if(p==null)
				continue;
			Thread newdt = newThread();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<Thread> oldObjs = new HashSet<Thread>();
		oldObjs.addAll(getThreads());
		
		for(Iterator<Thread> it=oldObjs.iterator(); it.hasNext();){
			Thread odt = it.next();
			found = false;
			for(Thread newdt :newObjs){
				if(odt.getThreadid().equals( newdt.getThreadid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeThread(odt);
		}
		oldObjs.clear();
		//insert or update
		for(Thread newdt :newObjs){
			found = false;
			for(Iterator<Thread> it=getThreads().iterator();
			 it.hasNext();){
				Thread odt = it.next();
				if(odt.getThreadid().equals( newdt.getThreadid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addThread(newdt);
		} 	
	}	
	public Set<ForumType> getForumTypes(){
		if(this.forumTypes==null)
			this.forumTypes = new HashSet<ForumType>();
		return this.forumTypes;
	}

	public void setForumTypes(Set<ForumType> forumTypes) {
		this.forumTypes = forumTypes;
	}	

	public void addForumType(ForumType forumType ){
		if (this.forumTypes==null)
			this.forumTypes = new HashSet<ForumType>();
		this.forumTypes.add(forumType);
	}
	
	public void removeForumType(ForumType forumType ){
		if (this.forumTypes==null)
			return;
		this.forumTypes.remove(forumType);
	}
	
	public ForumType newForumType(){
		ForumType res = new ForumType();
  
		res.setForumid(this.getForumid());

		return res;
	}
	
	public void replaceForumTypes(List<ForumType> forumTypes) {
		List<ForumType> newObjs = new ArrayList<ForumType>();
		for(ForumType p :forumTypes){
			if(p==null)
				continue;
			ForumType newdt = newForumType();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<ForumType> oldObjs = new HashSet<ForumType>();
		oldObjs.addAll(getForumTypes());
		
		for(Iterator<ForumType> it=oldObjs.iterator(); it.hasNext();){
			ForumType odt = it.next();
			found = false;
			for(ForumType newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeForumType(odt);
		}
		oldObjs.clear();
		//insert or update
		for(ForumType newdt :newObjs){
			found = false;
			for(Iterator<ForumType> it=getForumTypes().iterator();
			 it.hasNext();){
				ForumType odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addForumType(newdt);
		} 	
	}	
	public Set<ForumStaff> getForumStaffs(){
		if(this.forumStaffs==null)
			this.forumStaffs = new HashSet<ForumStaff>();
		return this.forumStaffs;
	}

	public void setForumStaffs(Set<ForumStaff> forumStaffs) {
		this.forumStaffs = forumStaffs;
	}	

	public void addForumStaff(ForumStaff forumStaff ){
		if (this.forumStaffs==null)
			this.forumStaffs = new HashSet<ForumStaff>();
		this.forumStaffs.add(forumStaff);
	}
	
	public void removeForumStaff(ForumStaff forumStaff ){
		if (this.forumStaffs==null)
			return;
		this.forumStaffs.remove(forumStaff);
	}
	
	public ForumStaff newForumStaff(){
		ForumStaff res = new ForumStaff();
  
		res.setForumid(this.getForumid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceForumStaffs(List<ForumStaff> forumStaffs) {
		List<ForumStaff> newObjs = new ArrayList<ForumStaff>();
		for(ForumStaff p :forumStaffs){
			if(p==null)
				continue;
			ForumStaff newdt = newForumStaff();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<ForumStaff> oldObjs = new HashSet<ForumStaff>();
		oldObjs.addAll(getForumStaffs());
		
		for(Iterator<ForumStaff> it=oldObjs.iterator(); it.hasNext();){
			ForumStaff odt = it.next();
			found = false;
			for(ForumStaff newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeForumStaff(odt);
		}
		oldObjs.clear();
		//insert or update
		for(ForumStaff newdt :newObjs){
			found = false;
			for(Iterator<ForumStaff> it=getForumStaffs().iterator();
			 it.hasNext();){
				ForumStaff odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addForumStaff(newdt);
		} 	
	}	


	public void copy(Forum other){
  
		this.setForumid(other.getForumid());
  
		this.boardcode= other.getBoardcode();  
		this.forumname= other.getForumname();  
		this.forumpic= other.getForumpic();  
		this.announcement= other.getAnnouncement();  
		this.joinright= other.getJoinright();  
		this.viewright= other.getViewright();  
		this.postright= other.getPostright();  
		this.replyright= other.getReplyright();  
		this.isforumer= other.getIsforumer();  
		this.createtime= other.getCreatetime();  
		this.mebernum= other.getMebernum();  
		this.threadnum= other.getThreadnum();  
		this.replynum= other.getReplynum();  
		this.forumstate= other.getForumstate();
	
		this.threads = other.getThreads();	
		this.forumStaffs = other.getForumStaffs();
	}
	
	public void copyNotNullProperty(Forum other){
  
	if( other.getForumid() != null)
		this.setForumid(other.getForumid());
  
		if( other.getBoardcode() != null)
			this.boardcode= other.getBoardcode();  
		if( other.getForumname() != null)
			this.forumname= other.getForumname();  
		if( other.getForumpic() != null)
			this.forumpic= other.getForumpic();  
		if( other.getAnnouncement() != null)
			this.announcement= other.getAnnouncement();  
		if( other.getJoinright() != null)
			this.joinright= other.getJoinright();
		if( other.getViewright() != null)
			this.viewright= other.getViewright();
		if( other.getPostright() != null)
			this.postright= other.getPostright();
		if( other.getReplyright() != null)
			this.replyright= other.getReplyright();
		if( other.getIsforumer() != null)
			this.isforumer= other.getIsforumer();
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getMebernum() != null)
			this.mebernum= other.getMebernum();  
		if( other.getThreadnum() != null)
			this.threadnum= other.getThreadnum();  
		if( other.getReplynum() != null)
			this.replynum= other.getReplynum();  
		if( other.getForumstate() != null)
			this.forumstate= other.getForumstate();
	
//		this.threads = other.getThreads();	
//		this.forumStaffs = other.getForumStaffs();
	}
	
	public void clearProperties(){
  
		this.boardcode= null;  
		this.forumname= null;  
		this.forumpic= null;  
		this.announcement= null;  
		this.joinright= null;
		this.viewright= null;
		this.postright= null;
		this.replyright= null;
		this.isforumer= null;
		this.createtime= null;  
		this.mebernum= null;  
		this.threadnum= null;  
		this.replynum= null;  
		this.forumstate= null;
	
		this.threads = new HashSet<Thread>();	
		this.forumStaffs = new HashSet<ForumStaff>();
	}
}
