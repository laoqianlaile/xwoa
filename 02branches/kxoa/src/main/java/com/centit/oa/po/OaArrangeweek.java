package com.centit.oa.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.centit.sys.util.DateTimeUtil;

public class OaArrangeweek implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String djId;
    private String dateran;
    private Date createtime;
    private String weekname;
    private String weektitle;
    private String remark;
    private String attendusers;
    private String attendleaders;
    private String address;
    private String depno;
    private String creater;
    private Date createdate;
    private String state;

    private String showTimeValue;
    private String depname;
    private String canAdded = "0";
    private String canUpdated = "0";
    private String canDeleted = "0";

    public OaArrangeweek() {

    }

    public OaArrangeweek(Date createtime, String beginDate, String endDate,
            String remark, String attendusers, String attendleaders,
            String address, String depno, String creater, String state) {
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
        if (StringUtils.isNotBlank(other.getAttendleaders())) {
            this.attendleaders = other.getAttendleaders();
        }
        if (StringUtils.isNotBlank(other.getAttendusers())) {
            this.attendusers = other.getAttendusers();
        }
        if (StringUtils.isNotBlank(other.getAddress())) {
            this.address = other.getAddress();
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
    }

    public void clearProperties() {
        this.djId = null;
        this.createtime = null;
        this.weekname = null;
        this.dateran = null;
        this.weektitle = null;
        this.remark = null;
        this.attendleaders = null;
        this.attendusers = null;
        this.address = null;
        this.depno = null;
        this.creater = null;
        this.createdate = null;
        this.state = null;
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

}
