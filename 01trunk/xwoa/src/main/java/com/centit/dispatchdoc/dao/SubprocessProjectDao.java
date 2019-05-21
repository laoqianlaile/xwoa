package com.centit.dispatchdoc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.SubprocessProject;

public class SubprocessProjectDao extends BaseDaoImpl<SubprocessProject>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SubprocessProjectDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("headunitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("accpetDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("projectUnitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("projectName" , CodeBook.LIKE_HQL_ID);

			filterField.put("projectType" , CodeBook.LIKE_HQL_ID);

			filterField.put("evaluationReason" , CodeBook.LIKE_HQL_ID);

			filterField.put("evaluationContent" , CodeBook.LIKE_HQL_ID);

			filterField.put("opinions" , CodeBook.LIKE_HQL_ID);

			filterField.put("createUserCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("createDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("syncErrorDesc" , CodeBook.LIKE_HQL_ID);

			filterField.put("updateDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("syncDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("syncSign" , CodeBook.LIKE_HQL_ID);

			filterField.put("wfcode" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("supDjId" , CodeBook.EQUAL_HQL_ID);
			filterField.put("processType" , CodeBook.EQUAL_HQL_ID);
			filterField.put("fileName" , CodeBook.LIKE_HQL_ID);
			filterField.put("orgCode" , CodeBook.EQUAL_HQL_ID);
			filterField.put("finishDate" , CodeBook.EQUAL_HQL_ID);
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " updateDate desc ");
		}
		return filterField;
	} 
	
	public String genNextDjID(){
        return "WT" + getNextKeyBySequence("S_PERMIT_APPLY",14);
    }
	
	 @SuppressWarnings("unchecked")
    public List<String> getOptBaseNotOverList(String djId) {
	     
	        StringBuffer sql = new StringBuffer();
	        sql.append(" select t.dj_id from opt_base_info t left join m_subprocess_project m on t.dj_id = m.no where (t.bizstate != 'C' or  t.bizstate is null)  ");
	        if (StringUtils.isNotBlank(djId)) {
	            sql.append(" and m.sup_djid = '" + djId + "' ");
	        }
	        //System.out.println(sql.toString());
	        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
	        List<String> versionList = sqlQuery.list();
	        return versionList;

	    }
}
