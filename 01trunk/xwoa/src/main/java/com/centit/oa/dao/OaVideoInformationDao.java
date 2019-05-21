package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaVideoInformation;

public class OaVideoInformationDao extends BaseDaoImpl<OaVideoInformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaVideoInformationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);

		    filterField.put("videoType" , CodeBook.LIKE_HQL_ID);
			filterField.put("infoType" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("releaseDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("validDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("publicKey" , CodeBook.LIKE_HQL_ID);

			filterField.put("bookmark" , CodeBook.LIKE_HQL_ID);

			filterField.put("bigImage" , CodeBook.LIKE_HQL_ID);

			filterField.put("smallImage" , CodeBook.LIKE_HQL_ID);

			filterField.put("referenceLinks" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("clickNum" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("yearSearch"," to_char(releaseDate,'yyyy')=? ");
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"clicknum desc");
			
			filterField.put("search"," (instr((title || publicKey ||infoType),?)>0) ");
					
			  //时间查询条件
            filterField.put("begReleaseDate" , "releaseDate >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            filterField.put("endReleaseDate" , "releaseDate <= to_date(?,'yyyy-MM-dd hh24:mi:ss')+1");
            filterField.put("year" , " ? = to_char(releaseDate,'yyyy')  ");  
            filterField.put("begyear" , " ? > to_char(releaseDate,'yyyy')  ");  
            filterField.put("excludeStates", "state not in(?)");
		}
		return filterField;
	} 
    /**
     * 定时器使用，使得过了视频有效期的视频的状态变成不可观看
     */
	 public void autoLoseEfficacy() {
	     doExecuteHql("update OaVideoInformation t  set t.state='2' where t.state ='1' and t.validDate<=sysdate ");
	 }
}
