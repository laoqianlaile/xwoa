package com.centit.oa.po;



/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VOaSurveyDetail implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;//流水号

	private String  djid;//调查序号
	private String  chosetype;//题目类型 OAChoseType  1:多选 2:单选 3:问答
	private String  title;//题目名称
	private String  itemnames;//题目选项
	private Long  chosenum;//选项个数
	private Long  limitnum;//最多可选个数
	private String  thesign;//是否必答  OAIsOrNo T是 F否
	
	private String detail;//调查信息明细
	private String creater;//投票者

	// Constructors
	/** default constructor */
	public VOaSurveyDetail() {
	}
	/** minimal constructor */
	public VOaSurveyDetail(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public VOaSurveyDetail(
	 String no		
	,String  djid,String  chosetype,String  title,String  itemnames,Long  chosenum,Long  limitnum,String  thesign,String detail,String creater) {
	
	
		this.no = no;		
	
		this.djid= djid;
		this.chosetype= chosetype;
		this.title= title;
		this.itemnames= itemnames;
		this.chosenum= chosenum;
		this.limitnum= limitnum;
		this.thesign= thesign;	
		this.detail=detail;
		this.creater=creater;
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
		return this.thesign;
	}
	
	public void setThesign(String thesign) {
		this.thesign = thesign;
	}



	public OaOnlineItems newOaOnlineItems(){
		OaOnlineItems res = new OaOnlineItems();
  
		res.setNo(this.getNo());

		return res;
	}


	public void copy(VOaSurveyDetail other){
  
		this.setNo(other.getNo());
  
		this.djid= other.getDjid();  
		this.chosetype= other.getChosetype();  
		this.title= other.getTitle();  
		this.itemnames= other.getItemnames();  
		this.chosenum= other.getChosenum();  
		this.limitnum= other.getLimitnum();  
		this.thesign= other.getThesign();
		this.detail= other.getDetail();
	

	}
	
	public void copyNotNullProperty(VOaSurveyDetail other){
  
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
		if( other.getDetail() != null)
		    this.detail= other.getDetail();
		if( other.getCreater() != null)
            this.creater= other.getCreater();
	
		
	}
	
	public void clearProperties(){
  
		this.djid= null;  
		this.chosetype= null;  
		this.title= null;  
		this.itemnames= null;  
		this.chosenum= null;  
		this.limitnum= null;  
		this.thesign= null;
		this.detail= null;
		this.creater=null;
	
		
	}
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getCreater() {
        return creater;
    }
    public void setCreater(String creater) {
        this.creater = creater;
    }
}
