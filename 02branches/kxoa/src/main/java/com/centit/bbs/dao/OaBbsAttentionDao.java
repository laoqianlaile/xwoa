package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.bbs.po.OaBbsAttention;

public class OaBbsAttentionDao extends BaseDaoImpl<OaBbsAttention>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaBbsAttentionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("themeno" , CodeBook.EQUAL_HQL_ID );

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);


			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("laytype" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " createtime desc ");

		}
		return filterField;
	} 
}
