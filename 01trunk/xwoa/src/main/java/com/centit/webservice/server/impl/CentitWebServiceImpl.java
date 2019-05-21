package com.centit.webservice.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import microsoft.exchange.webservices.data.misc.IFunctions.ToString;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.codehaus.xfire.transport.http.XFireServletController;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.centit.app.dao.DashboardDao;
import com.centit.app.exception.PublicInfoException;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Publicinfo;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.PublicinfoManager;
import com.centit.bbs.po.OaBbsTheme;
import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.service.OaBbsDiscussionManager;
import com.centit.bbs.service.OaBbsThemeManager;
import com.centit.bbs.service.OaLeaveMessagereplyManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.dispatchdoc.service.OptProcStuffInfoManager;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.mip.po.OaBbsMIP;
import com.centit.mip.po.OaBbsMIP.OaBbsLeaverMessageMIP;
import com.centit.mip.po.OaCarApplyMIP;
import com.centit.mip.po.OaInformationMIP;
import com.centit.mip.po.OaInnermsgMIP;
import com.centit.mip.po.OaInnermsgMIP.AttachlistMIP;
import com.centit.mip.po.OaInnermsgMIP.AttachmentMIP;
import com.centit.mip.po.OaInnermsgMIP.ReceiverMIP;
import com.centit.mip.po.OaMeetApplyMIP;
import com.centit.mip.po.OaMeetinfoMIP;
import com.centit.mip.po.OaMeetinfoMIP.InnerMeetinfo;
import com.centit.mip.po.OaMeetingmaterialApplyMIP;
import com.centit.mip.po.OaMeetingmaterialApplyMIP.OaAgendainfosMIP;
import com.centit.mip.po.OaMeetingmaterialMIP;
import com.centit.mip.po.OaMeetingmaterialApplyMIP.OaMeetingmaterialinfosMIP;
import com.centit.mip.po.OaScheduleMIP;
import com.centit.mip.po.OaSmssendMip;
import com.centit.mip.po.OaTripPlanMIP;
import com.centit.mip.po.OaUnitIncomedocMIP;
import com.centit.mip.po.OaUserinfoMIP;
import com.centit.mip.po.OabookBuffetApplyMIP;
import com.centit.mip.po.OabookLeaveApplyMIP;
import com.centit.mip.po.OabookLeaveReportedMIP;
import com.centit.mip.po.OabookNetApplicationMIP;
import com.centit.mip.po.OabookOfficeSuppApplyMIP;
import com.centit.mip.po.OasendAttendInfoResultMIP;
import com.centit.mip.po.OfficialApprovalMIP;
import com.centit.mip.po.OfficialMIP;
import com.centit.mip.po.OfficialNextProMIP;
import com.centit.mip.po.OfficialSendProMIP;
import com.centit.mip.po.OptProcCollectionMIP;
import com.centit.mip.po.PublicinfoMIP;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.po.Msgannex;
import com.centit.oa.po.OaArchive;
import com.centit.oa.po.OaBuffetapply;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaFilemanager;
import com.centit.oa.po.OaInformation;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.po.OaInfosummary;
import com.centit.oa.po.OaLeaveMessage;
import com.centit.oa.po.OaLeaveapply;
import com.centit.oa.po.OaLeavereported;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.OaMeetingApply;
import com.centit.oa.po.OaMeetingmaterial;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.po.OaNetapplication;
import com.centit.oa.po.OaOfficesupplies;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaTripPlan;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.po.VOaMeetingMaterialApply;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.po.VoaUnitArchiveDispatchdoc;
import com.centit.oa.po.VoaUnitArchiveIncomedoc;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.oa.service.OaArchiveManager;
import com.centit.oa.service.OaBuffetapplyManager;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaFilemanagerManager;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.oa.service.OaInformationManager;
import com.centit.oa.service.OaInfosummaryManager;
import com.centit.oa.service.OaLeaveMessageManager;
import com.centit.oa.service.OaLeaveapplyManager;
import com.centit.oa.service.OaLeavereportedManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaMeetingApplyManager;
import com.centit.oa.service.OaMeetingmaterialManager;
import com.centit.oa.service.OaMeetingmaterialinfosManager;
import com.centit.oa.service.OaNetapplicationManager;
import com.centit.oa.service.OaOfficesuppliesManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaScheduleManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaTripPlanManager;
import com.centit.oa.service.OaUnitIncomedocManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.oa.service.VoaUnitArchiveDispatchdocManager;
import com.centit.oa.service.VoaUnitArchiveIncomedocManager;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.MipLog;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.po.OptProcCollectionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptProcLock;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptProcCollection;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.MipLogManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptProcLockManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.powerruntime.service.TemplateFileManager;
import com.centit.powerruntime.service.VOptProcCollectionManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.OptInfoDao;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.dao.UserRoleDao;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FVUseroptlist;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.DictionaryManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.client.WsclientManager;
import com.centit.webservice.pojo.CentitWSReturninfo;
import com.centit.webservice.pojo.CentitWebServiceInfo;
import com.centit.webservice.server.CentitWebService;
import com.centit.webservice.util.CommonOptCodeUtil;
import com.centit.webservice.util.Cryptos;
import com.centit.webservice.util.DateUtil;
import com.centit.webservice.util.JsonUtil;
import com.centit.webservice.util.PDFUtil;
import com.centit.webservice.util.WebUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.VUserTaskList;

public class CentitWebServiceImpl implements CentitWebService {

    protected SysUnitManager sysUnitManager;
    protected SysUserManager sysUserManager;
    protected String isSubFlow;// T:子流程
    protected OptIdeaInfoDao optIdeaInfoDao;
    protected VuserTaskListOAManager vuserTaskListOAManager;
    protected WfNodeInstanceDao nodeInstanceDao;
    protected FlowEngine flowEngine;
    protected FlowDefine flowDefine;
    protected FlowManager flowManager;
    protected OptProcInfoManager optProcInfoManager;
    protected OptBaseInfoManager optBaseInfoManager;
    protected DictionaryManager dictionaryManager;
    /* ------------------ 通用业务操作部分 * */

    protected GeneralModuleParam moduleParam;
    protected GeneralModuleParamManager generalModuleParamManager;
    protected OptProcAttentionManager optProcAttentionManager;
    protected TemplateFileManager templateFileManager;
    protected OptProcInfo optProcInfo = new OptProcInfo();
    protected FUserDetail fUserDetail;

    private int number = 1;

    /**
     * 
     */
    private static Logger log = Logger.getLogger(CentitWebServiceImpl.class);
    private UserInfoDao sysuserdao;
    private UnitInfoDao sysunitdao;
    private UserRoleDao userRoleDao;
    private OptInfoDao functionDao;
    private OptBaseInfoDao optBaseInfoDao;
    private VOptBaseListManager vOptBaseListManager;
    private DashboardDao dashboardDao;
    private OaScheduleManager oaScheduleManager;// 日程安排
    private WsclientManager wsclientManager;

    private PublicinfoManager publicinfoManager;// 文档管理
    private FileinfoFsManager fileinfoFsManager;// 附件
    // private SysUserManager sysUserManager;// 用户信息（可获取操作权限）
    private InnermsgRecipientManager innermsgRecipientManager;// 收件箱
    private InnermsgManager innermsgManager;// 邮箱
    private OaInformationManager oaInformationManager;// 资讯类
    private OaInformationAttachmentManager oaInformationAttachmentManager;
    private OaUnitIncomedocManager oaUnitIncomedocManager;// 归档类
    private OaMeetinfoManager oaMeetinfoManager;
    private VOptProcCollectionManager vOptProcCollectionManager;// 文书收藏
    private OptProcCollectionManager optProcCollectionManager;
    private OptProcLockManager optProcLockManager;// 文书锁定
    private OaMeetApplyManager oaMeetApplyManager;
    private OaUserinfoManager oaUserinfoManager;// 通讯录
    private VoaUnitArchiveDispatchdocManager voaUnitArchiveDispatchdocManager;// 发文归档
    private VoaUnitArchiveIncomedocManager voaUnitArchiveIncomedocManager;// 收文归档
    private OaArchiveManager oaArchiveManager;// 公文归档-已归档汇总
    private OptProcStuffInfoManager optProcStuffInfoManager;

    private OptStuffInfoManager optStuffInfoManager;
    private OptPdfInfoManager optPdfInfoManager;

    private AddressBookRelectionManager addressBookRelectionManager;// 中间关联表
    private WfActionTaskDao actionTaskDao;
    private OaRemindInformationManager oaRemindInformationManager;

    private DispatchDocManager dispatchDocManager;
    private IncomeDocManager incomeDocManager;
    
  
    private OaSmssendManager oaSmssendManager;// 短信

    private OaCarApplyManager oaCarApplyManager;// 车辆申请

    private OaFilemanagerManager oaFilemanagerManager;// 市总文件管理

    private UserbizoptLogManager userbizoptLogManager;// 阅读记录
    
    private MipLogManager mipLogManager;
    
    private OaLeaveapplyManager oaLeaveapplyManager;//请假申请
    
    private VPowerUserInfoManager vPowerUserInfoManager;
    
    private VsuppowerinusingManager vsuppowerinusingManager;
    
    private OptApplyManager optApplyManager;
    
    private OaBuffetapplyManager oaBuffetapplyManager;
    
    private OaOfficesuppliesManager oaOfficesuppliesManager;
    
    private OaNetapplicationManager oaNetapplicationManager;
    
    private OaLeavereportedManager oaLeavereportedManager;
    private OaTripPlanManager oaTripPlanManager;
    private OaInfosummaryManager oaInfosummaryManager;
    
    private OaBbsThemeManager oaBbsThemeManager;//生活服务
    private OaLeaveMessageManager oaLeaveMessageManager;// 留言回复
    private OaLeaveMessagereplyManager oaLeaveMessagereplyManager;
    private OaBbsDiscussionManager oaBbsDiscussionManager;//子版块
    
    private OaMeetingmaterialManager oaMeetingmaterialManager;//会议资料
    private OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager;//会议资料
    private OaMeetingApplyManager oaMeetingApplyManager;//会议
    private JSONObject dataJson = new JSONObject();// 接口查询数据结果
    private CentitWebServiceInfo info = new CentitWebServiceInfo();

    private static String datetimePattern = "yyyy-MM-dd HH:mm:ss.SSS";
    private static String datetimePattern1 = "yyyy-MM-dd";
    private static int PAGESIZE = 5;// 默认分页
    private String printStackTrace;//记录MIP异常日志；
    private String operatorId="";//MIP操作者；
    
    private OptStuffInfoDao optStuffInfoDao ;
    private String userRank;//区分一般人员和委领导
    
  
    /**
     * 接口描述: OA系统提供各类待办事宜获取接口，通过该接口获取设定的需要提醒的待办事项： 新收发文 新通知公告 新领导日程 等
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            流程类型，例如：QB 签报,SQ 内部事权,FJX 发件箱,SJXWD 收件箱未读,DRAFTSBOX 草稿箱,DCDB
     *            督办催办,FW 发文,CARSQ 车辆申请,HYSQ 会议申请,SW 收文,WDTX 我的提醒,HYSSQ
     *            会议室申请。。。等(根据具体业务设置类型)
     * @return JSON
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getOfficialCount(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        Map<String, String> dashboardList = null;
        try {
            Map<String, Object> filterMap = JsonUtil.getMap4Json(json);
            /** 获取接口参数beg **/
            if (null != filterMap.get("paras")) {
                String userid = (String) ((JSONObject) filterMap.get("paras"))
                        .get("userid");
                if (null != ((JSONObject) filterMap.get("paras")).get("userid")) {
                    // 用户合法性验证
                    loadUserByUsername(userid);

                    String type = (String) ((JSONObject) filterMap.get("paras"))
                            .get("type");
                    if (StringUtils.isNotBlank(type)) {
                        /** 获取参数end **/

                        /** 查询数据beg **/
                        dashboardList = dashboardDao.getOfficialCount(userid,
                                type);
                        /** 查询数据end **/

                        // 处理查询条件 SW，FW--排序 order by
                        // decode（itemtype，'SW',1,'FW',2,'HYSQ',1,3）
                        String[] types = type.split(",");
                        List officialCount = new ArrayList();

                        if (null != types) {
                            for (String i : types) {
                                officialCount
                                        .add(null == dashboardList.get(i) ? "该类型不存在"
                                                : dashboardList.get(i));
                            }
                        }

                        dataJson.put("unhandlecount",
                                StringUtils.join(officialCount.toArray(), ","));// 根据参数类型，可以返回多个，以“，”隔开，顺序按照参数的type顺序
                        info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                        info.setBizdata(dataJson);
                    } else {
                        info.setReturninfo(new CentitWSReturninfo("1",
                                "请输入需要获取的待办类型!"));
                    }

                }
            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=printStackTrace(e);
        }
        System.out.println("=========="+Thread.currentThread().getStackTrace()[1].getMethodName());
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("getOfficialCount出参:" + returnInfo);
        this.savemiplog("getOfficialCount", operatorId, json, info,returnInfo, printStackTrace, "接口描述: OA系统提供各类待办事宜获取接口，通过该接口获取设定的需要提醒的待办事项： 新收发文 新通知公告 新领导日程 等");
        return returnInfo;
    }

    /**
     * 获取异常信息
     * @param e
     * @return
     */
    private String printStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }
    /**
     *  接口描述 接口返回当前登录人员的个人日程列表信息  支持条件查询  支持分页
     * 
     * @param userid
     *            用户唯一ID
     * @param cycletime
     *            时间周期（1,2,3）
     * @param cycletimetype
     *            时间周期类型（0全部、1周、2月、3季度、4年）
     * @param starttime
     *            起始时间
     * @param currentdatetime
     *            当前记录时间
     * @param pagesize
     *            请求记录条数
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    @Override
    public String getPersonalScheduleList(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaSchedule> objList = null;
        List<OaScheduleMIP> objMIPList = new ArrayList<OaScheduleMIP>();
        try {

            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);
            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /*
             * long endTime = DateUtil.getTime(mip.getStarttime(),
             * mip.getCycletime(), mip.getCycletimetype());
             */
            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("pBegin", DateUtil.convertToString(
                        mip.getStarttime(), datetimePattern));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("pEnd", DateUtil.convertToString(
                        mip.getEndtime(), datetimePattern));// 结束时间
            // 时间戳
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            filterMap.put("ORDER_BY", "createdate desc");// 按预计开始时间排序

            filterMap.put("sehType", "1");// 个人日程

            filterMap.put("creater", mip.getUserid());
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            /*
             * objList = oaScheduleManager.listObjects(filterMap, pageDesc,
             * mip.getUserid());
             */
            objList = oaScheduleManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaSchedule oaSchedule : objList) {
                    OaScheduleMIP mipTemp = new OaScheduleMIP();
                    mipTemp.setScheduleid(JsonUtil.replaceNullString(oaSchedule
                            .getSchno()));
                    mipTemp.setScheduletitle(JsonUtil
                            .replaceNullString(oaSchedule.getTitle()));
                    mipTemp.setSchedulebegindate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getPlanBegTime() ? null : String
                                    .valueOf(oaSchedule.getPlanBegTime()
                                            .getTime())));
                    mipTemp.setScheduleenddate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getPlanEndTime() ? null : String
                                    .valueOf(oaSchedule.getPlanEndTime()
                                            .getTime())));
                    mipTemp.setCreatedate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getCreatedate() ? null : String
                                    .valueOf(oaSchedule.getCreatedate()
                                            .getTime())));
                    mipTemp.setAddress(JsonUtil.replaceNullString(oaSchedule
                            .getAddress()));
                    mipTemp.setScheduleremark(JsonUtil
                            .replaceNullString(oaSchedule.getRemark()));
                    mipTemp.setIsimportant(JsonUtil
                            .replaceNullString(oaSchedule.getImportantTag()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("schedulelist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getPersonalScheduleList", operatorId, json,info, returnInfo, printStackTrace, "接口描述 接口返回当前登录人员的个人日程列表信息支持条件查询支持分页");
        return returnInfo;
    }

    /**
     * 接口返回具体某一条日程的明细内容
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    @Override
    public String getPersonalScheduleDetail(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        OaSchedule oaSchedule = new OaSchedule();

        try {

            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);
            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            oaSchedule = oaScheduleManager.getObjectById(mip.getScheduleid());

            // 按接口要求封装数据
            if (null != oaSchedule) {
                mip = new OaScheduleMIP();
                mip.setScheduleid(JsonUtil.replaceNullString(oaSchedule
                        .getSchno()));
                mip.setScheduletitle(JsonUtil.replaceNullString(oaSchedule
                        .getTitle()));
                mip.setSchedulebegindate(JsonUtil.replaceNullString(null == oaSchedule
                        .getPlanBegTime() ? null : String.valueOf(oaSchedule
                        .getPlanBegTime().getTime())));
                mip.setScheduleenddate(JsonUtil.replaceNullString(null == oaSchedule
                        .getPlanEndTime() ? null : String.valueOf(oaSchedule
                        .getPlanEndTime().getTime())));
                mip.setCreatedate(JsonUtil.replaceNullString(null == oaSchedule
                        .getCreatedate() ? null : String.valueOf(oaSchedule
                        .getCreatedate().getTime())));
                mip.setIsimportant(JsonUtil.replaceNullString(oaSchedule
                        .getImportantTag()));
                // mip.setScheduleurl(null);
                mip.setAddress(JsonUtil.replaceNullString(oaSchedule
                        .getAddress()));
                mip.setScheduleremark(JsonUtil.replaceNullString(oaSchedule
                        .getRemark()));
                dataJson.put("schedulel",
                        JsonUtil.createSimpleFormJsonString(mip));
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);

        this.savemiplog("getPersonalScheduleDetail", operatorId, json, info,returnInfo, printStackTrace, "接口描述:接口返回具体某一条日程的明细内容");
        return returnInfo;
    }

    /**
     * 通过该接口，增加一条个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param title
     *            日程安排标题
     * @param isimportant
     *            是否标注为重要
     * @param scheduleremark
     *            是否标注为重要（0：重要，1：不重要）
     * @param remindtime
     *            提醒时间
     * @param contenturl
     *            正文pdf附件url
     * @return JSON
     */

    @Override
    public String createPersonalSchedule(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        OaSchedule oaSchedule = new OaSchedule();

        try {

            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            if (!(StringUtils.isBlank(mip.getSchedulebegindate()) && StringUtils
                    .isBlank(mip.getScheduleenddate()))) {
                oaSchedule.setCreater(mip.getUserid());
                oaSchedule.setTitle(mip.getTitle());
                oaSchedule.setPlanBegTime(DateUtil.convertToDate(
                        mip.getSchedulebegindate(), datetimePattern));// 时间戳-date转换
                oaSchedule.setPlanEndTime(DateUtil.convertToDate(
                        mip.getScheduleenddate(), datetimePattern));// 时间戳-date转换
                oaSchedule.setImportantTag(mip.getIsimportant());
                oaSchedule.setRemark(mip.getScheduleremark());
                oaSchedule.setCreatedate(new Date());// 当前日期
                oaSchedule.setAddress(mip.getAddress());

                // 手机端insert默认字段
                oaSchedule.setItemtype("1");// 事项类型 1:约会 2:事件 3:邀请与会者
                oaSchedule.setSehType("1");// 日程类型 默认个人日程 1:个人 2:领导 3:其他
                oaSchedule.setTaskTag("B");// 任务标记

                // oaSchedule.setLastnoticetime(StrToDate(remindtime));// 最后提醒时间
                oaScheduleManager.saveObject(oaSchedule);
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "请输入日程安排时间时间!"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("createPersonalSchedule", operatorId, json, info,returnInfo, printStackTrace, "通过该接口，增加一条个人日程");
        return returnInfo;
    }

    /**
     * 通过该接口，修改一条已经存在的个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @param title
     *            日程安排标题
     * @param isimportant
     *            是否标注为重要
     * @param scheduleremark
     *            是否标注为重要（0：重要，1：不重要）
     * @param remindtime
     *            提醒时间
     * @param contenturl
     *            正文pdf附件url
     * @return JSON
     */

    @Override
    public String updatePersonalSchedule(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        try {

            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            OaSchedule object = oaScheduleManager.getObjectById(mip
                    .getScheduleid());

            OaSchedule oaSchedule = new OaSchedule();

            oaSchedule.setCreater(mip.getUserid());
            oaSchedule.setTitle(mip.getTitle());
            oaSchedule.setPlanBegTime(DateUtil.convertToDate(
                    mip.getSchedulebegindate(), datetimePattern));// 时间戳-date转换
            oaSchedule.setPlanEndTime(DateUtil.convertToDate(
                    mip.getScheduleenddate(), datetimePattern));// 时间戳-date转换
            oaSchedule.setImportantTag(mip.getIsimportant());
            oaSchedule.setRemark(mip.getScheduleremark());
            oaSchedule.setCreatedate(new Date());// 当前日期
            oaSchedule.setAddress(mip.getAddress());
            // oaSchedule.setLastnoticetime(DatetimeOpt.convertStringToDate(
            // remindtime, "yyyy-MM-dd HH:mm:ss"));// 最后提醒时间

            object.copyNotNullProperty(oaSchedule);// 非空字段更新

            oaScheduleManager.saveObject(object);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("updatePersonalSchedule", operatorId, json,info, returnInfo, printStackTrace, "通过该接口，修改一条已经存在的个人日程");
        return returnInfo;
    }

    /**
     * 通过该接口，删除一条已经存在的个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    @Override
    public String deletePersonalSchedule(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);
            OaSchedule oaSchedule = oaScheduleManager.getObjectById(mip
                    .getScheduleid());
            if (oaSchedule != null) {
                oaScheduleManager.deleteObjectById(mip.getScheduleid());
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("deletePersonalSchedule", operatorId, json, info,returnInfo, printStackTrace, "通过该接口，删除一条已经存在的个人日程");
        return returnInfo;
    }

    /**
     * 通过该接口获取登录用户权限内可访问的领导日程列表，支持条件查询
     * 
     * @param userid
     *            用户唯一ID
     * @param cycletime
     *            时间周期（1,2,3）
     * @param cycletimetype
     *            时间周期类型（0全部、1周、2月、3季度、4年）
     * @param starttime
     *            起始时间
     * @param currentdatetime
     *            当前记录时间
     * @param pagesize
     *            请求记录条数
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    @Override
    public String getLeaderScheduleList(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaSchedule> objList = null;
        List<OaScheduleMIP> objMIPList = new ArrayList<OaScheduleMIP>();
        try {
            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /*
             * long endTime = DateUtil.getTime(mip.getStarttime(),
             * mip.getCycletime(), mip.getCycletimetype());
             */
            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("pBegin", DateUtil.convertToString(
                        mip.getStarttime(), datetimePattern));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("pEnd", DateUtil.convertToString(
                        mip.getEndtime(), datetimePattern));// 结束时间
            // 时间戳
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            filterMap.put("ORDER_BY", "createdate desc");// 只有创建时间可以精确到秒

            filterMap.put("sehType", "2");// 领导日程
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            objList = oaScheduleManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaSchedule oaSchedule : objList) {
                    OaScheduleMIP mipTemp = new OaScheduleMIP();
                    mipTemp.setScheduleid(JsonUtil.replaceNullString(oaSchedule
                            .getSchno()));
                    mipTemp.setScheduletitle(JsonUtil
                            .replaceNullString(oaSchedule.getTitle()));
                    mipTemp.setSchedulebegindate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getPlanBegTime() ? null : String
                                    .valueOf(oaSchedule.getPlanBegTime()
                                            .getTime())));
                    mipTemp.setScheduleenddate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getPlanEndTime() ? null : String
                                    .valueOf(oaSchedule.getPlanEndTime()
                                            .getTime())));
                    mipTemp.setCreatedate(JsonUtil
                            .replaceNullString(null == oaSchedule
                                    .getCreatedate() ? null : String
                                    .valueOf(oaSchedule.getCreatedate()
                                            .getTime())));
                    mipTemp.setIsimportant(JsonUtil
                            .replaceNullString(oaSchedule.getImportantTag()));
                    mipTemp.setPublisher(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", oaSchedule.getCreater())));// 发布人姓名
                    mipTemp.setLeaders(JsonUtil.replaceNullString(oaSchedule
                            .getLeaders()));
                    mipTemp.setAddress(JsonUtil.replaceNullString(oaSchedule
                            .getAddress()));
                    mipTemp.setScheduleremark(JsonUtil
                            .replaceNullString(oaSchedule.getRemark()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("schedulelist",
                    JsonUtil.createOtherleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getLeaderScheduleList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取登录用户权限内可访问的领导日程列表，支持条件查询");
        return returnInfo;
    }

    /**
     * 通过该接口获取具体某一条领导日程详细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    @Override
    public String getLeaderScheduleDetail(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        try {

            OaScheduleMIP mip = (OaScheduleMIP) JsonUtil.getObject4GsonString(
                    json, OaScheduleMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            OaSchedule oaSchedule = oaScheduleManager.getObjectById(mip
                    .getScheduleid());

            // 按接口要求封装数据
            if (null != oaSchedule) {
                mip = new OaScheduleMIP();
                mip.setScheduleid(JsonUtil.replaceNullString(oaSchedule
                        .getSchno()));
                mip.setScheduletitle(JsonUtil.replaceNullString(oaSchedule
                        .getTitle()));
                mip.setSchedulebegindate(JsonUtil.replaceNullString(null == oaSchedule
                        .getPlanBegTime() ? null : String.valueOf(oaSchedule
                        .getPlanBegTime().getTime())));
                mip.setScheduleenddate(JsonUtil.replaceNullString(null == oaSchedule
                        .getPlanEndTime() ? null : String.valueOf(oaSchedule
                        .getPlanEndTime().getTime())));
                mip.setIsimportant(JsonUtil.replaceNullString(oaSchedule
                        .getImportantTag()));
                mip.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("usercode", oaSchedule.getCreater())));
                mip.setLeaders(JsonUtil.replaceNullString(oaSchedule
                        .getLeaders()));
                mip.setScheduleremark(JsonUtil.replaceNullString(oaSchedule
                        .getRemark()));
                mip.setAddress(JsonUtil.replaceNullString(oaSchedule
                        .getAddress()));

            }

            dataJson.put("schedulel", JsonUtil.createOtherFormJsonString(mip));
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getLeaderScheduleDetail", operatorId, json, info,returnInfo, printStackTrace, " 通过该接口获取具体某一条领导日程详细信息");
        return returnInfo;
    }

    /**
     * 通过该接口获取个人权限范围内的文档列表，支持条件查询，分页显示。[包括 个人文档 /部门文档 /资料库]
     * 
     * @param userid
     *            用户唯一ID
     * @param foldertype
     *            部门文档7 人文件夹 8 公共文件9 文件夹类型 6部门共享文档（资料库）
     * @param parentnode
     *            上级节点id（空字符串为获取根节点）
     * @param currentdatetime
     *            分页用检索时间
     * @param pagesize
     *            文档标题
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    @Override
    public String getPersonalDocList(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        FUserDetail fUserDetail = new FUserDetail();

        List<Publicinfo> objList = null;
        List<PublicinfoMIP> objMIPList = new ArrayList<PublicinfoMIP>();
        // 获取人员信息

        try {

            PublicinfoMIP mip = (PublicinfoMIP) JsonUtil.getObject4GsonString(
                    json, PublicinfoMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            String rootunitcode = mip.getUnitcode();// 部门编号
            if ("6".equals(mip.getFoldertype())) {// 参数验证
                if (StringUtils.isBlank(rootunitcode)) {
                    throw new Exception("请输入部门信息！");
                }
            }

            Publicinfo pinfo = null;
            int infoauth = 0;// 根目录权限
            /** 获取参数end **/

            /** 处理查询条件beg **/
            // parentnode 获取根目录
            String infocode = mip.getParentnode();// 上级节点
            if (StringUtils.isNotBlank(mip.getFoldertype())
                    && StringUtils.isBlank(infocode)) {

                if ("6".equals(mip.getFoldertype())) { // 部门共享（资料库）
                    pinfo = publicinfoManager.getUnitShareRootDirectory(
                            rootunitcode, fUserDetail);// 部门共享根目录
                }

                if ("7".equals(mip.getFoldertype())) {// 部门文档
                    pinfo = publicinfoManager.getUnitRootDirectory(fUserDetail);
                }
                if ("8".equals(mip.getFoldertype())) {// 人文件夹
                    pinfo = publicinfoManager
                            .getPersonalRootDirectory(fUserDetail);
                }
                if ("9".equals(mip.getFoldertype())) {// 公共文件夹
                    pinfo = publicinfoManager.getPublicRootDirectory();
                }

                infocode = pinfo.getInfocode();
            }
            // 获取parentnode目录权限

            if (null == pinfo) {
                pinfo = publicinfoManager.getObjectById(mip.getParentnode());
            }

            if (null == pinfo) {
                throw new Exception("文档不存在！");
            }

            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("filename", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(infocode))
                filterMap.put("parentinfocode", infocode);

            filterMap.put("ORDER_BY",
                    " isfolder desc , foldertype ,modifytime desc");// 只有创建时间可以精确到秒
            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            /** 处理查询条件end **/

            if (StringUtils.isNotBlank(mip.getFoldertype())) {

                if ("6".equals(mip.getFoldertype())) {// 部门共享
                    infoauth = publicinfoManager.getUnitShareAuthority(pinfo,
                            fUserDetail);
                    filterMap.put("unitcode",
                            null == rootunitcode ? fUserDetail.getPrimaryUnit()
                                    : rootunitcode);// 容错 默认查询本部门
                    objList = publicinfoManager.queryUnitShareFiles2(filterMap,
                            pageDesc, fUserDetail);
                }

                if ("7".equals(mip.getFoldertype())) {// 部门文档
                    infoauth = publicinfoManager.getUnitAuthority(pinfo,
                            fUserDetail);
                    filterMap.put("unitcode", fUserDetail.getPrimaryUnit());
                    objList = publicinfoManager.queryUnitFiles2(filterMap,
                            pageDesc, fUserDetail);
                }
                if ("8".equals(mip.getFoldertype())) {// 人文件夹
                    infoauth = publicinfoManager.getPersonalAuthority(pinfo,
                            fUserDetail);
                    filterMap.put("ownercode", fUserDetail.getUsercode());
                    objList = publicinfoManager.queryPersonalFiles2(filterMap,
                            pageDesc, fUserDetail);
                }
                if ("9".equals(mip.getFoldertype())) {// 公共文件夹
                    infoauth = publicinfoManager.getAuthority(pinfo,
                            fUserDetail);
                    objList = publicinfoManager.queryPublicFiles2(filterMap,
                            pageDesc, fUserDetail);
                }

            }

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (Publicinfo publicinfo : objList) {
                    // 获取附件信息
                    PublicinfoMIP mipTemp = new PublicinfoMIP();
                    mipTemp.setDocid(JsonUtil.replaceNullString(publicinfo
                            .getInfocode()));
                    mipTemp.setDocname(JsonUtil.replaceNullString(publicinfo
                            .getFilename()));
                    mipTemp.setDoctype(JsonUtil.replaceNullString(publicinfo
                            .getFileextension()));
                    mipTemp.setDocsize(JsonUtil.replaceNullString(null == publicinfo
                            .getFilesize() ? null : String.valueOf(publicinfo
                            .getFilesize())));
                    if (null != publicinfo.getFileinfoFs()) {
                        mipTemp.setDocurl(getFileDownloadUrl("PERSONALDOC",
                                publicinfo.getFileinfoFs().getFilecode()));
                    } else {
                        mipTemp.setDocurl(JsonUtil.replaceNullString(null));// 文件夹
                    }
                    mipTemp.setIsfolder(JsonUtil.replaceNullString(publicinfo
                            .getIsfolder()));
                    mipTemp.setCrud(JsonUtil.replaceNullString(publicinfo
                            .getAuthority()));// 操作权限
                    mipTemp.setModifytime(JsonUtil
                            .replaceNullString(null == publicinfo
                                    .getModifytime() ? null : String
                                    .valueOf(publicinfo.getModifytime()
                                            .getTime())));
                    mipTemp.setUsercode(publicinfo.getOwnercode());
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            // 追加根（父）目录信息获取权限
            JSONObject folderObj = new JSONObject();
            folderObj.put("folderId",
                    JsonUtil.replaceNullString(pinfo.getInfocode()));
            folderObj.put("folderName",
                    JsonUtil.replaceNullString(pinfo.getFilename()));
            folderObj.put("crud",
                    JsonUtil.replaceNullString(String.valueOf(infoauth)));
            dataJson.put("folder", folderObj);
            dataJson.put("fileList",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getPersonalDocList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取个人权限范围内的文档列表，支持条件查询，分页显示。[包括 个人文档 /部门文档 /资料库]");
        return returnInfo;
    }

    /**
     * 通过该接口上传文档
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param title
     *            文档标题
     * @param doctype
     *            文档类型
     * @param docurl
     *            文档下载地址
     * @param parentnode
     *            上级节点(空字符串默认上传到根节点)
     * @return
     */

    @Override
    public String uploadPersonalDoc(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        FUserDetail fUserDetail = new FUserDetail();
        try {

            PublicinfoMIP mip = (PublicinfoMIP) JsonUtil.getObject4GsonString(
                    json, PublicinfoMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());
            Publicinfo pinfo = null;
            String rootunitcode = mip.getUnitcode();// 部门编号
            if ("6".equals(mip.getFoldertype())) {// 参数验证
                if (StringUtils.isBlank(rootunitcode)) {
                    throw new Exception("请输入部门信息！");
                }
            }
            /** 获取参数end **/

            /** 处理查询条件beg **/
            // parentnode 获取根目录
            String infocode = mip.getParentnode();// 上级节点
            if (StringUtils.isNotBlank(mip.getFoldertype())
                    && StringUtils.isBlank(infocode)) {

                if ("6".equals(mip.getFoldertype())) { // 部门共享（资料库）
                    pinfo = publicinfoManager.getUnitShareRootDirectory(
                            rootunitcode, fUserDetail);// 部门共享根目录
                }

                if ("7".equals(mip.getFoldertype())) {// 部门文档
                    pinfo = publicinfoManager.getUnitRootDirectory(fUserDetail);
                }
                if ("8".equals(mip.getFoldertype())) {// 人文件夹
                    pinfo = publicinfoManager
                            .getPersonalRootDirectory(fUserDetail);
                }
                if ("9".equals(mip.getFoldertype())) {// 公共文件夹
                    pinfo = publicinfoManager.getPublicRootDirectory();
                }
                infocode = pinfo.getInfocode();
            }

            // 鉴权上传文件
            Publicinfo root = null;
            @SuppressWarnings("unused")
            Publicinfo publicinfo = null;

            // ---http://192.168.132.10:8088/oa/upload/tools/1.sql
            String PHONESERURL = mip.getDocurl();
            URL resourceUrl = new URL(PHONESERURL);
            // InputStream content = (InputStream) resourceUrl.getContent();

            HttpURLConnection httpUrl = (HttpURLConnection) resourceUrl
                    .openConnection();
            // httpUrl.connect();
            InputStream content = httpUrl.getInputStream();
            // httpUrl.disconnect();
            // 公共文件夹
            if ("9".equals(mip.getFoldertype())) {
                root = publicinfoManager.authenticate4UploadFile(infocode,
                        fUserDetail);
                FileinfoFs temp = processUploadedFile(content, mip.getTitle(),
                        mip.getDoctype(), CommonCodeUtil.PUBLICINFO_OPTCODE);
                publicinfo = publicinfoManager
                        .saveFile(temp, root, fUserDetail);
                // 部门文档
            } else if ("7".equals(mip.getFoldertype())) {
                root = publicinfoManager.authenticate4UploadUnitFile(infocode,
                        fUserDetail);
                FileinfoFs temp = processUploadedFile(content, mip.getTitle(),
                        mip.getDoctype(), CommonCodeUtil.UNITFILEINFO_OPTCODE);
                publicinfo = publicinfoManager.saveUnitFile(temp, root,
                        fUserDetail);
            }
            // 个人文件夹
            else if ("8".equals(mip.getFoldertype())) {
                root = publicinfoManager.authenticate4UploadPersonalFile(
                        infocode, fUserDetail);
                FileinfoFs temp = processUploadedFile(content, mip.getTitle(),
                        mip.getDoctype(), CommonCodeUtil.PERSONAL_OPTCODE);
                publicinfo = publicinfoManager.savePersonalFile(temp, root,
                        fUserDetail);
            }
            // 部门共享
            else if ("6".equals(mip.getFoldertype())) {
                root = publicinfoManager.authenticate4UploadUnitShareFile(
                        infocode, rootunitcode, fUserDetail);// 部门共享根目录
                FileinfoFs temp = processUploadedFile(content, mip.getTitle(),
                        mip.getDoctype(), CommonCodeUtil.UNITFILESHARE_OPTCODE);
                publicinfo = publicinfoManager.saveUnitShareFile(temp, root,
                        fUserDetail, rootunitcode);
            } else {
                throw new PublicInfoException("无效的业务类型。");
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("uploadPersonalDoc",operatorId, json, info,returnInfo, printStackTrace, "通过该接口上传文档");
        return returnInfo;
    }

    /**
     * 通过该接口可新增文档目录
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param parentnode
     *            上级节点id（空字符串为获取根节点）
     * @param foldername
     *            文件夹名称
     * @return
     */

    @Override
    public String createPersonalDocFolder(String json) {
       
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        FUserDetail fUserDetail = new FUserDetail();
        try {

            PublicinfoMIP mip = (PublicinfoMIP) JsonUtil.getObject4GsonString(
                    json, PublicinfoMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());
            String rootunitcode = mip.getUnitcode();// 部门编号
            if ("6".equals(mip.getFoldertype())) {// 参数验证
                if (StringUtils.isBlank(rootunitcode)) {
                    throw new Exception("请输入部门信息！");
                }
            }
            /** 获取参数end **/

            Publicinfo folder = null;
            Publicinfo pinfo = null;
            // parentnode 获取根目录
            String infocode = mip.getParentnode();// 上级节点
            if (StringUtils.isNotBlank(mip.getFoldertype())
                    && StringUtils.isBlank(infocode)) {
                if ("6".equals(mip.getFoldertype())) { // 部门共享（资料库）
                    pinfo = publicinfoManager.getUnitShareRootDirectory(
                            rootunitcode, fUserDetail);// 部门共享根目录
                }
                if ("7".equals(mip.getFoldertype())) {// 部门文档
                    pinfo = publicinfoManager.getUnitRootDirectory(fUserDetail);
                }
                if ("8".equals(mip.getFoldertype())) {// 人文件夹
                    pinfo = publicinfoManager
                            .getPersonalRootDirectory(fUserDetail);
                }
                if ("9".equals(mip.getFoldertype())) {// 公共文件夹
                    pinfo = publicinfoManager.getPublicRootDirectory();
                }
                infocode = pinfo.getInfocode();
            }

            // 公共文件夹
            if ("9".equals(mip.getFoldertype())) {
                folder = publicinfoManager.authenticate4AddFolder(
                        mip.getFoldername(), infocode, fUserDetail);
            }
            // 部门文档
            else if ("7".equals(mip.getFoldertype())) {
                folder = publicinfoManager.authenticate4AddUnitFolder(
                        mip.getFoldername(), infocode, fUserDetail);
            }
            // 个人文件夹
            else if ("8".equals(mip.getFoldertype())) {
                folder = publicinfoManager.authenticate4AddPersonalFolder(
                        mip.getFoldername(), infocode, fUserDetail);
            }
            // 部门共享
            else if ("6".equals(mip.getFoldertype())) {
                folder = publicinfoManager.authenticate4AddUnitShareFolder(
                        mip.getFoldername(), infocode, fUserDetail,
                        rootunitcode);// 部门共享根目录
            } else {
                throw new Exception("无效的业务类型。");
            }

            publicinfoManager.saveObject(folder);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("createPersonalDocFolder",operatorId, json, info,returnInfo, printStackTrace, "通过该接口可新增文档目录");
        return returnInfo;
    }

    /**
     * 文档目录修改或删除
     */
    @Override
    public String saveOrUpdatePersonalDocFolder(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        FUserDetail fUserDetail = new FUserDetail();
        try {
            PublicinfoMIP mip = (PublicinfoMIP) JsonUtil.getObject4GsonString(
                    json, PublicinfoMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());
            /** 获取参数end **/

            // 修改文件夹
            if ("0".equals(mip.getOperationtype())) {
                Publicinfo publicinfo = null;
                Publicinfo pinfo = publicinfoManager.getObjectById(mip.getId());
                // 公共文件夹
                if ("9".equals(mip.getFoldertype())) {
                    publicinfo = publicinfoManager.authenticate4RenameFiles(
                            pinfo.getInfocode(), pinfo.getParentinfocode(),
                            mip.getFoldername(), fUserDetail);
                }
                // 部门文档
                else if ("7".equals(mip.getFoldertype())) {
                    publicinfo = publicinfoManager
                            .authenticate4RenameUnitFiles(pinfo.getInfocode(),
                                    pinfo.getParentinfocode(),
                                    mip.getFoldername(), fUserDetail);
                }
                // 个人文件夹
                else if ("8".equals(mip.getFoldertype())) {
                    publicinfo = publicinfoManager
                            .authenticate4RenamePersonalFiles(
                                    pinfo.getInfocode(),
                                    pinfo.getParentinfocode(),
                                    mip.getFoldername(), fUserDetail);
                } else if ("6".equals(mip.getFoldertype())) { // 部门共享（资料库）
                    publicinfo = publicinfoManager
                            .authenticate4RenameUnitShareFiles(
                                    pinfo.getInfocode(),
                                    pinfo.getParentinfocode(),
                                    mip.getFoldername(), fUserDetail);// 部门共享根目录
                } else {
                    throw new Exception("无效的业务类型。");
                }

                publicinfoManager.saveObject(publicinfo);

                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            } else if ("1".equals(mip.getOperationtype())) {// 删除文件夹

                List<Publicinfo> toDeleteFiles = null;

                // 公共文件夹
                if ("9".equals(mip.getFoldertype())) {
                    toDeleteFiles = publicinfoManager.authenticate4DeleteFiles(
                            mip.getId(), fUserDetail);
                }
                // 部门文档
                if ("7".equals(mip.getFoldertype())) {
                    toDeleteFiles = publicinfoManager
                            .authenticate4DeleteGRBGBMWDFiles(mip.getId(),
                                    fUserDetail);
                }
                // 个人文件夹
                else if ("8".equals(mip.getFoldertype())) {
                    toDeleteFiles = publicinfoManager
                            .authenticate4DeletePersonalFiles(mip.getId(),
                                    fUserDetail);
                }
                // 部门共享（资料库）
                else if ("6".equals(mip.getFoldertype())) {
                    toDeleteFiles = publicinfoManager
                            .authenticate4DeleteUnitShareFiles(mip.getId(),
                                    fUserDetail);
                } else {
                    throw new PublicInfoException("无效的业务类型。");
                }

                for (Publicinfo publicinfo : toDeleteFiles) {
                    // this.deleteUploadFile(info);
                    publicinfoManager.deleteFile(publicinfo);
                }
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("saveOrUpdatePersonalDocFolder",operatorId, json, info,returnInfo, printStackTrace, "通过该接口可文档目录修改或删除");
        return returnInfo;
    }

    /**
     * 会议室列表:通过该接口获取会议室列表，支持条件查询，分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param currentdatetime
     *            检索时间类型
     * @param pagesize
     *            检索分页大小
     * @param keyword
     *            检索关键字
     * @return
     */

    @Override
    public String getBoardroomList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaMeetinfo> objList = null;
        List<OaMeetinfoMIP> objMIPList = new ArrayList<OaMeetinfoMIP>();
        try {

            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetinfoMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("meetingName", mip.getKeyword());// 搜索关键字
            filterMap.put("NP_isuse", true);
            filterMap.put("ORDER_BY", "createtime desc");// 只有创建时间可以精确到秒

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, 999999);// 该接口不分页

            // 获取所有会议室信息
            objList = oaMeetinfoManager.listObjects(filterMap, pageDesc);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaMeetinfo oaMeetinfo : objList) {
                    OaMeetinfoMIP mipTemp = getMipMeetApply(oaMeetinfo);
                    objMIPList.add(mipTemp);

                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("boardroomlist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBoardroomList",operatorId, json, info,returnInfo, printStackTrace, "会议室列表:通过该接口获取会议室列表，支持条件查询，分页显示");
        return returnInfo;
    }

    /**
     * 通过该接口获取车辆申请列表
     * 
     * @param json
     * @return
     */
    @Override
    public String getMeetApplyList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaMeetApply> objList = null;
        List<OaMeetApplyMIP> objMIPList = new ArrayList<OaMeetApplyMIP>();

        try {

            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetinfoMIP.class);

            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getBoardroomid()))
                filterMap.put("meetingNo", mip.getBoardroomid());// 会议室

            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("docpTimeBegin", DateUtil.convertToString(
                        mip.getStarttime(), "yyyy-MM-dd HH:mm:ss"));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("docpTimeEnd", DateUtil.convertToString(
                        mip.getEndtime(), "yyyy-MM-dd HH:mm:ss"));// 结束时间
            // STARTTIME,ENDTIME参数传空字符串默认获取当天会议情况
            if (StringUtils.isBlank(mip.getStarttime())
                    && StringUtils.isBlank(mip.getEndtime())) {
                filterMap.put(
                        "docpTimeBegin",
                        DatetimeOpt.convertDateToString(
                                DatetimeOpt.currentUtilDate(), "yyyy-MM-dd"));// 开始时间
                                                                              // 时间戳
                filterMap.put("docpTimeEnd", DatetimeOpt.convertDateToString(
                        DatetimeOpt.addDays(DatetimeOpt.currentUtilDate(), 1),
                        "yyyy-MM-dd"));// 结束时间
            }
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(mip.getType())) {
                if ("0".equals(mip.getType())) {// 仅自己的
                    filterMap.put("myself", mip.getUserid());
                } else if ("1".equals(mip.getType())) {// 自己+别人审核通过的
                    filterMap.put("mipSelf", mip.getUserid());
                }
            }

            filterMap.put("ORDER_BY", " cpBegTime  desc");
            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            /** 处理查询条件end **/

            objList = oaMeetApplyManager.getMeetApplyList(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaMeetApply oaMeetApply : objList) {
                    // 获取附件信息
                    OaMeetApplyMIP mipTemp = new OaMeetApplyMIP();
                    mipTemp.setMeetingid(JsonUtil.replaceNullString(oaMeetApply
                            .getDjId()));
                    mipTemp.setApplicantid(JsonUtil
                            .replaceNullString(oaMeetApply.getCreater()));
                    mipTemp.setApplicant(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "userCode", oaMeetApply.getCreater())));
                    mipTemp.setTitle(JsonUtil.replaceNullString(oaMeetApply
                            .getTitle()));
                    mipTemp.setStarttime(JsonUtil
                            .replaceNullString(null == oaMeetApply
                                    .getCpBegTime() ? null : String
                                    .valueOf(oaMeetApply.getCpBegTime()
                                            .getTime())));
                    mipTemp.setEndtime(JsonUtil
                            .replaceNullString(null == oaMeetApply
                                    .getCpEndTime() ? null : String
                                    .valueOf(oaMeetApply.getCpEndTime()
                                            .getTime())));
                    mipTemp.setState(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "meetState", oaMeetApply.getState())));
                    mipTemp.setMeetingName(oaMeetApply.getMeetingName());
                    mipTemp.setIsBasicUnit(oaMeetApply.getIsBasicUnit());
                    mipTemp.setIsStopCar(oaMeetApply.getIsStopCar());
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("meetinglist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMeetApplyList",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取车辆申请列表");
        return returnInfo;
    }

    /**
     * 处理会议室信息
     * 
     * @param oaMeetinfo
     * @return
     */
    private OaMeetinfoMIP getMipMeetApply(OaMeetinfo oaMeetinfo) {

        OaMeetinfoMIP mipTemp = new OaMeetinfoMIP();
        Date datetime = new Date();
        OaMeetApply apply = oaMeetApplyManager.getApplylistIsFree(
                oaMeetinfo.getDjid(), datetime, null);
        // 会议室最近可预订起始时间
        Date freeBegDate = new Date();
        Date freeEndDate = null;
        String Duration = "";
        /*
         * List<OaMeetApply> freeApplyList =
         * oaMeetApplyManager.getFreeApplylist( oaMeetinfo.getDjid(), datetime,
         * null);
         * 
         * if (null != freeApplyList && freeApplyList.size() > 1) { if (null !=
         * apply) {// 当前时间点不空闲，下一个可申请时间范围=当前时间后俩条数据的时间间隔 freeBegDate =
         * freeApplyList.get(0).getDoEndTime(); freeEndDate =
         * freeApplyList.get(1).getDoBegTime(); Duration =
         * String.valueOf(freeEndDate.getTime() - freeBegDate.getTime()); } if
         * (null == apply) {// 当前时间点空闲，下一个可申请时间范围=当前时间后一条数据起始时间-当前时间前一条数据结束时间
         * freeBegDate = datetime; freeEndDate =
         * freeApplyList.get(1).getDoBegTime(); Duration =
         * String.valueOf(freeEndDate.getTime() - freeBegDate.getTime()); }
         * 
         * } if (null != freeApplyList && freeApplyList.size() == 1) { if
         * (datetime.getTime() - freeApplyList.get(0).getDoEndTime().getTime() >
         * 0) {// 前一条数据 freeBegDate = datetime; } else {// 后一条数据 freeBegDate =
         * datetime;// 当前时间 freeEndDate = freeApplyList.get(0).getDoBegTime();
         * Duration = String.valueOf(freeEndDate.getTime() -
         * freeBegDate.getTime()); } }
         */

        mipTemp.setLasttime(JsonUtil.replaceNullString(String
                .valueOf(freeBegDate.getTime())));

        mipTemp.setStatus(null == apply ? "0" : "1");// 会议室当前时间已被申请
        mipTemp.setDuration(JsonUtil.replaceNullString(Duration));

        /*
         * //当前时间点不空闲，下一个可申请时间范围=当前时间后俩条数据的时间间隔 if (apply != null) {
         * mipTemp.setLasttime(JsonUtil.replaceNullString(String
         * .valueOf(apply.getDoEndTime().getTime())));
         * 
         * mipTemp.setStatus("1");// 会议室当前时间已被申请
         * mipTemp.setDuration(JsonUtil.replaceNullString(String
         * .valueOf(apply.getDoEndTime().getTime() - datetime.getTime()))); }
         * else { //当前时间点空闲，下一个可申请时间范围=当前时间后一条数据起始时间-当前时间前一条数据结束时间
         * mipTemp.setLasttime(JsonUtil.replaceNullString(String
         * .valueOf(datetime.getTime()))); mipTemp.setStatus("0");
         * mipTemp.setDuration("0");// 时长为0立即申请 }
         */
        mipTemp.setId(JsonUtil.replaceNullString(oaMeetinfo.getDjid()));
        mipTemp.setName(JsonUtil.replaceNullString(oaMeetinfo.getMeetingName()));

        return mipTemp;
    }

    /**
     * 会议室明细:通过该接口获取会议室明细信息，包括会议室名称、位置、预订情况，是否空闲等
     * 
     * @param userid
     *            用户唯一ID
     * @param boardroomid
     *            会议室id
     * @param starttime
     *            检索开始时间
     * @param endtime
     *            检索结束时间
     * 
     * @return
     */

    @Override
    public String getBoardroomDetail(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaMeetApply> objList = null;
        try {

            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetinfoMIP.class);

            loadUserByUsername(mip.getUserid());

            // mip.getBoardroomid()必填
            if (StringUtils.isNotBlank(mip.getBoardroomid())) {
                // 按接口要求封装数据--使用空字符串代替空值
                OaMeetinfo oaMeetinfo = oaMeetinfoManager.getObjectById(mip
                        .getBoardroomid());
                OaMeetinfoMIP mip2 = getMipMeetApply(oaMeetinfo);

                Map<String, Object> filterMap = new HashMap<String, Object>();
                if (StringUtils.isNotBlank(mip.getBoardroomid()))
                    filterMap.put("meetingNo", mip.getBoardroomid());// 开始时间 时间戳
                if (StringUtils.isNotBlank(mip.getStarttime()))
                    filterMap.put("docpTimeBegin", DateUtil.convertToString(
                            mip.getStarttime(), "yyyy-MM-dd HH:mm:ss"));// 开始时间
                                                                        // 时间戳
                if (StringUtils.isNotBlank(mip.getEndtime()))
                    filterMap.put("docpTimeEnd", DateUtil.convertToString(
                            mip.getEndtime(), "yyyy-MM-dd HH:mm:ss"));// 结束时间
                if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                    filterMap.put("currentdatetime", DateUtil.convertToString(
                            mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                if (StringUtils.isNotBlank(mip.getKeyword()))
                    filterMap.put("title", mip.getKeyword());// 搜索关键字

                filterMap.put("mipSelf", mip.getUserid()); // 能看到别人申请成功的记录，而可以看到自己创建所有的申请记录

                // STARTTIME,ENDTIME参数传空字符串默认获取当天会议情况
                if (StringUtils.isBlank(mip.getStarttime())
                        && StringUtils.isBlank(mip.getEndtime())) {
                    filterMap.put("docpTimeBegin", DatetimeOpt
                            .convertDateToString(DatetimeOpt.currentUtilDate(),
                                    "yyyy-MM-dd"));// 开始时间 时间戳
                    filterMap.put("docpTimeEnd", DatetimeOpt
                            .convertDateToString(
                                    DatetimeOpt.addDays(
                                            DatetimeOpt.currentUtilDate(), 1),
                                    "yyyy-MM-dd"));// 结束时间
                }

                PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                        .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                        : PAGESIZE);// 分页

                // 会议室明细：显示该会议室所有申请
                objList = oaMeetApplyManager.getMeetApplyList(filterMap,
                        pageDesc);

                List<InnerMeetinfo> oaMeetinfoMIPList = new ArrayList<InnerMeetinfo>();
                if (null != objList && objList.size() > 0) {
                    for (OaMeetApply meetinfo : objList) {
                        String begtime = null == meetinfo.getCpBegTime() ? null
                                : String.valueOf(meetinfo.getCpBegTime()
                                        .getTime());
                        String endtime = null == meetinfo.getCpEndTime() ? null
                                : String.valueOf(meetinfo.getCpEndTime()
                                        .getTime());
                        oaMeetinfoMIPList
                                .add(new InnerMeetinfo(meetinfo.getDjId(),
                                        oaMeetinfo.getCreater(),
                                        CodeRepositoryUtil.getValue("usercode", meetinfo.getCreater()),
                                        JsonUtil.replaceNullString(meetinfo.getTitle()), 
                                        JsonUtil.replaceNullString(begtime),
                                        JsonUtil.replaceNullString(endtime),
                                        JsonUtil.replaceNullString(meetinfo.getIsBasicUnit()), 
                                        JsonUtil.replaceNullString(meetinfo.getIsStopCar()), 
                                        JsonUtil.replaceNullString(meetinfo.getState()),
                                        JsonUtil.replaceNullString(meetinfo.getAttendingLeaderNames()),
                                        JsonUtil.replaceNullString( null==meetinfo.getMeetingPersionsNum()?"0":String.valueOf(meetinfo.getMeetingPersionsNum()))
                                      
                                        )
                                      );
                    }
                    mip2.setMeetinglist(oaMeetinfoMIPList);
                }

                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                dataJson.put("boardroom",
                        JsonUtil.createSimpleFormJsonString(mip2));
                info.setBizdata(dataJson);
            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "请输入需要查询的会议室!"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBoardroomDetail",operatorId, json, info,returnInfo, printStackTrace, "会议室明细:通过该接口获取会议室明细信息，包括会议室名称、位置、预订情况，是否空闲等");
        return returnInfo;
    }

    /**
     * 通过该接口可预订空闲会议室。
     */
    @Override
    public String bookBoardroom(String json) {
      
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            String flowCode = CommonOptCodeUtil.FLOWCODE_MEET_APPLY;;
            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetinfoMIP.class);
            loadUserByUsername(mip.getUserid());

            if (!(StringUtils.isBlank(mip.getStarttime()) && StringUtils
                    .isBlank(mip.getEndtime()))) {
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                // 处理数据
                OaMeetApply meetApply = new OaMeetApply();
                meetApply.setCreater(mip.getUserid());
                meetApply.setMeetingNo(mip.getBoardroomid());
                meetApply.setPlanBegTime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                meetApply.setPlanEndTime(DateUtil.convertToDate(
                        mip.getEndtime(), datetimePattern));
                meetApply.setCpBegTime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                meetApply.setCpEndTime(DateUtil.convertToDate(mip.getEndtime(),
                        datetimePattern));
                meetApply.setTitle(mip.getTitle());
                meetApply.setIsBasicUnit(mip.getIsBasicUnit());
                meetApply.setIsStopCar(mip.getIsStopCar());
                meetApply.setAttendingLeaderNames(BizCommUtil.getUsernamesFromUsercodes(mip.getMettingLeaders()));
                meetApply.setMeetingPersionsNum(StringUtils.isEmpty(mip.getMeetingPersionsNum())?0L:Long.parseLong(mip.getMeetingPersionsNum()));

                // 生成申请编号：编号规则以HY打头+时间戳
                String no = "HY-"
                        + new SimpleDateFormat("yyyyMMddHHmmss")
                                .format(new Date(System.currentTimeMillis()));

                if (StringUtils.isBlank(meetApply.getApplyNo())) {
                    meetApply.setApplyNo(no);
                }
                FlowDescribe flowDescribe = flowDefine
                        .getFlowDefObject(flowCode);
                meetApply.setOptid(flowDescribe.getOptId());
                meetApply.setBiztype("F");//
                meetApply.setBizstate("F");
                meetApply.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
                meetApply.setFlowcode(flowCode);
                meetApply.setCreatetime(DatetimeOpt.currentUtilDate());
                // 申请isuse状态为1
                meetApply.setState("1");
                meetApply.setDjId(oaMeetApplyManager.getNextKey());
                oaMeetApplyManager.saveObject(meetApply);

                meetApply = oaMeetApplyManager.getObjectById(meetApply
                        .getDjId());

                if (meetApply != null) {
                    OptBaseInfo optBaseInfo = optBaseInfoManager
                            .getObjectById(meetApply.getDjId());

                    if (optBaseInfo == null) {
                        optBaseInfo = new OptBaseInfo();
                        optBaseInfo.setDjId(meetApply.getDjId());

                        // 添加申请名称
                        // this.setTransaffairname(object, optBaseInfo);

                        optBaseInfo.setTransaffairname(meetApply.getTitle());
                        optBaseInfo.setOptId(flowDescribe.getOptId());
                        optBaseInfo.setFlowCode(flowCode);
                        optBaseInfo.setBiztype("F");//
                        optBaseInfo.setBizstate("F");
                        optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
                        optBaseInfo.setOrgname(fUserDetail.getUnitName());

                        optBaseInfo.setTransAffairNo(meetApply.getDjId());
                        optBaseInfo.setCreatedate(meetApply.getCreatetime());
                        optBaseInfo.setCreateuser(meetApply.getCreater());
                        optBaseInfoManager.saveObject(optBaseInfo);
                    }

                    FlowInstance flowInst = flowEngine.createInstance(flowCode,
                            meetApply.getTitle(), meetApply.getDjId(),
                            fUserDetail.getUsercode(),
                            fUserDetail.getPrimaryUnit());

                    long flowInstId = flowInst.getFlowInstId();

                    meetApply.setFlowInstId(flowInstId);
                    meetApply.setBiztype("W");// 等待审核
                    meetApply.setBizstate("W");

                    oaMeetApplyManager.saveObject(meetApply);

                    optBaseInfo = optBaseInfoManager.getObjectById(meetApply
                            .getDjId());
                    optBaseInfo.setFlowInstId(flowInstId);

                    optBaseInfo.setBiztype("W");// 等待审核
                    optBaseInfo.setBizstate("W");
                    optBaseInfoManager.saveObject(optBaseInfo);
                    // 将登记人员纳入办件角色
                    flowEngine.assignFlowWorkTeam(flowInstId, "jbr",
                            fUserDetail.getUsercode(), "登记人员");

                    OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
                    optIdeaInfo.setUsername(fUserDetail.getUsername());
                    FUnitinfo fUnitinfo = sysUnitManager
                            .getObjectById(fUserDetail.getPrimaryUnit().trim());
                    if (fUnitinfo == null) {
                        fUnitinfo = new FUnitinfo();
                    }
                    optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

                    optIdeaInfo.setTransidea("会议申请");

                    OptProcInfo procInfo = new OptProcInfo();
                    long nodeInstId = flowInst.getFirstNodeInstance()
                            .getNodeInstId();
                    procInfo.setNodeInstId(nodeInstId);
                    procInfo.setDjId(meetApply.getDjId());
                    procInfo.setNodename("会议申请");
                    procInfo.setTransdate(new Date(System.currentTimeMillis()));
                    procInfo.setNodeinststate("N");
                    procInfo.setUnitcode(fUserDetail.getPrimaryUnit());
                    procInfo.setUsercode(fUserDetail.getUsercode());
                    procInfo.setTransidea("会议申请");
                    procInfo.setNodeCode("会议申请");
                    optProcInfoManager.saveObject(procInfo);
                    optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

                }
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "请输入预定会议时间!"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBoardroomDetail",operatorId, json,info, returnInfo, printStackTrace, "通过该接口可预订空闲会议室");
        return returnInfo;
    }

    /**
     * 会议室取消预订:通过该接口，取消登录用户已经预订的会议室
     * 
     * @param userid
     *            用户唯一ID
     * @param meetingid
     *            会议实例id
     * @return
     */

    @Override
    public String cancelBookingBoardroom(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetinfoMIP.class);
            loadUserByUsername(mip.getUserid());

            fUserDetail = (FUserDetail) sysUserManager.loadUserByUsername(mip
                    .getUserid());

            if (StringUtils.isNotEmpty(mip.getMeetingid())) {
                OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(mip
                        .getMeetingid());
                if (mip.getUserid().equals(oaMeetApply.getCreater())) {
                    OptBaseInfo optBaseInfo = optBaseInfoManager
                            .getObjectById(mip.getMeetingid());
                    oaMeetApply.setState("7");// 取消
                    oaMeetApply.setBizstate("C");// 业务状态
                    oaMeetApply.setBiztype("C");// 业务类别
                    oaMeetApply.setSolvestatus("C");// 申请状态-取消
                    oaMeetApplyManager.saveObject(oaMeetApply);
                    flowManager.stopInstance(optBaseInfo.getFlowInstId(),
                            fUserDetail.getUsercode(), "取消会议申请");

                    // 1.获取参会人员
                    getUservalues("cancle", oaMeetApply.getDjId(),
                            fUserDetail.getUsercode());

                    String mesgTitle = "与您相关的一项会议申请[" + oaMeetApply.getTitle()
                            + "]已经被取消,请您及时关注。";
                    String mesgContent = oaMeetApply.getTitle()
                            + "于"
                            + DatetimeOpt.convertDatetimeToString(oaMeetApply
                                    .getCpBegTime())
                            + "到"
                            + DatetimeOpt.convertDatetimeToString(oaMeetApply
                                    .getCpEndTime()) + "取消举行。";

                    // 2.输送邮件数据
                    this.sendInnermesg(userValue, mesgTitle, mesgContent,
                            oaMeetApply.getDjId());

                    // 3.删除日程安排数据
                    oaScheduleManager.deleteByDjId(oaMeetApply.getDjId());

                    // 4.发送提醒
                    oaRemindInformationManager.sendOaRemindInformation(
                            oaMeetApply.getDjId(), fUserDetail.getUsercode(),
                            userValue, mesgTitle, mesgContent);

                    info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                } else {
                    info.setReturninfo(new CentitWSReturninfo("1",
                            "抱歉！您没有该项操作权限，请联系会议申请人取消申请！"));
                }

            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("cancelBookingBoardroom",operatorId, json, info,returnInfo, printStackTrace, "会议室取消预订:通过该接口，取消登录用户已经预订的会议室");
        return returnInfo;
    }

    /**
     * 通过该接口获取资讯列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            资讯类别：0 通知公告 1 规章制度 2 新闻发布 3 文件中心 4 公共资源。。。
     * 
     * @return
     */

    @Override
    public String getInfomationList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaInformation> objList = null;
        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();

        try {

            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();

            /*
             * if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
             * filterMap.put("currentdatetime", DateUtil.convertToString(
             * mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
             */if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(mip.getType()))
                filterMap.put("infoType", mip.getType());

            filterMap.put("state", "1");// 启用
            // 置顶排序
            filterMap
                    .put("ORDER_BY",
                            " (case when istop = 'T'  then  11   else  99  end),creatertime  desc");
            filterMap.put("NP_bettentime", true);// 查询有效期内
            PageDesc pageDesc = new PageDesc(StringUtils.isNotBlank(mip
                    .getPagenum()) ? Integer.valueOf(mip.getPagenum()) : 1,
                    StringUtils.isNotBlank(mip.getPagesize()) ? Integer
                            .valueOf(mip.getPagesize()) : PAGESIZE);// 分页

            /** 处理查询条件end **/

            objList = oaInformationManager.listObjects(filterMap, pageDesc);

            // 添加阅读状态
            // ===================================
            if (objList != null && objList.size() > 0) {
                for (int i = 0; i < objList.size(); i++) {
                    OaInformation oa = objList.get(i);
                    // =============办件添加是否阅读状态start=============
                    UserbizoptLog uboptlog = userbizoptLogManager.listObject(
                            oa.getNo(), mip.getUserid());
                    if (uboptlog != null) {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "T"));// 已读
                    } else {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "F"));// 未读
                    }
                    /*
                     * if("T".equals(oa.getIsTop())){//置顶
                     * oa.setReadstate("置顶");//防止被再次排序 }
                     */
                    // ================== end ===========
                }
                // 排序
                Collections.sort(objList, new Comparator<OaInformation>() {

                    @Override
                    public int compare(OaInformation o1, OaInformation o2) {
                        String hits0 = "";
                        String hits1 = "";
                        if ("T".equals(o1.getIsTop())) {
                            hits0 = "置顶" + o1.getReadstate();
                        } else {
                            hits0 = o1.getReadstate();
                        }
                        if ("T".equals(o2.getIsTop())) {
                            hits1 = "置顶" + o2.getReadstate();
                        } else {
                            hits1 = o2.getReadstate();
                        }
                        // String hits1 = o2.getIsTop()+o2.getReadstate();
                        return hits1.compareTo(hits0);

                    }

                });
            }

            // ====================================

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaInformation publicinfo : objList) {
                    // 获取附件信息
                    OaInformationMIP mipTemp = new OaInformationMIP();

                    mipTemp.setId(JsonUtil.replaceNullString(publicinfo.getNo()));
                    mipTemp.setTitle(JsonUtil.replaceNullString(publicinfo
                            .getTitle()));
                    mipTemp.setPublishtime(JsonUtil
                            .replaceNullString(null == publicinfo
                                    .getCreatertime() ? null : String
                                    .valueOf(publicinfo.getCreatertime()
                                            .getTime())));
                    mipTemp.setPublisher(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", publicinfo.getCreater())));
                    mipTemp.setIsTop("T".equals(publicinfo.getIsTop()) ? "置顶"
                            : "");
                    mipTemp.setReadstate(JsonUtil.replaceNullString(publicinfo
                            .getReadstate()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("infomationList",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getInfomationList",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取资讯列表信息，支持条件查询，支持分页显示");
        return returnInfo;
    }

    /**
     * 通过该接口获取某一资讯详细信息，包括标题、正文、附件等
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯ID
     * @return
     */

    @Override
    public String getInformationDetail(String json) {
        
        HttpServletRequest request = XFireServletController.getRequest();

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();
        // 获取人员信息
        OaInformation publicinfo = new OaInformation();
        try {
            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            publicinfo = oaInformationManager.getObjectById(mip
                    .getInformationid());

            if ("1".equals(publicinfo.getState())) {// 只对已启用的保存
                FUserDetail user = new FUserDetail();
                user.setUsercode(mip.getUserid());
                user.setLoginip(getIpAddr(request));
                UserbizoptLog u = new UserbizoptLog(new UserbizoptLogId(
                        publicinfo.getTitle(), publicinfo.getNo()));
                userbizoptLogManager.saveUserbizoptLog(u, user);
            }

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != publicinfo) {
                // 获取附件信息
                OaInformationMIP mipTemp   = new OaInformationMIP();
                mipTemp.setId(JsonUtil.replaceNullString(publicinfo.getNo()));
                mipTemp.setTitle(JsonUtil.replaceNullString(publicinfo.getTitle()));
                mipTemp.setPublishtime(JsonUtil.replaceNullString(null == publicinfo
                        .getCreatertime() ? null : String.valueOf(publicinfo
                        .getCreatertime().getTime())));
                mipTemp.setPublishdept(JsonUtil.replaceNullString(publicinfo
                        .getDepno()));
                /*
                 * mip.setFileurl(JsonUtil.replaceNullString(publicinfo
                 * .getImagePath()));
                 */
                mipTemp.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("usercode", publicinfo.getCreater())));
                String oldChar = XFireServletController.getRequest()
                        .getContextPath() + "/";
                String newChar = getFileDownloadUrl().replace("download", "");
                mipTemp.setContent(JsonUtil.replaceNullString(null != publicinfo
                        .getBodyContent() ? publicinfo.getBodyContent()
                        .replace(oldChar, newChar) : null));
                String imagepath = getFileDownloadUrl()
                        + "?pathType=relative&contentPath="
                        + publicinfo.getImagePath();
                mipTemp.setFileurl(JsonUtil.replaceNullString(null != publicinfo
                        .getImagePath() ? imagepath : null));
                mipTemp.setFilename(JsonUtil.replaceNullString(publicinfo
                        .getUploadFileName()));
                
                
                //"type":资讯类别,"isAttend":是否参与
                mipTemp.setType(publicinfo.getInfoType());
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                List<OaInfosummary> summarylistons = oaInfosummaryManager.findInfoAttend(mip.getInformationid(), fUserDetail.getUsername());
                
                if(null !=summarylistons && summarylistons.size()>0){
                    mipTemp.setIsAttend("T");
                }else{
                    mipTemp.setIsAttend("F");
                }

                
                
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                dataJson.put("information",
                        JsonUtil.createSimpleFormJsonString(mipTemp));
                info.setBizdata(dataJson);
            } 
           /* else {
                info.setReturninfo(new CentitWSReturninfo("1", "编号"
                        + mip.getInformationid() + "没有对应信息！"));
            }*/

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getInformationDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取某一资讯详细信息，包括标题、正文、附件等");
        return returnInfo;
    }

    // 获取用户IP
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯id
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    @Override
    public String getInformationAttachs(String json) {
        
        log.info("传参:" + json);

        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();
        List<OaInformationAttachment> attachments = null;
        try {

            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            attachments = oaInformationAttachmentManager.findAttachments(mip
                    .getInformationid());
            if (null != attachments) {
                for (OaInformationAttachment attach : attachments) {
                    OaInformationMIP obj = new OaInformationMIP();
                    obj.setAttachid(attach.getId().toString());
                    obj.setAttachtitle(attach.getFileName());
                    obj.setAttachsize(attach.getFileSize().toString());
                    obj.setAttachurl(getFileDownloadUrl("INFOSTUFF", attach
                            .getId().toString()));
                    objMIPList.add(obj);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("attachments",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getInformationAttachs",operatorId, json, info,returnInfo, printStackTrace, "通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开");
        return returnInfo;
    }

    /**
     * 通过该接口获取市总文件列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            资讯类别：0 通知公告 1 规章制度 2 新闻发布 3 文件中心 4 公共资源。。。
     * 
     * @return
     */

    @Override
    public String getFileManagerList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaFilemanager> objList = null;
        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();

        try {

            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(mip.getType()))
                filterMap.put("infoType", mip.getType());

            filterMap.put("state", "T");// 启用
            filterMap.put("ORDER_BY", " creatertime  desc");// 只有创建时间可以精确到秒
            filterMap.put("NP_bettentime", true);// 查询有效期内
            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            /** 处理查询条件end **/

            objList = oaFilemanagerManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaFilemanager publicinfo : objList) {
                    // 获取附件信息
                    OaInformationMIP mipTemp = new OaInformationMIP();
                    mipTemp.setId(JsonUtil.replaceNullString(publicinfo.getNo()));
                    mipTemp.setTitle(JsonUtil.replaceNullString(publicinfo
                            .getTitle()));
                    mipTemp.setPublishtime(JsonUtil
                            .replaceNullString(null == publicinfo
                                    .getCreatetime() ? null : String
                                    .valueOf(publicinfo.getCreatetime()
                                            .getTime())));
                    mipTemp.setPublisher(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", publicinfo.getCreater())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("fileManagerList",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getFileManagerList",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取市总文件列表信息，支持条件查询，支持分页显示");
        return returnInfo;
    }

    /**
     * 通过该接口获取某一文件详细信息，包括标题、正文、附件等
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯ID
     * @return
     */

    @Override
    public String getFileManagerDetail(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();
        // 获取人员信息
        OaFilemanager publicinfo = new OaFilemanager();
        try {
            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            publicinfo = oaFilemanagerManager.getObjectById(mip.getId());

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != publicinfo) {
                // 获取附件信息
                mip = new OaInformationMIP();
                mip.setId(JsonUtil.replaceNullString(publicinfo.getNo()));
                mip.setTitle(JsonUtil.replaceNullString(publicinfo.getTitle()));
                mip.setPublishtime(JsonUtil.replaceNullString(null == publicinfo
                        .getCreatetime() ? null : String.valueOf(publicinfo
                        .getCreatetime().getTime())));

                mip.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("usercode", publicinfo.getCreater())));
                String oldChar = XFireServletController.getRequest()
                        .getContextPath() + "/";
                String newChar = getFileDownloadUrl().replace("download", "");
                mip.setContent(JsonUtil.replaceNullString(null != publicinfo
                        .getRemark() ? publicinfo.getRemark().replace(oldChar,
                        newChar) : null));
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                dataJson.put("information",
                        JsonUtil.createSimpleFormJsonString(mip));
                info.setBizdata(dataJson);
            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "编号"
                        + mip.getInformationid() + "没有对应信息！"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getFileManagerDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取某一文件详细信息，包括标题、正文、附件等");
        return returnInfo;
    }

    /**
     * 通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            id
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    @Override
    public String getFileManagerAttachs(String json) {
        
        log.info("传参:" + json);

        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();
        List<OaInformationAttachment> attachments = null;
        try {

            OaInformationMIP mip = (OaInformationMIP) JsonUtil
                    .getObject4GsonString(json, OaInformationMIP.class);

            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            attachments = oaInformationAttachmentManager.findAttachments(mip
                    .getId());
            if (null != attachments) {
                for (OaInformationAttachment attach : attachments) {
                    OaInformationMIP obj = new OaInformationMIP();
                    obj.setAttachid(attach.getId().toString());
                    obj.setAttachtitle(attach.getFileName());
                    obj.setAttachsize(attach.getFileSize().toString());
                    obj.setAttachurl(getFileDownloadUrl("INFOSTUFF", attach
                            .getId().toString()));
                    objMIPList.add(obj);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("attachments",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getFileManagerAttachs",operatorId, json, info,returnInfo, printStackTrace, "通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开");
        return returnInfo;
    }

    /**
     * 通过该接口获取登录用户权限内的待办已办公文列表 1. 公文类型区分收文、发文 2. 支持条件查询 3. 支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param handletype
     *            检索待办还是已办信息，0为待办 1为已办
     * @param readstatus
     *            阅读状态 2 全部0已读 1 未读 默认为2
     * 
     * @return
     */

    @Override
    public String getOfficialList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<VuserTaskListOA> objList = null;
        List<VOptBaseList> optBaseList = null;

        List<OfficialMIP> objMIPList = new ArrayList<OfficialMIP>();
        try {

            OfficialMIP mip = (OfficialMIP) JsonUtil.getObject4GsonString(json,
                    OfficialMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = OfficialListGetFilterMap(mip);

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            // 检索待办还是已办信息，W为待办 C为已办
            if ("W".equals(mip.getHandletype())) {
                filterMap.put("ORDER_BY", "criticalLevel desc, readState asc,createtime desc ");//排序

                objList = vuserTaskListOAManager.listObjects(filterMap,
                        pageDesc);
                setUserTaskList(objList, objMIPList, mip.getUserid());
            } else if ("C".equals(mip.getHandletype())) {
                filterMap.put("itemType", mip.getType());// SW收文FW发文SQ内部事项DB督办。。。值为各业务流水的前缀
                filterMap.put("usercode", mip.getUserid());// 人员
                
                optBaseList = vOptBaseListManager.listObjects(filterMap,
                        pageDesc);
                setOptTaskList(optBaseList, objMIPList);
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialList",operatorId, json,info, returnInfo, printStackTrace, "通过该接口获取登录用户权限内的待办已办公文列表 1. 公文类型区分收文、发文 2. 支持条件查询 3. 支持分页显示");
        return returnInfo;
    }

    // 检索待办还是C已办信息，W为待办
    private void setUserTaskList(List<VuserTaskListOA> objList,
            List<OfficialMIP> objMIPList, String usercode) {
        // 按接口要求封装数据--使用空字符串代替空值
        List<FDatadictionary> datas = dictionaryManager.findByCdtbnm("critical_level");
        if (null != objList && objList.size() > 0) {
            for (VuserTaskListOA task : objList) {
                OfficialMIP mip = new OfficialMIP();
                mip.setDocid(JsonUtil.replaceNullString(task.getDjId()));
                mip.setTitle(JsonUtil.replaceNullString(task
                        .getTransaffairname() + "[" + task.getNodeName() + "]"));
                mip.setCreateusername(CodeRepositoryUtil.getValue("usercode",
                        task.getCreateuser()));
                mip.setCreatedatetime(JsonUtil.replaceNullString(null == task
                        .getCreatedate() ? null : String.valueOf(task
                        .getCreatedate().getTime())));
                mip.setNodeinstid(JsonUtil.replaceNullString(String
                        .valueOf(task.getNodeInstId())));
                mip.setHandlesource(JsonUtil.replaceNullString(String
                        .valueOf(task.getGwNature())));
                
                if(StringUtils.isNotBlank(task.getCriticalLevel())){
                    if(null !=datas && datas.size()>0){
                        for(FDatadictionary data :datas){
                            if(task.getCriticalLevel().equals(data.getDatacode())){
                                mip.setEmergercydegree(data.getDatavalue());
                            }
                        }
                    }
                }
                // =============办件添加是否阅读状态start=============
                UserbizoptLog uboptlog = userbizoptLogManager.listObject(
                        task.getDjId(), usercode, task.getNodeInstId());
                if (uboptlog != null) {
                    mip.setReadstate(CodeRepositoryUtil.getValue("readstate",
                            "T"));// 已读
                } else {
                    mip.setReadstate(CodeRepositoryUtil.getValue("readstate",
                            "F"));// 未读
                }
                objMIPList.add(mip);
            }
        }

    }

    // 检索待办还是已办信息，C为已办
    private void setOptTaskList(List<VOptBaseList> objList,
            List<OfficialMIP> objMIPList) {
        // 按接口要求封装数据--使用空字符串代替空值
        List<FDatadictionary> datas = dictionaryManager.findByCdtbnm("critical_level");
        if (null != objList && objList.size() > 0) {
            for (VOptBaseList task : objList) {
                OfficialMIP mip = new OfficialMIP();
                OptBaseInfo optBase = optBaseInfoManager
                        .getObjectById(task.getDjId());
                mip.setDocid(JsonUtil.replaceNullString(task.getDjId()));
                mip.setTitle(JsonUtil.replaceNullString(task
                        .getTransaffairname()));
                if(null != optBase){
                    if(StringUtils.isNotBlank(optBase.getCriticalLevel())){
                        if(null !=datas && datas.size()>0){
                            for(FDatadictionary data :datas){
                                if(optBase.getCriticalLevel().equals(data.getDatacode())){
                                    mip.setEmergercydegree(data.getDatavalue());
                                }
                            }
                        }
                    }
                }
                mip.setCreateusername(CodeRepositoryUtil.getValue("usercode",
                        task.getCreater()));
                mip.setCreatedatetime(JsonUtil.replaceNullString(null == task
                        .getCreatedate() ? null : String.valueOf(task
                        .getCreatedate().getTime())));
                mip.setNodeinstid("");
                mip.setHandlesource(JsonUtil.replaceNullString(task
                        .getGwNature()));
                objMIPList.add(mip);
            }
        }

    }

    /**
     * 处理查询条件
     * 
     * @param mip
     * @return
     */
    private Map<String, Object> OfficialListGetFilterMap(OfficialMIP mip) {
        Map<String, Object> filterMap = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
            filterMap.put("currentdatetime", DateUtil.convertToString(
                    mip.getCurrentdatetime(), datetimePattern));// 当前记录时间 时间戳
        if (StringUtils.isNotBlank(mip.getKeyword()))
            filterMap.put("transaffairname", mip.getKeyword());// 搜索关键字
        if (StringUtils.isNotBlank(mip.getUserid()))
            filterMap.put("userCode", mip.getUserid());// 用户唯一ID
        if (StringUtils.isNotBlank(mip.getType()))
            filterMap.put("itemtype", mip.getType());// SW收文FW发文SQ内部事项DB督办。。。值为各业务流水的前缀
        if (StringUtils.isNotBlank(mip.getHandlesource()))//公文来源
            filterMap.put("gwNature", mip.getHandlesource());// 01上级机构  02 本机构
        
        filterMap.put("ORDER_BY", "createdate desc");// 只有创建时间可以精确到秒 默认排序
        return filterMap;
    };

    /**
     * 通过该接口，获取某一公文明细信息以及公文锁定状态等
     */
    @Override
    public String getOfficialDetail(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        OptBaseInfo optBase = new OptBaseInfo();
        OptProcLock optLock = new OptProcLock();

        try {

            OfficialMIP mip = (OfficialMIP) JsonUtil.getObject4GsonString(json,
                    OfficialMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            optBase = optBaseInfoManager
                    .getObjectById(mip.getMessageitemguid());

            // 按接口要求封装数据
            if (null != optBase) {
                String djId = mip.getMessageitemguid();
                optLock = optProcLockManager.getOptProcLock(mip
                        .getMessageitemguid());
                /*
                 * optLock = optProcLockManager.getOptProcLock(
                 * mip.getMessageitemguid(), optBase.getBizstate().equals("L") ?
                 * "T" : "F");
                 */
                OptProcCollection collection = optProcCollectionManager
                        .getObjectById(new OptProcCollectionId(mip
                                .getMessageitemguid(), mip.getUserid()));
                String type = mip.getMessageitemguid().substring(0,
                        mip.getMessageitemguid().indexOf("0"));
                String emergercydegree = "";// 紧急程度
                // 紧急程度
                if ("SW".equals(type)) {
                    IncomeDoc doc = incomeDocManager.getObjectById(mip
                            .getMessageitemguid());
                    emergercydegree = null == doc ? "" : CodeRepositoryUtil
                            .getValue("critical_level", doc.getCriticalLevel());// 1.平急
                                                                                // 2.加急
                                                                                // 3.特急，4.特提5急件
                } else if ("FW".equals(type)) {
                    DispatchDoc doc = dispatchDocManager.getObjectById(mip
                            .getMessageitemguid());
                    emergercydegree = null == doc ? "" : CodeRepositoryUtil
                            .getValue("critical_level", doc.getCriticalLevel());// 1.平急
                                                                                // 2.加急
                                                                                // 3.特急，4.特提5急件
                }
                mip = new OfficialMIP();
                mip.setCreateusername(JsonUtil
                        .replaceNullString(CodeRepositoryUtil.getValue(
                                "usercode", optBase.getCreateuser())));// 创建人
                mip.setCreatedatetime(JsonUtil.replaceNullString(null == optBase
                        .getCreatedate() ? null : String.valueOf(optBase
                        .getCreatedate().getTime())));// 创建时间
                mip.setModifydatetime(JsonUtil.replaceNullString(null));// 修改时间
                mip.setHandlestatus(JsonUtil.replaceNullString(optBase
                        .getBizstate()));
                mip.setLockstatus(JsonUtil
                        .replaceNullString(null == optLock ? "F" : optLock
                                .getIslocked()));// 锁状态 F未锁 T锁定
                mip.setLockuserid(null == optLock ? "" : optLock.getUsercode());
                mip.setLockusername(null == optLock ? "" : CodeRepositoryUtil
                        .getValue("usercode", optLock.getUsercode()));
                mip.setLocktime(JsonUtil.replaceNullString(null == optLock ? ""
                        : (null == optLock.getLocksettime() ? null : String
                                .valueOf(optLock.getLocksettime().getTime()))));

                mip.setCollectstatus(JsonUtil
                        .replaceNullString(null == collection ? "F"
                                : collection.getIsatt()));//
                mip.setEmergercydegree(JsonUtil
                        .replaceNullString(emergercydegree));
                mip.setIsEndStep(JsonUtil.replaceNullString(null));
                mip.setWfType(JsonUtil.replaceNullString(null));
                // 获取pdf信息
                if ("true".equals(SysParametersUtils.getParameters(
                        "generatePdfForDocEnable", "false"))) {
                    OptPdfInfo optPdfInfo = optPdfInfoManager
                            .findNewestPdfInfo(djId);
                    if (optPdfInfo != null) {
                        mip.setDocurl(getFileDownloadUrl("PDF", optPdfInfo
                                .getId().toString()));
                        mip.setFilePw(Cryptos.aesEncrypt(
                                optPdfInfo.getSecretKey(),
                                PDFUtil.AES_USER_SECRET_KEY));
                        mip.setDocFileId(optPdfInfo.getId().toString());
                    } else {
                        mip.setDocurl("");
                        mip.setFilePw("");
                    }
                } else {
                    mip.setDocurl("");
                    mip.setFilePw("");
                }
                dataJson.put("docinfo",
                        JsonUtil.createSimpleFormJsonString(mip));
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                info.setBizdata(dataJson);
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，获取某一公文明细信息以及公文锁定状态等");
        return returnInfo;
    }

    /**
     * 公文历史审批记录:通过该接口，获取当前公文历史审批记录
     * 
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文(若收发文id不可能重复，改字段可废弃)
     * 
     * @return
     */

    @Override
    public String getOfficialApprovalList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OptIdeaInfo> objList = null;
        List<OfficialApprovalMIP> objMIPList = new ArrayList<OfficialApprovalMIP>();
        try {

            OfficialApprovalMIP mip = (OfficialApprovalMIP) JsonUtil
                    .getObject4GsonString(json, OfficialApprovalMIP.class);

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            objList = optProcInfoManager.listIdeaLogsByDjId(mip
                    .getMessageitemguid());

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OptIdeaInfo ideaInfo : objList) {
                    OfficialApprovalMIP mipTemp = new OfficialApprovalMIP();
                    mipTemp.setStepid(JsonUtil.replaceNullString(String
                            .valueOf(ideaInfo.getProcid())));// 步骤id
                    mipTemp.setStepname(JsonUtil.replaceNullString(ideaInfo
                            .getNodename()));// 步骤名称

                    mipTemp.setUsername(JsonUtil.replaceNullString(ideaInfo
                            .getUsername()));// username

                    mipTemp.setOperatdate(JsonUtil.replaceNullString(null == ideaInfo
                            .getTransdate() ? null : String.valueOf(ideaInfo
                            .getTransdate().getTime())));
                    mipTemp.setOption(JsonUtil.replaceNullString(ideaInfo
                            .getTranscontent()));// 处理意见
                    if ("true".equals(SysParametersUtils.getParameters(
                            "generatePdfForDocEnable", "false"))) {
                        OptPdfInfo optPdfInfo = optPdfInfoManager
                                .findPdfInfoBy(mip.getMessageitemguid(),
                                        ideaInfo.getNodeInstId(),
                                        ideaInfo.getUsercode());

                        if (optPdfInfo != null) {
                            mipTemp.setDocurl(optPdfInfo.getId() == null ? ""
                                    : getFileDownloadUrl("PDF", optPdfInfo
                                            .getId().toString()));
                            mipTemp.setFilePw(optPdfInfo.getSecretKey() == null ? ""
                                    : Cryptos.aesEncrypt(
                                            optPdfInfo.getSecretKey(),
                                            PDFUtil.AES_USER_SECRET_KEY));
                        } else {
                            mipTemp.setDocurl("");
                            mipTemp.setFilePw("");
                        }
                    } else {
                        mipTemp.setDocurl("");
                        mipTemp.setFilePw("");
                    }
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialApprovalList",operatorId, json, info,returnInfo, printStackTrace, "公文历史审批记录:通过该接口，获取当前公文历史审批记录");
        return returnInfo;
    }

    /**
     * 公文历史审批记录:通过该接口，获取当前公文历史审批记录
     * 
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文(若收发文id不可能重复，改字段可废弃)
     * 
     * @return
     */

    @Override
    public String getOfficialActiveNodeList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<VuserTaskListOA> objList = null;
        List<OfficialApprovalMIP> objMIPList = new ArrayList<OfficialApprovalMIP>();
        try {

            OfficialApprovalMIP mip = (OfficialApprovalMIP) JsonUtil
                    .getObject4GsonString(json, OfficialApprovalMIP.class);

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("djId", mip.getMessageitemguid());
            objList = vuserTaskListOAManager.listObjects(filterMap);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (VuserTaskListOA ideaInfo : objList) {
                    OfficialApprovalMIP mipTemp = new OfficialApprovalMIP();
                    mipTemp.setStepid(JsonUtil.replaceNullString(String
                            .valueOf(ideaInfo.getNodeInstId())));// 步骤id
                    mipTemp.setStepname(JsonUtil.replaceNullString(ideaInfo
                            .getNodeName()));// 步骤名称

                    mipTemp.setUsername(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", ideaInfo.getUserCode())));// username
                    mipTemp.setOperatdate(JsonUtil.replaceNullString(null == ideaInfo
                            .getCreatedate() ? null : String.valueOf(ideaInfo
                            .getCreatedate().getTime())));

                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("steplist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialActiveNodeList",operatorId, json, info,returnInfo, printStackTrace, "公文历史审批记录:通过该接口，获取当前公文历史审批记录");
        return returnInfo;
    }

    /**
     * 公文流程下一步信息:通过该接口，根据OA系统流程配置。获取当前公文流程下一步信息，包括下步骤接受人员
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    @SuppressWarnings("unchecked")
    @Override
    public String getNextOfficialProcessing(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        List<OfficialNextProMIP> objMIPList = new ArrayList<OfficialNextProMIP>();
        try {

            OfficialNextProMIP mip = (OfficialNextProMIP) JsonUtil
                    .getObject4GsonString(json, OfficialNextProMIP.class);
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            @SuppressWarnings("rawtypes")
            ThreadLocal<CentitWebCommonBizServiceImpl> centitWebCommonBizServiceImplThreadLocal = new ThreadLocal<CentitWebCommonBizServiceImpl>();
            @SuppressWarnings("rawtypes")
            CentitWebCommonBizServiceImpl centitWebCommonBizServiceImpl = getCentitWebCommonBizServiceImpl(centitWebCommonBizServiceImplThreadLocal);
            centitWebCommonBizServiceImpl.setfUserDetail(fUserDetail);

            objMIPList = centitWebCommonBizServiceImpl
                    .getNextOfficialProcessing(mip);
            String canSendmessg = centitWebCommonBizServiceImpl
                    .canSendmessg(mip);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("canSendmessg", canSendmessg);
            dataJson.put("nextsteplist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getNextOfficialProcessing",operatorId, json, info,returnInfo, printStackTrace, "公文流程下一步信息:通过该接口，根据OA系统流程配置。获取当前公文流程下一步信息，包括下步骤接受人员");
        return returnInfo;

    }

    /**
     * 通过该接口，发送已经签批或者签署意见的公文到OA系统。 手写签批公文返回签批的PDF文件 签署意见的公文返回录入的意见信息、录入人员、时间等
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param nextstepids
     *            下一步处理步骤id
     * @param nextpersonid
     *            下一步的处理人ID,指对应大平台中的人员id，多个用户之间采用;分开。
     * @param opinion
     *            处理意见
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param docfileurl
     *            Pdf下载地址
     * @param docfileid
     *            原加密文件id
     * 
     * @return
     */

    @Override
    public String sendOfficialProcess(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        try {

            OfficialSendProMIP mip = (OfficialSendProMIP) JsonUtil
                    .getObject4GsonString(json, OfficialSendProMIP.class);
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());
            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            @SuppressWarnings("rawtypes")
            ThreadLocal<CentitWebCommonBizServiceImpl> centitWebCommonBizServiceImplThreadLocal = new ThreadLocal<CentitWebCommonBizServiceImpl>();
            @SuppressWarnings("rawtypes")
            CentitWebCommonBizServiceImpl centitWebCommonBizServiceImpl = getCentitWebCommonBizServiceImpl(centitWebCommonBizServiceImplThreadLocal);
            centitWebCommonBizServiceImpl.setfUserDetail(fUserDetail);
            centitWebCommonBizServiceImpl.sendOfficialProcess(mip);

            if ("true".equals(SysParametersUtils.getParameters(
                    "generatePdfForDocEnable", "false"))) {
                NodeInstance nodeInst = flowEngine.getNodeInstById(Long
                        .valueOf(mip.getNodeinstid()));
                FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                        .getNodeId());
                OptPdfInfo optPdfInfo = null;
                try {
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<接口中，办件djId："
                            + mip.getMessageitemguid() + "pdf开始处理>>>>>>>>>>>>");
                    int bizType = optPdfInfoManager.getBizTypeForPdf(mip
                            .getMessageitemguid());
                    String pdfStorePath = optPdfInfoManager
                            .assignPdfStorePathOnDisk(mip.getMessageitemguid(),
                                    Long.valueOf(mip.getNodeinstid()));
                    File pdfStoreFile = new File(pdfStorePath);

                    // 重新生成pdf
                    String formHtmlUrl = "";
                    String contextPath = WebUtil
                            .getContextPath(XFireServletController.getRequest());
                    //发文
                    if (bizType == 1) {
                        formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(
                                contextPath, mip.getUserid(), mip
                                        .getMessageitemguid(), nodeInst
                                        .getFlowInstId().toString());
                    }
                    //收文
                    if (bizType == 2) {
                        formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(
                                contextPath, mip.getUserid(),
                                mip.getMessageitemguid());
                    }

                    // 签报
                    if (bizType == 3) {
                        formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(
                                contextPath, mip.getUserid(),
                                mip.getMessageitemguid());
                    }
                    // 车辆
                    if (bizType == 4) {
                        formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(
                                contextPath, mip.getUserid(),
                                mip.getMessageitemguid());
                    }
                    //事权
                    if(StringUtils.isBlank(formHtmlUrl)){
                         String moduleType =optBaseInfoManager.getApplyItemType(mip.getMessageitemguid());
                         formHtmlUrl = optPdfInfoManager.getSQFormHtmlUrl(contextPath,mip.getUserid(), moduleType,mip.getMessageitemguid());
                    }
                    
                    optPdfInfo = optPdfInfoManager.createPDF2(
                            mip.getMessageitemguid(),
                            Long.valueOf(mip.getNodeinstid()), formHtmlUrl);
                    // 如果存在图层，需要将图层添加上去
                    if (StringUtils.isNotBlank(mip.getDocfileurl())) {
                        // 如果没有重新生成PDF，那么就用最新的pdf信息
                        if (optPdfInfo == null) {
                            optPdfInfo = optPdfInfoManager.findNewestPdfInfo(mip.getMessageitemguid());
                        }
                        // 下载图层到新的目录
                        optPdfInfoManager.downloadLayer(mip.getDocfileurl(),pdfStoreFile.getParent());
                    }
                    if(StringUtils.isNotBlank(mip.getQpfileurl())){//有签批附件
                        String FilesStorePath = optStuffInfoManager.assignFilesStorePathOnDisk(mip.getMessageitemguid(),Long.valueOf(mip.getNodeinstid()));
                        File FilesStore = new File(FilesStorePath);
                        List<String> pathUrlList= optStuffInfoManager.downloadFiles(mip.getQpfileurl(),FilesStore.getParent());//下载签批附件zip到附件目录下
                        if(pathUrlList!=null && pathUrlList.size()>0){
                            for(int i=0;i<pathUrlList.size();i++){
                                String fullPath=pathUrlList.get(i);
                                File file = new File(fullPath);
                                file.length();
                                file.getName();
                                String [] nametemp=fullPath.split(",");
                                String sname = file.getName().substring(0,file.getName().lastIndexOf("."));
                                OptStuffInfo optStuffInfo =optStuffInfoManager.getObjectById(sname);
                                //optStuffInfo.setStuffpath(fullPath);
                                String type = mip.getMessageitemguid().replaceAll("\\d+", "").toLowerCase();
                                optStuffInfo.setStuffpath("\\"+type+File.separator + mip.getMessageitemguid() +"\\" + file.getName());
                                optStuffInfo.setDjId(mip.getMessageitemguid());
                                //optStuffInfo.setStuffid(mip.getFileid());
                                //optStuffInfo.setStuffname("材料附件");
                                optStuffInfo.setNodeInstId(Long.parseLong(mip.getNodeinstid()));
                                optStuffInfo.setUploadtime(new Date());
                                optStuffInfo.setUploadusercode(mip.getUserid());
                                optStuffInfo.setAttachsize(String.valueOf(file.length()));
                                //optStuffInfo.setFilename("已签批附件.doc");
                                optStuffInfoManager.saveObject(optStuffInfo); 
                            }
                           
                        }
                        
                    }
                    optPdfInfo.setUserCode(mip.getUserid());
                    // 添加图层在PDF上
                    optPdfInfo = optPdfInfoManager.addLayerOnPDF(optPdfInfo);
                    
                    // 将添加过图层的pdf转为swf
                    optPdfInfoManager.convertPdfToSwf(optPdfInfoManager
                            .getPdf2SwfToolPath(XFireServletController
                                    .getRequest()), optPdfInfo.getFilePath(),
                            pdfStoreFile.getParent(), pdfStoreFile.getName()
                                    .replace("pdf", "swf"));
                    // pdf 加密
                    optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo,
                            pdfStoreFile.getParent(), pdfStoreFile.getName());
                    optPdfInfo.setBizType(String.valueOf(bizType));
                    optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
                   
                    optPdfInfo.setNodeInstId(Long.valueOf(mip.getNodeinstid()));
                    optPdfInfo.setNodeName(nodeInfo.getNodeName());
                    optPdfInfoManager.saveObject(optPdfInfo);
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<接口中，办件djId："
                            + mip.getMessageitemguid()
                            + "pdf成功处理结束>>>>>>>>>>>>");
                } catch (Exception e) {
                    log.error("PDF处理异常:" + e.getMessage());
                } finally {
                    if (optPdfInfo != null
                            && StringUtils.isNotBlank(optPdfInfo
                                    .getTempDirPath())) {
                        optPdfInfoManager.clearTempFile(optPdfInfo
                                .getTempDirPath());
                    }
                }
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendOfficialProcess",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，发送已经签批或者签署意见的公文到OA系统。 手写签批公文返回签批的PDF文件 签署意见的公文返回录入的意见信息、录入人员、时间等");
        return returnInfo;
    }
    /**
     * 对附件地址进行处理
     * @param path
     * @param filename
     * @return
     */
    private static String getFilePath(String path, String... filename) {
        String filePath = path;
        for (String name : filename) {
            filePath += File.separator + name;
        }
        return filePath;
    }
    /**
     * 锁定状态更改:通过该接口发送公文锁定、解除锁定的状态。确保同一时间只允许一个人签批公文
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param lockstatus
     *            锁定状态 0解锁 1锁定
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    @Override
    public String lockOfficial(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OptProcCollectionMIP mip = (OptProcCollectionMIP) JsonUtil
                    .getObject4GsonString(json, OptProcCollectionMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            OptBaseInfo baseinfo = optBaseInfoManager.getObjectById(mip
                    .getMessageitemguid());

            OptProcLock optLock = optProcLockManager.getOptProcLock(mip
                    .getMessageitemguid());// 最新锁定状态

            // 只有在办件可以锁定
            if ((!"F".equals(baseinfo.getBizstate()) || !"C".equals(baseinfo
                    .getBizstate()))) {
                if (null != optLock
                        && optLock.getIslocked().equals(mip.getLockstatus())) {
                    throw new Exception("请不要重复锁定或解锁！");
                } else {
                    if ("T".equals(mip.getLockstatus())) {
                        baseinfo.setBizstate("L");// 锁定
                    } else if ("F".equals(mip.getLockstatus())) {
                        baseinfo.setBizstate("W");// 解锁
                    }
                    optBaseInfoDao.save(baseinfo);
                    OptProcLock lock = new OptProcLock();
                    lock.setDjId(mip.getMessageitemguid());
                    lock.setUsercode(mip.getUserid());
                    lock.setIslocked(mip.getLockstatus());
                    lock.setLocksettime(new Date(System.currentTimeMillis()));
                    optProcLockManager.saveObject(lock);// 修改save

                }
            } else {
                throw new Exception("错误信息：请核对参数messageitemguid;");
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("lockOfficial",operatorId, json, info,returnInfo, printStackTrace, "锁定状态更改:通过该接口发送公文锁定、解除锁定的状态。确保同一时间只允许一个人签批公文");
        return returnInfo;
    }

    /**
     * 获取最新锁定状态信息
     * 
     * @param json
     * @return
     */
    @Override
    public String getOfficialLockState(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OptProcCollectionMIP mip = (OptProcCollectionMIP) JsonUtil
                    .getObject4GsonString(json, OptProcCollectionMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            OptProcLock optLock = optProcLockManager.getOptProcLock(mip
                    .getMessageitemguid());

            OptProcCollectionMIP mipTemp = new OptProcCollectionMIP();
            if (null != optLock) {
                mipTemp.setDocid(JsonUtil.replaceNullString(optLock.getDjId()));
                mipTemp.setCreateuser(JsonUtil.replaceNullString(optLock
                        .getUsercode()));
                mipTemp.setCreatedatetime(JsonUtil.replaceNullString(null == optLock
                        .getLocksettime() ? null : String.valueOf(optLock
                        .getLocksettime().getTime())));
                mipTemp.setLockstatus(JsonUtil.replaceNullString(optLock
                        .getIslocked()));

                dataJson.put("lockState",
                        JsonUtil.createSimpleFormJsonString(mipTemp));
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialLockState",operatorId, json, info,returnInfo, printStackTrace, "获取最新锁定状态信息");
        return returnInfo;
    }

    /**
     * 获取公文附件列表:通过该接口，获取当前公文附件列表信息
     * 
     * @param userid
     *            用户唯一ID
     * @param docid
     *            公文id
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    @Override
    public String getOfficialAttachments(String json) {
       
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<VuserTaskListOA> objList = null;
        List<VOptBaseList> optBaseList = null;

        List<OaInformationMIP> objMIPList = new ArrayList<OaInformationMIP>();
        try {

            OfficialMIP mip = (OfficialMIP) JsonUtil.getObject4GsonString(json,
                    OfficialMIP.class);
            // messageitemguid
            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            Map<String, Object> filterMap = OfficialListGetFilterMap(mip);

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            List<OptStuffInfo> optstufffList = optStuffInfoManager
                    .getStuffInfoList(mip.getMessageitemguid());

            if (null != optstufffList && optstufffList.size() > 0) {
                for (OptStuffInfo stuffInfo : optstufffList) {
                    // 获取附件信息
                    OaInformationMIP informip = new OaInformationMIP();
                    informip.setAttachid(JsonUtil.replaceNullString(stuffInfo
                            .getStuffid()));
                    // 获取上传文件---- http://192.168.132.10:8088/oa
                    informip.setAttachtitle(JsonUtil
                            .replaceNullString(stuffInfo.getFilename()));
                  /*  informip.setAttachsize(JsonUtil.replaceNullString(String
                            .valueOf(null == stuffInfo.getStuffcontent() ? null
                                    : stuffInfo.getStuffcontent().length)));*/
                    informip.setAttachsize(String.valueOf(stuffInfo.getAttachsize()));
                    informip.setAttachurl(getFileDownloadUrl("DOCSTUFF",
                            stuffInfo.getStuffid()));
                    informip.setType(stuffInfo.getArchivetype());
                    objMIPList.add(informip);
                }

            }
            dataJson.put("doclist",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getOfficialAttachments",operatorId, json, info,returnInfo, printStackTrace, "获取公文附件列表:通过该接口，获取当前公文附件列表信息");
        return returnInfo;
    }

    /**
     * 通过该接口，收藏或者取消收藏具体某一公文，方便下次快速查找、审阅
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param collectstatus
     *            收藏状态 F取消收藏 T收藏
     * @param type
     *            公文类型，例如：SW收文 FW发文
     * 
     * @return
     */

    @Override
    public String collectOfficial(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OptProcCollectionMIP mip = (OptProcCollectionMIP) JsonUtil
                    .getObject4GsonString(json, OptProcCollectionMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 公文合法性验证
            checkOfficial(mip.getMessageitemguid());

            if ("T".equals(mip.getCollectstatus())) {// 添加收藏
                OptProcCollection collection = new OptProcCollection(
                        new OptProcCollectionId(mip.getMessageitemguid(),
                                mip.getUserid()));
                collection.setAtttype(mip.getMessageitemguid().substring(0,
                        mip.getMessageitemguid().indexOf("0")));// 截取djId
                collection.setIsatt("T");
                collection.setAttsettime(new Date(System.currentTimeMillis()));
                optProcCollectionManager.saveObject(collection);// 修改save
            } else if ("F".equals(mip.getCollectstatus())) {// 取消收藏
                OptProcCollection collection = optProcCollectionManager
                        .getObjectById(new OptProcCollectionId(mip
                                .getMessageitemguid(), mip.getUserid()));
                collection.setIsatt("F");
                collection
                        .setRemovesettime(new Date(System.currentTimeMillis()));
                optProcCollectionManager.saveObject(collection);
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("collectOfficial",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，收藏或者取消收藏具体某一公文，方便下次快速查找、审阅");
        return returnInfo;
    }

    /**
     * 公文收藏列表:通过该接口，获取已经收藏公文列表
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：FW收文 SW发文
     * 
     * @return
     */

    @Override
    public String getCollectOfficialList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<VOptProcCollection> objList = null;
        List<OptProcCollectionMIP> objMIPList = new ArrayList<OptProcCollectionMIP>();
        try {

            OptProcCollectionMIP mip = (OptProcCollectionMIP) JsonUtil
                    .getObject4GsonString(json, OptProcCollectionMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            filterMap.put("userCode", mip.getUserid());

            if (StringUtils.isNotBlank(mip.getType()))
                filterMap.put("atttype", mip.getType());
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字

            filterMap.put("ORDER_BY", "attsettime desc");// 收藏时间

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            objList = vOptProcCollectionManager
                    .listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (VOptProcCollection vOptProcCollection : objList) {
                    OptProcCollectionMIP mipTemp = new OptProcCollectionMIP();
                    mipTemp.setDocid(JsonUtil
                            .replaceNullString(vOptProcCollection.getDjId()));
                    mipTemp.setTitle(JsonUtil
                            .replaceNullString(vOptProcCollection
                                    .getTransaffairname()));
                    mipTemp.setCreatedatetime(JsonUtil
                            .replaceNullString(null == vOptProcCollection
                                    .getAttsettime() ? null : String
                                    .valueOf(vOptProcCollection.getAttsettime()
                                            .getTime())));
                    mipTemp.setCreateusername(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", vOptProcCollection.getCid()
                                            .getUsercode())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getCollectOfficialList",operatorId, json, info,returnInfo, printStackTrace, "公文收藏列表:通过该接口，获取已经收藏公文列表");
        return returnInfo;
    }

    /**
     * 根据当前登录用户权限，获取已经归档公文列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * @return
     */

    @Override
    public String getDocList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaArchive> oaArchiveList = null;
        List<VoaUnitArchiveIncomedoc> IncomedocList = null;

        List<OaUnitIncomedocMIP> objMIPList = new ArrayList<OaUnitIncomedocMIP>();
        try {

            OaUnitIncomedocMIP mip = (OaUnitIncomedocMIP) JsonUtil
                    .getObject4GsonString(json, OaUnitIncomedocMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());
            fUserDetail = (FUserDetail) sysUserManager
                    .loadUserByUsername(mip.getUserid());
            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
            // 关键字查询
            if (StringUtils.isNotBlank(mip.getKeyword())) {
                /*
                 * filterMap.put("title", mip.getKeyword());// 搜索关键字-标题
                 * filterMap.put("parallelTitle", mip.getKeyword());//
                 * 搜索关键字-并列题名 filterMap.put("deputyTitle", mip.getKeyword());//
                 * 搜索关键字-副题名
                 */filterMap.put("keywords", mip.getKeyword());// 搜索关键字-关键字
                /*
                 * filterMap.put("remark", mip.getKeyword());// 搜索关键字-备注
                 * filterMap.put("docno", mip.getKeyword());// 搜索关键字-文号
                 */}

            if (StringUtils.isNotBlank(mip.getType()))
                filterMap.put("doctype", mip.getType());// 搜索类型
            
           
            
          //根据人员确定查询范围
            if(StringUtils.isNotBlank(getGWNature(fUserDetail))){
                //获取上级机构配置信息
                FUnitinfo  unitParent =sysUnitManager.getObjectById(fUserDetail.getPrimaryUnit());
                if(null!=fUserDetail){
                    String unitcode=fUserDetail.getPrimaryUnit();
                    if(!CommonOptCodeUtil.TOP_UNITCODE.equals(unitParent.getParentunit())){//顶级部门
                         unitcode=CommonOptCodeUtil.GW_NATURE_SUP.equals(getGWNature(fUserDetail))?unitParent.getParentunit():unitcode;
                    }
                   //所属部门编号
                   filterMap.put("belongUnitcode", unitcode);
                    
                }
            }
            
            filterMap.put("ORDER_BY", "createtime desc");// 只有创建时间可以精确到秒
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            oaArchiveList = oaArchiveManager.listObjects(filterMap, pageDesc);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != oaArchiveList && oaArchiveList.size() > 0) {
                for (OaArchive oaArchive : oaArchiveList) {
                    OaUnitIncomedocMIP mipTemp = new OaUnitIncomedocMIP();
                    mipTemp.setDocid(JsonUtil.replaceNullString(oaArchive
                            .getId()));
                    mipTemp.setTitle(JsonUtil.replaceNullString(oaArchive
                            .getTitle()));
                    mipTemp.setCreatedatetime(JsonUtil
                            .replaceNullString(null == oaArchive
                                    .getCreatetime() ? null : String
                                    .valueOf(oaArchive.getCreatetime()
                                            .getTime())));
                    mipTemp.setCreateusername(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", oaArchive.getCreateuser())));
                    
                    mipTemp.setHandlesource(JsonUtil.replaceNullString(oaArchive
                            .getGwNature()));
                    
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getDocList",operatorId, json, info,returnInfo, printStackTrace, "根据当前登录用户权限，获取已经归档公文列表，支持条件查询，支持分页显示");
        return returnInfo;
    }

    /**
     * 根据当前登录用户权限，获取待归档公文列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * @return
     */

    public String getTodoDocList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<VoaUnitArchiveDispatchdoc> DispatchdocList = null;
        List<VoaUnitArchiveIncomedoc> IncomedocList = null;

        List<OaUnitIncomedocMIP> objMIPList = new ArrayList<OaUnitIncomedocMIP>();
        try {

            OaUnitIncomedocMIP mip = (OaUnitIncomedocMIP) JsonUtil
                    .getObject4GsonString(json, OaUnitIncomedocMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
            if (StringUtils.isNotBlank(mip.getUserid()))
                filterMap.put("usercode", mip.getUserid()); // 时间戳
            filterMap.put("NP_id", true);
            filterMap.put("ORDER_BY", "create_date desc");// 收藏时间
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            /** 1表示发文信息，0：表示收文信息 */
            if ("1".equals(mip.getType())) {
                if (StringUtils.isNotBlank(mip.getKeyword()))
                    filterMap.put("transaffairname", mip.getKeyword());// 搜索关键字
                DispatchdocList = voaUnitArchiveDispatchdocManager.listObjects(
                        filterMap, pageDesc);
                // 按接口要求封装数据--使用空字符串代替空值
                if (null != DispatchdocList && DispatchdocList.size() > 0) {
                    for (VoaUnitArchiveDispatchdoc incomedoc : DispatchdocList) {
                        OaUnitIncomedocMIP mipTemp = new OaUnitIncomedocMIP();
                        mipTemp.setDocid(JsonUtil.replaceNullString(incomedoc
                                .getNo()));
                        mipTemp.setTitle(JsonUtil.replaceNullString(incomedoc
                                .getTransaffairname()));
                        mipTemp.setCreatedatetime(JsonUtil
                                .replaceNullString(null == incomedoc
                                        .getCreateDate() ? null : String
                                        .valueOf(incomedoc.getCreateDate()
                                                .getTime())));
                        mipTemp.setCreateusername(JsonUtil
                                .replaceNullString(incomedoc.getUsercode()));
                        objMIPList.add(mipTemp);
                    }
                }
            } else if ("0".equals(mip.getType())) {
                if (StringUtils.isNotBlank(mip.getKeyword()))
                    filterMap.put("incomedDocTitle", mip.getKeyword());// 搜索关键字
                IncomedocList = voaUnitArchiveIncomedocManager.listObjects(
                        filterMap, pageDesc);
                // 按接口要求封装数据--使用空字符串代替空值
                if (null != IncomedocList && IncomedocList.size() > 0) {
                    for (VoaUnitArchiveIncomedoc incomedoc : IncomedocList) {
                        OaUnitIncomedocMIP mipTemp = new OaUnitIncomedocMIP();
                        mipTemp.setDocid(JsonUtil.replaceNullString(incomedoc
                                .getNo()));
                        mipTemp.setTitle(JsonUtil.replaceNullString(incomedoc
                                .getIncomedDocTitle()));
                        mipTemp.setCreatedatetime(JsonUtil
                                .replaceNullString(null == incomedoc
                                        .getCreatetime() ? null : String
                                        .valueOf(incomedoc.getCreatetime()
                                                .getTime())));
                        mipTemp.setCreateusername(JsonUtil
                                .replaceNullString(incomedoc.getUsercode()));
                        objMIPList.add(mipTemp);
                    }
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getTodoDocList",operatorId, json, info,returnInfo, printStackTrace, "根据当前登录用户权限，获取待归档公文列表，支持条件查询，支持分页显示");
        return returnInfo;
    }

    /**
     * 归档公文明细:通过该接口，获取某一已归档公文明细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    @Override
    public String getDocDetail(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        OaArchive obj = null;
        try {

            OaUnitIncomedocMIP mip = (OaUnitIncomedocMIP) JsonUtil
                    .getObject4GsonString(json, OaUnitIncomedocMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            obj = oaArchiveManager.getObjectById(mip.getMessageitemguid());

            if (null != obj && StringUtils.isNotBlank(obj.getNo())) {
                OptProcCollection collection = optProcCollectionManager
                        .getObjectById(new OptProcCollectionId(obj.getNo(), mip
                                .getUserid()));
                // 按接口要求封装数据--使用空字符串代替空值
                OaUnitIncomedocMIP mipTemp = new OaUnitIncomedocMIP();
                mipTemp.setCreateusername(JsonUtil.replaceNullString(obj
                        .getCreateuser()));
                mipTemp.setCreatedatetime(JsonUtil.replaceNullString(null == obj
                        .getCreatetime() ? null : String.valueOf(obj
                        .getCreatetime().getTime())));
                mipTemp.setModifydatetime(JsonUtil.replaceNullString(null == obj
                        .getLastmodifytime() ? null : String.valueOf(obj
                        .getLastmodifytime().getTime())));
                mipTemp.setCollectstatus(JsonUtil
                        .replaceNullString(null == collection ? "F"
                                : collection.getIsatt()));
                // pdf信息
                if ("true".equals(SysParametersUtils.getParameters(
                        "generatePdfForDocEnable", "false"))) {
                    OptPdfInfo optPdfInfo = optPdfInfoManager
                            .findNewestPdfInfo(obj.getNo());
                    if (optPdfInfo != null) {
                        mipTemp.setDocurl(JsonUtil
                                .replaceNullString(null == optPdfInfo ? ""
                                        : getFileDownloadUrl("PDF", optPdfInfo
                                                .getId().toString())));// 公文正文下载地址

                        mipTemp.setFilePw(JsonUtil
                                .replaceNullString(null == optPdfInfo ? ""
                                        : Cryptos.aesEncrypt(
                                                optPdfInfo.getSecretKey(),
                                                PDFUtil.AES_USER_SECRET_KEY)));
                    } else {
                        mipTemp.setDocurl("");
                        mipTemp.setFilePw("");
                    }
                } else {
                    mipTemp.setDocurl("");// 公文正文下载地址
                    mipTemp.setFilePw("");
                }
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                dataJson.put("info",
                        JsonUtil.createSimpleFormJsonString(mipTemp));
                info.setBizdata(dataJson);
            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "编号"
                        + mip.getMessageitemguid() + "没有对应公文归档信息！"));
            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getDocDetail",operatorId, json,info, returnInfo, printStackTrace, "归档公文明细:通过该接口，获取某一已归档公文明细信息");
        return returnInfo;
    }

    /**
     * 通讯录列表:通过该接口，获取内部通讯录列表，支持条件查询
     * 
     * @param userid
     *            用户唯一ID
     * @param deptid
     *            部门ID
     * @return
     */

    @Override
    public String getContactList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        FUserDetail fUserDetail = new FUserDetail();
        List<FUnitinfo> objList = null;
        List<OaUserinfoMIP> objMIPList = new ArrayList<OaUserinfoMIP>();
        try {
            /** 获取接口参数beg **/
            OaUserinfoMIP mip = (OaUserinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaUserinfoMIP.class);
            /** 获取参数end **/

            /** 附件处理beg **/
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());
            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getDeptid()))
                filterMap.put("parentunit", mip.getDeptid());

            objList = sysunitdao.listObjects(filterMap);

            if (null != objList && objList.size() > 0) {
                for (FUnitinfo unit : objList) {
                    OaUserinfoMIP mip1 = new OaUserinfoMIP();
                    mip1.setDeptid(unit.getUnitcode());
                    mip1.setDeptname(unit.getUnitname());
                    objMIPList.add(mip1);
                }
                dataJson.put("depts",
                        JsonUtil.createSimpleListJsonString(objMIPList));
            } else {
                List<FUserinfo> unitusers = oaUserinfoManager
                        .getUserUnderUserUnit(filterMap, mip.getDeptid());
                List<OaUserinfo> infos = new ArrayList<OaUserinfo>();
                if (unitusers != null && unitusers.size() >= 1) {
                    for (FUserinfo o : unitusers) {
                        // 查询oa_userinfo中的人员
                        OaUserinfo oainfos = oaUserinfoManager.getObjectById(o
                                .getUsercode());
                        if (oainfos != null) {
                            infos.add(oainfos);
                        }
                    }
                    for (OaUserinfo s : infos) {
                        FUserunit funit = sysUserManager
                                .getUserPrimaryUnit(fUserDetail.getUsercode());
                        FUserinfo user1 = sysUserManager.getObjectById(s
                                .getUsercode());
                        OaUserinfoMIP mip1 = new OaUserinfoMIP();
                        mip1.setUserid(user1.getUsercode());
                        mip1.setUsername(JsonUtil.replaceNullString(user1
                                .getUsername()));
                        mip1.setPosition(CodeRepositoryUtil.getValue(
                                "StationType", JsonUtil.replaceNullString(funit
                                        .getUserstation())));
                        mip1.setWays(JsonUtil.replaceNullString(s
                                .getTelephone()));
                        mip1.setValue(JsonUtil.replaceNullString(s
                                .getMobilephone()));
                        objMIPList.add(mip1);
                    }
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("users",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getContactList",operatorId, json, info,returnInfo, printStackTrace, "通讯录列表:通过该接口，获取内部通讯录列表，支持条件查询");
        return returnInfo;
    }

    /**
     * 获取通讯录人员明细:通过该接口，获取具体某一内部人员已经记录的信息，包括姓名、性别、年龄、职位、所属部门、联系方式等
     * 
     * @param userid
     *            用户唯一ID
     * @param personid
     *            人员ID
     * @return
     */

    @Override
    public String getUserinfo(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaUserinfoMIP> objMIPList = new ArrayList<OaUserinfoMIP>();

        FUserDetail fUserDetail = new FUserDetail();
        try {
            /** 获取接口参数beg **/
            OaUserinfoMIP mip = (OaUserinfoMIP) JsonUtil.getObject4GsonString(
                    json, OaUserinfoMIP.class);
            /** 获取参数end **/

            /** 附件处理beg **/
            fUserDetail = (FUserDetail) sysUserManager.loadUserByUsername(mip
                    .getUserid());
            OaUserinfo oainfo = oaUserinfoManager.getObjectById(fUserDetail
                    .getUsercode());
            FUserunit funit = sysUserManager.getUserPrimaryUnit(oainfo
                    .getUsercode());
            FUnitinfo unitinfo = sysunitdao.getObjectById(funit.getUnitcode());
            mip.setUsername(JsonUtil.replaceNullString(oainfo.getUsername()));
            mip.setPosition(CodeRepositoryUtil.getValue("StationType",
                    JsonUtil.replaceNullString(funit.getUserstation())));
            mip.setDeptid(JsonUtil.replaceNullString(fUserDetail
                    .getPrimaryUnit()));
            mip.setDeptname(JsonUtil.replaceNullString(unitinfo.getUnitname()));
            mip.setSex(JsonUtil.replaceNullString(oainfo.getSex()));
            mip.setWays(JsonUtil.replaceNullString(oainfo.getTelephone()));
            mip.setValue(JsonUtil.replaceNullString(oainfo.getMobilephone()));
            objMIPList.add(mip);

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("users",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getUserinfo",operatorId, json, info,returnInfo, printStackTrace, "获取通讯录人员明细:通过该接口，获取具体某一内部人员已经记录的信息，包括姓名、性别、年龄、职位、所属部门、联系方式等");
        return returnInfo;
    }

    /**
     * 未读邮件列表:获取登录用户的未读邮件列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            流程类型，例如：0全部1收件箱2发件箱
     * @param isread
     *            0：已读 1：未读 2：全部
     * 
     * @return
     */

    @Override
    public String getMsgList(String json) {
        // CentitWebServiceInfo info = new CentitWebServiceInfo();
        //
        // JSONObject dataJson = new JSONObject();// 接口查询数据结果
        
        List<Innermsg> objList = null;
        List<OaInnermsgMIP> objMIPList = new ArrayList<OaInnermsgMIP>();
        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
                    Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间 //
                                                                    // 时间戳
            filterMap.put("ORDER_BY", "senddate desc");
            filterMap.put("msgtype", "P");

            // http://192.168.132.10:8088/oa/oa/innermsg!list.do?s_msgtype=P&s_mailtype=O
            // 发件箱
            // http://192.168.132.10:8088/oa/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I&s_msgstate=U
            if (StringUtils.isNotBlank(mip.getType())) {// 0全部1收件箱2发件箱
                if ("1".equals(mip.getType())) {// 收件箱
                    if (StringUtils.isNotBlank(mip.getKeyword()))
                        filterMap.put("msgtitle", mip.getKeyword());// 搜索关键字
                    filterMap.put("mailtype", "I");

                    if (StringUtils.isNotBlank(mip.getIsread())) {
                        if ("0".equals(mip.getIsread())) {// 已读
                            filterMap.put("msgstate", "R");
                        } else if ("1".equals(mip.getIsread())) {// 未读
                            filterMap.put("msgstate", "U");
                        }// 2 全部
                    }
                } else if ("2".equals(mip.getType())) {// 发件箱
                    if (StringUtils.isNotBlank(mip.getKeyword()))
                        filterMap.put("msgtitle", mip.getKeyword());// 搜索关键字
                    if (StringUtils.isNotBlank(mip.getUserid()))
                        filterMap.put("sender", mip.getUserid());
                    filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
                            Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));
                    filterMap.put("mailtype", "O");

                }
            }

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            if (StringUtils.isNotBlank(mip.getType())) {// 0全部1收件箱2发件箱
                if ("1".equals(mip.getType())) {// 收件箱
                    objList = innermsgManager.listObjects(filterMap, pageDesc,
                            mip.getUserid());
                    // 添加收件阅读状态标记
                    if (objList != null && objList.size() > 0) {
                        for (int i = 0; i < objList.size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("msgcode", objList.get(i).getMsgcode());
                            map.put("receive", mip.getUserid());
                            map.put("msgstate", "U");
                            List<InnermsgRecipient> ureadList = innermsgRecipientManager
                                    .listObjects(map);// 收件阅读状态
                            if (ureadList != null && ureadList.size() > 0) {
                                objList.get(i).setMsgstate("U");
                            }
                        }
                    }
                } else if ("2".equals(mip.getType())) {// 发件箱
                    objList = innermsgManager.listObjects(filterMap, pageDesc);
                }
            }

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (Innermsg innermsg : objList) {
                    OaInnermsgMIP mipTemp = new OaInnermsgMIP();
                    mipTemp.setMsgid(JsonUtil.replaceNullString(innermsg
                            .getMsgcode()));// 邮件id
                    mipTemp.setMsgtitle(JsonUtil.replaceNullString(innermsg
                            .getMsgtitle()));// 邮件标题
                    mipTemp.setCreateuserid(JsonUtil.replaceNullString(innermsg
                            .getSender()));// 邮件发起人id
                    mipTemp.setCreateusername(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", innermsg.getSender())));// 邮件发起人姓名
                    mipTemp.setCreatetime(JsonUtil.replaceNullString(null == innermsg
                            .getSenddate() ? null : String.valueOf(innermsg
                            .getSenddate().getTime())));// 发起时间
                    mipTemp.setReadflag(JsonUtil.replaceNullString(innermsg
                            .getMsgstate()));// 是否已读 0已读1未读
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("innermsglist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMsgList",operatorId, json, info,returnInfo, printStackTrace, "未读邮件列表:获取登录用户的未读邮件列表，支持条件查询，支持分页显示");
        return returnInfo;
    }

    /**
     * 通过该接口更新邮件为已读邮件。
     */
    @Override
    public String updateMsgReadStatus(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);
            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            String msgcodes = mip.getMsgid();

            List<String> pk = Arrays.asList(msgcodes.split(","));

            if (null != pk) {
                for (String s : pk) {
                    Innermsg innermsg = innermsgManager.getObjectById(s);
                    innermsgRecipientManager.updateMsgStat(innermsg,
                            mip.getUserid());// 更新收件阅读状态
                }
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("updateMsgReadStatus",operatorId, json, info,returnInfo, printStackTrace, "通过该接口更新邮件为已读邮件");
        return returnInfo;
    }

    /**
     * 通过该接口获取某一邮件详细信息，包括标题、发送人、接受人、抄送、正文、附件等
     */
    @Override
    public String getMsgDetail(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        Innermsg innermsg = new Innermsg();

        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            innermsg = innermsgManager.getObjectById(mip.getMsgid());
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("msgcode", mip.getMsgid());
            List<InnermsgRecipient> innermsgRecipients = innermsgRecipientManager
                    .listObjects(filterMap);// 收件人s
            Set<Msgannex> msgannexs = innermsg.getMsgannexs();// 附件s
            // 按接口要求封装数据
            if (null != innermsg) {
                mip = new OaInnermsgMIP();
                List<ReceiverMIP> receiverMIPList = new ArrayList<ReceiverMIP>();
                List<AttachmentMIP> attachmentMIPList = new ArrayList<AttachmentMIP>();
                mip.setMsgid(JsonUtil.replaceNullString(innermsg.getMsgcode()));// 邮件id
                mip.setMsgtitle(JsonUtil.replaceNullString(innermsg
                        .getMsgtitle()));// 邮件标题
                mip.setMsgcontent(JsonUtil.replaceNullString(innermsg
                        .getMsgcontent()));// 邮件内容(HTML)
                mip.setCreateuserid(JsonUtil.replaceNullString(innermsg
                        .getSender()));// 邮件发起人id
                mip.setCreateusername(JsonUtil
                        .replaceNullString(CodeRepositoryUtil.getValue(
                                "usercode", innermsg.getSender())));// 邮件发起人姓名
                mip.setCreatetime(JsonUtil.replaceNullString(null == innermsg
                        .getSenddate() ? null : String.valueOf(innermsg
                        .getSenddate().getTime())));// 发起时间
                mip.setReplycount(String.valueOf(innermsgManager
                        .getReplayCount(innermsg)));
                mip.setUnreadcount(String.valueOf(innermsgManager
                        .getUnReadRepalyCount(innermsg)));
                mip.setReadflag(JsonUtil.replaceNullString(innermsg
                        .getMsgstate()));// 是否已读
                // 0已读
                // 1未读

                if (null != innermsgRecipients && !innermsgRecipients.isEmpty()) {
                    for (InnermsgRecipient iR : innermsgRecipients) {
                        receiverMIPList.add(new ReceiverMIP(iR.getReceive(),
                                CodeRepositoryUtil.getValue("usercode",
                                        iR.getReceive()), iR.getMsgstate()));
                    }
                }
                mip.setReceivers(receiverMIPList);

                if (null != msgannexs && !msgannexs.isEmpty()) {
                    for (Msgannex m : msgannexs) {
                        attachmentMIPList.add(new AttachmentMIP(m.getFileinfo()
                                .getFilecode(), m.getFileinfo().getFilename()
                                + "." + m.getFileinfo().getFileext(),
                                getFileDownloadUrl("MAILSTUFF", m.getFileinfo()
                                        .getFilecode())));
                    }
                }
                mip.setAttachment(attachmentMIPList);

                dataJson.put("innermsg",
                        JsonUtil.createSimpleFormJsonString(mip));

            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMsgDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取某一邮件详细信息，包括标题、发送人、接受人、抄送、正文、附件等");
        return returnInfo;
    }

    /**
     * 通过该接口实现邮件发送、回复、转发、上传附件等功能
     * 
     * @param userid
     *            用户唯一ID
     * @param content
     *            邮件内容
     * @param replaymsgid
     *            回复邮件id，回复操作时此项必填
     * @param title
     *            标题，回复操作时可为空
     * @param receiverids
     *            接受人ID,多个逗号分隔
     * @param attachlist
     *            附件列表
     * @param attachtitle
     *            附件标题
     * @param attachtype
     *            附件类型，附件后缀
     * @param attachurl
     *            附件下载地址
     * 
     * @return
     */

    @Override
    public String sendMsg(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        Innermsg innermsg = new Innermsg();

        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            /** 附件处理beg **/
            loadUserByUsername(mip.getUserid());
            List<AttachlistMIP> attachList = mip.getAttachlist();

            List<String> msgannexs = new ArrayList<String>();
            if (null != attachList && attachList.size() > 0) {
                for (Iterator<AttachlistMIP> iterator = attachList.iterator(); iterator
                        .hasNext();) {
                    AttachlistMIP attachlistMIP = iterator.next();

                    if (StringUtils.isNotBlank(attachlistMIP.getAttachurl())) {
                        String PHONESERURL = attachlistMIP.getAttachurl();
                        URL resourceUrl = new URL(PHONESERURL);

                        /*
                         * InputStream content = (InputStream) resourceUrl
                         * .getContent();
                         * 
                         * URL resourceUrl = new URL(PHONESERURL);
                         */
                        HttpURLConnection httpUrl = (HttpURLConnection) resourceUrl
                                .openConnection();
                        InputStream content = httpUrl.getInputStream();

                        FileinfoFs fileinfoFs = processUploadedFile(content,
                                attachlistMIP.getAttachtitle(),
                                attachlistMIP.getAttachtype(),
                                CommonCodeUtil.PUBLICINFO_OPTCODE);
                        msgannexs.add(fileinfoFs.getFilecode());
                    }

                }

                if (null != msgannexs && msgannexs.size() > 0) {
                    for (String s : msgannexs) {
                        innermsg.getMsgannexs().add(
                                new Msgannex(null, this.fileinfoFsManager
                                        .getObjectById(s)));
                    }
                }
            }

            /** 附件处理end **/

            innermsg.setSender(mip.getUserid());// 用户唯一ID
            innermsg.setMsgcontent(mip.getContent());// 内容
            innermsg.setTopMsgcode(mip.getReplaymsgid());// 回复邮件id
            innermsg.setTopType("2");// 回复
            innermsg.setMsgtitle(mip.getTitle());// 标题
            innermsg.setTo(mip.getReceiverids());// 接收人，“,”分割
            innermsg.setSenddate(new Date());// 当前日期

            // 接收人 中文名
            List<String> pk = Arrays.asList(mip.getReceiverids().split(","));
            List<String> userNames = new ArrayList<String>();
            if (null != pk) {
                for (String s : pk) {
                    userNames.add(CodeRepositoryUtil.getValue("usecode", s));
                }
            }
            innermsg.setReceivename(StringUtils.join(userNames.toArray(), ","));// 接收人
                                                                                // 中文名

            // 手机端insert默认字段
            innermsg.setMailtype(Innermsg.MAIL_TYPE_O);// 发件箱
            innermsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                    Innermsg.MSG_STAT_R));//
            innermsg.setMsgtype(Innermsg.MSG_TYPE_P);// 个人邮件

            innermsgManager.saveMsg(innermsg);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendMsg",operatorId, json,info, returnInfo, printStackTrace, "通过该接口实现邮件发送、回复、转发、上传附件等功能");
        return returnInfo;

    }

    /**
     * 通过该接口可删除邮件，支持单个、批量删除
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id,多个id“,”分割
     * @return
     */

    @Override
    public String deleteMsg(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            String mailType = mip.getType();
            String msgcodes = mip.getMsgids();
            List<String> pk = Arrays.asList(msgcodes.split(","));
            if (null != pk) {
                if ("1".equals(mailType)) {// 收件箱
                    innermsgRecipientManager.dropRec(pk, mip.getUserid());// 收件箱删除进入废件箱
                } else if ("2".equals(mailType)) {// 發件箱
                    innermsgManager.dropMsgs(pk, mip.getUserid());// 收件箱删除进入废件箱
                } else {
                    innermsgRecipientManager.dropRec(pk, mip.getUserid());// 收件箱删除进入废件箱---删除收件箱邮件
                }
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("deleteMsg",operatorId, json,info, returnInfo, printStackTrace, "通过该接口可删除邮件，支持单个、批量删除");
        return returnInfo;
    }

    /**
     * 已删邮件列表:通过该接口，获取已删邮件列表，支持条件查询，支持已删除邮件恢复功能
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            流程类型，例如：0全部1收件箱2发件箱
     * 
     * @return
     */

    @Override
    public String getDeleteMsgList(String json) {
       
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<Innermsg> objList = null;
        List<OaInnermsgMIP> objMIPList = new ArrayList<OaInnermsgMIP>();
        try {

            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
                    Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间 //
                                                                    // 时间戳
            filterMap.put("ORDER_BY", "senddate desc");

            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字

            // http://192.168.132.10:8088/oa/oa/innermsg!list.do?s_msgtype=P&s_mailtype=O
            // 发件箱
            // http://192.168.132.10:8088/oa/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I&s_msgstate=U
            if (StringUtils.isNotBlank(mip.getType())) {// 0全部1收件箱2发件箱
                if ("1".equals(mip.getType())) {// 收件箱
                    filterMap.put("mailtype", "T");
                } else if ("2".equals(mip.getType())) {// 发件箱
                    filterMap.put("mailtype", "T");
                    if (StringUtils.isNotBlank(mip.getUserid()))
                        filterMap.put("sender", mip.getUserid());
                }
            }

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            if (StringUtils.isNotBlank(mip.getType())) {// 0全部1收件箱2发件箱
                if ("1".equals(mip.getType())) {// 收件箱
                    objList = innermsgManager.listObjects(filterMap, pageDesc,
                            mip.getUserid());
                    // 添加收件阅读状态标记
                    if (objList != null && objList.size() > 0) {
                        for (int i = 0; i < objList.size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("msgcode", objList.get(i).getMsgcode());
                            map.put("receive", mip.getUserid());
                            map.put("msgstate", "U");
                            List<InnermsgRecipient> ureadList = innermsgRecipientManager
                                    .listObjects(map);// 收件阅读状态
                            if (ureadList != null && ureadList.size() > 0) {
                                objList.get(i).setMsgstate("U");
                            }
                        }
                    }
                } else if ("2".equals(mip.getType())) {// 发件箱
                    objList = innermsgManager.listObjects(filterMap, pageDesc);
                }
            }

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (Innermsg innermsg : objList) {
                    OaInnermsgMIP mipTemp = new OaInnermsgMIP();
                    mipTemp.setMsgid(JsonUtil.replaceNullString(innermsg
                            .getMsgcode()));// 邮件id
                    mipTemp.setMsgtitle(JsonUtil.replaceNullString(innermsg
                            .getMsgtitle()));// 邮件标题
                    mipTemp.setCreateuserid(JsonUtil.replaceNullString(innermsg
                            .getSender()));// 邮件发起人id
                    mipTemp.setCreateusername(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "usercode", innermsg.getSender())));// 邮件发起人姓名
                    mipTemp.setCreatetime(JsonUtil.replaceNullString(null == innermsg
                            .getSenddate() ? null : String.valueOf(innermsg
                            .getSenddate().getTime())));// 发起时间
                    mipTemp.setReadflag(JsonUtil.replaceNullString(innermsg
                            .getMsgstate()));// 是否已读
                    // 0已读
                    // 1未读
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("innermsglist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getDeleteMsgList",operatorId, json, info,returnInfo, printStackTrace, "已删邮件列表:通过该接口，获取已删邮件列表，支持条件查询，支持已删除邮件恢复功能");
        return returnInfo;
    }

    /**
     * 已删邮件恢复:通过该接口，恢复已删邮件状态，恢复的邮件自动进入收件箱或者发件箱
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id,多个id“,”分割
     * @return
     */

    @Override
    public String undeleteMsg(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            OaInnermsgMIP mip = (OaInnermsgMIP) JsonUtil.getObject4GsonString(
                    json, OaInnermsgMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            if (StringUtils.isBlank(mip.getMsgids())) {
                throw new Exception("请输入需要恢复的邮件标识");
            }

            String msgcodes = mip.getMsgids();
            String mailType = mip.getType();
            List<String> pk = Arrays.asList(msgcodes.split(","));

            if (null != pk) {
                if (null != pk) {
                    if ("1".equals(mailType)) {// 收件箱
                        innermsgRecipientManager.cancleDropRec(pk,
                                mip.getUserid());// 收件箱删除进入废件箱
                    } else if ("2".equals(mailType)) {// 發件箱
                        innermsgManager.cancleDropMsgs(pk, mip.getUserid());// 收件箱删除进入废件箱
                    }
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("undeleteMsg",operatorId, json, info,returnInfo, printStackTrace, "已删邮件恢复:通过该接口，恢复已删邮件状态，恢复的邮件自动进入收件箱或者发件箱");
        return returnInfo;
    }

    /**
     * 通过该接口，移动端将需要发送短信推送到pc端，实现短信发送
     */
    @Override
    public String sendSMS(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaSmssendMip mip = (OaSmssendMip) JsonUtil.getObject4GsonString(
                    json, OaSmssendMip.class);

            // 用户合法性验证
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            if (StringUtils.isNotBlank(mip.getReceiverids())) {
                String[] aa = mip.getReceiverids().split(",");
                for (String acceptpeocode : aa) {
                    oaSmssendManager.saveMsgs(acceptpeocode,
                            fUserDetail.getUsercode(), mip.getContent(), "R");
                }
                oaSmssendManager.executeSendMsg();
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendSMS",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，移动端将需要发送短信推送到pc端，实现短信发送");
        return returnInfo;
    }

    /**
     * 获取当前用户短信列表，支持条件查询，支持分页显示。
     */
    @Override
    public String getSMSList(String json) {
        
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaSmssendMip> objMIPList = new ArrayList<OaSmssendMip>();
        try {

            OaSmssendMip mip = (OaSmssendMip) JsonUtil.getObject4GsonString(
                    json, OaSmssendMip.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(mip.getUserid()))
                filterMap.put("sendpeocode", mip.getUserid());// 当前记录时间

            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间

            // 关键字查询
            if (StringUtils.isNotBlank(mip.getKeyword())) {
                filterMap.put("content", mip.getKeyword());// 搜索关键字-关键字

            }

            if (StringUtils.isNotBlank(mip.getState()))
                filterMap.put("restoremessage", mip.getState());// 搜索类型
            filterMap.put("ORDER_BY", "sendtime desc");// 只有创建时间可以精确到秒
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            List<OaSmssend> oaSmsList = oaSmssendManager.listObjects(filterMap,
                    pageDesc);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != oaSmsList && oaSmsList.size() > 0) {
                for (OaSmssend sms : oaSmsList) {
                    OaSmssendMip mipTemp = new OaSmssendMip();
                    mipTemp.setSmsid(JsonUtil.replaceNullString(String
                            .valueOf(sms.getSmsid())));
                    mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil
                            .getValue("sendMsgResState",
                                    sms.getRestoremessage())));
                    mipTemp.setContent(JsonUtil.replaceNullString(sms
                            .getContent()));

                    mipTemp.setSendtime(JsonUtil.replaceNullString(null == sms
                            .getSendtime() ? null : String.valueOf(sms
                            .getSendtime().getTime())));
                    mipTemp.setSendnum(JsonUtil.replaceNullString(sms
                            .getSendnum()));
                    mipTemp.setSendpeo(JsonUtil.replaceNullString(sms
                            .getSendpeo()));
                    mipTemp.setSendpeocode(JsonUtil.replaceNullString(sms
                            .getSendpeocode()));
                    mipTemp.setAcceptnum(JsonUtil.replaceNullString(sms
                            .getAcceptnum()));
                    mipTemp.setAcceptpeo(JsonUtil.replaceNullString(sms
                            .getAcceptpeo()));
                    mipTemp.setAcceptpeocode(JsonUtil.replaceNullString(sms
                            .getAcceptpeocode()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("smslist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getSMSList",operatorId, json, info,returnInfo, printStackTrace, "获取当前用户短信列表，支持条件查询，支持分页显示。");
        return returnInfo;
    }

    /**
     * 通过该接口，获取某一短信详细信息，支持短信转发
     */
    @Override
    public String getSMSDetail(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        try {

            OaSmssendMip mip = (OaSmssendMip) JsonUtil.getObject4GsonString(
                    json, OaSmssendMip.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());
            if (StringUtils.isBlank(mip.getSmsid())) {
                throw new Exception("请输入需要查询信息！");
            }
            OaSmssend sms = oaSmssendManager.getObjectById(Long.valueOf(mip
                    .getSmsid()));
            // 按接口要求封装数据
            if (null != sms) {
                OaSmssendMip mipTemp = new OaSmssendMip();
                mipTemp.setSmsid(JsonUtil.replaceNullString(String.valueOf(sms
                        .getSmsid())));
                mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("sendMsgResState", sms.getState())));
                mipTemp.setContent(JsonUtil.replaceNullString(sms.getContent()));

                mipTemp.setSendtime(JsonUtil.replaceNullString(null == sms
                        .getSendtime() ? null : String.valueOf(sms
                        .getSendtime().getTime())));
                mipTemp.setSendnum(JsonUtil.replaceNullString(sms.getSendnum()));
                mipTemp.setSendpeo(JsonUtil.replaceNullString(sms.getSendpeo()));
                mipTemp.setSendpeocode(JsonUtil.replaceNullString(sms
                        .getSendpeocode()));
                mipTemp.setAcceptnum(JsonUtil.replaceNullString(sms
                        .getAcceptnum()));
                mipTemp.setAcceptpeo(JsonUtil.replaceNullString(sms
                        .getAcceptpeo()));
                mipTemp.setAcceptpeocode(JsonUtil.replaceNullString(sms
                        .getAcceptpeocode()));

                dataJson.put("sms",
                        JsonUtil.createSimpleFormJsonString(mipTemp));

            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getSMSDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，获取某一短信详细信息，支持短信转发");
        return returnInfo;
    }

    /**
     * 移动端将短信最新状态推送到pc端，更新短信状态。
     */
    @Override
    public String updateSmsStatus(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaSmssendMip mip = (OaSmssendMip) JsonUtil.getObject4GsonString(
                    json, OaSmssendMip.class);

            // 用户合法性验证
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            if (StringUtils.isNotBlank(mip.getSmsid())) {
                String[] aa = mip.getReceiverids().split(",");
                for (String smsid : aa) {
                    OaSmssend sms = oaSmssendManager.getObjectById(smsid);
                    if (null != sms) {
                        // 发送短信内容到短信接口表--更改短信发送状态
                        sms.setState(mip.getState());
                        oaSmssendManager.saveObject(sms);
                    }

                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("updateSmsStatus",operatorId, json, info,returnInfo, printStackTrace, "移动端将短信最新状态推送到pc端，更新短信状态");
        return returnInfo;
    }

    /**
     * 通过该接口获取车辆申请列表
     * 
     * @param json
     * @return
     */
    @Override
    public String getCarApplyList(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaCarApply> objList = null;
        List<OaCarApplyMIP> objMIPList = new ArrayList<OaCarApplyMIP>();

        try {

            OaCarApplyMIP mip = (OaCarApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaCarApplyMIP.class);

            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("docpTimeBegin", DateUtil.convertToString(
                        mip.getStarttime(), "yyyy-MM-dd HH:mm:ss"));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("docpTimeEnd", DateUtil.convertToString(
                        mip.getEndtime(), "yyyy-MM-dd HH:mm:ss"));// 结束时间
            // STARTTIME,ENDTIME参数传空字符串默认获取当天会议情况
            if (StringUtils.isBlank(mip.getStarttime())
                    && StringUtils.isBlank(mip.getEndtime())) {
                filterMap.put(
                        "docpTimeBegin",
                        DatetimeOpt.convertDateToString(
                                DatetimeOpt.currentUtilDate(), "yyyy-MM-dd"));// 开始时间
                                                                              // 时间戳
                filterMap.put("docpTimeEnd", DatetimeOpt.convertDateToString(
                        DatetimeOpt.addDays(DatetimeOpt.currentUtilDate(), 1),
                        "yyyy-MM-dd"));// 结束时间
            }
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(mip.getType())) {
                if ("0".equals(mip.getType())) {// 仅自己的
                    filterMap.put("creater", mip.getUserid());
                } else if ("1".equals(mip.getType())) {// 自己+别人审核通过的
                    filterMap.put("mipSelf", mip.getUserid());
                }
            }

            filterMap.put("ORDER_BY", " cpBegTime  desc");
            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            /** 处理查询条件end **/

            objList = oaCarApplyManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaCarApply carApplyInfo : objList) {
                    // 获取附件信息
                    OaCarApplyMIP mipTemp = new OaCarApplyMIP();
                    mipTemp.setDjId(JsonUtil.replaceNullString(carApplyInfo
                            .getDjId()));
                    mipTemp.setApplicantid(JsonUtil
                            .replaceNullString(carApplyInfo.getCreater()));
                    mipTemp.setApplicant(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "userCode", carApplyInfo.getCreater())));
                    mipTemp.setTitle(JsonUtil.replaceNullString(carApplyInfo
                            .getTransaffairname()));
                    mipTemp.setStarttime(JsonUtil
                            .replaceNullString(null == carApplyInfo
                                    .getCpBegtime() ? null : String
                                    .valueOf(carApplyInfo.getCpBegtime()
                                            .getTime())));
                    mipTemp.setEndtime(JsonUtil
                            .replaceNullString(null == carApplyInfo
                                    .getCpEndtime() ? null : String
                                    .valueOf(carApplyInfo.getCpEndtime()
                                            .getTime())));
                    mipTemp.setState(JsonUtil
                            .replaceNullString(CodeRepositoryUtil.getValue(
                                    "meetState", carApplyInfo.getState())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("carapplylist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getCarApplyList",operatorId, json,info, returnInfo, printStackTrace, " 通过该接口获取车辆申请列表");
        return returnInfo;
    }

    /**
     * 通过该接口获取车辆申请详情
     * 
     * @param json
     * @return
     */
    @Override
    public String getCarApplyDetail(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        OaCarApply objInfo = new OaCarApply();

        try {

            OaCarApplyMIP mip = (OaCarApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaCarApplyMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            objInfo = oaCarApplyManager.getObjectById(mip.getDjId());

            // 按接口要求封装数据
            if (null != objInfo) {
                OaCarApplyMIP mipTemp = new OaCarApplyMIP();
                mipTemp.setDjId(JsonUtil.replaceNullString(objInfo.getDjId()));
                mipTemp.setApplicantid(JsonUtil.replaceNullString(objInfo
                        .getCreater()));
                mipTemp.setApplicant(JsonUtil
                        .replaceNullString(CodeRepositoryUtil.getValue(
                                "userCode", objInfo.getCreater())));
                mipTemp.setTitle(JsonUtil.replaceNullString(objInfo
                        .getTransaffairname()));
                mipTemp.setStarttime(JsonUtil.replaceNullString(null == objInfo
                        .getCpBegtime() ? null : String.valueOf(objInfo
                        .getCpBegtime().getTime())));
                mipTemp.setEndtime(JsonUtil.replaceNullString(null == objInfo
                        .getCpEndtime() ? null : String.valueOf(objInfo
                        .getCpEndtime().getTime())));
                mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("meetState", objInfo.getState())));
                mipTemp.setPersionsNum(JsonUtil
                        .replaceNullString(null == objInfo
                                .getMeetingPersionsNum() ? "" : String
                                .valueOf(objInfo.getMeetingPersionsNum())));
                mipTemp.setTelephone(JsonUtil.replaceNullString(objInfo
                        .getTelephone()));
                mipTemp.setRemark(JsonUtil.replaceNullString(objInfo
                        .getRemark()));
                mipTemp.setPath(JsonUtil.replaceNullString(objInfo
                        .getMeetingPersions()));
                mipTemp.setReqRemark(JsonUtil.replaceNullString(objInfo
                        .getReqRemark()));
                mipTemp.setCarno(JsonUtil.replaceNullString(objInfo.getCarno()));
                mipTemp.setDriver(JsonUtil.replaceNullString(objInfo
                        .getDriver()));
                mipTemp.setDrTelephone(JsonUtil.replaceNullString(objInfo
                        .getDrTelephone()));
                dataJson.put("carapplyinfo",
                        JsonUtil.createSimpleFormJsonString(mipTemp));
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getCarApplyDetail",operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取车辆申请详情");
        return returnInfo;

    }

    /**
     * 通过该接口发起用车申请
     * 
     * @param json
     * @return
     */
    @Override
    public String bookCarApply(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            String flowCode = "000858";// 车辆申请流程flowcode

            OaCarApplyMIP mip = (OaCarApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaCarApplyMIP.class);
            loadUserByUsername(mip.getUserid());

            if (!(StringUtils.isBlank(mip.getStarttime()) && StringUtils
                    .isBlank(mip.getEndtime()))) {
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                // 处理数据
                OaCarApply carApply = new OaCarApply();

                carApply.setCreater(mip.getUserid());
                carApply.setDepno(fUserDetail.getPrimaryUnit());

                carApply.setTransaffairname(mip.getTitle());
                carApply.setMeetingPersionsNum(StringUtils.isEmpty(mip
                        .getPersionsNum()) ? null : Long.valueOf(mip
                        .getPersionsNum()));
                carApply.setTelephone(mip.getTelephone());
                carApply.setRemark(mip.getRemark());
                carApply.setReqRemark(mip.getReqRemark());
                carApply.setMeetingPersions(mip.getPath());// 行车路线
                carApply.setPlanBegTime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                carApply.setPlanEndTime(DateUtil.convertToDate(
                        mip.getEndtime(), datetimePattern));
                carApply.setCpBegtime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                carApply.setCpEndtime(DateUtil.convertToDate(mip.getEndtime(),
                        datetimePattern));
                // 生成申请编号：编号规则以HY打头+时间戳
                String no = "CAR-"
                        + new SimpleDateFormat("yyyyMMddHHmmss")
                                .format(new Date(System.currentTimeMillis()));

                if (StringUtils.isBlank(carApply.getApplyNo())) {
                    carApply.setApplyNo(no);
                }
                FlowDescribe flowDescribe = flowDefine
                        .getFlowDefObject(flowCode);

                carApply.setOptid(flowDescribe.getOptId());
                carApply.setBiztype("F");//
                carApply.setBizstate("F");
                carApply.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
                carApply.setFlowcode(flowCode);
                carApply.setCreatetime(DatetimeOpt.currentUtilDate());
                carApply.setIsUse("F");
                carApply.setState("1");
                carApply.setDjId(oaCarApplyManager.getNextKey());

                oaCarApplyManager.saveObject(carApply);

                carApply = oaCarApplyManager.getObjectById(carApply.getDjId());

                if (carApply != null) {
                    OptBaseInfo optBaseInfo = optBaseInfoManager
                            .getObjectById(carApply.getDjId());

                    if (optBaseInfo == null) {
                        optBaseInfo = new OptBaseInfo();
                        optBaseInfo.setDjId(carApply.getDjId());

                        // 添加申请名称
                        // this.setTransaffairname(object, optBaseInfo);

                        optBaseInfo.setTransaffairname(carApply
                                .getTransaffairname());
                        optBaseInfo.setOptId(flowDescribe.getOptId());
                        optBaseInfo.setFlowCode(flowCode);
                        optBaseInfo.setBiztype("F");//
                        optBaseInfo.setBizstate("F");
                        optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
                        optBaseInfo.setOrgname(fUserDetail.getUnitName());

                        optBaseInfo.setTransAffairNo(carApply.getDjId());
                        optBaseInfo.setCreatedate(carApply.getCreatetime());
                        optBaseInfo.setCreateuser(carApply.getCreater());
                        optBaseInfoManager.saveObject(optBaseInfo);
                    }

                    FlowInstance flowInst = flowEngine.createInstance(flowCode,
                            carApply.getTransaffairname(), carApply.getDjId(),
                            fUserDetail.getUsercode(),
                            fUserDetail.getPrimaryUnit());

                    long flowInstId = flowInst.getFlowInstId();

                    carApply.setFlowInstId(flowInstId);
                    carApply.setBiztype("W");// 等待审核
                    carApply.setBizstate("W");

                    oaCarApplyManager.saveObject(carApply);

                    optBaseInfo = optBaseInfoManager.getObjectById(carApply
                            .getDjId());
                    optBaseInfo.setFlowInstId(flowInstId);

                    optBaseInfo.setBiztype("W");// 等待审核
                    optBaseInfo.setBizstate("W");
                    optBaseInfoManager.saveObject(optBaseInfo);
                    // 将登记人员纳入办件角色
                    flowEngine.assignFlowWorkTeam(flowInstId, "jbr",
                            fUserDetail.getUsercode(), "用车登记人员");

                    OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
                    optIdeaInfo.setUsername(fUserDetail.getUsername());
                    FUnitinfo fUnitinfo = sysUnitManager
                            .getObjectById(fUserDetail.getPrimaryUnit().trim());
                    if (fUnitinfo == null) {
                        fUnitinfo = new FUnitinfo();
                    }
                    optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

                    optIdeaInfo.setTransidea("车辆申请");

                    OptProcInfo procInfo = new OptProcInfo();
                    long nodeInstId = flowInst.getFirstNodeInstance()
                            .getNodeInstId();
                    procInfo.setNodeInstId(0L);
                    procInfo.setDjId(carApply.getDjId());
                    procInfo.setNodename("车辆申请");
                    procInfo.setTransdate(new Date(System.currentTimeMillis()));
                    procInfo.setNodeinststate("N");
                    procInfo.setUnitcode(fUserDetail.getPrimaryUnit());
                    procInfo.setUsercode(fUserDetail.getUsercode());
                    procInfo.setTransidea("车辆申请");
                    procInfo.setNodeCode("车辆申请");
                    optProcInfoManager.saveObject(procInfo);
                    optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);
                }

                createPDF(carApply.getDjId(), fUserDetail.getUsercode());
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "请输入请假申请时间!"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("bookCarApply",operatorId, json,info, returnInfo, printStackTrace, "通过该接口发起用车申请");
        return returnInfo;
    }

    /**
     * 通过该接口，取消登录用户已经预订的车辆申请
     * 
     * @param json
     * @return
     */
    @Override
    public String cancelBookingCarApply(String json) {
        log.info("传参:" + json);
        
        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {

            OaCarApplyMIP mip = (OaCarApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaCarApplyMIP.class);
            loadUserByUsername(mip.getUserid());

            fUserDetail = (FUserDetail) sysUserManager.loadUserByUsername(mip
                    .getUserid());

            if (StringUtils.isNotEmpty(mip.getDjId())) {
                OaCarApply apply = oaCarApplyManager.getObjectById(mip
                        .getDjId());
                if (mip.getUserid().equals(apply.getCreater())
                        && !"F".equals(apply.getBizstate())
                        && ("1".equals(apply.getState()) || "2".equals(apply
                                .getState()))) {
                    OptBaseInfo optBaseInfo = optBaseInfoManager
                            .getObjectById(mip.getDjId());
                    apply.setState("7");// 取消
                    apply.setBizstate("C");// 业务状态
                    apply.setBiztype("C");// 业务类别
                    apply.setSolvestatus("C");// 申请状态-取消
                    oaCarApplyManager.saveObject(apply);
                    flowManager.stopInstance(optBaseInfo.getFlowInstId(),
                            fUserDetail.getUsercode(), "取消车辆申请");

                    // 1.获取用车相关人员
                    getCarUservalues("cancle", apply.getDjId(),
                            fUserDetail.getUsercode());

                    String mesgTitle = "与您相关的一项用车申请["
                            + apply.getTransaffairname() + "]已经被取消,请您及时关注。";
                    String mesgContent = apply.getTransaffairname()
                            + "于"
                            + DatetimeOpt.convertDatetimeToString(apply
                                    .getCpBegtime())
                            + "到"
                            + DatetimeOpt.convertDatetimeToString(apply
                                    .getCpEndtime()) + "的用车申请，现在已经被取消。";

                    // 2.输送邮件数据
                    this.sendInnermesg(userValue, mesgTitle, mesgContent,
                            apply.getDjId());

                    // 4.发送提醒
                    oaRemindInformationManager.sendOaRemindInformation(
                            apply.getDjId(), fUserDetail.getUsercode(),
                            userValue, mesgTitle, mesgContent);

                    info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                } else {
                    info.setReturninfo(new CentitWSReturninfo("1",
                            "抱歉！您没有该项操作权限，请联系车辆申请人取消申请！"));
                }
                Pattern p = Pattern.compile("a*b");

            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "抱歉！您操作的数据不存在！"));
            }
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("cancelBookingCarApply",operatorId, json, info,returnInfo, printStackTrace, "通过该接口，取消登录用户已经预订的车辆申请");
        return returnInfo;
    }

    /*
     * 获取会议相关人员 1.与会领导 2.参会单位(按岗位过滤) 3.办件参与人员（不包括自己） type domeeting 1,2 cancle
     * 1,2,3
     */
    private void getCarUservalues(String type, String djId, String usercode) {
        OaCarApply apply = oaCarApplyManager.getObjectById(djId);

        if (null != usercode) {
            if (StringUtils.isNotBlank(djId)) {
                userValue = "";

                if ("cancle".equals(type)) {
                    // 流程参与人员
                    List<OptIdeaInfo> optIdeaInfos = optProcInfoManager
                            .listIdeaLogsByDjId(apply.getDjId());
                    if (optIdeaInfos != null && optIdeaInfos.size() > 0) {
                        for (int i = 0; i < optIdeaInfos.size(); i++) {
                            if (!usercode.equals(optIdeaInfos.get(i)
                                    .getUsercode())) {
                                String value = optIdeaInfos.get(i)
                                        .getUsercode() + ",";
                                userValue += value;
                            }
                        }
                    }

                    // 下一步办理人员
                    List<VUserTaskList> tasks = actionTaskDao
                            .getTasksByFlowid(apply.getFlowInstId());
                    if (null != tasks && tasks.size() > 0)
                        for (VUserTaskList task : tasks) {
                            if (!usercode.equals(task.getUserCode())) {
                                String value = task.getUserCode() + ",";
                                userValue += value;
                            }
                        }
                }
                if (StringUtils.isNotBlank(userValue)) {
                    userValue = userValue.substring(0, userValue.length() - 1);
                }
            }

        }

    }

    /**
     * 处理上传文件
     * 
     * @param item
     * @param path
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unused")
    private FileinfoFs processUploadedFile(File file, String filename,
            String optCode) throws IOException {
        FileinfoFs info = new FileinfoFs();
        info.setFilename(FilenameUtils.getBaseName(filename));
        info.setFileext(FilenameUtils.getExtension(filename));
        info.setFilesize(file.length());
        info.setRecorderDate(new Date());
        info.setOptcode(optCode);
        info.setInstid(1L);
        info.setIndb("0");
        info.setIsindex("1");

        fileinfoFsManager
                .saveFileinfo(info, new FileInputStream(file), optCode);

        return info;
    }

    /**
     * 处理上传文件
     * 
     * @param item
     * @param path
     * @return
     * @throws IOException
     */
    private FileinfoFs processUploadedFile(InputStream is, String filename,
            String fileext, String optCode) throws IOException {

        FileinfoFs info = new FileinfoFs();
        info.setFilename(FilenameUtils.getBaseName(filename));
        info.setFileext(fileext);
        info.setFilesize((long) is.available());
        info.setRecorderDate(new Date());
        info.setOptcode(optCode);
        info.setInstid(1L);
        info.setIndb("0");
        info.setIsindex("1");

        fileinfoFsManager.saveFileinfo(info, is, optCode);

        return info;
    }

    /**
     * 处理异常
     * 
     * @param e
     */
    private CentitWebServiceInfo doWithException(Exception e,
            CentitWebServiceInfo info) {
        String errormessage = "";
        if (e instanceof NumberFormatException || e instanceof JSONException) {
            errormessage = "接口参数异常：请核对接口参数；具体异常信息为：" + e.getMessage();
        } else if (e instanceof FileNotFoundException) {
            errormessage = "文件不存在:" + e.getMessage();
        } else if (e instanceof UsernameNotFoundException) {
            errormessage = "指定用户不合法，请查实后再试；";
        } else if (e instanceof DataAccessException) {
            errormessage = "数据库访问异常，请稍后再试；";
        } else if (e instanceof java.lang.NullPointerException) {
            errormessage = "数据不存在，请稍后再试";
        } else {
            errormessage = e.getMessage();
        }
        info.setReturninfo(new CentitWSReturninfo("1", errormessage));
        log.error(errormessage);
        return info;
    }
    /**
     * 记录MIP日志,所有MIP接口均需调用
     * @param userid
     * @param injson
     * @param retunJson
     * @param excjson
     */
  private void savemiplog(String infmethods,String userid,String accparameters,CentitWebServiceInfo info,String retunJson,String stackTrace,String remarkMethods){
      if(info!=null && !"0".equals(info.getReturninfo().getStatus())){//记录异常情况
      MipLog mipLog=new MipLog();
      mipLog.setMipid(mipLogManager.nextKey());//主键
      mipLog.setInfmethods(infmethods);//接口方法
      mipLog.setAccparameters(accparameters);//接口入参
      mipLog.setReturnddata(retunJson);//接口返回值
      mipLog.setExceptioninfo(stackTrace);//接口调用异常信息
      mipLog.setCreater(userid);//接口调用操作者
      mipLog.setCreatetime(new Date(System.currentTimeMillis()));//接口调用时间
      mipLog.setRemarkMethods(remarkMethods);//接口方法注释
      mipLogManager.saveObject(mipLog);
      }
      operatorId="";//用完后清空
      printStackTrace="";//用完后清空
  }
    /**
     * 改写原有获取人员信息 区分大小写
     * 
     * @param loginname
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     */
    public FUserDetail loadUserByUsername(String loginname)
            throws UsernameNotFoundException, DataAccessException {
        operatorId=loginname;
        FUserDetail sysuser = (FUserDetail) sysuserdao
                .loadUserByLoginname(loginname);
        
        sysuser.setSysusrodao(userRoleDao);
        List<FRoleinfo> roles = sysUserManager.getSysRolesByUsid(sysuser
                .getUsercode());
        List<FUserunit> usun = sysunitdao.getSysUnitsByUserId(sysuser
                .getUsercode());
        sysuser.setUserUnits(usun);
        sysuser.setUserSetting(sysUserManager.getUserSetting(sysuser
                .getUsercode()));
        sysuser.setUserFuncs(functionDao.getMenuFuncByUserID(sysuser
                .getUsercode()));

        sysuser.setAuthoritiesByRoles(roles);

        List<FVUseroptlist> uoptlist = functionDao
                .getAllOptMethodByUser(sysuser.getUsercode());
        Map<String, String> userOptList = new HashMap<String, String>();
        if (uoptlist != null) {
            for (FVUseroptlist opt : uoptlist)
                userOptList.put(opt.getOptid() + "-" + opt.getOptmethod(), "T");
        }
        // ServletActionContext.getRequest().getSession().setAttribute("userOptList",
        // userOptList);
        sysuser.setUserOptList(userOptList);
        
        return sysuser;
    }

    private String userValue;// 获取参会人员usercode

    /*
     * 获取会议相关人员 1.与会领导 2.参会单位(按岗位过滤) 3.办件参与人员（不包括自己） type domeeting 1,2 cancle
     * 1,2,3
     */
    public void getUservalues(String type, String djId, String usercode) {
        OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(djId);
        if (StringUtils.isNotBlank(usercode)) {
            if (StringUtils.isNotBlank(djId)) {
                userValue = "";
                List<AddressBookRelection> listuser = addressBookRelectionManager
                        .getUserlist(oaMeetApply.getDjId(), "0");
                if (listuser != null && listuser.size() > 0) {
                    for (int i = 0; i < listuser.size(); i++) {
                        String value = listuser.get(i).getShareman() + ",";
                        userValue += value;
                    }
                }
                if (StringUtils.isNotBlank(oaMeetApply.getAttendingUnits())) {
                    // 默认值 BMSWGD 字典项获取
                    String userStation = CodeRepositoryUtil.getDataPiece(
                            "HYCOMON", "HYACCEPT") != null ? CodeRepositoryUtil
                            .getDataPiece("HYCOMON", "HYACCEPT").getDatavalue()
                            : "BMSWGD";
                    List<FUserinfo> userInfoList = oaMeetApplyManager
                            .getSysUsersByRoleAndUnit(
                                    oaMeetApply.getAttendingUnits(),
                                    userStation);
                    if (userInfoList != null && userInfoList.size() > 0) {
                        for (int i = 0; i < userInfoList.size(); i++) {
                            String value = userInfoList.get(i).getUsercode()
                                    + ",";
                            userValue += value;
                        }
                    }
                }

                if ("cancle".equals(type)) {
                    // 流程参与人员
                    List<OptIdeaInfo> optIdeaInfos = optProcInfoManager
                            .listIdeaLogsByDjId(oaMeetApply.getDjId());
                    if (optIdeaInfos != null && optIdeaInfos.size() > 0) {
                        for (int i = 0; i < optIdeaInfos.size(); i++) {
                            if (!usercode.equals(optIdeaInfos.get(i)
                                    .getUsercode())) {
                                String value = optIdeaInfos.get(i)
                                        .getUsercode() + ",";
                                userValue += value;
                            }
                        }
                    }

                    // 下一步办理人员
                    List<VUserTaskList> tasks = actionTaskDao
                            .getTasksByFlowid(oaMeetApply.getFlowInstId());
                    if (null != tasks && tasks.size() > 0)
                        for (VUserTaskList task : tasks) {
                            if (!usercode.equals(task.getUserCode())) {
                                String value = task.getUserCode() + ",";
                                userValue += value;
                            }
                        }
                }

                if (StringUtils.isNotBlank(userValue)) {
                    userValue = userValue.substring(0, userValue.length() - 1);
                }
            }

        }

    }

    private void sendInnermesg(String userValue, String title, String content,
            String djId) {
        if (StringUtils.isNotBlank(userValue)) {
            OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(djId);// 业务数据
            Innermsg innermsg = new Innermsg();
            innermsg.setMsgcode(innermsgManager.getNextKey());
            innermsg.setMsgtype("P");// 类型个人邮件
            innermsg.setSender("系统发起");
            innermsg.setSenddate(new Date());
            innermsg.setReceiveUserCode(userValue);
            innermsg.setTo(userValue);
            innermsg.setReceivename(oaMeetApply.getMeetingPersions());
            innermsg.setMsgcontent(content);
            innermsg.setMsgtitle(title);
            innermsg.setMailtype(Innermsg.MAIL_TYPE_O);// 发件箱标记
            innermsgManager.saveMsg(innermsg);
        }
    }

    /**
     * 验证数据合法性
     * 
     * @param djId
     */
    public void checkOfficial(String djId) throws Exception {
        OptBaseInfo optBase = optBaseInfoManager.getObjectById(djId);
        if (null == optBase) {
            throw new PublicInfoException("编号" + djId + "没有对应公文信息！");
        }
    }

    private String getFileDownloadUrl(String fileType, String fileId) {
        return getFileDownloadUrl() + "?fileType=" + fileType + "&fileId="
                + fileId;
    }

    public String getFileDownloadUrl() {
        String mobileTerminalAccessAddr = SysParametersUtils.getParameters(
                "mobileTerminalAccessAddr", null);
        HttpServletRequest request = XFireServletController.getRequest();
        if (mobileTerminalAccessAddr == null
                || "".equals(mobileTerminalAccessAddr)) {
            return WebUtil.getContextPath(request) + "download";
        } else {
            if (mobileTerminalAccessAddr.endsWith("/")) {
                mobileTerminalAccessAddr = mobileTerminalAccessAddr.substring(
                        0, mobileTerminalAccessAddr.length() - 1);
            }
            return mobileTerminalAccessAddr + request.getContextPath()
                    + "/download";
        }

    }
    
    
    
    
    
    /**
     * 通过该接口可预订请假申请。
     */
    @Override
    public String bookLeaveApply(String json) {
       log.info("传参:" + json);
       
       CentitWebServiceInfo info = new CentitWebServiceInfo();   
       try {
           String flowCode = CommonOptCodeUtil.FLOWCODE_LEAVE_APPLY;
           OabookLeaveApplyMIP mip = (OabookLeaveApplyMIP) JsonUtil.getObject4GsonString(
                   json, OabookLeaveApplyMIP.class);
           loadUserByUsername(mip.getUserid());
           
           
           
           if (!(StringUtils.isBlank(mip.getApplydate()) && StringUtils
                   .isBlank(mip.getEndtime()))) {
               fUserDetail = (FUserDetail) sysUserManager
                       .loadUserByUsername(mip.getUserid());
         
           OaLeaveapply oaLeaveapply = new OaLeaveapply();
           
           //oaLeaveapply.setCreater(fuser.getUsercode());

           oaLeaveapply.setCreater(mip.getUserid());
           oaLeaveapply.setTransaffairname(mip.getTransAffairName());
           oaLeaveapply.setApplydate(DateUtil.convertToDate(
                   mip.getApplydate(), datetimePattern));
           oaLeaveapply.setEndtime(DateUtil.convertToDate(mip.getEndtime(), datetimePattern));
           oaLeaveapply.setLeavenum(mip.getLeaveNum());
           oaLeaveapply.setRemarkContent(mip.getRemark_content());
           
           FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
           oaLeaveapply.setOptId(flowDescribe.getOptId());
           oaLeaveapply.setBiztype("F");//
           oaLeaveapply.setBizstate("F");
           oaLeaveapply.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
           oaLeaveapply.setFlowcode(flowCode);
           oaLeaveapply.setCreatertime(DatetimeOpt.currentUtilDate());
           oaLeaveapply.setDjId(optApplyManager.getNextDjId("SQ","oaLeaveapply"));
           oaLeaveapply.setApplyuser(fUserDetail.getPrimaryUnit());
           oaLeaveapply.setPostleve(fUserDetail.getUserdesc());
          
           oaLeaveapplyManager.saveObject(oaLeaveapply);
           OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(oaLeaveapply
                   .getDjId());
           if (optBaseInfo == null) {
               optBaseInfo = new OptBaseInfo();
               optBaseInfo.setDjId(oaLeaveapply.getDjId());

               optBaseInfo.setTransaffairname(oaLeaveapply.getTransaffairname());
               optBaseInfo.setOptId(flowDescribe.getOptId());
               optBaseInfo.setFlowCode(flowCode);
               optBaseInfo.setBiztype("F");//
               optBaseInfo.setBizstate("F");
               optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
               optBaseInfo.setOrgname(fUserDetail.getUnitName());
               //optBaseInfo.setCriticalLevel(oaLeaveapply.getOptBaseInfo().getCriticalLevel());
               Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                       .findB_PowerByItemId(CommonOptCodeUtil.ITEMID_LEAVE_APPLY);
               optBaseInfo.setPowername(vsuppowerinusing.getItemName());

               optBaseInfo.setTransAffairNo(oaLeaveapply.getDjId());
               optBaseInfo.setCreatedate(oaLeaveapply.getCreatertime());
               optBaseInfo.setCreateuser(oaLeaveapply.getCreater());
               optBaseInfo.setPowerid(CommonOptCodeUtil.ITEMID_LEAVE_APPLY);
               optBaseInfoManager.saveObject(optBaseInfo);
           }
           if (oaLeaveapply != null) {
               FlowInstance flowInst = flowEngine.createInstance(flowCode,
                       oaLeaveapply.getTransaffairname(), oaLeaveapply.getDjId(),
                       fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());



               
               long flowInstId = flowInst.getFlowInstId();
               long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
               oaLeaveapply.setFlowInstId(flowInstId);
             /*  curNodeInstId = nodeInstId;
               curFlowInstId = flowInstId;*/

               oaLeaveapply.setFlowInstId(flowInstId);
               oaLeaveapply.setBiztype("W");// 等待审核
               oaLeaveapply.setBizstate("W");
               
               oaLeaveapplyManager.saveObject(oaLeaveapply);

               //OptApplyInfo塞值oaleaveapply
               OptApplyInfo optApplyInfo = new OptApplyInfo();
               optApplyInfo.setDjId(oaLeaveapply.getDjId());
               optApplyInfo.setApplyItemType(CommonOptCodeUtil.APPLY_ITEM_TYPE_QJ);
               optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
               optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
               optApplyInfo.setProposerName(fUserDetail.getUsername());
               optApplyManager.saveObject(optApplyInfo);
               
               
               
               optBaseInfo = optBaseInfoManager.getObjectById(oaLeaveapply.getDjId());
               optBaseInfo.setFlowInstId(flowInstId);

               optBaseInfo.setTransaffairname(oaLeaveapply.getTransaffairname());
               optBaseInfo.setBiztype("W");// 等待审核
               optBaseInfo.setBizstate("W");
               
               optBaseInfoManager.saveObject(optBaseInfo);
               // 将登记人员纳入办件角色
               flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                       fUserDetail.getUsercode(), "登记人员");
               // 将登记部门纳入权限引擎
               flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "JSBM",
                       fUserDetail.getPrimaryUnit(), "访客接待登记");

               VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
               // 获取事权对应的信息
               vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                       .getPowerid());
               
               flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                       "JSBM", vPowerUserInfo.getUnitcode(), "请假条接收部门");
               
               this.saveIdeaInfo("访客接待登记", 0L);
           }
           
               createPDF(oaLeaveapply.getDjId(), fUserDetail.getUsercode());
               info.setReturninfo(new CentitWSReturninfo("0", "成功"));

           } else {
               info.setReturninfo(new CentitWSReturninfo("1", "请输入请假申请时间!"));
           }
           
           
       }catch (Exception e) {
           info = doWithException(e, info);
           printStackTrace=this.printStackTrace(e);
       }
       
       String returnInfo = JsonUtil.createJsonString(info);
       log.info("出参:" + returnInfo);
       this.savemiplog("bookLeaveApply",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发起请假申请");
       return returnInfo;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 通过该接口可预订自助餐申请。
     */
    @Override
    public String bookBuffetApply(String json) {
       log.info("传参:" + json);
    
       CentitWebServiceInfo info = new CentitWebServiceInfo(); 
       
       try {
           String flowCode = CommonOptCodeUtil.FLOWCODE_BUFFET_APPLY;
           OabookBuffetApplyMIP mip = (OabookBuffetApplyMIP) JsonUtil.getObject4GsonString(
                   json, OabookBuffetApplyMIP.class);
           loadUserByUsername(mip.getUserid());
           
           
           if (!(StringUtils.isBlank(mip.getApplydate()))) {
               fUserDetail = (FUserDetail) sysUserManager
                       .loadUserByUsername(mip.getUserid());
               
           OaBuffetapply oaBuffetapply = new OaBuffetapply(); 
           
           oaBuffetapply.setCreater(mip.getUserid());
           oaBuffetapply.setTransaffairname(mip.getTransAffairName());
           oaBuffetapply.setApplydate(DateUtil.convertToDate(
                   mip.getApplydate(), datetimePattern));
           oaBuffetapply.setApplyuser(mip.getApplyuser());
           oaBuffetapply.setVisitors(mip.getVisitors());
           oaBuffetapply.setVisitorsnum(mip.getVisitorsnum());
           oaBuffetapply.setRemarkContent(mip.getRemark_content());
           
           oaBuffetapply.setDjId(optApplyManager.getNextDjId("SQ","oaBuffetapply"));//djid

           FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
           oaBuffetapply.setOptId(flowDescribe.getOptId());
           oaBuffetapply.setBiztype("F");//
           oaBuffetapply.setBizstate("F");
           oaBuffetapply.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
           oaBuffetapply.setFlowcode(flowCode);
           oaBuffetapply.setCreatertime(DatetimeOpt.currentUtilDate());
           // 申请isuse状态为1

           oaBuffetapplyManager.saveObject(oaBuffetapply);
           OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(oaBuffetapply
                   .getDjId());
           if (optBaseInfo == null) {
               optBaseInfo = new OptBaseInfo();
               optBaseInfo.setDjId(oaBuffetapply.getDjId());

               optBaseInfo.setTransaffairname(oaBuffetapply.getTransaffairname());
               optBaseInfo.setOptId(flowDescribe.getOptId());
               optBaseInfo.setFlowCode(flowCode);
               optBaseInfo.setBiztype("F");//
               optBaseInfo.setBizstate("F");
               optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
               optBaseInfo.setOrgname(fUserDetail.getUnitName());
               //optBaseInfo.setCriticalLevel(oaBuffetapply.getOptBaseInfo().getCriticalLevel());
               Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                       .findB_PowerByItemId(CommonOptCodeUtil.ITEMID_BUFFET_APPLY);
               optBaseInfo.setPowername(vsuppowerinusing.getItemName());

               optBaseInfo.setTransAffairNo(oaBuffetapply.getDjId());
               optBaseInfo.setCreatedate(oaBuffetapply.getCreatertime());
               optBaseInfo.setCreateuser(oaBuffetapply.getCreater());
               optBaseInfo.setPowerid(CommonOptCodeUtil.ITEMID_BUFFET_APPLY);
               optBaseInfoManager.saveObject(optBaseInfo);
           }
           if (oaBuffetapply != null) {
               FlowInstance flowInst = flowEngine.createInstance(flowCode,
                       oaBuffetapply.getTransaffairname(), oaBuffetapply.getDjId(),
                       fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());

               
               long flowInstId = flowInst.getFlowInstId();
               long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
               oaBuffetapply.setFlowInstId(flowInstId);

               oaBuffetapply.setFlowInstId(flowInstId);
               oaBuffetapply.setBiztype("W");// 等待审核
               oaBuffetapply.setBizstate("W");
               oaBuffetapplyManager.saveObject(oaBuffetapply);
               
               //OptApplyInfo塞值oaBuffetapply
               OptApplyInfo optApplyInfo = new OptApplyInfo();
               optApplyInfo.setDjId(oaBuffetapply.getDjId());
               optApplyInfo.setApplyItemType(CommonOptCodeUtil.APPLY_ITEM_TYPE_ZZC);
               optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
               optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
               optApplyInfo.setProposerName(fUserDetail.getUsername());
               optApplyManager.saveObject(optApplyInfo);
               

               optBaseInfo = optBaseInfoManager.getObjectById(oaBuffetapply.getDjId());
               optBaseInfo.setFlowInstId(flowInstId);

               optBaseInfo.setTransaffairname(oaBuffetapply.getTransaffairname());
               optBaseInfo.setBiztype("W");// 等待审核
               optBaseInfo.setBizstate("W");
               optBaseInfoManager.saveObject(optBaseInfo);
               // 将登记人员纳入办件角色
               flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                       fUserDetail.getUsercode(), "登记人员");
               // 将登记部门纳入权限引擎
               flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "DJBM",
                       fUserDetail.getPrimaryUnit(), "客情通报登记部门");
               
               VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
               // 获取事权对应的信息
               vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                       .getPowerid());
             
               this.saveIdeaInfo("接待清单登记", 0L);
               
           }
           
          /* //保存通用配置数据
           if(null!=optBaseInfo.getDjId()){
                 this.saveSQModuleData();   
           }*/
           
           createPDF(oaBuffetapply.getDjId(), fUserDetail.getUsercode());
           info.setReturninfo(new CentitWSReturninfo("0", "成功"));

           } else {
               info.setReturninfo(new CentitWSReturninfo("1", "请输入自助餐申请时间!"));
           }   
               
       
           }catch (Exception e) {
               info = doWithException(e, info);
               printStackTrace=this.printStackTrace(e);
           }   
       
       String returnInfo = JsonUtil.createJsonString(info);
       log.info("出参:" + returnInfo);
       this.savemiplog("bookBuffetApply",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发起自助餐申请");
       return returnInfo;
    }
    
    
    
    
    

  /**
  * 通过该接口可提交物品申领信息。
  */
 @Override
 public String bookOfficeSuppApply(String json) {
    log.info("传参:" + json);
 
    CentitWebServiceInfo info = new CentitWebServiceInfo(); 
    
    try {
        String flowCode = CommonOptCodeUtil.FLOWCODE_OFFICESUPPLIES;
        OabookOfficeSuppApplyMIP mip = (OabookOfficeSuppApplyMIP) JsonUtil.getObject4GsonString(
                json, OabookOfficeSuppApplyMIP.class);
        loadUserByUsername(mip.getUserid());
        
        
        if (!(StringUtils.isBlank(mip.getApplydate()))) {
            fUserDetail = (FUserDetail) sysUserManager
                    .loadUserByUsername(mip.getUserid());
            
        OaOfficesupplies Officesuppapply = new OaOfficesupplies();
        
        Officesuppapply.setCreater(mip.getUserid());
        Officesuppapply.setTransaffairname(mip.getTransAffairName());
        Officesuppapply.setApplydate(DateUtil.convertToDate(
                mip.getApplydate(), datetimePattern));
        Officesuppapply.setApplyuser(mip.getApplyuser());
        Officesuppapply.setRemarkContent(mip.getRemark_content());
        Officesuppapply.setRemark(mip.getRemark());
        
        Officesuppapply.setDjId(optApplyManager.getNextDjId("SQ","oaOfficesupplies"));//djid
        
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        Officesuppapply.setOptId(flowDescribe.getOptId());
        Officesuppapply.setBiztype("F");//
        Officesuppapply.setBizstate("F");
        Officesuppapply.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        Officesuppapply.setFlowcode(flowCode);
        Officesuppapply.setCreatertime(DatetimeOpt.currentUtilDate());
        
        Officesuppapply.setCreater(fUserDetail.getUsercode());
        Officesuppapply.setApplyuser(fUserDetail.getPrimaryUnit());
        // 申请isuse状态为1

        oaOfficesuppliesManager.saveObject(Officesuppapply);
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(Officesuppapply
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(Officesuppapply.getDjId());

            optBaseInfo.setTransaffairname(Officesuppapply.getTransaffairname());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
            optBaseInfo.setOrgname(fUserDetail.getUnitName());
        //  optBaseInfo.setCriticalLevel(Officesuppapply.getOptBaseInfo().getCriticalLevel());
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                    .findB_PowerByItemId(CommonOptCodeUtil.ITEMID_OFFICESUPPLIES);
            optBaseInfo.setPowername(vsuppowerinusing.getItemName());

            optBaseInfo.setTransAffairNo(Officesuppapply.getDjId());
            optBaseInfo.setCreatedate(Officesuppapply.getCreatertime());
            optBaseInfo.setCreateuser(Officesuppapply.getCreater());
            optBaseInfo.setPowerid(CommonOptCodeUtil.ITEMID_OFFICESUPPLIES);
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (Officesuppapply != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    Officesuppapply.getTransaffairname(), Officesuppapply.getDjId(),
                    fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();

            Officesuppapply.setFlowInstId(flowInstId);
            Officesuppapply.setBiztype("W");// 等待审核
            Officesuppapply.setBizstate("W");
            oaOfficesuppliesManager.saveObject(Officesuppapply);
            
            
            //OptApplyInfo塞值oaOfficesuppapply
            OptApplyInfo optApplyInfo = new OptApplyInfo();
            optApplyInfo.setDjId(Officesuppapply.getDjId());
            optApplyInfo.setApplyItemType(CommonOptCodeUtil.APPLY_ITEM_TYPE_WPSL);
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setProposerName(fUserDetail.getUsername());
            optApplyManager.saveObject(optApplyInfo);

            optBaseInfo = optBaseInfoManager.getObjectById(Officesuppapply.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setTransaffairname(Officesuppapply.getTransaffairname());
            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fUserDetail.getUsercode(), "登记人员");
            // 将登记部门纳入权限引擎
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "JSBM",
                    fUserDetail.getPrimaryUnit(), "访客接待登记");

            VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "物品申领接收部门");
            
            this.saveIdeaInfo("访客接待登记", 0L);
        }
        
        createPDF(Officesuppapply.getDjId(), fUserDetail.getUsercode());
        info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } else {
            info.setReturninfo(new CentitWSReturninfo("1", "请输入物品申领申请时间!"));
        }   
            
    
        }catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }   
    
    String returnInfo = JsonUtil.createJsonString(info);
    log.info("出参:" + returnInfo);
    this.savemiplog("bookOfficeSuppApply",operatorId, json,info, returnInfo, printStackTrace, "通过该接口发起物品申领信息");
    return returnInfo;
 }
    
    
    
    
    
 
 
 
 
 
 
 
 
 
 /**
  * 通过该接口可提交公示公告入网申请。
  * 
  */
 @Override
 public String bookNetApplication(String json) {
    log.info("传参:" + json);
 
    CentitWebServiceInfo info = new CentitWebServiceInfo(); 
    OaNetapplication netapplication = new OaNetapplication();
    
    try {
        String flowCode = CommonOptCodeUtil.FLOWCODE_NETAPPLICATION;
        OabookNetApplicationMIP mip = (OabookNetApplicationMIP) JsonUtil.getObject4GsonString(
                json, OabookNetApplicationMIP.class);
        loadUserByUsername(mip.getUserid());
        
        //设置djid
        String djid=optApplyManager.getNextDjId("SQ","oaNetapplication");
        /** 附件处理beg **/
        
        List<com.centit.mip.po.OabookNetApplicationMIP.AttachlistMIP> attachList = mip.getAttachlist();

        List<String> msgannexs = new ArrayList<String>();
        if (null != attachList && attachList.size() > 0) {
            for (Iterator<com.centit.mip.po.OabookNetApplicationMIP.AttachlistMIP> iterator = attachList.iterator(); iterator
                    .hasNext();) {
                com.centit.mip.po.OabookNetApplicationMIP.AttachlistMIP attachlistMIP = iterator.next();

                if (StringUtils.isNotBlank(attachlistMIP.getAttachurl())) {
                    String PHONESERURL = attachlistMIP.getAttachurl();
                    URL resourceUrl = new URL(PHONESERURL);

                    /*
                     * InputStream content = (InputStream) resourceUrl
                     * .getContent();
                     * 
                     * URL resourceUrl = new URL(PHONESERURL);
                     */
                    HttpURLConnection httpUrl = (HttpURLConnection) resourceUrl
                            .openConnection();
                    InputStream content = httpUrl.getInputStream();

                    FileinfoFs fileinfoFs = processUploadedFile(content,
                            attachlistMIP.getAttachtitle(),
                            attachlistMIP.getAttachtype(),
                            CommonCodeUtil.PUBLICINFO_OPTCODE);
                    //更换存储路径
                    String srcFileDirPath = fileinfoFs.getFile().getParent();
                    String distFileDirPath = srcFileDirPath.replace(SysParametersUtils.getUploadHome()+File.separator+CommonCodeUtil.PUBLICINFO_OPTCODE, SysParametersUtils.getWorkflowAffixHome());
                    File distDir = new File(distFileDirPath);
                    if(!distDir.exists() && distDir.isDirectory()){
                        distDir.mkdirs();
                    }
                    File distFile = new File(distDir + File.separator + fileinfoFs.getFilename()+"."+fileinfoFs.getFileext());
                    FileUtils.copyFile(fileinfoFs.getFile(), distFile);
                    fileinfoFs.getFile().delete();
                    
                    msgannexs.add(fileinfoFs.getFilecode());
                    OptStuffInfo stuff=new OptStuffInfo();
                    stuff.setDjId(djid);
                    stuff.setStuffid(UUID.randomUUID().toString().replaceAll("-", ""));
                    stuff.setFilename(fileinfoFs.getFilename()+"."+fileinfoFs.getFileext());//拼接附件与附件类型
                    stuff.setStuffpath(distFile.getAbsolutePath().replace(SysParametersUtils.getWorkflowAffixHome(), ""));
                    stuff.setUploadtime(new Date());
                    stuff.setUploadusercode(mip.getUserid());
                    optStuffInfoManager.saveObject(stuff);
                    //附件保存入opt_stuff_info表
                }

            }

            if (null != msgannexs && msgannexs.size() > 0) {
                for (String s : msgannexs) {
                    netapplication.getMsgannexs().add(
                            new Msgannex(null, this.fileinfoFsManager
                                    .getObjectById(s)));
                }
            }
        }
        
        /** 附件处理end **//*

        netapplication.setCreater(mip.getUserid());// 用户唯一ID
        netapplication.setRemarkContent(mip.getRemark_content());// 内容
        netapplication.setCreatertime(new Date());// 当前日期
*/        
        //oaNetapplicationManager.saveMsg(netapplication);
        info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        
        
        if (!(StringUtils.isBlank(mip.getApplydate()))) {
            fUserDetail = (FUserDetail) sysUserManager
                    .loadUserByUsername(mip.getUserid());
            
        OaNetapplication  oaNetapplication = new OaNetapplication();
        
        oaNetapplication.setCreater(mip.getUserid());
        oaNetapplication.setTransaffairname(mip.getTransAffairName());
        oaNetapplication.setApplydate(DateUtil.convertToDate(
                mip.getApplydate(), datetimePattern));
        oaNetapplication.setApplyuser(mip.getApplyuser());
        oaNetapplication.setRemarkContent(mip.getRemark_content());
        
        oaNetapplication.setDjId(djid);//djid
        
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        oaNetapplication.setOptId(flowDescribe.getOptId());
        oaNetapplication.setBiztype("F");//
        oaNetapplication.setBizstate("F");
        oaNetapplication.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        oaNetapplication.setFlowcode(flowCode);
        oaNetapplication.setCreatertime(DatetimeOpt.currentUtilDate());
        // 申请isuse状态为1
        oaNetapplication.setCreater(fUserDetail.getUsercode());
        oaNetapplication.setApplyuser(fUserDetail.getPrimaryUnit());
        
        oaNetapplicationManager.saveObject(oaNetapplication);
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(oaNetapplication
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(oaNetapplication.getDjId());

            optBaseInfo.setTransaffairname(oaNetapplication.getTransaffairname());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
            optBaseInfo.setOrgname(fUserDetail.getUnitName());
            //optBaseInfo.setCriticalLevel(object.getOptBaseInfo().getCriticalLevel());
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                    .findB_PowerByItemId(CommonOptCodeUtil.ITEMID_NETAPPLICATION);
            optBaseInfo.setPowername(vsuppowerinusing.getItemName());

            optBaseInfo.setTransAffairNo(oaNetapplication.getDjId());
            optBaseInfo.setCreatedate(oaNetapplication.getCreatertime());
            optBaseInfo.setCreateuser(oaNetapplication.getCreater());
            optBaseInfo.setPowerid(CommonOptCodeUtil.ITEMID_NETAPPLICATION);
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (oaNetapplication != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    oaNetapplication.getTransaffairname(), oaNetapplication.getDjId(),
                    fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            

            oaNetapplication.setFlowInstId(flowInstId);
            oaNetapplication.setBiztype("W");// 等待审核
            oaNetapplication.setBizstate("W");
            oaNetapplicationManager.saveObject(oaNetapplication);
            
            //OptApplyInfo塞值oaOfficesuppapply
            OptApplyInfo optApplyInfo = new OptApplyInfo();
            optApplyInfo.setDjId(oaNetapplication.getDjId());
            optApplyInfo.setApplyItemType(CommonOptCodeUtil.APPLY_ITEM_TYPE_RW);
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setProposerName(fUserDetail.getUsername());
            optApplyManager.saveObject(optApplyInfo);

            optBaseInfo = optBaseInfoManager.getObjectById(oaNetapplication.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setTransaffairname(oaNetapplication.getTransaffairname());
            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fUserDetail.getUsercode(), "登记人员");
            // 将登记部门纳入权限引擎
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "JSBM",
                    fUserDetail.getPrimaryUnit(), "访客接待登记");

            VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "入网申请接收部门");
            
            this.saveIdeaInfo("访客接待登记", 0L);
        }
        
        
        
        createPDF(oaNetapplication.getDjId(), fUserDetail.getUsercode());
        info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } else {
            info.setReturninfo(new CentitWSReturninfo("1", "请输入公示公告入网申请申请时间!"));
        }   
            
    
        }catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }   
    
    String returnInfo = JsonUtil.createJsonString(info);
    log.info("出参:" + returnInfo);
    this.savemiplog("bookNetApplication",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发起公示公告入网申请信息");
    return returnInfo;
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /**
  * 通过该接口可提交外出请假报备单。
  */
 @Override
 public String bookLeaveReported(String json) {
    log.info("传参:" + json);
 
    CentitWebServiceInfo info = new CentitWebServiceInfo(); 
    
    try {
        String flowCode = CommonOptCodeUtil.FLOWCODE_LEAVEREPORTED;
        OabookLeaveReportedMIP mip = (OabookLeaveReportedMIP) JsonUtil.getObject4GsonString(
                json, OabookLeaveReportedMIP.class);
        loadUserByUsername(mip.getUserid());
        
        
        if (!(StringUtils.isBlank(mip.getApplydate()))) {
            fUserDetail = (FUserDetail) sysUserManager
                    .loadUserByUsername(mip.getUserid());
            
        OaLeavereported  oaLeavereported = new OaLeavereported();
        FUserDetail sysuser = (FUserDetail) sysuserdao
                .loadUserByLoginname(mip.getUserid());
        oaLeavereported.setCreater(mip.getUserid());
        oaLeavereported.setTransaffairname(mip.getTransAffairName() + "外出请假报备单");
        oaLeavereported.setSolvenote(mip.getTransAffairName());
        oaLeavereported.setApplydate(DateUtil.convertToDate(
                mip.getApplydate(), datetimePattern));
        oaLeavereported.setEndtime(DateUtil.convertToDate(
                mip.getEndtime(), datetimePattern));
        oaLeavereported.setApplyuser(mip.getApplyuser());
        oaLeavereported.setPostleve(mip.getPostleve());
        oaLeavereported.setRemarkContent(mip.getRemark_content());
        oaLeavereported.setLeaveaddress(mip.getLeaveaddress());
        oaLeavereported.setTelephone(mip.getTelephone());
        oaLeavereported.setRemark(mip.getRemark());
        
        oaLeavereported.setDjId(optApplyManager.getNextDjId("SQ","oaLeavereported"));//djid
        
        
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        oaLeavereported.setOptId(flowDescribe.getOptId());
        oaLeavereported.setBiztype("F");//
        oaLeavereported.setBizstate("F");
        oaLeavereported.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        oaLeavereported.setFlowcode(flowCode);
        oaLeavereported.setCreatertime(DatetimeOpt.currentUtilDate());
        // 申请isuse状态为1
        
        /*oaLeavereported.setPostleve(fUserDetail.getPrimaryUnit());
        oaLeavereported.setPostleve(fUserDetail.getUserdesc());*/
        oaLeavereportedManager.saveObject(oaLeavereported);
        
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(oaLeavereported
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(oaLeavereported.getDjId());

            optBaseInfo.setTransaffairname(oaLeavereported.getTransaffairname());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
            optBaseInfo.setOrgname(fUserDetail.getUnitName());
           // optBaseInfo.setCriticalLevel(oaLeavereported.getOptBaseInfo().getCriticalLevel());
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                    .findB_PowerByItemId(CommonOptCodeUtil.ITEMID_LEAVEREPORTED);
            optBaseInfo.setPowername(vsuppowerinusing.getItemName());

            optBaseInfo.setTransAffairNo(oaLeavereported.getDjId());
            optBaseInfo.setCreatedate(oaLeavereported.getCreatertime());
            optBaseInfo.setCreateuser(oaLeavereported.getCreater());
            optBaseInfo.setPowerid(CommonOptCodeUtil.ITEMID_LEAVEREPORTED);
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (oaLeavereported != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    oaLeavereported.getTransaffairname(), oaLeavereported.getDjId(),
                    fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            

            oaLeavereported.setFlowInstId(flowInstId);
            oaLeavereported.setBiztype("W");// 等待审核
            oaLeavereported.setBizstate("W");
            oaLeavereportedManager.saveObject(oaLeavereported);
            
            
            //OptApplyInfo塞值oaLeavereported
            OptApplyInfo optApplyInfo = new OptApplyInfo();
            optApplyInfo.setDjId(oaLeavereported.getDjId());
            optApplyInfo.setApplyItemType(CommonOptCodeUtil.APPLY_ITEM_TYPE_WCQJ);
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setProposerName(fUserDetail.getUsername());
            optApplyManager.saveObject(optApplyInfo);

            optBaseInfo = optBaseInfoManager.getObjectById(oaLeavereported.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setTransaffairname(oaLeavereported.getTransaffairname());
            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    fUserDetail.getUsercode(), "登记人员");
            // 将登记部门纳入权限引擎
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "JSBM",
                    fUserDetail.getPrimaryUnit(), "访客接待登记");

            VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "外出请假报备接收部门");
            
            this.saveIdeaInfo("访客接待登记", 0L);
        }
        
        
        createPDF(oaLeavereported.getDjId(), fUserDetail.getUsercode());
        info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } else {
            info.setReturninfo(new CentitWSReturninfo("1", "请输入外出请假报备申请时间!"));
        }   
            
    
        }catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }   
    
    String returnInfo = JsonUtil.createJsonString(info);
    log.info("出参:" + returnInfo);
    this.savemiplog("bookLeaveReported",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发起外出请假报备信息");
    return returnInfo;
 }
 
 /**
  * 通过该接口可提交外出请假报备单。
  */
 @Override
 public String bookOatripPlan(String json) {
    log.info("传参:" + json);
 
    CentitWebServiceInfo info = new CentitWebServiceInfo(); 
    
    try {
        String flowCode = "001630";
        OaTripPlanMIP mip = (OaTripPlanMIP) JsonUtil.getObject4GsonString(
                json, OaTripPlanMIP.class);
        loadUserByUsername(mip.getUserid());
        
        
        if (!(StringUtils.isBlank(mip.getUserid()))) {
            fUserDetail = (FUserDetail) sysUserManager
                    .loadUserByUsername(mip.getUserid());
            
        OaTripPlan oa = new OaTripPlan();
        FUserDetail sysuser = (FUserDetail) sysuserdao.loadUserByLoginname(mip.getUserid());
        oa.setCreater(mip.getUserid());
        oa.setTripPlanName(fUserDetail.getUsername() + "出差申请单");
        oa.setStartTime(DateUtil.convertToDate(
                mip.getStartTime(), datetimePattern));
        oa.setEndTime(DateUtil.convertToDate(
                mip.getEndTime(), datetimePattern));
        oa.setTripPalce(mip.getTripPalce());
        oa.setTeamName(mip.getTeamName());
        oa.setTeamCodes(mip.getTeamCodes());
        oa.setTransport(mip.getTransport());
        oa.setContent(mip.getContent());
        oa.setRemark(mip.getRemark());
        oa.setCreateDate(new Date());
        oa.setItemId("WPSL-SQ-002");
        oa.setDeptno(fUserDetail.getPrimaryUnit());
        oa.setDjId(optApplyManager.getNextDjId("SQ","oaTripPlan"));//djid
        
        
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        oa.setBizState("F");
        oa.setFlowcode(flowCode);
        oa.setCreateDate(DatetimeOpt.currentUtilDate());
        // 申请isuse状态为1
        oaTripPlanManager.saveObject(oa);
        
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(oa
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(oa.getDjId());

            optBaseInfo.setTransaffairname(oa.getTripPlanName());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fUserDetail.getPrimaryUnit());
            optBaseInfo.setOrgname(fUserDetail.getUnitName());
           // optBaseInfo.setCriticalLevel(oaLeavereported.getOptBaseInfo().getCriticalLevel());
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingManager
                    .findB_PowerByItemId("WPSL-SQ-002");
            optBaseInfo.setPowername(vsuppowerinusing.getItemName());

            optBaseInfo.setTransAffairNo(oa.getDjId());
            optBaseInfo.setCreatedate(oa.getCreateDate());
            optBaseInfo.setCreateuser(oa.getCreater());
            optBaseInfo.setPowerid("WPSL-SQ-002");
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (oa != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    oa.getTripPlanName(), oa.getDjId(),
                    fUserDetail.getUsercode(), fUserDetail.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            

            oa.setFlowInstId(flowInstId);
            oa.setBizState("W");
            oaTripPlanManager.saveObject(oa);
            
            
            //OptApplyInfo塞值oaLeavereported
            OptApplyInfo optApplyInfo = new OptApplyInfo();
            optApplyInfo.setDjId(oa.getDjId());
            optApplyInfo.setApplyItemType("oaTripPlan");
            optApplyInfo.setBookDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setApplyDate(new Date(System.currentTimeMillis()));
            optApplyInfo.setProposerName(fUserDetail.getUsername());
            optApplyManager.saveObject(optApplyInfo);

            optBaseInfo = optBaseInfoManager.getObjectById(oa.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setTransaffairname(oa.getTripPlanName());
            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId, "SqApply",
                    mip.getUserid(), "登记人员");
            // 将登记部门纳入权限引擎
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(), "DJBM",
                    mip.getUserid(), "出差登记");
            
            VPowerUserInfo vPowerUserInfo = new VPowerUserInfo();
            // 获取事权对应的信息
            vPowerUserInfo = vPowerUserInfoManager.getPowerInfo(optBaseInfo
                    .getPowerid());
            
            flowEngine.assignFlowOrganize(optBaseInfo.getFlowInstId(),
                    "JSBM", vPowerUserInfo.getUnitcode(), "出差登记");
            
            
            this.saveIdeaInfo("访客接待登记", 0L);
        }
        
        
        createPDF(oa.getDjId(), fUserDetail.getUsercode());
        info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } else {
            info.setReturninfo(new CentitWSReturninfo("1", "参数有误"));
        }   
            
    
        }catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }   
    
    String returnInfo = JsonUtil.createJsonString(info);
    log.info("出参:" + returnInfo);
    this.savemiplog("bookLeaveReported",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发起外出请假报备信息");
    return returnInfo;
 }  

    private void createPDF() {
        // TODO Auto-generated method stub
        
    }

    private void saveIdeaInfo(String string, long l) {
        // TODO Auto-generated method stub
        
    }

    private FUserDetail getLoginUser() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 用车申请审批不涉及手写签批合并图层 在手机端发起流程并创建pdf
     */
    private void createPDF(String djId, String userCode) {
        // 生成pdf
        if ("true".equals(SysParametersUtils.getParameters(
                "generatePdfForDocEnable", "false"))) {
            final String exePath = optPdfInfoManager
                    .getPdf2SwfToolPath(XFireServletController.getRequest());
            int bizType = optPdfInfoManager.getBizTypeForPdf(djId);
            String formHtmlUrl = "";
            String contextPath = WebUtil.getContextPath(XFireServletController
                    .getRequest());
            if (bizType == 1) {
                formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(contextPath,
                        userCode, djId, "0");
            }
            if (bizType == 2) {
                formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath,
                        userCode, djId);
            }

            // 签报
            if (bizType == 3) {
                formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(contextPath,
                        userCode, djId);
            }
            // 车辆
            if (bizType == 4) {
                formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(contextPath,
                        userCode, djId);
            }
            
           // 事权
            if (bizType == 5) {
                String moduleType =optBaseInfoManager.getApplyItemType(djId);
                formHtmlUrl = optPdfInfoManager.getSQFormHtmlUrl(contextPath,
                        userCode, moduleType, djId);
            }
            createPDF(djId, "创建", 0L, userCode, exePath, formHtmlUrl);
        }
    }

    /**
     * 新增是生成pdf
     * 
     * @param oaCarApply
     * @param nodeName
     * @param nodeInstId
     * @param userCode
     * @param exeToolpath
     * @param formHtmlUrl
     */
    private void createPDF(String djId, String nodeName, Long nodeInstId,
            String userCode, String exeToolpath, String formHtmlUrl) {
        OptPdfInfo optPdfInfo = null;
        try {
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<djId:" + djId
                    + "生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(
                    djId, nodeInstId);
            File pdfStoreFile = new File(pdfStorePath);
            // 创建pdf
            optPdfInfo = optPdfInfoManager.createPDF(djId, nodeInstId,
                    formHtmlUrl);
            // 转成swf
            optPdfInfoManager.convertPdfToSwf(exeToolpath,
                    optPdfInfo.getFilePath(), pdfStoreFile.getParent(),
                    pdfStoreFile.getName().replace("pdf", "swf"));
            // 加密
            optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo,
                    pdfStoreFile.getParent(), pdfStoreFile.getName());

            optPdfInfo.setUserCode(userCode);
            optPdfInfo.setBizType(String.valueOf(optPdfInfoManager
                    .getBizTypeForPdf(djId)));
            optPdfInfo.setNodeName(nodeName);
            optPdfInfo.setNodeInstId(nodeInstId);
            optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
            optPdfInfoManager.saveObject(optPdfInfo);
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<申请djId:" + djId
                    + "生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (Exception e) {
            log.error("生成PDF异常：" + e.getMessage());
        } finally {
            if (optPdfInfo != null
                    && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())) {
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
    }

    private CentitWebCommonBizServiceImpl getCentitWebCommonBizServiceImpl(
            ThreadLocal<CentitWebCommonBizServiceImpl> centitWebCommonBizServiceImplThreadLocal) {
        CentitWebCommonBizServiceImpl centitWebCommonBizServiceImpl = centitWebCommonBizServiceImplThreadLocal
                .get();
        if (null == centitWebCommonBizServiceImpl) {
            // 获取spring的context --自动提交节点
            WebApplicationContext webApplicationContext = ContextLoader
                    .getCurrentWebApplicationContext();         
            centitWebCommonBizServiceImpl = (CentitWebCommonBizServiceImpl) webApplicationContext
                    .getBean("centitWebCommonBizServiceImpl");
            centitWebCommonBizServiceImplThreadLocal
                    .set(centitWebCommonBizServiceImpl);
        }
        return centitWebCommonBizServiceImpl;
    }
    
    
    
    
    
    
    
    
    /**
     *  接口描述 通过该接口推送资讯类交互信息，比如，活动参与结果、投票结果等。返回值包括：资讯ID、操作者、操作时间、操作结果等。
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯主键
     * @param attendTime
     *            交互时间
     * @param attendResult
     *            交互结果（这个结果由具体业务解释，可以为具体中文）
     * @param remark
     *            （此字段为扩展字段,暂时保留）
     * 
     * @return
     */
    @Override
    public String sendAttendInfoResult(String json) {
        // TODO Auto-generated method stub
        
            CentitWebServiceInfo info = new CentitWebServiceInfo();
        
            try {
                OasendAttendInfoResultMIP mip = (OasendAttendInfoResultMIP) JsonUtil.getObject4GsonString(
                        json, OasendAttendInfoResultMIP.class);
                loadUserByUsername(mip.getUserid());
                
                OaInfosummary oains = new OaInfosummary();
                oains.setNo(mip.getInformationid());
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                List<OaInfosummary> listons = oaInfosummaryManager.findInfoAttend(mip.getInformationid(), fUserDetail.getUsername());
                OaUserinfo userSex = oaUserinfoManager.getObjectById(fUserDetail.getUsercode());
               
                if(listons.size()==0){
                    oains.setId(oaInformationManager.getNextkey(""));
                    oains.setNo(mip.getInformationid());
                    oains.setCreater(fUserDetail.getUsername());
                    oains.setUnitcode(fUserDetail.getUnitName());
                    oains.setTelephone(fUserDetail.getContactPhone());
                    if (userSex != null){
                        oains.setSex(userSex.getSex());
                    }
                    oains.setRemark(mip.getRemark());
                    oains.setCreatertime(DatetimeOpt.currentUtilDate());
                    oaInfosummaryManager.saveObject(oains);
                    info.setReturninfo(new CentitWSReturninfo("0", "成功"));
                }else{
                    info.setReturninfo(new CentitWSReturninfo("0", "您已参与过该活动"));
                }
               
                info.setBizdata(dataJson);
            } catch (Exception e) {
                info = doWithException(e, info);
                printStackTrace=this.printStackTrace(e);
            }
            String returnInfo = JsonUtil.createJsonString(info);
            log.info("出参:" + returnInfo);
            this.savemiplog("sendAttendInfoResult", operatorId, json,info, returnInfo, printStackTrace, "接口描述 通过该接口推送资讯类交互信息，比如，活动参与结果、投票结果等。返回值包括：资讯ID、操作者、操作时间、操作结果等。");
            return returnInfo;
    }
        
        
    
    /**
     * 通过该接口获取生活服务类别列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            服务类别：出行CX、住房ZF、商品SP、娱乐YL、休闲XX、其他OT
     * 
     * @return
     */
    @Override
    public String getBbsInfoList(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaBbsTheme> objList = null;
        List<OaBbsMIP> objMIPList = new ArrayList<OaBbsMIP>();
        try {
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 时间戳
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("sublayouttitle", mip.getKeyword());// 搜索关键字
            if(StringUtils.isNotBlank(OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(mip.getType())))
            filterMap.put("layoutno", OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(mip.getType()));// 服务类别：出行CX、住房ZF、商品SP、娱乐YL、休闲XX、其他OT
            
            filterMap.put("ORDER_BY", "createtime desc");// 只有创建时间可以精确到秒

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            
            objList = oaBbsThemeManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaBbsTheme oaBbsTheme : objList) {
                    OaBbsMIP mipTemp = new OaBbsMIP();
                    mipTemp.setId(JsonUtil.replaceNullString(oaBbsTheme.getThemeno()));
                    mipTemp.setTitle(JsonUtil
                            .replaceNullString(oaBbsTheme.getSublayouttitle()));
                  
                    mipTemp.setPublishtime(JsonUtil
                            .replaceNullString(null == oaBbsTheme
                                    .getCreatetime() ? null : String
                                    .valueOf(oaBbsTheme.getCreatetime()
                                            .getTime())));
                    mipTemp.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                            .getValue("usercode", oaBbsTheme.getCreater())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("infoList",
                    JsonUtil.createOtherleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBbsInfoList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取生活服务类别列表信息，支持条件查询，支持分页显示");
        return returnInfo;
       
    }
    
    
    /**
     * 通过该接口获取生活服务最新最热帖子列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */
    @Override
    public String getBbsNewHotInfoList(String json){
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaBbsTheme> objList = null;
        List<OaBbsMIP> objMIPList = new ArrayList<OaBbsMIP>();
        try {
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 时间戳
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("sublayouttitle", mip.getKeyword());// 搜索关键字
            
            //1 new  2  hot  null或其他查最近10条
            if (StringUtils.isNotBlank(mip.getType())&&"2".equals(mip.getType())){
                filterMap.put("ORDER_BY", "postsviewnum desc");
            }else {
                filterMap.put("ORDER_BY", "createtime desc");
            }
            

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            
            objList = oaBbsThemeManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaBbsTheme oaBbsTheme : objList) {
                    OaBbsMIP mipTemp = new OaBbsMIP();
                    mipTemp.setId(JsonUtil.replaceNullString(oaBbsTheme.getThemeno()));
                    mipTemp.setTitle(JsonUtil
                            .replaceNullString(oaBbsTheme.getSublayouttitle()));
                  
                    mipTemp.setPublishtime(JsonUtil
                            .replaceNullString(null == oaBbsTheme
                                    .getCreatetime() ? null : String
                                    .valueOf(oaBbsTheme.getCreatetime()
                                            .getTime())));
                    mipTemp.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                            .getValue("usercode", oaBbsTheme.getCreater())));
                    mipTemp.setViewnum(JsonUtil.replaceNullString(String.valueOf(oaBbsTheme.getPostsviewnum())));
                    mipTemp.setType(OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(oaBbsTheme.getLayoutno()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("bbsInfolist",
                    JsonUtil.createOtherleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBbsNewHotInfoList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取生活服务最新最热帖子列表信息，支持条件查询，支持分页显示");
        return returnInfo;
        
    }
    
    
    
    /**
     * 通过该接口获取某一服务主题详细信息，包括标题、正文等。
     * 
     * @param userid
     *            用户唯一ID
     * @param themeNo
     *            主题ID
     * @return
     */
    @Override
    public String getBbsInfoDetail(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        try {

            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            OaBbsTheme oaBbsTheme = oaBbsThemeManager.getObjectById(mip.getThemeNo());
            
            // 更新帖子查看数
            oaBbsThemeManager.updatePostsViewNum(mip.getThemeNo());
            
            // 按接口要求封装数据
            if (null != oaBbsTheme) {
                mip = new OaBbsMIP();
                mip.setId(JsonUtil.replaceNullString(oaBbsTheme.getThemeno()));
                mip.setTitle(JsonUtil.replaceNullString(oaBbsTheme.getSublayouttitle()));
              
                mip.setPublishtime(JsonUtil.replaceNullString(null == oaBbsTheme
                        .getCreatetime() ? null : String.valueOf(oaBbsTheme
                        .getCreatetime().getTime())));
                mip.setPublishdept(JsonUtil.replaceNullString(""));//暂不塞值
                mip.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("usercode", oaBbsTheme.getCreater())));
                
                String oldChar = XFireServletController.getRequest()
                        .getContextPath() + "/";
                String newChar = getFileDownloadUrl().replace("download", "");
                mip.setContent(JsonUtil.replaceNullString(null != oaBbsTheme
                        .getBodycontent() ? oaBbsTheme.getBodycontent()
                        .replace(oldChar, newChar) : null));
              
            }

            dataJson.put("bbsInfo", JsonUtil.createSimpleFormJsonString(mip));
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBbsInfoDetail", operatorId, json, info,returnInfo, printStackTrace, " 通过该接口获取某一服务主题详细信息，包括标题、正文等");
        return returnInfo;
    }
    /**
     * 通过该接口获取某一服务主题对应留言信息，包括留言人、留言时间、留言内容等
     * 
     * @param userid
     *            用户唯一ID
     * @param themeNo
     *            主题ID
     * @return
     */
    @Override
    public String getBbsLeaveMessage(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaLeaveMessage> objList = null;
        List<OaBbsMIP> objMIPList = new ArrayList<OaBbsMIP>();
        try {
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            // 时间戳
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间
                                                                    // 时间戳
          
            if(StringUtils.isNotBlank(mip.getThemeNo()))
            filterMap.put("djid", mip.getThemeNo());
            filterMap.put("infoType", "BBS");// 留言类型
            filterMap.put("ORDER_BY", " creatertime desc");// 只有创建时间可以精确到秒

            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            objList = oaLeaveMessageManager.listObjects(filterMap, pageDesc);
            
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaLeaveMessage oaLeaveMessage : objList) {
                    OaBbsMIP mipTemp = new OaBbsMIP();
                    
                    mipTemp.setId(JsonUtil.replaceNullString(oaLeaveMessage.getNo()));
                    
                    mipTemp.setState(oaLeaveMessage.getState());
                    
                    String oldChar = XFireServletController.getRequest()
                            .getContextPath() + "/";
                    String newChar = getFileDownloadUrl().replace("download", "");
                    
                    mipTemp.setContent(JsonUtil.replaceNullString(null != oaLeaveMessage
                            .getMessageComment() ? oaLeaveMessage.getMessageComment()
                            .replace(oldChar, newChar) : null));
                    
                  
                    mipTemp.setPublishtime(JsonUtil
                            .replaceNullString(null == oaLeaveMessage
                                    .getCreatertime() ? null : String
                                    .valueOf(oaLeaveMessage.getCreatertime()
                                            .getTime())));
                    mipTemp.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                            .getValue("usercode", oaLeaveMessage.getCreater())));
                    
                    /** 处理查询条件beg **/
                    Map<String, Object> filterMapReplay = new HashMap<String, Object>();
                    filterMapReplay.put("lno", oaLeaveMessage.getNo());
                    List<OaLeaveMessagereply> replayList=  oaLeaveMessagereplyManager.listObjects(filterMapReplay);
                    
                    //回复明细
                    if(null!=replayList && replayList.size()>0){
                        List<OaBbsLeaverMessageMIP> leaverMessageMIPList =  new ArrayList<OaBbsLeaverMessageMIP>();
                        for(OaLeaveMessagereply replay  : replayList ){
                            OaBbsLeaverMessageMIP oaBbsLeaverMessageMIP = new OaBbsLeaverMessageMIP();
                            oaBbsLeaverMessageMIP.setReplayid(replay.getLno());
                            oaBbsLeaverMessageMIP.setId(JsonUtil.replaceNullString(replay.getLyno()));
                            oaBbsLeaverMessageMIP.setState(replay.getState());
                            
                            oaBbsLeaverMessageMIP.setContent(JsonUtil.replaceNullString(null != replay
                                    .getMessagecomment() ? replay.getMessagecomment()
                                    .replace(oldChar, newChar) : null));
                          
                            oaBbsLeaverMessageMIP.setPublishtime(JsonUtil
                                    .replaceNullString(null == replay
                                            .getCreatertime() ? null : String
                                            .valueOf(replay.getCreatertime()
                                                    .getTime())));
                            oaBbsLeaverMessageMIP.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                                    .getValue("usercode", replay.getCreater())));
                            leaverMessageMIPList.add(oaBbsLeaverMessageMIP);
                        }
                        mipTemp.setLeaverMessageList(leaverMessageMIPList);
                    }
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("leaveMessageList",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getBbsLeaveMessage", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取某一服务主题对应留言信息，包括留言人、留言时间、留言内容等");
        return returnInfo;
    }
    /**
     * 通过该接口发布帖子信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param LayoutNo
     *            所属版块
     * @param subLayoutTitle
     *            主题标题 IP
     * @param IP
     *            IP地址或者手机MAC地址
     * @param BodyContent
     *            正文内容
     */
    @Override
    public String sendBbsTheme(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
           
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);
            loadUserByUsername(mip.getUserid());
          if(StringUtils.isNotEmpty(OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(mip.getType()))){
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                // 处理数据
                OaBbsTheme oaBbsTheme = new OaBbsTheme();
                
                oaBbsTheme.setCreatetime(new Date(System.currentTimeMillis()));
                oaBbsTheme.setCreater(mip.getUserid());
                
                oaBbsTheme.setLayoutno(OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(mip.getType()));
                oaBbsTheme.setSublayouttitle(mip.getSubLayoutTitle());
                oaBbsTheme.setBodycontent(mip.getBodyContent());
                oaBbsTheme.setIp(mip.getIp());
                oaBbsTheme.setApprovalresults("T");// 审核状态为通过的（不需要审核的子模块，保存时默认塞值T）
                oaBbsTheme.setState("T");
                oaBbsThemeManager.saveObject(oaBbsTheme);
                
                // 更新子模块主题数
                oaBbsDiscussionManager.updateSubjectnum(OaBbsMIP.NAV_BBSTHEME_LAYOUTNO.get(mip.getType()));
                
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
          }else{
                info.setReturninfo(new CentitWSReturninfo("1", "请先选择生活服务类别!"));  
          }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendBbsTheme",operatorId, json, info,returnInfo, printStackTrace, "通过该接口发布帖子信息。");
        return returnInfo;
    }
    /**
     * 推送帖子留言信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param id
     *            帖子主题主键
     * @param sendTime
     *            留言时间
     * @param content
     *            留言内容
     */
    @Override
    public String sendBbsLeaveMessage(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
           
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);
            loadUserByUsername(mip.getUserid());
            
            //帖子信息
            OaBbsTheme  oaBbsTheme=oaBbsThemeManager.getObjectById(mip.getId());
            
          if(null!=oaBbsTheme){
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                // 处理数据
                OaLeaveMessage oaLeaveMessage = new OaLeaveMessage();
                
                oaLeaveMessage.setCreatertime(new Date(System.currentTimeMillis()));
                oaLeaveMessage.setCreater(mip.getUserid());
                
                oaLeaveMessage.setDjid(mip.getId());
                oaLeaveMessage.setMessageComment(mip.getContent());
                oaLeaveMessage.setIp(mip.getIp());
                oaLeaveMessage.setState("T");
                oaLeaveMessage.setInfoType("BBS");// info_type
                oaLeaveMessageManager.saveObject(oaLeaveMessage);
                
               
                oaBbsThemeManager.updatePostsNum(oaBbsTheme.getThemeno());
                // 更新讨论区子模块帖子回复数
                oaBbsDiscussionManager.updatePostsNum(oaBbsTheme.getLayoutno());
                
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
          }else{
                info.setReturninfo(new CentitWSReturninfo("1", "帖子不存在!"));  
          }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendBbsLeaveMessage",operatorId, json, info,returnInfo, printStackTrace, "推送帖子留言信息。");
        return returnInfo;
    }
    /**
     * 推送推送帖子留言回复信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param id
     *            帖子主题主键
     * @param sendTime
     *            留言时间
     * @param content
     *            留言内容
     */
    @Override
    public String sendBbsReplyLeaveMessage(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
           
            OaBbsMIP mip = (OaBbsMIP) JsonUtil.getObject4GsonString(
                    json, OaBbsMIP.class);
            loadUserByUsername(mip.getUserid());
            
            //留言信息
            OaLeaveMessage oaLeaveMessage=oaLeaveMessageManager.getObjectById(mip.getLeaveid());
            
          if(null!=oaLeaveMessage){
                fUserDetail = (FUserDetail) sysUserManager
                        .loadUserByUsername(mip.getUserid());
                // 处理数据
                OaLeaveMessagereply oaLeaveMessagereply = new OaLeaveMessagereply();
                
                oaLeaveMessagereply.setCreatertime(new Date(System.currentTimeMillis()));
                oaLeaveMessagereply.setCreater(mip.getUserid());
                
                oaLeaveMessagereply.setLno(mip.getLeaveid());
                oaLeaveMessagereply.setMessagecomment(mip.getContent());
                oaLeaveMessagereply.setIp(mip.getIp());
                oaLeaveMessagereply.setState("N");
                oaLeaveMessagereply.setLyno(oaLeaveMessagereplyManager.getNextKey());
                oaLeaveMessagereplyManager.saveObject(oaLeaveMessagereply);
                
                // 回复的回复增加一次主题回复数
                oaLeaveMessagereplyManager.addReplyNums(oaLeaveMessage.getDjid());
                
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));
          }else{
                info.setReturninfo(new CentitWSReturninfo("1", "留言回复不存在!"));  
          }

        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendBbsReplyLeaveMessage",operatorId, json, info,returnInfo, printStackTrace, "推送帖子留言回复信息。");
        return returnInfo;
    }
    /**
     *通过该接口获取议程列表信息
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param mid
     *            会议唯一ID
     * @param pagesize
     *            需要检索的记录条数
     * @return
     */
    @Override
    public String getAgendaList(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaMeetingmaterial> objList = null;
        List<OaMeetingmaterialMIP> objMIPList = new ArrayList<OaMeetingmaterialMIP>();
        try {
            OaMeetingmaterialMIP mip = (OaMeetingmaterialMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetingmaterialMIP.class);
            
         // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(mip.getDjId()))
                filterMap.put("djId", mip.getDjId());// 议程id
            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("createtime", DateUtil.convertToString(
                        mip.getStarttime(), datetimePattern1));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("motifytime", DateUtil.convertToString(
                        mip.getEndtime(), datetimePattern1));// 结束时间
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("meetingName", mip.getKeyword());// 搜索关键字
            filterMap.put("isuse", "F");
            filterMap.put("ORDER_BY", "meetingTime desc");// 只有创建时间可以精确到秒
            /** 处理查询条件end **/

            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            
            objList = oaMeetingmaterialManager.listObjects(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaMeetingmaterial oaMeetingmaterial : objList) {
                    OaMeetingmaterialMIP mipTemp = new OaMeetingmaterialMIP();
                    mipTemp.setDjId(JsonUtil.replaceNullString(oaMeetingmaterial
                            .getDjId()));
                    mipTemp.setMeetingName(JsonUtil
                            .replaceNullString(oaMeetingmaterial.getMeetingName()));
                    
                    mipTemp.setMeetingTime(JsonUtil
                            .replaceNullString(null == oaMeetingmaterial
                                    .getMeetingTime() ? null : String
                                    .valueOf(oaMeetingmaterial.getMeetingTime()
                                            .getTime())));
                    mipTemp.setCreatetime(JsonUtil
                            .replaceNullString(null == oaMeetingmaterial
                                    .getCreatetime() ? null : String
                                    .valueOf(oaMeetingmaterial.getCreatetime()
                                            .getTime())));
                    mipTemp.setMeetingAddress(JsonUtil
                            .replaceNullString(oaMeetingmaterial.getMeetingAddress()));
                    mipTemp.setMeetingAttendees(JsonUtil
                            .replaceNullString(oaMeetingmaterial.getMeetingAttendees()));
                   
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("meetingMateriallist",
                    JsonUtil.createSimpleFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMeetingMaterialList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取会议资料列表信息。");
        return returnInfo;
    }
    /**
     * 通过该接口获取议程详细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param djId
     *            会议材料ID
     * @return
     */
    @Override
    public String getAgendaDetail(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        try {

            OaMeetingmaterialApplyMIP mip = (OaMeetingmaterialApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetingmaterialApplyMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

            OaMeetingmaterial oaMeetingmaterial = oaMeetingmaterialManager.getObjectById(mip.getDjId());
            
            
            // 按接口要求封装数据
            if (null != oaMeetingmaterial) {
                //列表详细信息
                OaMeetingmaterialApplyMIP mipTemp = new OaMeetingmaterialApplyMIP();
                mipTemp.setDjId(JsonUtil.replaceNullString(oaMeetingmaterial.getDjId()));//议程主键
                mipTemp.setMeetingName(JsonUtil.replaceNullString(oaMeetingmaterial.getMeetingName()));//议程名称
                mipTemp.setMeetingAttendees(JsonUtil.replaceNullString(oaMeetingmaterial.getMeetingAttendees()));//议程参会人员
                mipTemp.setReportName(JsonUtil.replaceNullString(oaMeetingmaterial.getReportName()));//议程汇报人姓名
                
                mipTemp.setAttendUnitName(JsonUtil.replaceNullString(oaMeetingmaterial.getMeetingUnitnames()));//议程参会单位
                mipTemp.setForeignUserName(JsonUtil.replaceNullString(oaMeetingmaterial.getMeetingComein()));//外来人员
                mipTemp.setForeignUnitName(JsonUtil.replaceNullString(oaMeetingmaterial.getMeetingComeindeps()));//议程外来部门
                
                mipTemp.setReportName(JsonUtil.replaceNullString(oaMeetingmaterial.getReportName()));//议程汇报人姓名
                mipTemp.setOrderId(oaMeetingmaterial.getOrderId());
                
                
                
                
                
                
               /** 处理查询条件beg **/
                Map<String, Object> filterMapReplay = new HashMap<String, Object>();
                filterMapReplay.put("djId", oaMeetingmaterial.getDjId());
                //联合主键
                filterMapReplay.put("MEETING_ATTENDEE", mip.getUserid());
                /** 处理查询条件end **/
                List<OaMeetingmaterialinfos> oaMeetingmaterialinfosList=  oaMeetingmaterialinfosManager.listObjects(filterMapReplay);
                
                //回复明细
                if(null!=oaMeetingmaterialinfosList && oaMeetingmaterialinfosList.size()>0){
                    List<OaMeetingmaterialinfosMIP> oaMeetingmaterialinfosMIPList =  new ArrayList<OaMeetingmaterialinfosMIP>();
                    for(OaMeetingmaterialinfos infos  : oaMeetingmaterialinfosList ){
                           OaMeetingmaterialinfosMIP oaMeetingmaterialinfosMIP = new OaMeetingmaterialinfosMIP();
                           oaMeetingmaterialinfosMIP.setDjId(infos.getCid().getDjId());//对应的议程ID
                           oaMeetingmaterialinfosMIP.setUsercode(JsonUtil.replaceNullString(infos.getCid().getMeetingAttendee()));//用户Id
                           oaMeetingmaterialinfosMIP.setStuffId(infos.getCid().getStuffId());//附件ID
                           oaMeetingmaterialinfosMIP.setStuffPath(
                                   getFileDownloadUrl("DOCSTUFF",
                                           infos.getCid().getStuffId().toString()));//附件下载路径
                           oaMeetingmaterialinfosMIP.setStuffName(optStuffInfoManager.getStuffNameById(infos.getCid().getStuffId()));//附件名称
                           oaMeetingmaterialinfosMIP.setIsback(infos.getIsback());
                           oaMeetingmaterialinfosMIP.setRemark(infos.getRemark());
                           oaMeetingmaterialinfosMIPList.add(oaMeetingmaterialinfosMIP);
                    }
                    mipTemp.setMaterials(oaMeetingmaterialinfosMIPList);
                } 
                dataJson.put("meetingMaterialDetail", JsonUtil.createSimpleFormJsonString(mipTemp));
            }
            
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMeetingMaterialDetail", operatorId, json,info, returnInfo, printStackTrace, "  通过该接口获取会议材料明细信息。");
        return returnInfo;
    }
    /**
     * 通过该接口，提交阅后的会议材料附件，会议材料附件支持反复提交
     * 
     * @param userid
     *            用户唯一ID
     * @param djId
     *            会议材料ID
     * @param stuffId
     *            附件材料Id
     * @param stuff_path
     *           附件材料地址
     * @return
     */
    @Override
    public String sendMeetingMaterial(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        URL resourceUrl;
        InputStream content;
        try {

            OaMeetingmaterialMIP mip = (OaMeetingmaterialMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetingmaterialMIP.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());

           //验证数据是否存在
            Map<String, Object> filterMap= new HashMap<String, Object>();
            filterMap.put("djId", mip.getDjId());
            filterMap.put("MEETING_ATTENDEE", mip.getUserid());
            filterMap.put("stuffId", mip.getStuffId());
            List<OaMeetingmaterialinfos> oaMeetingmaterialinfosList=  oaMeetingmaterialinfosManager.listObjects(filterMap);
            
            
            // 按接口要求封装数据
            if (null != oaMeetingmaterialinfosList && oaMeetingmaterialinfosList.size()>0) {
                
                //保存会议资料信息
                OaMeetingmaterialinfos oaMeetingmaterialinfos= oaMeetingmaterialinfosList.get(0);
                oaMeetingmaterialinfos.setIsback("T");
                oaMeetingmaterialinfos.setBacktime(new Date());
                oaMeetingmaterialinfos.setRemark(mip.getRemark());
                oaMeetingmaterialinfosManager.saveObject(oaMeetingmaterialinfos);
                
                if(StringUtils.isNotBlank(mip.getQpfileurl())){
                    //更新附件
                    String PHONESERURL = mip.getQpfileurl();
                    resourceUrl = new URL(PHONESERURL);

                    //HttpURLConnection httpUrl = (HttpURLConnection) resourceUrl.openConnection();
                    //content = httpUrl.getInputStream();
                    
                    OptStuffInfo  optStuffInfo =optStuffInfoManager.getObjectById(mip.getStuffId());
                    
                    //路径规则，流程附件基本路径+办件类型+业务id
                    String dirPath = SysParametersUtils.getWorkflowAffixHome()  + optStuffInfo.getStuffpath();
                    File file = new File(dirPath);
                    String downloadPdfLayerPath = PDFUtil.downloadFiles(PHONESERURL, file.getParent());
                    File pdfLayer = new File(downloadPdfLayerPath);
                    List<String> layerPathArr = new ArrayList<String> ();
                    //判断下载后的签批附件是否是压缩包
                    if(pdfLayer.getName().endsWith(".zip")){
                        layerPathArr = PDFUtil.ectractQPHY(downloadPdfLayerPath, pdfLayer.getParent(),dirPath);//对下载后的图层解压处理
                    }else{
                        layerPathArr.add(downloadPdfLayerPath);
                    }
                    // 复制文件
                }
                
                
            }
            
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("sendMeetingMaterial", operatorId, json, info,returnInfo, printStackTrace, "  通过该接口，提交阅后的会议材料附件，会议材料附件支持反复提交。");
        return returnInfo;
    }
    
    
    
    /**
     * 区分一般人员和委领导
     * @param usercode
     * @return
     */
    private String getUserRankByUsercode(String usercode) {
        List<FUserunit> userUnits =sysUserManager.getSysUnitsByUserId(usercode);
        if(userUnits!=null && userUnits.size()>0){
            for(FUserunit info:userUnits){
                String userUnitRank=info.getUserrank();
                if("LD".equals(userUnitRank)){
                    userRank="LD";//主要领导
                    break;
                }else{
                    userRank=null;//其他人员
                }
            }
           
        }else{
            userRank=null;
        }
        return userRank;
    }
    
    
    
    
    /**
     * 通过该接口获取会议列表
     * 
     * @param userid
     *            用户唯一ID
     * 
     * @return
     */
    @Override
    public String getMeetingList(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果

        List<OaMeetingApply> objList = null;
        List<OaMeetingmaterialApplyMIP> objMIPList = new ArrayList<OaMeetingmaterialApplyMIP>();
        try {
            OaMeetingmaterialApplyMIP mip = (OaMeetingmaterialApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetingmaterialApplyMIP.class);
            
            // 用户合法性验证
            loadUserByUsername(mip.getUserid());
            /** 处理查询条件beg **/
            Map<String, Object> filterMap = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(mip.getUserid()))
            userRank=getUserRankByUsercode(mip.getUserid());
            if(StringUtils.isBlank(userRank)){//证明是一般人员
                filterMap.put("meeting_Attendeescodes",mip.getUserid());
            }else{//主要领导
                filterMap.put("attendLeaderCode",mip.getUserid());
            }
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("meetApplyName", mip.getKeyword());//会议名称关键字检索
            filterMap.put("ORDER_BY", "meetApplyTime desc");
            /** 处理查询条件end **/
            PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页
            
            objList =oaMeetingApplyManager.oaMeetingList(filterMap, pageDesc);

            // 按接口要求封装数据--使用空字符串代替空值
            if (null != objList && objList.size() > 0) {
                for (OaMeetingApply oaMeetingApply : objList) {
                    OaMeetingmaterialApplyMIP mipTemp = new OaMeetingmaterialApplyMIP();
                    //会议主键
                    mipTemp.setmId(JsonUtil.replaceNullString(oaMeetingApply
                            .getmId()));
                    //会议名称
                    mipTemp.setMeetApplyName(JsonUtil
                            .replaceNullString(oaMeetingApply.getMeetApplyName()));
                    //参会领导
                   mipTemp.setAttendLeaderName(JsonUtil
                            .replaceNullString(oaMeetingApply.getAttendLeaderName()));
                    //会议地点
                    mipTemp.setMeetApplyAddress(JsonUtil
                            .replaceNullString(oaMeetingApply.getMeetApplyAddress()));
                    //会议时间
                    mipTemp.setMeetApplyTime(JsonUtil
                            .replaceNullString(null == oaMeetingApply
                                    .getMeetApplytime() ? null : String
                                    .valueOf(oaMeetingApply.getMeetApplytime()
                                            .getTime())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("meetingApplylist",
                    JsonUtil.createSimpleFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMeetingApplyList", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取会议资料列表信息。");
        return returnInfo;
    }
    
    
    
    
    /**
     * 通过该接口获取会议详细信息
     * 
     * @param mId
     *           会议ID
     * 
     * @return
     */
    @Override
    public String getMeetingDetail(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        try {
            OaMeetingmaterialApplyMIP mip = (OaMeetingmaterialApplyMIP) JsonUtil.getObject4GsonString(
                    json, OaMeetingmaterialApplyMIP.class);
            
         // 用户合法性验证
            loadUserByUsername(mip.getUserid());

        
            OaMeetingApply  oaMeetingApply =oaMeetingApplyManager.getObjectById(mip.getmId());
            if (null != oaMeetingApply) {
                
                //获取会议详细信息
                OaMeetingmaterialApplyMIP mipTemp = new OaMeetingmaterialApplyMIP();
                mipTemp.setmId(JsonUtil.replaceNullString(oaMeetingApply.getMId()));//会议主键
                mipTemp.setMeetApplyName(JsonUtil.replaceNullString(oaMeetingApply.getMeetApplyName()));//会议名称
                mipTemp.setMeetApplyTime(JsonUtil.replaceNullString(null == oaMeetingApply
                        .getMeetApplytime() ? null : String.valueOf(oaMeetingApply
                        .getMeetApplytime().getTime())));//会议时间
                mipTemp.setMeetApplyAddress(JsonUtil.replaceNullString(oaMeetingApply.getMeetApplyAddress()));//会议地址
                mipTemp.setAttendUnitName(JsonUtil.replaceNullString(oaMeetingApply.getAttendUnitName()));//会议参会单位
                mipTemp.setAttendLeaderCode(JsonUtil.replaceNullString(oaMeetingApply.getAttendLeaderCode()));//参会领导
                mipTemp.setAttendLeaderName(JsonUtil.replaceNullString(oaMeetingApply.getAttendLeaderName()));//参会领导
                mipTemp.setForeignUserName(JsonUtil.replaceNullString(oaMeetingApply.getForeigUserName()));//会议外来人员
                mipTemp.setForeignUnitName(JsonUtil.replaceNullString(oaMeetingApply.getForeigUnitName()));//会议外来单位
                mipTemp.setRemark(JsonUtil.replaceNullString(oaMeetingApply.getMeetingRemark()));//会议备注
         
                //获取会议下面的议程列表信息
                /** 处理查询条件beg **/
                Map<String, Object> filterMap = new HashMap<String, Object>();
                if (StringUtils.isNotBlank(mip.getmId()))
                    filterMap.put("mId", mip.getmId());//会议名称
                /** 处理查询条件end **/
                PageDesc pageDesc = new PageDesc(1, StringUtils.isNotBlank(mip
                        .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                        : PAGESIZE);// 分页
                List<OaMeetingmaterial> objList = oaMeetingmaterialManager.listObjects(filterMap,pageDesc);
                // 按接口要求封装数据--使用空字符串代替空值
                if (null != objList && objList.size() > 0) {
                        List<OaAgendainfosMIP> objMIPList = new ArrayList<OaAgendainfosMIP>();
                        for (OaMeetingmaterial oaMeetingmaterial : objList) {
                            OaAgendainfosMIP mipTemp2 = new OaAgendainfosMIP();
                            mipTemp2.setDjId(JsonUtil.replaceNullString(oaMeetingmaterial
                                    .getDjId()));//议程ID
                            mipTemp2.setMeetingName(JsonUtil
                                    .replaceNullString(oaMeetingmaterial.getMeetingName()));//议程名称
                            mipTemp2.setMeetingAttendeescodes(JsonUtil
                                    .replaceNullString(oaMeetingmaterial.getMeetingAttendeesCodes()));
                            mipTemp2.setMeetingAttendees(JsonUtil
                                    .replaceNullString(oaMeetingmaterial.getMeetingAttendees()));//议程参加人员
                            mipTemp2.setReportName(JsonUtil
                                    .replaceNullString(oaMeetingmaterial.getReportName()));//议程汇报人
                            objMIPList.add(mipTemp2);
                        }
                        mipTemp.setAgendaInfos(objMIPList);
                    }
                dataJson.put("meetingDetail", JsonUtil.createSimpleFormJsonString(mipTemp));
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
            printStackTrace=this.printStackTrace(e);
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        this.savemiplog("getMeetingDetail", operatorId, json, info,returnInfo, printStackTrace, "通过该接口获取会议详细信息。");
        return returnInfo;
    }
    
    
    
    
    
    
    
    /**
     * 获取公文查询类型
     * 1.管理员查询所有
     * 2.办公室人员 查询 GW_NATURE_SUP
     * 3.其他人员 查询 GW_NATURE_DEP
     * 
     * @return
     */
    protected String getGWNature(FUserinfo user) {
        if (CommonOptCodeUtil.TOP_UNITCODE.equals(user.getPrimaryUnit())){
            return null;
        }
        else if (CommonOptCodeUtil.GW_NATURE_SUP_UNITCODE.equals(user.getPrimaryUnit())){
            return CommonOptCodeUtil.GW_NATURE_SUP;
        }else {
            return CommonOptCodeUtil.GW_NATURE_DEP;
        }
    } 
    
    
    
    /**
     * 通过该接口终端收发文附件上传
     * @param string 
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param title
     *            文档标题
     * @param doctype
     *            文档类型
     * @param docurl
     *            文档下载地址
     * @param parentnode
     *            上级节点(空字符串默认上传到根节点)
     * @return
     */

    @Override
    public boolean uploadFile(String messageitemguid, String qpfileurl) {
        
        log.info("传参:");
        boolean flag=true;
        CentitWebServiceInfo info = new CentitWebServiceInfo();

        FUserDetail fUserDetail = new FUserDetail();
        OptStuffInfo optStuffInfo = null;
        // 添加图层在PDF上
       // optStuffInfo = optStuffInfoManager.addLayerOnPDF(optStuffInfo);
           
         return flag;
    }

    
    /** 
    * 复制整个文件夹内容 
    * @param oldPath String 原文件路径 如： D:/oa.home/upload/pdf
    * @param newPath String 复制后路径 如： D:/oa.home/upload/workflowaffix 
    * @return boolean 
    */ 
    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }
    }
    
    
    /* get set 方法 beg */
    public void setOptIdeaInfoDao(OptIdeaInfoDao baseDao) {
        this.optIdeaInfoDao = baseDao;
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

    public void setDashboardDao(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    public void setSysuserDao(UserInfoDao userdao) {
        this.sysuserdao = userdao;
    }

    public void setSysunitDao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }

    public void setWsclientManager(WsclientManager wsclientManager) {
        this.wsclientManager = wsclientManager;
    }

    public void setOaScheduleManager(OaScheduleManager oaScheduleManager) {
        this.oaScheduleManager = oaScheduleManager;
    }

    public PublicinfoManager getPublicinfoManager() {
        return publicinfoManager;
    }

    public void setPublicinfoManager(PublicinfoManager publicinfoManager) {
        this.publicinfoManager = publicinfoManager;
    }

    public FileinfoFsManager getFileinfoFsManager() {
        return fileinfoFsManager;
    }

    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }

    public void setInnermsgRecipientManager(
            InnermsgRecipientManager innermsgRecipientManager) {
        this.innermsgRecipientManager = innermsgRecipientManager;
    }

    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }

    public void setOaInformationManager(
            OaInformationManager oaInformationManager) {
        this.oaInformationManager = oaInformationManager;
    }

    public void setOaUnitIncomedocManager(
            OaUnitIncomedocManager oaUnitIncomedocManager) {
        this.oaUnitIncomedocManager = oaUnitIncomedocManager;
    }

    public void setOaMeetinfoManager(OaMeetinfoManager oaMeetinfoManager) {
        this.oaMeetinfoManager = oaMeetinfoManager;
    }

    public VOptProcCollectionManager getvOptProcCollectionManager() {
        return vOptProcCollectionManager;
    }

    public void setvOptProcCollectionManager(
            VOptProcCollectionManager vOptProcCollectionManager) {
        this.vOptProcCollectionManager = vOptProcCollectionManager;
    }

    public void setOptProcCollectionManager(
            OptProcCollectionManager optProcCollectionManager) {
        this.optProcCollectionManager = optProcCollectionManager;
    }

    public void setOptProcLockManager(OptProcLockManager optProcLockManager) {
        this.optProcLockManager = optProcLockManager;
    }

    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }

    public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
        this.nodeInstanceDao = nodeInstanceDao;
    }

    public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
        this.oaMeetApplyManager = oaMeetApplyManager;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setFunctionDao(OptInfoDao functionDao) {
        this.functionDao = functionDao;
    }

    public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
        this.oaUserinfoManager = oaUserinfoManager;
    }

    public void setVoaUnitArchiveDispatchdocManager(
            VoaUnitArchiveDispatchdocManager voaUnitArchiveDispatchdocManager) {
        this.voaUnitArchiveDispatchdocManager = voaUnitArchiveDispatchdocManager;
    }

    public void setVoaUnitArchiveIncomedocManager(
            VoaUnitArchiveIncomedocManager voaUnitArchiveIncomedocManager) {
        this.voaUnitArchiveIncomedocManager = voaUnitArchiveIncomedocManager;
    }

    public void setOaArchiveManager(OaArchiveManager oaArchiveManager) {
        this.oaArchiveManager = oaArchiveManager;
    }

    public OptProcStuffInfoManager getOptProcStuffInfoManager() {
        return optProcStuffInfoManager;
    }

    public void setOptProcStuffInfoManager(
            OptProcStuffInfoManager optProcStuffInfoManager) {
        this.optProcStuffInfoManager = optProcStuffInfoManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }

    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }

    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
        this.optPdfInfoManager = optPdfInfoManager;
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public String getIsSubFlow() {
        return isSubFlow;
    }

    public void setIsSubFlow(String isSubFlow) {
        this.isSubFlow = isSubFlow;
    }

    public VuserTaskListOAManager getVuserTaskListOAManager() {
        return vuserTaskListOAManager;
    }

    public void setVuserTaskListOAManager(
            VuserTaskListOAManager vuserTaskListOAManager) {
        this.vuserTaskListOAManager = vuserTaskListOAManager;
    }

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public FlowDefine getFlowDefine() {
        return flowDefine;
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public FlowManager getFlowManager() {
        return flowManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public OptBaseInfoManager getOptBaseInfoManager() {
        return optBaseInfoManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public GeneralModuleParam getModuleParam() {
        return moduleParam;
    }

    public void setModuleParam(GeneralModuleParam moduleParam) {
        this.moduleParam = moduleParam;
    }

    public GeneralModuleParamManager getGeneralModuleParamManager() {
        return generalModuleParamManager;
    }

    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }

    public OptProcAttentionManager getOptProcAttentionManager() {
        return optProcAttentionManager;
    }

    public void setOptProcAttentionManager(
            OptProcAttentionManager optProcAttentionManager) {
        this.optProcAttentionManager = optProcAttentionManager;
    }

    public TemplateFileManager getTemplateFileManager() {
        return templateFileManager;
    }

    public void setTemplateFileManager(TemplateFileManager templateFileManager) {
        this.templateFileManager = templateFileManager;
    }

    public OptProcInfo getOptProcInfo() {
        return optProcInfo;
    }

    public void setOptProcInfo(OptProcInfo optProcInfo) {
        this.optProcInfo = optProcInfo;
    }

    public FUserDetail getfUserDetail() {
        return fUserDetail;
    }

    public void setfUserDetail(FUserDetail fUserDetail) {
        this.fUserDetail = fUserDetail;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UserInfoDao getSysuserdao() {
        return sysuserdao;
    }

    public void setSysuserdao(UserInfoDao sysuserdao) {
        this.sysuserdao = sysuserdao;
    }

    public UnitInfoDao getSysunitdao() {
        return sysunitdao;
    }

    public void setSysunitdao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }

    public JSONObject getDataJson() {
        return dataJson;
    }

    public void setDataJson(JSONObject dataJson) {
        this.dataJson = dataJson;
    }

    public CentitWebServiceInfo getInfo() {
        return info;
    }

    public void setInfo(CentitWebServiceInfo info) {
        this.info = info;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public OptIdeaInfoDao getOptIdeaInfoDao() {
        return optIdeaInfoDao;
    }

    public WfNodeInstanceDao getNodeInstanceDao() {
        return nodeInstanceDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public OptInfoDao getFunctionDao() {
        return functionDao;
    }

    public OptBaseInfoDao getOptBaseInfoDao() {
        return optBaseInfoDao;
    }

    public VOptBaseListManager getvOptBaseListManager() {
        return vOptBaseListManager;
    }

    public DashboardDao getDashboardDao() {
        return dashboardDao;
    }

    public OaScheduleManager getOaScheduleManager() {
        return oaScheduleManager;
    }

    public WsclientManager getWsclientManager() {
        return wsclientManager;
    }

    public InnermsgRecipientManager getInnermsgRecipientManager() {
        return innermsgRecipientManager;
    }

    public InnermsgManager getInnermsgManager() {
        return innermsgManager;
    }

    public OaInformationManager getOaInformationManager() {
        return oaInformationManager;
    }

    public OaUnitIncomedocManager getOaUnitIncomedocManager() {
        return oaUnitIncomedocManager;
    }

    public OaMeetinfoManager getOaMeetinfoManager() {
        return oaMeetinfoManager;
    }

    public OptProcCollectionManager getOptProcCollectionManager() {
        return optProcCollectionManager;
    }

    public OptProcLockManager getOptProcLockManager() {
        return optProcLockManager;
    }

    public OaMeetApplyManager getOaMeetApplyManager() {
        return oaMeetApplyManager;
    }

    public OaUserinfoManager getOaUserinfoManager() {
        return oaUserinfoManager;
    }

    public VoaUnitArchiveDispatchdocManager getVoaUnitArchiveDispatchdocManager() {
        return voaUnitArchiveDispatchdocManager;
    }

    public VoaUnitArchiveIncomedocManager getVoaUnitArchiveIncomedocManager() {
        return voaUnitArchiveIncomedocManager;
    }

    public OaArchiveManager getOaArchiveManager() {
        return oaArchiveManager;
    }

    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }

    public OptPdfInfoManager getOptPdfInfoManager() {
        return optPdfInfoManager;
    }

    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public WfActionTaskDao getActionTaskDao() {
        return actionTaskDao;
    }

    public OaRemindInformationManager getOaRemindInformationManager() {
        return oaRemindInformationManager;
    }

    public DispatchDocManager getDispatchDocManager() {
        return dispatchDocManager;
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }

    public IncomeDocManager getIncomeDocManager() {
        return incomeDocManager;
    }

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }

    public OaSmssendManager getOaSmssendManager() {
        return oaSmssendManager;
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

    public void setUserbizoptLogManager(
            UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    
    

    public OaCarApplyManager getOaCarApplyManager() {
        return oaCarApplyManager;
    }

    public void setOaCarApplyManager(OaCarApplyManager oaCarApplyManager) {
        this.oaCarApplyManager = oaCarApplyManager;
    }

    public OaInformationAttachmentManager getOaInformationAttachmentManager() {
        return oaInformationAttachmentManager;
    }

    public void setOaInformationAttachmentManager(
            OaInformationAttachmentManager oaInformationAttachmentManager) {
        this.oaInformationAttachmentManager = oaInformationAttachmentManager;
    }

    public OaFilemanagerManager getOaFilemanagerManager() {
        return oaFilemanagerManager;
    }

    public void setOaFilemanagerManager(
            OaFilemanagerManager oaFilemanagerManager) {
        this.oaFilemanagerManager = oaFilemanagerManager;
    }

    public void setMipLogManager(MipLogManager mipLogManager) {
        this.mipLogManager = mipLogManager;
    }

    public OaLeaveapplyManager getOaLeaveapplyManager() {
        return oaLeaveapplyManager;
    }

    public void setOaLeaveapplyManager(OaLeaveapplyManager oaLeaveapplyManager) {
        this.oaLeaveapplyManager = oaLeaveapplyManager;
    }

    public VPowerUserInfoManager getvPowerUserInfoManager() {
        return vPowerUserInfoManager;
    }

    public void setvPowerUserInfoManager(VPowerUserInfoManager vPowerUserInfoManager) {
        this.vPowerUserInfoManager = vPowerUserInfoManager;
    }

    public VsuppowerinusingManager getVsuppowerinusingManager() {
        return vsuppowerinusingManager;
    }

    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.vsuppowerinusingManager = vsuppowerinusingManager;
    }

    public OptApplyManager getOptApplyManager() {
        return optApplyManager;
    }

    public void setOptApplyManager(OptApplyManager optApplyManager) {
        this.optApplyManager = optApplyManager;
    }

    public OaBuffetapplyManager getOaBuffetapplyManager() {
        return oaBuffetapplyManager;
    }

    public void setOaBuffetapplyManager(OaBuffetapplyManager oaBuffetapplyManager) {
        this.oaBuffetapplyManager = oaBuffetapplyManager;
    }

    public OaBbsThemeManager getOaBbsThemeManager() {
        return oaBbsThemeManager;
    }

    public void setOaBbsThemeManager(OaBbsThemeManager oaBbsThemeManager) {
        this.oaBbsThemeManager = oaBbsThemeManager;
    }

    public OaLeaveMessageManager getOaLeaveMessageManager() {
        return oaLeaveMessageManager;
    }

    public void setOaLeaveMessageManager(OaLeaveMessageManager oaLeaveMessageManager) {
        this.oaLeaveMessageManager = oaLeaveMessageManager;
    }

    public OaLeaveMessagereplyManager getOaLeaveMessagereplyManager() {
        return oaLeaveMessagereplyManager;
    }

    public void setOaLeaveMessagereplyManager(
            OaLeaveMessagereplyManager oaLeaveMessagereplyManager) {
        this.oaLeaveMessagereplyManager = oaLeaveMessagereplyManager;
    }

    public OaBbsDiscussionManager getOaBbsDiscussionManager() {
        return oaBbsDiscussionManager;
    }

    public void setOaBbsDiscussionManager(
            OaBbsDiscussionManager oaBbsDiscussionManager) {
        this.oaBbsDiscussionManager = oaBbsDiscussionManager;
    }
    public OaOfficesuppliesManager getOaOfficesuppliesManager() {
        return oaOfficesuppliesManager;
    }

    public void setOaOfficesuppliesManager(
            OaOfficesuppliesManager oaOfficesuppliesManager) {
        this.oaOfficesuppliesManager = oaOfficesuppliesManager;
    }

    public OaNetapplicationManager getOaNetapplicationManager() {
        return oaNetapplicationManager;
    }

    public void setOaNetapplicationManager(
            OaNetapplicationManager oaNetapplicationManager) {
        this.oaNetapplicationManager = oaNetapplicationManager;
    }

    public OaLeavereportedManager getOaLeavereportedManager() {
        return oaLeavereportedManager;
    }

    public void setOaLeavereportedManager(
            OaLeavereportedManager oaLeavereportedManager) {
        this.oaLeavereportedManager = oaLeavereportedManager;
    }

    public OaMeetingmaterialManager getOaMeetingmaterialManager() {
        return oaMeetingmaterialManager;
    }

    public void setOaMeetingmaterialManager(
            OaMeetingmaterialManager oaMeetingmaterialManager) {
        this.oaMeetingmaterialManager = oaMeetingmaterialManager;
    }

    public OaMeetingmaterialinfosManager getOaMeetingmaterialinfosManager() {
        return oaMeetingmaterialinfosManager;
    }

    public void setOaMeetingmaterialinfosManager(
            OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager) {
        this.oaMeetingmaterialinfosManager = oaMeetingmaterialinfosManager;
    }
    
    
    

    public OaInfosummaryManager getOaInfosummaryManager() {
        return oaInfosummaryManager;
    }

    public void setOaInfosummaryManager(OaInfosummaryManager oaInfosummaryManager) {
        this.oaInfosummaryManager = oaInfosummaryManager;
    }

    public OptStuffInfoDao getOptStuffInfoDao() {
        return optStuffInfoDao;
    }

    public void setOptStuffInfoDao(OptStuffInfoDao optStuffInfoDao) {
        this.optStuffInfoDao = optStuffInfoDao;
    }

    public DictionaryManager getDictionaryManager() {
        return dictionaryManager;
    }

    public void setDictionaryManager(DictionaryManager dictionaryManager) {
        this.dictionaryManager = dictionaryManager;
    }
    public static String getDatetimePattern1() {
        return datetimePattern1;
    }

    public static void setDatetimePattern1(String datetimePattern1) {
        CentitWebServiceImpl.datetimePattern1 = datetimePattern1;
    }

    public OaMeetingApplyManager getOaMeetingApplyManager() {
        return oaMeetingApplyManager;
    }

    public void setOaMeetingApplyManager(
            OaMeetingApplyManager oaMeetingApplyManager) {
        this.oaMeetingApplyManager = oaMeetingApplyManager;
    }

    public OaTripPlanManager getOaTripPlanManager() {
        return oaTripPlanManager;
    }

    public void setOaTripPlanManager(OaTripPlanManager oaTripPlanManager) {
        this.oaTripPlanManager = oaTripPlanManager;
    }



    /* get set 方法 end */
}
