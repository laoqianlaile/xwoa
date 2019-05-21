package com.centit.webservice.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.mip.po.CentitWebSerPowerRuntimeEntityVariable;
import com.centit.mip.po.OfficialNextProMIP;
import com.centit.mip.po.OfficialNextProMIP.DeptInfoMIP;
import com.centit.mip.po.OfficialNextProMIP.UserInfoMIP;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.TemplateFileManager;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitFilterEngine;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.service.SysVariableTranslate;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.WorkflowException;
import com.centit.workflow.sample.po.WfFlowDefine;
import com.centit.workflow.sample.po.WfNode;
import com.opensymphony.xwork2.ModelDriven;

public class CentitWebPowerRuntimeServiceImpl<T extends Object> extends
        BaseEntityExtremeAction<T> implements ModelDriven<T> {
    private static final long serialVersionUID = 1L;

    protected SysVariableTranslate businessVariable = null;
    protected FlowEngine flowEngine;
    protected FlowDefine flowDefine;
    protected FlowManager flowManager;
    protected OptProcInfoManager optProcInfoManager;
    protected OptBaseInfoManager optBaseInfoManager;
//    private static ThreadLocal<Long> curNodeInstId = new ThreadLocal<Long>(){}
    protected Long curNodeInstId;
    protected Long curFlowInstId;
    protected String curFlowPhase;
    private String curdjId;
    protected String moduleCode;
    protected String documentTemplateIds;
    protected String nodeCode;

    /* ------------------ 通用业务操作部分 * */

    protected GeneralModuleParam moduleParam;
    protected List<TemplateFile> templateList;
    protected GeneralModuleParamManager generalModuleParamManager;
    protected OptProcAttentionManager optProcAttentionManager;
    protected TemplateFileManager templateFileManager;

    protected OptProcInfo optProcInfo=new OptProcInfo();
  
    protected String roleCodefromFlow;// 角色代码：工作流程配置
    protected List<TemplateFile> templateFileList = null;
    private String templateFromNode;
    protected String nodeName;

    protected String teamUserCodes;// 办件用户代码
    protected String teamhqUserCodes;// 办件会签用户代码
    protected String zbOrgCode;// 主办单位代码
    protected String xbOrgCodes;// 协办单位代码
    protected String xbOrgNames;// 协办单位名称
    protected String attUserCodes;// 关注人员代码
    protected String attUserNames;
    protected String bjUserNames;
    protected String bjhqUserNames;
    protected String zbUserCodes;
    protected String zbUserNames;
    protected String xbUserCodes;
    protected String xbUserNames;

    protected String engineUserCodes;
    protected String engineUserNames;

    // ----centitwebservice接口
    protected String teamRoleCode;// 办件角色代码
    protected String zbOrgRoleCode;// 主办单位角色代码
    protected String xbOrgRoleCode;// 协办单位角色代码
    protected String zbUserRoleCode;// 办件用户
    protected String xbUserRoleCode;// 加载审核人角色代码
    protected String engineRoleCode;// 权限引擎

    protected List<UserInfoMIP> teamUserList;// 办件用户代码
    protected List<UserInfoMIP> attUserList;// 关注人员代码
    protected List<UserInfoMIP> zbUserList;// 办件用户代码
    protected List<UserInfoMIP> xbUserList;// 加载审核人用户代码
    protected List<UserInfoMIP> engineUserList;// 权限引擎

    protected List<DeptInfoMIP> zbOrgList;// 主办单位代码
    protected List<DeptInfoMIP> xbOrgList;// 协办单位代码

    protected List<OfficialNextProMIP> nextsteplist;// 流程下一步办理信息接口参数
    protected OaPowerrolergroupManager oaPowerrolergroupMag; 

    public List<OfficialNextProMIP> getNextsteplist() {
        return nextsteplist;
    }

    public void setNextsteplist(List<OfficialNextProMIP> nextsteplist) {
        this.nextsteplist = nextsteplist;
    }

    protected VuserTaskListOA userTaskInfo;

    protected FUserDetail fUserDetail;

    /**
     * 加载主办承办人 移动端不考虑暂存的情况
     */
    protected List<UserInfoMIP>  initZBUsersConfig(GeneralModuleParam moduleParam) {
        List<UserInfoMIP> zbUserList = new ArrayList<UserInfoMIP>();
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        /**
         * 根据参数是否需要 办件人员
         */
        // FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getHasZbUser())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getZbUserFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            List <String> userslist=generalUserWithUnitJson("zbUserList",  users);

            if (userslist != null) {
                
                 for (String user : userslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "usercode", user))){//过滤掉部门内容
                    UserInfoMIP userinfo = new UserInfoMIP();
                    userinfo.setUserid(user);
                    userinfo.setUsername(CodeRepositoryUtil.getValue(
                            "usercode", user));
                    zbUserList.add(userinfo);
                    }
                }
            }
        }
        return zbUserList;
    }

    /**
     * 加载审核人(协办人员)
     */
    protected List<UserInfoMIP>  initXBUsersConfig(GeneralModuleParam moduleParam) {
        List<UserInfoMIP> xbUserList = new ArrayList<UserInfoMIP>();
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }

        /**
         * 根据参数是否需要 办件人员
         */
        // FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getHasXbUser())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getXbUserFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            List <String> userslist=generalUserWithUnitJson("xbUserList",  users);

            if (userslist != null) {
                for (String user : userslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "usercode", user))){//过滤掉部门内容
                    UserInfoMIP userinfo = new UserInfoMIP();
                    userinfo.setUserid(user);
                    userinfo.setUsername(CodeRepositoryUtil.getValue(
                            "usercode", user));
                    xbUserList.add(userinfo);
                    }
                }
            }
        }
        
        return xbUserList;
    }

    /**
     * 加载办件角色
     */

    protected List<UserInfoMIP> initTeamUsersConfig(GeneralModuleParam moduleParam) {
        List<UserInfoMIP> teamUserList = new ArrayList<UserInfoMIP>();
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }

        /**
         * 根据参数是否需要 办件人员
         */
        // FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getAssignTeamRole())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getTeamRoleFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            List <String> userslist=generalUserWithUnitJson("teamUserList",  users);

            if (userslist != null) {
                for (String user : userslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "usercode", user))){
                        UserInfoMIP userinfo = new UserInfoMIP();
                        userinfo.setUserid(user);
                        userinfo.setUsername(CodeRepositoryUtil.getValue(
                                "usercode", user));
                        teamUserList.add(userinfo);
                    }
                }
            }
        }
        
        return teamUserList;
    }

    /**
     * 获得关注人名单
     */
    protected List<UserInfoMIP>  initAttUsersConfig(GeneralModuleParam moduleParam) {
        List<UserInfoMIP> attUserList = new ArrayList<UserInfoMIP>();
        /**
         * 根据参数是否需要 关注人 ，提供候选关注人列表
         */
        if (moduleParam != null && moduleParam.getHasAttention() != null
                && moduleParam.getHasAttention().equals("T")) {

            Set<String> attUsers = SysUserFilterEngine.calcOperators(
                    moduleParam.getAttentionFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);

            if (attUsers != null) {
                for (String user : attUsers) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "usercode", user))){
                    UserInfoMIP userinfo = new UserInfoMIP();
                    userinfo.setUserid(user);
                    userinfo.setUsername(CodeRepositoryUtil.getValue(
                            "usercode", user));
                    attUserList.add(userinfo);
                    }
                }
            }
        }
        return attUserList;
    }

    /**
     * 加载权限引擎角色
     */
    protected List<UserInfoMIP>  initEngineUsersConfig(GeneralModuleParam moduleParam) {
        List<UserInfoMIP> engineUserList = new ArrayList<UserInfoMIP>();
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }

        /**
         * 根据参数是否需要 办件人员
         */
        // FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getAssignEngineRole())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getEngineRoleFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            List <String> userslist=generalUserWithUnitJson("engineUserList",  users);

            if (userslist != null) {
                for (String user : userslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "usercode", user))){
                    UserInfoMIP userinfo = new UserInfoMIP();
                    userinfo.setUserid(user);
                    userinfo.setUsername(CodeRepositoryUtil.getValue(
                            "usercode", user));
                    engineUserList.add(userinfo);
                    }
                }
            }
        }
        return engineUserList;
    }



    /**
     * 加载协办单位
     */
    protected List<DeptInfoMIP> initZBOrgConfig(GeneralModuleParam moduleParam) {
        List<DeptInfoMIP>  zbOrgList = new ArrayList<DeptInfoMIP>();
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        // 主办默认选全部机构
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getZbOrgRoleCode())
                && StringUtils.isNotBlank(moduleParam.getXbOrgRoleCode())
                && moduleParam.getHasOrgRole().equals("T")) {

            Set<String> zbdepts = SysUnitFilterEngine.calcUnitsExp("all", null,
                    fUserDetail.getPrimaryUnit(), null, null, null);
            
            List <String> unitslist=generalUserWithUnitJson("zbOrgList",  zbdepts);
             if (unitslist != null) {
                for (String dept : unitslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "unitcode", dept))){
                    DeptInfoMIP deptinfo = new DeptInfoMIP();
                    deptinfo.setDeptid(dept);
                    deptinfo.setDeptname(CodeRepositoryUtil.getValue(
                            "unitcode", dept));
                    zbOrgList.add(deptinfo);
                    }
                }
            }
        }

        return zbOrgList;
    }

    /**
     * 加载协办单位
     * 
     * @return
     */
    protected List<DeptInfoMIP> initXBOrgConfig(GeneralModuleParam moduleParam) {
        List<DeptInfoMIP> xbOrgList = new ArrayList<DeptInfoMIP>();
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getXbOrgRoleCode())
                && moduleParam.getHasOrgRole().equals("T")) {

            Set<String> xbdepts = SysUnitFilterEngine.calcUnitsByExp(
                    "S(ALL)", null,
                    fUserDetail.getPrimaryUnit(), null, null, null);
            
            List <String> unitslist=generalUserWithUnitJson("zbOrgList",  xbdepts);
            if (unitslist != null) {
               for (String dept : unitslist) {
                    if("T".equals(CodeRepositoryUtil.getItemState( "unitcode", dept))){
                    DeptInfoMIP deptinfo = new DeptInfoMIP();
                    deptinfo.setDeptid(dept);
                    deptinfo.setDeptname(CodeRepositoryUtil.getValue(
                            "unitcode", dept));
                    xbOrgList.add(deptinfo);
                    }
                }
            }
        }
        return xbOrgList;
    }

    /**
     * 获取节点编号（包括流程编号）
     * 
     * @return false 没有找到对应的参数
     * @throws Exception
     */

    protected void extractFlowOptParam() throws Exception {
        try {
            if (userTaskInfo != null) {
                curdjId = userTaskInfo.getDjId();
                curNodeInstId = userTaskInfo.getNodeInstId();
                curFlowInstId = userTaskInfo.getFlowInstId();
                curFlowPhase = userTaskInfo.getFlowPhase();
                moduleCode = getmoduleCode4tOptParam(userTaskInfo.getOptParam());
                nodeCode = userTaskInfo.getNodeCode();

            }
        } catch (Exception e) {
            throw new Exception("错误信息：没有获取到正确的流程信息;");
        }

    }

    protected Set<Long> submitNode() throws WorkflowException {
        return submitNode(true);
    }

    protected Set<Long> submitNode(boolean closeBranch)
            throws WorkflowException {
        
        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext application = webApplicationContext
                .getServletContext();
        return flowEngine.submitOpt(curNodeInstId, fUserDetail.getUsercode(),
                fUserDetail.getPrimaryUnit(), closeBranch, getBusinessVariable(), application);
    }

    protected Set<Long> submitNode(String grantorCode, boolean closeBranch)
            throws WorkflowException {

        return flowEngine.submitOpt(curNodeInstId, fUserDetail.getUsercode(),
                grantorCode, fUserDetail.getPrimaryUnit(), closeBranch, null,
                null);
    }

    protected Set<Long> submitNode(ServletContext application)
            throws WorkflowException {
          flowEngine.submitOpt(curNodeInstId, fUserDetail.getUsercode(),
                fUserDetail.getPrimaryUnit(), true, getBusinessVariable(), application);
            return      optBaseInfoManager.getNextNodeInstends(curNodeInstId);
    }
    protected Set<Long> submitNode(String grantorCode)
            throws WorkflowException, Exception {
        return submitNode(grantorCode, true);
    }

    public SysVariableTranslate getBusinessVariable() {
        object=(T) optProcInfo;
      
            CentitWebSerPowerRuntimeEntityVariable<T> bo = new CentitWebSerPowerRuntimeEntityVariable<T>();
            bo.setModuleObject(object);
            bo.setServletRequest(object, null);
            businessVariable = bo;
      
        return businessVariable;
    }

    /**
     * 获取modulecode moduleCode=ZMHZ_BMFZR&deleteFlag=F
     * 
     * @param optParam
     * @return
     */
    protected static String getmoduleCode4tOptParam(String optParam) {
        String moduleCode = null;
        if (StringUtils.isNotBlank(optParam)) {
            String[] strArray = optParam.split("&");
            for (int i = 0; i < strArray.length; i++) {
                String strTemp = strArray[i];
                if (strTemp.contains("moduleCode=")) {
                    moduleCode = strTemp.replace("moduleCode=", "");
                    break;
                }
            }
        }
        return moduleCode;
    }

    /**
     * 根据usercodes获取usernames “u0001,9999999,u00932”---》"路人甲，系统管理员，路人乙"
     * unitcode获取unitnames “D0001,0,D00932”---》"机构一，顶级部门，机构二"
     * 
     * @param usercodes
     * @return
     */
    public String getNamesByCodes(String codes, String codeType) {
        String tempNames = "";
        if (codes != null) {
            String Temps[] = codes.split(",");
            for (String code : Temps) {

                if ((tempNames.endsWith(""))) {
                    tempNames = tempNames + ",";
                }
                tempNames = tempNames
                        + CodeRepositoryUtil.getValue(codeType, code);
            }
            tempNames = tempNames.substring(1, tempNames.length());
        }
        return tempNames;
    }
    
    
    protected List <String> generalUserWithUnitJson(String attName, Set<String> users){
        //获取所有人员，已经递归排序过，组成插件需要的结果集,该集合根节点只有一个
        if(oaPowerrolergroupMag == null){
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
            oaPowerrolergroupMag = (OaPowerrolergroupManager) ctx.getBean("oaPowerrolergroupManager");
        }
       
        List<Map<String,String>> unitUsers = oaPowerrolergroupMag.setUnitUser();
        if(unitUsers == null || unitUsers.size()==0 || users == null || users.size()==0){
           
            return null;
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
        
        List <String> ja =  new ArrayList<String>();
        for(Map<String,String> item : unitUsers){
            if(lastIdRes.contains(item.get("id"))){
                    ja.add(item.get("id"));
            }
        }
        
       return  ja;
    }
    
    /**
     * 多用于创建指定部门的多实例节点
     * @param nodeUnits
     * @return
     * @throws WorkflowException
     */
    protected Set<Long> submitOptWithAssignUnits(String nodeCode,Set<String>  unitcode)
            throws WorkflowException {
        
        FUserDetail curUser = ((FUserDetail) getLoginUser());
        
        Map<Long,Set<String>> nodeUnits=setAssignUnits(nodeCode,  unitcode);
        flowEngine.submitOptWithAssignUnits(curNodeInstId, curUser.getUsercode(),
                curUser.getPrimaryUnit(), getBusinessVariable(), nodeUnits,null);
        return   optBaseInfoManager.getNextNodeInstends(curNodeInstId);
    }
    
    /**
     * 指定部门创建节点 
     * 依据当前流程信息确定 指定节点nodeid
     * 依据当前节点信息 确定部门unitcode
     */
    public Map<Long,Set<String>>  setAssignUnits(String nodeCode,Set<String>  unitcode){
       
        FlowInstance flowInstance = flowEngine.getFlowInstById(this.getFlowInstId());//流程实例信息
        NodeInstance nodeinstance = flowEngine.getNodeInstById(this.getNodeInstId());//节点实例信息
        
        WfFlowDefine  flow=flowDefine.getFlowDefObject(flowInstance.getFlowCode(),flowInstance.getVersion());
        Set<WfNode> wfNodes = flow.getWfNodes();  //节点信息
        Map<Long,Set<String>> nodeUnits=null;
        Long nodeid=null;
        
        Set<String> set = new HashSet<String>();
        set.add(nodeinstance.getUnitCode());
        
        Set<String>  unit=null==unitcode ? set:unitcode;//节点所属部门
        if(null!=wfNodes&&wfNodes.size()>0){
            for(WfNode node:wfNodes){
                if(nodeCode.equals(node.getNodeCode())){//约定nodecode唯一
                    nodeid= node.getNodeId();
                    nodeUnits=new  HashMap<Long,Set<String>>();
                    nodeUnits.put(nodeid, unit);
                   break; 
                }
                
            } 
        }
       
        return   nodeUnits;
    }
    public Long getNodeInstId() {
        return curNodeInstId;
    }

    public void setNodeInstId(Long curNodeInstId) {
        this.curNodeInstId = curNodeInstId;
    }

    public Long getFlowInstId() {
        return curFlowInstId;
    }

    public void setFlowInstId(Long curFlowInstId) {
        this.curFlowInstId = curFlowInstId;
    }

    public String getFlowPhase() {
        return curFlowPhase;
    }

    public void setFlowPhase(String curFlowPhase) {
        this.curFlowPhase = curFlowPhase;
    }

    public String getDjId() {
        return curdjId;
    }

    public void setDjId(String djId) {
        this.curdjId = djId;
    }

    public List<TemplateFile> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateFile> templateList) {
        this.templateList = templateList;
    }

    public GeneralModuleParam getModuleParam() {
        return moduleParam;
    }

    public void setModuleParam(GeneralModuleParam moduleParam) {
        this.moduleParam = moduleParam;
    }

    public void setOptProcInfo(OptProcInfo optProcInfo) {
        this.optProcInfo = optProcInfo;
    }

    public OptProcInfo getOptProcInfo() {
        return optProcInfo;
    }

    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }

    public void setOptProcAttentionManager(
            OptProcAttentionManager optProcAttentionManager) {
        this.optProcAttentionManager = optProcAttentionManager;
    }

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public FlowManager getFlowManager() {
        return flowManager;
    }

    public TemplateFileManager getTemplateFileManager() {
        return templateFileManager;
    }

    public void setTemplateFileManager(TemplateFileManager templateFileManager) {
        this.templateFileManager = templateFileManager;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public OptProcAttentionManager getOptProcAttentionManager() {
        return optProcAttentionManager;
    }

    public String getTemplateFromNode() {
        return templateFromNode;
    }

    public void setTemplateFromNode(String templateFromNode) {
        this.templateFromNode = templateFromNode;
    }

    public String getRoleCodefromFlow() {
        return roleCodefromFlow;
    }

    public void setRoleCodefromFlow(String roleCodefromFlow) {
        this.roleCodefromFlow = roleCodefromFlow;
    }

    public OptBaseInfoManager getOptBaseInfoManager() {
        return optBaseInfoManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getXbOrgRoleCode() {
        return xbOrgRoleCode;
    }

    public void setXbOrgRoleCode(String xbOrgRoleCode) {
        this.xbOrgRoleCode = xbOrgRoleCode;
    }

    public List<UserInfoMIP> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<UserInfoMIP> teamUserList) {
        this.teamUserList = teamUserList;
    }

    public List<UserInfoMIP> getAttUserList() {
        return attUserList;
    }

    public void setAttUserList(List<UserInfoMIP> attUserList) {
        this.attUserList = attUserList;
    }

    public List<UserInfoMIP> getZbUserList() {
        return zbUserList;
    }

    public void setZbUserList(List<UserInfoMIP> zbUserList) {
        this.zbUserList = zbUserList;
    }

   
    public List<UserInfoMIP> getXbUserList() {
        return xbUserList;
    }

    public void setXbUserList(List<UserInfoMIP> xbUserList) {
        this.xbUserList = xbUserList;
    }

    public List<DeptInfoMIP> getZbOrgList() {
        return zbOrgList;
    }

    public void setZbOrgList(List<DeptInfoMIP> zbOrgList) {
        this.zbOrgList = zbOrgList;
    }

    public List<DeptInfoMIP> getXbOrgList() {
        return xbOrgList;
    }

    public void setXbOrgList(List<DeptInfoMIP> xbOrgList) {
        this.xbOrgList = xbOrgList;
    }

    public VuserTaskListOA getUserTaskInfo() {
        return userTaskInfo;
    }

    public void setUserTaskInfo(VuserTaskListOA userTaskInfo) {
        this.userTaskInfo = userTaskInfo;
    }

    public FUserDetail getfUserDetail() {
        return fUserDetail;
    }

    public void setfUserDetail(FUserDetail fUserDetail) {
        this.fUserDetail = fUserDetail;
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

    public String getTeamhqUserCodes() {
        return teamhqUserCodes;
    }

    public void setTeamhqUserCodes(String teamhqUserCodes) {
        this.teamhqUserCodes = teamhqUserCodes;
    }

    public String getBjhqUserNames() {
        return bjhqUserNames;
    }

    public void setBjhqUserNames(String bjhqUserNames) {
        this.bjhqUserNames = bjhqUserNames;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager basemgr) {
        oaPowerrolergroupMag = basemgr;
    }
    
}
