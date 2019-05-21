/**
 * 
 */
package com.centit.app.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.app.exception.PublicInfoException;
import com.centit.app.po.Dashboard;
import com.centit.app.po.OptDashboardActive;
import com.centit.app.po.OptDashboardLayout;
import com.centit.app.po.OptLayoutMethod;
import com.centit.app.po.Publicinfo;
import com.centit.app.po.VOaScheduleRemind;
import com.centit.app.service.DashboardManager;
import com.centit.app.service.OptDashboardActiveManager;
import com.centit.app.service.OptDashboardLayoutManager;
import com.centit.app.service.OptLayoutMethodManager;
import com.centit.app.service.PublicinfoManager;
import com.centit.app.service.VOaScheduleRemindManager;
import com.centit.core.action.BaseAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VDispatchDocList;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.VDispatchDocListManager;
import com.centit.dispatchdoc.service.VIncomeDocListManager;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.po.OaFeedback;
import com.centit.oa.po.OaInformation;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.OaServiceentrance;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.po.VOaInformation;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.oa.service.OaFeedbackManager;
import com.centit.oa.service.OaInformationManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaScheduleManager;
import com.centit.oa.service.OaServiceentranceManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.OaUnitIncomedocManager;
import com.centit.oa.service.OptFileTransferReceiveManager;
import com.centit.oa.service.OptFileTransferSendManager;
import com.centit.oa.service.VOaInformationManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.powerruntime.po.VOptApplyTaskList;
import com.centit.powerruntime.po.VProcAttention;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.MyHome;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserSettingManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateTimeUtil;
import com.opensymphony.xwork2.ActionContext;

public class DashboardAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private InnermsgManager innermsgManager;
//    private SysUserManager sysUserManager;
//    private SysUnitManager sysUnitManager;
//    private OutwayManager outwayManager;
//    private ComplaintManager complaintManager;
//    private SuperviseOnworkManager superviseOnworkManager;

    private UserSettingManager userSettingMgr;

    private List<Innermsg> msgList;
    private List<Innermsg> msgrlList;//日历邮件

    private List<MyHome> myHomeList;
    
    private Object json;

    /**
     * 栖霞系统start
     */
    private String sysType;
    private OptProcAttentionManager optProcAttentionManager;
    private List<VOptApplyTaskList> xkdbList;
    private List<VProcAttention> attList;
    
    private OaInformationManager oaInformationManager;
    private OaFeedbackManager oaFeedbackManager;
    private OaScheduleManager oaScheduleManager;

    private DashboardManager dashboardManager;
    private SysUserManager sysUserManager;
    private OaUnitIncomedocManager oaUnitIncomedocManager;
    private PublicinfoManager publicinfoManager;
    //日历待办 ===
    private VuserTaskListOAManager vuserTaskListOAManager;
    private OaSuperviseManager oaSuperviseManager;
    private OaRemindInformationManager oaRemindInformationManager;//消息
    private List<OaRemindInformation> oaRemindInformationList;//消息list
    //=========

    private List<OaSchedule> ldrcList;
    private List<OaSchedule> gerenrcList;
    private List<VOaScheduleRemind> gerenrcrlList;//日历个人日程提醒
    private List<OaInformation> xwList;

    private List<VOaInformation> tzggList;
    private List<OaInformation> tzggrlList;//日历通知公告
    private  List<OaFeedback> fkListl;
    private  List<Dashboard> dashboardList;
    
    private List<VIncomeDocList> bmswList;
    private List<Publicinfo> fileList;
    private List<Publicinfo> unitFileList;
    private List<Publicinfo> personalFileList;
    
    private String userRank=null;//登录用户行政级别 厅长 or 处长 or 一般职员
    /**
     * end
     */

    private OaServiceentranceManager oaServiceentranceManager;
    private List<OaServiceentrance> oaserviceList;//业务入口
    private List<VuserTaskListOA> vuserTaskListrl;//待办日历
    
    private List<VuserTaskListOA> jjcqList;
    private List<VuserTaskListOA> ycqList;

    private OptFileTransferReceiveManager optFileTransferReceiveMgr;
    private OptFileTransferSendManager optFileTransferSendMgr;
    private OptDashboardActiveManager optDashboardActiveManager;
    private OptDashboardLayoutManager optDashboardLayoutManager;
    private OptLayoutMethodManager optLayoutMethodManager;
    /**
     * 
     * @return
     * @throws Exception
     */
    public String show() throws Exception {
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        if(uinfo!=null && !StringUtils.isBlank(uinfo.getUsercode())){
        myHomeList = userSettingMgr.queryMyHomeByUsercode(uinfo.getUsercode());
        JSONArray data = new JSONArray();
        data.addAll(myHomeList);
        request.setAttribute("data", data);

        Map<String, Object> session = ActionContext.getContext().getSession();
        sysType = (String) session.get("sysType");

        //oa待办
       //通知公告
       // this.Tzgg_dashboard();
        //最新新闻
        //this.xw_dashboard();
        //情况反馈
        //this.qkfk_dashboard();
        //领导日程安排
        this.ldrc_dashboard();
        
        //首页待办表格
        this.sydb_dashboard();
        
        //首页未读件数据
        this.unreadEmail_dashboard();
        
        // 部门收文数据
        this.bmsw_dashboard();
        
        // 文档
        this.file_dashboard();
        
        //业务入口
        this.getoaServiceEntrance();
        
        //新待办
        //this.sydb_new();
        
        userRank=getUserRankByUsercode(((FUserDetail) getLoginUser()).getUsercode());
        
        return "dashboard";
        }else{
           return "login"; 
        }
    }
    
    public String showV2(){
        
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        if (uinfo != null && !StringUtils.isBlank(uinfo.getUsercode())) {
            myHomeList = userSettingMgr.queryMyHomeByUsercode(uinfo
                    .getUsercode());
        }
        JSONArray data = new JSONArray();
        data.addAll(myHomeList);
        request.setAttribute("data", data);

        Map<String, Object> session = ActionContext.getContext().getSession();
        sysType = (String) session.get("sysType");
        getDashboardNum(null);
        userRank=getUserRankByUsercode(((FUserDetail) getLoginUser()).getUsercode());
        return "dashboardV2";

    }
    
    public String showV3(){
        
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        if (uinfo != null && !StringUtils.isBlank(uinfo.getUsercode())) {
            String usercode = uinfo.getUsercode();
            myHomeList = userSettingMgr.queryMyHomeByUsercode(usercode);
            JSONArray data = new JSONArray();
            data.addAll(myHomeList);
            request.setAttribute("data", data);
            
            Map<String, Object> session = ActionContext.getContext().getSession();
            sysType = (String) session.get("sysType");
            getDashboardNum(null);
            // 设置页面信息
            this.setDashboardInfo(uinfo.getUsercode());
            
        }
        return "dashboardV3";
    }
    
    /**
     * 中间层次用户登录界面
     * @return
     */
    public String showMiddleUnit(){
        
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        if (uinfo != null && !StringUtils.isBlank(uinfo.getUsercode())) {
           
            
        }
        return "dashboardV4";
    }
    
    public String showV5(){
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        OptDashboardActive dashboardActive = optDashboardActiveManager.findActive(uinfo.getUsercode());
        OptDashboardLayout optDashboardLayout = null;
        if(dashboardActive!=null){
             optDashboardLayout = optDashboardLayoutManager.getObjectById(dashboardActive.getLayoutId());
        }else{
           List<OptDashboardLayout> optDashboardLayoutList = optDashboardLayoutManager.listObjects();
           if(optDashboardLayoutList!=null && optDashboardLayoutList.size() > 0){
               for(OptDashboardLayout item : optDashboardLayoutList){
                   if("BUILTIN".equals(item.getLayoutType())){
                       optDashboardLayout = item;
                       break;
                   }
               }
           }
        }
        if(optDashboardLayout!=null){
            request.setAttribute("optDashboardLayout", optDashboardLayout);
            OptLayoutMethod optLayoutMethod = optLayoutMethodManager.getObjectById(optDashboardLayout.getLayoutMethodId());
            request.setAttribute("optLayoutMethod", optLayoutMethod);
        }
        return "dashboardV5";
    }
    
    public String nbzx_dashboard() {

        Map<String, Object> filterMap = new HashMap<String, Object>();
        

        filterMap.put("state", "1");
        filterMap.put("NP_bettentime", true);//查询有效期内
        tzggList = voaInformationManager.listObjects(filterMap);
        
        this.setReadStateOfInfos(tzggList);
        
        return "tzgg";
    }

    public void setReadStateOfInfos(List<VOaInformation> infoList){

        FUserDetail uinfo = (FUserDetail) getLoginUser();
        // 添加未读和已读状态
        if(null != infoList && !infoList.isEmpty()){
            
            for(int i=0;i<infoList.size();i++){
                VOaInformation oa=infoList.get(i);
                
                if(null != uinfo){
                    
                    UserbizoptLog uboptlog=userbizoptLogManager.listObject(oa.getNo(), uinfo.getUsercode());
                    oa.setInfoType(CodeRepositoryUtil.getValue("infoType", oa.getInfoType()));
                    if(uboptlog!=null){
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "T"));//已读
                        //判断是否从首页未读进入，如果是就把已读删除
                    }else{
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "F"));//未读
                    }
                }
            }  
        }
    }
    /**
     * 设置页面信息
     * @param usercode
     */
    public void setDashboardInfo(String usercode){
       
       // userRank = this.getUserrank(usercode);
     
        FUserunit dept = sysUserManager.getUserPrimaryUnit(usercode);
        String sParentUnit = dept.getUnitcode();
        request.setAttribute("unitcode", sParentUnit);
        
        request.setAttribute("unReadRemindNum", dashboardManager.getUnreadRemind(usercode));
        request.setAttribute("unReadBulletinNum", dashboardManager.getUnReadBulletinNum(usercode));
        request.setAttribute("swTaskNum", dashboardManager.getSWTaskNum(usercode));
        request.setAttribute("fwTaskNum", dashboardManager.getFWTaskNum(usercode));
        request.setAttribute("unReadEmailNum", dashboardManager.getUnreadOuterEmailNum(usercode));
        request.setAttribute("lwTaskNum", dashboardManager.getLWTaskNum(usercode));
       //判断角色是否为主席，如果是拟发文、阅办文  变为  “审阅”、“审批"
        request.setAttribute("userCode", usercode);
         List<FRoleinfo> userroles = sysUserManager.getSysRolesByUsid(usercode);
        if(userroles!=null&&userroles.size()>0){
            for(FRoleinfo f:userroles){
                if("1-zx".equals(f.getRolecode())){
                    request.setAttribute("isLD", "true");
                }
            }
        }
    }
    
    private Map<String,Long> ajaxDashboardNumMap;//首页未读数
    
    public Map<String, Long> getAjaxDashboardNumMap() {
        return ajaxDashboardNumMap;
    }

    public void setAjaxDashboardNumMap(Map<String, Long> ajaxDashboardNumMap) {
        this.ajaxDashboardNumMap = ajaxDashboardNumMap;
    }
//通过ajax获取首页未读数
    public String ajaxDashboardNum(){
        FUserDetail uinfo = (FUserDetail) getLoginUser();
        if (uinfo != null && !StringUtils.isBlank(uinfo.getUsercode())) {
            String usercode = uinfo.getUsercode();
            ajaxDashboardNumMap=new HashMap<String, Long>();
            ajaxDashboardNumMap.put("unReadRemindNumSpan", dashboardManager.getUnreadRemind(usercode));
            ajaxDashboardNumMap.put("unReadBulletinNumSpan", dashboardManager.getUnReadBulletinNum(usercode));
            ajaxDashboardNumMap.put("swTaskNumSpan", dashboardManager.getSWTaskNum(usercode));
            ajaxDashboardNumMap.put("fwTaskNumSpan", dashboardManager.getFWTaskNum(usercode));
            ajaxDashboardNumMap.put("unReadEmailNumSpan", dashboardManager.getUnreadOuterEmailNum(usercode));
            ajaxDashboardNumMap.put("lwTaskNumSpan", dashboardManager.getLWTaskNum(usercode));
        }
        return "ajaxDashboardNum";
    }
    
    /**
     * 根据usercode 获取用户行政职务
     * @param usercode
     * @return
     */
    private String getUserRankByUsercode(String  usercode){
        
        FUserunit userUnit =sysUserManager.getUserPrimaryUnit(usercode);
        
        if(null==userUnit){
            userRank=null;
        }else
        {
            String userUnitRank=userUnit.getUserrank();
            if("ZT".equals(userUnitRank)||"FT".equals(userUnitRank)){
            userRank="TZ";//厅长级别
            }else if("ZC".equals(userUnitRank)||"FC".equals(userUnitRank)){
            userRank="CZ";//处长级别
            }else{
            userRank=null;//其他（科员，办事员等）--/科长
            }
        }
        return userRank;
        
    }
    
    /**
     * 根据usercode获取行政职务
     * @param usercode
     * @return
     */
    public String getUserrank(String usercode){
        
        FUserunit userUnit =sysUserManager.getUserPrimaryUnit(usercode);
        
        if(null != userUnit){
            return userUnit.getUserrank();
        }else {
            return null;
        }
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }
    private VOaInformationManager voaInformationManager;
    public void setVoaInformationManager(VOaInformationManager voaInformationManager) {
        this.voaInformationManager = voaInformationManager;
    }

    //通知公告
    @SuppressWarnings("unchecked")
    private void Tzgg_dashboard() {
       // FUserDetail fuser =((FUserDetail)getLoginUser());
     
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        filterMap.put("GRODE_BY", " readstate asc ");
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("state", "1");
        filterMap.put("infoType","info" );
        tzggList=(List<VOaInformation>) getSubList(voaInformationManager.listObjects(filterMap, pageDesc), 7);
        
    }
    
  
    //最新新闻
    @SuppressWarnings("unchecked")
    private void xw_dashboard() {
        //FUserDetail fuser =((FUserDetail)getLoginUser());
     
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("state", "1");
        filterMap.put("infoType","news" );
        xwList=(List<OaInformation>) getSubList(oaInformationManager.listObjects(filterMap, pageDesc), 7);
        
    }
    //情况反馈
    @SuppressWarnings("unchecked")
    private void qkfk_dashboard() {
       // FUserDetail fuser =((FUserDetail)getLoginUser());
     
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("isopen", "1");
        fkListl=(List<OaFeedback>) getSubList(oaFeedbackManager.listObjects(filterMap, pageDesc), 7);
        
    }
    //领导日程安排
    @SuppressWarnings("unchecked")
    private void ldrc_dashboard() {
      //  FUserDetail fuser =((FUserDetail)getLoginUser());
     
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
    
        filterMap.put("sehType","2" );
        ldrcList=(List<OaSchedule>) getSubList(oaScheduleManager.listObjects(filterMap, pageDesc), 7);
        
        filterMap.put("sehType", "1");
        filterMap.put("canAdd", "T");
        filterMap.put("creater", ((FUserDetail) getLoginUser()).getUsercode());
        gerenrcList = (List<OaSchedule>) getSubList(oaScheduleManager.listObjects(filterMap, pageDesc) ,7);
        if(null!=ldrcList&&ldrcList.size()>0){
            for (int i=0;i<ldrcList.size();i++){
                ldrcList.get(i).setDateTag(DateTimeUtil.smartCalcSpanDays(ldrcList.get(i).getPlanBegTime(), new Date())) ;
            }
            
        }
        
        if(null!=gerenrcList&&gerenrcList.size()>0){
            for (int i=0;i<gerenrcList.size();i++){
                gerenrcList.get(i).setDateTag(DateTimeUtil.smartCalcSpanDays(gerenrcList.get(i).getPlanBegTime(), new Date())) ;
            }
            
        }
    }
    
    /**
     * 部门收文*/
    public void bmsw_dashboard(){
        
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit funit = sysUserManager.getUserPrimaryUnit(user
                .getUsercode());
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("unitcode", funit.getUnitcode());
        filterMap.put("NP_isopen", true);//显示公开
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        bmswList = (List<VIncomeDocList>) getSubList(incomeDocListManager.listIncomeDocListV2(filterMap,pageDesc), 7);
        
    }
    
    public void file_dashboard(){
        
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        Publicinfo root = null;
        Publicinfo unitroot = null;
        Publicinfo personalRoot = null;
        
        try {
            // 公共文件夹（资源库）
            root = publicinfoManager.getPublicRootDirectory();
            // 部门文件夹
            unitroot = publicinfoManager.getUnitRootDirectory(uinfo);
            // 个人文件夹
            personalRoot = publicinfoManager.getPersonalRootDirectory(uinfo);
            // 资源库
            fileList = (List<Publicinfo>) getSubList(publicinfoManager.queryPublicFiles(root.getInfocode(), uinfo),7);

            // 部门文档
            unitFileList = (List<Publicinfo>) getSubList(publicinfoManager.queryUnitFiles(unitroot.getInfocode(), uinfo),4);
            // 个人文档
            personalFileList = (List<Publicinfo>) getSubList(publicinfoManager.queryPersonalFiles(personalRoot.getInfocode(), uinfo),4);

        } catch (PublicInfoException e) {
            e.printStackTrace();
        } 
    }
    /**
     * 测试从OA移植框架
     * 
     * @return
     * @throws Exception
     */
    public String test() throws Exception {
        
       List<Map<String, String>> list = new ArrayList<Map<String, String>>();
       
       Map<String, String> data = new HashMap<String, String>();
       data.put("id", "9527");
       data.put("name", "周星星");
       data.put("sex", "1");
       data.put("work", "卧底");
       data.put("weapon", "1");
       data.put("weaponName", "温柔一刀");
       
       list.add(data);
       
       request.setAttribute("applyList", list);
        
        return "test";
    }
    
    
    //首页待办
    /**
     * 
     */
    @SuppressWarnings("unused")
    private void sydb_dashboard() {
       // FUserDetail fuser =((FUserDetail)getLoginUser());
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
       
        Map<String, Object> filterMap = new HashMap<String, Object>();
       
        filterMap.put("userCode", userCode);
//        Limit limit = ExtremeTableUtils.getLimit(request);
//        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit);
        
        //dashboardList=dashboardManager.listObjects(filterMap);
        // 此处解决存储过程定时生成数据时，如果正好登录的时间是存储过程删除、插入数据的时候的情况，此情况时 查出的数据位空，带到页面会报错；20141210
        //if(dashboardList==null || dashboardList.size()==0){
            dashboardList=dashboardManager.getDashboardListFormVOADASHBOARD(filterMap);
       // }       
    }
    
    
    //未读件
    @SuppressWarnings("unchecked")
    private void unreadEmail_dashboard() {
        //FUserDetail fuser =((FUserDetail)getLoginUser());
     
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("mailtype", "I");//I=收件箱 O=发件箱 D=草稿箱 T=废件箱
        filterMap.put("msgtype","P" );//P=个人为消息 A=机构为公告 M=邮件 //文件群发F
        filterMap.put("msgstate","U" );// U=未读 R=已读 D=删除
        msgList=(List<Innermsg>) getSubList(innermsgManager.listObjects(filterMap, pageDesc,getLoginUserCode()), 7);
        
    }
    
      
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
    
    public String weapons() throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("name", "温柔一刀");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "2");
        data.put("name", "霸王枪");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "3");
        data.put("name", "血滴子");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("name", "温柔一刀");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "2");
        data.put("name", "霸王枪");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "3");
        data.put("name", "血滴子");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("name", "温柔一刀");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "2");
        data.put("name", "霸王枪");
        list.add(data);
        
        data = new HashMap<String, String>();
        data.put("id", "3");
        data.put("name", "血滴子");
        list.add(data);
        
        request.setAttribute("weapons", list);
        
        return "weapons";
    }
    
    
    public String checkUserName() throws Exception {
        String username = request.getParameter("username");
        
        if (StringUtils.isBlank(username)) {
            json = "您输入的用户名为空，请重新输入";
            return "json";
        }
        
        if ("admin".equals(username)) {
            json = username + "用户名已存在，请换一个用户名";
            return "json";
        }
        
        json = true;
        
        return "json";
    }

    /**
     * 截取LIST前N条数据
     * 
     * @param objList
     *            列表对象
     * @param dataLen
     *            数据条数
     * @return
     */
    private List<?> getSubList(List<?> objList, int dataLen) {
        objList = (objList!=null && objList.size() > dataLen ? objList.subList(0, dataLen - 1)
                : objList);
        return objList;
    }
    /**
     * 日历事项入口
     * by 2016-02-24 dk
     */
    public String showCalendar(){
        try {
            String cd=(String)request.getParameter("date");
            Date date=null;
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date=format.parse(cd);
            showGRRC(date);//个人日程
            
        } catch (ParseException e) {
            e.printStackTrace();
            return ERROR;
        }
        return "showCalendar";
    }
    
    private VOaScheduleRemindManager voaScheduleRemindManager;
    public void setVoaScheduleRemindManager(
            VOaScheduleRemindManager voaScheduleRemindManager) {
        this.voaScheduleRemindManager = voaScheduleRemindManager;
    }

    //个人日程——日历
    private void showGRRC(Date date){
        
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        FUserDetail user=(FUserDetail)getLoginUser();
        String usercode=user.getUsercode();
        //查询当天
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        filterMap.put("cBegin",format.format(new Date( date.getTime()+(0*60*60*1000))));
        filterMap.put("cEnd",format.format(new Date( date.getTime()+(24*60*60*1000))));
        //filterMap.put("sehType", "1");
        //filterMap.put("canAdd", "T");
        //filterMap.put("ORDER_BY", " planBegTime asc");
       // filterMap.put("inschno",  ((FUserDetail) getLoginUser()).getUsercode());
       // filterMap.put("usercode", ((FUserDetail) getLoginUser()).getUsercode());
        gerenrcrlList = voaScheduleRemindManager.listObjects(filterMap,usercode);
        if(null!=gerenrcrlList&&gerenrcrlList.size()>0){
            for (int i=0;i<gerenrcrlList.size();i++){
                VOaScheduleRemind o=gerenrcrlList.get(i);
                if("RCAP".equals(o.getReType())){
                    o.setItemType(CodeRepositoryUtil.getValue("oaItemType",o.getItemType()));
                }else if("TX".equals(o.getReType())){
                    /*o.setItemType(CodeRepositoryUtil.getValue("oa_superviseType",o.getItemType()));*/
                    o.setItemType("提醒");
                }
            }
        }
    }
    //邮件——日历
    private void showYJ(Date date){
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("mailtype", "I");//I=收件箱 O=发件箱 D=草稿箱 T=废件箱
        filterMap.put("msgtype","P" );//P=个人为消息 A=机构为公告 M=邮件 //文件群发F
        filterMap.put("msgstate","U" );// U=未读 R=已读 D=删除
        filterMap.put("cBegin",format.format(new Date( date.getTime()+(0*60*60*1000))));
        filterMap.put("cEnd",format.format(new Date( date.getTime()+(24*60*60*1000))));
        setbackSearchColumn(filterMap);
        msgrlList=(List<Innermsg>) getSubList(innermsgManager.listObjects(filterMap, pageDesc,getLoginUserCode()), 7);
    }
    //待办——日历
    private void showDB(Date date){
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("userCode", userCode);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        filterMap.put("cBegTime",format.format(new Date( date.getTime()+(0*60*60*1000))) );
        filterMap.put("cEndTime", format.format(new Date( date.getTime()+(24*60*60*1000))));
       
        vuserTaskListrl = vuserTaskListOAManager.listObjects(filterMap, pageDesc);
        if(vuserTaskListrl!=null&&vuserTaskListrl.size()>0){
            //判断objList是否是督办流程的列表数据
                  for(int i=0;i<vuserTaskListrl.size();i++){
                   
                      //事项被督办的话，在办件流水号前面用图标标注
                      VuserTaskListOA oa=vuserTaskListrl.get(i);
                      Map<String, Object> filterMapTemp = new HashMap<String, Object>();
                      filterMapTemp.put("supDjidEq",oa.getDjId());
                      
                     List<OaSupervise> supList=oaSuperviseManager.listObjects(filterMapTemp);
                     if(supList!=null&&supList.size()>0){
                         oa.setIsSuprised("1");
                     }else{
                         oa.setIsSuprised("0");
                     }
                      
                      //督办件添加督办时限标识
                      if((vuserTaskListrl.get(i).getDjId()).indexOf("DCDB")!=-1){
//                      VuserTaskListOA oa=objList.get(i);
                      OaSupervise oasup=oaSuperviseManager.getObjectById(oa.getDjId());
                          if(oasup!=null&&oasup.getLimittime()!=null){
                              Date nowtime=(new Date(System.currentTimeMillis()));
                              //比较日期，
                             //flag=true  超时；flag=false 提醒
                              boolean flag = oasup.getLimittime().before(nowtime);
                         
                            //warntype 0 提醒 1 超时
                                  if(flag){
                                      oa.setWarnType("1");
                                    
                                  }else{
                                      oa.setWarnType("0");
                                  }
                              
                          }
                      }
                  }
        }
    }
   /*//通知公告-日历 
    private void showTZ(Date date){
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        filterMap.put("state", "1");
        filterMap.put("infoType","info" );
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        filterMap.put("begReleaseDate",format.format(date) );
        filterMap.put("endReleaseDate", format.format(date));
        
        tzggrlList=(List<OaInformation>) getSubList(oaInformationManager.listObjects(filterMap, pageDesc), 7);
    }*/
    //提醒、消息——日历
    private void showTX(Date date){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        filterMap.put("begCreatetime",format.format(new Date( date.getTime()+(0*60*60*1000))));
        filterMap.put("endCreatetime",format.format(new Date( date.getTime()+(24*60*60*1000))));
        oaRemindInformationList = oaRemindInformationManager.recipientList(filterMap, pageDesc,getLoginUserCode());
        setbackSearchColumn(filterMap);
    }
    
    /**
     * 查询添加到首页的业务入口
     * @return
     */
    private void getoaServiceEntrance(){
        try {
            FUserDetail fuser =((FUserDetail)getLoginUser());
            oaserviceList=oaServiceentranceManager.getListByusercode(fuser.getUsercode());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    

    public InnermsgManager getInnermsgManager() {
        return innermsgManager;
    }

    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }

    public List<Innermsg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Innermsg> msgList) {
        this.msgList = msgList;
    }

    public UserSettingManager getUserSettingMgr() {
        return userSettingMgr;
    }

    public void setUserSettingMgr(UserSettingManager userSettingMgr) {
        this.userSettingMgr = userSettingMgr;
    }

    public List<MyHome> getMyHomeList() {
        return myHomeList;
    }

    public void setMyHomeList(List<MyHome> myHomeList) {
        this.myHomeList = myHomeList;
    }

//    public void setSuperviseOnworkManager(
//            SuperviseOnworkManager superviseOnworkManager) {
//        this.superviseOnworkManager = superviseOnworkManager;
//    }
    /*
    private TrackLogManager trackLogManager;

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public void setOutwayManager(OutwayManager outwayManager) {
        this.outwayManager = outwayManager;
    }

    public void setComplaintManager(ComplaintManager complaintManager) {
        this.complaintManager = complaintManager;
    }

    public void setTrackLogManager(TrackLogManager trackLogManager) {
        this.trackLogManager = trackLogManager;
    }
*/
    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public List<VOptApplyTaskList> getXkdbList() {
        return xkdbList;
    }

    public void setXkdbList(List<VOptApplyTaskList> xkdbList) {
        this.xkdbList = xkdbList;
    }

    public List<VProcAttention> getAttList() {
        return attList;
    }

    public void setAttList(List<VProcAttention> attList) {
        this.attList = attList;
    }


    public void setOptProcAttentionManager(
            OptProcAttentionManager optProcAttentionManager) {
        this.optProcAttentionManager = optProcAttentionManager;
    }
    
    public void treeMenu() throws Exception  {
        String key = request.getParameter("key");
        
        ServletActionContext.getResponse().getWriter().print(CodeRepositoryUtil.getDictionaryAsJson(key));
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
    public List<VOaInformation> getTzggList() {
        return tzggList;
    }


    public void setTzggList(List<VOaInformation> tzggList) {
        this.tzggList = tzggList;
    }
    public OaInformationManager getOaInformationManager() {
        return oaInformationManager;
    }


    public void setOaInformationManager(OaInformationManager oaInformationManager) {
        this.oaInformationManager = oaInformationManager;
    }
    public OaFeedbackManager getOaFeedbackManager() {
        return oaFeedbackManager;
    }


    public void setOaFeedbackManager(OaFeedbackManager oaFeedbackManager) {
        this.oaFeedbackManager = oaFeedbackManager;
    }

    public List<OaFeedback> getFkListl() {
        return fkListl;
    }


    public void setFkListl(List<OaFeedback> fkListl) {
        this.fkListl = fkListl;
    }
    public OaScheduleManager getOaScheduleManager() {
        return oaScheduleManager;
    }


    public void setOaScheduleManager(OaScheduleManager oaScheduleManager) {
        this.oaScheduleManager = oaScheduleManager;
    }
    public List<OaSchedule> getLdrcList() {
        return ldrcList;
    }

    public void setLdrcList(List<OaSchedule> ldrcList) {
        this.ldrcList = ldrcList;
    }
    
    public List<OaSchedule> getGerenrcList() {
        return gerenrcList;
    }

    public void setGerenrcList(List<OaSchedule> gerenrcList) {
        this.gerenrcList = gerenrcList;
    }


    public List<OaInformation> getXwList() {
        return xwList;
    }


    public void setXwList(List<OaInformation> xwList) {
        this.xwList = xwList;
    }




    public DashboardManager getDashboardManager() {
        return dashboardManager;
    }


    public void setDashboardManager(DashboardManager dashboardManager) {
        this.dashboardManager = dashboardManager;
    }


    public List<Dashboard> getDashboardList() {
        return dashboardList;
    }


    public void setDashboardList(List<Dashboard> dashboardList) {
        this.dashboardList = dashboardList;
    }


    public List<VIncomeDocList> getBmswList() {
        return bmswList;
    }


    public void setBmswList(List<VIncomeDocList> bmswList) {
        this.bmswList = bmswList;
    }


    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }


    public void setOaUnitIncomedocManager(
            OaUnitIncomedocManager oaUnitIncomedocManager) {
        this.oaUnitIncomedocManager = oaUnitIncomedocManager;
    }
    
    public List<Publicinfo> getFileList() {
        return fileList;
    }


    public void setFileList(List<Publicinfo> fileList) {
        this.fileList = fileList;
    }


    public void setPublicinfoManager(PublicinfoManager publicinfoManager) {
        this.publicinfoManager = publicinfoManager;
    }


    public List<Publicinfo> getUnitFileList() {
        return unitFileList;
    }


    public void setUnitFileList(List<Publicinfo> unitFileList) {
        this.unitFileList = unitFileList;
    }


    public List<Publicinfo> getPersonalFileList() {
        return personalFileList;
    }


    public void setPersonalFileList(List<Publicinfo> personalFileList) {
        this.personalFileList = personalFileList;
    }

    public void setOaServiceentranceManager(
            OaServiceentranceManager oaServiceentranceManager) {
        this.oaServiceentranceManager = oaServiceentranceManager;
    }

    public List<OaServiceentrance> getOaserviceList() {
        return oaserviceList;
    }

    public void setOaserviceList(List<OaServiceentrance> oaserviceList) {
        this.oaserviceList = oaserviceList;
    }

    public List<VOaScheduleRemind> getGerenrcrlList() {
        return gerenrcrlList;
    }

    public void setGerenrcrlList(List<VOaScheduleRemind> gerenrcrlList) {
        this.gerenrcrlList = gerenrcrlList;
    }

    public List<Innermsg> getMsgrlList() {
        return msgrlList;
    }

    public void setMsgrlList(List<Innermsg> msgrlList) {
        this.msgrlList = msgrlList;
    }

    public void setVuserTaskListOAManager(
            VuserTaskListOAManager vuserTaskListOAManager) {
        this.vuserTaskListOAManager = vuserTaskListOAManager;
    }

    public List<VuserTaskListOA> getVuserTaskListrl() {
        return vuserTaskListrl;
    }

    public void setVuserTaskListrl(List<VuserTaskListOA> vuserTaskListrl) {
        this.vuserTaskListrl = vuserTaskListrl;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public List<OaInformation> getTzggrlList() {
        return tzggrlList;
    }

    public void setTzggrlList(List<OaInformation> tzggrlList) {
        this.tzggrlList = tzggrlList;
    }

    public List<OaRemindInformation> getOaRemindInformationList() {
        return oaRemindInformationList;
    }

    public void setOaRemindInformationList(
            List<OaRemindInformation> oaRemindInformationList) {
        this.oaRemindInformationList = oaRemindInformationList;
    }

    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }
    private UserbizoptLogManager userbizoptLogManager;//阅读记录
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    
    public List<VuserTaskListOA> getJjcqList() {
        return jjcqList;
    }

    public void setJjcqList(List<VuserTaskListOA> jjcqList) {
        this.jjcqList = jjcqList;
    }

    public List<VuserTaskListOA> getYcqList() {
        return ycqList;
    }

    public void setYcqList(List<VuserTaskListOA> ycqList) {
        this.ycqList = ycqList;
    }

/**
 * 首页待办-ajax
 * @return
 */
public String sydb_new(){
      FUserDetail fuser = ((FUserDetail) getLoginUser());
      String userCode = fuser.getUsercode();
      @SuppressWarnings("unchecked")
      Map<Object, Object> paramMap = request.getParameterMap();
      resetPageParam(paramMap);
      Map<String, Object> filterMap = convertSearchColumn(paramMap);
      filterMap.put("userCode", userCode);
      filterMap.put("ORDER_BY", "criticalLevel desc, readState asc,createtime desc ");
      vuserTaskListrl=vuserTaskListOAManager.listObjects(filterMap);
      if(vuserTaskListrl!=null&&vuserTaskListrl.size()>0){
          //判断objList是否是督办流程的列表数据
                for(int i=0;i<vuserTaskListrl.size();i++){
                 
                    //事项被督办的话，在办件流水号前面用图标标注
                    VuserTaskListOA oa=vuserTaskListrl.get(i);
                  //=============办件添加是否阅读状态start=============
                   UserbizoptLog uboptlog=userbizoptLogManager.listObject(oa.getDjId(), userCode, oa.getNodeInstId());
                    if(uboptlog!=null){
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "T"));//已读
                    }else{
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "F"));//未读
                    }
                    //==================       end      ===========
                    oa.setItemtype(CodeRepositoryUtil.getValue("optTypeName", oa.getItemtype()));
                }
      }
      if(vuserTaskListrl.size()>10)
          vuserTaskListrl =(List<VuserTaskListOA>) getSubList(vuserTaskListrl,10);
      return "sydb";
  }
/**
 * 首页通知公告-ajax
 * @return
 */
public String tzgg_new(){
    FUserDetail fuser = ((FUserDetail) getLoginUser());
    String userCode = fuser.getUsercode();
    Map<Object,Object> paramMap = request.getParameterMap();
    resetPageParam(paramMap);
    Map<String,Object> filterMap = convertSearchColumn( paramMap );
    filterMap.put("state", "1");
    filterMap.put("infoType","info" );
    filterMap.put("ORDER_BY", " releaseDate desc ");
    List<VOaInformation> l=voaInformationManager.listVoainformation(filterMap);
    
    ////增加阅读状态
    if(l!=null&&l.size()>0){
        for(int i=0;i<l.size();i++){
            String s = l.get(i).getNo();
            if(userbizoptLogManager.listObject(l.get(i).getNo(), userCode)!=null){
                l.get(i).setReadstate(CodeRepositoryUtil.getValue("readstate","T"));//已读
            }else{
                l.get(i).setReadstate(CodeRepositoryUtil.getValue("readstate","F"));
            }
        }
    }
    //排序
    Collections.sort(l, new Comparator<VOaInformation>(){

        @Override
        public int compare(VOaInformation o1, VOaInformation o2) {
            String hits0 = o1.getReadstate();
            String hits1 = o2.getReadstate();
            return hits1.compareTo(hits0);
            
        }
        
    });
    //先截取后排序
    if(l.size()>7){
        l=(List<VOaInformation>) getSubList(l, 7);
    }
    tzggList=l;
    return "tzgg";
}
    /**
     * 首页邮箱——ajax
     * @return
     */
    public String syyj_new(){
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        String mailtype=request.getParameter("mailtype");
        filterMap.put("mailtype", mailtype);//I=收件箱 O=发件箱 D=草稿箱 T=废件箱
        filterMap.put("msgtype","P" );//P=个人为消息 A=机构为公告 M=邮件 //文件群发F
        //filterMap.put("msgstate","U" );// U=未读 R=已读 D=删除
        setbackSearchColumn(filterMap);
        if("I".equals(mailtype)){
            msgrlList=innermsgManager.listObjects(filterMap, pageDesc,getLoginUserCode());
        }else{
            if (!CodeRepositoryUtil
                    .getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_A)
                    .equalsIgnoreCase(String.valueOf("P"))) {
                filterMap.put("sender", getLoginUserCode());
                filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
                        Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));
            }
            msgrlList=innermsgManager.listObjects(filterMap, pageDesc);
        }
      //在字典里取得发件人姓名
        if(msgrlList!=null&&msgrlList.size()>0){
            for(int i=0;i<msgrlList.size();i++){
                Innermsg oa=msgrlList.get(i);
                oa.setSender(CodeRepositoryUtil.getValue("usercode", oa.getSender()));
             // 添加收件阅读状态标记
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("msgcode", oa.getMsgcode());
                map.put("receive", getLoginUserCode());
                map.put("msgstate", "U");
                List<InnermsgRecipient> ureadList = innermsgRecipientManager
                        .listObjects(map);// 跟新收件阅读状态
                if (ureadList != null && ureadList.size() > 0) {
                    oa.setMsgstate("U");
                }else{
                    oa.setMsgstate("R");
                }
            }  
        }
      //排序
        Collections.sort(msgrlList, new Comparator<Innermsg>(){

            @Override
            public int compare(Innermsg o1, Innermsg o2) {
                String hits0 = o1.getMsgstate();
                String hits1 = o2.getMsgstate();
                return hits1.compareTo(hits0);
                
            }
            
        });
        msgrlList=(List<Innermsg>) getSubList(msgrlList,7);
        return "yj";
    }
    private VOptBaseListManager vOptBaseListManager;
    private List<VOptBaseList>  voptBaseList;
   
    public List<VOptBaseList> getVoptBaseList() {
        return voptBaseList;
    }

    public void setVoptBaseList(List<VOptBaseList> voptBaseList) {
        this.voptBaseList = voptBaseList;
    }

    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }
    /**
     * 经我办理
     * @return
     */
    public String jwbl_new(){
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
  
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        filterMap.put("NP_bizstate", true);//默认查看在办已办件
        if("W".equals(request.getParameter("bizstate"))){
            filterMap.put("NP_bizstatein", true);
        }else{
            filterMap.put("bizstate", request.getParameter("bizstate"));
        }
      //查询列表--默认显示自己的
        filterMap.put("usercode", fuser.getUsercode());
        voptBaseList =(List<VOptBaseList>) getSubList( vOptBaseListManager.listOptBaseInfo(filterMap,
                pageDesc),7);
        
        //取得办件类型
        if(voptBaseList!=null&&voptBaseList.size()>0){
            for(int i=0;i<voptBaseList.size();i++){
                VOptBaseList oa=voptBaseList.get(i);
                oa.setItemTypeName(CodeRepositoryUtil.getValue("optTypeName", oa.getItemType()));
                oa.setItemType(CodeRepositoryUtil.getValue("optType", oa.getItemType()));
            }  
        }
        return "jwbl";
    }
    /**
     * 收文
     * @return
     */
    private VIncomeDocListManager incomeDocListManager;
    

    public void setIncomeDocListManager(VIncomeDocListManager incomeDocListManager) {
        this.incomeDocListManager = incomeDocListManager;
    }

    public String sw_new(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit funit = sysUserManager.getUserPrimaryUnit(user
                .getUsercode());
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        //区分单位收文和部门收文
        if(StringUtils.isNotBlank(request.getParameter("bmsw"))){
        filterMap.put("unitcode", funit.getUnitcode());
        }
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        bmswList = (List<VIncomeDocList>) getSubList(incomeDocListManager.listIncomeDocListV2(filterMap,pageDesc), 7);
        return "sw";
    }
    private List<VDispatchDocList> dispatchDocList;
    private VDispatchDocListManager dispatchDocListManager;
    public void setDispatchDocListManager(
            VDispatchDocListManager dispatchDocListManager) {
        this.dispatchDocListManager = dispatchDocListManager;
    }

    public List<VDispatchDocList> getDispatchDocList() {
        return dispatchDocList;
    }

    public void setDispatchDocList(List<VDispatchDocList> dispatchDocList) {
        this.dispatchDocList = dispatchDocList;
    }
    /**
     * 发文
     * @return
     */
    public String fw_new(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        FUserunit funit = sysUserManager.getUserPrimaryUnit(loginUser
                .getUsercode());
        //区别单位和部门发文
        if(StringUtils.isNotBlank(request.getParameter("bmfw"))){
            filterMap.put("unitcode",funit.getUnitcode());
        }
        //只显示已办结
        filterMap.put("bizstate", "C");
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        dispatchDocList = (List<VDispatchDocList>) getSubList(dispatchDocListManager.listDispatchDocV2(filterMap, pageDesc),7);
        return "fw";
    }
    Map<String,String> nummap; //未读信息数量
    Map<String,String> messagenummap;//消息提醒数量
    public Map<String, String> getMessagenummap() {
        return messagenummap;
    }

    public void setMessagenummap(Map<String, String> messagenummap) {
        this.messagenummap = messagenummap;
    }

    public Map<String, String> getNummap() {
        return nummap;
    }

    public void setNummap(Map<String, String> nummap) {
        this.nummap = nummap;
    }
    //未读信息数量
    public void getDashboardNum(String itemtype){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        //itemtype
        nummap=dashboardManager.getdashboardNum(loginUser.getUsercode(), itemtype);
    }
    //获取消息提醒数量
    public String getMessageNum(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        messagenummap=dashboardManager.getMessageNum(loginUser.getUsercode());
        if(messagenummap.size()<=0){
            messagenummap=null;
        }
        return "messageNum";
    }
    
    
    
    
    //=================消息弹窗===========================
  /*  private VDispatchDocListManager vDispatchDocListManager;//收文

   

    public void setvDispatchDocListManager(
            VDispatchDocListManager vDispatchDocListManager) {
        this.vDispatchDocListManager = vDispatchDocListManager;
    }*/

    private List<OaRemindInformation> oaRemindInformation;//提醒
    private List<Innermsg> emailList;//邮件
    private List<VOaInformation> oaInformationList;//通知
    private List<VuserTaskListOA> DBList;//待办
    private List<VDispatchDocList> dispatchDocListBM;//收文
    private List<VIncomeDocList> incomeDocList;//发文

    public List<VIncomeDocList> getIncomeDocList() {
        return incomeDocList;
    }

    public void setIncomeDocList(List<VIncomeDocList> incomeDocList) {
        this.incomeDocList = incomeDocList;
    }

    public List<VDispatchDocList> getDispatchDocListBM() {
        return dispatchDocList;
    }

    public void setDispatchDocListBM(List<VDispatchDocList> dispatchDocList) {
        this.dispatchDocListBM = dispatchDocList;
    }

    public List<VuserTaskListOA> getDBList() {
        return DBList;
    }

    public void setDBList(List<VuserTaskListOA> dBList) {
        DBList = dBList;
    }

    public List<VOaInformation> getOaInformationList() {
        return oaInformationList;
    }

    public void setOaInformationList(List<VOaInformation> oaInformationList) {
        this.oaInformationList = oaInformationList;
    }

    private InnermsgRecipientManager innermsgRecipientManager;

    public void setInnermsgRecipientManager(
            InnermsgRecipientManager innermsgRecipientManager) {
        this.innermsgRecipientManager = innermsgRecipientManager;
    }

    public List<Innermsg> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Innermsg> emailList) {
        this.emailList = emailList;
    }

    public List<OaRemindInformation> getOaRemindInformation() {
        return oaRemindInformation;
    }

    public void setOaRemindInformation(List<OaRemindInformation> oaRemindInformation) {
        this.oaRemindInformation = oaRemindInformation;
    }
    /**
     * 首页弹窗
     */
    private Map<String,List> showMap;
    public Map<String, List> getShowMap() {
        return showMap;
    }

    public void setShowMap(Map<String, List> showMap) {
        this.showMap = showMap;
    }

    public String showWindow(){
        oaRemindInformation();
        email();
        toDayOaInformation();
        DBVuserTaskListOA();
        //BMGW();
        showMap.put("TX", oaRemindInformation);
        showMap.put("YJ", emailList);
        showMap.put("TZ",oaInformationList);
        showMap.put("DB", DBList);
        return "shwoWindow";
    }
    /**
     * 未读提醒
     */
    public String oaRemindInformation(){
        FUserDetail user = (FUserDetail) getLoginUser();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("bizType", "0");
        filterMap.put("orderbegtime", true);
       // filterMap.put("begtimetoday",DateUtil.getCurrentDate()+" 00:00:00");
        //filterMap.put("endtimetoday",DateUtil.getCurrentDate()+" 23:59:59");
        //排除已经查看过的提醒
        //filterMap.put("notInUserBizoptLog", user.getUsercode());
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        oaRemindInformation = (List<OaRemindInformation>) getSubList(oaRemindInformationManager.recipientList(filterMap, pageDesc,
                user.getUsercode()),7);
        setbackSearchColumn(filterMap);
        if(oaRemindInformation!=null&&oaRemindInformation.size()>0){
            for(OaRemindInformation o:oaRemindInformation){
                if(StringUtils.isNotBlank(o.getReType())){
                o.setReType(CodeRepositoryUtil.getValue("oa_superviseType",o.getReType()));
                }else{
                    o.setReType("提醒");
                }
            }
        }
        return "WDTX";
    }
    /**
     * 未读邮件
     */
    public void email(){
        FUserDetail user = (FUserDetail) getLoginUser();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
        //filterMap.put("msgstate", "U");
        filterMap.put("mailtype", "I");
        filterMap.put("msgtype", "P");
        //排除提醒中已经查看过的邮件
        //filterMap.put("notInUserBizoptLog", user.getUsercode());
        List<Innermsg> list = innermsgManager.listObjects(filterMap, pageDesc,
                user.getUsercode());
        // 添加收件阅读状态标记
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("msgcode", list.get(i).getMsgcode());
                map.put("receive", user.getUsercode());
                map.put("msgstate", "U");
                List<InnermsgRecipient> ureadList = innermsgRecipientManager
                        .listObjects(map);// 跟新收件阅读状态
                if (ureadList != null && ureadList.size() > 0) {
                    list.get(i).setMsgstate("U");
                    emailList.add(list.get(i));
                }
            }

        }
        emailList=(List<Innermsg>) getSubList(emailList,5);
        
    }
    /**
     * 
     * 未读通知
     */
    public String toDayOaInformation(){
        tzgg_new();
        oaInformationList=tzggList;
        List<VOaInformation> l=oaInformationList;
        for(int i=0;i<l.size();i++){
            VOaInformation o=l.get(i);
            if("已读".equals(o.getReadstate())){
                oaInformationList.remove(o);
                i--;
            }
        }
        return "JRTZ";
    }
    /**
     * 待办
     */
    public String  DBVuserTaskListOA(){
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("userCode", userCode);
        filterMap.put("readstate", "F");
        filterMap.put("ORDER_BY", " readState asc,createdate desc ");
        DBList = vuserTaskListOAManager.listObjects(filterMap);
        if(DBList!=null&&DBList.size()>0){
            //判断objList是否是督办流程的列表数据
                  for(int i=0;i<DBList.size();i++){
                   
                      //事项被督办的话，在办件流水号前面用图标标注
                      VuserTaskListOA oa=DBList.get(i);
                      Map<String, Object> filterMapTemp = new HashMap<String, Object>();
                      filterMapTemp.put("supDjidEq",oa.getDjId());
                      
                     List<OaSupervise> supList=oaSuperviseManager.listObjects(filterMapTemp);
                     if(supList!=null&&supList.size()>0){
                         oa.setIsSuprised("1");
                     }else{
                         oa.setIsSuprised("0");
                     }
                      //督办件添加督办时限标识
                      if((DBList.get(i).getDjId()).indexOf("DCDB")!=-1){
//                      VuserTaskListOA oa=objList.get(i);
                      OaSupervise oasup=oaSuperviseManager.getObjectById(oa.getDjId());
                          if(oasup!=null&&oasup.getLimittime()!=null){
                              Date nowtime=(new Date(System.currentTimeMillis()));
                              //比较日期，
                             //flag=true  超时；flag=false 提醒
                              boolean flag = oasup.getLimittime().before(nowtime);
                         
                            //warntype 0 提醒 1 超时
                                  if(flag){
                                      oa.setWarnType("1");
                                    
                                  }else{
                                      oa.setWarnType("0");
                                  }
                          }
                      }
                      oa.setItemtype(CodeRepositoryUtil.getValue("optTypeName", oa.getItemtype()));
                  }
        }
        return "DBLIST";
    }
   private List<VIncomeDocList> vincomddoclist;
  public List<VIncomeDocList> getVincomddoclist() {
    return vincomddoclist;
}

public void setVincomddoclist(List<VIncomeDocList> vincomddoclist) {
    this.vincomddoclist = vincomddoclist;
}
//未读收文
public String wdsw(){
    FUserDetail user = ((FUserDetail) getLoginUser());
    FUserunit funit = sysUserManager.getUserPrimaryUnit(user
            .getUsercode());
    @SuppressWarnings("unchecked")
    Map<Object, Object> paramMap = request.getParameterMap();
    resetPageParam(paramMap);
    Map<String, Object> filterMap = convertSearchColumn(paramMap);
    //区分单位收文和部门收文
    if(StringUtils.isNotBlank(request.getParameter("bmsw"))){
    filterMap.put("unitcode", funit.getUnitcode());
    }
    Limit limit = ExtremeTableUtils.getLimit(request);
    PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
    vincomddoclist = incomeDocListManager.listIncomeDocListV2(filterMap,pageDesc);
    if(vincomddoclist!=null&&vincomddoclist.size()>0){
        for(int i=0;i<vincomddoclist.size();i++){
            VIncomeDocList oa=vincomddoclist.get(i);
            UserbizoptLog l=userbizoptLogManager.listObject(oa.getDjId(), user.getUsercode());
            if(l!=null){
                vincomddoclist.remove(oa);
                i--;
            }
        }
    }
    vincomddoclist = (List<VIncomeDocList>) getSubList(vincomddoclist, 7);
    return "wdsw";
  }
//日历增加日程、提醒
private String newtime;
 public String getNewtime() {
    return newtime;
}

public void setNewtime(String newtime) {
    this.newtime = newtime;
}

public String calendarSX(){
     String newtime=request.getParameter("newtime");
     if(StringUtils.isNotBlank(newtime)){
         request.setAttribute("newtime", newtime);
     }
     return "calendarSX";
 }

    public String cq_new() {

        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String userCode = fuser.getUsercode();
        List<VuserTaskListOA> taskList = new ArrayList<VuserTaskListOA>();
        
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("userCode", userCode);
        filterMap.put("itemtype", "FW");
        filterMap.put("flowCode", "000392");
        List<VuserTaskListOA> fwTaskList = vuserTaskListOAManager.listObjects(filterMap);
        
        filterMap.put("itemtype", "SW");
        filterMap.put("flowCode", "000394");
        List<VuserTaskListOA> swTaskList = vuserTaskListOAManager.listObjects(filterMap);
        
        taskList.addAll(fwTaskList);
        taskList.addAll(swTaskList);
        
        String overdueType = request.getParameter("overdueType");
        
        if("I".equals(overdueType)){
            jjcqList = new ArrayList<VuserTaskListOA>();
            for(VuserTaskListOA o : taskList){
                // 即将超期
                if("I".equals(vuserTaskListOAManager.getOverDueState(o))){
                    o.setItemTypeName(CodeRepositoryUtil.getValue("optTypeName", o.getItemtype()));
                    jjcqList.add(o);
                }
            }
            jjcqList = (List<VuserTaskListOA>)getSubList(jjcqList, 6);
            
            return "jjcq";
        }else{
            ycqList = new ArrayList<VuserTaskListOA>();
            for(VuserTaskListOA o : taskList){
                // 已超期
                if("O".equals(vuserTaskListOAManager.getOverDueState(o))){
                    o.setItemTypeName(CodeRepositoryUtil.getValue("optTypeName", o.getItemtype()));
                    ycqList.add(o);
                }
            }
            ycqList = (List<VuserTaskListOA>)getSubList(ycqList, 6);
            
            return "ycq";
        }
        
    }
    //某月有日程提醒的日期
    public String listHaveTX(){
        try {
            Map<Object,Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String,Object> filterMap = convertSearchColumn( paramMap );
            FUserDetail user=(FUserDetail)getLoginUser();
            String usercode=user.getUsercode();
            String firstday=(String)request.getParameter("reqDate");
            String[] daysq=firstday.split("-");
            String lastday=daysq[0]+"-"+daysq[1]+"-31 00:00:00";
            Date dateFirst=null;
            Date dateLast=null;
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFirst=format.parse(firstday);
            dateLast=format.parse(lastday);
            //查询当月
            filterMap.put("cBegin",format.format(new Date( dateFirst.getTime()+(0*60*60*1000))));
            filterMap.put("cEnd",format.format(new Date( dateLast.getTime()+(24*60*60*1000))));
            List<VOaScheduleRemind> l = voaScheduleRemindManager.listObjects(filterMap,usercode);
            List<String> s=new ArrayList<String>();
            if(null!=l&&l.size()>0){
                for (int i=0;i<l.size();i++){
                    VOaScheduleRemind o=l.get(i);
                    String day= String.valueOf(o.getBegTime().getDate());
                    s.add(day);
                }
            }
            //去重
            dateNumList=new ArrayList<String>(new HashSet<String>(s));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "txdate";
    }
    
    public String unitFile() {

        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        Publicinfo unitroot = null;

        try {
            // 部门文件夹
            unitroot = publicinfoManager.getUnitRootDirectory(uinfo);
            // 部门文档
            unitFileList = (List<Publicinfo>) getSubList(
                    publicinfoManager.queryUnitFiles(unitroot.getInfocode(),
                            uinfo), 4);

        } catch (PublicInfoException e) {
            e.printStackTrace();
        }

        return "unitFile";
    }
    //获取功能入口
    public String getFunctionEntry(){
        return "functionEntry";
    }
    
    private List<String> dateNumList;


    public List<String> getDateNumList() {
        return dateNumList;
    }

    public void setDateNumList(List<String> dateNumList) {
        this.dateNumList = dateNumList;
    }



    public OptFileTransferReceiveManager getOptFileTransferReceiveMgr() {
        return optFileTransferReceiveMgr;
    }
    
    public void setOptFileTransferReceiveMgr(
            OptFileTransferReceiveManager optFileTransferReceiveMgr) {
        this.optFileTransferReceiveMgr = optFileTransferReceiveMgr;
       
    }
    public OptFileTransferSendManager getOptFileTransferSendMgr() {
        return optFileTransferSendMgr;
    }

    public void setOptFileTransferSendMgr(
            OptFileTransferSendManager optFileTransferSendMgr) {
        this.optFileTransferSendMgr = optFileTransferSendMgr;
       
    }

    public OptDashboardActiveManager getOptDashboardActiveManager() {
        return optDashboardActiveManager;
    }

    public void setOptDashboardActiveManager(
            OptDashboardActiveManager optDashboardActiveManager) {
        this.optDashboardActiveManager = optDashboardActiveManager;
    }

    public OptDashboardLayoutManager getOptDashboardLayoutManager() {
        return optDashboardLayoutManager;
    }

    public void setOptDashboardLayoutManager(
            OptDashboardLayoutManager optDashboardLayoutManager) {
        this.optDashboardLayoutManager = optDashboardLayoutManager;
    }

    public OptLayoutMethodManager getOptLayoutMethodManager() {
        return optLayoutMethodManager;
    }

    public void setOptLayoutMethodManager(
            OptLayoutMethodManager optLayoutMethodManager) {
        this.optLayoutMethodManager = optLayoutMethodManager;
    }
    
    
}
