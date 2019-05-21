package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaServiceentrance;
import com.centit.sys.po.FVUseroptmoudlelist;

public class OaServiceentranceDao extends BaseDaoImpl<OaServiceentrance>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaServiceentranceDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("id" , CodeBook.EQUAL_HQL_ID);


			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("optname" , CodeBook.LIKE_HQL_ID);

			filterField.put("ordernum" , CodeBook.LIKE_HQL_ID);

			filterField.put("opturl" , CodeBook.LIKE_HQL_ID);

			filterField.put("settime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
	public List<FVUseroptmoudlelist> getList(){
	    String sql="select optid,optname from v_hi_optinfo where topoptid = 'YGJG' and hi_level='2' and optid in ('YGJGBGZY','YGJGGRBG','YGJGGWLZ','YGJGNBSX','YGJGRCBG','Zhgl' )";
	    this.findObjectsBySql(sql);
	    return null;
	}
	/**
	 * 查询添加到首页的业务入口
	 * @param usercode
	 * @return
	 */
	public List<OaServiceentrance> getListByusercode(String usercode){
	    String sql="select * from oa_serviceentrance where usercode ='"+usercode+"' order by ordernum desc";
	    List<OaServiceentrance> l = (List<OaServiceentrance>)this.findObjectsBySql(sql);
	    return l;
	}
}
