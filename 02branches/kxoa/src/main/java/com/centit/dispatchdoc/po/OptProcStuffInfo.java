package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptProcStuffInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String stuffid;

	private String  filecode;
	private String  djId;
	private Long  nodeinstid;
	private String  nodename;
	private String  filename;
	private String  filetype;
	private Date  uploadtime;
	private String  uploadusercode;
	private String  archivetype;
	private String  sign;

	// Constructors
	/** default constructor */
	public OptProcStuffInfo() {
	}
	/** minimal constructor */
	public OptProcStuffInfo(
		String stuffid		
		,String  djId) {
	
	
		this.stuffid = stuffid;		
	
		this.djId= djId; 		
	}

/** full constructor */
	public OptProcStuffInfo(
	 String stuffid		
	,String  filecode,String  djId,Long  nodeinstid,String  nodename,String  filename,String  filetype,Date  uploadtime,String  uploadusercode,String  archivetype,String  sign) {
	
	
		this.stuffid = stuffid;		
	
		this.filecode= filecode;
		this.djId= djId;
		this.nodeinstid= nodeinstid;
		this.nodename= nodename;
		this.filename= filename;
		this.filetype= filetype;
		this.uploadtime= uploadtime;
		this.uploadusercode= uploadusercode;
		this.archivetype= archivetype;
		this.sign= sign;		
	}
	

  
	public String getStuffid() {
		return this.stuffid;
	}

	public void setStuffid(String stuffid) {
		this.stuffid = stuffid;
	}
	// Property accessors
  
	public String getFilecode() {
		return this.filecode;
	}
	
	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}
  
	public String getDjId() {
		return this.djId;
	}
	
	public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public Long getNodeinstid() {
		return this.nodeinstid;
	}
	
	public void setNodeinstid(Long nodeinstid) {
		this.nodeinstid = nodeinstid;
	}
  
	public String getNodename() {
		return this.nodename;
	}
	
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
  
	public String getFilename() {
		return this.filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
  
	public String getFiletype() {
		return this.filetype;
	}
	
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
  
	public Date getUploadtime() {
		return this.uploadtime;
	}
	
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
  
	public String getUploadusercode() {
		return this.uploadusercode;
	}
	
	public void setUploadusercode(String uploadusercode) {
		this.uploadusercode = uploadusercode;
	}
  
	public String getArchivetype() {
		return this.archivetype;
	}
	
	public void setArchivetype(String archivetype) {
		this.archivetype = archivetype;
	}
  
	public String getSign() {
		return this.sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}



	public void copy(OptProcStuffInfo other){
  
		this.setStuffid(other.getStuffid());
  
		this.filecode= other.getFilecode();  
		this.djId= other.getDjId();  
		this.nodeinstid= other.getNodeinstid();  
		this.nodename= other.getNodename();  
		this.filename= other.getFilename();  
		this.filetype= other.getFiletype();  
		this.uploadtime= other.getUploadtime();  
		this.uploadusercode= other.getUploadusercode();  
		this.archivetype= other.getArchivetype();  
		this.sign= other.getSign();

	}
	
	public void copyNotNullProperty(OptProcStuffInfo other){
  
	if( other.getStuffid() != null)
		this.setStuffid(other.getStuffid());
  
		if( other.getFilecode() != null)
			this.filecode= other.getFilecode();  
		if( other.getDjId() != null)
			this.djId= other.getDjId();  
		if( other.getNodeinstid() != null)
			this.nodeinstid= other.getNodeinstid();  
		if( other.getNodename() != null)
			this.nodename= other.getNodename();  
		if( other.getFilename() != null)
			this.filename= other.getFilename();  
		if( other.getFiletype() != null)
			this.filetype= other.getFiletype();  
		if( other.getUploadtime() != null)
			this.uploadtime= other.getUploadtime();  
		if( other.getUploadusercode() != null)
			this.uploadusercode= other.getUploadusercode();  
		if( other.getArchivetype() != null)
			this.archivetype= other.getArchivetype();  
		if( other.getSign() != null)
			this.sign= other.getSign();

	}
	
	public void clearProperties(){
  
		this.filecode= null;  
		this.djId= null;  
		this.nodeinstid= null;  
		this.nodename= null;  
		this.filename= null;  
		this.filetype= null;  
		this.uploadtime= null;  
		this.uploadusercode= null;  
		this.archivetype= null;  
		this.sign= null;

	}
}
