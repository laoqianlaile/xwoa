package com.centit.oa.po;

import java.io.Serializable;
import java.util.Date;
import com.centit.powerruntime.po.OptBaseInfo;

public class OaTripPlan implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String djId;
    
    private String tripPlanName;//名称
    
    private String deptno;//所在处室
    
    private Date startTime;//起止时间
    
    private Date endTime;//结束时间
    
    private String unrank;//职位
    
    private String tripPalce;//出差地点
    
    private String teamName;//陪同人员
    
    private String teamCodes;//陪同人员
    
    private String tripType;//出差类型  
    
    private String transport;//交通工具
    
    private String creater;//申请人
    
    private String content;//出差事由
    
    private String remark;//备注
    
    private Date createDate;
    
    private String  flowcode;
    
    private Long  flowInstId;
    
    private String  itemId;
    
    private String applyItemType;
    
    private Date updateTime;
    
    private String bizState;
    
    private OptBaseInfo optBaseInfo;
    
    public void copyNotNullProperty(OaTripPlan other){
            if(other.getTripPlanName() != null)
                this.tripPlanName=other.getTripPlanName();
            if( other.getDeptno() !=null)
                this.deptno= other.getDeptno();
            if(other.getCreater() !=null)
                this.creater= other.getCreater();
            if( other.getStartTime() != null)
                this.startTime= other.getStartTime(); 
            if(other.getEndTime() != null)
                this.endTime = other.getEndTime();
            if(other.getTripPalce() !=null)
                this.tripPalce =other.getTripPalce();
            if( other.getContent() != null)
                this.content= other.getContent();  
            if( other.getCreateDate() != null)
                this.createDate= other.getCreateDate();  
            if(other.getFlowcode() !=null)
                this.flowcode = other.getFlowcode();
            if(other.getFlowInstId() != null)
                this.flowInstId = other.getFlowInstId();
            if(other.getItemId() != null)
                this.itemId = other.getItemId();
            if(other.getApplyItemType() != null)
                this.applyItemType = other.getApplyItemType();
            if(other.getUpdateTime() != null)
                this.updateTime= other.getUpdateTime();
            if(other.getBizState() != null)
                this.bizState=other.getBizState();
            if(other.getTeamCodes() != null)
                this.teamCodes = other.getTeamCodes();
            if(other.getTeamName() != null)
                teamName = other.getTeamName();
            if(other.getTripType() != null)
                tripType = other.getTripType();
            if(other.getTransport() != null)
                transport = other.getTransport();
            if(other.getRemark() !=null)
                remark =other.getRemark();
        }
    public void copy(OaTripPlan other){
        this.tripPlanName = other.getTripPlanName();
        this.deptno= other.getDeptno();  
        this.creater= other.getCreater();  
        this.startTime= other.getStartTime(); 
        this.endTime = other.getEndTime();
        this.tripPalce = other.getTripPalce();
        this.content= other.getContent();  
        this.createDate= other.getCreateDate();
        this.flowcode= other.getFlowcode();
        this.flowInstId= other.getFlowInstId();  
        this.itemId= other.getItemId();  
        this.applyItemType= other.getApplyItemType();  
        this.updateTime= other.getUpdateTime(); 
        this.bizState = other.getBizState();
        this.teamName = other.getTeamName();
        this.teamCodes = other.getTeamCodes();
        this.tripType = other.getTripType();
        this.transport = other.getTransport();
        this.remark= other.getRemark();
    }
 
    public void clearProperties(){
        this.tripPlanName = null;
        this.deptno= null;  
        this.creater= null;  
        this.startTime= null; 
        this.endTime= null;
        this.tripPalce =null;
        this.content= null;  
        this.createDate= null;
        this.flowcode= null;
        this.flowInstId= null;  
        this.itemId= null;  
        this.applyItemType= null;  
        this.updateTime= null; 
        this.teamName = null;
        this.teamCodes = null;
        this.tripType = null;
        this.transport = null;
        this.bizState = null;
        this.remark= null;
    }
    
    public OaTripPlan(String djId, String pId, String deptno, Date startTime,Date endTime,
            String tripPalce, String teamName, String teamCodes,
            String tripType, String transport, String creater, String content,
            Date createDate, String flowcode, Long flowInstId, String itemId,
            String applyItemType, Date updateTime, String bizState,String tripPlanName,String remark) {
        super();
        this.djId = djId;
        this.deptno = deptno;
        this.endTime = endTime;
        this.startTime = startTime;
        this.tripPalce = tripPalce;
        this.teamName = teamName;
        this.teamCodes = teamCodes;
        this.tripType = tripType;
        this.transport = transport;
        this.creater = creater;
        this.content = content;
        this.createDate = createDate;
        this.flowcode = flowcode;
        this.flowInstId = flowInstId;
        this.itemId = itemId;
        this.applyItemType = applyItemType;
        this.updateTime = updateTime;
        this.bizState = bizState;
        this.tripPlanName=tripPlanName;
        this.remark=remark;
    }
    public OaTripPlan() {
        super();
    }

    public String getBizState() {
        return bizState;
    }

    public void setBizState(String bizState) {
        this.bizState = bizState;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
    
}
