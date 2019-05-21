package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.OuterNetFileinfoFs;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OuterNetFileinfoFsDao extends BaseDaoImpl<OuterNetFileinfoFs>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OuterNetFileinfoFsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("filecode" , CodeBook.EQUAL_HQL_ID);


			filterField.put("filename" , CodeBook.LIKE_HQL_ID);

			filterField.put("path" , CodeBook.LIKE_HQL_ID);
			filterField.put("isDelete" , CodeBook.EQUAL_HQL_ID);
			filterField.put("beforeRecorderDate" , "recorderDate < sysdate - ?");

		}
		return filterField;
	}



}
