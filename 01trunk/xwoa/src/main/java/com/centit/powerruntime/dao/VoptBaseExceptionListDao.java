package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.VoptBaseExceptionList;

public class VoptBaseExceptionListDao extends BaseDaoImpl<VoptBaseExceptionList>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VoptBaseExceptionListDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);


			filterField.put("djId" , CodeBook.LIKE_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizusernames" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizusercodes" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("itemType" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("type" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("typeName" , CodeBook.LIKE_HQL_ID);
			
			 filterField.put("begTime",
	                    " createdate>= to_date(?, 'yyyy-mm-dd') ");

	            filterField.put("endTime",
	                    " createdate<= to_date(?, 'yyyy-mm-dd')+1 ");
	            
	         filterField.put("controlTime", " (sysdate-o.CREATETIME)>=?");
	         
	         filterField.put(CodeBook.ORDER_BY_HQL_ID, "createdate desc");

		}
		return filterField;
	} 
	
	
}
