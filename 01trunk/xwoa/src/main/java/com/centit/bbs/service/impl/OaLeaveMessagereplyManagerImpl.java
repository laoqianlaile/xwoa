package com.centit.bbs.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.bbs.po.OaBbsTheme;
import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.dao.OaLeaveMessagereplyDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.service.OaBbsThemeManager;
import com.centit.bbs.service.OaLeaveMessagereplyManager;

public class OaLeaveMessagereplyManagerImpl extends BaseEntityManagerImpl<OaLeaveMessagereply>
	implements OaLeaveMessagereplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaLeaveMessagereplyManager.class);

	private OaLeaveMessagereplyDao oaLeaveMessagereplyDao ;
	public void setOaLeaveMessagereplyDao(OaLeaveMessagereplyDao baseDao)
	{
		this.oaLeaveMessagereplyDao = baseDao;
		setBaseDao(this.oaLeaveMessagereplyDao);
	}
	
	private OaBbsThemeManager oaBbsThemeManager;
	
	public void setOaBbsThemeManager(OaBbsThemeManager oaBbsThemeManager) {
        this.oaBbsThemeManager = oaBbsThemeManager;
    }

    //获取主键
    @Override
    public String getNextKey() {
        return "LY"+oaLeaveMessagereplyDao.getNextKeyBySequence("s_OaLeaveMessagereply", 12);
    }
    
    /**
     * 回复的回复增加一次主题的回复数
     * @param themeno
     */
    public void addReplyNums(String themeno){
        
        if(StringUtils.isNotBlank(themeno)){
            OaBbsTheme theme = oaBbsThemeManager.getObjectById(themeno);
            if(null != theme.getPostsnum()){
                theme.setPostsnum(theme.getPostsnum() + 1);
            }else{
                theme.setPostsnum(Long.parseLong("1"));
            }
        }
    }
	
    /**
     * 删除回复时减少一次主题的回复数
     * @param themeno
     */
    public void reduceReplyNums(String themeno){
        
        if(StringUtils.isNotBlank(themeno)){
            OaBbsTheme theme = oaBbsThemeManager.getObjectById(themeno);
            if(null != theme.getPostsnum()){
                theme.setPostsnum(theme.getPostsnum() - 1);
            }else{
                theme.setPostsnum(Long.parseLong("0"));
            }
        }
    }
    
    @Override
    public void deleteObjectById(Serializable id) {
        OaLeaveMessagereply reply = super.getObjectById(id);
        if(reply != null){
            reply.setState("D");
        }
        
    }

    @Override
    public void deleteByMsgNo(String msgNo) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("lno", msgNo);
        filterMap.put("excludeStates", "D");
        List<OaLeaveMessagereply> replyList = super.listObjects(filterMap);
        if(replyList != null && replyList.size() > 0){
            for(OaLeaveMessagereply o : replyList){
                o.setState("D");
            }
        }
    }
}

