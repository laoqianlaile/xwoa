package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaCarinfo;

public class OaCarinfoDao extends BaseDaoImpl<OaCarinfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaCarinfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("carno" , CodeBook.LIKE_HQL_ID);

			filterField.put("isuse" , CodeBook.LIKE_HQL_ID);

			filterField.put("brand" , CodeBook.LIKE_HQL_ID);

			filterField.put("modelType" , CodeBook.LIKE_HQL_ID);

			filterField.put("carType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("ratifyNumber" , CodeBook.LIKE_HQL_ID);

			filterField.put("ratifyLoad" , CodeBook.LIKE_HQL_ID);

			filterField.put("ratifyOil" , CodeBook.LIKE_HQL_ID);

			filterField.put("displacement" , CodeBook.LIKE_HQL_ID);

			filterField.put("oilLabel" , CodeBook.LIKE_HQL_ID);

			filterField.put("frameNumber" , CodeBook.LIKE_HQL_ID);

			filterField.put("engineNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("driver" , CodeBook.LIKE_HQL_ID);

			filterField.put("usingNature" , CodeBook.LIKE_HQL_ID);

			filterField.put("carItems" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsibleDep" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsiblePersion" , CodeBook.LIKE_HQL_ID);

			filterField.put("carPicture" , CodeBook.LIKE_HQL_ID);

			filterField.put("carPictureName" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("motifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("NP_isuse", " (isuse='T' or isuse is null ) ");
			
			filterField.put("rangeType" , CodeBook.EQUAL_HQL_ID);
			
		}
		return filterField;
	} 
}
