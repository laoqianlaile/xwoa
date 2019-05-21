package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaFilemanager;

public class OaFilemanagerDao extends BaseDaoImpl<OaFilemanager>
{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaFilemanager.class);
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();
    
            filterField.put("no" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put("infoType" , CodeBook.EQUAL_HQL_ID);

            filterField.put("title" , CodeBook.LIKE_HQL_ID);

            filterField.put("state" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put("searchword" , " instr((title||publicKey),?)>0 ");
            
            filterField.put("currentdatetime" , "creatertime<  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
        }
        return filterField;
    } 
    
    public void autoInvalidated(){
        doExecuteHql("update OaInformation t  set t.state='0' where t.state ='1' and t.validDate<=sysdate ");
    }
}
