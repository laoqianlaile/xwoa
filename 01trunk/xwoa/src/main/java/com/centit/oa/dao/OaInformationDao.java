package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaInformation;

public class OaInformationDao extends BaseDaoImpl<OaInformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaInformationDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("infoType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("majorDegree" , CodeBook.LIKE_HQL_ID);

			filterField.put("releaseDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("validDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("publicKey" , CodeBook.LIKE_HQL_ID);

			filterField.put("docNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("author" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadFile" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadFileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("referenceLinks" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodyContent" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("searchword" , " instr((title||publicKey),?)>0 ");
			
			filterField.put("NP_imagePath" , "  imagePath is not null  ");
			
			filterField.put("NForbiddenState", "(state is null or state = ?)");
			filterField.put("begcreatertime" , "creatertime >= to_date(?,'yyyy-MM-dd')");
            filterField.put("endcreatertime" , "creatertime <= to_date(?,'yyyy-MM-dd')");
            
            filterField.put("begReleaseDate", "releaseDate >= to_date(?,'yyyy-MM-dd')");
            filterField.put("endReleaseDate", "releaseDate <= to_date(?,'yyyy-MM-dd')");
            
            //filterField.put(CodeBook.ORDER_BY_HQL_ID, "releaseDate desc");
            
            filterField.put("currentdatetime" , "creatertime<  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            
            filterField.put("NP_bettentime", " to_date(to_char(releaseDate,'yyyy-MM-dd'),'yyyy-MM-dd')<=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and ( to_date(to_char(validDate,'yyyy-MM-dd'),'yyyy-MM-dd')>=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') or validDate is null )");
            //增加置顶排序
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " (case when istop = 'T' and infoType = 'bulletin' then  11 when istop = 'T' and infoType != 'bulletin' then 12  else  99  end),releaseDate desc");
		}
		return filterField;
	} 
	 public void autoInvalidated(){
	     doExecuteHql("update OaInformation t  set t.state='0' where t.state ='1' and t.validDate<=sysdate ");
	 }
}
