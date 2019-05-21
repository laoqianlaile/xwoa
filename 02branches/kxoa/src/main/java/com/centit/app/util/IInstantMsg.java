package com.centit.app.util;

import java.util.List;

/**
 * 
 * 即时消息发送接口
 * 
 * @author sx
 * @create 2012-12-12
 * @version
 */
public interface IInstantMsg {
    /**
     * 发送即时消息
     * 
     * @param userCodes
     *            接收消息的 usercode
     * @param message
     *            发送消息的字符串，使用json数据发送复杂数据类型
     * @param script
     *            接收message的js方法，为了区分，最好是带上js命名空间
     *            
     */
    
    void push(List<String> userCodes, String message, String script);

    void push(List<String> userCodes, String message);

    /**
     * 发送即时消息，收取消息人为当前系统中所有人
     * 
     * @param message
     *            发送消息的字符串，使用json数据发送复杂数据类型
     * @param script
     *            接收message的js方法，为了区分，最好是带上js命名空间
     */
    void pushAll(String message, String script);

    void pushAll(String message);

}
