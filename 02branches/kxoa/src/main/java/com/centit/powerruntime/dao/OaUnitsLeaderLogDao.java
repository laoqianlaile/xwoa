package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.po.OaUnitsLeaderLog;

public class OaUnitsLeaderLogDao extends BaseDaoImpl<OaUnitsLeaderLog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaUnitsLeaderLogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("leadercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("isuse" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcodes" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
