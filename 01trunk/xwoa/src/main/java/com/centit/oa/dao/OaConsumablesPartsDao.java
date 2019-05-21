package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaConsumablesParts;

public class OaConsumablesPartsDao extends BaseDaoImpl<OaConsumablesParts>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaConsumablesPartsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("applydate" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("consContent" , CodeBook.LIKE_HQL_ID);

			filterField.put("consNum" , CodeBook.LIKE_HQL_ID);

			filterField.put("partsContent" , CodeBook.LIKE_HQL_ID);

			filterField.put("partsNum" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("optId" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
