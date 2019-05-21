package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.VSupPowerWithoutLob;

public class VSupPowerWithoutLobDao extends BaseDaoImpl<VSupPowerWithoutLob> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VSupPowerWithoutLobDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("orgId", CodeBook.LIKE_HQL_ID);

            filterField.put("itemName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemStaName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemType", CodeBook.LIKE_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("isNetwork", CodeBook.LIKE_HQL_ID);

            filterField.put("isFormula", CodeBook.LIKE_HQL_ID);

            filterField.put("phone", CodeBook.LIKE_HQL_ID);

            filterField.put("address", CodeBook.LIKE_HQL_ID);

            filterField.put("isinuse", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);

            filterField.put("ischange", CodeBook.LIKE_HQL_ID);

            filterField.put("fileName", CodeBook.LIKE_HQL_ID);

            filterField.put("auditSign", CodeBook.LIKE_HQL_ID);

            filterField.put("monitorPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptLink", CodeBook.LIKE_HQL_ID);

            filterField.put("legalTimeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("charge", CodeBook.LIKE_HQL_ID);

            filterField.put("formName", CodeBook.LIKE_HQL_ID);

            filterField.put("inFlowImgName", CodeBook.LIKE_HQL_ID);

            filterField.put("ischarge", CodeBook.LIKE_HQL_ID);

            filterField.put("punishClass", CodeBook.LIKE_HQL_ID);

            filterField.put("transactDepname", CodeBook.LIKE_HQL_ID);

            filterField.put("promiseType", CodeBook.LIKE_HQL_ID);

            filterField.put("anticipateType", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepid", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepstate", CodeBook.LIKE_HQL_ID);

            filterField.put("entrustName", CodeBook.LIKE_HQL_ID);

            filterField.put("qlState", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<VSupPowerWithoutLob> listSupPowerOnlyList(
            Map<String, Object> filterMap, PageDesc pageDesc) {
     
        SQLQuery query = getSession().createSQLQuery(
                "select item_id as itemId,max(version) as version,org_id as orgId"
                        + ",item_name as itemName,item_type as itemType "
                        + "from v_suppowerwithoutlob "
                        + "group by item_id,org_id,item_name,item_type");

        query.addScalar("itemId", StringType.INSTANCE);
        query.addScalar("version", StringType.INSTANCE);
        query.addScalar("orgId", StringType.INSTANCE);
        query.addScalar("itemName", StringType.INSTANCE);
        query.addScalar("itemType", LongType.INSTANCE);
        List<VSupPowerWithoutLob>  list = (List<VSupPowerWithoutLob>) query.setResultTransformer(
                Transformers.aliasToBean(VSupPowerWithoutLob.class)).list();
        pageDesc.setTotalRows(Integer.valueOf(list.size()));
        return list;
    }

}
