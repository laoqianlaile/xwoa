package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaSmssendLog;

public interface OaSmssendLogManager extends BaseEntityManager<OaSmssendLog> {

    /**
     * 获取主键
     * @return
     */
    public String getNextValueOfSequence() ;
    
    /**
     * 保存对应的短信发送记录
     * @param oaSmssend
     */
    public void saveSMSLogs(OaSmssend oaSmssend);
}
