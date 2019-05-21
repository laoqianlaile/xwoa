package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.EquipmentInfo;
import com.centit.sys.po.FDatadictionary;

public class EquipmentInfoDao extends BaseDaoImpl<EquipmentInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(EquipmentInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("equipmentId", CodeBook.EQUAL_HQL_ID);

            filterField.put("equipmentCode", CodeBook.LIKE_HQL_ID);

            filterField.put("equipmentName", CodeBook.LIKE_HQL_ID);

            // filterField.put("equipmentType" ,
            // " equipmentState in (select datacode from FDatadictionary where extracode= ? )");

            // filterField.put("supEquipmentType" , CodeBook.EQUAL_HQL_ID);

            // filterField.put("equipmentType", CodeBook.EQUAL_HQL_ID);

            filterField.put("equipmentState", CodeBook.EQUAL_HQL_ID);

            filterField.put("equipmentDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("inuseTime", CodeBook.LIKE_HQL_ID);

            filterField.put("equipmentCharge", CodeBook.LIKE_HQL_ID);

            filterField.put("equipmentPrice", CodeBook.LIKE_HQL_ID);

            filterField.put("yearlyDepreciation", CodeBook.LIKE_HQL_ID);

            filterField.put("recorder", CodeBook.LIKE_HQL_ID);

            filterField.put("recordeDate", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<EquipmentInfo> listByType(EquipmentInfo object,
            List<String> types, PageDesc pageDesc, Order order) {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(EquipmentInfo.class);

        Example example = Example.create(object);
        example.enableLike(MatchMode.ANYWHERE);
        criteria.add(example);
        criteria.add(Restrictions.in("equipmentType", types));

        if (null != order) {
            criteria.addOrder(order);
        }

        List<EquipmentInfo> list = getHibernateTemplate().findByCriteria(
                criteria, pageDesc.getRowStart(), pageDesc.getPageSize());
        criteria.setProjection(Projections.rowCount());
        List<Long> q = getHibernateTemplate().findByCriteria(criteria);
        pageDesc.setTotalRows(q.get(0).intValue());
        return list;
    }

    public void updatesById(FDatadictionary datadictionary) {
        doExecuteHql(
                "update EquipmentInfo set equipmentType=?   where equipmentType =?  ",
                new Object[] { datadictionary.getExtracode(),
                        datadictionary.getDatacode() });
    }
  //树形结构删除本级，下级上移
    public void updatesByid(FDatadictionary datadictionary) {
       
            doExecuteHql(
                    " update FDatadictionary set extracode=?   where id.catalogcode =?  and extracode=? ",
                    new Object[] { datadictionary.getExtracode(),
                            datadictionary.getId().getCatalogcode(),
                            datadictionary.getId().getDatacode() });
        }

}
