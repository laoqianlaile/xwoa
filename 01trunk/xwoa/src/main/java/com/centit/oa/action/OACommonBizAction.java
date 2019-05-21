package com.centit.oa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.optmodel.PowerRuntimeEntityAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfTeam;

/**
 * 
 * TODO oa流程业务
 * 
 * @author zkp
 * @create 2014-6-12
 * @version
 */
public class OACommonBizAction<T> extends PowerRuntimeEntityAction<T> implements
        ServletResponseAware {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected OptJspInfo jspInfo;
   
    protected String isSubFlow;// T:子流程
    protected HttpServletResponse response;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    protected String unitsJson;// 部门JSON
    protected String unitsJsonExp;// 部门JSON
    /**
     * 各业务节点可调用此方法生成业务JSON，如果数据复杂，可建视图PO
     * 
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

   
    /**
     * 页面组织机构树形结构JSON
     * @return
     */
    protected String initUnitsUsersTree() {
        JSONArray ja =oaPowerrolergroupManager.putUnitsUsersTree();
        return ja!=null?ja.toString():"";
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
                            teamrolecode, userCode);
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
            this.initTeamUsersConfig();
            super.initTemplateConfig();
            super.initTemplateFromNode();
            /**
             * 配置当前步骤名称
             */
            if(curNodeInstId!=null){
            NodeInstance nodeInst = flowEngine.getNodeInstById(curNodeInstId);
            
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            nodeName = nodeInfo.getNodeName();
            }
            /**
             * 通用运行配置，是否引用上一步骤的办理意见
             */
            if ("T".equals(moduleParam.getHasPreIdea())) {
                NodeInstance nodeInst1 = flowEngine
                        .getNodeInstById(curNodeInstId);
                if (nodeInst1.getPrevNodeInstId() != null) {
                    optProcInfo = optProcInfoManager
                            .getObjectByNodeInstId(nodeInst1
                                    .getPrevNodeInstId());
                }
            }
            /**
             * 批分功能配置
             */
            if ("T".equals(moduleParam.getHasOrgRole())) {
                
                initPFUnit(moduleParam.getXbOrgRoleCode(), null);
                
                initXbOrgConfig();
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
     * 
     * @param xbOrgRoleCode
     * @param nodeCode
     */
    private void initAPUnit(String xbOrgRoleFilter, String nodeCode) {
        List<FUnitinfo> unitList = new ArrayList<FUnitinfo>();
        if(StringUtils.isNotBlank(xbOrgRoleFilter)){
            String [] unitLists=xbOrgRoleFilter.split(",");
            if(unitLists!=null){
                for(String unitcode:unitLists){
                    FUnitinfo fUnitinfo=sysUnitManager.getObjectById(unitcode);
                    unitList.add(fUnitinfo);
                }
            }
        }
        if(unitList!=null &&unitList.size()>0){
            unitsJson=unit2JSON(unitList);
        }else{
            unitsJson="{}"; 
        }
    } 
    
    private String unit2JSON(List<FUnitinfo> unitList) {

        if (unitList == null) {
            return "";
        }

        JSONArray jsonArr = new JSONArray();
        for (FUnitinfo unitInfo : unitList) {

            // 如果机构名称为空，则不放入JSON对象
            if (unitInfo.getUnitname() == null
                    || unitInfo.getUnitname().equals("")) {
                continue;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("nodeID", unitInfo.getUnitcode());//菜单编号
            
            jsonObj.put("name", unitInfo.getUnitname());//菜单名称
           
            jsonArr.add(jsonObj);
        }
        return jsonArr.toString();
    }
    /**
     * 获取主办协办部门参数
     */
    protected void initPFUnit(String roleCode, String nodeCode) {

        List<FUnitinfo> unitList = null;
       
        if ("xtcfb".equals(nodeCode) || "wbhb".equals(nodeCode)) {
            VUserTaskList userTask = flowManager.getUserTaskInfo(curNodeInstId);
            unitList = sysUnitManager.getAllSubUnits(userTask.getUnitCode());
            unitsJson = sysUnitManager.getAllUnitsJSON(userTask.getUnitCode());
        } else {
            unitList = sysUnitManager.listObjects();
            unitsJson = sysUnitManager.getAllUnitsJSONNoTree();
        }
        /*
         * if(null!=unitList&&unitList.size()>0){//去除市总工会顶级部门
            if(null!=unitList.get(0)&&"1".equals(unitList.get(0).getUnitcode())){
                unitList.remove(0);
            }
        }
        unitsJson=unitsJson.replace("{\"nodeID\":\"1\",\"name\":\"市总工会\"},", "");//去除市总工会顶级部门
        unitsJson=unitsJson.replace(",{\"nodeID\":\"002041\",\"name\":\"中间层次\"}", "");//去除中间层次
        
        */        request.setAttribute("unitList", unitList);

        // unitsJson = sysUnitManager.getAllUnitsJSON(userTask.getUnitCode());

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

    /**
     * 获取上一步骤的办理过程信息
     * 
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
        frameList.add(GeneralOperatorAction.getStuffListFrame(optBaseInfo
                .getDjId()));// 办件附件信息
        jspInfo = new OptJspInfo();
        jspInfo.setTitle(moduleDesc);
        jspInfo.setFrameList(frameList);
    }

    /**
     * 法制业务通用的配置
     * 
     * @param djid
     * @return
     */
    protected OptHtmlFrameInfo getCommonTransFrame(String djId,
            String nodeCode, String flowPhase) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameLegend("办理信息");
        transFrameInfo.setFrameSrc("/oa/oATasksExcute!doOpt.do?djId=" + djId
                + "&flowInstId=" + super.getFlowInstId() + "&nodeInstId="
                + super.getNodeInstId() + "&moduleCode=" + moduleCode
                + "&documentTemplateIds=" + documentTemplateIds + "&nodeCode="
                + nodeCode + "&flowPhase=" + flowPhase+"&isDelete="+isDelete);
        transFrameInfo.setFrameHeight("400px");
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
        procFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!listIdeaLogs.do?djId="
                        + djId);
        procFrameInfo.setFrameLegend("过程信息");
        procFrameInfo.setFrameHeight("300px");
        return procFrameInfo;
    }

    /**
     * 监审部门回复汇聚节点iframe
     * 
     * @param msg
     * @param response
     */

    public OptHtmlFrameInfo getDepTransFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("depFrame");
        depFrameInfo.setFrameLegend("事项信息");
        depFrameInfo.setFrameSrc("/oa/oATasksExcute!getDepreply.do?djId="
                + djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
   
    /**
     * 办公室业务信息
     * 
     * @param msg
     * @param response
     */

    public OptHtmlFrameInfo optgenMilitarycasesFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("Militarycases");
        depFrameInfo.setFrameLegend("会议申请信息");
        depFrameInfo.setFrameSrc("/oa/oaMeetApply!view.do?djId=" + djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    /**
     * 车辆业务信息
     * 
     * @param djId
     * @return
     */
    public OptHtmlFrameInfo optgenOaCarApplyFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("CarApply");
        depFrameInfo.setFrameLegend("车辆申请信息");
        depFrameInfo.setFrameSrc("/oa/oaCarApply!view.do?djId=" + djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    /**
     * 督办
     * 
     * @param djId
     * @return
     */
    public OptHtmlFrameInfo optgenOaSuperviseFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("Supervise");
        depFrameInfo.setFrameLegend("督办信息");
        depFrameInfo.setFrameSrc("/oa/oaSupervise!view.do?djId=" + djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    /**
     * 会议室信息
     * 
     * @param djId
     * @return
     */
    public OptHtmlFrameInfo optgenOaMeetroomApplyFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("MeetroomApply");
        depFrameInfo.setFrameLegend("会议室信息");
        depFrameInfo.setFrameSrc("/oa/oaMeetroomApply!view.do?djId=" + djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    /**
     * 申请信息
     * 
     * @param djId
     * @return
     */
    public OptHtmlFrameInfo optgenOaApplyFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("OaApply");
        depFrameInfo.setFrameLegend("申请信息");
        depFrameInfo.setFrameSrc("/powerruntime/optApply!view.do?djId=" + djId
                + "&showback=TRUE");
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    
    /**
     * 封装关联业务信息
     * 
     * @param djId
     * @return
     */
    public OptHtmlFrameInfo optgenOaBizBindInfoFrame(String djId) {
        OptHtmlFrameInfo depFrameInfo = new OptHtmlFrameInfo();
        depFrameInfo.setFrameId("OaBizBindInfo");
        depFrameInfo.setFrameLegend("关联业务信息");
        depFrameInfo.setFrameSrc("/oa/oaBizBindInfo!listbiz4tab.do?djid=" + djId+"&startDjid="+djId);
        depFrameInfo.setFrameHeight("200px");
        return depFrameInfo;
    }
    
    @Override
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
        if (moduleParam != null) {
            FUserDetail fUserDetail = (FUserDetail) getLoginUser();

            if ("T".equals(moduleParam.getAssignTeamRole())) {
                Set<String> users = SysUserFilterEngine.calcOperators(
                        moduleParam.getTeamRoleFilter(),
                        fUserDetail.getPrimaryUnit(), null, null, null,
                        fUserDetail.getUsercode(), null);
                generalUserWithUnitJson("userjson", users);
            }
        }
    }
    
    
    /**
     * 事权个性化表单登记时 通用模块等数据加载
     * 登记流程属性默认为 QJ（业务类型）_DJ
     * 目前支持 人员选择（直接指定到人）  部门选择（接收部门）
     * 
     */
    protected  void  initSQModuleData(String  modulecode){
        
        //查询模块信息
        moduleParam=generalModuleParamManager.getObjectById(modulecode);
       
        /**
         * 获得办件角色人名单,根据参数是否需要 办件人员
         */
        this.initTeamUsersConfig();
        /**
         * 获得主办人名单,根据参数是否需要 主办人办件人员
         */
        this.initZBUsersConfig() ;
        
        /**
         * 获得协办办人名单,根据参数是否需要协办人办件人员
         */
        this.initXBUsersConfig() ;
        /**
         * 权限引擎
         */
        super.initEngineUsersConfig();
       
        /**
         * 批分功能配置
         */
        if ("T".equals(moduleParam.getHasOrgRole())) {
            
            initPFUnit(moduleParam.getXbOrgRoleCode(), null);
            
            initXbOrgConfig();
        }
    }
    
    /**
     * 与initSQModuleData对应
     * 保存事权个性化表单登记时 通用模块等数据加载
     * 登记流程属性默认为 QJ（业务类型）_DJ
     * 目前支持 人员选择（直接指定到人）  部门选择（接收部门）
     * 
     */
    protected  void  saveSQModuleData(){
        super.saveTeamRolepeople();// 保存角色
        super.saveZBTeamRolepeople();// 保存角色
        super.saveXBTeamRolepeople();// 保存角色
        super.saveEngineRolepeople();// 保存权限引擎角色
        
        super.saveZbOrg();//保存主办单位
        super.saveXbOrg();//保存协办单位
    }

    
    /**
     *人员树形式展示办件角色json
     *
     * @param attName
     * @param users
     */
    protected void generalUserWithUnitJson(String attName, Set<String> users) {
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

    
    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }

  

    public String getIsSubFlow() {
        return isSubFlow;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public void setIsSubFlow(String isSubFlow) {
        this.isSubFlow = isSubFlow;
    }

    public void ajaxResponseJson(HttpServletResponse response, JSONObject json) {
        this.ajaxResponseText(response, json.toString());
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

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }

    public String getUnitsJsonExp() {
        return unitsJsonExp;
    }

    public void setUnitsJsonExp(String unitsJsonExp) {
        this.unitsJsonExp = unitsJsonExp;
    }

    public OptHtmlFrameInfo getBizViewFrame(String djId) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        String superUrl="/"+CodeRepositoryUtil.getValue("optType", StringUtils.substringBefore(djId, "0"))+"!"+BizCommUtil.getViewOfMethod(djId)+"?djId=" +djId;
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo
        .setFrameSrc(superUrl);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
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
     
     protected void initCommonUsersConfig(String attName){
         //获取所有人员，已经递归排序过，组成插件需要的结果集,该集合根节点只有一个
         if(oaPowerrolergroupManager == null){
             ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
             oaPowerrolergroupManager = (OaPowerrolergroupManager) ctx.getBean("oaPowerrolergroupManager");
         }
         FUserDetail loginInfo = (FUserDetail) getLoginUser();
         List<Map<String,String>> unitUsers = oaPowerrolergroupManager.setCommonUser(loginInfo.getLoginname());
         if(unitUsers == null || unitUsers.size()==0 ){
             request.setAttribute(attName, new JSONArray());
             return;
         }
     }
}
