package com.centit.webservice.server;

import java.util.List;
import java.util.Map;

import com.centit.webservice.pojo.SysLogDTO;

/**
 * 
 * 太极日志服务接口
 * 
 * @author Ghost
 * @create 2017年2月21日
 * @version
 */
public interface TaijiLogRestService {
    /**
     * 服务基本路径
     */
    String basePath = "http://192.16.199.10:8050/log";
    /**
     * 推送日志
     * @param dto
     * @return 
     */
    void sendLog();
    
    /**
     * 获取日志列表
     * @param param
     * @return  
     */
    List<SysLogDTO> findLogs(Map<String,Object> param);
}
