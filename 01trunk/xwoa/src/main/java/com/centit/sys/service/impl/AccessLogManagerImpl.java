package com.centit.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.AccessLogDao;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.util.DateUtil;
import com.centit.webservice.pojo.SysLogDTO;

public class AccessLogManagerImpl extends BaseEntityManagerImpl<FAccessLog>
        implements AccessLogManager{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private AccessLogDao accessLogDao;
    
    public void setAccessLogDao(AccessLogDao accessLogDao) {
        this.accessLogDao = accessLogDao;
        setBaseDao(accessLogDao);
    }

    @Override
    public Long getNextLogId() {
        return accessLogDao.getNextLongSequence("S_ACCESSLOG");
    }

    @Override
    public int getUserCountOnline() {
        return accessLogDao.getUserCountOnline();
    }

    @Override
    public List<FAccessLog> findAccessLogUndo(int limitMinutes) {
        return accessLogDao.findAccessLogUndo(limitMinutes);
    }

    @Override
    public String fingLastLoginTime(String usercode) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", usercode);
        filterMap.put("accesstype", FAccessLog.ACCESS_LOGINING);
       List<FAccessLog> list = super.listObjects(filterMap);
       if(list!=null && !list.isEmpty()){
          return  DateUtil.date2String(list.get(0).getAccesstime(), "yyyy-MM-dd HH:mm:ss");
       }
        return null;
    }
    /**
     * 获取应用日志信息
     * @return
     */
    public List<Object[]> getSysLogDTOlist(){
        return accessLogDao.getSysLogDTOlist();
    }
}
