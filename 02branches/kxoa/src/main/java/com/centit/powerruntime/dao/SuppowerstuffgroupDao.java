package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.Suppowerstuffgroup;

public class SuppowerstuffgroupDao extends BaseDaoImpl<Suppowerstuffgroup>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SuppowerstuffgroupDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("groupId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("itemId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("stuffGroup" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("groupDesc" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	public String  getNextGroupCode(){
	    Long sNo = 00000000+Long.parseLong(getNextValueOfSequence("S_STUFFGROUP"));
	    return sNo.toString();
	    
	}
}
