package com.centit.bbs.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.po.OaBbsAttention;
import com.centit.bbs.po.OaBbsDashboard;
import com.centit.bbs.po.OaBbsDiscussion;
import com.centit.bbs.po.OaBbsTheme;
import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.po.VBbsThemeReplay;
import com.centit.bbs.service.OaBbsAttentionManager;
import com.centit.bbs.service.OaBbsDashboardManager;
import com.centit.bbs.service.OaBbsDiscussionManager;
import com.centit.bbs.service.OaBbsThemeManager;
import com.centit.bbs.service.OaLeaveMessagereplyManager;
import com.centit.bbs.service.VBbsThemeUserManager;
import com.centit.bbs.util.IPUtil;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaLeaveMessage;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaLeaveMessageManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserManager;

/**
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2014-12-23
 * @version
 */
public class OaBbsThemeAction extends BaseEntityDwzAction<OaBbsTheme> {
    private static final Log log = LogFactory.getLog(OaBbsThemeAction.class);
    private static final long serialVersionUID = 1L;
    private OaBbsThemeManager oaBbsThemeMag;
    private OaBbsDiscussionManager oaBbsDiscussionManager;
    private OaBbsDashboardManager oaBbsDashboardManager;
    private OaBbsAttentionManager oaBbsAttentionManager;
    private OaUserinfoManager oaUserinfoManager;// 讨论区用户信息
    protected SysUserManager sysUserManager;

    private OaBbsDiscussion bbsDiscussion;
    private OaLeaveMessageManager oaLeaveMessageManager;// 留言回复
    
    private OaLeaveMessagereplyManager oaLeaveMessagereplyManager;
    
    private OaUserinfo oaUserinfo;// 讨论区用户信息
    private OaLeaveMessage oaLeaveMessage;
    private String s_layoutno;// 子模块流水号
    private String s_layoutcode;// 版面代码
    private String s_usercode;
    private List<OaLeaveMessage> replyList;// 留言 回复
    private List<OaBbsDashboard> oaBbsDashboards;
    private List<OaBbsDiscussion> oaBbsDiscussions;
    private List<OaBbsAttention> oaBbsAttentions;
    private List<OaLeaveMessage> oaLeaveMessages;
    private VBbsThemeUserManager vBbsThemeUserManager;
    private Long todayThemeNum;

    private Long bbsscNum;// 收藏数
    private Long bbsgzNum;// 关注数
    private Long bbszcNum;// 支持数
    private Long bbsfdNum;// 反对数

    public void setOaBbsThemeManager(OaBbsThemeManager basemgr) {
        oaBbsThemeMag = basemgr;
        this.setBaseEntityManager(oaBbsThemeMag);
    }

    /***
     * 讨论区模块信息>讨论区子模块信息>主题列表 追加模块，子模块是否在开放以及是否在开放时间内
     * 
     * @return
     */
    public String showThemeMainPage() {

        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("state", "T");// 状态 数据字典 T 公开 F 不公开 D 删除
        filterMap.put("approvalresults", "T");// 审核状态为通过的（不需要审核的子模块，保存时默认塞值T）
        filterMap.put("NP_isopen", true);

        // 页面导航栏
        if (StringUtils.isNotBlank(s_layoutno)) {
            bbsDiscussion = oaBbsDiscussionManager.getObjectById(s_layoutno);
        } else {
            bbsDiscussion = new OaBbsDiscussion();
        }

        // 当天主题数
        todayThemeNum = oaBbsThemeMag.getTodayThemeNum(s_layoutno);

        PageDesc pageDesc = makePageDesc();

        // 添加页面时间筛选及排序字段
        addFilterMap(filterMap);

        objList = oaBbsThemeMag.listObjects(filterMap, pageDesc);

        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;

        // 模块信息（公开+模块类型）+子模块信息
        Map<String, Object> filterMapDash = new HashMap<String, Object>();
        FUserunit dept = sysUserManager.getUserPrimaryUnit(this
                .getLoginUserCode());

        filterMapDash.put("openType", "T");// 公开
        filterMapDash.put("layouttypeFP", dept.getUnitcode());// 本部门+综合模块
        oaBbsDashboards = oaBbsDashboardManager.getDashboardList(filterMapDash);

        return "themeMainPage";

    }

    public String save() {
        // 页面导航栏
        if (StringUtils.isNotBlank(s_layoutno)) {
            bbsDiscussion = oaBbsDiscussionManager.getObjectById(s_layoutno);
            if (null != bbsDiscussion && "F".equals(bbsDiscussion.getIsneed())) {
                object.setApprovalresults("T");// 审核状态为通过的（不需要审核的子模块，保存时默认塞值T）
                object.setState("T");
            }else{
                object.setState("F");
            }
        }

        object.setThemeset("Y");// : 帖子设置一般 默认为一般；
        object.setCreater(getLoginUserCode());
        object.setCreatetime(new Date());
        object.setLastmodifytime(new Date());// 最后更新时间初始与创建时间一致
        //add by lay 2015-11-21
        object.setIp(IPUtil.getIRealIPAddr(request));
        //end add
        super.save();

        // 更新子模块主题数
        oaBbsDiscussionManager.updateSubjectnum(object.getLayoutno());

        return "gotoNext";

    }

    public String view() {
        // 更新帖子查看数
        oaBbsThemeMag.updatePostsViewNum(object.getThemeno());

        super.view();

        // 页面导航栏
        if (StringUtils.isNotBlank(s_layoutno)) {
            bbsDiscussion = oaBbsDiscussionManager.getObjectById(s_layoutno);
        } else {
            s_layoutno = object.getLayoutno();
            bbsDiscussion = oaBbsDiscussionManager.getObjectById(s_layoutno);
        }

        // 用户信息
        oaUserinfo = oaUserinfoManager.getObjectById(object.getCreater());// 获取当前用户信息
        
        request.setAttribute("loginUser", oaUserinfoManager.getObjectById(this.getLoginUserCode()));

        // 帖子回复
        replyList();

        if (null != replyList && replyList.size() > 0) {
            for (OaLeaveMessage oaLeaveMessage : replyList) {
                OaUserinfo levOaUserinfo = oaUserinfoManager
                        .getObjectById(oaLeaveMessage.getCreater());// 获取留言用户信息
                oaLeaveMessage.setOauserinfo(levOaUserinfo);// 获取人员信息
                if (null != levOaUserinfo
                        && null != levOaUserinfo.getPictureim()) {
                    oaLeaveMessage.setSign("T");// 扩展标记字段：在谈论版留言中用作标记留言人是否有上传头像
                }
                
                Map<String, Object> filterMap = new HashMap<String, Object>();
                
                filterMap.put("lno", oaLeaveMessage.getNo());
                List<OaLeaveMessagereply> infos = new ArrayList<OaLeaveMessagereply>();
                infos = oaLeaveMessagereplyManager.listObjects(filterMap);
                if(infos!=null&&infos.size()>0){
                    oaLeaveMessage.setOaLeaveMessagereplys(infos);
                }
            }
        }
        // 帖子人员头像是否存在标志

        // attention 统计
        // OaBbsAttention oaBbsAttention =new OaBbsAttention(new
        // OaBbsAttentionId(object.getThemeno(),getLoginUserCode(),null) );

        // oaBbsAttentions= oaBbsAttentionManager.getLaytypeNum(oaBbsAttention
        // );
        return VIEW;

    }

    /*
     * 帖子回复列表
     */
    public void replyList() {

        // infoType;留言类型djid;业务流水号
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());
//        filterMap.put("state", "T");// 审核通过的留言 --在页面提示留言被删除
        filterMap.put("djid", object.getThemeno());// 业务流水号
        filterMap.put("infoType", "BBS");// 留言类型
        if (null == filterMap.get("ORDER_BY")) {
            filterMap.put("ORDER_BY", "creatertime asc");// 默认排序顺序
        }

        PageDesc pageDesc = makeSearchPageDesc(request);
        replyList = oaLeaveMessageManager.listObjects(filterMap, pageDesc);
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
    }

    /**
     * 保存回帖
     */
    public String saveReply() {
        FUserDetail user = (FUserDetail) getLoginUser();
        // 留言
        object.getOaLeaveMessage().setDjid(object.getThemeno());
        object.getOaLeaveMessage().setCreater(user.getUsercode());// 留言人
        object.getOaLeaveMessage().setCreatertime(new Date());// 留言日期
        object.getOaLeaveMessage().setState("T");// 审核状态 默认通过（无审核功能）
        object.getOaLeaveMessage().setInfoType("BBS");// info_type
        //add by lay2015-11-21 添加ip地址
        object.getOaLeaveMessage().setIp(IPUtil.getIRealIPAddr(request));
        //end add
        oaLeaveMessageManager.saveObject(object.getOaLeaveMessage());// 保存留言

        // 更新帖子回复数
        oaBbsThemeMag.updatePostsNum(object.getThemeno());
        OaBbsTheme oaBbsTheme = oaBbsThemeMag
                .getObjectById(object.getThemeno());
        // 更新讨论区子模块帖子回复数
        oaBbsDiscussionManager.updatePostsNum(oaBbsTheme.getLayoutno());

        return view();
    }

    /**
     * 根据页面时间查询条件，排序字段完善filterMap
     * 
     * @param filterMap
     */
    private void addFilterMap(Map<String, Object> filterMap) {
        String timeType = filterMap.get("timeType") == null ? null
                : (String) filterMap.get("timeType");
        String orderType = filterMap.get("orderType") == null ? null
                : (String) filterMap.get("orderType");
        if (StringUtils.isNotBlank(timeType)) {
            Date begTime = new Date();// 起始时间
            Date endTime = new Date();// 结束时间
            // 1当天2昨天3一周4一个月5三个月
            if ("1".equals(timeType)) {
                begTime = DatetimeOpt.addDays(new Date(), 0);
                endTime = DatetimeOpt.addDays(new Date(), 1);
            } else if ("2".equals(timeType)) {
                begTime = DatetimeOpt.addDays(new Date(), -1);
                endTime = DatetimeOpt.addDays(new Date(), 0);
            } else if ("3".equals(timeType)) {
                int week = DatetimeOpt.getDayOfWeek(new Date());
                begTime = DatetimeOpt.addDays(new Date(), -week);
                endTime = DatetimeOpt.addDays(new Date(), 1);
            } else if ("4".equals(timeType)) {

                begTime = DatetimeOpt.addMonths(new Date(), -1);
                endTime = DatetimeOpt.addDays(new Date(), 1);
            } else if ("5".equals(timeType)) {

                begTime = DatetimeOpt.addMonths(new Date(), -3);
                endTime = DatetimeOpt.addDays(new Date(), 1);
            }
            filterMap.put("begTime", DatetimeOpt.convertDateToString(begTime));
            filterMap.put("endTime", DatetimeOpt.convertDateToString(endTime));
        }
        // 1默认排序2发帖时间3回复/查看4.查看5.最后发表6.热门
        if (StringUtils.isNotBlank(orderType)) {
            String orderBy = "";
            String selfOrderBy = "DECODE(themeset,'G','1','T','2','J','3','Y','4',themeset)";
            if ("2".equals(orderType)) {
                orderBy = "  createtime desc  ";
            } else if ("3".equals(orderType)) {
                orderBy = "  postsnum desc, postsviewnum desc";
            } else if ("4".equals(orderType)) {
                orderBy = "   postsviewnum desc";
            } else if ("5".equals(orderType)) {
                orderBy = "  lastmodifytime desc";
            } else if ("6".equals(orderType)) {
                // 热门的判定标准是什么---暂取关注数
                orderBy = "   attentionum desc";
            }
            orderBy = orderBy == "" ? (selfOrderBy)
                    : (orderBy + "," + selfOrderBy);
            filterMap.put("ORDER_BY", orderBy);
        }

    }

    public String edit() {

        // 不确定子模块发帖
        oaBbsDashboards = oaBbsDashboardManager.listObjects();
        String layoutcode = request.getParameter("s_layoutcode");
        OaBbsDashboard oaBbsDashboard = oaBbsDashboardManager
                .getObjectById(layoutcode);
        if (StringUtils.isNotEmpty(layoutcode)) {
            // 子模块下拉框
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("layoutcode", layoutcode);
            oaBbsDiscussions = oaBbsDiscussionManager.listObjects(filterMap);

            // 设置讨论区模块开放状态
            Date now = new Date();
            if (null != oaBbsDiscussions) {
                for (OaBbsDiscussion d : oaBbsDiscussions) {
                    if (oaBbsDashboardManager.isShowTime(d.getStarttime(), now,
                            d.getEndtime(), 0, 12)
                            || oaBbsDashboardManager.isShowTime(
                                    d.getStarttimepm(), now, d.getEndtimepm(),
                                    12, 24)) // 是否设置开发时间+判断上午下午
                        d.setIsShowTime("T");
                }
            }
        }

        // 首页快捷发帖---帖子列表页面发帖
        String layoutno = request.getParameter("s_layoutno");
        bbsDiscussion = oaBbsDiscussionManager.getObjectById(layoutno);
        super.edit();

        return EDIT;

    }

    private List<Map<String, String>> json;

    /*
     * * 动态获取讨论版模块
     * 
     * @SuppressWarnings("unchecked") public String option() {
     * 
     * String layoutcode = request.getParameter("s_layoutcode");
     * 
     * json = new ArrayList<Map<String, String>>(); if
     * (StringUtils.isBlank(layoutcode)) {
     * 
     * return "options"; } OaBbsDashboard oaBbsDashboard =
     * oaBbsDashboardManager.getObjectById(layoutcode); Map<String, String> temp
     * = new HashMap<String, String>(); temp.put("key", ""); temp.put("value",
     * "--请选择--");
     * 
     * 
     * Map<String, Object> filterMap = new HashMap<String, Object>();
     * filterMap.put("layoutcode", layoutcode);
     * 
     * oaBbsDiscussions=oaBbsDiscussionManager.listObjects(filterMap);
     * 
     * json.add(temp); for (OaBbsDiscussion oaBbsDiscussion :
     * oaBbsDashboard.getOaBbsDiscussions()) {
     * 
     * Map<String, String> op = new HashMap<String, String>();
     * 
     * op.put("key", oaBbsDiscussion.getLayoutno()); op.put("value",
     * oaBbsDiscussion.getSublayouttitle());
     * 
     * json.add(op);
     * 
     * } return "options"; }
     */

    public String list() {

        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        // 列出未删除的帖子
        if (null != filterMap) {
            filterMap.put("notDeleted", "D");
        }

        PageDesc pageDesc = makePageDesc();

        objList = oaBbsThemeMag.listObjects(filterMap, pageDesc);

        totalRows = pageDesc.getTotalRows();
        this.pageDesc = pageDesc;

        resetPageParam(paramMap);
        return LIST;
    }

    /**
     * 后台查看帖子
     * 
     * @return
     */
    public String themeView() {

        OaBbsTheme o = oaBbsThemeMag.getObject(object);
        if (object == null) {

            return LIST;
        }
        if (o != null)
            oaBbsThemeMag.copyObject(object, o);

        return "themeView";
    }

    public String themeAdd() {

        return "themeAdd";
    }

    /**
     * 后台编辑帖子
     * 
     * @return
     */
    public String themeEdit() {

        OaBbsTheme dbObject = oaBbsThemeMag.getObject(object);
        if (null != dbObject) {
            oaBbsThemeMag.copyObject(object, dbObject);
        }

        request.setAttribute("edit", request.getParameter("edit"));
        return "themeEdit";
    }

    /**
     * 后台保存帖子
     * 
     * @return
     */
    public String themeSave() {

        OaBbsTheme dbObject = oaBbsThemeMag.getObject(object);

        if (null != dbObject) {
            oaBbsThemeMag.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
        }

        if (StringUtils.isBlank(object.getCreater())) {
            object.setCreater(getLoginUserCode());
            object.setCreatetime(new Date());
        }

        // 设置审核人和审核时间
        if (StringUtils.isNotBlank(object.getApprovalresults())) {
            object.setApproval(getLoginUserCode());
            object.setApprovaltime(new Date());
        }
        object.setIp(IPUtil.getIRealIPAddr(request));
        oaBbsThemeMag.saveObject(object);
        return "themeSuccess";
    }

    /**
     * 删除帖子（将帖子状态设置为“已删除”）
     * 
     * @return
     */
    public String themeDelete() {

        OaBbsTheme dbObject = oaBbsThemeMag.getObject(object);

        if (null != dbObject) {
            object = dbObject;
            // 将状态设置为“已删除”
            object.setState("D");
        }

        oaBbsThemeMag.saveObject(object);

        return "themeSuccess";
    }
    /**
     * 启用
     * @return
     */
    public String setup(){
        OaBbsTheme o = oaBbsThemeMag.getObject(object);
        if (object == null) {
            return LIST;
        }
        if (o != null) {
            o.setState("F");
            oaBbsThemeMag.saveObject(o);
        }
        return SUCCESS;
    }
    /**
     * 更新帖子状态，审核结果字段
     * 
     * @return
     */
    public String themeUpdate() {

        OaBbsTheme dbObject = oaBbsThemeMag.getObject(object);

        if (null != dbObject) {
            oaBbsThemeMag.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
            // 设置审核人和审核时间
            if (StringUtils.isNotBlank(object.getApprovalresults())) {
                object.setApproval(getLoginUserCode());
                object.setApprovaltime(new Date());
            }
        }

        oaBbsThemeMag.saveObject(object);

        return "themeSuccess";
    }

    /**
     * 批量操作 更新帖子状态，审核结果字段
     * 
     * @return
     */
    public String themeUpdates() {

        String ids = request.getParameter("layoutnos");
        String state = object.getState();
        String approvalresults = object.getApprovalresults();
        List<String> pk = Arrays.asList(ids.split(","));

        if (StringUtils.isNotEmpty(ids)) {
            
            for (String themeno : ids.split(",")) {
                //hibernate持久化
                OaBbsTheme oaBbsTheme=new OaBbsTheme();
                oaBbsTheme.setThemeno(themeno);
                // 批量公开不公开删除
                if (StringUtils.isNotBlank(state)) {
                    oaBbsTheme.setState(state);
                    
                }
                // 批量通过不通过
                if (StringUtils.isNotBlank(approvalresults)) {
                    oaBbsTheme.setApprovalresults(approvalresults);
                    oaBbsTheme.setApproval(getLoginUserCode());
                    oaBbsTheme.setApprovaltime(new Date());
                }
                
                OaBbsTheme dbObject = oaBbsThemeMag.getObject(oaBbsTheme);

                if (null != dbObject) {
                    oaBbsThemeMag.copyObjectNotNullProperty(dbObject, oaBbsTheme);
                    oaBbsTheme = dbObject;

                }
                //先删除主题依赖的部分
               if("D".equals(oaBbsTheme.getState())){
                   oaBbsThemeMag.deleteThemeDependencies(oaBbsTheme.getThemeno());
               }
                oaBbsThemeMag.saveObject(oaBbsTheme);
            }
           
        }

        return "themeSuccess";
    }

    /**
     * 我的帖子(展示个人发布的所有帖子； ---别人访问我空间时不能显示删除禁用贴
     * 如果某个帖子已被管理员删除或禁用，也显示对应帖子，但状态标志位不可操作)
     * 
     * @return
     */
    public String ownerThemeList() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        // 别人访问我空间时不能显示删除禁用贴
        if (null != filterMap.get("usercode")
                && !getLoginUserCode().equals(filterMap.get("usercode"))) {
            filterMap.put("notDeleted", "D");
        }

        // PageDesc pageDesc = DwzTableUtils.makePageDesc(request, 20);
        PageDesc pageDesc = makeSearchPageDesc(request);

        objList = oaBbsThemeMag.listObjects(filterMap, pageDesc);

        // totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
        return "ownerThemeList";

    }

    public PageDesc makeSearchPageDesc(HttpServletRequest request) {

        String pagesize = request.getParameter("numPerPage");
        String pageno = request.getParameter("pageNum");

        String offset = request.getParameter("pager.offset");
        int pageSize = StringUtils.isNumeric(pagesize) ? Integer
                .parseInt(pagesize) : 10;
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

    private String curUrl;
    private OptJspInfo jspInfo;

    public String generalOptView() {

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        // 用于初始化查看页面默认显示
        curUrl = "/bbs/oaBbsTheme!ownerThemeList.do?s_usercode=" + s_usercode;
        jspInfo = new OptJspInfo();
        jspInfo.setTitle("我的帖子");
        jspInfo.setFrameList(frameList);

        return "ownerCommon";
    }

    /**
     * 关注列表 (usercode,laytype)
     * 
     * @return
     */
    public String attentionThemeList() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        PageDesc pageDesc = makeSearchPageDesc(request);
        oaBbsAttentions = oaBbsAttentionManager
                .listObjects(filterMap, pageDesc);
        if (null != oaBbsAttentions && oaBbsAttentions.size() > 0) {
            for (OaBbsAttention oaBbsAttention : oaBbsAttentions) {
                objList.add(oaBbsThemeMag.getObjectById(oaBbsAttention.getCid()
                        .getThemeno()));
            }

        }
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
        return "attentionThemeList";

    }

    /**
     * 我的回复
     * 
     * @return
     */
    public String ownerReplayList() {

        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        PageDesc pageDesc = makeSearchPageDesc(request);

        // infoType :BBS
        filterMap.put("infoType", "BBS");
        //留言回复功能扩展 --增加leavereplay数据
      /*  oaLeaveMessages = oaLeaveMessageManager
                .listObjects(filterMap, pageDesc);
        if (null != oaLeaveMessages && oaLeaveMessages.size() > 0) {
            for (OaLeaveMessage oaLeaveMessage : oaLeaveMessages) {
                // if(null!=oaBbsThemeMag.getObjectById(oaLeaveMessage.getDjid())){
                objList.add(oaBbsThemeMag.getObjectById(oaLeaveMessage
                        .getDjid()));// 业务流水号
                // }

            }

        }*/
        // 别人访问我空间时不能显示删除禁用回复
        if (null != filterMap.get("usercode")
                && !getLoginUserCode().equals(filterMap.get("usercode"))) {
            filterMap.put("notDeleted", "D");
        }
        List<VBbsThemeReplay> themeReplayList= vBbsThemeUserManager.getThemeReplyList(filterMap, pageDesc);
        if (null != themeReplayList && themeReplayList.size() > 0) {
            for (VBbsThemeReplay themeReplay : themeReplayList) {
                objList.add(oaBbsThemeMag.getObjectById(themeReplay
                        .getDjid()));// 业务流水号
                }

            }
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
        request.setAttribute("themeReplayList", themeReplayList);
        return "ownerReplayList";
    }

    /***
     * 帖子管理后台展示tree
     */
    
    public String oaThemeLab() {
        //discussition板块+子版块
        putDisTree();
        return "oaThemeLab";
    }
    
    /**
     * 获取讨论版面数据
     * 
     * @return
     */
    public void putDisTree() {
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        JSONArray jadashboard = oaBbsDashboardManager.putLayoutTree(user.getUsercode(),
                sParentUnit);
        JSONArray jadiscusstion = oaBbsDiscussionManager.putLayoutTree(user.getUsercode(),
                sParentUnit);
        jadashboard.addAll(jadiscusstion);
        request.setAttribute("unit", jadashboard.toString());
    }
    /********* get set 方法 ********/
    public String getS_layoutno() {
        return s_layoutno;
    }

    public void setS_layoutno(String s_layoutno) {
        this.s_layoutno = s_layoutno;
    }

    public OaBbsDiscussionManager getOaBbsDiscussionManager() {
        return oaBbsDiscussionManager;
    }

    public void setOaBbsDiscussionManager(
            OaBbsDiscussionManager oaBbsDiscussionManager) {
        this.oaBbsDiscussionManager = oaBbsDiscussionManager;
    }

    public OaBbsDiscussion getBbsDiscussion() {
        return bbsDiscussion;
    }

    public void setBbsDiscussion(OaBbsDiscussion bbsDiscussion) {
        this.bbsDiscussion = bbsDiscussion;
    }

    private String getLoginUserCode() {

        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    public OaLeaveMessageManager getOaLeaveMessageManager() {
        return oaLeaveMessageManager;
    }

    public void setOaLeaveMessageManager(
            OaLeaveMessageManager oaLeaveMessageManager) {
        this.oaLeaveMessageManager = oaLeaveMessageManager;
    }

    public List<OaLeaveMessage> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<OaLeaveMessage> replyList) {
        this.replyList = replyList;
    }

    public Long getTodayThemeNum() {
        return todayThemeNum;
    }

    public void setTodayThemeNum(Long todayThemeNum) {
        this.todayThemeNum = todayThemeNum;
    }

    public List<Map<String, String>> getJson() {
        return json;
    }

    public void setJson(List<Map<String, String>> json) {
        this.json = json;
    }

    public OaBbsDashboardManager getOaBbsDashboardManager() {
        return oaBbsDashboardManager;
    }

    public void setOaBbsDashboardManager(
            OaBbsDashboardManager oaBbsDashboardManager) {
        this.oaBbsDashboardManager = oaBbsDashboardManager;
    }

    public List<OaBbsDashboard> getOaBbsDashboards() {
        return oaBbsDashboards;
    }

    public void setOaBbsDashboards(List<OaBbsDashboard> oaBbsDashboards) {
        this.oaBbsDashboards = oaBbsDashboards;
    }

    public List<OaBbsDiscussion> getOaBbsDiscussions() {
        return oaBbsDiscussions;
    }

    public void setOaBbsDiscussions(List<OaBbsDiscussion> oaBbsDiscussions) {
        this.oaBbsDiscussions = oaBbsDiscussions;
    }

    public String getS_layoutcode() {
        return s_layoutcode;
    }

    public void setS_layoutcode(String s_layoutcode) {
        this.s_layoutcode = s_layoutcode;
    }

    public List<OaBbsAttention> getOaBbsAttentions() {
        return oaBbsAttentions;
    }

    public void setOaBbsAttentions(List<OaBbsAttention> oaBbsAttentions) {
        this.oaBbsAttentions = oaBbsAttentions;
    }

    public OaBbsAttentionManager getOaBbsAttentionManager() {
        return oaBbsAttentionManager;
    }

    public void setOaBbsAttentionManager(
            OaBbsAttentionManager oaBbsAttentionManager) {
        this.oaBbsAttentionManager = oaBbsAttentionManager;
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

    public String getS_usercode() {
        return s_usercode;
    }

    public void setS_usercode(String s_usercode) {
        this.s_usercode = s_usercode;
    }

    public List<OaLeaveMessage> getOaLeaveMessages() {
        return oaLeaveMessages;
    }

    public void setOaLeaveMessages(List<OaLeaveMessage> oaLeaveMessages) {
        this.oaLeaveMessages = oaLeaveMessages;
    }

    public OaUserinfoManager getOaUserinfoManager() {
        return oaUserinfoManager;
    }

    public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
        this.oaUserinfoManager = oaUserinfoManager;
    }

    public OaUserinfo getOaUserinfo() {
        return oaUserinfo;
    }

    public void setOaUserinfo(OaUserinfo oaUserinfo) {
        this.oaUserinfo = oaUserinfo;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setOaLeaveMessagereplyManager(
            OaLeaveMessagereplyManager oaLeaveMessagereplyManager) {
        this.oaLeaveMessagereplyManager = oaLeaveMessagereplyManager;
    }

    public VBbsThemeUserManager getvBbsThemeUserManager() {
        return vBbsThemeUserManager;
    }

    public void setvBbsThemeUserManager(VBbsThemeUserManager vBbsThemeUserManager) {
        this.vBbsThemeUserManager = vBbsThemeUserManager;
    }
    
}

