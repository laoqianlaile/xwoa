package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.PowerOptInfo;

public class PowerOptInfoDao extends BaseDaoImpl<PowerOptInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(PowerOptInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("wfcode", CodeBook.LIKE_HQL_ID);

            filterField.put("riskid", CodeBook.LIKE_HQL_ID);

            filterField.put("group_id", CodeBook.LIKE_HQL_ID);

            filterField.put("setoperator", CodeBook.LIKE_HQL_ID);

            filterField.put("setime", CodeBook.LIKE_HQL_ID);

            filterField.put("applyItemType", CodeBook.LIKE_HQL_ID);
            
            filterField.put("isApplyUser", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("oaModuleType", CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<PowerOptInfo> getObjectsByItemID(String itemId) {
        List<PowerOptInfo> po = null;
        try {
            po = (List<PowerOptInfo>) getHibernateTemplate().find(
                    "From PowerOptInfo where cid.itemId=?", itemId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return po;
    }

    public PowerOptInfo getObjectByItemID(String itemId) {
        @SuppressWarnings("unchecked")
        List<PowerOptInfo> po = (List<PowerOptInfo>) getHibernateTemplate()
                .find("From PowerOptInfo where cid.itemId=?", itemId);
        if (po != null && po.size() > 0) {
            return po.get(0);
        }
        return null;
    }

    /**
     * 1、删除之前以保存的信息 2、保存新的数据
     * 
     * @param object
     */
    public void savePowerOptInfo(List<PowerOptInfo> powerOptInfoList) {
        if (powerOptInfoList.size() < 1)
            return;
        // 1 双主键被修改过
        if(null==this.getObjectById(powerOptInfoList.get(0).getCid())){
            this.doExecuteHql("DELETE FROM PowerOptInfo where cid.itemId =? ",
                    powerOptInfoList.get(0).getItemId());  
        }
      
        // 2
        for (PowerOptInfo poi : powerOptInfoList) {
            getHibernateTemplate().saveOrUpdate(poi);
        }

    }
}
