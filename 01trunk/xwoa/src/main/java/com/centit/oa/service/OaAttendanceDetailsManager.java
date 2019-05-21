package com.centit.oa.service;

import java.util.Date;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaAttendanceDetails;

public interface OaAttendanceDetailsManager  extends BaseEntityManager<OaAttendanceDetails> {
    
    /**
     * 更新考勤信息
     * @param usercode
     * @param updateDate
     * @param isUpdate
     */
    public void updateAttendanceDetails(String usercode, Date updateDate, String isUpdate);
}