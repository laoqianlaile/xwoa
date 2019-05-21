package com.centit.bbs.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.bbs.po.OaBbsDashboard;
import com.centit.bbs.po.OaBbsDiscussion;
import com.centit.bbs.service.OaBbsDashboardManager;
import com.centit.bbs.service.OaBbsThemeManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;

public class OaBbsDashboardAction extends BaseEntityDwzAction<OaBbsDashboard> {
    private static final Log log = LogFactory
            .getLog(OaBbsDashboardAction.class);
    private static final long serialVersionUID = 1L;
    private OaBbsDashboardManager oaBbsDashboardMag;
    private OaUserinfoManager oaUserinfoManager;// 讨论区用户信息
    private OaUserinfo oaUserinfo;
    private OaBbsDashboard oaBbsDashboard;

    private SysUnitManager sysUnitManager;
    protected SysUserManager sysUserManager;
    protected OaBbsThemeManager oaBbsThemeManager;
    private String bodycode;// 接收部门传值

    public void setOaBbsDashboardManager(OaBbsDashboardManager basemgr) {
        oaBbsDashboardMag = basemgr;
        this.setBaseEntityManager(oaBbsDashboardMag);
    }

    private List<OaBbsDiscussion> oaBbsDiscussions;

    public List<OaBbsDiscussion> getNewOaBbsDiscussions() {
        return this.oaBbsDiscussions;
    }

    public void setNewOaBbsDiscussions(List<OaBbsDiscussion> oaBbsDiscussions) {
        this.oaBbsDiscussions = oaBbsDiscussions;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public OaPowerrolergroupManager oaPowerrolergroupManager;

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    private List<FUserinfo> userinfos;

    private List<Map<String, String>> jsonDatas;

    public List<Map<String, String>> getJsonDatas() {
        return jsonDatas;
    }

    public void setJsonDatas(List<Map<String, String>> jsonDatas) {
        this.jsonDatas = jsonDatas;
    }

    public List<FUserinfo> getUserinfos() {
        return userinfos;
    }

    public void setUserinfos(List<FUserinfo> userinfos) {
        this.userinfos = userinfos;
    }

    /*
     * 获取人员
     */
    public void initUsers() throws IOException {
        JSONArray userjson = null;
        
        String userCode = request.getParameter("userCode");
        String layoutType = request.getParameter("layoutType");
        String unitCode = request.getParameter("unitCode");
        
        if(StringUtils.isBlank(userCode) || StringUtils.isBlank(unitCode) || StringUtils.isBlank(layoutType)){
            throw new IllegalArgumentException("用户编码和模块类型以及所属部门不能为空");
        }
        //如果不是超级管理员用户，只能选择自己所在部门下的所有员工
        if(!"0".equals(userCode) && !"99999999".equals(userCode)){  
            List<Map<String, String>> userMapArr = oaPowerrolergroupManager.setSubUnitsUser(userCode);
            userjson = new JSONArray();
            userjson.addAll(userMapArr);
        }else{
          //如果是超级管理员，但是模块内型是内部模块的话，只能选择当前选中的部门下的员工
            if("U".equals(layoutType)){
                List<Map<String, String>> userMapArr = oaPowerrolergroupManager.setSubUnitsUserByUnitCode(unitCode);
                userjson = new JSONArray();
                userjson.addAll(userMapArr);
            }else{
                userjson = oaPowerrolergroupManager.putUnitsUsersTree();
            }
        }
        ServletActionContext.getResponse().getWriter().print(userjson);
    }

    /**
     * 列表入口包含机构菜单
     * 
     * @return
     */
    public String oaBbsDiscussionsLab() {
        putUnitTree();
        return "oaBbsDiscussionsLab";
    }

    /**
     * 获取部门数据
     * 
     * @return
     */
    public void putUnitTree() {
        FUserDetail user = (FUserDetail)getLoginUser();
        JSONArray ja = null;
        if(user == null){
            return;
        }
        //是否显示树0-不显示 1-显示
        int showTree = 0;
        if(!"0".equals(user.getUsercode()) && !"99999999".equals(user.getUsercode())){  
            List<Map<String, String>> unitMapArr = oaPowerrolergroupManager.putSubUnits(user.getPrimaryUnit());
            ja = new JSONArray();
            ja.addAll(unitMapArr);
        }else{
            ja = oaPowerrolergroupManager.putUnitsTree(); 
            showTree = 1;
        }
        request.setAttribute("showTree", showTree);
        request.setAttribute("unit", ja.toString());
    }

    /**
     * 重载built方法
     * 
     * @return
     */
    public String built() {
        super.built();
        return BUILT;
    }

    public String list() {

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("isvalid", "T");

        // 列出可用的机构
        //
//        List<FUnitinfo> units = sysUnitManager.listObjects(filterMap);
//
//        request.setAttribute("units", units);
        //
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        filterMap = convertSearchColumn(paramMap);

        PageDesc pageDesc = makePageDesc();
        if (StringUtils.isNotBlank(bodycode)) {
            filterMap.put("unitcode", bodycode);
        }
        if(null == filterMap.get("includeDel")){
            filterMap.put("excludeStates", "D");
          }
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        if (objList != null && objList.size() > 0) {
            // 设置管理员名称
            for (OaBbsDashboard o : objList) {
                o.convertApprovalNames();
            }
        }
        totalRows = pageDesc.getTotalRows();

        this.pageDesc = pageDesc;
        setbackSearchColumn(filterMap);

        return LIST;

    }

    /**
     * 判断用户能否创建子版块
     * 
     * @param usercode
     * @param o
     */
    public void setGrantedValue(String usercode, OaBbsDashboard o) {

        if (StringUtils.isNotBlank(usercode)
                && StringUtils.isNotBlank(o.getApprovals())) {

            String[] usercodes = o.getApprovals().split(",");

            for (int i = 0; i < usercodes.length; i++) {
                if (usercode.equals(usercodes[i])) {
                    o.setIsGranted("Y");
                    return;
                }
            }

            o.setIsGranted("N");
        }
    }

    public String edit() {

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("isvalid", "T");
        //add by lay 2015-11-19 高级管理员看所有部门，其他看自己部门
        FUserDetail user = ((FUserDetail) getLoginUser());
        if(user !=null && !"0".equals(user.getUsercode()) && !"99999999".equals(user.getUsercode())){
            filterMap.put("UNITCODE", user.getPrimaryUnit());
        }
        //end add
        // 列出所有机构
        List<FUnitinfo> units = sysUnitManager.listObjects(filterMap);

        request.setAttribute("units", units);

        //delete by lay 2015-11-15 需要二级联动了，不适合提前把值发送到前台
        // 获取人员，为选择子模块管理员所用
        //initUsers(user);
        //end 

        // 获取数据库中数据
        OaBbsDashboard o = oaBbsDashboardMag.getObject(object);
        if (o != null)
            oaBbsDashboardMag.copyObject(object, o);
        else{
            oaBbsDashboardMag.clearObjectProperties(object);
            object.setState("N");//默认状态
            object.setUnitcode(bodycode);
        }
            

        // 将用户代码转成用户名暂存在approvalNames字段中
        object.convertApprovalNames();

        // jsonDate(object.getApprovals());

        return EDIT;

    }

    public String view() {

        OaBbsDashboard o = oaBbsDashboardMag.getObject(object);
        if (object == null) {
            return LIST;
        }
        if (o != null) {
            oaBbsDashboardMag.copyObject(object, o);
            // 将用户代码转成用户名暂存在approvalNames字段中
            object.convertApprovalNames();
        }
        return VIEW;
    }

    public String delete() {

        oaBbsDashboardMag.deleteObject(object);

        return SUCCESS;
    }
    /**
     * 启用
     * @return
     */
    public String setup(){
        OaBbsDashboard o = oaBbsDashboardMag.getObject(object);
        if (object == null) {
            return LIST;
        }
        if (o != null) {
            o.setState("N");
            oaBbsDashboardMag.saveObject(o);
        }
        return SUCCESS;
    }
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
            m.put("id", userinfo.getUsercode());
            m.put("name", userinfo.getUsername() + "[" + userinfo.getUsercode()
                    + "]" + "[" + userinfo.getLoginname() + "]");
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

    public void jsonDate(String usercode) {

        JSONArray ja = new JSONArray();
        jsonDatas = new ArrayList<Map<String, String>>();
        if (StringUtils.isNotEmpty(usercode)) {
            String[] usercodes = usercode.split(",");
            for (String s : usercodes) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", s);
                m.put("showName", CodeRepositoryUtil.getValue("usercode", s));
                jsonDatas.add(m);
            }
            ja.addAll(jsonDatas);
        }
        request.setAttribute("populate", ja.toString());
    }

    public String save() {
        // 根据页面暂存的时间段变量设置开放时间范围
        object.setOpentime();

        OaBbsDashboard dbObject = oaBbsDashboardMag.getObject(object);
        if (dbObject != null) {
            oaBbsDashboardMag.copyObjectNotNullProperty(dbObject, object);
            // 重新设置开放时间段，如果页面传来的暂存时间点有空值，就清空该时间点
            this.setOpentimeWithNull(object, dbObject);
            object = dbObject;
        }

        FUserDetail user = ((FUserDetail) getLoginUser());

        // 设置创建人和创建时间
        if (null != user && StringUtils.isBlank(object.getCreater())) {
            object.setCreater(user.getUsercode());
        }
        if (null == object.getCreatetime()) {
            object.setCreatetime(new Date());
        }

        // 如果设置了子模块管理员，就设置当前时间为设定时间
        if (StringUtils.isNotBlank(object.getApprovals())) {
            object.setApprovaltime(new Date());
        }

        // 删除原先大模块与子模块管理员间的关联
        oaBbsDashboardMag.deleteDashboardRelations(object);

        oaBbsDashboardMag.saveObject(object);

        return SUCCESS;
    }

    /**
     * 重新设置开放时间段，如果页面有传来空值，就清空该时间点
     * 
     * @param object
     *            页面对象
     * @param dbObject
     *            数据库对象
     */

    public void setOpentimeWithNull(OaBbsDashboard object,
            OaBbsDashboard dbObject) {

        if (StringUtils.isBlank(object.getStarttimeTemp())) {
            dbObject.setStarttime(null);
        }
        if (StringUtils.isBlank(object.getEndtimeTemp())) {
            dbObject.setEndtime(null);
        }
        if (StringUtils.isBlank(object.getStarttimepmTemp())) {
            dbObject.setStarttimepm(null);
        }
        if (StringUtils.isBlank(object.getEndtimepmTemp())) {
            dbObject.setEndtimepm(null);
        }
    }

    public String showBbsIframe() {
       String usercode="";
        try{
            usercode=((FUserinfo) this.getLoginUser()).getUsercode();
        }catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        // 用户信息
        oaUserinfo = oaUserinfoManager.getObjectById(usercode);// 获取当前用户信息

        // 模块信息（公开+模块类型）+子模块信息
        Map<String, Object> filterMap = new HashMap<String, Object>();
        FUserunit dept = sysUserManager.getUserPrimaryUnit(usercode);

        filterMap.put("openType", "T");// 公开
        filterMap.put("layouttypeFP", dept.getUnitcode());// 本部门+综合模块
        objList = oaBbsDashboardMag.getDashboardList(filterMap);
        // setTimeTage();
        return "bbsIframe";
    }

    public String showTVMainPage() {
        return "oaBbsShowTVMainPage";
    }

    /**
     * 讨论区模块信息
     * 
     * @return
     */
    public String showBbsMainPage() {
        String usercode="";
        try{
            usercode=((FUserinfo) this.getLoginUser()).getUsercode();
        }catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        // 用户信息
        oaUserinfo = oaUserinfoManager.getObjectById(usercode);// 获取当前用户信息

        // 统计信息
        oaBbsDashboard = oaBbsDashboardMag.getTotalSum();

        // 模块信息（公开+模块类型）+子模块信息
        Map<String, Object> filterMap = new HashMap<String, Object>();
        FUserunit dept = sysUserManager.getUserPrimaryUnit(usercode);

        filterMap.put("openType", "T");// 公开
        filterMap.put("layouttypeFP", dept.getUnitcode());// 本部门+综合模块
        filterMap.put("excludeStates", "D");
        objList = oaBbsDashboardMag.listObjects(filterMap);

        setTimeTage();
        // objList=oaBbsDashboardMag.getDashboardList(filterMap);

        return "bbsMainPage";
    }

    /**
     * 讨论区模块信息>讨论区子模块信息
     * 
     * @return
     */
    public String showBbsDisMainPage() {

        // 子模块信息
        object = oaBbsDashboardMag.getObjectById(object.getLayoutcode());

        // 设置讨论区模块开放状态
        Date now = new Date();
        // 讨论区模块开放状态优先级高于子模块开放状态

        if (null!=object&&"T".equals(object.getOpenType())
                && ("F".equals(object.getIsdocontral()) || "T".equals(object
                        .getIsdocontral())
                        && (oaBbsDashboardMag.isShowTime(object.getStarttime(),
                                now, object.getEndtime(), 0, 12) || oaBbsDashboardMag
                                .isShowTime(object.getStarttimepm(), now,
                                        object.getEndtimepm(), 12, 24)))) // 是否设置开发时间+判断上午下午
        {
            object.setIsShowTime("T");

            if ( null != object.getOaBbsDiscussions()) {
                for (OaBbsDiscussion d : object.getOaBbsDiscussions()) {
                    if ("T".equals(d.getOpenType())
                            && ("F".equals(d.getIsdocontral()) || "T".equals(d
                                    .getIsdocontral())
                                    && (oaBbsDashboardMag.isShowTime(
                                            d.getStarttime(), now,
                                            d.getEndtime(), 0, 12) || oaBbsDashboardMag
                                            .isShowTime(d.getStarttimepm(),
                                                    now, d.getEndtimepm(), 12,
                                                    24)))) // 是否设置开发时间+判断上午下午
                    {
                        d.setIsShowTime("T");
                    } else {
                        d.setOpenTimeTip("上午:"
                                + openTimeFormate(d.getStarttime()) + "~"
                                + openTimeFormate(d.getEndtime()));// 开放时间提示
                        d.setOpenTimeTipEnd("下午:"
                                + openTimeFormate(d.getStarttimepm()) + "~"
                                + openTimeFormate(d.getEndtimepm()) );
                    }
                    // 统计子模块当天发帖量
                    d.setTodayThemenum(oaBbsThemeManager.getTodayThemeNum(d
                            .getLayoutno()));

                }
            }
        } else {
            object.setOpenTimeTip("上午:"
                    + openTimeFormate(object.getStarttime()) + "~"
                    + openTimeFormate(object.getEndtime()) );// 开放时间提示
            object.setOpenTimeTipEnd("下午:"
                    + openTimeFormate(object.getStarttimepm()) + "~"
                    + openTimeFormate(object.getEndtimepm()) );
            
        }

        return "bbsDisMainPage";
    }

    public void setTimeTage() {
        if (null != objList && objList.size() > 0) {
            // 设置讨论区模块开放状态
            for (OaBbsDashboard o : objList) {
                Date now = new Date();

                // 讨论区模块开放状态优先级高于子模块开放状态
                if (null != o &&"T".equals(o.getOpenType())
                        && ("F".equals(o.getIsdocontral()) || "T".equals(o
                                .getIsdocontral())
                                && (oaBbsDashboardMag.isShowTime(
                                        o.getStarttime(), now, o.getEndtime(),
                                        0, 12) || oaBbsDashboardMag.isShowTime(
                                        o.getStarttimepm(), now,
                                        o.getEndtimepm(), 12, 24)))) // 是否设置开发时间+判断上午下午
                {
                    o.setIsShowTime("T");

                    if ( null != o.getOaBbsDiscussions()) {

                        for (OaBbsDiscussion d : o.getOaBbsDiscussions()) {
                            // 设置子讨论区模块开放状态
                            if ("T".equals(d.getOpenType())
                                    && ("F".equals(d.getIsdocontral()) || "T"
                                            .equals(d.getIsdocontral())
                                            && (oaBbsDashboardMag.isShowTime(
                                                    d.getStarttime(), now,
                                                    d.getEndtime(), 0, 12) || oaBbsDashboardMag
                                                    .isShowTime(
                                                            d.getStarttimepm(),
                                                            now,
                                                            d.getEndtimepm(),
                                                            12, 24)))) // 是否设置开发时间+判断上午下午
                            {
                                d.setIsShowTime("T");

                            }
                            else {
                                d.setOpenTimeTip("\n上午:"
                                        + openTimeFormate(d.getStarttime()) + "~"
                                        + openTimeFormate(d.getEndtime()) );// 开放时间提示
                                d.setOpenTimeTipEnd("\n下午:"
                                        + openTimeFormate(d.getStarttimepm()) + "~"
                                        + openTimeFormate(d.getEndtimepm()) );
                            }
                            // 统计子模块当天发帖量
                            d.setTodayThemenum(oaBbsThemeManager
                                    .getTodayThemeNum(d.getLayoutno()));
                        }

                    }
                } else {
                    o.setOpenTimeTip("\n上午:"
                            + openTimeFormate(o.getStarttime()) + "~"
                            + openTimeFormate(o.getEndtime()) );// 开放时间提示
                    o.setOpenTimeTipEnd("\n下午:"
                            + openTimeFormate(o.getStarttimepm()) + "~"
                            + openTimeFormate(o.getEndtimepm()) );
                }
            }
        }
    }

    /*private String getLoginUserCode() {

        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }*/

    private String openTimeFormate(Date date) {
        String openTimeTip = "--";
        
        if (null != date) {
            openTimeTip = DatetimeOpt.convertDateToString(date, "HH:ss");
        }
        return openTimeTip;
    }
    
    private JSONObject json;
    /**
     * 根据编号获取板块名称
     * @return
     */
    public String getLayoutName(){
         json = new JSONObject();

        object=oaBbsDashboardMag.getObject(object);
      
        json.put("status", object.getLayoutname()==null?"模块已删除":object.getLayoutname());
       
        return "options";
    }
    public JSONObject getJson(){
        return json;
    }
    
    public void setJson(JSONObject json){
        this.json=json;
    }
    /********* get set 方法 ********/
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

    public OaBbsDashboard getOaBbsDashboard() {
        return oaBbsDashboard;
    }

    public void setOaBbsDashboard(OaBbsDashboard oaBbsDashboard) {
        this.oaBbsDashboard = oaBbsDashboard;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public OaBbsThemeManager getOaBbsThemeManager() {
        return oaBbsThemeManager;
    }

    public void setOaBbsThemeManager(OaBbsThemeManager oaBbsThemeManager) {
        this.oaBbsThemeManager = oaBbsThemeManager;
    }

    public String getBodycode() {
        return bodycode;
    }

    public void setBodycode(String bodycode) {
        this.bodycode = bodycode;
    }

}
