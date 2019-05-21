package com.centit.dispatchdoc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.OptZwhReservedDao;
import com.centit.dispatchdoc.po.OptZwhReserved;
import com.centit.dispatchdoc.service.OptZwhReservedManager;
import com.centit.support.utils.DatetimeOpt;

public class OptZwhReservedManagerImpl extends
        BaseEntityManagerImpl<OptZwhReserved> implements OptZwhReservedManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OptZwhReservedManager.class);

    private OptZwhReservedDao optZwhReservedDao;

    public void setOptZwhReservedDao(OptZwhReservedDao baseDao) {
        this.optZwhReservedDao = baseDao;
        setBaseDao(this.optZwhReservedDao);
    }
    
    public String getNextkey() {
        return optZwhReservedDao.getNextKeyBySequence("S_OPT_ZWH_RESERVED", 8);
    }
    
    public synchronized String saveOptZwhReserveds(String wjlx, String lshYear, long reservedNo, String userCode, String reservedReason) {
        if (StringUtils.isNotBlank(wjlx) && StringUtils.isNotBlank(lshYear) && StringUtils.isNotBlank(userCode)
                && StringUtils.isNotBlank(reservedReason) && reservedNo > 0) {
            Date now = DatetimeOpt.currentUtilDate();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("wjlx", wjlx);
            paramMap.put("lshYear", lshYear);
            paramMap.put("lsh", reservedNo);
            
            List<OptZwhReserved> list = optZwhReservedDao.listObjects(paramMap);
            
            if (list.isEmpty()) {
                try {
                    OptZwhReserved zwhReserved = new OptZwhReserved();
                    zwhReserved.setReservedId(Long.parseLong(this.getNextkey()));
                    zwhReserved.setWjlx(wjlx);
                    zwhReserved.setLshYear(lshYear);
                    zwhReserved.setLsh(reservedNo);
                    zwhReserved.setCreateUser(userCode);
                    zwhReserved.setCreateDate(now);
                    zwhReserved.setReservedReason(reservedReason);
                    zwhReserved.setIsValid("T");
                    
                    optZwhReservedDao.saveObject(zwhReserved);
                } catch (Exception e) {
                    
                }
            }
            return "success";
        }
        
        return "failed";
    }
    
}
