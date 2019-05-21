package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.efile.mgt.EfileManager;
import com.centit.efile.model.EfileStore;
import com.centit.oa.po.OaInformation;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.po.OaInfosummary;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.po.VOaInformation;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.oa.service.OaInformationManager;
import com.centit.oa.service.OaInfosummaryManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.oa.service.VOaInformationManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.CodeRepositoryUtilExtend;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;

public class OaInformationAction extends BaseEntityDwzAction<OaInformation> implements ServletResponseAware{
    private static final Log log = LogFactory.getLog(OaInformationAction.class);
    private static final long serialVersionUID = 1L;
    private OaInformationManager oaInformationMag;
    private File[] document;
    private File[] video;
    private String[] documentFileName;
    private String[] videoFileName;
    private String infoType;// 标记信息类型
    private String flag;
    private InputStream stuffStream;// 供下载使用
    private String filename;
    private UserbizoptLogManager userbizoptLogManager;
    private VOaInformationManager voaInformationManager;
    private List<VOaInformation> vobjList;
    private OaInformationAttachmentManager oaInformationAttachmentManager;
    private OaInfosummaryManager oaInfosummaryManager;
    private OaUserinfoManager oaUserinfoManager;
    private List<VOaInformation> oaActList;
    protected HttpServletResponse response;
    private Object json;
    
    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
    
    public OaUserinfoManager getOaUserinfoManager() {
        return oaUserinfoManager;
    }

    public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
        this.oaUserinfoManager = oaUserinfoManager;
    }

    public OaInfosummaryManager getOaInfosummaryManager() {
        return oaInfosummaryManager;
    }

    public void setOaInfosummaryManager(
            OaInfosummaryManager oaInfosummaryManager) {
        this.oaInfosummaryManager = oaInfosummaryManager;
    }

    public List<VOaInformation> getVobjList() {
        return vobjList;
    }

    public void setVobjList(List<VOaInformation> vobjList) {
        this.vobjList = vobjList;
    }

    public void setVoaInformationManager(
            VOaInformationManager voaInformationManager) {
        this.voaInformationManager = voaInformationManager;
    }

    public void setUserbizoptLogManager(
            UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }

    public OaInformationAttachmentManager getOaInformationAttachmentManager() {
        return oaInformationAttachmentManager;
    }

    public void setOaInformationAttachmentManager(
            OaInformationAttachmentManager oaInformationAttachmentManager) {
        this.oaInformationAttachmentManager = oaInformationAttachmentManager;
    }

    private List<FDatadictionary> infoTypesList;
    @SuppressWarnings("rawtypes")
    private List infoList;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public void setOaInformationManager(OaInformationManager basemgr) {
        oaInformationMag = basemgr;
        this.setBaseEntityManager(oaInformationMag);
    }

    private String show_type;

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    @Override
    public String view() {
        // 保存查看日志
        OaInformation o = oaInformationMag.getObject(object);
        FUserDetail user = (FUserDetail) getLoginUser();
        if ("1".equals(o.getState())) {// 只对已启用的保存

            UserbizoptLog u = new UserbizoptLog(new UserbizoptLogId(
                    o.getTitle(), o.getNo()));
            userbizoptLogManager.saveUserbizoptLog(u, user);
            request.setAttribute("show_type", request.getParameter("show_type"));
        }

        super.view();
        List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager
                .findDocAttachments(o.getNo());
        List<OaInformationAttachment> videoAttachments = oaInformationAttachmentManager
                .findVideoAttachments(o.getNo());

        request.setAttribute("docAttachments", docAttachments);
        request.setAttribute("videoAttachments", videoAttachments);

        List<OaInfosummary> listons = oaInfosummaryManager.findInfoAttend(
                object.getNo(), user.getUsercode());
        if (listons.size() > 0) {
            request.setAttribute("attend_act", 1);
        } else {
            if ("activity".equals(o.getInfoType())) {
                if (null != o.getValidDate()
                        && o.getValidDate().after(
                                new Date(System.currentTimeMillis()))) {
                    request.setAttribute("isOver", 1);
                } else {
                    request.setAttribute("isOver", 0);
                }
            }
        }
        if ("GGZY".equals(flag)) {
            return VIEW;
        } else {
            return "viewGGZY";
        }
    }

    @Override
    public String save() {
        OaInformation oaInformation = oaInformationMag.getObjectById(object
                .getNo());
        if (oaInformation == null) {
            oaInformation = new OaInformation();
        }

        oaInformationMag.copyObjectNotNullProperty(oaInformation, object);

        // 有效期在页面被清空后，值为null，若用copyObjectNotNullProperty，则会重新赋值，且值为数据库中的值
        if (null == object.getValidDate()) {
            oaInformation.setValidDate(null);
        }
        object = oaInformation;
        FUserDetail user = (FUserDetail) getLoginUser();
        object.setDepno(user.getPrimaryUnit());
        if (StringUtils.isBlank(object.getNo())) {
            object.setNo(oaInformationMag.getNextkey(""));
            object.setDepno(user.getPrimaryUnit());
            object.setCreatertime(DatetimeOpt.currentUtilDate());
            object.setCreater(user.getUsercode());
        } else {
            object.setLastmodifytime(DatetimeOpt.currentUtilDate());
        }

        saveFile();

        oaInformationMag.saveObject(object);
        return this.mainlist();
    }

    @Override
    public String edit() {
        List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager
                .findDocAttachments(object.getNo());
        List<OaInformationAttachment> videoAttachments = oaInformationAttachmentManager
                .findVideoAttachments(object.getNo());

        request.setAttribute("docAttachments", docAttachments);
        request.setAttribute("videoAttachments", videoAttachments);
        return super.edit();
    }

    @Override
    public String built() {
        infoType = request.getParameter("infoType");
        // 如果从首页进入，用dashboard标记
        if (StringUtils.isNotBlank(request.getParameter("dashboard"))) {
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        if(null==object.getReleaseDate()){
            object.setReleaseDate(new Date());
        }
        return EDIT;
    }

    // 附件下载--数据库获取
    public String downStuffInfo() throws IOException {
        OaInformation info = oaInformationMag.getObjectById(object.getNo());
        if (null == info) {
            return "download";
        }
        byte[] bt = null;
        String fn = info.getUploadFileName();
        bt = info.getUploadFile();
        try {
            if (bt != null) {
                setStuffStream(new ByteArrayInputStream(bt));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";

    }

    public void removeAttachment() {
        boolean f = false;
        try {
            Long attachmentId = Long.valueOf(request
                    .getParameter("attachmentId"));
            OaInformationAttachment attachment = oaInformationAttachmentManager
                    .getObjectById(attachmentId);
            oaInformationAttachmentManager.deleteObjectById(attachment.getId());
            // 删除磁盘上的文件
            String absolutePath = SysParametersUtils.getInfosHome()
                    + attachment.getPath();
            EfileManager.remove(absolutePath);
            f = true;
        } catch (Exception e) {
            log.error("删除附件异常:" + e.getMessage());
        }

        PrintWriter out = null;
        ;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(f);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();
        out.close();
    }

    // 附件下载--服务器端下载
    public String downLocalStuffInfo() throws IOException {

        OaInformationAttachment attachment = oaInformationAttachmentManager
                .getObjectById(Long.valueOf(request.getParameter("no")));
        if (null == attachment) {
            return "download";
        }
        String absolutePath = SysParametersUtils.getInfosHome()
                + attachment.getPath();
        File file = new File(absolutePath);
        try {
        InputStream stuffStream = FileUtils.openInputStream(file);

        
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
/*            saveError(e.getMessage());
            this.postAlertMessage("附件为空或为纸质文件", response);*/
            return null;
        }
        this.setFilename(new String(attachment.getFileName().getBytes("GBK"),
                "ISO8859-1"));

        return "download";
    }
    
    //判断文件是否存在,此方法为downLocalStuffInfo（）作铺垫
    public String fileNull() throws IOException{

        OaInformationAttachment attachment = oaInformationAttachmentManager
                .getObjectById(Long.valueOf(request.getParameter("no")));
        if (null == attachment) {
            return "download";
        }
        String absolutePath = SysParametersUtils.getInfosHome()
                + attachment.getPath();
        File file = new File(absolutePath);
        InputStream stuffStream = FileUtils.openInputStream(file);
        try {
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";

    }
    

    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 新闻 列表（首页） 关键字+标题
     */
    public String mainlistFromDashboard() {
        FUserDetail user = (FUserDetail) getLoginUser();
        String userCode = user.getUsercode();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        pageDesc = makeSearchPageDesc(request);
        paramMap.put("creater", userCode);
        paramMap.put("state", "1");
        vobjList = voaInformationManager.listUnReadInfos(paramMap, pageDesc);
        return "mainlist";
    }

    public String mainlist() {
        mark = "true";
        list();
        return "mainlist";
    }

    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            String userCode = user.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (StringUtils.isNotBlank(request.getParameter("s_infoType"))) {
                infoType = request.getParameter("s_infoType");
            } else if (StringUtils.isNotBlank(request.getParameter("infoType"))) {
                infoType = request.getParameter("infoType");
            }
            request.setAttribute("infoType", infoType);
            flag = request.getParameter("flag");
            filterMap.put("infoType", infoType);
            filterMap.put("state", "1");
            if (StringUtils.isBlank(flag)) {
                filterMap.remove("state");
                filterMap.put("NForbiddenState", "1");// 不查禁用的对象
            }
            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("NForbiddenState");

            }
            if ("GGZY".equals(flag)) {
                filterMap.put("NP_bettentime", true);// 查询有效期内
            }
            // filterMap.put("ORDER_BY", " releaseDate desc ");
            // 默认查询当前月份第一天到现在的记录
            if (StringUtils
                    .isNotBlank((String) filterMap.get("begReleaseDate"))
                    && StringUtils.isNotBlank((String) filterMap
                            .get("endReleaseDate"))) {
                /*
                 * filterMap.put("begReleaseDate",DateUtil.getCurrentMonthOfDay()
                 * ); filterMap.put("endReleaseDate",
                 * DateUtil.convertDateToString(new Date(), "yyyy-MM-dd"));
                 * request
                 * .setAttribute("s_begReleaseDate",DateUtil.getCurrentMonthOfDay
                 * () ); request.setAttribute("s_endReleaseDate",DatetimeOpt.
                 * convertDateToString(new Date(), "yyyy-MM-dd") );
                 */
                filterMap.put("endReleaseDate", DateUtil.convertDateToString(
                        DatetimeOpt.convertStringToDate(
                                (String) filterMap.get("endReleaseDate"),
                                "yyyy-MM-dd"), "yyyy-MM-dd"));
                request.setAttribute("endReleaseDate", DateUtil
                        .convertPrevDateToString(DatetimeOpt
                                .convertStringToDate((String) filterMap
                                        .get("endReleaseDate"), "yyyy-MM-dd"),
                                "yyyy-MM-dd"));
            }/*
              * else{
              * 
              * }
              */
            PageDesc pageDesc = null;
            if ("true".equals(mark)) {
                pageDesc = makeSearchPageDesc(request);
            } else {

                pageDesc = makePageDesc();
            }

            this.pageDesc = pageDesc;
            // objList = baseEntityManager.listObjects(filterMap, pageDesc);
            // 从视图查 by dk 2016-3-1
            vobjList = voaInformationManager.listObjects(filterMap, pageDesc);
            // 增加阅读状态
            if (vobjList != null && vobjList.size() > 0) {
                for (int i = 0; i < vobjList.size(); i++) {
                    VOaInformation oa = vobjList.get(i);
                    // =============办件添加是否阅读状态start=============
                    UserbizoptLog uboptlog = userbizoptLogManager.listObject(
                            oa.getNo(), userCode);
                    if (uboptlog != null) {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "T"));// 已读
                        // 判断是否从首页未读进入，如果是就把已读删除
                        // modify by lay 2017-01-16
                        // if(StringUtils.isNotBlank(request.getParameter("notread"))&&!"T".equals(oa.getIsTop())){
                        if (StringUtils.isNotBlank(request
                                .getParameter("notread"))) {
                            // end modify
                            vobjList.remove(oa);
                            i--;
                        }
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
                Collections.sort(vobjList, new Comparator<VOaInformation>() {

                    @Override
                    public int compare(VOaInformation o1, VOaInformation o2) {
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
            totalRows = pageDesc.getTotalRows();
            // 判断是否从首页未读进入，如果是就把已读删除
            if (StringUtils.isNotBlank(request.getParameter("notread"))) {
                request.setAttribute("notread", request.getParameter("notread"));
            }
            setbackSearchColumn(filterMap);
            // return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String publishlist() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            String userCode = user.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            request.setAttribute("infoType", "activity");
            flag = request.getParameter("flag");
            filterMap.put("infoType", "activity");
            filterMap.put("state", "1");
            if (StringUtils.isBlank(flag)) {
                filterMap.remove("state");
                filterMap.put("NForbiddenState", "1");// 不查禁用的对象
            }
            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("NForbiddenState");

            }
            if ("GGZY".equals(flag)) {
                filterMap.put("NP_bettentime", true);// 查询有效期内
            }
            // filterMap.put("ORDER_BY", " releaseDate desc ");
            // 默认查询当前月份第一天到现在的记录
            if (StringUtils
                    .isNotBlank((String) filterMap.get("begReleaseDate"))
                    && StringUtils.isNotBlank((String) filterMap
                            .get("endReleaseDate"))) {
                /*
                 * filterMap.put("begReleaseDate",DateUtil.getCurrentMonthOfDay()
                 * ); filterMap.put("endReleaseDate",
                 * DateUtil.convertDateToString(new Date(), "yyyy-MM-dd"));
                 * request
                 * .setAttribute("s_begReleaseDate",DateUtil.getCurrentMonthOfDay
                 * () ); request.setAttribute("s_endReleaseDate",DatetimeOpt.
                 * convertDateToString(new Date(), "yyyy-MM-dd") );
                 */
                filterMap.put("endReleaseDate", DateUtil.convertDateToString(
                        DatetimeOpt.convertStringToDate(
                                (String) filterMap.get("endReleaseDate"),
                                "yyyy-MM-dd"), "yyyy-MM-dd"));
                request.setAttribute("endReleaseDate", DateUtil
                        .convertPrevDateToString(DatetimeOpt
                                .convertStringToDate((String) filterMap
                                        .get("endReleaseDate"), "yyyy-MM-dd"),
                                "yyyy-MM-dd"));
            }/*
              * else{
              * 
              * }
              */
            PageDesc pageDesc = null;
            if ("true".equals(mark)) {
                pageDesc = makeSearchPageDesc(request);
            } else {

                pageDesc = makePageDesc();
            }

            this.pageDesc = pageDesc;
            // objList = baseEntityManager.listObjects(filterMap, pageDesc);
            // 从视图查 by dk 2016-3-1
            filterMap.put("creater", userCode);
            vobjList = voaInformationManager.listObjects(filterMap, pageDesc);
            // 增加阅读状态
            if (vobjList != null && vobjList.size() > 0) {
                for (int i = 0; i < vobjList.size(); i++) {
                    VOaInformation oa = vobjList.get(i);
                    // =============办件添加是否阅读状态start=============
                    UserbizoptLog uboptlog = userbizoptLogManager.listObject(
                            oa.getNo(), userCode);
                    if (uboptlog != null) {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "T"));// 已读
                        // 判断是否从首页未读进入，如果是就把已读删除
                        if (StringUtils.isNotBlank(request
                                .getParameter("notread"))
                                && !"T".equals(oa.getIsTop())) {
                            vobjList.remove(oa);
                            i--;
                        }
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
                Collections.sort(vobjList, new Comparator<VOaInformation>() {

                    @Override
                    public int compare(VOaInformation o1, VOaInformation o2) {
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
            totalRows = pageDesc.getTotalRows();
            // 判断是否从首页未读进入，如果是就把已读删除
            if (StringUtils.isNotBlank(request.getParameter("notread"))) {
                request.setAttribute("notread", request.getParameter("notread"));
            }
            setbackSearchColumn(filterMap);
            oaActList = vobjList;
            // return LIST;
            return "publishlist";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String attendlist() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            String userCode = user.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (StringUtils.isNotBlank(request.getParameter("s_infoType"))) {
                infoType = request.getParameter("s_infoType");
            } else if (StringUtils.isNotBlank(request.getParameter("infoType"))) {
                infoType = request.getParameter("infoType");
            }
            request.setAttribute("infoType", infoType);
            flag = request.getParameter("flag");
            filterMap.put("infoType", infoType);
            filterMap.put("state", "1");
            // 默认查询当前月份第一天到现在的记录
            if (StringUtils
                    .isNotBlank((String) filterMap.get("begReleaseDate"))
                    && StringUtils.isNotBlank((String) filterMap
                            .get("endReleaseDate"))) {
                filterMap.put("endReleaseDate", DateUtil.convertDateToString(
                        DatetimeOpt.convertStringToDate(
                                (String) filterMap.get("endReleaseDate"),
                                "yyyy-MM-dd"), "yyyy-MM-dd"));
                request.setAttribute("endReleaseDate", DateUtil
                        .convertPrevDateToString(DatetimeOpt
                                .convertStringToDate((String) filterMap
                                        .get("endReleaseDate"), "yyyy-MM-dd"),
                                "yyyy-MM-dd"));
            }
            PageDesc pageDesc = null;
            if ("true".equals(mark)) {
                pageDesc = makeSearchPageDesc(request);
            } else {

                pageDesc = makePageDesc();
            }

            this.pageDesc = pageDesc;
            filterMap.put("userCode", userCode);
            vobjList = voaInformationManager.listInformationOfAttandList(
                    filterMap, pageDesc);
            // 增加阅读状态
            if (vobjList != null && vobjList.size() > 0) {
                for (int i = 0; i < vobjList.size(); i++) {
                    VOaInformation oa = vobjList.get(i);
                    // =============办件添加是否阅读状态start=============
                    UserbizoptLog uboptlog = userbizoptLogManager.listObject(
                            oa.getNo(), userCode);
                    if (uboptlog != null) {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "T"));// 已读
                        // 判断是否从首页未读进入，如果是就把已读删除
                        if (StringUtils.isNotBlank(request
                                .getParameter("notread"))
                                && !"T".equals(oa.getIsTop())) {
                            vobjList.remove(oa);
                            i--;
                        }
                    } else {
                        oa.setReadstate(CodeRepositoryUtil.getValue(
                                "readstate", "F"));// 未读
                    }
                    // ================== end ===========
                }
                // 排序
                Collections.sort(vobjList, new Comparator<VOaInformation>() {

                    @Override
                    public int compare(VOaInformation o1, VOaInformation o2) {
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
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            return "attendlist";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 批量删除功能
     * 
     * @return
     */
    public String deleteIds() {
        // 批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
            for (String no : ids.split(",")) {
                // 存放作修改字段
                object.setNo(no);
                super.delete();
            }
        }
        return this.mainlist();
    }

    /**
     * 删除功能
     * 
     * @return
     */
    public String delete() {
        // 批量ids
        String ids = request.getParameter("no");
        if (StringUtils.isNotEmpty(ids)) {
            for (String no : ids.split(",")) {
                // 如果是活动
                if ("activity".equals(object.getInfoType())) {
                    Map<String, Object> filterMap = new HashMap<String, Object>();
                    filterMap.put("no", no);
                    List<OaInfosummary> summarys = oaInfosummaryManager
                            .listObjects(filterMap);
                    if (summarys != null) {
                        for (OaInfosummary summary : summarys) {
                            oaInfosummaryManager.deleteObject(summary);
                        }
                    }
                }
                // 存放作修改字段
                object.setNo(no);
                super.delete();

                List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager
                        .findDocAttachments(object.getNo());
                for (OaInformationAttachment fileItem : docAttachments) {
                    oaInformationAttachmentManager.deleteObjectById(fileItem
                            .getId());
                }
                List<OaInformationAttachment> videoAttachments = oaInformationAttachmentManager
                        .findVideoAttachments(object.getNo());
                for (OaInformationAttachment fileItem : videoAttachments) {
                    oaInformationAttachmentManager.deleteObjectById(fileItem
                            .getId());
                }

                // 删除磁盘上的文件
                String absolutePath = SysParametersUtils.getInfosHome()
                        + File.separator + no;
                EfileManager.remove(absolutePath);
            }
        }
        return this.mainlist();
    }

    /**
     * 学习园地新闻动态首页
     * 
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String oaInfomationMainpage() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        PageDesc pageDesc = makePageDesc();

        filterMap.put("infoType", "image");// 图片新闻
        filterMap.put("NP_imageUrl", true);// 图片新闻
        filterMap.put("state", "1");// 启用
        // 图片新闻列表
        objList = (List<OaInformation>) getSubList(
                baseEntityManager.listObjects(filterMap, pageDesc), 5);
        filterMap.remove("isImage");
        filterMap.remove("imagePath");

        // 获取数据字典
        infoTypesList = CodeRepositoryUtilExtend.getDictionary("infoType");

        // 按字典项依次获取列表--文字新闻
        if (null != infoTypesList) {
            infoList = new ArrayList();
            for (FDatadictionary f : infoTypesList) {
                filterMap.put("infoType", f.getDatacode());
                // 列表不为空时截取固定长度
                List<OaInformation> info = (List<OaInformation>) getSubList(
                        baseEntityManager.listObjects(filterMap, pageDesc), 9);
                infoList.add(info);

            }
        }
        return "mainpage";
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
        objList = (objList != null && objList.size() > dataLen ? objList
                .subList(0, dataLen - 1) : objList);
        return objList;
    }

    /**
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String showImage() throws Exception {
        OaInformation info = baseEntityManager.getObjectById(object.getNo());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            // 输出图片
            out.write(info.getUploadFile());
            out.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (null != out)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void saveFile() {
        try {
            String dirPath = SysParametersUtils.getInfosHome() + File.separator
                    + object.getNo();
            if (document != null) {
                for (int i = 0; i < document.length; i++) {
                    EfileStore efileStore = new EfileStore(document[i],
                            documentFileName[i], dirPath);
                    EfileManager.store(efileStore);

                    OaInformationAttachment attachment = new OaInformationAttachment();
                    attachment.setFileName(documentFileName[i]);
                    attachment.setFileSize(efileStore.getContentLength());
                    attachment.setType(OaInformationAttachment.TYPE_DOCUMENT);
                    attachment.setPath(efileStore.getAbsolutePath().replace(
                            SysParametersUtils.getInfosHome(), ""));
                    attachment.setRefId(object.getNo());
                    attachment.setId(oaInformationAttachmentManager
                            .getNextSequence());
                    oaInformationAttachmentManager.saveObject(attachment);
                }
            }

            if (video != null) {
                for (int i = 0; i < video.length; i++) {
                    EfileStore efileStore = new EfileStore(video[i],
                            videoFileName[i], dirPath);
                    EfileManager.store(efileStore);

                    OaInformationAttachment attachment = new OaInformationAttachment();
                    attachment.setFileName(videoFileName[i]);
                    attachment.setFileSize(efileStore.getContentLength());
                    attachment.setType(OaInformationAttachment.TYPE_VEDIO);
                    attachment.setPath(efileStore.getAbsolutePath().replace(
                            SysParametersUtils.getInfosHome(), ""));
                    attachment.setRefId(object.getNo());
                    attachment.setId(oaInformationAttachmentManager
                            .getNextSequence());
                    oaInformationAttachmentManager.saveObject(attachment);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 参与活动
     * 
     * @return
     */
    public String addAttendInfoSum() {
        try {
            String infoNo = request.getParameter("infono");
            OaInfosummary oains = new OaInfosummary();
            FUserDetail user = (FUserDetail) getLoginUser();
            List<OaInfosummary> listons = oaInfosummaryManager.findInfoAttend(
                    infoNo, user.getUsercode());
            OaUserinfo userSex = oaUserinfoManager.getObjectById(user
                    .getUsercode());

            if (listons.size() == 0) {
                oains.setId(oaInfosummaryManager.getNextkey(""));
                oains.setNo(infoNo);
                oains.setCreater(user.getUsercode());
                oains.setUnitcode(user.getPrimaryUnit());
                oains.setTelephone(userSex.getMobilephone());
                if (userSex != null) {
                    oains.setSex(userSex.getSex());
                }
                oains.setRemark("");
                oains.setCreatertime(DatetimeOpt.currentUtilDate());
                oaInfosummaryManager.saveObject(oains);
                request.setAttribute("canyu", "T");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "json";
    }

    public PageDesc makeSearchPageDesc(HttpServletRequest request) {

        String pagesize = request.getParameter("numPerPage");
        String pageno = request.getParameter("pageNum");

        String offset = request.getParameter("pager.offset");
        int pageSize = StringUtils.isNumeric(pagesize) ? Integer
                .parseInt(pagesize) : 15;
        int pageNo = Integer.parseInt(StringUtils.isNumeric(pageno) ? pageno
                : "1");

        if (StringUtils.isNotBlank(offset) && !StringUtils.isNotBlank(pageno)
                && StringUtils.isNumeric(offset)) {
            int offsetNum = Integer.parseInt(offset);

            pageNo = (offsetNum / pageSize) + 1;
        }

        PageDesc pageDesc = new PageDesc(pageNo, pageSize);

        return pageDesc;

    }

    public static Log getLog() {
        return log;
    }

    public File[] getDocument() {
        return document;
    }

    public void setDocument(File[] document) {
        this.document = document;
    }

    public File[] getVideo() {
        return video;
    }

    public void setVideo(File[] video) {
        this.video = video;
    }

    public String[] getDocumentFileName() {
        return documentFileName;
    }

    public void setDocumentFileName(String[] documentFileName) {
        this.documentFileName = documentFileName;
    }

    public String[] getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String[] videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<FDatadictionary> getInfoTypesList() {
        return infoTypesList;
    }

    public void setInfoTypesList(List<FDatadictionary> infoTypesList) {
        this.infoTypesList = infoTypesList;
    }

    public List getInfoList() {
        return infoList;
    }

    public void setInfoList(List infoList) {
        this.infoList = infoList;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
    /**
     * 弹出提示信息
     * 
     * @param msg
     * @param response
     */
    protected void postAlertMessage(String msg, HttpServletResponse response) {

        String alertCoding = "GBK";

        ServletOutputStream sos;
        String str = "<script language=\"JavaScript\""
                + " type=\"text/JavaScript\" charset=\"" + alertCoding + "\">"
                + "javascript:alert('" + msg + "');history.back(-1);"
                + " </script>";

        response.setContentType("text/html; charset=" + alertCoding);
        try {
            sos = response.getOutputStream();
            byte[] b = str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
