package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.po.OaInfosummary;
import com.centit.sys.security.FUserDetail;

public class OaInfosummaryDao extends BaseDaoImpl<OaInfosummary>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaInfosummaryDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("sex" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	@SuppressWarnings("unchecked")
    public  List<OaInfosummary> findInfoAttend(String no,String creater){
        return (List<OaInfosummary>) super.findObjectsByHql("from OaInfosummary where no=? and creater=?",  new Object[]{no,creater});
    }
	@SuppressWarnings("unchecked")
    public  List<OaInfosummary> findInfoAttendByUser(String creater){
        return (List<OaInfosummary>) super.findObjectsByHql("from OaInfosummary where creater=?",  new Object[]{creater});
    }
}
