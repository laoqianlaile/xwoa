package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.bbs.po.OaLeaveMessagereply;

public class OaLeaveMessagereplyDao extends BaseDaoImpl<OaLeaveMessagereply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaLeaveMessagereplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("lyno" , CodeBook.EQUAL_HQL_ID);


			filterField.put("lno" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"creatertime asc");

			filterField.put("messagecomment" , CodeBook.LIKE_HQL_ID);
            
			filterField.put("state" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("excludeStates" , "state not in (?)");
		}
		return filterField;
	} 
}
