package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerbase.po.PowerUserInfo;

public class PowerUserInfoDao extends BaseDaoImpl<PowerUserInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(PowerUserInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("itemId" , " item_id=? ");


			filterField.put("setoperator" , CodeBook.LIKE_HQL_ID);

			filterField.put("settime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}

    public void delectByItemId(String itemId) {
        // TODO Auto-generated method stub
        final String hql = "delete from PowerUserInfo p where p.cid.itemId = ?";

        this.doExecuteHql(hql,
                new Object[] { itemId });
    }

    public void delectByUsercode(String usercode) {
        // TODO Auto-generated method stub
        final String hql = "delete from PowerUserInfo p where p.cid.usercode = ?";

        this.doExecuteHql(hql,
                new Object[] { usercode });
        
    }


}
