package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.sys.po.UserbizoptLog;

public class UserbizoptLogDao extends BaseDaoImpl<UserbizoptLog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(UserbizoptLogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);

			filterField.put("bizname" , CodeBook.EQUAL_HQL_ID);

			filterField.put("biztype" , CodeBook.EQUAL_HQL_ID);

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createtime" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createuser" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createrip" , CodeBook.EQUAL_HQL_ID);


			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	public List<UserbizoptLog> listObject(String djId,String usercode, Long nodeinstid){
	    String Hql=" from UserbizoptLog  where 1=1 "+
	            " and dj_Id="+HQLUtils.buildHqlStringForSQL(djId)+
	            " and createuser="+HQLUtils.buildHqlStringForSQL(usercode)+
	            " and nodeinstid="+nodeinstid;
	    @SuppressWarnings("unchecked")
	    List<UserbizoptLog> l=getHibernateTemplate().find(Hql);
	    if(l==null||l.size()<1){
	        return null;
	    }else
	        return l;
	}
	public List<UserbizoptLog> listObject(String djId,String usercode, String remark){
        String Hql=" from UserbizoptLog  where 1=1 "+
                " and dj_Id="+HQLUtils.buildHqlStringForSQL(djId)+
                " and createuser="+HQLUtils.buildHqlStringForSQL(usercode)+
                " and remark="+HQLUtils.buildHqlStringForSQL(remark);
        @SuppressWarnings("unchecked")
        List<UserbizoptLog> l=getHibernateTemplate().find(Hql);
        if(l==null||l.size()<1){
            return null;
        }else
            return l;
    }
	public List<UserbizoptLog> listObject(String djId,String usercode){
        String Hql=" from UserbizoptLog  where 1=1 "+
                " and dj_Id="+HQLUtils.buildHqlStringForSQL(djId)+
                " and createuser="+HQLUtils.buildHqlStringForSQL(usercode);
        @SuppressWarnings("unchecked")
        List<UserbizoptLog> l=getHibernateTemplate().find(Hql);
        if(l==null||l.size()<1){
            return null;
        }else
            return l;
    }
	/**
	 * 查询排除他自己的阅读记录
	 * @param djId
	 * @param usercode
	 * @param nodeinstid
	 * @return
	 */
	public List<UserbizoptLog> listObjectNotSelf(String djId, String usercode,Long nodeinstid){
        String Hql=" from UserbizoptLog  where 1=1 "+
                " and dj_Id="+HQLUtils.buildHqlStringForSQL(djId)+
                " and createuser!="+HQLUtils.buildHqlStringForSQL(usercode)+
                 " and nodeinstid="+nodeinstid;;
        @SuppressWarnings("unchecked")
        List<UserbizoptLog> l=getHibernateTemplate().find(Hql);
        if(l==null||l.size()<1){
            return null;
        }else
            return l;
    }
}
