package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.po.SignedReport;

public class SignedReportDao extends BaseDaoImpl<SignedReport>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SignedReportDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("secretsDegree" , CodeBook.LIKE_HQL_ID);

			filterField.put("criticalLeveal" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedReportTitle" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedReportNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedPersion" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("otherDep" , CodeBook.LIKE_HQL_ID);

			filterField.put("mainSignedDep" , CodeBook.LIKE_HQL_ID);

			filterField.put("draftUserName" , CodeBook.LIKE_HQL_ID);

			filterField.put("draftDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("draftTelephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("retentionPeriod" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedReportFile" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedReportFileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("recordId" , CodeBook.LIKE_HQL_ID);

			filterField.put("queryPower" , CodeBook.LIKE_HQL_ID);

			filterField.put("particularGroup" , CodeBook.LIKE_HQL_ID);

			filterField.put("archiveDepno" , CodeBook.LIKE_HQL_ID);

			filterField.put("archiveNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("signedFileType" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowinstid" , CodeBook.LIKE_HQL_ID);
		    filterField.put("itemId", CodeBook.LIKE_HQL_ID);
		    filterField.put("begTime", " draftDate>= to_date(?, 'yyyy-mm-dd') ");
		           
	         filterField.put("endTime", " draftDate<= to_date(?, 'yyyy-mm-dd')+1 ");

		}
		return filterField;
	} 
}
