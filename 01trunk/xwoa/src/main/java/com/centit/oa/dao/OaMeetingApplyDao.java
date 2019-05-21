package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaMeetingApply;

public class OaMeetingApplyDao extends BaseDaoImpl<OaMeetingApply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetingApplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("meetApplyName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("meetApplyAddress" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("attendLeaderCode" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("attendLeaderName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("attendUnitName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("foreigUserName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("foreigUnitName" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("meetingRemark" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

            filterField.put(CodeBook.ORDER_BY_HQL_ID," createtime desc" );
		}
		return filterField;
	} 
   
}
