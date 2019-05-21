package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.powerruntime.po.OptBaseInfo;

public class OptBaseInfoDao extends BaseDaoImpl<OptBaseInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptBaseInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("optId", CodeBook.EQUAL_HQL_ID);

            filterField.put("flowCode", CodeBook.EQUAL_HQL_ID);

            filterField.put("flowInstId", CodeBook.EQUAL_HQL_ID);

            filterField.put("transAffairNo", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("transAffairQueryKey", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);

            filterField.put("biztype", CodeBook.EQUAL_HQL_ID);

            filterField.put("orgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("orgname", CodeBook.LIKE_HQL_ID);

            filterField.put("headunitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("headusercode", CodeBook.LIKE_HQL_ID);

            filterField.put("content", CodeBook.LIKE_HQL_ID);

            filterField.put("powerid", CodeBook.LIKE_HQL_ID);

            filterField.put("powername", CodeBook.LIKE_HQL_ID);

            filterField.put("solvestatus", CodeBook.LIKE_HQL_ID);

            filterField.put("solvetime", CodeBook.LIKE_HQL_ID);

            filterField.put("solvenote", CodeBook.LIKE_HQL_ID);

            filterField.put("createuser", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("caseNo", CodeBook.LIKE_HQL_ID);

            filterField.put("sendArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("riskType", CodeBook.LIKE_HQL_ID);

            filterField.put("riskDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("riskResult", CodeBook.LIKE_HQL_ID);
            
            filterField.put("isUpload", CodeBook.LIKE_HQL_ID);
            filterField.put("dbType", CodeBook.LIKE_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID,"djId desc");
            
        }
        return filterField;
    }

    public OptBaseInfo getOptBaseByFlowId(Long flowinstid) {
        List<OptBaseInfo> optBaseList = this.listObjects(
                "from OptBaseInfo where flowInstId = ?", flowinstid);
        if (optBaseList == null || optBaseList.size() == 0) {
            return null;
        }
        return optBaseList.get(0);
    }

    @SuppressWarnings("unchecked")
    public int getNumOfsameModel(String codeModel, String year) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select dj_id,caseno from opt_base_info where to_char(createdate, 'yyyy')="
                + HQLUtils.buildHqlStringForSQL(year)
                + " and caseNo like "
                + HQLUtils.buildHqlStringForSQL("%" + codeModel + "%"));
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sb.toString());
        return l.size();
    }

    public List<OptBaseInfo> listOptBaseInfo(Map<String, Object> filterMap, PageDesc pageDesc){
        String shql = " from OptBaseInfo where bizstate<>'F' ";
        List<OptBaseInfo> c = (List<OptBaseInfo>) listObjects(shql,filterMap,pageDesc);
        return c;
    }

    @SuppressWarnings("unchecked")
    public List<VuserTaskListOA> getTasksByNodeInstId(Long curNodeInstId) {
            String sql = "select t.NODEINSTID,t.NODENAME,t.USERCODE,t.UNITCODE,t.WFOPTTAG,t.WFOPTNAME,t.WFINSTID from v_user_task_list t WHERE 1=1 and t.NODEINSTID='"+curNodeInstId+"'";
            List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
            List<VuserTaskListOA> list=new ArrayList<VuserTaskListOA>() ;
            if (null!=l&&!l.isEmpty()) {
                for (int i = 0; i < l.size(); i++) {
                    Object[] O = (Object[]) l.get(i);
                    VuserTaskListOA po = new VuserTaskListOA();
                    int nodeistid=Integer.valueOf((O[0].toString()));
                    po.setNodeInstId((long)nodeistid);       
                    po.setNodeName((String)O[1]);
                    po.setUserCode((String)O[2]);
                    po.setUnitCode((String)O[3]);
                    po.setDjId((String)O[4]);
                    po.setFlowOptName((String)O[5]);   
                    int flowistid=Integer.valueOf((O[6].toString()));
                    po.setFlowInstId((long)flowistid);             
                    list.add(po);
                }
            }
             return list;
         
         }
    /**
     * 根据收文号查询(已提交)
     * @param acceptArchiveNo 收文编号
     * @return list<OptBaseInfo>
     */
    public List<OptBaseInfo> listOptBaseInfo(
            String acceptArchiveNo){
        String shql = " from OptBaseInfo where bizstate !='F'  and acceptArchiveNo='"+acceptArchiveNo+"'";
        
        List<OptBaseInfo> c = (List<OptBaseInfo>) listObjects(shql);
        return c;
    }
    
    
    /**
     * 根据收文号查询(已提交)
     * @param acceptArchiveNo 收文编号
     * @return list<OptBaseInfo>
     */
    public List<OptBaseInfo> listOptBaseInfoModify(
            String acceptArchiveNo,String powerid){
        String shql = " from OptBaseInfo where bizstate !='F'  and acceptArchiveNo='"+acceptArchiveNo+"' and powerid='"+powerid+"'";
        
        List<OptBaseInfo> c = (List<OptBaseInfo>) listObjects(shql);
        return c;
    }
    
    

    @SuppressWarnings("unchecked")
    public String getNodeNamesByFlowinstid(Long flowinstid) {
        String sql = "select 1,to_char(wmsys.wm_concat(nodename)) from (select  WFINSTID,nodename,row_number() over(partition by WFINSTID,nodename,NODESTATE order by nodename) cn from V_NODE_INSTDETAIL N where N.NODESTATE = 'N') i where  i.WFINSTID ="+flowinstid+" and cn=1 ";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
        String returnvalue="";
        if(l!=null && l.size()>0){
            Object[] O = (Object[]) l.get(0);
            returnvalue=(String)O[1];
        }
        return returnvalue;
    }

    @SuppressWarnings("unchecked")
    public String getuserNamesByFlowinstid(Long flowinstid) {
        String sql = "select 1,tem.bizusername from (select nodename,t.WFINSTID,to_char(wmsys.wm_concat(u.username)) as bizusername from v_user_task_list t left join f_userinfo u on u.usercode=t.USERCODE group by t.WFINSTID,t.NODENAME ) tem where tem.WFINSTID="+flowinstid+" and rownum=1 ";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
        String returnvalue="";
        if(l!=null && l.size()>0){
            Object[] O = (Object[]) l.get(0);
            returnvalue=O[1].toString();
        }
        return returnvalue;
    }
    @SuppressWarnings("unchecked")
    public List<Object[]> listoptBaseExceptionNum(){
        String sql="select name,type,num,itemtype from V_OPT_BASE_EXCEPTION_COUNT t";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
        return l;
    }
    //获取本年度办件统计
    @SuppressWarnings("unchecked")
    public List<Object[]> listoptBaseNumByYear(){
        String sql="select substr(t.dj_id,1,instr(t.dj_id,'0')-1) as itemtype,substr(t.dj_id,1,instr(t.dj_id,'0')-1)||'_NUM_'||t.bizstate as type,count(t.dj_id) as num from opt_base_info t where t.bizstate!='F' and t.createdate>=to_date((select to_char(sysdate,'yyyy')||'-01-01' from dual),'yyyy-MM-dd') group by substr(t.dj_id,1,instr(t.dj_id,'0')-1),t.bizstate";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
        return l;
    }
}
