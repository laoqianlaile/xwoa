package com.centit.powerruntime.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OptJspInfo {
    /**
     * iFrame urls
     */
    private List<String> urls;
    
    private Map<String,OptHtmlFrameInfo> frameMap;
    
    private List<OptHtmlFrameInfo> frameList;
    /**
     * 页面主题
     */
    private String title;
    
    public OptJspInfo(){
        
    }
    
    public OptJspInfo(String title){
        this.setTitle(title);
    }
    
    public List<String> getUrls() {
        return urls;
    }
    
    public void setUrls(List<String> urls) {
        if(urls==null)
            urls = new ArrayList<String>();
        this.urls = urls;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getUrlCount() {
        if(urls==null)
            return 0;
        return urls.size();
    }

    public Map<String, OptHtmlFrameInfo> getFrameMap() {
        return frameMap;
    }

    public void setFrameMap(Map<String, OptHtmlFrameInfo> frameMap) {
        this.frameMap = frameMap;
    }

    public List<OptHtmlFrameInfo> getFrameList() {
        return frameList;
    }

    public void setFrameList(List<OptHtmlFrameInfo> frameList) {
        this.frameList = frameList;
    }
}
