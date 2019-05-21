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

import com.centit.oa.po.OaMeetroomApply;

public class OaMeetroomApplyDao extends BaseDaoImpl<OaMeetroomApply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetroomApplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("applyNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("planBegTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("planEndTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("reqRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("doTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("doBegTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("doEndTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.LIKE_HQL_ID);

			filterField.put("doDepno" , CodeBook.LIKE_HQL_ID);

			filterField.put("doCreater" , CodeBook.LIKE_HQL_ID);

			filterField.put("doRemark" , CodeBook.LIKE_HQL_ID);

			filterField.put("isUse" , CodeBook.LIKE_HQL_ID);

			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("todoremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowinstid" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);
			 filterField.put("begTime", " createtime>= to_date(?, 'yyyy-mm-dd') ");
             
             filterField.put("endTime", " createtime<= to_date(?, 'yyyy-mm-dd')+1 ");

        
             //申请时间
             filterField.put("planBeginTimeBegin", " planBegTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("planBeginTimeEnd", " planBegTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("planEndTimeBegin", " planEndTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("planEndTimeEnd", " planEndTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             //调配时间
             filterField.put("doBeginTimeBegin", " doBegTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("doBeginTimeEnd", " doBegTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("doEndTimeBegin", " doEndTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("doEndTimeEnd", " doEndTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             filterField.put("otherDjId", "  djid != ? ");
             //某一会议室的历史安排时间查看条件
             filterField.put("NP_", "  state in ('2','3')");
             
             
             filterField.put("invalid", " doEndTime >=to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             
             
             filterField.put(CodeBook.ORDER_BY_HQL_ID, "createtime desc");
		}
		return filterField;
	}


    public List<OaMeetroomApply> getgetDjidlist() {
        String shql = " from OaMeetroomApply  where bizstate='C'  order by createtime ";
        Object[] objects = new Object[]{};
        List<OaMeetroomApply> list = this.listObjects(shql, objects);
        return list;
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
    
    
    public List<OaMeetroomApply> getApplylist(String djId, String meetingNo,
            Date beg, Date end,String state) {
        //根据state 判断时间字段 默认预计时间
        String begTag="planBegTime";
        String endTag="planEndTime";
        
        getTimeTagByState(state,begTag, endTag);
        
        String sHql=" from OaMeetroomApply t where 1=1 ";
        
        if(StringUtils.hasText(djId) ){
            sHql+=" and  djId !=" +HQLUtils.buildHqlStringForSQL(djId);//验证时排除自身djid
        }
        
        if(StringUtils.hasText(meetingNo) ){
            sHql+=" and  meetingNo =" +HQLUtils.buildHqlStringForSQL(meetingNo);
        }
        if(StringUtils.hasText(state) ){
            sHql+=" and  state =" +HQLUtils.buildHqlStringForSQL(state);
        }
        //时间限制 HQLUtils.buildHqlDateStringForOracle(beg)
        if(null != state && null != beg && null != end){
            sHql+=" and ( " +
                    "(" +
                    "("+begTag+"  between "+HQLUtils.buildHqlDateStringForOracle(beg)+" and "+HQLUtils.buildHqlDateStringForOracle(end)+") or " +
                    "("+endTag+"  between "+HQLUtils.buildHqlDateStringForOracle(beg)+" and "+HQLUtils.buildHqlDateStringForOracle(end)+") or " +
                    "("+begTag+" <= "+HQLUtils.buildHqlDateStringForOracle(beg)+" and  "+endTag+" >= "+HQLUtils.buildHqlDateStringForOracle(end)+")" +
                    " )"+
                    ")" ;
                    
            
        }
        List<OaMeetroomApply> oaMeetApplyList=this.listObjects(sHql);
        
        
        return oaMeetApplyList;
    }
}
