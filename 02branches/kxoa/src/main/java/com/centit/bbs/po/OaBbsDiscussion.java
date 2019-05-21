package com.centit.bbs.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.centit.support.utils.DatetimeOpt;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBbsDiscussion implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String layoutno;  // 主键：SM + n*0 + 序号         其中SM是 Son(或者Sub) Module 缩写

	private String  layoutcode;
	private String  sublayouttitle;
	private String  showpicturename;
	private byte[]  showpicture;
	private Long  orderno;
	private Long  subjectnum;
	private Long  postsnum;
	private Date  lastmodifytime;
	private String  creater;
	private Date  createtime;
	private String  approvalresults;
	private String  approval;
	private Date  approvaltime;
	private String  openType;
	private String  isneed;
	
	private String isdocontral;
	private Date starttime;
	private Date endtime;
	private Date starttimepm;
	private Date endtimepm;
	
	// 开放时间的暂存变量
	private String starttimeTemp;
    private String endtimeTemp;
    private String starttimepmTemp;
    private String endtimepmTemp;
    
    
    private String isShowTime;//模块是否展示
    private Long todayThemenum;//今日发帖数
    
   private String colorOftitle;//子模块名称色标
   
   private String openTimeTip;//开放时间提示
   private String openTimeTipEnd;//开放时间提示
   /**
    * 状态N-未提交 R-启用 P-停用 D-删除
    */
   private String state;
   
   
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getStarttimeTemp() {
        return starttimeTemp;
    }
    public void setStarttimeTemp(String starttimeTemp) {
        this.starttimeTemp = starttimeTemp;
    }
    public String getEndtimeTemp() {
        return endtimeTemp;
    }
    public void setEndtimeTemp(String endtimeTemp) {
        this.endtimeTemp = endtimeTemp;
    }
    public String getStarttimepmTemp() {
        return starttimepmTemp;
    }
    public void setStarttimepmTemp(String starttimepmTemp) {
        this.starttimepmTemp = starttimepmTemp;
    }
    public String getEndtimepmTemp() {
        return endtimepmTemp;
    }
    public void setEndtimepmTemp(String endtimepmTemp) {
        this.endtimepmTemp = endtimepmTemp;
    }

    private Set<OaBbsTheme> oaBbsThemes = null;// new ArrayList<OaBbsTheme>();
	
	
	private OaBbsDashboard oaBbsDashboard;
	
	public OaBbsDashboard getOaBbsDashboard() {
        return oaBbsDashboard;
    }
    public void setOaBbsDashboard(OaBbsDashboard oaBbsDashboard) {
        this.oaBbsDashboard = oaBbsDashboard;
    }
    // Constructors
	/** default constructor */
	public OaBbsDiscussion() {
	}
	/** minimal constructor */
	public OaBbsDiscussion(
		String layoutno		
		,String  layoutcode) {
	
	
		this.layoutno = layoutno;		
	
		this.layoutcode= layoutcode; 		
	}

/** full constructor */
	public OaBbsDiscussion(
	 String layoutno		
	,String  layoutcode,String  sublayouttitle,String  showpicturename,byte[]  showpicture,Long  orderno,
	Long  subjectnum,Long  postsnum,Date  lastmodifytime,String  creater,Date  createtime,String  approvalresults,
	String  approval,Date  approvaltime,String  openType,String  isneed,String isdocontral,Date starttime,
	Date endtime, Date starttimepm, Date endtimepm,String colorOftitle) {
	
	
		this.layoutno = layoutno;		
	
		this.layoutcode= layoutcode;
		this.sublayouttitle= sublayouttitle;
		this.showpicturename= showpicturename;
		this.showpicture= showpicture;
		this.orderno= orderno;
		this.subjectnum= subjectnum;
		this.postsnum= postsnum;
		this.lastmodifytime= lastmodifytime;
		this.creater= creater;
		this.createtime= createtime;
		this.approvalresults= approvalresults;
		this.approval= approval;
		this.approvaltime= approvaltime;
		this.openType= openType;
		this.isneed= isneed;	
		this.isdocontral = isdocontral;
		this.starttime = starttime;
		this.endtime = endtime;
		this.starttimepm = starttimepm;
		this.endtimepm = endtimepm;
		this.colorOftitle=colorOftitle;
	}
	

  
	public String getLayoutno() {
		return this.layoutno;
	}

	public void setLayoutno(String layoutno) {
		this.layoutno = layoutno;
	}
	// Property accessors
  
	public String getLayoutcode() {
		return this.layoutcode;
	}
	
	public void setLayoutcode(String layoutcode) {
		this.layoutcode = layoutcode;
	}
  
	public String getSublayouttitle() {
		return this.sublayouttitle;
	}
	
	public void setSublayouttitle(String sublayouttitle) {
		this.sublayouttitle = sublayouttitle;
	}
  
	public String getShowpicturename() {
		return this.showpicturename;
	}
	
	public void setShowpicturename(String showpicturename) {
		this.showpicturename = showpicturename;
	}
  
	public byte[] getShowpicture() {
		return this.showpicture;
	}
	
	public void setShowpicture(byte[] showpicture) {
		this.showpicture = showpicture;
	}
  
	public Long getOrderno() {
		return this.orderno;
	}
	
	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}
  
	public Long getSubjectnum() {
		return this.subjectnum;
	}
	
	public void setSubjectnum(Long subjectnum) {
		this.subjectnum = subjectnum;
	}
  
	public Long getPostsnum() {
		return this.postsnum;
	}
	
	public void setPostsnum(Long postsnum) {
		this.postsnum = postsnum;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
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
  
	public String getApprovalresults() {
		return this.approvalresults;
	}
	
	public void setApprovalresults(String approvalresults) {
		this.approvalresults = approvalresults;
	}
  
	public String getApproval() {
		return this.approval;
	}
	
	public void setApproval(String approval) {
		this.approval = approval;
	}
  
	public Date getApprovaltime() {
		return this.approvaltime;
	}
	
	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}
  
	public String getOpenType() {
		return this.openType;
	}
	
	public void setOpenType(String openType) {
		this.openType = openType;
	}
  
	public String getIsneed() {
		return this.isneed;
	}
	
	public void setIsneed(String isneed) {
		this.isneed = isneed;
	}
	
	public String getIsdocontral() {
        return isdocontral;
    }
    public void setIsdocontral(String isdocontral) {
        this.isdocontral = isdocontral;
    }
    public Date getStarttime() {
        return starttime;
    }
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return endtime;
    }
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    public Date getStarttimepm() {
        return starttimepm;
    }
    public void setStarttimepm(Date starttimepm) {
        this.starttimepm = starttimepm;
    }
    public Date getEndtimepm() {
        return endtimepm;
    }
    public void setEndtimepm(Date endtimepm) {
        this.endtimepm = endtimepm;
    }
    
    public Set<OaBbsTheme> getOaBbsThemes(){
		if(this.oaBbsThemes==null)
			this.oaBbsThemes = new HashSet<OaBbsTheme>();
		return this.oaBbsThemes;
	}

	public void setOaBbsThemes(Set<OaBbsTheme> oaBbsThemes) {
		this.oaBbsThemes = oaBbsThemes;
	}	

	public void addOaBbsTheme(OaBbsTheme oaBbsTheme ){
		if (this.oaBbsThemes==null)
			this.oaBbsThemes = new HashSet<OaBbsTheme>();
		this.oaBbsThemes.add(oaBbsTheme);
	}
	
	public void removeOaBbsTheme(OaBbsTheme oaBbsTheme ){
		if (this.oaBbsThemes==null)
			return;
		this.oaBbsThemes.remove(oaBbsTheme);
	}
	
	public OaBbsTheme newOaBbsTheme(){
		OaBbsTheme res = new OaBbsTheme();
  
		res.setLayoutno(this.getLayoutno());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaBbsThemes(List<OaBbsTheme> oaBbsThemes) {
		List<OaBbsTheme> newObjs = new ArrayList<OaBbsTheme>();
		for(OaBbsTheme p :oaBbsThemes){
			if(p==null)
				continue;
			OaBbsTheme newdt = newOaBbsTheme();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaBbsTheme> oldObjs = new HashSet<OaBbsTheme>();
		oldObjs.addAll(getOaBbsThemes());
		
		for(Iterator<OaBbsTheme> it=oldObjs.iterator(); it.hasNext();){
			OaBbsTheme odt = it.next();
			found = false;
			for(OaBbsTheme newdt :newObjs){
				if(odt.getThemeno().equals( newdt.getThemeno())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaBbsTheme(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaBbsTheme newdt :newObjs){
			found = false;
			for(Iterator<OaBbsTheme> it=getOaBbsThemes().iterator();
			 it.hasNext();){
				OaBbsTheme odt = it.next();
				if(odt.getThemeno().equals( newdt.getThemeno())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaBbsTheme(newdt);
		} 	
	}	


	public void copy(OaBbsDiscussion other){
  
		this.setLayoutno(other.getLayoutno());
  
		this.layoutcode= other.getLayoutcode();  
		this.sublayouttitle= other.getSublayouttitle();  
		this.showpicturename= other.getShowpicturename();  
		this.showpicture= other.getShowpicture();  
		this.orderno= other.getOrderno();  
		this.subjectnum= other.getSubjectnum();  
		this.postsnum= other.getPostsnum();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.approvalresults= other.getApprovalresults();  
		this.approval= other.getApproval();  
		this.approvaltime= other.getApprovaltime();  
		this.openType= other.getOpenType();  
		this.isneed= other.getIsneed();
	
		this.oaBbsThemes = other.getOaBbsThemes();
		this.isdocontral = other.getIsdocontral();
		this.starttime = other.getStarttime();
		this.endtime = other.getEndtime();
		this.starttimepm = other.getStarttimepm();
		this.endtimepm = other.getEndtimepm();
		this.colorOftitle=other.getColorOftitle();
		this.state = other.getState();
	}
	
	public void copyNotNullProperty(OaBbsDiscussion other){
  
	if( other.getLayoutno() != null)
		this.setLayoutno(other.getLayoutno());
  
		if( other.getLayoutcode() != null)
			this.layoutcode= other.getLayoutcode();  
		if( other.getSublayouttitle() != null)
			this.sublayouttitle= other.getSublayouttitle();  
		if( other.getShowpicturename() != null)
			this.showpicturename= other.getShowpicturename();  
		if( other.getShowpicture() != null)
			this.showpicture= other.getShowpicture();  
		if( other.getOrderno() != null)
			this.orderno= other.getOrderno();  
		if( other.getSubjectnum() != null)
			this.subjectnum= other.getSubjectnum();  
		if( other.getPostsnum() != null)
			this.postsnum= other.getPostsnum();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getApprovalresults() != null)
			this.approvalresults= other.getApprovalresults();  
		if( other.getApproval() != null)
			this.approval= other.getApproval();  
		if( other.getApprovaltime() != null)
			this.approvaltime= other.getApprovaltime();  
		if( other.getOpenType() != null)
			this.openType= other.getOpenType();  
		if( other.getIsneed() != null)
			this.isneed= other.getIsneed();
		if( other.getIsdocontral() != null)
		    this.isdocontral = other.getIsdocontral();
		if( other.getStarttime() != null)
		    this.starttime = other.getStarttime();
		if( other.getEndtime() != null)
		    this.endtime = other.getEndtime();
		if( other.getStarttimepm() != null)
		    this.starttimepm = other.getStarttimepm();
		if( other.getEndtimepm() != null)
		    this.endtimepm = other.getEndtimepm();
	
		if(other.getColorOftitle()!=null){
		    this.colorOftitle=other.getColorOftitle();
		}
		if(other.getState()!=null){
		    this.state = other.getState();
		}
		this.oaBbsThemes = other.getOaBbsThemes();
	}
	
	public void clearProperties(){
  
		this.layoutcode= null;  
		this.sublayouttitle= null;  
		this.showpicturename= null;  
		this.showpicture= null;  
		this.orderno= null;  
		this.subjectnum= null;  
		this.postsnum= null;  
		this.lastmodifytime= null;  
		this.creater= null;  
		this.createtime= null;  
		this.approvalresults= null;  
		this.approval= null;  
		this.approvaltime= null;  
		this.openType= null;  
		this.isneed= null;
		this.isdocontral = null;
		this.starttime = null;
		this.endtime = null;
		this.starttimepm = null;
		this.endtimepm = null;
	    this.state = null;
	    
		this.oaBbsThemes = new HashSet<OaBbsTheme>();
	}
	
	/**
	 *  根据页面的string型暂存变量转成Date型存入开放时间变量中
	 */
	public void setOpentime(){
	    
	    try{
	        if(StringUtils.isNotBlank(starttimeTemp)){
	            setStarttime(DatetimeOpt.convertStringToDate(starttimeTemp, "yyyy-MM-dd HH:mm"));
	        }
	        
	        if(StringUtils.isNotBlank(endtimeTemp)){
                setEndtime(DatetimeOpt.convertStringToDate(endtimeTemp, "yyyy-MM-dd HH:mm"));
            }
	        
	        if(StringUtils.isNotBlank(starttimepmTemp)){
                setStarttimepm(DatetimeOpt.convertStringToDate(starttimepmTemp, "yyyy-MM-dd HH:mm"));
            }
	        
	        if(StringUtils.isNotBlank(endtimepmTemp)){
	            setEndtimepm(DatetimeOpt.convertStringToDate(endtimepmTemp, "yyyy-MM-dd HH:mm"));
            }
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	}
    public String getIsShowTime() {
        return isShowTime;
    }
    public void setIsShowTime(String isShowTime) {
        this.isShowTime = isShowTime;
    }
    public Long getTodayThemenum() {
        return todayThemenum;
    }
    public void setTodayThemenum(Long todayThemenum) {
        this.todayThemenum = todayThemenum;
    }
    public String getColorOftitle() {
        return colorOftitle;
    }
    public void setColorOftitle(String colorOftitle) {
        this.colorOftitle = colorOftitle;
    }
    public String getOpenTimeTip() {
        return openTimeTip;
    }
    public void setOpenTimeTip(String openTimeTip) {
        this.openTimeTip = openTimeTip;
    }
    public String getOpenTimeTipEnd() {
        return openTimeTipEnd;
    }
    public void setOpenTimeTipEnd(String openTimeTipEnd) {
        this.openTimeTipEnd = openTimeTipEnd;
    }
}
