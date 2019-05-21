package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaBizBindInfo;

public class OaBizBindInfoDao extends BaseDaoImpl<OaBizBindInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaBizBindInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("startDjid" , CodeBook.LIKE_HQL_ID);

			filterField.put("endDjid" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizType" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "  createtime desc ");

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}

    public void deleteStartObjectById(String djId) {
        // TODO Auto-generated method stub
        final String hql = "delete from OaBizBindInfo o where o.endDjid = ? ";
        this.doExecuteHql(hql,djId);
        
    } 
}
