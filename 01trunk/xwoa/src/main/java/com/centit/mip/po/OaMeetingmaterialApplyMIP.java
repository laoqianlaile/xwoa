package com.centit.mip.po;


import java.util.ArrayList;
import java.util.List;

import com.centit.mip.po.OaMeetingmaterialMIP.OaMeetingmaterialinfosMIP;
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

public class OaMeetingmaterialApplyMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	
	//一些查询条件
	@Expose
    @Since(2.0)
	private String userid;
	@Expose
    @Since(2.0)
    private String keyword;
	@Expose
    @Since(2.0)
    private String currentDateTime;
	@Expose
    @Since(2.0) 
    private String pageSize;
	@Expose
    @Since(2.0)
    private String startTime;
	@Expose
    @Since(2.0)
    private String endTime;
	@Expose
    @Since(2.0)
    private String pagesize;//页数
	

	//议题
	@Expose
    @Since(2.0)
    private String djId;//议题主键
	@Expose
    @Since(2.0)
    private String meetingName;//议题名称
	@Expose
    @Since(2.0)
    private String meetingAttendeescodes;//议题参会人员
	@Expose
    @Since(2.0)
	private String meetingAttendees;//议题参会人员
	@Expose
    @Since(2.0)
	private String meetingAddress;//议题召开地点
	@Expose
    @Since(2.0)
    private String meetingTime;//议题召开时间
	@Expose
	@Since(2.0)
    private String reportName;//汇报人
	@Expose
    @Since(2.0)
    private String attendUnitName;//参会单位
	@Expose
    @Since(2.0)
    private String foreignUserName;//外来人员
    @Expose
    @Since(2.0)
    private String foreignUnitName;//外来单位
	
    @Expose
    @Since(2.0)
    private long orderId;//会议关联议程排序号
	@Expose
    @Since(2.0)
    public List<OaMeetingmaterialinfosMIP> materials = new ArrayList<OaMeetingmaterialinfosMIP>();//议题下的材料列表
	
	//会议
	@Expose
    @Since(2.0)
	private String mId;//会议主键
	@Expose
    @Since(2.0)
    private String meetApplyName;//会议名称
	@Expose
    @Since(2.0)
    private String meetApplyAddress;//会议召开地点
	@Expose
    @Since(2.0)
    private String meetApplyTime;//会议召开时间
	
	@Expose
    @Since(2.0)
	private String attendLeaderCode;//参会领导
	@Expose
	@Since(2.0)
	private String attendLeaderName;//参会领导
    
	@Expose
    @Since(2.0)
    private String remark;//备注
	
	@Expose
    @Since(2.0)
	public List<OaAgendainfosMIP> agendaInfos = new ArrayList<OaAgendainfosMIP>();//会议下的议程列表
	
 /*   //材料
    @Expose(serialize = false)
    private String qpfileurl;
    @Expose
    @Since(2.0)
    private String stuffId;//附件材料主键id
    @Expose  @Since(2.0)
    private String stuffPath;// 材料地址
    
    
    @Expose
    @Since(2.0)
    private String remark;//备注
*/   
    
   
  
   
   
    /**
    * 会议资料材料
    * TODO Class description should be added
    * 
    * @author wqq
    * @create 2018年5月17日
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
   
   /**
    * 会议列表信息
    * TODO Class description should be added
    * 
    * @author wqq
    * @create 2018年5月17日
    * @version
    */
   public static class OaAgendainfosMIP {
       @Expose
       @Since(2.0)
       private String djId;
       
       @Expose
       @Since(2.0)
       private String meetingName;//议题名称
       
       @Expose
       @Since(2.0)
       private String meetingAttendeescodes;//议题参会人员
     
       
       @Expose
       @Since(2.0)
       private String meetingAttendees;//议题参会人员
       
       @Expose
       @Since(2.0)
       private String reportName;//议题汇报人
       
       public String getMeetingAttendees() {
           return meetingAttendees;
       }
       public void setMeetingAttendees(String meetingAttendees) {
            this.meetingAttendees = meetingAttendees;
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
       public String getMeetingAttendeescodes() {
           return meetingAttendeescodes;
       }
       public void setMeetingAttendeescodes(String meetingAttendeescodes) {
           this.meetingAttendeescodes = meetingAttendeescodes;
       }
       public String getReportName() {
           return reportName;
       }
       public void setReportName(String reportName) {
           this.reportName = reportName;
       }
       
   }
   
   
   
    public String getPagesize() {
        return pagesize;
    }
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
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
    public String getCurrentDateTime() {
        return currentDateTime;
    }
    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
    public String getPageSize() {
        return pageSize;
    }
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getmId() {
        return mId;
    }
    public void setmId(String mId) {
        this.mId = mId;
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
    public String getMeetingAttendeescodes() {
        return meetingAttendeescodes;
    }
    public void setMeetingAttendeescodes(String meetingAttendeescodes) {
        this.meetingAttendeescodes = meetingAttendeescodes;
    }
    public String getMeetingTime() {
        return meetingTime;
    }
    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }
    public String getMeetApplyName() {
        return meetApplyName;
    }
    public void setMeetApplyName(String meetApplyName) {
        this.meetApplyName = meetApplyName;
    }
    public String getMeetApplyAddress() {
        return meetApplyAddress;
    }
    public void setMeetApplyAddress(String meetApplyAddress) {
        this.meetApplyAddress = meetApplyAddress;
    }
    public String getMeetApplyTime() {
        return meetApplyTime;
    }
    public void setMeetApplyTime(String meetApplyTime) {
        this.meetApplyTime = meetApplyTime;
    }
   public String getAttendUnitName() {
        return attendUnitName;
    }
    public void setAttendUnitName(String attendUnitName) {
        this.attendUnitName = attendUnitName;
    }
    public String getForeignUserName() {
        return foreignUserName;
    }
    public void setForeignUserName(String foreignUserName) {
        this.foreignUserName = foreignUserName;
    }
    public String getForeignUnitName() {
        return foreignUnitName;
    }
    public void setForeignUnitName(String foreignUnitName) {
        this.foreignUnitName = foreignUnitName;
    }
    /* public String getQpfileurl() {
        return qpfileurl;
    }
    public void setQpfileurl(String qpfileurl) {
        this.qpfileurl = qpfileurl;
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
    }*/
   
    public String getMeetingAddress() {
        return meetingAddress;
    }
    public String getMeetingAttendees() {
        return meetingAttendees;
    }
    public void setMeetingAttendees(String meetingAttendees) {
        this.meetingAttendees = meetingAttendees;
    }
    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public String getAttendLeaderCode() {
        return attendLeaderCode;
    }
    public void setAttendLeaderCode(String attendLeaderCode) {
        this.attendLeaderCode = attendLeaderCode;
    }
    public String getAttendLeaderName() {
        return attendLeaderName;
    }
    public void setAttendLeaderName(String attendLeaderName) {
        this.attendLeaderName = attendLeaderName;
    }
    public String getReportName() {
        return reportName;
    }
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    public List<OaMeetingmaterialinfosMIP> getMaterials() {
        return materials;
    }
    public void setMaterials(List<OaMeetingmaterialinfosMIP> materials) {
        this.materials = materials;
    }
    public List<OaAgendainfosMIP> getAgendaInfos() {
        return agendaInfos;
    }
    public void setAgendaInfos(List<OaAgendainfosMIP> agendaInfos) {
        this.agendaInfos = agendaInfos;
    }
  
}
