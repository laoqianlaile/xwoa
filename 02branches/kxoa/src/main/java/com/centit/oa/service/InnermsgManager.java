package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.oa.po.Innermsg;
import com.centit.oa.po.UserMailConfig;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;

public interface InnermsgManager extends BaseEntityManager<Innermsg> {

    /**
     * 保存即时消息
     * 
     * @param innermsg
     */
    void saveMsg(Innermsg innermsg);

    /**
     * 保存即时消息：简单的保存收件人等字段，不包括抄送，密送
     * 
     * @param innermsg
     */
    void saveSimpleMsg(Innermsg innermsg);

    /**
     * 保存公告
     * 
     * @param innermsg
     */
    void saveAnnouncement(Innermsg innermsg);

    /**
     * 删除即时消息
     * 
     * @param innermsg
     * @param mailType
     *            收取消息或发送消息识别,套用MailType
     */
    void deleteMsg(Innermsg innermsg, String mailType);

    /**
     * 批量删除
     * 
     * @param innermsgs
     * @param mailType
     */
    void deleteMsgs(List<Innermsg> innermsgs, String mailType);

    /**
     * 废件箱还原到发件箱
     * 
     * @param pk
     * @param loginUserCode
     */
    public void cancleDropMsgs(List<String> pk, String loginUserCode);

    /**
     * 批量記入廢件箱
     * 
     * @param pk
     * @param loginUserCode
     */
    public void dropMsgs(List<String> pk, String loginUserCode);

    /**
     * 邮件状态互换[inbox <--> outbox <--> ]
     * 
     * @param innermsg
     */
    void updateMailBox(List<Innermsg> innermsgs);

    /**
     * 邮件状态互换[inbox <--> outbox <--> ]
     * 
     * @param innermsg
     */
    void updateMailBox(List<Innermsg> innermsgs, String to);

    /**
     * 删除邮件
     * 
     * @param innermsg
     */
    void deleteMail(Innermsg innermsg);

    /**
     * 发送邮件
     * 
     * @param innermsg
     */
    void saveSendMail(Innermsg innermsg);

    /**
     * 接收服务器邮件
     * 
     * @param innermsg
     */
    int saveReceiveMail(Innermsg innermsg);

    /**
     * 邮件发送接口
     * 
     * @param emailid
     *            {@link UserMailConfig} 主键
     * @param to
     *            收件人邮箱账户集合
     * @param cc
     *            抄送人邮箱账户集合
     * @param title
     *            标题
     * @param content
     *            内容
     * @param filecodes
     *            附件Id
     */
    void saveSendMail(String emailid, List<String> to, List<String> cc,
            String title, String content, String... filecodes);

    /**
     * 消息发送接口
     * 
     * @param senderUsercode
     *            发送人usercode
     * @param receiverUsercode
     *            接收人usercode集合
     * @param title
     *            标题
     * @param content
     *            内容
     * @param filecodes
     *            附件Id
     */
    void saveSendMessage(String senderUsercode, List<String> receiverUsercode,
            String title, String content, String... filecodes);

    List<Innermsg> listBySearch(String usercode, String key, PageDesc pageDesc);

    // /**
    // * 查询回复消息
    // * @param replymsgcode
    // * @return
    // */
    // public List<Innermsg> listReplyMsgs(Long replymsgcode);
    //
    // /**
    // * 查询个人邮件消息
    // * @param receive
    // * @return
    // */
    // public List<Innermsg> listMyMsgs(String receive);
    //
    // /**
    // * 保存消息
    // */
    // public void saveInnermsg(Innermsg innermsg);
    //
    //
    // /**
    // * 根据消息编号获取文件信息
    // * @param msgcode
    // * @return
    // */
    // public List<FileInfo> listFilesByMsg(Long msgcode);

    public String getNextKey();

    /**
     * 草稿箱删除附件使用
     */
    public void deleteMsgnnex(String msgcode, String filecode);

    /**
     * 收件箱列表
     * 
     * @param filterMap
     * @param pageDesc
     * @param usercode
     * @return
     */
    public List<Innermsg> listObjects(Map<String, Object> filterMap,
            PageDesc pageDesc, String usercode);

    void autoSend();

    /**
     * 回复数
     * 
     * @param innermsg
     * @return
     */
    public int getReplayCount(Innermsg innermsg);

    /**
     * 未读回复数
     * 
     * @param innermsg
     * @return
     */
    public int getUnReadRepalyCount(Innermsg innermsg);
}
