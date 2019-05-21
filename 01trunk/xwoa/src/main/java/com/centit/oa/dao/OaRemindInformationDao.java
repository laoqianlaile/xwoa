package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaRemindInformation;

public class OaRemindInformationDao extends BaseDaoImpl<OaRemindInformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaRemindInformationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("thesign" , CodeBook.LIKE_HQL_ID);

			filterField.put("djid" , CodeBook.LIKE_HQL_ID);

			filterField.put("reType" , CodeBook.EQUAL_HQL_ID);

//			filterField.put("begtime" , CodeBook.LIKE_HQL_ID);
//
//			filterField.put("endtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("frequency" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

//			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createRemark" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begTime" , "BEGTIME >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
	        filterField.put("endTime" , "ENDTIME <= to_date(?,'yyyy-MM-dd hh24:mi:ss')-1");
//	        filterField.put("begTime", "createtime >= to_date(?,'yyyy-mm-dd')");
//            filterField.put("endTime", "createtime <= to_date(?,'yyyy-mm-dd')+1");    
	        filterField.put("createtime" ,"createtime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
	        
	        //提醒发起时间查询条件
	        filterField.put("begCreatetime" , "createtime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            filterField.put("endCreatetime" , "createtime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put("begtimetoday" , "begtime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            filterField.put("endtimetoday" , "begtime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " createtime desc ");

		}
		return filterField;
	} 
}
