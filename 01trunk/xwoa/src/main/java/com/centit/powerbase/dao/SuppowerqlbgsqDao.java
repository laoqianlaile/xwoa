package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerbase.po.Suppowerqlbgsq;

public class SuppowerqlbgsqDao extends BaseDaoImpl<Suppowerqlbgsq>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SuppowerqlbgsqDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("changeId" , CodeBook.EQUAL_HQL_ID);
			 filterField.put("itemId" , CodeBook.LIKE_HQL_ID);
	         filterField.put("version" , CodeBook.LIKE_HQL_ID);



			filterField.put("beginTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("orgId" , CodeBook.LIKE_HQL_ID);

			filterField.put("itemName" , CodeBook.LIKE_HQL_ID);

			filterField.put("itemStaName" , CodeBook.LIKE_HQL_ID);

			filterField.put("itemType" , CodeBook.LIKE_HQL_ID);

			filterField.put("timeLimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("isNetwork" , CodeBook.LIKE_HQL_ID);

			filterField.put("isFormula" , CodeBook.LIKE_HQL_ID);

			filterField.put("phone" , CodeBook.LIKE_HQL_ID);

			filterField.put("address" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("auditSign" , CodeBook.LIKE_HQL_ID);

			filterField.put("monitorPhone" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptLink" , CodeBook.LIKE_HQL_ID);

			filterField.put("legalTimeLimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("charge" , CodeBook.LIKE_HQL_ID);

			filterField.put("formName" , CodeBook.LIKE_HQL_ID);

			filterField.put("fileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("inFlowImgName" , CodeBook.LIKE_HQL_ID);

			filterField.put("ischarge" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishClass" , CodeBook.LIKE_HQL_ID);

			filterField.put("transactDepname" , CodeBook.LIKE_HQL_ID);

			filterField.put("promiseType" , CodeBook.LIKE_HQL_ID);

			filterField.put("anticipateType" , CodeBook.LIKE_HQL_ID);

			filterField.put("qlDepid" , CodeBook.LIKE_HQL_ID);

			filterField.put("qlDepstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("entrustName" , CodeBook.LIKE_HQL_ID);

			filterField.put("qlState" , CodeBook.LIKE_HQL_ID);

			filterField.put("replyPeople" , CodeBook.LIKE_HQL_ID);

			filterField.put("auditor" , CodeBook.LIKE_HQL_ID);

			filterField.put("requester" , CodeBook.LIKE_HQL_ID);
			
			
			
			filterField.put("chgContent" , CodeBook.LIKE_HQL_ID);

            filterField.put("chgReason" , CodeBook.LIKE_HQL_ID);

            filterField.put("chgResult" , CodeBook.LIKE_HQL_ID);

            filterField.put("requestTime" , CodeBook.LIKE_HQL_ID);

            filterField.put("auditContent" , CodeBook.LIKE_HQL_ID);

            filterField.put("auditTime" , CodeBook.LIKE_HQL_ID);

            filterField.put("replyContent" , CodeBook.LIKE_HQL_ID);

            filterField.put("replyTime" , CodeBook.LIKE_HQL_ID);
            filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	 public Suppowerqlbgsq getSuppowerqlbgsqInfo(String itemId, Long version) {
	        List<Suppowerqlbgsq> procs = this.listObjects("From Suppowerqlbgsq where item_id =  "+HQLUtils.buildHqlStringForSQL(itemId)+" and version="+HQLUtils.buildHqlStringForSQL(Long.toString(version)));
	        if (procs == null || procs.size() < 1)
	            return null;
	        return procs.get(0);
	    } 
}
