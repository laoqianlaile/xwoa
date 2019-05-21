package com.centit.bbs.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.service.OaLeaveMessagereplyManager;
import com.centit.bbs.util.IPUtil;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.sys.security.FUserDetail;
	

public class OaLeaveMessagereplyAction  extends BaseEntityDwzAction<OaLeaveMessagereply>  {
	private static final Log log = LogFactory.getLog(OaLeaveMessagereplyAction.class);
	private static final long serialVersionUID = 1L;
	private OaLeaveMessagereplyManager oaLeaveMessagereplyMag;
	private String s_djid;
	private String s_infoType;
	private String s_layoutno;// 子模块流水号
	private String themeno;
	public void setOaLeaveMessagereplyManager(OaLeaveMessagereplyManager basemgr)
	{
		oaLeaveMessagereplyMag = basemgr;
		this.setBaseEntityManager(oaLeaveMessagereplyMag);
	}
	 
	 //保存回复内容
    @Override
     public String save() {
        FUserDetail user = (FUserDetail) getLoginUser();
        try {
//            String content = new String(object.getMessagecomment().getBytes("ISO-8859-1"),"UTF-8");
            object.setMessagecomment(object.getMessagecomment());
            //add by lay 2015-11-21 添加状态和ip
            object.setState("N");
            object.setIp(IPUtil.getIRealIPAddr(request));
            //end add
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (StringUtils.isBlank(object.getLyno())) {
            object.setLyno(oaLeaveMessagereplyMag.getNextKey());
        }
        object.setCreater(user.getUsercode());//留言人
        object.setCreatertime(new Date());//留言日期
        oaLeaveMessagereplyMag.saveObject(object);
        // 回复的回复增加一次主题回复数
        oaLeaveMessagereplyMag.addReplyNums(themeno);
        return "refeshReplyList";
    }
    
    public String deleteMsg(){
        //modify by lay 2015-11-21 改成逻辑删除
        //super.delete();
        oaLeaveMessagereplyMag.deleteObjectById(object.getLyno());
        // end modify
        
        // 删除回复时减少一次主题回复数
        oaLeaveMessagereplyMag.reduceReplyNums(themeno);
        return "";
    }
    
    public String getS_djid() {
        return s_djid;
    }
    public void setS_djid(String s_djid) {
        this.s_djid = s_djid;
    }
    public String getS_infoType() {
        return s_infoType;
    }
    public void setS_infoType(String s_infoType) {
        this.s_infoType = s_infoType;
    }
    public String getS_layoutno() {
        return s_layoutno;
    }
    public void setS_layoutno(String s_layoutno) {
        this.s_layoutno = s_layoutno;
    }
    public String getThemeno() {
        return themeno;
    }
    public void setThemeno(String themeno) {
        this.themeno = themeno;
    }
    
}
