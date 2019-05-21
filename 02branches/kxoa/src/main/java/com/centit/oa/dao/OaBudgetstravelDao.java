package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaBudgetstravel;

public class OaBudgetstravelDao extends BaseDaoImpl<OaBudgetstravel>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaBudgetstravelDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("address" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("trans" , CodeBook.LIKE_HQL_ID);

			filterField.put("transStandard" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountTrans" , CodeBook.LIKE_HQL_ID);

			filterField.put("accomStandard" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountAccom" , CodeBook.LIKE_HQL_ID);

			filterField.put("standardFood" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountFood" , CodeBook.LIKE_HQL_ID);

			filterField.put("miscellaneousStandard" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountMiscellaneous" , CodeBook.LIKE_HQL_ID);

			filterField.put("otStandard" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountOther" , CodeBook.LIKE_HQL_ID);

			filterField.put("otStandard2" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountOther2" , CodeBook.LIKE_HQL_ID);

			filterField.put("amountall" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowCode" , CodeBook.LIKE_HQL_ID);

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
