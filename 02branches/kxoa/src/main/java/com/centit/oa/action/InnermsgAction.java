package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.app.po.FileinfoFs;
import com.centit.app.service.FileinfoFsManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
//import com.centit.oa.po.ConsumablesCheckDetail;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.po.Msgannex;
import com.centit.oa.po.UserMailConfig;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.UserMailConfigManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.HtmlFormUtils;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateTimeUtil;
import com.centit.sys.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

public class InnermsgAction extends BaseEntityDwzAction<Innermsg> {
    /**
     *
     */
    private static final long serialVersionUID = -2551187666778246797L;
    private InnermsgManager innermsgMag;
    private InnermsgRecipientManager innermsgRecipientManager;
    private UserMailConfigManager userMailConfigManager;
    private FileinfoFsManager fileinfoManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private String s_msgtype;
    private String s_mailtype;// 邮箱类型
    private String s_showtype;// 页面显示标记：阅读状态下拉框是否显示

    private String userValue;// 人员编号
    private Object json;
    private Innermsg innermsg;

    private String toUserVaule;// 收件人
    private String ccUserVaule;// 抄送
    private String bccUserVaule;// 密送
    boolean flag = false;// 本人是否为密送

    // private List<Innermsg> replyList;
    // private List<FileInfo> fileList;
    private String s_NP_senddate;// 发件箱列表查看后返回时，区分是否"我的首页"点击进入
    private String s_msgstate;// 收件箱列表查看后返回时，区分是否"我的首页"点击进入
    private String s_newmsgtype;// s_newmsgtype用来区分个人邮件下查看后返回时，刷新的是innermsg的哪个方法

    private String isShow;//首页待办是否需要时间查询
    Map<String, String> result = new HashMap<String, String>();
    
    private String originate;// 用于存放链接来源
    
    public String getS_NP_senddate() {
        return s_NP_senddate;
    }

    public void setS_NP_senddate(String s_NP_senddate) {
        this.s_NP_senddate = s_NP_senddate;
    }

    public String getS_msgstate() {
        return s_msgstate;
    }

    public void setS_msgstate(String s_msgstate) {
        this.s_msgstate = s_msgstate;
    }

    public String getS_newmsgtype() {
        return s_newmsgtype;
    }

    public void setS_newmsgtype(String s_newmsgtype) {
        this.s_newmsgtype = s_newmsgtype;
    }

    public String getS_msgtype() {
        return s_msgtype;
    }

    public void setS_msgtype(String s_msgtype) {
        this.s_msgtype = s_msgtype;
    }

    public void setInnermsgManager(InnermsgManager basemgr) {
        innermsgMag = basemgr;
        this.setBaseEntityManager(innermsgMag);
    }

    public void setInnermsgRecipientManager(
            InnermsgRecipientManager innermsgRecipientManager) {
        this.innermsgRecipientManager = innermsgRecipientManager;
    }

    public void setFileinfoFsManager(FileinfoFsManager fileinfoManager) {
        this.fileinfoManager = fileinfoManager;
    }

    /**
     * 消息发件箱
     */
    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if (!CodeRepositoryUtil
                .getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_A)
                .equalsIgnoreCase(String.valueOf(filterMap.get("msgtype")))) {
            filterMap.put("sender", this.getLoginUserCode());
            filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
                    Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));
        }

        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        if (!StringUtils.hasText(orderField)
                && !StringUtils.hasText(orderDirection)) {
            filterMap.put(CodeBook.SELF_ORDER_BY, "senddate desc");// 默认按发送日期排序
        }

        else if (StringUtils.hasText(orderField)
                && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                    + orderDirection);

        }
      //默认查询当前月份第一天到现在的记录
        if(!"T".equals(isShow)){//首页待办 
            if(StringBaseOpt.isNvl((String)filterMap.get("begsenddate"))&&StringBaseOpt.isNvl((String)filterMap.get("endsenddate"))){
                filterMap.put("begsenddate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endsenddate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
        }
        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        this.pageDesc = pageDesc;
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        // String mailtype1=(String) filterMap.get("mailtype");
        // if(StringUtils.hasText(mailtype1)&&mailtype1.equals("D")){
        // return "draftsBoxlist";
        // }
        if (CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_A)
                .equalsIgnoreCase(String.valueOf(filterMap.get("msgtype")))) {
            return "msgAList";// 为公告时返回公告的页面 add by lq

        } else {
            //return LIST; 
        	return "listV2";
        }

    }

    /**
     * 收件箱列表（收件人，抄送，密送多条数据有相同接受人员时，收件箱只出现同一条数据）
     */

    @SuppressWarnings("unchecked")
    public String recList() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        // filterMap.put("msgstateNoEq", CodeRepositoryUtil.getValue(
        // Innermsg.MSG_STAT, Innermsg.MSG_STAT_D));//发件被删除状态，其他收件人仍可以看见该记录

        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
        //默认查询当前月份第一天到现在的记录
        if(!"T".equals(isShow)){//首页待办 
            if(StringBaseOpt.isNvl((String)filterMap.get("begsenddate"))&&StringBaseOpt.isNvl((String)filterMap.get("endsenddate"))){
                filterMap.put("begsenddate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endsenddate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
        }
        objList = innermsgMag.listObjects(filterMap, pageDesc,
                getLoginUserCode());
        // 添加收件阅读状态标记
        int unReadNum = 0;
        if (objList != null && objList.size() > 0) {
            for (int i = 0; i < objList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("msgcode", objList.get(i).getMsgcode());
                map.put("receive", getLoginUserCode());
                map.put("msgstate", "U");
                List<InnermsgRecipient> ureadList = innermsgRecipientManager
                        .listObjects(map);// 跟新收件阅读状态
                if (ureadList != null && ureadList.size() > 0) {
                    objList.get(i).setMsgstate("U");
                    unReadNum += ureadList.size();
                }
            }
        }
        
        request.setAttribute("unReadNum", unReadNum);
        this.pageDesc = pageDesc;
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "recList";
    }

    /**
     * 设置当前用户邮件账户
     * 
     * @return
     */
    public void putMailAccount() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        List<Map<String, String>> boxs = new ArrayList<Map<String, String>>();
        Map<String, String> ibox = new HashMap<String, String>();
        ibox.put("name", "收件箱");
        ibox.put("mailtype", Innermsg.MAIL_TYPE_I);
        Map<String, String> obox = new HashMap<String, String>();
        obox.put("name", "发件箱");
        obox.put("mailtype", Innermsg.MAIL_TYPE_O);
        Map<String, String> dbox = new HashMap<String, String>();
        dbox.put("name", "草稿箱");
        dbox.put("mailtype", Innermsg.MAIL_TYPE_D);
        Map<String, String> tbox = new HashMap<String, String>();
        tbox.put("name", "废件箱");
        tbox.put("mailtype", Innermsg.MAIL_TYPE_T);

        boxs.add(ibox);
        boxs.add(obox);
        boxs.add(dbox);
        boxs.add(tbox);

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("usercode", this.getLoginUserCode());

        // 当前用户所拥有Mail账号
        List<UserMailConfig> mailConfigs = userMailConfigManager
                .listObjects(filterMap);

        request.setAttribute("mailConfigs", mailConfigs);

        for (UserMailConfig uc : mailConfigs) {
            Map<String, Object> jsonData = new HashMap<String, Object>();
            jsonData.put("name", uc.getMailaccount());
            jsonData.put("children", boxs);
            jsonData.put("open", true);

            result.add(jsonData);
        }

        request.setAttribute("jsonData", JSONArray.fromObject(result)
                .toString());

    }

    @SuppressWarnings("unused")
    private List<UserMailConfig> listUserMail() {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("usercode", this.getLoginUserCode());

        // 当前用户所拥有Mail账号
        return userMailConfigManager.listObjects(filterMap);
    }

    /**
     * 邮箱列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listMailBox() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        if (!filterMap.containsKey("mailtype")
                || !filterMap.containsKey("mailaccount")) {
            putMailAccount();
            return "listMailBox";
        }

        Map<String, Object> mailConfigFilterMap = new HashMap<String, Object>();
        mailConfigFilterMap.put("mailaccountEQ", filterMap.get("mailaccount"));
        mailConfigFilterMap.put("usercode", getLoginUserCode());

        List<UserMailConfig> userMailConfigs = userMailConfigManager
                .listObjects(mailConfigFilterMap);
        if (CollectionUtils.isEmpty(userMailConfigs)) {
            putMailAccount();
            return "listMailBox";
        }

        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        if (StringUtils.hasText(orderField)
                && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                    + orderDirection);

        }

        filterMap.put("msgtype",
                CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE, "M"));
        filterMap.put("emailid", userMailConfigs.get(0).getEmailid());

        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);

        objList = this.innermsgMag.listObjects(filterMap, pageDesc);

        this.pageDesc = pageDesc;

        putMailAccount();
        setbackSearchColumn(filterMap);
        return "listMailBox";
    }

    public String add() {
        // 获取参会人员
        initUsers();
        // putInnermsgTree();

        return "add";
    }

    @SuppressWarnings("unchecked")
    public String addMail() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        // object = baseEntityManager.getObject(object);

        UserMailConfig c = userMailConfigManager.getObject(object.getC());
        object.setC(c);

        return "addMail";
    }

    /**
     * 发送邮件
     * 
     * @return
     */
    public String saveSendMail() {
        setForwardParam();

        addMsgannex();

        Innermsg innermsg = baseEntityManager.getObject(object);
        if (null != innermsg) {
            innermsg.copyNotNullProperty(object);
            object = innermsg;
        }

        innermsgMag.saveSendMail(object);

        return SUCCESS;
    }

    /**
     * 收取邮件
     * 
     * @return
     */
    public String saveReceiveMail() {
        setForwardParam();

        int length = -1;
        object.setReceiveUserCode(getLoginUserCode());
        try {
            length = innermsgMag.saveReceiveMail(object);
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();

            return SUCCESS;
        }

        if (0 == length) {
            successMessage = "没有新邮件";
        } else {
            successMessage = "成功接收  " + length + "  封邮件";
        }
        return SUCCESS;
    }

    /**
     * 邮件状态互换[inbox <--> outbox <--> ]
     * 
     * @return
     */
    public String modifyMailBox() {
        setForwardParam();

        List<Innermsg> innermsgs = new ArrayList<Innermsg>();
        for (Innermsg innermsg : getInnermsgs()) {
            innermsgs.add(baseEntityManager.getObject(innermsg));
        }

        if (CollectionUtils.isEmpty(innermsgs)) {
            return SUCCESS;
        }

        String to = request.getParameter("to");
        if (StringUtils.hasText(to)) {
            innermsgMag.updateMailBox(innermsgs, to);
        } else {
            innermsgMag.updateMailBox(innermsgs);
        }

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    private void setForwardParam() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        object.setC(new UserMailConfig((String) filterMap.get("mailaccount")));
        object.setMailtype((String) filterMap.get("mailtype"));
    }

    /**
     * 获取部门人员数据
     * 
     * @return
     */
    public void putInnermsgTree() {
        List<FUnitinfo> units = CodeRepositoryUtil.getAllUnits("T");
        List<FUserinfo> users = CodeRepositoryUtil.getAllUsers("T");
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();

        for (FUnitinfo unitinfo : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", unitinfo.getUnitcode());

            // 顶级父节点如果不存在，设置为null，否则ztree找不到顶级节点
            m.put("pId",
                    "0".equals(unitinfo.getParentunit()) ? null : unitinfo
                            .getParentunit());
            m.put("name", unitinfo.getUnitname());
            // m.put("open", "false");
            m.put("p", "true");
            unit.add(m);
        }
        // unit.remove(44);
        for (FUserinfo userinfo : users) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userinfo.getUsercode());

            if (!StringUtils.hasText(userinfo.getPrimaryUnit())) {
                continue;
            }

            m.put("pId", userinfo.getPrimaryUnit());
            m.put("name", userinfo.getUsername());
            m.put("p", "false");

            unit.add(m);
        }

        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        ActionContext.getContext().put("unit", ja.toString());
    }

    /**
     * 保存消息
     * 
     * @return
     */
    public String saveMsg() {
        final String view = "saveMsg";
        object.setSender(this.getLoginUserCode());
        if ("F".equals(object.getIsAuto())) {// 非定时发送
            object.setSenddate(new Date());
        }
        object.setMailtype(Innermsg.MAIL_TYPE_O);
        addMsgannex();
        // 公告
        if (CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_A)
                .equalsIgnoreCase(this.object.getMsgtype())) {
            this.object.setMsgstate(Innermsg.MSG_STAT_R);

            // 公告时，M_INNERMSG_RECIPIENT表中也插入一条消息， receivetype = A

            this.innermsgMag.saveAnnouncement(this.object);
            return view;
        }

        this.innermsgMag.saveMsg(object);

        // 如果是从在线人员列表进来的，就再返回过去
        if("usersOnline".equals(originate)){
            return "userOnlineList";
        }
        
        return "innermsgSucces";
        
    }

    /**
     * 保存消息草稿
     */
    public String saveDraft() {
        object.setMailtype(Innermsg.MAIL_TYPE_D);
        object.setSender(this.getLoginUserCode());
        if ("F".equals(object.getIsAuto())) {// 非定时发送
            object.setSenddate(new Date());
        }
        object.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                Innermsg.MSG_STAT_R));
        addMsgannex();
        this.innermsgMag.saveMsg(object);
        
        if("I".equals(s_mailtype)){
            return "innermsgRecList";
        }
        
        // 如果是从在线人员列表进来的，就再返回过去
        if("usersOnline".equals(originate)){
            return "userOnlineList";
        }
        
        return this.list();
    }

    /**
     * 添加附件
     */
    private void addMsgannex() {
        Object o = ActionContext.getContext().getParameters().get("filecodes");
        if (null != o) {
            String msgannex = HtmlFormUtils.getParameterString(o);
            if (StringUtils.hasText(msgannex)) {
                String[] msgannexs = msgannex.split(",");
                for (String s : msgannexs) {

                    object.getMsgannexs().add(
                            new Msgannex(null, this.fileinfoManager
                                    .getObjectById(s)));
                }
            }
        }
    }

    /**
     * 删除消息
     * 
     * @return
     */
    public String deleteMsg() {
        final String innermsgList = "innermsgList";
        // 发件箱
        if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_O).equalsIgnoreCase(object.getMailtype())) {

            object.setSender(this.getLoginUserCode());
            if (CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE,
                    Innermsg.MSG_TYPE_A).equalsIgnoreCase(object.getMsgtype())) {
                // 公告直接删除
                this.object = this.baseEntityManager.getObject(object);
                this.innermsgMag.deleteObject(object);
            } else {
                this.innermsgMag.deleteMsgs(getInnermsgs(), CodeRepositoryUtil
                        .getValue(Innermsg.MAIL_TYPE, Innermsg.MAIL_TYPE_O));
            }

            return innermsgList;
        } else if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE, "I")
                .equalsIgnoreCase(object.getMailtype())) {
            object.setReceiveUserCode(this.getLoginUserCode());

            this.innermsgMag.deleteMsgs(getInnermsgs(), CodeRepositoryUtil
                    .getValue(Innermsg.MAIL_TYPE, Innermsg.MAIL_TYPE_I));

        } else if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE, "D")
                .equalsIgnoreCase(object.getMailtype())) {
            this.innermsgMag.deleteMsgs(getInnermsgs(), CodeRepositoryUtil
                    .getValue(Innermsg.MAIL_TYPE, Innermsg.MAIL_TYPE_D));
        }

        return innermsgList;

    }

    /**
     * 删除收件箱
     * 
     * @return
     */
    public String deleteRec() {
        List<String> pk = new ArrayList<String>();
        if (StringUtils.hasText(object.getMsgcode())) {
            pk.add(object.getMsgcode());
        } else {
            String ids = request.getParameter("msgcodes");

            pk = Arrays.asList(ids.split(","));
        }

        innermsgRecipientManager.deleteRec(pk, getLoginUserCode());

        return recList();
    }

    /**
     * 删除收件箱---废件箱
     * 
     * @return
     */
    public String dropRec() {
        List<String> pk = new ArrayList<String>();
        if (StringUtils.hasText(object.getMsgcode())) {
            pk.add(object.getMsgcode());
        } else {
            String ids = request.getParameter("msgcodes");

            pk = Arrays.asList(ids.split(","));
        }

        innermsgRecipientManager.dropRec(pk, getLoginUserCode());

        return recList();
    }

    /**
     * 批量还原废件箱邮件到收件箱中
     * 
     * @return
     */
    public String cancleDropRec() {
        List<String> pk = new ArrayList<String>();
        if (StringUtils.hasText(object.getMsgcode())) {
            pk.add(object.getMsgcode());
        } else {
            String ids = request.getParameter("msgcodes");

            pk = Arrays.asList(ids.split(","));
        }

        innermsgRecipientManager.cancleDropRec(pk, getLoginUserCode());

        return recList();
    }

    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    private List<Innermsg> getInnermsgs() {
        List<Innermsg> innermsgs = new ArrayList<Innermsg>();
        if (StringUtils.hasText(object.getMsgcode())) {
            innermsgs.add(object);
            return innermsgs;
        }

        String msgcodes = request.getParameter("msgcodes");

        if (!StringUtils.hasText(msgcodes)) {
            return innermsgs;
        }
        for (String msgcode : msgcodes.split(",")) {
            innermsgs.add(new Innermsg(msgcode));
        }

        return innermsgs;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String view() {
        setForwardParam();
        isMyBcc();// 判断自己是否为密送
        innermsgRecipientManager.updateMsgStat(object, getLoginUserCode());// 跟新收件阅读状态

        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        object = this.innermsgMag.getObjectById(object.getMsgcode());
        /*if (CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_A)
                .equalsIgnoreCase(object.getMsgtype())) {

            // return "viewForA";
        }*/
        object.setDateTag(DateTimeUtil.smartCalcSpanDays(object.getSenddate(),
                new Date()));
        
        if(null != object.getMsgannexs() && !object.getMsgannexs().isEmpty()){
            request.setAttribute("msgannesxNum", object.getMsgannexs().size());
        }
      //如果是从首页进入，用isshow标记，用于关闭模式窗口
        if(request.getParameter("isShow")!=null&&request.getParameter("isShow")!=""){
            request.setAttribute("isShow", request.getParameter("isShow"));
        }
        // 邮件
        if (CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_P)//目前邮件标记为p,不是m
                .equalsIgnoreCase(this.object.getMsgtype())) {
            if (CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                    Innermsg.MSG_STAT_U).equalsIgnoreCase(object.getMsgstate())) {

                object.setMsgstate(CodeRepositoryUtil.getValue(
                        Innermsg.MSG_STAT, Innermsg.MSG_STAT_R));
                this.innermsgMag.saveObject(object);

            }

            // 草稿箱，跳转至 发送邮件
            if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                    Innermsg.MAIL_TYPE_D)
                    .equalsIgnoreCase(object.getMailtype())) {

                // 将类型设为发件箱

               // return "addMail";
            }
          //  return "viewMail";
        }

        return VIEW;
    }

    /**
     * 修改草稿
     */
    public String editDraft() {

        Innermsg inmsg = this.baseEntityManager.getObject(object);
        if (inmsg != null)
            // 将对象o copy给object，object自己的属性会保留
            baseEntityManager.copyObject(object, inmsg);
        initUsers();
        // this.getUsers();

        toUserVaule = this.getUsersByRtype("T");// 收件人
        ccUserVaule = this.getUsersByRtype("C");// 抄送
        bccUserVaule = this.getUsersByRtype("B");// 密送

        request.setAttribute("minDate", new Date());
        //如果是从公共办理页码进来，用from来标记
        if(request.getParameter("from")!=null&&request.getParameter("from")!=""){
            request.setAttribute("from",request.getParameter("from"));
        }//如果是从首页更多进来，用dashboard来标记，用于去除返回按钮
        if(request.getParameter("dashboard")!=null&&request.getParameter("dashboard")!=""){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
         //如果是从首页进入，用isshow标记，用于关闭模式窗口
        if(request.getParameter("isShow")!=null&&request.getParameter("isShow")!=""){
            request.setAttribute("isShow", request.getParameter("isShow"));
        }
        // 从在线人员列表进来
        if("usersOnline".equals(originate)){
            toUserVaule = request.getParameter("usercodes");
            object.setReceivename(BizCommUtil.getUsernamesFromUsercodes(toUserVaule));
        }

        return "editDraft";
    }

    
    /**
     * 删除草稿箱的附件
     */
    public String deletefile() {

        String filecode = request.getParameter("filecode");
        if (StringUtils.hasText(object.getMsgcode())
                && StringUtils.hasText(filecode))
            this.innermsgMag.deleteMsgnnex(object.getMsgcode(), filecode);
        return "files";
    }

    /**
     * 准备修改草稿箱时在特效组件里显示收件人
     */
    public String getUsers() {
        Set<InnermsgRecipient> irs = object.getInnermsgRecipients();
        if (null != irs && !irs.isEmpty()) {
            userValue = "";
            for (InnermsgRecipient iR : irs) {
                String value = iR.getReceive() + ",";
                userValue += value;
            }
        }
        if (StringUtils.hasText(userValue)) {
            userValue = userValue.substring(0, userValue.length() - 1);
        }
        return userValue;
    }

    /**
     * 判断自己是否为密送
     * 
     * @return
     */
    public boolean isMyBcc() {

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("msgcode", object.getMsgcode());
        filterMap.put("receivetype", "B");// 密送
        filterMap.put("receive", getLoginUserCode());// 当前登录者
        List<InnermsgRecipient> recList = innermsgRecipientManager
                .listObjects(filterMap);
        if ((recList != null && recList.size() > 0)) {
            flag = true;
        }
        return flag;

    }

    /**
     * 准备修改草稿箱时在特效组件里显示收件人
     */
    public String getUsersByRtype(String recipientType) {
        userValue = "";
        if (null != object && null != object.getMsgcode()) {
            List<InnermsgRecipient> irs = innermsgRecipientManager.listByRtype(
                    object.getMsgcode(), recipientType);
            if (null != irs && !irs.isEmpty()) {

                for (InnermsgRecipient iR : irs) {
                    String value = iR.getReceive() + ",";
                    userValue += value;
                }
            }
        }
        if (StringUtils.hasText(userValue)) {
            userValue = userValue.substring(0, userValue.length() - 1);
        }
        return userValue;
    }

    public void setUserMailConfigManager(
            UserMailConfigManager userMailConfigManager) {
        this.userMailConfigManager = userMailConfigManager;
    }

    public String search() {
        PageDesc pageDesc = makePageDesc();
        String key = request.getParameter("key");
        if (Innermsg.MAIL_TYPE_O.equals(object.getMailtype())) {
            objList = innermsgMag.listBySearch(getLoginUserCode(), key,
                    pageDesc);
        } else if (Innermsg.MAIL_TYPE_I.equals(object.getMailtype())) {
            request.setAttribute("innermsgRecipients", innermsgRecipientManager
                    .listBySearch(getLoginUserCode(), key, pageDesc));
        }

        super.pageDesc = pageDesc;
        return "search";
    }

    /**
     * 获取用户机构树形结构
     * 
     * @return
     */
    public String getUnitUsers() {
        putInnermsgTree();

        return "userTree";
    }

    /**
     * 文件转发
     * 
     * @return
     */
    public String rewardMail() {
        object = this.innermsgMag.getObjectById(object.getMsgcode());
        
        object.setTopMsgcode(object.getMsgcode());//回复(转发)邮件id
        object.setTopType("1");//1 转发 2 回复
        
        initUsers();
        toUserVaule = this.getUsersByRtype("T");// 收件人

        ccUserVaule = this.getUsersByRtype("C");// 抄送
        
        //如果是从首页进入，用isshow标记，用于关闭模式窗口
        if(request.getParameter("isShow")!=null&&request.getParameter("isShow")!=""){
            request.setAttribute("isShow", request.getParameter("isShow"));
        }
        
        innermsgRecipientManager.updateMsgStat(object, getLoginUserCode());// 更新收件阅读状态
        return "rewardMail";
    }

    /**
     * 文件答复
     * 
     * @return
     */
    public String replayMail() {

        Innermsg inmsg = this.innermsgMag.getObjectById(object.getMsgcode());
        if (inmsg != null)
            // 将对象o copy给object，object自己的属性会保留
            baseEntityManager.copyObject(object, inmsg);
        toUserVaule = object.getSender();// 答复收件人，为原邮件发件人
        object.setReceivename(CodeRepositoryUtil.getValue("usercode",
                toUserVaule));
        object.setSender(getLoginUserCode());// 答复发件人

        ccUserVaule = this.getUsersByRtype("C");// 抄送
        object.setBccName(null);// 密送人不答复

        object.setTopMsgcode(object.getMsgcode());//回复(转发)邮件id
        object.setTopType("2");//1 转发 2 回复
        
        initUsers();
        innermsgRecipientManager.updateMsgStat(object, getLoginUserCode());// 更新收件阅读状态
        return "replayMail";
    }

    /**
     * 邮件全部答复
     * 
     * @return
     */
    public String replayAllMail() {
        Innermsg inmsg = this.innermsgMag.getObjectById(object.getMsgcode());
        if (inmsg != null)
            // 将对象o copy给object，object自己的属性会保留
            baseEntityManager.copyObject(object, inmsg);

        toUserVaule = this.getUsersByRtype("T");// 全部答复收件人，为原邮件发件人+原邮件收件人
        if (!toUserVaule.contains(object.getSender())) {
            if (StringUtils.hasText(toUserVaule)) {
                toUserVaule = object.getSender() + "," + toUserVaule;
                object.setReceivename(CodeRepositoryUtil.getValue("usercode",
                        toUserVaule) + "," + object.getReceivename());
            } else {
                toUserVaule = object.getSender();
                object.setReceivename(CodeRepositoryUtil.getValue("usercode",
                        toUserVaule));
            }
        }

        object.setSender(getLoginUserCode());// 答复发件人

        ccUserVaule = this.getUsersByRtype("C");// 抄送
        object.setBccName(null);// 密送人不答复

        initUsers();

        innermsgRecipientManager.updateMsgStat(object, getLoginUserCode());// 更新收件阅读状态
        return "replayMail";
    }

    /*
     * 获取人员
     */
    public void initUsers() {
        // JSONArray userjson
        // =oaPowerrolergroupManager.putAllUserTree(getLoginUserCode());
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
        
        request.setAttribute("stationUsers", oaPowerrolergroupManager.getStationtUsersTree(null));
        request.setAttribute("unitLeaderuserjson", oaPowerrolergroupManager.getUnitLeaderUsersTree());
    }

    public String annexExist(){
        
        String filecode = request.getParameter("filecode");
        FileinfoFs info = fileinfoManager.getObjectById(filecode);
        
        try{
            fileinfoManager.downloadFileinfo(info);
        }catch(Exception e){
            result.put("error", "文件不存在");
        }
        
        return "annexExist";
    }
    
    public String getS_mailtype() {
        return s_mailtype;
    }

    public void setS_mailtype(String s_mailtype) {
        this.s_mailtype = s_mailtype;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public Innermsg getInnermsg() {
        return innermsg;
    }

    public void setInnermsg(Innermsg innermsg) {
        this.innermsg = innermsg;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public String getToUserVaule() {
        return toUserVaule;
    }

    public void setToUserVaule(String toUserVaule) {
        this.toUserVaule = toUserVaule;
    }

    public String getCcUserVaule() {
        return ccUserVaule;
    }

    public void setCcUserVaule(String ccUserVaule) {
        this.ccUserVaule = ccUserVaule;
    }

    public String getBccUserVaule() {
        return bccUserVaule;
    }

    public void setBccUserVaule(String bccUserVaule) {
        this.bccUserVaule = bccUserVaule;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getS_showtype() {
        return s_showtype;
    }

    public void setS_showtype(String s_showtype) {
        this.s_showtype = s_showtype;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Map<String, String> getResult() {
        return result;
    }
    
    public String getOriginate() {
        return originate;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
    
}
