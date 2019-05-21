package com.centit.mailclient.util;

public class MailConstants {
    /**
     * 下面几种邮件协议pop3和imap是收件服务器应用的协议，smtp是发信服务器的协议，exchange微软公司的一套电子邮件服务组件
     * 至于几种协议类型的区别自己去查资料
     */
    public static final String MAIL_PROTOCOL_TYPE_POP3 = "pop3";
    public static final String MAIL_PROTOCOL_TYPE_IMAP = "imap";
    public static final String MAIL_PROTOCOL_TYPE_EXCHANGE = "exchange";
    public static final String MAIL_PROTOCOL_TYPE_SMTP = "smtp";
    
    /**
     * 协议类型 ssl和非ssl，例如我们http和https
     */
    public static final String PROTOCOL_NORMAL = "normal";
    public static final String PROTOCOL_SSL = "ssl";
    
    /**
     * 邮件发送类型
     */
    public static final Integer SEND_TYPE_NORMAL = 11; //正常发送
    public static final Integer SEND_TYPE_REPLAY = 12;//回复发送
    public static final Integer SEND_TYPE_TRANSMIT = 13;//转发
    /**
     * 接收类型
     */
    public static final Integer OWN_TYPE_SEND = 20;
    public static final Integer OWN_TYPE_NORMAL = 21; //正常接收
    public static final Integer OWN_TYPE_COPY = 22;//抄送接收
    public static final Integer OWN_TYPE_SECRET = 23;//密送接收
    
    /**
     * 邮件处于位置
     */
    public static final Integer LOCATION_OUTBOX = 1;//发件箱
    public static final Integer LOCATION_INBOX = 2;//收件箱
    public static final Integer LOCATION_DRAFTBOX = 3;//草稿箱
    
    /**
     * BOOL 变量true 和 false 的别名
     */
    public static final String BOOL_TRUE_ALIAS = "T";
    public static final String BOOL_FALSE_ALIAS = "F";
    
    
    
}
