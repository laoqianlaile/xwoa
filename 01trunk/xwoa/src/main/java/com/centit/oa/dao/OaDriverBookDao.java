package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaDriverBook;

public class OaDriverBookDao extends BaseDaoImpl<OaDriverBook>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaDriverBookDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.LIKE_HQL_ID);


			filterField.put("carno" , CodeBook.LIKE_HQL_ID);

			filterField.put("brand" , CodeBook.LIKE_HQL_ID);

			filterField.put("driver" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("caruser" , CodeBook.LIKE_HQL_ID);

			filterField.put("startDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("backDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("startMileage" , CodeBook.LIKE_HQL_ID);

			filterField.put("endMileage" , CodeBook.LIKE_HQL_ID);

			filterField.put("thisMileage" , CodeBook.LIKE_HQL_ID);

			filterField.put("totalCost" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
	         filterField.put("cardjid" , CodeBook.LIKE_HQL_ID);
			
		}
		return filterField;
	} 
}
