package com.centit.dispatchdoc.action;

import java.io.File;
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

import com.centit.dispatchdoc.po.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;
import org.springframework.core.task.TaskExecutor;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.dispatchdoc.service.SignedReportManager;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.po.OptProcCollectionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.SampleFlowUtils;
import com.centit.workflow.sample.dao.WfActionLogDao;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.dao.WfFlowInstanceDao;
import com.centit.workflow.sample.dao.WfManageActionDao;
import com.centit.workflow.sample.dao.WfNodeDao;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfActionLog;
import com.centit.workflow.sample.po.WfActionTask;
import com.centit.workflow.sample.po.WfFlowInstance;
import com.centit.workflow.sample.po.WfManageAction;
import com.centit.workflow.sample.po.WfNode;
import com.centit.workflow.sample.po.WfNodeInstance;

/**
 * 收发文流程待办事项公用流转ACTION
 * 
 * @author hx
 * @create 2013-9-23
 * @version
 */
public class IODocTasksExcuteAction extends IODocCommonBizAction<OptProcInfo>
        implements ServletResponseAware {

    private static final long serialVersionUID = 1L;
    HttpServletResponse response;

    private FunctionManager functionManager;
    private DispatchDocManager dispatchDocManager;
    private IncomeDocManager incomeDocManager;
    private OptStuffInfoManager optStuffInfoManager;
    private OaBizBindInfoManager oaBizBindInfoManager;
    private OptProcCollectionManager optProcCollectionManager;
    private OptApplyManager optApplyManager;
    private OaSmssendManager oaSmssendManager;
    private WfNodeInstanceDao nodeInstanceDao;
    private WfFlowInstanceDao flowInstanceDao;
    // 发文frame类型（编辑/查看）
    private String frameType;
    // 收发文关联frame类型（编辑/查看）
    private String docRelativeFrameType;
    private String roleCode;
    private String unitsJson;// 部门JSON


    // Tab显示详细信息
    private List<OptNewlyIdea> optNewlyIdeaList = new ArrayList<OptNewlyIdea>();
    private String curUrl;
    private SignedReportManager signedReportManager;
    private OaSuperviseManager oaSuperviseManager;
    
   
    /****************************** RTX相关内容 ************************************/
    private WfActionTaskDao actionTaskDao;
    private RtxInfoManager rtxInfoManager;
    /******************************** 各节点 操作定义整合 ********************************/
    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    private List<NodeInstance> userCompNodesList;
    private WfNodeDao flowNodeDao;
    
    private OptIdeaInfoDao optIdeaInfoDao;
    private WfActionLogDao actionLogDao;
    private WfManageActionDao manageActionDao;
    private VuserTaskListOAManager vuserTaskListOAManager;
  

    /**
     * 收文通用操作
     * 
     * @return
     */
    public String genIncomeDocFrame() {

        return boundAllInfoframeModuleTwo( null);//null 通用办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    /**
     * 启动子流程运转
     * 
     * @return
     */
    public String genProjectSubFrame() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        this.setNodeInstId(object.getNodeInstId());

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        frameList
                .add(genSubProjectFrame(object.getDjId(), object.getNodeCode()));// 引用启动子流程业务的办理界面

        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
                "genProjectSubFrame");
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
      //公文收藏
        this.getCollsatus();
        return "optframe";
    }

    /**
     * 节点操作通用配置
     * 
     * @return
     */
    public String genDispatchDocFrame() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        List<VuserTaskListOA> vuserTaskList=new ArrayList<VuserTaskListOA>();
        if("XW-FW-0001".equals(optBaseInfo.getPowerid())&&"xwfw_bmsw".equals(object.getNodeCode())) {
            Map<String, Object> paramTemp = new HashMap<String, Object>();
            paramTemp.put("nodeInstId", object.getNodeInstId());
            paramTemp.put("userCode", ((FUserDetail) getLoginUser()).getUsercode());
            vuserTaskList=vuserTaskListOAManager.listObjects(paramTemp);
          
        }
        if(vuserTaskList.size()==0) {
            request.setAttribute("flag", 0);//显示过程信息
            frameList= getBaseInfoIframe( frameList,optBaseInfo.getDjId());//基本信息（业务信息+办理信息）
            frameList.add(super.getPreviousNode(optBaseInfo.getDjId()));// 前一节点办理信息
            frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 附件

            frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面
        }else {
            request.setAttribute("flag", 1);//显示过程信息
            frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 附件
            frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面
        }
      
       
        
        
        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
                "genDispatchDocFrame");

        super.initFlowTime();
        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        // 公文收藏
            //返回待办
        request.setAttribute("dashboard", request.getParameter("dashboard"));
        this.getCollsatus();
        if(object.getNodeCode().equals("jcfw_gz") || object.getNodeCode().equals("xwfw_gz") || object.getNodeCode().equals("xwbmfw_gz") || object.getNodeCode().equals("xwfw_bmsw") || object.getNodeCode().equals("jcfw_bmsw")){//盖章环节查询正文PDF方法
        //查询对应发文对应下的正文pdf路径用于盖章
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("djId", optBaseInfo.getDjId());
        paramMap.put("archivetype", "zw-pdf");
        List<OptStuffInfo> info = optStuffInfoManager.listObjects(paramMap);
        
        String loadPath="C:"+"\\"+"GZpdf";
        File up = new File( loadPath );//自动在C盘里创建GZpdf文件夹
        if ( !up.exists() )
        {
            up.mkdir();
        }
        
        if(info != null && info.size()>0){
            String stuff = info.get(0).getStuffpath();
            //获取磁盘里对应目录下文件名
            String paths[]=stuff.split("\\\\");
            //保存本地的文件名与磁盘上文件名保持一直
            String wj= paths[paths.length-1];//文件名
            String wenjianjia = paths[paths.length-2];//文件夹
            String type = paths[paths.length-3];//类型
            String uploadfilepath = SysParametersUtils.getWorkflowAffixHome() + File.separator + type + File.separator + wenjianjia;//服务器正文pdf路径
            System.out.println(uploadfilepath);
            String stuffpdf = "C:"+ File.separator + "GZpdf" + File.separator + wj;//本地盖完章PDF路径
            String stuffid = info.get(0).getStuffid();
            request.setAttribute("pdfFile", stuffpdf);
            request.setAttribute("stuffid", stuffid);
            request.setAttribute("wj", wj);
            request.setAttribute("uploadfilepath", uploadfilepath);
            }
        }
        return "optframe";
    }
    
    /**
     * 流程回撤
     * 
     * @return
     */
    
    public String listUserCompTask(){
        String userCode = ((FUserDetail) getLoginUser()).getUsercode();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        String flowOptName =null;
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        userCompNodesList = listUserCompleteTasks(pageDesc,userCode,filterMap);
        totalRows = pageDesc.getTotalRows();
        if(StringUtils.isNotBlank(flowOptName)){
            request.setAttribute("s_flowOptName", flowOptName);
        }
        return "CompNodes";
} 
    private List<NodeInstance> listUserCompleteTasks(PageDesc pageDesc, String userCode, Map<String, Object> filterMap) {
        Map<String, Object> mapN = new HashMap<String, Object>();
        Map<String, Object> mapflow = new HashMap<String, Object>();
        String flowOptName=null;
        String lastUpdateTime=null;
        if(StringUtils.isNotBlank((String)filterMap.get("flowOptName"))){
            flowOptName =(String)filterMap.get("flowOptName");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("lastUpdateTime"))){
            lastUpdateTime =(String)filterMap.get("lastUpdateTime");
        }
        //根据条件过滤
        List<WfNodeInstance> tempList = optProcInfoManager.listfNodeInstanceByUser(userCode);
        int pageStart = (pageDesc.getPageNo() - 1) * pageDesc.getPageSize() + 1;
        int pageEnd = pageDesc.getPageNo()*pageDesc.getPageSize();
        List<NodeInstance> temp = new ArrayList<NodeInstance>();
        if (tempList != null) {
            int index = 0;
            for (WfNodeInstance nodeInst : tempList) {
                WfFlowInstance flowInst = flowInstanceDao
                        .getObjectById(nodeInst.getFlowInstId());
                if (flowInst == null || !"N".equals(flowInst.getInstState())) {
                    continue;
                }
                //办件名称过滤
                if (StringUtils.isNotBlank(flowOptName) && !flowInst.getFlowOptName().contains(flowOptName)) {
                    continue;
                }
                //时间过滤DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd")
                if(StringUtils.isNotBlank(lastUpdateTime) && flowInst.getLastUpdateTime()!=null){
                    String lastUpdateTimeTemp=DatetimeOpt.convertDateToString(flowInst.getLastUpdateTime(), "yyyy-MM-dd");
                    if(!lastUpdateTimeTemp.equals(lastUpdateTime)){
                     continue;   
                    }
                }
                nodeInst.setFlowOptName(flowInst.getFlowOptName());
                nodeInst.setFlowOptTag(flowInst.getFlowOptTag());

                WfNode wfNode = flowNodeDao.getObjectById(nodeInst.getNodeId());
                if (wfNode == null) {
                    continue;
                }
                nodeInst.setRoleCode(wfNode.getRoleCode());
                nodeInst.setRoleType(wfNode.getRoleType());
                nodeInst.setNodeName(wfNode.getNodeName());

                // 判断节点任务是否提供回收：a.下一步办理人为当前操作人员的;b.下一节点已经被处理过的
                    nodeInst.setIsRecycle("yes");
                    index ++;
                    if(index >= pageStart && index <= pageEnd){
                        temp.add(nodeInst);
                    }
            }
            pageDesc.setTotalRows(index);
            return temp;
        }
        return new ArrayList<NodeInstance>();
    }
    
    /**
     * 根据djId获取业务信息,
     * 并追加在frameList
     * @param frameList
     * @param djId
     */
    public List<OptHtmlFrameInfo> getBaseInfoIframe(List<OptHtmlFrameInfo>  frameList,String djId){
        
        String type = djId.replaceAll("\\d+", "");
        if("SW".equals(type)){
            IncomeDoc incomeDoc = incomeDocManager.getObjectById(djId);
            if(null != incomeDoc){
                frameList.add(getIncomeDocFrame(djId));//基础业务信息查看
            }
        }  
        if("FW".equals(type)){
            DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(djId);
                if(checkCanEdit()){
                    frameList.add(getDispatchDocEditFrame(djId));// 基础业务信息查看
                    
               }else{
                   frameList.add(getDispatchDocViewFrame(djId));// 基础业务信息查看   
                }
        }
        return frameList;
    }
    
    /**
     * 公文收藏
     */
    private String getCollsatus() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        if(fuser==null){
            return "login";
        }
        String userCode = fuser.getUsercode();

        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());

        OptProcCollection collection = optProcCollectionManager
                .getObjectById(new OptProcCollectionId(optBaseInfo.getDjId(),
                        userCode));
        if (collection != null && "T".equals(collection.getIsatt())) {
            request.setAttribute("collstatus", "recoll");
        } else {
            request.setAttribute("collstatus", "coll");
        }
        return "";
    }

    /**
     * 会签汇总
     * 
     * @return
     */
    public String signTheSummary() {
        return this.generalOptFrame("ioDocTasksExcute", "toDoSignTheSummary",
                "会签汇总");
    }

    /**
     * 置文号
     * 
     * @return
     */
    public String incomeDocZWH() {
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(optBaseInfo
                .getDjId());
        
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        String orgCode=fuser.getPrimaryUnit();//文号所属部门
        
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameLegend("办理信息");
        transFrameInfo.setFrameSrc("/dispatchdoc/ioDocArchiveNo!edit.do?djId="
                + optBaseInfo.getDjId() + "&flowInstId="
                + super.getFlowInstId() + "&nodeInstId="
                + object.getNodeInstId() + "&moduleCode=" + moduleCode
                + "&wjlx=" + dispatchDoc.getDispatchFileType() + "&orgCode="
                + orgCode + "&flowPhase="
                + object.getFlowPhase() + "&nodeCode=" + object.getNodeCode());

       
//      List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();       
//        frameList.add(transFrameInfo);
//
//        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(), "incomeDocZWH");
//        super.initFlowTime();
//        // 加载该环节是否存在督办信息
//        this.dcdbInfo(object.getNodeCode());
//        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
//        request.setAttribute("sqdjId", optBaseInfo.getDjId());
//        // 公文收藏
//        this.getCollsatus();
        
    
        return  boundAllInfoframeModuleTwo(transFrameInfo);//置文号办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    
    
    
    
    
    
    
    
    /**
     * 阅件办公室审核
     * 
     * @return
     */
    public String getPieceFrame() {

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        this.setNodeInstId(object.getNodeInstId());

        frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面

        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
                "getPieceFrame");
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);

        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        // 公文收藏
        this.getCollsatus();
        return "optframe";
    }

    /**
     * 办（阅办）件 前三个节点，涉及到选择主任阅示的节点
     * 
     * @return
     */
    public String byj_chooseZRWS() {
       
       
        
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        
//        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
//        
//        IncomeDoc incomeDoc = incomeDocManager.getObjectById(optBaseInfo
//                .getDjId());
//        if(null != incomeDoc){
//            frameList.add(getIncomeDocFrame(optBaseInfo.getDjId()));//基础业务信息查看
//        }
//        
//        frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
//                object.getNodeCode(), object.getFlowPhase()));// 附件
        
        
        
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameLegend("办理信息");
        transFrameInfo
                .setFrameSrc("/dispatchdoc/ioDocTasksExcute!tobyj_chooseZRWS.do?djId="
                        + optBaseInfo.getDjId()
                        + "&flowInstId="
                        + super.getFlowInstId()
                        + "&nodeInstId="
                        + object.getNodeInstId()
                        + "&flowPhase="
                        + object.getFlowPhase()
                        + "&nodeCode="
                        + object.getNodeCode()
                        + "&moduleCode="
                        + moduleCode
                        + "&documentTemplateIds=" + documentTemplateIds);
//        frameList.add(transFrameInfo);
//
//        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
//                "genIncomeDocFrame");
//        super.initFlowTime();
//        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
//        initUsers();
//        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
//        // 配置启动事权或者发文的开关
//        request.setAttribute("sqdjId", optBaseInfo.getDjId());
//        // 公文收藏
//        this.getCollsatus();
        
         return  boundAllInfoframeModuleTwo(transFrameInfo);//transFrameInfo办理界面-后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    /**
     * 退回，重新编辑公文信息
     * 
     * @return
     */
    public String reEditGW() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");

        transFrameInfo
                .setFrameSrc("/dispatchdoc/incomeDoc!registerGWEdit.do?djId="
                        + optBaseInfo.getDjId() + "&flowInstId="
                        + super.getFlowInstId() + "&nodeInstId="
                        + object.getNodeInstId() + "&moduleCode=" + moduleCode
                        + "&documentTemplateIds=" + documentTemplateIds
                        + "&flowCode=" + optBaseInfo.getFlowCode() + "&optId="
                        + optBaseInfo.getOptId() + "&itemId="
                        + optBaseInfo.getPowerid());
        frameList.add(transFrameInfo);
        this.boundAllInfoframe(frameList, object.getDjId(), "genIncomeDocFrame");
        super.initFlowTime();
        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        // 配置启动事权或者发文的开关
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        // 公文收藏
        this.getCollsatus();
        return "optframe";
    }

    public String reEditFW() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");

        transFrameInfo
                .setFrameSrc("/dispatchdoc/dispatchDoc!registerFWEdit.do?djId="
                        + object.getDjId() + "&flowInstId="
                        + super.getFlowInstId() + "&nodeInstId="
                        + object.getNodeInstId() + "&moduleCode=" + moduleCode
                        + "&documentTemplateIds=" + documentTemplateIds
                        + "&flowCode=" + optBaseInfo.getFlowCode() + "&optId="
                        + optBaseInfo.getOptId() + "&itemId="
                        + optBaseInfo.getPowerid());

        frameList.add(transFrameInfo);
        
        frameList=getBaseInfoIframe( frameList,optBaseInfo.getDjId());//基本信息（业务信息+办理信息）
       

        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo fif = flowEngine.getNodeInfoById(nodeInst.getNodeId());
        this.setFrameList(frameList, fif.getNodeName() + "办理");

        super.initFlowTime();
        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        // 公文收藏
        this.getCollsatus();
        return "optframe";
    }

    /**
     * 退回，重新编辑审批信息
     * 
     * @return
     */
    public String reEditSP() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameSrc("/dispatchdoc/incomeDoc!reEdit.do?djId="
                + object.getDjId() + "&flowInstId=" + super.getFlowInstId()
                + "&nodeInstId=" + object.getNodeInstId() + "&moduleCode="
                + moduleCode + "&documentTemplateIds=" + documentTemplateIds
                + "&flowCode=" + optBaseInfo.getFlowCode() + "&optId="
                + optBaseInfo.getOptId() + "&itemId="
                + optBaseInfo.getPowerid());
        frameList.add(transFrameInfo);
        this.boundAllInfoframe(frameList, object.getDjId(), "genIncomeDocFrame");
        super.initFlowTime();
        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        // 公文收藏
        this.getCollsatus();
        return "optframe";
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

    public List<OaSupervise> getDcdblist() {
        return dcdblist;
    }

    public void setDcdblist(List<OaSupervise> dcdblist) {
        this.dcdblist = dcdblist;
    }

    private String dbdjid;

    public String getDbdjid() {
        return dbdjid;
    }

    public void setDbdjid(String dbdjid) {
        this.dbdjid = dbdjid;
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
        if (dcdblist.size() > 0) {
            dbdjid = dcdblist.get(0).getSupDjid();
        }
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
//        frameList.add(getAllInfoFrame(djId, bizMenth));// 查看全部过程信息

        // frameList 页面列表显示
        // frameList=getAllInfoListFrame(frameList,djId,bizMenth);
        jspInfo = new OptJspInfo();
        jspInfo.setTitle(fif.getNodeName()
                + (fif.getNodeName().endsWith("办理") ? "" : "办理"));
        jspInfo.setFrameList(frameList);
    }
    
    /**
     * 组合所有查看页面 模板2
     * 左侧 拟文单+附件+办理信息+保存提交按钮  右侧 快速定位+过程信息+流程图等信息查看
     * @param todoFrameInfo
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

        this.setNodeInstId(object.getNodeInstId());
        
        frameList=getBaseInfoIframe(frameList, optBaseInfo.getDjId());// 基础业务信息查看-收發文通用
         
        
        frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 附件
        
        if(null==todoFrameInfo){
            frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                    object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面
        }else{
            frameList.add(todoFrameInfo);
        
        }
        jspInfo = new OptJspInfo();
        jspInfo.setFrameList(frameList);
        //页面布局结束
        
        
        //功能点开始
        //公文收藏|是否已经收藏 
        this.getCollsatus();
        
        //是否有关联事项
        boolean flag = this.getOaBizInfolist(optBaseInfo.getDjId());
        request.setAttribute("hasRelSubject", flag);
        //流程时限
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        // 配置启动事权或者发文的开关
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
       //功能点结束
        //返回待办
        request.setAttribute("dashboard", request.getParameter("dashboard"));
        return "optframe";
    } 
    
    

   

    /**
     * 组合所有查看页面2
     * 
     * @param djId
     * @return
     */
    private OptHtmlFrameInfo getAllInfoFrame(String djId, String bizMenth) {
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        String itemType = null;
        if(StringUtils.isNotEmpty(djId)){
            itemType = djId.replaceAll("[0-9]+", "");
        }
        procFrameInfo.setFrameId("AllInfoFrame");
        procFrameInfo
                .setFrameSrc("/dispatchdoc/ioDocTasksExcute!viewAllList.do?djId="
                        + djId
                        + "&flowInstId="
                        + super.getFlowInstId()
                        + "&bizMenth=" + bizMenth
                        + "&itemType="+itemType);
        procFrameInfo.setFrameHeight("400px");
        return procFrameInfo;
    }

    /**
     * 页面展示所有iframe方式，和LAB方式区别
     * 
     * @param frameList
     * @param djId
     * @param bizMenth
     * @return
     */
    @SuppressWarnings("unused")
    private List<OptHtmlFrameInfo> getAllInfoListFrame(
            List<OptHtmlFrameInfo> frameList, String djId, String bizMenth) {
        // frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
        frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        // add by lq 收文基础信息查看
        /*
         * if ("genIncomeDocFrame".equals(bizMenth)) { //
         * optNewlyIdeaList.add(optNewly_getIncomeDocFrame2
         * (optBaseInfo.getDjId()));// // 基础收文业务信息查看 }
         */

        // 通用节点时，拟文后节点，将建设项目审批信息放到Tab页显示
        if ("genDispatchDocFrame".equals(bizMenth)
                || "generalOptFrame".equals(bizMenth)
                || "incomeDocZWH".equals(bizMenth)
                || "getPieceFrame".equals(bizMenth)) {
            DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(object
                    .getDjId());
            if (null != dispatchDoc) {
                // optNewlyIdeaList.add(getDispatchDocFrame(object.getDjId(),
                // "view"));// 基础业务信息查看
                optNewlyIdeaList.add(optNewly_getDispatchDocFrame(
                        optBaseInfo.getDjId(), "view"));// 基础业务信息查看
                // 拟文信息
                // if (!"nw".equals(object.getFlowPhase())) {
                // IncomeProject
                // incomeProject=incomeProjectManager.getObjectById(optBaseInfo.getDjId());
                // if(incomeProject!=null){
                // IncomeDoc incomeDoc =
                // incomeDocManager.getObjectById(object.getDjId());
                // if (null != incomeDoc &&
                // "YBXM".equals(incomeDoc.getApplyItemType())) {
                // optNewlyIdeaList.add(optNewly_viewProjectInfoApprovalFrame(object.getDjId()));
                // }
                // }
                // }
            }
        }
        // }
        return frameList;
    }

    public String viewAllList() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        String bizMenth = request.getParameter("bizMenth");
        Boolean flag = this.getOaBizInfolist(optBaseInfo.getDjId());
        if (flag) {
            optNewlyIdeaList.add(optNewly_genOaBizBindInfoFrame(optBaseInfo
                    .getDjId()));
        }
        // add by lq 收文基础信息查看
        if ("genIncomeDocFrame".equals(bizMenth)) {
            optNewlyIdeaList.add(optNewly_getIncomeDocFrame2(optBaseInfo
                    .getDjId()));// 基础收文业务信息查看
        }

        // 通用节点时，拟文后节点，将建设项目审批信息放到Tab页显示
        if ("genDispatchDocFrame".equals(bizMenth)
                || "generalOptFrame".equals(bizMenth)
                || "incomeDocZWH".equals(bizMenth)
                || "getPieceFrame".equals(bizMenth)) {
            DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(object
                    .getDjId());
            if (null != dispatchDoc) {
                // optNewlyIdeaList.add(getDispatchDocFrame(object.getDjId(),
                // "view"));// 基础业务信息查看
                optNewlyIdeaList.add(optNewly_getDispatchDocFrame(
                        optBaseInfo.getDjId(), "view"));// 基础业务信息查看
                // 拟文信息
                // if (!"nw".equals(object.getFlowPhase())) {
                // IncomeProject
                // incomeProject=incomeProjectManager.getObjectById(optBaseInfo.getDjId());
                // if(incomeProject!=null){
                // IncomeDoc incomeDoc =
                // incomeDocManager.getObjectById(object.getDjId());
                // if (null != incomeDoc &&
                // "YBXM".equals(incomeDoc.getApplyItemType())) {
                // optNewlyIdeaList.add(optNewly_viewProjectInfoApprovalFrame(object.getDjId()));
                // }
                // }
                // }
            }
        }
        // }

        curUrl = "/powerruntime/generalOperator!listIdeaLogs.do?djId="
                + optBaseInfo.getDjId();
        if (null == super.getFlowInstId()) {
            super.setFlowInstId((long) 9999999);
        }
        request.setAttribute("flowInstId", super.getFlowInstId());
        return "viewAllList";
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

    /**
     * 封装关联业务信息
     */
    private OptNewlyIdea optNewly_genOaBizBindInfoFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 4);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("关联业务信息");
        newlyIdea.setUrl("/oa/oaBizBindInfo!listbiz4tab.do?djid=" + djId);
        return newlyIdea;
    }

    /**
     * 拟文信息
     * 
     * @param djId
     * @param string
     * @return
     */
    private OptNewlyIdea optNewly_getDispatchDocFrame(String djId, String string) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 5);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("拟文信息");
        newlyIdea
                .setUrl("/dispatchdoc/dispatchDoc!viewDispatchDocInfo.do?djId="
                        + djId + "&flowInstId=" + super.getFlowInstId());
        return newlyIdea;
    }

    /**
     * 收文业务信息
     * 
     * @param djId
     * @return
     */
    private OptNewlyIdea optNewly_getIncomeDocFrame2(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 3);
        newlyIdea.setDjId(djId);
        newlyIdea.setNodename("阅办文业务信息");
        // newlyIdea.setUrl("/dispatchdoc/incomeDoc!viewIncomeDoc.do?djId=" +
        // djId);
        newlyIdea
                .setUrl("/dispatchdoc/incomeDoc!registerDoOrReadView.do?vewtype=T&djId="
                        + djId);
        return newlyIdea;
    }

    /**
     * Tab_收文业务信息
     * 
     * @param djId
     * @return
     */
    @SuppressWarnings("unused")
    private OptNewlyIdea optNewly_getIncomeDocFrame(String djId) {
        OptNewlyIdea newlyIdea = new OptNewlyIdea();
        newlyIdea.setNodeid((long) 1);
        newlyIdea.setDjId(djId);
        // newlyIdea.setNodename("登记信息");
        newlyIdea
                .setUrl("/dispatchdoc/incomeDoc!viewIncomeDocAndProjectInfo.do?djId="
                        + djId);
        return newlyIdea;
    }

    /********************************* 办理页面加载 ******************************/

    public String doOpt() {

        try {
            super.setDjId(object.getDjId());
            super.setNodeInstId(curNodeInstId);
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);// "XKSL"
            FUserDetail fUserDetail = (FUserDetail) getLoginUser();
            extractFlowOptParam();
            
          
            //是否显示前一步办理信息
            /* if("T".equals(moduleParam.getHasPreIdea())){*/
                /* object = optProcInfoManager.getObjectByNodeInstId(object
                         .getNodeInstId());*/
                 object= optProcInfoManager.getObjectByDjidAndNodeInstId(object.getDjId(), object.getNodeInstId(),fUserDetail.getUsercode() );
             /*}*/
            

            // 判断是 新建 还是更新
            if (object == null) {
                object = new OptProcInfo();
                object.setDjId(super.getDjId());
                object.setNodeInstId(curNodeInstId);
            }
//            super.initalOptProcInfo();
            /**
             * 根据参数是否需要 关注人 ，提供候选关注人列表
             */
            super.initAttUsersConfig();
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
             * 根据参数是否有风险点 ，提供风险点的风险内控手段与结果的维护
             */
            super.initRiskConfig();

            /**
             * 根据是否可以上传附件 ，确定可以上传附件的类型
             */
            if (moduleParam.getHasStuff() != null
                    && "T".equals(moduleParam.getHasStuff())) {
                OptBaseInfo optBaseInfo = optBaseInfoManager
                        .getObjectById(object.getDjId());
                if (optBaseInfo != null) {
                    moduleParam.setPowerId(optBaseInfo.getPowerid());
                }
            }
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            if (moduleParam != null && moduleParam.getBookMarkType() != null
                    && "A".equals(moduleParam.getBookMarkType())) {
                optCommonBizJson = dispatchDocManager.getOptBaseInfoJSONByDjId(
                        object.getDjId(), documentTemplateIds,
                        fuser.getPrimaryUnit());

                optProcInfoJSON = dispatchDocManager
                        .getOptProcInfoJSONByDjId(object.getDjId());
            } else if (moduleParam != null
                    && moduleParam.getBookMarkType() != null
                    && "S".equals(moduleParam.getBookMarkType())) {
                optCommonBizJson = dispatchDocManager
                        .getOptSLHJSONByDjId(object.getDjId());

            }
            DispatchDoc disDoc = dispatchDocManager.getObjectById(object
                    .getDjId());

            if (moduleParam != null && moduleParam.getHasDocument() != null
                    && "T".equals(moduleParam.getHasDocument())) {
              //modify by lay 
//                if (disDoc != null) {
//                    object.setRecordId(disDoc.getRecordId());
//                    object.setArchiveType("zw");
//                }
                object.setRecordId(moduleParam.getDocumentTemplateIds());
                object.setArchiveType(moduleParam.getDocumentType());
                //获取json数据
                if(optCommonBizJson == null){
                    optCommonBizJson = dispatchDocManager.getOptBaseInfoJSONByDjId(
                            object.getDjId(), documentTemplateIds,
                            fuser.getPrimaryUnit());
                }
                //end add
            }
            /**
             * 根据是否可以生产公文 ，确定需要编辑的文档模板
             */
            super.initTemplateConfig();
            /**
             * 多模板情况加载
             */
            super.initTemplateFromNode();

         

            String nodeCode = request.getParameter("nodeCode");

            /**
             * 批分功能配置
             */
            if ("T".equals(moduleParam.getHasOrgRole())) {

                initPFUnit(moduleParam.getXbOrgRoleCode(), nodeCode);
                initXbOrgConfig();
            }
            
            String flowPhase = request.getParameter("flowPhase");
            request.setAttribute("flowPhase", flowPhase);
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            request.setAttribute("powerid", optBaseInfo.getPowerid());

            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
          
            if (StringUtils.isBlank(object.getRecordId())
                    && StringUtils.isNotBlank(documentTemplateIds)) {
                object.setRecordId(documentTemplateIds);
                object.setArchiveType("zw");
            }
            
          //保存查看日志
            FUserDetail user = (FUserDetail) getLoginUser();
            UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(baseInfo.getTransaffairname(),baseInfo.getDjId()),curNodeInstId);
            userbizoptLogManager.saveUserbizoptLog(u, user);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e.getCause());
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
        return "IODocTransForm";

    }
    private UserbizoptLogManager userbizoptLogManager;
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }

    

    /**
     * 引用启动子流程业务的办理界面
     * 
     * @return
     */
    public String doSubOpt() {
        try {
            super.setDjId(object.getDjId());
            super.setNodeInstId(curNodeInstId);
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);// "XKSL"

            extractFlowOptParam();

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
            super.initTeamUsersConfig();

            /**
             * 根据参数是否有风险点 ，提供风险点的风险内控手段与结果的维护
             */
            super.initRiskConfig();

            /**
             * 根据是否可以上传附件 ，确定可以上传附件的类型
             */
            if (moduleParam.getHasStuff() != null
                    && "T".equals(moduleParam.getHasStuff())) {
                OptBaseInfo optBaseInfo = optBaseInfoManager
                        .getObjectById(object.getDjId());
                if (optBaseInfo != null) {
                    moduleParam.setPowerId(optBaseInfo.getPowerid());
                }
            }
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            optCommonBizJson = dispatchDocManager.getOptBaseInfoJSONByDjId(
                    object.getDjId(), documentTemplateIds,
                    fuser.getPrimaryUnit());

            if (moduleParam != null && moduleParam.getHasDocument() != null
                    && "T".equals(moduleParam.getHasDocument())) {
                DispatchDoc disDoc = dispatchDocManager.getObjectById(object
                        .getDjId());
                if (disDoc != null) {
                    object.setRecordId(disDoc.getRecordId());
                    object.setArchiveType("zw");
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
             * 通用运行配置，是否引用上一步骤的办理意见
             */
            if ("T".equals(moduleParam.getHasPreIdea())) {
                OptProcInfo preTransInfo = super.getPreTransInfo();
                if (preTransInfo != null) {
                    object.setIdeacode(preTransInfo.getIdeacode());
                    object.setIshq(preTransInfo.getIshq());
                    // object.setTranscontent(preTransInfo.getTranscontent());
                }
            }

            /**
             * 是否会签后台判断
             * 
             */
            String nodeCode = request.getParameter("nodeCode");

            /**
             * 批分功能配置
             */
            if ("T".equals(moduleParam.getHasOrgRole())) {
                initPFUnit(moduleParam.getXbOrgRoleCode(), nodeCode);
                initXbOrgConfig();
            }

            if ("zbcsfzrsh".equals(nodeCode)) {
                Set<String> fos = flowEngine.viewFlowOrganize(
                        super.getFlowInstId(), "hbcsfb");
                object.setIdeacode((null != fos && !fos.isEmpty()) ? "H" : "T");
            }
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
        return "SubProjectTransForm";

    }
    /*********************************** 保存\提交操作 *********************************************/
    public String submitOpt() {
        try {
           
               saveOpt();
               // 保存过程日志信息
               saveIdeaInfo();
               FUserDetail user = (FUserDetail)getLoginUser();
            // 拟文节点可以不生成正文，但在不生成正文的情况下，需要保存一个name为noZw的流程变量（如果生成正文，则需要清楚该流程变量）
            String noZw = request.getParameter("noZw");
            noZw = StringUtils.isBlank(noZw) ? null : noZw;
            flowEngine.saveFlowVariable(super.curFlowInstId, "noZw", noZw);
            if (StringUtils.isNotBlank(object.getIshq())) {
                flowEngine.saveFlowVariable(super.getFlowInstId(), "ishq",object.getIshq());
            }
            //主要领导签发退回的时候 把rollback的值塞进去
            if("FW_LDQF".equals(object.getNodeCode())&&"LDQF".equals(object.getIdeacode())) {
                flowEngine.saveFlowVariable(super.getFlowInstId(), "rollBack",object.getNodeCode());
            }
            
            Set<Long> nextNode = new HashSet<Long>();
            if("xwsw_bmcy".equals(object.getNodeCode())&&"F".equals(object.getIdeacode())) {
                Long oneNode=flowManager.rollbackOpt(object.getNodeInstId(), user.getUsercode());
                nextNode.add(oneNode);
            }else if("xwbmsw_bmcy".equals(object.getNodeCode())&&"F".equals(object.getIdeacode())){
                Long oneNode=flowManager.rollbackOpt(object.getNodeInstId(), user.getUsercode());
                nextNode.add(oneNode);
            }else if("tysw_bmcy".equals(object.getNodeCode())&&"F".equals(object.getIdeacode())){
                Long oneNode=flowManager.rollbackOpt(object.getNodeInstId(), user.getUsercode());
                nextNode.add(oneNode);
            }else {
                nextNode = submitNode();
            }
            if("xwsw_zldy".equals(object.getNodeCode())) {//领导意见汇总节点提交之前做特殊处理，删除领导审批ldSP
                flowEngine.deleteFlowOrganize(this.getFlowInstId(), "ldSP");
                flowEngine.deleteFlowWorkTeam(this.getFlowInstId(), "ldSP");
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
                refreshLeaderSort();
                flowEngine.deleteFlowWorkTeam(this.getFlowInstId(), "fgwldSH");//清除分管领导阅示wf_team数据，为领导意见汇总回退做准备
            }
            
           
            if(null != user){
                if("xwsw_fldy".equals(object.getNodeCode())) {
                    if(null!=nextNode && nextNode.size()==0) {
                        nextNode.add(object.getNodeInstId());
                    }
                }
                // 发送短信
                oaSmssendManager.saveFlowMsgs(request.getParameter("isSendMessage"), user.getUsercode(), nextNode);    
                oaSmssendManager.executeSendMsg();
            }
            
            if("xwsw_ldyjhz".equals(object.getNodeCode())){
                List<NodeInstance> wfids= flowManager.listFlowInstNodes(super.getFlowInstId());
                if(wfids!=null && wfids.size()>0){
                    for(NodeInstance nodeInstance:wfids){
                        if("I".equals(nodeInstance.getNodeState())){
                            flowManager.activizeNodeInstance(nodeInstance.getNodeInstId(), "99999999");
                        }
                    }
                }
            }
            
            //多实例节点导致节点失效
            if("xwsw_swyb".equals(object.getNodeCode())){
                List<NodeInstance> wfids= flowManager.listFlowInstNodes(super.getFlowInstId());
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
            if(("xwsw_swyb".equals(object.getNodeCode()) && "T".equals(object.getIdeacode()))
                    || ("xwsw_bmcy".equals(object.getNodeCode()) && "B".equals(object.getIdeacode()))
                    || ("xwsw_bsw".equals(object.getNodeCode()) && "Y".equals(object.getIdeacode()))){
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
                        OptBaseInfo bean = optBaseInfoManager.getOptBaseByFlowId(super.getFlowInstId());//汇总 的时候是否完成 状态修改
                        bean.setBizstate("W");
                        optBaseInfoManager.saveObject(bean);
                    }
                }
            }
            //内部转办重复给一个人，只收到一件待办
            List<String> nameall = new ArrayList<String>();
            if("xwsw_swyb".equals(object.getNodeCode()) && "YB".equals(object.getIdeacode())){
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Object> maptask = new HashMap<String, Object>();
                map.put("wfinstid", String.valueOf(super.getFlowInstId()));
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
                         //发文
                        if(bizType == 1){
                             formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(contextPath,userCode, object.getDjId(), String.valueOf(curFlowInstId));
                        }
                        //收文
                        if(bizType == 2){
                             formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath, userCode, object.getDjId());
                             
                        }
                        createPDF(object.getDjId(), object.getNodeInstId(),userCode,exePath,formHtmlUrl);
                    }
                });
            }
            return "refreshTasks";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e.getCause());
            request.setAttribute("error", "当前操作提交失败，详见系统日志。");
            return ERROR;
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
    private void refreshLeaderSort(){
        List<Oaleadersort> oaleadersortList = dispatchDocManager.listOaLeaderSortByNode(object.getNodeInstId());
        if(oaleadersortList == null || oaleadersortList.size() == 0 ){
            return;
        }
        //和当前操作用户比较 将状态设置为不可用。oaleadersortList这个已经按order排序，所以下面直接判断
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        boolean flag = false;
        for(int i = oaleadersortList.size()-1;i >= 0;i--){
            Oaleadersort oaleadersort = oaleadersortList.get(i);
            if(fuser.getUsercode() != null
                    && fuser.getUsercode().equals(oaleadersort.getUsercode())){
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
    
   
    
    private void createPDF(String djId,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        NodeInstance nodeInst = flowEngine.getNodeInstById(nodeInstId);
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        OptPdfInfo optPdfInfo = null;
        try {
            String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(djId, nodeInstId);
            File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF2(djId,nodeInstId,formHtmlUrl);
           optPdfInfo.setUserCode(userCode);
            //重新添加图层
           optPdfInfo = optPdfInfoManager.addLayerOnPDF(optPdfInfo);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
           //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
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
    /**
     * 
     * @param nextNode
     */
    public void saveSms(Set<Long> nextNode) {
        //rtx开关
        String autoLoginRtx=CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTX").getDatavalue();
        if(StringUtils.isNotBlank(autoLoginRtx) && "T".equals(autoLoginRtx)){
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
     * 挂起流程
     */
    private void suspendInstance() {
        // 行政审批处经办人提出办理意见步骤，如果是补正操作。。。
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        if (optBaseInfo != null) {
            optBaseInfo.setIsUpload("0");// 未同步状态
            optBaseInfo.setBizstate("B");
            optBaseInfoManager.saveObject(optBaseInfo);
            // 挂起流程
            flowManager.suspendInstance(super.getFlowInstId(),
                    ((FUserDetail) getLoginUser()).getUsercode(),
                    "【补正:外网数据报送，挂起流程！】");
        }
    }

    /**
     * 保存操作结果
     * 
     * @return
     */
    public String saveOpt() {
        try {
            String djId = object.getDjId();
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);
            super.setNodeInstId(object.getNodeInstId());
            if (djId.startsWith("SW")) {
                IncomeDoc incomeDoc = incomeDocManager.getObjectById(djId);

                if (null != incomeDoc) {
                    incomeDoc.setUpdateDate(DatetimeOpt.currentUtilDate());

                    incomeDocManager.saveObject(incomeDoc);
                }
            }

            // 添加保存操作
            FUserDetail loginInfo = (FUserDetail) getLoginUser();
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
            if(("xwsw_swyb".equals(object.getNodeCode()) && "YB".equals(object.getIdeacode()))
                    || ("xwsw_nbbl".equals(object.getNodeCode()))
                    || ("xwbmsw_bmcy".equals(object.getNodeCode()) && "NYB".equals(object.getIdeacode()))
                    || ("tysw_bmcy".equals(object.getNodeCode()) && "NYB".equals(object.getIdeacode()))
                    || ("xwsw_bmcy".equals(object.getNodeCode()) && "NYB".equals(object.getIdeacode()))
                    || ("xwsw_swrpf".equals(object.getNodeCode()) && "PF".equals(object.getIdeacode()))){
                flowEngine.deleteFlowWorkTeam(nodeInst.getFlowInstId(), "zbcbr");
            }
            
            if(("fw_bfh".equals(object.getNodeCode()) && "FLD".equals(object.getIdeacode()))
                    || ("xwsw_nbbl".equals(object.getNodeCode()))
                   ){
                flowEngine.deleteFlowWorkTeam(nodeInst.getFlowInstId(), "fgwldqf");
            }
            
            Map<String,Set<String>> workTeamSet  = flowEngine.viewFlowWorkTeam(super.getFlowInstId());
            if("xwsw_ldyjhz".equals(object.getNodeCode())){//领导意见汇总
                if(workTeamSet != null && workTeamSet.size() > 0){//fgwldSH
                    for(String role :workTeamSet.keySet()){
                        if("fgwldSH".equals(role)){
                            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(),role);
                            optProcInfoManager.deleteLeaderOder(super.getFlowInstId());
                        }
                    }
                }    
            }
            // 保存recordId
            if (StringUtils.isBlank(object.getRecordId())) {
                String curTemplateId = request.getParameter("curTemplateId");
                if (StringUtils.isNotBlank(curTemplateId)) {
                    object.setRecordId(curTemplateId);
                } else {
                    String dnTemplateId = request.getParameter("dnTemplateId");
                    if (StringUtils.isNotBlank(dnTemplateId)) {
                        object.setRecordId(dnTemplateId);
                    }
                }
            }
            optProcInfoManager.saveObject(object);// 
           // 保存关注人员
            this.saveAtt();
            super.saveTeamRolepeople();// 保存角色
            super.saveZBTeamRolepeople();// 保存角色
            super.saveXBTeamRolepeople();// 保存角色
            super.saveEngineRolepeople();// 保存权限引擎角色
            
            
            // 保持流程和业务的信息
            this.saveBizSubmitInfo();
           

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
            // add by zkp 跳转各自流程代办列表
            this.initReturn();
            return "refreshTasks";
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存流程过程信息失败。");
            return ERROR;
        }
    }


    /**
     * 根据环节代码提交业务信息
     * 
     *
     */
    private void saveBizSubmitInfo() {

        String nodeCode = object.getNodeCode();

        // 签报流程保存业务数据操作
        if ("qb_bgrcl".equalsIgnoreCase(nodeCode)
                && object.getIdeacode().equals("bm")) {// 签报拟稿人结束流程时保存数据

            SignedReport signedReport = signedReportManager
                    .getObjectById(object.getDjId());
            signedReport.setSolvenote(object.getTranscontent()); // 办结意见
            signedReport.setSolvetime(new Date(System.currentTimeMillis()));// 办结时间
            signedReport.setBizstate("C");
            signedReport.setSolvestatus("C");
            
            signedReportManager.saveObject(signedReport);
        }

    }

    private String flowCode;
    private String itemtype;

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    private void initReturn() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        // 区分事权和收发文代办列表根据itemType
        IncomeDoc incomeDoc = incomeDocManager.getObjectById(object.getDjId());
        if (object.getDjId().indexOf("FW") != -1) {
            flowCode = optBaseInfo.getFlowCode();
            itemtype = "FW";
        } else if (object.getDjId().indexOf("SW") != -1) {
            flowCode = optBaseInfo.getFlowCode();
            itemtype = "SW";
        }
        if (object.getDjId().indexOf("QB") != -1) {
            flowCode = optBaseInfo.getFlowCode();
        }
        if (object.getDjId().indexOf("SQ") != -1) {
            flowCode = optBaseInfo.getFlowCode();
            itemtype = incomeDoc.getApplyItemType();
        }
    }

    /**
     * 获取主办协办部门参数
     */
    protected void initPFUnit(String roleCode, String nodeCode) {

        List<FUnitinfo> unitList = null;
        VUserTaskList userTask = flowManager.getUserTaskInfo(curNodeInstId);
        
        unitList = sysUnitManager.listObjects();
        unitsJson = sysUnitManager.getAllUnitsJSONNoTree();
      

        if(null!=unitList&&unitList.size()>0){//去除市总工会顶级部门
            if(null!=unitList.get(0)&&"1".equals(unitList.get(0).getUnitcode())){
                unitList.remove(0);
            }
        }

        request.setAttribute("unitList", unitList);
    }



    /**
     * 收文业务数据查看
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo
                .setFrameSrc("/dispatchdoc/incomeDoc!registerDoOrReadView.do?vewtype=T&djId="
                        + djid);
        viewFrameInfo.setFrameHeight("700px");
        return viewFrameInfo;
    }




    /**
     * 
     *
     *            []
     */
    private String[] viewFlowWorkTeam(String roleCode) {
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



    /**
     * 主动发文通用操作定义
     * 
     * @return
     */
    public String getActiveDocFrame() {
        
        return  boundAllInfoframeModuleTwo(null);//null 通用办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
        
    }

    /**
     * 引用启动子流程业务的办理界面
     * 
     * @param djId
     * * @param nodeCode
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
     * 置文号：不符合通用条件的特殊业务处理
     * 
     * @return
     */
    public String dispatchDocZWH() {
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(object
                .getDjId());

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");

        transFrameInfo.setFrameSrc("/dispatchdoc/ioDocArchiveNo!edit.do?djId="
                + object.getDjId() + "&flowInstId=" + super.getFlowInstId()
                + "&nodeInstId=" + object.getNodeInstId() + "&moduleCode="
                + moduleCode + "&wjlx=" + dispatchDoc.getDispatchFileType()
                + "&orgCode=" + optBaseInfo.getOrgcode() + "&flowPhase="
                + object.getFlowPhase() + "&nodeCode=" + object.getNodeCode());

        frameList.add(transFrameInfo);
        
        frameList=getBaseInfoIframe( frameList,optBaseInfo.getDjId());//基本信息（业务信息+办理信息）
      
        this.setFrameList(frameList, "置文号");
        super.initFlowTime();
        return "optframe";
    }

    /**
     * 查看或者编辑发文业务信息FRAME
     * 
     * @param djid
     *            查看或者编辑
     * @return
     */
    private OptHtmlFrameInfo getDispatchDocViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();      
            viewFrameInfo.setFrameId("view2Frame");
            viewFrameInfo
                    .setFrameSrc("/dispatchdoc/dispatchDoc!viewDispatchDocInfo.do?djId="
                            + djid + "&flowInstId=" + super.curFlowInstId);       
        viewFrameInfo.setFrameLegend("拟文单");

        return viewFrameInfo;
    }

    /**
     * 查看或者编辑发文业务信息FRAME
     * 
     * @param djid
     *            查看或者编辑
     * @return
     */
    private OptHtmlFrameInfo getDispatchDocEditFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();      
            viewFrameInfo.setFrameId("view2Frame");
            viewFrameInfo
                    .setFrameSrc("/dispatchdoc/dispatchDoc!viewDispatchDocInfo.do?djId="
                            + djid + "&flowInstId=" + super.curFlowInstId+"&canEdit=T");       
        viewFrameInfo.setFrameLegend("拟文单");

        return viewFrameInfo;
    }

    /**
     * 查看或者编辑发文业务信息FRAME
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getIncomeDocFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameLegend("公文阅批单");
        viewFrameInfo.setFrameId("viewIncomeDocFrame");
        if(checkCanEdit()){
            viewFrameInfo
            .setFrameSrc("/dispatchdoc/incomeDoc!registerDoOrReadView.do?djId="
                    + djid+"&canEdit=T");

       }else{
           viewFrameInfo
           .setFrameSrc("/dispatchdoc/incomeDoc!registerDoOrReadView.do?djId="
                   + djid);
        }
        return viewFrameInfo;
    }

    /**
     * 查看或者编辑发文业务信息FRAME
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getProjectEditFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("transFrame");
        viewFrameInfo
                .setFrameSrc("/dispatchdoc/incomeDoc!registerProjectEdit.do?djId="
                        + djid
                        + "&flowInstId="
                        + super.getFlowInstId()
                        + "&nodeInstId="
                        + object.getNodeInstId()
                        + "&isRepair=" + request.getParameter("repair"));
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

        optIdeaInfo.setUnitname(fuerunit.getUnitName());
        optIdeaInfo.setModuleCode(moduleCode);// 办理意见与通用模块关联（控制环节意见显示）

        /** 获取过程日志信息：环节名称、办理意见、环节状态 start */
        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        object.setNodename(nodeInfo.getNodeName());
        object.setNodeinststate(nodeInst.getNodeState());
        optIdeaInfo.setTransidea(object.getTransidea());
        /** 获取过程日志信息：环节名称、办理意见、环节状态 end */

        optProcInfoManager.saveIdeaInfo(optIdeaInfo, object);
    }

    public void saveIdeaInfo(String optCode, String ideaCode, String transIdea,
            String transContent) {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        /** 获得用户所在部门 start */
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(loginInfo.getPrimaryUnit());
        fuerunit.setUserCode(loginInfo.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        /** 获得用户所在部门 end */
        if (fuerunit != null)
            optIdeaInfo.setUnitname(fuerunit.getUnitName());

        /** 获取过程日志信息：环节名称、办理意见、环节状态 start */
        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());

        optIdeaInfo.setDjId(object.getDjId());
        optIdeaInfo.setNodeInstId(object.getNodeInstId());
        optIdeaInfo.setNodeinststate(nodeInst.getNodeState());
        optIdeaInfo.setOptcode(optCode);
        optIdeaInfo.setNodename(nodeInfo.getNodeName());
        optIdeaInfo.setTransdate(DatetimeOpt.currentUtilDate());
        optIdeaInfo.setTranscontent(transContent);
        optIdeaInfo.setUnitcode(loginInfo.getPrimaryUnit());
        optIdeaInfo.setUsercode(loginInfo.getUsercode());
        optIdeaInfo.setIdeacode(ideaCode);
        optIdeaInfo.setTransidea(transIdea);
        optIdeaInfo.setNodeCode(nodeInfo.getNodeCode());
        optIdeaInfo.setFlowPhase(nodeInfo.getFlowPhase());
        optIdeaInfo.setModuleCode(moduleCode);// 办理意见与通用模块关联（控制环节意见显示）

        /** 获取过程日志信息：环节名称、办理意见、环节状态 end */

        optProcInfoManager.saveIdeaInfo(optIdeaInfo, null);
    }

    /**
     * 选择工作流
     * 
     * @return
     */
    public String selectFlow() {

        return this.genIncomeDocFrame();
    }

    /**
     * 办公室批分节点操作
     * 
     * @return
     */
    public String officeBranch() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        frameList.add(super.getCommonTransFrame(object.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("pfFrame");
        transFrameInfo
                .setFrameSrc("/dispatchdoc/ioDocTasksExcute!ioDocPF.do?djId="
                        + object.getDjId() + "&flowInstId="
                        + super.getFlowInstId() + "&nodeInstId="
                        + object.getNodeInstId() + "&moduleCode=" + moduleCode
                        + "&iszbfb=" + iszbfb + "&isxb=" + isxb);

        frameList.add(transFrameInfo);
        frameList.add(getIncomeDocFrame(object.getDjId()));// 基础业务信息查看
        this.setFrameList(frameList, "批分办理");
        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        return "optframe";
    }

    public String genZDFWHQHZFrame() {
       
        this.setNodeInstId(object.getNodeInstId());
        
        return  boundAllInfoframeModuleTwo(null);//置文号办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }

    private List<OptProcInfo> optProcInfos;
    private List<OptIdeaInfo> optIdeaInfolist;

    public String toDoSignTheSummary() {
        NodeInstance nit = flowEngine.getNodeInstById(object.getNodeInstId());
        NodeInstance nit_ = flowEngine.getNodeInstById(nit.getPrevNodeInstId());
        FlowNodeInfo fif = flowEngine.getNodeInfoById(nit_.getNodeId());
        optIdeaInfolist =optProcInfoManager.listIdeaLogsByDjIdAndNodeCode(object.getDjId(), fif.getNodeCode());
        return "toDoSignTheSummary";
    }


    public List<OptIdeaInfo> getOptIdeaInfolist() {
        return optIdeaInfolist;
    }

    public void setOptIdeaInfolist(List<OptIdeaInfo> optIdeaInfolist) {
        this.optIdeaInfolist = optIdeaInfolist;
    }

    public String tobyj_chooseZRWS() {
        
        this.doOpt();
        return "tobyj_chooseZRWS";
    }

    public List<OptProcInfo> getOptProcInfos() {
        return optProcInfos;
    }

    public void setOptProcInfos(List<OptProcInfo> optProcInfos) {
        this.optProcInfos = optProcInfos;
    }

    /**
     * 流程操作方法整合
     * 
     * @param actionName
     *            action名称
     * @param optMethod
     *            操作方法
     * @param nodeName
     *            节点名称:缺省情况下为流程节点名称,反之，自定义名称
     * @return
     */
    private String generalOptFrame(String actionName, String optMethod,
            String nodeName) {
        if (StringUtils.isBlank(actionName)) {
            actionName = "ioDocTasksExcute";
        }
        if (StringUtils.isBlank(optMethod)) {
            optMethod = "generCommonTrans";
        }
        if (StringUtils.isBlank(nodeName)) {
            // NodeInstance nit =
            // flowEngine.getNodeInstById(object.getNodeInstId());
            // FlowNodeInfo fif = flowEngine.getNodeInfoById(nit.getNodeId());
            // nodeName = fif.getNodeName();
        }

        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());

        frameList.add(super.getCommonTransFrame(object.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 引用通用的办理界面

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("pfFrame1");
        transFrameInfo.setFrameSrc("/dispatchdoc/" + actionName + "!"
                + optMethod + ".do?djId=" + object.getDjId() + "&flowInstId="
                + super.getFlowInstId() + "&nodeInstId="
                + object.getNodeInstId() + "&moduleCode=" + moduleCode
                + "&documentTemplateIds=" + documentTemplateIds);
        frameList.add(transFrameInfo);
        this.boundAllInfoframe(frameList, object.getDjId(), "generalOptFrame");
        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        return "optframe";
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
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        String[] userCodes = request.getParameter("userCode").split(",");
        if (userCodes != null && userCodes.length > 0) {
            flowEngine.assignFlowWorkTeam(super.getFlowInstId(), roleCode,
                    new HashSet<String>(Arrays.asList(userCodes)),
                    loginInfo.getUsercode());
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
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        String[] orgCodes = request.getParameter("orgCode").split(",");
        if (orgCodes != null && orgCodes.length > 0) {
            flowEngine.assignFlowOrganize(super.getFlowInstId(), roleCode,
                    new HashSet<String>(Arrays.asList(orgCodes)),
                    loginInfo.getUsercode());
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
            FUserDetail userDetail = ((FUserDetail) getLoginUser());
            optProcAttentionManager.deleteFlowAttentionByOptUser(
                    object.getDjId(), userDetail.getUsercode());
            if (!StringUtils.isBlank(attUserCodes)) {

                // 获取页面attUserCodes
                String[] attUserCode = attUserCodes.split(",");
                // 拆分userCode

                if (attUserCode.length > 0) {

                    for (int i = 0; i < attUserCode.length; i++) {

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

    private String iszb;
    private String iszbfb;
    private String isxb;

    /**
     * 通用批分节点操作
     * 
     * @return
     */
    public String ioDocPF() {
        setTeamMap(flowEngine.viewFlowWorkTeam(super.getFlowInstId()));
        setOrgMap(flowEngine.viewFlowOrganize(super.getFlowInstId()));

        if ("T".equals(iszbfb)) {
            request.setAttribute("unitList", sysUnitManager.listObjects());

            Set<String> orgCodeSet = flowEngine.viewFlowOrganize(
                    super.curFlowInstId, "zbcsfb");

            request.setAttribute("zbcsUnit", orgCodeSet.isEmpty() ? ""
                    : orgCodeSet.iterator().next());
        }
        FUserDetail user = ((FUserDetail) getLoginUser());

        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        if (dept != null) {

            unitsJson = sysUnitManager.getAllUnitsJSON(dept.getUnitcode());

            // FUnitinfo unit =
            // sysUnitManager.getObjectById(dept.getUnitcode());
            //
            // parentUnit = unit.getParentunit();

        } else {
            unitsJson = "{}";
        }
        /*
         * super.initUsers(); setUnitsJson(sysUnitManager.getAllUnitsJSON());
         */
        return "ioDocPF";
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
     * 负责人分工
     * 
     * @return
     */
    public String principalDivision() {

        return this.defaltTransFrame();
    }
    
    /**
     * 回撤功能改造
     * @return
     */
    public String reclaimUserTask() {
        final String userCode = ((FUserDetail) getLoginUser()).getUsercode();
        long nodeinstid = 0;
        Map<String, Object> filterMap = new HashMap<String, Object>();
        Map<String, Object> Map = new HashMap<String, Object>();
        Map<String, Object> idea = new HashMap<String, Object>();
        Map.put("nodeinstid", object.getNodeInstId());
        List<WfNodeInstance> tempList = nodeInstanceDao.listObjects(Map);
        if (!(null == tempList) && tempList.size() > 0) {
            filterMap.put("nodeId", tempList.get(0).getNodeId());
            List<WfNode> list = flowNodeDao.listObjects(filterMap);
            if (!(null == list) && list.size() > 0) {
                if ("C".equals(list.get(0).getOptType())) {
                    List<WfActionTask> action = actionTaskDao.getActionTaskByNodeidAndUser(object.getNodeInstId(),userCode);
                    if(!(null == action) && action.size() > 0){
                        WfActionTask actionTask = action.get(0);
                        actionTaskDao.deleteObject(actionTask);
                        actionTask.setTaskState("A");
                        actionTaskDao.saveNewObject(actionTask);
                    }
                    idea.put("nodeinstid", object.getNodeInstId());
                    List<OptIdeaInfo> ideas = optIdeaInfoDao.listObjects(idea);
                    optProcInfoManager.deleteObjectById(object.getNodeInstId());
                    for(OptIdeaInfo info : ideas){
                        if(!userCode.equals(info.getUsercode())){
                            optIdeaInfoDao.saveNewObject(info);
                        }
                    }
                } else if (flowManager.nodeCanBeReclaim(object.getNodeInstId())) {
                    resetFlowToThisNode(object.getNodeInstId(),
                            ((FUserDetail) getLoginUser()).getUsercode());

                    if ("E".equals(list.get(0).getOptType())) {
                        nodeinstid = tempList.get(0).getPrevNodeInstId();

                    }

                }
            }
            idea.put("usercode", userCode);
            if (0 != nodeinstid) {
                idea.put("nodeinstid", nodeinstid);
                List<OptIdeaInfo> ideas = optIdeaInfoDao.listObjects(idea);
                if(!(null == ideas) && ideas.size() > 0){
                    OptIdeaInfo ideainfo = ideas.get(0);
                    optIdeaInfoDao.deleteObject(ideainfo);
                }
                UserbizoptLog l=userbizoptLogManager.listObject(object.getDjId(), userCode, nodeinstid);
                if(null !=l){
                    userbizoptLogManager.deleteObject(l);
                }
                resetFlowToThisNode(nodeinstid,
                        ((FUserDetail) getLoginUser()).getUsercode());
            } else {
                idea.put("nodeinstid", object.getNodeInstId());
                List<OptIdeaInfo> ideas = optIdeaInfoDao.listObjects(idea);
                if(!(null == ideas) && ideas.size() > 0){
                    OptIdeaInfo ideainfo = ideas.get(0);
                    optIdeaInfoDao.deleteObject(ideainfo);
                }
                UserbizoptLog l=userbizoptLogManager.listObject(object.getDjId(), userCode, object.getNodeInstId());
                if(null !=l){
                    userbizoptLogManager.deleteObject(l);
                }
            }
            if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
                //重新生成pdf
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
                         //发文
                        if(bizType == 1){
                             formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(contextPath,userCode, object.getDjId(), String.valueOf(curFlowInstId));
                        }
                        //收文
                        if(bizType == 2){
                             formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath, userCode, object.getDjId());
                        }
                        createPDF(object.getDjId(), object.getNodeInstId(),userCode,exePath,formHtmlUrl);
                    }
                });
            }

        }
        
        return listUserCompTask();
    }
    
    /**
     * 从这个节点重新运行该流程，包括已经结束的流程
     */
    public long resetFlowToThisNode(long nodeInstId, String mangerUserCode) {
        
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        WfNodeInstance thisnode = nodeInstanceDao.getObjectById(nodeInstId);
        if (thisnode == null)
            return -1;
        // 当前节点状态必需不能为正常，如果是正常则没有必要重置
        if ("N".equals(thisnode.getNodeState()))
            return -6;

        WfFlowInstance flow = flowInstanceDao.getObjectById(thisnode
                .getFlowInstId());
        if (flow == null)
            return -2;
        WfNode nodedef = flowNodeDao.getObjectById(thisnode.getNodeId());
        if ("A".equals(nodedef.getNodeType())
                || "F".equals(nodedef.getNodeType())) {
            // 不能设定到开始或者结束节点
            return -5;
        }
        if(!"G".equals(nodedef.getNodeType())){
            
            for (WfNodeInstance nodeInst : flow.getWfNodeInstances()) {
                if (("N".equals(nodeInst.getNodeState())
                        || "R".equals(nodeInst.getNodeState()) || "W"
                        .equals(nodeInst.getNodeState()))
                        && (nodeInst.getNodeId().equals(thisnode.getNodeId()))
                        && nodeInst.getRunToken().equals(thisnode.getRunToken())) {
                    // 已经有一个正在运行的相同节点实例，不能重置到该节点
                    return -6;
                }
            }                                        
        }
        

        flow.setInstState("N");
        flow.setLastUpdateTime(new Date(System.currentTimeMillis()));
        flow.setLastUpdateUser(mangerUserCode);
        // 将所有的下层正常节点都设置为 B 已回退
        String thisToken = thisnode.getRunToken();
        Date updateTime = DatetimeOpt.currentUtilDate();
        //收文多实例特殊处理，回退会把多实例其他节点一同回退
        if(flow.getFlowOptTag().contains("SW")){
            for (WfNodeInstance nodeInst : flow.getWfNodeInstances()) {
                String currToken = nodeInst.getRunToken();
                if (("N".equals(nodeInst.getNodeState())
                        || "R".equals(nodeInst.getNodeState()) || "W"
                        .equals(nodeInst.getNodeState()))
                        && nodeInst.getPrevNodeInstId().equals(nodeInstId)) {
                    
                    if ("W".equals(nodeInst.getNodeState())) { // 结束子流程
                        WfFlowInstance subFlowInst = flowInstanceDao
                                .getObjectById(nodeInst.getSubFlowInstId());
                        if (subFlowInst != null) {
                            SampleFlowUtils.endInstance(subFlowInst, "F",
                                    mangerUserCode, flowInstanceDao);
                            subFlowInst.setLastUpdateUser(mangerUserCode);
                            flowInstanceDao.saveObject(subFlowInst);
                        }
                    }
                    nodeInst.setNodeState("B");
                    // 设置最后更新时间和更新人
                    nodeInst.setLastUpdateUser(mangerUserCode);
                    nodeInst.setLastUpdateTime(updateTime);
                    WfActionLog wfactlog = SampleFlowUtils.createActionLog("E",
                            mangerUserCode, nodeInstId);
                    wfactlog.setActionId(actionLogDao.getNextActionId());
                    nodeInst.addWfActionLog(wfactlog);
                    
                    WfNode nodedefnew = flowNodeDao.getObjectById(nodeInst.getNodeId());
                    incomeProjectManager.deleteFlowWorkTeam(thisnode.getFlowInstId(), nodedefnew.getRoleCode(), fuser.getUsercode());
                }
            }
        }else{
            
            for (WfNodeInstance nodeInst : flow.getWfNodeInstances()) {
                String currToken = nodeInst.getRunToken();
                if (("N".equals(nodeInst.getNodeState())
                        || "R".equals(nodeInst.getNodeState()) || "W"
                        .equals(nodeInst.getNodeState()))
                        && currToken != null
                        && thisToken != null
                        && (currToken.equals(thisToken)
                                || currToken.startsWith(thisToken + '.') || thisToken
                                .startsWith(currToken + '.'))) {
                    
                    if ("W".equals(nodeInst.getNodeState())) { // 结束子流程
                        WfFlowInstance subFlowInst = flowInstanceDao
                                .getObjectById(nodeInst.getSubFlowInstId());
                        if (subFlowInst != null) {
                            SampleFlowUtils.endInstance(subFlowInst, "F",
                                    mangerUserCode, flowInstanceDao);
                            subFlowInst.setLastUpdateUser(mangerUserCode);
                            flowInstanceDao.saveObject(subFlowInst);
                        }
                    }
                    nodeInst.setNodeState("B");
                    // 设置最后更新时间和更新人
                    nodeInst.setLastUpdateUser(mangerUserCode);
                    nodeInst.setLastUpdateTime(updateTime);
                    WfActionLog wfactlog = SampleFlowUtils.createActionLog("E",
                            mangerUserCode, nodeInstId);
                    wfactlog.setActionId(actionLogDao.getNextActionId());
                    nodeInst.addWfActionLog(wfactlog);
                }
            }
        }
        // 创建新节点
        long lastNodeInstId = nodeInstanceDao.getNextNodeInstId();
        WfNodeInstance nextNodeInst = flow.newWfNodeInstance();
        nextNodeInst.copyNotNullProperty(thisnode);
        nextNodeInst.setNodeInstId(lastNodeInstId);
        nextNodeInst.setNodeState("N");
        nextNodeInst.setTaskAssigned("F");
        nextNodeInst.setLastUpdateUser(mangerUserCode);
        nextNodeInst.setLastUpdateTime(updateTime);

        for (WfActionTask task : thisnode.getWfActionTasks()) {
            if ("T".equals(task.getIsvalid())) {
                WfActionTask newtask = SampleFlowUtils.createActionTask(
                        task.getUserCode(), nextNodeInst, nodedef);
                newtask.setTaskId(actionTaskDao.getNextTaskId());
                // 要判断 过期时间的问题
                nextNodeInst.addWfActionTask(newtask);
            }
        }

        flow.addWfNodeInstance(nextNodeInst);
        flowInstanceDao.saveObject(flow);

        WfManageAction managerAct = SampleFlowUtils.createManagerAction(
                flow.getFlowInstId(), mangerUserCode, "R");
        managerAct.setActionId(manageActionDao.getNextManageId());
        managerAct.setAdminDesc("node:" + nodeInstId);
        manageActionDao.saveObject(managerAct);

        return lastNodeInstId;
    }
    
    /**
     * 主办承办人受理
     * 
     * @return
     */
    public String registrarAccept() {

        // super.initTemplateFromNode();
        return this.defaltTransFrame();
    }

    /**
     * 主办承办人办理
     * 
     * @return
     */
    public String thereunderFor() {

        return this.defaltTransFrame();
    }

    /**
     * 负责人审核
     * 
     * @return
     */
    public String managerVerify() {

        return this.defaltTransFrame();
    }

    /**
     * 暂缓处理
     * 
     * @return
     */
    public String suspendNodeInstance() {
        flowManager.suspendNodeInstance(object.getNodeInstId(),
                ((FUserDetail) getLoginUser()).getUsercode());
        saveIdeaInfo("suspend", "suspend", "暂缓", object.getTranscontent());
        // return "toDispatchDocTask";
        return "refreshTasks";
    }
    public String deleteFlowBtnInstance() {
        if(StringUtils.isNotBlank(object.getDjId())){
            deleteObjectBanInfo(object.getDjId());
        }
        return "refreshTasks";
    }
    
    public void deleteObjectBanInfo(String djId) {
        optApplyManager.deleteObjectBanInfo(djId);
    }
    
    /**
     * 回退节点
     * 
     * @return
     */
    public String rollBackOpt() {
        flowManager.rollbackOpt(object.getNodeInstId(),
                ((FUserDetail) getLoginUser()).getUsercode());
        saveIdeaInfo("rollback", "rollback", "回退", object.getTranscontent());
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
        saveIdeaInfo("endflow", "endflow", "终止", object.getTranscontent());
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
     * 默认办理方法，某个步骤没有开发完成，方便进入下一个节点。防止步骤分工开发后的阻塞
     * 
     * @return
     */
    private String defaltTransFrame() {
        OptHtmlFrameInfo transFrame = new OptHtmlFrameInfo();
        transFrame.setFrameId("transFrame");
        transFrame
                .setFrameSrc("/punish/punishTasksExecute!defaultTrans.do?djId="
                        + object.getDjId() + "&flowInstId="
                        + super.getFlowInstId() + "&nodeInstId="
                        + object.getNodeInstId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        frameList.add(transFrame);
        this.setFrameList(frameList, "默认办理");
        return "optframe";
    }

    /**
     * 办（阅办）件 流程相关主流程入口
     * 
     */

    /**
     * 办阅_办公室分办
     * 
     * @return
     */
    public String byj_officeBranch() {
        // this.ioDocPF();
        return this.byj_generalOptFrame("incomeDoc", "byj_officeBranch",
                "办阅_办公室分办");
    }

    
    public String ioDocLeader(){
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
      
        this.setNodeInstId(object.getNodeInstId());
        
        /*request.setAttribute("sqdjId", optBaseInfo.getDjId());
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        frameList= getBaseInfoIframe( frameList,optBaseInfo.getDjId());//基本信息（业务信息+办理信息）
        
        frameList.add(super.getCommonStuffFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase()));// 附件
        
        frameList.add(super.getCommonTransFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(),"ioDocLeaderTransInfo"));// 引用通用的办理界面
        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
                "genDispatchDocFrame");

        super.initFlowTime();
        // 加载该环节是否存在督办信息
        this.dcdbInfo(object.getNodeCode());
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        // 公文收藏
        this.getCollsatus();*/
        
        
        return  boundAllInfoframeModuleTwo(super.getCommonTransFrame(optBaseInfo.getDjId(),
                object.getNodeCode(), object.getFlowPhase(),"ioDocLeaderTransInfo"));//置文号办理页面--后续功能逻辑请在boundAllInfoframeModuleTwo中添加;
    }
    
    public String ioDocLeaderTransInfo(){
        this.doOpt();
        return "ioDocLeaderTransInfo";
    }
    /**
     * 会签汇总
     * 
     * @return
     */
    public String byj_signTheSummary() {
        return this.byj_generalOptFrame("ioDocTasksExcute",
                "toDoSignTheSummary", "会签汇总");
    }

    /**
     * 流程操作方法整合
     * 
     * @param actionName
     *            action名称
     * @param optMethod
     *            操作方法
     * @param nodeName
     *            节点名称:缺省情况下为流程节点名称,反之，自定义名称
     * @return
     */
    private String byj_generalOptFrame(String actionName, String optMethod,
            String nodeName) {
        if (StringUtils.isBlank(actionName)) {
            actionName = "ioDocTasksExcute";
        }
        if (StringUtils.isBlank(optMethod)) {
            optMethod = "generCommonTrans";
        }
        if (StringUtils.isBlank(nodeName)) {
            NodeInstance nit = flowEngine.getNodeInstById(object
                    .getNodeInstId());
            FlowNodeInfo fif = flowEngine.getNodeInfoById(nit.getNodeId());
            nodeName = fif.getNodeName();
        }

        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super
                .getFlowInstId());
        if (optBaseInfo == null) {
            this.postAlertMessage("操作失败，原因可能是办件不存在！", response);
            return null;
        }
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        this.setNodeInstId(object.getNodeInstId());

        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("transFrame");
        transFrameInfo.setFrameSrc("/dispatchdoc/" + actionName + "!"
                + optMethod + ".do?djId=" + optBaseInfo.getDjId()
                + "&flowInstId=" + super.getFlowInstId() + "&nodeInstId="
                + object.getNodeInstId() + "&moduleCode=" + moduleCode
                + "&documentTemplateIds=" + documentTemplateIds + "&iszbfb="
                + iszbfb + "&isxb=" + isxb + "&nodeCode="
                + object.getNodeCode() + "&flowPhase=" + object.getFlowPhase());

        frameList.add(transFrameInfo);
        this.boundAllInfoframe(frameList, optBaseInfo.getDjId(),
                "genDispatchDocFrame");

        // 配置启动事权或者发文的开关
        this.setFrameList(frameList, nodeName);     
        super.initFlowTime();
        moduleParam = generalModuleParamManager.getObjectById(moduleCode);
        // 配置启动事权或者发文的开关
        request.setAttribute("sqdjId", optBaseInfo.getDjId());
        return "optframe";
    }

    private OptHtmlFrameInfo selectPF(String djId) {
        this.setNodeInstId(object.getNodeInstId());
        OptHtmlFrameInfo transFrameInfo = new OptHtmlFrameInfo();
        transFrameInfo.setFrameId("pfFrame");
        transFrameInfo
                .setFrameSrc("/dispatchdoc/ioDocTasksExcute!ioDocPF.do?djId="
                        + djId + "&flowInstId=" + super.getFlowInstId()
                        + "&nodeInstId=" + object.getNodeInstId()
                        + "&moduleCode=" + moduleCode + "&iszbfb=" + iszbfb
                        + "&isxb=" + isxb);
        initUsers();
        request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON());
        return transFrameInfo;
    }

    private OptHtmlFrameInfo getIncomeDocFrame2(String djId) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo
                .setFrameSrc("/dispatchdoc/incomeDoc!viewIncomeDoc.do?djId="
                        + djId);
        viewFrameInfo.setFrameLegend("登记信息");
        return viewFrameInfo;
    }

    /**
     * 
     * 办（阅办）件 流程相关主流程结束
     */

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
    

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
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

    public String getIszb() {
        return iszb;
    }

    public void setIszb(String iszb) {
        this.iszb = iszb;
    }

    public String getIsxb() {
        return isxb;
    }

    public void setIsxb(String isxb) {
        this.isxb = isxb;
    }

    public String getIszbfb() {
        return iszbfb;
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }



    public void setIszbfb(String iszbfb) {
        this.iszbfb = iszbfb;
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

  


    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public void setSignedReportManager(SignedReportManager signedReportManager) {
        this.signedReportManager = signedReportManager;
    }

    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public void setOaBizBindInfoManager(
            OaBizBindInfoManager oaBizBindInfoManager) {
        this.oaBizBindInfoManager = oaBizBindInfoManager;
    }

    public void setOptProcCollectionManager(
            OptProcCollectionManager optProcCollectionManager) {
        this.optProcCollectionManager = optProcCollectionManager;
    }

    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }

    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }
    public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
        this.optPdfInfoManager = optPdfInfoManager;
    }
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

    public OptApplyManager getOptApplyManager() {
        return optApplyManager;
    }

    public void setOptApplyManager(OptApplyManager optApplyManager) {
        this.optApplyManager = optApplyManager;
    }

    public WfNodeInstanceDao getNodeInstanceDao() {
        return nodeInstanceDao;
    }

    public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
        this.nodeInstanceDao = nodeInstanceDao;
    }

    public WfFlowInstanceDao getFlowInstanceDao() {
        return flowInstanceDao;
    }

    public void setFlowInstanceDao(WfFlowInstanceDao flowInstanceDao) {
        this.flowInstanceDao = flowInstanceDao;
    }

    public List<NodeInstance> getUserCompNodesList() {
        return userCompNodesList;
    }

    public void setUserCompNodesList(List<NodeInstance> userCompNodesList) {
        this.userCompNodesList = userCompNodesList;
    }

    public WfNodeDao getFlowNodeDao() {
        return flowNodeDao;
    }

    public void setFlowNodeDao(WfNodeDao flowNodeDao) {
        this.flowNodeDao = flowNodeDao;
    }

    public OptIdeaInfoDao getOptIdeaInfoDao() {
        return optIdeaInfoDao;
    }

    public void setOptIdeaInfoDao(OptIdeaInfoDao optIdeaInfoDao) {
        this.optIdeaInfoDao = optIdeaInfoDao;
    }

    public WfActionLogDao getActionLogDao() {
        return actionLogDao;
    }

    public void setActionLogDao(WfActionLogDao actionLogDao) {
        this.actionLogDao = actionLogDao;
    }

    public WfManageActionDao getManageActionDao() {
        return manageActionDao;
    }

    public void setManageActionDao(WfManageActionDao manageActionDao) {
        this.manageActionDao = manageActionDao;
    }
    public VuserTaskListOAManager getVuserTaskListOAManager() {
        return vuserTaskListOAManager;
    }

    public void setVuserTaskListOAManager(
            VuserTaskListOAManager vuserTaskListOAManager) {
        this.vuserTaskListOAManager = vuserTaskListOAManager;
    }
    

    
}
