package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.RiskInfo;

public class RiskInfoDao extends BaseDaoImpl<RiskInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(RiskInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("riskid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("risktype" , CodeBook.LIKE_HQL_ID);

			filterField.put("riskdes" , CodeBook.LIKE_HQL_ID);

			filterField.put("riskdeal" , CodeBook.LIKE_HQL_ID);

			filterField.put("settime" , CodeBook.LIKE_HQL_ID);

			filterField.put("setuser" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
