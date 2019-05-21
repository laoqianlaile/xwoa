package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.IncomeDoc;

public class IncomeDocDao extends BaseDaoImpl<IncomeDoc> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IncomeDocDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("internalNo", CodeBook.LIKE_HQL_ID);

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptNo", CodeBook.LIKE_HQL_ID);

            filterField.put("itemSource", CodeBook.LIKE_HQL_ID);

            filterField.put("applyItemType", CodeBook.LIKE_HQL_ID);

            filterField.put("projectName", CodeBook.LIKE_HQL_ID);

            filterField.put("applyDemo", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocNo", CodeBook.EQUAL_HQL_ID);

            filterField.put("incomeDocTitle", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDeptType", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDeptName", CodeBook.LIKE_HQL_ID);

            filterField.put("applyOrgCode", CodeBook.LIKE_HQL_ID);

            filterField.put("docCreateDate", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDate", CodeBook.LIKE_HQL_ID);

            filterField.put("secretDegree", CodeBook.LIKE_HQL_ID);

            filterField.put("contactName", CodeBook.LIKE_HQL_ID);

            filterField.put("contactPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("applyUser", CodeBook.LIKE_HQL_ID);

            filterField.put("applyUserPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("applyUserAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("applyUserZip", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocFileName", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocFile", CodeBook.LIKE_HQL_ID);

            filterField.put("operateState", CodeBook.LIKE_HQL_ID);

            filterField.put("syncErrorDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("createDate", CodeBook.LIKE_HQL_ID);

            filterField.put("updateDate", CodeBook.LIKE_HQL_ID);

            filterField.put("syncDate", CodeBook.LIKE_HQL_ID);

            filterField.put("syncSign", CodeBook.LIKE_HQL_ID);
            
            filterField.put("caseAcceptDate", CodeBook.LIKE_HQL_ID);
            
            filterField.put("signIssueDate", CodeBook.LIKE_HQL_ID);
            
            filterField.put("provincialGovDocNo", CodeBook.LIKE_HQL_ID);
            
            filterField.put("notDjId", "djId<>?");
            
            filterField.put("NP_gw", " applyItemType like 'gw%'");
            
            filterField.put("NP_sp", " applyItemType not like 'gw%'");

        }
        return filterField;
    }

    public IncomeDoc getIncomeDoc(String internalNo, String itemId) {
        String shql = " from IncomeDoc where internalNo=? and itemId=? ";
        Object[] objects = new Object[] { internalNo, itemId };
        List<IncomeDoc> incomeDocs = this.listObjects(shql, objects);
        if (incomeDocs != null && incomeDocs.size() >= 1) {
            return incomeDocs.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * 获取系统内的发文机关名称
     * @return
     */
    public List<String> getIncomeDeptNames(){
        
        StringBuffer sb = new StringBuffer("select distinct t.income_dept_name from m_income_doc t where t.income_dept_name is not null");
        
        List<String> list = (List<String>)this.findObjectsBySql(sb.toString());
        
        return list;
    }
    
    /**
     * 获取系统内的来文单位分类
     * @return
     */
    public List<String> getIncomeAcceptNos(){
        
        StringBuffer sb = new StringBuffer("select distinct substr(t.accept_no, 0,instr(t.accept_no,'〔') -1 ) from m_income_doc t where t.accept_no is not null");
        
        List<String> list = (List<String>)this.findObjectsBySql(sb.toString());
        
        return list;
    }
}
