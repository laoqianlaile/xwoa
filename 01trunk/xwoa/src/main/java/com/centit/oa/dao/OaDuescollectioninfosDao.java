package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaDuescollectioninfos;

public class OaDuescollectioninfosDao extends
        BaseDaoImpl<OaDuescollectioninfos> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaDuescollectioninfosDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);

            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("createtime", CodeBook.LIKE_HQL_ID);

            filterField.put("amount", CodeBook.LIKE_HQL_ID);

            filterField.put("isfinish", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public void deleteDuescollectioninfosById(String djId) {
        doExecuteHql("delete from OaDuescollectioninfos  where cid.djId=? ",
                new Object[] { djId });
    }

    public void initalTempState(String djId) {
        // TODO Auto-generated method stub
        doExecuteHql(
                "update OaDuescollectioninfos  set tempstate='F' where  cid.djId=? ",
                new Object[] { djId });
    }

    public void clearObjectProperties(String djId) {
        doExecuteHql(
                "delete from OaDuescollectioninfos  where tempstate='F' and cid.djId=? ",
                new Object[] { djId });
    }

    public List<Object[]> listUnitsDistinct(String djId) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select distinct djId,unitcode from OA_DUESCOLLECTIONINFOS where djId="
                + HQLUtils.buildHqlStringForSQL(djId));
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sb.toString());
        return l;
    }
}
