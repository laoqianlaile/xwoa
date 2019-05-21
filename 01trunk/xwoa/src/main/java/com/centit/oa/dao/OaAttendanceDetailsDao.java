package com.centit.oa.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.oa.po.OaAttendanceDetails;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OaAttendanceDetailsDao extends BaseDaoImpl<OaAttendanceDetails>
    {
        private static final long serialVersionUID = 1L;
        public static final Log log = LogFactory.getLog(OaAttendanceDetails.class);
        
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("userid" , CodeBook.EQUAL_HQL_ID);

            filterField.put("state" , CodeBook.LIKE_HQL_ID);

            filterField.put("holidayType" , CodeBook.LIKE_HQL_ID);


        }
        return filterField;
    } 
    
    /**
     * 更新考勤信息
     * @param usercode
     * @param updateDate
     * @param isUpdate
     */
    public void updateAttendanceDetails(String usercode, Date updateDate, String isUpdate){
        
        callProcedure("P_UPDATE_ATTENDANCE_DAY", usercode, updateDate, isUpdate);
    }
    
    
}
