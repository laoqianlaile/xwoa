package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaAccidentRecord;

public class OaAccidentRecordDao extends BaseDaoImpl<OaAccidentRecord>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaAccidentRecordDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("carno" , CodeBook.LIKE_HQL_ID);

			filterField.put("carType" , CodeBook.LIKE_HQL_ID);

			filterField.put("brand" , CodeBook.LIKE_HQL_ID);

			filterField.put("modelType" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("douser" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("dotime" , CodeBook.LIKE_HQL_ID);

			filterField.put("doaddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsibility" , CodeBook.LIKE_HQL_ID);

			filterField.put("casualties" , CodeBook.LIKE_HQL_ID);

			filterField.put("losses" , CodeBook.LIKE_HQL_ID);

			filterField.put("penalty" , CodeBook.LIKE_HQL_ID);

			filterField.put("actualDamages" , CodeBook.LIKE_HQL_ID);

			filterField.put("claimDifference" , CodeBook.LIKE_HQL_ID);

			filterField.put("accidentAfter" , CodeBook.LIKE_HQL_ID);

			filterField.put("scenePhotos" , CodeBook.LIKE_HQL_ID);

			filterField.put("photoName" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);
            filterField.put("cardjid" , CodeBook.LIKE_HQL_ID);
		}
		return filterField;
	} 
}
