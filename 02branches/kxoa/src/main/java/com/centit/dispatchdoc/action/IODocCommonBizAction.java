/**
 * 
 */
package com.centit.dispatchdoc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.dispatchdoc.service.IncomeProjectManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.optmodel.PowerRuntimeEntityAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.service.SysUserManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.WfTeam;

/**
 * 收发文公用业务操作父类
 * 
 * @author ljy
 * @create 2013-9-23
 * @version 
 */
public class IODocCommonBizAction <T> extends PowerRuntimeEntityAction<T> implements ServletResponseAware {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected OptJspInfo jspInfo;
    protected SysUnitManager sysUnitManager;
    protected SysUserManager sysUserManager;
    protected String isSubFlow;//T:子流程
    protected IncomeProjectManager incomeProjectManager;
    protected SuppowerManager suppowerManager;

    protected HttpServletResponse response;
    protected OaPowerrolergroupManager oaPowerrolergroupMag; 
    /**
     * 保存办件角色
     */
    protected void saveTeamRolepeople() {
        this.saveTeamRole(teamRoleCode,teamUserCodes);
        
           /* flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), teamRoleCode);
            if (!StringUtils.isBlank(teamUserCodes)) {
                String[] teamUserCode = teamUserCodes.split(",");
                if (teamUserCode.length > 0) {
                    for (String userCode : teamUserCode) {
                        flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                                teamRoleCode, userCode);
                    }
                }
            }*/
        
    }
    
    /*
     * 保存主办承办人
     */
    protected void saveZBUser(){
        this.saveEngineRole(zbUserRoleCode,zbUserCodes);
    }
    
   /* *//**
     * 保存审核人
     *//*
    protected void saveAuditUser(){
        this.saveEngineRole(auditUserRoleCode,auditUserCodes);
    }*/
    
    /**
     * 保存办件角色（带参数）
     * @param roleCode
     * @param userCodes
     */
    protected void saveTeamRole(String roleCode,String userCodes){
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        if (!StringUtils.isBlank(userCodes)) {
            String[] teamUserCode = userCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                            roleCode, userCode,loginInfo.getUsercode());
                }
            }
        }
    }
    
    /**
     * 办件角色删除机制
     * @param roleCode
     * @param userCodes
     */
    protected void delectTeamRole(String roleCode){
         FUserDetail loginInfo = (FUserDetail) getLoginUser();
        //是否显示前一步办理信息-显示代表可以修改别人的数据
        if("T".equals(moduleParam.getHasPreIdea())){
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode);
        }else{
            //只能删除自己--loginInfo.getUsercode()为字段authdesc，需要重新写方法
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode,loginInfo.getUsercode());
        }
    }
    /**
     * 保存变量参数（引擎角色）
     * @param roleCode
     * @param userCodes
     */
    private void saveEngineRole(String roleCode,String userCodes){
        
        //如果未选择人员，则不保存
        if (!StringUtils.isBlank(userCodes)) {
            String[] engineUserCode = userCodes.split(",");
            Set<String> engineUserSet = new HashSet<String>();
            if (engineUserCode.length > 0) {
                for (String userCode : engineUserCode) {
                    engineUserSet.add(userCode);
                }
            }
            flowEngine.saveFlowNodeVariable(curNodeInstId, roleCode, engineUserSet);
        }
    }
    /**
     *保存角色引擎
     */
    protected void saveEngineRolepeople() {
        if (!StringUtils.isBlank(engineUserCodes)) {
            String[] engineUserCode = engineUserCodes.split(",");
            Set<String> engineUserSet = new HashSet<String>();
            if (engineUserCode.length > 0) {
                for (String userCode : engineUserCode) {
                    engineUserSet.add(userCode);
                }
            }
            flowEngine.saveFlowNodeVariable(curNodeInstId, engineRoleCode, engineUserSet);
        }
    }
    
    /**
     *保存指定角色人员
     * @param teamrolecode 小组
     * @param teamusercodes 角色
     */
    protected void saveTeamRolepeople(String teamusercodes,String teamrolecode) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), teamrolecode);
        if (!StringUtils.isBlank(teamusercodes)) {
            String[] teamUserCode = teamusercodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                            teamrolecode, userCode,loginInfo.getUsercode());
                }
            }
        }
    }
    
    protected String actionName;// 对应form的actionName
    protected String submitOptURI;// 对应form的提交方法名
    protected String saveOptURI;// 对应form的保存方法名
    /**
     * 如果使用初始化，则方法的三个参数缺一不可！
     * 
     * @param actionname
     *            对应form的actionName
     * @param submitopturi
     *            对应form的提交方法名
     * @param saveopturi
     *            对应form的保存方法名
     */
    public void initalGenneralOpt(String actionname, String submitopturi,
            String saveopturi) {
        this.actionName = actionname;
        this.submitOptURI = submitopturi;
        this.saveOptURI = saveopturi;
    }
    
    /**
     * 用于加载通用配置
     * 
     * @return
     */
    public String generalOpt() {
        try {
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);//
            extractFlowOptParam();
            super.initTeamUsersConfig();
            super.initTemplateConfig();
            super.initTemplateFromNode();
            /**
             * 配置当前步骤名称
             */
            NodeInstance nodeInst = flowEngine.getNodeInstById(curNodeInstId);
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            nodeName = nodeInfo.getNodeName();
            /**
             * 通用运行配置，是否引用上一步骤的办理意见
             */
            if ("T".equals(moduleParam.getHasPreIdea())) {
                NodeInstance nodeInst1 = flowEngine.getNodeInstById(curNodeInstId);
                if(nodeInst1.getPrevNodeInstId()!=null){
                optProcInfo = optProcInfoManager.getObjectByNodeInstId(nodeInst1.getPrevNodeInstId());
                }
            }
            return "optTrans";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e.getCause());
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
    }
    /**
     * 基础业务信息查询
     * @return
     */
    protected OptBaseInfo getOptBaseInfo(){
        OptBaseInfo optBase = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        if(optBase == null){
            optBase = new OptBaseInfo();
        }
        return optBase;
    }
    
    /**
     * 获取上一步骤的办理过程信息
     * @return
     */
    protected OptProcInfo getPreTransInfo() {
        NodeInstance nodeInst = flowEngine.getNodeInstById(curNodeInstId);
        OptProcInfo procInfo = null;
        if (nodeInst != null && nodeInst.getPrevNodeInstId() != null) {
            procInfo = optProcInfoManager.getObjectByNodeInstId(nodeInst
                    .getPrevNodeInstId());
        }
        return procInfo;
    }

    /**
     * FRAME设置公用部分
     * 
     * @param frameList
     * @param moduleDesc
     * @return
     */
    protected void setFrameList(List<OptHtmlFrameInfo> frameList,
            String moduleDesc) {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        frameList.add(getProcFrame(optBaseInfo.getDjId())); // 过程信息
        frameList.add(GeneralOperatorAction.getStuffListFrame(optBaseInfo.getDjId()));//办件附件信息
        jspInfo = new OptJspInfo();
        jspInfo.setTitle(moduleDesc);
        jspInfo.setFrameList(frameList);
    }
    
    /**
     * 查看过程信息
     * 
     * @param djid
     * @return
     */
     protected OptHtmlFrameInfo getCommonTransFrame(String djId,String nodeCode, String flowPhase,String methed) {
         OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
         transFrameInfo.setFrameId("transFrame");
         transFrameInfo.setFrameLegend("办理信息");
         transFrameInfo
                 .setFrameSrc("/dispatchdoc/ioDocTasksExcute!"+methed+".do?djId="
                         + djId
                         + "&flowInstId="
                         + super.getFlowInstId()
                         + "&nodeInstId="
                         + super.getNodeInstId()
                         + "&moduleCode=" + moduleCode
                         + "&documentTemplateIds=" + documentTemplateIds
                         + "&nodeCode=" + nodeCode
                         + "&flowPhase=" + flowPhase);
        return transFrameInfo;
    }
     /**
      * 查看过程信息
      * 
      * @param djid
      * @return
      */
      protected OptHtmlFrameInfo getCommonTransFrame(String djId,String nodeCode, String flowPhase) {
          OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
          transFrameInfo.setFrameId("transFrame");
          transFrameInfo.setFrameLegend("办理信息");
          transFrameInfo
                  .setFrameSrc("/dispatchdoc/ioDocTasksExcute!doOpt.do?djId="
                          + djId
                          + "&flowInstId="
                          + super.getFlowInstId()
                          + "&nodeInstId="
                          + super.getNodeInstId()
                          + "&moduleCode=" + moduleCode
                          + "&documentTemplateIds=" + documentTemplateIds
                          + "&nodeCode=" + nodeCode
                          + "&flowPhase=" + flowPhase);
         return transFrameInfo;
     }
      
      
      /**
       * 查看附件信息
       * 
       * @param djid
       * @return
       */
       protected OptHtmlFrameInfo getCommonStuffFrame(String djId,String nodeCode, String flowPhase) {
           OptHtmlFrameInfo stuffFrameInfo = new OptHtmlFrameInfo();
           stuffFrameInfo.setFrameLegend("附件");
           stuffFrameInfo.setFrameId("stuffFrame");
           stuffFrameInfo
                   .setFrameSrc("/powerruntime/generalOperator!listStuffs.do?djId=" +djId);
          return stuffFrameInfo;
      }
     public OptHtmlFrameInfo getHandleApprovalTransFrame(String djId,
             String nodeCode) {
         OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
         transFrameInfo.setFrameId("transFrame");
         transFrameInfo
                 .setFrameSrc("/dispatchdoc/ioDocTasksExcute!doOpt_HandleApproval.do?djId="
                         + djId
                         + "&flowInstId="
                         + super.getFlowInstId()
                         + "&nodeInstId="
                         + super.getNodeInstId()
                         + "&moduleCode=" + moduleCode
                         + "&documentTemplateIds=" + documentTemplateIds
                         + "&nodeCode=" + nodeCode
                         + "&flowPhase=" + super.getFlowPhase());
        return transFrameInfo;
     }
    /**
     * 查看过程信息
     * 
     * @param djid
     * @return
     */
    protected OptHtmlFrameInfo getProcFrame(String djId) {
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        procFrameInfo.setFrameId("procFrame");
        procFrameInfo.setFrameSrc("/powerruntime/generalOperator!listIdeaLogs.do?djId=" + djId);
        procFrameInfo.setFrameLegend("过程信息");
        procFrameInfo.setFrameHeight("300px");
        return procFrameInfo;
    }

    @Override
    protected void initTeamUsersConfig() {
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();
        
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        List<WfTeam> userCodes = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getTeamRoleCode())) {
            //是否显示前一步办理信息
            if("T".equals(moduleParam.getHasPreIdea())){
            userCodes = flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
                    moduleParam.getTeamRoleCode());
            }else{//只显示自己保存的数据
                userCodes = flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
                        moduleParam.getTeamRoleCode(),fUserDetail.getUsercode()); 
            }
        } 
        
        String tempArr[] = this.subStrUsers(userCodes);
        
        teamUserCodes = tempArr[0];
        bjUserNames = tempArr[1];

        /**
         * 根据参数是否需要 办件人员
         */
        if (moduleParam != null) {
           

            if ("T".equals(moduleParam.getAssignTeamRole())) {
                Set<String> usersofleader=optBaseInfoManager.getUsersOfleader(fUserDetail.getUsercode());
                if(usersofleader!=null&&"T".equals(moduleParam.getHasJZ())&&("sw_czsh".equals(moduleParam.getModuleCode()))){//如果人员存在分管部门，则获取分管部门下的人员
                generalUserWithUnitJson("userjson", usersofleader);
                }else{
                    Set<String> users = SysUserFilterEngine.calcOperators(
                            moduleParam.getTeamRoleFilter(),
                            fUserDetail.getPrimaryUnit(), null, null, null,
                            fUserDetail.getUsercode(), null);
                    generalUserWithUnitJson("userjson", users);
                }
            }
        }
    }
    
    /**
     * 加载权限引擎角色
     */
    protected void initEngineUsersConfig() {
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        List<WfTeam> userCodes = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getEngineRoleCode())) {
            FUserDetail loginInfo = (FUserDetail) getLoginUser();
            userCodes = flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
                    moduleParam.getEngineRoleCode(),loginInfo.getUsercode());
        } 
        
       String[] tempArr = this.subStrUsers(userCodes);
       
       engineUserCodes = tempArr[0];
       engineUserNames = tempArr[1];
        
        /**
         * 根据参数是否需要 办件人员
         */
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getAssignEngineRole())) {   
            Set<String> usersofleader=optBaseInfoManager.getUsersOfleader(fUserDetail.getUsercode());
            if(usersofleader!=null&&"T".equals(moduleParam.getHasJZ())&&("sw_czsh".equals(moduleParam.getModuleCode()))){//如果人员存在分管部门，则获取分管部门下的人员
            generalUserWithUnitJson("engineUserJson", usersofleader);
            }else{
                Set<String> users = SysUserFilterEngine.calcOperators(
                        moduleParam.getEngineRoleFilter(),
                        fUserDetail.getPrimaryUnit(), null, null, null,
                        fUserDetail.getUsercode(), null);
                generalUserWithUnitJson("engineUserJson", users);
            }          
        }       
    }
    
    protected void generalUserWithUnitJson(String attName, Set<String> users){
        //获取所有人员，已经递归排序过，组成插件需要的结果集,该集合根节点只有一个
        if(oaPowerrolergroupMag == null){
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
            oaPowerrolergroupMag = (OaPowerrolergroupManager) ctx.getBean("oaPowerrolergroupManager");
        }
       
        List<Map<String,String>> unitUsers = oaPowerrolergroupMag.setUnitUser();
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
            for(Entry<String,String> entry:routes.entrySet()){
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
     *人员树形式展示办件角色json
     *
     * @param attName
     * @param users
     */
    protected void generalUserWithUnitJsonOld(String attName, Set<String> users) {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
        // 查询出所有部门
        List<FUnitinfo> unitArr = sysUnitManager.listObjects(filterMap);
        // 查询出所有用户
        List<FUserinfo> userArr = sysUserManager.listObjects(filterMap);
        // 根据办件人员过滤掉没有用的用户
        List<FUserinfo> userTempArr = new ArrayList<FUserinfo>();

        for (FUserinfo fu : userArr) {
            if (users.contains(fu.getUsercode())) {
                String primaryUnitCode = sysUserManager.getUserPrimaryUnit(
                        fu.getUsercode()).getUnitcode();
                fu.setPrimaryUnit(primaryUnitCode);
                userTempArr.add(fu);
            }
        }

        List<FUnitinfo> unitTempArr = new ArrayList<FUnitinfo>();
        filterUnitOfUser(unitTempArr, new StringBuffer(","), userTempArr,
                unitArr);

        JSONArray ja = new JSONArray();
        for (FUnitinfo fu : unitTempArr) {
            JSONObject jo = new JSONObject();
            jo.put("id", fu.getUnitcode());
            jo.put("pId",
                    "0".equals(fu.getParentunit()) ? null : fu.getParentunit());
            jo.put("name", fu.getUnitname());
            jo.put("icon", "../scripts/inputZtree/img/diy/unit.png");
            jo.put("type", "false");// 部门不可被选中
            // 一级目录下菜单展开
            jo.put("open", "0".equals(fu.getParentunit()) ? "true" : "false");
            jo.put("unitorder", fu.getUnitorder());
            ja.add(jo);
        }
        for (FUserinfo fu : userTempArr) {
            JSONObject jo = new JSONObject();
            jo.put("id", fu.getUsercode());
            jo.put("pId", fu.getPrimaryUnit());
            jo.put("name", fu.getUsername());
            jo.put("icon", "../scripts/inputZtree/img/diy/person.png");
            jo.put("userorder", fu.getUserorder());
            ja.add(jo);
        }
        request.setAttribute(attName, ja);
    }
    
    /**
     * 根据办件用户获取他的部门
     * 
     * @param resultUnits
     *            存放部门的集合作为结果集
     * @param unitCodesExistInResult
     *            已经存在结果集中的，防止由子找父时，不能的子可能有相同的父节点，这里声明一个变量，这样方便判断，格式“,xxxx,xxxx,
     *            ”
     * @param requestUsers
     *            办件人员集合
     * @param allUnits
     *            所有部门，从中筛选有用的
     */
    private void filterUnitOfUser(List<FUnitinfo> resultUnits,
            StringBuffer unitCodesExistInResult, List<FUserinfo> requestUsers,
            List<FUnitinfo> allUnits) {
        for (FUserinfo user : requestUsers) {
            for (FUnitinfo unit : allUnits) {
                if (user.getPrimaryUnit() != null
                        && user.getPrimaryUnit().equals(unit.getUnitcode())) {
                    // 如果结果集中不存在該部门，我们可以将它加入到结果集中去
                    if (unitCodesExistInResult.toString().indexOf(
                            "," + unit.getUnitcode() + ",") < 0) {
                        unitCodesExistInResult.append(unit.getUnitcode())
                                .append(",");
                        resultUnits.add(unit);
                        // 根据子部门去找他的父级部门
                        findUnitByChild(resultUnits, unitCodesExistInResult,
                                allUnits, unit.getParentunit());
                    }
                    break;
                }
            }
        }
    }
    /**
     * 根据子部门找上级部门，递归获取
     * 
     * @param resultUnits
     *            存放部门的集合作为结果集
     * @param unitCodesExistInResult
     *            已经存在结果集中的，防止由子找父时，不能的子可能有相同的父节点，这里声明一个变量，这样方便判断，格式“,xxxx,xxxx,
     *            ”
     * @param allUnits
     *            所有部门，从中筛选有用的
     * @param unitCode
     *            部门编码
     */
    private void findUnitByChild(List<FUnitinfo> resultUnits,
            StringBuffer unitCodesExistInResult, List<FUnitinfo> allUnits,
            String unitCode) {
        for (FUnitinfo fu : allUnits) {
            if (unitCode.equals(fu.getUnitcode())) {
                // 如果结果集中不存在該部门，我们可以将它加入到结果集中去
                if (unitCodesExistInResult.toString().indexOf(
                        "," + fu.getUnitcode() + ",") < 0) {
                    unitCodesExistInResult.append(fu.getUnitcode()).append(",");
                    resultUnits.add(fu);
                    // 开始递归找父级
                    findUnitByChild(resultUnits, unitCodesExistInResult,
                            allUnits, fu.getParentunit());
                }
                break;
            }
        }
    }

    
    protected void initTeamUsersConfig(GeneralModuleParam moduleParam,
            String order) {

        List<WfTeam> userCodes = null;
        if (this.getFlowInstId() != null) {
            userCodes = flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
                    moduleParam.getTeamRoleCode());
        }

        String tempArr[] = this.subStrUsers(userCodes);

        String teamUserCodes = tempArr[0];
        String bjUserNames = tempArr[1];
        request.setAttribute("teamUserCodes" + order, teamUserCodes);
        request.setAttribute("bjUserNames" + order, bjUserNames);
        /**
         * 根据参数是否需要 办件人员
         */
        if (moduleParam != null) {
            FUserDetail fUserDetail = (FUserDetail) getLoginUser();

            if ("T".equals(moduleParam.getAssignTeamRole())) {
                Set<String> users = SysUserFilterEngine.calcOperators(
                        moduleParam.getTeamRoleFilter(),
                        fUserDetail.getPrimaryUnit(), null, null, null,
                        fUserDetail.getUsercode(), null);
                generalUserWithUnitJson("userjson" + order, users);
            }
        }
    }
    
    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
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

    public void setIncomeProjectManager(IncomeProjectManager incomeProjectManager) {
        this.incomeProjectManager = incomeProjectManager;
    }
    
    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getActionName() {
        return actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public String getSubmitOptURI() {
        return submitOptURI;
    }
    public void setSubmitOptURI(String submitOptURI) {
        this.submitOptURI = submitOptURI;
    }
    public String getSaveOptURI() {
        return saveOptURI;
    }
    public void setSaveOptURI(String saveOptURI) {
        this.saveOptURI = saveOptURI;
    }
    public SuppowerManager getSuppowerManager() {
        return suppowerManager;
    }
    public void setOaPowerrolergroupManager(OaPowerrolergroupManager basemgr) {
        oaPowerrolergroupMag = basemgr;
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
//            int strSize = (int) str.length();
            byte[] b = str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public OptHtmlFrameInfo getLeaderTransFrame(String djId, String nodeCode,
            String flowPhase) {
        // TODO Auto-generated method stub
        return null;
    }
}
