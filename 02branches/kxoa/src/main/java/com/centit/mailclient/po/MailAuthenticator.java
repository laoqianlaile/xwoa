package com.centit.mailclient.po;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * 邮件服务器认证类，把他注册到sesion中，这样收发邮件不用在注入了用户名和密码了
 * 
 * @author lay
 * @create 2016年5月12日
 * @version
 */
public class MailAuthenticator extends Authenticator{
   private String userName;
   private String password;
   
   public MailAuthenticator(String username,String password){
       this.userName = username;
       this.password = password;
   }
   
   protected PasswordAuthentication getPasswordAuthentication(){
       return new PasswordAuthentication(userName, password);
   }
   
}
