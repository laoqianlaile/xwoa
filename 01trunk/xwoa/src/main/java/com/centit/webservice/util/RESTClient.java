package com.centit.webservice.util;

import org.springframework.web.client.RestTemplate;
/**
 * 
 * RestTemplate调用REST资源
 * 
 * 通常我们用httpclient来模拟http请求，而对于restfule，spring提供了RestTemplate来供我们调用rest接口。
 * 两者而言Spring RestTemplate比httpClient更优雅的Restful URL访问
 * @author Ghost
 * @create 2017年2月20日
 * @version
 */
public class RESTClient {
    private static class SingletonRestTemplate {  
        /** 
         * 单例对象实例 
         */  
        static final RestTemplate INSTANCE = new RestTemplate();  
    }  
  
    private RESTClient() {  
    }  
      
    /** 
     * 单例实例 
     */  
    public static RestTemplate getInstance() {  
        return SingletonRestTemplate.INSTANCE;  
    }  
    
      
    /** 
     * get根据url获取对象 
     */  
    public static String get(String url) {  
        return RESTClient.getInstance().getForObject(url, String.class,  
                new Object[] {});  
    }  
  
    /** 
     * getById根据ID获取对象 
     */  
    public static String getById(String url, String id) {  
        return RESTClient.getInstance().getForObject(url, String.class,  
                id);  
    }  
  
    /** 
     * post提交对象 
     */  
    public static String post(String url, String data) {  
        return RESTClient.getInstance().postForObject(url, null,  
                String.class, data);  
    }  
  
    /** 
     * put修改对象 
     */  
    public static void put(String url, String data) {  
        RESTClient.getInstance().put(url, null, data);  
    }  
  
    /** 
     * delete根据ID删除对象 
     */  
    public static void delete(String url, String id) {  
        RESTClient.getInstance().delete(url, id);  
    }  
}
