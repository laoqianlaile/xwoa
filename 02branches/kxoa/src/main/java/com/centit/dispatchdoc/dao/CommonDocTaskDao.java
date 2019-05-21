package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.CommonDocTask;
import com.centit.dispatchdoc.po.IncomeDocTask;

public class CommonDocTaskDao extends BaseDaoImpl<CommonDocTask>{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(CommonDocTaskDao.class);
    
public Map<String, String> getFilterField() {
    if( filterField == null){
        filterField = new HashMap<String, String>();

        filterField.put("taskId" , CodeBook.EQUAL_HQL_ID);


        filterField.put("nodeInstId" , CodeBook.LIKE_HQL_ID);

        filterField.put("unitCode" , CodeBook.LIKE_HQL_ID);

        filterField.put("userCode" , CodeBook.LIKE_HQL_ID);

        filterField.put("roleType" , CodeBook.LIKE_HQL_ID);

        filterField.put("roleCode" , CodeBook.LIKE_HQL_ID);

        filterField.put("optId" , CodeBook.LIKE_HQL_ID);

        filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

        filterField.put("flowOptName" , CodeBook.LIKE_HQL_ID);

        filterField.put("flowOptTag" , CodeBook.LIKE_HQL_ID);

        filterField.put("authDesc" , CodeBook.LIKE_HQL_ID);

        filterField.put("nodeName" , CodeBook.LIKE_HQL_ID);

        filterField.put("nodeType" , CodeBook.LIKE_HQL_ID);

        filterField.put("nodeOptType" , CodeBook.LIKE_HQL_ID);

        filterField.put("optName" , CodeBook.LIKE_HQL_ID);

        filterField.put("methodName" , CodeBook.LIKE_HQL_ID);

        filterField.put("optUrl" , CodeBook.LIKE_HQL_ID);

        filterField.put("optMethod" , CodeBook.LIKE_HQL_ID);

        filterField.put("optDesc" , CodeBook.LIKE_HQL_ID);

        filterField.put("optCode" , CodeBook.LIKE_HQL_ID);

        filterField.put("optParam" , CodeBook.LIKE_HQL_ID);

        filterField.put("inststate" , CodeBook.LIKE_HQL_ID);

        filterField.put("nodeCreateTime" , CodeBook.LIKE_HQL_ID);

        filterField.put("expireOptSign" , CodeBook.LIKE_HQL_ID);

        filterField.put("expireOpt" , CodeBook.LIKE_HQL_ID);

        filterField.put("grantor" , CodeBook.LIKE_HQL_ID);

        filterField.put("timeLimit" , CodeBook.LIKE_HQL_ID);

        filterField.put("lastUpdateUser" , CodeBook.LIKE_HQL_ID);

        filterField.put("lastUpdateTime" , CodeBook.LIKE_HQL_ID);

        filterField.put("flowPhase" , CodeBook.LIKE_HQL_ID);

        filterField.put("djId" , CodeBook.LIKE_HQL_ID);

        filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

        filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

        filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

        filterField.put("orgcode" , CodeBook.LIKE_HQL_ID);

        filterField.put("orgname" , CodeBook.LIKE_HQL_ID);

        filterField.put("headunitcode" , CodeBook.LIKE_HQL_ID);

        filterField.put("headusercode" , CodeBook.LIKE_HQL_ID);

        filterField.put("content" , CodeBook.LIKE_HQL_ID);

        filterField.put("powerid" , CodeBook.LIKE_HQL_ID);

        filterField.put("powername" , CodeBook.LIKE_HQL_ID);

        filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

        filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

        filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

        filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

        filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

        filterField.put("riskType" , CodeBook.LIKE_HQL_ID);

        filterField.put("sendArchiveNo" , CodeBook.LIKE_HQL_ID);

        filterField.put("acceptArchiveNo" , CodeBook.LIKE_HQL_ID);

        filterField.put("riskDesc" , CodeBook.LIKE_HQL_ID);

        filterField.put("riskResult" , CodeBook.LIKE_HQL_ID);

        filterField.put("transAffairNo" , CodeBook.LIKE_HQL_ID);

        filterField.put("transAffairQueryKey" , CodeBook.LIKE_HQL_ID);
        
        filterField.put("flowCode" , CodeBook.EQUAL_HQL_ID);
        
    }
    return filterField;
} 

public CommonDocTask getObjectByDjId(String djId){
    String shql = " from IncomeDocTask where djId=? ";
    Object[] objects = new Object[]{djId};
    List<CommonDocTask> Applys = this.listObjects(shql, objects);
    if (Applys != null && Applys.size() >= 1){
        return Applys.get(0);
    }else{
        return null;
    }
}

@SuppressWarnings("unchecked")
public List<CommonDocTask> listIncomeDocTask_Common(
        Map<String, Object> filterMap, PageDesc pageDesc) {
    String shql = "FROM v_user_task_list_common where 1=1  ";
    HqlAndParams hql = builderHqlAndParams(shql, filterMap);
    
    String hql1 = "select *  " + hql.getHql();
    String hql2 = "select count(*)  " + hql.getHql();
    int startPos = 0;
    int maxSize;
    startPos = pageDesc.getRowStart();
    maxSize = pageDesc.getPageSize();

    List<CommonDocTask> l= null;
    try {

        l = getHibernateTemplate().executeFind(
                new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                        maxSize, IncomeDocTask.class));
        pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                .get(0).toString()));
    } catch (Exception e) {
        log.error(e.getMessage());
    }

    return l;
}
}
