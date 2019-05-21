package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;

public class InnermsgRecipientAction extends
        BaseEntityDwzAction<InnermsgRecipient> {
    private static final Log log = LogFactory
            .getLog(InnermsgRecipientAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private InnermsgRecipientManager innermsgRecipientMag;

    private String s_msgtype;
    private String s_mailtype;// 邮箱类型
    private String s_showtype;//页面显示标记：阅读状态下拉框是否显示

    public String getS_msgtype() {
        return s_msgtype;
    }

    public void setS_msgtype(String s_msgtype) {
        this.s_msgtype = s_msgtype;
    }

    public void setInnermsgRecipientManager(InnermsgRecipientManager basemgr) {
        innermsgRecipientMag = basemgr;
        this.setBaseEntityManager(innermsgRecipientMag);
    }

    @Override
    public String delete() {
        List<String> pk = new ArrayList<String>();
        if (StringUtils.hasText(object.getId())) {
            pk.add(object.getId());
        } else {
            String ids = request.getParameter("ids");

            pk = Arrays.asList(ids.split(","));
        }

        innermsgRecipientMag.delete(pk);

        return list();
    }
    
   
    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("receive", this.getLoginUserCode());
        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        if (!StringUtils.hasText(orderField)
                && !StringUtils.hasText(orderDirection)) {
            filterMap.put(CodeBook.SELF_ORDER_BY, "msgstate desc,innermsg.senddate desc");
        }
        //将msgtitle替换innermsg.msgtitle 原因：target is null for setProperty
        if (StringUtils.hasText((String)filterMap.get("msgtitle"))) {
            filterMap.put("innermsg.msgtitle",(String)filterMap.get("msgtitle"));
            filterMap.remove("msgtitle");
        }

        else if (StringUtils.hasText(orderField)
                && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                    + orderDirection);

        }
        if(StringBaseOpt.isNvl((String)filterMap.get("begsenddate"))&&StringBaseOpt.isNvl((String)filterMap.get("endsenddate"))){
            filterMap.put("begsenddate",DateUtil.getCurrentMonthOfDay() );
            filterMap.put("endsenddate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
        }
        PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        //替换回msgtitle，在列表回显
        if(!"".equals((String)filterMap.get("innermsg.msgtitle"))&&(String)filterMap.get("innermsg.msgtitle")!=null){
        filterMap.put("msgtitle",(String)filterMap.get("innermsg.msgtitle"));
        }
        
        this.pageDesc = pageDesc;
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        //return LIST;
        return "listV2";
    }

    @Override
    public String view() {
        this.object = this.innermsgRecipientMag.getObjectById(this.object
                .getId());

        this.setRead();

        return VIEW;
    }

    /**
     * 回复信息
     * 
     * @return
     */
    public String recipient() {
        this.object = this.innermsgRecipientMag.getObject(object);
        this.setRead();
        return "recipient";
    }

    /**
     * 设置已读
     */
    private void setRead() {
        if (!CodeRepositoryUtil
                .getValue(Innermsg.MSG_STAT, Innermsg.MSG_STAT_R).equals(
                        this.object.getMsgstate())) {

            this.object.setMsgstate(CodeRepositoryUtil.getValue(
                    Innermsg.MSG_STAT, Innermsg.MSG_STAT_R));
            this.innermsgRecipientMag.saveObject(object);
        }
    }

    @SuppressWarnings("unused")
    private static void setFilterMapValue(Map<String, Object> filterMap,
            String key) {
        if (filterMap.containsKey(key)) {
            filterMap.put(key, "%" + filterMap.get(key) + "%");
        }
    }

    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    /**
     * 用户个人桌面
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String userPortal() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        // mailtype不参与查询，只参与标识页面功能
        filterMap.remove("mailtype");

        filterMap.put("receive", this.getLoginUserCode());

        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        this.pageDesc = pageDesc;
        return "userPortal";
    }

    public static Log getLog() {
        return log;
    }

    public String getS_mailtype() {
        return s_mailtype;
    }

    public void setS_mailtype(String s_mailtype) {
        this.s_mailtype = s_mailtype;
    }

    public String getS_showtype() {
        return s_showtype;
    }

    public void setS_showtype(String s_showtype) {
        this.s_showtype = s_showtype;
    }

}
