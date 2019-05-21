package com.centit.mip.po;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 会议资料
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2017年2月8日
 * @version
 */

public class OaMeetingmaterialMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	@Expose(serialize = false) 
	private String userid;
	@Expose(serialize = false) 
    private String keyword;
	@Expose(serialize = false) 
    private String currentdatetime;
	@Expose(serialize = false) 
    private String pagesize;
	@Expose(serialize = false) 
    private String starttime;
	@Expose(serialize = false) 
    private String endtime;
	@Expose(serialize = false)
	private String qpfileurl;
	@Expose        
    private String djId;
	@Expose        
    private String meetingName;//会议名称
	@Expose
    private String meetingTime;//会议时间
	@Expose
    private String meetingAddress;//会议地点
	@Expose
	private String createtime;	//创建时间
	

	@Expose
    @Since(2.0)
	private String meetingAgenda; //创建时间,
	@Expose
    @Since(2.0)
    private String meetingAttendees; //参会人员,
	@Expose
    @Since(2.0)
    private String meetingUnitNames; //参会单位,
	@Expose
    @Since(2.0)
    private String meetingComein; //外来单位人员  
	
	@Expose
    @Since(2.0)
    private String meetingComeinDeps; //外来单位人员  
	
	@Expose
    @Since(2.0)
	private String meetingRemark;//会议备注说明
	
	 @Expose
     @Since(2.0)
     private String stuffId;//附件材料主键id
	 
     @Expose
     @Since(2.0)
     private String remark;//备注

     @Expose  @Since(2.0)
     private String stuffPath;// 材料地址
	
	@Expose @Since(2.0)
	 public List<OaMeetingmaterialinfosMIP> materials = new ArrayList<OaMeetingmaterialinfosMIP>();
	
	 /**
     * 会议资料材料
     * TODO Class description should be added
     * 
     * @author lq
     * @create 2017年2月8日
     * @version
     */
    public static class OaMeetingmaterialinfosMIP {
        @Expose
        @Since(2.0)
        private String djId;
        
        @Expose
        @Since(2.0)
        private String usercode;
        
        @Expose
        @Since(2.0)
        private String stuffId;//附件材料主键id
        
        @Expose
        @Since(2.0)
        private String stuffName;//附件材料名称

        @Expose  
        @Since(2.0)
        private String stuffPath;// 材料地址
        
        @Expose  
        @Since(2.0)
        private String isback;
        
        @Expose  
        @Since(2.0)
        private String remark;//备注

        public String getStuffId() {
            return stuffId;
        }

        public void setStuffId(String stuffId) {
            this.stuffId = stuffId;
        }

        public String getStuffName() {
            return stuffName;
        }

        public void setStuffName(String stuffName) {
            this.stuffName = stuffName;
        }

        public String getStuffPath() {
            return stuffPath;
        }

        public void setStuffPath(String stuffPath) {
            this.stuffPath = stuffPath;
        }

        public String getDjId() {
            return djId;
        }

        public void setDjId(String djId) {
            this.djId = djId;
        }

        public String getUsercode() {
            return usercode;
        }

        public void setUsercode(String usercode) {
            this.usercode = usercode;
        }

        public String getIsback() {
            return isback;
        }

        public void setIsback(String isback) {
            this.isback = isback;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        
     

        
        
    }

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

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getQpfileurl() {
        return qpfileurl;
    }

    public void setQpfileurl(String qpfileurl) {
        this.qpfileurl = qpfileurl;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMeetingAgenda() {
        return meetingAgenda;
    }

    public void setMeetingAgenda(String meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public String getMeetingAttendees() {
        return meetingAttendees;
    }

    public void setMeetingAttendees(String meetingAttendees) {
        this.meetingAttendees = meetingAttendees;
    }

    public String getMeetingUnitNames() {
        return meetingUnitNames;
    }

    public void setMeetingUnitNames(String meetingUnitNames) {
        this.meetingUnitNames = meetingUnitNames;
    }

    public String getMeetingComein() {
        return meetingComein;
    }

    public void setMeetingComein(String meetingComein) {
        this.meetingComein = meetingComein;
    }

    public String getMeetingRemark() {
        return meetingRemark;
    }

    public void setMeetingRemark(String meetingRemark) {
        this.meetingRemark = meetingRemark;
    }

    public String getStuffId() {
        return stuffId;
    }

    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStuffPath() {
        return stuffPath;
    }

    public void setStuffPath(String stuffPath) {
        this.stuffPath = stuffPath;
    }

    public List<OaMeetingmaterialinfosMIP> getMaterials() {
        return materials;
    }

    public void setMaterials(List<OaMeetingmaterialinfosMIP> materials) {
        this.materials = materials;
    }

    public String getMeetingComeinDeps() {
        return meetingComeinDeps;
    }

    public void setMeetingComeinDeps(String meetingComeinDeps) {
        this.meetingComeinDeps = meetingComeinDeps;
    }

    
    
	

  
}
