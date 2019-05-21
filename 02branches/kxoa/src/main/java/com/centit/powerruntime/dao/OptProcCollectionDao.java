package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.po.OptProcCollection;

public class OptProcCollectionDao extends BaseDaoImpl<OptProcCollection>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptProcCollectionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);


			filterField.put("atttype" , CodeBook.LIKE_HQL_ID);

			filterField.put("attsettime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isatt" , CodeBook.LIKE_HQL_ID);

			filterField.put("removesettime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID , " attsettime desc ");

		}
		return filterField;
	} 
}
