package com.centit.app.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.centit.app.po.Thread;
import com.centit.app.po.VOaScheduleRemind;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;

public class VOaScheduleRemindDao extends BaseDaoImpl<VOaScheduleRemind>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOaScheduleRemindDao.class);
		
	public Map<String, String> getFilterField() {
//		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("itemType" , CodeBook.LIKE_HQL_ID);

			filterField.put("cBegin" , "begTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			
            filterField.put("cEnd" , "begTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");

			filterField.put("reType" , CodeBook.LIKE_HQL_ID);

			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

			filterField.put(CodeBook.ORDER_BY_HQL_ID, " begTime asc");
			
			filterField.put("inschno", " s.schno in (select r.addrbookid"+
                            " from OA_ADDRESS_BOOK_RELECTION r"+
                           " where r.shareman = ?) or creater=? ");
//		}
		return filterField;
	}

	public List<VOaScheduleRemind> listObjects(Map<String, Object> filterMap,String usercode) {
	    StringBuffer sql=new StringBuffer("select * from (select s.schno as no,s.title as title,to_char(s.plan_beg_time,'yyyy-mm-dd hh24:mi:ss') as begtime,s.creater as usercode,s.itemtype as itemtype,'RCAP' as re_type from oa_schedule s where "+
	            "s.schno in (select distinct a.schno from oa_schedule a where a.creater="+HQLUtils.buildHqlStringForSQL(usercode) +
	           " or a.schno in (select r.addrbookid from oa_address_book_relection r where  r.shareman="+HQLUtils.buildHqlStringForSQL(usercode)+")) "+
	            " and s.seh_type='1' "+
	            " union "+
	           "select v.addrbookid as no,o.title as title,to_char(o.begtime,'yyyy-mm-dd hh24:mi:ss') as begtime,v.shareman as usercode,o.re_type as itemtype,'TX' as re_type "+
	              " from oa_address_book_relection  v "+
	               "left join oa_remind_information o on o.no=v.addrbookid "+
	              " where  v.addrbookid like 'TX%' and v.shareman="+HQLUtils.buildHqlStringForSQL(usercode)+")");
	    if(StringUtils.isNotBlank((String)filterMap.get("cBegin"))&&StringUtils.isNotBlank((String)filterMap.get("cEnd"))){
	        sql.append("where  to_date(begtime,'yyyy-mm-dd hh24:mi:ss')>=to_date("+HQLUtils.buildHqlStringForSQL((String)filterMap.get("cBegin"))+",'yyyy-mm-dd hh24:mi:ss') and to_date(begtime,'yyyy-mm-dd hh24:mi:ss')<to_date("+HQLUtils.buildHqlStringForSQL((String)filterMap.get("cEnd"))+",'yyyy-mm-dd hh24:mi:ss')");
	    }
	  List<Object[]> l= (List<Object[]>) findObjectsBySql(sql.toString());
	  List<VOaScheduleRemind> volist = new ArrayList<VOaScheduleRemind>();
	  SimpleDateFormat fromat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  try {
        if(l!=null && l.size()>0){
              for(Object[] o:l){
                  VOaScheduleRemind vo=new VOaScheduleRemind();
                  vo.setNo(o[0].toString());
                  vo.setTitle((o[1]!=null&&!"".equals(o[1].toString()))?o[1].toString():o[2].toString().substring(0, 10));
                  vo.setBegTime(fromat.parse(o[2].toString()));
                  vo.setUsercode(o[3].toString());
                  vo.setItemType(o[4].toString());
                  vo.setReType(o[5].toString());
                  volist.add(vo);
              }
          }
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	  return volist;
	}  
        
    }




