package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.VOptShowIdeaInfo;

public class VOptShowIdeaInfoDao extends BaseDaoImpl<VOptShowIdeaInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOptShowIdeaInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);
		}
		return filterField;
	} 

    
   
	
}
