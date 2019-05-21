package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.po.OaOnlineItems;

public class OaOnlineItemsDao extends BaseDaoImpl<OaOnlineItems>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaOnlineItemsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("itemid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
    /**
     * 调查选项信息
     */
    @Override
    public void saveObject(OaOnlineItems o) {
        if (!StringUtils.hasText(o.getItemid())) {
            o.setItemid("TMXS"
                    + super.getNextKeyBySequence("S_OA_ONLINE_ITEMS_NO", 12));
        }
        super.saveObject(o);
    }
}
