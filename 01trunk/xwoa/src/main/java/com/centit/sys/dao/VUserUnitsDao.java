package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.sys.po.VUserUnits;

public class VUserUnitsDao extends BaseDaoImpl<VUserUnits>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VUserUnitsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("unitCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("unitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("userName" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitOrder" , CodeBook.LIKE_HQL_ID);

			filterField.put("userOrder" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
	 public List<VUserUnits> getUnitUsers(String unitcode) {
	        try {
	            String shql = "from VUserUnits where cid.unitCode = ?";
	            
	            List<VUserUnits> list = this.listObjects(shql, unitcode);
	            
	            if (list != null && list.size() > 0) {
	                return list;
	            } else {
	                return null;
	            }
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return null;
	        }

	    }
}
