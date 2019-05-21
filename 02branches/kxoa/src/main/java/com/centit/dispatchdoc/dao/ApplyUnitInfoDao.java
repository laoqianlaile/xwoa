package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.ApplyUnitInfo;

public class ApplyUnitInfoDao extends BaseDaoImpl<ApplyUnitInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ApplyUnitInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("unitcode", CodeBook.EQUAL_HQL_ID);

            filterField.put("loginname", CodeBook.LIKE_HQL_ID);

            filterField.put("isvalid", CodeBook.LIKE_HQL_ID);

            filterField.put("auditor", CodeBook.LIKE_HQL_ID);

            filterField.put("auditDate", CodeBook.LIKE_HQL_ID);

            filterField.put("auditstate", CodeBook.LIKE_HQL_ID);

            filterField.put("contactemail", CodeBook.LIKE_HQL_ID);

            filterField.put("contactfax", CodeBook.LIKE_HQL_ID);

            filterField.put("contactphone", CodeBook.LIKE_HQL_ID);

            filterField.put("officephone", CodeBook.LIKE_HQL_ID);

            filterField.put("contactdep", CodeBook.LIKE_HQL_ID);

            filterField.put("contactcode", CodeBook.LIKE_HQL_ID);

            filterField.put("contactcodetype", CodeBook.LIKE_HQL_ID);

            filterField.put("contact", CodeBook.LIKE_HQL_ID);

            filterField.put("inchargeunit", CodeBook.LIKE_HQL_ID);

            filterField.put("unitzip", CodeBook.LIKE_HQL_ID);

            filterField.put("unitaddress", CodeBook.LIKE_HQL_ID);

            filterField.put("unittype", CodeBook.LIKE_HQL_ID);

            filterField.put("orgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("unitname", CodeBook.LIKE_HQL_ID);

            filterField.put("areacode", CodeBook.LIKE_HQL_ID);

            filterField.put("unitarea", CodeBook.LIKE_HQL_ID);

            filterField.put("registeTime", CodeBook.LIKE_HQL_ID);

            filterField.put("openusertype", CodeBook.LIKE_HQL_ID);

            filterField.put("usertype", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifydate", CodeBook.LIKE_HQL_ID);

            filterField.put("userword", CodeBook.LIKE_HQL_ID);

            filterField.put("userorder", CodeBook.LIKE_HQL_ID);

            filterField.put("regcellphone", CodeBook.LIKE_HQL_ID);

            filterField.put("regemail", CodeBook.LIKE_HQL_ID);

            filterField.put("loginip", CodeBook.LIKE_HQL_ID);

            filterField.put("userdesc", CodeBook.LIKE_HQL_ID);

            filterField.put("username", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }
}
