package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.OptZwhReserved;

public interface OptZwhReservedManager extends
        BaseEntityManager<OptZwhReserved> {
    
    public String getNextkey();
    
    public String saveOptZwhReserveds(String wjlx, String lshYear, long reservedNo, String userCode, String reservedReason);
    
}
