package com.centit.powerbase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerbase.po.Pcfreeumpiredegree;

public class PcfreeumpiredegreeDao extends BaseDaoImpl<Pcfreeumpiredegree> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(PcfreeumpiredegreeDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("punishclassid", CodeBook.EQUAL_HQL_ID);

            filterField.put("degreeno", CodeBook.EQUAL_HQL_ID);

            filterField.put("itemId", CodeBook.EQUAL_HQL_ID);

            filterField.put("version", CodeBook.EQUAL_HQL_ID);

            filterField.put("isinuse", CodeBook.LIKE_HQL_ID);

            filterField.put("punishfactgrade", CodeBook.LIKE_HQL_ID);

            filterField.put("punishbasis", CodeBook.LIKE_HQL_ID);

            filterField.put("standardNo", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<Pcfreeumpiredegree> listFreeumpire(String itemId, Long version) {

        String baseHQL = "From Pcfreeumpiredegree where itemId = ?  and version =?  order by degreeno desc";
        return (List<Pcfreeumpiredegree>) findObjectsByHql(baseHQL,
                new Object[] { itemId, version });
    }

    public Pcfreeumpiredegree getObjectByItemsId(Long degreeno) {
        List<Pcfreeumpiredegree> procs = this
                .listObjects("From Pcfreeumpiredegree where   degreeno="
                        + degreeno);
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }

    public String generateNextDegreeno() {
        return getNextKeyBySequence("S_PCFREEUMPIREDEGREE", 0);

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Map> listPunishFactGrade(String itemId, Long version) {
        List<Map> list = new ArrayList<Map>();
        String sSqlsen = "select p.degreeno as id,p.punishfactgrade as value from B_freeumpiredegree p "
                + "where   p.isinuse='1' "
                + "  and p.itemId="
                + HQLUtils.buildHqlStringForSQL(itemId)
                + " and version="
                + HQLUtils.buildHqlStringForSQL(Long.toString(version));
        List<Object[]> l = (List<Object[]>) findObjectsByHql(sSqlsen);
        ;
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                Object[] O = (Object[]) l.get(i);
                Map po = new HashMap();
                po.put("id", O[0]);
                po.put("value", O[1]);
                list.add(po);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public boolean getdegreeNoSelByID(String item_id) {
        boolean flag = false;
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(a.degreeNo) as nDegree,'scar' from b_freeumpiredegree a where a.item_id='"
                + item_id
                + "' and a.isInuse=1 and a.version = (select max(b.version) as version from b_power b where b.item_id = a.item_id) ");
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql.toString());
        Object[] count = (Object[]) l.get(0);
        int num = Integer.parseInt(count[0].toString());
        if (num > 0) {
            flag = true;
        }
        return flag;
    }
/**
 * 获取
 * @param itemId
 * @return
 */
    @SuppressWarnings("unchecked")
    public List<Pcfreeumpiredegree> getObjectByItemsIdAndMaxVesion(String itemId) {
        String baseHQL = "From Pcfreeumpiredegree a where a.itemId = ?   order by version desc";
        return (List<Pcfreeumpiredegree>) findObjectsByHql(baseHQL, itemId);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Pcfreeumpiredegree> getObjectByItemsId(String itemId) {
        String baseHQL = "From Pcfreeumpiredegree a where a.itemId = ?  and a.version = (select max(b.version) as version from b_power b where b.item_id = a.item_id)  order by a.degreeno desc";
        return (List<Pcfreeumpiredegree>) findObjectsByHql(baseHQL, itemId);
    }
}
