package com.centit.app.action;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import net.sf.json.JSONObject;

import com.centit.app.po.OaWorkDay;
import com.centit.app.service.CalendarManager;
import com.centit.app.service.IToDoMatterData;
import com.centit.app.service.OaWorkDayManager;
import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
import com.centit.core.utils.WorkTimeSpan;
import com.centit.oa.service.OaLeaveOverTimeManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.app.service.IToDoMatter;
import com.centit.sys.service.WorkCalendar;

public class CalendarAction extends BaseEntityExtremeAction<OaWorkDay> {
    
    private static final long serialVersionUID = 1L;
    private OaWorkDayManager oaWorkDayManager;
    private WorkCalendar workCalendar; 
    private JSONObject  result;
    
    private CalendarManager calendarManager;
    
    private OaLeaveOverTimeManager oaLeaveOverTimeManager;
    
    public void setCalendarManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }
    
    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public void setOaWorkDayManager(OaWorkDayManager oaWorkDayManager) {
        this.oaWorkDayManager = oaWorkDayManager;
        super.setBaseEntityManager(oaWorkDayManager);
    }
    
    public void setOaLeaveOverTimeManager(
            OaLeaveOverTimeManager oaLeaveOverTimeManager) {
        this.oaLeaveOverTimeManager = oaLeaveOverTimeManager;
    }

    public JSONObject getResult() {
        return result;
    }
    public void setResult(JSONObject result) {
        this.result = result;
    }
//    public String view(){
//              
//        return "calendar";
//    }
    public String getUnusualDays(){ 
        return "unusual"; 
    }
    
    public String savedate(){
      try{       
         OaWorkDay dbobject= oaWorkDayManager.getObjectById(object.getWorkday());
        if(dbobject!=null){
         if(object.getDaytype().equals("C")){        //当把这个日期设置为正常时 把这个日期从数据库里面删除
             oaWorkDayManager.deleteObject(dbobject);
         }
         else{
         oaWorkDayManager.copyObjectNotNullProperty(dbobject, object);
         object=dbobject;  
         }
       }
       if(!object.getDaytype().equals("C")){
       oaWorkDayManager.saveObject(object);    
       
      // oaLeaveOverTimeManager.updateAttendDetailsByCalendar(object);
       }
       Map<String,String> map= new HashMap<String, String>();
       map.put("DayType",object.getDaytype());
       result=JSONObject.fromObject(map);
       
    }catch (Exception e) {
        e.printStackTrace();
   }
        return "save";
    }
    
    private Date  beginDate;
    private Date  endDate;
    
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @SuppressWarnings("deprecation")
    public String getDayArray(){
       try{
        List<OaWorkDay> list=oaWorkDayManager.getListByDay(beginDate, endDate);
        if(list!=null){
      //  Map<String,String> map1=new HashMap<String, String>();
       // Map<String,String> map2=new HashMap<String, String>();
        Map<String,String> map =new HashMap<String,String>(); 
        SimpleDateFormat df=new SimpleDateFormat("MMdd");
        for(OaWorkDay day : list){
            if(day.getDaytype().equals("A")){ 
               String d=df.format(day.getWorkday());
                map.put(df.format(day.getWorkday()),"A");               
            }
         
            else{
                map.put(df.format(day.getWorkday()),"B");
            }              
        }
       // map.put("A", map1);
      //  map.put("B", map2);
        result=JSONObject.fromObject(map); 
        System.out.print(result);
        }
        else{
        result=null;   
        }
        return "jsonArray";
       }catch (Exception e) {
       e.printStackTrace();
       return ERROR;
    }
    }
    
    @SuppressWarnings("deprecation")
    public String test() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date s=sdf.parse("2012-07-14 09:00:25");
        Date s1=sdf.parse("2012-07-15 15:20:35");
        WorkTimeSpan workTimeSpan= workCalendar.getWorkTime(s, s1);
        System.out.println(workTimeSpan.getTimeSpanDesc());
        
        
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
    public String view() {
        String instance = request.getParameter("instance");
        List<String> instances = Arrays.asList(instance.split(","));
        
        Map<String, IToDoMatter> toDoMatter = calendarManager.getToDoMatter();
        
        Map<String, Boolean> inst = new HashMap<String, Boolean>();
        
        Map<String, String> values = new LinkedHashMap<String, String>();
        for (Entry<String, IToDoMatter> doMatter : toDoMatter.entrySet()) {
            values.put(doMatter.getKey(), doMatter.getValue().getTaskCatalog().getCatalogName());
        }
        
        Collection<String> intersection = CollectionUtils.intersection(toDoMatter.keySet(), instances);
        for (String string : intersection) {
            inst.put(string, true);
        }
        
        request.setAttribute("values", values);
        request.setAttribute("inst", inst);
    
        String day = WebUtils.findParameterValue(request, "day");
        if(!StringUtils.hasText(day)) {
            day = DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd");
        }
        
        String tasktype = WebUtils.findParameterValue(request, "tasktype");
        
        String usercode = ((FUserinfo)getLoginUser()).getUsercode();
        
        Date start = DatetimeOpt.convertStringToDate(day + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DatetimeOpt.convertStringToDate(day + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        
        PageDesc pageDesc = makePageDesc();
        List<? extends IToDoMatterData> todoes = toDoMatter.get(instance).getUserTaskList(usercode, tasktype, start, end, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        
        request.setAttribute("todoes", todoes);
        return VIEW;
    }
    
    public String viewCalendar(){
        
        return "calendar";
    }

    @SuppressWarnings("unchecked")
    public void ajax() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }

        params.put("usercode", ((FUserDetail) super.getLoginUser()).getUsercode());
        params.put("contextPath", request.getContextPath());
        

        String resultJson = calendarManager.getTaskListData(params);

        if (StringUtils.hasText(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }
    
    /**
     * 获取所属人员信息
     * @throws IOException 
     */
    public void getOwner() throws IOException {
        String tasktype = WebUtils.findParameterValue(request, "tasktype");
        String instance = WebUtils.findParameterValue(request, "instance");
        
        String usercode = ((FUserinfo)getLoginUser()).getUsercode();
        Map<String, Map<String, String>> taskListOwner = calendarManager.getToDoMatter().get(instance).getTaskListOwner(usercode);
        if(null == taskListOwner) {
            
            
            return;
        }
        
        if(!taskListOwner.containsKey(tasktype)) {
            
            
            return;
        }
        
        String tasklistowner = JSONObject.fromObject(taskListOwner.get(tasktype)).toString();
        
        ServletActionContext.getResponse().getWriter().print(tasklistowner);
    }
  
}
