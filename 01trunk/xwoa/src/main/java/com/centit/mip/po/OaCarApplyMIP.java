package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 车辆申请接口数据
 * @author lq
 * @create 2016年5月12日
 * @version
 */

public class OaCarApplyMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户唯一ID
	 */
	@Expose(serialize = false) 
	private String userid;
	/**
	 * 关键字
	 */
	@Expose(serialize = false) 
    private String keyword;
	/**
	 * 分页-开始时间
	 */
	@Expose(serialize = false) 
    private String currentdatetime;
	/**
	 * 页数
	 */
	@Expose(serialize = false) 
    private String pagesize;
	/**
	 * 列表类型
	 * 枚举 0 自己所有的  1全部（自己所有+别人审核通过的）
	 */
	@Expose(serialize = false) 
    private String type;
	
	/**
	 * 开始时间
	 */
	@Expose 
    private String starttime;
	/**
	 * 结束时间
	 */
    @Expose 
    private String endtime;
	/**
	 * 实例id
	 */
	@Expose        
    private String djId;
	/**
	 * 申请人id
	 */
	@Expose        
    private String applicantid;
	/**
	 * 申请人姓名
	 */
	@Expose
    private String applicant;
	/**
	 * 标题
	 */
	@Expose
    private String title;
	/**
	 * 申请状态
	 */
	@Expose
	private String state;
	/**
	 * 乘车人数
	 */
	@Expose
    @Since(2.0)
	private String persionsNum;
	/***
	 * 联系电话
	 */
	@Expose
    @Since(2.0)
	private String telephone; 
	/**
	 * 用车事由
	 */
	@Expose
    @Since(2.0)
    private String reqRemark; 
	/**
     * 行车路线
     */
    @Expose
    @Since(2.0)
    private String path; 
	
    /**
	 * 备注
	 */
	@Expose
    @Since(2.0)
    private String remark;
	/**
	 * 车牌号
	 */
	@Expose
    @Since(2.0)
    private String carno; 
	/**
	 * 司机
	 */
	@Expose
    @Since(2.0)
	private String driver;
	/**
	 * 司机联系电话号
	 */
	@Expose
    @Since(2.0)
    private String drTelephone;
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getCurrentdatetime() {
        return currentdatetime;
    }
    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }
    public String getPagesize() {
        return pagesize;
    }
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getApplicantid() {
        return applicantid;
    }
    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }
    public String getApplicant() {
        return applicant;
    }
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPersionsNum() {
        return persionsNum;
    }
    public void setPersionsNum(String persionsNum) {
        this.persionsNum = persionsNum;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getReqRemark() {
        return reqRemark;
    }
    public void setReqRemark(String reqRemark) {
        this.reqRemark = reqRemark;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCarno() {
        return carno;
    }
    public void setCarno(String carno) {
        this.carno = carno;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getDrTelephone() {
        return drTelephone;
    }
    public void setDrTelephone(String drTelephone) {
        this.drTelephone = drTelephone;
    } 
    

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
	
	
  
}
