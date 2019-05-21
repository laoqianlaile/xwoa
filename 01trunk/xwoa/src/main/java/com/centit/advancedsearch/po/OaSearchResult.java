package com.centit.advancedsearch.po;

import com.centit.sys.service.CodeRepositoryUtil;

/**
 * 
 * 检索结果
 * 
 * @author Ghost
 * @create 2017年2月6日
 * @version
 */
public class OaSearchResult implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 业务id
     */
    private String djId;
    /**
     * 业务类型
     */
    private String itemType;
    /**
     * 标题
     */
    private String title;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文号
     */
    private String caseNo;
    
    private String creater;
    
    private String usercode;
    
    
    public String getCreater() {
        return creater;
    }
    public void setCreater(String creater) {
        this.creater = creater;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getItemType() {
        return itemType;
    }
    public String getItemTypeText(){
        if(itemType==null){
            return null;
        }else if("YJ".equals(itemType)){
            return "邮件";
        }else if("ZX".equals(itemType)){
            return "资讯";
        }else{
           return CodeRepositoryUtil.getValue("oa_ITEM_TYPE", itemType); 
        }
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getCaseNo() {
        return caseNo;
    }
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }
}
