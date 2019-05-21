package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.po.OaLeaderunits;

public class OaLeaderunitsDao extends BaseDaoImpl<OaLeaderunits>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaLeaderunitsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("leadercode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("unitcode" , CodeBook.EQUAL_HQL_ID);


		}
		return filterField;
	}

    public void deleteObjects(String leadercode) {
        // TODO Auto-generated method stub
        String sql=" delete from OaLeaderunits where leadercode=? ";
        this.doExecuteHql(sql,
                new String[]{leadercode});
    } 
}
