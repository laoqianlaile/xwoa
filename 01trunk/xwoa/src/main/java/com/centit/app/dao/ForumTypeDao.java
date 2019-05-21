package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.ForumType;

public class ForumTypeDao extends BaseDaoImpl<ForumType>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(ForumTypeDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("type" , CodeBook.EQUAL_HQL_ID);

			filterField.put("forumid" , CodeBook.EQUAL_HQL_ID);


		}
		return filterField;
	} 
}
