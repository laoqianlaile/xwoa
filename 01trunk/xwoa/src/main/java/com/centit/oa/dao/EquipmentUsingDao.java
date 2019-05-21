package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.EquipmentUsing;

public class EquipmentUsingDao extends BaseDaoImpl<EquipmentUsing> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(EquipmentUsingDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("useRequestId", CodeBook.EQUAL_HQL_ID);

            filterField.put("equipmentId", CodeBook.EQUAL_HQL_ID);

            filterField.put("applicant", CodeBook.LIKE_HQL_ID);
            
            filterField.put("applicanteq", " applicant=? ");

            filterField.put("applicantTime", CodeBook.LIKE_HQL_ID);

            filterField.put("purposeType", CodeBook.LIKE_HQL_ID);

            filterField.put("purposeDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("planBeginTime", " plan_Begin_Time >= to_date(?,'yyyy-MM-dd')");

            filterField.put("planEndTime", " plan_End_Time <= to_date(?,'yyyy-MM-dd')");
            
            filterField.put("planBeginTimeBegin", " planBeginTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put("planBeginTimeEnd", " planBeginTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put("planEndTimeBegin", " planEndTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put("planEndTimeEnd", " planEndTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put("auditor", CodeBook.LIKE_HQL_ID);

            filterField.put("auditTime", CodeBook.LIKE_HQL_ID);

            filterField.put("beAccepted", CodeBook.LIKE_HQL_ID);

            filterField.put("beUsed", CodeBook.LIKE_HQL_ID);
    
            filterField.put("beginTime", " ( begin_Time >= to_date(?,'yyyy-mm-dd') or begin_Time is null ) ");

            filterField.put("endTime", " ( end_Time <= to_date(?,'yyyy-mm-dd') or end_Time is null ) ");

            filterField.put("useCost", CodeBook.LIKE_HQL_ID);

            filterField.put("useDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("feedbackUser", CodeBook.LIKE_HQL_ID);

            filterField.put("feedbackTime", " feedbackTime is null ");
           
            filterField.put("equipmentState",  CodeBook.EQUAL_HQL_ID );
            
//            filterField.put("equipmentType", "equipmentId in ( ? )");
            
        }
        return filterField;
    }

}
