package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 自助餐申请接口数据
 * @author gy
 * @create 2017年2月7日
 * @version
 */

public class OabookBuffetApplyMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户唯一ID
	 */
	@Expose(serialize = false) 
	private String userid;
	/**
	 * 标题
	 */
	@Expose(serialize = false) 
    private String transAffairName;
	/**
	 * 申请时间
	 */
	@Expose(serialize = false) 
    private String applydate;
	/**
	 * 申请部门
	 */
	@Expose(serialize = false) 
    private String applyuser;
	/**
	 * 来访人员
	 */
	@Expose(serialize = false) 
    private String visitors;
	/**
	 * 来访人数
	 */
	@Expose(serialize = false) 
	private String visitorsnum;
	
	
	/**
	 * 来访目的
	 */
    @Expose 
    private String remark_content;
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getTransAffairName() {
        return transAffairName;
    }
    public void setTransAffairName(String transAffairName) {
        this.transAffairName = transAffairName;
    }
    public String getApplydate() {
        return applydate;
    }
    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }
    
    public String getApplyuser() {
        return applyuser;
    }
    public void setApplyuser(String applyuser) {
        this.applyuser = applyuser;
    }
    public String getVisitors() {
        return visitors;
    }
    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }
    public String getVisitorsnum() {
        return visitorsnum;
    }
    public void setVisitorsnum(String visitorsnum) {
        this.visitorsnum = visitorsnum;
    }
    public String getRemark_content() {
        return remark_content;
    }
    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }
	
    
	
	
  
}
