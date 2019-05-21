package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.po.OaMeetingmaterialinfos;

public class OaMeetingmaterialinfosDao extends BaseDaoImpl<OaMeetingmaterialinfos>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetingmaterialinfosDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("MEETING_ATTENDEE" , CodeBook.EQUAL_HQL_ID);


			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isback" , CodeBook.LIKE_HQL_ID);

			filterField.put("backtime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("stuffId" , CodeBook.EQUAL_HQL_ID);
		}
		return filterField;
	} 
    @SuppressWarnings("unchecked")
    public List<OaMeetingmaterialinfos> findStuffIdByCode(String meetcode,String djid){
       return (List<OaMeetingmaterialinfos>) super.findObjectsByHql("from OaMeetingmaterialinfos where cid.meetingAttendee=? and cid.djId=?", new Object[]{meetcode,djid});
    }
    public void deleteBydjId(String djId){
        doExecuteHql("delete from oa_meetingmaterialinfos t where  t.djid='"+djId+"'");
    }

}
