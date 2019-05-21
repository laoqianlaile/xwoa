package com.centit.app.po;

import java.util.Date;

//首页数据显示
public class Dashboard {
    private Long id;
    private String name;
    private String sum; 
    private String usercode;
    private String flowcode;
    private String itemType;
    private String type;
    private Date creattime;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSum() {
        return sum;
    }
    public void setSum(String sum) {
        this.sum = sum;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getFlowcode() {
        return flowcode;
    }
    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getCreattime() {
        return creattime;
    }
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

  
}
