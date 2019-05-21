package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaSchedule;

public class OaScheduleDao extends BaseDaoImpl<OaSchedule>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaScheduleDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("schno" , CodeBook.EQUAL_HQL_ID);


			filterField.put("planBegTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("planEndTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("address" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("sehType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("itemtype" , CodeBook.EQUAL_HQL_ID);

			filterField.put("meetid" , CodeBook.LIKE_HQL_ID);

			filterField.put("city" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("secretDegress" , CodeBook.LIKE_HQL_ID);

			filterField.put("noticesign" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastnoticetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("taskdeadline" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("importantTag" , CodeBook.EQUAL_HQL_ID);
			
			  //申请时间
            filterField.put("planBeginTimeBegin", " planBegTime >= to_date(?,'yyyy-MM-dd')");
            
            filterField.put("planBeginTimeEnd", " planBegTime <= to_date(?,'yyyy-MM-dd')+1");

            filterField.put("leaders" , CodeBook.LIKE_HQL_ID);
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "planBegTime desc");
            
            filterField.put("createdateBegin", " createdate > to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            
            
            
            //接口查询条件--时间戳
            filterField.put("pBegin", " planBegTime > to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            filterField.put("pEnd", " planBegTime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            filterField.put("currentdatetime", " createdate < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            
            //日历查询
            filterField.put("cBegin", " planBegTime>=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
            filterField.put("cEnd", " planBegTime<=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
            
            
		}
		return filterField;
	}

    public void deleteByDjId(String djId) {
        final String hql = "delete from OaSchedule o where o.djId = ? ";
        this.doExecuteHql(hql,djId);
        
    } 
}
