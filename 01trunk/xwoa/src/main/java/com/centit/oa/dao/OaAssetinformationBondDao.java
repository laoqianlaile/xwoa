package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaAssetinformationBond;

public class OaAssetinformationBondDao extends BaseDaoImpl<OaAssetinformationBond>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaAssetinformationBondDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}

    public List<OaAssetinformationBond> listOaAssetinformation(String djid) {
        // TODO Auto-generated method stub
        String hql = "FROM OaAssetinformationBond where djid=?";
        Object[] objects = new Object[]{djid};
        List<OaAssetinformationBond> ls = this.listObjects(hql, objects);
        if (ls != null && ls.size() >= 1){
            return ls;
        }else{
            return null;
        }
    } 
}
