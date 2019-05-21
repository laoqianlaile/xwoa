package com.centit.oa.service.impl;


import java.util.Date;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaAttendanceDetailsDao;
import com.centit.oa.po.OaAttendanceDetails;
import com.centit.oa.service.OaAttendanceDetailsManager;


public class OaAttendanceDetailsManagerImpl extends BaseEntityManagerImpl<OaAttendanceDetails>
        implements OaAttendanceDetailsManager {
    /**
* 
*/
    private static final long serialVersionUID = 1L;

    private OaAttendanceDetailsDao oaAttendanceDetailsDao;
    
    public void setOaAttendanceDetailsDao(OaAttendanceDetailsDao oaAttendanceDetailsDao) {
        this.oaAttendanceDetailsDao = oaAttendanceDetailsDao;
        setBaseDao(oaAttendanceDetailsDao);
    }

    /**
     * 更新考勤信息
     * @param usercode
     * @param updateDate
     * @param isUpdate
     */
    public void updateAttendanceDetails(String usercode, Date updateDate, String isUpdate){
        oaAttendanceDetailsDao.updateAttendanceDetails(usercode, updateDate, isUpdate);
    }

}
