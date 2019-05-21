package com.centit.oa.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.centit.oa.po.OaHelpinfo;
import com.centit.support.utils.DatetimeOpt;


public class OaHelpinfoDao extends BaseDaoImpl<OaHelpinfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaHelpinfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
		
			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);
	
			filterField.put("columnType" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("infoName" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("remark" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("fileDoc" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("fileDocname" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("state" , CodeBook.LIKE_HQL_ID);
	
			filterField.put("begCreatertime" ,"creatertime >= to_date(?,'yyyy-MM-dd')");
	
			filterField.put("endCreatertime" , "creatertime <= to_date(?,'yyyy-MM-dd')");
	
			filterField.put("isallowcomment" , CodeBook.EQUAL_HQL_ID);
		}
		return filterField;
	} 
    public List<OaHelpinfo> search(String search,PageDesc pageDesc){
	     List<OaHelpinfo> objects = null;
	     if(StringUtils.isNotBlank(search)){
	        String shql="from OaHelpinfo where (infoName like '"+HQLUtils.getMatchString(search)+"' " +
	        		"or remark like '"+HQLUtils.getMatchString(search)+"' ) and state != 'F'";
	        objects=this.listObjects(shql, pageDesc);
	     }
	     return objects;
	 }
    public  Query getQuery( Query query,Map<String,Object> filterMap){
        if(StringUtils.isNotBlank((String) filterMap.get("isgood"))){
            query.setString("isgood", (String) filterMap.get("isgood"));
        }
        if(StringUtils.isNotBlank((String) filterMap.get("infoName"))){
            query.setString("infoName", "%"+(String) filterMap.get("infoName")+"%");
        }
        if(StringUtils.isNotBlank((String) filterMap.get("djid"))){
            query.setString("djid", (String) filterMap.get("djid"));
        }
        if(StringUtils.isNotBlank((String) filterMap.get("state"))){
            query.setString("state", (String) filterMap.get("state"));
        }
        if(StringUtils.isNotBlank((String) filterMap.get("columnType"))){
            query.setString("columnType", (String) filterMap.get("columnType"));
        }
        if(StringUtils.isNotBlank((String) filterMap.get("remark"))){
            query.setString("remark", "%"+(String) filterMap.get("remark")+"%");
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("fileDocname"))){
            query.setString("fileDocname", (String) filterMap.get("fileDocname"));
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("isallowcomment"))){
            query.setString("isallowcomment", (String) filterMap.get("isallowcomment"));
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("begCreatertime"))&&StringUtils.isNotBlank((String) filterMap.get("begCreatertime"))){
          query.setDate("begCreatertime", DatetimeOpt.convertStringToDate((String) filterMap.get("begCreatertime"), "yyyy-MM-dd"));
          query.setDate("endCreatertime", DatetimeOpt.convertStringToDate((String) filterMap.get("endCreatertime"), "yyyy-MM-dd"));
      }  
        return query;
    }
    public String getHQL(String shql,Map<String,Object> filterMap){
        String hql=shql;
        if(StringUtils.isNotBlank((String) filterMap.get("isgood"))){
            hql+=" and isgood like :isgood ";
        }
        if(StringUtils.isNotBlank((String) filterMap.get("infoName"))){
            hql+=" and infoName like :infoName ";
        }
        if(StringUtils.isNotBlank((String) filterMap.get("djid"))){
            hql+=" and djid=:djid ";
        }
        if(StringUtils.isNotBlank((String) filterMap.get("state"))){
            hql+=" and state=:state ";
        }
        if(StringUtils.isNotBlank((String) filterMap.get("columnType"))){
            hql+=" and columnType=:columnType ";
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("remark"))){
            hql+=" and remark like :remark ";
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("begCreatertime"))&&StringUtils.isNotBlank((String) filterMap.get("endCreatertime"))){
            hql+=" and creatertime > :begCreatertime and creatertime < :endCreatertime";
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("fileDocname"))){
            hql+=" and fileDocname = :fileDocname ";
        }  
        if(StringUtils.isNotBlank((String) filterMap.get("isallowcomment"))){
            hql+=" and isallowcomment = :isallowcomment ";
        }  
        hql+="order by isgood desc ";
        return hql;
    }
    @SuppressWarnings("unchecked")
    public List<OaHelpinfo> listOahelpinfo(final Map<String,Object> filterMap,final PageDesc pageDesc){
                String shql1="select new OaHelpinfo(djid,columnType,infoName,remark"
                  +",creater,creatertime,fileDocname,state"
                  +",isallowcomment,isgood,replynum,viewnum) from OaHelpinfo where 1=1 ";
                //根据filterMap拼接hql语句
                shql1=this.getHQL(shql1, filterMap);
                final String shql=shql1;
        return getHibernateTemplate().executeFind(new HibernateCallback<List<OaHelpinfo>>() {

            @Override
            public List<OaHelpinfo> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                            Query query = session.createQuery(shql);
                          //根据filterMap拼接填充参数
                            query=getQuery(query, filterMap);
                            
                            final int FirstResult=pageDesc.getRowStart();
                            final int MaxResult=pageDesc.getPageSize();
                          
                            List<OaHelpinfo> objects= query.setFirstResult(FirstResult).setMaxResults(MaxResult).list();
                           
                            String shql2="select count(*) from "+shql.substring(shql.indexOf("OaHelpinfo where "));
                            Query query1 = session.createQuery(shql2);
                            query1=getQuery(query1, filterMap);
                            Long totalRows=(Long) query1.uniqueResult();
                            pageDesc.setTotalRows(totalRows.intValue());                
                            return objects;
            }
        });
    }
    @SuppressWarnings("unchecked")
    public List<String> getDjids() {
        List<String> djids=new ArrayList<String>();
        DetachedCriteria criteria =DetachedCriteria.forClass(OaHelpinfo.class);
        criteria.add(Restrictions.eq("state", "T"));
        List<OaHelpinfo> objects=this.getHibernateTemplate().findByCriteria(criteria);
        for (OaHelpinfo oaHelpinfo : objects) {
            djids.add(oaHelpinfo.getDjid());
        }
        return djids;
    }
}
