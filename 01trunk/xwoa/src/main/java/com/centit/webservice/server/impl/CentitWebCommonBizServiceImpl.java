package com.centit.webservice.server.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.Oaleadersort;
import com.centit.dispatchdoc.po.SignedReport;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.mip.po.OfficialNextProMIP;
import com.centit.mip.po.OfficialNextProMIP.DeptListMIP;
import com.centit.mip.po.OfficialNextProMIP.UserInfoMIP;
import com.centit.mip.po.OfficialNextProMIP.UserListMIP;
import com.centit.mip.po.OfficialSendProMIP;
import com.centit.mip.po.OfficialSendProMIP.NextDeptListMIP;
import com.centit.mip.po.OfficialSendProMIP.NextStepListMIP;
import com.centit.mip.po.OfficialSendProMIP.NextUserListMIP;
import com.centit.oa.po.OaSchedule;
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
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.dao.WfFlowInstanceDao;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfActionTask;
import com.centit.workflow.sample.po.WfNodeInstance;

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
    private WfFlowInstanceDao flowInstanceDao;
    private WfActionTaskDao actionTaskDao;
    
    private OaSmssendManager oaSmssendManager;
    private DispatchDocManager dispatchDocManager;
    

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
    
    //分管领导 审核顺序 过滤
    @SuppressWarnings("unused")
    protected List<VuserTaskListOA> filterTasks(List<VuserTaskListOA> vuserTaskListOAList,String userCode){
        List<Oaleadersort> oaleadersortList = dispatchDocManager.listOaLeaderSortByUserCode(userCode);
        Set<Long> activeNodeSet = new HashSet<Long>();
        if(oaleadersortList == null || oaleadersortList.size() == 0 ||
                vuserTaskListOAList == null || vuserTaskListOAList.size() == 0){
            return vuserTaskListOAList;
        }
        //得到当前用户 未激活的'领导顺序'任务
        for(Oaleadersort oaleadersort : oaleadersortList){
            if(userCode != null && userCode.equals(oaleadersort.getUsercode()) && "F".equals(oaleadersort.getIsvalid())){
                activeNodeSet.add(oaleadersort.getNodeInstId());
            }
        }
        if(activeNodeSet.size() == 0 ){
            return vuserTaskListOAList;
        }
        //将未激活的任务剔除
        Iterator<VuserTaskListOA> iterator = vuserTaskListOAList.iterator();
        while (iterator.hasNext()){
            VuserTaskListOA vuserTaskListOA = iterator.next();
            if(activeNodeSet.contains(vuserTaskListOA.getNodeInstId())){
                iterator.remove();
            }
        }
        return vuserTaskListOAList;
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
        filterMap.put("nodeInstId", Long.parseLong(mip.getNodeinstid()==null?"0":mip.getNodeinstid()));
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
        
        NodeInstance nodeInst = flowEngine.getNodeInstById(Long.valueOf(mip.getNodeinstid()));
        if(("xwsw_swyb".equals(nodeCode) && "YB".equals(optProcInfo.getIdeacode()))
                || ("xwsw_nbbl".equals(nodeCode))
                || ("xwsw_bmcy".equals(nodeCode) && "NYB".equals(optProcInfo.getIdeacode()))
                || ("xwsw_swrpf".equals(nodeCode) && "PF".equals(optProcInfo.getIdeacode()))){
            flowEngine.deleteFlowWorkTeam(nodeInst.getFlowInstId(), "zbcbr");
        }
        
        // 验证环节是否支持在pc端处理
        checkNodeHasMIPPower(userTask);

        // 获取 流程参数 默认值

        extractFlowOptParam();

        setDateByMIP(mip);

        setModuleParamDate();

        saveOpt();
                
        saveIdeaInfo();
        Set<Long> nextNode = new HashSet<Long>();//发送短信
        if("xwsw_bmcy".equals(nodeCode)&&"F".equals(optProcInfo.getIdeacode())) {
            Long oneNode=flowManager.rollbackOpt(nodeInst.getNodeInstId(), mip.getUserid());
            nextNode.add(oneNode);
        }else if("xwbmsw_bmcy".equals(nodeCode)&&"F".equals(optProcInfo.getIdeacode())){
            Long oneNode=flowManager.rollbackOpt(nodeInst.getNodeInstId(), mip.getUserid());
            nextNode.add(oneNode);
        }else if("tysw_bmcy".equals(nodeCode)&&"F".equals(optProcInfo.getIdeacode())){
            Long oneNode=flowManager.rollbackOpt(nodeInst.getNodeInstId(), mip.getUserid());
            nextNode.add(oneNode);
        }else {
            nextNode = submitNode();
        }
        
      //副局批分后删除记录，不然提交给其他副局时候，该副局也会受到待办
        if("xwsw_nbbl".equals(nodeCode)){
            flowEngine.deleteFlowWorkTeam(nodeInst.getFlowInstId(), "FJZSH",mip.getUserid());
        }
        
        //多实例节点导致节点失效
        if("xwsw_swyb".equals(nodeCode)){
            List<NodeInstance> wfids= flowManager.listFlowInstNodes(nodeInst.getFlowInstId());
            if(wfids!=null && wfids.size()>0){
                for(NodeInstance nodeInstance:wfids){
                    if("I".equals(nodeInstance.getNodeState())){
                        flowManager.activizeNodeInstance(nodeInstance.getNodeInstId(), "99999999");
                    }
                }
            }
        }
        //多实例节点导致节点失效
        boolean istrue = false;
        if(("xwsw_swyb".equals(nodeCode) && "T".equals(optProcInfo.getIdeacode()))
                || ("xwsw_bmcy".equals(nodeCode) && "B".equals(optProcInfo.getIdeacode()))
                || ("xwsw_bsw".equals(nodeCode) && "Y".equals(optProcInfo.getIdeacode()))){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("wfinstid", String.valueOf(super.getFlowInstId()));
            List<WfNodeInstance> wfids= nodeInstanceDao.listObjects(map);
            if(wfids!=null && wfids.size()>0){
                for(WfNodeInstance nodeInstance:wfids){
                    if("E".equals(nodeInstance.getNodeState())){
                        nodeInstanceDao.updtNodeInstState(nodeInstance.getNodeInstId(), "N");
                        istrue = true;
                    }
                }
                if(istrue){
                    flowInstanceDao.updtFlowInstState(super.getFlowInstId(), "N");
                    OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(nodeInst.getFlowInstId());//汇总 的时候是否完成 状态修改
                    bean.setBizstate("W");
                    optBaseInfoManager.saveObject(bean);
                }
            }
        }
        //内部转办重复给一个人，只收到一件待办
        List<String> nameall = new ArrayList<String>();
        if("xwsw_swyb".equals(nodeCode) && "YB".equals(optProcInfo.getIdeacode())){
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> maptask = new HashMap<String, Object>();
            map.put("wfinstid", String.valueOf(nodeInst.getFlowInstId()));
            map.put("nodestate", "N");
            List<WfNodeInstance> wfids= nodeInstanceDao.listObjects(map);
            if(wfids!=null && wfids.size()>0){
                for(WfNodeInstance nodeInstance:wfids){
                    maptask.put("nodeinstid", nodeInstance.getNodeInstId());
                    List<WfActionTask> tasks = actionTaskDao.listObjects(maptask);
                    if(null != tasks && tasks.size() > 0){
                       for(WfActionTask task :tasks){
                           if("A".equals(task.getTaskState())){
                               if(nameall.size() ==0){
                                   nameall.add(task.getUserCode());
                               }else{
                                   if(nameall.contains(task.getUserCode())){
                                   String sql = "update WfActionTask t set t.taskState = 'C' where t.nodeInstId ='" + nodeInstance.getNodeInstId()
                                                   + "' and userCode = '" + task.getUserCode() +"'";
                                   String sql1 = "update WfNodeInstance t set t.nodeState = 'C' where t.nodeInstId ='" + nodeInstance.getNodeInstId()+"'";
                                   actionTaskDao.doExecuteHql(sql);
                                   nodeInstanceDao.doExecuteHql(sql1);
                                   }else{
                                       nameall.add(task.getUserCode());
                                   }
                               }
                           }
                       } 
                    }
                }
            }
        }
        
        
        
        //如果是 收文流程批分，需要单独保存分管领导
        if("xwsw_bfz".equals(object.getNodeCode())){
            if(nextNode != null && nextNode.size() > 0){
                saveLeaderSort(nextNode.iterator().next());
            }
        }
      //如果是领导意见汇总，需要单独保存分管领导
        if("xwsw_ldyjhz".equals(object.getNodeCode())){
            if(nextNode != null && nextNode.size() > 0){
                saveLeaderSort(nextNode.iterator().next());
            }
        }
        //如果是分管领导审核 需要更新 领导排序状态位
        if("xwsw_fldy".equals(object.getNodeCode())){
            refreshLeaderSort(mip);
        }
        
        if(null!=nextNode){//发送短信
            oaSmssendManager.saveFlowMsgs(mip.getSendmessg(), mip.getUserid(), nextNode);
            oaSmssendManager.executeSendMsg();
        }
        
   
    }
    
    
    //保存 分管领导排序
    private void saveLeaderSort(Long nodeInstId){
        List<String> userCodes = null;
        if(teamUserCodes != null && teamUserCodes.trim().length() > 0){
            userCodes = new ArrayList<String>(Arrays.asList(teamUserCodes.split(",")));
        }
        if(userCodes == null || userCodes.size() == 0){
            return;
        }
        //初始化第一个人的 状态为用，其他不可用
        for(int i=0;i < userCodes.size();i++){
            Oaleadersort oaleadersort = new Oaleadersort();
            if(i == userCodes.size() - 1){
                oaleadersort.setIsvalid("T");
            }else{
                oaleadersort.setIsvalid("F");
            }
            oaleadersort.setLeaderorder(String.valueOf(i));
            oaleadersort.setNodeInstId(nodeInstId);
            oaleadersort.setStagecode(object.getNodeCode());
            oaleadersort.setTeamrolecode(teamRoleCode);
            oaleadersort.setUsercode(userCodes.get(i));
            dispatchDocManager.saveOaLeaderSort(oaleadersort);
        }
    }
    //更新 领导排序;将当前可用的记录变为不可用，order+1的记录变为可用
    private void refreshLeaderSort(OfficialSendProMIP mip){
        List<Oaleadersort> oaleadersortList = dispatchDocManager.listOaLeaderSortByNode(object.getNodeInstId());
        if(oaleadersortList == null || oaleadersortList.size() == 0 ){
            return;
        }
        //和当前操作用户比较 将状态设置为不可用。oaleadersortList这个已经按order排序，所以下面直接判断
        
        
        
        boolean flag = false;
        for(int i = oaleadersortList.size()-1;i >= 0;i--){
            Oaleadersort oaleadersort = oaleadersortList.get(i);
            if(mip.getUserid()!= null
                    && mip.getUserid().equals(oaleadersort.getUsercode())){
                oaleadersort.setIsvalid("F");
                flag = true;
                dispatchDocManager.saveOaLeaderSort(oaleadersort);
                continue;
            }
            if(flag){
                oaleadersort.setIsvalid("T");
                dispatchDocManager.saveOaLeaderSort(oaleadersort);
                break;
            }
        }
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
        xbUserRoleCode = moduleParam.getXbOrgRoleCode();
        zbUserRoleCode = moduleParam.getZbUserRoleCode();
    }

    /**
     * zbOrg;// 主办单位代码 xbOrg;// 协办单位代码 attUser;// 关注人员代码 teamUser;//
     * 办件用户代码(bjUserName) zbUser; xbUser; engineUser
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
                    if ("xbUser".equals(nextUser.getUserRole())) {
                        xbUserCodes = nextUser.getNextpersonid();
                        xbUserNames = getNamesByCodes(xbUserCodes,
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

   
            optProcInfoManager.saveObject(optProcInfo);// 保存办件人员
            // 保存关注人员
            this.saveAtt();
            this.saveZBUser();// 保存主办承办人
            this.saveXBUser();// 保存审核人
            this.saveTeamRolepeople();
            this.saveEngineRolepeople();// 保存权限引擎角色

            

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
            xbUserList = super.initXBUsersConfig(moduleParam);

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
        List<OfficialNextProMIP> nextStepList = new ArrayList<OfficialNextProMIP>();
       

        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
         if(null!=moduleParam){
             //默认初始值均为F
             
             
             // 办理意见
             ideaCatalog = moduleParam.getIdeaCatalog();// 结果代码

             if ("T".equals(moduleParam.getHasIdea())
                     && StringUtils.isNotBlank(ideaCatalog)) {
                 dataDictionaryList = CodeRepositoryUtil.getDictionary(ideaCatalog);// 办理意见列表
             }
             
             //根据配置准备各种数据
             //初始数据
             JSONObject initModuleData = prepareInitModuleData();
             
             // 设置办件角色
             if (StringUtils.isNotBlank(moduleParam.getAssignTeamRole())
                     && "T".equals(moduleParam.getAssignTeamRole())) {
                 if (null != teamUserList && teamUserList.size() > 0) {
                     initModuleData.put("bj", "T");
                 }

             }
             
             // 设置权限引擎角色
             if (StringUtils.isNotBlank(moduleParam.getAssignEngineRole())
                     && "T".equals(moduleParam.getAssignEngineRole())) {
                 if (null != engineUserList && engineUserList.size() > 0) {// 办件角色
                     initModuleData.put("en", "T");
                 }
             }
             
             // 设置主办人
             if (StringUtils.isNotBlank(moduleParam.getHasZbUser())
                     && "T".equals(moduleParam.getHasZbUser())) {
                 if (null != zbUserList && zbUserList.size() > 0) {
                     initModuleData.put("zbu", "T");
                 }

             }
             
             // 设置协办人
             if (StringUtils.isNotBlank(moduleParam.getHasXbUser())
                     && "T".equals(moduleParam.getHasXbUser())) {
                 if (null != xbUserList && xbUserList.size() > 0) {
                     initModuleData.put("xbu", "T");
                 }

             }
             
             // 是否批分
             if (StringUtils.isNotBlank(moduleParam.getHasOrgRole())
                     && "T".equals(moduleParam.getHasOrgRole())) {
                 if (null != zbOrgList && zbOrgList.size() > 0) {// 主办
                     initModuleData.put("zbOrg", "T");  // 3 主办单位
                 }
                 if (null != xbOrgList && xbOrgList.size() > 0) {// 协办
                     initModuleData.put("xbOrg", "T");  // 4 协办单位
                 }
             }
            
             
             nextStepList=initCommonFilterMapByOpt(  dataDictionaryList,initModuleData);// 通用的页面页面流转控制 与数据字典的datadesc关联
    
         }
         
        return nextStepList;
    }

    
  /***
   * 通用的页面页面流转控制 与数据字典的datadesc关联
   * @param mip
   * @param initModuleData
 * @param string 
   * @return
   */
    private List<OfficialNextProMIP> initCommonFilterMapByOpt(List<FDatadictionary> dataDictionaryList,
            JSONObject initModuleData) {
        
      
        UserListMIP bjuserList = null;//办件角色
        UserListMIP enuserList = null;//权限引擎
        UserListMIP zbuserList = null;//主办人
        UserListMIP xbuserList = null;//协办人
        DeptListMIP deptListzb = null;
        DeptListMIP deptListxb = null;
        
        bjuserList = new UserListMIP(
                moduleParam.getTeamRoleName(), "teamUser",
                null, teamUserList);
        enuserList = new UserListMIP(
                moduleParam.getEngineRoleName(), "engineUser",
                null, engineUserList);
        zbuserList = new UserListMIP(
                moduleParam.getTeamRoleName(), "zbUser",
                null, zbUserList);
        xbuserList = new UserListMIP(
                moduleParam.getTeamRoleName(), "xbUser",
                null, xbUserList);
        deptListzb = new DeptListMIP("主办处室", "zbOrg", "1",
                zbOrgList);
        deptListxb = new DeptListMIP(
                moduleParam.getXbOrgRoleName(), "xbOrg", null,
                xbOrgList);
        
        
        List<OfficialNextProMIP> nextStepList = new ArrayList<OfficialNextProMIP>();
        
        //有意见--根据模块配置及字典扩展规则处理数据
        if (null != dataDictionaryList && dataDictionaryList.size() > 0) {
            
            for (FDatadictionary dataDic : dataDictionaryList) {
                OfficialNextProMIP mip = new OfficialNextProMIP();
                mip.setStepid(dataDic.getDatacode());// T\F\B\or others
                mip.setStepname(dataDic.getDatavalue());
                if(StringUtils.isNotBlank(dataDic.getDatadesc())&&JsonUtil.isJson(dataDic.getDatadesc()) ){//验证是否为合法json
                  
                    JSONObject jsonObject = JSONObject.fromObject(dataDic.getDatadesc());
                    
                    mip.setUserlist(new ArrayList<UserListMIP>());
                    mip.setDeptlist(new ArrayList<DeptListMIP>());
                   
                    if(jsonObject.containsKey("bj")&&StringUtils.isNotBlank(jsonObject.getString("bj"))){
                        if("T".equals(initModuleData.getString("bj"))&&"T".equals(jsonObject.getString("bj"))){
                            mip.getUserlist().add(bjuserList);
                        }
                    }
                    if(jsonObject.containsKey("en")&&StringUtils.isNotBlank(jsonObject.getString("en"))){
                        if("T".equals(initModuleData.getString("en"))&&"T".equals(jsonObject.getString("en"))){
                            mip.getUserlist().add(enuserList);
                        }
                    }
                    if(jsonObject.containsKey("zbu")&&StringUtils.isNotBlank(jsonObject.getString("zbu"))){
                        if("T".equals(initModuleData.getString("zbu"))&&"T".equals(jsonObject.getString("zbu"))){
                            mip.getUserlist().add(zbuserList);
                        }
                    }
                    if(jsonObject.containsKey("xbu")&&StringUtils.isNotBlank(jsonObject.getString("xbu"))){
                        if("T".equals(initModuleData.getString("xbu"))&&"T".equals(jsonObject.getString("xbu"))){
                            mip.getUserlist().add(xbuserList);
                        }
                    }
                    if(jsonObject.containsKey("org")&&StringUtils.isNotBlank(jsonObject.getString("org"))){
                        if("T".equals(initModuleData.getString("zbOrg"))&&"T".equals(jsonObject.getString("org"))){
                            mip.getDeptlist().add(deptListzb);
                        }
                    }
                    if(jsonObject.containsKey("org")&&StringUtils.isNotBlank(jsonObject.getString("org"))){
                        if("T".equals(initModuleData.getString("xbOrg"))&&"T".equals(jsonObject.getString("org"))){
                            mip.getDeptlist().add(deptListxb);
                        }
                    } 
                }

                nextStepList.add(mip);
            }
        }
        //无意见--根据模块配置处理数据
        if (null == dataDictionaryList || dataDictionaryList.size() == 0) {
            
            OfficialNextProMIP mip = new OfficialNextProMIP();
            mip.setStepid(JsonUtil.replaceNullString(null));
            mip.setStepname(JsonUtil.replaceNullString(null));
            
            if("T".equals(initModuleData.getString("bj"))){
                mip.getUserlist().add(bjuserList);
            }
            if("T".equals(initModuleData.getString("en"))){
                mip.getUserlist().add(enuserList);
            }
            if("T".equals(initModuleData.getString("zbu"))){
                mip.getUserlist().add(zbuserList);
            }
            if("T".equals(initModuleData.getString("xbu"))){
                mip.getUserlist().add(xbuserList);
            }
            if("T".equals(initModuleData.getString("zbOrg"))){
                mip.getDeptlist().add(deptListzb);
            }
            if("T".equals(initModuleData.getString("xbOrg"))){
                mip.getDeptlist().add(deptListxb);
            }
            
            nextStepList.add(mip);
        }
        
        
        
        
        
        
        return nextStepList;
    }

    /**
     * 设置默认值
     * 枚举需要控制的逻辑块--默认不显示
     * @return
     */
    private   JSONObject prepareInitModuleData(){
        
        JSONObject initModuleData = new JSONObject();
        initModuleData.put("bj", "F");  //1办件角色
        initModuleData.put("en", "F");  // 2权限引擎角色
        initModuleData.put("zbu", "F");  // 3 主办角色
        initModuleData.put("xbu", "F");  // 4 协办角色
        initModuleData.put("zbOrg", "F");  // 3 主办单位
        initModuleData.put("xbOrg", "F");  // 4 协办单位
        return  initModuleData;
    }
    


    /**
     * 保存办件角色
     */
    protected void saveTeamRolepeople() {
        this.saveTeamRole(teamRoleCode, teamUserCodes);
    }

    /*
     * 保存主办承办人--办件角色的一种
     */
    protected void saveZBUser() {
        this.saveTeamRole(zbUserRoleCode, zbUserCodes);
    }

    /**
     * 保存协办人--办件角色的一种
     */
    protected void saveXBUser() {
        this.saveTeamRole(xbUserRoleCode, xbUserCodes);
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

        moduleCode = getmoduleCode4tOptParam(userTaskInfo.getOptParam());
        
          if(StringUtils.isNotBlank(moduleCode)){//判断moduleCode是否为空并取出对应主键所对一条信息
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);
            
                if(null == moduleParam||null != moduleParam &&"F".equals(moduleParam.getCanphoneopt())){//判断是只在PC端处理
                    throw new Exception("该环节不支持在移动端处理，请尽快登录PC端办理！");
                        
                }  
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

    public DispatchDocManager getDispatchDocManager() {
        return dispatchDocManager;
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }

    public WfFlowInstanceDao getFlowInstanceDao() {
        return flowInstanceDao;
    }

    public void setFlowInstanceDao(WfFlowInstanceDao flowInstanceDao) {
        this.flowInstanceDao = flowInstanceDao;
    }

    public WfActionTaskDao getActionTaskDao() {
        return actionTaskDao;
    }

    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }
    

    /* get set 方法 end */
}
