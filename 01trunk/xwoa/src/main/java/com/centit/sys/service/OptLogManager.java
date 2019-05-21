package com.centit.sys.service;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.OptLog;

public interface OptLogManager extends BaseEntityManager<OptLog> {

    /**
     * 清理此日期之前的日志信息
     * 
     * @param date
     */
    void deleteByCheckUp(Date date);
    
    
    List<String> listOptIds();
}
