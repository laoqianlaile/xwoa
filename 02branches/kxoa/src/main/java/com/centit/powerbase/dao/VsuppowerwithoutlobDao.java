package com.centit.powerbase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Suppowerqlbgsq;
import com.centit.powerbase.po.Vsuppowerwithoutlob;

public class VsuppowerwithoutlobDao extends BaseDaoImpl<Vsuppowerwithoutlob> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VsuppowerwithoutlobDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("item_id", CodeBook.LIKE_HQL_ID);

            filterField.put("version", CodeBook.EQUAL_HQL_ID);

            filterField
                    .put("begTime", "to_char(beginTime, 'yyyy-mm-dd') <= ? ");

            filterField.put("endTime",
                    " (endTime is null or to_char(endTime, 'yyyy-mm-dd') >?) ");

            filterField.put("NP_not", "version <>0 ");

            filterField.put("orgId", CodeBook.LIKE_HQL_ID);

            filterField.put("itemName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemStaName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemType", CodeBook.LIKE_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("isNetwork", CodeBook.LIKE_HQL_ID);

            filterField.put("isFormula", CodeBook.LIKE_HQL_ID);

            filterField.put("phone", CodeBook.LIKE_HQL_ID);

            filterField.put("address", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);

            filterField.put("auditSign", CodeBook.LIKE_HQL_ID);

            filterField.put("monitorPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptLink", CodeBook.LIKE_HQL_ID);

            filterField.put("legalTimeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("charge", CodeBook.LIKE_HQL_ID);

            filterField.put("formName", CodeBook.LIKE_HQL_ID);

            filterField.put("fileName", CodeBook.LIKE_HQL_ID);

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

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " item_id , version ");

        }
        return filterField;
    }

    public List<Vsuppowerwithoutlob> getObjectBystateAndVersion(String qlState,
            String version) {
        List<Vsuppowerwithoutlob> procs = this
                .listObjects("From Vsuppowerwithoutlob where ql_state =  "
                        + HQLUtils.buildHqlStringForSQL(qlState)
                        + " and version="
                        + HQLUtils.buildHqlStringForSQL(version));
        return procs;
    }

    @SuppressWarnings("unchecked")
    public List<Vsuppowerwithoutlob> listSuppowerWithoutLob(String flag,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        List<Vsuppowerwithoutlob> list = null;
        String itemid = (String) filterMap.get("itemId");
        String itemName = (String) filterMap.get("itemName");
        String itemType = (String) filterMap.get("itemType");
        String orgId = (String) filterMap.get("orgId");
        String sParentUnit = (String) filterMap.get("sParentUnit");
        StringBuffer sql = new StringBuffer();

        if ("sq".equals(flag)) {
            sql.append(" select B_V_PowerWithoutlob.item_id as itemId, B_V_PowerWithoutlob.item_Name as itemName,B_V_PowerWithoutlob.org_id as orgId, B_V_PowerWithoutlob.ITEM_TYPE as itemType, B_V_PowerWithoutlob.version as version, B_V_PowerWithoutlob.ql_state  as qlState ,"
                    + "b_powerchglog.chg_type as chgType ");
            sql.append(" From B_V_PowerWithoutlob left join b_powerchglog on B_V_PowerWithoutlob.item_id = b_powerchglog.item_id and B_V_PowerWithoutlob.version = b_powerchglog.version  and b_powerchglog.reply_time is null left join V_HI_UNITINFO on  B_V_PowerWithoutlob.org_id = V_HI_UNITINFO.depno "
                    + "where 1=1 and B_V_PowerWithoutlob.version<> 0 "
                    + " and B_V_PowerWithoutlob.begin_time <=sysdate and (B_V_PowerWithoutlob.end_time is null or B_V_PowerWithoutlob.end_time > sysdate)  ");
            // sql.append(" From v_suppower_without_lob where 1=1 and version<>0 ");
        } else if ("sh".equals(flag)) {
            sql.append(" select item_id as itemId, item_Name as itemName,org_id as orgId, ITEM_TYPE as itemType, version as version, ql_state  as qlState ,"
                    + "chg_type as chgType ");
            sql.append(" From B_V_POWERCHANGE left join V_HI_UNITINFO on  B_V_POWERCHANGE.org_id = V_HI_UNITINFO.depno where 1=1 and auditor is null and requester is not null ");
            // sql.append(" From v_suppowerqlbgsq where 1=1 and auditor is null and requester is not null ");
        } else {
            sql.append(" select item_id as itemId, item_Name as itemName,org_id as orgId, ITEM_TYPE as itemType, version as version, ql_state  as qlState ,"
                    + "chg_type as chgType ");
            sql.append(" From B_V_POWERCHANGE left join V_HI_UNITINFO on  B_V_POWERCHANGE.org_id = V_HI_UNITINFO.depno where 1=1 and reply_people is null and auditor is not null ");
            // sql.append(" From v_suppowerqlbgsq where 1=1 and reply_people is null and auditor is not null ");
        }
        if (StringUtils.isNotBlank(itemid)) {
            if ("sq".equals(flag)) {
                sql.append(" and B_V_PowerWithoutlob.item_id like "
                        + HQLUtils.buildHqlStringForSQL(HQLUtils
                                .getMatchString(itemid)));
            } else {
                sql.append(" and item_id like "
                        + HQLUtils.buildHqlStringForSQL(HQLUtils
                                .getMatchString(itemid)));
            }
        }
        if (StringUtils.isNotBlank(itemName)) {
            sql.append(" and item_Name like "
                    + HQLUtils.buildHqlStringForSQL(HQLUtils
                            .getMatchString(itemName)));
        }
        if (StringUtils.isNotBlank(itemType)) {
            sql.append(" and item_Type = "
                    + HQLUtils.buildHqlStringForSQL(itemType));
        }
        if (StringUtils.isNotBlank(orgId)) {
            sql.append(" and org_Id like "
                    + HQLUtils.buildHqlStringForSQL(HQLUtils
                            .getMatchString(orgId)));
        }
        if (StringUtils.isNotBlank(sParentUnit)) {
            sql.append(" and topunitcode = "
                    + HQLUtils.buildHqlStringForSQL(sParentUnit));
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        sqlQuery.addScalar("itemType", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("qlState", StringType.INSTANCE);
        sqlQuery.addScalar("chgType", StringType.INSTANCE);
        if ("sq".equals(flag)) {
            list = (List<Vsuppowerwithoutlob>) sqlQuery.setResultTransformer(
                    Transformers.aliasToBean(Vsuppowerwithoutlob.class)).list();
        } else {
            list = (List<Vsuppowerwithoutlob>) sqlQuery.setResultTransformer(
                    Transformers.aliasToBean(Suppowerqlbgsq.class)).list();
        }
        pageDesc.setTotalRows(Integer.valueOf(list.size()));
        return list;
    }
}
