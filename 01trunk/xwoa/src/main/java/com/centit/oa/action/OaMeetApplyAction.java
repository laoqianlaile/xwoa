package com.centit.oa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.OaMeetroomApply;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.po.VOaMeetApplyList;
import com.centit.oa.po.VoaMeetMinute;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetMinutesManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaMeetroomApplyManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaScheduleManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;

public class OaMeetApplyAction extends OACommonBizAction<OaMeetApply> {
    private static final Log log = LogFactory.getLog(OaMeetApplyAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private OaMeetApplyManager oaMeetApplyMag;

    public void setOaMeetApplyManager(OaMeetApplyManager basemgr) {
        oaMeetApplyMag = basemgr;
        this.setBaseEntityManager(oaMeetApplyMag);
    }

    private OptJspInfo jspInfo;
    private List<OptNewlyIdea> optNewlyIdeaList;

    private OptNewlyIdeaManager optNewlyIdeaManager;

    private OaMeetinfoManager oaMeetinfoManager;
    private AddressBookRelectionManager addressBookRelectionManager;
    private OaMeetMinutesManager oaMeetMinutesManager;
    private OaSuperviseManager oaSuperviseManager;
    private InnermsgManager innermsgManager;//邮件通知
    private OaScheduleManager oaScheduleManager;//日程安排
    private OaMeetroomApplyManager oaMeetroomApplyManager;//选取会议室安排
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private OaRemindInformationManager oaRemindInformationManager;
    
    private String curUrl;

    private FlowDefine flowDefine;
    
    private OaMeetroomApply oaMeetroomApply;

    private String flowCode;

    private List<FUnitinfo> unitlist;// 法制办部门

    private List<OaMeetinfo> oaMeetinfolist;// 法制办部门
    private String show_type;// 区别查看页面是否显示新增按钮
    private String viewtype; // 区别查看页面是否有返回按钮
    private String retValue;//接收参会部门
    
    private String otherItem;//会议标准其他
    private List reqRemarkList;//会议标准
    private String reqRemarkNew;//新的会议标准用语view展示

    private Object json;
    private String userValue;// 获取参会人员usercode
    private List<VoaMeetMinute> meetlist;

    private  List<OaSupervise> oasuplist;
    private List<VOaMeetApplyList> oaMeetApplyList;
    
    private String ideacode;//申请会议意见，是否同意 by dk 2016-0105
   
    public String getIdeacode() {
        return ideacode;
    }
    public void setIdeacode(String ideacode) {
        this.ideacode = ideacode;
    }

    /**
     * RTX相关
     */
    private RtxInfoManager rtxInfoManager;  
    private WfActionTaskDao actionTaskDao;
    
    public List<OaSupervise> getOasuplist() {
        return oasuplist;
    }
    public void setOasuplist(List<OaSupervise> oasuplist) {
        this.oasuplist = oasuplist;
    }  

    /**
     * 列表数据
     */
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            
          FUserDetail user = ((FUserDetail) getLoginUser());
            
          if ("myself".equals(this.show_type)) {
              filterMap.put("myself",user.getUsercode());//自己所有数据
          }
            if ("self".equals(this.show_type)) {
                filterMap.put("self",user.getUsercode());//自己所有数据+别人提交流程数据
            }
            if("T".equals(this.show_type)){//申请查看页面，查看已经提交的历史申请情况（全部人员的）
                filterMap.put("NP_tbizstate",true);           
            } 
        
          //默认查询当前月份第一天开始的所有数据
            if(StringUtils.isBlank((String)filterMap.get("cpBegTime"))){
                filterMap.put("cpBegTime",DateUtil.getCurrentMonthOfDay() );
//                filterMap.put("cpEndTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetApplyMag.getMeetApplyList(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
         // 部门列表
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            setbackSearchColumn(filterMap);
            //return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    public String meetApplyPanel(){
      //do_meeting 页面传递参数
        String from=request.getParameter("from");
        request.setAttribute("from", from);
        return "meetApplyPanel";
    }
    
    public void wirteDatasBoardroomArranged(){
        String strBeginDate = request.getParameter("beginDate");
        String strEndDate = request.getParameter("endDate");
        String from=request.getParameter("from");
        
        boolean result = false;
        String message = "";
        String data = "";
        if(StringUtils.isEmpty(strBeginDate) || StringUtils.isEmpty(strEndDate)){
            message = "服务器请求参数异常";
        }else{
            try{
                if(StringUtils.isNotBlank(from)&&"doMeeting".equals(from)){
                    data = oaMeetApplyMag.getBoardroomJsonArranged(strBeginDate, strEndDate,"1,2");
                }else{
                    data = oaMeetApplyMag.getBoardroomJsonArranged(strBeginDate, strEndDate,"2");
                }
                result = true;
                message ="操作成功";
            }catch(Exception e){
                message = "服务器处理异常，操作失败";
            }
        }
       
        String jsonStr = "{\"result\":"+result+",\"message\":\""+message+"\",\"data\":"+data+"}";
        
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(jsonStr);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }
    /**
     * 去查看汇总页面
     * @return
     */
    public String goListGather(){
        return "listGather";
    }
    /**
     * 查看个人发起的申请以及涉及办理的会议申请信息列表
     * @return
     */
    public String listOwn() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            
          
            if ("F".equals(this.show_type)) {// 登记的查询list
                filterMap.put("biztype", "F");
            }
            FUserDetail user = ((FUserDetail) getLoginUser());
            filterMap.put("usercode", user.getUsercode());
            //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            oaMeetApplyList= oaMeetApplyMag.listMeetingInvolvedIn(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
         // 部门列表
           
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            setbackSearchColumn(filterMap);
            return "listOwn";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public List<Map<String, String>> getMeetings() {
        List<Map<String, String>> sslist = new ArrayList<Map<String, String>>();
        Map<String, Object> picMap = new HashMap<String, Object>();
        picMap.put("isuse", "T");
        
        List<OaMeetinfo> list =oaMeetinfoManager.listObjectsWithOutLOB(picMap);
        for (OaMeetinfo o : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", o.getDjid());
            map.put("name", o.getMeetingName());
            sslist.add(map);
        }
        return sslist;
    }
    /**
     * 
     * @return 返回部门信息
     */
    List<FUnitinfo> listSelectOrgList = new ArrayList<FUnitinfo>();
    public String listSelectOrg() {
        try {
            //获取所有部门
            //listSelectOrgList = CodeRepositoryUtil.getAllUnits("T");
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("isvalid", "T");
            listSelectOrgList = sysUnitManager.listObjects(filterMap);
            totalRows = listSelectOrgList==null?0:listSelectOrgList.size();
            return "listSelectOrg";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }


    /**
     * 列表数据
     */
    public String selectlist() {
        try {

            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // 部门列表
            unitlist = sysUnitManager.getAllSubUnits("1");
            filterMap.put("biztype", "C");

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetApplyMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            return "listdjid";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
    // private List<AddressBookRelection> listuser;
    @Override
    public String edit() {
        super.edit();
        FUserDetail user = ((FUserDetail) getLoginUser());
        if(null==user){
            return  "login";
        }
        oaMeetApply = oaMeetApplyMag.getObjectById(object.getDjId());
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(oaMeetApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HY打头+时间戳
        String no = "HY-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        
        if(StringUtils.isBlank(object.getApplyNo())){
            object.setApplyNo(no);            
        }
        Map<String,String> map =new HashMap<String,String>(); 
        List<FDatadictionary> datalist = CodeRepositoryUtil.getDictionary("meetStandard");
        for(FDatadictionary nary : datalist){
            map.put(nary.getDatacode(), nary.getDatavalue());
        }
        request.setAttribute("dataMap", map);
        if(oaMeetApply!=null){
            String [] dealtype=null;
            String [] retName=null;
            if(oaMeetApply.getReqRemark()!=null && oaMeetApply.getReqRemark()!=""){
                dealtype=oaMeetApply.getReqRemark().split(",");
            }
            //参会单位显示
            getUnitNames(oaMeetApply.getAttendingUnits());
           /* if(oaMeetApply.getAttendingUnits()!=null && oaMeetApply.getAttendingUnits()!=""){
                retName=oaMeetApply.getAttendingUnits().split(",");
                StringBuilder sb =new StringBuilder();
                for(String rtName : retName){
                    String retNameValue = CodeRepositoryUtil.getValue("unitcode", rtName);
                    sb.append(retNameValue+",");
                }
                if(sb.length()>0){
                    sb.deleteCharAt(sb.length()-1);
                }
                retValue=sb.toString();
            }*/
            if(dealtype!=null && dealtype.length>0){
                reqRemarkList=Arrays.asList(dealtype);
            }            
        }
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("1");
        // 获取参会人员
        //initUsers();
        //编辑的时候自动去人员中间表去获取usercode
        //getUservalues();

        // 会议室列表:启用
        /*Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);*/
        //会议室申请信息
        /*if(StringUtils.isNotBlank(object.getSupDjid())){
            oaMeetroomApply=oaMeetroomApplyManager.getObjectById(object.getSupDjid());
        }*/
        
        request.setAttribute("usercode", user.getUsercode());
        request.setAttribute("parentunitcode", object.getDepno());
        this.initparam("HYDJ");//归档手动登记通用配置信息
        return EDIT;
    }

    /**
     * 日历展示部分发起申请
     * @return
     */
  
    public String addNew() {
        object.setPlanBegTime(object.getPlanBegTime());
        object.setPlanEndTime(object.getPlanEndTime());
        object.setMeetingNo(object.getMeetingNo());
        
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(oaMeetApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HYS打头+时间戳
        String no = "HYS-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        object.setApplyNo(no);
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("1");
        // 获取参会人员
        //initUsers();
        // 会议室列表:启用
        /*Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);*/
        Map<String,String> map =new HashMap<String,String>(); 
        List<FDatadictionary> datalist = CodeRepositoryUtil.getDictionary("meetStandard");
        for(FDatadictionary nary : datalist){
            map.put(nary.getDatacode(), nary.getDatavalue());
        }
        request.setAttribute("dataMap", map);
        FUserDetail user = ((FUserDetail) getLoginUser());
        request.setAttribute("usercode", user.getUsercode());
        this.initparam("HYDJ");//归档手动登记通用配置信息        
        return EDIT;
    }
    private UserbizoptLogManager userbizoptLogManager;
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    
    /**
     * 会议室安排
     * 
     * @return
     */
    public String doMeeting() {        
        oaMeetinfolist = oaMeetinfoManager.listObjectsWithOutLOB(null);
        this.view();
        super.generalOpt();
        unitsJsonExp =  sysUnitManager.getAllUnitsJSONNoTree();
        if(object.getDoBegTime()==null){
            object.setDoBegTime(object.getPlanBegTime());
        }
        if(object.getDoEndTime()==null){
            object.setDoEndTime(object.getPlanEndTime());
        }
        
      //保存查看日志
        FUserDetail user = (FUserDetail) getLoginUser();
        UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(object.getTitle(),object.getDjId()),curNodeInstId);
        userbizoptLogManager.saveUserbizoptLog(u, user);
        
        
        return "doMeeting";
    }
   
    /**
     * 会议室回退登记
     * 
     * @return
     */
    public String doReEdit() {
        this.edit();
        super.generalOpt();
        return "doReEdit";
    }

    /**
     * 会议室使用反馈
     * 
     * @return
     */
    public String doBack() {
        this.edit();
        super.generalOpt();
        return "doBack";
    }

    /**
     * 保存会议室-反馈特殊节点信息
     * 
     * @return
     */
    public String savedoBack() {
        OaMeetApply oaMeetApply = oaMeetApplyMag
                .getObjectById(object.getDjId());
        if (oaMeetApply != null) {
            // 提交流程保存办结信息
            object.setSolvenote(object.getTodoremark());
            object.setBizstate("C");
            object.setBiztype("C");
            // 提交反馈时跟新isuse的状态置为3
            object.setState("3");
            oaMeetApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
        }
        oaMeetApplyMag.saveObject(object);
        return "refreshTasks";
    }

    /**
     * 提交会议室-使用反馈特殊节点信息
     * 
     * @return
     */
    public String submitdoBack() {
   
        this.savedoBack();
        // 保存过程日志信息
        saveIdeaInfo("会议室使用反馈");
        Set<Long> nextNode = submitNode(false);
        saveSms(nextNode);
        return "refreshTasks";
    }

    /**
     * 保存会议室-安排特殊节点信息
     * 
     * @return
     */
    public String savedoMeeting() {
        OaMeetApply oaMeetApply = oaMeetApplyMag
                .getObjectById(object.getDjId());
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        object.setDoCreater(fuser.getUsercode());
        object.setDoDepno(fuser.getPrimaryUnit());
        object.setRecomUnits(xbOrgCodes);//会议保障部门
        object.setRecomUnitNames(xbOrgNames);//会议保障部门
        
        if (oaMeetApply != null) {
            //防止基层停车标识被覆盖
            object.setIsBasicUnit(oaMeetApply.getIsBasicUnit());
            object.setIsStopCar(oaMeetApply.getIsStopCar());
            //从页面获取最新数据和数据库数据合并
            oaMeetApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
            object.setDoCreater(fuser.getUsercode());
            object.setDoDepno(fuser.getPrimaryUnit());
            object.setDoTime(DatetimeOpt.currentUtilDate());
        }
        if (!isInuse()){
            List<OaMeetApply> applylist= oaMeetApplyMag.getApplylist(object.getDjId(),
                    object.getMeetingNo(), object.getDoBegTime(),
                    object.getDoEndTime(), "2");
            if (applylist != null && applylist.size() > 0) {
                for(OaMeetApply meet:applylist){  
                    //提醒申请人
                    OaRemindInformation oaRemindInformation = new OaRemindInformation();
                    OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(meet.getDjId());
                    String no = oaRemindInformationManager.getNextkey();
                    oaRemindInformation.setNo(no);
                    oaRemindInformation.setCreater(fuser.getUsercode());
                    oaRemindInformation.setFrequency(Long.parseLong("5"));
                    oaRemindInformation.setCreatetime(DatetimeOpt.currentUtilDate());
                    oaRemindInformation.setDjid(meet.getDjId());
                    oaRemindInformation.setTitle("关于["+meet.getTitle()+"]的会议室安排已经被占用的告知");
                    oaRemindInformation.setBegtime(DatetimeOpt.currentUtilDate());
                    oaRemindInformation.setEndtime(DatetimeOpt.addDays(DatetimeOpt.currentUtilDate(), 1));
                    oaRemindInformation.setThesign("1");//已发送
                    oaRemindInformation.setUsercode(meet.getCreater());
                    oaRemindInformation.setReType("HYSQ");
                    oaRemindInformation.setRemark("不好意思，您申请的"+ DatetimeOpt.convertDateToString(meet.getDoBegTime(),"yyyy年MM月dd日")+meet.getTitle()+"的会议室另有重要安排，已被取消，请您重新申请。");
                    oaRemindInformationManager.saveObject(oaRemindInformation);
                    
                    AddressBookRelection o= new AddressBookRelection();
                    o.setAddrbookid(no);
                    o.setBizType("0");
                    o.setShareman(meet.getCreater());
                    addressBookRelectionManager.saveObject(o);
                    
                    
                    meet.setBizstate("C");// 业务状态
                    meet.setBiztype("C");// 业务类别
                    meet.setSolvestatus("Z");
                    meet.setState("5");
                    oaMeetApplyMag.saveObject(meet);    
                    flowManager.stopInstance(optBaseInfo.getFlowInstId(),fuser.getUsercode(), "抢占会议申请");
                    
                    //提醒相关部门人员
                    List<OptIdeaInfo> optIdeaInfos=optProcInfoManager.listIdeaLogsByDjIdAndNodeCode(meet.getDjId(),"hys_APFW");
                    if(optIdeaInfos!=null && optIdeaInfos.size()>0){
                        for(OptIdeaInfo bean:optIdeaInfos){
                            OaRemindInformation beana = new OaRemindInformation();
                            
                            String no1 = oaRemindInformationManager.getNextkey();
                            beana.setNo(no1);
                            beana.setCreater(fuser.getUsercode());
                            beana.setFrequency(Long.parseLong("5"));
                            beana.setCreatetime(DatetimeOpt.currentUtilDate());
                            beana.setDjid(bean.getDjId());
                            beana.setRemark("与您相关的一项会议申请["+ DatetimeOpt.convertDateToString(meet.getDoBegTime(),"yyyy年MM月dd日")+meet.getTitle()+"]有变动,请您及时关注。");
                            beana.setBegtime(DatetimeOpt.currentUtilDate());
                            beana.setEndtime(DatetimeOpt.addDays(DatetimeOpt.currentUtilDate(), 1));
                            beana.setThesign("1");//已发送
                            beana.setUsercode(bean.getUsercode());
                            beana.setReType("HYSQ");
                            beana.setTitle("关于["+DatetimeOpt.convertDateToString(meet.getDoBegTime(),"yyyy年MM月dd日")+meet.getTitle()+"]的会议室安排已经被占用的告知");
                            oaRemindInformationManager.saveObject(beana);
                            
                            AddressBookRelection o1= new AddressBookRelection();
                            o1.setAddrbookid(no1);
                            o1.setBizType("0");
                            o1.setShareman(bean.getUsercode());
                            addressBookRelectionManager.saveObject(o1); 
                        }
                    }
                }
               
            }
        }
      
        oaMeetApplyMag.saveObject(object);
        
        OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(object.getDjId());
        // 保存协办单位
        if (StringUtils.isNotBlank(xbOrgCodes)) {
            flowEngine.deleteFlowOrganizeByAuth(optBaseInfo.getFlowInstId(),
                    xbOrgRoleCode, fuser.getUsercode());
            String[] orgCodes = xbOrgCodes.split("[,]");
            if (orgCodes != null && orgCodes.length > 0) {
                flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                        xbOrgRoleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)),
                        fuser.getUsercode());
                flowEngine.saveFlowNodeVariable(curNodeInstId,
                        xbOrgRoleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)));
            }
        }
        return "refreshTasks";
    }

    /**
     * 提交会议室-安排特殊节点信息
     * 
     * @return
     */
    public String submitdoMeeting() {
      
        
        this.savedoMeeting();
        OaMeetApply oaMeetApply = oaMeetApplyMag
                .getObjectById(object.getDjId());
        
       
        // 保存过程日志信息
        saveIdeaInfo("会议室安排");
        Set<Long> nextNode = submitNode(false);
        
        //同意会议室安排--给会议相关人员发送通知，通知类型为：内部邮件、个人日程、rtx消息
        if("T".equals(ideacode)){
            oaMeetApply.setState("2");//表示已经安排
            oaMeetApply.setCpBegTime(object.getDoBegTime());
            oaMeetApply.setCpEndTime(object.getDoEndTime());
            oaMeetApplyMag.saveObject(oaMeetApply);
            OaMeetinfo oaMeetinfo=oaMeetinfoManager.getObjectById(object.getMeetingNo());
            String mesgTitle="[会议安排]"+oaMeetApply.getTitle();
            String mesgContent="<font style='font-weight: bold;'>会议名称</font>："+oaMeetApply.getTitle()+"；<br><br>";
            mesgContent+="<font style='font-weight: bold;'>时间地点</font>："+"会议于"+DatetimeOpt.convertDateToString(object.getDoBegTime(), "yyyy年MM月dd日 HH:mm")+" 至 "+DatetimeOpt.convertDateToString(object.getDoEndTime(), "yyyy年MM月dd日 HH:mm")+"在"+oaMeetinfo.getMeetingName()+"举行；<br><br>";
            mesgContent+=StringUtils.isBlank(oaMeetApply.getAttendingLeaderNames())?" ":" <font style='font-weight: bold;'>参会领导</font>："+oaMeetApply.getAttendingLeaderNames()+"；<br><br>";
            mesgContent+=StringUtils.isBlank(getUnitNames(oaMeetApply.getAttendingUnits()))?" ":" <font style='font-weight: bold;'>参会单位</font>："+getUnitNames(oaMeetApply.getAttendingUnits())+"；<br><br>";
            mesgContent+=StringUtils.isBlank(oaMeetApply.getOtherUnits())?" ":" <font style='font-weight: bold;'>其他参会单位</font>："+oaMeetApply.getOtherUnits()+"；<br><br>";
            mesgContent+=StringUtils.isBlank(oaMeetApply.getDoRemark())?" ":" <font style='font-weight: bold;'>备注说明</font>："+oaMeetApply.getDoRemark()+"；<br><br>";
            mesgContent+="以上。<br><br>";
            mesgContent+="<font color='blue'>注意：此邮件为系统自动发送，请不要回复</font>。";
            //1.获取参会人员
            getUservalues("domeeting");
           
            //2.输送邮件数据
            this.sendInnermesg(userValue,mesgTitle,mesgContent);
            
            //3.输送日程安排数据
            this.sendschedule(userValue);
            
            //4.通知下一步流程办理人员
            saveSms(nextNode);
             
        }else{//不同意会议室安排--给会议申请人发送通知
            oaMeetApply.setState("4");//表示不同意会议室安排
            oaMeetApplyMag.saveObject(oaMeetApply);
            
            String mesgTitle="[会议安排]"+oaMeetApply.getTitle()+"安排不通过";
            String mesgContent=oaMeetApply.getTitle()+"[会议安排]"+oaMeetApply.getTitle()+"安排不通过,如有疑问详情咨询相关人员";
           //输送邮件数据
            this.sendInnermesg(oaMeetApply.getCreater(),mesgTitle,mesgContent);
        }
        
       
        
        
        return "refreshTasks";
    }
    
    /**
     * 
     * @param nextNode
     */
    public void saveSms(Set<Long> nextNode) {
        String expireNoticeType = CodeRepositoryUtil.getValue("WFNotice",
                "type");
        if (nextNode != null && !nextNode.isEmpty()) {
            for (Long nodeActive : nextNode) {
                List<VUserTaskList> tasks = actionTaskDao
                        .getTasksByNodeInstId(nodeActive);
                saveNewTaskNotice(expireNoticeType, tasks);
            }
        }

    }

    private void saveNewTaskNotice(String noticeType, List<VUserTaskList> tasks) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
 
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        url += "/page/rtx/signauth.jsp?url=dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000857";

        for (VUserTaskList task : tasks) {
            FUserinfo userInfo = sysUserManager.getObjectById(task
                    .getUserCode());
            if(userInfo!=null){
            String loginName = userInfo.getLoginname();

            RtxInfo rtxInfo = new RtxInfo();
            rtxInfo.setNo(rtxInfoManager.getNextkey());
            rtxInfo.setDjId(object.getDjId());
            rtxInfo.setNodeId(task.getNodeInstId());
            rtxInfo.setCreateDate(new Date(System.currentTimeMillis()));
            rtxInfo.setCreateUserCode(loginInfo.getUsercode());
            rtxInfo.setCreateUserName(loginInfo.getUsername());
            rtxInfo.setInfoContent("[" + optBaseInfo.getTransaffairname() + "|"
                    + url + "]");
            rtxInfo.setReceiveUserCode(task.getUserCode());
            rtxInfo.setReceiveUserName(loginName);
            rtxInfo.setIsSend("0");
            // sender.sendMessage("9999999"/*系统管理员*/,
            // loginName, "您有一个新待办：",
            // "["+optBaseInfo.getTransaffairname()+"|"+url+"]");
            rtxInfoManager.saveObject(rtxInfo);
            }
        }
    }
    
  
    /*
     * 获取会议相关人员
     * 1.与会领导
     * 2.参会单位(按岗位过滤)
     * 3.办件参与人员（不包括自己）
     * type domeeting  1,2
     *      cancle 1,2,3
     */
    private void getUservalues(String type) {
        OaMeetApply oaMeetApply = oaMeetApplyMag.getObjectById(object.getDjId());
        FUserDetail user = ((FUserDetail) getLoginUser());
        if(null!=user&&null!=user.getUsercode()){
            if (StringUtils.isNotBlank(object.getDjId())) {
                userValue="";
                List<AddressBookRelection> listuser=addressBookRelectionManager.getUserlist(oaMeetApply.getDjId(),"0");
                if(listuser!=null && listuser.size()>0){    
                    for(int i=0;i<listuser.size();i++){
                            String value=listuser.get(i).getShareman()+",";
                            userValue+=value; 
                    }
                }
                if(StringUtils.isNotBlank(oaMeetApply.getAttendingUnits())){
                    //默认值 BMSWGD  字典项获取
                    String userStation=CodeRepositoryUtil.getDataPiece("HYCOMON", "HYACCEPT")!=null?CodeRepositoryUtil.getDataPiece("HYCOMON", "HYACCEPT").getDatavalue():"BMSWGD";
                    List<FUserinfo> userInfoList=oaMeetApplyMag.getSysUsersByRoleAndUnit(oaMeetApply.getAttendingUnits(),userStation);
                    if(userInfoList!=null && userInfoList.size()>0){    
                       for(int i=0;i<userInfoList.size();i++){
                         String value=userInfoList.get(i).getUsercode()+",";
                         userValue+=value;
                       }
                   }
                }
                
               
                if("cancle".equals(type)){
                  //流程参与人员
                    List<OptIdeaInfo> optIdeaInfos=optProcInfoManager.listIdeaLogsByDjId(oaMeetApply.getDjId());
                    if(optIdeaInfos!=null && optIdeaInfos.size()>0){    
                        for(int i=0;i<optIdeaInfos.size();i++){
                            if(!user.getUsercode().equals(optIdeaInfos.get(i).getUsercode())){
                          String value=optIdeaInfos.get(i).getUsercode()+",";
                          userValue+=value;
                            }
                        }
                    } 
                    
                    //   下一步办理人员
                    List<VUserTaskList> tasks=actionTaskDao.getTasksByFlowid(oaMeetApply.getFlowInstId());
                    if(null!=tasks&&tasks.size()>0)
                    for (VUserTaskList task : tasks) {
                        if(!user.getUsercode().equals(task.getUserCode())){
                            String value=task.getUserCode()+",";
                            userValue+=value;
                              }
                    }
                }
             
                
                
               
                if(StringUtils.isNotBlank(userValue)){
                    userValue=userValue.substring(0, userValue.length()-1); 
                    }
               } 
            
        }

    }
    
    /*
     * 发起日程安排，为参会人员添加个人日程
     */
    private void sendschedule(String userValue) {
        if (StringUtils.isNotBlank(userValue)) {
            OaMeetApply oaMeetApply=oaMeetApplyMag.getObjectById(object.getDjId());//业务数据
            OaMeetinfo oaMeetinfo=oaMeetinfoManager.getObjectById(object.getMeetingNo());
            String remark=StringUtils.isBlank(oaMeetApply.getAttendingLeaderNames())?" ":" 参会领导："+oaMeetApply.getAttendingLeaderNames();
            remark+=StringUtils.isBlank(getUnitNames(oaMeetApply.getAttendingUnits()))?" ":" 参会单位："+getUnitNames(oaMeetApply.getAttendingUnits());
            remark+=StringUtils.isBlank(oaMeetApply.getDoRemark())?" ":" 备注："+oaMeetApply.getDoRemark();
            OaSchedule oaSchedule=new OaSchedule();
            oaSchedule.setPlanBegTime(oaMeetApply.getPlanBegTime());
            oaSchedule.setPlanEndTime(oaMeetApply.getPlanEndTime());     
            oaSchedule.setTitle("[会议安排]"+oaMeetApply.getTitle());
            oaSchedule.setAddress("在"+oaMeetinfo.getMeetingName()+"开会"); 
            oaSchedule.setRemark(remark);
            oaSchedule.setSehType("1");//个人
            oaSchedule.setItemtype("3");
            oaSchedule.setMeetid(oaMeetApply.getMeetingNo());
            oaSchedule.setCity(oaMeetApply.getMeetingPersions());
            oaSchedule.setCreater("系统发起");
            oaSchedule.setCreatedate(new Date());
            oaSchedule.setResRemark(oaMeetApply.getDoRemark());
            oaSchedule.setIsEmail("A");//发送邮件提醒
            oaSchedule.setDjId(oaMeetApply.getDjId());
         
            oaScheduleManager.saveObject(oaSchedule);
            //3.中间表日程安排部分：1.参会领导。2.与会人员
            // 把参会人员记录到中间表m_address_book_relection
            addressBookRelectionManager.deleteuser(oaSchedule.getSchno());          
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection info = new AddressBookRelection();
                    info.setAddrbookid(oaSchedule.getSchno());
                    info.setShareman(values[i]);
                    info.setBizType("2");
                    addressBookRelectionManager.saveObject(info);
                }
            }
      
        }            
        
    }
    
    public String getUnitNames(String unitcodes){
        if(StringUtils.isNotBlank(unitcodes)){
            String [] retName=unitcodes.split(",");
           StringBuilder sb =new StringBuilder();
           for(String rtName : retName){
               String retNameValue = CodeRepositoryUtil.getValue("unitcode", rtName);
               sb.append(retNameValue+",");
           }
           if(sb.length()>0){
               sb.deleteCharAt(sb.length()-1);
           }
           retValue=sb.toString();
       }
        return retValue;
    }
    @Override
    public String built(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        //unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
        unitlist = sysUnitManager.getAllSubUnits("1");
        super.built();
//        request.setAttribute("parentunitcode", sParentUnit);
        //部门选择设置默认值--当前人员所在主部门
        object.setDepno(sParentUnit);//depno中存放的实为unitcode
        //初始化会议标准内容
        Map<String,String> map =new HashMap<String,String>(); 
        List<FDatadictionary> datalist = CodeRepositoryUtil.getDictionary("meetStandard");
        if(datalist!=null &&datalist.size()>0){
            for(FDatadictionary nary : datalist){
                map.put(nary.getDatacode(), nary.getDatavalue());
            } 
        }
        request.setAttribute("dataMap", map);
        request.setAttribute("usercode", user.getUsercode());
        this.initparam("HYDJ");//归档手动登记通用配置信息        
        return BUILT ;
    }

    private void initparam(String modecode) {
        // TODO Auto-generated method stub
        super.moduleCode=modecode;
        super.generalOpt();
    }
    private void sendInnermesg(String userValue,String title,String content) {
        if (StringUtils.isNotBlank(userValue)) {
            OaMeetApply oaMeetApply=oaMeetApplyMag.getObjectById(object.getDjId());//业务数据       
            Innermsg innermsg=new Innermsg();
            innermsg.setMsgcode(innermsgManager.getNextKey());
            innermsg.setMsgtype("P");//类型个人邮件
            innermsg.setSender("系统发起");
            innermsg.setSenddate(new Date());
            innermsg.setReceiveUserCode(userValue);
            innermsg.setTo(userValue);
            innermsg.setReceivename(oaMeetApply.getMeetingPersions());
            innermsg.setMsgcontent(content);
            innermsg.setMsgtitle(title);
            innermsg.setMailtype(Innermsg.MAIL_TYPE_O);//发件箱标记
            innermsgManager.saveMsg(innermsg);
        }            
    }
    
    
    /**
     * 保存会议室登记特殊节点信息
     * 
     * @return
     */
    public String saveReEdit() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        OaMeetApply oaMeetApply = oaMeetApplyMag
                .getObjectById(object.getDjId());// 业务数据

        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (oaMeetApply != null) {// 跟新业务数据
            object.setCreater(fuser.getUsercode());
            object.setDepno(fuser.getPrimaryUnit());
            oaMeetApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
        }
        if (optBaseInfo != null) {// 更新流程业务数据
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setTransaffairname(object.getTitle());
            optBaseInfo.setTransAffairNo(object.getDjId());
            optBaseInfo.setCreatedate(object.getCreatetime());
            optBaseInfo.setCreateuser(object.getCreater());
            optBaseInfoManager.saveObject(optBaseInfo);

        }
        oaMeetApplyMag.saveObject(object);

        return "refreshTasks";
    }

    /**
     * 提交会议室登记申请特殊节点信息
     * 
     * @return
     */
    public String submitReEdit() {
      

        this.saveReEdit();
        // 保存过程日志信息
        saveIdeaInfo("回退登记申请");
        Set<Long> nextNode = submitNode(false);
        saveSms(nextNode);
        return "refreshTasks";
    }

    /**
     * 通用业务框架属性会议室信息查看
     */

    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        // 查看办件业务数据信息
        frameList.add(getBizDataViewFrame(object.getDjId()));
        
        frameList.add(super.getCommonStuffFrame(object.getDjId(),
                null, null));// 附件
        
        //add by lay 2015-11-13 恢复原来的方法
        // 用于展示查看详细信息Lab标签内容 原先tab方式显示
//        frameList.add(this.getAllViewFrame(object.getDjId()));
        //end add
        
        //delete by lay 2015-11-13
      //frameList 页面列表显示
      //frameList=getAllInfoListFrame(frameList,object.getDjId());
        //end delete
        jspInfo = new OptJspInfo();
//        jspInfo.setTitle("会议办理查看");
        jspInfo.setFrameList(frameList);

        // 用于查看流程图
        OaMeetApply apply = oaMeetApplyMag.getObject(object);
        if(null != apply){
            object.setFlowInstId(apply.getFlowInstId());
        }
      //从首页进入
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        
        return "generalOptView";
    }
    private List<OptHtmlFrameInfo> getAllInfoListFrame(List<OptHtmlFrameInfo> frameList,String djId) {
        
        frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
        frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
        //督办催办信息
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.clear();
        filterMap.put("supDjid", object.getDjId());
        oasuplist=oaSuperviseManager.listObjects(filterMap);
        if(null!=oasuplist&&oasuplist.size()>0){
            frameList.add(OaSuperviseAction.getSupListFrame( object.getDjId())) ;
        }
        return frameList;  
    }
   
    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @param djid
     * @return
     */
    @SuppressWarnings("unused")
    private OptHtmlFrameInfo getAllViewFrame(String djid) {
        String itemType = null;
        if(StringUtils.isNotEmpty(djid)){
            itemType = djid.replaceAll("[0-9]+", "");
        }
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("AllInfoFrame");
        stuffsFrameInfo.setFrameSrc("/oa/oaMeetApply!getAllCaseView.do?djId="
                + djid + "&nodeInstId=" + curNodeInstId
                + "&itemType="+itemType);
        stuffsFrameInfo.setFrameHeight("300px");
        return stuffsFrameInfo;
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @return
     */
    public String getAllCaseView() {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("djId", object.getDjId());
        filterMap.put("isdisplay", "1");
        optNewlyIdeaList = optNewlyIdeaManager.listObjects(filterMap);
        long nodeId = (long) 0;
        if (curNodeInstId != null && curNodeInstId != 0) {
            NodeInstance nodeInst = flowEngine.getNodeInstById(curNodeInstId);
            nodeId = nodeInst.getNodeId();
        }
        // 用于初始化查看页面默认显示
        curUrl = "/powerruntime/generalOperator!listIdeaLogs.do?djId="
                + object.getDjId();
        if (optNewlyIdeaList != null && optNewlyIdeaList.size() > 0
                && nodeId != 0) {
            for (OptNewlyIdea bean : optNewlyIdeaList) {
                if (bean.getNodeid() == nodeId) {
                    curUrl = bean.getUrl();
                    break;
                }
            }
        }
        object = oaMeetApplyMag.getObject(object);

        if (null == object.getFlowInstId()) {
            object.setFlowInstId((long) 9999999);
        }
        
        //delete by lay 2015-11-13 没有什么用
        //根据djid获取最大版本号，若不存在版本号在纪要信息不予显示
//        filterMap.put("djid", object.getDjId());
//        FUserDetail fuser = ((FUserDetail) getLoginUser());
//        meetlist=oaMeetMinutesManager.getMaxlist(filterMap, pageDesc, fuser.getUsercode());
        //end delete
        //督办催办信息
        filterMap.clear();
        filterMap.put("supDjid", object.getDjId());
        oasuplist=oaSuperviseManager.listObjects(filterMap);
        request.setAttribute("oasuplist", oasuplist);
        
        //delete by lay 2015-11-13 没有什么用
//        if(meetlist!=null && meetlist.size()>0){
//            request.setAttribute("version", meetlist.get(0).getVersion());
//        }else{
//            request.setAttribute("version", null);
//        }
        //
        request.setAttribute("flowInstId", object.getFlowInstId());
        request.setAttribute("nodeId", nodeId);
        return "meetinginfoView";
    }

    /**
     * 会议室业务数据查看
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameLegend("会议申请信息");
        viewFrameInfo.setFrameSrc("/oa/oaMeetApply!view.do?viewtype=T&djId="
                + djid);
        viewFrameInfo.setFrameHeight("700px");
        return viewFrameInfo;
    }
    
    /**
     * 添加申请名称——形式：关于【xxxx年xx月xx日n人使用会议室】的申请
     * @param object
     * @param optBaseInfo
     */
    public void setTransaffairname(OaMeetApply object, OptBaseInfo optBaseInfo){
        
        StringBuilder s = new StringBuilder("关于【");
        if(null != object && null != optBaseInfo){
            
            Date createTime = object.getCreatetime();
            if(null != createTime){
                s.append(DatetimeOpt.getYear(createTime) + "年" + DatetimeOpt.getMonth(createTime)
                        + "月" + DatetimeOpt.getDay(createTime) + "日");
            }
            Long l = object.getMeetingPersionsNum();
            if(null != l){
                s.append(l + "人");
            }
        }
        s.append("使用会议室】的申请");
        optBaseInfo.setTransaffairname(s.toString());
    }

    /**
     * 保存业务数据
     * 
     * @return
     */

    public String savePermitReg() {
        try {
            // 保存
            FUserDetail fuser = ((FUserDetail) getLoginUser());

            object.setCreater(fuser.getUsercode());
            object.setCreatetime(new Date());
            
//            object.setDepno(fuser.getPrimaryUnit());//会议部门页面可选
            FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
            object.setOptid(flowDescribe.getOptId());
            object.setFlowcode(flowCode);
            object.setBiztype("F");//
            object.setBizstate("F");
            // 标记为暂存
            object.setState("6");
            object.setCpBegTime(object.getPlanBegTime());
            object.setCpEndTime(object.getPlanEndTime());
            
            addressBookRelectionManager.deleteuser(object.getDjId());
            
            userValue=object.getAttendingLeadercodes();
            // 把参会人员记录到中间表m_address_book_relection
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection info = new AddressBookRelection();
                    info.setAddrbookid(object.getDjId());
                    info.setShareman(values[i]);
                    info.setBizType("0");
                    addressBookRelectionManager.saveObject(info);
                }
            }
            //保存
            String []reqRemark =request.getParameterValues("reqRemark");
            StringBuilder sb =new StringBuilder();
            if(reqRemark!=null && reqRemark.length>0){
                for(String s :reqRemark){
                    sb.append(s+",");
                }
            }//去掉最后一个逗号
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            } 
            object.setReqRemark(sb.toString());
           
            //如果是一般会议室申请保存 需清理视频会议室信息
            if("O".equals(object.getMeetType())){
                object.clearTVProperties();
            }//为视频会议时保存提交的参会部门
            //if(StringUtils.isNotBlank(retValue)&&"P".equals(object.getMeetType())){
                //object.setAttendingUnits(retValue);
            //}
            oaMeetApplyMag.saveObject(object);

            object = oaMeetApplyMag.getObjectById(object.getDjId());

            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                
               // 添加申请名称
//                this.setTransaffairname(object, optBaseInfo);
                optBaseInfo.setTransaffairname(object.getTitle());
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                optBaseInfo.setOrgname(fuser.getUnitName());
          
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(DatetimeOpt.currentUtilDate());
                optBaseInfo.setCreateuser(object.getCreater());
                optBaseInfoManager.saveObject(optBaseInfo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return this.list();
    }

    /**
     * 保持并提交流程
     * 
     * @return
     */
    public String saveAndSubmit() {
        // 保存
        FUserDetail fuser = ((FUserDetail) getLoginUser());

        object.setCreater(fuser.getUsercode());
        //object.setDepno(fuser.getPrimaryUnit());//注释掉，页面的会议部门不是当前用户的主机构 20151112
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        object.setOptid(flowDescribe.getOptId());
        object.setBiztype("F");//
        object.setBizstate("F");
        object.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        object.setFlowcode(flowCode);
        object.setCreatetime(DatetimeOpt.currentUtilDate());
        // 申请isuse状态为1
        object.setState("1");
        oaMeetApplyMag.saveObject(object);

        object = oaMeetApplyMag.getObjectById(object.getDjId());

       
       
        if (object != null) {
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                
                // 添加申请名称
//                this.setTransaffairname(object, optBaseInfo);
                
                optBaseInfo.setTransaffairname( object.getTitle());
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                optBaseInfo.setOrgname(fuser.getUnitName());
          
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(object.getCreatetime());
                optBaseInfo.setCreateuser(object.getCreater());
                optBaseInfoManager.saveObject(optBaseInfo);
            }
            
            
            
            FlowInstance flowInst = flowEngine.createInstance(flowCode,object.getTitle(), object.getDjId(),
                    fuser.getUsercode(), fuser.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;
            curFlowInstId = flowInstId;

            object.setFlowInstId(flowInstId);
            object.setBiztype("W");// 等待审核
            object.setBizstate("W");
            addressBookRelectionManager.deleteuser(object.getDjId());
            
            userValue=object.getAttendingLeadercodes();//与会领导
            // 把参会人员记录到中间表m_address_book_relection
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection info = new AddressBookRelection();
                    info.setAddrbookid(object.getDjId());
                    info.setShareman(values[i]);
                    info.setBizType("0");
                    addressBookRelectionManager.saveObject(info);
                }
            }
            
            object.setCpBegTime(object.getPlanBegTime());
            object.setCpEndTime(object.getPlanEndTime());
            
            oaMeetApplyMag.saveObject(object);

            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            //将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId,"jbr",fuser.getUsercode(),"登记人员");
            
            saveIdeaInfo();

            // 将登记部门纳入权限引擎
            //flowEngine.assignFlowOrganize(flowInstId, "FZBM",
            //fuser.getPrimaryUnit());
        }
       // submitNode();
        
      //向Rtx推送消息
        List<VuserTaskListOA> list=optBaseInfoManager.getTasksByNodeInstId(curNodeInstId);
        this.SendRtxMsn(object.getDjId(),list);
        return "refreshTasks";
    }
    /**
     * 保存用车发起步骤过程日志信息
     */
    public void saveIdeaInfo() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();

        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        FUnitinfo fUnitinfo = sysUnitManager.getObjectById(loginInfo.getPrimaryUnit().trim());
        if (fUnitinfo == null) {
            fUnitinfo = new FUnitinfo();
        }
        optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

        optIdeaInfo.setTransidea("会议申请");
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(0L);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename("会议申请");
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea("会议申请");

        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }
   
    /**
     * 向RTX信息表插入待发送的rtx待办信息
     * @param djId
     * @param list
     */
    private boolean SendRtxMsn(String djId, List<VuserTaskListOA> list) {
            boolean returnvalue=true;
            if(list!=null && list.size()>0){
            //只有在现场环境才发送消息
            String messwitch = CodeRepositoryUtil.getValue("SYSPARAM",
                    "MESSWITCH");    
            if(StringUtils.isBlank(messwitch) || "F".equals(messwitch)){
                returnvalue=false;
            }
            if (returnvalue) {

                FUserDetail loginInfo = (FUserDetail) getLoginUser();
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());          

                String url = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + request.getContextPath();
                url += "/page/rtx/signauth.jsp?url=dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000857";

                for (VuserTaskListOA task : list) {
                    FUserinfo userInfo = sysUserManager.getObjectById(task
                            .getUserCode());
                    String loginName = userInfo.getLoginname();

                    RtxInfo rtxInfo = new RtxInfo();
                    rtxInfo.setNo(rtxInfoManager.getNextkey());
                    rtxInfo.setDjId(object.getDjId());
                    rtxInfo.setNodeId(task.getNodeInstId());
                    rtxInfo.setCreateDate(new Date(System.currentTimeMillis()));
                    rtxInfo.setCreateUserCode(loginInfo.getUsercode());
                    rtxInfo.setCreateUserName(loginInfo.getUsername());
                    rtxInfo.setInfoContent("[" + optBaseInfo.getTransaffairname() + "|"
                            + url + "]");
                    rtxInfo.setReceiveUserCode(task.getUserCode());
                    rtxInfo.setReceiveUserName(loginName);
                    rtxInfo.setIsSend("0");
                    // sender.sendMessage("9999999"/*系统管理员*/,
                    // loginName, "您有一个新待办：",
                    // "["+optBaseInfo.getTransaffairname()+"|"+url+"]");
                    rtxInfoManager.saveObject(rtxInfo);
                }         
            }
            }
            return returnvalue;
    }
    /**
     * 保存回退登记骤过程日志信息
     */
    private String nodeCode;

    public void saveIdeaInfo(String nodename) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();

        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        FUnitinfo fUnitinfo = sysUnitManager.getObjectById(loginInfo
                .getPrimaryUnit().trim());
        if (fUnitinfo == null) {
            fUnitinfo = new FUnitinfo();
        }
        optIdeaInfo.setUnitname(fUnitinfo.getUnitname());
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）
        optIdeaInfo.setTransidea(nodename);

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(curNodeInstId);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename(nodename);
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea(nodename);
        procInfo.setNodeCode(nodeCode);
        procInfo.setIdeacode(ideacode);
        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }

    /**
     * 查看
     * 
     */
    private OaMeetApply oaMeetApply;

    //private String contextPath;

    public String view() {
        super.view();
        oaMeetApply = oaMeetApplyMag.getObjectById(object.getDjId());
        
        //视频会议  会议安排---ideacode=T --通信中心
        //if(oaMeetApply!=null&&"P".equals(oaMeetApply.getMeetType())){
           // request.setAttribute("isBM", "T");
            //add by lay
            //参会单位显示
           getUnitNames(oaMeetApply.getAttendingUnits());
            /*if(oaMeetApply.getAttendingUnits()!=null && oaMeetApply.getAttendingUnits()!=""){
                 String [] retName=oaMeetApply.getAttendingUnits().split(",");
                StringBuilder sb =new StringBuilder();
                for(String rtName : retName){
                    String retNameValue = CodeRepositoryUtil.getValue("unitcode", rtName);
                    sb.append(retNameValue+",");
                }
                if(sb.length()>0){
                    sb.deleteCharAt(sb.length()-1);
                }
                retValue=sb.toString();
            }*/
            //end add
       // }
        
        if(oaMeetApply!=null&&StringUtils.isNotBlank(oaMeetApply.getReqRemark())){
            String [] dealtype =oaMeetApply.getReqRemark().split(",");
            StringBuilder sb =new StringBuilder();
            if(dealtype!=null && dealtype.length>0){
                for(String s:dealtype){
                    sb.append(CodeRepositoryUtil.getDataPiece("meetStandard", s.trim())+",");  
                }
            }
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            }
            reqRemarkNew=sb.toString();
        }
        return "view";
    }

    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
    
    @SuppressWarnings("unused")
    private String getLoginUserCode() {
        if(null==(FUserinfo) this.getLoginUser()){
            return  "login";
        }else{
            return ((FUserinfo) this.getLoginUser()).getUsercode();
        }
    }
    public String delete() {
        super.delete();

        return "delete";
    }

    /**
     * 查看某一会议室的历史安排记录
     * 
     * @return
     * @throws
     */

    public String meetplan() {

        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("NP_", true);

        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList = oaMeetApplyMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();

        return "meetplan";
    }

    /**
     * 时间段是否空闲
     * 
     * @return
     * @throws IOException
     */
    public String isTFree() throws IOException {
        json = true;

        if (!isExist()) {
            json = false;
            return "json";
        }
        return "json";

    }

    /**
     * 验证申请时间是否空闲 （未用：）validate时使用
     * 
     * @throws IOException
     */
    public void isFree() throws IOException {

        ServletActionContext.getResponse().getWriter().print(isExist());

    }
    public void isInuser() throws IOException {

        ServletActionContext.getResponse().getWriter().print(isInuse());

    }
    /**
     * 判断申请时间或者调配时间段是否空闲 优先级：已调配作为判断条件
     * 如果djId已存在，在编辑验证时需要排除当前记录
     * 
     * @return
     */
    private boolean isInuse() {
        List<OaMeetApply> applylist = new ArrayList<OaMeetApply>();
        if (null != object.getDoBegTime() && null != object.getDoEndTime()) {
            applylist = oaMeetApplyMag.getApplylistNew(object.getDjId(),
                    object.getMeetingNo(), object.getDoBegTime(),
                    object.getDoEndTime(), "2");
        }
        if (applylist != null && applylist.size() > 0) {
            return false;

        } else {
            return true;

        }
    }

    /**
     * 判断申请时间或者调配时间段是否空闲 优先级：同一条申请，若已被调配则以调配时间为占用时间，否则按申请时间
     * 如果djId已存在，在编辑验证时需要排除当前记录
     * 
     * @return
     */
    private boolean isExist() {
        List<OaMeetApply> applylist = new ArrayList<OaMeetApply>();
       
        // 调配时的验证(传入时间字段不一样)
        if (null != object.getDoBegTime() && null != object.getDoEndTime()) {
            applylist = oaMeetApplyMag.getApplylistNew(object.getDjId(),
                    object.getMeetingNo(), object.getDoBegTime(),
                    object.getDoEndTime(), "2");
        }else{ // 申请时的验证
            if (null != object.getPlanBegTime() && null != object.getPlanEndTime()) {
                applylist = oaMeetApplyMag.getApplylistNew(object.getDjId(),
                        object.getMeetingNo(), object.getPlanBegTime(),
                        object.getPlanEndTime(), "2");
            }
        }

        if (applylist != null && applylist.size() > 0) {
            return false;

        } else {
            return true;

        }

    }

    /**
     * 列表显示在日历上 默认查询时间
     */
    public String calendarView() {
        // 在会议室里instance只有一个值，为方便查询，从calendar中copy代码出来并简化
        // String instance = request.getParameter("instance");
        // List<String> instances = Arrays.asList(instance.split(","));
        Map<String, Boolean> inst = new HashMap<String, Boolean>();

        Map<String, String> values = new LinkedHashMap<String, String>();

        values.put("oaMeetApplyManager", "会议申请");

        inst.put("oaMeetApplyManager", true);

        // 会议室列表:启用(oaMeetinfolist未用到)
//        Map<String, Object> filterMapHys = new HashMap<String, Object>();
//        filterMapHys.put("isuse", "T");
//        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);

        request.setAttribute("values", values);
        request.setAttribute("inst", inst);

        return "calendarView";
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

        // 获得开始结束时间
        Date start = new Date(
                Long.parseLong((String) params.get("start")) * 1000);
        Date end = new Date(
                Long.parseLong((String) params.get("end")) * 1000 - 1000);
        //String meetingNo = (String) params.get("meetingNo");

        /*List<OaMeetApply> applylist = oaMeetApplyMag.getApplylist(null,
                meetingNo, start, end, "1,2");*/
        List<OaMeetApply> applylist = oaMeetApplyMag.getApplylistV2(start, end, "1,2");

        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(applylist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }

    private void getJsonResult(List<OaMeetApply> meetApplyList,
            List<Map<String, Object>> jsonResult, Map<String, Object> params) {

        // 添加实例，操作相关实现类接口
        List<Map<String, Object>> result = putDataToMap(meetApplyList, params);

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
            List<OaMeetApply> meetApplyList, Map<String, Object> params) {
        String contextPath = (String) params.get("contextPath");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (OaMeetApply data : meetApplyList) {
            Map<String, Object> task = new HashMap<String, Object>();
            // 用户owner
            String owner = CodeRepositoryUtil.getValue("usercode",
                    data.getCreater());

            task.put("id", data.getDjId());

            String title ="";
            title += "\n" +   data.getOaMeetinfo().getMeetingName();
            title+="\n" +  '[' + owner + "] " +  data.getTitle();
//            if (30 < title.length()) {
//                title = title.substring(0, 30) + "...";
//            }

            task.put("title", title);

            // 显示标记颜色 申请未确认
            if (StringUtils.isNotBlank(data.getState())
                    && "1".equals(data.getState())) {
                task.put("tasktag", "yellow");
                // 确认后按调配时间显示
            }
            if (StringUtils.isNotBlank(data.getState())
                    && "2".equals(data.getState())) {
                task.put("tasktag", "green");
               

            }// 使用记录
            /*if (StringUtils.isNotBlank(data.getState())
                    && "3".equals(data.getState())) {
                task.put("tasktag", "purple");
                task.put("start", DatetimeOpt.convertDateToString(
                        data.getBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

            }*/
            task.put("start", DatetimeOpt.convertDateToString(
                    data.getCpBegTime(), "yyyy-MM-dd HH:mm:ss"));
            task.put("end", DatetimeOpt.convertDateToString(
                    data.getCpEndTime(), "yyyy-MM-dd HH:mm:ss"));
            task.put("textColor","black");

            // 可编辑
            task.put("editable", true);

            task.put("allDay", false);

            String url = "oa/oaMeetApply!generalOptView.do?djId="
                    + data.getDjId() + "&nodeInstId=0";// dijd

            if (StringUtils.isNotBlank(url)) {
                StringBuilder sb = new StringBuilder();
                // List<String> ignore = Arrays.asList("contextPath",
                // "usercode");
                // for (Entry<String, Object> entry : params.entrySet()) {
                // if (ignore.contains(entry.getKey())) {
                // continue;
                // }
                // sb.append('&' + entry.getKey() + '=' + entry.getValue());
                // }
                task.put("url", url + sb);
            }
            result.add(task);
        }

        return result;
    }
    /**
     * 会议室时间查看
     * 
     * @param djid
     * @return
     */
    public static OptHtmlFrameInfo getBizTimeViewFrame(String djId) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameLegend("事项信息");
        /*viewFrameInfo
                .setFrameSrc("/wwd/srPermitApply!viewItem.do?djId=" + djid);*/
        viewFrameInfo
        .setFrameSrc("/oa/oaMeetApply!viewDateFrame.do?djId=" + djId);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
    }
    public String viewDateFrame(){ 
        String djId = (String)request.getParameter("djId");
        OaMeetApply oaMeetApply = oaMeetApplyMag.getObjectById(djId);
        request.setAttribute("oaMeetApply", oaMeetApply);
        List<OaMeetApply> applylist = new ArrayList<OaMeetApply>();
        if (null != oaMeetApply.getPlanBegTime() && null != oaMeetApply.getPlanEndTime()) {
            //已调配但未使用的会议室列表
            applylist = oaMeetApplyMag.getApplylistNew(oaMeetApply.getDjId(),
                    oaMeetApply.getMeetingNo(), oaMeetApply.getPlanBegTime(),
                    oaMeetApply.getPlanEndTime(), "2");
        }
        if (applylist != null && applylist.size() >= 1){
            request.setAttribute("flag", true);
        }
        request.setAttribute("applylist", applylist);
        return "oaMeetApplyviewDateFrame";
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
            String djId = request.getParameter("djId");
            paramMap.put("djId", new String(djId.getBytes("ISO-8859-1"),
                    "GBK").trim());
           
            addressBookRelectionManager.deleteuser(djId);

            ((JSONObject) json).put("status", "none" );
        } catch (Exception e) {
            log.info(e);
            ((JSONObject) json).put("status", "failed");
        }

        return "json";
    }

   /**
    * 取消会议申请
    * 1.修改meetapply状态state 为7取消
    * 2.修改流程状态为结束
    * 3.发送通知给会议相关人员：参会人员+流程参与人员，通知类型：邮件\提醒(rtx)
    */
    public String cancleApply(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        if(user==null){
            return "login";}
        else{
            if (null!=object &&StringUtils.isNotEmpty(object.getDjId())) {
                OaMeetApply oaMeetApply = oaMeetApplyMag.getObjectById(object.getDjId());
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());
                oaMeetApply.setState("7");//取消
                oaMeetApply.setBizstate("C");//业务状态
                oaMeetApply.setBiztype("C");//业务类别
                oaMeetApply.setSolvestatus("C");//申请状态-取消
                oaMeetApplyMag.saveObject(oaMeetApply);
                flowManager.stopInstance(optBaseInfo.getFlowInstId(), user.getUsercode(), "取消会议申请");
                
         
            //1.获取参会人员
            getUservalues("cancle");
            
            String mesgTitle="与您相关的一项会议申请["+oaMeetApply.getTitle()+"]已经被取消,请您及时关注。";
            String mesgContent=oaMeetApply.getTitle()+"于"+DatetimeOpt.convertDatetimeToString(oaMeetApply.getPlanBegTime())+"到"+DatetimeOpt.convertDatetimeToString(oaMeetApply.getDoEndTime())+"取消举行。";
           
            //2.输送邮件数据
            this.sendInnermesg(userValue,mesgTitle,mesgContent);
            
            //3.删除日程安排数据
            oaScheduleManager.deleteByDjId(oaMeetApply.getDjId());
            
            //4.发送提醒
            oaRemindInformationManager.sendOaRemindInformation(oaMeetApply.getDjId(), user.getUsercode(), userValue, mesgTitle, mesgContent);
            
            //5.rtx发送通知
//            this.saveNewNotice(oaMeetApply.getDjId(),userValue,mesgTitle,mesgContent);
            } 
        }
        
        return list();
    }
    /**
     * 向RTX发送通知（不包含链接）
     * @param noticeType
     * @param userValue
     * @param mesgTitle
     * @param mesgContent
     */
    private void saveNewNotice(String djId ,String userValue,String mesgTitle,String mesgContent) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
       if(null!=loginInfo){
           
           if (StringUtils.isNotBlank(userValue)) {
               boolean returnvalue=true;
               //只有在现场环境才发送消息
               String messwitch = CodeRepositoryUtil.getValue("SYSPARAM",
                       "MESSWITCH");    
               if(StringUtils.isBlank(messwitch) || "F".equals(messwitch)){
                   returnvalue=false;
               }
               if (returnvalue) {
                   String[] values = this.userValue.split(",");// 分割字符串
                   for (int i = 0; i < values.length; i++) {
                       FUserinfo userInfo = sysUserManager.getObjectById(values[i]);//验证系统内用户
                       if(userInfo!=null){
                       RtxInfo rtxInfo = new RtxInfo();
                       rtxInfo.setNo(rtxInfoManager.getNextkey());
                       rtxInfo.setDjId(djId);
                       rtxInfo.setNodeId(null);
                       rtxInfo.setCreateDate(new Date(System.currentTimeMillis()));
                       rtxInfo.setCreateUserCode(loginInfo.getUsercode());
                       rtxInfo.setCreateUserName(loginInfo.getUsername());
                       rtxInfo.setInfoContent("[" + mesgTitle +  "]");
                       rtxInfo.setReceiveUserCode(values[i]);
                       rtxInfo.setReceiveUserName(CodeRepositoryUtil.getValue("usercode", values[i]));
                       rtxInfo.setIsSend("0");
                       rtxInfoManager.saveObject(rtxInfo);
                       }
                    }
                 }
          }
       }
    }
    
    
    /*********************************** getter()、setter() ****************************************/

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }

    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }

    public List<OptNewlyIdea> getOptNewlyIdeaList() {
        return optNewlyIdeaList;
    }

    public void setOptNewlyIdeaList(List<OptNewlyIdea> optNewlyIdeaList) {
        this.optNewlyIdeaList = optNewlyIdeaList;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    public FlowDefine getFlowDefine() {
        return flowDefine;
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }

    public static Log getLog() {
        return log;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
        this.optNewlyIdeaManager = optNewlyIdeaManager;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public OaMeetinfoManager getOaMeetinfoManager() {
        return oaMeetinfoManager;
    }

    public void setOaMeetinfoManager(OaMeetinfoManager oaMeetinfoManager) {
        this.oaMeetinfoManager = oaMeetinfoManager;
    }

    public List<OaMeetinfo> getOaMeetinfolist() {
        return oaMeetinfolist;
    }

    public void setOaMeetinfolist(List<OaMeetinfo> oaMeetinfolist) {
        this.oaMeetinfolist = oaMeetinfolist;
    }

    public OaMeetApply getOaMeetApply() {
        return oaMeetApply;
    }

    public void setOaMeetApply(OaMeetApply oaMeetApply) {
        this.oaMeetApply = oaMeetApply;
    }

    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }
    
    public OaMeetMinutesManager getOaMeetMinutesManager() {
        return oaMeetMinutesManager;
    }

    public void setOaMeetMinutesManager(OaMeetMinutesManager oaMeetMinutesManager) {
        this.oaMeetMinutesManager = oaMeetMinutesManager;
    }
    public List<VoaMeetMinute> getMeetlist() {
        return meetlist;
    }

    public void setMeetlist(List<VoaMeetMinute> meetlist) {
        this.meetlist = meetlist;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }
    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }
    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }
    public void setOaScheduleManager(OaScheduleManager oaScheduleManager) {
        this.oaScheduleManager = oaScheduleManager;
    }
    public OaMeetroomApplyManager getOaMeetroomApplyManager() {
        return oaMeetroomApplyManager;
    }
    public void setOaMeetroomApplyManager(OaMeetroomApplyManager oaMeetroomApplyManager) {
        this.oaMeetroomApplyManager = oaMeetroomApplyManager;
    }
    public OaMeetroomApply getOaMeetroomApply() {
        return oaMeetroomApply;
    }
    public void setOaMeetroomApply(OaMeetroomApply oaMeetroomApply) {
        this.oaMeetroomApply = oaMeetroomApply;
    }
    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }
    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }
    public String getOtherItem() {
        return otherItem;
    }
    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }
    public List getReqRemarkList() {
        return reqRemarkList;
    }
    public void setReqRemarkList(List reqRemarkList) {
        this.reqRemarkList = reqRemarkList;
    }
    public String getReqRemarkNew() {
        return reqRemarkNew;
    }
    public void setReqRemarkNew(String reqRemarkNew) {
        this.reqRemarkNew = reqRemarkNew;
    }
    public List<FUnitinfo> getListSelectOrgList() {
        return listSelectOrgList;
    }

    public void setListSelectOrgList(List<FUnitinfo> listSelectOrgList) {
        this.listSelectOrgList = listSelectOrgList;
    }
    public String getRetValue() {
        return retValue;
    }
    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }
    public List<VOaMeetApplyList> getOaMeetApplyList() {
        return oaMeetApplyList;
    }
    public void setOaMeetApplyList(List<VOaMeetApplyList> oaMeetApplyList) {
        this.oaMeetApplyList = oaMeetApplyList;
    }
    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }
    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }    
}
