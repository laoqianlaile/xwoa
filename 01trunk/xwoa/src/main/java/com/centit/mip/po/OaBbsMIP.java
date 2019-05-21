package com.centit.mip.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * 生活服务
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2017年2月7日
 * @version
 */

public class OaBbsMIP {
    
    private static final long serialVersionUID = 1L;
    
    @Expose  @Since(1.0)
    private String id;// id
    @Expose  @Since(1.0)
    private String title;// 主题
    @Expose  @Since(1.0)
    private String publishtime;// 发布时间
    @Expose  @Since(1.0)
    private String publisher;// 发布人
    @Expose  @Since(1.0)
    private String viewnum;// 访问量
  
  
    @Expose  @Since(2.0) 
    private String publishdept;
    @Expose  @Since(2.0) 
    private String content;
    
    @Expose  @Since(2.0) 
    private String state;
    
   /**
    * 用户唯一ID
    */
    @Expose (serialize = false) 
    private String userid;
    
    /**
     * 检索关键字
     */
    @Expose (serialize = false) 
    private String keyword;
    
    /**
     * 当前检索时间，分页使用
     */
    @Expose (serialize = false) 
    private String currentdatetime;
    
    /**
     * 检索记录条数
     */
    @Expose (serialize = false) 
    private String pagesize;


    /**
     * 生活服务类别
     */

    @Expose
    private String type;
    
    /**
     * 主题ID
     */
    @Expose (serialize = false) 
    private String themeNo;
    @Expose (serialize = false) 
    private String layoutNo;
    @Expose (serialize = false) 
    private String subLayoutTitle;
    @Expose (serialize = false) 
    private String ip;
    @Expose (serialize = false) 
    private String bodyContent;
    
    /**
     * 发布留言
     */
    @Expose (serialize = false) 
    private String sendTime;
    
    /**
     * 发布留言回复
     */
    @Expose (serialize = false) 
    private String leaveid;
    
    
    
    
    
    
    @Expose @Since(2.1)
    public List<OaBbsLeaverMessageMIP> leaverMessageList = new ArrayList<OaBbsLeaverMessageMIP>();
    
    /**
     * 留言回复
     * TODO Class description should be added
     * 
     * @author lq
     * @create 2017年2月8日
     * @version
     */
    public static class OaBbsLeaverMessageMIP {
        @Expose
        @Since(2.1)
        private String replayid;
        @Expose
        @Since(2.1)
        private String id;

        @Expose  @Since(1.1)
        private String publishtime;// 发布时间
        @Expose  @Since(1.1)
        private String publisher;// 发布人
      
        @Expose 
        @Since(2.1) 
        private String content;
        
        @Expose  @Since(2.1) 
        private String state;
        
        public String getReplayid() {
            return replayid;
        }

        public void setReplayid(String replayid) {
            this.replayid = replayid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
        

    }
    
    
    /**
     * 服务类别：出行CX、住房ZF、商品SP、娱乐YL、休闲XX、其他OT
     */
    public final static Map<String, String> NAV_BBSTHEME_LAYOUTNO = new HashMap<String, String>() {  
        {  
            put("CX", "SM000000000065");  
            put("ZF", "SM000000000066");  
            put("SP", "SM000000000067");  
            put("YL", "SM000000000068");  
            put("XX", "SM000000000069");  
            put("QT", "SM000000000081"); 
            
            put("SM000000000065", "CX");  
            put("SM000000000066", "ZF");  
            put("SM000000000067", "SP");  
            put("SM000000000068", "YL");  
            put("SM000000000069", "XX");  
            put("SM000000000081", "QT");  
          
        }  
    }; 
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getViewnum() {
        return viewnum;
    }

    public void setViewnum(String viewnum) {
        this.viewnum = viewnum;
    }

    public String getPublishdept() {
        return publishdept;
    }

    public void setPublishdept(String publishdept) {
        this.publishdept = publishdept;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getThemeNo() {
        return themeNo;
    }

    public void setThemeNo(String themeNo) {
        this.themeNo = themeNo;
    }

    public String getLayoutNo(String type) {
        
        return layoutNo;
    }

    public void setLayoutNo(String layoutNo) {
        this.layoutNo = layoutNo;
    }

    public List<OaBbsLeaverMessageMIP> getLeaverMessageList() {
        return leaverMessageList;
    }

    public void setLeaverMessageList(List<OaBbsLeaverMessageMIP> leaverMessageList) {
        this.leaverMessageList = leaverMessageList;
    }

    public String getLayoutNo() {
        return layoutNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubLayoutTitle() {
        return subLayoutTitle;
    }

    public void setSubLayoutTitle(String subLayoutTitle) {
        this.subLayoutTitle = subLayoutTitle;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }


}
