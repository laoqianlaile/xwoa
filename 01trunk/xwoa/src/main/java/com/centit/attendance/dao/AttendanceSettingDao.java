package com.centit.attendance.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.attendance.po.AttendanceSetting;

public class AttendanceSettingDao extends BaseDaoImpl<AttendanceSetting>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(AttendanceSettingDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("attcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastcodedate" , CodeBook.LIKE_HQL_ID);

			filterField.put("schedulingtype" , CodeBook.LIKE_HQL_ID);

			filterField.put("statetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("onetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("twotype" , CodeBook.LIKE_HQL_ID);

			filterField.put("threetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("fourtype" , CodeBook.LIKE_HQL_ID);

			filterField.put("fivetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("sixtype" , CodeBook.LIKE_HQL_ID);

			filterField.put("onetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("twotime" , CodeBook.LIKE_HQL_ID);

			filterField.put("threetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("fourtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("fivetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("sextime" , CodeBook.LIKE_HQL_ID);

			filterField.put("notworkweek" , CodeBook.LIKE_HQL_ID);

			filterField.put("notworkdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("personnelcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("intermissiontime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);

			filterField.put("saattendancetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("xaattendancetime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
	/**
	 * 查询设置时间，返回一个实体
	 */
	public AttendanceSetting findTime(){
        StringBuffer sb = new StringBuffer();
        sb.append("select a.djid,a.saattendancetime,a.xaattendancetime from oa_attendance_setting a");
        @SuppressWarnings("unchecked")
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());
        AttendanceSetting aat =new AttendanceSetting();
        for(Object[] obj : list){
            aat =new AttendanceSetting();
            if(obj[0]!=null)
                aat.setDjid(obj[0].toString());
            if(obj[1]!=null)
                aat.setSaattendancetime(obj[1].toString());
            if(obj[2]!=null)
                aat.setXaattendancetime(obj[2].toString());
        }
        
        return aat;
	}
	
}
