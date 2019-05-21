package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.ThreadAnnex;

public class ThreadAnnexDao extends BaseDaoImpl<ThreadAnnex>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(ThreadAnnexDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("threadid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("filecode" , CodeBook.EQUAL_HQL_ID);


		}
		return filterField;
	} 
}
