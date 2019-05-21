package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaKqvisitorgroup;
import com.centit.oa.po.OaMeetingmaterial;

public class OaKqvisitorgroupDao extends BaseDaoImpl<OaKqvisitorgroup>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaKqvisitorgroupDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("approtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvalremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodycontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("approval" , CodeBook.LIKE_HQL_ID);

			filterField.put("jdtype" , CodeBook.LIKE_HQL_ID);

			filterField.put("travel" , CodeBook.LIKE_HQL_ID);

			filterField.put("leavetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("lodgingcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("mealplace" , CodeBook.LIKE_HQL_ID);

			filterField.put("mealcase" , CodeBook.LIKE_HQL_ID);

			filterField.put("kqdepname" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetplase" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begTime", " approtime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " approtime< to_date(?, 'yyyy-mm-dd')+1 ");
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID ," approtime desc");

		}
		return filterField;
	}
	/*public List<OaKqvisitorgroup> listOaKqvisitorgroup(
	          Map<String, Object> filterMap, PageDesc pageDesc) {
	      String sHql=" from OaKqvisitorgroup t where 1=1 ";

	      if(StringUtils.isNotBlank((String)filterMap.get("begTime"))){
	          sHql+=" and approtime>= to_date('"+filterMap.get("begTime")+"', 'yyyy-MM-dd')";
	      }
	      if(StringUtils.isNotBlank((String)filterMap.get("endTime"))){
	          sHql+=" and approtime< to_date('"+filterMap.get("endTime")+"', 'yyyy-MM-dd')+1";
	      }
	      if(StringUtils.isNotBlank((String)filterMap.get("jdtype"))){
	          sHql+=" and jdtype="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("jdtype"));
	      }
	      if(StringUtils.isNotBlank((String)filterMap.get("kqdepname"))){
              sHql+=" and kqdepname="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("kqdepname"));
          }
	      List<OaKqvisitorgroup>  oaKqvisitorgroupList = null;
	      try {
	          oaKqvisitorgroupList=this.listObjects(sHql);
	      } catch (Exception e) {
	          log.error(e.getMessage());
	      }
	      
	      return oaKqvisitorgroupList;
	  } */
}
