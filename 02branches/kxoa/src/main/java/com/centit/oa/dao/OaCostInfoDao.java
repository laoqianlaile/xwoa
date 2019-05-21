package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaCostInfo;

public class OaCostInfoDao extends BaseDaoImpl<OaCostInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaCostInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("costType" , CodeBook.LIKE_HQL_ID);

			filterField.put("thecost" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
