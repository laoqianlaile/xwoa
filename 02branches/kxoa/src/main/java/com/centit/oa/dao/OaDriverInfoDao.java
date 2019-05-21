package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaDriverInfo;

public class OaDriverInfoDao extends BaseDaoImpl<OaDriverInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaDriverInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("codenum" , CodeBook.LIKE_HQL_ID);

			filterField.put("name" , CodeBook.LIKE_HQL_ID);

			filterField.put("drLicenseNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("releaseDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("validDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("publicType" , CodeBook.LIKE_HQL_ID);

			filterField.put("beenDriving" , CodeBook.LIKE_HQL_ID);

			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("birthDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("age" , CodeBook.LIKE_HQL_ID);

			filterField.put("sex" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobile" , CodeBook.LIKE_HQL_ID);

			filterField.put("address" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("workExperience" , CodeBook.LIKE_HQL_ID);

			filterField.put("personalPhoto" , CodeBook.LIKE_HQL_ID);

			filterField.put("photoname" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("NP_state", " (state='T' or state is null) ");
			
			filterField.put("NP_state_W", " state<>'W') ");
		}
		return filterField;
	} 
}
