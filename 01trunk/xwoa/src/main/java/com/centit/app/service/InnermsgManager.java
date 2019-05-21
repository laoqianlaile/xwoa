package com.centit.app.service;

import java.util.List;

import com.centit.app.po.FileInfo;
import com.centit.app.po.Innermsg;
import com.centit.core.service.BaseEntityManager;

public interface InnermsgManager extends BaseEntityManager<Innermsg> 
{
    
    /**
     * 查询回复消息
     * @param replymsgcode
     * @return
     */
    public List<Innermsg> listReplyMsgs(Long replymsgcode);
    
    /**
     * 查询个人邮件消息
     * @param receive
     * @return
     */
    public List<Innermsg> listMyMsgs(String receive);
    
    /**
     * 保存消息
     */
    public void saveInnermsg(Innermsg innermsg);
    
    
    /**
     * 根据消息编号获取文件信息
     * @param msgcode
     * @return
     */
    public List<FileInfo> listFilesByMsg(Long msgcode);
}
