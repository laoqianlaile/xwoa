package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.VProjectAuditInfo;

public class VProjectAuditInfoDao extends BaseDaoImpl<VProjectAuditInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VProjectAuditInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("projectId", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("projectUnitName", CodeBook.LIKE_HQL_ID);

            filterField.put("projectName", CodeBook.LIKE_HQL_ID);

            filterField.put("registeredAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("eiaApprovalType", CodeBook.EQUAL_HQL_ID);

            filterField.put("buildLegal", CodeBook.LIKE_HQL_ID);

            filterField.put("industryField", CodeBook.LIKE_HQL_ID);

            filterField.put("buildContent", CodeBook.LIKE_HQL_ID);

            filterField.put("totalInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("projectType", CodeBook.EQUAL_HQL_ID);

            filterField.put("auditUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("sendArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("begTime", "solvetime >= to_date(?,'yyyy-mm-dd')");

            filterField.put("endTime", "solvetime <= to_date(?,'yyyy-mm-dd')");

            filterField.put("contactName", CodeBook.LIKE_HQL_ID);

            filterField.put("contactPhone", CodeBook.LIKE_HQL_ID);
            
            filterField.put("powerid", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("projectAreaCode", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("incomeDeptName", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("isUpload", CodeBook.EQUAL_HQL_ID);

            filterField.put("acceptDate", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("begAcceptDate", "acceptDate >= to_date(?,'yyyy-mm-dd')");

            filterField.put("endAcceptDate", "acceptDate <= to_date(?,'yyyy-mm-dd')");
        }
        return filterField;
    }
}
