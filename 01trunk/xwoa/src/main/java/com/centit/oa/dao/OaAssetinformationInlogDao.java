package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaAssetinformationInlog;

public class OaAssetinformationInlogDao extends BaseDaoImpl<OaAssetinformationInlog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaAssetinformationInlogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("assetnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("assetunit" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createRemark" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begcreatertime" , "CREATETIME >= to_date(?,'yyyy-mm-dd')");
	            
	        filterField.put("endcreatertime" , "CREATETIME < to_date(?,'yyyy-mm-dd')+1");

		}
		return filterField;
	} 
}
