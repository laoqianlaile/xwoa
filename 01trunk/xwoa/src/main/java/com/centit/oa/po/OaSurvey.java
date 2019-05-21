package com.centit.oa.po;

import java.util.Date;

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

public class OaSurvey implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;//序号

	private String  title;//title
	private String  reType;//调查类型
	private String  remark;//调查描述
	private Date  begtime;//开始时间
	private Date  endtime;//结束时间
	private String  creater;//发起者
	private Date  createtime;//发起时间
	private String  createRemark;//发起说明
	private String  createDepno;//发起部门
	private String  thesign;//投票状态OATheSign   1.未开始 2.进行中 3.结束  4.删除
	private String  sendusers;//人员范围
	private String  isautoend;//自动结束
	private String  isviewresult;//查看设置
	private String  isbookn;//记名调查 OAIsbookn  Y 记名 N 不记名 
	private Set<OaOnlineItem> oaOnlineItems = null;// new ArrayList<OaOnlineItem>();
	private Set<OaSurveydetail> oaSurveydetails = null;// new ArrayList<OaSurveydetail>();
	
	
	private String canVote="F";//是否可以投票

	private OaSurveyType oaSurveyType;
//	private OaCommonType oaCommonType;
	// Constructors
	/** default constructor */
	public OaSurvey() {
	}
	/** minimal constructor */
	public OaSurvey(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}

/** full constructor */
	public OaSurvey(
	 String djid		
	,String  title,String  reType,String  remark,Date  begtime,Date  endtime,String  creater,Date  createtime,String  createRemark,String  createDepno,String  thesign,String  sendusers,String  isautoend,String  isviewresult,String  isbookn) {
	
	    super();
		this.djid = djid;		
	
		this.title= title;
		this.reType= reType;
		this.remark= remark;
		this.begtime= begtime;
		this.endtime= endtime;
		this.creater= creater;
		this.createtime= createtime;
		this.createRemark= createRemark;
		this.createDepno= createDepno;
		this.thesign= thesign;
		this.sendusers= sendusers;
		this.isautoend= isautoend;
		this.isviewresult= isviewresult;
		this.isbookn= isbookn;		
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getReType() {
		return this.reType;
	}
	
	public void setReType(String reType) {
		this.reType = reType;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getBegtime() {
		return this.begtime;
	}
	
	public void setBegtime(Date begtime) {
		this.begtime = begtime;
	}
  
	public Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getCreateRemark() {
		return this.createRemark;
	}
	
	public void setCreateRemark(String createRemark) {
		this.createRemark = createRemark;
	}
  
	public String getCreateDepno() {
		return this.createDepno;
	}
	
	public void setCreateDepno(String createDepno) {
		this.createDepno = createDepno;
	}
  
	public String getThesign() {
		return this.thesign;
	}
	
	public void setThesign(String thesign) {
		this.thesign = thesign;
	}
  
	public String getSendusers() {
		return this.sendusers;
	}
	
	public void setSendusers(String sendusers) {
		this.sendusers = sendusers;
	}
  
	public String getIsautoend() {
	    if (StringUtils.isBlank(this.isautoend)) {
            this.isautoend = "N";
        }
		return this.isautoend;
	}
	
	public void setIsautoend(String isautoend) {
		this.isautoend = isautoend;
	}
  
	public String getIsviewresult() {
	    if (StringUtils.isBlank(this.isviewresult)) {
            this.isviewresult = "N";
        }
		return this.isviewresult;
	}
	
	public void setIsviewresult(String isviewresult) {
	    
		this.isviewresult = isviewresult;
	}
  
	public String getIsbookn() {
	    if (StringUtils.isBlank(this.isbookn)) {
            this.isbookn = "N";
        }
		return this.isbookn;
	}
	
	public void setIsbookn(String isbookn) {
		this.isbookn = isbookn;
	}


	public Set<OaOnlineItem> getOaOnlineItems(){
		if(this.oaOnlineItems==null)
			this.oaOnlineItems = new HashSet<OaOnlineItem>();
		return this.oaOnlineItems;
	}

	public void setOaOnlineItems(Set<OaOnlineItem> oaOnlineItems) {
		this.oaOnlineItems = oaOnlineItems;
	}	

	public void addOaOnlineItem(OaOnlineItem oaOnlineItem ){
		if (this.oaOnlineItems==null)
			this.oaOnlineItems = new HashSet<OaOnlineItem>();
		this.oaOnlineItems.add(oaOnlineItem);
	}
	
	public void removeOaOnlineItem(OaOnlineItem oaOnlineItem ){
		if (this.oaOnlineItems==null)
			return;
		this.oaOnlineItems.remove(oaOnlineItem);
	}
	
	public OaOnlineItem newOaOnlineItem(){
		OaOnlineItem res = new OaOnlineItem();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceOaOnlineItems(List<OaOnlineItem> oaOnlineItems) {
		List<OaOnlineItem> newObjs = new ArrayList<OaOnlineItem>();
		for(OaOnlineItem p :oaOnlineItems){
			if(p==null)
				continue;
			OaOnlineItem newdt = newOaOnlineItem();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaOnlineItem> oldObjs = new HashSet<OaOnlineItem>();
		oldObjs.addAll(getOaOnlineItems());
		
		for(Iterator<OaOnlineItem> it=oldObjs.iterator(); it.hasNext();){
			OaOnlineItem odt = it.next();
			found = false;
			for(OaOnlineItem newdt :newObjs){
				if(odt.getNo().equals( newdt.getNo())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaOnlineItem(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaOnlineItem newdt :newObjs){
			found = false;
			for(Iterator<OaOnlineItem> it=getOaOnlineItems().iterator();
			 it.hasNext();){
				OaOnlineItem odt = it.next();
				if(odt.getNo().equals( newdt.getNo())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaOnlineItem(newdt);
		} 	
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
  
		res.setDjid(this.getDjid());

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


	public void copy(OaSurvey other){
  
		this.setDjid(other.getDjid());
  
		this.title= other.getTitle();  
		this.reType= other.getReType();  
		this.remark= other.getRemark();  
		this.begtime= other.getBegtime();  
		this.endtime= other.getEndtime();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.createRemark= other.getCreateRemark();  
		this.createDepno= other.getCreateDepno();  
		this.thesign= other.getThesign();  
		this.sendusers= other.getSendusers();  
		this.isautoend= other.getIsautoend();  
		this.isviewresult= other.getIsviewresult();  
		this.isbookn= other.getIsbookn();
	
		
		//this.oaOnlineItems = other.getOaOnlineItems();	
		//this.oaSurveydetails = other.getOaSurveydetails();
		
//		this.oaCommonType=other.getOaCommonType();
		this.oaSurveyType=other.getOaSurveyType();
	}
	
	public void copyNotNullProperty(OaSurvey other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getReType() != null)
			this.reType= other.getReType();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getBegtime() != null)
			this.begtime= other.getBegtime();  
		if( other.getEndtime() != null)
			this.endtime= other.getEndtime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateRemark() != null)
			this.createRemark= other.getCreateRemark();  
		if( other.getCreateDepno() != null)
			this.createDepno= other.getCreateDepno();  
		if( other.getThesign() != null)
			this.thesign= other.getThesign();  
		if( other.getSendusers() != null)
			this.sendusers= other.getSendusers();  
		if( other.getIsautoend() != null)
			this.isautoend= other.getIsautoend();  
		if( other.getIsviewresult() != null)
			this.isviewresult= other.getIsviewresult();  
		if( other.getIsbookn() != null)
			this.isbookn= other.getIsbookn();
	
		this.oaOnlineItems = other.getOaOnlineItems();	
		this.oaSurveydetails = other.getOaSurveydetails();
	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.reType= null;  
		this.remark= null;  
		this.begtime= null;  
		this.endtime= null;  
		this.creater= null;  
		this.createtime= null;  
		this.createRemark= null;  
		this.createDepno= null;  
		this.thesign= null;  
		this.sendusers= null;  
		this.isautoend= null;  
		this.isviewresult= null;  
		this.isbookn= null;
	
		this.oaOnlineItems = new HashSet<OaOnlineItem>();	
		this.oaSurveydetails = new HashSet<OaSurveydetail>();
	}
//    public OaCommonType getOaCommonType() {
//        return oaCommonType;
//    }
//    public void setOaCommonType(OaCommonType oaCommonType) {
//        this.oaCommonType = oaCommonType;
//    }
    public String getCanVote() {
        return canVote;
    }
    public OaSurveyType getOaSurveyType() {
        return oaSurveyType;
    }
    public void setOaSurveyType(OaSurveyType oaSurveyType) {
        this.oaSurveyType = oaSurveyType;
    }
    public void setCanVote(String canVote) {
        this.canVote = canVote;
    }
    public static void copyObject(OaSurvey oaSurveyNew, OaSurvey oaSurvey) {
        oaSurveyNew.setBegtime(oaSurvey.getBegtime());
        oaSurveyNew.setCanVote(oaSurvey.getCanVote());
        oaSurveyNew.setCreateDepno(oaSurvey.getCreateDepno());
        oaSurveyNew.setCreater(oaSurvey.getCreater());
        oaSurveyNew.setCreateRemark(oaSurvey.getCreateRemark());
        oaSurveyNew.setCreatetime(oaSurvey.getCreatetime());
       
        oaSurveyNew.setEndtime(oaSurvey.getEndtime());
        oaSurveyNew.setIsautoend(oaSurvey.getIsautoend());
        oaSurveyNew.setIsbookn(oaSurvey.getIsbookn());
        oaSurveyNew.setIsviewresult(oaSurvey.getIsviewresult());
        oaSurveyNew.setRemark(oaSurvey.getRemark());
        oaSurveyNew.setReType(oaSurvey.getReType());
        oaSurveyNew.setSendusers(oaSurvey.getSendusers());
        oaSurveyNew.setThesign(oaSurvey.getThesign());//状态为未发布
        oaSurveyNew.setTitle(oaSurvey.getTitle());
        
    }
  
}
