package com.centit.webservice.server.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.SignedReport;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.mip.po.OfficialNextProMIP;
import com.centit.mip.po.OfficialNextProMIP.DeptListMIP;
import com.centit.mip.po.OfficialNextProMIP.UserInfoMIP;
import com.centit.mip.po.OfficialNextProMIP.UserListMIP;
import com.centit.mip.po.OfficialSendProMIP;
import com.centit.mip.po.OfficialSendProMIP.NextDeptListMIP;
import com.centit.mip.po.OfficialSendProMIP.NextStepListMIP;
import com.centit.mip.po.OfficialSendProMIP.NextUserListMIP;
import com.centit.oa.service.OaSmssendManager;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.webservice.util.JsonUtil;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.FlowVariable;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.VUserTaskList;

/**
 * 
 * TODO MIP接口流程业务
 * 
 * @version
 */
public class CentitWebCommonBizServiceImpl<T> extends
        CentitWebPowerRuntimeServiceImpl<OptProcInfo> {
    private static final long serialVersionUID = 1L;

    protected SysUnitManager sysUnitManager;
    protected SysUserManager sysUserManager;
    protected String isSubFlow;// T:子流程
    protected OptStuffInfoManager optStuffInfoManager;
    protected OptIdeaInfoDao optIdeaInfoDao;
    protected VuserTaskListOAManager vuserTaskListOAManager;
    protected WfNodeInstanceDao nodeInstanceDao;
    
    private OaSmssendManager oaSmssendManager;

    // protected OptProcInfo optProcInfo=new OptProcInfo();

    /**
     * 根据webservice接口参数获取待办信息
     * 
     * @param mip
     * @return
     * @throws Exception
     */
    protected VuserTaskListOA getUserTaskInfo(Map<String, Object> filterMap)
            throws Exception {

        List<VuserTaskListOA> userTaskList = vuserTaskListOAManager
                .listObjects(filterMap);
        userTaskInfo = null != userTaskList && userTaskList.size() > 0 ? userTaskList
                .get(0) : null;
        if (null == userTaskInfo) {
            throw new Exception("没找到对应的待办信息！");
        }
        return userTaskInfo;
    }
    
    /**
     * 是否可以发送短信
     * @param mip
     * @return
     * @throws Exception
     */
    protected String  canSendmessg(
            OfficialNextProMIP mip) throws Exception {
        
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("nodeInstId", Long.parseLong(mip.getNodeinstid()));
        filterMap.put("djId", mip.getMessageitemguid());
        filterMap.put("userCode", mip.getUserid());
        // 获取待办信息--优先调用
        List<VuserTaskListOA> userTaskList = vuserTaskListOAManager
                .listObjects(filterMap);
        VuserTaskListOA taskInfo = null != userTaskList && userTaskList.size() > 0 ? userTaskList
                .get(0) : null;
        if (null == userTaskInfo) {
            throw new Exception("没找到对应的待办信息！");
        }
        String moduleCode = getmoduleCode4tOptParam(taskInfo.getOptParam());
        GeneralModuleParam moduleParam=generalModuleParamManager.getObjectById(moduleCode);
        if(null!=moduleParam&&"T".equals(moduleParam.getCanSendMessage())){
            return "T";
        }
                return "F";
        
    }

    /**
     * 根据OA系统流程配置。获取当前公文流程下一步信息，包括下步骤接受人员。
     * 
     * @param mip
     * @return
     * @throws Exception
     */
    protected List<OfficialNextProMIP> getNextOfficialProcessing(
            OfficialNextProMIP mip) throws Exception {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("nodeInstId", Long.parseLong(mip.getNodeinstid()));
        filterMap.put("djId", mip.getMessageitemguid());
        filterMap.put("userCode", mip.getUserid());
        // 获取待办信息--优先调用
        VuserTaskListOA userTask = getUserTaskInfo(filterMap);

        // 验证环节是否支持在pc端处理

        checkNodeHasMIPPower(userTask);

        // 获取 流程参数 默认值
        this.extractFlowOptParam();

        // 根据通用配置获取参数
        generalOpt();

        // 处理数据
        List<OfficialNextProMIP> nextStepList = doOpt();

        return nextStepList;
    }

    /**
     * 发送已经签批或者签署意见的公文到OA系统。 手写签批公文返回签批的PDF文件 签署意见的公文返回录入的意见信息、录入人员、时间等
     * 
     * @param mip
     * @throws Exception
     */
    protected void sendOfficialProcess(OfficialSendProMIP mip)

    throws Exception {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("nodeInstId", Long.parseLong(mip.getNodeinstid()));
        filterMap.put("djId", mip.getMessageitemguid());
        filterMap.put("userCode", mip.getUserid());
        // 获取待办信息--优先调用
        VuserTaskListOA userTask = getUserTaskInfo(filterMap);

        // 验证环节是否支持在pc端处理
        checkNodeHasMIPPower(userTask);

        // 获取 流程参数 默认值

        extractFlowOptParam();

        setDateByMIP(mip);

        setModuleParamDate();

        saveOpt();
        // 特殊节点处理
        if(!("FW_CSSH".equals(optProcInfo.getNodeCode())&&"HG".equals(optProcInfo.getIdeacode()))){//多次转办处理--其余节点依旧按原先模式
            // 流程提交后操作办件角色--办件角色直接对节点提交权限验证有影响
            saveflowTeamInfo();
        }

        saveIdeaInfo();
        Set<Long> nextNode = null;//发送短信
        
        if( "sw_bl".equals(object.getNodeCode())){
            
            nextNode =submitOptWithAssignUnits("sw_yd",null);//部门阅档环节按照阅文办理部门创建实例
        }else{
            
            nextNode = submitNode();
        }
        
        if(("FW_CSSH".equals(optProcInfo.getNodeCode())&&"HG".equals(optProcInfo.getIdeacode()))){//多次转办处理
            // 流程提交后操作办件角色--办件角色直接对节点提交权限验证有影响
            saveflowTeamInfo();
        }
        
        if(null!=nextNode){//发送短信
            oaSmssendManager.saveFlowMsgs(mip.getSendmessg(), mip.getUserid(), nextNode);
            oaSmssendManager.executeSendMsg();
        }
        
        

        //收文分管领导节点提交以及领导意见汇总节点提交时候，特殊处理，将可能会被失效的节点实例恢复正常
        if("sw_yd".equals(object.getNodeCode()) ||"sw_py".equals(optProcInfo.getNodeCode()) ||"SW_ff".equals(optProcInfo.getNodeCode()) ||"sw_fgldpy".equals(object.getNodeCode()) || "SW_LDYJSY".equals(object.getNodeCode())|| "sw_bl".equals(object.getNodeCode())){
            List<NodeInstance> wfids= flowManager.listFlowInstNodes(super.getFlowInstId());
            if(wfids!=null && wfids.size()>0){
                for(NodeInstance nodeInstance:wfids){
                    if("I".equals(nodeInstance.getNodeState())){
                        flowManager.activizeNodeInstance(nodeInstance.getNodeInstId(), "99999999");
                    }
                }
            }
            
          //如果是分管领导环节
            if("sw_fgldpy".equals(optProcInfo.getNodeCode())){
                flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), "fgwldSH", fUserDetail.getUsercode());                    
            }
        }
      //收文流程 多个多实例节点需要结束时候，出现提交结束导致流程中未办的节点被结束的情况，此处手动恢复流程实例、流程环节实例状态以及业务状态为正常；
        //出现环节的有 收文办理\处长审核、收文分发环节；
        if("sw_yd".equals(optProcInfo.getNodeCode()) ||"sw_bl".equals(optProcInfo.getNodeCode()) || "sw_czsh".equals(optProcInfo.getNodeCode()) || "SW_ff".equals(optProcInfo.getNodeCode()) || "sw_fgldpy".equals(optProcInfo.getNodeCode())||"SW_LDYJSY".equals(optProcInfo.getNodeCode()) ){
            List<NodeInstance> nodeList = flowManager.listFlowInstNodes(super.getFlowInstId());
            if(nodeList!=null && nodeList.size()>0){
                for(NodeInstance nodeInstance:nodeList){
                    if("E".equals(nodeInstance.getNodeState())){
                        flowManager.activizeNodeInstance(nodeInstance.getNodeInstId(), "99999999");
                        optBaseInfoManager.activizeNodeInstance(super.getFlowInstId(),nodeInstance.getNodeInstId(),"99999999");
                    }
                }
            }
        }
        // pdf处理

    }

    private Set<Long>  submintOpt() {
        // 获取spring的context --自动提交节点
        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext application = webApplicationContext.getServletContext();

        if (StringUtils.isNotBlank(optProcInfo.getIshq())) {
            flowEngine.saveFlowVariable(super.getFlowInstId(), "ishq",
                    optProcInfo.getIshq());
        }

        return submitNode(application);

    }

    /**
     * 获取通用配置参数
     */
    private void setModuleParamDate() {
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        teamRoleCode = moduleParam.getTeamRoleCode();// 办件角色代码
        zbOrgRoleCode = moduleParam.getZbOrgRoleCode();// 主办单位角色代码
        xbOrgRoleCode = moduleParam.getXbOrgRoleCode();// 协办单位角色代码
        engineRoleCode = moduleParam.getEngineRoleCode();
        auditUserRoleCode = moduleParam.getXbOrgRoleCode();
        zbUserRoleCode = moduleParam.getZbUserRoleCode();
    }

    /**
     * zbOrg;// 主办单位代码 xbOrg;// 协办单位代码 attUser;// 关注人员代码 teamUser;//
     * 办件用户代码(bjUserName) zbUser; auditUser; engineUser
     * 
     * @param mip
     * @throws Exception
     */
    protected void setDateByMIP(OfficialSendProMIP mip) throws Exception {
        // 获取下一步骤处理信息
        List<NextStepListMIP> nextStepList = mip.getNextsteplist();
        /* 通用处理-get(0) beg */
        if (null != nextStepList && nextStepList.size() > 0) {
            for (NextStepListMIP nextStep : nextStepList) {
                if (null == optProcInfo) {
                    optProcInfo = new OptProcInfo();
                }
                moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode);
                optProcInfo.setIdeacode(nextStep.getNextstepids());
                optProcInfo.setDjId(mip.getMessageitemguid());
                optProcInfo.setNodeInstId(Long.parseLong(mip.getNodeinstid()));
                optProcInfo
                        .setTransidea(CodeRepositoryUtil.getValue(
                                moduleParam.getIdeaCatalog(),
                                nextStep.getNextstepids()));
                optProcInfo.setTranscontent(mip.getOpinion());
                List<NextUserListMIP> userlist = nextStep.getUserlist();
                List<NextDeptListMIP> deptlist = nextStep.getDeptlist();
                for (NextUserListMIP nextUser : userlist) {
                    if ("teamUser".equals(nextUser.getUserRole())) {
                        teamUserCodes = nextUser.getNextpersonid();
                        bjUserNames = getNamesByCodes(teamUserCodes, "usercode");
                    }
                    if ("teamhqUser".equals(nextUser.getUserRole())) {
                        teamhqUserCodes = nextUser.getNextpersonid();
                        bjhqUserNames = getNamesByCodes(teamhqUserCodes,
                                "bjhqUserNames");
                    }
                    if ("attUser".equals(nextUser.getUserRole())) {
                        attUserCodes = nextUser.getNextpersonid();
                        attUserNames = getNamesByCodes(attUserCodes, "usercode");
                    }
                    if ("zbUser".equals(nextUser.getUserRole())) {
                        zbUserCodes = nextUser.getNextpersonid();
                        zbUserNames = getNamesByCodes(zbUserCodes, "usercode");
                    }
                    if ("auditUser".equals(nextUser.getUserRole())) {
                        auditUserCodes = nextUser.getNextpersonid();
                        auditUserNames = getNamesByCodes(auditUserCodes,
                                "usercode");
                    }
                    if ("engineUser".equals(nextUser.getUserRole())) {
                        engineUserCodes = nextUser.getNextpersonid();
                        engineUserNames = getNamesByCodes(engineUserCodes,
                                "usercode");
                    }
                }
                for (NextDeptListMIP nextDept : deptlist) {
                    if ("zbOrg".equals(nextDept.getDeptRole())) {
                        zbOrgCode = nextDept.getNextunitinfo();
                    }
                    if ("xbOrg".equals(nextDept.getDeptRole())) {
                        xbOrgCodes = nextDept.getNextunitinfo();
                        xbOrgNames = getNamesByCodes(xbOrgCodes, "unitcode");
                    }

                }
            }
        }
        /* 通用处理-get(0) end */

    }

    /**
     * 保存操作结果
     * 
     * @return
     */
    public void saveOpt() {
        try {
            if (optProcInfo == null) {
                optProcInfo = new OptProcInfo();
            }

            moduleParam = generalModuleParamManager.getObjectById(moduleCode);

            // 添加保存操作
            optProcInfo.setDjId(super.getDjId());
            optProcInfo.setNodeInstId(curNodeInstId);
            optProcInfo.setNodeCode(nodeCode);
            optProcInfo.setTransdate(new Date(System.currentTimeMillis()));
            optProcInfo.setUsercode(fUserDetail.getUsercode());
            optProcInfo.setUnitcode(fUserDetail.getPrimaryUnit());

            NodeInstance nodeInst = flowEngine.getNodeInstById(optProcInfo
                    .getNodeInstId());
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            optProcInfo.setNodename(nodeInfo.getNodeName());
            optProcInfo.setNodeinststate(nodeInst.getNodeState());

            // 存在风险点的信息:风险类别、风险描述、风险内控手段与结果
            if (StringUtils.isNotBlank(optProcInfo.getRisktype())) {
                optProcInfo.setIsrisk("T");// 存在
            } else {
                optProcInfo.setIsrisk("F");// 不存在
            }

            /*** 发文特殊节点数据处理开始 ***/
            // 发文 处室负责人审核 选择会签经办人*
            // 发文 处室负责人审核 判断是否需要会签
            if ("FW_CSSH".equals(nodeInfo.getNodeCode())) {
                Set<String> fos = flowEngine.viewFlowOrganize(
                        super.getFlowInstId(), "zbcshq");
                if (fos != null && fos.size() > 0) {
                    optProcInfo.setIshq("T");
                } else {
                    optProcInfo.setIshq("F");
                }
            }
            if ("FW_CSSH".equals(nodeCode)) {// 发文 处室负责人审核
                if ("T".equals(optProcInfo.getIdeacode())) {// 转部门会签ideacode=='T'
                    Set<String> fos = flowEngine.viewFlowOrganize(
                            super.getFlowInstId(), "zbcshq");
                    if (fos != null && fos.size() > 0) {// ishq=='T'
                        GeneralModuleParam moduleParam = generalModuleParamManager
                                .getObjectById(moduleCode + "_exp" + 2);// 扩展
                        // List<UserInfoMIP>
                        // teamhqUserList=initTeamUsersConfig(moduleParam);
                        // UserListMIP bjuserhqList = new
                        // UserListMIP("teamhqUser", null, teamhqUserList);
                        this.saveTeamRole(moduleParam.getTeamRoleCode(),
                                teamhqUserCodes);
                    }
                }
            }

            /******** 核稿步骤：获取委办会签变量 ************/
            if (nodeInfo != null && nodeInfo.getNodeCode().equals("zbcsfzrsh")) {
                List<FlowVariable> varsWB = flowEngine
                        .viewFlowVariablesByVarname(super.getFlowInstId(),
                                "iswbhq");
                if (varsWB != null && !varsWB.isEmpty()) {
                    FlowVariable flowVariable = varsWB.get(0);
                    optProcInfo.setIswbhq(flowVariable.getVarValue());
                }
            }
            // 发文处室负责人审核保存会签人员 -----办公室秘书核稿 选择 会签人员
            if ("zbcsfzrsh".equals(nodeCode)) {
                GeneralModuleParam moduleParamTemp = moduleParam;
                if ("HQ".equals(optProcInfo.getIdeacode())) {// 办公室秘书核稿 选择 会签人员
                    moduleParamTemp = generalModuleParamManager
                            .getObjectById(moduleCode + "_exp" + 2);// 扩展
                }
                if ("BGS".equals(optProcInfo.getIdeacode())) {// 办公室秘书核稿
                                                              // -----办公室负责人
                    moduleParamTemp = generalModuleParamManager
                            .getObjectById(moduleCode + "_exp" + 3);// 扩展
                }
                this.saveTeamRole(moduleParamTemp.getTeamRoleCode(),
                        teamhqUserCodes);

            }

            /*** 发文特殊节点数据处理结束 ***/

            optProcInfoManager.saveObject(optProcInfo);// 保存办件人员
            // 保存关注人员
            this.saveAtt();
            this.saveZBUser();// 保存主办承办人
            this.saveAuditUser();// 保存审核人
//            this.saveTeamRolepeople();
            this.saveEngineRolepeople();// 保存权限引擎角色

            // 保存主办承办人
            if ("zbcbr".equals(super.teamRoleCode)
                    || "zbcsfb".equals(this.zbOrgRoleCode)) {
                OptBaseInfo optBaseInfo = this.getOptBaseInfo();
                if ("zbcbr".equals(this.teamRoleCode)) {
                    if (StringUtils.isNotBlank(super.teamUserCodes)) {
                        String[] userCodes = super.teamUserCodes.split(",");
                        optBaseInfo.setHeadusercode(userCodes[0]);
                    }
                }
                if ("zbcsfb".equals(super.zbOrgRoleCode)) {
                    optBaseInfo.setHeadunitcode(super.zbOrgCode);
                }

                optBaseInfoManager.saveObject(optBaseInfo);
            }

            // 保存主办单位
            if (StringUtils.isNotBlank(zbOrgRoleCode)
                    && StringUtils.isNotBlank(zbOrgCode)) {
                flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                        zbOrgRoleCode, fUserDetail.getUsercode());
                flowEngine.assignFlowOrganize(super.getFlowInstId(),
                        zbOrgRoleCode, zbOrgCode, fUserDetail.getUsercode());
                Set<String> sValues = new HashSet<String>();
                sValues.add(zbOrgCode);
                flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                        zbOrgRoleCode, sValues);
            }

            // 保存协办单位
            if (StringUtils.isNotBlank(xbOrgCodes)
                    && StringUtils.isNotBlank(xbOrgRoleCode)) {
                flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                        xbOrgRoleCode, fUserDetail.getUsercode());
                String[] orgCodes = xbOrgCodes.split("[,]");
                if (orgCodes != null && orgCodes.length > 0) {
                    flowEngine.assignFlowOrganize(super.getFlowInstId(),
                            xbOrgRoleCode,
                            new HashSet<String>(Arrays.asList(orgCodes)),
                            fUserDetail.getUsercode());
                    flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                            xbOrgRoleCode,
                            new HashSet<String>(Arrays.asList(orgCodes)));
                }
            }

        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存流程过程信息失败。");
        }
    }

    /**
     * 流程流转信息-办件
     */
    private void saveflowTeamInfo() {
        this.saveTeamRolepeople();
        GeneralModuleParam moduleParamTemp = moduleParam;

        // 发文处室负责人审核保存会签人员 -----办公室秘书核稿 选择 会签人员
        if ("FW_CSSH".equals(nodeCode)
                && "HQ".equals(optProcInfo.getIdeacode())) {

            moduleParamTemp = generalModuleParamManager
                    .getObjectById(moduleCode + "_exp" + 2);// 扩展
            if (null != teamhqUserCodes)
                this.saveTeamRole(moduleParamTemp.getTeamRoleCode(),
                        teamhqUserCodes);
        }
        // --办公室秘书核稿 选择 会签人员
        if ("FW_BGSMS".equals(nodeCode)
                && "HQ".equals(optProcInfo.getIdeacode())) {

            moduleParamTemp = generalModuleParamManager
                    .getObjectById(moduleCode + "_exp" + 2);// 扩展
            if (null != teamhqUserCodes)
                this.saveTeamRole(moduleParamTemp.getTeamRoleCode(),
                        teamhqUserCodes);
        }
        // 办公室审核提交领导
        if ("FW_BGSFS".equals(nodeCode)
                && "F".equals(optProcInfo.getIdeacode())) {

            moduleParamTemp = generalModuleParamManager
                    .getObjectById(moduleCode + "_exp" + 2);// 扩展
            if (null != teamhqUserCodes)
                this.saveTeamRole(moduleParamTemp.getTeamRoleCode(),
                        teamhqUserCodes);
        }

        // 发文处室负责人发起再次审核
        if ("FW_CSSH".equals(nodeCode)
                && "HG".equals(optProcInfo.getIdeacode())
                || "FW_BGSMS".equals(nodeCode)
                && "BGS".equals(optProcInfo.getIdeacode())) {// 不分办) {
            moduleParamTemp = generalModuleParamManager
                    .getObjectById(moduleCode + "_exp" + 3);// 扩展
            if (null != teamhqUserCodes)
                this.saveTeamRole(moduleParamTemp.getTeamRoleCode(),
                        teamhqUserCodes);
            if ("FW_CSSH".equals(nodeCode)) {
                delectTeamRole(moduleParamTemp.getTeamRoleCode());
            }

        }
    }

    /**
     * 办件角色删除机制
     * 
     * @param roleCode
     * @param userCodes
     */
    protected void delectTeamRole(String roleCode) {
        // 是否显示前一步办理信息-显示代表可以修改别人的数据
        if ("T".equals(moduleParam.getHasPreIdea())) {
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode);
        } else {
            // 只能删除自己--loginInfo.getUsercode()为字段authdesc，需要重新写方法
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode,
                    fUserDetail.getUsercode());
        }
    }

    /**
     * 保存过程日志信息
     */
    public void saveIdeaInfo() {

        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(fUserDetail.getUsername());

        /** 获得用户所在部门 start */
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(fUserDetail.getPrimaryUnit());
        fuerunit.setUserCode(fUserDetail.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        /** 获得用户所在部门 end */

        optIdeaInfo.setUnitname(fuerunit.getUnitName());
        optIdeaInfo.setModuleCode(moduleCode);// 办理意见与通用模块关联（控制环节意见显示）

        /** 获取过程日志信息：环节名称、办理意见、环节状态 start */
        NodeInstance nodeInst = flowEngine.getNodeInstById(optProcInfo
                .getNodeInstId());
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        optProcInfo.setNodename(nodeInfo.getNodeName());
        optProcInfo.setNodeinststate(nodeInst.getNodeState());
        optIdeaInfo.setTransidea(StringUtils.isNotBlank(optProcInfo.getTransidea())?optProcInfo.getTransidea():"提交");
        optIdeaInfo.setIsPC("F");// 手机端
        /** 获取过程日志信息：环节名称、办理意见、环节状态 end */

        optProcInfoManager.saveIdeaInfo(optIdeaInfo, optProcInfo);

    }

    /**
     * 用于加载通用配置
     * 
     * @throws Exception
     */
    public void generalOpt() throws Exception {
        try {

            if (optProcInfo == null) {
                optProcInfo = new OptProcInfo();
                optProcInfo.setDjId(super.getDjId());
                optProcInfo.setNodeInstId(curNodeInstId);
            }

            moduleParam = generalModuleParamManager.getObjectById(moduleCode);

            // super.initTeamUsersConfig();

            optProcInfo = optProcInfoManager
                    .getObjectByNodeInstId(curNodeInstId);

            /**
             * 根据参数是否需要 关注人 ，提供候选关注人列表
             */
            super.initAttUsersConfig(moduleParam);
            /**
             * 获得办件角色人名单,根据参数是否需要 办件人员
             */
            teamUserList = super.initTeamUsersConfig(moduleParam);

            /**
             * 主办承办人
             */
            zbUserList = super.initZBUsersConfig(moduleParam);

            /**
             * 审核人
             */
            auditUserList = super.initAuditUsersConfig(moduleParam);

            /**
             * 权限引擎
             */
            engineUserList = super.initEngineUsersConfig(moduleParam);
            /**
             * 批分功能配置
             */
            if ("T".equals(moduleParam.getHasOrgRole())) {
                zbOrgList = initZBOrgConfig(moduleParam);
                xbOrgList = initXBOrgConfig(moduleParam);
            }

        } catch (Exception e) {

            throw new Exception("当前操作提交失败，详见系统日志。");

        }
    }

    public List<OfficialNextProMIP> doOpt() throws Exception {
        String ideaCatalog = null;
        List<FDatadictionary> dataDictionaryList = null;
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;
        List<OfficialNextProMIP> nextStepList = new ArrayList<OfficialNextProMIP>();

        moduleParam = generalModuleParamManager.getObjectById(moduleCode);

        // 办理意见
        ideaCatalog = moduleParam.getIdeaCatalog();// 结果代码

        if ("T".equals(moduleParam.getHasIdea())
                && StringUtils.isNotBlank(ideaCatalog)) {
            dataDictionaryList = CodeRepositoryUtil.getDictionary(ideaCatalog);// 办理意见列表
        }

        // 办理页面无意见流转
        if (null == dataDictionaryList || dataDictionaryList.size() == 0) {
            if (null != moduleParam) {
                // 设置办件角色
                if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                        && "T".equals(moduleParam.getAssignTeamRole())) {
                    if (null != teamUserList && teamUserList.size() > 0) {
                        bjuserList = new UserListMIP(
                                moduleParam.getTeamRoleName(), "teamUser",
                                null, teamUserList);
                    }

                }
            }
            // 设置权限引擎角色
            if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                    && "T".equals(moduleParam.getAssignEngineRole())) {
                if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                    enuserList = new UserListMIP(
                            moduleParam.getEngineRoleName(), "engineUser",
                            null, engineUserList);
                }
            }
            // 是否批分
            if (StringUtils.isNotBlank(moduleParam.getHasOrgRole())
                    && "T".equals(moduleParam.getHasOrgRole())) {
                if (null != zbOrgList && zbOrgList.size() > 0) {// 主办
                    deptListzb = new DeptListMIP("主办处室", "zbOrg", "1",
                            zbOrgList);
                }
                if (null != xbOrgList && xbOrgList.size() > 0) {// 协办
                    deptListxb = new DeptListMIP(
                            moduleParam.getXbOrgRoleName(), "xbOrg", null,
                            xbOrgList);
                }
            }

            OfficialNextProMIP mip = new OfficialNextProMIP();
            // Map<String, Object> filterMap = new HashMap<String, Object>();
            mip.setStepid(JsonUtil.replaceNullString(null));
            mip.setStepname(JsonUtil.replaceNullString(null));
            
           
                if (null != bjuserList) {
                    mip.getUserlist().add(bjuserList);
                }
                if (null != enuserList) {
                    mip.getUserlist().add(enuserList);
                }
                if (null != deptListzb) {
                    mip.getDeptlist().add(deptListzb);
                }
                if (null != deptListxb) {
                    mip.getDeptlist().add(deptListxb);
                } 
            
        //收文主要领导节点排除--pc端配置主要用来生成快捷意见
        if("sw_tldpy".equals(nodeCode)||"sw_fgldpy".equals(nodeCode)){
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
        }
            nextStepList.add(mip);
        }

        if (null != dataDictionaryList && dataDictionaryList.size() > 0) {
            for (FDatadictionary dataDic : dataDictionaryList) {
                OfficialNextProMIP mip = new OfficialNextProMIP();
                mip.setStepid(dataDic.getDatacode());// T\F\B\or others
                mip.setStepname(dataDic.getDatavalue());

                mip = initFilterMapByOpt(
                        super.getDjId().replaceAll("\\d+", ""), mip, dataDic);// 根据业务类别初始化下一步办理数据

                nextStepList.add(mip);
            }
        }
        return nextStepList;
    }

    // 按流程类别初始化数据
    private OfficialNextProMIP initFilterMapByOpt(String type,
            OfficialNextProMIP mip, FDatadictionary dataDic) {
        if ("FW".equals(type)) {
            return initFWFilterMapByOpt(mip, dataDic);
        }
        if ("SW".equals(type)) {
            return initSWFilterMapByOpt(null, mip, dataDic);
        }
        if ("CARSQ".equals(type)) {
            return initCARSQFilterMapByOpt(mip, dataDic);
        }
        if ("QB".equals(type)) {
            return initQBFilterMapByOpt(mip,  dataDic);
        }
        return null;
    }

    private OfficialNextProMIP initSWFilterMapByOpt(
            Map<String, Object> filterMap, OfficialNextProMIP mip,
            FDatadictionary dataDic) {
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;

        if (null != moduleParam) {
            // 设置办件角色
            if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                    && "T".equals(moduleParam.getAssignTeamRole())) {
                if (null != teamUserList && teamUserList.size() > 0) {
                    bjuserList = new UserListMIP(moduleParam.getTeamRoleName(),
                            "teamUser", null, teamUserList);
                }

            }
        }
        // 设置权限引擎角色
        if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                && "T".equals(moduleParam.getAssignEngineRole())) {
            if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                enuserList = new UserListMIP(moduleParam.getEngineRoleName(),
                        "engineUser", null, engineUserList);
            }
        }
        // 是否批分
        if (StringUtils.isNotBlank(moduleParam.getHasOrgRole())
                && "T".equals(moduleParam.getHasOrgRole())) {
            if (null != zbOrgList && zbOrgList.size() > 0) {// 主办
                deptListzb = new DeptListMIP("主办处室", "zbOrg", "1", zbOrgList);
            }
            if (null != xbOrgList && xbOrgList.size() > 0) {// 协办
                deptListxb = new DeptListMIP(moduleParam.getXbOrgRoleName(),
                        "xbOrg", null, xbOrgList);
            }
        }
        // 通用操作默认逻辑-待整理 初始化 T B F T选人 选协办 B F隐藏
        if ("T".equals(dataDic.getDatacode()) && null != bjuserList) {// 办件角色
            mip.getUserlist().add(bjuserList);
        }
        if ("T".equals(dataDic.getDatacode()) && null != deptListzb) {// 全部显示
            mip.getDeptlist().add(deptListzb);

        } else if ("F".equals(dataDic.getDatacode())
                || "B".equals(dataDic.getDatacode())) {// 显示协办
        }

        /******* 收文 开始 *********/
//      1.办公室主任批分 sw_py
        if ("sw_py".equals(nodeCode)) {// 1收文厅办主任批分
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 提交接收部门
                mip.getDeptlist().add(deptListxb);
            } else if ("F".equals(dataDic.getDatacode())) {// 转厅领导审阅
                mip.getUserlist().add(enuserList);
            }  else if ("FG".equals(dataDic.getDatacode())) {// 转分管领导
                mip.getUserlist().add(bjuserList);
            } else if ("B".equals(dataDic.getDatacode())) {// 直接归档
                
            }else if ("TFG".equals(dataDic.getDatacode())) {// 转领导+部门
                mip.getUserlist().add(bjuserList);
                mip.getDeptlist().add(deptListxb);
            } 
        }
//      2.市总领导批阅sw_tldpy
        if ("sw_tldpy".equals(nodeCode)) {// 2收文厅领导批阅
            // 预留-无办理意见
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
        }
//      3.分管领导批阅sw_fgldpy 
        if ("sw_fgldpy".equals(nodeCode)) {// 3收文领导意见汇总
           /* mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("LD".equals(dataDic.getDatacode())) {//提交主要领导审阅 
                mip.getUserlist().add(enuserList); 
            } else if ("T".equals(dataDic.getDatacode())) {//提交部门
                mip.getDeptlist().add(deptListxb);
            } else if("LZ".equals(dataDic.getDatacode())){//提交分管领导以及部门收文
                mip.getDeptlist().add(deptListxb);
                mip.getUserlist().add(bjuserList);
            }else if("F".equals(dataDic.getDatacode())){//提交分管领导
                mip.getUserlist().add(bjuserList); 
            }*/
            //只用来出示快捷意见
        }
//      4.市总领导意见汇总SW_LDYJSY
        if ("SW_LDYJSY".equals(nodeCode)) {// 4市总领导意见汇总SW_LDYJSY
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 提交接收部门
                mip.getDeptlist().add(deptListxb);
            } else if ("F".equals(dataDic.getDatacode())) {// 提交分管领导审阅
                mip.getUserlist().add(bjuserList);
            } else if("LZ".equals(dataDic.getDatacode())){//提交分管领导以及部门收文
                mip.getUserlist().add(bjuserList);
                mip.getDeptlist().add(deptListxb);
            }else if("LD".equals(dataDic.getDatacode())){//提交主要领导
                mip.getUserlist().add(enuserList);
            }else if("TF".equals(dataDic.getDatacode())){//提交领导以及部门收文
                mip.getUserlist().add(bjuserList);
                mip.getDeptlist().add(deptListxb);
            }
        }
//      5.部门收文sw_yd
        if ("sw_yd".equals(nodeCode)) {//发文处室负责人审核 F非本处室业务  T 提交部门
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 业务处室已收文
                mip.getUserlist().add(enuserList);
            } else if ("F".equals(dataDic.getDatacode())) {// 非本处室业务
                
            } else {
                
            }
        }
//      6.文书分发sw_ff
        if("SW_ff".equals(nodeCode)){//收文——收文分发节点
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if("F".equals(dataDic.getDatacode())){//直接分发
//                   mip.getUserlist().add(bjuserList);
              }
            else if("B".equals(dataDic.getDatacode())){//提交领导审阅  
//                mip.getDeptlist().add(deptListxb);

            }
            else if("T".equals(dataDic.getDatacode())){//提交部门
                  mip.getDeptlist().add(deptListxb);

              }   
          }
//      7.部门审核sw_czsh
        if ("sw_czsh".equals(nodeCode)) {// 收文处长审核
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("F".equals(dataDic.getDatacode())) {// 业务处室已收文
                mip.getUserlist().add(enuserList);
            } else if ("T".equals(dataDic.getDatacode())
                    || "B".equals(dataDic.getDatacode())) {// 非本处室业务
            } 
        }
//      8.收文办理sw_bl
        if ("sw_bl".equals(nodeCode)) {// 收文办理
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("F".equals(dataDic.getDatacode())) {// 转他人办理
                mip.getUserlist().add(enuserList);
            } else if ("T".equals(dataDic.getDatacode())) {// 收文归档
                
            } else {
              
            }
        }

        /***** 收文 结束 ********/
        return mip;

    }

    private OfficialNextProMIP initFWFilterMapByOpt(OfficialNextProMIP mip,
            FDatadictionary dataDic) {
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;

        if (null != moduleParam) {
            // 设置办件角色
            if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                    && "T".equals(moduleParam.getAssignTeamRole())) {
                if (null != teamUserList && teamUserList.size() > 0) {
                    bjuserList = new UserListMIP(moduleParam.getTeamRoleName(),
                            "teamUser", null, teamUserList);
                }

            }
        }
        // 设置权限引擎角色
        if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                && "T".equals(moduleParam.getAssignEngineRole())) {
            if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                enuserList = new UserListMIP(moduleParam.getEngineRoleName(),
                        "engineUser", null, engineUserList);
            }
        }
        // 是否批分
        if (StringUtils.isNotBlank(moduleParam.getHasOrgRole())
                && "T".equals(moduleParam.getHasOrgRole())) {
            if (null != zbOrgList && zbOrgList.size() > 0) {// 主办
                deptListzb = new DeptListMIP("主办处室", "zbOrg", "1", zbOrgList);
            }
            if (null != xbOrgList && xbOrgList.size() > 0) {// 协办
                deptListxb = new DeptListMIP(moduleParam.getXbOrgRoleName(),
                        "xbOrg", null, xbOrgList);
            }
        }
        // 通用操作默认逻辑-待整理 初始化 T B F T选人 选协办 B F隐藏
        if ("T".equals(dataDic.getDatacode()) && null != bjuserList) {// 办件角色
            mip.getUserlist().add(bjuserList);
        }
        if ("T".equals(dataDic.getDatacode()) && null != deptListzb) {// 全部显示
            mip.getDeptlist().add(deptListzb);

        } else if ("F".equals(dataDic.getDatacode())
                || "B".equals(dataDic.getDatacode())) {// 显示协办
        }

        /******* 发文 开始 *********/

        if ("FW_CSSH".equals(nodeCode)) {// 发文 处室负责人审核
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 转部门会签ideacode=='T'
             mip.getUserlist().add(bjuserList);
            }
            if ("HQ".equals(dataDic.getDatacode())) {// 转部门会签ideacode=='T'
                GeneralModuleParam moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode + "_exp" + 2);// 扩展
                if (null != moduleParam) {
                    List<UserInfoMIP> teamhqUserList = initTeamUsersConfig(moduleParam);
                    UserListMIP bjuserhqList = new UserListMIP(
                            moduleParam.getTeamRoleName(), "teamhqUser", null,
                            teamhqUserList);
                    if (null != bjuserhqList) {
                        mip.getUserlist().add(bjuserhqList);
                    }
                }
            }
            if ("HG".equals(dataDic.getDatacode())) {// 办公室秘书核稿 选择 会签人员
                GeneralModuleParam moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode + "_exp" + 3);// 扩展
                if (null != moduleParam) {
                    List<UserInfoMIP> teamhqUserList = initTeamUsersConfig(moduleParam);
                    UserListMIP bjuserhqList = new UserListMIP(
                            moduleParam.getTeamRoleName(), "teamhqUser", null,
                            teamhqUserList);
                    if (null != bjuserhqList) {
                        mip.getUserlist().add(bjuserhqList);
                    }
                }
            }
            if ("LDQF".equals(dataDic.getDatacode())) {//  转领导签发
                mip.getUserlist().add(enuserList);
            }
        }

        if ("FW_BGSMS".equals(nodeCode)) {// 发文 办公室秘书核稿
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("HQ".equals(dataDic.getDatacode())) {// 办公室秘书核稿 选择 会签人员
                GeneralModuleParam moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode + "_exp" + 2);// 扩展
                if (null != moduleParam) {
                    List<UserInfoMIP> teamhqUserList = initTeamUsersConfig(moduleParam);
                    UserListMIP bjuserhqList = new UserListMIP(
                            moduleParam.getTeamRoleName(), "teamhqUser", null,
                            teamhqUserList);
                    if (null != bjuserhqList) {
                        mip.getUserlist().add(bjuserhqList);
                    }
                }
            } else if ("BGS".equals(dataDic.getDatacode())) {// 办公室秘书核稿 选择 会签人员
                GeneralModuleParam moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode + "_exp" + 3);// 扩展
                if (null != moduleParam) {
                    List<UserInfoMIP> teamhqUserList = initTeamUsersConfig(moduleParam);
                    UserListMIP bjuserhqList = new UserListMIP(
                            moduleParam.getTeamRoleName(), "teamhqUser", null,
                            teamhqUserList);
                    if (null != bjuserhqList) {
                        mip.getUserlist().add(bjuserhqList);
                    }
                }

            } else if ("LDQF".equals(dataDic.getDatacode())) {// 转领导签发
                mip.getUserlist().add(bjuserList);
            }
        }

        if ("FW_XGNW".equals(nodeCode)) {// 发文 修改拟文---转处室负责人需在处理
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 转处室负责人审核
//                mip.getDeptlist().add(deptListxb);
            } else if ("F".equals(dataDic.getDatacode())) {// 提交秘书核稿
//                mip.getUserlist().add(bjuserList);
            }
        }

        if ("FW_BGSFS".equals(nodeCode)) {// 发文 办公室负责人复审
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 转分管领导会签
             mip.getUserlist().add(bjuserList);
            } else if ("F".equals(dataDic.getDatacode())) {// 转领导签发
                GeneralModuleParam moduleParam = generalModuleParamManager
                        .getObjectById(moduleCode + "_exp" + 2);// 扩展
                if (null != moduleParam) {
                    List<UserInfoMIP> teamhqUserList = initTeamUsersConfig(moduleParam);
                    UserListMIP bjuserhqList = new UserListMIP(
                            moduleParam.getTeamRoleName(), "teamhqUser", null,
                            teamhqUserList);
                    if (null != bjuserhqList) {
                        mip.getUserlist().add(bjuserhqList);
                    }
                }
            }
        }

        if ("FW_FGLDHQ".equals(nodeCode)) {// 发文 分管领导会签
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("T".equals(dataDic.getDatacode())) {// 提交领导签发
             mip.getUserlist().add(enuserList);
            }

        }
        if ("FW_LDQF".equals(nodeCode)) {// 发文 领导签发
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("F".equals(dataDic.getDatacode())) {// 提交阅办
                mip.getUserlist().add(bjuserList);
            }
        }

        if ("FW_GZ".equals(nodeCode)) {// 发文 盖章
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("B".equals(dataDic.getDatacode())) {// 退回排版校对修改
//                mip.getUserlist().add(bjuserList);
            }
        }

        return mip;

        /******* 发文 结束 *********/
    }
    
    
    
    private OfficialNextProMIP initQBFilterMapByOpt(OfficialNextProMIP mip,
            FDatadictionary dataDic) {
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;

        if (null != moduleParam) {
            // 设置办件角色
            if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                    && "T".equals(moduleParam.getAssignTeamRole())) {
                if (null != teamUserList && teamUserList.size() > 0) {
                    bjuserList = new UserListMIP(moduleParam.getTeamRoleName(),
                            "teamUser", null, teamUserList);
                }

            }
        }
        // 设置权限引擎角色
        if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                && "T".equals(moduleParam.getAssignEngineRole())) {
            if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                enuserList = new UserListMIP(moduleParam.getEngineRoleName(),
                        "engineUser", null, engineUserList);
            }
        }
        // 是否批分
        if (StringUtils.isNotBlank(moduleParam.getHasOrgRole())
                && "T".equals(moduleParam.getHasOrgRole())) {
            if (null != zbOrgList && zbOrgList.size() > 0) {// 主办
                deptListzb = new DeptListMIP("主办处室", "zbOrg", "1", zbOrgList);
            }
            if (null != xbOrgList && xbOrgList.size() > 0) {// 协办
                deptListxb = new DeptListMIP(moduleParam.getXbOrgRoleName(),
                        "xbOrg", null, xbOrgList);
            }
        }
        // 通用操作默认逻辑-待整理 初始化 T B F T选人 选协办 B F隐藏
        if ("T".equals(dataDic.getDatacode()) && null != bjuserList) {// 办件角色
            mip.getUserlist().add(bjuserList);
        }
        if ("T".equals(dataDic.getDatacode()) && null != deptListzb) {// 全部显示
            mip.getDeptlist().add(deptListzb);

        } else if ("F".equals(dataDic.getDatacode())
                || "B".equals(dataDic.getDatacode())) {// 显示协办
        }

        /******* 签报 开始 *********/

        if ("qb_sh".equals(nodeCode)) {// 部门审核（签报）
            /*FG       呈分管主席批示 
             cs        转部门会签   
             T         提交主办人办理 
             JS        处理完成并结束 
             F         退回修改    
            */
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("FG".equals(dataDic.getDatacode())) {// 呈分管主席批示
                mip.getUserlist().add(bjuserList);
            }
            if ("cs".equals(dataDic.getDatacode())) {//转部门会签 
//                mip.getDeptlist().add(deptListzb);
                mip.getDeptlist().add(deptListxb);
            }
          /*  if ("T".equals(dataDic.getDatacode())) {// 提交主办人办理 
            }
            if ("JS".equals(dataDic.getDatacode())) {//  转领导签发
            }
            if ("F".equals(dataDic.getDatacode())) {//  转领导签发
            }*/
        }
        if ("qb_zyldqy".equals(nodeCode)) {// 主席批示（签报）
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            
        }
        if ("qb_ldqy".equals(nodeCode)) {// 分管主席批示（签报）
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            /*if ("T".equals(dataDic.getDatacode())) {//提交部门处理    
               
            }*/
            if ("F".equals(dataDic.getDatacode())) {//呈主席批示
                mip.getUserlist().add(bjuserList); 
            }
        }
        if ("qb_bgrcl".equals(nodeCode)) {// 签报办理人（签报）
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
            if ("cs".equals(dataDic.getDatacode())) {//提交部门会签
                mip.getDeptlist().add(deptListxb); 
            }
            /*if ("bm".equals(dataDic.getDatacode())) {//呈部处长审批
                
            }*/
            
        }
        
        if ("qb_bmhq".equals(nodeCode)) {// 部门会签（签报）
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
        }
        if ("qb_ng".equals(nodeCode)) {// 拟稿（签报）
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
        }

        return mip;

        /******* 签报结束 *********/
    }
    
    
    private OfficialNextProMIP initCARSQFilterMapByOpt(OfficialNextProMIP mip,
            FDatadictionary dataDic) {
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;

        if (null != moduleParam) {
            // 设置办件角色
            if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                    && "T".equals(moduleParam.getAssignTeamRole())) {
                if (null != teamUserList && teamUserList.size() > 0) {
                    bjuserList = new UserListMIP(moduleParam.getTeamRoleName(),
                            "teamUser", null, teamUserList);
                }

            }
        }
        // 设置权限引擎角色
        if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                && "T".equals(moduleParam.getAssignEngineRole())) {
            if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                enuserList = new UserListMIP(moduleParam.getEngineRoleName(),
                        "engineUser", null, engineUserList);
            }
        }

        // 通用操作默认逻辑-待整理 初始化 T B F T选人 选协办 B F隐藏
        if ("T".equals(dataDic.getDatacode()) && null != bjuserList) {// 办件角色
        }
        if ("T".equals(dataDic.getDatacode()) && null != deptListzb) {// 全部显示

        } else if ("F".equals(dataDic.getDatacode())
                || "B".equals(dataDic.getDatacode())) {// 显示协办
        }

        /*******车辆 开始 *********/

        if ("car_sh".equals(nodeCode)) {// 用车申请
            mip.setUserlist(new ArrayList<UserListMIP>());
            mip.setDeptlist(new ArrayList<DeptListMIP>());
        }
        return mip;

        /******* 车辆结束 *********/
    }

    protected void temp() {
        /**
         * 是否会签后台判断
         * 
         */
        if ("FW_CSSH".equals(nodeCode)) {
            if (!"T".equals(moduleParam.getHasPreIdea())) {
                Set<String> fos = flowEngine.viewFlowOrganize(
                        super.getFlowInstId(), "zbcshq");
                if (fos != null && fos.size() > 0) {
                    optProcInfo.setIshq("T");
                } else {
                    optProcInfo.setIshq("F");
                }
            }
        }
    }

    /**
     * 获取主办协办部门参数
     */
    protected void initPFUnit(String roleCode, String nodeCode) {

        List<FUnitinfo> unitList = null;
        VUserTaskList userTask = flowManager.getUserTaskInfo(curNodeInstId);
        if ("xtcfb".equals(nodeCode) || "wbhb".equals(nodeCode)) {
            unitList = sysUnitManager.getAllSubUnits(userTask.getUnitCode());
        } else {
            unitList = sysUnitManager.listObjects();
        }

        // request.setAttribute("unitList", unitList);

    }

    /**
     * 保存办件角色
     */
    protected void saveTeamRolepeople() {
        this.saveTeamRole(teamRoleCode, teamUserCodes);
    }

    /*
     * 保存主办承办人
     */
    protected void saveZBUser() {
        this.saveEngineRole(zbUserRoleCode, zbUserCodes);
    }

    /**
     * 保存审核人
     */
    protected void saveAuditUser() {
        this.saveEngineRole(auditUserRoleCode, auditUserCodes);
    }

    /**
     * 保存办件角色（带参数）
     * 
     * @param roleCode
     * @param userCodes
     */
    private void saveTeamRole(String roleCode, String userCodes) {

        if (!StringUtils.isBlank(userCodes)) {
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode);
            String[] teamUserCode = userCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                            roleCode, userCode, fUserDetail.getUsercode());
                }
            }
        }

    }

    /**
     * 保存变量参数（引擎角色）
     * 
     * @param roleCode
     * @param userCodes
     */
    private void saveEngineRole(String roleCode, String userCodes) {

        // 如果未选择人员，则不保存
        if (!StringUtils.isBlank(userCodes)) {
            String[] engineUserCode = userCodes.split(",");
            Set<String> engineUserSet = new HashSet<String>();
            if (engineUserCode.length > 0) {
                for (String userCode : engineUserCode) {
                    engineUserSet.add(userCode);
                }
            }
            flowEngine.saveFlowNodeVariable(curNodeInstId, roleCode,
                    engineUserSet);
        }
    }

    /**
     * 保存角色引擎
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
     * 保存角色引擎
     */
    protected void saveEngineRolepeople(String engineRoleCode) {
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
     * 保存指定角色人员
     * 
     * @param teamrolecode
     *            小组
     * @param teamusercodes
     *            角色
     */
    protected void saveTeamRolepeople(String teamusercodes, String teamrolecode) {
        flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), teamrolecode);
        if (!StringUtils.isBlank(teamusercodes)) {
            String[] teamUserCode = teamusercodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                            teamrolecode, userCode, fUserDetail.getUsercode());
                }
            }
        }
    }

    /**
     * 添加设置关注
     * 
     * @return
     * 
     */
    public void saveAtt() {
        try {

            optProcAttentionManager.deleteFlowAttentionByOptUser(
                    optProcInfo.getDjId(), fUserDetail.getUsercode());
            if (!StringUtils.isBlank(attUserCodes)) {

                // 获取页面attUserCodes
                String[] attUserCode = attUserCodes.split(",");
                // 拆分userCode

                if (attUserCode.length > 0) {

                    for (int i = 0; i < attUserCode.length; i++) {

                        OptProcAttention optProcAttention = new OptProcAttention();
                        optProcAttention.setUserCode(attUserCode[i]);
                        optProcAttention.setDjId(optProcInfo.getDjId());
                        optProcAttention.setAttSetUser(fUserDetail
                                .getUsercode());
                        optProcAttention.setAttType(optProcInfo
                                .getOptProcAttention().getAttType());
                        optProcAttention.setAttSetTime(new Date(System
                                .currentTimeMillis()));
                        optProcAttention.setNodeInstId(optProcInfo
                                .getNodeInstId());
                        optProcAttention.setIsAtt("0");// 0，未提出（关注意见状态），1 已经提出
                        optProcInfoManager.saveAtt(optProcAttention);// 保存关注
                    }
                }
            }
            // savedMessage();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            saveError(e.getMessage());
        }
    }

    /**
     * 基础业务信息查询
     * 
     * @return
     */
    protected OptBaseInfo getOptBaseInfo() {
        OptBaseInfo optBase = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        if (optBase == null) {
            optBase = new OptBaseInfo();
        }
        return optBase;
    }

    protected List<FUnitinfo> ruturnUnits() {

        List<FUnitinfo> unitlist = sysUnitManager.getAllSubUnits("1");
        List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
        // 剔除非科室的部门
        if (unitlist != null && unitlist.size() > 0) {
            for (FUnitinfo fUnitinfo : unitlist) {
                if ("1".equals(fUnitinfo.getParentunit())
                        || "1".equals(fUnitinfo.getUnitcode())) {
                    continue;
                } else {
                    delList.add(fUnitinfo);
                }
            }
            unitlist.removeAll(delList);
        }

        return unitlist;
    }

    /**
     * //只获取科室一级的部门
     * 
     * @return
     */
    protected String ruturnUnitsJson() {

        List<FUnitinfo> unitlist = sysUnitManager.getAllSubUnits("1");
        List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
        // 剔除非科室的部门
        if (unitlist != null && unitlist.size() > 0) {
            for (FUnitinfo fUnitinfo : unitlist) {
                if ("1".equals(fUnitinfo.getParentunit())
                        || "1".equals(fUnitinfo.getUnitcode())) {
                    continue;
                } else {
                    delList.add(fUnitinfo);
                }
            }
            unitlist.removeAll(delList);
        }

        return unit2JSON(unitlist);
    }

    /**
     * 返回部门JSON
     * 
     * @param unitlist
     * @return
     */
    private String unit2JSON(List<FUnitinfo> unitlist) {
        if (unitlist == null) {
            return "";
        }

        JSONArray jsonArr = new JSONArray();
        for (FUnitinfo unitInfo : unitlist) {
            if (unitInfo.getUnitname() == null
                    || unitInfo.getUnitname().equals("")) {
                continue;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("nodeID", unitInfo.getUnitcode());//

            jsonObj.put("name", unitInfo.getUnitname());//

            jsonArr.add(jsonObj);
        }
        return jsonArr.toString();
    }

    /**
     * 验证移动端是否有环节操作权限
     * 
     * @param djId
     */
    public void checkNodeHasMIPPower(VuserTaskListOA oa) throws Exception {

        if (null == oa || "fwh".equals(oa.getNodeCode())||"FW_ZWH".equals(oa.getNodeCode())||"car_ap".equals(oa.getNodeCode()) ||"car_ap".equals(oa.getNodeCode()) ||"car_ap".equals(oa.getNodeCode())||"car_syfk".equals(oa.getNodeCode())   ) {
            throw new Exception("该环节不支持在移动端处理，请尽快登录PC端办理！");
        }
    }

    /* get set 方法 beg */
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

    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public OptIdeaInfoDao getOptIdeaInfoDao() {
        return optIdeaInfoDao;
    }

    public void setOptIdeaInfoDao(OptIdeaInfoDao optIdeaInfoDao) {
        this.optIdeaInfoDao = optIdeaInfoDao;
    }

    public VuserTaskListOAManager getVuserTaskListOAManager() {
        return vuserTaskListOAManager;
    }

    public void setVuserTaskListOAManager(
            VuserTaskListOAManager vuserTaskListOAManager) {
        this.vuserTaskListOAManager = vuserTaskListOAManager;
    }

    public WfNodeInstanceDao getNodeInstanceDao() {
        return nodeInstanceDao;
    }

    public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
        this.nodeInstanceDao = nodeInstanceDao;
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
    

    /* get set 方法 end */
}
