package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaEquipmentpurchase;

public class OaEquipmentpurchaseDao extends BaseDaoImpl<OaEquipmentpurchase>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaEquipmentpurchaseDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("itentype" , CodeBook.LIKE_HQL_ID);

			filterField.put("ecategory" , CodeBook.LIKE_HQL_ID);

			filterField.put("tmodel" , CodeBook.LIKE_HQL_ID);

			filterField.put("thenumber" , CodeBook.LIKE_HQL_ID);

			filterField.put("theprice" , CodeBook.LIKE_HQL_ID);

			filterField.put("serialnumber" , CodeBook.LIKE_HQL_ID);

			filterField.put("applydate" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("remarkContent" , CodeBook.LIKE_HQL_ID);

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
