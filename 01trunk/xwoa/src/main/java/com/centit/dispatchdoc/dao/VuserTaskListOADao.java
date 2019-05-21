package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.sys.service.CodeRepositoryUtil;

public class VuserTaskListOADao extends BaseDaoImpl<VuserTaskListOA> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VuserTaskListOADao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("taskId", CodeBook.EQUAL_HQL_ID);

            filterField.put("nodeInstId", CodeBook.EQUAL_HQL_ID);

            filterField.put("unitCode", CodeBook.LIKE_HQL_ID);

            filterField.put("userCode", CodeBook.EQUAL_HQL_ID);

            filterField.put("roleType", CodeBook.LIKE_HQL_ID);

            filterField.put("roleCode", CodeBook.LIKE_HQL_ID);

            filterField.put("optId", CodeBook.LIKE_HQL_ID);

            filterField.put("flowInstId", CodeBook.LIKE_HQL_ID);

            filterField.put("flowOptName", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("flowOptTag", CodeBook.LIKE_HQL_ID);

            filterField.put("authDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeName", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeType", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeOptType", CodeBook.LIKE_HQL_ID);

            filterField.put("optName", CodeBook.LIKE_HQL_ID);

            filterField.put("methodName", CodeBook.LIKE_HQL_ID);

            filterField.put("optUrl", CodeBook.LIKE_HQL_ID);

            filterField.put("optMethod", CodeBook.LIKE_HQL_ID);

            filterField.put("optDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("optCode", CodeBook.LIKE_HQL_ID);

            filterField.put("optParam", CodeBook.LIKE_HQL_ID);

            filterField.put("inststate", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeCreateTime", CodeBook.LIKE_HQL_ID);

            filterField.put("expireOptSign", CodeBook.LIKE_HQL_ID);

            filterField.put("expireOpt", CodeBook.LIKE_HQL_ID);

            filterField.put("grantor", CodeBook.LIKE_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("lastUpdateUser", CodeBook.LIKE_HQL_ID);

            filterField.put("lastUpdateTime", CodeBook.LIKE_HQL_ID);

            filterField.put("flowPhase", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeCode", CodeBook.LIKE_HQL_ID);

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("powerid", CodeBook.LIKE_HQL_ID);

            filterField.put("powername", CodeBook.LIKE_HQL_ID);

            filterField.put("flowCode", CodeBook.LIKE_HQL_ID);

            filterField.put("createuser", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("caseNo", CodeBook.LIKE_HQL_ID);

            filterField.put("sendArchiveNo", CodeBook.LIKE_HQL_ID);
            

            filterField.put("acceptArchiveNo", CodeBook.LIKE_HQL_ID);
            
            filterField.put("gwNature", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("begTime",
                    " createtime>= to_date(?, 'yyyy-mm-dd') ");

            filterField.put("endTime",
                    " createtime<= to_date(?, 'yyyy-mm-dd')+1 ");

            filterField.put("itemtype", CodeBook.EQUAL_HQL_ID);

            filterField.put("supdjid", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);
            
            //filterField.put(CodeBook.ORDER_BY_HQL_ID, " createdate desc ");
            
            // filterField.put("ywtype", CodeBook.EQUAL_HQL_ID);
            filterField.put("cBegTime",
                    " createdate>= to_date(?, 'yyyy-MM-dd hh24:mi:ss') ");
            
            filterField.put("cEndTime",
                    " createdate<= to_date(?, 'yyyy-MM-dd hh24:mi:ss') ");
            
            filterField.put("readstate", CodeBook.LIKE_HQL_ID);
            

            filterField.put("currentdatetime", " createdate < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            
            filterField.put("criticalLevel", CodeBook.LIKE_HQL_ID);
            //即将超期
            filterField.put("NP_toBeExtended"," remainingDays is not null and remainingDays >= 0 and remainingDays <= 3");
            //已经超期
            filterField.put("NP_hasExtended"," remainingDays is not null and remainingDays < 0");
            //超期多时未处理
            filterField.put("NP_seriousExtended"," to_date(to_char(createtime,'yyyy-mm-dd'),'yyyy-mm-dd')-to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') < "+CodeRepositoryUtil.getValue("SYSPARAM", "UNHANDLELIMITDAYS"));
        }
        return filterField;
    }

    /*
     * @SuppressWarnings("unchecked") public List<VoadDcdbNum>
     * getdcdbnum(Map<String, Object> filterMap, PageDesc pageDesc) { String sql
     * = "select * from v_oa_dcdbnum ";
     * 
     * return ((List<VoadDcdbNum>)
     * this.findObjectsBySql(sql,VoadDcdbNum.class));
     * 
     * }
     */

    /**
     * 根据节点实例id及usercode 唯一确定待办是否存在
     * 
     * @param nodeInstId
     * @param userCode
     * @return
     */
    public boolean checkUserTask(Long nodeInstId, String userCode) {
        if(nodeInstId==null || StringUtils.isBlank(userCode)){
            return false;
        }
        
        String shql = " from VuserTaskListOA where nodeInstId=? and userCode=? ";
        Object[] objects = new Object[] { nodeInstId, userCode };
        List<VuserTaskListOA> userTaskList = this.listObjects(shql, objects);
        if (userTaskList != null && userTaskList.size() >= 1) {
            return true;
        } else {
            return false;
        }

    }
    
    
    /**
     * 获取当前步骤中待办及已办的人员
     * @param nodeinstId
     * @return
     */
    public List<Object[]> getCurrentTransactUsers(String djId){
        
        StringBuffer sb = new StringBuffer("select f.unitname, t.usercode, t.bizstate, to_char(t.nodename)  from ")
        .append("(select uu.UNITCODE, v.USERCODE, v.BIZSTATE, v.nodename from v_user_task_list_oa v ")
        .append("left join f_userunit uu on v.usercode = uu.usercode ")
        .append("where v.dj_id = ? ") 
        .append("and uu.isprimary = 'T' ")
        .append("union ")
        .append("select distinct o.unitcode, o.usercode, 'C' as bizstate, o.nodename ")
        .append("from opt_idea_info o ")
        .append("where o.dj_id = ?) t ")
        .append("join f_unitinfo f on t.unitcode = f.unitcode ")
        .append("order by f.unitorder"); 
        
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString(),new Object[]{djId,djId});
        
        return list;
    }
    
    /**
     * 获取办件当前的办理步骤，如果有多个的，就用逗号隔开
     * @param djId
     * @return
     */
    public List<Object[]> getCurrentNodeNames(String djId){
        
        StringBuffer sb = new StringBuffer("select to_char(wmsys.wm_concat(distinct t.NODENAME)) nodename ")
        .append("from v_user_task_list_oa t ")
        .append("where t.DJ_ID = '" + djId + "'");
        
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());
        
        return list;
    }
    
    /**
     * 获取办件当前的节点nodeInstId，如果有多个的，就用逗号隔开
     * @param djId
     * @return
     */
    public List<Object[]> getCurrentNodeInstId(String djId){
        
        StringBuffer sb = new StringBuffer("select wmsys.wm_concat(distinct t.nodeInstId) nodeInstId ")
        .append("from v_user_task_list_oa t ")
        .append("where t.DJ_ID = '" + djId + "'");
        
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());
        
        return list;
    }
}
