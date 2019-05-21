
package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaCommonType;

public class OaCommonTypeDao extends BaseDaoImpl<OaCommonType>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaCommonTypeDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("publicType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("orderno" , CodeBook.LIKE_HQL_ID);

			filterField.put("coding" , CodeBook.LIKE_HQL_ID);

			filterField.put("comName" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("owner" , " ( creater=? or isopen= '1' ) ");
			
			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isopen" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " publicType,orderno asc");
			
			filterField.put("NP_state" , "  (state='T' or state is null ) " );
		}
		return filterField;
	} 
}
