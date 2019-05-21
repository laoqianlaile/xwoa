package com.centit.bbs.po;

import java.util.Date;
import java.util.List;

import com.centit.bbs.po.OaLeaveMessagereply;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VBbsThemeReplay implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long replayId;
    private String no;// 序号
    private String lyno;// 回复序号
    private String infoType;// 留言类型
    private String djid;// 业务流水号
    private String messageComment;// 留言内容
    private String creater;// 留言人
    private Date creatertime;// 留言日期
    private String state;// 审核状态

    private String sign;// 扩展标记字段：在谈论版留言中用作标记留言人是否有上传头像
    private String ip;
    private String approvals;// 管理员

    private String layoutno;
    private String layoutcode;// 所属大模块主键

    private String type;// 标记数据来源 1 OA_LEAVE_MESSAGE 2 OA_LEAVE_MESSAGEREPLY
    
    //帖子相关信息
    private String themeTitle;
    private String subLayoutTitle;
    private String layoutName;
    
    // Constructors
    /** default constructor */
    public VBbsThemeReplay() {
    }

    public VBbsThemeReplay(String no, String infoType, String djid,
            String creater, Date creatertime, String messageComment) {
        super();
        this.no = no;
        this.infoType = infoType;
        this.djid = djid;
        this.messageComment = messageComment;
        this.creater = creater;
        this.creatertime = creatertime;
        // this.state = state;
    }

    /** full constructor */
    public VBbsThemeReplay(String no, String infoType, String djid,
            String creater, Date creatertime, String state,
            String messageComment, String lyno, String approvals,
            Long replayId, String layoutno, String layoutcode,String themeTitle,String subLayoutTitle,String layoutName) {
        this.no = no;
        this.infoType = infoType;
        this.djid = djid;
        this.creater = creater;
        this.creatertime = creatertime;
        this.state = state;
        this.messageComment = messageComment;

        this.replayId = replayId;
        this.lyno = lyno;
        this.approvals = approvals;
        this.layoutcode = layoutcode;
        this.layoutno = layoutno;
        
        this.themeTitle=themeTitle;
        this.subLayoutTitle=subLayoutTitle;
        this.layoutName=layoutName;

    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }

    public String getMessageComment() {
        return messageComment;
    }

    public void setMessageComment(String messageComment) {
        this.messageComment = messageComment;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatertime() {
        return creatertime;
    }

    public void setCreatertime(Date creatertime) {
        this.creatertime = creatertime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void copy(VBbsThemeReplay other) {

        this.setNo(other.getNo());

        this.infoType = other.getInfoType();
        this.djid = other.getDjid();
        this.creater = other.getCreater();
        this.creatertime = other.getCreatertime();
        this.state = other.getState();
        this.messageComment = other.getMessageComment();
        this.ip = other.getIp();
    }

    public void copyNotNullProperty(VBbsThemeReplay other) {

        if (other.getNo() != null)
            this.setNo(other.getNo());

        if (other.getInfoType() != null)
            this.infoType = other.getInfoType();
        if (other.getDjid() != null)
            this.djid = other.getDjid();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatertime() != null)
            this.creatertime = other.getCreatertime();
        if (other.getState() != null)
            this.state = other.getState();
        if (other.getMessageComment() != null)
            this.messageComment = other.getMessageComment();
        if (other.getIp() != null) {
            this.ip = other.getIp();
        }
    }

    public void clearProperties() {

        this.infoType = null;
        this.djid = null;
        this.creater = null;
        this.creatertime = null;
        this.state = null;
        this.messageComment = null;
        this.ip = null;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getReplayId() {
        return replayId;
    }

    public void setReplayId(Long replayId) {
        this.replayId = replayId;
    }

    public String getLyno() {
        return lyno;
    }

    public void setLyno(String lyno) {
        this.lyno = lyno;
    }

    public String getApprovals() {
        return approvals;
    }

    public void setApprovals(String approvals) {
        this.approvals = approvals;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLayoutno() {
        return layoutno;
    }

    public void setLayoutno(String layoutno) {
        this.layoutno = layoutno;
    }

    public String getLayoutcode() {
        return layoutcode;
    }

    public void setLayoutcode(String layoutcode) {
        this.layoutcode = layoutcode;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getSubLayoutTitle() {
        return subLayoutTitle;
    }

    public void setSubLayoutTitle(String subLayoutTitle) {
        this.subLayoutTitle = subLayoutTitle;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

}
