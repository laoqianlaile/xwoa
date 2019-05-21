package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaLeaveReply;

public class OaLeaveReplyDao extends BaseDaoImpl<OaLeaveReply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaLeaveReplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("ino" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("messagecomment" , CodeBook.LIKE_HQL_ID);

			filterField.put("perno" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
