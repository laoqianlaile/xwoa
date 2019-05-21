package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.dispatchdoc.po.OptLeaderShip;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class OptLeaderShipDao extends BaseDaoImpl<OptLeaderShip>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptLeaderShipDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("leaderNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("leaderName" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("shipDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("leaderNote" , CodeBook.LIKE_HQL_ID);

			filterField.put("createUserCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("createDate" , CodeBook.LIKE_HQL_ID);
			
		}
		return filterField;
	} 
}
