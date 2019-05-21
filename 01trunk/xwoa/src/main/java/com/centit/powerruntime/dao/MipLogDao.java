package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.po.MipLog;

public class MipLogDao extends BaseDaoImpl<MipLog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(MipLogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("mipid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("infmethods" , CodeBook.LIKE_HQL_ID);

			filterField.put("accparameters" , CodeBook.LIKE_HQL_ID);

			filterField.put("returnddata" , CodeBook.LIKE_HQL_ID);

			filterField.put("exceptioninfo" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("remarkMethods" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " createtime desc");

		}
		return filterField;
	} 
}
