package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.po.VoaBizBindInfo;

public class VoaBizBindInfoDao extends BaseDaoImpl<VoaBizBindInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VoaBizBindInfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.LIKE_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("orgcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("powerid" , CodeBook.LIKE_HQL_ID);

			filterField.put("powername" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("itemtype" , CodeBook.LIKE_HQL_ID);
			//根据登记时间查询
			filterField.put("createStartDate", " createdate >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			filterField.put("createEndDate", " createdate <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			 filterField.put("djIdno", " dj_Id !=? ");
	          filterField.put("djIdli", " dj_Id like ?");  
	        filterField.put("djIdnoin", "  dj_Id not in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid=? ) ");
	        
	        //根据发文时间查询
	        filterField.put("dispatchStartDate", " dispatchDate >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            filterField.put("dispatchEndDate", " dispatchDate <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
	         filterField.put(CodeBook.ORDER_BY_HQL_ID, "  createdate desc ");  
	         //发文号
	         filterField.put("dispatchDocNoli", " dispatch_doc_no like ?  ");
	         
	         filterField.put("usercode",  CodeBook.LIKE_HQL_ID);
		}
		return filterField;
	}

    @SuppressWarnings("unchecked")
    public List<VoaBizBindInfo> listVoaBizBindInfo(String djId,String itemtype) {
        // TODO Auto-generated method stub
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  V_OA_BIZ_BIND_INFO v  where 1=1 and itemtype="+HQLUtils.buildHqlStringForSQL(itemtype)+"and v.dj_id!="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.dj_id not in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid="+HQLUtils.buildHqlStringForSQL(djId)+")");
        return (List<VoaBizBindInfo>) this.findObjectsBySql(sql.toString(), null, VoaBizBindInfo.class);
    } 
    @SuppressWarnings("unchecked")
    public List<VoaBizBindInfo> listNotVoaBizBindInfo(String djId,String itemtype,String usercode) {
        // TODO Auto-generated method stub
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  V_OA_BIZ_BIND_INFO v  where 1=1 and  usercode="+HQLUtils.buildHqlStringForSQL(usercode)+" and itemtype="+HQLUtils.buildHqlStringForSQL(itemtype)+"and v.dj_id!="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.dj_id  in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid="+HQLUtils.buildHqlStringForSQL(djId)+")");
        return (List<VoaBizBindInfo>) this.findObjectsBySql(sql.toString(), null, VoaBizBindInfo.class);
    }
    
    //分页查询 dk 2015-12-28
    @SuppressWarnings("unchecked")
    public List<VoaBizBindInfo> listVoaBizBindInfo(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        String shql = " from V_OA_BIZ_BIND_INFO where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        //String orderBy="order by createdate desc";//排序
        
        
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), " order by  createdate desc")){
            hql.setHql(hql.getHql().replace(" order by  createdate desc", ""));
          
        }
        
        String hql1 = "select distinct dj_id,transaffairname,orgcode, orgname,powerid,powername, biztype," +
                "bizstate,itemtype,createdate, dispatch_doc_no,dispatch_user,dispatch_date" +
                  hql.getHql() ;//+orderBy;
       
       
        String hql2 = "select count(distinct dj_id )  " + hql.getHql();// +orderBy;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VoaBizBindInfo>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, VoaBizBindInfo.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    
    }
}
