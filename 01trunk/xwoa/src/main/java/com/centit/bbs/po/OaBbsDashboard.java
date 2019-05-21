package com.centit.bbs.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBbsDashboard implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String layoutcode;  // 主键：PM + n*0 + 序号         其中PM是 Primary Module 缩写

	private String  unitcode;
	private String  layouttype;
	private Long  orderno;
	private String  creater;
	private Date  createtime;
	private String  openType;
	private String  approvals;
	private Date  approvaltime;
	private String  approvalremark;
	private Date  starttime;
	private Date  endtime;
	private Set<OaBbsDiscussion> oaBbsDiscussions = null;// new ArrayList<OaBbsDiscussion>();

	
	//bbs首页面统计用
	private Long todayThemeNum;//今日帖子总数
	private Long preThemeNum;//昨天帖子总数
	private Long themeNum;//帖子数
	
	
	private String layoutname;
	
	private String isdocontral;
    private Date starttimepm;
    private Date endtimepm;
	
	/**
	 *  用于列表显示管理员名字,暂存变量，不存数据库
	 */
	private String approvalNames;

	/**
	 *  开放时间的暂存变量
	 */
    private String starttimeTemp;
    private String endtimeTemp;
    private String starttimepmTemp;
    private String endtimepmTemp;
    
    
    private String isShowTime;//模块是否展示
    /**
     *  暂存变量，用于存放当前用户能否创建子版块的值，可以创建为“Y”，否则为“N”
     */
    private String isGranted;
    
    
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
    
    public String getIsGranted() {
        return isGranted;
    }
    public void setIsGranted(String isGranted) {
        this.isGranted = isGranted;
    }
    // Constructors
	/** default constructor */
	public OaBbsDashboard() {
	}
	/** minimal constructor */
	public OaBbsDashboard(
		String layoutcode		
		) {
	
	
		this.layoutcode = layoutcode;		
			
	}

/** full constructor */
	public OaBbsDashboard(
	 String layoutcode		
	,String  unitcode,String  layouttype,Long  orderno,String  creater,Date  createtime,
	String  openType,String  approvals,Date  approvaltime,String  approvalremark,Date  starttime,
	Date  endtime, String layoutname, String isdocontral, Date starttimepm, Date endtimepm) {
	
	
		this.layoutcode = layoutcode;		
	
		this.unitcode= unitcode;
		this.layouttype= layouttype;
		this.orderno= orderno;
		this.creater= creater;
		this.createtime= createtime;
		this.openType= openType;
		this.approvals= approvals;
		this.approvaltime= approvaltime;
		this.approvalremark= approvalremark;
		this.starttime= starttime;
		this.endtime= endtime;	
		this.layoutname = layoutname;
		this.isdocontral = isdocontral;
		this.starttimepm = starttimepm;
		this.endtimepm = endtimepm;
	}
	

  
	public String getLayoutcode() {
		return this.layoutcode;
	}

	public void setLayoutcode(String layoutcode) {
		this.layoutcode = layoutcode;
	}
	// Property accessors
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
  
	public String getLayouttype() {
		return this.layouttype;
	}
	
	public void setLayouttype(String layouttype) {
		this.layouttype = layouttype;
	}
  
	public Long getOrderno() {
		return this.orderno;
	}
	
	public void setOrderno(Long orderno) {
		this.orderno = orderno;
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
  
	public String getOpenType() {
		return this.openType;
	}
	
	public void setOpenType(String openType) {
		this.openType = openType;
	}
  
	public String getApprovals() {
		return this.approvals;
	}
	
	public void setApprovals(String approvals) {
		this.approvals = approvals;
	}
  
	public Date getApprovaltime() {
		return this.approvaltime;
	}
	
	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}
  
	public String getApprovalremark() {
		return this.approvalremark;
	}
	
	public void setApprovalremark(String approvalremark) {
		this.approvalremark = approvalremark;
	}
  
	public Date getStarttime() {
		return this.starttime;
	}
	
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
  
	public Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}


	public Set<OaBbsDiscussion> getOaBbsDiscussions(){
		if(this.oaBbsDiscussions==null)
			this.oaBbsDiscussions = new HashSet<OaBbsDiscussion>();
		return this.oaBbsDiscussions;
	}

	public void setOaBbsDiscussions(Set<OaBbsDiscussion> oaBbsDiscussions) {
		this.oaBbsDiscussions = oaBbsDiscussions;
	}	

	public void addOaBbsDiscussion(OaBbsDiscussion oaBbsDiscussion ){
		if (this.oaBbsDiscussions==null)
			this.oaBbsDiscussions = new HashSet<OaBbsDiscussion>();
		this.oaBbsDiscussions.add(oaBbsDiscussion);
	}
	
	public void removeOaBbsDiscussion(OaBbsDiscussion oaBbsDiscussion ){
		if (this.oaBbsDiscussions==null)
			return;
		this.oaBbsDiscussions.remove(oaBbsDiscussion);
	}
	
	public OaBbsDiscussion newOaBbsDiscussion(){
		OaBbsDiscussion res = new OaBbsDiscussion();
  
		res.setLayoutcode(this.getLayoutcode());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaBbsDiscussions(List<OaBbsDiscussion> oaBbsDiscussions) {
		List<OaBbsDiscussion> newObjs = new ArrayList<OaBbsDiscussion>();
		for(OaBbsDiscussion p :oaBbsDiscussions){
			if(p==null)
				continue;
			OaBbsDiscussion newdt = newOaBbsDiscussion();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaBbsDiscussion> oldObjs = new HashSet<OaBbsDiscussion>();
		oldObjs.addAll(getOaBbsDiscussions());
		
		for(Iterator<OaBbsDiscussion> it=oldObjs.iterator(); it.hasNext();){
			OaBbsDiscussion odt = it.next();
			found = false;
			for(OaBbsDiscussion newdt :newObjs){
				if(odt.getLayoutno().equals( newdt.getLayoutno())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaBbsDiscussion(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaBbsDiscussion newdt :newObjs){
			found = false;
			for(Iterator<OaBbsDiscussion> it=getOaBbsDiscussions().iterator();
			 it.hasNext();){
				OaBbsDiscussion odt = it.next();
				if(odt.getLayoutno().equals( newdt.getLayoutno())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaBbsDiscussion(newdt);
		} 	
	}	


	public void copy(OaBbsDashboard other){
  
		this.setLayoutcode(other.getLayoutcode());
  
		this.unitcode= other.getUnitcode();  
		this.layouttype= other.getLayouttype();  
		this.orderno= other.getOrderno();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.openType= other.getOpenType();  
		this.approvals= other.getApprovals();  
		this.approvaltime= other.getApprovaltime();  
		this.approvalremark= other.getApprovalremark();  
		this.starttime= other.getStarttime();  
		this.endtime= other.getEndtime();
		this.layoutname = other.getLayoutname();
		this.isdocontral = other.getIsdocontral();
		this.starttimepm = other.getStarttimepm();
		this.endtimepm = other.getEndtimepm();
	    this.state = other.getState();
		this.oaBbsDiscussions = other.getOaBbsDiscussions();
	}
	
	public void copyNotNullProperty(OaBbsDashboard other){
  
	if( other.getLayoutcode() != null)
		this.setLayoutcode(other.getLayoutcode());
  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getLayouttype() != null)
			this.layouttype= other.getLayouttype();  
		if( other.getOrderno() != null)
			this.orderno= other.getOrderno();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getOpenType() != null)
			this.openType= other.getOpenType();  
		if( other.getApprovals() != null)
			this.approvals= other.getApprovals();  
		if( other.getApprovaltime() != null)
			this.approvaltime= other.getApprovaltime();  
		if( other.getApprovalremark() != null)
			this.approvalremark= other.getApprovalremark();  
		if( other.getStarttime() != null)
			this.starttime= other.getStarttime();  
		if( other.getEndtime() != null)
			this.endtime= other.getEndtime();
		if( other.getLayoutname() != null)
		    this.layoutname = other.getLayoutname();
		if( other.getIsdocontral() != null)
		    this.isdocontral = other.getIsdocontral();
		if( other.getStarttimepm() != null)
		    this.starttimepm = other.getStarttimepm();
		if( other.getEndtimepm() != null)
		    this.endtimepm = other.getEndtimepm();
	    if( other.getState() != null)
	        this.state = other.getState();
		this.oaBbsDiscussions = other.getOaBbsDiscussions();
	}
	
	public void clearProperties(){
  
		this.unitcode= null;  
		this.layouttype= null;  
		this.orderno= null;  
		this.creater= null;  
		this.createtime= null;  
		this.openType= null;  
		this.approvals= null;  
		this.approvaltime= null;  
		this.approvalremark= null;  
		this.starttime= null;  
		this.endtime= null;
		this.layoutname = null;
		this.isdocontral = null;
		this.starttimepm = null;
		this.endtimepm = null;
	    this.state = null;
	    
		this.oaBbsDiscussions = new HashSet<OaBbsDiscussion>();
	}
    public Long getTodayThemeNum() {
        return todayThemeNum;
    }
    public void setTodayThemeNum(Long todayThemeNum) {
        this.todayThemeNum = todayThemeNum;
    }
    public Long getPreThemeNum() {
        return preThemeNum;
    }
    public void setPreThemeNum(Long preThemeNum) {
        this.preThemeNum = preThemeNum;
    }
    public Long getThemeNum() {
        return themeNum;
    }
    public void setThemeNum(Long themeNum) {
        this.themeNum = themeNum;
    }
	
    public String getLayoutname() {
        return layoutname;
    }
    public void setLayoutname(String layoutname) {
        this.layoutname = layoutname;
    }
    public String getApprovalNames() {
        return approvalNames;
    }
    public void setApprovalNames(String approvalNames) {
        this.approvalNames = approvalNames;
    }
    
    public String getIsdocontral() {
        return isdocontral;
    }
    public void setIsdocontral(String isdocontral) {
        this.isdocontral = isdocontral;
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
    /**
     * 根据管理员代码设置管理员名称
     */
    public void convertApprovalNames(){
        
        if(StringUtils.isNotBlank(this.getApprovals())){
            String[] usercodes = this.getApprovals().split(",");
            
            String userNames = "";
            for(int i = 0; i < usercodes.length; i++){
                userNames += CodeRepositoryUtil.getValue("usercode", usercodes[i]) + ",";
            }
            
            userNames = userNames.substring(0, userNames.length() -1);
            
            this.setApprovalNames(userNames);
        }
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
