package com.centit.mailclient.po;

import com.centit.mailclient.util.MailConstants;
import com.centit.webservice.util.Cryptos;

/**
 * 邮件配置
 * 
 * 服务器          SSL协议端口                            非SSL协议端口
 * IMAP     993              143
 * SMTP     465/994          25
 * POP3     995              110
 * 
 * POP3协议允许电子邮件客户端下载服务器上的邮件，但是在客户端的操作（如移动邮件、标记已读等），不会反馈到服务器上，
 * 比如通过客户端收取了邮箱中的3封邮件并移动到其他文件夹，邮箱服务器上的这些邮件是没有同时被移动的 
 * 而IMAP提供webmail 与电子邮件客户端之间的双向通信，
 * 客户端的操作都会反馈到服务器上，对邮件进行的操作，服务器上的邮件也会做相应的动作。
 * 
 * @author lay
 * @create 2016年5月11日
 * @version 1.0
 */
public class MailProfile implements java.io.Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 配置项主键
     */
    private Long id;
    /**
     * 用户编码
     */
    private String usercode;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 邮箱账号密码
     */
    private String password;
    /**
     * 当前配置的显示名称，可以让你看出是用的哪个邮箱
     */
    private String displayName;
    /**
     * 对方接收邮件时，发件人显示的名称
     */
    private String senderName;
    /**
     * 收信服务器类型  POP3,IMAP,EXCHANGE
     */
    private String receiveHostType = MailConstants.MAIL_PROTOCOL_TYPE_POP3;
    /**
     * 收信服务器地址
     */
    private String receiveHostAddr;
    /**
     * 收信协议normal,ssl
     */
    private String receiveProtocol = MailConstants.PROTOCOL_NORMAL;
    /**
     * 收信端口
     */
    private String receivePort = "110";
    /**
     * 发信服务器地址
     */
    private String sendHostAddr;
    /**
     * 发信协议normal,ssl
     */
    private String sendProtocol = MailConstants.PROTOCOL_NORMAL;
    /**
     * 发信端口
     */
    private String sendPort = "25";
  
    /**
     * 配置是否激活 T-是 F-否
     */
    private String isActive = "T";
    
    public MailProfile(){
        
    }
    
    public MailProfile(String usercode,String email,String password){
        this.usercode = usercode;
        this.email = email;
        this.password = password;
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static String encryptPassword(String pwd){
        if(pwd==null || "".equals(pwd)){
            return null;
        }
       return Cryptos.aesEncrypt(pwd);
    }
    
    public String getPlaintextPassword(){
        if(password==null || "".equals(password)){
            return null;
        }
        return Cryptos.aesDecrypt(password);
    }
    
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiveHostType() {
        return receiveHostType;
    }
    public void setReceiveHostType(String receiveHostType) {
        this.receiveHostType = receiveHostType;
    }
    public String getReceiveHostAddr() {
        return receiveHostAddr;
    }
    public void setReceiveHostAddr(String receiveHostAddr) {
        this.receiveHostAddr = receiveHostAddr;
    }
    public String getReceiveProtocol() {
        return receiveProtocol;
    }
    public void setReceiveProtocol(String receiveProtocol) {
        this.receiveProtocol = receiveProtocol;
    }
    public String getReceivePort() {
        return receivePort;
    }
    public void setReceivePort(String receivePort) {
        this.receivePort = receivePort;
    }
    public String getSendHostAddr() {
        return sendHostAddr;
    }
    public void setSendHostAddr(String sendHostAddr) {
        this.sendHostAddr = sendHostAddr;
    }
    public String getSendProtocol() {
        return sendProtocol;
    }
    public void setSendProtocol(String sendProtocol) {
        this.sendProtocol = sendProtocol;
    }
    public String getSendPort() {
        return sendPort;
    }
    public void setSendPort(String sendPort) {
        this.sendPort = sendPort;
    }
    public String getIsActive() {
        return isActive;
    }
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    
}
