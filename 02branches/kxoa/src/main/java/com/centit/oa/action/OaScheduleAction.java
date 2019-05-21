package com.centit.oa.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.AddressBookRelectionId;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.OaScheduleResponse;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaScheduleManager;
import com.centit.oa.service.OaScheduleResponseManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateTimeUtil;
import com.centit.sys.util.DateUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class OaScheduleAction extends BaseEntityDwzAction<OaSchedule> {
    private static final Log log = LogFactory.getLog(OaScheduleAction.class);
    private static final long serialVersionUID = 1L;
    private OaScheduleManager oaScheduleMag;
    private AddressBookRelectionManager addressBookRelectionManager;
    
    private OaScheduleResponseManager oaScheduleResponseManager;//日程安排响应
    
    private InnermsgManager innermsgManager;//邮件通知
    
    private OaRemindInformationManager oaRemindInformationManager;//提醒
//    private 
    
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private String resShow="Edit"; //响应显示标识
    
   
    List<OaScheduleResponse> resList;
    List<OaScheduleResponse> resListJS;//1.响应接受列表
    List<OaScheduleResponse> resListZH;//2.响应暂缓列表
    List<OaScheduleResponse> resListJJ;//3.响应拒绝列表
    
    private String s_sehType;// 日程类型1:个人2:领导3:其他
    private String s_canAdd;// 日程类型1:个人2:领导3:其他

    private String userValue;// 人员编号
    private String leaderValue;// 参会领导编号
    
    String noticSign="R";//提醒标记（编辑为R 继续提醒）
    String emailSign="R";//邮件标记（编辑为R 继续发邮件提醒）
    
    private String curUrl;
    private OptJspInfo jspInfo;

    
    private Object json;

    private String show_type;

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public void setOaScheduleManager(OaScheduleManager basemgr) {
        oaScheduleMag = basemgr;
        this.setBaseEntityManager(oaScheduleMag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        try {
            Map<String, Object> filterMap = convertSearchColumn(request
                    .getParameterMap());
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
          //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("planBeginTimeBegin"))&&StringUtils.isBlank((String)filterMap.get("planBeginTimeEnd"))){
                filterMap.put("planBeginTimeBegin",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("planBeginTimeEnd", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            if("2".equals(s_sehType)){//领导日程安排
                objList = baseEntityManager.listObjects(filterMap, pageDesc);
            }else if ("1".equals(s_sehType)){ //为个人日程安排时，只查看自己的日程
                String usercode="";
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
//              filterMap.put("creater",user.getUsercode());//条件转为在后继方法中
                usercode=user.getUsercode();
                objList = oaScheduleMag.listObjects(filterMap, pageDesc, usercode);
            }
         
            
           if(null!=filterMap.get("leaders")){
                jsonDate((String)filterMap.get("leaders"));//页面自动匹配人员默认值（保留查询条件）
            }
           
            
            
            
            setbackSearchColumn(filterMap);
           // ajax();
            Map<String, String> values = new LinkedHashMap<String, String>();
            values.put("oaScheduleManager", "日程安排");

            request.setAttribute("values", values);
            
            
            totalRows = pageDesc.getTotalRows();
            return "CalendarView";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    @SuppressWarnings("unchecked")
    public String tabList() {
        try {
            Map<String, Object> filterMap = convertSearchColumn(request
                    .getParameterMap());
            
           // String usercode="";
        
            PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
          //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("planBeginTimeBegin"))&&StringUtils.isBlank((String)filterMap.get("planBeginTimeEnd"))){
                filterMap.put("planBeginTimeBegin",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("planBeginTimeEnd", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            if("2".equals(s_sehType)){
                objList = oaScheduleMag.listObjects(filterMap, pageDesc);
            }else if ("1".equals(s_sehType)){//为个人日程安排时，只查看与自己有关的日程
             //  FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
//             filterMap.put("creater",user.getUsercode());//条件转为在后继方法中
               // usercode=user.getUsercode();
                filterMap.put("creater", ((FUserDetail) getLoginUser()).getUsercode());
                objList = oaScheduleMag.listObjects(filterMap, pageDesc);
            }
            if(null!=objList&&objList.size()>0){
                for (int i=0;i<objList.size();i++){
                    objList.get(i).setDateTag(DateTimeUtil.smartCalcSpanDays(objList.get(i).getPlanBegTime(), new Date())) ;
                }
                
            }
            setbackSearchColumn(filterMap);
           // ajax();
            Map<String, String> values = new LinkedHashMap<String, String>();
            values.put("oaScheduleManager", "日程安排");
            request.setAttribute("values", values);
            totalRows = objList.size();
            //return "list";
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String leaderList() {
        this.list();
        return "LeaderCalendarView";
    }
    
    
    
    public String leaderTabList() {
        this.list();
        //return "leaderTabList";
        return "leaderTabListV2";
    }
    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        // 获取参会人员
        initUsers();
        getUser();//中间表与会人员
        getLeaders();//中间表参会领导
        super.edit();
        getReslist();//按响应类型获取响应列表
        return EDIT;
    }
    
    /**
     * 按响应类型获取响应列表
     */
    private void getReslist(){
        //编辑,查看时出现
        if(StringUtils.isNotBlank(object.getSchno())){
            //接受
            resListJS=oaScheduleResponseManager.getOaScheduleResponseListByresType(object.getSchno(),"1"); 
            //暂缓
            resListZH=oaScheduleResponseManager.getOaScheduleResponseListByresType(object.getSchno(),"2");  
            //拒绝
            resListJJ=oaScheduleResponseManager.getOaScheduleResponseListByresType(object.getSchno(),"3");  
        }
    }
    
    
    
    
    /**
     * 编辑，同时获取部门列表
     */
    public String view() {
        super.view();
        resShow();//响应显示类型
        getReslist();//按响应类型获取响应列表
      //从首页进入
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        return VIEW;
    }
    
    private void resShow(){
        Map<String, Object> filterMap = new HashMap<String, Object>();
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
        filterMap.put("schno",object.getSchno());
        filterMap.put("usercode",user.getUsercode());
        
       resList=  oaScheduleResponseManager.listObjects(filterMap);
       if (null!=resList && resList.size()>0){
           resShow="View";
       }
    }
    /**
     * 日历展示发起申请(新)
     * @return
     */
    public String addOaSchedule(){
        request.setAttribute("xzrc_sy", request.getParameter("xzrc_sy"));
        this.addNew();
        return "addOaSchedule";
    }
    /**
     * 日历展示领导部分发起申请
     * @return
     */
    @Override
    public String built(){
        initUsers();
        Date currDate = new Date();  
        //WORK_BEGIN工作日开始WORK_END工作日结束   是数据字典进行维护
        String workbegin = CodeRepositoryUtil.getDataPiece("SYSPARAM", "WORK_BEGIN").getDatavalue();
        String workend = CodeRepositoryUtil.getDataPiece("SYSPARAM", "WORK_END").getDatavalue();
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");  
        SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
        Date BegTime;
        try {
            BegTime = formatime.parse(dateTime.format(currDate) + " " + workbegin);
            Date EndTime = formatime.parse(dateTime.format(currDate) + " " + workend);
            object.setPlanBegTime(BegTime);
            object.setPlanEndTime(EndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }//如果从首页进入，用dashboard标记
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        return EDIT;
    }
    /**
     * 日历展示部分发起申请
     * @return
     */
    public String addNew() {
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(StringUtils.isNotBlank(request.getParameter("xzrc_sy"))){
                object.setPlanBegTime(format.parse(request.getParameter("planBegTime_s")));
                object.setPlanEndTime(format.parse(request.getParameter("planEndTime_s")));
                request.setAttribute("xzrc_sy", request.getParameter("xzrc_sy"));
            }else{
            Date beforeDate = new Date(object.getPlanBegTime().getTime() + (9*60*60*1000));
            object.setPlanBegTime(beforeDate);
            Date afterDate = new Date(object.getPlanBegTime().getTime()+(8*60*60*1000));
            object.setPlanEndTime(afterDate);
            }
            // 获取参会人员（借用为领导共用）
            initUsers();
            getUser();
            getLeaders();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return EDIT;
    }
    
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String schno : ids.split(",")) {
            object.setSchno(schno);
            super.delete();
         }
        }
      return SUCCESS;
    }
    /**
     * 编辑的时候自动去人员中间表去获取usercode
     */
    private List<AddressBookRelection> listuser;

    private void getUser() {
        // 编辑的时候自动去人员中间表去获取usercode
        if (StringUtils.isNotBlank(object.getSchno())) {
            listuser = addressBookRelectionManager.getUserlist(
                    object.getSchno(), "2");
            if (listuser != null && listuser.size() > 0) {
                userValue = "";
                for (int i = 0; i < listuser.size(); i++) {
                    String value = listuser.get(i).getShareman() + ",";
                    userValue += value;
                }
            }
            if (StringUtils.isNotBlank(userValue)) {
                userValue = userValue.substring(0, userValue.length() - 1);
            }
        }

    }
    
    private void getLeaders() {
        // 编辑的时候自动去人员中间表去获取usercode
        if (StringUtils.isNotBlank(object.getSchno())) {
            listuser = addressBookRelectionManager.getUserlist(
                    object.getSchno(), "1");
            if (listuser != null && listuser.size() > 0) {
                leaderValue = "";
                for (int i = 0; i < listuser.size(); i++) {
                    String value = listuser.get(i).getShareman() + ",";
                    leaderValue += value;
                }
            }
            if (StringUtils.isNotBlank(leaderValue)) {
                leaderValue = leaderValue.substring(0, leaderValue.length() - 1);
            }
        }

    }
    /**
     *保存新
    */
    public void saveAddOaSchedule(){
      try{
           FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
           if (StringUtils.isBlank(object.getSchno())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatedate(DatetimeOpt.currentUtilDate());
                emailSign="A";//邮件标志为：N不发邮件、A发邮件提醒 R 继续发邮件提醒
                noticSign="A";
            }
           oaScheduleMag.saveObject(object);
            saveOthers();
      }catch(Exception e){
        e.printStackTrace();
      }
    }


    public String save() {
        try {
           
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            OaSchedule oaSchedule=oaScheduleMag.getObjectById(object.getSchno());
            if(oaSchedule!=null){
                oaScheduleMag.copyObjectNotNullProperty(oaSchedule, object);
                object = oaSchedule;
            }
            if (StringUtils.isBlank(object.getSchno())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatedate(DatetimeOpt.currentUtilDate());
                emailSign="A";//邮件标志为：N不发邮件、A发邮件提醒 R 继续发邮件提醒
                noticSign="A";
                
            }
            //事项类型不为3:邀请与会者 清空信息
//            if(StringUtils.isNotBlank(object.getSchno())&&StringUtils.isNotBlank(object.getItemtype())&&!"3".equals(object.getItemtype())){
                //删除参会人员（中间表日程安排biztype1.参会领导。2.与会人员）
//                object.setCity("");
//                object.setMeetid("");
//            }
            oaScheduleMag.saveObject(object);
            saveOthers();
            return this.tabList(); 
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * A保存中间表，B保存日程提醒，C邮件提醒
     */
    private void saveOthers(){
        //A中间表日程安排部分：1.参会领导。2.与会人员
        // 把参会人员记录到中间表m_address_book_relection
        addressBookRelectionManager.deleteuser(object.getSchno());
        //事项类型不为3:邀请与会者 清空信息
        if(StringUtils.isNotBlank(userValue)){
//        if (StringUtils.isNotBlank(userValue)&&StringUtils.isNotBlank(object.getItemtype())&&"3".equals(object.getItemtype())) {
            String[] values = this.userValue.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection info = new AddressBookRelection();
                info.setAddrbookid(object.getSchno());
                info.setShareman(values[i]);
                info.setBizType("2");
                addressBookRelectionManager.saveObject(info);
            }
        }
        // 把参会领导记录到中间表m_address_book_relection
        if (StringUtils.isNotBlank(leaderValue)) {
            String[] values = this.leaderValue.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection info = new AddressBookRelection(new AddressBookRelectionId(object.getSchno(),values[i],"1"));

                addressBookRelectionManager.saveObject(info);
            }
        }
        //B保存日程提醒
        if (StringUtils.isNotBlank(userValue)&&noticSign.equals(object.getNoticeSign())) {
            OaRemindInformation oaRemindInformation =new OaRemindInformation();
            oaRemindInformation.setNo(oaRemindInformationManager.getNextkey());
            oaRemindInformation.setTitle("[日程安排]"+object.getTitle());
            oaRemindInformation.setUsercode(object.getCity());
            oaRemindInformation.setCreater(this.getLoginUserCode());//日程安排人员
            oaRemindInformation.setBegtime(object.getPlanBegTime());
            oaRemindInformation.setEndtime(object.getPlanEndTime());
            oaRemindInformation.setCreatetime(new Date());
            oaRemindInformation.setRemark(object.getRemark());
            oaRemindInformation.setDjid(object.getSchno());
            oaRemindInformation.setReType("13");//日程安排标
            oaRemindInformation.setThesign("1");//已发送提醒
            oaRemindInformation.setCreateRemark( "系统发起");
            oaRemindInformation.setRemark(object.getTitle()+"于"+DatetimeOpt.convertDatetimeToString(object.getPlanBegTime())+"到"+DatetimeOpt.convertDatetimeToString(object.getPlanEndTime())+"举行。");
            oaRemindInformationManager.saveObject(oaRemindInformation) ;
            String[] values = this.userValue.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection info = new AddressBookRelection();
                info.setAddrbookid(oaRemindInformation.getNo());
                info.setShareman(values[i]);
                info.setBizType("0");//未读标记
                addressBookRelectionManager.saveObject(info);
            }
        }
        //C邮件提醒
        if (StringUtils.isNotBlank(userValue)&&emailSign.equals(object.getIsEmail())) {
            Innermsg innermsg=new Innermsg();
            innermsg.setMsgcode(innermsgManager.getNextKey());
            innermsg.setMsgtype("P");//类型个人邮件
            innermsg.setSender(this.getLoginUserCode());
            innermsg.setSenddate(new Date());
            innermsg.setReceiveUserCode(userValue);
            innermsg.setReceivename(object.getCity());
            innermsg.setMsgcontent(object.getTitle()+"于"+DatetimeOpt.convertDatetimeToString(object.getCreatedate())+"到"+DatetimeOpt.convertDatetimeToString(object.getPlanEndTime())+"举行。");
            innermsg.setMsgtitle("[日程安排]"+object.getTitle());
            innermsg.setMailtype(Innermsg.MAIL_TYPE_O);//发件箱标记
            innermsgManager.saveMsg(innermsg);
        }
       
    }
    /**
     * 获取当前人员usercode
     * @return
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    
    /**
     * ajax验证人员并操作人员中间表
     * 
     * @return
     */
    public String checkdjid() {
        json = new JSONObject();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String schno = request.getParameter("schno");
            String bizType = request.getParameter("bizType");
            paramMap.put("schno", new String(schno.getBytes("ISO-8859-1"),
                    "GBK").trim()); 
           //删除参会人员（中间表日程安排biztype1.参会领导。2.与会人员）
            addressBookRelectionManager.deleteuser(schno,bizType);

            ((JSONObject) json).put("status", "none" );
        } catch (Exception e) {
            log.info(e);
            ((JSONObject) json).put("status", "failed");
        }

        return "json";
    }

    
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray ja =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", ja.toString());
    }

    @SuppressWarnings("unchecked")
    public void ajax() throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> parameterNames = request.getParameterNames();
        
        
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }

        params.put("usercode",
                ((FUserDetail) super.getLoginUser()).getUsercode());
        params.put("contextPath", request.getContextPath());

        
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());
        if(filterMap.get("title") != null){
            filterMap.put("title",URLDecoder.decode(request.getParameter("s_title"),"UTF-8")) ;
        }
        if(filterMap.get("leaders") != null){
            filterMap.put("leaders",URLDecoder.decode(request.getParameter("s_leaders"),"UTF-8")) ;
        }
        List<OaSchedule> oaSchedulelist = new ArrayList<OaSchedule>() ;
        
        //领导日程安排不作特殊处理
        if("2".equals(s_sehType)){
            oaSchedulelist = oaScheduleMag.listObjects(filterMap);
        }else if ("1".equals(s_sehType)){
            String usercode="";
            //为个人日程安排时，只查看自己的日程
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
                usercode=user.getUsercode();
                oaSchedulelist= oaScheduleMag.listObjects(filterMap, usercode);
        }

        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(oaSchedulelist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }
    
    
    
    
    /**
     * 列表显示在日历上 默认查询时间
     */
    public String calendarView() {
        if(request.getParameter("leaders")!= null){
           try {
            jsonDate(URLDecoder.decode(request.getParameter("leaders"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//页面自动匹配人员默认值（保留查询条件）
       }
        return "calendarView";
    }
    
    @SuppressWarnings({ "unchecked"})
    public void ajaxTab() throws IOException {
    
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }

        params.put("usercode",
                ((FUserDetail) super.getLoginUser()).getUsercode());
      

        // 获得开始结束时间
        Date start = new Date(
                Long.parseLong((String) params.get("start")) * 1000);
        Date end = new Date(
                Long.parseLong((String) params.get("end")) * 1000 - 1000);
        params.put("planBeginTimeBegin",DatetimeOpt.convertDateToString(start));
        params.put("planBeginTimeEnd",DatetimeOpt.convertDateToString(end));
        params.put("sehType",s_sehType);
        if(params.get("leaders") != null){
            params.put("leaders",URLDecoder.decode(request.getParameter("leaders"),"UTF-8")) ;
        }
        
         List<OaSchedule> oaSchedulelist = new ArrayList<OaSchedule>() ;
        
        //领导日程安排不作特殊处理
        if("2".equals(s_sehType)){
            oaSchedulelist = oaScheduleMag.listObjects(params);
        }else if ("1".equals(s_sehType)){
            String usercode="";
            //为个人日程安排时，只查看自己的日程
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
                usercode=user.getUsercode();
                oaSchedulelist= oaScheduleMag.listObjects(params, usercode);
        }

       
        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(oaSchedulelist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
        
        
    }
    
    private void getJsonResult(List<OaSchedule> oaSchedulelist,
            List<Map<String, Object>> jsonResult, Map<String, Object> params) {

        // 添加实例，操作相关实现类接口
        List<Map<String, Object>> result = putDataToMap(oaSchedulelist, params);

        jsonResult.addAll(result);
    }
    
    /**
     * 将数据转换为日历控件所需要的Json数据格式
     * 
     * @param tasklist
     * @return
     */
    @SuppressWarnings("static-method")
    private List<Map<String, Object>> putDataToMap(
            List<OaSchedule> oaSchedulelist, Map<String, Object> params) {
        String contextPath = (String) params.get("contextPath");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(null!=oaSchedulelist){
        for (OaSchedule data : oaSchedulelist) {
            Map<String, Object> task = new HashMap<String, Object>();
            // 用户owner
            /*String owner = CodeRepositoryUtil.getValue("usercode",
                    data.getCreater());*/

            task.put("id", data.getSchno());
            
            String title ="";
            title+="["+CodeRepositoryUtil.getValue("oaItemType", data.getItemtype())+"]";
            title+=null==data.getAddress()?"":data.getAddress()+"\n";//地点
            title+=null==data.getLeaders()?"":data.getLeaders()+"\n";//领导
            title+= data.getTitle();
            title+=null==data.getRemark()?"":data.getRemark()+"\n";//
             //周视图不需要截取
            /*if (15< title.length()) {
                title = title.substring(0, 15) + "...";
            }*/

               task.put("title", title);

                 //默认与无色不一样
                task.put("tasktag", null==CodeRepositoryUtil.getDataPiece("TASKTAG", data.getTaskTag())?"":CodeRepositoryUtil.getDataPiece("TASKTAG", data.getTaskTag()).getExtracode());
                task.put("textColor","black");
                task.put("start", DatetimeOpt.convertDateToString(
                        data.getPlanBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getPlanEndTime(), "yyyy-MM-dd HH:mm:ss"));
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
               
            // 可编辑 :自己创建自己编辑
            if(user.getUsercode().equals(data.getCreater()))   {
                task.put("editable", false);
            } 

            task.put("allDay", false);

            String url = "oa/oaSchedule!view.do?schno="
                    + data.getSchno()+"&s_sehType="+data.getSehType() ;// 

            if (StringUtils.isNotBlank(url)) {
                StringBuilder sb = new StringBuilder();
              
                task.put("url",  url + sb);
            }
            result.add(task);
          }
        }
        return result;
    }
    
    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        // 用于初始化查看页面默认显示
        curUrl="/oa/oaSchedule!calendarView.do?s_sehType="+s_sehType+"&s_canAdd="+s_canAdd;
        jspInfo = new OptJspInfo();
        jspInfo.setTitle("日程安排查看");
        jspInfo.setFrameList(frameList);

        return "oaScheduleView";
    }
    
    
    private List<Map<String, String>> jsonDatas;
    private List<FUserinfo> userinfos;
    /*
     * 列表查询时获取人员
     */
    public String selectUser() {
        List<FUserinfo> temp = CodeRepositoryUtil.getAllUsers("T");
        jsonDatas = new ArrayList<Map<String, String>>();
        userinfos = new ArrayList<FUserinfo>();
        String matchInfo = request.getParameter("q");
        String matchCount = "5";

        int count = (null == matchInfo || "".equals(matchInfo)) ? 0 : Integer
                .parseInt(matchCount);

        if (null == matchInfo || "".equals(matchInfo)) {
            return "userinfos";
        }

        matchInfo = matchInfo.toLowerCase();
        int index = 0;
        for (FUserinfo user : temp) {
            if (index >= count)
                break;

            if (user.getUsername().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            } else if (user.getLoginname().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            } else if (user.getUsercode().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            }
        }
        for (FUserinfo userinfo : userinfos) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userinfo.getUsername());// userinfo.getUsercode()
            m.put("name", userinfo.getUsername()+ "[" + userinfo.getLoginname() + "]" );//+ "[" + userinfo.getUsercode()+ "]" + "[" + userinfo.getLoginname() + "]"
            m.put("showName", userinfo.getUsername());
            jsonDatas.add(m);
        }
        return "userinfos";
    }
    
    private static FUserinfo copyUserInfo(FUserinfo info) {
        FUserinfo temp = new FUserinfo();

        temp.setUsercode(info.getUsercode());
        temp.setUsername(info.getUsername());
        temp.setLoginname(info.getLoginname());

        return temp;
    }
    
 public void jsonDate(String usercode){
        
        JSONArray ja = new JSONArray();
        jsonDatas = new ArrayList<Map<String, String>>();
        if (StringUtils.isNotEmpty(usercode)) {
        String[] usercodes = usercode.split(",");
        for (String s : usercodes) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("id", s);
        m.put("showName",CodeRepositoryUtil.getValue("usercode",s));
        jsonDatas.add(m);
        ja.addAll(jsonDatas);
            }
        }
       request.setAttribute("populate", ja.toString()); 
    }
    
    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public String getS_sehType() {
        return s_sehType;
    }

    public void setS_sehType(String s_sehType) {
        this.s_sehType = s_sehType;
    }
    
    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public String getLeaderValue() {
        return leaderValue;
    }

    public void setLeaderValue(String leaderValue) {
        this.leaderValue = leaderValue;
    }

    public String getResShow() {
        return resShow;
    }

    public void setResShow(String resShow) {
        this.resShow = resShow;
    }

    public List<OaScheduleResponse> getResListJS() {
        return resListJS;
    }

    public void setResListJS(List<OaScheduleResponse> resListJS) {
        this.resListJS = resListJS;
    }

    public List<OaScheduleResponse> getResListZH() {
        return resListZH;
    }

    public void setResListZH(List<OaScheduleResponse> resListZH) {
        this.resListZH = resListZH;
    }

    public List<OaScheduleResponse> getResListJJ() {
        return resListJJ;
    }

    public void setResListJJ(List<OaScheduleResponse> resListJJ) {
        this.resListJJ = resListJJ;
    }

    public List<OaScheduleResponse> getResList() {
        return resList;
    }

    public void setResList(List<OaScheduleResponse> resList) {
        this.resList = resList;
    }




    
    public OaScheduleResponseManager getOaScheduleResponseManager() {
        return oaScheduleResponseManager;
    }

    public void setOaScheduleResponseManager(
            OaScheduleResponseManager oaScheduleResponseManager) {
        this.oaScheduleResponseManager = oaScheduleResponseManager;
    }
    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }

    public InnermsgManager getInnermsgManager() {
        return innermsgManager;
    }

    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }

    public OaRemindInformationManager getOaRemindInformationManager() {
        return oaRemindInformationManager;
    }

    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }

    public String getCurUrl() {
        return curUrl;
    }
    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }
    
   
    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }

    public String getS_canAdd() {
        return s_canAdd;
    }

    public void setS_canAdd(String s_canAdd) {
        this.s_canAdd = s_canAdd;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    
    public List<Map<String, String>> getJsonDatas() {
        return jsonDatas;
    }
    public void setJsonDatas(List<Map<String, String>> jsonDatas) {
        this.jsonDatas = jsonDatas;
    }
    public void setUserinfos(List<FUserinfo> userinfos) {
        this.userinfos = userinfos;
    }
}
