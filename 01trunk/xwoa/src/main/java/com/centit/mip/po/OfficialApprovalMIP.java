package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OfficialApprovalMIP {
    
    private static final long serialVersionUID = 1L;
    
    
    @Expose @Since(1.0)
    private String stepid;// 步骤id
    @Expose @Since(1.0)
    private String stepname;// 步骤名称
    @Expose @Since(1.0)
    private String username;// 处理人
    
    @Expose @Since(1.0)
    private String operatdate;// 处理时间
    @Expose @Since(1.0)
    private String option;// 处理意见
    @Expose @Since(1.0)
    private String docurl;//附件地址
    @Expose @Since(1.0)
    private String filePw;


    
    
    
   
    @Expose (serialize = false) 
    private String type;//SW收文FW发文SQ内部事项DB督办。。。值为各业务流水的前缀
    @Expose (serialize = false) 
    private String messageitemguid;//工作项唯一消息id
    
    
    public void setStepid(String stepid) {
        this.stepid = stepid;
    }
    public void setStepname(String stepname) {
        this.stepname = stepname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
  
    public void setOperatdate(String operatdate) {
        this.operatdate = operatdate;
    }
    public void setOption(String option) {
        this.option = option;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setMessageitemguid(String messageitemguid) {
        this.messageitemguid = messageitemguid;
    }
    public String getStepid() {
        return stepid;
    }
    public String getStepname() {
        return stepname;
    }
    public String getUsername() {
        return username;
    }
 
    public String getOperatdate() {
        return operatdate;
    }
    public String getOption() {
        return option;
    }
    public String getType() {
        return type;
    }
    public String getMessageitemguid() {
        return messageitemguid;
    }
    public String getDocurl() {
        return docurl;
    }
    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }
    public String getFilePw() {
        return filePw;
    }
    public void setFilePw(String filePw) {
        this.filePw = filePw;
    }

}
