package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerruntime.po.OptProcLock;

public class OptProcLockDao extends BaseDaoImpl<OptProcLock> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptProcLockDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("lockid", CodeBook.EQUAL_HQL_ID);

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("locksettime", CodeBook.LIKE_HQL_ID);

            filterField.put("islocked", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @Override
    public void saveObject(OptProcLock o) {
        if (null == o.getLockid()) {
            o.setLockid("LK" + getNextKeyBySequence("S_OPT_LOCK_ID", 30));
        }
        super.saveObject(o);
    }

    /**
     * 获取办件最新锁定信息
     * 
     * @param djid
     * @param isLocked
     * @return
     */
    public OptProcLock getOptProcLock(String djId, String islocked) {
        String hql = "From OptProcLock t where t.djId= "
                + HQLUtils.buildHqlStringForSQL(djId) + " and t.islocked="
                + HQLUtils.buildHqlStringForSQL(islocked)
                + " order by t.locksettime desc ";
        List<OptProcLock> lockList = this.listObjects(hql);
        return (null == lockList || lockList.size()==0)? null : lockList.get(0);
    }
    
    /**
     * 获取办件最新锁定信息
     * 
     * @param djid
     * @param isLocked
     * @return
     */
    public OptProcLock getOptProcLock(String djId) {
        String hql = "From OptProcLock t where t.djId= "
                + HQLUtils.buildHqlStringForSQL(djId) 
                + " order by t.locksettime desc ";
        List<OptProcLock> lockList = this.listObjects(hql);
        return (null == lockList || lockList.size()==0)? null : lockList.get(0);
    }
}
