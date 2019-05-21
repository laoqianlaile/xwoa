package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.centit.core.dao.SQLQueryCallBack;

public class ZtreeDao extends HibernateDaoSupport{
    
   /**
    * 获取组织人员树，全部门的所有有效人员
    * @return
    */
   public List<Map<String,String>> findOrgUserTree(){
       StringBuffer sql = new StringBuffer();
       sql.append("select * from(")
          .append(" select unitcode as id,decode(parentunit,'0','BM',parentunit) as pid,unitname as name,unitorder as orderby,'unit' as choosetype,'O' as p from f_unitinfo where ISVALID='T'")
          .append("union all ")
          .append(" select t2.usercode as id,t1.unitcode as pid,t2.username as name,t2.userorder*100+t2.sortid as orderby,'user' as choosetype,'C' as p from f_userunit t1,f_userinfo t2 where t1.usercode = t2.usercode and t1.isprimary='T' and t2.isvalid='T'")
          .append(") t")
          .append(" start with pid='BM'")
          .append(" connect by pid = prior id")
          .append(" order siblings by orderby");
       
        @SuppressWarnings("unchecked")
        List<Object[]> rows = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString()));
        
       return buildMapList(rows);
   }
   
   /**
    * 根据组织编码获取当前部门信息以及其下属部门和部门下的人员
    * @param orgCode
    * @return
    */
   public List<Map<String,String>> findOrgUserTreeByOrgCode(String orgCode){
       StringBuffer sql = new StringBuffer();
       sql.append("select * from(")
          .append(" select  unitcode as id,decode(unitcode,?,'BM',parentunit) as pid,unitname as name,unitorder as orderby,'unit' as choosetype,'O' as p from f_unitinfo")
          .append(" start with unitcode=?")
          .append(" connect by parentunit = prior unitcode")
          .append(" union all")
          .append(" select t2.usercode as id,t1.unitcode as pid,t2.username as name, t2.userorder*100+t2.sortid  as orderby,'user' as choosetype,'C' as p from f_userunit t1,f_userinfo t2 where t1.usercode = t2.usercode and t1.isprimary='T' and t2.isvalid='T'")
          .append(") t")
          .append(" start with pid='BM'")
          .append(" connect by pid = prior id")
          .append(" order siblings by orderby");
       @SuppressWarnings("unchecked")
       List<Object[]> rows = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString(),new Object[] { orgCode,orgCode }));
       return buildMapList(rows);
   }
   
   private List<Map<String,String>> buildMapList(List<Object[]> rows){
       List<Map<String,String>> res = new ArrayList<Map<String,String>>();
       if(rows != null){
           for(Object[] arr:rows){
               Map<String,String> map = new HashMap<String,String>();
               map.put("id", objToString(arr[0]));
               map.put("pId", objToString(arr[1]));
               map.put("open", arr[1]==null?"false":String.valueOf(("BM".equals(arr[1]))));
               map.put("name", objToString(arr[2]));
               map.put("choosetype", objToString(arr[4]));
               map.put("p", objToString(arr[5]));
               if("unit".equals(arr[4])){
                   map.put("unitorder",objToString(arr[3]));
                   map.put("type", "false");// 部门不可被选中
                   map.put("icon", "../scripts/inputZtree/img/diy/unit.png");     
               }
               if("user".equals(arr[4])){
                   map.put("type", "true");
                   map.put("userorder",objToString(arr[3]));
                   map.put("icon", "../scripts/inputZtree/img/diy/person.png");
               }
               res.add(map);
           }
       }
       return res;
   }
   private String objToString(Object o){
       String res = "";
       if(o != null){
           res = o.toString();
       }
       return res;
   }

public List<Map<String, String>> findOrgDyUserTree() {
    StringBuffer sql = new StringBuffer();
    sql.append("select * from(")
       .append(" select unitcode as id,decode(parentunit,'0','BM',parentunit) as pid,unitname as name,unitorder as orderby,'unit' as choosetype,'O' as p from f_unitinfo where ISVALID='T'")
       .append("union all ")
       .append(" select t2.usercode as id,t1.unitcode as pid,t2.username as name,t2.userorder*100+t2.sortid as orderby,'user' as choosetype,'C' as p from f_userunit t1,f_userinfo t2 where t1.usercode = t2.usercode and t1.isprimary='T' and t2.isvalid='T'")
       .append(" and t2.usercode in (select oo.usercode from oa_userinfo oo where oo.party='T')) t")
       .append(" start with pid='BM'")
       .append(" connect by pid = prior id")
       .append(" order siblings by orderby");
    
     @SuppressWarnings("unchecked")
     List<Object[]> rows = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString()));
     
    return buildMapList(rows);
}
/**
 * 获取职务人员树，全部门的所有有效人员
 * @return
 */
public List<Map<String,String>> findCommonUserTree(String loginName){
    StringBuffer sql = new StringBuffer();
    sql.append("select * from(")
       .append(" select 'a' as id,'BM' as pid,'常用联系人' as name,1 as orderby,'unit' as choosetype,'O' as p from f_userinfo where loginname='"+loginName+"' ")
       .append("union all ")
       .append(" select t2.usercode as id,'a' as pid,t2.username as name,t1.sortid as orderby,'user' as choosetype,'C' as p from f_userinfo t1,common_person t2 where t1.usercode = t2.z_usercode ")
       .append("and t1.loginname='"+loginName+"' ) t")
       .append(" start with pid='BM'")
       .append(" connect by pid = prior id")
       .append(" order siblings by orderby");
    
     @SuppressWarnings("unchecked")
     List<Object[]> rows = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString()));
    return buildMapList(rows);
}
/**
 * 获取职务人员树，全部门的所有有效人员
 * @return
 */
public List<Map<String,String>> findRankUserTree(){
    StringBuffer sql = new StringBuffer();
    sql.append("select * from(")
    .append(" select datacode as id,decode(extracode,'0','BM',extracode) as pid,datavalue as name,to_number(extracode2) as orderby,'unit' as choosetype,'O' as p from f_datadictionary where catalogcode='commonRank' ")
    .append("union all ")
    .append(" select t2.usercode as id,case "
         +" when t1.unitcode='001801' then 'WLD' "
         +" when t1.userrank='ZC' then 'ZC' "
         +" when t1.userrank in('DYY','FXSY','FC') then 'QTCJ' "
         +" when t1.userrank in('ZK') and t1.unitcode in('001784','002184','002185','002186','002187','002188') then 'ZSDW' "
         +" end as pid,t2.username as name,t2.userorder*100+t2.sortid as orderby,'user' as choosetype,'C' as p from f_userunit t1,f_userinfo t2 where t1.usercode = t2.usercode and t1.isprimary='T' and t2.isvalid='T'")
    .append(") t")
    .append(" start with pid='BM'")
    .append(" connect by pid = prior id")
    .append(" order siblings by orderby");
    
     @SuppressWarnings("unchecked")
     List<Object[]> rows = getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString()));
    return buildMapList(rows);
}
}
