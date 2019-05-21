package com.centit.oa.dao;

import java.util.ArrayList;
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
import com.centit.sys.util.DateUtil;

public class VOptBaseListDao extends BaseDaoImpl<VOptBaseList>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOptBaseListDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("itemType" , CodeBook.EQUAL_HQL_ID);
			filterField.put("itemTypeli" , " itemType like ? ");
			
			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);
			filterField.put("bizstate" , CodeBook.EQUAL_HQL_ID);
			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);
			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);
			filterField.put("begincreateDate", " createdate>= to_date(?, 'yyyy-mm-dd') ");
	        filterField.put("endcreateDate", " createdate< to_date(?, 'yyyy-mm-dd')+1 ");
	        
	        filterField.put("NP_bizstate", " bizstate !='F' ");//默认查看在办已办件
	        filterField.put("djId",CodeBook.LIKE_HQL_ID);
	        filterField.put("djIdli", " dj_Id like ? ");
	        
	        filterField.put("djIdno", " dj_Id !=? ");
	        
	        filterField.put("djIdnoin", "  dj_Id not in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid=? ) ");
	        //查看在办办件
	        filterField.put("NP_bizstatein", " bizstate in ('T','W') ");
	        //当前办理人员
	        filterField.put("bizusernames", CodeBook.LIKE_HQL_ID);
	        
	        filterField.put("currentdatetime", " createdate < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
		
	        filterField.put(CodeBook.ORDER_BY_HQL_ID, " createdate desc ");
		}
		return filterField;
	}

    @SuppressWarnings("unchecked")
    public List<VOptBaseList> listOptBaseInfo(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        String shql = "from v_opt_base_list where 1=1 ";
        if("W".equals(filterMap.get("bizstate"))){
            filterMap.remove("bizstate");
            filterMap.put("NP_bizstatein", true);
        }
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by createdate desc";//排序
        
        if ("true".equals(filterMap.get("queryUnderUnit"))) {
            if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
              String  unitcode=(String)filterMap.get("unitcode");
              queryUnderUnit = "  ( unitcode=" +HQLUtils.buildHqlStringForSQL(unitcode)+" or unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
                      + HQLUtils.buildHqlStringForSQL(unitcode) + " )) ";//下级部门
              queryUsercode="and creater in ( select usercode from F_Userunit  where unitcode in(select unitcode from f_unitinfo where  " +queryUnderUnit+
                    " ))";//用户编码
            }
        }
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by  createdate desc")){
            hql.setHql(hql.getHql().replace("order by  createdate desc", ""));
          
        }
        
        String hql1 = "select distinct dj_id,'1' as usercode ,creater,nodename, optid,flowinstid,flowcode, transaffairno," +
        		"transaffairname,transaffairquerykey,bizstate, biztype,orgcode,orgname,headunitcode, headusercode," +
        		"powerid, powername, solvestatus,solvetime,solvenote,createdate,sendarchiveno,acceptarchiveno,apply_item_type,itemType,bizusernames,bizusercodes " + hql.getHql() + queryUsercode+orderBy;
       
       
        String hql2 = "select count(distinct dj_id )  " + hql.getHql() + queryUsercode+orderBy;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VOptBaseList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, VOptBaseList.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    
    }

    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
            String currDjId) {
        String shql = "from v_opt_base_list where 1=1 ";
        if("W".equals(filterMap.get("bizstate"))){
            filterMap.remove("bizstate");
            filterMap.put("NP_bizstatein", true);
        }
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by createdate desc";//排序
        
        if ("true".equals(filterMap.get("queryUnderUnit"))) {
            if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
              String  unitcode=(String)filterMap.get("unitcode");
              queryUnderUnit = "  ( unitcode=" +HQLUtils.buildHqlStringForSQL(unitcode)+" or unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
                      + HQLUtils.buildHqlStringForSQL(unitcode) + " )) ";//下级部门
              queryUsercode="and creater in ( select usercode from F_Userunit  where unitcode in(select unitcode from f_unitinfo where  " +queryUnderUnit+
                    " ))";//用户编码
            }
        }
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by  createdate desc")){
            hql.setHql(hql.getHql().replace("order by  createdate desc", ""));
          
        }
        
        String hql1 = "select distinct dj_id,'1' as usercode ,creater,nodename, optid,flowinstid,flowcode, transaffairno," +
                "transaffairname,transaffairquerykey,bizstate, biztype,orgcode,orgname,headunitcode, headusercode," +
                "powerid, powername, solvestatus,solvetime,solvenote,createdate,sendarchiveno,acceptarchiveno,apply_item_type,itemType,bizusernames,bizusercodes " + hql.getHql() + queryUsercode+orderBy;
       
        
      //添加上一项 和下一项
        StringBuffer sql = new StringBuffer("with temp as(");
        sql.append(hql1)
           .append(")")
           .append("select p.no prevno,n.no nextno")
           .append(" from (select rownum rn,temp.* from temp) c")
           .append(" left join (select rownum rn,temp.dj_id as no from temp) p on p.rn = c.rn-1")
           .append(" left join (select rownum rn,temp.dj_id as no from temp) n on n.rn = c.rn+1")
           .append(" where c.dj_id="+HQLUtils.buildHqlStringForSQL(currDjId))
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
    @SuppressWarnings("unchecked")
    public List<VOptBaseList> listOptBaseInfoLeader(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String shql = "select dj_id,nodename,transaffairname,creater,itemType,createdate,bizstate,flowCode,unitname from v_opt_base_list_fgbm  where 1=1 ";

       
        StringBuffer sb=new StringBuffer();
        if(StringUtils.isNotBlank((String)filterMap.get("begincreateDate"))){
            sb.append(" and createdate>= to_date('"+filterMap.get("begincreateDate")+"', 'yyyy-mm-dd')");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("endcreateDate"))){
            sb.append(" and createdate< to_date('"+filterMap.get("endcreateDate")+"', 'yyyy-mm-dd')+1 ");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("itemType"))){
            sb.append(" and itemType="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("itemType")));
        }
        if(StringUtils.isNotBlank((String)filterMap.get("bizstate"))){
            if("C".equals((String)filterMap.get("bizstate"))){
            sb.append(" and bizstate="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("bizstate")));
            }else{
            sb.append(" and (bizstate='W' or bizstate='T')");  
            }
        }
        
        if(StringUtils.isNotBlank((String)filterMap.get("transaffairname"))){
            sb.append(" and transaffairname like '%"+((String)filterMap.get("transaffairname")).trim()+"%' ");
        }
        
        if(StringUtils.isNotBlank((String)filterMap.get("nodename"))){
            sb.append(" and nodename like '%"+((String)filterMap.get("nodename")).trim()+"%' ");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("unitname"))){
            sb.append(" and unitname like '%"+((String)filterMap.get("unitname")).trim()+"%' ");
        }
        sb.append(" and bizstate !='F' ");
        
        String queryUnderUnit = " and leadercode=" +HQLUtils.buildHqlStringForSQL((String)filterMap.get("leadercode")) +
            		" order by createdate desc ";
            
        String sq = shql + sb.toString()+queryUnderUnit ;
        
        
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sq);
        List<VOptBaseList> optBaseLists=new ArrayList<VOptBaseList>();
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                VOptBaseList bean=new VOptBaseList();
                bean.setDjId(o[0]!=null?o[0].toString():"");
                bean.setNodename(o[1]!=null?o[1].toString():"");
                bean.setTransaffairname(o[2]!=null?o[2].toString():"");
                bean.setCreater(o[3]!=null?o[3].toString():"");
                bean.setItemType(o[4]!=null?o[4].toString():"");
                bean.setCreatedate(o[5]!=null?DateUtil.dateStr2DateTime(o[5].toString()):null);
                bean.setBizstate(o[6]!=null?o[6].toString():"");
                bean.setFlowCode(o[7]!=null?o[7].toString():"");
                bean.setUnitname(o[8]!=null?o[8].toString():"");
                optBaseLists.add(bean);
            }
        }
        
        return optBaseLists;
    } 
    
    @SuppressWarnings("unchecked")
    public List<VOptBaseList> listNotVOptBaseList(String djId,String itemtype) {
        // TODO Auto-generated method stub
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  v_Opt_Base_List v  where 1=1 and itemtype="+HQLUtils.buildHqlStringForSQL(itemtype)+"and v.dj_id!="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.dj_id  in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid="+HQLUtils.buildHqlStringForSQL(djId)+")");
        return (List<VOptBaseList>)this.findObjectsBySql(sql.toString(), null, VOptBaseList.class);
    }
    @SuppressWarnings("unchecked")
    public List<VOptBaseList> listVOptBaseList(String djId,String itemtype,String usercode) {
        // TODO Auto-generated method stub
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  v_Opt_Base_List v  where 1=1 and itemtype="+HQLUtils.buildHqlStringForSQL(itemtype)+"and v.dj_id!="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.dj_id not in (select o.end_djid from OA_BIZ_BIND_INFO o where  o.start_djid="+HQLUtils.buildHqlStringForSQL(djId)+")");
        return (List<VOptBaseList>) this.findObjectsBySql(sql.toString(), null, VOptBaseList.class);
    } 
    /**
     * 
     * 办件合法性验证
     * @param djId
     * @param usercode
     * @return
     * retrun true 办件合法
     */
    @SuppressWarnings("unchecked")
    public boolean listVOptBaseListVail(String djId,String usercode) {
        boolean b=false;
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  v_bizpoweroption_user  v  where 1=1 and v.dj_id="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.usercode="+HQLUtils.buildHqlStringForSQL(usercode));
        List<VOptBaseList> l=(List<VOptBaseList>)this.findObjectsBySql(sql.toString(), null, VOptBaseList.class);
        if(l!=null&&l.size()>0){
            b=true;
        }
        return b;
    } 
    @SuppressWarnings("unchecked")
    public VOptBaseList getObjectByDjid(String djId){
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  v_Opt_Base_List v  where 1=1 and  v.dj_id="+HQLUtils.buildHqlStringForSQL(djId));
        return (VOptBaseList) this.findObjectsBySql(sql.toString(), null, VOptBaseList.class).get(0);
    }
   
    /**
     *  根据djid usercode 唯一获得业务信息
     * 是否需要办件查看权限验证
     * @param djId
     * @param usercode
     * @return
     * retrun true 需要验证
     */
    @SuppressWarnings("unchecked")
    public boolean isVailViewPower(String djId,String usercode) {
        boolean b=true;
        StringBuffer sql=new StringBuffer();
        sql.append("select v.* from  v_Opt_Base_List  v  where 1=1 and v.dj_id="+HQLUtils.buildHqlStringForSQL(djId)+" and  v.usercode="+HQLUtils.buildHqlStringForSQL(usercode));
        List<VOptBaseList> l=(List<VOptBaseList>)this.findObjectsBySql(sql.toString(), null, VOptBaseList.class);
        if(l!=null&&l.size()>0){
            b=false;
        }
        return b;
    } 
}
