package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.Addressbooks;
import com.centit.oa.po.OaDuescollection;
import com.centit.oa.po.OaKqvisitorgroup;
import com.centit.sys.po.FUserinfo;
import com.sun.org.apache.bcel.internal.classfile.Code;

public class OaDuescollectionDao extends BaseDaoImpl<OaDuescollection>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaDuescollectionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("endtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendinfo" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendshortnew" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendnotice" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendemail" , CodeBook.LIKE_HQL_ID);

			filterField.put("isfinish" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendpersens" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendpersennames" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);
			

            filterField.put("begTime", " endtime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " endtime< to_date(?, 'yyyy-mm-dd')+1 ");
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID," endtime desc");

		}
		return filterField;
	} 
    @SuppressWarnings("unchecked")
    public List<FUserinfo> getUserByCode(String usercode) {
        List<FUserinfo> list = new ArrayList<FUserinfo>();   
            list = getHibernateTemplate().find(" select f.username from FUserinfo f where f.usercode = ?", new Object[] {usercode});       
        return list;
    }
    public List<OaDuescollection> listOaDuescollection(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String sHql=" from OaDuescollection t where 1=1 ";

        if(StringUtils.isNotBlank((String)filterMap.get("begTime"))){
            sHql+=" and endtime>= to_date('"+filterMap.get("begTime")+"', 'yyyy-MM-dd hh24:mi:ss')";
        }
        if(StringUtils.isNotBlank((String)filterMap.get("endTime"))){
            sHql+=" and endtime< to_date('"+filterMap.get("endTime")+"', 'yyyy-MM-dd hh24:mi:ss')";
        }

        
        List<OaDuescollection>  oaDuescollectionList = null;
        try {
            oaDuescollectionList=this.listObjects(sHql);
            pageDesc.setTotalRows(oaDuescollectionList.size());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        return oaDuescollectionList;
    } 
}
