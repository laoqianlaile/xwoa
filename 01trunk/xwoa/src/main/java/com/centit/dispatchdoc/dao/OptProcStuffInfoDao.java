package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.po.OptProcStuffInfo;

public class OptProcStuffInfoDao extends BaseDaoImpl<OptProcStuffInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptProcStuffInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("stuffid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("filecode" , CodeBook.LIKE_HQL_ID);

			filterField.put("djId" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);

			filterField.put("filename" , CodeBook.LIKE_HQL_ID);

			filterField.put("filetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadusercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("archivetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("sign" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
