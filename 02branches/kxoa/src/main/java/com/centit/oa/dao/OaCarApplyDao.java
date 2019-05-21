package com.centit.oa.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.po.OaCarApply;

public class OaCarApplyDao extends BaseDaoImpl<OaCarApply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaCarApplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("applyNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingPersionsNum" , CodeBook.LIKE_HQL_ID);

			filterField.put("destination" , CodeBook.LIKE_HQL_ID);

			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("planBegTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("planEndTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("reqRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingPersions" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("doTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("doBegTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("doEndTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);

			filterField.put("doDepno" , CodeBook.LIKE_HQL_ID);

			filterField.put("doCreater" , CodeBook.LIKE_HQL_ID);

			filterField.put("cardjid" , CodeBook.LIKE_HQL_ID);

			filterField.put("carno" , CodeBook.LIKE_HQL_ID);

			filterField.put("driver" , CodeBook.LIKE_HQL_ID);

			filterField.put("brand" , CodeBook.LIKE_HQL_ID);

			filterField.put("modelType" , CodeBook.LIKE_HQL_ID);

			filterField.put("drTelephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("doRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("isUse" , CodeBook.LIKE_HQL_ID);

			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("todoremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);
		    filterField.put("begTime", " createtime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " createtime< to_date(?, 'yyyy-mm-dd')+1 ");
            
            //filterField.put(CodeBook.ORDER_BY_HQL_ID,"createtime desc");
            filterField.put(CodeBook.ORDER_BY_HQL_ID,"cpBegtime desc");
            
            filterField.put("begTime"," cpBegTime>= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
            filterField.put("endTime"," cpBegTime<= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
            
            filterField.put("self", "  ( state <>'6' or creater=? )  ");//返回自己所有的申请及别人提交流程的申请
            
            
            //接口使用参数
            filterField.put("currentdatetime", " cpBegTime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
            filterField.put("docpTimeBegin", " cpBegTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            filterField.put("docpTimeEnd", " cpBegTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            filterField.put("mipSelf", "  ( state ='2' or creater=? )  ");//返回自己所有的申请及别人已通过的申请
            filterField.put("NP_bizstate", "  bizstate <>'F' ");
		}
		return filterField;
	} 
	
	/**
     * 根据状态获取时间字段
     * @param state
     * @param begTag
     * @param endTag
     * @return
     */
    private Void getTimeTagByState(String state, String begTag,String endTag){
        if("1".equals(state)){//预计
            begTag="planBegTime";
            endTag="planEndTime";
        }else  if("2".equals(state)){//调配
            begTag="doBegTime";
            endTag="doEndTime";
        }else  if("3".equals(state)){//实用
            begTag="begTime";
            endTag="endTime";
        }
        return null;
        
    }
    
    
    public List<OaCarApply> getApplylist(String djId, String cardjid,
            Date beg, Date end,String state) {
        //根据state 判断时间字段 默认预计时间
        String begTag="cpBegTime";
        String endTag="cpEndTime";
        
       // getTimeTagByState(state,begTag, endTag);
        
        String sHql=" from OaCarApply t where 1=1 ";
        
        if(StringUtils.hasText(djId) ){
            sHql+=" and  djId !=" +HQLUtils.buildHqlStringForSQL(djId);//验证时排除自身djid
        }
        
        if(StringUtils.hasText(cardjid) ){
            sHql+=" and  cardjid =" +HQLUtils.buildHqlStringForSQL(cardjid);
        }
        if(StringUtils.hasText(state) ){
            sHql+=" and  state =" +HQLUtils.buildHqlStringForSQL(state);
        }
        //时间限制 HQLUtils.buildHqlDateStringForOracle(beg)
        if(null != state && null != beg && null != end){
            sHql+=" and ( " +
//                    "(" +
                    "("+begTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
                    "("+endTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
                    "("+begTag+" <= "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and  "+endTag+" >= "+HQLUtils.buildHqlDateTimeStringForOracle(end)+")" +
//                    " )"+
                    ")" ;
        }
        List<OaCarApply> oaCarApplyList=this.listObjects(sHql);
        
        
        return oaCarApplyList;
    }
    
}
