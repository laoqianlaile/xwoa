package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.OptProcInfo;

public class OptProcInfoDao extends BaseDaoImpl<OptProcInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptProcInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("nodeinstid", CodeBook.EQUAL_HQL_ID);

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("nodename", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeinststate", CodeBook.LIKE_HQL_ID);

            filterField.put("transcontent", CodeBook.LIKE_HQL_ID);

            filterField.put("ideacode", CodeBook.LIKE_HQL_ID);

            filterField.put("transidea", CodeBook.LIKE_HQL_ID);

            filterField.put("transdate", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("istrans", CodeBook.LIKE_HQL_ID);

            filterField.put("optcode", CodeBook.LIKE_HQL_ID);

            filterField.put("isrisk", CodeBook.LIKE_HQL_ID);

            filterField.put("risktype", CodeBook.LIKE_HQL_ID);

            filterField.put("riskdesc", CodeBook.LIKE_HQL_ID);

            filterField.put("riskresult", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public OptProcInfo getObjectByNodeInstId(long nodeInstId) {
        @SuppressWarnings("unchecked")
        List<OptProcInfo> procs = (List<OptProcInfo>) getHibernateTemplate()
                .find("From OptProcInfo where nodeinstid=?", nodeInstId);
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }

    /**
     * 根据业务主键、环节代码获取过程日志信息
     * 
     * @param djId
     * @param nodeCode
     * @return
     */
    public List<OptProcInfo> getObjectsByNodeCode(String djId, String nodeCode) {
        return this.listObjects("From OptProcInfo"
                + " where djId = ? and nodeCode=? order by transdate ",
                new Object[] { djId, nodeCode });
    }

    public List<OptProcInfo> getObjectsByNodeCode(String djId, String[] nodeCode) {
        return this
                .listObjects(
                        "From OptProcInfo"
                                + " where djId = ? and (nodeCode=? or nodeCode=?) order by transdate ",
                        new Object[] { djId, nodeCode[0], nodeCode[1] });
    }

    public OptProcInfo getObjectByNodeInstIdAndDjId(Long nodeInstId, String djId) {
        @SuppressWarnings("unchecked")
        List<OptProcInfo> procs = (List<OptProcInfo>) getHibernateTemplate()
                .find("From OptProcInfo where nodeinstid=? and djId=?",
                        new Object[] { nodeInstId, djId });
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }

    /**
     * 根据业务主键 获取过程日志信息中有文书的节点
     * 
     * @param djId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OptProcInfo> getObjectOfRecordIdById(String djId) {
        List<OptProcInfo> procs = (List<OptProcInfo>) getHibernateTemplate()
                .find("From OptProcInfo where djId=? and recordId is not null order by transdate desc ",
                        djId);
            return procs;
    }

    public List<OptProcInfo> getObjectOfRecordIdById(String djId,
            String documentType) {
        List<OptProcInfo> procs = (List<OptProcInfo>) getHibernateTemplate()
                .find("From OptProcInfo where djId=? and archiveType=?  and recordId is not null order by transdate desc ",
                        new Object[] {djId,documentType });
            return procs;
    }
    
    /**
     * 根据业务主键,节点，用户 获取过程日志信息
     * @param djId
     * @param nodeInstId
     * @param usercode
     * @return
     */
    public OptProcInfo getObjectByDjidAndNodeInstId(String djId,Long nodeInstId,String usercode) {
        @SuppressWarnings("unchecked")
        List<OptProcInfo> procs = (List<OptProcInfo>) getHibernateTemplate()
                .find("From OptProcInfo where djId=? and nodeinstid=? and usercode=?", new Object[]{djId,nodeInstId,usercode});
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    }
}
