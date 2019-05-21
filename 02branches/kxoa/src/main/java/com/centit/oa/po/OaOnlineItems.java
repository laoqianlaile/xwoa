package com.centit.oa.po;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaOnlineItems implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String itemid;//序号

	private String  no;//题目流水号
	private String  title;//选项明细名称
	private Set<OaSurveydetail> oaSurveydetails = null;// new ArrayList<OaSurveydetail>();

	// Constructors
	/** default constructor */
	public OaOnlineItems() {
	}
	/** minimal constructor */
	public OaOnlineItems(
		String itemid		
		) {
	
	
		this.itemid = itemid;		
			
	}

/** full constructor */
	public OaOnlineItems(
	 String itemid		
	,String  no,String  title) {
	
	
		this.itemid = itemid;		
	
		this.no= no;
		this.title= title;		
	}
	

  
	public String getItemid() {
		return this.itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	// Property accessors
  
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}


	public Set<OaSurveydetail> getOaSurveydetails(){
		if(this.oaSurveydetails==null)
			this.oaSurveydetails = new HashSet<OaSurveydetail>();
		return this.oaSurveydetails;
	}

	public void setOaSurveydetails(Set<OaSurveydetail> oaSurveydetails) {
		this.oaSurveydetails = oaSurveydetails;
	}	

	public void addOaSurveydetail(OaSurveydetail oaSurveydetail ){
		if (this.oaSurveydetails==null)
			this.oaSurveydetails = new HashSet<OaSurveydetail>();
		this.oaSurveydetails.add(oaSurveydetail);
	}
	
	public void removeOaSurveydetail(OaSurveydetail oaSurveydetail ){
		if (this.oaSurveydetails==null)
			return;
		this.oaSurveydetails.remove(oaSurveydetail);
	}
	
	public OaSurveydetail newOaSurveydetail(){
		OaSurveydetail res = new OaSurveydetail();
  
		res.setItemid(this.getItemid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceOaSurveydetails(List<OaSurveydetail> oaSurveydetails) {
		List<OaSurveydetail> newObjs = new ArrayList<OaSurveydetail>();
		for(OaSurveydetail p :oaSurveydetails){
			if(p==null)
				continue;
			OaSurveydetail newdt = newOaSurveydetail();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaSurveydetail> oldObjs = new HashSet<OaSurveydetail>();
		oldObjs.addAll(getOaSurveydetails());
		
		for(Iterator<OaSurveydetail> it=oldObjs.iterator(); it.hasNext();){
			OaSurveydetail odt = it.next();
			found = false;
			for(OaSurveydetail newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaSurveydetail(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaSurveydetail newdt :newObjs){
			found = false;
			for(Iterator<OaSurveydetail> it=getOaSurveydetails().iterator();
			 it.hasNext();){
				OaSurveydetail odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaSurveydetail(newdt);
		} 	
	}	


	public void copy(OaOnlineItems other){
  
		this.setItemid(other.getItemid());
  
		this.no= other.getNo();  
		this.title= other.getTitle();
	
		this.oaSurveydetails = other.getOaSurveydetails();
	}
	
	public void copyNotNullProperty(OaOnlineItems other){
  
	if( other.getItemid() != null)
		this.setItemid(other.getItemid());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getTitle() != null)
			this.title= other.getTitle();
	
		this.oaSurveydetails = other.getOaSurveydetails();
	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.title= null;
	
		this.oaSurveydetails = new HashSet<OaSurveydetail>();
	}
}
