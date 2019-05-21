package com.centit.powerruntime.dao;

import java.util.ArrayList;
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
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.VOptApplyInfo;

public class OptApplyDao extends BaseDaoImpl<OptApplyInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptApplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);
			filterField.put("channelName" , CodeBook.LIKE_HQL_ID);

			filterField.put("channelLevel" , CodeBook.LIKE_HQL_ID);

			filterField.put("elevationSystem" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyDate" , "applyDate like to_date(?,'yyyy-mm-dd')");

			filterField.put("proposerName" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyItem" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyReason" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyWay" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerType" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerPaperType" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerPaperCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerPhone" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerMobile" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerAddr" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerZipcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerEmail" , CodeBook.LIKE_HQL_ID);

			filterField.put("proposerUnitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentName" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentPaperType" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentPaperCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentPhone" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentMobile" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentAddr" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("recievedepno" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentZipcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentEmail" , CodeBook.LIKE_HQL_ID);

			filterField.put("agentUnitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("applyMemo" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptDate" , "acceptDate like to_date(?,'yyyy-mm-dd')");
			filterField.put("bookDate" , "bookDate like to_date(?,'yyyy-mm-dd')");
			filterField.put("auditingDate" , "auditingDate like to_date(?,'yyyy-mm-dd')");

			filterField.put("headUsercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("headAuditingIdea" , CodeBook.LIKE_HQL_ID);

			filterField.put("checkIdeaDate" , "checkIdeaDate like to_date(?,'yyyy-mm-dd')");

			filterField.put("checkUsercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("checkIdea" , CodeBook.LIKE_HQL_ID);

			filterField.put("checkDetail" , CodeBook.LIKE_HQL_ID);

			filterField.put("checkAddr" , CodeBook.LIKE_HQL_ID);

			filterField.put("checkDate" , "checkDate like to_date(?,'yyyy-mm-dd')");

			filterField.put("checkMemo" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " bookDate desc");

		}
		return filterField;
	} 
	
	public String genNextDjID(){
	    return "XK" + getNextKeyBySequence("S_PERMIT_APPLY",14);
	}
	
	  @SuppressWarnings("unchecked")
	    public List<VOptApplyInfo> listOptApplyInfo(
	            Map<String, Object> filterMap, PageDesc pageDesc) {
	        String shql = "from v_opt_apply_info where 1=1 ";

	        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
	        String queryUnderUnit = "";
	        if ("true".equals(filterMap.get("queryUnderUnit"))) {
	            queryUnderUnit = " or orgcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
	                    + filterMap.get("orgcode") + " ) ";
	        }
	        String hql1 = "select *  " + hql.getHql() + queryUnderUnit;
	        String hql2 = "select count(*)  " + hql.getHql() + queryUnderUnit;
	        int startPos = 0;
	        int maxSize;
	        startPos = pageDesc.getRowStart();
	        maxSize = pageDesc.getPageSize();

	        List<VOptApplyInfo> l = null;
	        try {

	            l = getHibernateTemplate().executeFind(
	                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
	                            maxSize, VOptApplyInfo.class));
	            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
	                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
	                    .get(0).toString()));
	        } catch (Exception e) {
	            log.error(e.getMessage());
	        }

	        return l;
	    }
     //办件删除
	  public void deleteObjectBanInfo(String djId){
	        try{
	            this.callProcedure("p_data_clean", djId);
	        
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

        @SuppressWarnings("unchecked")
        public List<VuserTaskListOA> getuserbysql(Long curNodeInstId) {
            String sql = "select t.NODEINSTID,t.NODENAME,t.USERCODE,t.UNITCODE,t.WFOPTTAG,t.WFOPTNAME,t.WFINSTID from v_user_task_list t WHERE 1=1 and t.NODEINSTID='"+curNodeInstId+"'";
            List<Object[]> l = (List<Object[]>) findObjectsBySql(sql);
            List<VuserTaskListOA> list=new ArrayList<VuserTaskListOA>() ;
            if (null!=l&&!l.isEmpty()) {
                for (int i = 0; i < l.size(); i++) {
                    Object[] O = (Object[]) l.get(i);
                    VuserTaskListOA po = new VuserTaskListOA();
                    int nodeistid=Integer.valueOf((O[0].toString()));
                    po.setNodeInstId((long)nodeistid);       
                    po.setNodeName((String)O[1]);
                    po.setUserCode((String)O[2]);
                    po.setUnitCode((String)O[3]);
                    po.setDjId((String)O[4]);
                    po.setFlowOptName((String)O[5]);   
                    int flowistid=Integer.valueOf((O[6].toString()));
                    po.setFlowInstId((long)flowistid);             
                    list.add(po);
                }
            }
             return list;
         
         }
}
