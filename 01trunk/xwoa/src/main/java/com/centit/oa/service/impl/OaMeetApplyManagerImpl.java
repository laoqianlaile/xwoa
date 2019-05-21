package com.centit.oa.service.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaMeetApplyDao;
import com.centit.oa.dao.VMeetApplyListDao;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.VOaMeetApplyList;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.util.DateUtil;

public class OaMeetApplyManagerImpl extends BaseEntityManagerImpl<OaMeetApply>
	implements OaMeetApplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetApplyManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaMeetApplyDao oaMeetApplyDao ;
	private UserInfoDao sysUserDao;

    public void setOaMeetApplyDao(OaMeetApplyDao baseDao)
	{
		this.oaMeetApplyDao = baseDao;
		setBaseDao(this.oaMeetApplyDao);
	}
	
	private VMeetApplyListDao vmeetApplyListDao;
    public void setVmeetApplyListDao(VMeetApplyListDao vmeetApplyListDao) {
        this.vmeetApplyListDao = vmeetApplyListDao;
    }
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "HYSQ"+oaMeetApplyDao.getNextKeyBySequence("s_OaMeetApply", 12);
    }
    @Override
    public List<OaMeetApply> getDjidlist() {
        // TODO Auto-generated method stub
        return  this.oaMeetApplyDao.getgetDjidlist();
    }
    /**
     *  已分配但未使用的state=2 会议室申请列表"
     */
    public List<OaMeetApply> getApplylistNew(String djId, String meetingNo,
            Date beg, Date end,String state) {
        List<OaMeetApply>  applylist=new ArrayList<OaMeetApply>();
        List<OaMeetApply>  templist=oaMeetApplyDao.getApplylistNew( djId,  meetingNo,
                beg,  end,  state);
        applylist.addAll(templist);
        return applylist;
    }
    /**
     * 会议室申请列表 states为希望获得的状态，eg"1,2"
     */
    @Override
    public List<OaMeetApply> getApplylist(String djId, String meetingNo,
            Date beg, Date end,String states) {
        List<OaMeetApply>  applylist=new ArrayList<OaMeetApply>();
        if (StringUtils.isNotBlank(states)) {
            String[] state = states.split(",");
            
            for (String s : state) {
                List<OaMeetApply>  templist=oaMeetApplyDao.getApplylist( djId,  meetingNo,
                        beg,  end,  s);
                applylist.addAll(templist);
                }
            }
        return applylist;
    }
    @Override
    public List<OaMeetApply> getOaMeetinfoList(Date beginTime, Date endTime,
            String djid) {
        String sql = "from OaMeetApply where  1=1  and ("
                + "( planBegTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss')"
                + " and planBegTime <=to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                 + " or ( planEndTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and planEndTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                 + " or ( planBegTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and planEndTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                  + " or ( begTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and begTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                + " or ( endTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and endTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                + "or ( begTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and  endTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss'))) "
                + " and  meetingNo= '"
                + djid
                + "' and State!='4'   order by endTime , planBegTime ";
        return oaMeetApplyDao.listObjects(sql);
    }
    @Override
    public OaMeetApply listObjectsByState(String djId) {
        // TODO Auto-generated method stub
        String sql = "from OaMeetApply where  1=1  and state='2' and djid="+HQLUtils.buildHqlStringForSQL(djId);
        List<OaMeetApply> applys = oaMeetApplyDao.listObjects(sql);
        if(applys!=null && applys.size()>0){
            return applys.get(0);
        }
        return null;
    }
    
    @Override
    public OaMeetApply getApplylistIsFree(String meetingNo,Date currentdate,Date enddate) {
        // TODO Auto-generated method stub
        return oaMeetApplyDao.getApplylistIsFree(meetingNo,currentdate, enddate);
    }
    @Override
    public List<OaMeetApply> applys(String usercode, String meetingNo,
            Date beg, Date end) {
        // TODO Auto-generated method stub
        return oaMeetApplyDao.applys(usercode, meetingNo,
                beg, end);
    }
    @Override
    public List<VOaMeetApplyList> listMeetingInvolvedIn(
            Map<String, Object> filtermap, PageDesc pageDesc) {
        return vmeetApplyListDao.listMeetApply(filtermap, pageDesc);
    }
    /**
     * 获取指定部门下的人员
     * @param unitcodes
     * @return
     */
    public List<FUserinfo>  getUsersByUnits(String unitcodes) {
        return sysUserDao.getUsersByUnits(unitcodes);
        
    }
    /**
     * 获取指定部门下指定岗位的人员
     * @param unitcodes
     * @return
     */
    public List<FUserinfo>  getSysUsersByRoleAndUnit(String unitcodes,String userstation) {
        return sysUserDao.getSysUsersByRoleAndUnit(unitcodes,userstation);
        
    }
    
    
    public void setSysUserDao(UserInfoDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }
    @Override
    public List<OaMeetApply> getFreeApplylist(String meetingNo,
            Date currentdate, Date enddate) {
       
        return oaMeetApplyDao.getFreeApplylist(meetingNo,currentdate, enddate);
    }
    @Override
    public List<OaMeetApply> getApplylistV2(Date beg, Date end, String state) {
        return oaMeetApplyDao.getApplylistV2(beg, end, state);
    }
    @Override
    public List<OaMeetApply> getMeetApplyList(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        return oaMeetApplyDao.getMeetApplyList(filterMap, pageDesc);
    }

    
    @Override
    public String getBoardroomJsonArranged(String strBeginDate,
            String strEndDate,String state) {
        Date beginDate = DateUtil.dateStr2Date(strBeginDate);
        Date endDate = DateUtil.dateStr2Date(strEndDate);
        
        //先查会议室，以会议室名称作为纵向指标口径
        List<OaMeetinfo> oaMeetInfos =  oaMeetApplyDao.getMeetInfoList();
        //以开始日期到结束日期这周数据作为列头   
        List<String> timeList = getTimeEnum(beginDate, endDate);
        //内容数据
        List<OaMeetApply> oaMeetApplys = oaMeetApplyDao.getApplylistV2(beginDate, endDate, state);
        
        JSONArray ja = new JSONArray();
        
        if(oaMeetInfos!=null && !oaMeetInfos.isEmpty()){
            for(OaMeetinfo oaMeetinfo : oaMeetInfos){
                JSONObject jo = new JSONObject();
                jo.put("roomId", oaMeetinfo.getDjid());
                jo.put("roomName", oaMeetinfo.getMeetingName());
                
                if(timeList!=null && !timeList.isEmpty()){
                    for(int i=0;i<timeList.size();i++){
                        List<String> ranges = getTimeRange(oaMeetinfo.getDjid(), timeList.get(i), oaMeetApplys);
                        jo.put("weekDay-"+(i+1), ranges);
                    }
                }
                ja.add(jo);
            }
        }
      
        return ja.toJSONString();
    }
    
    private List<String> getTimeRange(String roomId,String time,List<OaMeetApply> oaMeetApplys){
        List<String> ranges = new ArrayList<String>();
        if(oaMeetApplys!=null && !oaMeetApplys.isEmpty()){
            for(OaMeetApply oaMeetApply : oaMeetApplys){
                String cpBeginTime =  DateUtil.date2String(oaMeetApply.getCpBegTime(), "yyyy-MM-dd HH:mm");
                String cpEndTime = DateUtil.date2String(oaMeetApply.getCpEndTime(), "yyyy-MM-dd HH:mm");
                if(roomId.equals(oaMeetApply.getMeetingNo())){
                    String shortCpBeginTime = cpBeginTime.substring(0, 10);
                    String shortCpEndTime = cpEndTime.substring(0,10);
                    
                    if(time.equals(shortCpBeginTime)){
                        //如果会议使用时间是同一天
                        if(shortCpBeginTime.equals(shortCpEndTime)){
                            String start = cpBeginTime.substring(11);
                            String end = cpEndTime.substring(11);
                            ranges.add(start+"~"+end+","+oaMeetApply.getDjId()+","+oaMeetApply.getState());
                        }else{
                            //如果不是同一天
                            String start = cpBeginTime.replace("-", ".");
                            String end = cpEndTime.replace("-", ".");
                            ranges.add(start+"~"+end+","+oaMeetApply.getDjId()+","+oaMeetApply.getState());
                        }
                    }
                }
            }
        }
        return ranges;
    }
    

    /**
     * 获取日期枚举
     * @param beginDate
     * @param endDate
     * @return
     */
    private List<String> getTimeEnum(Date beginDate,Date endDate){
        List<String> timeList = new ArrayList<String>();
        Date nextDate = beginDate;
        do{
            String strNextDate = DateUtil.date2String(nextDate, "yyyy-MM-dd");
            timeList.add(strNextDate);
            strNextDate = DateUtil.AddDays(strNextDate, 1);
            nextDate = DateUtil.dateStr2Date(strNextDate);
        }while(!DateUtil.isAfter(endDate,nextDate));
        return timeList;
    }

}

