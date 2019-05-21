package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centit.bbs.po.OaBbsDiscussion;

public class OaBbsDiscussionDao extends BaseDaoImpl<OaBbsDiscussion>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaBbsDiscussionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("layoutno" , CodeBook.EQUAL_HQL_ID);


			filterField.put("layoutcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("sublayouttitle" , CodeBook.LIKE_HQL_ID);

			filterField.put("showpicturename" , CodeBook.LIKE_HQL_ID);

			filterField.put("showpicture" , CodeBook.LIKE_HQL_ID);

			filterField.put("orderno" , CodeBook.LIKE_HQL_ID);

			filterField.put("subjectnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("postsnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvalresults" , CodeBook.LIKE_HQL_ID);

			filterField.put("approval" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvaltime" , CodeBook.LIKE_HQL_ID);

			filterField.put("openType" , CodeBook.LIKE_HQL_ID);

			filterField.put("isneed" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("planBeginTimeBegin" , "createtime >= to_date(?,'yyyy-mm-dd')");
		    filterField.put("planBeginTimeEnd" , "createtime < to_date(?,'yyyy-mm-dd')+1");
			
			filterField.put("oaBbsDashboard.approvals",  CodeBook.LIKE_HQL_ID);
			filterField.put("excludeStates", "state not in(?)");
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "orderno asc");
			
		}
		return filterField;
	} 
	
    public String getNextValueOfSequence(){
        
        return super.getNextValueOfSequence("S_BBS_DISCUSSION");
    }
   
}
