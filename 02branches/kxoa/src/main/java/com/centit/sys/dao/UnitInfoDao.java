package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;

public class UnitInfoDao extends BaseDaoImpl<FUnitinfo> {
	public static final Log log = LogFactory.getLog(UnitInfoDao.class);
	private static final long serialVersionUID = 1L;
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("UNITCODE" , CodeBook.EQUAL_HQL_ID);
			filterField.put("UNITNAME" , CodeBook.LIKE_HQL_ID);
			filterField.put("ISVALID" , CodeBook.EQUAL_HQL_ID);
			filterField.put("PARENTUNIT" , CodeBook.EQUAL_HQL_ID);
			filterField.put(CodeBook.ORDER_BY_HQL_ID," parentunit,unitorder asc");
		}
		return filterField;
	}		
	
	@SuppressWarnings("unchecked")
	public List<FUnitinfo> getSubUnits(String superUnitID)
	{
		// Oracle only
		 
		String sSqlsen;
		String dn = getDialectName();
		if("Oracle10gDialect".endsWith(dn)||"OracleDialect".endsWith(dn))
			sSqlsen ="select UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID  "+
						"from f_Unitinfo "+
						"where ISVALID='T' "+
						"start with (UNITCODE = '"+superUnitID+"') "+
							"connect by  prior UNITCODE = PARENTUNIT "+
						"order by LEVEL,unitorder";
		else// if("DB2Dialect".endsWith(dn) || "SQLServerDialect".endsWith(dn)) // ibme db2 sql server 
			sSqlsen = "WITH RPL (LEVEL,UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID) AS "+
					    " ("+
					            "SELECT 1 as LEVEL,UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID "+
					            "FROM f_Unitinfo "+ 
					            "WHERE UNITCODE='"+superUnitID+"' "+
					          "UNION ALL "+
					            "SELECT PARENT.LEVEL+1 as LEVEL, CHILD.UNITCODE,CHILD.PARENTUNIT,CHILD.UNITTYPE, "+
					                        "CHILD.ISVALID,CHILD.UNITNAME,CHILD.UNITDESC,CHILD.ADDRBOOKID "+ 
					            "FROM RPL PARENT, f_Unitinfo CHILD "+
					          "WHERE PARENT.UNITCODE = CHILD.PARENTUNIT "+
					      ") "+
					"SELECT UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID "+
					"FROM RPL WHERE ISVALID='T' ORDER BY LEVEL,unitorder";		
		
		// sql server
		
		List<FUnitinfo> l =(List<FUnitinfo>) findObjectsBySql(sSqlsen,FUnitinfo.class);
		
		return l;
	}
	
	@SuppressWarnings("unchecked")
	public List<FUnitinfo> getAllSubUnits(String superUnitID)
	{
		// Oracle only
		 
		String sSqlsen;
		String dn = getDialectName();
		if("Oracle10gDialect".endsWith(dn)||"OracleDialect".endsWith(dn)) /*交通厅行权添加了DEPNO字段*/
			sSqlsen =/*"select UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID,UNITSHORTNAME,DEPNO,UNITORDER "*/
			            "select * "+
						"from f_Unitinfo "+						
							"connect by  prior UNITCODE = PARENTUNIT "+"start with (UNITCODE = '"+superUnitID+"') "+
						"order by LEVEL,unitorder";
		else// if("DB2Dialect".endsWith(dn) || "SQLServerDialect".endsWith(dn)) // ibme db2 sql server 
			sSqlsen = "WITH RPL (LEVEL,UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID) AS "+
					    " ("+
					            "SELECT 1 as LEVEL,UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID "+
					            "FROM f_Unitinfo "+ 
					            "WHERE UNITCODE='"+superUnitID+"' "+
					          "UNION ALL "+
					            "SELECT PARENT.LEVEL+1 as LEVEL, CHILD.UNITCODE,CHILD.PARENTUNIT,CHILD.UNITTYPE, "+
					                        "CHILD.ISVALID,CHILD.UNITNAME,CHILD.UNITDESC,CHILD.ADDRBOOKID "+ 
					            "FROM RPL PARENT, f_Unitinfo CHILD "+
					          "WHERE PARENT.UNITCODE = CHILD.PARENTUNIT "+
					      ") "+
					"SELECT UNITCODE,PARENTUNIT,UNITTYPE,ISVALID,UNITNAME,UNITDESC,ADDRBOOKID "+
					"FROM RPL ORDER BY LEVEL,unitorder";		
		// sql server
		log.debug(sSqlsen);
		List<FUnitinfo> l =(List<FUnitinfo>) findObjectsBySql(sSqlsen,FUnitinfo.class);
	/*	if(l != null ){
		  //删除自身
	        for(int i=0;i<l.size();i++){
	            if( l.get(i).getUnitcode().equals(superUnitID) ){
	                l.remove(i);
	                break;
	            }
	        } 
		}*/
		//为什么要删除自身
		
		return l;		
	}


	public String getNextKey() {
	/*	return getNextKeyByHqlStrOfMax("unitcode",
						"FUnitinfo WHERE unitcode !='99999999'",6);*/
		return getNextKeyBySequence("S_UNITCODE", 6);
	}

	@SuppressWarnings("unchecked")
	public List<FUserunit> getSysUnitsByUserId(String userId) {
		List<FUserunit> ls = getHibernateTemplate().find("FROM FUserunit where id.usercode=?",userId);
		/*for (FUserunit usun : ls) {
			usun.setUnitname(CodeRepositoryUtil.getValue("unitcode",usun.getId().getUnitcode() ));
		}
		*/
		return ls;
	}
	
	public  FUserunit getUserPrimaryUnit(String userId) {
        List<FUserunit> ul = getSysUnitsByUserId(userId);
        if(ul==null)
            return null;
        FUserunit uu=null;
        for(FUserunit u : ul){
            if("T".equals(u.getIsprimary()))
            {
                uu = u;
                break;
            }else if(uu==null)
                uu = u;
        }
        return uu;
    }   
	
	@SuppressWarnings("unchecked")
	public List<FUserunit> getSysUsersByUnitId(String unitCode) {
		List<FUserunit> ls = getHibernateTemplate().find("FROM FUserunit where id.unitcode=?",unitCode);
		return ls;
	}	
	@SuppressWarnings("unchecked")
    public String getUnitCode(String depno) {
        List<FUnitinfo> ls = getHibernateTemplate().find("FROM FUnitinfo where 1=1 and depno=?",depno);
        if(ls!=null){
            return ls.get(0).getUnitcode();
        }else{
            return null;
        }
    }   
	@SuppressWarnings("unchecked")
	public List<FUserinfo> getUnitUsers(String unitCode)
	{
		String sSqlsen ="select a.* "+
						"from f_Userinfo a join f_userunit b on(a.usercode=b.usercode) "+
						"where b.unitcode ='"+unitCode+"'";
		
		return (List<FUserinfo>) findObjectsBySql(sSqlsen,FUserinfo.class);	
	}	
	
	@SuppressWarnings("unchecked")
	public List<FUserinfo> getRelationUsers(String unitCode)
	{
		String sSqlsen = "select * FROM F_Userinfo ui where ui.usercode in "+
				"(select usercode from f_userunit where unitcode='"+unitCode+"') or "+
				"ui.usercode in (select usercode from f_userrole where rolecode like '"+unitCode+"-%')";
		
		return (List<FUserinfo>) findObjectsBySql(sSqlsen,FUserinfo.class);	
	}
	
	@SuppressWarnings("unchecked")
	public String getUnitNameOfCode(String unitcode)
	{
		Query query = super.getSession().createSQLQuery("select unitname from f_unitinfo where unitcode='" + unitcode + "'");
		List<Object[]> username = query.list();
		Object o = username.get(0);
		return o.toString();
	}
	
	
	@SuppressWarnings("unchecked")
    public List<FUnitinfo> listUnitinfoByUnitcodes(List<String> unitcodes) {

//        return super.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(FUnitinfo.class)
//                .add(Restrictions.or(Restrictions.in("unitcode", unitcodes), Restrictions.in("unitalias", unitcodes))));
        return (List<FUnitinfo>)super.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(FUnitinfo.class)
        		.add(Restrictions.in("unitcode", unitcodes)));
    }
	
	/**
     * 批量添加或更新
     * @param unitinfos
     */
    public void batchSave(List<FUnitinfo> unitinfos) {
        for (int i = 0; i < unitinfos.size(); i++) {
            saveObject(unitinfos.get(i));
        }
    }
    
    public void batchMerge(List<FUnitinfo> unitinfos) {
    	for (int i = 0; i < unitinfos.size(); i++) {
            getHibernateTemplate().merge(unitinfos.get(i));

            if (19 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
    }
    
    public FUnitinfo getUnitByName(String name) {
        if (StringUtils.isNotBlank(name)) {
            String hql = "from FUnitinfo where unitname like '%" + name + "%' or unitshortname like '%" + name + "%' order by unitorder asc";
            List<FUnitinfo> list = listObjects(hql);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }
}
