package com.centit.mailclient.po;

import java.util.Date;
import java.util.List;

import com.centit.mailclient.util.MailConstants;

/**
 * 
 * 邮件持久类
 * 
 * @author lay
 * @create 2016年5月11日
 * @version 1.0
 */
public class MailInfo implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 邮箱配置id
     */
    private String email;
    
    /**
     * 用户编码
     */
    private String usercode;
    
    /**
     * 发信人
     */
    private String sender;
 
    /**
     * 收信人 多个用；隔开
     */
    private String receiver;
    
    /**
     * 抄送到对象，多个用；隔开
     */
    private String copyer;
    /**
     * 密送到对象，多个用；隔开
     */
    private String secreter;
    
    /**
     * 发送类型 11-普通发送 12-回复发送 13-转发
     */
    private Integer sendType;
    /**
     * 所属类型
     * 
     * 20-发送 21-普通发送接收 22-抄送接收 23--密送接收  
     */
    private Integer ownType;
    
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 位置 1-发件箱 2-收件箱 3-草稿箱
     */
    private Integer location;
    /**
     *已读标识 T-已读 F-未读
     */
    private String readState = MailConstants.BOOL_FALSE_ALIAS;
    /**
     * 是否有附件T-有 F-无
     */
    private String hasAttachment = MailConstants.BOOL_FALSE_ALIAS;
    /**
     * 是否有效T-有 F-无
     */
    private String isvalid = MailConstants.BOOL_TRUE_ALIAS;
    
    /**
     * 系统发出的为0，邮箱服务器上拉出来记录实际message的id
     */
    private String messageId = "0";
    /**
     * 附件列表
     */
    private List<MailAttachment> attachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getSender() {
        return sender;
    }
    
    public String getSenderNickname(){
        if(sender!=null){
            String nickName = sender;
            if(sender.indexOf("<")!=-1 && sender.indexOf(">")!=-1){
                nickName = sender.substring(0,sender.indexOf("<"));
            }
            return nickName;
        }
        return null;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMailTo(){
       return convertMailAddr(receiver);
    }
    
    private String convertMailAddr(String oldAddr){
        String newAddr = "";
        if(oldAddr!=null && !"".equals(oldAddr)){
             String []newAddrs = oldAddr.split(";");
             String temp = "";
             for(String tempNewAddr : newAddrs){
                 if(newAddr.indexOf("<")!=-1){
                     temp = tempNewAddr.substring(tempNewAddr.indexOf("<")+1, tempNewAddr.length()-1);
                 }else{
                     temp = tempNewAddr;
                 }
                 newAddr = newAddr + "," +temp;
             }
             newAddr = newAddr.replaceFirst(",", "");
        }
        return newAddr;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCopyer() {
        return copyer;
    }
    
    public String getMailCc(){
        return convertMailAddr(copyer);
    }
    
    public void setCopyer(String copyer) {
        this.copyer = copyer;
    }

    public String getSecreter() {
        return secreter;
    }
    
    public String getMailBcc(){
        return convertMailAddr(secreter);
    }
    public void setSecreter(String secreter) {
        this.secreter = secreter;
    }

    public Integer getOwnType() {
        return ownType;
    }

    public void setOwnType(Integer ownType) {
        this.ownType = ownType;
    }
    
    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public String getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(String hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public List<MailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MailAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
