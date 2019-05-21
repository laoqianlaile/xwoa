package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.bbs.po.OaBbsTheme;

public class OaBbsThemeDao extends BaseDaoImpl<OaBbsTheme>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaBbsThemeDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("themeno" , CodeBook.EQUAL_HQL_ID);


			filterField.put("layoutno" , CodeBook.EQUAL_HQL_ID);

			filterField.put("sublayouttitle" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodycontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("usercode" , " creater=? ");

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);
            
			filterField.put("excludeStates" , "state not in (?)");
			
			filterField.put("approval" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvalresults" , CodeBook.EQUAL_HQL_ID);

			filterField.put("approvaltime" , CodeBook.LIKE_HQL_ID);

			filterField.put("approvalremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("postsnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("postsviewnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("themeset" , CodeBook.LIKE_HQL_ID);

			filterField.put("attentionum" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("createtimeEq" , "  to_char(createtime,'yyyy-mm-dd')=? ");
			
			filterField.put("begTime", " createtime >=to_date(?,'yyyy-mm-dd')");
			filterField.put("endTime", " createtime <=to_date(?,'yyyy-mm-dd')");
			
			//字段没加索引时instr效率高于like
			filterField.put("search"," (instr((sublayouttitle || bodycontent),?)>0) ");
			
			filterField.put("notDeleted", " state <> ? ");
			//decode（columnname，值1,翻译值1,值2,翻译值2,...值n,翻译值n,缺省值）
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " DECODE(themeset,'G','1','T','2','J','3','Y','4',themeset) ,createtime desc");

			
			filterField.put("NP_isopen","layoutno in ( "+getOpenSql() +")");
		}
		return filterField;
	} 
	
	private String  getOpenSql(){ 
	
	 
	    
	    String sqlDas="select o.layoutcode from OaBbsDashboard o where 1=1 and" +
                    " (" +
                    "o.openType='T' " +
                    //是否设置开发时间+判断上午下午 
                    "and (o.isdocontral ='F' or o.isdocontral='T'and (" +
                   
                    " (case when o.starttime is not null then  to_char(o.starttime, 'HH24:MI:SS') else '00:00:00' end )<=to_char(sysdate, 'HH24:MI:SS')" +
                    " and (case when o.endtime is not null then to_char(o.endtime, 'HH24:MI:SS') else '12:59:00' end) >= to_char(sysdate, 'HH24:MI:SS') " +
                    " or (case when o.starttimepm is not null then to_char(o.starttimepm, 'HH24:MI:SS') else '13:00:00' end) <=to_char(sysdate, 'HH24:MI:SS') " +
                    "and (case when o.endtimepm is not null then  to_char(o.endtimepm, 'HH24:MI:SS') else '23:59:59' end) >=to_char(sysdate, 'HH24:MI:SS')" +
                                                                     ")" +
                          ")" +
                    ")" ;
	    String sqlDis="select d.layoutno from OaBbsDiscussion d where 1=1 and" +
                    " (" +
                    "d.openType='T' " +
                    //是否设置开发时间+判断上午下午 
                    "and (d.isdocontral ='F' or d.isdocontral='T'and (" +
                    " (case when d.starttime is not null then  to_char(d.starttime, 'HH24:MI:SS') else '00:00:00' end )<=to_char(sysdate, 'HH24:MI:SS')" +
                    " and (case when d.endtime is not null then to_char(d.endtime, 'HH24:MI:SS') else '12:59:00' end) >= to_char(sysdate, 'HH24:MI:SS') " +
                    " or (case when d.starttimepm is not null then to_char(d.starttimepm, 'HH24:MI:SS') else '13:00:00' end) <=to_char(sysdate, 'HH24:MI:SS') " +
                    "and (case when d.endtimepm is not null then  to_char(d.endtimepm, 'HH24:MI:SS') else '23:59:59' end) >=to_char(sysdate, 'HH24:MI:SS')" +
                                                                     ")" +
                        ")" +
                     ")" +   
                    "and  d.layoutcode in ("+sqlDas+")";
	   
//	    String hsql="  select t.layoutno from OaBbsTheme t " +
//                "left join OaBbsDashboard  m on m.layoutno=t.layoutno  and m.openType='T' " +
//                "left join OaBbsDiscussion n on n.layoutcode=m.layoutcode n.openType='T' "+
//                " where 1=1 and layoutno in ( "+sqlDis +")";
	   
//	    String hsql="  select t.layoutno from oa_bbs_theme t left join oa_bbs_discussion  m on m.layoutno=t.layoutno and m.open_type='T' left join oa_bbs_dashboard n on n.layoutcode=m.layoutcode and n.open_type='T'" +
//	    		"where 1=1    and t.layoutno in ( "+sqlDis +")";
	    return sqlDis;
	}
	
	
}
