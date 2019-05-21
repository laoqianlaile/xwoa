package com.centit.webservice.server.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.service.AccessLogManager;
import com.centit.webservice.pojo.SysLogDTO;
import com.centit.webservice.server.TaijiLogRestService;
import com.centit.webservice.util.RESTClient;
import com.google.gson.Gson;

/**
 * 
 * 太极系统日志接口
 * 
 * @author Ghost
 * @create 2017年2月21日
 * @version
 */
public class TaijiSysLogRestServiceImpl implements TaijiLogRestService {
    private Logger log = Logger.getLogger(TaijiSysLogRestServiceImpl.class);
    private AccessLogManager accessLogManager;
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    /**
     * 推送数据到太极日志管理系统
     */
    @Override
    public void sendLog() {
        List<Object[]> listObject=accessLogManager.getSysLogDTOlist();
        if(listObject!=null && listObject.size()>0){
            for (int i = 0; i < listObject.size(); i++) {
                Object[] O = (Object[]) listObject.get(i);
                SysLogDTO po = new SysLogDTO();
                po.setKey((String)O[0]);
                po.setLogLevel((String)O[1]);
                po.setUserId((String)O[2]);
                po.setLogModular((String)O[3]);
                po.setLogFunction((String)O[4]);
                po.setOperatType((String)O[5]);
                po.setLogIp((String)O[6]);
                po.setLogClass((String)O[7]);
                po.setLogMethod((String)O[8]);
                po.setSysCode("oa_sys");
                Gson gson=new Gson();
                String jsonStr =gson.toJson(po).toString();
                try{
                   //String result = RESTClient.post(basePath+"/log/wdrmpapplog/post", jsonStr);
                   String result=post(basePath+"/log/wdrmpapplog/post", jsonStr);
                   JSONObject jo = JSONObject.parseObject(result);
                   if(jo.get("success")==null || "false".equals(jo.getString("success"))){
                       log.error("推送系统日志【"+jsonStr+"】,接口返回失败。");
                   }else{//推送成功，对日志信息进行处理
                       FAccessLog info=new FAccessLog();
                       info.setId(Long.valueOf(String.valueOf(O[9])));
                       info.setIp((String)O[6]);
                       info.setUsercode(po.getUserId());
                       info.setAccesstype("1");
                       info.setAccesstime(new Date());
                       info.setIsUpload("1");//同步成功
                       accessLogManager.saveObject(info);
                   }
                }catch(Exception e){
                    log.error("推送系统日志【"+jsonStr+"】时发生异常:"+e.getMessage());
                    FAccessLog info=new FAccessLog();
                    info.setId(Long.valueOf(String.valueOf(O[9])));
                    info.setIp((String)O[6]);
                    info.setUsercode(po.getUserId());
                    info.setAccesstime(new Date());
                    info.setAccesstype("1");
                    info.setIsUpload("2");//同步失败
                    accessLogManager.saveObject(info);
                }
            }
        }
    }
    /**
     * 推送数据具体实现过程
     * @param url
     * @param jsonString
     * @return
     */
    public static String post(String url, String jsonString) {
        String response = "";
        try {
            StringEntity paramesEntity = new StringEntity(jsonString, "utf-8");
            paramesEntity.setContentType("application/json;charset=utf-8");
            HttpPost post = new HttpPost(url);
            post.setEntity(paramesEntity);
            HttpResponse postResponse = httpClient.execute(post);
            HttpEntity postEntity = postResponse.getEntity();
            if (null != postEntity) {
                InputStream inStream = postEntity.getContent();
                response = convertStreamToString(inStream);
                post.abort();
                System.out.println(response);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * 转换stream为string字符串
     * 
     * @param is
     *            输入流
     * @return 流形成的字符串
     */
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**
     * 获取系统日志信息
     */
    @Override
    public List<SysLogDTO> findLogs(Map<String, Object> param) {
        if(param == null){
            return null;
        }
        String paramJson = JSONObject.toJSONString(param);
        try{
            String result = RESTClient.get(basePath+"/log/applog/get");  
            JSONObject jo = JSONObject.parseObject(result);
            if(jo.get("result")==null || "1".equals(jo.getString("result"))){
                log.error("获取系统日志【"+paramJson+"】,接口返回失败。");
            }
            
            JSONArray ja = jo.getJSONArray("list");
            if(ja != null){
                List<SysLogDTO> logDtos = JSONArray.parseArray(ja.toJSONString(), SysLogDTO.class);
                return logDtos;
            }
        }catch(Exception e){
            log.error("获取系统日志【"+paramJson+"】,发生异常:"+e.getMessage());
        }
        return null;
    }
    public AccessLogManager getAccessLogManager() {
        return accessLogManager;
    }
    public void setAccessLogManager(AccessLogManager accessLogManager) {
        this.accessLogManager = accessLogManager;
    }
    
}
