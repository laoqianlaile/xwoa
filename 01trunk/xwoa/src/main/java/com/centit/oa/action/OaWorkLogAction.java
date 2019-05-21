package com.centit.oa.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.OaWorkLog;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaWorkLogManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

public class OaWorkLogAction extends BaseEntityDwzAction<OaWorkLog> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaWorkLogAction.class);
    private static final long serialVersionUID = 1L;
    private OaWorkLogManager oaWorkLogMag;
    private AddressBookRelectionManager addressBookRelectionManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;

    public void setOaWorkLogManager(OaWorkLogManager basemgr) {
        oaWorkLogMag = basemgr;
        this.setBaseEntityManager(oaWorkLogMag);
    }

    private String userValue;// 人员编号
    private Object json;

    // private Date s_releaseDate;

    private void index(Map<String, Object> filterMap) {
        PageDesc pageDesc = makePageDesc();
        objList = oaWorkLogMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
    }

    /**
     * 工作日志
     * 
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // creater为当前登录人员
            filterMap.put("ownerCode", getLoginUserCode());
            index(filterMap);
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 只用来显示查询条件
     * 
     * @return
     */
    public String calendarList() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // creater为当前登录人员
            filterMap.put("ownerCode", getLoginUserCode());
            // index(filterMap);
            // 页面查询条件
            setbackSearchColumn(filterMap);
            return "calendarView";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    // 当天该日志类型是否已存在
    public boolean exitList() {
        // 参数：日志类型，日期
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        // creater为当前登录人员
        filterMap.put("creater", getLoginUserCode());

        List<OaWorkLog> logList = oaWorkLogMag.listObjects(filterMap);
        // 页面查询条件
        setbackSearchColumn(filterMap);

        if (logList != null && logList.size() > 0) {
            return false;

        } else {
            return true;

        }
    }

    /**
     * 时间段是否空闲
     * 
     * @return
     * @throws IOException
     */
    public String isLogExit() throws IOException {
        json = true;

        if (!exitList()) {
            json = false;
            return "json";
        }
        return "json";

    }

    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        // 获取参会人员
        initUsers();
        getUser();// 中间表与会人员
        super.edit();
        return EDIT;
    }

    /**
     * 新增，同时获取部门列表
     */
    public String addNew() {
        // 页面传来时间（为后期可能补录日志保留）
        Date releaseDate = object.getReleaseDate();
        // 获取参会人员
        initUsers();
        getUser();// 中间表与会人员
        super.edit();
        object.setReleaseDate(releaseDate);
        String maxDate= DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd");
        request.setAttribute("maxDate",maxDate);
        return EDIT;
    }

    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息

            if (StringUtils.isBlank(object.getNo())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatertime(DatetimeOpt.currentUtilDate());
            }
            // 不分享 or 为个人日志 清空中间表信息
            if (StringUtils.isNotBlank(object.getNo())
                    && ("m".equals(object.getInfoType()) || !"Y".equals(object.getIsshare()))) {
                // 删除分享人员（中间表日程安排biztype 0）
                object.setShares("");
                object.setIsallowcomment("");
                userValue = "";
            }
            super.save();
            saveOthers();
            return "calendarList";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    
    public String delecte() {
        super.delete();
        return "calendarList";
    }
    

    /**
     * A保存中间表
     */
    private void saveOthers() {
        // A中间表日志共享部分：biztype 0
        // 把参会人员记录到中间表m_address_book_relection
        addressBookRelectionManager.deleteuser(object.getNo());

        if (StringUtils.isNotBlank(userValue)) {
            String[] values = this.userValue.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection info = new AddressBookRelection();
                info.setAddrbookid(object.getNo());
                info.setShareman(values[i]);
                info.setBizType("0");
                addressBookRelectionManager.saveObject(info);
            }
        }
    }

    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }

    /**
     * 编辑的时候自动去人员中间表去获取usercode
     */
    private List<AddressBookRelection> listuser;

    private void getUser() {
        // 编辑的时候自动去人员中间表去获取usercode
        if (StringUtils.isNotBlank(object.getNo())) {
            listuser = addressBookRelectionManager.getUserlist(object.getNo(),
                    "0");
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
        Date start = DatetimeOpt.convertSqlDate(new Date(Long
                .parseLong((String) params.get("start")) * 1000));
        Date end = DatetimeOpt.convertSqlDate(new Date(Long
                .parseLong((String) params.get("end")) * 1000 - 1000));
        // creater为当前登录人员
        params.put("ownerCode", getLoginUserCode());
        // 其实结束时间
        params.put("releaseBegDate", DatetimeOpt.convertDateToString(start));
        params.put("releaseEndDate", DatetimeOpt.convertDateToString(end));

        if (params.get("title") != null) {
            params.put("title",
                    URLDecoder.decode(request.getParameter("title"), "UTF-8"));
        }

        List<OaWorkLog> workLoglist = oaWorkLogMag.listObjects(params);

        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(workLoglist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }

    private void getJsonResult(List<OaWorkLog> workLoglist,
            List<Map<String, Object>> jsonResult, Map<String, Object> params) {

        // 添加实例，操作相关实现类接口
        List<Map<String, Object>> result = putDataToMap(workLoglist, params);

        jsonResult.addAll(result);
    }

    /**
     * 将数据转换为日历控件所需要的Json数据格式
     * 
     * @param tasklist
     * @return
     */
    @SuppressWarnings("static-method")
    private List<Map<String, Object>> putDataToMap(List<OaWorkLog> workLoglist,
            Map<String, Object> params) {
        String contextPath = (String) params.get("contextPath");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (OaWorkLog data : workLoglist) {

            Map<String, Object> task = new HashMap<String, Object>();
            // 用户owner
            String owner = CodeRepositoryUtil.getValue("usercode",
                    data.getCreater());

            task.put("id", data.getNo());

            task.put("allDay", true);
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            // 默认url
            String url = "/oa/oaWorkLog!view.do?no=" + data.getNo();// no

            String title = "";
            String content;

            // 自己的个人，工作日志
            if (user.getUsercode().equals(data.getCreater())) {
                // 个人日志
                if (StringUtils.isNotBlank(data.getInfoType())
                        && "m".equals(data.getInfoType())) {
                    task.put("tasktag", "yellow");
                }
                // 工作日志
                if (StringUtils.isNotBlank(data.getInfoType())
                        && "w".equals(data.getInfoType())) {
                    task.put("tasktag", "green");
                }
                // 自己日记点击为编辑操作
                url = "/oa/oaWorkLog!edit.do?no=" + data.getNo();// no
                title = data.getTitle();
                // 可编辑
                task.put("editable", true);

            } else {
                // 别人共享工作日志,界面不区分颜色
                title = '[' + owner + "] " + data.getTitle();
//                task.put("tasktag", "purple");
                task.put("tasktag", "green");
                // 可编辑
                task.put("editable", false);
            }
            // 标题不是必填项，却是日历显示标题的必要字段 只有title和start是必须的
            if (StringUtils.isNotBlank(title)) {
//                title = data.getTitle();
                if (30 < title.length()) {
                    title = title.substring(0, 30) + "...";
                }
                task.put("title", title);
            } else {
                task.put("title", "无标题");
            }

           
            if (StringUtils.isNotBlank(data.getRemark())) {
                content = data.getRemark();
                if (100 < content.length()) {
                    content = content.substring(0, 50) + "...";
                }
                task.put("content", content);
            } else {
                task.put("content", "无內容");
            }
            task.put("start", DatetimeOpt.convertDateToString(
                    data.getReleaseDate(), "yyyy-MM-dd HH:mm:ss"));
            task.put("textColor", "black");

            task.put("allDay", true);

            if (StringUtils.isNotBlank(url)) {
                StringBuilder sb = new StringBuilder();

                task.put("url", contextPath + url + sb);
            }
            result.add(task);

        }

        return result;
    }

    /*
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
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

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    // public Date getS_releaseDate() {
    // return s_releaseDate;
    // }
    //
    // public void setS_releaseDate(Date s_releaseDate) {
    // this.s_releaseDate = s_releaseDate;
    // }
}
