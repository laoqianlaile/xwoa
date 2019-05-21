package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.po.VBbsThemeReplay;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class VBbsThemeReplayDao extends BaseDaoImpl<VBbsThemeReplay>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VBbsThemeReplayDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);

			filterField.put("lyno" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("infoType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("djid" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("usercode" ,  " creater=? ");

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("messagecomment" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("type" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("excludeStates", "state not in(?)");
			
			filterField.put("notDeleted", "  ( state <> ?  or state is null ) ");
			
			filterField.put("approvals", CodeBook.LIKE_HQL_ID);
			
			 filterField.put("layoutno" , CodeBook.EQUAL_HQL_ID);
	            
	         filterField.put("layoutcode" , CodeBook.EQUAL_HQL_ID);
	         
	         filterField.put("themenos" , "  djid in  (?) ");
	         
			//排序 未审核 ，时间排序  T\F通过和不通过N-正常 D-删除
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " DECODE(state,'N','1','T','2','F','3','D','4',state) , creatertime asc");

		}
		return filterField;
	}
	
	
}
