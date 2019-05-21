package com.centit.bbs.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.bbs.po.OaLeaveMessagereply;

public interface OaLeaveMessagereplyManager extends BaseEntityManager<OaLeaveMessagereply> 
{
    //获取主键
    public String getNextKey();

    /**
     * 回复的回复增加一次主题的回复数
     * @param themeno
     */
    public void addReplyNums(String themeno);
    
    /**
     * 删除回复时减少一次主题的回复数
     * @param themeno
     */
    public void reduceReplyNums(String themeno);
    
    /**
     * 删除留言时删除回复
     * @param themeno
     */
    public void deleteByMsgNo(String msgNo);
}
