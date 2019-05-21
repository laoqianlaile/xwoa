package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.oa.po.OaMeetinfo;

public class OaMeetinfoDao extends BaseDaoImpl<OaMeetinfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetinfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("isuse" , CodeBook.EQUAL_HQL_ID);

			filterField.put("coding" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingName" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingType" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNumber" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingUseing" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingAddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingInfloor" , CodeBook.LIKE_HQL_ID);

			filterField.put("basicConfiguration" , CodeBook.LIKE_HQL_ID);

			filterField.put("accessoryEquipment" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsibleDep" , CodeBook.LIKE_HQL_ID);

			filterField.put("responsiblePersion" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetinfPicture" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetinfPictureName" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("motifytime" , CodeBook.LIKE_HQL_ID);	
			
			filterField.put("orderno" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("NP_isuse", "( isuse='T' or isuse is null )");
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " orderno asc");
			filterField.put("currentdatetime" , "createtime<=  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");


		}
		return filterField;
	}

    public List<OaMeetinfo> listObjectsWithOutLob(Map<String, Object> filterMap) {
        String shql = " select new OaMeetinfo(o.djid,o.meetingName) from OaMeetinfo  o ";
        return listObjects(shql, filterMap);
    }

    @SuppressWarnings("unchecked")
    public List<OaMeetinfo> listObjectsWithOutLOB(Map<String, Object> picMap) {
        String sSqlsen = "select djid,meeting_Name from Oa_Meetinfo where isuse='T' ";        
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
        List<OaMeetinfo> optBaseLists=new ArrayList<OaMeetinfo>();
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                OaMeetinfo bean=new OaMeetinfo();
                bean.setDjid(o[0]!=null?o[0].toString():"");
                bean.setMeetingName(o[1]!=null?o[1].toString():"");
            
                optBaseLists.add(bean);
            }
        }
        
        return optBaseLists;
    }

    @SuppressWarnings("unchecked")
    public OaMeetinfo getObjectByIdWithOutLOB(String djid) {
        
        String sSqlsen = "select djid,meeting_Name,to_char(Basic_configuration),to_char(Accessory_equipment) from Oa_Meetinfo where isuse='T' and djid="+HQLUtils.buildHqlStringForSQL(djid);        
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
        List<OaMeetinfo> optBaseLists=new ArrayList<OaMeetinfo>();
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                OaMeetinfo bean=new OaMeetinfo();
                bean.setDjid(o[0]!=null?o[0].toString():"");
                bean.setMeetingName(o[1]!=null?o[1].toString():"");
                bean.setBasicConfiguration(o[2]!=null?o[2].toString():"");
                bean.setAccessoryEquipment(o[3]!=null?o[3].toString():"");
                optBaseLists.add(bean);
            }
            return optBaseLists.get(0);
        }else{
            return null;  
        }
        
        
    } 
}
