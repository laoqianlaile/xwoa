package com.centit.oa.dao;

import com.centit.oa.po.Msgannex;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsgannexDao extends BaseDaoImpl<Msgannex> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(MsgannexDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("msgcode", CodeBook.EQUAL_HQL_ID);

            filterField.put("filecode", CodeBook.EQUAL_HQL_ID);


        }
        return filterField;
    }

    @Override
    public void saveObject(Msgannex o) {
        if (!StringUtils.hasText(o.getMsgannexId())) {
            o.setMsgannexId(this.getNextValueOfSequence("S_MSGCODE"));
        }
        super.saveObject(o);
    }

    public void batchSaveObject(List<Msgannex> objs) {
        for (int i = 0; i < objs.size(); i++) {
            if (0 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }

            this.saveObject(objs.get(i));
        }
    }
    public void deleteMsgnnex(String msgcode,String filecode){
        if(StringUtils.hasText(msgcode)&&StringUtils.hasText(filecode)){
        DetachedCriteria criteria=DetachedCriteria.forClass(Msgannex.class);
        criteria.add(Restrictions.and(Restrictions.eq("innermsg.msgcode",msgcode ), Restrictions.eq("fileinfo.filecode", filecode)));
        @SuppressWarnings("unchecked")
        List<Msgannex> msgnnexs=getHibernateTemplate().findByCriteria(criteria);
        if(null!=msgnnexs&&!msgnnexs.isEmpty()){
            for (Msgannex msgannex : msgnnexs) {
                this.deleteObject(msgannex);
            }
        }
        }
    }
}
