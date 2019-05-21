package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.OptIdeaInfo;

public class OptIdeaInfoDao extends BaseDaoImpl<OptIdeaInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptIdeaInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("procid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);

			filterField.put("optcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("djId" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitname" , CodeBook.LIKE_HQL_ID);

			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("username" , CodeBook.LIKE_HQL_ID);

			filterField.put("transdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("ideacode" , CodeBook.LIKE_HQL_ID);

			filterField.put("transidea" , CodeBook.LIKE_HQL_ID);

			filterField.put("transcontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeinststate" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
    public long getNextIdeaPK() {
        String sNo = getNextValueOfSequence("S_OPT_IDEA_INFO");
        return Long.valueOf(sNo);
    }

    public OptIdeaInfo getObjectByNodeInstId(long nodeInstId) {
        @SuppressWarnings("unchecked")
        List<OptIdeaInfo> procs = (List<OptIdeaInfo>) getHibernateTemplate()
                .find("From OptIdeaInfo where nodeInstId=?", nodeInstId);
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }
    
    public List<OptIdeaInfo> getObjectsByNodeCode(String djId,String nodeCode){
        return  this.listObjects("From OptIdeaInfo" +
                " where djId = ? and nodeCode=? order by transdate ",new Object[]{djId,nodeCode});
    }
	
}
