package com.centit.oa.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centit.oa.po.OaLeaveMessage;
import com.centit.support.utils.DatetimeOpt;

public class OaLeaveMessageDao extends BaseDaoImpl<OaLeaveMessage> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaLeaveMessageDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("no", CodeBook.EQUAL_HQL_ID);

            filterField.put("infoType", CodeBook.EQUAL_HQL_ID);

            filterField.put("djid", CodeBook.LIKE_HQL_ID);

            filterField.put("creater", CodeBook.EQUAL_HQL_ID);

            filterField.put("replycreater", " creater=? ");

            filterField.put("usercode", " creater=? ");

            filterField.put("creatertime", CodeBook.LIKE_HQL_ID);

            filterField.put("messagecomment", CodeBook.LIKE_HQL_ID);

            filterField.put("state", CodeBook.LIKE_HQL_ID);

            filterField.put("excludeStates", "state not in(?)");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " creatertime asc");

        }
        return filterField;
    }

    public Map<String, Object> getMount(String columnType, List<String> djids) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(columnType)) {
            // 查某类型天帖子总数
            DetachedCriteria criteria = DetachedCriteria
                    .forClass(OaLeaveMessage.class);
            criteria.add(Restrictions.eq("infoType", columnType));
            criteria.setProjection(Projections.rowCount());
            criteria.add(Restrictions.eq("state", "T"));
            if (null != djids && !djids.isEmpty())
                criteria.add(Restrictions.in("djid", djids));
            result.put("total", getHibernateTemplate().findByCriteria(criteria)
                    .get(0));
            // 查今天某类型帖子总数
            DetachedCriteria criteria1 = DetachedCriteria
                    .forClass(OaLeaveMessage.class);
            Date current = new Date();
            Date today = DatetimeOpt.truncateToDay(current);
            Date tomorrow = DatetimeOpt.addDays(today, 1);
            if (null != djids && !djids.isEmpty())
                criteria1
                        .add(Restrictions.and(Restrictions.in("djid", djids),
                                Restrictions.and(Restrictions.eq("state", "T"),
                                        Restrictions.and(Restrictions.eq(
                                                "infoType", columnType),
                                                Restrictions.and(Restrictions
                                                        .gt("creatertime",
                                                                today),
                                                        Restrictions.lt(
                                                                "creatertime",
                                                                tomorrow))))));
            else
                criteria1.add(Restrictions.and(Restrictions.eq("state", "T"),
                        Restrictions.and(Restrictions
                                .eq("infoType", columnType), Restrictions.and(
                                Restrictions.gt("creatertime", today),
                                Restrictions.lt("creatertime", tomorrow)))));
            // criteria.add(Restrictions.in("djid", djids));
            criteria1.setProjection(Projections.rowCount());
            result.put("today", getHibernateTemplate()
                    .findByCriteria(criteria1).get(0));
        }
        return result;
    }

}
