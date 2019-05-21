package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.VPowerUserInfo;

public class VPowerUserInfoDao extends BaseDaoImpl<VPowerUserInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VPowerUserInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("orgcode", " org_id = ? ");
			
			filterField.put("orgId", " org_id like ? ");
			
			filterField.put("cid.itemId" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("itemName",CodeBook.LIKE_HQL_ID);


			filterField.put("itemType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("settime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("wfcode", CodeBook.LIKE_HQL_ID);
			
			filterField.put("optid", CodeBook.LIKE_HQL_ID);
	            
	        filterField.put("itemType", CodeBook.LIKE_HQL_ID);
	        
	        filterField.put("isapplyuser", CodeBook.LIKE_HQL_ID);
	        
	        filterField.put("isNetwork" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	} 
	
	    public List<VPowerUserInfo> getVList(Map<String, Object> filterMap,
	            PageDesc pageDesc) {
	        return listObjects( filterMap,  pageDesc);
	    }

        @SuppressWarnings("unchecked")
        public VPowerUserInfo getPowerInfo(String item_id) {
            List<VPowerUserInfo> po = (List<VPowerUserInfo>) getHibernateTemplate().find("From VPowerUserInfo where cid.itemId=?  ", new String[]{item_id});
            if (po != null && po.size() > 0) {
                return po.get(0);
            }
            return new VPowerUserInfo();
        }
}
