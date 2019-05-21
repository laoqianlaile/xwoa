package com.centit.sys.po;

public class VOptTree {
    private String optID;
    private String preOptID;
    private String optName;
    
    public VOptTree(){
        
    }
    
    public VOptTree (String optID) {
        this.optID = optID;
    }
    
    public String getOptID() {
        return optID;
    }
    
    public void setOptID(String optID) {
        this.optID = optID;
    }
    
    public String getPreOptID() {
        return preOptID;
    }
    
    public void setPreOptID(String preOptID) {
        this.preOptID = preOptID;
    }
    
    public String getOptName() {
        return optName;
    }
    
    public void setOptName(String optName) {
        this.optName = optName;
    }
}
