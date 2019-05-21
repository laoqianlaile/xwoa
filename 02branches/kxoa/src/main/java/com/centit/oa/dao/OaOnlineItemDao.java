package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.po.OaOnlineItem;

public class OaOnlineItemDao extends BaseDaoImpl<OaOnlineItem>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaOnlineItemDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("chosetype" , CodeBook.EQUAL_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("itemnames" , CodeBook.LIKE_HQL_ID);

			filterField.put("chosenum" , CodeBook.LIKE_HQL_ID);

			filterField.put("limitnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("thesign" , CodeBook.EQUAL_HQL_ID);
			
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "no asc");
			

		}
		return filterField;
	} 
	
    /**
     * 题目信息
     */
    @Override
    public void saveObject(OaOnlineItem o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("TMXX"
                    + super.getNextKeyBySequence("S_OA_ONLINE_ITEM_NO", 12));
        }
        super.saveObject(o);
    }
    
    /**
     * 删除题目选项
     * @param o
     */
    public void deleteitemsByNo(OaOnlineItem o) {
        final String hql = "delete from OaOnlineItems o where o.no = ? ";
        this.doExecuteHql(hql,o.getNo());
    }
    
}
