package com.centit.mip.po;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 公示公告入网申请接口数据
 * @author gy
 * @create 2017年2月8日
 * @version
 */

public class OabookNetApplicationMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户唯一ID
	 */
	@Expose(serialize = false) 
	private String userid;
	/**
	 * 标题
	 */
	@Expose(serialize = false) 
    private String transAffairName;
	/**
	 * 公示公告时间
	 */
	@Expose(serialize = false) 
    private String applydate;
	/**
	 * 公示公告部门
	 */
	@Expose(serialize = false) 
    private String applyuser;
	/**
	 * 公示公告内容
	 */
    @Expose 
    private String remark_content;
    /**
     * 附件名称
     */
    @Expose  
    @Since(2.0)
    private String attachtype;
    /**
     * 附件类型
     */
    @Expose  
    @Since(2.0)
    private String attachtitle;
    /**
     * 附件地址
     */
    @Expose  
    @Since(2.0)
    private String attachurl;
    
    @Expose (serialize = false)
    private List<AttachlistMIP> attachlist;
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getTransAffairName() {
        return transAffairName;
    }
    public void setTransAffairName(String transAffairName) {
        this.transAffairName = transAffairName;
    }
    public String getApplydate() {
        return applydate;
    }
    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }
    
    public String getApplyuser() {
        return applyuser;
    }
    public void setApplyuser(String applyuser) {
        this.applyuser = applyuser;
    }
 
    public String getRemark_content() {
        return remark_content;
    }
    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
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
    
    public String getAttachtype() {
        return attachtype;
    }
    public void setAttachtype(String attachtype) {
        this.attachtype = attachtype;
    }
    public List<AttachlistMIP> getAttachlist() {
        return attachlist;
    }
    public void setAttachlist(List<AttachlistMIP> attachlist) {
        this.attachlist = attachlist;
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
        private String attachurl;
        @Expose (serialize = false) 
        private String attachtype;
        
        public AttachlistMIP(String attachtitle,String attachurl,String attachtype){
            
            this.attachtitle=attachtitle;
            this.attachurl=attachurl;
            this.attachtype=attachtype;
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

        public String getAttachtype() {
            return attachtype;
        }

        public void setAttachtype(String attachtype) {
            this.attachtype = attachtype;
        }
        
    
    }

   
	
  
}
