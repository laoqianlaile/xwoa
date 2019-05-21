package com.centit.oa.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.oa.po.VOaInformation;
import com.centit.sys.util.DateUtil;

public class VOaInformationDao extends BaseDaoImpl<VOaInformation>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOaInformationDao.class);
		
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

			//filterField.put("uploadFile" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadFileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("referenceLinks" , CodeBook.LIKE_HQL_ID);

			//filterField.put("bodyContent" , CodeBook.LIKE_HQL_ID);

			//filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("readstate" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("searchword" , " instr((title||publicKey),?)>0 ");
			
			filterField.put("NP_imagePath" , "  imagePath is not null  ");
			
			filterField.put("NForbiddenState", "(state is null or state = ?)");
			filterField.put("begcreatertime" , "creatertime >= to_date(?,'yyyy-MM-dd')");
            filterField.put("endcreatertime" , "creatertime <= to_date(?,'yyyy-MM-dd')");
            
            filterField.put("begReleaseDate", "releaseDate >= to_date(?,'yyyy-MM-dd')");
            filterField.put("endReleaseDate", "releaseDate <= to_date(?,'yyyy-MM-dd')");
            
            filterField.put("currentdatetime" , "creatertime<=  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            
            filterField.put("info_Type" , " info_Type = ? ");
            
            filterField.put("NP_bettentime", " releaseDate<=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and ( validDate>=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') or validDate is null )");
            
          //增加置顶排序
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " (case when istop = 'T' and infoType = 'bulletin' then  11 when istop = 'T' and infoType != 'bulletin' then 12  else  99  end),creatertime desc");
		}
		return filterField;
	} 
	@SuppressWarnings("unchecked")
	public List<VOaInformation> listVoainformation(Map<String, Object> filterMap){
	    String shql = " from v_oa_information where 1=1 ";
	    String orderBy=" order by RELEASE_DATE desc";//排序
	    String state="";
	    String infoType="";
	    String NForbiddenState="";
        if(StringUtils.isNotBlank((String)filterMap.get("state"))){
            state=" and state="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("state"));
        }
        if(StringUtils.isNotBlank((String)filterMap.get("infoType"))){
            infoType=" and info_Type="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("infoType"));
        }
        if(StringUtils.isNotBlank((String)filterMap.get("NForbiddenState"))){
            NForbiddenState=" and state is null or state ="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("NForbiddenState"));
        }
        String NP_bettentime=" and release_Date<=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and ( valid_Date>=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') or valid_Date is null )";
	    String sql = "select distinct NO,TITLE,RELEASE_DATE ,  " +
                "STATE ,  NEWMSG,MAJOR_DEGREE,PUBLIC_KEY,DOC_NO,creater " +
                shql+infoType+NForbiddenState+state+NP_bettentime+orderBy ;
	    List<Object[]> res = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql));
	    List<VOaInformation> voaInfoList = new ArrayList<VOaInformation>();
	    if(res!=null&&res.size()>0){
	        for(Object[] oArr: res){
	            VOaInformation voaInfo = new VOaInformation();
	            voaInfo.setNo(oArr[0]!=null?oArr[0].toString():"");
	            voaInfo.setTitle(oArr[1]!=null?oArr[1].toString():"");
                voaInfo.setCreatertime(oArr[2]!=null?DateUtil.dateStr2DateTime(oArr[2].toString()):null);
	            voaInfo.setState(oArr[3]!=null?oArr[3].toString():"");
	            voaInfo.setNewmsg(oArr[4]!=null?oArr[4].toString():"");
	            voaInfo.setMajorDegree(oArr[5]!=null?oArr[5].toString():"");
	            voaInfo.setPublicKey(oArr[6]!=null?oArr[6].toString():"");
	            voaInfo.setDocNo(oArr[7]!=null?oArr[7].toString():"");
	            voaInfo.setCreater(oArr[8]!=null?oArr[8].toString():"");
	            voaInfoList.add(voaInfo);
	        }
	    }
	    return voaInfoList;
	    
	}
}
