package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaMeetingmaterial;

public class OaMeetingmaterialDao extends BaseDaoImpl<OaMeetingmaterial>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetingmaterialDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("mId" , CodeBook.EQUAL_HQL_ID);
			filterField.put("meetingName" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingAgenda" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingAttendees" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("meetingAttendeesCodes" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingUnitnames" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingComein" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingComeindeps" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingAddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("motifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isuse" , CodeBook.EQUAL_HQL_ID);
			
			/*filterField.put("begTime", " meetingTime>= to_date(?, 'yyyy-MM-dd hh24:mi:ss') ");
			
            filterField.put("endTime", " meetingTime<= to_date(?, 'yyyy-MM-dd hh24:mi:ss') ");*/
            
            filterField.put("begTime", " meetingTime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " meetingTime< to_date(?, 'yyyy-mm-dd')+1 ");
            
            
            //接口查询条件--时间戳
            filterField.put("pBegin", " meetingTime > to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            filterField.put("pEnd", " meetingTime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            filterField.put("currentdatetime", " meetingTime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");

            filterField.put(CodeBook.ORDER_BY_HQL_ID," meetingTime desc" );
		}
		return filterField;
	} 
    /**
   * 得到主键
   * @return
   */
  public String getDjId(){
      return super.getNextKeyByHqlStrOfMax("djId", "OaMeetingmaterial", 16);
  }
  
  public List<OaMeetingmaterial> listOaMeetingmaterial(
          Map<String, Object> filterMap, PageDesc pageDesc) {
      String sHql=" from OaMeetingmaterial t where 1=1 ";

      if(StringUtils.isNotBlank((String)filterMap.get("begTime"))){
          /*sHql+=" and meetingTime>= to_date('"+filterMap.get("begTime")+"', 'yyyy-mm-dd')";*/
          sHql+=" and meetingTime>= to_date('"+filterMap.get("begTime")+"', 'yyyy-MM-dd hh24:mi:ss')";
      }
      if(StringUtils.isNotBlank((String)filterMap.get("endTime"))){
          sHql+=" and meetingTime< to_date('"+filterMap.get("endTime")+"', 'yyyy-MM-dd hh24:mi:ss')";
      }

      
      List<OaMeetingmaterial>  oaMeetingmaterialList = null;
      try {
          oaMeetingmaterialList=this.listObjects(sHql);
          pageDesc.setTotalRows(oaMeetingmaterialList.size());
      } catch (Exception e) {
          log.error(e.getMessage());
      }
      
      return oaMeetingmaterialList;
  } 
}
