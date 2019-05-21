package com.centit.sys.dao;

import java.util.List;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;

public class UserRoleDao extends BaseDaoImpl<FUserrole> {
    private static final long serialVersionUID = 1L;

    public void deleteByRoid(String roid) {
        doExecuteHql("DELETE FROM FUserrole WHERE id.rolecode = ?", roid);
    }

    public void deleteByUsid(String usid) {
        doExecuteHql("DELETE FROM FUserrole WHERE id.usercode = ?", usid);

    }


    @SuppressWarnings("unchecked")
    public List<FRoleinfo> getSysRolesByUsid(String usid) {
        String sSqlsen = "select ROLECODE,ROLENAME,ISVALID,ROLEDESC " +
                "from F_V_USERROLES where usercode='" + usid + "'";
        List<FRoleinfo> l = (List<FRoleinfo>) findObjectsBySql(sSqlsen, FRoleinfo.class);
        return l;
    }

    @SuppressWarnings("unchecked")
    public List<FUserrole> getUserRolesByUsid(String usid, String rolePrefix) {
        String[] params = null;
        String hql = "FROM FUserrole ur where ur.id.usercode=? " +
                "and ur.id.rolecode like '" + rolePrefix + "%' " +
                "and ur.id.obtaindate <= sysdate " +
                "ORDER BY obtaindate,secededate";

        params = new String[]{usid};
        return getHibernateTemplate().find(hql, (Object[]) params);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<FUserrole> getUserRolesByRoid(String roid) {
        String[] params = null;
        String hql = "FROM FUserrole ur where ur.id.rolecode=? " +
                "and ur.id.obtaindate <= sysdate "+
                "ORDER BY obtaindate,secededate";

        params = new String[]{roid};
        return getHibernateTemplate().find(hql, (Object[]) params);
    }
    

    @SuppressWarnings("unchecked")
    public List<FUserrole> getAllUserRolesByUsid(String usid, String rolePrefix) {
        String[] params = null;
        String hql = "FROM FUserrole ur	" +
                "where ur.id.usercode=? " +
                "and ur.id.rolecode like '" + rolePrefix + "%' " +
                "ORDER BY obtaindate,secededate";

        params = new String[]{usid};
        return getHibernateTemplate().find(hql, (Object[]) params);
    }

    @SuppressWarnings("unchecked")
    public FUserrole getValidUserrole(String usercode, String rolecode) {
        String[] params = null;
        String hql = "FROM FUserrole ur where ur.id.usercode=? " +
                "and ur.id.rolecode = ? " +
                //"and ur.id.obtaindate <= sysdate "+
                "ORDER BY obtaindate,secededate";

        params = new String[]{usercode, rolecode};
        List<FUserrole> urlt = (List<FUserrole>) getHibernateTemplate().find(hql, (Object[]) params);
        if (urlt == null || urlt.size() < 1)
            return null;
        return urlt.get(0);
    }

    public void flush() {
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public void updateUsercode(String sourceUsercode, String destUsercode) {
        final String hql = "update FUserrole un set un.id.usercode = ? where un.id.usercode = ?";

        super.doExecuteHql(hql, new Object[]{destUsercode, sourceUsercode});
    }

    @SuppressWarnings("unchecked")
    public FUserrole getValidUserrole2V(String usercode, String rolecode) {
        
        List<Object[]> l=null;
        String sSqlsen = " select t.usercode,t.ROLECODE FROM F_V_USERROLES t where t.usercode=" +HQLUtils.buildHqlStringForSQL(usercode)+
                " and t.ROLECODE=" +HQLUtils.buildHqlStringForSQL(rolecode);
        System.out.println(sSqlsen);
        l = (List<Object[]>) findObjectsBySql(sSqlsen);
        
        if (l == null || l.size() < 1){
            return null;
        }else{
          Object[] object= l.get(0);
          FUserrole fUserrole=new FUserrole();
          fUserrole.setId(new FUserroleId(object[0].toString(),object[1].toString()));
          return fUserrole;
        }        
    }

    
}
