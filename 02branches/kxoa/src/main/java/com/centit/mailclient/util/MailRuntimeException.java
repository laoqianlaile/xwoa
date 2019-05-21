package com.centit.mailclient.util;

/**
 * 
 * 自定义邮件运行异常，方便捕捉信息
 * 
 * @author lay
 * @create 2016年5月20日
 * @version
 */
public class MailRuntimeException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 消息简介
     */
    private String descMessage;
    
    private String detailMessage;
    
    public MailRuntimeException(String descMessage,String detailMessage){
        this.descMessage = descMessage;
        this.detailMessage = detailMessage;
    }

    public String getDescMessage() {
        return descMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
    
    
        
    
}
