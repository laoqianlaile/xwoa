package com.centit.oa.service;



import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.VOaMeetApplyList;
import com.centit.sys.po.FUserinfo;

public interface OaMeetApplyManager extends BaseEntityManager<OaMeetApply> 
{

   public String getNextKey();

   public List<OaMeetApply> getDjidlist();
   
   /**
    * 一段时间内的申请记录，调配记录
 * @param meetingNO 
 * @param djId 
 * @param end 
 * @param beg 
    * @return
    */
   public List<OaMeetApply> getApplylist(String djId, String meetingNO, Date beg, Date end,String state);
   
   public List<OaMeetApply> getOaMeetinfoList(Date beginTime, Date endTime,
        String djid);
   /**
    * 申请已调配记录state=2
 * @param meetingNO 
 * @param djId 
 * @param end 
 * @param beg 
    * @return
    */
   public List<OaMeetApply> getApplylistNew(String djId, String meetingNO, Date beg, Date end,String state);

   public OaMeetApply listObjectsByState(String djId);
   /**
    * 查询分配的会议室某个时间点 会议室是否空闲
    * @param meetingNo
    * @return
    */
   public OaMeetApply getApplylistIsFree(String meetingNo,Date currentdate,Date enddate);
   /**
    * 查询摸某个时间点附件俩条预约通过记录--推算最近可预约时间
    * @param meetingNo
    * @return
    */
   public List<OaMeetApply> getFreeApplylist(String meetingNo,Date currentdate,Date enddate);
   
   
/**
 * 查询别人申请成功的记录，自己创建所有的申请记录。
 * @param usercode
 * @param meetingNo
 * @param beg
 * @param end
 * @return
 */
   public List<OaMeetApply> applys(String usercode,String meetingNo,Date beg,Date end);
   
   /**
    * 查出自己参与的会议室办件
    * @param filtermap
    * @param pageDesc
    * @return
    */
   public List<VOaMeetApplyList> listMeetingInvolvedIn(Map<String,Object> filtermap,PageDesc pageDesc); 


   /**
    * 获取指定部门下的人员
    * @param unitcodes
    * @return
    */
   public List<FUserinfo>  getUsersByUnits(String unitcodes);
   
   /**
    * 获取指定部门下指定岗位的人员
    * @param unitcodes
    * @return
    */
   public List<FUserinfo>  getSysUsersByRoleAndUnit(String unitcodes,String userstation);

   /**
    * 一段时间内的申请记录，调配记录
 * @param end 
 * @param beg 
    * @return
    */
   public List<OaMeetApply> getApplylistV2(Date beg, Date end,String state);
   /**
    * 查询会议申请
    * @param filterMap
    * @param pageDesc
    * @return
    */
   public List<OaMeetApply> getMeetApplyList(Map<String, Object> filterMap, PageDesc pageDesc);

   
   /**
    * 获取已安排的会议室情况
    * @param strBeginDate
    * @param strEndDate
    * @return
    */
   public String getBoardroomJsonArranged(String strBeginDate,String strEndDate,String state);


}
