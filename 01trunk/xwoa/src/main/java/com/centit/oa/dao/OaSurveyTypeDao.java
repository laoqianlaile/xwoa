package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaSurveyType;

public class OaSurveyTypeDao extends BaseDaoImpl<OaSurveyType>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaSurveyTypeDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("reType" , CodeBook.LIKE_HQL_ID);

			filterField.put("comName" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" ,CodeBook.EQUAL_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);
	        filterField.put("beginCreatertime", "creatertime >= to_date(?,'yyyy-mm-dd')");
            filterField.put("endCreatertime", "creatertime <= to_date(?,'yyyy-mm-dd')+1");

		}
		return filterField;
	} 
	  /**
     * 生成调查类型编码
     * 
     * @return String
     */
    public String getNextNo() {
        return String.valueOf("DCLX" + this.getNextKeyBySequence("S_OASURVEYTYPE",12));

    }
}
