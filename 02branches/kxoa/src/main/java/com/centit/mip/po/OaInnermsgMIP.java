package com.centit.mip.po;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaInnermsgMIP {
    
    private static final long serialVersionUID = 1L;
    
    @Expose  
    @Since(1.0)
    private String msgid;//邮件id
    @Expose  
    @Since(1.0)
    private String msgtitle;//邮件标题
    
    @Expose  
    @Since(2.0)
    private String msgcontent;//邮件内容(HTML)
    
    @Expose  
    @Since(1.0)
    private String createuserid;//邮件发起人id
    
    @Expose  
    @Since(1.0)
    private String createusername;//邮件发起人姓名
    @Expose  
    @Since(1.0)
    private String createtime;//发起时间
   
    @Expose  
    @Since(2.0)
    private String replycount;//回复数
    @Expose  
    @Since(2.0)
    private String unreadcount;//未读回复数
    
    @Expose  
    @Since(1.0)
    private String readflag;//是否已读 0已读 1未读 
    
    @Expose  
    @Since(2.0)
    private  List<ReceiverMIP> receivers;
    @Expose  
    @Since(2.0)
    private List<AttachmentMIP> attachment;
    
    
    @Expose (serialize = false) 
    private String userid;//人员id
    @Expose (serialize = false) 
    private String keyword;//关键字，搜索条件使用
    @Expose (serialize = false) 
    private String currentdatetime;//当前检索时间，精确到秒或毫秒
    @Expose (serialize = false) 
    private String pagesize;//需要检索的记录条数
    @Expose (serialize = false) 
    private String type;//0全部1收件箱2发件箱
    @Expose (serialize = false) 
    private String isread;//0：已读 1：未读 2：全部
    @Expose (serialize = false) 
    private String  content;//内容
    @Expose (serialize = false) 
    private String replaymsgid;//回复邮件id
    @Expose (serialize = false) 
    private String title;//标题
    @Expose (serialize = false) 
    private String receiverids;//接收人，“,”分割
    @Expose  (serialize = false) 
    
    private String msgids;//邮件id
    
    @Expose (serialize = false)
    private List<AttachlistMIP> attachlist;
    
    public  static  class  ReceiverMIP{
        @Expose  
        @Since(2.0)
        private String  receiverid;
        @Expose  
        @Since(2.0)
        private String receivername;
        
        @Expose  
        @Since(2.0)
        private String readflag;
      
        public ReceiverMIP(String receiverid,String receivername,String readflag){
            this.receiverid=receiverid;
            this.receivername=receivername;
            this.readflag=readflag;
        }
        
        public String getReceiverid() {
            return receiverid;
        }
        public void setReceiverid(String receiverid) {
            this.receiverid = receiverid;
        }
        public String getReceivername() {
            return receivername;
        }
        public void setReceivername(String receivername) {
            this.receivername = receivername;
        }

        public String getReadflag() {
            return readflag;
        }

        public void setReadflag(String readflag) {
            this.readflag = readflag;
        }
        
        
    }
    
    public static class AttachmentMIP{
        @Expose  
        @Since(2.0)
        private String attachid;
        @Expose  
        @Since(2.0)
        private String attachtitle;
        @Expose  
        @Since(2.0)
        private String attachurl;
        
        public AttachmentMIP(String attachid,String attachtitle,String attachurl){
            
            this.attachid=attachid;
            this.attachtitle=attachtitle;
            this.attachurl=attachurl;
        }
 
        public String getAttachid() {
            return attachid;
        }
        public void setAttachid(String attachid) {
            this.attachid = attachid;
        }
        public String getAttachtitle() {
            return attachtitle;
        }
        public void setAttachtitle(String attachtitle) {
            this.attachtitle = attachtitle;
        }
        public String getAttachurl() {
            return attachurl;
        }
        public void setAttachurl(String attachurl) {
            this.attachurl = attachurl;
        }
        
        
        
    }
    
    public static class AttachlistMIP{
        @Expose (serialize = false) 
        private String attachtitle;
        @Expose (serialize = false) 
        private String attachtype;
        @Expose (serialize = false) 
        private String attachurl;
        
        public AttachlistMIP(String attachtitle,String attachtype,String attachurl){
            
            this.attachtitle=attachtitle;
            this.attachtype=attachtype;
            this.attachurl=attachurl;
        }
 
        public String getAttachtitle() {
            return attachtitle;
        }
        public void setAttachtitle(String attachtitle) {
            this.attachtitle = attachtitle;
        }
        
        public String getAttachtype() {
            return attachtype;
        }

        public void setAttachtype(String attachtype) {
            this.attachtype = attachtype;
        }

        public String getAttachurl() {
            return attachurl;
        }
        public void setAttachurl(String attachurl) {
            this.attachurl = attachurl;
        }
        
        
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getMsgtitle() {
        return msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getReadflag() {
        return readflag;
    }

    public void setReadflag(String readflag) {
        this.readflag = readflag;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public String getReplycount() {
        return replycount;
    }

    public void setReplycount(String replycount) {
        this.replycount = replycount;
    }

    public String getUnreadcount() {
        return unreadcount;
    }

    public void setUnreadcount(String unreadcount) {
        this.unreadcount = unreadcount;
    }

  

    public List<ReceiverMIP> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<ReceiverMIP> receivers) {
        this.receivers = receivers;
    }

    public List<AttachmentMIP> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<AttachmentMIP> attachment) {
        this.attachment = attachment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCurrentdatetime() {
        return currentdatetime;
    }

    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplaymsgid() {
        return replaymsgid;
    }

    public void setReplaymsgid(String replaymsgid) {
        this.replaymsgid = replaymsgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiverids() {
        return receiverids;
    }

    public void setReceiverids(String receiverids) {
        this.receiverids = receiverids;
    }

    public List<AttachlistMIP> getAttachlist() {
        return attachlist;
    }

    public void setAttachlist(List<AttachlistMIP> attachlist) {
        this.attachlist = attachlist;
    }

    public String getMsgids() {
        return msgids;
    }

    public void setMsgids(String msgids) {
        this.msgids = msgids;
    }

   
    
    
}
