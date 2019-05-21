package com.centit.webservice.server.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.webservice.pojo.SysLogDTO;
import com.centit.webservice.server.TaijiLogRestService;
import com.centit.webservice.util.RESTClient;

/**
 * 
 * 太极应用日志接口服务
 * 
 * @author Ghost
 * @create 2017年2月21日
 * @version
 */
public class TaijiAppLogRestServiceImpl implements TaijiLogRestService{
    private Logger log = Logger.getLogger(TaijiAppLogRestServiceImpl.class);
    @Override
    public void sendLog() {
       
        String jsonStr = JSONObject.toJSONString("");
        try{
           String result = RESTClient.post(basePath+"/log/applog/post", jsonStr);
           JSONObject jo = JSONObject.parseObject(result);
           if(jo.get("result")==null || "1".equals(jo.getString("result"))){
               log.error("推送应用操作日志【"+jsonStr+"】,接口返回失败。");
           }
        }catch(Exception e){
            log.error("推送应用操作日志【"+jsonStr+"】时发生异常:"+e.getMessage());
        }
    }

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
                log.error("获取应用操作日志【"+paramJson+"】,接口返回失败。");
            }
            
            JSONArray ja = jo.getJSONArray("list");
            if(ja != null){
                List<SysLogDTO> logDtos = JSONArray.parseArray(ja.toJSONString(), SysLogDTO.class);
                return logDtos;
            }
        }catch(Exception e){
            log.error("获取应用操作日志【"+paramJson+"】,发生异常:"+e.getMessage());
        }
        return null;
    }

}
