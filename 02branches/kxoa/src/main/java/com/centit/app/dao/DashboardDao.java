package com.centit.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centit.app.po.Dashboard;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import java.math.BigDecimal;

public class DashboardDao extends BaseDaoImpl<Dashboard> {
    private static final long serialVersionUID = 1L;
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put( CodeBook.ORDER_BY_HQL_ID ,"  userCode,no asc");

        }
        return filterField;
    } 
       @SuppressWarnings("unchecked")
    public List<Dashboard> getDashboardListFormVOADASHBOARD(
            Map<String, Object> filterMap) {
        List<Dashboard> dashboardlList = new ArrayList<Dashboard>();
        StringBuffer sb = new StringBuffer();
        sb.append("select t.name,t.num,t.itemtype as type from v_oa_dashboard t where usercode ="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("userCode"))+" order by t.usercode,t.no asc");
        List<Object[]> l = ((List<Object[]>) findObjectsBySql(sb.toString()));  
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                Dashboard dashboard=new Dashboard();
                dashboard.setName((String)o[0]);
                dashboard.setSum(o[1].toString());
                dashboard.setType((String)o[2]);
                dashboardlList.add(dashboard);
            }
        }
        return dashboardlList;
    }
       
       /**
        * 接口描述: OA系统提供各类待办事宜获取接口，通过该接口获取设定的需要提醒的待办事项： 新收发文 新通知公告 新领导日程 等
        * 
        * @param userid
        *            用户唯一ID
        * @param type
        *            流程类型，例如：0收文 1发文 2待参加会议 3通知会议。。。等(根据具体业务设置类型)
        * @return JSON
        */
       @SuppressWarnings("unchecked")
       public Map<String, String> getOfficialCount(String usercode ,String types) {
           Map<String, String> dashboard=new HashMap<String, String>();
           StringBuffer sb = new StringBuffer();
           sb.append("select t.itemtype,t.num  from v_user_task_dashboard_mip t where usercode ="+HQLUtils.buildHqlStringForSQL(usercode));
           if(StringUtils.isNotBlank(types)){
              sb.append(" and itemtype in  "+HQLUtils.convertStringsByQuote(types.split(",")));
           }
           List<Object[]>  l = ((List<Object[]>) findObjectsBySql(sb.toString()));  
           if(l!=null && l.size()>0){
               for(Object[] o:l){
                   dashboard.put((String)o[0], o[1].toString());
               }
           }
           return dashboard;
       }
      /**
       * OA系统查询未读信息数量
       * @param usercode
       *            用户id
       * @param itemtype
       *            "DWBL":待我办理   "SJX"：收件箱
       * @return
       */
     @SuppressWarnings("unchecked")
     public Map<String,String> getdashboardNum(String usercode,String itemtype){
         Map<String, String> dashboard=new HashMap<String, String>();
         StringBuffer sb = new StringBuffer();
         sb.append("select t.no,t.num from v_oa_user_task_innermsg t where t.usercode="+HQLUtils.buildHqlStringForSQL(usercode));
         if(StringUtils.isNotBlank(itemtype)){
             sb.append(" and t.itemtype="+HQLUtils.buildHqlStringForSQL(itemtype));
          }
         List<Object[]>  l = ((List<Object[]>) findObjectsBySql(sb.toString()));  
         if(l!=null && l.size()>0){
             for(Object[] o:l){
                 dashboard.put(o[0].toString(), o[1].toString());
             }
         }
         return dashboard;
     }  
     /**
      * OA系统查询消息提醒弹窗信息数量
      * @param usercode
      *            用户id
      * 
      * @return
      */
    @SuppressWarnings("unchecked")
    public Map<String,String> getMessageNum(String usercode){
        Map<String, String> dashboard=new HashMap<String, String>();
        StringBuffer sb = new StringBuffer();
        sb.append("select t.no,t.num from v_oa_dashboard t where t.usercode="+HQLUtils.buildHqlStringForSQL(usercode));
        List<Object[]>  l = ((List<Object[]>) findObjectsBySql(sb.toString()));  
        //查询通知公告未读数量
        String tznum="select nvl(count(no),0) as num from (select v.no  from v_oa_information v where    v.info_type='info' and v.state='1'  "+
                " and v.release_Date<=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and ( v.valid_Date>=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') or v.valid_Date is null )"+
                " and v.NO not in (select f.dj_id from f_userbizopt_log f where f.biztype='INFO' and f.createuser="+HQLUtils.buildHqlStringForSQL(usercode)+" ) )";
        List<Object[]>  s = ((List<Object[]>) findObjectsBySql(tznum.toString()));
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                dashboard.put(o[0].toString(), o[1].toString());
            }
        }
        if(StringUtils.isNotBlank(dashboard.get("6"))){
            dashboard.remove("6");
        }
        if(s!=null && s.size()>0){
            Object a=s.get(0);
            dashboard.put("6",a.toString());
        }
        
        return dashboard;
    }  
    
    /**
     * 获取未读邮件数
     * @param usercode
     * @return
     */
    public Long getUnReadEmailNum(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(*) as num from M_INNERMSG_RECIPIENT_FS m ")
        .append("inner join M_INNERMSG_FS f on m.msgcode = f.msgcode ")
        .append("where m.msgstate = 'U' ")
        .append("and m.mailtype != 'D' ")
        .append("and f.msgtype = 'P' ")
        .append("and m.mailtype = 'I' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and m.receive = '" + usercode + "' ");
        }
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
        
    }
    
    /**
     * 获取未读通知数
     * @param usercode
     * @return
     */
    public Long getUnReadBulletinNum(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(*) as num from oa_information t ")
        .append("where t.state='1' and  t.info_type in  ('GGZY','news','bulletin')");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append(" and t.no not in (select dj_id from f_userbizopt_log where createuser= ? )");
        }
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString(),new Object[]{usercode});
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
    
    /**
     * 获取收文待办数
     * @param usercode
     * @return
     */
    public Long getSWTaskNum(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(*) as num from v_user_task_list_oa t ")
        .append("where t.ITEMTYPE = 'SW' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and t.usercode = '" + usercode + "' ");
        }
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
    
    /**
     * 获取发文待办数
     * @param usercode
     * @return
     */
    public Long getFWTaskNum(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(*) as num from v_user_task_list_oa t ")
        .append("where t.ITEMTYPE = 'FW' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and t.usercode = '" + usercode + "' ");
        }
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
    
    /**
     * 获取来文待办未读数
     * @param usercode
     * @return
     */
    public Long getLWTaskNum(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(*) as num from v_user_task_list_oa t ")
        .append("where t.READSTATE = 'F' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and t.usercode = '" + usercode + "' ");
        }
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
    
    
    /**
     * 获取未读提醒数
     * @param usercode
     * @return
     */
    public Long getUnreadRemind(String usercode){
        
        StringBuffer sb = new StringBuffer("select count(t.no) as num from oa_remind_information t ")
        .append("where t.no in (select r.addrbookid ")
        .append("from OA_ADDRESS_BOOK_RELECTION r ")
        .append("where r.biztype = '0' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and r.shareman = '" + usercode + "' ");
        }
        sb.append(")");
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
    
    /**
     * 获取未读外部邮件数
     * @param usercode
     * @return
     */
    public Long getUnreadOuterEmailNum(String usercode){
        StringBuffer sb = new StringBuffer("select count(t.id) from f_mail_info t ")
        .append("right join f_mail_profile f on t.email = f.email ")
        .append("and f.isactive = 'T' ")
        .append("and t.location = '2' ")
        .append("where t.readstate = 'F' ");
        
        if(StringUtils.isNotBlank(usercode)){
            sb.append("and f.usercode = " + HQLUtils.buildHqlStringForSQL(usercode) );
        }
        
        List<BigDecimal> list = (List<BigDecimal>) this.findObjectsBySql(sb.toString());
        
        if(null != list && !list.isEmpty()){
            return Long.parseLong(list.get(0).toString());
        }
        
        return Long.parseLong("0");
    }
}
