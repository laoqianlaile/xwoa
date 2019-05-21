package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.VOptApplyTaskList;

public class VOptApplyTaskDao extends BaseDaoImpl<VOptApplyTaskList> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VOptApplyTaskList.class);
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            //自己添加
            filterField = new HashMap<String, String>();
            filterField.put("nodeInstId", CodeBook.EQUAL_HQL_ID);

            filterField.put("flowInstId", CodeBook.LIKE_HQL_ID);

            filterField.put("wfOptName", CodeBook.LIKE_HQL_ID);

            filterField.put("wfOptTag", CodeBook.LIKE_HQL_ID);

            filterField.put("userCode", CodeBook.EQUAL_HQL_ID);

            filterField.put("roleType", CodeBook.LIKE_HQL_ID);

            filterField.put("roleCode", CodeBook.LIKE_HQL_ID);

            filterField.put("authDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeName", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeType", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeOptType", CodeBook.LIKE_HQL_ID);

            filterField.put("optName", CodeBook.LIKE_HQL_ID);

            filterField.put("methodName", CodeBook.LIKE_HQL_ID);

            filterField.put("optUrl", CodeBook.LIKE_HQL_ID);

            filterField.put("optMethod", CodeBook.LIKE_HQL_ID);

            filterField.put("optDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("optCode", CodeBook.EQUAL_HQL_ID);

            filterField.put("inststate", CodeBook.LIKE_HQL_ID);

            filterField.put("flowPhase", CodeBook.LIKE_HQL_ID);

            filterField.put("noGrantor",
                    "1 = to_number(?) and grantor is null ");

            filterField.put("grantor",
                    "1 = to_number(?) and grantor is not null ");

            filterField.put(CodeBook.ORDER_BY_HQL_ID,
                    "nodeCreateTime desc");
            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

           //.put("flowinstid", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.LIKE_HQL_ID);

            filterField.put("biztype", CodeBook.LIKE_HQL_ID);

            filterField.put("orgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("orgname", CodeBook.LIKE_HQL_ID);

            filterField.put("headunitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("headusercode", CodeBook.LIKE_HQL_ID);

            filterField.put("content", CodeBook.LIKE_HQL_ID);

            filterField.put("powerid", CodeBook.LIKE_HQL_ID);

            filterField.put("powername", CodeBook.LIKE_HQL_ID);

            filterField.put("solvestatus", CodeBook.LIKE_HQL_ID);

            filterField.put("solvetime", CodeBook.LIKE_HQL_ID);

            filterField.put("solvenote", CodeBook.LIKE_HQL_ID);

            filterField.put("createuser", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("sendArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("caseNo", CodeBook.LIKE_HQL_ID);
            
        }
        return filterField;
        }

  

}
