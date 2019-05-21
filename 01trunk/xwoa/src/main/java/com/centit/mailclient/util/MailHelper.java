package com.centit.mailclient.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.centit.mailclient.po.MailAttachment;
import com.centit.mailclient.po.MailAuthenticator;
import com.centit.mailclient.po.MailInfo;
import com.centit.mailclient.po.MailProfile;
import com.centit.sys.util.IdGen;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;

/**
 * 
 * 邮件辅助类
 * 可参考http://www.sxt.cn/u/756/blog/4569
 * http://blog.csdn.net/zoufaxiang/article/details/50847965
 * 
 * 传统的收发邮件我们javamail足以应付了，
 * 但是对于Exchange我们需要EWS，这是个java的Exchange webservice。
 * 本辅助类没有对邮箱服务器做任何写操作，只有读操作
 * 
 * @author lay
 * @create 2016年5月12日
 * @version
 */
public class MailHelper {
   private static Logger log = Logger.getLogger(MailHelper.class);
   /**
    * 
    * @param profile
    * @param type 1-为了Transport 2-为了Store
    * @return
    */
   @SuppressWarnings("restriction")
public static Session getSession(MailProfile profile,int type){
      Properties props = new Properties();
      if(log.isDebugEnabled()){
          props.put("mail.debug","true");
      }
      
      if(type==1){
          props.put("mail.smtp.host", profile.getSendHostAddr()); 
          props.put("mail.smtp.port", profile.getSendPort());
          props.put("mail.transport.protocol", "smtp");
          props.put("mail.smtp.auth", "true");//这句我们默认需要认证了
          if(MailConstants.PROTOCOL_SSL.equals(profile.getSendProtocol())){
              final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
              Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
              props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);  
              props.setProperty("mail.smtp.socketFactory.fallback", "false");  
              props.setProperty("mail.smtp.socketFactory.port", profile.getSendPort());  
          }
      }
      if(type==2){
          //pop3
          if(MailConstants.MAIL_PROTOCOL_TYPE_POP3.equals(profile.getReceiveHostType())){
              props.put("mail.pop3.host", profile.getReceiveHostAddr());
              props.put("mail.pop3.port", profile.getReceivePort());
              if(MailConstants.PROTOCOL_SSL.equals(profile.getReceiveProtocol())){
                  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
                  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
                  props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);  
                  props.setProperty("mail.pop3.socketFactory.fallback", "false");  
                  props.setProperty("mail.pop3.socketFactory.port", profile.getReceivePort());  
              }
          }
          //imap
          if(MailConstants.MAIL_PROTOCOL_TYPE_IMAP.equals(profile.getReceiveHostType())){
              props.put("mail.imap.host", profile.getReceiveHostAddr());
              props.put("mail.imap.port", profile.getReceivePort());
              if(MailConstants.PROTOCOL_SSL.equals(profile.getReceiveProtocol())){
                  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
                  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
                  props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);  
                  props.setProperty("mail.imap.socketFactory.fallback", "false");  
                  props.setProperty("mail.imap.socketFactory.port", profile.getReceivePort());  
              }
          }
          props.put("mail.store.protocol", profile.getReceiveHostType());
      }
      Authenticator authenticator = new MailAuthenticator(profile.getEmail(), profile.getPlaintextPassword());
      //将认证设置进去，一般邮箱收发服务器都有认证的,注意这里不用getDefautInstance,因为该方法会将session存到缓存里面，一旦认证失败了，你再修改认证信息的话，是没有效果的
      Session session = Session.getInstance(props, authenticator);
      return session;
   }
   
   /**
    * 断言配置文件是否有效
    * @param profile
    * @return
    */
   public static boolean assertProfileAvailable(MailProfile profile){
       boolean enabled = true;
       //如果不是exchange协议
       if(!MailConstants.MAIL_PROTOCOL_TYPE_EXCHANGE.equals(profile.getReceiveHostType())){
           Session session = MailHelper.getSession(profile, 2);
           Store store = null;
           try {
             store = session.getStore();
             store.connect();
           } catch (NoSuchProviderException e) {
               enabled = false;
           } catch (MessagingException e) {
               enabled = false;
           } finally{
               if(store!=null){
                   try {store.close();} catch (MessagingException e) {}
               }
           }
       }else{//如果是exchange的协议
           try{
               ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
               String [] arr = profile.getEmail().split("@");
               ExchangeCredentials credentials = new WebCredentials(arr[0], profile.getPlaintextPassword(), arr[1]);
               service.setCredentials(credentials);
               //设置Exchange连接的服务器地址
               service.setUrl(new URI("https://"+profile.getReceiveHostAddr()+"/EWS/Exchange.asmx"));
                //绑定邮箱
              microsoft.exchange.webservices.data.core.service.folder.Folder.bind(service, WellKnownFolderName.Inbox);
           }catch(Exception e){
               enabled = false;
           }
       }
       return enabled;
   }
   
   public static void sendMail(MailProfile profile,MailInfo mailInfo){
       InternetAddress[] toAddrs = null;//收件人
       InternetAddress[] ccAddrs = null;//抄送地址
       InternetAddress[] bccAddrs = null;//密送地址
       
       Session session = getSession(profile, 1);
       MimeMessage msg = new MimeMessage(session);
      
       try{
           String senderName = "";
           if(mailInfo.getSender() !=null){
               senderName = MimeUtility.encodeText(profile.getSenderName());
           }
           if(profile.getEmail() != null){
               msg.setFrom(new InternetAddress(senderName+"<"+profile.getEmail()+">"));
           }
           if (mailInfo.getMailTo() != null){
               toAddrs = InternetAddress.parse(mailInfo.getMailTo(), false);
               msg.setRecipients(Message.RecipientType.TO, toAddrs);
           }
           if (mailInfo.getMailCc() != null){
               ccAddrs = InternetAddress.parse(mailInfo.getMailCc(), false);
               msg.setRecipients(Message.RecipientType.CC, ccAddrs);
           }
           if (mailInfo.getMailBcc() != null){
               bccAddrs = InternetAddress.parse(mailInfo.getMailBcc(), false);
               msg.setRecipients(Message.RecipientType.BCC, bccAddrs);
           }
           msg.setSubject(MimeUtility.encodeText(mailInfo.getSubject()));
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
           Multipart mainPart = new MimeMultipart();
           // 创建一个内容的MimeBodyPart
           BodyPart contentPart  = new MimeBodyPart();
           contentPart.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
           mainPart.addBodyPart(contentPart);
           
           if(mailInfo.getAttachments()!=null && !mailInfo.getAttachments().isEmpty()){
               for(MailAttachment affix : mailInfo.getAttachments()){
                   BodyPart affixPart  = new MimeBodyPart();
                   DataSource source = new FileDataSource(affix.getAbsolutePath());
                 //添加附件的内容
                   affixPart.setDataHandler(new DataHandler(source));
                 //添加附件的标题
                   affixPart.setFileName(MimeUtility.encodeText(affix.getFileName()));
                   mainPart.addBodyPart(affixPart);
               }
           }
        // 将MiniMultipart对象设置为邮件内容
           msg.setContent(mainPart);
           msg.setSentDate(mailInfo.getSendTime());

           Transport.send(msg);
       }catch(MessagingException e){
           if(e instanceof AuthenticationFailedException){
               throw new MailRuntimeException("认证失败，请检测用户名和密码是否正确！",e.getMessage());
           }
           if(e.getNextException() instanceof UnknownHostException){
               throw new MailRuntimeException("服务器地址配置错误，请检查地址是否正确！",e.getMessage());
           }
           throw new MailRuntimeException("发送邮件失败，未知错误！",e.getMessage());
       } catch (UnsupportedEncodingException e) {
           throw new MailRuntimeException("给发件信息编码失败！",e.getMessage());
       }
   }
   
   /**
    * 接收邮件,只接收昨天到现在的邮件，防止数据量过大
    * @param profile
    * @return
    */
   public static List<MailInfo> receiveMails(MailProfile profile){
       if(MailConstants.MAIL_PROTOCOL_TYPE_EXCHANGE.equals(profile.getReceiveHostType())){
           return receiveMailsForExchange(profile);
       }
       
       Session session = getSession(profile, 2);
       Store store = null;
       Folder folder = null;
       List<MailInfo> mailInfoList = null;
       try {
           store = session.getStore();
           store.connect();
           
           //连接到Store之后,接下来,获得一个folder,必须打开它就可以读取里边的消息了.
           //POP3唯一可用的文件夹就是INBOX,如果实用IMAP,还可以用其他的文件夹.
           folder = store.getFolder("INBOX");
           folder.open(Folder.READ_ONLY);
           //Folder的getMessages()方法时采取了很智能的方式：首先接收新邮件列表，然后再需要的时候（比如读取邮件内容）才从邮件服务器读取邮件内容。
           //在读取邮件时，我们可以用Message类的getContent()方法接收邮件或是writeTo()方法将邮件保存，
           //getContent()方法只接收邮件内容（不包含邮件头），而writeTo()方法将包括邮件头。
           //Message message[] = folder.getMessages();
           
           //http://892848153.iteye.com/blog/1724601过滤条件
           Calendar cal = Calendar.getInstance();
           cal.add(Calendar.DATE,-1);
           
           SearchTerm comparisonTermGe = new SentDateTerm(ComparisonTerm.GE, cal.getTime());//获取从昨天到现在的邮件，减少邮件的大量数据
           Message []messages = folder.search(comparisonTermGe);
           mailInfoList = parseMessage(profile,folder, messages);
           
       }catch (NoSuchProviderException e){
           throw new MailRuntimeException("无法连接服务器！",e.getMessage());
       }catch (MessagingException e) {
           if(e instanceof AuthenticationFailedException){
               throw new MailRuntimeException("认证失败，请检测用户名和密码是否正确！",e.getMessage());
           }
           if(e.getNextException() instanceof UnknownHostException){
               throw new MailRuntimeException("服务器地址配置错误，请检查地址是否正确！",e.getMessage());
           }
           throw new MailRuntimeException("无法从服务器上接收邮件，未知错误！",e.getMessage());
       }finally{
           if (folder != null) {
               try {
                   if(folder.isOpen()){
                       folder.close(false);
                   }
               } catch (MessagingException e) {}
           }
           if(store!=null){
               try {store.close();} catch (MessagingException e) {}
           }
       }
       return mailInfoList;
   }
   
   private static List<MailInfo> receiveMailsForExchange(MailProfile profile){
       List<MailInfo> mailInfos = new ArrayList<MailInfo>();
       try{
           ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
           String [] arr = profile.getEmail().split("@");
           ExchangeCredentials credentials = new WebCredentials(arr[0], profile.getPlaintextPassword(), arr[1]);
           service.setCredentials(credentials);
           //设置Exchange连接的服务器地址
           service.setUrl(new URI("https://"+profile.getReceiveHostAddr()+"/EWS/Exchange.asmx"));
            //绑定邮箱
           microsoft.exchange.webservices.data.core.service.folder.Folder inbox = microsoft.exchange.webservices.data.core.service.folder.Folder.bind(service, WellKnownFolderName.Inbox);
           //获取邮箱文件数量
           int count = inbox.getTotalCount();
           Calendar cal = Calendar.getInstance();
           cal.add(Calendar.DATE,-1);
           SearchFilter sf = new SearchFilter.IsGreaterThanOrEqualTo(EmailMessageSchema.DateTimeSent, cal.getTime());
           //获取邮箱列表
           FindItemsResults<Item> findResults = service.findItems(WellKnownFolderName.Inbox, sf, new ItemView(count));
           for (Item item : findResults.getItems()) {
               EmailMessage message = EmailMessage.bind(service, item.getId());
               
               MailInfo mailInfo = new MailInfo();
               
               //消息id
               String messageId = item.getId().getUniqueId();
               mailInfo.setMessageId(messageId);
               mailInfo.setEmail(profile.getEmail());
               mailInfo.setUsercode(profile.getUsercode());
               //发送人
               String from = message.getFrom().getAddress();
               mailInfo.setSender(from);
               mailInfo.setSendType(MailConstants.SEND_TYPE_NORMAL);
               //收件人
               String to = getReceiveAddress(message.getToRecipients());
               mailInfo.setReceiver(to);
               if(StringUtils.isNotEmpty(to)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_NORMAL);
               }
               //抄送
               String cc = getReceiveAddress(message.getCcRecipients());
               mailInfo.setCopyer(cc);
               if(StringUtils.isNotEmpty(cc)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_COPY);
               }
               //密送
               String bcc = getReceiveAddress(message.getBccRecipients());
               mailInfo.setSecreter(bcc);
               if(StringUtils.isNotEmpty(bcc)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_SECRET);
               }
               //发送时间
               mailInfo.setSendTime(message.getDateTimeSent());
               mailInfo.setCreateTime(message.getDateTimeCreated());
               //位置
               mailInfo.setLocation(MailConstants.LOCATION_INBOX);
               //主题
               String subject = message.getSubject();
               mailInfo.setSubject(subject);
               //获取内容
               
               mailInfo.setContent(message.getBody().toString());
               //附件
               if(message.getHasAttachments()){
                   List<MailAttachment> mailAttachments = new ArrayList<MailAttachment>();
                   
                   List<Attachment> attachs = message.getAttachments().getItems();
                   for(Attachment f : attachs){
                       if(f instanceof FileAttachment){
                           MailAttachment affixFile = new MailAttachment();
                           affixFile.setFileName(f.getName());
                           affixFile.setPath(IdGen.uuid()+affixFile.getExtensionName());
                           affixFile.setFileSize(Long.valueOf(f.getSize()));
                           
                           
                           File file = new File(affixFile.getAbsolutePath());
                           if(!file.getParentFile().exists()){
                               file.getParentFile().mkdirs();
                           }
                           ((FileAttachment)f).load(file.getPath());
                           
                           mailAttachments.add(affixFile);
                       }
                   }
                   mailInfo.setAttachments(mailAttachments);
                   mailInfo.setHasAttachment(MailConstants.BOOL_TRUE_ALIAS);
               }
               mailInfos.add(mailInfo);
               
           }
       }catch(Exception e){
           if(e.getMessage()!=null && e.getMessage().indexOf("Unauthorized")>-1){
               throw new MailRuntimeException("认证失败，请检测用户名和密码是否正确！",e.getMessage());
           }
           throw new MailRuntimeException("无法从服务器上接收邮件，未知错误！",e.getMessage());
       }
       return mailInfos;
   }
   
   /**
    * 解析邮件
    * @param email
    * @param usercode
    * @param messages
    * @return
    */
   private static List<MailInfo> parseMessage(MailProfile profile, Folder folder,Message []messages){
       if(messages == null || messages.length==0){
           return null;
       }
       List<MailInfo> mailInfoList = new ArrayList<MailInfo>();
       try{
           for(Message msg : messages){
               MimeMessage mimeMsg = (MimeMessage)msg; 
               MailInfo mailInfo = new MailInfo();
               
               //消息id
               String messageId = getMessageID(folder, profile.getReceiveHostType(), mimeMsg);
               mailInfo.setMessageId(messageId);
               mailInfo.setEmail(profile.getEmail());
               mailInfo.setUsercode(profile.getUsercode());
               //发送人
               String from = getFrom(mimeMsg);
               mailInfo.setSender(from);
               mailInfo.setSendType(MailConstants.SEND_TYPE_NORMAL);
               //收件人
               String to = getReceiveAddress(mimeMsg,Message.RecipientType.TO);
               mailInfo.setReceiver(to);
               if(StringUtils.isNotEmpty(to)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_NORMAL);
               }
               //抄送
               String cc = getReceiveAddress(mimeMsg,Message.RecipientType.CC);
               mailInfo.setCopyer(cc);
               if(StringUtils.isNotEmpty(cc)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_COPY);
               }
               //密送
               String bcc = getReceiveAddress(mimeMsg,Message.RecipientType.BCC);
               mailInfo.setSecreter(bcc);
               if(StringUtils.isNotEmpty(bcc)){
                   mailInfo.setOwnType(MailConstants.OWN_TYPE_SECRET);
               }
               //发送时间
               mailInfo.setSendTime(mimeMsg.getSentDate());
               mailInfo.setCreateTime(mimeMsg.getSentDate());
               //位置
               mailInfo.setLocation(MailConstants.LOCATION_INBOX);
               //主题
               String subject = decodeText(mimeMsg.getSubject());
               mailInfo.setSubject(subject);
               //获取内容
               StringBuffer content = new StringBuffer();
               extractMailTextContent(mimeMsg,content);
               mailInfo.setContent(content.toString());
               //附件
               if(isContainAttachment(mimeMsg)){
                   List<MailAttachment> attachments = extractMailAttachment(mimeMsg);
                   mailInfo.setAttachments(attachments);
                   mailInfo.setHasAttachment(MailConstants.BOOL_TRUE_ALIAS);
               }
               mailInfoList.add(mailInfo);
           }
       }catch(Exception e){
           throw new MailRuntimeException("解析邮件失败",e.getMessage());
       }
       return mailInfoList;
   }
   /**
    * 无论是pop3还是imap都没有一个好的方法只接收新邮件 
    * 我们只能拿到邮件列表后自几判断，大多是通过邮件的UID进行判断 
    * 注意UID与MessageID并非一个东西，UID是邮箱用来标识你这个账户的每一封邮件的东西，
    * 而MessageID是发送邮件的时候生成的唯一ID，也有可能发送没有你的接收邮箱自己生成，或者是javamail生成的，总是取messageid需要下载邮件的头，
    * 这样有联网操作会很慢的，所以我们只需要存储下来uid就好了，
    * 记得保存的时候按照邮箱存储，因为不同的邮箱uid会重复的，
    * 至于规律最好别去参考有增的就有减的，所以还是安心的遍历吧，反正很快 
    * @param inboxFolder
    * @param protocolType
    * @param mimeMsg
    * @return
    */
   private static String getMessageID(Folder inboxFolder,String protocolType,MimeMessage mimeMsg){
       try {
           if(MailConstants.MAIL_PROTOCOL_TYPE_POP3.equals(protocolType)){
               POP3Folder inbox = (POP3Folder) inboxFolder; 
               return inbox.getUID(mimeMsg);
           }
           if(MailConstants.MAIL_PROTOCOL_TYPE_IMAP.equals(protocolType)){
               IMAPFolder inbox = (IMAPFolder) inboxFolder;
               return String.valueOf(inbox.getUID(mimeMsg));
           }
       } catch (MessagingException e) {
           throw new MailRuntimeException("无法获取消息的UID",e.getMessage());
       }
       throw new IllegalArgumentException("未知协议类型，无法获取消息的UID");
   }
   /**
    * 抽取附件
    * @param part
    * @return
    * @throws MessagingException
    * @throws IOException
    */
   private static List<MailAttachment> extractMailAttachment(Part part){
       List<MailAttachment> attachments = new ArrayList<MailAttachment>();
       try{
           if (part.isMimeType("multipart/*")) {    
               Multipart multipart = (Multipart) part.getContent();//复杂体邮件
             //复杂体邮件包含多个邮件体    
               int partCount = multipart.getCount(); 
               for (int i = 0; i < partCount; i++) {
                 //获得复杂体邮件中其中一个邮件体
                  BodyPart bodyPart = multipart.getBodyPart(i);    
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                  String disp = bodyPart.getDisposition();
                  if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {    
                      MailAttachment attachment = new MailAttachment();
                      attachment.setFileName(bodyPart.getFileName());
                      attachment.setPath(IdGen.uuid()+attachment.getExtensionName());
                      attachment.setFileSize(Long.valueOf(bodyPart.getSize()));
                      
                      attachments.add(attachment);
                  } else if (bodyPart.isMimeType("multipart/*")) { 
                     List<MailAttachment> partAttachments = extractMailAttachment(bodyPart);
                     attachments.addAll(partAttachments);
                  } else {  
                      String contentType = bodyPart.getContentType();    
                      if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {    
                          MailAttachment attachment = new MailAttachment();
                          attachment.setFileName(bodyPart.getFileName());
                          attachment.setPath(IdGen.uuid()+attachment.getExtensionName());
                          attachment.setFileSize(Long.valueOf(bodyPart.getSize()));
                          
                          saveFile(bodyPart.getInputStream(), attachment);
                          attachments.add(attachment);
                      }
                  } 
               }
           }else if (part.isMimeType("message/rfc822")) { 
               List<MailAttachment> partAttachments = extractMailAttachment((Part) part.getContent());
               attachments.addAll(partAttachments);
           }
       }catch(Exception e){
           throw new MailRuntimeException("拉取邮箱服务器上邮件时，解析附件失败！",e.getMessage());
       }
       return attachments;
   }
   
   /**
    * 保存文件
    * @param is
    * @param attachment
    * @throws FileNotFoundException
    * @throws IOException
    */
   private static void saveFile(InputStream is, MailAttachment attachment){
       try{
           File affixFile = new File(attachment.getAbsolutePath());
           if(!affixFile.getParentFile().exists()){
               affixFile.getParentFile().mkdirs();
           }
           BufferedInputStream bis = new BufferedInputStream(is);    
           BufferedOutputStream bos = new BufferedOutputStream(    
                   new FileOutputStream(new File(attachment.getAbsolutePath())));    
           int len = -1;    
           while ((len = bis.read()) != -1) {    
               bos.write(len);  
               bos.flush();  
           }  
           bos.close();    
           bis.close();   
       }catch(Exception e){
           throw new MailRuntimeException("拉取邮箱服务器上邮件时，附件存储失败！",e.getMessage());
       }
       
   }  
   /**  
    * 判断邮件中是否包含附件  
    * @param msg 邮件内容  
    * @return 邮件中存在附件返回true，不存在返回false  
    * @throws MessagingException  
    * @throws IOException  
    */    
   private static boolean isContainAttachment(Part part){    
       boolean flag = false; 
       try{
           if (part.isMimeType("multipart/*")) {    
               MimeMultipart multipart = (MimeMultipart) part.getContent();    
               int partCount = multipart.getCount();    
               for (int i = 0; i < partCount; i++) {    
                   BodyPart bodyPart = multipart.getBodyPart(i);    
                   String disp = bodyPart.getDisposition();    
                   if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {    
                       flag = true;    
                   } else if (bodyPart.isMimeType("multipart/*")) {    
                       flag = isContainAttachment(bodyPart);    
                   } else {    
                       String contentType = bodyPart.getContentType();    
                       if (contentType.indexOf("application") != -1) {    
                           flag = true;    
                       }      
                           
                       if (contentType.indexOf("name") != -1) {    
                           flag = true;    
                       }     
                   }    
                       
                   if (flag) break;    
               }    
           } else if (part.isMimeType("message/rfc822")) {    
               flag = isContainAttachment((Part)part.getContent());    
           }  
       }catch(Exception e){
           throw new MailRuntimeException("无法判断邮件是否带有附件！",e.getMessage());
       }
        
       return flag;    
   }
   
   /** 
    * 获得邮件文本内容
    * 
    *  这里有个问题用outlook或foxmail等客户端发送的邮件有2个重复的部分，一部分是text/plain 
    *  另一个部分是text/html,为了防止重复，我们只接受html的那部分
    * @param part 邮件体 
    * @param content 存储邮件文本内容的字符串 
    * @throws MessagingException 
    * @throws IOException 
    * 
    */  
   private static void extractMailTextContent(Part part, StringBuffer content) {  
       try{
           //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
           boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
           if (part.isMimeType("text/html") && !isContainTextAttach) {  
               content.append(part.getContent().toString());  
           } else if (part.isMimeType("message/rfc822")) {   
               extractMailTextContent((Part)part.getContent(),content);  
           } else if (part.isMimeType("multipart/*")) {  
               Multipart multipart = (Multipart) part.getContent();  
               int partCount = multipart.getCount();  
               for (int i = 0; i < partCount; i++) {  
                   BodyPart bodyPart = multipart.getBodyPart(i);  
                   extractMailTextContent(bodyPart,content);  
               }  
           }  
       }catch(Exception e){
           throw new MailRuntimeException("获取邮件内容失败！",e.getMessage());
       }
   }  
   
   /**
   * 文本解码  
   * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本  
   * @return 解码后的文本  
   * @throws UnsupportedEncodingException  
   */    
  private static String decodeText(String encodeText){  
      try{
          if (encodeText == null || "".equals(encodeText)) {    
              return "";    
          } else {  
              return MimeUtility.decodeText(encodeText);    
          }    
      }catch(UnsupportedEncodingException e){
          throw new MailRuntimeException("获取邮件时，文本解码失败！",e.getMessage());
      }
      
  }    
   
   /**  
    * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人  
    * <p>Message.RecipientType.TO  收件人</p>  
    * <p>Message.RecipientType.CC  抄送</p>  
    * <p>Message.RecipientType.BCC 密送</p>  
    * @param msg 邮件内容  
    * @param type 收件人类型  
    * @return 收件人1 <邮件地址1>;收件人2 <邮件地址2>; ...  
    * @throws MessagingException  
    */    
   private static String getReceiveAddress(MimeMessage msg, Message.RecipientType type){    
       StringBuffer receiveAddress = new StringBuffer();    
       try{
           Address[] addresss = null;    
           if (type == null) {    
               addresss = msg.getAllRecipients();    
           } else {    
               addresss = msg.getRecipients(type);    
           }    
               
           if (addresss == null || addresss.length < 1)    
               return null;    
           for (Address address : addresss) {    
               InternetAddress internetAddress = (InternetAddress)address;    
               receiveAddress.append(internetAddress.toUnicodeString()).append(";");    
           }    
               
           receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个分号    
               
           return receiveAddress.toString();
       }catch(MessagingException e){
           throw new MailRuntimeException("获取收件人失败！",e.getMessage());
       }
   } 
   
   private static String getReceiveAddress(EmailAddressCollection collection){
       StringBuffer receiveAddress = new StringBuffer();    
       if(collection!=null&&!collection.getItems().isEmpty()){
           for(EmailAddress address:collection.getItems()){
               receiveAddress.append(address.getAddress()).append(";");
           }
           receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个分号    
       }
       return receiveAddress.toString();
   }
   /**
    * 获取发件人
    * @param msg
    * @return
 * @throws MessagingException 
    */
   private static String getFrom(MimeMessage msg){
       try{
           Address[] froms = msg.getFrom();    
           if (froms.length < 1)    
               return null;   
           InternetAddress netAddr = (InternetAddress) froms[0];
           return netAddr.getPersonal()+"<" + netAddr.getAddress() + ">";
       }catch(MessagingException e){
           throw new MailRuntimeException("获取发件人失败！",e.getMessage());
       }
   }
}
