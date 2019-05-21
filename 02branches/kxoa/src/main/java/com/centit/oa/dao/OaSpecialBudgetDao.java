package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaSpecialBudget;

public class OaSpecialBudgetDao extends BaseDaoImpl<OaSpecialBudget>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaSpecialBudgetDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("meettype" , CodeBook.LIKE_HQL_ID);

			filterField.put("outerPersions" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingPersionsNum" , CodeBook.LIKE_HQL_ID);

			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard2" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount 2" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard3" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount 3" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard4" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount 4" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard5" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount 5" , CodeBook.LIKE_HQL_ID);

			filterField.put("standard6" , CodeBook.LIKE_HQL_ID);

			filterField.put("amount6" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountall" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
}
