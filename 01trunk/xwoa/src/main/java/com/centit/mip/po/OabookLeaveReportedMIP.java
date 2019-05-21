package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 外出请假报备单接口数据
 * @author gy
 * @create 2017年2月8日
 * @version
 */

public class OabookLeaveReportedMIP implements java.io.Serializable {
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
	 * 离开时间
	 */
	@Expose(serialize = false) 
    private String endtime;
	/**
     * 返回时间
     */
    @Expose(serialize = false) 
    private String applydate;
	/**
	 * 所在部门
	 */
	@Expose(serialize = false) 
    private String applyuser;
	/**
     * 职位
     */
    @Expose(serialize = false) 
    private String postleve;
	/**
	 * 公示公告内容
	 */
    @Expose 
    private String remark_content;
    /**
     * 外出地点
     */
    @Expose 
    private String leaveaddress;
    /**
     * 联系方式
     */
    @Expose 
    private String telephone;
    /**
     * 备注
     */
    @Expose 
    private String remark;
    
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
 
    public String getRemark_content() {
        return remark_content;
    }
    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getPostleve() {
        return postleve;
    }
    public void setPostleve(String postleve) {
        this.postleve = postleve;
    }
    public String getLeaveaddress() {
        return leaveaddress;
    }
    public void setLeaveaddress(String leaveaddress) {
        this.leaveaddress = leaveaddress;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
   
	
    
	
	
  
}
