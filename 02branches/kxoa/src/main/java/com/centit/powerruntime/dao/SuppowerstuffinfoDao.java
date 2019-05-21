package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.Suppowerstuffinfo;

public class SuppowerstuffinfoDao extends BaseDaoImpl<Suppowerstuffinfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SuppowerstuffinfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("sortId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("groupId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("stuffType" , CodeBook.LIKE_HQL_ID);

			filterField.put("stuffName" , CodeBook.LIKE_HQL_ID);

			filterField.put("isNeed" , CodeBook.LIKE_HQL_ID);

			filterField.put("isElectron" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("archivetype" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " stuffOrder asc ");
			

		}
		return filterField;
	} 
	
	@SuppressWarnings("unchecked")
    public List<Suppowerstuffinfo> getinfosByGroupId( String groupid){
	    
	    String hql="From Suppowerstuffinfo where groupId = ? order by stuffOrder asc";
	    return getHibernateTemplate().find(hql, groupid);
	}

    public void deleteObjectByGroupId(String groupId) {
        doExecuteHql(
                "delete from Suppowerstuffinfo  where groupId=?) ",
                groupId);
        
    }
 
	

}
