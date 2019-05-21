package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.OptWritdef;
import com.centit.sys.po.FDatadictionary;

public class OptWritdefDao extends BaseDaoImpl<OptWritdef> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptWritdefDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("writid", CodeBook.EQUAL_HQL_ID);

            filterField.put("temptype", CodeBook.LIKE_HQL_ID);

            filterField.put("recordid", CodeBook.LIKE_HQL_ID);

            filterField.put("writcode", CodeBook.LIKE_HQL_ID);

            filterField.put("initvalue", CodeBook.LIKE_HQL_ID);

            filterField.put("isinuse", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public List<OptWritdef> getObjectsByTempType(String tempType) {
        return this
                .listObjects(
                        "from OptWritdef where temptype =? and recordid not in (select w.recordid from  opt_writdef w where w.isinuse='T')",
                        tempType);
    }

    @SuppressWarnings("unchecked")
    public List<FDatadictionary> getItemTypesWithOutHave() {
        List<FDatadictionary> list = new ArrayList<FDatadictionary>();
        String sSqlsen = "select t.datacode, t.datavalue  from f_datadictionary t where t.catalogcode = 'ITEM_TYPE' and t.datacode not in (select t.temptype from opt_writdef t)";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                Object[] O = (Object[]) l.get(i);
                FDatadictionary po = new FDatadictionary();
                po.setDatacode((String) O[0]);
                po.setDatavalue((String) O[1]);
                list.add(po);
            }
        }
        return list;
    }

    public OptWritdef getObjectByTempType(String tempType) {
        List<OptWritdef> l = this.listObjects("from OptWritdef where temptype =?", tempType);
        if (l == null || l.size() < 1)
            return null;
        return l.get(0);
    }
}
