package com.centit.mip.po;

import java.io.Serializable;
import java.util.Date;
import com.centit.powerruntime.po.OptBaseInfo;
import com.google.gson.annotations.Expose;

public class OaTripPlanMIP implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String djId;
    @Expose(serialize = false) 
    private String tripPlanName;//名称
    @Expose(serialize = false) 
    private String deptno;//所在处室
    @Expose(serialize = false) 
    private String startTime;//起止时间
    @Expose(serialize = false) 
    private String endTime;//结束时间
    
    private String unrank;//职位
    @Expose(serialize = false) 
    private String tripPalce;//出差地点
    
    private String teamName;//陪同人员
    
    private String teamCodes;//陪同人员
    
    private String tripType;//出差类型  
    @Expose(serialize = false) 
    private String transport;//交通工具
    
    private String userid;//申请人
    @Expose(serialize = false) 
    private String content;//出差事由
    
    private String remark;//备注
    
    private String createDate;
    
    private String  flowcode;
    
    private Long  flowInstId;
    
    private String  itemId;
    
    private String applyItemType;
    
    private Date updateTime;
    
    private String bizState;
    
    private OptBaseInfo optBaseInfo;
    
    public OaTripPlanMIP() {
        super();
    }

    public String getBizState() {
        return bizState;
    }

    public void setBizState(String bizState) {
        this.bizState = bizState;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlowcode() {
        return flowcode;
    }

    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getApplyItemType() {
        return applyItemType;
    }

    public void setApplyItemType(String applyItemType) {
        this.applyItemType = applyItemType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }

    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getDeptno() {
        return deptno;
    }
    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }
    public String getTripPalce() {
        return tripPalce;
    }
    public void setTripPalce(String tripPalce) {
        this.tripPalce = tripPalce;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamCodes() {
        return teamCodes;
    }
    public void setTeamCodes(String teamCodes) {
        this.teamCodes = teamCodes;
    }
    public String getTripType() {
        return tripType;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
    public String getTransport() {
        return transport;
    }
    public void setTransport(String transport) {
        this.transport = transport;
    }
    public String getTripPlanName() {
        return tripPlanName;
    }
    public void setTripPlanName(String tripPlanName) {
        this.tripPlanName = tripPlanName;
    }
    public String getUnrank() {
        return unrank;
    }
    public void setUnrank(String unrank) {
        this.unrank = unrank;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }
    
}
