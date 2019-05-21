package com.centit.oa.po;

import com.centit.sys.util.DateTimeUtil;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OaArrangeweek implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String djId;    //编号
    private String dateran; //时间段
    private Date createtime;    //安排日期
    private String weekname;    //星期
    private String weektitle;   //标题
    private String remark;     //工作内容
    private String attendusers; //参加人员
    private String attendleaders;   //领导成员
    private String address; //地点
    private String depno;   //责任部门
    private String creater; //记录者
    private Date createdate;    //记录时间
    private String state;   //状态
    private String remarkInfo;//备注信息
    private String content;//备注
    private String showTimeValue;
    private String depname;
    private String canAdded = "0";
    private String canUpdated = "0";
    private String canDeleted = "0";
    private String attendleaderscodes;//参加领导编号
    private String attenduserscodes;//参加人员编号
    private String meetType;
    private String itemtype;//活动类型

    private String office_djid;//关联的办件id
    private String office_applyItemType;
    private String office_itemType;//关联的办件路径
    private String office_optType;//关联的办件类型
    private String office_bjName;//关联的办件名称
    private String colorType;

    
    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public OaArrangeweek() {

    }

    public OaArrangeweek(Date createtime, String beginDate, String endDate,
            String remark, String attendusers, String attendleaders,
            String address, String depno, String creater, String state,String remarkInfo,String content,String attenduserscodes,String attendleaderscodes,String itemtype) {
        this.createtime = createtime;
        if (null != createtime) {
            try {
                Date timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                                .format(createtime));
                this.weekname = DateTimeUtil.getDayOfWeekShort(timeDate);
                int hour = Integer.parseInt(new SimpleDateFormat("HH")
                        .format(createtime));
                if (hour < 12) {
                    this.dateran = "SW";
                } else if (hour < 18) {
                    this.dateran = "XW";
                } else {
                    this.dateran = "WS";
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            this.weektitle = new SimpleDateFormat("yyyy年MM月dd日")
                    .format(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate))
                    + "~"
                    + new SimpleDateFormat("yyyy年MM月dd日")
                            .format(new SimpleDateFormat("yyyy-MM-dd")
                                    .parse(endDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            this.weektitle = "";
        }
        this.remark = remark;
        this.attendleaders = attendleaders;
        this.attendusers = attendusers;
        this.address = address;
        this.depno = depno;
        this.creater = creater;
        this.state = state;
        this.remarkInfo = remarkInfo;
        this.content = content;
        this.attenduserscodes = attenduserscodes;
        this.attendleaderscodes = attendleaderscodes;
        this.itemtype = itemtype;
    }

    public void copy(OaArrangeweek other) {
        if (StringUtils.isNotBlank(other.getDjId())) {
            this.djId = other.getDjId();
        }
        if (null != other.getCreatetime()) {
            this.createtime = other.getCreatetime();
        }
        if (null != other.getDateran()) {
            this.dateran = other.getDateran();
        }
        if (StringUtils.isNotBlank(other.getWeekname())) {
            this.weekname = other.getWeekname();
        }
        if (StringUtils.isNotBlank(other.getWeektitle())) {
            this.weektitle = other.getWeektitle();
        }
        if (StringUtils.isNotBlank(other.getRemark())) {
            this.remark = other.getRemark();
        }
        if (StringUtils.isNotBlank(other.getRemarkInfo())) {
            this.remarkInfo = other.getRemarkInfo();
        }
        if (StringUtils.isNotBlank(other.getAttendleaders())) {
            this.attendleaders = other.getAttendleaders();
        }
        if (StringUtils.isNotBlank(other.getAttendusers())) {
            this.attendusers = other.getAttendusers();
        }
        if (StringUtils.isNotBlank(other.getAddress())) {
            this.address = other.getAddress();
        }
        if (StringUtils.isNotBlank(other.getContent())){
            this.content = other.getContent();
        }
        if (StringUtils.isNotBlank(other.getAttenduserscodes())){
            this.attenduserscodes=other.getAttenduserscodes();
        }
        if (StringUtils.isNotBlank(other.getAttendleaderscodes())){
            this.attendleaderscodes=other.getAttendleaderscodes();
        
        if (StringUtils.isNotBlank(other.getItemtype())){
            this.itemtype = other.getItemtype();
            }
        }
    }

    public void copyNotNullProperty(OaArrangeweek other) {
        if (StringUtils.isNotBlank(other.getDjId())) {
            this.djId = other.getDjId();
        }
        if (null != other.getCreatetime()) {
            this.createtime = other.getCreatetime();
        }
        if (null != other.getDateran()) {
            this.dateran = other.getDateran();
        }
        if (StringUtils.isNotBlank(other.getWeekname())) {
            this.weekname = other.getWeekname();
        }
        if (StringUtils.isNotBlank(other.getWeektitle())) {
            this.weektitle = other.getWeektitle();
        }
        if (StringUtils.isNotBlank(other.getRemark())) {
            this.remark = other.getRemark();
        }
        if (StringUtils.isNotBlank(other.getRemarkInfo())) {
            this.remarkInfo = other.getRemarkInfo();
        }
        if (StringUtils.isNotBlank(other.getAttendleaders())) {
            this.attendleaders = other.getAttendleaders();
        }
        if (StringUtils.isNotBlank(other.getAttendusers())) {
            this.attendusers = other.getAttendusers();
        }
        if (StringUtils.isNotBlank(other.getAddress())) {
            this.address = other.getAddress();
        }
        if (StringUtils.isNotBlank(other.getDepno())) {
            this.depno = other.getDepno();
        }
        if (StringUtils.isNotBlank(other.getCreater())) {
            this.creater = other.getCreater();
        }
        if (StringUtils.isNotBlank(other.getState())) {
            this.state = other.getState();
        }
        if (StringUtils.isNotBlank(other.getContent())){
            this.content = other.getContent();
        }
        if (StringUtils.isNotBlank(other.getAttenduserscodes())){
            this.attenduserscodes=other.getAttenduserscodes();
        }
        if (StringUtils.isNotBlank(other.getAttendleaderscodes())){
            this.attendleaderscodes=other.getAttendleaderscodes();
        }
        if (StringUtils.isNotBlank(other.getItemtype())){
            this.itemtype=other.getItemtype();
        }
    }

    public void clearProperties() {
        this.djId = null;
        this.createtime = null;
        this.weekname = null;
        this.dateran = null;
        this.weektitle = null;
        this.remark = null;
        this.remarkInfo = null;
        this.attendleaders = null;
        this.attendusers = null;
        this.address = null;
        this.depno = null;
        this.creater = null;
        this.createdate = null;
        this.state = null;
        this.content = null;
        this.attenduserscodes = null;
        this.attendleaderscodes = null;
        this.itemtype = null;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getDateran() {
        return dateran;
    }

    public void setDateran(String dateran) {
        this.dateran = dateran;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createdate) {
        if (StringUtils.isNotBlank(createdate)) {
            try {
                setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(createdate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
        int hour = Integer.parseInt(new SimpleDateFormat("HH")
                .format(createtime));
        if (hour < 12) {
            setDateran("SW");
        } else if (hour < 18) {
            setDateran("XW");
        } else {
            setDateran("WS");
        }
        setWeekname(DateTimeUtil.getDayOfWeekShort(createtime));
    }

    public String getWeekname() {
        return weekname;
    }

    public void setWeekname(String weekname) {
        this.weekname = weekname;
    }

    public String getWeektitle() {
        return weektitle;
    }

    public void setWeektitle(String begindate, String enddate) {
        try {
            this.weektitle = new SimpleDateFormat("yyyy年MM月dd日")
                    .format(new SimpleDateFormat("yyyy-MM-dd").parse(begindate))
                    + "~"
                    + new SimpleDateFormat("yyyy年MM月dd日")
                            .format(new SimpleDateFormat("yyyy-MM-dd")
                                    .parse(enddate));
        } catch (ParseException e) {
            this.weektitle = "";
        }
    }

    public void setWeektitle(String weektitle) {
        this.weektitle = weektitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttendusers() {
        return attendusers;
    }

    public void setAttendusers(String attendusers) {
        this.attendusers = attendusers;
    }

    public String getAttendleaders() {
        return attendleaders;
    }

    public void setAttendleaders(String attendleaders) {
        this.attendleaders = attendleaders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCanAdded() {
        return canAdded;
    }

    public void setCanAdded(String canAdded) {
        this.canAdded = canAdded;
    }

    public String getCanUpdated() {
        return canUpdated;
    }

    public void setCanUpdated(String canUpdated) {
        this.canUpdated = canUpdated;
    }

    public String getCanDeleted() {
        return canDeleted;
    }

    public void setCanDeleted(String canDeleted) {
        this.canDeleted = canDeleted;
    }

    public String getShowTimeValue() {
        return showTimeValue;
    }

    public void setShowTimeValue(String showTimeValue) {
        this.showTimeValue = showTimeValue;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttendleaderscodes() {
        return attendleaderscodes;
    }

    public void setAttendleaderscodes(String attendleaderscodes) {
        this.attendleaderscodes = attendleaderscodes;
    }

    public String getAttenduserscodes() {
        return attenduserscodes;
    }

    public void setAttenduserscodes(String attenduserscodes) {
        this.attenduserscodes = attenduserscodes;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }


    public String getOffice_djid() {
        return office_djid;
    }

    public void setOffice_djid(String office_djid) {
        this.office_djid = office_djid;
    }

    public String getOffice_applyItemType() {
        return office_applyItemType;
    }

    public void setOffice_applyItemType(String office_applyItemType) {
        this.office_applyItemType = office_applyItemType;
    }

    public String getOffice_itemType() {
        return office_itemType;
    }

    public void setOffice_itemType(String office_itemType) {
        this.office_itemType = office_itemType;
    }

    public String getOffice_optType() {
        return office_optType;
    }

    public void setOffice_optType(String office_optType) {
        this.office_optType = office_optType;
    }

    public String getOffice_bjName() {
        return office_bjName;
    }

    public void setOffice_bjName(String office_bjName) {
        this.office_bjName = office_bjName;
    }


}
