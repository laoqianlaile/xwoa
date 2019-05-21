package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.VoptIdeaInfo;

public class VoptIdeaInfoDao extends BaseDaoImpl<VoptIdeaInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VoptIdeaInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("procid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);


			filterField.put("djId" , CodeBook.LIKE_HQL_ID);


			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("username" , CodeBook.LIKE_HQL_ID);

			filterField.put("transdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " transdate desc ");
			
			filterField.put("NP_today", " trunc(transdate)=trunc(sysdate) ");

		}
		return filterField;
	} 
	
	
}
