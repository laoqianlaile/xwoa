package com.centit.app.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.OaWorkingTime;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.support.utils.DatetimeOpt;


public class OaWorkingTimeDao extends BaseDaoImpl<OaWorkingTime>
	{
    private static final long serialVersionUID = 1L;
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("worktype" , CodeBook.EQUAL_HQL_ID);

			filterField.put("worktypename" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule1" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule1begin" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule1end" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule2" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule2begin" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule2end" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule3" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule3begin" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule3end" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule4" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule4begin" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedule4end" , CodeBook.LIKE_HQL_ID);

			filterField.put("automatch" , CodeBook.LIKE_HQL_ID);

			filterField.put("matchbeginday" , CodeBook.LIKE_HQL_ID);

			filterField.put("matchendday" , CodeBook.LIKE_HQL_ID);

			filterField.put("worktypedesc" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	//判断日期的所属的工作时间
	public OaWorkingTime getObjectbyDate(Date date){
	    int m=DatetimeOpt.getMonth(date)*100+DatetimeOpt.getDay(date);
	    List<OaWorkingTime> list= listObjects();
	    OaWorkingTime o = null;
	    if(list!=null)
    	    for(OaWorkingTime a:list){
    	        if("1".equals(a.getAutomatch())){
    	           if(m>toDateInt(a.getMatchbeginday())&&m<toDateInt(a.getMatchendday()) )
    	               return a;
    	        }	        
    	    }	    
	    return o;
	}
	//str的第一位为+号 ，则加12
	public int toDateInt(String str){
	    int m =0;
	    if(str.startsWith("+")){
	        str=str.substring(1);
	        m=Integer.parseInt(str);
	        m=m+1200;
	    }	    
	    return m;
	}
}
