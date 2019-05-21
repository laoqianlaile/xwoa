package com.centit.oa.po;

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

public class OaHelpinfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;

	private String  columnType;
	private String  infoName;
	private String  remark;
	private String  creater;
	private Date  creatertime;
	private byte[]  fileDoc;
	private String  fileDocname;
    private String  state;
	private String  isallowcomment;
	private String isgood;//1.精华帖子2.0普通帖子
	private Long replynum;
	private Long  viewnum;
	private Set<OaLeaveMessage> oaLeaveMessages = null;// new ArrayList<OaLeaveMessage>();

	// Constructors
	/** default constructor */
	public OaHelpinfo() {
	}
	/** minimal constructor */
	public OaHelpinfo(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}  
	/** full constructor */
    public OaHelpinfo(
     String djid        
    ,String  columnType
    ,String  infoName
    ,String  remark
    ,String  creater
    ,Date  creatertime

    ,String  fileDocname
    ,String  state
    ,String  isallowcomment
    ,String isgood,
    Long replynum,
    Long  viewnum
    ) {
    
    
        this.djid = djid;       
    
        this.columnType= columnType;
        this.infoName= infoName;
        this.remark= remark;
        this.creater= creater;
        this.creatertime= creatertime;

        this.fileDocname= fileDocname;
        this.state= state;
        this.isallowcomment= isallowcomment;    
        this.isgood=isgood;
        this.replynum=replynum;
        this.viewnum=viewnum;
    }
/** full constructor */
	public OaHelpinfo(
	 String djid		
	,String  columnType
	,String  infoName
	,String  remark
	,String  creater
	,Date  creatertime
	,byte[]  fileDoc
	,String  fileDocname
	,String  state
	,String  isallowcomment
	,String isgood,
	Long replynum,
	Long  viewnum
	) {
	
	
		this.djid = djid;		
	
		this.columnType= columnType;
		this.infoName= infoName;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;
		this.fileDoc= fileDoc;
		this.fileDocname= fileDocname;
		this.state= state;
		this.isallowcomment= isallowcomment;	
		this.isgood=isgood;
		this.replynum=replynum;
		this.viewnum=viewnum;
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
	public Long getReplynum() {
        return replynum;
    }
    public void setReplynum(Long replynum) {
        this.replynum = replynum;
    }
	public String getColumnType() {
		return this.columnType;
	}
	
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
    public Long getViewnum() {
        return viewnum;
    }
    public void setViewnum(Long viewnum) {
        this.viewnum = viewnum;
    }
    public String getIsgood() {
        return isgood;
    }
    public void setIsgood(String isgood) {
        this.isgood = isgood;
    }
	public String getInfoName() {
		return this.infoName;
	}
	
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public byte[] getFileDoc() {
		return this.fileDoc;
	}
	
	public void setFileDoc(byte[] fileDoc) {
		this.fileDoc = fileDoc;
	}
  
	public String getFileDocname() {
		return this.fileDocname;
	}
	
	public void setFileDocname(String fileDocname) {
		this.fileDocname = fileDocname;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
  
	public String getIsallowcomment() {
		return this.isallowcomment;
	}
	
	public void setIsallowcomment(String isallowcomment) {
		this.isallowcomment = isallowcomment;
	}


	public Set<OaLeaveMessage> getOaLeaveMessages(){
		if(this.oaLeaveMessages==null)
			this.oaLeaveMessages = new HashSet<OaLeaveMessage>();
		return this.oaLeaveMessages;
	}

	public void setOaLeaveMessages(Set<OaLeaveMessage> oaLeaveMessages) {
		this.oaLeaveMessages = oaLeaveMessages;
	}	

	public void addOaLeaveMessage(OaLeaveMessage oaLeaveMessage ){
		if (this.oaLeaveMessages==null)
			this.oaLeaveMessages = new HashSet<OaLeaveMessage>();
		this.oaLeaveMessages.add(oaLeaveMessage);
	}
	
	public void removeOaLeaveMessage(OaLeaveMessage oaLeaveMessage ){
		if (this.oaLeaveMessages==null)
			return;
		this.oaLeaveMessages.remove(oaLeaveMessage);
	}
	
	public OaLeaveMessage newOaLeaveMessage(){
		OaLeaveMessage res = new OaLeaveMessage();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceOaLeaveMessages(List<OaLeaveMessage> oaLeaveMessages) {
		List<OaLeaveMessage> newObjs = new ArrayList<OaLeaveMessage>();
		for(OaLeaveMessage p :oaLeaveMessages){
			if(p==null)
				continue;
			OaLeaveMessage newdt = newOaLeaveMessage();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaLeaveMessage> oldObjs = new HashSet<OaLeaveMessage>();
		oldObjs.addAll(getOaLeaveMessages());
		
		for(Iterator<OaLeaveMessage> it=oldObjs.iterator(); it.hasNext();){
			OaLeaveMessage odt = it.next();
			found = false;
			for(OaLeaveMessage newdt :newObjs){
				if(odt.getNo().equals( newdt.getNo())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaLeaveMessage(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaLeaveMessage newdt :newObjs){
			found = false;
			for(Iterator<OaLeaveMessage> it=getOaLeaveMessages().iterator();
			 it.hasNext();){
				OaLeaveMessage odt = it.next();
				if(odt.getNo().equals( newdt.getNo())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaLeaveMessage(newdt);
		} 	
	}	


	public void copy(OaHelpinfo other){
  
		this.setDjid(other.getDjid());
  
		this.columnType= other.getColumnType();  
		this.infoName= other.getInfoName();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.fileDoc= other.getFileDoc();  
		this.fileDocname= other.getFileDocname();  
		this.state= other.getState();  
		this.isallowcomment= other.getIsallowcomment();
	
		this.oaLeaveMessages = other.getOaLeaveMessages();
		this.isgood=other.getIsgood();
        this.replynum=other.getReplynum();
        this.viewnum=other.getViewnum();
	}
	
	public void copyNotNullProperty(OaHelpinfo other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getColumnType() != null)
			this.columnType= other.getColumnType();  
		if( other.getInfoName() != null)
			this.infoName= other.getInfoName();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getFileDoc() != null)
			this.fileDoc= other.getFileDoc();  
		if( other.getFileDocname() != null)
			this.fileDocname= other.getFileDocname();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getIsallowcomment() != null)
			this.isallowcomment= other.getIsallowcomment();
		if(other.getIsgood()!=null)
		    this.isgood=other.getIsgood();
		if(other.getReplynum()!=null)
		    this.replynum=other.getReplynum();
		if(other.getViewnum()!=null)
		    this.viewnum=other.getViewnum();
		this.oaLeaveMessages = other.getOaLeaveMessages();
	}
	
	public void clearProperties(){
  
		this.columnType= null;  
		this.infoName= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.fileDoc= null;  
		this.fileDocname= null;  
		this.state= null;  
		this.isallowcomment= null;
		this.isgood=null;
        this.replynum=null;
        this.viewnum=null;
		this.oaLeaveMessages = new HashSet<OaLeaveMessage>();
	}
}
