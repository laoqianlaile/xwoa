package com.centit.dispatchdoc.po;

public class VTransactUser implements java.io.Serializable {
    private static final long serialVersionUID =  1L;
    
    private String unitname;
    private String usercode;
    private String taskState;
    private String nodename;
    
    
    public String getUnitname() {
        return unitname;
    }
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getTaskState() {
        return taskState;
    }
    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
    public String getNodename() {
        return nodename;
    }
    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    
}
