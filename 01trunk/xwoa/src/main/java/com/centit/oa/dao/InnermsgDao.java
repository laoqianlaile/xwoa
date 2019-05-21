package com.centit.oa.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.StringUtils;

import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;

public class InnermsgDao extends BaseDaoImpl<Innermsg> {
    private static final long serialVersionUID = 1L;

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("msgcode", CodeBook.EQUAL_HQL_ID);

            filterField.put("sender", CodeBook.EQUAL_HQL_ID);

            filterField.put("begsenddate",
                    "senddate >= to_date(?,'yyyy-mm-dd')");

            filterField.put("endsenddate",
                    "senddate <= to_date(?,'yyyy-mm-dd')+1");

            filterField.put("replymsgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("msgtitle", CodeBook.LIKE_HQL_ID);

            filterField.put("receivetype", CodeBook.LIKE_HQL_ID);

            filterField.put("receivename", CodeBook.LIKE_HQL_ID);

            filterField.put("msgstate", CodeBook.EQUAL_HQL_ID);

            filterField.put("msgstateNoEq", "msgstate <> ?");

            filterField.put("msgtype", CodeBook.EQUAL_HQL_ID);

            filterField.put("mailtype", CodeBook.EQUAL_HQL_ID);

            filterField.put("msgcontent", CodeBook.LIKE_HQL_ID);

            filterField.put("emailid", "c.emailid = ?");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, "senddate desc");

            filterField
                    .put("NP_senddate",
                            " senddate between trunc(SYSDATE - 1) and trunc(SYSDATE+1 ) ");

            // 接口查询条件--时间戳
            filterField.put("currentdatetime",
                    " senddate < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            
          //日历查询
            filterField.put("cBegin", " senddate>=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
            filterField.put("cEnd", " senddate<=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");

        }
        return filterField;
    }

    /**
     * 生成内部消息编码
     * 
     * @return String
     */
    public String getNextMsgCode() {
        return String.valueOf(this.getNextValueOfSequence("S_MSGCODE"));

    }

    @Override
    public void saveObject(Innermsg o) {
        if (!StringUtils.hasText(o.getMsgcode())) {
            o.setMsgcode(this.getNextMsgCode());
        }
        super.saveObject(o);
    }

    public List<Innermsg> listBySearch(Collection<String> msgcodes,
            String usercode, PageDesc pageDesc) {
        String hql = "from Innermsg i where i.msgcode in (:msgcodes) and i.sender = :usercode and i.msgstate <> 'D'";

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
    private List<Innermsg> list(final String hql,
            final Map<String, Object> params, final PageDesc pageDesc) {
        return getHibernateTemplate().executeFind(
                new HibernateCallback<List<Innermsg>>() {

                    @Override
                    public List<Innermsg> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query createQuery = session.createQuery(hql);
                        createQuery.setProperties(params);

                        return createQuery
                                .setFirstResult(pageDesc.getRowStart())
                                .setMaxResults(pageDesc.getPageSize()).list();
                    }
                });
    }

    /**
     * 定时发送邮件（发件箱保存为草稿的，设定发送时间小于当前时间数据）
     */
    public void autoSend() {
        doExecuteHql("update Innermsg t set t.mailtype='O'   where t.isAuto ='T' and t.mailtype='D' and t.senddate<=sysdate ");
    }

    /**
     * 回复数
     * 
     * @param innermsg
     * @return
     */
    public int getReplayCount(Innermsg innermsg) {
        @SuppressWarnings("unchecked")
        List<Innermsg> list = getHibernateTemplate().find(
                "from Innermsg t where t.topMsgcode=? ",
                new Object[] { innermsg.getMsgcode() });
        return list == null ? 0 : list.size();
    }

    /**
     * 未读回复数
     * 
     * @param innermsg
     * @return
     */
    public int getUnReadRepalyCount(Innermsg innermsg) {

        @SuppressWarnings("unchecked")
        List<InnermsgRecipient> list = getHibernateTemplate()
                .find(" from InnermsgRecipient t where t.msgstate='U' "
                        + " and t.innermsg.msgcode in "
                        + " (select msgcode from Innermsg a where a.topMsgcode=? ) "
                        + " and t.receive= ? ",
                        new Object[] { innermsg.getMsgcode(),
                                innermsg.getSender() });
        return list == null ? 0 : list.size();
    }

    /**
     * 邮件状态标记为废件箱
     * 
     * @param pk
     * @param loginUserCode
     */
    public void dropMsgs(List<String> pk, String loginUserCode) {
        if (null != pk) {
            for (String s : pk) {
                String hql = "update Innermsg I set I.mailtype ='T'   where I.innermsg.msgcode="
                        + HQLUtils.buildHqlStringForSQL(s)
                        + " and I.sender="
                        + HQLUtils.buildHqlStringForSQL(loginUserCode);
                this.getHibernateTemplate().getSessionFactory().openSession()
                        .createQuery(hql).executeUpdate();
            }
        }

    }

    /**
     * 废件箱还原到收件箱
     * 
     * @param pk
     * @param loginUserCode
     */
    public void cancleDropMsgs(List<String> pk, String loginUserCode) {
        if (null != pk) {
            for (String s : pk) {
                String hql = "update Innermsg I set I.mailtype ='O'   where I.innermsg.msgcode="
                        + HQLUtils.buildHqlStringForSQL(s)
                        + " and I.sender="
                        + HQLUtils.buildHqlStringForSQL(loginUserCode);
                this.getHibernateTemplate().getSessionFactory().openSession()
                        .createQuery(hql).executeUpdate();
            }
        }

    }
    // /**
    // * 查询某条消息的所有回复信息
    // *
    // * @param replymsgcode
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // public List<Innermsg> listMsgsByReplycode(Long replymsgcode) {
    // String baseHQL =
    // "from Innermsg where replymsgcode = ? order by senddate asc";
    // return (List<Innermsg>) findObjectsByHql(baseHQL, replymsgcode);
    // }
    //
    // /**
    // * 根据接受人查询邮件
    // *
    // * @param replymsgcode
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // public List<Innermsg> listMsgsByReceive(String receive) {
    // String baseHQL =
    // "from Innermsg where receive = ? order by senddate desc";
    // return (List<Innermsg>) findObjectsByHql(baseHQL, receive);
    // }
    //
    // @Override
    // public void saveObject(Innermsg innermsg) {
    // // if(innermsg.getMsgcode() == null || innermsg.getMsgcode() == 0){
    // // innermsg.setMsgcode(getNextMsgCode());
    // // }
    // super.saveObject(innermsg);
    // }

}
