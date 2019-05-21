package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 推送资讯类交互结果信息接口数据
 * @author gy
 * @create 2017年2月9日
 * @version
 */

public class OasendAttendInfoResultMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户唯一ID
	 */
	@Expose
    @Since(2.0)
	private String userid;
	/**
	 * 资讯主键
	 */
	@Expose
    @Since(2.0) 
    private String informationid;
	/**
	 * 交互时间
	 */
	@Expose
    @Since(2.0) 
	private String attendTime;
	/**
	 * 交互结果
	 */
	@Expose
    @Since(2.0) 
	private String attendResult;
    /**
     * 备注
     */
	@Expose
    @Since(2.0)
    private String remark;
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getInformationid() {
        return informationid;
    }
    public void setInformationid(String informationid) {
        this.informationid = informationid;
    }
    public String getAttendTime() {
        return attendTime;
    }
    public void setAttendTime(String attendTime) {
        this.attendTime = attendTime;
    }
    public String getAttendResult() {
        return attendResult;
    }
    public void setAttendResult(String attendResult) {
        this.attendResult = attendResult;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
   
	
    
	
	
  
}
