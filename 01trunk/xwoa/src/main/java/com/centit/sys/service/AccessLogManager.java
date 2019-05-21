package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FAccessLog;
import com.centit.webservice.pojo.SysLogDTO;

public interface AccessLogManager  extends BaseEntityManager<FAccessLog> {
    public Long getNextLogId();
    
    /**
     * 获取在线用户数
     * @return
     */
    public int getUserCountOnline();
    
    /**
     * 获取限定时间里没有操作的日志
     * @param limitMinutes
     * @return
     */
    public List<FAccessLog> findAccessLogUndo(int limitMinutes);
    
    /**
     * 获取最后一次登录时间
     * @param usercode
     * @return
     */
    public String fingLastLoginTime(String usercode);
    /**
     * 获取应用日志信息
     * @return
     */
    public List<Object[]> getSysLogDTOlist();
}
