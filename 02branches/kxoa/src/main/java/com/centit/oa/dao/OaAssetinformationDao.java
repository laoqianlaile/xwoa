package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaAssetinformation;

public class OaAssetinformationDao extends BaseDaoImpl<OaAssetinformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaAssetinformationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("datacode" , CodeBook.LIKE_HQL_ID);

			filterField.put("assetnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("assetunit" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("createDepno" , CodeBook.LIKE_HQL_ID);

			filterField.put("senddeptype" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);

			filterField.put("assetleftalert" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begcreatertime" , "CREATETIME >= to_date(?,'yyyy-mm-dd')");
			
			filterField.put("endcreatertime" , "CREATETIME < to_date(?,'yyyy-mm-dd')+1");

		}
		return filterField;
	} 
}
