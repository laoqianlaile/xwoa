package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.oa.po.OaArchive;
import com.centit.support.utils.StringBaseOpt;
import com.centit.support.utils.StringRegularOpt;
import com.centit.sys.util.DateUtil;

public class OaArchiveDao extends BaseDaoImpl<OaArchive>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaArchiveDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsibledep" , CodeBook.LIKE_HQL_ID);

			filterField.put("titanic" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("bookdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("pages" , CodeBook.LIKE_HQL_ID);

			filterField.put("copies" , CodeBook.LIKE_HQL_ID);

			filterField.put("duration" , CodeBook.LIKE_HQL_ID);

			filterField.put("filingannual" ," filingannual=to_number(?)");

			filterField.put("filingdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("locationpath" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("doctype" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("updateuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("docno" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("allcaseno" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("parallelTitle" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("deputyTitle" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("classification" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("keywords" , "   (instr((title||parallelTitle||deputyTitle||remark||docno||keywords),?)>0) ");
			
			filterField.put("begincomeDate", " filingdate>= to_date(?, 'yyyy-mm-dd') ");

		    filterField.put("endincomeDate", " filingdate< to_date(?, 'yyyy-mm-dd')+1 ");
			
		    filterField.put("currentdatetime" , "createtime<  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
		    
		    filterField.put("gwNature" , CodeBook.EQUAL_HQL_ID);
		    
		    filterField.put("belongUnitcode" , CodeBook.EQUAL_HQL_ID);
		    
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"titanic asc");
		}
		return filterField;
	}
    
	
    
    @SuppressWarnings("unchecked")
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){
        String shql = "select * from OA_ARCHIVE where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        
//    //添加上一项 和下一项
        StringBuffer sql = new StringBuffer("with temp as(");
        sql.append(hql.getHql())
           .append(")")
           .append("select p.no prevno,n.no nextno")
           .append(" from (select rownum rn,temp.* from temp) c")
           .append(" left join (select rownum rn,temp.no from temp) p on p.rn = c.rn-1")
           .append(" left join (select rownum rn,temp.no from temp) n on n.rn = c.rn+1")
           .append(" where c.no="+HQLUtils.buildHqlStringForSQL(currDjId))
           .append(" order by c.rn");
        List<Object[]>  l = getHibernateTemplate().executeFind(
                new SQLQueryCallBack(sql.toString(),hql.getParams()));
        List<String> res = new ArrayList<String>();
        if(l!=null&&l.size()>0){
            res.add(l.get(0)[0]==null?null : l.get(0)[0].toString());
            res.add(l.get(0)[1]==null?null :l.get(0)[1].toString());
        }
       return res;
    }
	
    public void updateTatanic(String duration) {
        StringBuffer sql = new StringBuffer();
        sql.append("update oa_archive a set a.titanic = (select b.r from (select row_number() over(order by o.createtime desc) r,o.id from oa_archive o  where duration = '"+duration+"') b   where b.id = a.id) where a.duration = '"+duration+"'");
        this.doExecuteSql(sql.toString());
    }

    public Long getNextMaxKey() {
        // TODO Auto-generated method stub
        String keynum=this.getNextKeyMax("titanic", "OaArchive where filingannual="+HQLUtils.buildHqlStringForSQL(DateUtil.getCurrentDate().substring(0, 4)));
        if("0".equals(keynum)){
            return 1L;
        }
        return Long.parseLong(keynum);
    }

    public OaArchive getObjectByIds(Long filingannual, String titanic, String belongUnitcode) {
        String shql = " from OaArchive  where filingannual=? and titanic=?  and  belongUnitcode =? ";
        List<OaArchive> list = this.listObjects(shql, new Object[]{filingannual,titanic,belongUnitcode});
        return (list!=null&&list.size()>0)?list.get(0):null;
    }

    @SuppressWarnings("unchecked")
    public List<OaArchive> listNdList() {
        String shql = "select distinct filingannual,1 from Oa_Archive  where 1=1 ";

        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        List<OaArchive> optBaseLists=new ArrayList<OaArchive>();
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                OaArchive bean=new OaArchive();
                bean.setFilingannual(o[0]!=null?Long.parseLong(o[0].toString()):null);
                optBaseLists.add(bean);
            }
        }
        
        return optBaseLists;
    }



    public Long getNextTitanic(String filingannual, String belongUnitcode) {
     // TODO Auto-generated method stub
        String keynum=this.getNextKeyMax("titanic", "OaArchive where filingannual="+HQLUtils.buildHqlStringForSQL(filingannual)+" and belongUnitcode ="+HQLUtils.buildHqlStringForSQL(belongUnitcode));
        if("0".equals(keynum)){
            return 1L;
        }
        return Long.parseLong(keynum);
    } 
}
