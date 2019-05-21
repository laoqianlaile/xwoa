package com.centit.oa.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;

public class InnermsgRecipientDao extends BaseDaoImpl<InnermsgRecipient> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(InnermsgRecipientDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("innermsg.msgtitle", CodeBook.LIKE_HQL_ID);

            filterField.put("msgcode", "innermsg.msgcode=? ");

            filterField.put("sender", "innermsg.sender like ?");

            filterField.put("begsenddate",
                    "innermsg.senddate >= to_date(?,'yyyy-mm-dd')");

            filterField.put("endsenddate",
                    "innermsg.senddate <= to_date(?,'yyyy-mm-dd')+1");

            filterField.put("receive", CodeBook.EQUAL_HQL_ID);

            filterField.put("replymsgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("receivetype", CodeBook.EQUAL_HQL_ID);

            filterField.put("mailtype", CodeBook.EQUAL_HQL_ID);

            filterField.put("msgstate", CodeBook.LIKE_HQL_ID);

            filterField.put("msgtype", " receivetype = ? ");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, "innermsg.senddate desc");

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<InnermsgRecipient> listRecipient(InnermsgRecipient ir,
            PageDesc pageDesc) {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(InnermsgRecipient.class);
        criteria.add(Example.create(ir));

        List<InnermsgRecipient> result = super.getHibernateTemplate()
                .findByCriteria(criteria, pageDesc.getPageNo(),
                        pageDesc.getPageSize());

        // pageDesc.setTotalRows((Integer) super.getHibernateTemplate()
        // .findByCriteria(criteria.setProjection(Projections.rowCount())).get(0));
        return result;
    }

    /**
     * 生成内部消息编码
     * 
     * @return String
     */
    public String getNextMsgRecipiCode() {
        return String.valueOf(this.getNextValueOfSequence("S_MSGRECIPICODE"));

    }

    public void delete(InnermsgRecipient ir) {
        final String hql = "delete from InnermsgRecipient ir where ir.innermsg = ? and ir.receive = ?";

        this.doExecuteHql(hql,
                new Object[] { ir.getInnermsg(), ir.getReceive() });
    }

    public void saveBatch(List<InnermsgRecipient> irs) {
        for (int i = 0; i < irs.size(); i++) {
            this.save(irs.get(i));
            if (0 == i % 20) {
                this.getHibernateTemplate().flush();
                this.getHibernateTemplate().clear();
            }
        }

    }

    /**
     * 计算此消息持有人数
     * 
     * @param ir
     * @return
     */
    public long countHoldUsers(InnermsgRecipient ir) {
        final String hql = "select count(*) from InnermsgRecipient ir where ir.innermsg.msgcode = ?";

        return (Long) this.findObjectsByHql(hql,
                new Object[] { ir.getInnermsg().getMsgcode() }).get(0);
    }

    /**
     * 删除指定MsgCode消息
     * 
     * @param msgCode
     */
    public void deleteAllMsgByMsgCode(String msgCode) {
        final String hql = "delete from InnermsgRecipient ir where ir.innermsg.msgcode = ?";

        this.doExecuteHql(hql, new Object[] { msgCode });
    }

    @SuppressWarnings("unchecked")
    public List<InnermsgRecipient> listAllByPk(List<String> pk) {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(InnermsgRecipient.class);
        criteria.add(Restrictions.in("id", pk));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    public List<InnermsgRecipient> listBySearch(Collection<String> msgcodes,
            String usercode, PageDesc pageDesc) {
        String hql = "from InnermsgRecipient i where i.innermsg.msgcode in (:msgcodes) and i.receive = :usercode";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msgcodes", msgcodes);
        params.put("usercode", usercode);

        pageDesc.setTotalRows(count("select count(*) " + hql, params, pageDesc)
                .intValue());

        return list(hql, params, pageDesc);
    }

    private Long count(final String hql, final Map<String, Object> params,
            final PageDesc pageDesc) {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {

            @Override
            public Long doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query createQuery = session.createQuery(hql);
                createQuery.setProperties(params);

                return (Long) createQuery.iterate().next();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private List<InnermsgRecipient> list(final String hql,
            final Map<String, Object> params, final PageDesc pageDesc) {
        return getHibernateTemplate().executeFind(
                new HibernateCallback<List<InnermsgRecipient>>() {

                    @Override
                    public List<InnermsgRecipient> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query createQuery = session.createQuery(hql);
                        createQuery.setProperties(params);

                        return createQuery
                                .setFirstResult(pageDesc.getRowStart())
                                .setMaxResults(pageDesc.getPageSize()).list();
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public List<InnermsgRecipient> listByRtype(String msgCode,
            String recipientType) {
        List<InnermsgRecipient> list = getHibernateTemplate()
                .find("from InnermsgRecipient t where t.innermsg.msgcode=? and t.receivetype=? ",
                        new Object[] { msgCode, recipientType });
        return list;
    }

    public void deleteRec(List<String> pk, String loginUserCode) {
        if (null != pk) {
            for (String s : pk) {
                String hql = "delete from InnermsgRecipient I where I.innermsg.msgcode ="
                        + HQLUtils.buildHqlStringForSQL(s) + " and I.receive=" +HQLUtils.buildHqlStringForSQL(loginUserCode);
                this.getHibernateTemplate().getSessionFactory().openSession()
                        .createQuery(hql).executeUpdate();
            }
        }

    }
    
    /**
     * 邮件状态标记为废件箱
     * @param pk
     * @param loginUserCode
     */
    public void dropRec(List<String> pk, String loginUserCode) {
        if (null != pk) {
            for (String s : pk) {
                String hql = "update InnermsgRecipient I set I.mailtype ='T'   where I.innermsg.msgcode="
                        + HQLUtils.buildHqlStringForSQL(s) + " and I.receive=" +HQLUtils.buildHqlStringForSQL(loginUserCode);
                this.getHibernateTemplate().getSessionFactory().openSession()
                        .createQuery(hql).executeUpdate();
            }
        }

    }
    
    /**
     * 废件箱还原到收件箱
     * @param pk
     * @param loginUserCode
     */
    public void cancleDropRec(List<String> pk, String loginUserCode) {
        if (null != pk) {
            for (String s : pk) {
                String hql = "update InnermsgRecipient I set I.mailtype ='I'   where I.innermsg.msgcode="
                        + HQLUtils.buildHqlStringForSQL(s) + " and I.receive=" +HQLUtils.buildHqlStringForSQL(loginUserCode);
                this.getHibernateTemplate().getSessionFactory().openSession()
                        .createQuery(hql).executeUpdate();
            }
        }

    }
/**
 * 查看时收件箱与本人有关的该条收件msgstat都更新为已读
 * @param innermsg
 * @param loginUserCode
 */
    public void updateMsgStat(Innermsg innermsg, String loginUserCode) {
        doExecuteHql(
                "update InnermsgRecipient I set I.msgstate ='R'   where I.innermsg.msgcode=?  and  I.receive=? and I.msgstate ='U'",
                new Object[] { innermsg.getMsgcode(),
                        loginUserCode });
        
    }
}
