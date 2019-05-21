package com.centit.oa.po;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaOnlineItem implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;//流水号

	private String  djid;//调查序号
	private String  chosetype;//题目类型 OAChoseType  1:多选 2:单选 3:问答
	private String  title;//题目名称
	private String  itemnames;//题目选项
	private Long  chosenum;//选项个数
	private Long  limitnum;//最多可选个数
	private String  thesign;//是否必答  OAIsOrNo T是 F否
	private Set<OaOnlineItems> oaOnlineItemss = null;// new ArrayList<OaOnlineItems>();

	// Constructors
	/** default constructor */
	public OaOnlineItem() {
	}
	/** minimal constructor */
	public OaOnlineItem(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaOnlineItem(
	 String no		
	,String  djid,String  chosetype,String  title,String  itemnames,Long  chosenum,Long  limitnum,String  thesign) {
	
	
		this.no = no;		
	
		this.djid= djid;
		this.chosetype= chosetype;
		this.title= title;
		this.itemnames= itemnames;
		this.chosenum= chosenum;
		this.limitnum= limitnum;
		this.thesign= thesign;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getDjid() {
		return this.djid;
	}
	
	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public String getChosetype() {
		return this.chosetype;
	}
	
	public void setChosetype(String chosetype) {
		this.chosetype = chosetype;
	}
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getItemnames() {
		return this.itemnames;
	}
	
	public void setItemnames(String itemnames) {
		this.itemnames = itemnames;
	}
  
	public Long getChosenum() {
		return this.chosenum;
	}
	
	public void setChosenum(Long chosenum) {
		this.chosenum = chosenum;
	}
  
	public Long getLimitnum() {
		return this.limitnum;
	}
	
	public void setLimitnum(Long limitnum) {
		this.limitnum = limitnum;
	}
  
	public String getThesign() {
	    if (StringUtils.isBlank(this.thesign)) {
            this.thesign = "F";
        }
		return this.thesign;
	}
	
	public void setThesign(String thesign) {
		this.thesign = thesign;
	}


	public Set<OaOnlineItems> getOaOnlineItemss(){
		if(this.oaOnlineItemss==null)
			this.oaOnlineItemss = new HashSet<OaOnlineItems>();
		return this.oaOnlineItemss;
	}

	public void setOaOnlineItemss(Set<OaOnlineItems> oaOnlineItemss) {
		this.oaOnlineItemss = oaOnlineItemss;
	}	

	public void addOaOnlineItems(OaOnlineItems oaOnlineItems ){
		if (this.oaOnlineItemss==null)
			this.oaOnlineItemss = new HashSet<OaOnlineItems>();
		this.oaOnlineItemss.add(oaOnlineItems);
	}
	
	public void removeOaOnlineItems(OaOnlineItems oaOnlineItems ){
		if (this.oaOnlineItemss==null)
			return;
		this.oaOnlineItemss.remove(oaOnlineItems);
	}
	
	public OaOnlineItems newOaOnlineItems(){
		OaOnlineItems res = new OaOnlineItems();
  
		res.setNo(this.getNo());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceOaOnlineItemss(List<OaOnlineItems> oaOnlineItemss) {
		List<OaOnlineItems> newObjs = new ArrayList<OaOnlineItems>();
		for(OaOnlineItems p :oaOnlineItemss){
			if(p==null)
				continue;
			OaOnlineItems newdt = newOaOnlineItems();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaOnlineItems> oldObjs = new HashSet<OaOnlineItems>();
		oldObjs.addAll(getOaOnlineItemss());
		
		for(Iterator<OaOnlineItems> it=oldObjs.iterator(); it.hasNext();){
			OaOnlineItems odt = it.next();
			found = false;
			for(OaOnlineItems newdt :newObjs){
				if(odt.getItemid().equals( newdt.getItemid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaOnlineItems(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaOnlineItems newdt :newObjs){
			found = false;
			for(Iterator<OaOnlineItems> it=getOaOnlineItemss().iterator();
			 it.hasNext();){
				OaOnlineItems odt = it.next();
				if(odt.getItemid().equals( newdt.getItemid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaOnlineItems(newdt);
		} 	
	}	


	public void copy(OaOnlineItem other){
  
		this.setNo(other.getNo());
  
		this.djid= other.getDjid();  
		this.chosetype= other.getChosetype();  
		this.title= other.getTitle();  
		this.itemnames= other.getItemnames();  
		this.chosenum= other.getChosenum();  
		this.limitnum= other.getLimitnum();  
		this.thesign= other.getThesign();
	
		this.oaOnlineItemss = other.getOaOnlineItemss();
	}
	
	public void copyNotNullProperty(OaOnlineItem other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getDjid() != null)
			this.djid= other.getDjid();  
		if( other.getChosetype() != null)
			this.chosetype= other.getChosetype();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getItemnames() != null)
			this.itemnames= other.getItemnames();  
		if( other.getChosenum() != null)
			this.chosenum= other.getChosenum();  
		if( other.getLimitnum() != null)
			this.limitnum= other.getLimitnum();  
		if( other.getThesign() != null)
			this.thesign= other.getThesign();
	
		this.oaOnlineItemss = other.getOaOnlineItemss();
	}
	
	public void clearProperties(){
  
		this.djid= null;  
		this.chosetype= null;  
		this.title= null;  
		this.itemnames= null;  
		this.chosenum= null;  
		this.limitnum= null;  
		this.thesign= null;
	
		this.oaOnlineItemss = new HashSet<OaOnlineItems>();
	}
}
