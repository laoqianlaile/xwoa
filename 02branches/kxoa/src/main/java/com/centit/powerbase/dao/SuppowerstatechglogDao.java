package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerbase.po.Suppowerstatechglog;

public class SuppowerstatechglogDao extends BaseDaoImpl<Suppowerstatechglog> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(SuppowerstatechglogDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("chgno", CodeBook.EQUAL_HQL_ID);

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("version", CodeBook.LIKE_HQL_ID);

            filterField.put("beginTime", CodeBook.LIKE_HQL_ID);

            filterField.put("endTime", CodeBook.LIKE_HQL_ID);

            filterField.put("chgType", CodeBook.LIKE_HQL_ID);

            filterField.put("recoder", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public String genNextPunishClassID() {
        String sKey = getNextValueOfSequence("S_Suppowerstatechglog");

        return sKey;

    }

    public Suppowerstatechglog getObjectByIdAndVersion(String itemId,
            Long version) {
        List<Suppowerstatechglog> procs = this
                .listObjects("From Suppowerstatechglog where itemId =  "
                        + HQLUtils.buildHqlStringForSQL(itemId)
                        + " and version="
                        + HQLUtils.buildHqlStringForSQL(Long.toString(version))
                        + " order by chgno desc");
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }
    
    public void updateSuppowerstatechglog(Suppowerstatechglog suppower) {
        doExecuteHql(
                "update Suppowerstatechglog set endTime=? where itemId =?  and endTime is null ",
                new Object[] { suppower.getEndTime(), suppower.getItemId()});
    }
}
