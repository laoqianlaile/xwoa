package com.centit.bbs.util;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * 获取客户端常用的ip工具类
 * 
 * @author lay
 * @create 2015年11月21日
 * @version
 */
public class IPUtil {
    /**
     * 获取真实的ip地址
     * @param request
     * @return
     */
   public static String getIRealIPAddr(HttpServletRequest  request){
       String ip = request.getHeader("x-forwarded-for"); 
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip))    {   
         ip = request.getHeader("Proxy-Client-IP"); 
     } 
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)   || "null".equalsIgnoreCase(ip)) {  
       ip = request.getHeader("WL-Proxy-Client-IP"); 
     } 
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)    || "null".equalsIgnoreCase(ip)) { 
       ip = request.getRemoteAddr(); 
     } 
     return ip; 
   }
}
