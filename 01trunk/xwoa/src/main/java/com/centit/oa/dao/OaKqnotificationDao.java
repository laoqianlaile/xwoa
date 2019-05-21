package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaKqnotification;

public class OaKqnotificationDao extends BaseDaoImpl<OaKqnotification>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaKqnotificationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("periods" , CodeBook.LIKE_HQL_ID);

			filterField.put("kqdepname" , CodeBook.LIKE_HQL_ID);

			filterField.put("kqtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("visitors" , CodeBook.LIKE_HQL_ID);

			filterField.put("contactuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("contactphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("kqcontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("optId" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
