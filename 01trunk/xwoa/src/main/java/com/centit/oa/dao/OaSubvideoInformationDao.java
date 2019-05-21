package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaSubvideoInformation;

public class OaSubvideoInformationDao extends BaseDaoImpl<OaSubvideoInformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaSubvideoInformationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("releaseDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("isuseprarent" , CodeBook.LIKE_HQL_ID);

			filterField.put("smallimage" , CodeBook.LIKE_HQL_ID);

			filterField.put("videoPath" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("clickNum" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"creatertime desc");

		}
		return filterField;
	} 
}
