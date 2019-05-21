package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaUnitIncomedoc;

public class OaUnitIncomedocDao extends BaseDaoImpl<OaUnitIncomedoc>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaUnitIncomedocDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);

			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("isopen" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("updateuser" , CodeBook.LIKE_HQL_ID);
			
		}
		return filterField;
	}
}
