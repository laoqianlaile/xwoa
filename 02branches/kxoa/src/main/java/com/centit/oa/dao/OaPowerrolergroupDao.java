package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaPowerrolergroup;

public class OaPowerrolergroupDao extends BaseDaoImpl<OaPowerrolergroup>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaPowerrolergroupDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("groupType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("groupName" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("NP_state", " state='T' or state is null ");

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "groupName desc");

		}
		return filterField;
	} 
}
