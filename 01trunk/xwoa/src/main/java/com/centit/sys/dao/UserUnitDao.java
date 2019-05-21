package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;

public class UserUnitDao extends BaseDaoImpl<FUserunit> {
    private static final long serialVersionUID = 1L;

    
    
    
    @Override
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("userrank", "id in ( select  id from FUserunit where id.userrank = ? )");
            filterField.put("userstation", "id in ( select  id from FUserunit where id.userstation = ? )");
           //filterField.put("userstation", "userstation=?");
            filterField.put("username", "id in (select id from FUserunit where id.usercode in (select usercode from FUserinfo where username like ?))");
            filterField.put("unitcode", "id in (select id from FUserunit where id.unitcode = ?)");
            filterField.put("isprimary", "id in (select id from FUserunit where isprimary=?)");

        }
        return filterField;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<FUserunit> getSysUnitsByUserId(String userId) {
        List<FUserunit> ls = getHibernateTemplate().find(
                "FROM FUserunit fUserunit where fUserunit.id.usercode=?",
                userId);
        /*
         * for (FUserunit usun : ls) {
         * usun.setUnitname(CodeRepositoryUtil.getValue
         * ("unitcode",usun.getId().getUnitcode() )); }
         */
        return ls;
    }

    
    
    
    
    @SuppressWarnings("unchecked")
    public List<FUserunit> getSysUsersByUnitId(String unitCode) {
        List<FUserunit> ls = getHibernateTemplate().find(
                "FROM FUserunit fUserunit where fUserunit.id.unitcode=?",
                unitCode);
        return ls;
    }

    /**
     * unitcode不为null就是某个处室的某个角色，为NULL就是所有处室的某个角色
     * 
     * @param roleType
     * @param roleCode
     * @param unitCode
     * @param zq
     * @return
     */

    @SuppressWarnings("unchecked")
    public List<FUserunit> getSysUsersByRoleAndUnit(String roleType,
            String roleCode, String unitCode) {
        List<FUserunit> ls = null;
        if (unitCode != null && !"".equals(unitCode)) {
            if ("gw".equals(roleType))
                ls = getHibernateTemplate()
                        .find("FROM FUserunit fUserunit where fUserunit.id.unitcode=? and fUserunit.id.userstation=? ",
                                unitCode, roleCode);
            else if ("xz".equals(roleType))
                ls = getHibernateTemplate()
                        .find("FROM FUserunit fUserunit where fUserunit.id.unitcode=? and fUserunit.id.userrank=? ",
                                unitCode, roleCode);
        } else {
            if ("gw".equals(roleType))
                ls = getHibernateTemplate()
                        .find("FROM FUserunit fUserunit where fUserunit.id.userstation=? ",
                                roleCode);
            else if ("xz".equals(roleType))
                ls = getHibernateTemplate()
                        .find("FROM FUserunit fUserunit where fUserunit.id.userrank=? ",
                                roleCode);
        }
        return ls;
    }

    public FUserunit getUserunitByUserid(String userid) {    
        @SuppressWarnings("unchecked")
        List<FUserunit> list= getHibernateTemplate().find("FROM FUserunit fUserunit where fUserunit.isprimary = 'T' and fUserunit.id.usercode=? ",userid);      
        if(list!=null){
        return list.get(0);
        }
        else {
         return null; 
        }
    }
    
    public List<FUserunit> getSysUsersByUnitId(String unitCode, PageDesc pageDesc,Map<String,Object> filterMap) {
        
        StringBuffer hql = new StringBuffer("FROM FUserunit fUserunit where fUserunit.id.unitcode=? ");
        
        if(null != filterMap && null != filterMap.get("ORDER_BY")){
            hql.append("order by "+(String)filterMap.get("ORDER_BY"));
        }
        return super.listObjects(hql.toString(),unitCode, pageDesc);

    }


    /**
     * 删除表中所有数据
     * @param clazz
     * @param <T>
     */
    public <T> void deleteAll(Class<T> clazz) {
        final String hql = "delete from " + clazz.getName();

        super.doExecuteHql(hql);
    }

    /**
     * 批量添加或更新
     * @param userunits
     */
    public void batchSave(List<FUserunit> userunits) {
        for (int i = 0; i < userunits.size(); i++) {
            super.saveObject(userunits.get(i));

            if (0 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
    }


    public void flush() {
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }


    @SuppressWarnings("unchecked")
    public List<FUserunit> listUserunitByIds(List<FUserunitId> ids) {
        return super.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(FUserunit.class)
        .add(Restrictions.in("id", ids)));
    }

    public void batchMerge(List<FUserunit> userunits) {
    	for (int i = 0; i < userunits.size(); i++) {
            getHibernateTemplate().merge(userunits.get(i));

            if (19 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
    }

    /**
     * 将当前用户的主机构改为非主机构
     * @param usercode
     */
    public void updateUserPrimaryUnit(String usercode) {
        final String hql = "update FUserunit un set un.isprimary = 'F' where un.usercode = ?";

        super.doExecuteHql(hql, usercode);
    }


    public void updateUsercode(String sourceUsercode, String destUsercode) {
        final String hql = "update FUserunit un set un.id.usercode = ? where un.id.usercode = ?";

        doExecuteHql(hql, new Object[]{destUsercode, sourceUsercode});
    }
    
    /**
     * 查询系统中有人员的岗位
     */
    public List<Object[]> getAllStationsInUse(String[] stationsChosen){
        
        StringBuffer hql = new StringBuffer("select t.userstation, f.datavalue, f.extracode2 from f_userunit t ")
                .append("left join f_datadictionary f on t.userstation = f.datacode ")
                .append("where t.isprimary = 'T' ")
                .append("and f.catalogcode = 'StationType' ");
                
        if(null != stationsChosen && stationsChosen.length > 0){
            hql.append("and t.userstation in(");
            for(int i = 0; i < stationsChosen.length -1 ; i++){
                hql.append("'" + stationsChosen[i] + "',");
            }
            hql.append("'" + stationsChosen[stationsChosen.length -1]+ "') ");
        }      
        
        hql.append("group by t.userstation, f.datavalue, f.extracode2 ")
                .append("order by to_number(f.extracode2) ");
       
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(hql.toString());
        return list;
        
    }


    /**
     * 删除人员主部门机构
     */
    public void deleteUserUnit(String usercode) {
            this.doExecuteHql("DELETE FROM FUserunit rp where  rp.isprimary='T' and rp.id.usercode=?", usercode);
            
        }   
}
