package com.centit.powerruntime.optmodel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.RiskInfo;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.RiskInfoManager;
import com.centit.powerruntime.service.TemplateFileManager;
import com.centit.support.utils.HtmlFormUtils;
import com.centit.support.utils.StringRegularOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.SysVariableTranslate;
import com.centit.webservice.util.CommonOptCodeUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.FlowVariable;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.WorkflowException;
import com.centit.workflow.sample.po.WfFlowDefine;
import com.centit.workflow.sample.po.WfNode;
import com.centit.workflow.sample.po.WfOrganize;
import com.centit.workflow.sample.po.WfTeam;
import com.opensymphony.xwork2.ModelDriven;

public class PowerRuntimeEntityAction<T extends Object> extends
        BaseEntityExtremeAction<T> implements ModelDriven<T> {
    private static final long serialVersionUID = 1L;

    protected SysVariableTranslate businessVariable = null;
    protected FlowEngine flowEngine;
    protected FlowManager flowManager;
    protected OptProcInfoManager optProcInfoManager;
    protected OptBaseInfoManager optBaseInfoManager;
    protected Long curNodeInstId;
    protected Long curFlowInstId;
    protected String curFlowPhase;
    protected String curdjId;
    private String nextOptUrl;
    protected String moduleCode;
    protected String documentTemplateIds;
    protected String isDelete;//T：可以删除，F：不可删除，默认可删

    protected String processNote;// 各个节点的意见集合
    
    protected FlowDefine flowDefine;

    /* ------------------ 通用业务操作部分 * */
    protected String teamRoleCode;// 办件角色代码
    protected String teamUserCodes;// 办件用户代码
    protected String zbOrgRoleCode;// 主办单位角色代码
    protected String zbOrgCode;// 主办单位代码
    protected String xbOrgRoleCode;// 协办单位角色代码
    protected String xbOrgCodes;// 协办单位代码
    protected String xbOrgNames;//协办单位名称
    protected GeneralModuleParam moduleParam;
    protected List<TemplateFile> templateList;
    protected GeneralModuleParamManager generalModuleParamManager;
    protected OptProcAttentionManager optProcAttentionManager;
    protected TemplateFileManager templateFileManager;
    private RiskInfoManager riskInfoManager;
    protected String attUserCodes;// 关注人员代码
    protected String attUserNames;
    protected String bjUserNames;
    protected OptProcInfo optProcInfo;
    private  Map<String,Set<String>> teamMap;
    private  Map<String,Set<String>> orgMap;
    protected String roleCodefromFlow;// 角色代码：工作流程配置
    protected List<TemplateFile> templateFileList = null;
    private String templateFromNode;
    
    //公用JSON数据存放
    protected Object optCommonBizJson;
    protected Object optProcInfoJSON;

    public Object getOptProcInfoJSON() {
        return optProcInfoJSON;
    }
    public void setOptProcInfoJSON(Object optProcInfoJSON) {
        this.optProcInfoJSON = optProcInfoJSON;
    }
    protected String flowTime;
    protected String nodeTime;
    protected String flowDefTime;
    protected String nodeName;
    protected String affairTitle;
    protected String nodePromiseTime;
    
    
    protected SysUnitManager sysUnitManager;
    protected SysUserManager sysUserManager;
    
    protected void initFlowTime(){
        FlowInstance flowInstance = flowEngine.getFlowInstById(this.getFlowInstId());
        flowTime = flowInstance.getTimeLimitStr();
        NodeInstance nodeinstance = flowEngine.getNodeInstById(this.getNodeInstId());
        nodeTime = nodeinstance.getTimeLimitStr();
        
        FlowNodeInfo wfnode = flowEngine.getNodeInfoById(nodeinstance.getNodeId());
        nodeName = wfnode.getNodeName();
        nodePromiseTime = nodeinstance.getPromiseTimeStr();
//        String worktime = workCalendar.getWorkTime(
//                nodeInstance.getCreateTime(), new Date()).getTimeSpanDesc();
//        request.setAttribute("worktime", worktime);
        FlowDescribe flowdefine = flowEngine.getFlowDefObject(
                flowInstance.getFlowCode(), flowInstance.getVersion());
        //String nodeDefTime = flowEngine.getNodeDefTimelimit(this.getNodeInstId());
        flowDefTime = flowdefine.getTimeLimit();
        //request.setAttribute("nodeDefTime", nodeDefTime);
        OptBaseInfo baseInfo = optBaseInfoManager.getOptBaseByFlowId(this.getFlowInstId());
        affairTitle = baseInfo.getTransaffairname();
    }
    /**
     * 验证办件合法性，保存日志
     * @param djId
     * @param fuser
     * @return
     */
    protected String listVOptBaseListVail(String djId, FUserDetail fuser){
        String vail=optBaseInfoManager.saveVOptBaseListVail(djId, fuser);
        if(vail!=null){
        String vails[]=vail.split(",");
        if(vails.length>1){
            if(vails[0].equals("false")){
                request.setAttribute("errortype", vails[1]);
            }
        }else
            return ERROR;
        }
        return vail;
    }
    
    
    
    
    /**
     * 获得关注人名单
     */
    protected void initAttUsersConfig() {
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

       /* List<OptProcAttention> attList = optProcAttentionManager
                .getAttentionsByDjId(curdjId);*/
        List<OptProcAttention> attList = optProcAttentionManager.
                listAttentionByFlowInstId(curdjId, fUserDetail.getUsercode());
         
        attUserCodes = "";
        attUserNames = "";
        if (attList != null && attList.size() != 0) {
            for (OptProcAttention att : attList) {
                if (attUserCodes.endsWith(""))
                    attUserCodes = attUserCodes + ",";
                attUserCodes = attUserCodes + att.getUserCode();
                if (attUserNames.endsWith(""))
                    attUserNames = attUserNames + ",";
                attUserNames = attUserNames
                        + CodeRepositoryUtil.getValue("usercode",
                                att.getUserCode());
            }

            attUserCodes = attUserCodes.substring(1, attUserCodes.length());
            attUserNames = attUserNames.substring(1, attUserNames.length());
        }

        /**
         * 根据参数是否需要 关注人 ，提供候选关注人列表
         */
        if (moduleParam != null && moduleParam.getHasAttention() != null
                && moduleParam.getHasAttention().equals("T")) {

            Set<String> attUsers = SysUserFilterEngine.calcOperators(
                    moduleParam.getAttentionFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            this.generalUserJson("attentionJson", attUsers);
        }
    }

    /**
     * 加载协办单位
     */
    protected void initXbOrgConfig() {
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        List<WfOrganize> orgCodes = null;
        List<WfOrganize> zbOrgCodes = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getXbOrgRoleCode())
                && moduleParam.getHasOrgRole().equals("T")) {
            //FUserDetail loginInfo = (FUserDetail) getLoginUser();
            orgCodes = flowEngine.viewFlowOrganizeList(this.getFlowInstId(),
                    moduleParam.getXbOrgRoleCode(), "");
            zbOrgCodes = flowEngine.viewFlowOrganizeList(this.getFlowInstId(),
                    moduleParam.getZbOrgRoleCode(), "");
        }

        xbOrgCodes = "";
        xbOrgNames = "";

        if (zbOrgCodes != null && zbOrgCodes.size() > 0) {
            for (WfOrganize orgObj : zbOrgCodes) {
                zbOrgCode = orgObj.getUnitCode();
                break;
            }
            
        }
        if (orgCodes != null && orgCodes.size() > 0) {
            for (WfOrganize orgCode : orgCodes) {
                if ((xbOrgCodes.endsWith(""))) {
                    xbOrgCodes = xbOrgCodes + ",";
                }
                xbOrgCodes = xbOrgCodes + orgCode.getUnitCode();
                if ((xbOrgNames.endsWith(""))) {
                    xbOrgNames = xbOrgNames + ",";
                }
                xbOrgNames = xbOrgNames
                        + CodeRepositoryUtil.getValue("unitcode", orgCode.getUnitCode());
            }
            xbOrgCodes = xbOrgCodes.substring(1, xbOrgCodes.length());
            xbOrgNames = xbOrgNames.substring(1, xbOrgNames.length());
        }
    }
    
    protected String engineRoleCode;
    
    protected String engineUserCodes;
    
    protected String engineUserNames;
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
            /*Set<String> usersofleader=optBaseInfoManager.getUsersOfleader(fUserDetail.getUsercode());
            if(usersofleader==null){   */         
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getEngineRoleFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            this.generalUserJson("engineUserJson", users);
            /*}else{//如果人员存在分管部门，则获取分管部门下的人员
            this.generalUserJson("engineUserJson", usersofleader);
            }*/
        }
    }
    
    
    /**
     * 加载权限引擎角色
     */
    protected void initEngineUsersConfig_new() {
        
        String tempCodes = "";
        String tempNames = "";
        
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        FlowVariable var = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getEngineRoleCode())) {
           NodeInstance nodeInst =  flowEngine.getNodeInstById(this.getNodeInstId());
            var = flowEngine.viewNodeVariable(nodeInst.getFlowInstId(),nodeInst.getRunToken(),
                    moduleParam.getEngineRoleCode());
        } 
        
        if (var != null) {
            String usersStr = var.getVarValue();

            if (usersStr != null) {
                String usersTemp[] = usersStr.split(";");
                for (String user : usersTemp) {

                    if ((tempNames.endsWith(""))) {
                        tempNames = tempNames + ",";
                    }

                    tempNames = tempNames
                            + CodeRepositoryUtil.getValue("usercode", user);
                }
                tempCodes = usersStr.replaceAll(";", ",");
                tempNames = tempNames.substring(1, tempNames.length());
            }
        }

       engineUserCodes = tempCodes;
       engineUserNames = tempNames;
        
        /**
         * 根据参数是否需要 办件人员
         */
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getAssignEngineRole())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getEngineRoleFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            this.generalUserJson("engineUserJson", users);
        }
    }
    
    /**
     * 拼接USER数据
     * @param userCodes
     * @param tempCodes
     * @param tempNames
     */
    protected String[] subStrUsers(List<WfTeam> userCodes) {
        String tempCodes = "";
        String tempNames = "";
        String[] tempArr = new String[2];

        if (userCodes != null && userCodes.size() > 0) {
            for (WfTeam wf_team : userCodes) {

                if ((tempCodes.endsWith(""))) {
                    tempCodes = tempCodes + ",";
                }

                tempCodes = tempCodes + wf_team.getUserCode();

                if ((tempNames.endsWith(""))) {
                    tempNames = tempNames + ",";
                }

                tempNames = tempNames
                        + CodeRepositoryUtil.getValue("usercode",
                                wf_team.getUserCode());
            }

            tempCodes = tempCodes.substring(1, tempCodes.length());
            tempNames = tempNames.substring(1, tempNames.length());

        }
        tempArr[0] = tempCodes;
        tempArr[1] = tempNames;
        return tempArr;
    }
    
    /**
     * 生成USER JSON数据
     * @param attName
     * @param users
     */
    private void generalUserJson(String attName,Set<String> users){
        JSONArray userjson = new JSONArray();
        if (users != null) {
            for (String user : users) {
                
                String username = CodeRepositoryUtil.getValue("usercode",
                        user);
                String userorder = CodeRepositoryUtil.getValue("userorder",
                        user);
                String loginName = CodeRepositoryUtil.getValue("userloginname",
                        user);
                if(!userorder.equals(user)){
                    JSONObject usermap = new JSONObject();
                    usermap.put("name", username);
                    usermap.put("nodeID", user);
                    usermap.put("loginName", loginName);
                    usermap.put("belongId", "-1");
                    usermap.put("levle", 2);
                    usermap.put("userorder", userorder == null ? "0": userorder);
                    userjson.add(usermap);
                }
            }
        }
        userjson = sortJSONArray(userjson);
        request.setAttribute(attName, userjson);
    }
    
    protected String zbUserCodes;
    protected String zbUserNames;
    protected String zbUserRoleCode;
    /**
     * 加载主办承办人
     */
    protected void initZBUsersConfig() {
    
        String tempCodes = "";
        String tempNames = "";
        
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        FlowVariable var = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getZbUserRoleCode())) {
           NodeInstance nodeInst =  flowEngine.getNodeInstById(this.getNodeInstId());
            var = flowEngine.viewNodeVariable(nodeInst.getFlowInstId(),nodeInst.getRunToken(),
                    moduleParam.getZbUserRoleCode());
        } 
        
        if (var != null) {
            String usersStr = var.getVarValue();

            if (usersStr != null) {
                String usersTemp[] = usersStr.split(";");
                for (String user : usersTemp) {

                    if ((tempNames.endsWith(""))) {
                        tempNames = tempNames + ",";
                    }

                    tempNames = tempNames
                            + CodeRepositoryUtil.getValue("usercode", user);
                }
                tempCodes = usersStr.replaceAll(";", ",");
                tempNames = tempNames.substring(1, tempNames.length());
            }
        }
        
        zbUserCodes = tempCodes;
        zbUserNames= tempNames;
        /**
         * 根据参数是否需要 办件人员
         */
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getHasZbUser())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getZbUserFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            this.generalUserWithUnitJson("zbuserjson", users);
        }
    }
    
    protected String xbUserCodes;
    protected String xbUserNames;
    protected String xbUserRoleCode;
    
    /**
     * 加载审核人(协办人员)
     */
    protected void initXBUsersConfig() {
        
        String tempCodes = "";
        String tempNames = "";
        
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        FlowVariable var = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getXbUserRoleCode())) {
           NodeInstance nodeInst =  flowEngine.getNodeInstById(this.getNodeInstId());
            var = flowEngine.viewNodeVariable(nodeInst.getFlowInstId(),nodeInst.getRunToken(),
                    moduleParam.getXbUserRoleCode());
        } 
        if (var != null) {
            String usersStr = var.getVarValue();

            if (usersStr != null) {
                String usersTemp[] = usersStr.split(";");
                for (String user : usersTemp) {

                    if ((tempNames.endsWith(""))) {
                        tempNames = tempNames + ",";
                    }

                    tempNames = tempNames
                            + CodeRepositoryUtil.getValue("usercode", user);
                }
                tempCodes = usersStr.replaceAll(";", ",");
                tempNames = tempNames.substring(1, tempNames.length());
            }
        }
        
        xbUserCodes = tempCodes;
        xbUserNames= tempNames;
       
        /**
         * 根据参数是否需要 办件人员
         */
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getHasXbUser())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getXbUserFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            this.generalUserWithUnitJson("xbuserjson", users);
        }
    }
        
    
    
    
    
    
    /**
     * 加载办件角色
     */
    protected void initTeamUsersConfig() {
        /**
         * 获得办件角色人名单
         */
        if (moduleParam == null) {
            moduleParam = new GeneralModuleParam();
        }
        List<WfTeam> userCodes = null;
        if (this.getFlowInstId() != null
                && StringUtils.isNotBlank(moduleParam.getTeamRoleCode())) {
            userCodes = flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
                    moduleParam.getTeamRoleCode());
        } 
        
        String tempArr[] = this.subStrUsers(userCodes);
        
        teamUserCodes = tempArr[0];
        bjUserNames = tempArr[1];

        /**
         * 根据参数是否需要 办件人员
         */
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();

        if ("T".equals(moduleParam.getAssignTeamRole())) {
            Set<String> users = SysUserFilterEngine.calcOperators(
                    moduleParam.getTeamRoleFilter(),
                    fUserDetail.getPrimaryUnit(), null, null, null,
                    fUserDetail.getUsercode(), null);
            
            generalUserWithUnitJson("userjson", users);
        }
    }
    
    /**
     * 获取主办协办部门参数
     */
    protected void initPFUnit(String roleCode, String nodeCode) {

            List<FUnitinfo> unitList = null;
            unitList = sysUnitManager.listObjects();
            request.setAttribute("unitList", unitList);

    }
    
    /**
     * 保存办件角色
     */
    protected void saveTeamRolepeople() {
        flowEngine.deleteFlowWorkTeam(this.getFlowInstId(), teamRoleCode);
        if (!StringUtils.isBlank(teamUserCodes)) {
            String[] teamUserCode = teamUserCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(this.getFlowInstId(),
                            teamRoleCode, userCode);
                }
            }
        }
    }
    /**
     * 保存主办人
     */
    protected void saveZBTeamRolepeople() {
        flowEngine.deleteFlowWorkTeam(this.getFlowInstId(), zbUserRoleCode);
        if (!StringUtils.isBlank(zbUserCodes)) {
            String[] teamUserCode = zbUserCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(this.getFlowInstId(),
                            zbUserRoleCode, userCode);
                }
            }
        }
    }
    /**
     * 保存协办人
     */
    protected void saveXBTeamRolepeople() {
        flowEngine.deleteFlowWorkTeam(this.getFlowInstId(), xbUserRoleCode);
        if (!StringUtils.isBlank(xbUserCodes)) {
            String[] teamUserCode = xbUserCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(this.getFlowInstId(),
                            xbUserRoleCode, userCode);
                }
            }
        }
    }
    /**
     * 保存权限引擎
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
            flowEngine.saveFlowNodeVariable(curNodeInstId, engineRoleCode,
                    engineUserSet);
        }
    }
    
    /**
     *  保存主办单位
     */
    protected  void   saveZbOrg(){
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();   
       
        if (StringUtils.isNotBlank(zbOrgRoleCode)
                && StringUtils.isNotBlank(zbOrgCode)) {
            flowEngine.deleteFlowOrganizeByAuth(curNodeInstId,
                    zbOrgRoleCode, fUserDetail.getUsercode());
            flowEngine.assignFlowOrganize(curNodeInstId,
                    zbOrgRoleCode, zbOrgCode, fUserDetail.getUsercode());
            Set<String> sValues = new HashSet<String>();
            sValues.add(zbOrgCode);
            flowEngine.saveFlowNodeVariable(curNodeInstId,
                    zbOrgRoleCode, sValues);
        }
    }
    
    /**
     *  保存主办单位
     */
    protected  void   saveXbOrg(){
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();   
       
        // 保存协办单位
        if (StringUtils.isNotBlank(xbOrgCodes)) {
            flowEngine.deleteFlowOrganizeByAuth(curNodeInstId,
                    xbOrgRoleCode, fUserDetail.getUsercode());
            String[] orgCodes = xbOrgCodes.split("[,]");
            if (orgCodes != null && orgCodes.length > 0) {
                flowEngine.assignFlowOrganize(curNodeInstId,
                        xbOrgRoleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)),
                        fUserDetail.getUsercode());
                flowEngine.saveFlowNodeVariable(curNodeInstId,
                        xbOrgRoleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)));
            }
        }
    }

    public JSONArray sortJSONArray(JSONArray jsonArr) {
        //JSONArray temp = jsonArr;
        for (int i = 0; i < jsonArr.size(); i++) {
            long l = Long.parseLong(jsonArr.getJSONObject(i).get("userorder")
                    .toString());
            for (int j = i + 1; j < jsonArr.size(); j++) {
                long nl = Long.parseLong(jsonArr.getJSONObject(j)
                        .get("userorder").toString());
                if (l > nl) 
                {
                    JSONObject temp = jsonArr.getJSONObject(j);
                    l = nl;
                    
                    jsonArr.set(j, jsonArr.getJSONObject(i));
                    jsonArr.set(i, temp);
                }
            }
        }
        return jsonArr;
    }

    /*
     * 获取办件人员
     */
    public void initUsers() {
        List<FUserinfo> users = CodeRepositoryUtil.getAllUsers("A");
        JSONArray userjson = new JSONArray();
        if (users != null) {
            for (FUserinfo user : users) {
                JSONObject usermap = new JSONObject();
                usermap.put("name", user.getUsername());
                usermap.put("nodeID", user.getUsercode());
                usermap.put("belongId", "-1");
                usermap.put("levle", 2);
                userjson.add(usermap);
            }
        }
        request.setAttribute("userjson", userjson);
    }

    /**
     * 根据参数是否有风险点 ，提供风险点的风险内控手段与结果的维护
     */
    protected void initRiskConfig() {

        if (moduleParam != null && moduleParam.getRiskId() != null) {
            // 判断是新增还是修改
            RiskInfo riskInfo = new RiskInfo();
            if (StringUtils.isBlank(optProcInfo.getRisktype())) {

                riskInfo = riskInfoManager.getObjectById(moduleParam
                        .getRiskId());
            } else {
                riskInfo.setRisktype(optProcInfo.getRisktype());
                riskInfo.setRiskdes(optProcInfo.getRiskdesc());
                riskInfo.setRiskdeal(optProcInfo.getRiskresult());
            }
            moduleParam.setRiskInfo(riskInfo);
        }
    }

    /**
     * 根据是否可以生产公文 ，确定需要编辑的文档模板
     */
    protected void initTemplateConfig() {
        if (moduleParam != null && moduleParam.getHasDocument() != null
                && moduleParam.getHasDocument().equals("T")) {// 是否存在模版

            String templates = moduleParam.getDocumentTemplateIds();
            if (!StringUtils.isBlank(templates)) {
                String[] tempArr = templates.split("[,]");
                templateList = new ArrayList<TemplateFile>();
                for (String recordId : tempArr) {
                    TemplateFile temp = templateFileManager
                            .getTempByRecord(recordId);
                    templateList.add(temp);
                }
            } else {
                templateList = templateFileManager
                        .listTemplateByType(moduleParam.getDocumentType());
            }
        }
    }

    /**
     * 遇到步骤需要两个以上指定文书的（如回执、意见书等），可从节点参数中配置， 这样能解决通用配置步骤以及不走通用配置的步骤的多文书问题。
     * 配置说明：参数变量名documentTemplateIds：多个文书的，模板编号之间用“,”,隔开;
     */
    protected void initTemplateFromNode() {
        // String param = request.getParameter("documentTemplateIds");
        if (StringUtils.isNotBlank(documentTemplateIds)
                && !"null".equalsIgnoreCase(documentTemplateIds)) {
            templateFileList = new ArrayList<TemplateFile>();
            String[] params = documentTemplateIds.split(",");
            for (String tempId : params) {
                TemplateFile templateFile = templateFileManager
                        .getTempByRecord(tempId);
                templateFileList.add(templateFile);
            }

            if (templateFileList.size() > 0) {
                templateFromNode = "TRUE";
            }
        }
    }

    public String getDocumentTemplateIds() {
        return documentTemplateIds;
    }

    public void setDocumentTemplateIds(String documentTemplateIds) {
        this.documentTemplateIds = documentTemplateIds;
    }

    /**
     * 初始化风险点
     * 
     * @return
     */
    protected void initalOptProcInfo() {
        optProcInfo = optProcInfoManager.getObjectByNodeInstId(curNodeInstId);
        if (optProcInfo == null) {
            optProcInfo = new OptProcInfo();
            optProcInfo.setIsrisk("F");// 给出默认风险点:为没有风险点
        } else {
            if (StringUtils.isBlank(optProcInfo.getIsrisk()))
                optProcInfo.setIsrisk("F");// 给出默认风险点:为没有风险点
        }

    }

    /**
     * 如果下一步骤含本人，直接进入下一步骤； 如果不含本人，提示办理完毕，返回待办件列表
     */
    public String nextStep() {
        try {
            NodeInstance nit = flowEngine.getNodeInstById(curNodeInstId);
            FlowInstance inst = flowManager
                    .getFlowInstance(nit.getFlowInstId());

            long nextNodeInstId = 0l;
            for (NodeInstance nodeInst : inst.getActiveNodeInstances()) {
                String url = flowManager.getNodeOptUrl(
                        nodeInst.getNodeInstId(),
                        ((FUserDetail) getLoginUser()).getUsercode());
                if (url != null && nextNodeInstId < nodeInst.getNodeInstId()
                        && !"".equals(url.trim())) {
                    nextNodeInstId = nodeInst.getNodeInstId();
                    nextOptUrl = "/" + url;
                }
            }
            if (nextNodeInstId > 0l)
                return "gotoNext";
            else
                return "refreshTasks";

        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "进入下一阶段办理步骤出错，详见系统日志。");
            return ERROR;
        }
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
        // this.request.getSession().getServletContext();
    }

    /**
     * 需要设定自定义 变量解释器是需要调用这个函数
     * 
     * @param businessVariable
     */
    public void setBusinessVariable(SysVariableTranslate businessVariable) {
        this.businessVariable = businessVariable;
    }

    /**
     * 从request的参数中获取节点编号（包括流程编号）
     * 
     * @return false 没有找到对应的参数
     */
    @SuppressWarnings("unchecked")
    protected boolean extractFlowOptParam() {
        boolean bHasExtract = false;
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Object oParam = paramMap.get("nodeInstId");
            if (oParam != null) {
                String sNodeInstId = HtmlFormUtils.getParameterString(oParam);
                if (sNodeInstId != null
                        && StringRegularOpt.isNumber(sNodeInstId)) {
                    curNodeInstId = Long.valueOf(sNodeInstId);
                }
            }
            oParam = paramMap.get("flowInstId");
            if (oParam != null) {
                String sFlowInstId = HtmlFormUtils.getParameterString(oParam);
                if (sFlowInstId != null
                        && StringRegularOpt.isNumber(sFlowInstId)) {
                    curFlowInstId = Long.valueOf(sFlowInstId);
                }
            }

            oParam = paramMap.get("flowPhase");
            if (oParam != null) {
                String sFlowPhase = HtmlFormUtils.getParameterString(oParam);
                if (StringUtils.isNotBlank(sFlowPhase)) {
                    curFlowPhase = sFlowPhase;
                }
            }
         
            /*
             * oParam = paramMap.get("optParam"); if (oParam != null) {
             * curOptParam = StringBaseOpt.getParameterString(oParam); }
             */
            bHasExtract = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bHasExtract;
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

    protected Set<Long> submitNode() throws WorkflowException {
        return submitNode(true);
    }
    protected Set<Long> submitNodeWithAssignUnits() throws WorkflowException {
        return submitNode();
    }
    public SysVariableTranslate getBusinessVariable() {
        if (businessVariable == null) {
            PowerRuntimeEntityVariable<T> bo = new PowerRuntimeEntityVariable<T>();
            bo.setModuleObject(object);
            bo.setServletRequest(request);
            businessVariable = bo;
        }
        return businessVariable;
    }

    /**
     * 设置业务数据对象
     * 
     * @param optObject
     */
    public void setOptObject(Object optObject) {
        PowerRuntimeEntityVariable<T> bo = new PowerRuntimeEntityVariable<T>();
        bo.setModuleObject(object, optObject);
        bo.setServletRequest(request);
        businessVariable = bo;
    }

    protected Set<Long> submitNode(boolean closeBranch)
            throws WorkflowException {
        extractFlowOptParam();
        FUserDetail curUser = ((FUserDetail) getLoginUser());
        flowEngine.submitOpt(curNodeInstId, curUser.getUsercode(),
                curUser.getPrimaryUnit(), closeBranch, getBusinessVariable(),
                this.request.getSession().getServletContext());
        return   optBaseInfoManager.getNextNodeInstends(curNodeInstId);
    }
    
    /**
     * 多用于创建指定部门的多实例节点
     * @param nodeUnits
     * @return
     * @throws WorkflowException
     */
    protected Set<Long> submitOptWithAssignUnits(String nodeCode,Set<String>  unitcode)
            throws WorkflowException {
        extractFlowOptParam();
        
        FUserDetail curUser = ((FUserDetail) getLoginUser());
        
        Map<Long,Set<String>> nodeUnits=setAssignUnits(nodeCode,  unitcode);
        flowEngine.submitOptWithAssignUnits(curNodeInstId, curUser.getUsercode(),
                curUser.getPrimaryUnit(), getBusinessVariable(), nodeUnits,
                this.request.getSession().getServletContext());
        return   optBaseInfoManager.getNextNodeInstends(curNodeInstId);
    }
    
 
   
    protected Set<Long> submitNode(String grantorCode, boolean closeBranch)
            throws WorkflowException {
        extractFlowOptParam();
        FUserDetail curUser = ((FUserDetail) getLoginUser());

        return flowEngine.submitOpt(curNodeInstId, curUser.getUsercode(),
                grantorCode, curUser.getPrimaryUnit(), closeBranch,
                getBusinessVariable(), this.request.getSession()
                        .getServletContext());
    }

    protected Set<Long> submitNode(String grantorCode) throws WorkflowException {
        return submitNode(grantorCode, true);
    }

    public void addSqClFrame(Long nodeInstId, Long groupId,
            List<OptHtmlFrameInfo> frameList, String dj_id) {

        groupId = ((groupId == null) ? 103L : groupId);
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("stuffsFrame");
        viewFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!gotoCFsqcl.do?stuffInfo.djId="
                        + dj_id
                        + "&stuffInfo.nodeInstId="
                        + nodeInstId
                        + "&suppowerstuffinfo.groupId=" + groupId);
        // List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        frameList.add(viewFrameInfo);

    }
    
    /**
     * 各业务节点可调用此方法生成业务JSON，如果数据复杂，可建视图PO
     * @param obj
     */
    protected void initCommonBizJSON(Object obj) {
        JsonConfig jsonConfig = new JsonConfig();

        // 解决过滤空值问题
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName,
                    Object fieldValue) {
                return null == fieldValue;
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);

        optCommonBizJson = JSONObject.fromObject(obj, jsonConfig);
    }
    
    
    // 简单操作的ajax方法，使用json作为数据传输
    public void ajaxResponseJson(HttpServletResponse response, JSONObject json) {
        this.ajaxResponseText(response, json.toString());
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
    
    
    private void generalUserWithUnitJson(String attName, Set<String> users) {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
        // 查询出所有部门
        List<FUnitinfo> unitArr = sysUnitManager.listObjects(filterMap);
        // 查询出所有用户
        List<FUserinfo> userArr = sysUserManager.listObjects(filterMap);
        // 根据办件人员过滤掉没有用的用户
        List<FUserinfo> userTempArr = new ArrayList<FUserinfo>();
        if(null!=users){
            for (FUserinfo fu : userArr) {
                if (users.contains(fu.getUsercode())) {
                    String primaryUnitCode = sysUserManager.getUserPrimaryUnit(
                            fu.getUsercode()).getUnitcode();
                    fu.setPrimaryUnit(primaryUnitCode);
                    userTempArr.add(fu);
                }
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
    /**
     *  简单操作的ajax方法，使用文本作为数据传输（主要在需要同步时使用）
     * @param response
     * @param text
     */
    @SuppressWarnings("resource")
    public void ajaxResponseText(HttpServletResponse response, String text) {
        // response 返回页面信息
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = null;

        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            writer = response.getWriter();
            writer.write(text); // 发送json数据
        } catch (Exception e) {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            try {
                writer = response.getWriter();
                writer.write(text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    /**
     * 查看页面是否显示编辑按钮
     * --统一入口：从配置获取该节点是否支持配置
     * --各节点可根据业务需求在方法中枚举扩充
     * @param nodeCode
     * @return
     */
    public  boolean  checkCanEdit(){
        boolean  flage=false;
        
        GeneralModuleParam  moduleParam=generalModuleParamManager.getObjectById(moduleCode);
        
        if(null!=moduleParam&&"T".equals(moduleParam.getCaneditopt())){
            
            flage=true;
        }
       
        return flage;
        
    } 
    
    
    /**
     * 获取公文查询类型
     * 1.管理员查询所有
     * 2.办公室人员 查询 GW_NATURE_SUP
     * 3.其他人员 查询 GW_NATURE_DEP
     * 
     * @return
     */
    protected String getGWNature() {
        
        FUserinfo user= (FUserinfo) this.getLoginUser();
        if (CommonOptCodeUtil.TOP_UNITCODE.equals(user.getPrimaryUnit())){
            return null;
        }
        else if (CommonOptCodeUtil.GW_NATURE_SUP_UNITCODE.equals(user.getPrimaryUnit())){
            return CommonOptCodeUtil.GW_NATURE_SUP;
        }else {
            return CommonOptCodeUtil.GW_NATURE_DEP;
        }
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

    public String getTeamRoleCode() {
        return teamRoleCode;
    }

    public void setTeamRoleCode(String teamRoleCode) {
        this.teamRoleCode = teamRoleCode;
    }

    public String getTeamUserCodes() {
        return teamUserCodes;
    }

    public void setTeamUserCodes(String teamUserCodes) {
        this.teamUserCodes = teamUserCodes;
    }

    public String getAttUserCodes() {
        return attUserCodes;
    }

    public void setAttUserCodes(String attUserCodes) {
        this.attUserCodes = attUserCodes;
    }

    public String getAttUserNames() {
        return attUserNames;
    }

    public void setAttUserNames(String attUserNames) {
        this.attUserNames = attUserNames;
    }

    public String getBjUserNames() {
        return bjUserNames;
    }

    public void setBjUserNames(String bjUserNames) {
        this.bjUserNames = bjUserNames;
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

    public void setRiskInfoManager(RiskInfoManager riskInfoManager) {
        this.riskInfoManager = riskInfoManager;
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

    public void setNextOptUrl(String nextOptUrl) {
        this.nextOptUrl = nextOptUrl;
    }

    public String getNextOptUrl() {
        return nextOptUrl;
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

    public String getProcessNote() {
        return processNote;
    }

    public List<TemplateFile> getTemplateFileList() {
        return templateFileList;
    }

    public void setTemplateFileList(List<TemplateFile> templateFileList) {
        this.templateFileList = templateFileList;
    }

    public void setProcessNote(String processNote) {
        this.processNote = processNote;
    }

    public OptProcAttentionManager getOptProcAttentionManager() {
        return optProcAttentionManager;
    }

    public RiskInfoManager getRiskInfoManager() {
        return riskInfoManager;
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

    public Map<String,Set<String>> getTeamMap() {
        return teamMap;
    }

    public void setTeamMap(Map<String,Set<String>> teamMap) {
        this.teamMap = teamMap;
    }

    public Map<String,Set<String>> getOrgMap() {
        return orgMap;
    }

    public void setOrgMap(Map<String,Set<String>> orgMap) {
        this.orgMap = orgMap;
    }
    
    public Object getOptCommonBizJson() {
        return optCommonBizJson;
    }

    public void setOptCommonBizJson(Object optCommonBizJson) {
        this.optCommonBizJson = optCommonBizJson;
    }
    public String getFlowTime() {
        return flowTime;
    }
    public void setFlowTime(String flowTime) {
        this.flowTime = flowTime;
    }
    public String getNodeTime() {
        return nodeTime;
    }
    public void setNodeTime(String nodeTime) {
        this.nodeTime = nodeTime;
    }
    public String getFlowDefTime() {
        return flowDefTime;
    }
    public void setFlowDefTime(String flowDefTime) {
        this.flowDefTime = flowDefTime;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getAffairTitle() {
        return affairTitle;
    }
    public void setAffairTitle(String affairTitle) {
        this.affairTitle = affairTitle;
    }
    public String getNodePromiseTime() {
        return nodePromiseTime;
    }
    public void setNodePromiseTime(String nodePromiseTime) {
        this.nodePromiseTime = nodePromiseTime;
    }
    public String getXbOrgRoleCode() {
        return xbOrgRoleCode;
    }
    public void setXbOrgRoleCode(String xbOrgRoleCode) {
        this.xbOrgRoleCode = xbOrgRoleCode;
    }
    public String getXbOrgCodes() {
        return xbOrgCodes;
    }
    public void setXbOrgCodes(String xbOrgCodes) {
        this.xbOrgCodes = xbOrgCodes;
    }
    public String getXbOrgNames() {
        return xbOrgNames;
    }
    public void setXbOrgNames(String xbOrgNames) {
        this.xbOrgNames = xbOrgNames;
    }
    public String getZbOrgRoleCode() {
        return zbOrgRoleCode;
    }
    public void setZbOrgRoleCode(String zbOrgRoleCode) {
        this.zbOrgRoleCode = zbOrgRoleCode;
    }
    public String getZbOrgCode() {
        return zbOrgCode;
    }
    public void setZbOrgCode(String zbOrgCode) {
        this.zbOrgCode = zbOrgCode;
    }
    public String getEngineRoleCode() {
        return engineRoleCode;
    }
    public void setEngineRoleCode(String engineRoleCode) {
        this.engineRoleCode = engineRoleCode;
    }
    public String getEngineUserCodes() {
        return engineUserCodes;
    }
    public void setEngineUserCodes(String engineUserCodes) {
        this.engineUserCodes = engineUserCodes;
    }
    public String getEngineUserNames() {
        return engineUserNames;
    }
    public void setEngineUserNames(String engineUserNames) {
        this.engineUserNames = engineUserNames;
    }
    public String getZbUserCodes() {
        return zbUserCodes;
    }
    public void setZbUserCodes(String zbUserCodes) {
        this.zbUserCodes = zbUserCodes;
    }
    public String getZbUserNames() {
        return zbUserNames;
    }
    public void setZbUserNames(String zbUserNames) {
        this.zbUserNames = zbUserNames;
    }
    public String getZbUserRoleCode() {
        return zbUserRoleCode;
    }
    public void setZbUserRoleCode(String zbUserRoleCode) {
        this.zbUserRoleCode = zbUserRoleCode;
    }
   

    public String getXbUserCodes() {
        return xbUserCodes;
    }
    public void setXbUserCodes(String xbUserCodes) {
        this.xbUserCodes = xbUserCodes;
    }
    public String getXbUserNames() {
        return xbUserNames;
    }
    public void setXbUserNames(String xbUserNames) {
        this.xbUserNames = xbUserNames;
    }
    public String getXbUserRoleCode() {
        return xbUserRoleCode;
    }
    public void setXbUserRoleCode(String xbUserRoleCode) {
        this.xbUserRoleCode = xbUserRoleCode;
    }
    public String getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    public FlowDefine getFlowDefine() {
        return flowDefine;
    }
    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
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
}
