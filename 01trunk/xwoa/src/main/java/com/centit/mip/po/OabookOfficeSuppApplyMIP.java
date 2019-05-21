package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 物品申领接口数据
 * @author gy
 * @create 2017年2月8日
 * @version
 */

public class OabookOfficeSuppApplyMIP implements java.io.Serializable {
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
	 * 事由
	 */
    @Expose 
    private String remark_content;
    
    /**
     * 物品
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	
    
	
	
  
}
