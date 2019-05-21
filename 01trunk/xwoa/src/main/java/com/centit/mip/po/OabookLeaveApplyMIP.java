package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 请假条申请接口数据
 * @author gy
 * @create 2017年2月6日
 * @version
 */

public class OabookLeaveApplyMIP implements java.io.Serializable {
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
	 * 预计开始时间
	 */
	@Expose(serialize = false) 
    private String applydate;
	/**
	 * 预计结束时间
	 */
	@Expose(serialize = false) 
    private String endtime;
	/**
	 * 假期天数
	 */
	@Expose(serialize = false) 
    private String leaveNum;
	
	
	/**
	 * 请假事由
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
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getLeaveNum() {
        return leaveNum;
    }
    public void setLeaveNum(String leaveNum) {
        this.leaveNum = leaveNum;
    }
   /* public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }*/
    public String getRemark_content() {
        return remark_content;
    }
    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }
	
    
	
	
  
}
