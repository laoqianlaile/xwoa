package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaFeedback;

public class OaFeedbackDao extends BaseDaoImpl<OaFeedback>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaFeedbackDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.LIKE_HQL_ID);


			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("feedFile" , CodeBook.LIKE_HQL_ID);

			filterField.put("feedFileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("reception" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isanonymous" , CodeBook.LIKE_HQL_ID);

			filterField.put("replyInformation" , CodeBook.LIKE_HQL_ID);

			filterField.put("replyTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isopen" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("isAnswer" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("begcreatertime" , "creatertime >= to_date(?,'yyyy-mm-dd')");
			filterField.put("endcreatertime" , "creatertime < to_date(?,'yyyy-mm-dd')+1");
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "creatertime desc");
		}
		return filterField;
	} 
}
