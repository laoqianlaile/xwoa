package com.centit.sys.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.sys.po.UseroptLog;

public class UseroptLogDao extends BaseDaoImpl<UseroptLog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(UseroptLogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);


			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizname" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("runerrortype" , CodeBook.LIKE_HQL_ID);

			filterField.put("createrip" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begCreateTime" , " createtime >= to_date(?, 'yyyy-mm-dd') ");
			
			filterField.put("endCreateTime" , " createtime < to_date(?, 'yyyy-mm-dd')+1 ");

		}
		return filterField;
	} 
}
