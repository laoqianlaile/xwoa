package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.po.VOptBaseList;

public class OaUserinfoDao extends BaseDaoImpl<OaUserinfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaUserinfoDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);


			//filterField.put("username" , CodeBook.LIKE_HQL_ID);
			filterField.put("username" ," usercode in (select usercode f_userinfo where username like ?) ");

			filterField.put("headpicturename" , CodeBook.LIKE_HQL_ID);

			filterField.put("headpicture" , CodeBook.LIKE_HQL_ID);

			filterField.put("sex" , CodeBook.LIKE_HQL_ID);

			filterField.put("placeofbirth" , CodeBook.LIKE_HQL_ID);

			filterField.put("tolive" , CodeBook.LIKE_HQL_ID);

			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobilephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("qq" , CodeBook.LIKE_HQL_ID);

			filterField.put("msn" , CodeBook.LIKE_HQL_ID);

			filterField.put("email" , CodeBook.LIKE_HQL_ID);

			filterField.put("school" , CodeBook.LIKE_HQL_ID);

			filterField.put("record" , CodeBook.LIKE_HQL_ID);

			filterField.put("documenttype" , CodeBook.LIKE_HQL_ID);

			filterField.put("idno" , CodeBook.LIKE_HQL_ID);

			filterField.put("mailingaddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("personalsignature" , CodeBook.LIKE_HQL_ID);

			filterField.put("introduce" , CodeBook.LIKE_HQL_ID);

			filterField.put("hobbies" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifydate" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("userlevel" , CodeBook.LIKE_HQL_ID);

			filterField.put("levelnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("picturename" , CodeBook.LIKE_HQL_ID);

			filterField.put("pictureim" , CodeBook.LIKE_HQL_ID);

			filterField.put("workplace" , CodeBook.LIKE_HQL_ID);

			filterField.put("birthday" , CodeBook.LIKE_HQL_ID);

			filterField.put("otherlinks" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " userorder asc  ,sortid asc  ");
			
		}
		return filterField;
	} 
	public List<OaUserinfo> listOauserinfoObjects(Map<String, Object> filterMap){
	  String  shql="select o.usercode ,u.username ,o.headpicturename ,o.headpicture,o.sex,o.placeofbirth,o.tolive ,o.telephone,o.mobilephone,o.qq,o.msn,"+
       "o.email,o.school,o.record, o.documenttype,o.idno,o.mailingaddress,o.personalsignature,o.introduce,o.hobbies,o.lastmodifydate,"+
       "o.createdate,o.userlevel,o.levelnum,o.remark,o.picturename,o.pictureim,o.workplace,o.birthday,o.otherlinks,o.isusepicture,"+
       "o.isshowsex,o.isshowplaceofbirth,o.isshowtolive,o.isshowtelephone,o.isshowmobilephone,o.isshowschool,o.isshowemail,"+
       "o.isshowmsn,o.isshowqq,o.isshowintroduce,o.isshowhobbies,o.isshowidno,o.isshowrecord"+ 
  " from oa_userinfo o "+
  " join f_userinfo u on o.usercode=u.usercode "+
 " where 1 = 1 ";
	  StringBuffer sb=new StringBuffer();
	  if(StringUtils.isNotBlank((String)filterMap.get("sex"))){
	      sb.append(" and o.sex="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("sex")));
      }
	  if(StringUtils.isNotBlank((String)filterMap.get("userName"))){
          sb.append(" and u.username like "+HQLUtils.buildHqlStringForSQL("%"+(String)filterMap.get("userName")+"%"));
      }
	  if(StringUtils.isNotBlank((String)filterMap.get("telephone"))){
          sb.append(" and o.telephone like "+HQLUtils.buildHqlStringForSQL("%"+(String)filterMap.get("telephone")+"%"));
      }
	  if(StringUtils.isNotBlank((String)filterMap.get("mobilephone"))){
          sb.append(" and o.mobilephone like "+HQLUtils.buildHqlStringForSQL("%"+(String)filterMap.get("mobilephone")+"%"));
      }
	  if(StringUtils.isNotBlank((String)filterMap.get("usercode"))){
          sb.append(" and o.usercode="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("usercode")));
      }
	  String sq = shql + sb.toString();
	  List<Object[]> l = (List<Object[]>) findObjectsBySql(sq);
	  List<OaUserinfo> oauserinfolist=new ArrayList<OaUserinfo>();
	  if(l!=null && l.size()>0){
          for(Object[] o:l){
              OaUserinfo bean=new OaUserinfo();
              bean.setUsercode(o[0]!=null?o[0].toString():"");
              bean.setUsername(o[1]!=null?o[1].toString():"");
              bean.setSex(o[4]!=null?o[4].toString():"");
              bean.setTelephone(o[7]!=null?o[7].toString():"");
              bean.setMobilephone(o[8]!=null?o[8].toString():"");
              bean.setWorkplace(o[27]!=null?o[27].toString():"");
              oauserinfolist.add(bean);
          }
	  }
	    return oauserinfolist;
	}
}
