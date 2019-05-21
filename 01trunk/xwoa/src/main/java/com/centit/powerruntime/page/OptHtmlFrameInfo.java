/**
 * 
 */
package com.centit.powerruntime.page;

/**
 * TODO Class description should be added
 * 
 * @author ljy
 * @create 2012-8-28
 * @version
 */
public class OptHtmlFrameInfo {

    private String frameId;
    private String frameLegend;
    private String frameSrc;
    private String frameWidth;
    private String frameHeight;
    private String frameScrolling; // yes , no

    public OptHtmlFrameInfo() {
        
    }
    
    public OptHtmlFrameInfo(String frameId, String frameLegend, String frameSrc, String frameWidth,
            String frameHeight, String frameScrolling) {
        this.frameId = frameId;
        this.frameLegend = frameLegend;
        this.frameSrc = frameSrc;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameScrolling = frameScrolling;
    }

    public String getFrameSrc() {
        return frameSrc;
    }

    public void setFrameSrc(String frameSrc) {
        this.frameSrc = frameSrc;
    }

    public String getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(String frameWidth) {
        this.frameWidth = frameWidth;
    }

    public String getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(String frameHeight) {
        this.frameHeight = frameHeight;
    }

    public String getFrameScrolling() {
        return frameScrolling;
    }

    public void setFrameScrolling(String frameScrolling) {
        this.frameScrolling = frameScrolling;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getFrameLegend() {
        return frameLegend;
    }

    public void setFrameLegend(String frameLegend) {
        this.frameLegend = frameLegend;
    }
}
