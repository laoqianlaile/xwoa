package com.centit.oa.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.core.task.TaskExecutor;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.service.DocRelativeManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetroomApply;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetroomApplyManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.action.OptApplyAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcAttentionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.service.WorkCalendar;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfOrganize;

/**
 * oa 公用流转ACTION
 * 
 * @author zkp
 * @create 2014-6-12
 * @version
 */
public class OATasksExcuteAction extends OACommonBizAction<OptProcInfo>
        implements ServletResponseAware {

    private static final long serialVersionUID = 1L;
    HttpServletResponse response;

    private FunctionManager functionManager;
    private DocRelativeManager docRelativeManager;
    private IncomeDocManager incomeDocManager;
    private GeneralModuleParamManager generalModuleParamManager;
    // 发文frame类型（编辑/查看）
    private String frameType;
    // 收发文关联frame类型（编辑/查看）
    private String docRelativeFrameType;
    private String roleCode;
    // // tab页显示的组装frameList begin
    // tabFrameList其中的frameId必须唯一，建议使用表示表单所对应的信息来命名，该frameList只在tab页中使用，页面使用include的方式，因此在一个方法中组装即可
    private List<OptHtmlFrameInfo> tabFrameList;
    private String tabShowFrameId; // 默认显示的frameId，必须与tabFrameList中的唯一一个对应
    // // tab页显示的组装frameList end

    // Tab显示详细信息
    private List<OptNewlyIdea> optNewlyIdeaList = new ArrayList<OptNewlyIdea>();
    private String curUrl;

    private OaMeetApplyManager oaMeetApplyManager;
    private OaCarApplyManager oaCarApplyManager;
    private OaSuperviseManager oaSuperviseManager;//督办
    private OaMeetroomApplyManager oaMeetroomApplyManager;
    private OaBizBindInfoManager oaBizBindInfoManager;//关联业务

    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    
    private List<WfOrganize> unticodelist = new ArrayList<WfOrganize>(); // 送达部门list
    private List<OptProcInfo> stuffInfoLList;
    
    public List<WfOrganize> getUnticodelist() {
        return unticodelist;
    }

    public void setUnticodelist(List<WfOrganize> unticodelist) {
        this.unticodelist = unticodelist;
    }

    /******************************** 各节点 操作定义整合 ********************************/
    /**
     * 节点操作通用配置
     * 
     * @return
     */
    public String genOACommonFrame() {
         return boundAllInfoframeModuleTwo( null);//null 通用办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    
    /**
     * 组合所有查看页面 模板2
     * 左侧 拟文单+附件+办理信息+保存提交按钮  右侧 快速定位+过程信息+流程图等信息查看
     * @param frameList
     * @param djId
     */
    private String boundAllInfoframeModuleTwo(OptHtmlFrameInfo todoFrameInfo) {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        if(fuser==null){
            return "login";
        }
        
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        
        //页面布局开始
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        
        // 基础业务信息查看
        frameList=getBaseInfoIframe( frameList,optBaseInfo.getDjId());
        
        frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 附件
        
        if(null==todoFrameInfo){
            frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面
        }else{
            frameList.add(todoFrameInfo);//个性化办理界面
        
        }
        jspInfo = new OptJspInfo();
        jspInfo.setFrameList(frameList);
        //页面布局结束
        
        
        //功能点开始
        
        //是否有关联事项
        boolean flag = this.getOaBizInfolist(optBaseInfo.getDjId());
        request.setAttribute("hasRelSubject", flag);
       
        // 配置启动事权或者发文的开关
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        
        //流程时限
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);

        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        //从首页日历处以模式窗口方式进入 by dk 2016-2-16
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
       //功能点结束
        
        return "optframe";
        
    }
    /**
     * 根据djId获取业务信息,
     * 并追加在frameList
     * @param frameList
     * @param djId
     */
    public List<OptHtmlFrameInfo> getBaseInfoIframe(List<OptHtmlFrameInfo>  frameList,String djId){
       
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
       
        String bizMenth=this.initReturnFrame(djId);
        // 基础信息查看
        if ("genOaMeetApplyFrame".equals(bizMenth)) {// 办公室申请
            frameList.add(super.optgenMilitarycasesFrame(djId));
        } else if ("genOaCarApplyFrame".equals(bizMenth)) {

            frameList.add(super.optgenOaCarApplyFrame(djId));
        } else if ("genOaSuperviseFrame".equals(bizMenth)) {

            frameList.add(super.optgenOaSuperviseFrame(djId));
        } else if ("genOaMeetroomApplyFrame".equals(bizMenth)) {

            frameList.add(super.optgenOaMeetroomApplyFrame(djId));
        } 
        // 监审业务部门回复汇聚
        if ("HMFZ_JSBB".equals(object.getNodeCode())) {
            frameList.add(super.getDepTransFrame(djId));
        }
        // 事项登记查看申请人信息
        if ("genOaApplyFrame".equals(this.initReturnFrame(djId))
                || "genOaApplyQBFrame".equals(this.initReturnFrame(djId))) {
            frameList
                    .add(OptApplyAction.getBizDataViewFrame(djId));
        }

        return frameList;
    }
    
    
    /**
     * 待办页面  基本信息+办理信息+tab
     * 历史版本保留
     */
    private void boundAllInfoframeModule(){
                OptBaseInfo baseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        request.setAttribute("sqdjId", baseInfo.getDjId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        this.setNodeInstId(object.getNodeInstId());

        frameList.add(super.getBizViewFrame(baseInfo.getDjId()));

        // 监审业务部门回复汇聚
        if ("HMFZ_JSBB".equals(object.getNodeCode())) {
            frameList.add(super.getDepTransFrame(baseInfo.getDjId()));
        }
        if ("car_sh".equals(object.getNodeCode())
                || "car_sp".equals(object.getNodeCode())) {
            frameList.add(OaCarApplyAction.getBizTimeViewFrame(baseInfo
                    .getDjId()));
        }

        if ("hys_sp".equals(object.getNodeCode())) {
            frameList.add(OaMeetApplyAction.getBizTimeViewFrame(baseInfo
                    .getDjId()));
        }
        // 事项登记查看申请人信息
        if ("genOaApplyFrame".equals(this.initReturnFrame(baseInfo.getDjId()))
                || "genOaApplyQBFrame".equals(this.initReturnFrame(baseInfo
                        .getDjId()))) {
            frameList
                    .add(OptApplyAction.getBizDataViewFrame(baseInfo.getDjId()));
        }

        if (optProcInfoManager.isShowIdeaLogs(baseInfo.getDjId())) {
            // 环节办理意见
            frameList.add(GeneralOperatorAction.getShowIdeaListFrame(baseInfo
                    .getDjId()));
        }

        // 引用通用的办理界面
        frameList.add(super.getCommonTransFrame(baseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));

        this.boundAllInfoframe(frameList, baseInfo.getDjId(),
                this.initReturnFrame(baseInfo.getDjId()));
    }
    /**
     * 规整待办界面，根据不同的业务要求
     */
    private String initReturnFrame(String djid) {

        String returnfame = "";
        if (djid.indexOf("HYSQ") != -1) {// 会议
            returnfame = "genOaMeetApplyFrame";
        } else if (djid.indexOf("CAR") != -1) {// 车辆
            returnfame = "genOaCarApplyFrame";
        } else if (djid.indexOf("DCDB") != -1) {//
            returnfame = "genOaSuperviseFrame";
        } else if (djid.indexOf("HYSSQ") != -1) {// 会议室
            returnfame = "genOaMeetroomApplyFrame";
        } else if (djid.indexOf("SQ") != -1) {// 事权
            returnfame = "genOaApplyFrame";
        } else if (djid.indexOf("QB") != -1) {// 事权
            returnfame = "genOaApplyQBFrame";
        }
        // TODO ...

        return returnfame;
    }

    /**
     * 办理信息-会议室、车辆安排
     * 
     * @return
     */
    public String doMeeting() {
        
       OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
       this.setNodeInstId(object.getNodeInstId());
       /*      List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        this.setNodeInstId(object.getNodeInstId());
        String actionname = "";
        String bizMenth = "";
        if ("car_ap".equals(object.getNodeCode())) {
            frameList.add(OaCarApplyAction.getBizTimeViewFrame(optBaseInfo
                    .getDjId()));
        }
        if ("hys_ap".equals(object.getNodeCode())) {
            frameList.add(OaMeetApplyAction.getBizTimeViewFrame(optBaseInfo
                    .getDjId()));
        }
        if ("hys_ap".equalsIgnoreCase(object.getNodeCode())) {// 会议安排
            actionname = "oaMeetApply";
            bizMenth = "genOaMeetApplyFrame";
        } else if ("car_ap".equalsIgnoreCase(object.getNodeCode())) {// 车辆安排
            actionname = "oaCarApply";
            bizMenth = "genOaCarApplyFrame";
        } else if ("hys_hysap".equalsIgnoreCase(object.getNodeCode())) {// 会议室安排
            actionname = "oaMeetroomApply";
            bizMenth = "genOaMeetroomApplyFrame";
        }

        if (optProcInfoManager.isShowIdeaLogs(optBaseInfo.getDjId())) {
            // 环节办理意见
            frameList.add(GeneralOperatorAction
                    .getShowIdeaListFrame(optBaseInfo.getDjId()));
        }

        // 引用通用的办理界面
        frameList.add(this.getdoMeetingFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(), actionname));

        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(), bizMenth);

        super.initFlowTime();
        *//**
         * 加载该环节是否存在督办信息
         *//*

        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);*/
     
       
       String actionname = "";
       String bizMenth = "";
       if ("hys_ap".equalsIgnoreCase(object.getNodeCode())) {// 会议安排
           actionname = "oaMeetApply";
       } else if ("car_ap".equalsIgnoreCase(object.getNodeCode())) {// 车辆安排
           actionname = "oaCarApply";
       } else if ("hys_hysap".equalsIgnoreCase(object.getNodeCode())) {// 会议室安排
           actionname = "oaMeetroomApply";
       }
       
         return  boundAllInfoframeModuleTwo(this.getdoMeetingFrame(optBaseInfo.getDjId(),
                 object.getNodeCode(), object.getFlowPhase(), actionname));//置文号办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    /**
     * 办理信息-会议/会议室/车辆安排-获取定制方法
     * 
     * @return
     */
    private OptHtmlFrameInfo getdoMeetingFrame(String djId, String nodeCode,
            String flowPhase, String actionname) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameLegend("办理信息");
        transFrameInfo.setFrameSrc("/oa/" + actionname + "!doMeeting.do?djId="
                + djId + "&flowInstId=" + super.getFlowInstId()
                + "&nodeInstId=" + super.getNodeInstId() + "&moduleCode="
                + moduleCode + "&documentTemplateIds=" + documentTemplateIds
                + "&nodeCode=" + nodeCode + "&flowPhase=" + flowPhase);
        return transFrameInfo;
    }

    /**
     * 办理信息-会议室使用反馈
     * 
     * @return
     */
    public String doBack() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        String actionname = "";
        if ("hys_fk".equalsIgnoreCase(object.getNodeCode())) {// 会议室反馈
            actionname = "oaMeetApply";
        } else if ("car_syfk".equalsIgnoreCase(object.getNodeCode())) {// 车辆使用反馈
            actionname = "oaCarApply";
        }
        return boundAllInfoframeModuleTwo( this.getdoBackFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(), actionname) );
    }

    /**
     * 办理信息-会议室使用反馈-获取定制方法
     * 
     * @return
     */
    private OptHtmlFrameInfo getdoBackFrame(String djId, String nodeCode,
            String flowPhase, String actionname) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameLegend("使用反馈");
        transFrameInfo.setFrameSrc("/oa/" + actionname + "!doBack.do?djId="
                + djId + "&flowInstId=" + super.getFlowInstId()
                + "&nodeInstId=" + super.getNodeInstId() + "&moduleCode="
                + moduleCode + "&documentTemplateIds=" + documentTemplateIds
                + "&nodeCode=" + nodeCode + "&flowPhase=" + flowPhase);
        return transFrameInfo;
    }

    /**
     * 回退补正登记节点
     * 
     * @return
     */
    public String reEditHYS() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        this.setNodeInstId(object.getNodeInstId());

        String actionname = "";

        if ("hys_sq".equalsIgnoreCase(object.getNodeCode())) {// 会议室
            actionname = "oaMeetApply";
        } else if ("car_sq".equalsIgnoreCase(object.getNodeCode())) {// 车辆使用
            actionname = "oaCarApply";
        } else if ("hys_hyssq".equalsIgnoreCase(object.getNodeCode())) {// 车辆使用
            actionname = "oaMeetroomApply";
        }

        if (optProcInfoManager.isShowIdeaLogs(optBaseInfo.getDjId())) {
            // 环节办理意见
            frameList.add(GeneralOperatorAction
                    .getShowIdeaListFrame(optBaseInfo.getDjId()));
        }

        // 引用通用的办理界面
        frameList.add(this.getReEdit(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(), actionname));

        String bizMenth = "";
        if ("hys_sq".equalsIgnoreCase(object.getNodeCode())) {// 会议室
            bizMenth = "genOaMeetApplyFrame";
        } else if ("car_sq".equalsIgnoreCase(object.getNodeCode())) {// 车辆
            bizMenth = "genOaCarApplyFrame";
        } else if ("hys_hyssq".equalsIgnoreCase(object.getNodeCode())) {// 车辆
            bizMenth = "genOaMeetroomApplyFrame";
        }

        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(), bizMenth);

        super.initFlowTime();
        /**
         * 加载该环节是否存在督办信息
         */

        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        return "optframe";
    }

    /**
     * 会退到事权登记节点
     * 
     * @return
     */
    public String doEditFlowOpt() {
        OptBaseInfo baseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        request.setAttribute("sqdjId", baseInfo.getDjId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        this.setNodeInstId(object.getNodeInstId());
        String actionname = "";
        // if("SQ_BXDJ".equals(object.getNodeCode())||"SQ_DJ".equals(object.getNodeCode())){
        actionname = "optApply";
        frameList.add(this.getReEditSQ(baseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(), actionname,
                baseInfo.getPowerid()));
        // }
        this.boundAllInfoframe(frameList, baseInfo.getDjId(),
                this.initReturnFrame(baseInfo.getDjId()));

        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);

        // 加载该环节是否存在督办信息

        this.dcdbInfo(object.getNodeCode());

        return "optframe";
    }

    /**
     * 回退事权登记节点
     * 
     * @return
     */
    private OptHtmlFrameInfo getReEditSQ(String djId, String nodeCode,
            String flowPhase, String actionname, String itemId) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameSrc("/powerruntime/" + actionname
                + "!editOptBaseSQ.do?djId=" + djId + "&flowInstId="
                + super.getFlowInstId() + "&nodeInstId="
                + super.getNodeInstId() + "&moduleCode=" + moduleCode
                + "&documentTemplateIds=" + documentTemplateIds + "&nodeCode="
                + nodeCode + "&flowPhase=" + flowPhase + "&itemId=" + itemId);
        return transFrameInfo;
    }

    /**
     * 回退补正登记节点
     * 
     * @return
     */
    private OptHtmlFrameInfo getReEdit(String djId, String nodeCode,
            String flowPhase, String actionname) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameSrc("/oa/" + actionname + "!doReEdit.do?djId="
                + djId + "&flowInstId=" + super.getFlowInstId()
                + "&nodeInstId=" + super.getNodeInstId() + "&moduleCode="
                + moduleCode + "&documentTemplateIds=" + documentTemplateIds
                + "&nodeCode=" + nodeCode + "&flowPhase=" + flowPhase);
        return transFrameInfo;
    }

    /********************************** Tab组装 ****************************************/
    /**
     * 组合所有查看页面1
     * 
     * @param frameList
     * @param djId
     */
    private void boundAllInfoframe(List<OptHtmlFrameInfo> frameList,
            String djId, String bizMenth) {

        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo fif = flowEngine.getNodeInfoById(nodeInst.getNodeId());
        // frameList 原先tab方式显示
        //frameList.add(getAllInfoFrame(djId, bizMenth));// 查看全部过程信息

        // frameList 页面列表显示
        // frameList=getAllInfoListFrame(frameList,djId,bizMenth);
        jspInfo = new OptJspInfo();
        jspInfo.setTitle(fif.getNodeName()
                + (fif.getNodeName().endsWith("办理") ? "" : "办理"));
        jspInfo.setFrameList(frameList);
    }

    @SuppressWarnings("unused")
    private List<OptHtmlFrameInfo> getAllInfoListFrame(
            List<OptHtmlFrameInfo> frameList, String djId, String bizMenth) {

        frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
        frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
        // 查关联的源信息
        frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId, "S", null));// 主动发起的关联
        frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId, "E", null));// 被其他事项关联
        // 督查督办查看督办发起信息（原先没有）
        // if ("genOaSuperviseFrame".equals(bizMenth)) {
        // frameList.add(viewSuperviseListFrame(djId));
        // }

        String djid = djId;

        optNewlyIdeaList.clear();
        // 基础信息查看
        if ("genOaMeetApplyFrame".equals(bizMenth)) {// 办公室申请
            optNewlyIdeaList.add(optNewly_genMilitarycasesFrame(djid));
        } else if ("genOaCarApplyFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaCarApplyFrame(djid));
        } else if ("genOaSuperviseFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaSuperviseFrame(djid));
        } else if ("genOaMeetroomApplyFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaMeetroomApplyFrame(djid));
        } else if ("genOaApplyFrame".equals(bizMenth)) {
            // 事项登记查看申请人信息
            optNewlyIdeaList.add(optNewly_genOaApplyFrame(djid));
        }
        return frameList;
    }

    /**
     * TAB属性配置
     * 
     * @param djId
     * @param string
     * @return
     */
    private OptNewlyIdea optNewly_genOaApplyFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 5);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("申请信息");
        newlyIdea.setUrl("/powerruntime/optApply!view.do?djId=" + djId
                + "&showback=TRUE");
        return newlyIdea;
    }

    /**
     * 组合所有查看页面2
     * 
     * @param djId
     * @return
     */
    private OptHtmlFrameInfo getAllInfoFrame(String djId, String bizMenth) {
        String itemType = null;
        if(StringUtils.isNotEmpty(djId)){
            itemType = djId.replaceAll("[0-9]+", "");
        }
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        procFrameInfo.setFrameId("AllInfoFrame");
        procFrameInfo.setFrameSrc("/oa/oATasksExcute!viewAllList.do?djId="
                + djId + "&flowInstId=" + super.getFlowInstId() + "&bizMenth="
                + bizMenth
                +"&itemType="+itemType);
        procFrameInfo.setFrameHeight("400px");
        return procFrameInfo;
    }

    public String viewAllList() {
        String bizMenth = request.getParameter("bizMenth");

        String djid = request.getParameter("djId");
        if (object != null) {
            djid = object.getDjId();
        }
        optNewlyIdeaList.clear();
        // 基础信息查看
        if ("genOaMeetApplyFrame".equals(bizMenth)) {// 办公室申请
            optNewlyIdeaList.add(optNewly_genMilitarycasesFrame(djid));
        } else if ("genOaCarApplyFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaCarApplyFrame(djid));
        } else if ("genOaSuperviseFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaSuperviseFrame(djid));
        } else if ("genOaMeetroomApplyFrame".equals(bizMenth)) {

            optNewlyIdeaList.add(optNewly_genOaMeetroomApplyFrame(djid));
        } else if ("genOaApplyFrame".equals(bizMenth) || "genOaApplyQBFrame".equals(bizMenth)) {// 关联业务信息
            // 查询关联业务表中是否存在当前办件的关联信息
            Boolean flag = this.getOaBizInfolist(djid);
            if (!flag) {
                optNewlyIdeaList.add(optNewly_genOaApplyFrame(djid));
            } else {
                optNewlyIdeaList.add(optNewly_genOaApplyFrame(djid));
                optNewlyIdeaList.add(optNewly_genOaBizBindInfoFrame(djid));
            }

        }
        curUrl = "/powerruntime/generalOperator!listIdeaLogs.do?djId=" + djid;
        if (null == super.getFlowInstId()) {
            super.setFlowInstId((long) 9999999);
        }
        request.setAttribute("flowInstId", super.getFlowInstId());
        return "viewAllList";
    }

    /**
     * 需要显示的环节意见
     * 
     * @param djId
     * @param bizMenth
     * @return
     */
    private OptHtmlFrameInfo getAllShowInfoFrame(String djId, String bizMenth) {
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        procFrameInfo.setFrameId("AllShowInfoFrame");
        procFrameInfo.setFrameSrc("/oa/oATasksExcute!viewAllShowList.do?djId="
                + djId + "&flowInstId=" + super.getFlowInstId() + "&bizMenth="
                + bizMenth);
        procFrameInfo.setFrameHeight("400px");
        return procFrameInfo;
    }

    /**
     * 供通用页面办理查看使用
     */
    @SuppressWarnings("unchecked")
    public Boolean getOaBizInfolist(String djId) {
        List<OaBizBindInfo> sobjectlist = new ArrayList<OaBizBindInfo>();// 主动关联
        List<OaBizBindInfo> eobjectlist = new ArrayList<OaBizBindInfo>();// 被动关联
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if (StringUtils.isNotBlank(djId)) {// s_startDjid非空时，查相关主动关联的事项
            filterMap.put("startDjid", djId);
            PageDesc pageDesc = makePageDesc();
            sobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
            filterMap.remove("startDjid");
            filterMap.put("endDjid", djId);
            eobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
        }
        if ((sobjectlist != null && sobjectlist.size() >= 1)
                || (eobjectlist != null && eobjectlist.size() >= 1)) {
            return true;
        } else {
            return false;
        }
    }

    private OptNewlyIdea optNewly_genOaMeetroomApplyFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 3);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("会议室信息");
        newlyIdea.setUrl("/oa/oaMeetroomApply!view.do?djId=" + djId);
        return newlyIdea;
    }

    /**
     * 封装关联业务信息
     */
    private OptNewlyIdea optNewly_genOaBizBindInfoFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 4);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("关联业务信息");
        //加startDjid以判断在查看关联业务信息页面是否有关闭按钮
        newlyIdea.setUrl("/oa/oaBizBindInfo!listbiz4tab.do?djid=" + djId+"&startDjid="+djId);
        return newlyIdea;
    }

    /**
     * 督办
     * 
     * @param djId
     * @return
     */

    private OptNewlyIdea optNewly_genOaSuperviseFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 3);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("督办信息");
        newlyIdea.setUrl("/oa/oaSupervise!view.do?djId=" + djId);
        return newlyIdea;
    }

    /**
     * 办公室业务信息
     * 
     * @param djId
     * @return
     */
    private OptNewlyIdea optNewly_genMilitarycasesFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 3);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("会议申请信息");
        newlyIdea.setUrl("/oa/oaMeetApply!view.do?djId=" + djId);
        return newlyIdea;
    }

    /**
     * 车辆业务信息
     * 
     * @param djId
     * @return
     */
    private OptNewlyIdea optNewly_genOaCarApplyFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 3);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("车辆申请信息");
        newlyIdea.setUrl("/oa/oaCarApply!view.do?djId=" + djId);
        return newlyIdea;
    }
    
    
    private UserbizoptLogManager userbizoptLogManager;
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }

    /********************************* 办理页面加载 ******************************/

    public String doOpt() {

        try {
            super.setDjId(object.getDjId());
            super.setNodeInstId(curNodeInstId);
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);// "XKSL"

            extractFlowOptParam();
            //System.out.println(isDelete);
            object = optProcInfoManager.getObjectByNodeInstId(object
                    .getNodeInstId());  

            // 判断是 新建 还是更新
            if (object == null) {
                object = new OptProcInfo();
                object.setDjId(super.getDjId());
                object.setNodeInstId(curNodeInstId);
            }
            super.initalOptProcInfo();
         
            /**
             * 根据参数是否需要 关注人 ，提供候选关注人列表
             */
            super.initAttUsersConfig();
            /**
             * 获得办件角色人名单,根据参数是否需要 办件人员
             */
            this.initTeamUsersConfig();

            /**
             * 权限引擎
             */
            super.initEngineUsersConfig();

            /**
             * 根据参数是否有风险点 ，提供风险点的风险内控手段与结果的维护
             */
            super.initRiskConfig();

            /**
             * 根据是否可以上传附件 ，确定可以上传附件的类型
             */
            if (moduleParam != null && moduleParam.getHasStuff() != null
                    && "T".equals(moduleParam.getHasStuff())) {
                OptBaseInfo optBaseInfo = optBaseInfoManager
                        .getObjectById(object.getDjId());
                if (optBaseInfo != null) {
                    moduleParam.setPowerId(optBaseInfo.getPowerid());
                }
            }

            /**
             * 办理过程 如果有文书编辑的，设置文书模板编号
             */
            if (moduleParam != null && moduleParam.getHasDocument() != null
                    && "T".equals(moduleParam.getHasDocument())) {   
                OptProcInfo procInfo = new OptProcInfo();
      
                    List<OptProcInfo> proList = optProcInfoManager
                            .getObjectOfRecordIdById(object.getDjId(),moduleParam.getDocumentType());
                    procInfo = (proList != null && proList.size() > 0) ? proList
                            .get(0) : null;           
                // 初始化文书信息
                if (procInfo != null) {
                    object.setRecordId(procInfo.getRecordId());
                    object.setArchiveType(procInfo.getArchiveType());
                }

            }      
            /**
             * 根据是否可以生产公文 ，确定需要编辑的文档模板
             */
            super.initTemplateConfig();
            /**
             * 多模板情况加载
             */
            super.initTemplateFromNode();
            /**
             * 批分功能配置
             */
            if ("T".equals(moduleParam.getHasOrgRole())) {
                String nodeCode = request.getParameter("nodeCode");
                initPFUnit(moduleParam.getXbOrgRoleCode(), nodeCode);
                initXbOrgConfig();
            }
            /**
             * 
             * 通用运行配置，是否引用上一步骤的办理意见
             */
            if ("T".equals(moduleParam.getHasPreIdea())) {
                OptProcInfo preTransInfo = super.getPreTransInfo();
                if (preTransInfo != null) {
                    object.setIdeacode(preTransInfo.getIdeacode());
                    object.setIshq(preTransInfo.getIshq());
                    if (null != preTransInfo.getRecordId()) {
                        object.setRecordId(preTransInfo.getRecordId());
                    }              
                }
            }
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo != null) {
                request.setAttribute("wfcode", optBaseInfo.getFlowCode());
            }
            
          //保存查看阅读日志   by dk 2016-2-23
            FUserDetail user = (FUserDetail) getLoginUser();
            UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(optBaseInfo.getTransaffairname(), object.getDjId()),curNodeInstId);
            userbizoptLogManager.saveUserbizoptLog(u, user);
            

        } catch (Exception e) {
            log.error(e, e.getCause());
            e.printStackTrace();
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
        return "IODocTransForm";
    }
    /**
     * 获取文书recordId
     * @return
     */
    
    public String existTemplate() {
        String responseText = "";
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("djId", object.getDjId());
            paramMap.put("archiveType", moduleParam.getDocumentType());
            
            stuffInfoLList = optProcInfoManager
                    .listObjects(paramMap);
            if(stuffInfoLList!=null || stuffInfoLList.size()>0){
                responseText =stuffInfoLList.get(0).getRecordId();
            }
        } catch (Exception e) {
            log.info(e);
        } 
        return responseText;
    }
    @Override
    protected void initTeamUsersConfig() {
        /**
         * 获得办件角色人名单
         */
        //modify by HX 2015-11-11 start 注释掉的目的是不需要在页面默认出现上一环节选择的人员
        /*
         * if (moduleParam == null) { moduleParam = new GeneralModuleParam(); }
         * List<WfTeam> userCodes = null; if (this.getFlowInstId() != null &&
         * StringUtils.isNotBlank(moduleParam.getTeamRoleCode())) { userCodes =
         * flowEngine.viewFlowWorkTeamList(this.getFlowInstId(),
         * moduleParam.getTeamRoleCode()); }
         * 
         * String tempArr[] = super.subStrUsers(userCodes);
         * 
         * teamUserCodes = tempArr[0]; bjUserNames = tempArr[1];
         */
        //end 
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

    private void generalUserWithUnitJson(String attName, Set<String> users) {
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

    /*  *//**
     * 文书JSON数据封装
     */
    /*
     * private void initailOptCommonBizJson() { // TODO Auto-generated method
     * stub String djid=object.getDjId(); Object o = null; if
     * (object.getDjId().indexOf("BY") != -1) {
     * o=militarycasesManager.getObjectById(djid);
     * 
     * }else if (object.getDjId().indexOf("XZFY") != -1) {
     * o=reconsiderationManager.getObjectById(djid); }else if
     * (object.getDjId().indexOf("QC") != -1) { Demolitioncase
     * demolitioncase=demolitioncaseManager.getObjectById(djid);
     * demolitioncase.setApplydepid
     * (CodeRepositoryUtil.getValue("unitcode",demolitioncase.getApplydepid()));
     * demolitioncase
     * .setApplydate_1(DatetimeOpt.convertDateToString(demolitioncase
     * .getApplydate(), "yyyy年MM月dd日")); o=demolitioncase; }else if
     * (object.getDjId().indexOf("JD") != -1) {
     * o=lawsupervisionManager.getObjectById(djid); }else if
     * (object.getDjId().indexOf("ZRZJ") != -1) { }
     * 
     * optCommonBizJson super.initCommonBizJSON(o); }
     */

    /*********************************** 保存\提交操作 *********************************************/
    private String actionName;

    /**
     * 提交操作结果
     * 
     * @return
     */
    public String submitOpt() {
        try {

            saveOpt();
         // 保存过程日志信息
            saveIdeaInfo();
            super.saveTeamRolepeople();// 保存角色
            super.saveEngineRolepeople();// 保存权限引擎角色
            Set<Long> nextNode = submitNode();
            //            
            saveSms(nextNode);     
            
            
            if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                //重新生成pdf
                final String userCode = ((FUserDetail) getLoginUser()).getUsercode();
                final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
                String mobileTerminalAccessAddr = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
                if(mobileTerminalAccessAddr.endsWith("/")){
                    mobileTerminalAccessAddr = mobileTerminalAccessAddr.substring(0, mobileTerminalAccessAddr.length()-1);
                }
                mobileTerminalAccessAddr = mobileTerminalAccessAddr + request.getContextPath();
                final String contextPath = mobileTerminalAccessAddr;
                taskExecutor.execute(new Runnable(){
                    @Override
                    public void run() {
                        int bizType = optPdfInfoManager.getBizTypeForPdf(object.getDjId());
                        String formHtmlUrl = "";
                         //签报
                        if(bizType == 3){
                             formHtmlUrl = optPdfInfoManager.getQBFormHtmlUrl(contextPath,userCode, object.getDjId());
                        }
                        //车辆
                        if(bizType == 4){
                             formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(contextPath, userCode, object.getDjId());
                        }
                        createPDF(object.getDjId(), object.getNodeInstId(),userCode,exePath,formHtmlUrl);
                    }
                });
            }
        } catch (Exception e) {
            log.error(e, e.getCause());
            e.printStackTrace();
            request.setAttribute("error", "当前操作提交失败，详见系统日志。");
            return ERROR;
        }
        this.initReturn();

        return "refreshTasks";
    }
    
    private void createPDF(String djId,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        NodeInstance nodeInst = flowEngine.getNodeInstById(nodeInstId);
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        OptPdfInfo optPdfInfo = null;
        try {
            String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(djId, nodeInstId);
            File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(djId,nodeInstId,formHtmlUrl);
            //重新添加图层
           optPdfInfo = optPdfInfoManager.addLayerOnPDF(optPdfInfo);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
           //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(djId)));
           optPdfInfo.setNodeName(nodeInfo.getNodeName());
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
    }
    /****************************** RTX相关内容 ************************************/
    private WfActionTaskDao actionTaskDao;
    private RtxInfoManager rtxInfoManager;

    /**
     * 
     * @param nextNode
     */
    public void saveSms(Set<Long> nextNode) {
        String expireNoticeType = CodeRepositoryUtil.getValue("WFNotice",
                "type");
        if (nextNode != null && !nextNode.isEmpty()) {
            for (Long nodeActive : nextNode) {
                List<VUserTaskList> tasks = actionTaskDao
                        .getTasksByNodeInstId(nodeActive);
                saveNewTaskNotice(expireNoticeType, tasks);
            }
        }

    }

    private void saveNewTaskNotice(String noticeType, List<VUserTaskList> tasks) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
 
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        url += "/page/rtx/signauth.jsp?url=dispatchdoc/vuserTaskListOA!list.do";

        for (VUserTaskList task : tasks) {
            FUserinfo userInfo = sysUserManager.getObjectById(task
                    .getUserCode());
            String loginName = userInfo.getLoginname();

            RtxInfo rtxInfo = new RtxInfo();
            rtxInfo.setNo(rtxInfoManager.getNextkey());
            rtxInfo.setDjId(object.getDjId());
            rtxInfo.setNodeId(task.getNodeInstId());
            rtxInfo.setCreateDate(new Date(System.currentTimeMillis()));
            rtxInfo.setCreateUserCode(loginInfo.getUsercode());
            rtxInfo.setCreateUserName(loginInfo.getUsername());
            rtxInfo.setInfoContent("[" + optBaseInfo.getTransaffairname() + "|"
                    + url + "]");
            rtxInfo.setReceiveUserCode(task.getUserCode());
            rtxInfo.setReceiveUserName(loginName);
            rtxInfo.setIsSend("0");
            // sender.sendMessage("9999999"/*系统管理员*/,
            // loginName, "您有一个新待办：",
            // "["+optBaseInfo.getTransaffairname()+"|"+url+"]");
            rtxInfoManager.saveObject(rtxInfo);
        }
    }

    /**
     * 规整返回待办界面，根据不同的业务要求
     */
    private String flowCode;
    private String itemtype;

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    /**
     * 根据DJID返回列表参数flowCode，itemtype
     */
    private void initReturn() {

        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());

        if (object.getDjId().indexOf("HYS") != -1) {
            flowCode = optBaseInfo.getFlowCode();
        } else if (object.getDjId().indexOf("CARSQ") != -1) {
            flowCode = optBaseInfo.getFlowCode();
        }
        if (object.getDjId().indexOf("QB") != -1) {
            // 和菜单查询条件保持一致
            itemtype = "QB";
            // flowCode = optBaseInfo.getFlowCode();
        } else if (object.getDjId().indexOf("DCDB") != -1) {
            flowCode = optBaseInfo.getFlowCode();
        }
        // HYSQ CARSQ与SQ区别 如果要检索的字符串值没有出现，则该方法返回 -1。
        else if (object.getDjId().indexOf("SQ") != -1
                && object.getDjId().indexOf("CARSQ") == -1
                && object.getDjId().indexOf("HYSQ") == -1) {
            itemtype = "SQ";
            // flowCode = optBaseInfo.getFlowCode();
        }

    }

    /**
     * 保存操作结果
     * 
     * @return
     */
    public String saveOpt() {
        try {
            super.setNodeInstId(object.getNodeInstId());
            FUserDetail loginInfo = (FUserDetail) getLoginUser();

            // 添加保存操作
            object.setTransdate(new Date(System.currentTimeMillis()));
            object.setUsercode(loginInfo.getUsercode());
            object.setUnitcode(loginInfo.getPrimaryUnit());

            NodeInstance nodeInst = flowEngine.getNodeInstById(object
                    .getNodeInstId());
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            object.setNodename(nodeInfo.getNodeName());
            object.setNodeinststate(nodeInst.getNodeState());

            // 存在风险点的信息:风险类别、风险描述、风险内控手段与结果
            if (StringUtils.isNotBlank(object.getRisktype())) {
                object.setIsrisk("T");// 存在
            } else {
                object.setIsrisk("F");// 不存在
            }
            optProcInfoManager.saveObject(object);// 保存办件人员
         // 保存主办单位
            if (StringUtils.isNotBlank(zbOrgRoleCode)
                    && StringUtils.isNotBlank(zbOrgCode)) {
                flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                        zbOrgRoleCode, loginInfo.getUsercode());
                flowEngine.assignFlowOrganize(super.getFlowInstId(),
                        zbOrgRoleCode, zbOrgCode, loginInfo.getUsercode());
                Set<String> sValues = new HashSet<String>();
                sValues.add(zbOrgCode);
                flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                        zbOrgRoleCode, sValues);
            }

            // 保存协办单位
            if (StringUtils.isNotBlank(xbOrgCodes)) {
                flowEngine.deleteFlowOrganizeByAuth(super.getFlowInstId(),
                        xbOrgRoleCode, loginInfo.getUsercode());
                String[] orgCodes = xbOrgCodes.split("[,]");
                if (orgCodes != null && orgCodes.length > 0) {
                    flowEngine.assignFlowOrganize(super.getFlowInstId(),
                            xbOrgRoleCode,
                            new HashSet<String>(Arrays.asList(orgCodes)),
                            loginInfo.getUsercode());
                    flowEngine.saveFlowNodeVariable(super.getNodeInstId(),
                            xbOrgRoleCode,
                            new HashSet<String>(Arrays.asList(orgCodes)));
                }
            }
            // 保存关注人员
            this.saveAtt();
            
            // 保持流程和业务的信息
            this.saveBizSubmitInfo();
            this.initReturn();

            return "refreshTasks";
        } catch (Exception e) {
            log.error(e, e.getCause());
            e.printStackTrace();
            request.setAttribute("error", "保存流程过程信息失败。");
            return ERROR;
        }
    }

    /**
     * 根据环节代码提交业务信息
     * 
     * @param object
     */
    private void saveBizSubmitInfo() {
        String nodeCode = object.getNodeCode();
        // 会议流程节点
        if ("hys_sp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("T")) {// 审批节点跟新isuse字段同意2 不同意4
            OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(object
                    .getDjId());// 业务数据
            oaMeetApply.setState("2");// 跟新是否在用状态
            oaMeetApply.setSolvestatus("T");// 同意
            oaMeetApplyManager.saveObject(oaMeetApply);
        } else if ("hys_sp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("F")) {// 审批节点跟新isuse字段同意2 不同意4
            OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(object
                    .getDjId());// 业务数据
            oaMeetApply.setState("4");// 跟新是否在用状态
            oaMeetApply.setSolvestatus("F");// 不同意
            oaMeetApplyManager.saveObject(oaMeetApply);
        }
        // 车辆使用管理节点特殊处理
        if ("car_sp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("T")) {// 审批节点跟新isuse字段同意2 不同意4
            OaCarApply oaCarApply = oaCarApplyManager.getObjectById(object
                    .getDjId());// 业务数据
            oaCarApply.setState("2");// 跟新是否在用状态
            oaCarApply.setSolvestatus("T");// 同意
            oaCarApplyManager.saveObject(oaCarApply);
        } else if ("car_sp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("F")) {// 审批节点跟新isuse字段同意2 不同意4
            OaCarApply oaCarApply = oaCarApplyManager.getObjectById(object
                    .getDjId());// 业务数据
            oaCarApply.setState("4");// 跟新是否在用状态
            oaCarApply.setSolvestatus("F");// 不同意
            oaCarApplyManager.saveObject(oaCarApply);
        }
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        // 督办催办
        if ("dbhf".equalsIgnoreCase(nodeCode)) {// 督办回复

            OaSupervise oaSupervise = oaSuperviseManager.getObjectById(object
                    .getDjId());// 业务数据
            oaSupervise.setState("1");// 督办反馈
            oaSupervise.setReplayDate(new Date(System.currentTimeMillis()));
            oaSupervise.setReplayDepno(loginInfo.getPrimaryUnit());
            oaSupervise.setReplayUser(loginInfo.getUsercode());
            oaSupervise.setReplayRemark(object.getTranscontent());
            oaSuperviseManager.saveObject(oaSupervise);
        } else if ("dbjl".equalsIgnoreCase(nodeCode)) {// 督办结论

            OaSupervise oaSupervise = oaSuperviseManager.getObjectById(object
                    .getDjId());// 业务数据
            oaSupervise.setState("2");// 督办反馈
            oaSupervise.setFeedbackDate(new Date(System.currentTimeMillis()));
            oaSupervise.setFeedbacker(loginInfo.getUsercode());
            oaSupervise.setFeedbackRemark(object.getTranscontent());
            oaSupervise.setLastmodifytime(new Date(System.currentTimeMillis()));
            oaSupervise.setBizstate("C");
            oaSupervise.setSolvenote(object.getTranscontent()); // 办结意见
            oaSupervise.setSolvetime(new Date(System.currentTimeMillis()));// 办结时间
            oaSuperviseManager.saveObject(oaSupervise);
        }
        // 会议室
        if ("hys_hyssp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("T")) {// 审批节点跟新isuse字段同意2 不同意4
            OaMeetroomApply oaMeetApply = oaMeetroomApplyManager
                    .getObjectById(object.getDjId());// 业务数据
            oaMeetApply.setState("2");// 跟新是否在用状态
            oaMeetApply.setSolvestatus("T");// 同意
            oaMeetroomApplyManager.saveObject(oaMeetApply);
        } else if ("hys_hyssp".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("F")) {// 审批节点跟新isuse字段同意2 不同意4
            OaMeetroomApply oaMeetApply = oaMeetroomApplyManager
                    .getObjectById(object.getDjId());// 业务数据
            oaMeetApply.setState("4");// 跟新是否在用状态
            oaMeetApply.setSolvestatus("F");// 不同意
            oaMeetroomApplyManager.saveObject(oaMeetApply);
        }
    }

    /**
     * 办件查看
     * 
     * @return
     */
    public String transaffairView() {
        tabFrameList = new ArrayList<OptHtmlFrameInfo>();

        if (StringUtils.isBlank(object.getDjId())) {
            String flowid = request.getParameter("flowInstId");
            OptBaseInfo baseinfo = optBaseInfoManager.getOptBaseByFlowId(Long
                    .parseLong(flowid));
            if (baseinfo != null) {
                object.setDjId(baseinfo.getDjId());
            }
        }
        tabFrameList.add(super.getProcFrame(object.getDjId())); // 过程信息
        tabFrameList.add(GeneralOperatorAction.getStuffListFrame(object
                .getDjId())); // 材料信息

        tabShowFrameId = tabFrameList.get(0).getFrameId();

        if (null != super.curNodeInstId
                && StringUtils.isNotBlank(String.valueOf(super.curNodeInstId))) {
            super.initFlowTime();
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        } else {
            affairTitle = super.getOptBaseInfo().getTransaffairname();
        }

        // super.initFlowTime();
        return "transaffairView";
    }

    /**
     * 公文_督查件_办公室分管审核
     * 
     * @return
     */
    public String officeInChargeFrame() {
        this.doOpt();
        List<FUserinfo> zrList = CodeRepositoryUtil
                .getSortedPrimaryUnitUsers("001531");// 委领导
        String ryTOJSON = this.getRyToJson(zrList);
        request.setAttribute("fgldJson", ryTOJSON);// 分管领导
        request.setAttribute("wldJson", ryTOJSON);// 委领导

        // 带入已经保存的人员信息
        String[] fgld_team = this.viewFlowWorkTeam("fgwldqf");
        fgld_teamUserCodes = fgld_team[0];
        fgld_bjUserNames = fgld_team[1];
        /*
         * 委领导
         */
        String[] wld_team = this.viewFlowWorkTeam("wldqf");
        wld_teamUserCodes = wld_team[0];
        wld_bjUserNames = wld_team[1];

        return "officeInChargeFrame";
    }

    /**
     * 
     * @param string
     *            []
     */
    private String[] viewFlowWorkTeam(String roleCode) {
        // TODO Auto-generated method stub
        String[] re = new String[] { "", "" };
        String returncodes = "";
        String returnnames = "";
        Set<String> userCodes = flowEngine.viewFlowWorkTeam(
                this.getFlowInstId(), roleCode);
        if (userCodes != null && userCodes.size() > 0) {
            for (String userCode : userCodes) {
                if ((returncodes.endsWith(""))) {
                    returncodes = returncodes + ",";
                }
                returncodes = returncodes + userCode;
                if ((returnnames.endsWith(""))) {
                    returnnames = returnnames + ",";
                }
                returnnames = returnnames
                        + CodeRepositoryUtil.getValue("usercode", userCode);
            }
            returncodes = returncodes.substring(1, returncodes.length());
            returnnames = returnnames.substring(1, returnnames.length());
        }
        re[0] = returncodes;
        re[1] = returnnames;
        return re;
    }

    /**
     * 将人员列表组装成JSon
     * 
     * @param zrList
     * @return
     */
    private String getRyToJson(List<FUserinfo> zrList) {
        // TODO Auto-generated method stub
        String ryJson = "";
        if (zrList != null && zrList.size() > 0) {
            int p = 0;
            StringBuilder zrJson = new StringBuilder("[");
            for (FUserinfo u : zrList) {
                if (p > 0)
                    zrJson.append(",");
                p++;
                zrJson.append("{\"name\":\"").append(u.getUsername())
                        .append("\",").append("\"nodeID\":\"")
                        .append(u.getUsercode()).append("\",")
                        .append("\"belongId\":0,\"levle\":0}");

            }
            zrJson.append("]");
            ryJson = zrJson.toString();
        }
        return ryJson;
    }

    /*
     * 分管领导
     */
    private String fgld_teamUserCodes;
    private String fgld_bjUserNames;
    /*
     * 委领导
     */
    private String wld_teamUserCodes;
    private String wld_bjUserNames;

    /**
     * 提交办公室分管审核
     * 
     * @return
     * @throws IOException
     */
    public String submitOpt_offic() throws IOException {
        // 保存分管领导以及委领导
        super.saveTeamRolepeople(fgld_teamUserCodes, "fgwldqf");
        super.saveTeamRolepeople(wld_teamUserCodes, "wldqf");
        return this.submitOpt();
    }

    /**
     * 引用启动子流程业务的办理界面
     * 
     * @param djid
     * @return
     */
    protected OptHtmlFrameInfo genSubProjectFrame(String djId, String nodeCode) {
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo
                .setFrameSrc("/dispatchdoc/ioDocTasksExcute!doSubOpt.do?djId="
                        + djId + "&flowInstId=" + super.getFlowInstId()
                        + "&nodeInstId=" + super.getNodeInstId()
                        + "&moduleCode=" + moduleCode + "&documentTemplateIds="
                        + documentTemplateIds + "&nodeCode=" + nodeCode
                        + "&flowPhase=" + object.getFlowPhase());
        return transFrameInfo;
    }

    public String doOpt_HandleApproval() {
        this.doOpt();
        return "HandleApproval";
    }

    /**
     * 查看项目信息
     * 
     * @param djid
     * @return
     */
    @SuppressWarnings("unused")
    private OptHtmlFrameInfo viewProjectInfoFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("projectInfoFrame");
        viewFrameInfo
                .setFrameSrc("/dispatchdoc/incomeProject!view.do?retVal=projectInfoView&djId="
                        + djid);
        viewFrameInfo.setFrameLegend("项目信息");

        return viewFrameInfo;
    }

    /**
     * 查看项目信息
     * 
     * @param djid
     * @return
     */
    @SuppressWarnings("unused")
    private OptHtmlFrameInfo viewProjectInfoApprovalFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("projectInfoApprovaViewlFrame");
        viewFrameInfo
                .setFrameSrc("/dispatchdoc/incomeProject!view.do?retVal=approvalInfoView&djId="
                        + djid);

        return viewFrameInfo;
    }

    /**
     * 查看督办信息
     * 
     * @param djid
     * @return
     */
    @SuppressWarnings("unused")
    private OptHtmlFrameInfo viewSuperviseListFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("superviseListFrame");
        viewFrameInfo.setFrameSrc("/oa/oaSupervise!superviselist.do?s_supDjid="
                + djid);
        viewFrameInfo.setFrameLegend("项目信息");

        return viewFrameInfo;
    }

    /**
     * 保存过程日志信息
     */
    public void saveIdeaInfo() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        /** 获得用户所在部门 start */
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(loginInfo.getPrimaryUnit());
        fuerunit.setUserCode(loginInfo.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        /** 获得用户所在部门 end */
        String unitname = "";
        if (fuerunit != null) {
            unitname = fuerunit.getUnitName();
        } else {
            unitname = CodeRepositoryUtil.getUnitInfoByCode(
                    loginInfo.getPrimaryUnit()).getUnitname();
        }
        optIdeaInfo.setUnitname(unitname);

        /** 获取过程日志信息：环节名称、办理意见、环节状态 start */
        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        object.setNodename(nodeInfo.getNodeName());
        object.setNodeinststate(nodeInst.getNodeState());
        optIdeaInfo.setTransidea(StringUtils.isBlank(object.getTransidea())?"提交":object.getTransidea());
        optIdeaInfo.setModuleCode(moduleCode);// 办理意见与通用模块关联（控制环节意见显示）
        /** 获取过程日志信息：环节名称、办理意见、环节状态 end */

        optProcInfoManager.saveIdeaInfo(optIdeaInfo, object);
        // OptBaseInfo baseinfo = super.getOptBaseInfo();
        // checkRel(unitname, loginInfo, baseinfo.getBizstate());
    }

    /**
     * 保存过程日志信息
     */
    public void saveIdeaInfo(String transidea) {
        object.setTransidea(transidea);
        saveIdeaInfo();
    }

    private List<OptProcInfo> optProcInfos;
    private WorkCalendar workCalendar;

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public IncomeDocManager getIncomeDocManager() {
        return incomeDocManager;
    }

    public GeneralModuleParamManager getGeneralModuleParamManager() {
        return generalModuleParamManager;
    }

    public String stopOtherBranchNodes() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        flowEngine.disableOtherBranchNodes(object.getNodeInstId(),
                loginInfo.getUsercode());
        return null;// this.toDoSignTheSummary();
    }

    public List<OptProcInfo> getOptProcInfos() {
        return optProcInfos;
    }

    public void setOptProcInfos(List<OptProcInfo> optProcInfos) {
        this.optProcInfos = optProcInfos;
    }

    public String selectOrganize() {
        try {

            Set<String> orgCodeSet = flowEngine.viewFlowOrganize(
                    super.curFlowInstId, roleCode);

            List<FUnitinfo> unitList = new ArrayList<FUnitinfo>();

            if (!orgCodeSet.isEmpty()) {
                List<String> orgCodeList = new ArrayList<String>();
                orgCodeList.addAll(orgCodeSet);

                for (int i = 0; i < orgCodeList.size(); i++) {
                    unitList.add(sysUnitManager.getObjectById(orgCodeList
                            .get(i)));
                }
            }

            String unitString = request.getParameter("unitString");
            if (StringUtils.isNotBlank(unitString)) {
                List<String> hbUnitCodeList = Arrays.asList(unitString
                        .split(","));

                for (int i = 0; i < unitList.size(); i++) {
                    unitList.get(i).setIsvalid("");
                    for (int j = 0; j < hbUnitCodeList.size(); j++) {
                        if (unitList.get(i).getUnitcode()
                                .equals(hbUnitCodeList.get(j))) {
                            unitList.get(i).setIsvalid("T");
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < unitList.size(); i++) {
                    unitList.get(i).setIsvalid("");
                }
            }

            request.setAttribute("selUnits", unitList);
            request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());

            return "selectOrganize";
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }

    public String assignWorkGroup() {
        String[] userCodes = request.getParameter("userCode").split(",");
        if (userCodes != null && userCodes.length > 0) {
            flowEngine.assignFlowWorkTeam(super.getFlowInstId(), roleCode,
                    new HashSet<String>(Arrays.asList(userCodes)));
        }
        return "ListWorkGroup";
    }

    public String deleteWorkGroup() {
        flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode);
        return "ListWorkGroup";
    }

    public String deleteWorkGroupUser() {
        flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), roleCode,
                request.getParameter("userCode"));
        return "ListWorkGroup";
    }

    public String assignFlowOrganize() {
        String[] orgCodes = request.getParameter("orgCode").split(",");
        if (orgCodes != null && orgCodes.length > 0) {
            flowEngine.assignFlowOrganize(super.getFlowInstId(), roleCode,
                    new HashSet<String>(Arrays.asList(orgCodes)));
        }
        return "ListWorkGroup";
    }

    public String assignFlowOrganizeAjax() {
        JSONObject json = new JSONObject();
        try {
            String[] orgCodes = request.getParameter("orgCode").split(",");
            if (orgCodes != null && orgCodes.length > 0) {
                flowEngine.assignFlowOrganize(super.getFlowInstId(), roleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)));
            }
            json.put("status", "success");
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }

    public String deleteFlowOrganize() {
        log.info("nodeInstId:" + object.getNodeInstId());
        flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
        return "ListWorkGroup";
    }

    public String deleteFlowOrganizeAjax() {
        JSONObject json = new JSONObject();
        try {
            flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
            if ("zbcsfb".equals(roleCode)) {
                OptBaseInfo optBaseInfo = super.getOptBaseInfo();
                optBaseInfo.setHeadunitcode(null);

                optBaseInfoManager.saveObject(optBaseInfo);
            }
            json.put("status", "success");
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }

    public String replaceFlowOrganizeAjax() {
        JSONObject json = new JSONObject();
        try {
            flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
            String[] orgCodes = request.getParameter("orgCode").split(",");
            if (orgCodes != null && orgCodes.length > 0) {
                flowEngine.assignFlowOrganize(super.getFlowInstId(), roleCode,
                        new HashSet<String>(Arrays.asList(orgCodes)));
            }
            if ("zbcsfb".equals(roleCode) && orgCodes.length == 1
                    && StringUtils.isNotBlank(orgCodes[0])) {
                OptBaseInfo optBaseInfo = super.getOptBaseInfo();
                optBaseInfo.setHeadunitcode(orgCodes[0]);

                optBaseInfoManager.saveObject(optBaseInfo);
            }
            json.put("status", "success");
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }

    public String deleteFlowOrganizeUnitAjax() {
        JSONObject json = new JSONObject();
        try {
            flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode,
                    request.getParameter("orgCode"));
            json.put("status", "success");
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }

    public String deleteFlowOrganizeUnit() {
        flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode,
                request.getParameter("orgCode"));
        return "ListWorkGroup";
    }

    public String checkArchiveTypesByDjId() {
        String result = "";
        try {
            List<OptStuffInfo> optProcInfoList = optProcInfoManager
                    .listStuffsByArchiveType(object.getDjId(),
                            object.getArchiveType());

            result = optProcInfoList.isEmpty() ? "none" : "exist";
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }

        return null;
    }

    /**
     * 添加设置关注
     * 
     * @return
     * 
     */
    public String saveAtt() {
        try {
            if (!StringUtils.isBlank(attUserCodes)) {
                FUserDetail userDetail = ((FUserDetail) getLoginUser());

                // 获取页面attUserCodes
                String[] attUserCode = attUserCodes.split(",");
                // 拆分userCode
                if (attUserCode.length > 0) {
                    for (int i = 0; i < attUserCode.length; i++) {
                        optProcAttentionManager
                                .deleteObjectById(new OptProcAttentionId(object
                                        .getDjId(), attUserCode[i]));
                        OptProcAttention optProcAttention = new OptProcAttention();
                        optProcAttention.setUserCode(attUserCode[i]);
                        optProcAttention.setDjId(object.getDjId());
                        optProcAttention
                                .setAttSetUser(userDetail.getUsercode());
                        optProcAttention.setAttType(object
                                .getOptProcAttention().getAttType());
                        optProcAttention.setAttSetTime(new Date(System
                                .currentTimeMillis()));
                        optProcAttention.setNodeInstId(object.getNodeInstId());
                        optProcAttention.setIsAtt("0");// 0，未提出（关注意见状态），1 已经提出
                        optProcInfoManager.saveAtt(optProcAttention);// 保存关注
                    }
                }
            }
            savedMessage();
            return LIST;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            saveError(e.getMessage());
            return ERROR;
        }
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
        System.out.println(userjson.toString());
        request.setAttribute("userjson", userjson);
    }

    /**
     * 暂缓处理
     * 
     * @return
     */
    public String suspendNodeInstance() {
        flowManager.suspendNodeInstance(object.getNodeInstId(),
                ((FUserDetail) getLoginUser()).getUsercode());
        // return "toDispatchDocTask";
        saveIdeaInfo("暂缓");
        return "refreshTasks";
    }

    /**
     * 回退节点
     * 
     * @return
     */
    public String rollBackOpt() {
        flowManager.rollbackOpt(object.getNodeInstId(),
                ((FUserDetail) getLoginUser()).getUsercode());
        saveIdeaInfo("回退");
        // 跳转各自代办
        this.initReturn();
        return "refreshTasks";
    }

    /**
     * 结束流程
     * 
     * @return
     */
    public String endFlow() {
        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        flowManager.stopInstance(nodeInst.getFlowInstId(),
                ((FUserDetail) getLoginUser()).getUsercode(), "强制结束流程");
        return "toDispatchDocTask";
    }

    /**
     * 默认办理方法，某个步骤没有开发完成，方便进入下一个节点。防止步骤分工开发后的阻塞
     * 
     * @return
     */
    public String defaultTrans() {
        return "defaultTrans";
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
            // int strSize = (int) str.length();
            byte[] b = str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 获取监审业务部门回复汇聚节点列表
     * 
     * @param msg
     * @param response
     */
    public String getDepreply() {

        try {

            // 监审业务部门回复汇聚
            optProcInfos = optProcInfoManager.getObjectsByNodeCode(
                    object.getDjId(), "HMFZ_JSBMHF");

        } catch (Exception e) {
            log.error(e, e.getCause());
            e.printStackTrace();
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
        return "depreply";

    }

    /**
     * 督查督办信息
     * 
     * @param nodecode
     * 
     * @param msg
     * @param response
     */

    private List<OaSupervise> dcdblist;
    private String dbdjid;

    public String getDbdjid() {
        return dbdjid;
    }

    public void setDbdjid(String dbdjid) {
        this.dbdjid = dbdjid;
    }

    public List<OaSupervise> getDcdblist() {
        return dcdblist;
    }

    public void setDcdblist(List<OaSupervise> dcdblist) {
        this.dcdblist = dcdblist;
    }

    private void dcdbInfo(String nodeCode) {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("supDjid", optBaseInfo.getDjId());
        filterMap.put("nodeCode", nodeCode);
        filterMap.put("state", "0");// 该环节督办已发起
        dcdblist = oaSuperviseManager.listObjects(filterMap);
        if (dcdblist!=null&&dcdblist.size() > 0) {
            dbdjid = dcdblist.get(0).getSupDjid();
        }
    }

    /*************************************** 以下为getter()、setter() ****************************************/
    @Override
    public void setServletResponse(HttpServletResponse response) {
        // TODO Auto-generated method stub
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }

    public String getDocRelativeFrameType() {
        return docRelativeFrameType;
    }

    public void setDocRelativeFrameType(String docRelativeFrameType) {
        this.docRelativeFrameType = docRelativeFrameType;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }

    public String getFgld_teamUserCodes() {
        return fgld_teamUserCodes;
    }

    public void setFgld_teamUserCodes(String fgld_teamUserCodes) {
        this.fgld_teamUserCodes = fgld_teamUserCodes;
    }

    public String getWld_teamUserCodes() {
        return wld_teamUserCodes;
    }

    public void setWld_teamUserCodes(String wld_teamUserCodes) {
        this.wld_teamUserCodes = wld_teamUserCodes;
    }

    public String getFgld_bjUserNames() {
        return fgld_bjUserNames;
    }

    public void setFgld_bjUserNames(String fgld_bjUserNames) {
        this.fgld_bjUserNames = fgld_bjUserNames;
    }

    public String getWld_bjUserNames() {
        return wld_bjUserNames;
    }

    public void setWld_bjUserNames(String wld_bjUserNames) {
        this.wld_bjUserNames = wld_bjUserNames;
    }

    public FunctionManager getFunctionManager() {
        return functionManager;
    }

    public List<OptNewlyIdea> getOptNewlyIdeaList() {
        return optNewlyIdeaList;
    }

    public void setOptNewlyIdeaList(List<OptNewlyIdea> optNewlyIdeaList) {
        this.optNewlyIdeaList = optNewlyIdeaList;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    public List<OptHtmlFrameInfo> getTabFrameList() {
        return tabFrameList;
    }

    public void setTabFrameList(List<OptHtmlFrameInfo> tabFrameList) {
        this.tabFrameList = tabFrameList;
    }

    public String getTabShowFrameId() {
        return tabShowFrameId;
    }

    public void setTabShowFrameId(String tabShowFrameId) {
        this.tabShowFrameId = tabShowFrameId;
    }

    public DocRelativeManager getDocRelativeManager() {
        return docRelativeManager;
    }

    public void setDocRelativeManager(DocRelativeManager docRelativeManager) {
        this.docRelativeManager = docRelativeManager;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public static void main(String[] args) {

    }

    public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
        this.oaMeetApplyManager = oaMeetApplyManager;
    }

    public OaCarApplyManager getOaCarApplyManager() {
        return oaCarApplyManager;
    }

    public void setOaCarApplyManager(OaCarApplyManager oaCarApplyManager) {
        this.oaCarApplyManager = oaCarApplyManager;
    }

    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public void setOaMeetroomApplyManager(
            OaMeetroomApplyManager oaMeetroomApplyManager) {
        this.oaMeetroomApplyManager = oaMeetroomApplyManager;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public void setOaBizBindInfoManager(
            OaBizBindInfoManager oaBizBindInfoManager) {
        this.oaBizBindInfoManager = oaBizBindInfoManager;
    }

    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }

    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }

    public List<OptProcInfo> getStuffInfoLList() {
        return stuffInfoLList;
    }

    public void setStuffInfoLList(List<OptProcInfo> stuffInfoLList) {
        this.stuffInfoLList = stuffInfoLList;
    }

    public OptPdfInfoManager getOptPdfInfoManager() {
        return optPdfInfoManager;
    }

    public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
        this.optPdfInfoManager = optPdfInfoManager;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}
