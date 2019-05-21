package com.centit.advancedsearch.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.dao.SQLUtils;
import com.centit.core.utils.PageDesc;
/**
 * 
 * 高级搜索持久层
 * 
 * @author Ghost
 * @create 2017年2月8日
 * @version
 */
public class AdvancedSearchDao extends  BaseDaoImpl<OaSearchResult>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 查询附件 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaSearchResult> findInStuffScope(Map<String,Object>filterMap,PageDesc pageDesc){
        StringBuffer sb = new StringBuffer();
        sb.append("select distinct dj_id,itemtype,title,filename,caseno,creater,usercode");
        sb.append(" from v_opt_advanced_search where filename like ? and (creater=? or usercode=?) order by dj_id");
        
        List<Object[]> objList =  (List<Object[]>) findObjectsByHql(sb.toString(), new String[]{"%"+filterMap.get("keywords").toString()+"%",
            filterMap.get("usercode").toString(),filterMap.get("usercode").toString()}, pageDesc);
        return convertResult(objList);
    }
    /**
     * 查询所有
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaSearchResult> findAll(Map<String,Object>filterMap,PageDesc pageDesc){
        StringBuffer sb = new StringBuffer();
        sb.append("select distinct dj_id,itemtype,title,filename,caseno,creater,usercode");
        sb.append(" from v_opt_advanced_search where title||content||caseno||filename like ? and (creater=? or usercode=? or usercode='public') order by itemtype,dj_id");
      List<Object[]> objList =  (List<Object[]>) findObjectsByHql(sb.toString(), new String[]{"%"+filterMap.get("keywords").toString()+"%",
            filterMap.get("usercode").toString(),filterMap.get("usercode").toString()}, pageDesc);
        return convertResult(objList);
    }
    
    private  List<?> findObjectsByHql(String shql, Object[] values,
            PageDesc pageDesc) {
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();
        // HashMaps for search variety fields
        List<?> l = null;
        try {
            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(shql, values, startPos, maxSize));
        } catch (Exception e) {
            log.error(e.getMessage());
            // return null;
        }
        // set the totalRows to param
        // get the total number of the record,
        
        
        String sql = "select count(1) from ("+SQLUtils.removeOrderBy(shql)+")";
        pageDesc.setTotalRows(Integer.valueOf(super.findObjectsBySql(sql, values).get(0).toString()));

        return l;
    }
    /**
     * 转换结果，注意结果集的顺序
     * djId,itemType,title,fileName,caseNo,creater,usercode
     * @param objList
     * @return
     */
    private List<OaSearchResult> convertResult(List<Object[]> objList){
        if(objList == null)
            return null;
        List<OaSearchResult> searchResults = new ArrayList<OaSearchResult>();
        for(Object[] obj : objList){
            OaSearchResult searchResult = new OaSearchResult();
            if(obj[0]!=null)
                searchResult.setDjId(obj[0].toString());
            if(obj[1]!=null)
                searchResult.setItemType(obj[1].toString());
            if(obj[2]!=null)
                searchResult.setTitle(obj[2].toString());
            if(obj[3]!=null)
                searchResult.setFileName(obj[3].toString());
            if(obj[4]!=null)
                searchResult.setCaseNo(obj[4].toString());
            if(obj[5]!=null)
                searchResult.setCreater(obj[5].toString());
            if(obj[6]!=null)
                searchResult.setUsercode(obj[6].toString());
            searchResults.add(searchResult);
        }
        return searchResults;
    }
    /**
     * 查询非附件的
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaSearchResult> findInScope(Map<String,Object>filterMap,PageDesc pageDesc){
        StringBuffer sb = new StringBuffer();
        sb.append("select distinct dj_id,itemtype,title,filename,caseno,creater,usercode");
        sb.append(" from v_opt_advanced_search where title||content||caseno like ? and (creater=? or usercode=? or usercode='public')");
        if(Integer.parseInt(filterMap.get("scope").toString())==1){//如果是办件
            sb.append(" and itemType not in('ZX','YJ')");
        }else{
            String itemType = "";
            if(Integer.parseInt(filterMap.get("scope").toString())==3){//邮件
                itemType = "YJ";
            }
            if(Integer.parseInt(filterMap.get("scope").toString())==4){//资讯
                itemType = "ZX";
            }
            sb.append(" and itemType = "+HQLUtils.buildHqlStringForSQL(itemType));
        }
        sb.append(" order by dj_Id");
        
        List<Object[]> objList =  (List<Object[]>) findObjectsByHql(sb.toString(), new String[]{"%"+filterMap.get("keywords").toString()+"%",
            filterMap.get("usercode").toString(),filterMap.get("usercode").toString()}, pageDesc);
        return convertResult(objList);
    }
    /**
     * 重写全局搜索
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public List<OaSearchResult> findAllNew(String optval,String title,String ucode){
       StringBuffer sb = new StringBuffer();
       sb.append("select distinct dj_id,itemtype,title,filename,caseno,creater,usercode from v_opt_advanced_search where (usercode='"+ucode+"' or usercode='public')");
        //搜索条件
       if(null!=title && title!="")
            sb.append(" and title like '%"+title+"%'");
       if(null!=optval && optval!=""){
            if(optval.equals("0")){//全部
             
            }else if(optval.equals("1")){//办件    ZX,YJ
                sb.append(" and (itemtype ='ZX' OR itemtype ='YJ')");
            }else if(optval.equals("2")){//附件  查询有附件的数据
                sb.append(" and filename is not null");
            }else if(optval.equals("3")){//邮件    YJ
                sb.append(" and itemtype ='YJ'");
            }else if(optval.equals("4")){//资讯  ZX
                sb.append(" and itemtype ='ZX'");
            }
       }
       sb.append(" order by itemtype,dj_id");
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());  
        if(list == null)
            return null;
        List<OaSearchResult> searchResults = new ArrayList<OaSearchResult>();
        for(Object[] obj : list){
            OaSearchResult searchResult = new OaSearchResult();
            if(obj[0]!=null)
                searchResult.setDjId(obj[0].toString());
            if(obj[1]!=null)
                searchResult.setItemType(obj[1].toString());
            if(obj[2]!=null)
                searchResult.setTitle(obj[2].toString());
            if(obj[3]!=null)
                searchResult.setFileName(obj[3].toString());
            if(obj[4]!=null)
                searchResult.setCaseNo(obj[4].toString());
            if(obj[5]!=null)
                searchResult.setCreater(obj[5].toString());
            if(obj[6]!=null)
                searchResult.setUsercode(obj[6].toString());
            searchResults.add(searchResult);
        }
        return searchResults;
    
}
}