package com.centit.oa.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;

public class OaMeetApplyDao extends BaseDaoImpl<OaMeetApply>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaMeetApplyDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.LIKE_HQL_ID);


			filterField.put("applyNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("depno" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("title" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingNo" , CodeBook.EQUAL_HQL_ID);

			filterField.put("planBegTime", " planBegTime >= to_date(?,'yyyy-MM-dd')");

            filterField.put("planEndTime", " planEndTime <= to_date(?,'yyyy-MM-dd')");
            filterField.put("meetingName"," oaMeetinfo.meetingName like ? ");
			filterField.put("telephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingPersions" , CodeBook.LIKE_HQL_ID);

			filterField.put("outerPersions" , CodeBook.LIKE_HQL_ID);

			filterField.put("meetingPersionsNum" , CodeBook.LIKE_HQL_ID);

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

//			filterField.put("begTime" , CodeBook.LIKE_HQL_ID);
//
//			filterField.put("endTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("todoremark" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowcode" , CodeBook.LIKE_HQL_ID);
			filterField.put("otherItem" , CodeBook.LIKE_HQL_ID);
			
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
             
             filterField.put("NP_bizstate", "  bizstate <>'F' ");
             
             //filterField.put(CodeBook.ORDER_BY_HQL_ID, " planEndTime desc");
             
             filterField.put("meetType" , CodeBook.LIKE_HQL_ID);
             filterField.put("meeting_rang" , CodeBook.LIKE_HQL_ID);
             filterField.put("units" , CodeBook.LIKE_HQL_ID);
             filterField.put("attendingUnits" , CodeBook.LIKE_HQL_ID);
             filterField.put("alignment_time" , CodeBook.LIKE_HQL_ID);
             filterField.put("use_venue" , CodeBook.LIKE_HQL_ID);
             filterField.put("otheruse_venue" , CodeBook.LIKE_HQL_ID);
             filterField.put("isSLmeeting" , CodeBook.LIKE_HQL_ID);
             
             filterField.put("currentdatetime", " cpBegTime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
             filterField.put("mipSelf", "  ( state ='2' or t.creater=? )  ");
            
             filterField.put("cpBegTime",  " cpBegTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             filterField.put("cpEndTime",  " cpBegTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')+1");
             filterField.put(CodeBook.ORDER_BY_HQL_ID, " cpEndTime desc");
             
             filterField.put("docpTimeBegin", " cpBegTime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
             filterField.put("docpTimeEnd", " cpBegTime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
		
             
             filterField.put("self", "  ( t.state <>'6' or t.creater=? )  ");//返回自己所有的申请及别人提交流程的申请
             filterField.put("myself", "  t.creater=?   ");//返回自己所有的申请及别人提交流程的申请
             
             filterField.put("tmeetingNo", " t.meeting_no like ? ");
             filterField.put("tapplyNo", " t.apply_No like trim(?) ");
             filterField.put("napplyNo", " t.apply_No like trim(?) ");
             
             filterField.put("tstate",  " t.state like ?");
             filterField.put("tdepno",  " t.depno like trim(?)");
             filterField.put("NP_tbizstate", "  t.bizstate <>'F' ");
             
		}
		return filterField;
	}

    public List<OaMeetApply> getgetDjidlist() {
        String shql = " from OaMeetApply  where bizstate='C'  order by createtime ";
        Object[] objects = new Object[]{};
        List<OaMeetApply> list = this.listObjects(shql, objects);
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
    
    
    public List<OaMeetApply> getApplylist(String djId, String meetingNo,
            Date beg, Date end,String state) {
        //根据state 判断时间字段 默认预计时间
        //String begTag="planBegTime";
        //String endTag="planEndTime";
        String begTag="cpBegTime";
        String endTag="cpEndTime";
        
        getTimeTagByState(state,begTag, endTag);
        
        String sHql=" from OaMeetApply t where 1=1 ";
        
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
            		"("+begTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
            		"("+endTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
            		"("+begTag+" <= "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and  "+endTag+" >= "+HQLUtils.buildHqlDateTimeStringForOracle(end)+")" +
            		" )"+
            		")" ;
            		
            
        }
        List<OaMeetApply> oaMeetApplyList=this.listObjects(sHql);
        
        return oaMeetApplyList;
    }
    public  String getStr(String meetingNo,Date beg,Date end,String begTag ,String  endTag){
       String sql=" and( " +
               "(" +
               "("+begTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
               "("+endTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
               "("+begTag+" <= "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and  "+endTag+" >= "+HQLUtils.buildHqlDateTimeStringForOracle(end)+")" +
               " )"+
               ")" ;
       return sql; 
    }
    public  String applyssql(String usercode ,String meetingNo,Date beg,Date end){
        String sHql=" from OaMeetApply t where 1=1 and meetingNo="+HQLUtils.buildHqlStringForSQL(meetingNo);
        String sql1="";
        String sql2= "";
        String sql3="";
        if(beg!=null&&end==null){
            sql1=getStr( meetingNo, beg, end, "plan_beg_time"  ,  "plan_end_time");
        }else if(end!=null&&beg==null){
            sql2=getStr( meetingNo, beg, end, "do_beg_time"  ,  "do_end_time");
        }else if(end==null&&beg==null){
            sql3 =sHql+" and doBegTime is not null and creater="+HQLUtils.buildHqlStringForSQL(usercode);
        }
         if("".equals(sql3))  
            sql3=sHql+"(t.creater="+usercode+" and ("+sql1+" or "+sql2+"))" +
        		"or(t.creater<>"+usercode+" and "+sql2+"))" ;
        return sql3;
    }
    
    public List<OaMeetApply> applys(String usercode,String meetingNo,Date beg,Date end){
        String sHql = applyssql(usercode,meetingNo,beg,end);
        List<OaMeetApply> oaMeetApplyList=this.listObjects(sHql);
        return oaMeetApplyList;
    }
    /**
     * 某个时间点 会议室是否空闲
     * state=2 申请通过
     * @param meetingNo
     * @param currentdate
     * @param enddate
     * @return
     */
    public OaMeetApply getApplylistIsFree(String meetingNo,Date currentdate,Date enddate){
        String sHql=" from OaMeetApply t where 1=1 and meetingNo="+HQLUtils.buildHqlStringForSQL(meetingNo);
        
        sHql+=" and (doBegTime <= "+HQLUtils.buildHqlDateTimeStringForOracle(currentdate)+" and  doEndTime>= "+HQLUtils.buildHqlDateTimeStringForOracle(currentdate)+")" ;
        
        if(enddate!=null){
            sHql+=" or (doBegTime <= "+HQLUtils.buildHqlDateTimeStringForOracle(enddate)+" and  doEndTime>= "+HQLUtils.buildHqlDateTimeStringForOracle(enddate)+")";
        }
        sHql+=" and  state='2' ";
        List<OaMeetApply> oaMeetApplyList=this.listObjects(sHql);
        if(oaMeetApplyList!=null&&oaMeetApplyList.size()>0){
            return oaMeetApplyList.get(0);
        }
        return null;
    }

    public List<OaMeetApply> getApplylistNew(String djId, String meetingNo,
            Date beg, Date end, String state) {
        String begTag="doBegTime";
        String endTag="doEndTime";
        String sHql=" from OaMeetApply t where 1=1 ";
        
        if(StringUtils.hasText(djId) ){
            sHql+=" and  djId !=" +HQLUtils.buildHqlStringForSQL(djId);//验证时排除自身djid
        }
        
        if(StringUtils.hasText(meetingNo) ){
            sHql+=" and  meetingNo =" +HQLUtils.buildHqlStringForSQL(meetingNo);
        }
        if(StringUtils.hasText(state) ){
            sHql+=" and  state =" +HQLUtils.buildHqlStringForSQL(state);
        } 
        if(null != state && null != beg && null != end){
            sHql+=" and ( " +
                    "(" +
                    "("+begTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
                    "("+endTag+"  between "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and "+HQLUtils.buildHqlDateTimeStringForOracle(end)+") or " +
                    "("+begTag+" <= "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and  "+endTag+" >= "+HQLUtils.buildHqlDateTimeStringForOracle(end)+")" +
                    " )"+
                    ")" ;
                    
        }
        List<OaMeetApply> oaMeetApplyList=this.listObjects(sHql);
        
        return oaMeetApplyList;
    }

    public List<OaMeetApply> getFreeApplylist(String meetingNo,
            Date currentdate, Date enddate) {
        List<OaMeetApply>  freeApplyList=new ArrayList<OaMeetApply>();
        List<OaMeetApply> oaMeetApplyList=new ArrayList<OaMeetApply>();
        Boolean isfree=null== getApplylistIsFree(meetingNo,currentdate,enddate)? true:false;
        if(isfree){ //当前时间点空闲，下一个可申请时间范围=当前时间后一条数据起始时间-当前时间前一条数据结束时间
           
            //前一条数据
            String berfore=" from OaMeetApply t where t.meetingNo =" +HQLUtils.buildHqlStringForSQL(meetingNo)+" and  t.doEndTime <"+HQLUtils.buildHqlDateTimeStringForOracle(currentdate)+" and  state='2' and rownum <2   order by t.doEndTime desc ";
            oaMeetApplyList=this.listObjects(berfore);
            if(null!=oaMeetApplyList)
            freeApplyList.addAll(oaMeetApplyList);
            //后一条数据
            String after=" from OaMeetApply t where t.meetingNo =" +HQLUtils.buildHqlStringForSQL(meetingNo)+" and  t.doBegTime> "+HQLUtils.buildHqlDateTimeStringForOracle(currentdate)+" and  state='2' and rownum <2   order by t.doBegTime  ";
            oaMeetApplyList=this.listObjects(after);
            if(null!=oaMeetApplyList)
            freeApplyList.addAll(oaMeetApplyList);
        }else{//当前时间点不空闲，下一个可申请时间范围=当前时间后俩条数据的时间间隔
            //后俩条数据
            String after=" from OaMeetApply t where t.meetingNo =" +HQLUtils.buildHqlStringForSQL(meetingNo)+" and  t.doEndTime> "+HQLUtils.buildHqlDateTimeStringForOracle(currentdate)+"  and  state='2' and rownum <3   order by t.doEndTime ";
            oaMeetApplyList=this.listObjects(after);
            if(null!=oaMeetApplyList)
            freeApplyList.addAll(oaMeetApplyList);
        }
        return freeApplyList;
    }

    public List<OaMeetApply> getApplylistV2(Date beg, Date end,String state) {
        //根据state 判断时间字段 默认预计时间
        //String begTag="planBegTime";
        //String endTag="planEndTime";
        String begTag="cpBegTime";
        String endTag="cpEndTime";
        
        getTimeTagByState(state,begTag, endTag);
        
        String sHql=" from OaMeetApply t where 1=1 ";
        
        if(StringUtils.hasText(state) ){
            sHql+=" and  state in (" +state+")";
        }
        //时间限制 HQLUtils.buildHqlDateStringForOracle(beg)
        if(null != state && null != beg && null != end){
            sHql+=" and ( " +
                    "(" +
                    "("+begTag+" >= "+HQLUtils.buildHqlDateTimeStringForOracle(beg)+" and  "+endTag+" <= "+HQLUtils.buildHqlDateTimeStringForOracle(end)+")" +
                    " )"+
                    ")" ;
                    
            
        }
        List<OaMeetApply> oaMeetApplyList=this.listObjects(sHql);
        
        return oaMeetApplyList;
    }
    
    @SuppressWarnings("unchecked")
    public List<OaMeetApply> getMeetApplyList(Map<String, Object> filterMap, PageDesc pageDesc){
        String shql = " from OA_MEET_APPLY t join oa_meetinfo f on t.meeting_no=f.djid where 1=1 ";
        boolean flag=filterMap.get("tapplyNo")!=null;
        String applyNo =flag?(String)filterMap.get("tapplyNo"):"";
        if(flag){//表示有值
            filterMap.remove("tapplyNo");
            filterMap.put("napplyNo", "%"+applyNo+"%");
        }
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        
        String orderBy=" order by cpEndTime desc";//默认排序
        
        if(hql.getHql()!=null){
            if(hql.getHql().indexOf("order by ")>0){
//                hql.setHql(hql.getHql().replace("order by  cpEndTime desc", ""));
                orderBy="";
            }
        }
        //修正SQL语句
        if(hql.getHql().contains("meetingNo")){
            hql.setHql(hql.getHql().replaceAll("meetingNo", "meeting_No"));
        }
       
        String hql1 = "select distinct t.djId,t.state,to_char(t.cpBegTime,'yyyy-MM-dd hh24:mi:ss') cpBegTime,to_char(t.cpEndTime,'yyyy-MM-dd hh24:mi:ss') cpEndTime,f.meeting_name,t.title," +
        		"t.attendingLeaderNames,t.depno,t.apply_No,t.biztype,t.creater  ,t.isBasicUnit,t.isStopCar,t.MEETING_PERSIONS_NUM ,t.MEETING_PERSIONS    " + hql.getHql()+orderBy; 
        
        String hql2 = "select count(distinct t.djId )  " + hql.getHql() ;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<OaMeetApply>  oaMeetapplyList = new ArrayList<OaMeetApply>();
        List<Object[]>  l = getHibernateTemplate().executeFind(
                new SQLQueryCallBack(hql1.toString(),hql.getParams(),startPos,maxSize));
        try {
           // l=getHibernateTemplate().executeFind(new SQLQueryCallBack(hql1, hql.getParams(), startPos, maxSize, OaMeetApply.class));
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        SimpleDateFormat fromat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if(l!=null && l.size()>0){
                for(Object[] o:l){
                    OaMeetApply m=new OaMeetApply();
                    m.setDjId(o[0]!=null?o[0].toString():null);
                    m.setState(o[1]!=null?o[1].toString():null);
                    m.setCpBegTime(o[2]!=null?fromat.parse(o[2].toString()):null);
                    m.setCpEndTime(o[3]!=null?fromat.parse(o[3].toString()):null);
                    m.setMeetingName(o[4]!=null?o[4].toString():null);
                    m.setTitle(o[5]!=null?o[5].toString():null);
                    m.setAttendingLeaderNames(o[6]!=null?o[6].toString():null);
                    m.setDepno(o[7]!=null?o[7].toString():null);
                    m.setApplyNo(o[8]!=null?o[8].toString():null);
                    m.setBiztype(o[9]!=null?o[9].toString():null);
                    m.setCreater(o[10]!=null?o[10].toString():null);
                    m.setIsBasicUnit(o[11]!=null?o[11].toString():null);
                    m.setIsStopCar(o[12]!=null?o[12].toString():null);
                    m.setMeetingPersionsNum(o[13]!=null?Integer.valueOf(o[13].toString()):0L);
                    m.setMeetingPersions(o[14]!=null?o[14].toString():null);
                    oaMeetapplyList.add(m);
                }
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(flag){//表示有值
            filterMap.remove("napplyNo");
            filterMap.put("tapplyNo", applyNo);
        }
        return oaMeetapplyList;
    }

@SuppressWarnings("unchecked")
public List<OaMeetinfo> getMeetInfoList(){
    StringBuffer sb = new StringBuffer();
    sb.append("select djid,meeting_name from oa_meetinfo where isuse='T' order by orderno asc");
    List<Object[]> l = (List<Object[]>) findObjectsBySql(sb.toString());
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
}
