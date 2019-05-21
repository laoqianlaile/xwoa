package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaKqreception;

public class OaKqreceptionDao extends BaseDaoImpl<OaKqreception>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaKqreceptionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("layoutno" , CodeBook.LIKE_HQL_ID);

			filterField.put("kqdepname" , CodeBook.LIKE_HQL_ID);

			filterField.put("approtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvalremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("approval" , CodeBook.LIKE_HQL_ID);

			filterField.put("approphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("leavetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodycontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("lodgingplace" , CodeBook.LIKE_HQL_ID);

			filterField.put("lodgingcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("mealplace" , CodeBook.LIKE_HQL_ID);

			filterField.put("mealcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetplase" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetcontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("otheritems" , CodeBook.LIKE_HQL_ID);

			filterField.put("costtotalcapital" , CodeBook.LIKE_HQL_ID);

			filterField.put("costtotallowcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

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
