package com.centit.oa.action;

import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.oa.Utils.ExcelUtils;
import com.centit.oa.po.OaArrangeweek;
import com.centit.oa.po.OaWorkSummary;
import com.centit.oa.service.OaArrangeweekManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.util.DateTimeUtil;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

public class OaArrangeweekAction extends OACommonBizAction<OaArrangeweek> {
    private static final long serialVersionUID = 1L;
    private List<OaWorkSummary> workSummaries;
    private String result;
    private List<FUnitinfo> unitlist; 
    private OaArrangeweekManager oaArrangeweekManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private IncomeDocManager  incomeDocManager;

    public IncomeDocManager getIncomeDocManager() {
        return incomeDocManager;
    }

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }

    public void setOaArrangeweekManager(OaArrangeweekManager basemgr) {
        oaArrangeweekManager = basemgr;
        this.setBaseEntityManager(oaArrangeweekManager);
    }
    

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }
    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }



    public List<OaWorkSummary> getWorkSummaries() {
        return workSummaries;
    }

    public void setWorkSummaries(List<OaWorkSummary> workSummaries) {
        this.workSummaries = workSummaries;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
    private void initparam(String modecode) {
        // TODO Auto-generated method stub
        super.moduleCode=modecode;
        super.generalOpt();
    }
    /**
     * 编辑
     * @return
     */
    public String regiseter() {
        String popPage = request.getParameter("popPage");
        FUserDetail user = ((FUserDetail) getLoginUser());
        request.setAttribute("usercode", user.getUsercode());
        //this.initparam("HYDJ");//归档手动登记通用配置信息
        //initUsers();
        //设置人员选择的json
        Set<String> users = SysUserFilterEngine.calcOperators(
                "D(ALL)",
                "1", null, null, null,
                user.getUsercode(), null);
        generalUserWithUnitJson("userjson", users);
        generalUserWithRankJson("rankjson", users);
        this.initCommonUsersConfig("commonJson");
        if (StringUtils.isBlank(popPage)) {
            popPage = "false";
        }
        request.setAttribute("unit",user.getPrimaryUnit());
        request.setAttribute("popPage", popPage);//意思？？？
        String djId = request.getParameter("djId");
        /*if (StringUtils.isNotBlank(djId)) {
            object = oaArrangeweekManager.getObjectById(djId);
        } else {
        }*/
        object = oaArrangeweekManager.getObjectById(djId);
        unitlist=sysUnitManager.getAllSubUnits("1");
        if (StringUtils.isBlank(request.getParameter("listType"))) {
            request.setAttribute("listType", "0");
        } else {
            request.setAttribute("listType", request.getParameter("listType"));
        }
        if (StringUtils.isBlank(request.getParameter("meetType"))) {
            request.setAttribute("meetType", "A");
        } else {
            request.setAttribute("meetType", request.getParameter("meetType"));
        }


        return "regiseter";
    }

    /**
     *@author：luo_c
     *@Description: 导出excel
     *@Date:15:02 2017/10/30 0030
     *@class:ExcelUtils
     */
    public void exportExcel() throws IOException, ParseException {
        this.getWorkSummariesJsons();
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
        String conditions = java.net.URLDecoder.decode(request.getParameter("otherparams"), "UTF-8");
        if(StringUtils.isNotEmpty(conditions)){
            conditions = conditions.charAt(conditions.lastIndexOf(":")+1)+"";
        }
        ExcelUtils.doPoi2Excel(conditions, workSummaries);
    }
    /**
     * 编辑保存方法
     * @return
     * @throws UnsupportedEncodingException
     */
    public String saveOaArrange() throws UnsupportedEncodingException {
        OaArrangeweek oaarrangeweek = new OaArrangeweek();
        if (StringUtils.isBlank(request.getParameter("saveParams"))) {//
            object.setCreatetime(request.getParameter("selectTime"));
            object.setWeektitle(request.getParameter("begindate"),request.getParameter("enddate"));
            FUserDetail user = ((FUserDetail) getLoginUser());
            oaarrangeweek.copyNotNullProperty(object);
            oaarrangeweek.setDjId(oaArrangeweekManager.getNextKey());
            oaarrangeweek.setCreater(user.getUsercode());
            if(StringUtils.isBlank( object.getDepno())){
                oaarrangeweek.setDepno(user.getPrimaryUnit());//责任处室 
            }
            oaarrangeweek.setCreatedate(new Date(System.currentTimeMillis()));
            oaArrangeweekManager.saveOaArrange(oaarrangeweek);
            object.clearProperties();
            return "regiseter";
        } else {//
            result = "OK";
            String saveParams = java.net.URLDecoder.decode(request.getParameter("saveParams"), "UTF-8");
            HashMap<String, Object> searchColumn = new HashMap<String, Object>();
            for (int i = 0; i < saveParams.split(";").length; i++) {
                String tmp = saveParams.split(";")[i];
                int mark = tmp.indexOf(":");
                if (StringUtils.isNotBlank(tmp.substring(mark + 1))) {
                    String key = tmp.substring(0, mark);
                    String value = tmp.substring(mark + 1);
                    if (StringUtils.isNotBlank(value)) {
                        searchColumn.put(key, value);
                    }
                }
            }
            if (searchColumn.containsKey("djId")) {
                oaarrangeweek = oaArrangeweekManager.getObjectById(String.valueOf(searchColumn.get("djId")));
            } else {
                oaarrangeweek.setDjId(oaArrangeweekManager.getNextKey());
            }
            oaarrangeweek.setWeektitle(String.valueOf(searchColumn.get("begindate")),
                    String.valueOf(searchColumn.get("enddate")));
            oaarrangeweek.setCreatetime(String.valueOf(searchColumn.get("selectTime")));
            oaarrangeweek.setWeektitle( String.valueOf(searchColumn.get("begindate")),
                    String.valueOf(searchColumn.get("enddate")));
            //选择人员修改非必填   增加非空判断   页面就不会显示null
            if(null == searchColumn.get("attendusers") || "null".equals(searchColumn.get("attendusers"))){
                oaarrangeweek.setAttendusers("");
            }else{
                oaarrangeweek.setAttendusers(String.valueOf(searchColumn.get("attendusers")));
            }
            if(null == searchColumn.get("attendleaders") || "null".equals(searchColumn.get("attendleaders"))){
                oaarrangeweek.setAttendleaders("");
            }else{
                oaarrangeweek.setAttendleaders(String.valueOf(searchColumn.get("attendleaders")));
            }


            oaarrangeweek.setAddress(String.valueOf(searchColumn.get("address")));
            oaarrangeweek.setRemark(String.valueOf(searchColumn.get("remark")));
            oaarrangeweek.setMeetType(String.valueOf(searchColumn.get("meetType")));
           // oaarrangeweek.setRemarkInfo(String.valueOf(searchColumn.get("remarkInfo")));
            oaarrangeweek.setItemtype(String.valueOf(searchColumn.get("itemtype")));
            if (searchColumn.containsKey("depno")) {
                FUserDetail user = ((FUserDetail) getLoginUser());
                oaarrangeweek.setDepno(String.valueOf(searchColumn.get("depno")));
                oaarrangeweek.setCreater(user.getUsercode());
                oaarrangeweek.setCreatedate(new Date(System.currentTimeMillis()));
            }
            oaarrangeweek.setState(String.valueOf(searchColumn.get("state")));





            if(null==searchColumn.get("office_bjName") || "null".equals(searchColumn.get("office_bjName"))){
                oaarrangeweek.setOffice_bjName("");
            }else{
                oaarrangeweek.setOffice_bjName(String.valueOf(searchColumn.get("office_bjName")));
            }
            if(null==searchColumn.get("office_djid") || "null".equals(searchColumn.get("office_djid"))){
                oaarrangeweek.setOffice_djid("");
            }else{
                oaarrangeweek.setOffice_djid(String.valueOf(searchColumn.get("office_djid")));
            }
            if(null==searchColumn.get("office_applyItemType") || "null".equals(searchColumn.get("office_applyItemType"))){
                oaarrangeweek.setOffice_applyItemType("");
            }else{
                oaarrangeweek.setOffice_applyItemType(String.valueOf(searchColumn.get("office_applyItemType")));
            }
            if(null==searchColumn.get("office_itemType") || "null".equals(searchColumn.get("office_itemType"))){
                oaarrangeweek.setOffice_itemType("");
            }else{
                oaarrangeweek.setOffice_itemType(String.valueOf(searchColumn.get("office_itemType")));
            }
            if(null==searchColumn.get("office_optType") || "null".equals(searchColumn.get("office_optType"))){
                oaarrangeweek.setOffice_optType("");
            }else{
                oaarrangeweek.setOffice_optType(String.valueOf(searchColumn.get("office_optType")));
            }




            try {
                oaArrangeweekManager.saveOaArrange(oaarrangeweek);
            } catch (Exception e) {
                result = "False";
            }
            return "JsonResult";
        }
    }

    public String list() {
        String listType = request.getParameter("listType");
        String meetType = request.getParameter("meetType");
        request.setAttribute("listType", listType);
        request.setAttribute("meetType", meetType);
        request.setAttribute("beginDate", DateTimeUtil.getWeekFirstDate());
        request.setAttribute("endDate", DateTimeUtil.getWeekLastDate());
        request.setAttribute("unitlist", sysUnitManager.getAllSubUnits("1"));
        if ("0".equals(listType)) {
            FUserDetail user = ((FUserDetail) getLoginUser());
            request.setAttribute("curDepno", user.getPrimaryUnit());
        }
        return LIST;
    }

    public String getWorkSummariesJsons() throws ParseException, IOException {
        HashMap<String, Object> searchColumn = new HashMap<String, Object>();
        String conditions = java.net.URLDecoder.decode(request.getParameter("otherparams"), "UTF-8");
        for (int i = 0; i < conditions.split(";").length; i++) {
            String tmp = conditions.split(";")[i];
            int mark = tmp.indexOf(":");
            if (StringUtils.isNotBlank(tmp.substring(mark + 1))) {
                String key = tmp.substring(0, mark);
                String value = tmp.substring(mark + 1);
                if (StringUtils.isNotBlank(value)) {
                    searchColumn.put(key, value);
                }
            }
        }
        FUserDetail user = ((FUserDetail) getLoginUser());
        searchColumn.put("username", user.getUsername());
       // request.setAttribute("usercode", user.getUsercode());
        //request.setAttribute("username", user.getUsername());
        workSummaries = oaArrangeweekManager.getWorkSummaries(searchColumn);
        if(workSummaries !=null && workSummaries.size() >0){
            for(OaWorkSummary oas : workSummaries){
                if(oas.getArrangeweekJsons() !=null && oas.getArrangeweekJsons().size() >0){
                    for(OaArrangeweek oa : oas.getArrangeweekJsons()){
                        if("HY".equals(oa.getOffice_itemType())){
                            oa.setColorType("1");
                        }
                    }
                }
            }
        }
        return "workSummary";
    }

    public String view() {
        object = oaArrangeweekManager.getObjectById(request.getParameter("djId"));
        if(null!=object){
            if("HY".equals(object.getOffice_itemType())){
                IncomeDoc incomeDoc=incomeDocManager.getObjectById(object.getOffice_djid());
                if(null==incomeDoc){
                    request.setAttribute("flag",false);
                }else{
                    request.setAttribute("flag",true);
                }
            }else{
                request.setAttribute("flag",true);
            }
        }


        String listType = request.getParameter("listType");
        FUserDetail user = ((FUserDetail) getLoginUser());
        if ("1".equals(listType)
                || ("0".equals(listType) && !object.getDepno().equals(
                        user.getPrimaryUnit()))) {
            object.setDepname(oaArrangeweekManager.getUnitName(object.getDepno()));
        }
        return VIEW;
    }

    public String delete() {
        result = "OK";
        try {
            oaArrangeweekManager.deleteObjectById(request.getParameter("djId"));
        } catch (Exception e) {
            result = "False";
        }
        return "JsonResult";
    }
    
    
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }

    /**
     * 对用户根据部门进行封装处理
     * @param attName
     * @param users
     */
    protected void generalUserWithUnitJson(String attName, Set<String> users){
        //获取所有人员，已经递归排序过，组成插件需要的结果集,该集合根节点只有一个
        if(oaPowerrolergroupManager == null){
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
            oaPowerrolergroupManager = (OaPowerrolergroupManager) ctx.getBean("oaPowerrolergroupManager");
        }

        List<Map<String,String>> unitUsers = oaPowerrolergroupManager.setUnitUser();
        if(unitUsers == null || unitUsers.size()==0 || users == null || users.size()==0){
            request.setAttribute(attName, new JSONArray());
            return;
        }
        /**
         * 声明tree 路由结果集
         * 格式 ：key为ztree的id，value为ztree的pid的拼接
         * 下面a是b的父，b是c的父，那么routes的数据结构如下
         * a ----a
         * b ----a,b
         * c ----a,b,c
         */
        Map<String,String> routes = new HashMap<String,String>();

        //遍历ztree的结果集，缓存路由需要的数据,注意该缓存策略建立在根节点只有一个的基础上，多个的话你自己想去
        for(int i=0;i<unitUsers.size();i++){
            Map<String,String> ztreeItem = unitUsers.get(i);
            if(i==0){
                routes.put(ztreeItem.get("id"),ztreeItem.get("id"));
            }else{
                routes.put(ztreeItem.get("id"),routes.get(ztreeItem.get("pId"))+","+ztreeItem.get("id"));
            }
        }

        //比对users和routes，看user是否是route某一项的结尾
        Set<String> routesAfterFilted = new HashSet<String>();
        for(String leafNode : users){
            for(Map.Entry<String,String> entry:routes.entrySet()){
                //这里前面添加”，“,防止过滤错误，比如过滤0的，而110也包含0,这样就会把110也过滤出来了
                if((","+entry.getValue()).endsWith(","+leafNode)){
                    routesAfterFilted.add(entry.getValue());
                    //因为一个用户只有一个主部门，这里为了提高效率，一旦过滤到了，直接跳出当前循环
                    break;
                }

            }
        }

        //将路由拆分下来，并去重，因为找到子没有用，还要把所有父给找出来
        Set<String> lastIdRes = new HashSet<String>();
        for(String route:routesAfterFilted){
            String[]temp = route.split(",");
            for(String id : temp){
                lastIdRes.add(id);
            }
        }

        JSONArray ja = new JSONArray();
        for(Map<String,String> item : unitUsers){
            for(String id:lastIdRes){
                if(item.get("id").equals(id)){
                    ja.add(item);
                    break;
                }
            }
        }
        request.setAttribute(attName, ja);
    }
    /**
     * 对用户岗位进行封装
     * @param attName
     * @param users
     */
    private void generalUserWithRankJson(String attName, Set<String> users){
        //获取所有人员，已经递归排序过，组成插件需要的结果集,该集合根节点只有一个
        if(oaPowerrolergroupManager == null){
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
            oaPowerrolergroupManager = (OaPowerrolergroupManager) ctx.getBean("oaPowerrolergroupManager");
        }

        List<Map<String,String>> unitUsers = oaPowerrolergroupManager.setRankUser();
        if(unitUsers == null || unitUsers.size()==0 || users == null || users.size()==0){
            request.setAttribute(attName, new JSONArray());
            return;
        }
        /**
         * 声明tree 路由结果集
         * 格式 ：key为ztree的id，value为ztree的pid的拼接
         * 下面a是b的父，b是c的父，那么routes的数据结构如下
         * a ----a
         * b ----a,b
         * c ----a,b,c
         */
        Map<String,String> routes = new HashMap<String,String>();

        //遍历ztree的结果集，缓存路由需要的数据,注意该缓存策略建立在根节点只有一个的基础上，多个的话你自己想去
        for(int i=0;i<unitUsers.size();i++){
            Map<String,String> ztreeItem = unitUsers.get(i);
            if(i==0){
                routes.put(ztreeItem.get("id"),ztreeItem.get("id"));
            }else{
                routes.put(ztreeItem.get("id"),routes.get(ztreeItem.get("pId"))+","+ztreeItem.get("id"));
            }
        }

        //比对users和routes，看user是否是route某一项的结尾
        Set<String> routesAfterFilted = new HashSet<String>();
        for(String leafNode : users){
            for(Map.Entry<String,String> entry:routes.entrySet()){
                //这里前面添加”，“,防止过滤错误，比如过滤0的，而110也包含0,这样就会把110也过滤出来了
                if((","+entry.getValue()).endsWith(","+leafNode)){
                    routesAfterFilted.add(entry.getValue());
                    //因为一个用户只有一个主部门，这里为了提高效率，一旦过滤到了，直接跳出当前循环
                    break;
                }

            }
        }

        //将路由拆分下来，并去重，因为找到子没有用，还要把所有父给找出来
        Set<String> lastIdRes = new HashSet<String>();
        for(String route:routesAfterFilted){
            String[]temp = route.split(",");
            for(String id : temp){
                lastIdRes.add(id);
            }
        }

        JSONArray ja = new JSONArray();
        for(Map<String,String> item : unitUsers){
            for(String id:lastIdRes){
                if(item.get("id").equals(id)){
                    ja.add(item);
                    break;
                }
            }
        }
        request.setAttribute(attName, ja);
    }


    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }


    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }

   /* public static void main(String[] args) {
        String str = "oa_jxw/sys/mainFrame!showMain.do#external_GCSYZAP";
        char str1 =  str.charAt(str.lastIndexOf("_")+1);
       System.out.println(str);
       System.out.println(str1);
    }*/
}
