package com.centit.sys.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentitMenu {
    
    private String id;
    
    private String pid;
    
    private String text;
    
    /**
     * open closed
     */
    private String state = "open";
    
    private boolean checked = false;
    
    private String icon;
    
    private String url;
    
    private List<CentitMenu> children = new ArrayList<CentitMenu>();
    
    private Map<String, String> attributes = new HashMap<String, String>();
    
    public CentitMenu(FOptinfo optInfo) {
        if (null == optInfo) return;
        
        this.id = optInfo.getOptid();
        this.pid = optInfo.getPreoptid();
        this.text = optInfo.getOptname();
        this.url = optInfo.getOpturl();
        
        this.attributes.put("pageType", optInfo.getPageType());
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<CentitMenu> getChildren() {
        return children;
    }

    public void setChildren(List<CentitMenu> children) {
        this.children = children;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
