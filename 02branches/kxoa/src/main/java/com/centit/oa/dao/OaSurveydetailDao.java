package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.po.VOaSurveyDetail;

public class OaSurveydetailDao extends BaseDaoImpl<OaSurveydetail>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaSurveydetailDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("itemid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);


			filterField.put("no" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
	
	 /**
     * 删除某个人的某个问答信息
     * @param o
     */
    public void deleteDetailByNo(OaSurveydetail o) {
        final String hql = "delete from OaSurveydetail o where o.cid.creater = ? and o.djid=?";
        this.doExecuteHql(hql,new Object[]{o.getCid().getCreater(),o.getDjid()});
    }
    
   /**
    * 根据调查id，投票者获取详细调查结果
    * @param o
    * @return
    */
   public List<VOaSurveyDetail> getSurveyDetail(String djid ,String creater) {
       String hql="From VOaSurveyDetail where djid=? and creater =? ";
    @SuppressWarnings("unchecked")
    List<VOaSurveyDetail> list=(List<VOaSurveyDetail>) super
            .findObjectsByHql(
                    hql,
                    new Object[] { djid, creater });
     return list;
   }
    /**
     * 按調查id獲取人員列表
     * @return
     */
//    @SuppressWarnings("unchecked")
//    public List<FUserinfo> detailList(String djid,PageDesc pageDesc){
     
//        DetachedCriteria criteria = DetachedCriteria.forClass(OaSurveydetail.class);
//       if (StringUtils.isNotBlank(djid)) {
//           criteria.add(Restrictions.eq("djid", djid));
//       }
//        criteria.setProjection(Projections.rowCount());
//
//        pageDesc.setTotalRows(((List<Long>) getHibernateTemplate().findByCriteria(criteria)).get(0).intValue());
//
//       
//        return getHibernateTemplate().findByCriteria(criteria, pageDesc.getRowStart(), pageDesc.getTotalRows());
        
//        
//       List<FUserinfo> list=new ArrayList<FUserinfo>();
//        list=getHibernateTemplate().find("select distinct cid.creater from OaSurveydetail  where djid=? ",new Object[]{djid});
//       String shql="select distinct t.cid.creater from OaSurveydetail t ";
     
//       list=listObjects(shql, pageDesc) ;  
//       String shql = "from FUserinfo u where u.usercode " +
//               " in (select distinct o.cid.creater ,o.djid from OaSurveydetail o  where o.djid=?)  ";
//       return this.listObjects(shql,new Object[]{djid} , pageDesc);


        
       
       
//    }
}
