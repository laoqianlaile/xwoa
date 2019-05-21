package com.centit.webservice.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import com.centit.mip.po.OaCarApplyMIP;
import com.centit.mip.po.OaInformationMIP;
import com.centit.mip.po.OaInnermsgMIP;
import com.centit.mip.po.OaInnermsgMIP.AttachlistMIP;
import com.centit.mip.po.OaInnermsgMIP.AttachmentMIP;
import com.centit.mip.po.OaInnermsgMIP.ReceiverMIP;
import com.centit.mip.po.OaMeetApplyMIP;
import com.centit.mip.po.OaMeetinfoMIP;
import com.centit.mip.po.OaMeetinfoMIP.InnerMeetinfo;
import com.centit.mip.po.OaScheduleMIP;
import com.centit.mip.po.OaSmssendMip;
import com.centit.mip.po.OaUnitIncomedocMIP;
import com.centit.mip.po.OaUserinfoMIP;
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
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaFilemanager;
import com.centit.oa.po.OaInformation;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.po.VoaUnitArchiveDispatchdoc;
import com.centit.oa.po.VoaUnitArchiveIncomedoc;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.oa.service.OaArchiveManager;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaFilemanagerManager;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.oa.service.OaInformationManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaScheduleManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaUnitIncomedocManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.oa.service.VoaUnitArchiveDispatchdocManager;
import com.centit.oa.service.VoaUnitArchiveIncomedocManager;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.po.OptProcCollectionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptProcLock;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptProcCollection;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptProcLockManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.powerruntime.service.TemplateFileManager;
import com.centit.powerruntime.service.VOptProcCollectionManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.OptInfoDao;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.dao.UserRoleDao;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FVUseroptlist;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.client.WsclientManager;
import com.centit.webservice.pojo.CentitWSReturninfo;
import com.centit.webservice.pojo.CentitWebServiceInfo;
import com.centit.webservice.server.CentitWebService;
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
    
    private OaCarApplyManager oaCarApplyManager;//车辆申请
    
    private OaFilemanagerManager oaFilemanagerManager;//市总文件管理
    
    private UserbizoptLogManager userbizoptLogManager;//阅读记录
    private JSONObject dataJson = new JSONObject();// 接口查询数据结果
    private CentitWebServiceInfo info = new CentitWebServiceInfo();

    private static String datetimePattern = "yyyy-MM-dd HH:mm:ss.SSS";
    private static int PAGESIZE = 5;// 默认分页

    /**
     * 接口描述: OA系统提供各类待办事宜获取接口，通过该接口获取设定的需要提醒的待办事项： 新收发文 新通知公告 新领导日程 等
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            流程类型，例如：QB  签报,SQ  内部事权,FJX  发件箱,SJXWD  收件箱未读,DRAFTSBOX  草稿箱,DCDB  督办催办,FW  发文,CARSQ  车辆申请,HYSQ  会议申请,SW  收文,WDTX  我的提醒,HYSSQ  会议室申请。。。等(根据具体业务设置类型)
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
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("getOfficialCount出参:" + returnInfo);
        return returnInfo;
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
           /*objList = oaScheduleManager.listObjects(filterMap, pageDesc,
                    mip.getUserid());*/
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
                    mipTemp.setAddress(JsonUtil
                            .replaceNullString(oaSchedule.getAddress()));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                mip.setAddress(JsonUtil
                        .replaceNullString(oaSchedule.getAddress()));
                mip.setScheduleremark(JsonUtil.replaceNullString(oaSchedule
                        .getRemark()));
                dataJson.put("schedulel",
                        JsonUtil.createSimpleFormJsonString(mip));
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                    mipTemp.setAddress(JsonUtil
                            .replaceNullString(oaSchedule.getAddress()));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    /**
     * 通过该接口获取个人权限范围内的文档列表，支持条件查询，分页显示。[包括 个人文档 /部门文档 /资料库]
     * 
     * @param userid
     *            用户唯一ID
     * @param foldertype
     *            部门文档7 人文件夹 8 公共文件9 文件夹类型  6部门共享文档（资料库）
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
            
            
            String rootunitcode=mip.getUnitcode();//部门编号
            if("6".equals(mip.getFoldertype())){//参数验证
                if(StringUtils.isBlank(rootunitcode)){
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
                    pinfo = publicinfoManager.getUnitShareRootDirectory(rootunitcode,fUserDetail);//部门共享根目录
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
                    filterMap.put("unitcode", null==rootunitcode?fUserDetail.getPrimaryUnit():rootunitcode);//容错 默认查询本部门
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
                    mipTemp.setUsercode( publicinfo.getOwnercode());
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            String rootunitcode=mip.getUnitcode();//部门编号
            if("6".equals(mip.getFoldertype())){//参数验证
                if(StringUtils.isBlank(rootunitcode)){
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
                    pinfo = publicinfoManager.getUnitShareRootDirectory(rootunitcode,fUserDetail);//部门共享根目录
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
                root = publicinfoManager.authenticate4UploadUnitShareFile(infocode,rootunitcode,fUserDetail);//部门共享根目录
                FileinfoFs temp = processUploadedFile(content, mip.getTitle(), mip.getDoctype(), CommonCodeUtil.UNITFILESHARE_OPTCODE);
                publicinfo = publicinfoManager.saveUnitShareFile(temp, root, fUserDetail,rootunitcode);
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            String rootunitcode=mip.getUnitcode();//部门编号
            if("6".equals(mip.getFoldertype())){//参数验证
                if(StringUtils.isBlank(rootunitcode)){
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
                    pinfo = publicinfoManager.getUnitShareRootDirectory(rootunitcode,fUserDetail);//部门共享根目录
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
                folder = publicinfoManager.authenticate4AddUnitShareFolder(mip.getFoldername(), infocode, fUserDetail,rootunitcode);//部门共享根目录
            }
            else {
                throw new Exception("无效的业务类型。");
            }

            publicinfoManager.saveObject(folder);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

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
                }
                else  if ("6".equals(mip.getFoldertype())) { // 部门共享（资料库）
                    publicinfo = publicinfoManager
                            .authenticate4RenameUnitShareFiles(
                                    pinfo.getInfocode(),
                                    pinfo.getParentinfocode(),
                                    mip.getFoldername(), fUserDetail);//部门共享根目录
                }
                else {
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
                if ( "7".equals(mip.getFoldertype())) {
                    toDeleteFiles = publicinfoManager.authenticate4DeleteGRBGBMWDFiles(
                            mip.getId(), fUserDetail);
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
                }else {
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    
    /**
     * 通过该接口获取车辆申请列表
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

            OaMeetinfoMIP mip = (OaMeetinfoMIP) JsonUtil
                    .getObject4GsonString(json, OaMeetinfoMIP.class);

            loadUserByUsername(mip.getUserid());

            Map<String, Object> filterMap = new HashMap<String, Object>();
            
            if (StringUtils.isNotBlank(mip.getBoardroomid()))
                filterMap.put("meetingNo", mip.getBoardroomid());//会议室
            
            if (StringUtils.isNotBlank(mip.getStarttime()))
                filterMap.put("docpTimeBegin", DateUtil.convertToString(
                        mip.getStarttime(), "yyyy-MM-dd HH:mm:ss"));// 开始时间 时间戳
            if (StringUtils.isNotBlank(mip.getEndtime()))
                filterMap.put("docpTimeEnd", DateUtil.convertToString(
                        mip.getEndtime(), "yyyy-MM-dd HH:mm:ss"));// 结束时间
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
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if(StringUtils.isNotBlank(mip.getType())){
                if("0".equals(mip.getType())){//仅自己的
                    filterMap.put("myself", mip.getUserid());
                }
                else if("1".equals(mip.getType())){//自己+别人审核通过的
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
                    mipTemp.setMeetingid(JsonUtil.replaceNullString(oaMeetApply.getDjId()));
                    mipTemp.setApplicantid(JsonUtil.replaceNullString(oaMeetApply.getCreater()));
                    mipTemp.setApplicant(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("userCode", oaMeetApply.getCreater())));
                    mipTemp.setTitle(JsonUtil.replaceNullString(oaMeetApply
                            .getTitle()));
                    mipTemp.setStarttime(JsonUtil.replaceNullString(null == oaMeetApply
                            .getCpBegTime() ? null : String
                            .valueOf(oaMeetApply.getCpBegTime().getTime())));
                    mipTemp.setEndtime(JsonUtil.replaceNullString(null == oaMeetApply
                            .getCpEndTime() ? null : String
                            .valueOf(oaMeetApply.getCpEndTime().getTime())));
                    mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("meetState", oaMeetApply.getState())));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        /*List<OaMeetApply> freeApplyList = oaMeetApplyManager.getFreeApplylist(
                oaMeetinfo.getDjid(), datetime, null);
        
        if (null != freeApplyList && freeApplyList.size() > 1) {
            if (null != apply) {// 当前时间点不空闲，下一个可申请时间范围=当前时间后俩条数据的时间间隔
                freeBegDate = freeApplyList.get(0).getDoEndTime();
                freeEndDate = freeApplyList.get(1).getDoBegTime();
                Duration = String.valueOf(freeEndDate.getTime()
                        - freeBegDate.getTime());
            }
            if (null == apply) {// 当前时间点空闲，下一个可申请时间范围=当前时间后一条数据起始时间-当前时间前一条数据结束时间
                freeBegDate = datetime;
                freeEndDate = freeApplyList.get(1).getDoBegTime();
                Duration = String.valueOf(freeEndDate.getTime()
                        - freeBegDate.getTime());
            }

        }
        if (null != freeApplyList && freeApplyList.size() == 1) {
            if (datetime.getTime()
                    - freeApplyList.get(0).getDoEndTime().getTime() > 0) {// 前一条数据
                freeBegDate = datetime;
            } else {// 后一条数据
                freeBegDate = datetime;// 当前时间
                freeEndDate = freeApplyList.get(0).getDoBegTime();
                Duration = String.valueOf(freeEndDate.getTime()
                        - freeBegDate.getTime());
            }
        }*/ 
       

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
                    filterMap.put("meetingNo",mip.getBoardroomid());// 开始时间 时间戳
                if (StringUtils.isNotBlank(mip.getStarttime()))
                    filterMap.put("docpTimeBegin", DateUtil
                            .convertToString(mip.getStarttime(),
                                    "yyyy-MM-dd HH:mm:ss"));// 开始时间 时间戳
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
                objList = oaMeetApplyManager.getMeetApplyList(filterMap, pageDesc);
               
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
                                        CodeRepositoryUtil.getValue("usercode",
                                                meetinfo.getCreater()),
                                        meetinfo.getTitle(), JsonUtil
                                                .replaceNullString(begtime),
                                        JsonUtil.replaceNullString(endtime),
                                        meetinfo.getIsBasicUnit(),
                                        meetinfo.getIsStopCar(),
                                        JsonUtil.replaceNullString(meetinfo
                                                .getState())));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    @Override
    public String bookBoardroom(String json) {

        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            String flowCode = "000857";
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
                meetApply.setCpEndTime(DateUtil.convertToDate(
                        mip.getEndtime(), datetimePattern));
                meetApply.setTitle(mip.getTitle());
                meetApply.setIsBasicUnit(mip.getIsBasicUnit());
                meetApply.setIsStopCar(mip.getIsStopCar());
                
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
                        FUnitinfo fUnitinfo = sysUnitManager.getObjectById(fUserDetail
                                .getPrimaryUnit().trim());
                        if (fUnitinfo == null) {
                            fUnitinfo = new FUnitinfo();
                        }
                        optIdeaInfo.setUnitname(fUnitinfo.getUnitname());
                       
                        optIdeaInfo.setTransidea("会议申请");

                        OptProcInfo procInfo = new OptProcInfo();
                        long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
                        procInfo.setNodeInstId(nodeInstId);
                        procInfo.setDjId(meetApply
                                .getDjId());
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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

           /* if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
*/            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if (StringUtils.isNotBlank(mip.getType()))
                filterMap.put("infoType", mip.getType());

            filterMap.put("state", "1");// 启用
            //置顶排序
            filterMap.put("ORDER_BY", " (case when istop = 'T'  then  11   else  99  end),creatertime  desc");
            filterMap.put("NP_bettentime", true);//查询有效期内
            PageDesc pageDesc = new PageDesc(StringUtils.isNotBlank(mip
                    .getPagenum()) ? Integer.valueOf(mip.getPagenum())
                    : 1, StringUtils.isNotBlank(mip
                    .getPagesize()) ? Integer.valueOf(mip.getPagesize())
                    : PAGESIZE);// 分页

            /** 处理查询条件end **/

            objList = oaInformationManager.listObjects(filterMap, pageDesc);
            
            //添加阅读状态
            //===================================
            if(objList!=null&&objList.size()>0){
                for(int i=0;i<objList.size();i++){
                    OaInformation oa=objList.get(i);
                    //=============办件添加是否阅读状态start=============
                    UserbizoptLog uboptlog=userbizoptLogManager.listObject(oa.getNo(), mip.getUserid());
                    if(uboptlog!=null){
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "T"));//已读
                    }else{
                        oa.setReadstate(CodeRepositoryUtil.getValue("readstate", "F"));//未读
                    }
                   /* if("T".equals(oa.getIsTop())){//置顶
                        oa.setReadstate("置顶");//防止被再次排序
                    }*/
                    //==================       end      ===========
                }  
                //排序
                Collections.sort(objList, new Comparator<OaInformation>(){
                    
                    @Override
                    public int compare(OaInformation o1, OaInformation o2) {
                        String hits0="";
                        String hits1="";
                        if("T".equals(o1.getIsTop())){
                             hits0 = "置顶"+o1.getReadstate();
                        }else{
                             hits0 = o1.getReadstate(); 
                        }
                        if("T".equals(o2.getIsTop())){
                             hits1 = "置顶"+o2.getReadstate();
                        }else{
                            hits1 = o2.getReadstate(); 
                        }
                       // String hits1 = o2.getIsTop()+o2.getReadstate();
                        return hits1.compareTo(hits0);
                        
                    }
                    
                });
            }
            
            //====================================
            
            

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
                    mipTemp.setIsTop("T".equals(publicinfo
                            .getIsTop())?"置顶":"");
                    mipTemp.setReadstate(JsonUtil.replaceNullString(publicinfo.getReadstate()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("infomationList",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            
            if("1".equals(publicinfo.getState())){//只对已启用的保存
                FUserDetail user = new FUserDetail();
                user.setUsercode(mip.getUserid());
                user.setLoginip(getIpAddr(request));
                UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(publicinfo.getTitle(), publicinfo.getNo()));
                userbizoptLogManager.saveUserbizoptLog(u, user);
                }
            
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != publicinfo) {
                // 获取附件信息
                mip = new OaInformationMIP();
                mip.setId(JsonUtil.replaceNullString(publicinfo.getNo()));
                mip.setTitle(JsonUtil.replaceNullString(publicinfo.getTitle()));
                mip.setPublishtime(JsonUtil.replaceNullString(null == publicinfo
                        .getCreatertime() ? null : String.valueOf(publicinfo
                        .getCreatertime().getTime())));
                mip.setPublishdept(JsonUtil.replaceNullString(publicinfo
                        .getDepno()));
                /*
                 * mip.setFileurl(JsonUtil.replaceNullString(publicinfo
                 * .getImagePath()));
                 */
                mip.setPublisher(JsonUtil.replaceNullString(CodeRepositoryUtil
                        .getValue("usercode", publicinfo.getCreater())));
                String oldChar = XFireServletController.getRequest().getContextPath()+"/";
                String newChar = getFileDownloadUrl().replace("download", "");
                mip.setContent(JsonUtil.replaceNullString(null!=publicinfo.getBodyContent()?
                        publicinfo.getBodyContent().replace(oldChar, newChar):null));
                String imagepath = getFileDownloadUrl()
                        + "?pathType=relative&contentPath="
                        + publicinfo.getImagePath();
                mip.setFileurl(JsonUtil.replaceNullString(null!=publicinfo.getImagePath()?imagepath:null));
                mip.setFilename(JsonUtil.replaceNullString(publicinfo.getUploadFileName()));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }
    
    //获取用户IP
    public String getIpAddr(HttpServletRequest request) {   
        String ip = request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
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
                for(OaInformationAttachment attach : attachments){
                    OaInformationMIP obj = new OaInformationMIP();
                    obj.setAttachid(attach.getId().toString());
                    obj.setAttachtitle(attach.getFileName());
                    obj.setAttachsize(attach.getFileSize().toString());
                    obj.setAttachurl(getFileDownloadUrl("INFOSTUFF", attach.getId()
                            .toString()));
                    objMIPList.add(obj);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("attachments",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            filterMap.put("NP_bettentime", true);//查询有效期内
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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

            publicinfo = oaFilemanagerManager.getObjectById(mip
                    .getId());

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
                String oldChar = XFireServletController.getRequest().getContextPath()+"/";
                String newChar = getFileDownloadUrl().replace("download", "");
                mip.setContent(JsonUtil.replaceNullString(null!=publicinfo.getRemark()?
                        publicinfo.getRemark().replace(oldChar, newChar):null));
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                for(OaInformationAttachment attach : attachments){
                    OaInformationMIP obj = new OaInformationMIP();
                    obj.setAttachid(attach.getId().toString());
                    obj.setAttachtitle(attach.getFileName());
                    obj.setAttachsize(attach.getFileSize().toString());
                    obj.setAttachurl(getFileDownloadUrl("INFOSTUFF", attach.getId()
                            .toString()));
                    objMIPList.add(obj);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("attachments",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                objList = vuserTaskListOAManager.listObjects(filterMap,
                        pageDesc);
                setUserTaskList(objList, objMIPList,mip.getUserid());
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    // 检索待办还是C已办信息，W为待办
    private void setUserTaskList(List<VuserTaskListOA> objList,
            List<OfficialMIP> objMIPList,String usercode) {
        // 按接口要求封装数据--使用空字符串代替空值
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
                
              //=============办件添加是否阅读状态start=============
                UserbizoptLog uboptlog=userbizoptLogManager.listObject(task.getDjId(), usercode,task.getNodeInstId() );
                if(uboptlog!=null){
                    mip.setReadstate(CodeRepositoryUtil.getValue("readstate", "T"));//已读
                }else{
                    mip.setReadstate(CodeRepositoryUtil.getValue("readstate", "F"));//未读
                }
                objMIPList.add(mip);
            }
        }

    }

    // 检索待办还是已办信息，C为已办
    private void setOptTaskList(List<VOptBaseList> objList,
            List<OfficialMIP> objMIPList) {
        // 按接口要求封装数据--使用空字符串代替空值
        if (null != objList && objList.size() > 0) {
            for (VOptBaseList task : objList) {
                OfficialMIP mip = new OfficialMIP();
                mip.setDocid(JsonUtil.replaceNullString(task.getDjId()));
                mip.setTitle(JsonUtil.replaceNullString(task
                        .getTransaffairname()));
                mip.setCreateusername(CodeRepositoryUtil.getValue("usercode",
                        task.getCreater()));
                mip.setCreatedatetime(JsonUtil.replaceNullString(null == task
                        .getCreatedate() ? null : String.valueOf(task
                        .getCreatedate().getTime())));
                mip.setNodeinstid("");
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
         filterMap.put("ORDER_BY", "createdate desc");// 只有创建时间可以精确到秒 默认排序
        return filterMap;
    };

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
                if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                    OptPdfInfo optPdfInfo = optPdfInfoManager
                            .findNewestPdfInfo(djId);
                    if (optPdfInfo != null) {
                        mip.setDocurl(getFileDownloadUrl("PDF", optPdfInfo.getId()
                                .toString()));
                        mip.setFilePw(Cryptos.aesEncrypt(optPdfInfo.getSecretKey(),
                                PDFUtil.AES_USER_SECRET_KEY));
                        mip.setDocFileId(optPdfInfo.getId().toString());
                    } else {
                        mip.setDocurl("");
                        mip.setFilePw("");
                    }
                }else{
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                    if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                        OptPdfInfo optPdfInfo = optPdfInfoManager.findPdfInfoBy(
                                mip.getMessageitemguid(), ideaInfo.getNodeInstId(),ideaInfo.getUsercode());
    
                        if (optPdfInfo != null) {
                            mipTemp.setDocurl(optPdfInfo.getId() == null ? ""
                                    : getFileDownloadUrl("PDF", optPdfInfo.getId()
                                            .toString()));
                            mipTemp.setFilePw(optPdfInfo.getSecretKey() == null ? ""
                                    : Cryptos.aesEncrypt(optPdfInfo.getSecretKey(),
                                            PDFUtil.AES_USER_SECRET_KEY));
                        } else {
                            mipTemp.setDocurl("");
                            mipTemp.setFilePw("");
                        }
                    }else{
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            String  canSendmessg= centitWebCommonBizServiceImpl.canSendmessg(mip);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("canSendmessg",canSendmessg);
            dataJson.put("nextsteplist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);
        } catch (Exception e) {

            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
            
            if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                NodeInstance nodeInst = flowEngine.getNodeInstById(Long
                        .valueOf(mip.getNodeinstid()));
                FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                        .getNodeId());
                OptPdfInfo optPdfInfo = null;
                try{
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<接口中，办件djId："+mip.getMessageitemguid()+"pdf开始处理>>>>>>>>>>>>");
                    int bizType = optPdfInfoManager
                            .getBizTypeForPdf(mip.getMessageitemguid());
                    String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(mip.getMessageitemguid(), Long.valueOf(mip.getNodeinstid()));
                    File pdfStoreFile = new File(pdfStorePath);
                    
                    //重新生成pdf
                        String formHtmlUrl = "";
                        String contextPath = WebUtil.getContextPath(XFireServletController.getRequest());
                        if(bizType == 1){
                            formHtmlUrl =  optPdfInfoManager.getFWFormHtmlUrl(contextPath, mip.getUserid(),
                                    mip.getMessageitemguid(), nodeInst.getFlowInstId().toString());
                        }
                        if(bizType == 2){
                            formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath, mip.getUserid(), mip.getMessageitemguid());
                        }
                        
                        //签报
                        if(bizType == 3){
                             formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(contextPath, mip.getUserid(), mip.getMessageitemguid());
                        }
                        //车辆
                        if(bizType == 4){
                             formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(contextPath,  mip.getUserid(), mip.getMessageitemguid());
                        }
                        optPdfInfo = optPdfInfoManager.createPDF(mip.getMessageitemguid(), Long.valueOf(mip.getNodeinstid()), formHtmlUrl);
                    // 如果存在图层，需要将图层添加上去
                    if (StringUtils.isNotBlank(mip.getDocfileurl())) {
                        //如果没有重新生成PDF，那么就用最新的pdf信息
                        if(optPdfInfo == null){
                            optPdfInfo = optPdfInfoManager.findNewestPdfInfo(mip.getMessageitemguid());
                        }
                        //下载图层到新的目录
                        optPdfInfoManager.downloadLayer(mip.getDocfileurl(), pdfStoreFile.getParent());
                    }
                    
                    //添加图层在PDF上
                    optPdfInfo = optPdfInfoManager.addLayerOnPDF(optPdfInfo);
                    //将添加过图层的pdf转为swf
                    optPdfInfoManager.convertPdfToSwf(optPdfInfoManager
                                        .getPdf2SwfToolPath(XFireServletController
                                                .getRequest()), optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
                    //pdf 加密
                    optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
                    optPdfInfo.setBizType(String.valueOf(bizType));
                    optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
                    optPdfInfo.setUserCode(mip.getUserid());
                    optPdfInfo.setNodeInstId(Long.valueOf(mip.getNodeinstid()));
                    optPdfInfo.setNodeName(nodeInfo.getNodeName());
                    optPdfInfoManager.saveObject(optPdfInfo);
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<接口中，办件djId："+mip.getMessageitemguid()+"pdf成功处理结束>>>>>>>>>>>>");
                }catch(Exception e){
                    log.error("PDF处理异常:"+e.getMessage());
                }finally{
                    if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                        optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
                    }
                }
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            info.setBizdata(dataJson);

        } catch (Exception e) {

            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                    informip.setAttachsize(JsonUtil.replaceNullString(String
                            .valueOf(null==stuffInfo.getStuffcontent()?null:stuffInfo.getStuffcontent().length)));
                    informip.setAttachurl(getFileDownloadUrl("DOCSTUFF",
                            stuffInfo.getStuffid()) + "&storageLocation=db");
                    objMIPList.add(informip);
                }
                
               
            }
            dataJson.put("doclist",
                    JsonUtil.createOtherFormJsonString(objMIPList));
            info.setBizdata(dataJson);
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("doclist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                    OptPdfInfo optPdfInfo = optPdfInfoManager.findNewestPdfInfo(obj
                            .getNo());
                     if(optPdfInfo != null){
                        mipTemp.setDocurl(JsonUtil
                                .replaceNullString(null == optPdfInfo ? ""
                                        : getFileDownloadUrl("PDF", optPdfInfo.getId()
                                                .toString())));// 公文正文下载地址
        
                        mipTemp.setFilePw(JsonUtil
                                .replaceNullString(null == optPdfInfo ? "" : Cryptos
                                        .aesEncrypt(optPdfInfo.getSecretKey(),
                                                PDFUtil.AES_USER_SECRET_KEY)));
                     }else{
                         mipTemp.setDocurl("");
                         mipTemp.setFilePw("");
                     }
                }else{
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
                        filterMap.put("title", mip.getKeyword());// 搜索关键字
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

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
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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

            OaSmssendMip mip = (OaSmssendMip) JsonUtil
                    .getObject4GsonString(json, OaSmssendMip.class);

            // 用户合法性验证
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            if(StringUtils.isNotBlank(mip.getReceiverids())){
                String [] aa=mip.getReceiverids().split(",");
                for(String acceptpeocode:aa){
                    oaSmssendManager.saveMsgs(acceptpeocode, fUserDetail.getUsercode(), mip.getContent(), "R");
                }
                oaSmssendManager.executeSendMsg(); 
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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

            OaSmssendMip mip = (OaSmssendMip) JsonUtil
                    .getObject4GsonString(json, OaSmssendMip.class);

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

            List<OaSmssend> oaSmsList = oaSmssendManager.listObjects(filterMap, pageDesc);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != oaSmsList && oaSmsList.size() > 0) {
                for (OaSmssend sms : oaSmsList) {
                    OaSmssendMip mipTemp = new OaSmssendMip();
                    mipTemp.setSmsid(JsonUtil.replaceNullString(String.valueOf(sms.getSmsid())));
                    mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("sendMsgResState", sms.getRestoremessage())));
                    mipTemp.setContent(JsonUtil.replaceNullString(sms.getContent()));
                  
                    mipTemp.setSendtime(JsonUtil
                            .replaceNullString(null == sms
                                    .getSendtime() ? null : String
                                    .valueOf(sms.getSendtime()
                                            .getTime())));
                    mipTemp.setSendnum(JsonUtil.replaceNullString(sms.getSendnum()));
                    mipTemp.setSendpeo(JsonUtil.replaceNullString(sms.getSendpeo()));
                    mipTemp.setSendpeocode(JsonUtil.replaceNullString(sms.getSendpeocode()));
                    mipTemp.setAcceptnum(JsonUtil.replaceNullString(sms.getAcceptnum()));
                    mipTemp.setAcceptpeo(JsonUtil.replaceNullString(sms.getAcceptpeo()));
                    mipTemp.setAcceptpeocode(JsonUtil.replaceNullString(sms.getAcceptpeocode()));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("smslist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    @Override
    public String getSMSDetail(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();

        JSONObject dataJson = new JSONObject();// 接口查询数据结果


        try {

            OaSmssendMip mip = (OaSmssendMip) JsonUtil
                    .getObject4GsonString(json, OaSmssendMip.class);

            // 用户合法性验证
            loadUserByUsername(mip.getUserid());
          if(StringUtils.isBlank(mip.getSmsid())){
              throw new Exception("请输入需要查询信息！");
          }
            OaSmssend sms=oaSmssendManager.getObjectById(Long.valueOf(mip.getSmsid()));
            // 按接口要求封装数据
            if (null != sms) {
                OaSmssendMip mipTemp = new OaSmssendMip();
                mipTemp.setSmsid(JsonUtil.replaceNullString(String.valueOf(sms.getSmsid())));
                mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("sendMsgResState", sms.getState())));
                mipTemp.setContent(JsonUtil.replaceNullString(sms.getContent()));
              
                mipTemp.setSendtime(JsonUtil
                        .replaceNullString(null == sms
                                .getSendtime() ? null : String
                                .valueOf(sms.getSendtime()
                                        .getTime())));
                mipTemp.setSendnum(JsonUtil.replaceNullString(sms.getSendnum()));
                mipTemp.setSendpeo(JsonUtil.replaceNullString(sms.getSendpeo()));
                mipTemp.setSendpeocode(JsonUtil.replaceNullString(sms.getSendpeocode()));
                mipTemp.setAcceptnum(JsonUtil.replaceNullString(sms.getAcceptnum()));
                mipTemp.setAcceptpeo(JsonUtil.replaceNullString(sms.getAcceptpeo()));
                mipTemp.setAcceptpeocode(JsonUtil.replaceNullString(sms.getAcceptpeocode()));
                
                dataJson.put("sms",
                        JsonUtil.createSimpleFormJsonString(mipTemp));

            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
        }
        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
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

            OaSmssendMip mip = (OaSmssendMip) JsonUtil
                    .getObject4GsonString(json, OaSmssendMip.class);

            // 用户合法性验证
            fUserDetail = (FUserDetail) loadUserByUsername(mip.getUserid());

            if(StringUtils.isNotBlank(mip.getSmsid())){
                String [] aa=mip.getReceiverids().split(",");
                for(String smsid:aa){
                    OaSmssend sms=oaSmssendManager.getObjectById(smsid);
                    if(null!=sms){
                    //发送短信内容到短信接口表--更改短信发送状态
                        sms.setState(mip.getState());
                        oaSmssendManager.saveObject(sms);
                    }
                   
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
        
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }
    
    
    
    /**
     * 通过该接口获取车辆申请列表
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

            OaCarApplyMIP mip = (OaCarApplyMIP) JsonUtil
                    .getObject4GsonString(json, OaCarApplyMIP.class);

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
                filterMap.put("docpTimeBegin", DatetimeOpt
                        .convertDateToString(DatetimeOpt.currentUtilDate(),
                                "yyyy-MM-dd"));// 开始时间 时间戳
                filterMap.put("docpTimeEnd", DatetimeOpt
                        .convertDateToString(
                                DatetimeOpt.addDays(
                                        DatetimeOpt.currentUtilDate(), 1),
                                "yyyy-MM-dd"));// 结束时间
            }
            if (StringUtils.isNotBlank(mip.getCurrentdatetime()))
                filterMap.put("currentdatetime", DateUtil.convertToString(
                        mip.getCurrentdatetime(), datetimePattern));// 当前记录时间时间戳
            if (StringUtils.isNotBlank(mip.getKeyword()))
                filterMap.put("title", mip.getKeyword());// 搜索关键字
            if(StringUtils.isNotBlank(mip.getType())){
                if("0".equals(mip.getType())){//仅自己的
                    filterMap.put("creater", mip.getUserid());
                }
                else if("1".equals(mip.getType())){//自己+别人审核通过的
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
                    mipTemp.setDjId(JsonUtil.replaceNullString(carApplyInfo.getDjId()));
                    mipTemp.setApplicantid(JsonUtil.replaceNullString(carApplyInfo.getCreater()));
                    mipTemp.setApplicant(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("userCode", carApplyInfo.getCreater())));
                    mipTemp.setTitle(JsonUtil.replaceNullString(carApplyInfo
                            .getTransaffairname()));
                    mipTemp.setStarttime(JsonUtil.replaceNullString(null == carApplyInfo
                            .getCpBegtime() ? null : String
                            .valueOf(carApplyInfo.getCpBegtime().getTime())));
                    mipTemp.setEndtime(JsonUtil.replaceNullString(null == carApplyInfo
                            .getCpEndtime() ? null : String
                            .valueOf(carApplyInfo.getCpEndtime().getTime())));
                    mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("meetState", carApplyInfo.getState())));
                    objMIPList.add(mipTemp);
                }
            }

            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            dataJson.put("carapplylist",
                    JsonUtil.createSimpleListJsonString(objMIPList));
            info.setBizdata(dataJson);

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }
    
    /**
     * 通过该接口获取车辆申请详情
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
                mipTemp.setApplicantid(JsonUtil.replaceNullString(objInfo.getCreater()));
                mipTemp.setApplicant(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("userCode", objInfo.getCreater())));
                mipTemp.setTitle(JsonUtil.replaceNullString(objInfo
                        .getTransaffairname()));
                mipTemp.setStarttime(JsonUtil.replaceNullString(null == objInfo
                        .getCpBegtime() ? null : String
                        .valueOf(objInfo.getCpBegtime().getTime())));
                mipTemp.setEndtime(JsonUtil.replaceNullString(null == objInfo
                        .getCpEndtime()? null : String
                        .valueOf(objInfo.getCpEndtime().getTime())));
                mipTemp.setState(JsonUtil.replaceNullString(CodeRepositoryUtil.getValue("meetState", objInfo.getState())));
                mipTemp.setPersionsNum(JsonUtil.replaceNullString(null==objInfo.getMeetingPersionsNum()?"":String.valueOf(objInfo.getMeetingPersionsNum())));
                mipTemp.setTelephone(JsonUtil.replaceNullString(objInfo.getTelephone()));
                mipTemp.setRemark(JsonUtil.replaceNullString(objInfo.getRemark()));
                mipTemp.setPath(JsonUtil.replaceNullString(objInfo.getMeetingPersions()));
                mipTemp.setReqRemark(JsonUtil.replaceNullString(objInfo.getReqRemark()));
                mipTemp.setCarno(JsonUtil.replaceNullString(objInfo.getCarno()));
                mipTemp.setDriver(JsonUtil.replaceNullString(objInfo.getDriver()));
                mipTemp.setDrTelephone(JsonUtil.replaceNullString(objInfo.getDrTelephone()));
                dataJson.put("carapplyinfo",
                        JsonUtil.createSimpleFormJsonString(mipTemp));
            }
            info.setReturninfo(new CentitWSReturninfo("0", "成功"));
            info.setBizdata(dataJson);
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    
    }
    
    /**
     * 通过该接口发起用车申请
     * @param json
     * @return
     */
    @Override
    public String bookCarApply(String json) {
        log.info("传参:" + json);

        CentitWebServiceInfo info = new CentitWebServiceInfo();
        try {
            String flowCode = "000858";//车辆申请流程flowcode
            
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
                carApply.setMeetingPersionsNum(StringUtils.isEmpty(mip.getPersionsNum())?null:Long.valueOf(mip.getPersionsNum()));
                carApply.setTelephone(mip.getTelephone());
                carApply.setRemark(mip.getRemark());
                carApply.setReqRemark(mip.getReqRemark());
                carApply.setMeetingPersions(mip.getPath());//行车路线
                carApply.setPlanBegTime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                carApply.setPlanEndTime(DateUtil.convertToDate(
                        mip.getEndtime(), datetimePattern));
                carApply.setCpBegtime(DateUtil.convertToDate(
                        mip.getStarttime(), datetimePattern));
                carApply.setCpEndtime(DateUtil.convertToDate(
                        mip.getEndtime(), datetimePattern));
                // 生成申请编号：编号规则以HY打头+时间戳
                String no = "CAR-"
                        + new SimpleDateFormat("yyyyMMddHHmmss")
                                .format(new Date(System.currentTimeMillis()));

                if (StringUtils.isBlank(carApply.getApplyNo())) {
                    carApply.setApplyNo(no);
                }
                FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
                
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

                        optBaseInfo.setTransaffairname(carApply.getTransaffairname());
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
                    FUnitinfo fUnitinfo = sysUnitManager.getObjectById(fUserDetail
                            .getPrimaryUnit().trim());
                    if (fUnitinfo == null) {
                        fUnitinfo = new FUnitinfo();
                    }
                    optIdeaInfo.setUnitname(fUnitinfo.getUnitname());
                   
                    optIdeaInfo.setTransidea("车辆申请");

                    OptProcInfo procInfo = new OptProcInfo();
                    long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
                    procInfo.setNodeInstId(0L);
                    procInfo.setDjId(carApply
                            .getDjId());
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
                
                createPDF( carApply.getDjId() , fUserDetail.getUsercode());
                info.setReturninfo(new CentitWSReturninfo("0", "成功"));

            } else {
                info.setReturninfo(new CentitWSReturninfo("1", "请输入预定车辆时间!"));
            }

        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }
       
    
    /**
     * 通过该接口，取消登录用户已经预订的车辆申请
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
                if (mip.getUserid().equals(apply.getCreater())&& !"F".equals(apply.getBizstate())
                        && ("1".equals(apply.getState()) || "2"
                                .equals(apply.getState()))) {
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

                    String mesgTitle = "与您相关的一项用车申请[" + apply.getTransaffairname()
                            + "]已经被取消,请您及时关注。";
                    String mesgContent = apply.getTransaffairname()
                            + "于"
                            + DatetimeOpt.convertDatetimeToString(apply
                                    .getCpBegtime())
                            + "到"
                            + DatetimeOpt.convertDatetimeToString(apply
                                    .getCpEndtime()) + "的用车申请，现在已经被取消。";

                    // 2.输送邮件数据
                    this.sendInnermesg(userValue, mesgTitle, mesgContent,apply.getDjId());

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

            }else {
                info.setReturninfo(new CentitWSReturninfo("1",
                        "抱歉！您操作的数据不存在！"));
            }
        } catch (Exception e) {
            info = doWithException(e, info);
        }

        String returnInfo = JsonUtil.createJsonString(info);
        log.info("出参:" + returnInfo);
        return returnInfo;
    }

    
    
    
    /*
     * 获取会议相关人员
     * 1.与会领导
     * 2.参会单位(按岗位过滤)
     * 3.办件参与人员（不包括自己）
     * type domeeting  1,2
     *      cancle 1,2,3
     */
    private void getCarUservalues(String type, String djId, String usercode) {
        OaCarApply apply = oaCarApplyManager.getObjectById(djId);
       
        if(null!=usercode){
            if (StringUtils.isNotBlank(djId)) {
                userValue="";
               
                if("cancle".equals(type)){
                  //流程参与人员
                    List<OptIdeaInfo> optIdeaInfos=optProcInfoManager.listIdeaLogsByDjId(apply.getDjId());
                    if(optIdeaInfos!=null && optIdeaInfos.size()>0){    
                        for(int i=0;i<optIdeaInfos.size();i++){
                            if(!usercode.equals(optIdeaInfos.get(i).getUsercode())){
                          String value=optIdeaInfos.get(i).getUsercode()+",";
                          userValue+=value;
                            }
                        }
                    } 
                    
                    //   下一步办理人员
                    List<VUserTaskList> tasks=actionTaskDao.getTasksByFlowid(apply.getFlowInstId());
                    if(null!=tasks&&tasks.size()>0)
                    for (VUserTaskList task : tasks) {
                        if(!usercode.equals(task.getUserCode())){
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
     * 改写原有获取人员信息 区分大小写
     * 
     * @param loginname
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     */
    public FUserDetail loadUserByUsername(String loginname)
            throws UsernameNotFoundException, DataAccessException {

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
        String mobileTerminalAccessAddr = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
        HttpServletRequest request = XFireServletController.getRequest();
        if(mobileTerminalAccessAddr == null || "".equals(mobileTerminalAccessAddr)){
            return WebUtil.getContextPath(request)
                    + "download";
        }else{
            if(mobileTerminalAccessAddr.endsWith("/")){
                mobileTerminalAccessAddr = mobileTerminalAccessAddr.substring(0, mobileTerminalAccessAddr.length()-1);
            }
            return mobileTerminalAccessAddr + request.getContextPath()+"/download";
        }
       
    }
    
    /**
     * 用车申请审批不涉及手写签批合并图层
     * 在手机端发起流程并创建pdf
     */
     private  void createPDF(String djId ,String userCode){
         //生成pdf
         if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
             final String exePath = optPdfInfoManager.getPdf2SwfToolPath(XFireServletController.getRequest());
             int bizType = optPdfInfoManager
                     .getBizTypeForPdf(djId);
             String formHtmlUrl = "";
             String contextPath = WebUtil.getContextPath(XFireServletController.getRequest());
             if(bizType == 1){
                 formHtmlUrl =  optPdfInfoManager.getFWFormHtmlUrl(contextPath, userCode,
                         djId, "0");
             }
             if(bizType == 2){
                 formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath, userCode, djId);
             }
             
             //签报
             if(bizType == 3){
                  formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(contextPath, userCode, djId);
             }
             //车辆
             if(bizType == 4){
                  formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(contextPath,  userCode, djId);
             }
                     createPDF(djId, "创建", 0L,userCode,exePath,formHtmlUrl);
         }
     }
     /**
      * 新增是生成pdf
      * @param oaCarApply
      * @param nodeName
      * @param nodeInstId
      * @param userCode
      * @param exeToolpath
      * @param formHtmlUrl
      */
    private void createPDF(String djId,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        OptPdfInfo optPdfInfo = null;
        try {
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<djId:"+djId+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(djId, nodeInstId);
           File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(djId,nodeInstId,formHtmlUrl);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
            //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(djId)));
           optPdfInfo.setNodeName(nodeName);
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<申请djId:"+djId+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        } catch (Exception e) {
            log.error("生成PDF异常："+e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
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

    // ThreadLocal<CentitWebCommonBizServiceImpl>
    // centitWebCommonBizServiceImplThreadLocal = new
    // ThreadLocal<CentitWebCommonBizServiceImpl>();
    private CentitWebCommonBizServiceImpl getCentitWebCommonBizServiceImpl(
            ThreadLocal<CentitWebCommonBizServiceImpl> centitWebCommonBizServiceImplThreadLocal) {
        CentitWebCommonBizServiceImpl centitWebCommonBizServiceImpl = centitWebCommonBizServiceImplThreadLocal
                .get();
        if (null == centitWebCommonBizServiceImpl) {
            // 获取spring的context --自动提交节点
            WebApplicationContext webApplicationContext = ContextLoader
                    .getCurrentWebApplicationContext();
            ServletContext application = webApplicationContext
                    .getServletContext();
            centitWebCommonBizServiceImpl = (CentitWebCommonBizServiceImpl) webApplicationContext
                    .getBean("centitWebCommonBizServiceImpl");
            // centitWebCommonBizServiceImpl = new
            // CentitWebCommonBizServiceImpl();
            centitWebCommonBizServiceImplThreadLocal
                    .set(centitWebCommonBizServiceImpl);
        }
        return centitWebCommonBizServiceImpl;
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

    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
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

    public void setOaFilemanagerManager(OaFilemanagerManager oaFilemanagerManager) {
        this.oaFilemanagerManager = oaFilemanagerManager;
    }
    
    

    /* get set 方法 end */
}
