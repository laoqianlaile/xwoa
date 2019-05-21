package com.centit.powerbase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerbase.po.PowerOrgInfo;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.sys.po.FUnitinfo;

public class PowerOrgInfoDao extends BaseDaoImpl<PowerOrgInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(PowerOrgInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("cid.itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("cid.unitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("wfcode", CodeBook.LIKE_HQL_ID);

            filterField.put("setoperator", CodeBook.LIKE_HQL_ID);

            filterField.put("setime", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public List<VPowerOrgInfo> listOfReadyPowerOrgInfoList(PowerOrgInfo o) {
        String hql = "from VPowerOrgInfo where unitcode = ?";
        @SuppressWarnings("unchecked")
        List<VPowerOrgInfo> sqclList = (List<VPowerOrgInfo>) this
                .findObjectsByHql(hql, o.getCid().getUnitcode());

        return sqclList;
    }

    @SuppressWarnings("unchecked")
    public List<VPowerOrgInfo> listOfAllPowerOrgInfoList(PowerOrgInfo o) {
        List<VPowerOrgInfo> list = new ArrayList<VPowerOrgInfo>();
        String sSqlsen = "select t.item_id, t.item_name,t.item_type from B_V_POWERINUSING t where 1=1 ";
        if (StringUtils.isNotBlank(o.getUnitcode())) {
            sSqlsen += " and t.item_id not in (select p.item_id  from c_power_org_info p where p.unitcode='"
                    + o.getUnitcode() + "')";
        }
        if (StringUtils.isNotBlank(o.getItem_type())) {
            sSqlsen += " and item_type='" + o.getItem_type() + "'";
        }
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                Object[] O = (Object[]) l.get(i);
                VPowerOrgInfo po = new VPowerOrgInfo();
                po.setItemId((String) O[0]);
                po.setItemName((String) O[1]);
                list.add(po);
            }
        }

        return list;
    }

    /**
     * 保存
     * 1、在保存之前先删除，该部门历史数据
     * 2、保存此次新关联数据
     * @param powerOrgInfos
     */
    public void savePowerOrgInfos(List<PowerOrgInfo> powerOrgInfos) {
        if (powerOrgInfos.size() < 1)
            return;
      
        //2
        for (PowerOrgInfo pi : powerOrgInfos) {
//            //1
//            this.doExecuteHql("DELETE FROM PowerOrgInfo where cid.unitcode = ?",
//                    pi.getUnitcode());
            getHibernateTemplate().saveOrUpdate(pi);
        }

    }


    @SuppressWarnings("unchecked")
    public List<FUnitinfo> listUnitList(String itemId) {
        String sSqlsen = "select t.* from f_unitinfo t where 1=1 ";
        if (StringUtils.isNotBlank(itemId)) {
            sSqlsen += " and t.unitcode not in (select p.item_id from c_power_org_info p where p.item_id='"
                    + itemId+ "')";
        }
        
        return (List<FUnitinfo>) findObjectsBySql(sSqlsen,FUnitinfo.class);    
    }

    public void savePowerOrgs(List<PowerOrgInfo> powerOrgInfos) {
        if (powerOrgInfos.size() < 1)
            return;
     
        for (PowerOrgInfo pi : powerOrgInfos) {
            getHibernateTemplate().saveOrUpdate(pi);
        }
        
    }
}
