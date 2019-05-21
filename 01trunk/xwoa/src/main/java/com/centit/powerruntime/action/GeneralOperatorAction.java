package com.centit.powerruntime.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import sun.misc.BASE64Decoder;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.efile.mgt.EfileManager;
import com.centit.efile.model.EfileStore;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.service.OaMeetingmaterialinfosManager;
import com.centit.powerruntime.optmodel.PowerRuntimeEntityAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcAttentionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.Suppowerstuffgroup;
import com.centit.powerruntime.po.Suppowerstuffinfo;
import com.centit.powerruntime.po.VOptShowIdeaInfo;
import com.centit.powerruntime.po.VOptStuffInfo;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.powerruntime.service.SuppowerstuffgroupManager;
import com.centit.powerruntime.service.SuppowerstuffinfoManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.util.JsonUtil;
import com.centit.webservice.util.PDFUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.VNodeInstDetail;
import com.itextpdf.text.pdf.PdfReader;

/**
 * 
 * 通用的业务操作类，其中包括一下功能 1，获取某个业务的所有操作环节 2，获得某个业务的所有附件 3，上传附件（可以内嵌到通用的业务操作中）
 * 4，通用的业务操作页面、保存和提交
 * 
 * @author codefan
 * @create 2012-6-27
 * @version
 */
public class GeneralOperatorAction extends
        PowerRuntimeEntityAction<OptProcInfo> implements ServletResponseAware {
    private static final Log log = LogFactory
            .getLog(GeneralOperatorAction.class);
    private static final long serialVersionUID = 1L;
    private GeneralModuleParamManager generalModuleParamMag;
    private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;

    private OptBaseInfoManager optBaseInfoManager;
    private SuppowerstuffgroupManager suppowerstuffgroupManager;
    private SuppowerstuffinfoManager suppowerstuffinfoManager;
    private OptApplyManager optApplyManager;
    private DispatchDocManager dispatchDocManager;
    private OptStuffInfoManager optStuffInfoManager;
    private FlowDefine flowDefine;
    
    private  FlowEngine flowEngine;
    
    private String roleCode;
    private String unitsJson;
    private String optBaseInfoJson;

    private List<FlowDescribe> fwList;
    private List<FlowDescribe> swList;
    private List<Map<String, String>> year;
    private List<FUnitinfo> unitinfos;
    
    protected String exportFileName;
    
    private OptProcInfoManager optProcInfoManager;//办件过程信息
    
    public void setSuppowerstuffinfoManager(
            SuppowerstuffinfoManager suppowerstuffinfoManager) {
        this.suppowerstuffinfoManager = suppowerstuffinfoManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public void setGeneralModuleParamMag(
            GeneralModuleParamManager generalModuleParamMag) {
        this.generalModuleParamMag = generalModuleParamMag;
    }
    
    public GeneralOperatorAction() {
        object = new OptProcInfo();
    }

    public void setOptProcInfoManager(OptProcInfoManager basemgr) {
        optProcInfoManager = basemgr;
        this.setBaseEntityManager(optProcInfoManager);
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    /**
     * 通用批分节点操作
     * 
     * @return
     */
    public String ioDocPF() {
        setTeamMap(flowEngine.viewFlowWorkTeam(super.getFlowInstId()));
        setOrgMap(flowEngine.viewFlowOrganize(super.getFlowInstId()));
        /*
         * super.initUsers(); setUnitsJson(sysUnitManager.getAllUnitsJSON());
         */
        return "ioDocPF";
    }
    /**
     * 页面选择部门
     * @return
     */
    public String listSelect() {
        try {
            unitinfos = sysUnitManager.listObjects();
            totalRows = objList.size();
            return "listSelect";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 通用置文号节点操作
     * 
     * @return
     */
    // public String ioDocZwh() {
    // DispatchDoc dis = dispatchDocManager.getObjectById(object.getDjId());
    // String fwh = dis.getDispatchDocNo();
    // String wjlx = dis.getDispatchFileType();
    // String lsh = dis.getDjId();
    //
    // year = getFromArray();
    //
    // return "ioDocZwh";
    // }

    public String assignWorkGroup() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        String[] userCodes = request.getParameter("userCode").split(",");
        if (userCodes != null && userCodes.length > 0) {
            flowEngine.assignFlowWorkTeam(super.getFlowInstId(), roleCode,
                    new HashSet<String>(Arrays.asList(userCodes)),
                    loginInfo.getUsercode());
        }
        // request.setAttribute("flowInstId", super.getFlowInstId());
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
        // request.setAttribute("flowInstId", super.getFlowInstId());
        return "ListWorkGroup";
    }

    public List<FUnitinfo> getUnitinfos() {
        return unitinfos;
    }

    public void setUnitinfos(List<FUnitinfo> unitinfos) {
        this.unitinfos = unitinfos;
    }

    public String deleteFlowOrganize() {
        log.info("nodeInstId:" + object.getNodeInstId());
        flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
        return "ListWorkGroup";
    }

    public String deleteFlowOrganizeUnit() {
        flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode,
                request.getParameter("orgCode"));
        return "ListWorkGroup";
    }

    /**
     * 获取通用模块参数 ，以供生成通用模块操作界面
     * 
     * @return
     */
    public String doOpt() {

        try {
            moduleParam = generalModuleParamMag.getObjectById(moduleCode);// "XKSL"
            extractFlowOptParam();
            object = optProcInfoManager.getObjectByNodeInstId(curNodeInstId);

            // 判断是 新建 还是更新
            if (object == null)
                object = new OptProcInfo();

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
            if (moduleParam != null&&moduleParam.getHasStuff() != null
                    && moduleParam.getHasStuff().equals("T")) {
                OptBaseInfo optBaseInfo = optBaseInfoManager
                        .getObjectById(object.getDjId());
                if (optBaseInfo != null) {
                    moduleParam.setPowerId(optBaseInfo.getPowerid());
                }
            }

            /**
             * 事权办理过程 如果有文书编辑的，设置文书模板编号
             */
            if (moduleParam != null && moduleParam.getHasDocument() != null
                    && "T".equals(moduleParam.getHasDocument())) {
                NodeInstance nodeInstance=flowEngine.getNodeInstById(object.getNodeInstId());
                OptProcInfo procInfo=optProcInfoManager.getObjectByNodeInstId(nodeInstance.getPrevNodeInstId());
                if(procInfo!=null){
                    object.setRecordId(procInfo.getRecordId()); 
                    object.setArchiveType(procInfo.getArchiveType());
                }                                

            }
            optBaseInfoJson = optApplyManager.getJSONDocumentNames(object
                    .getDjId());
            /**
             * 根据是否可以生产公文 ，确定需要编辑的文档模板
             */
            super.initTemplateConfig();
            /**
             * 多模板情况加载
             */
            super.initTemplateFromNode();

        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "通用配置功能出错，请检查各配置项是否准确。");
            return ERROR;
        }
        return "optForm";
    }

    /**
     * 保存操作结果
     * 
     * @return
     */
    public String saveOpt() {
        // 添加保存操作
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        object.setTransdate(new Date(System.currentTimeMillis()));
        object.setUsercode(loginInfo.getUsercode());
        object.setUnitcode(loginInfo.getPrimaryUnit());

        this.setNodeInstId(object.getNodeInstId());

        log.info("NodeInstId:" + object.getNodeInstId());
        NodeInstance nodeInst = flowEngine.getNodeInstById(object
                .getNodeInstId());
        FlowNodeInfo nodeInfo = flowEngine
                .getNodeInfoById(nodeInst.getNodeId());
        object.setNodename(nodeInfo.getNodeName());
        object.setNodeinststate(nodeInst.getNodeState());

        // 存在风险点的信息:风险类别、风险描述、风险内控手段与结果
        if (StringUtils.isNotBlank(object.getRisktype())) {
            object.setIsrisk("T");// 存在
        } else {
            object.setIsrisk("F");// 不存在
        }

        object.setRecordId(object.getRecordId());
        optProcInfoManager.saveObject(object);

        // 保存关注人员
        saveAtt();
        saveTeamRolepeople();
        return "refreshTasks";
    }

    /**
     * 提交操作结果
     * 
     * @return
     */
    public String submitOpt() {
        saveOpt();

        submitNode();
        // 保存过程日志信息
        saveIdeaInfo();

        return "refreshTasks";
    }

    /**
     * 暂缓处理
     * 
     * @return
     */
    public String suspendNodeInstance() {
        flowManager.suspendNodeInstance(object.getNodeInstId(),
                ((FUserDetail) getLoginUser()).getUsercode());
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
        return "refreshTasks";
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
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

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

    // 在这儿 可能还需要 添加 暂停计时、挂起流程和终止流程的方法
    /**
     * -------------------------------------------------------------------------
     * 获取流程操作日志
     */

    List<OptIdeaInfo> ideaLogs;// 各个操作节点
    List<VOptShowIdeaInfo> showIdealogs;//需要显示环节意见的操作节点

    public List<VOptShowIdeaInfo> getShowIdealogs() {
        return showIdealogs;
    }

    public void setShowIdealogs(List<VOptShowIdeaInfo> showIdealogs) {
        this.showIdealogs = showIdealogs;
    }

    public List<OptIdeaInfo> getIdeaLogs() {
        return ideaLogs;
    }

    public void setIdeaLogs(List<OptIdeaInfo> ideaLogs) {
        this.ideaLogs = ideaLogs;
    }

    public String listIdeaLogs() {

        ideaLogs = optProcInfoManager.listIdeaLogsByDjId(object.getDjId());
        return "listIdeaLogs";
    }
    
    public String listIdeaLogDetails(){
        
        ideaLogs = optProcInfoManager.listIdeaLogsByDjId(object.getDjId());
        return "listIdeaLogDetails";
    }
    
    /**
     * 导出办件过程详细信息excel表
     * @return
     */
    public void exportIdeaLogs() throws IOException{
        
        ideaLogs = optProcInfoManager.listIdeaLogsByDjId(object.getDjId());
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
        
        // 获取需要生成Excel的数据
        if (ideaLogs != null && !ideaLogs.isEmpty()) {
            for (OptIdeaInfo o : ideaLogs) {
                Object[] temp = new Object[6];
                temp[0] = o.getNodename();
                temp[1] = CodeRepositoryUtil.getValue("unitcode2JC", o.getUnitcode());
                temp[2] = o.getUsername();
                temp[3] = DatetimeOpt.convertDateToString(o.getTransdate(), "yyyy-MM-dd hh:mm");
                temp[4] = o.getTransidea();
                temp[5] = o.getTranscontent();
                chosedList.add(temp);
            }
        }
        
        String[] header = { "环节名称", "部门名称 ", "办理人员姓名","办理时间", "办理决定","办理意见"};  // 表头
        String titleName = "办件过程详细信息";
        BizCommUtil.doPoi2Excel(titleName, header, chosedList);
        
    }
    
    /**
     * 显示环节办理意见
     * @return
     */
    public String listShowIdeaLogs() {

        showIdealogs = optProcInfoManager.listShowIdeaLogsByDjId(object.getDjId());
        return "showListIdeaLogs";
    }
    
    /**
     * 是否显示办理意见iframe
     * @param djId
     * @return
     */
    public  boolean isShowIdeaLogs(String djid) {

        showIdealogs = optProcInfoManager.listShowIdeaLogsByDjId(djid);
        
        return showIdealogs == null || showIdealogs.size()<=0 ? false :true ;
    }
    
    public static OptHtmlFrameInfo getIdeaListFrame(String djId) {
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        procFrameInfo.setFrameId("procFrame");
        procFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!listIdeaLogs.do?djId="
                        + djId);
        procFrameInfo.setFrameHeight("300px");
        // frameMap.put("proc", procFrameInfo);
        return procFrameInfo;
    }
    
    public static OptHtmlFrameInfo getShowIdeaListFrame(String djId) {
        OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
        procFrameInfo.setFrameId("frm_showIdeas");
        procFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!listShowIdeaLogs.do?djId="
                        + djId);
        procFrameInfo.setFrameHeight("300px");
        // frameMap.put("proc", procFrameInfo);
        return procFrameInfo;
    }

    /**
     * -------------------------------------------------------------------------
     * 对附件的操作
     */
    private OptStuffInfo stuffInfo;

    private List<Suppowerstuffinfo> suppowerstuffinfos;
    /**
     * 附件材料文件，供上传使用
     */
    private File stuffFile;

    private String stuffFileFileName;

    public String getStuffFileFileName() {
        return stuffFileFileName;
    }

    public void setStuffFileFileName(String stuffFileFileName) {
        this.stuffFileFileName = stuffFileFileName;
    }

    /**
     * 材料下载使用
     */
    private InputStream stuffStream;

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public File getStuffFile() {
        return stuffFile;
    }

    public void setStuffFile(File stuffFile) {
        this.stuffFile = stuffFile;
    }

    public OptStuffInfo getStuffInfo() {
        return stuffInfo;
    }

    public void setStuffInfo(OptStuffInfo stuffInfo) {
        this.stuffInfo = stuffInfo;
    }

    public String downloadStuff() {
        OptStuffInfo stuffObj = optProcInfoManager.getStuffById(stuffInfo
                .getStuffid());
        if (stuffObj != null)
            stuffInfo = stuffObj;

        try {
            String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
            setStuffStream(new FileInputStream(absolutePath));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        // 用户下载 附件材料
        return "download";
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }

    /**
     * -------------------------------------------------------------------------
     * 保存附件
     */
    public String saveStuff() {
        try {
            FUserDetail loguser = ((FUserDetail) getLoginUser());
            if ("1".equals(stuffInfo.getIszhi())) {
                stuffInfo.setStuffid(getUUID());

                stuffInfo.setUploadtime(new Date());
                stuffInfo.setUploadusercode(loguser.getUsercode());
                stuffInfo.setFilename(stuffInfo.getStuffname() + "[纸质]");

                VNodeInstDetail o = flowManager
                        .getVNodeInstDetailbyNode(stuffInfo.getNodeInstId());
                if (o != null) {
                    stuffInfo.setNodename(o.getNodeName());
                } else {
                    stuffInfo.setNodename("办件登记");
                }
                optProcInfoManager.saveStuffInfo(stuffInfo);
            }
            if (stuffInfo.getIszhi() == null && stuffFile == null) {
                // 删除纸质项
                OptStuffInfo optStuffInfo = optStuffInfoManager
                        .getObjectById_SortId(stuffInfo.getDjId(),
                                stuffInfo.getSortId());
                optStuffInfoManager.deleteObject(optStuffInfo);
                // optProcInfoManager.deleteStuffByiszhi(stuffInfo.getSortId());
            } else if (stuffFile != null) {
                String type = stuffInfo.getDjId().replaceAll("\\d+", "").toLowerCase();
                //路径规则，流程附件基本路径+办件类型+业务id
                String dirPath = SysParametersUtils.getWorkflowAffixHome() + File.separator + type + File.separator + stuffInfo.getDjId();
                EfileStore efileStore = new EfileStore(stuffFile, stuffFileFileName,  dirPath);
                
                EfileManager.store(efileStore);
                
                /*收文ceb处理开始*/
                 if("SW".equalsIgnoreCase(type) && "ceb".equals(efileStore.getExtName(false))){
                     //ceb文件路径
                     String exePath = request.getSession().getServletContext().getRealPath("/upload/tools/c2pfree/UIConsole.exe");
                     try{
                         //转换pdf
                         String pdfPath = PDFUtil.convertCebToPdf(exePath, efileStore.getAbsolutePath(), efileStore.getStoreDir());
                         if(StringUtils.isNotBlank(pdfPath)){
                             efileStore.setStoreName(efileStore.getStoreName().replaceAll("\\.ceb", ".pdf"));
                             stuffFileFileName = stuffFileFileName.replaceAll("\\.ceb", ".pdf");
                         }
                     }catch(Exception e){
                         log.error(e.getMessage()); 
                     }
                  
                    
                 }
                /*收文ceb处理结束*/
                //只保存相对路径
                stuffInfo.setStuffpath(efileStore.getAbsolutePath().replace(SysParametersUtils.getWorkflowAffixHome(), ""));
                stuffInfo.setStuffid(getUUID());
                stuffInfo.setFilename(stuffFileFileName);
                stuffInfo.setAttachsize(String.valueOf(stuffFile.length()));
                stuffInfo.setUploadtime(new Date());
                stuffInfo.setUploadusercode(loguser.getUsercode());

                VNodeInstDetail o = flowManager
                        .getVNodeInstDetailbyNode(stuffInfo.getNodeInstId());
                if (o != null) {
                    stuffInfo.setNodename(o.getNodeName());
                } else {
                    stuffInfo.setNodename("办件登记");
                }
                optProcInfoManager.saveStuffInfo(stuffInfo);
            }
            return "gotosqcl";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    public String saveStuffOfBookPage() {
       this.saveStuff();
      return "gotosqclOfBookPage";
    }
    public String saveCFStuff() {
        try {
            FUserDetail loguser = ((FUserDetail) getLoginUser());
            if ("1".equals(stuffInfo.getIszhi())) {
                stuffInfo.setStuffid(getUUID());

                stuffInfo.setUploadtime(new Date());
                stuffInfo.setUploadusercode(loguser.getUsercode());
                stuffInfo.setFilename(stuffInfo.getStuffname() + "[纸质]");
                stuffInfo.setIsuse("0");
                VNodeInstDetail o = flowManager
                        .getVNodeInstDetailbyNode(stuffInfo.getNodeInstId());
                if (o != null) {
                    stuffInfo.setNodename(o.getNodeName());
                } else {
                    stuffInfo.setNodename("行政处罚登记");
                }
                optProcInfoManager.saveStuffInfo(stuffInfo);
            }
            if (stuffInfo.getIszhi() == null && stuffFile == null) {
                optProcInfoManager.deleteStuffByiszhi(stuffInfo.getSortId());
            } else if (stuffFile != null) {
                String type = stuffInfo.getDjId().replaceAll("\\d+", "").toLowerCase();
                //路径规则，流程附件基本路径+办件类型+业务id
                String dirPath = SysParametersUtils.getWorkflowAffixHome() + File.separator + type + File.separator + stuffInfo.getDjId();
                EfileStore efileStore = new EfileStore(stuffFile, stuffFileFileName,  dirPath);
                
                EfileManager.store(efileStore);
                //只保存相对路径
                stuffInfo.setStuffpath(efileStore.getAbsolutePath().replace(SysParametersUtils.getWorkflowAffixHome(), ""));
                stuffInfo.setStuffid(getUUID());
                stuffInfo.setFilename(stuffFileFileName);
                stuffInfo.setAttachsize(String.valueOf(stuffFile.length()));
                stuffInfo.setUploadtime(new Date());
                stuffInfo.setUploadusercode(loguser.getUsercode());
                stuffInfo.setIsuse("0");
                VNodeInstDetail o = flowManager
                        .getVNodeInstDetailbyNode(stuffInfo.getNodeInstId());
                if (o != null) {
                    stuffInfo.setNodename(o.getNodeName());
                } else {
                    stuffInfo.setNodename("行政处罚登记");
                }
                optProcInfoManager.saveStuffInfo(stuffInfo);
            }
            return "gotoCFsqcl";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    private String isAllsc; // 是否全部上传

    public String getIsAllsc() {
        return isAllsc;
    }

    public void setIsAllsc(String isAllsc) {
        this.isAllsc = isAllsc;
    }
     private String uc;
  

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String gotosqcl() {
      //当前登录人员是否是附件上传者
        FUserDetail user = (FUserDetail) getLoginUser();
        uc=user.getUsercode();//与页面材料数据循环比较

        suppowerstuffinfos = suppowerstuffinfoManager
                .getinfosByGroupId(stuffInfo.getGroupid());
        optStuffs = optProcInfoManager.listStuffsByDjId(stuffInfo.getDjId());
        isAllsc = isAllSC(suppowerstuffinfos, optStuffs);
      
        return "sqclList";
    }
    
    /**
     * 用于首页初次登记时候上传的附件控件
     * @return
     */
    public String gotosqclOfBookPage() {
        //当前登录人员是否是附件上传者
          FUserDetail user = (FUserDetail) getLoginUser();
          uc=user.getUsercode();//与页面材料数据循环比较

          suppowerstuffinfos = suppowerstuffinfoManager
                  .getinfosByGroupId(stuffInfo.getGroupid());
          Map<String, Object> paramMap = new HashMap<String, Object>();
          paramMap.put("djId", stuffInfo.getDjId());
          paramMap.put("nodeinstid", stuffInfo.getNodeInstId());
          optStuffs = optStuffInfoManager
                  .listObjects(paramMap);
          isAllsc = isAllSC(suppowerstuffinfos, optStuffs);
        
          return "sqclListOfBookPage";
      }
    
    public String deleteStuffInfoOfBookPage() {
        stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());
        optProcInfoManager.deleteStuffInfo(stuffInfo);
        //删除磁盘上的文件
        String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
        EfileManager.remove(absolutePath);
        return "gotosqclOfBookPage";

    }
    // 判断是否全部传完了 1：传完了 0：没有传完
    public String isAllSC(List<Suppowerstuffinfo> suppowerstuffinfos,
            List<OptStuffInfo> optStuffs) {
        String ast = "1";
        Boolean[] bool = new Boolean[suppowerstuffinfos.size()];
        int i = 0;
        for (Suppowerstuffinfo a : suppowerstuffinfos) {
            if ("1".equals(a.getIsNeed())) {
                bool[i] = false;
                if (optStuffs == null) {
                    ast = "0";
                    return ast;
                }
                for (OptStuffInfo st : optStuffs) {
                    if (Long.toString(a.getSortId()).equals(st.getSortId()))
                        bool[i] = true;
                }
                i++;
            } else {
                bool[i] = true;
                i++;
            }
        }
        Boolean b = true;
        for (int j = 0; j < bool.length; j++) {
            b = b && bool[j];
        }
        if (!b)
            ast = "0";
        return ast;
    }

    public String deleteStuffInfo() {
        stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());
        optProcInfoManager.deleteStuffInfo(stuffInfo);
        //删除磁盘上的文件
        String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
        EfileManager.remove(absolutePath);
        return "gotosqcl";

    }
    
    public String deleteCFStuffInfo() {
        stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());
        optProcInfoManager.deleteStuffInfo(stuffInfo);
        
        //删除磁盘上的文件
        String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
        EfileManager.remove(absolutePath);
        return "gotoCFsqcl";
    }

    /**
     * 发文步骤根据DJID和ArchiveType 删除当前文书
     * 
     * @return
     */
    public String deleteStuffByArchiveType() {
        List<OptStuffInfo> osf = optProcInfoManager.listStuffsByArchiveType(
                object.getDjId(), object.getArchiveType());
        if (osf != null && osf.size() > 0) {
            OptStuffInfo optStuffInfo= osf.get(0);
            optProcInfoManager.deleteStuffInfo(optStuffInfo);
            if(optStuffInfo.getStuffpath()!=null){
                EfileManager.remove(SysParametersUtils.getWorkflowAffixHome() + optStuffInfo.getStuffpath());
            }
            OptProcInfo procInfo = optProcInfoManager
                    .getObjectByNodeInstId(object.getNodeInstId());
            if(procInfo!=null){
                procInfo.setArchiveType("");
                procInfo.setRecordId("");
                optProcInfoManager.saveObject(procInfo);
            }
        }
        return null;
    }

    public String gotoCFsqcl() {
        try {
            suppowerstuffinfos = suppowerstuffinfoManager
                    .getinfosByGroupId(suppowerstuffinfo.getGroupId());
            optStuffs = optProcInfoManager
                    .listStuffsByDjId(stuffInfo.getDjId());
            isAllsc = isAllSC(suppowerstuffinfos, optStuffs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cFsqclList";
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    protected HttpServletResponse response;
    
    /**
     * 下载签发的pdf
     * @return
     * @throws IOException
     */
        public String downIdeaInfo() throws IOException {
            stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());

            String fn = stuffInfo.getFilename();
            try {
                if (stuffInfo.getIszhi() != null) {
                    this.postAlertMessage("附件为空或为纸质文件", response);
                    return null;
                }
                if (stuffInfo.getIszhi() == null) {
                    String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
                    PdfReader reader = new PdfReader("HelloWorld.pdf", "World".getBytes());
                     
                   byte [] ooo= reader.getMetadata();
                   
                    
                    inputStream=new FileInputStream(absolutePath);
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError(e.getMessage());
            }
            this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
            return "download";

        }
        
/**
 * 下载上传的附件
 * @return
 * @throws IOException
 */
    public String downStuffInfo() throws IOException {
        stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());

        String fn = stuffInfo.getFilename();
        try {
            if (stuffInfo.getIszhi() != null) {
                this.postAlertMessage("附件为空或为纸质文件", response);
                return null;
            }
            if (stuffInfo.getIszhi() == null) {
                String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
                inputStream=new FileInputStream(absolutePath);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";

    }
    
    //判断文件是否存在
    public String fileNull() throws IOException{
        stuffInfo = optProcInfoManager.getStuffById(stuffInfo.getStuffid());
       
        if (stuffInfo.getIszhi() != null) {
            this.postAlertMessage("附件为空或为纸质文件", response);
            return null;
        }
            String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
            File file = new File(absolutePath);
            InputStream stuffStream = FileUtils.openInputStream(file);
        try {
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";

    }
    /**
     * -------------------------------------------------------------------------
     * 材料分组
     */

    private List<Suppowerstuffgroup> stuffgroups;
    private Suppowerstuffgroup suppowerstuffgroup;
    private Suppowerstuffinfo suppowerstuffinfo;

    public Suppowerstuffinfo getSuppowerstuffinfo() {
        return suppowerstuffinfo;
    }

    public void setSuppowerstuffinfo(Suppowerstuffinfo suppowerstuffinfo) {
        this.suppowerstuffinfo = suppowerstuffinfo;
    }

    @SuppressWarnings("unchecked")
    public String stuffdivide() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        stuffgroups = suppowerstuffgroupManager
                .listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "stuffdivide";
    }

    public String selectstuffGroup() {
        stuffgroups = suppowerstuffgroupManager.listObjects();
        totalRows = stuffgroups.size();
        return "selectstuffGroup";
    }

    public String groupbuilt() {
        if (suppowerstuffgroup != null) {
            suppowerstuffgroup = suppowerstuffgroupManager
                    .getObjectById(suppowerstuffgroup.getGroupId());
        } else {
            suppowerstuffgroup = new Suppowerstuffgroup();
            suppowerstuffgroup.setGroupId(suppowerstuffgroupManager
                    .getNextGroupCode());
        }
        return "groupbuilt";
    }

    public String groupedit() {
        suppowerstuffgroup = suppowerstuffgroupManager
                .getObjectById(suppowerstuffgroup.getGroupId());
        return "groupbuilt";
    }

    /**
     * 删除材料分组：如果存在材料明细，将材料明细记录也删除
     * 
     * @return
     */
    public String deleteStuffgroup() {
        suppowerstuffgroup = suppowerstuffgroupManager
                .getObjectById(suppowerstuffgroup.getGroupId());
        if (suppowerstuffgroup != null) {
            // 如果存在旗下明细信息，将其删除
            List<Suppowerstuffinfo> suppowerstuffinfolist = suppowerstuffinfoManager
                    .getinfosByGroupId(suppowerstuffgroup.getGroupId());
            if (suppowerstuffinfolist != null
                    && suppowerstuffinfolist.size() > 0) {
                suppowerstuffinfoManager
                        .deleteObjectByGroupId(suppowerstuffgroup.getGroupId());
            }
            // 删除分组信息
            suppowerstuffgroupManager.deleteObjectById(suppowerstuffgroup
                    .getGroupId());
        }
        return this.stuffdivide();
    }

    
    
    /**
     * 办理意见显示
     * 获取流程的通用模块列表
     * 1.依据djid 获取 流程实例
     * 2.依据flowinstid获取 流程信息
     * 3.依据flow 信息 获取所有节点 的通用模块信息，并过滤 获得需要在页面显示意见的数据
     * @param djId
     * @return
     */
    public String showIdeaModuleList() {
        json=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        String djid=request.getParameter("djid");
        OptBaseInfo  baseInfo=optBaseInfoManager.getObjectById(djid);//获取业务信息
       
        if(null!=baseInfo ){//获取流程节点信息
            FlowInstance flowInstance=flowEngine.getFlowInstById(baseInfo.getFlowInstId());
            List<GeneralModuleParam> moduleListtemp= generalModuleParamManager.getGeneralModuleList(flowInstance.getFlowCode(),flowInstance.getVersion());
        
            if(null!=moduleListtemp&&moduleListtemp.size()>0){
                for(GeneralModuleParam  moduleParam:moduleListtemp){
                    JSONObject map = new JSONObject();
                    map.put("moduleCode", JsonUtil.replaceNullString(moduleParam.getModuleCode()));
                    map.put("isShowInNode",JsonUtil.replaceNullString(moduleParam.getIsShowInNode()));
                    map.put("nodeLabel", JsonUtil.replaceNullString(moduleParam.getNodeLabel()));
                   
                    jsonArray.add(map);
                }
            }
        }
        
        json.put("moduleList", jsonArray);
        return "options";
      
    }
    
    public String saveStuffGroup() {
        Suppowerstuffgroup dbobject = suppowerstuffgroupManager
                .getObjectById(suppowerstuffgroup.getGroupId());
        if (dbobject != null) {
            dbobject.copyNotNullProperty(suppowerstuffgroup);
            suppowerstuffgroup = dbobject;
        }
        suppowerstuffgroupManager.saveObject(suppowerstuffgroup);
        return "groupSuccess";
    }

    public String viewGroupInfo() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        suppowerstuffinfos = suppowerstuffinfoManager.listObjects(filterMap,
                pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "viewGroupInfo";
    }

    public String stuffinfobuilt() {
        if (suppowerstuffinfo != null)
            suppowerstuffinfo = suppowerstuffinfoManager
                    .getObjectById(suppowerstuffinfo.getSortId());
        else {
            suppowerstuffinfo = new Suppowerstuffinfo();
            suppowerstuffinfo.setSortId(Long.parseLong(suppowerstuffinfoManager
                    .getNextKey()));
            suppowerstuffinfo.setGroupId(request.getParameter("s_groupId"));
        }
        return "stuffinfobuilt";
    }

    public String saveStuffinfo() {
        try {
            suppowerstuffinfo.setIsupload("0");
            suppowerstuffinfoManager.saveObject(suppowerstuffinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "saveStuffinfo";
    }

    public List<Suppowerstuffgroup> getStuffgroups() {
        return stuffgroups;
    }

    public void setStuffgroups(List<Suppowerstuffgroup> stuffgroups) {
        this.stuffgroups = stuffgroups;
    }

    /**
     * -------------------------------------------------------------------------
     * 获取附件列表
     */
    List<OptStuffInfo> optStuffs;

    // 附件
    List<Object[]> stuffnames0;
    // 正文
    List<OptStuffInfo> stuffnames1;

    List<VOptStuffInfo> vOptStuffs;

    // 附件
    List<Object[]> vStuffnames0;
    // 正文
    List<VOptStuffInfo> vStuffnames1;

    public List<VOptStuffInfo> getVOptStuffs() {
        return vOptStuffs;
    }

    public void setVOptStuffs(List<VOptStuffInfo> vOptStuffs) {
        this.vOptStuffs = vOptStuffs;
    }

    public List<Object[]> getVStuffnames0() {
        return vStuffnames0;
    }

    public void setVStuffnames0(List<Object[]> vStuffnames0) {
        this.vStuffnames0 = vStuffnames0;
    }

    public List<VOptStuffInfo> getVStuffnames1() {
        return vStuffnames1;
    }

    public void setVStuffnames1(List<VOptStuffInfo> vStuffnames1) {
        this.vStuffnames1 = vStuffnames1;
    }

    public List<Object[]> getStuffnames0() {
        return stuffnames0;
    }

    public void setStuffnames0(List<Object[]> stuffnames0) {
        this.stuffnames0 = stuffnames0;
    }

    public List<OptStuffInfo> getStuffnames1() {
        return stuffnames1;
    }

    public void setStuffnames1(List<OptStuffInfo> stuffnames1) {
        this.stuffnames1 = stuffnames1;
    }

    public List<OptStuffInfo> getOptStuffs() {
        return optStuffs;
    }

    public void setOptStuffs(List<OptStuffInfo> optStuffs) {
        this.optStuffs = optStuffs;
    }

    public String listStuffs() {
        // 展示附件页面 ，参见行权系统中的页面
        optStuffs = optProcInfoManager.listStuffsByDjId(object.getDjId());
        vOptStuffs = optProcInfoManager.getStuffsByDjId(object.getDjId());
        stuffnames0 = optProcInfoManager
                .listStuffsByDjIdGroupByStuffname0(object.getDjId()); // 材料组上传
       vStuffnames1 = optProcInfoManager
                .getStuffsByDjIdGroupByStuffname1(object.getDjId()); //查询所有的附件
       
     /*  if(vStuffnames1.size()>0) {
           VOptStuffInfo vOptStuffInfo1=new VOptStuffInfo();
           if("zwpdf".equals(vStuffnames1.get(vStuffnames1.size()-1).getArchivetype()) || "zw-pdf".equals(vStuffnames1.get(vStuffnames1.size()-1).getArchivetype())) {
               vOptStuffInfo1=vStuffnames1.get(vStuffnames1.size()-1);
           }
           Iterator<VOptStuffInfo> it = vStuffnames1.iterator();
           while(it.hasNext()){
               VOptStuffInfo x = it.next();
               if("zwpdf".equals(x.getArchivetype()) || "zw-pdf".equals(x.getArchivetype())){
                   it.remove();
               }
           }
           vStuffnames1.add(vOptStuffInfo1);
       }*/
        /*
         * add by hll 20140320 将材料组重新进行归类： 1，收文材料，archivetype=fj；
         * 2，正文材料，archivetype=nwfj； 3，办理材料，除收文材料及正文材料以外的其他材料。
         */

        String fjGroupId = "", nwfjGroupId = "";
        for (int i = 0; i < vOptStuffs.size(); i++) {
            if (StringUtils.isNotBlank(fjGroupId)
                    && StringUtils.isNotBlank(nwfjGroupId)) {
                break;
            }
            if ("swfj".equals(vOptStuffs.get(i).getArchivetype())) { // 获取申请材料的groupId
                fjGroupId = vOptStuffs.get(i).getGroupid();
            }
            if ("fwfj".equals(vOptStuffs.get(i).getArchivetype())) { // 获取正文材料的groupId
                fjGroupId = vOptStuffs.get(i).getGroupid();
            }
        }

        List<Suppowerstuffinfo> fjInfos = StringUtils.isBlank(fjGroupId) ? (new ArrayList<Suppowerstuffinfo>())
                : suppowerstuffinfoManager.getinfosByGroupId(fjGroupId); // 收文材料信息
        List<Suppowerstuffinfo> nwfjInfos = StringUtils.isBlank(nwfjGroupId) ? (new ArrayList<Suppowerstuffinfo>())
                : suppowerstuffinfoManager.getinfosByGroupId(nwfjGroupId); // 正文材料信息

        List<VOptStuffInfo> stuffList = new ArrayList<VOptStuffInfo>();
        List<List<Suppowerstuffinfo>> stuffInfoList = new ArrayList<List<Suppowerstuffinfo>>();
        stuffInfoList.add(fjInfos);
        stuffInfoList.add(nwfjInfos);

        for (int i = vOptStuffs.size() - 1; i >= 0; i--) {
            if ("zw".equals(vOptStuffs.get(i).getArchivetype())
                    || "zw-pdf".equals(vOptStuffs.get(i).getArchivetype())) {
                vOptStuffs.get(i).setFiletype("2");
                stuffList.add(0, vOptStuffs.get(i));
                vOptStuffs.remove(i);
            }
        }

        for (int index = 1; index >= 0; index--) {
            List<Suppowerstuffinfo> infos = stuffInfoList.get(index);
            for (int i = infos.size() - 1; i >= 0; i--) {
                String stuffName = infos.get(i).getStuffName();
                for (int j = vOptStuffs.size() - 1; j >= 0; j--) {
                    if (stuffName.equals(vOptStuffs.get(j).getStuffname())) {
                        vOptStuffs.get(i).setFiletype(1 == index ? "2" : "1");
                        stuffList.add(0, vOptStuffs.get(j));
                        vOptStuffs.remove(j);
                    }
                }
            }
        }

        for (int i = 0; i < vOptStuffs.size(); i++) {
            vOptStuffs.get(i).setFiletype("4");
        }

        vOptStuffs.addAll(0, stuffList);

        List<Object[]> stuffCountList = new ArrayList<Object[]>();

        for (int index = 1; index >= 0; index--) {
            List<Suppowerstuffinfo> infos = stuffInfoList.get(index);
            for (int i = infos.size() - 1; i >= 0; i--) {
                String stuffName = infos.get(i).getStuffName();
                for (int j = stuffnames0.size() - 1; j >= 0; j--) {
                    if (stuffName.equals(stuffnames0.get(j)[0])) {
                        stuffCountList.add(0, stuffnames0.get(j));
                        stuffnames0.remove(j);
                    }
                }
            }
        }
        stuffnames0.addAll(0, stuffCountList);

        return "listStuffs";
    }
    
    
    
    public String listPreNode() {
         Long previousNode=optBaseInfoManager.getPreviousNode(object.getNodeInstId());
         if(null!=previousNode) {
             ideaLogs= optProcInfoManager.listIdeaLogs(object.getDjId(),previousNode);
         }
        return "listPreNode";
    }
    
    
    
    

    public static OptHtmlFrameInfo getStuffListFrame(String djId) {
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("stuffsFrame");
        stuffsFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!listStuffs.do?djId="
                        + djId);
        stuffsFrameInfo.setFrameHeight("300px");
        stuffsFrameInfo.setFrameLegend("材料信息");
        // frameMap.put("stuffs", stuffsFrameInfo);
        // frameMap.put("proc", procFrameInfo);
        return stuffsFrameInfo;
    }

    /************************************************************
     * 此项为办件查看：查看附件，只保留下载查看功能，不支持删除。
     * 
     * @param djId
     * @return
     */
    public static OptHtmlFrameInfo getStuffsViewFrame(String djId) {
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("viewStuffsFrame");
        stuffsFrameInfo
                .setFrameSrc("/powerruntime/generalOperator!viewListStuffs.do?djId="
                        + djId);
        stuffsFrameInfo.setFrameHeight("300px");
        return stuffsFrameInfo;
    }

    /************************************************************
     * 此项为办件查看：查看附件，只保留下载查看功能，不支持删除。
     * 
     * @param djId
     * @return
     */
    public String viewListStuffs() {
        // 展示附件页面 ，参见行权系统中的页面
        optStuffs = optProcInfoManager.listStuffsByDjId(object.getDjId());
        return "viewListStuffs";
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

    public String existTemplate() {
        String responseText = "";
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("djId", object.getDjId());
            paramMap.put("archivetype", object.getArchiveType());
            // paramMap.put("nodeinstid", object.getNodeInstId());

            List<OptStuffInfo> stuffInfoLList = optStuffInfoManager
                    .listObjects(paramMap);

            responseText = (null == stuffInfoLList || stuffInfoLList.isEmpty()) ? "no"
                    : "yes";
        } catch (Exception e) {
            log.info(e);
        } finally {
            super.ajaxResponseText(response, responseText);
        }

        return null;
    }

    
    
    
    private JSONObject json;

    /**
     * 通过环节代码获取办理过程信息
     * @param nodeCodes
     * type : sigle 单一 取最新数据
     * dataModel :数据模板 user 办理人员
     *             detail 详细办理信息
     * @return
     */
    public String getAllIdeaInfoByNodeCodes() {
        json = new JSONObject();
        String idea="";
        String djid=request.getParameter("djid");
        String nodeCodes=request.getParameter("nodeCodes") ;
        String identify="nodeCodes" ;
        String type=request.getParameter("type") ;
        String dataModel=request.getParameter("dataModel") ;
        
        idea=idealDetail( djid, type , dataModel, nodeCodes );
        json.put("idea", idea);
        return "options";
    }
    

    /**
     * 通过通用模块获取办理过程信息
     * @param moduleCode
     * ----
     *通用模块获取以下字段
     * type : sigle 单一 取最新数据
     * dataModel :数据模板 
     * @return
     */
    public String  getAllIdeaInfoByModuleCode() {
        json = new JSONObject();
        String idea="";
        String djid=JsonUtil.replaceNullString(request.getParameter("djid"));
        String moduleCode=JsonUtil.replaceNullString(request.getParameter("moduleCode"));
        String ideaModule= "{\"type\":\"\",\"dataModule\":\"{transcontent} </br> <div class='r' align='right'> {username} {transdate}</div>\" ,\"DATE_PATTERN\":\"yyyy-MM-dd HH:mm\" }";//默认;
        
        //获取模板信息
        GeneralModuleParam  moduleParam =generalModuleParamManager.getObjectById(moduleCode);
        if(null!=moduleParam && StringUtils.isNotBlank(moduleParam.getIdeaModule()) ){//空值时取默认值
            ideaModule= moduleParam.getIdeaModule();//意见模板
        
        }
       
        JSONObject obj = JSONObject.fromObject(ideaModule);
        
        List<OptIdeaInfo> optIdeaInfos = null;
        if(StringUtils.isNotBlank(djid)){
           optIdeaInfos = optProcInfoManager
                        .listIdeaLogsByDjIdAndModuleCode(djid, moduleCode);
           idea=idealDetail( obj,optIdeaInfos);
        }
        json.put("idea", idea);
        return "options";
    }   
    
    
   
    
   /**
    * 按需生成意见
    * @param djid
    * @param type
    * @param dataModel
    * @return
    */
    private String idealDetail( JSONObject json, List<OptIdeaInfo> optIdeaInfos){
        String idea = "";// 办理信息
        String type=json.has("type")?json.getString("type"):"";
        String dataModule=json.has("dataModule")?json.getString("dataModule"):"";
        String DATE_PATTERN=json.has("DATE_PATTERN")?json.getString("DATE_PATTERN"):"";
            if (optIdeaInfos != null && !optIdeaInfos.isEmpty()) {
                String view="";
                for (OptIdeaInfo optIdeaInfo : optIdeaInfos) {
                   Map<String, Object>  dateMap =new HashMap<String, Object>();
                   dateMap.put("{username}",JsonUtil.replaceNullString(optIdeaInfo.getUsername()));
                   dateMap.put("{transdate}",null==optIdeaInfo.getTransdate()?"":DateUtil.date2String(optIdeaInfo.getTransdate(),DATE_PATTERN));
                   dateMap.put("{transcontent}",JsonUtil.replaceNullString(optIdeaInfo.getTranscontent()));
                    
                    if(!("sigle".equals(type)&&StringUtils.isNotBlank(idea))){//是否单条记录
                        view=BizCommUtil.replace(dateMap, dataModule);//替换
                        idea += view ;
                    }
            }
            json.put("idea", idea);
        }  
        return idea;
    }
     
    

    /**
     * 按需生成意见--传参
     * @param djid
     * @param type
     * @param dataModel
     * identify  过滤字段
     * identifyDate 具体数值
     * @return
     */
     private String idealDetail(String djid,String type ,String dataModel,String nodeCodes){
         String idea = "";// 办理信息
         List<OptIdeaInfo> optIdeaInfos = null;
         if(StringUtils.isNotBlank(djid)){
          //依据nodecodes查询
           optIdeaInfos =optProcInfoManager
                         .listIdeaLogsByDjIdAndNodeCodes(djid, nodeCodes);
             if (optIdeaInfos != null && !optIdeaInfos.isEmpty()) {
                 String view="";
                 for (OptIdeaInfo optIdeaInfo : optIdeaInfos) {
                     if(!("sigle".equals(type)&&StringUtils.isNotBlank(idea))){
                         if("user".equals(dataModel)){
                             view=optIdeaInfo.getUsername();
                         }else{
                             view = String.format("%s</br> <div class='r' align='right'> %s&nbsp;&nbsp;%s</div>", JsonUtil.replaceNullString(optIdeaInfo.getTranscontent()),optIdeaInfo.getUsername(),DateUtil
                                     .date2String(optIdeaInfo.getTransdate(),"yyyy-MM-dd HH:mm")
                                    );
                         }
                         idea += view ;
                     }
                    
                 }
             }
             json.put("idea", idea);
         }  
         return idea;
     }
    /**
     * 获取所有附件信息
     * @return
     */
    public String getAllStuffs() {
        json = new JSONObject();
        String djid=request.getParameter("djid");
        optStuffs = optProcInfoManager.listStuffsByDjId(djid);
        
        json.put("stuff", optStuffs);

        return "options";
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

    public void saveTeamRolepeople() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), teamRoleCode);
        if (!StringUtils.isBlank(teamUserCodes)) {
            String[] teamUserCode = teamUserCodes.split(",");
            if (teamUserCode.length > 0) {
                for (String userCode : teamUserCode) {
                    flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                            teamRoleCode, userCode, loginInfo.getUsercode());
                }
            }
        }
    }

    public List<Suppowerstuffinfo> getSuppowerstuffinfos() {
        return suppowerstuffinfos;
    }

    public void setSuppowerstuffinfos(List<Suppowerstuffinfo> suppowerstuffinfos) {
        this.suppowerstuffinfos = suppowerstuffinfos;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public String getAttUserNames() {
        return attUserNames;
    }

    public void setAttUserNames(String attUserNames) {
        this.attUserNames = attUserNames;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setSuppowerstuffgroupManager(
            SuppowerstuffgroupManager suppowerstuffgroupManager) {
        this.suppowerstuffgroupManager = suppowerstuffgroupManager;
    }

    public Suppowerstuffgroup getSuppowerstuffgroup() {
        return suppowerstuffgroup;
    }

    public void setSuppowerstuffgroup(Suppowerstuffgroup suppowerstuffgroup) {
        this.suppowerstuffgroup = suppowerstuffgroup;
    }

    public String getOptBaseInfoJson() {
        return optBaseInfoJson;
    }

    public void setOptBaseInfoJson(String optBaseInfoJson) {
        this.optBaseInfoJson = optBaseInfoJson;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 弹出提示信息
     * 
     * @param msg
     * @param response
     */
    protected void postAlertMessage(String msg, HttpServletResponse response) {

        String alertCoding = "UTF-8";

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

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 选择流程
     * 
     * @return
     */
    public String ioSelectFlow() {
        try {
            fwList = flowDefine.getFlowsByOptId("IO_DOC");
            // swList = flowDefine.getFlowsByOptId("INCOME_DOC");

            savedMessage();
            return "ioSelectFlow";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            saveError(e.getMessage());
            return ERROR;
        }
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }

    public List<FlowDescribe> getFwList() {
        return fwList;
    }

    public void setFwList(List<FlowDescribe> fwList) {
        this.fwList = fwList;
    }

    public List<FlowDescribe> getSwList() {
        return swList;
    }

    public void setSwList(List<FlowDescribe> swList) {
        this.swList = swList;
    }

    /*
     * 得到当前年份前后5年的时间段
     */
    public List<Map<String, String>> getFromArray() {
        List<Map<String, String>> fromArray = new ArrayList<Map<String, String>>();
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        for (int i = y - 5; i < y + 5; i++) {
            HashMap<String, String> cache = new HashMap<String, String>();
            cache.put("id", String.valueOf(i));
            cache.put("name", String.valueOf(i));
            fromArray.add(cache);
        }
        return fromArray;
    }

    public List<Map<String, String>> getYear() {
        return year;
    }

    public void setYear(List<Map<String, String>> year) {
        this.year = year;
    }

    public DispatchDocManager getDispatchDocManager() {
        return dispatchDocManager;
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public void setOptApplyManager(OptApplyManager optApplyManager) {
        this.optApplyManager = optApplyManager;
    }

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }
    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }
    
    /**
     * 盖完章自动上传附件至服务器磁盘里
     * @return
     * @throws IOException
     */
    public void doPost() throws IOException, ServletException {  
        // 设置数据传输单元大小为1KB  
        int unit_size = 1024;  
        // 初始化xml文件大小（以字节为单位）  
        int xmlfilesize = 0;  
        // 初始化上传文件名称（完整文件名）  
        String xmlfilename = "";  
        // 初始化上传文件保存路径（绝对物理路径）  
        String xmlfilepath = "";  
        // 声明文件存储字节数组  
        byte[] xmlfilebytes = null;  
        try {  
            // 初始化 SAX 串行xml文件解析器  
            SAXBuilder builder = new SAXBuilder();  
            Document doc = builder.build(request.getInputStream());  
            Element eroot = doc.getRootElement();  
            // 获取上传文件的完整名称  
            Iterator it_name = eroot.getChildren("uploadfilename").iterator(); 
            System.out.println(it_name);
            if (it_name.hasNext()) {  
                xmlfilename = ((Element) it_name.next()).getText();  
            }  
            // 获取上传文件保存的绝对物理路径  
            Iterator it_path = eroot.getChildren("uploadfilepath").iterator();  
            if (it_path.hasNext()) {  
                xmlfilepath = ((Element) it_path.next()).getText();  
            }  
            Iterator it_size = eroot.getChildren("uploadfilesize").iterator();  
            if (it_size.hasNext()) {  
                xmlfilesize = Integer.parseInt(((Element) it_size.next())  
                        .getText());  
            }
                if (xmlfilesize > 0) {  
                    int unit_count = 0;  
                    // 为存储文件内容的字节数组分配存储空间  
                    xmlfilebytes = new byte[xmlfilesize];  
                    // 循环读取文件内容，并保存到字节数组中  
                    Iterator it_content = eroot.getChildren("uploadcontent")  
                            .iterator();  
                    while (it_content.hasNext()) {  
                        // 初始化Base64编码解码器  
                        BASE64Decoder base64 = new BASE64Decoder();  
                        byte[] xmlnodebytearray = base64.decodeBuffer(((Element) it_content.next()).getText());  
                        if (xmlnodebytearray.length >= unit_size) {  
                            // 读取一个完整数据单元的数据  
                            System.arraycopy(xmlnodebytearray, 0, xmlfilebytes,  
                                    unit_count * unit_size, unit_size);  
                        } else {  
                            // 读取小于一个数据单元的所有数据  
                            System.arraycopy(xmlnodebytearray, 0, xmlfilebytes,  
                                    unit_count * unit_size, xmlfilesize  
                                            % unit_size);  
                        }  
                        // 继续向下读取文件内容  
                        unit_count++;  
                    }  
                }  
          
            // 保存路径  
                System.out.println(xmlfilepath);
            File path = new File(xmlfilepath);  
            if(!path.exists()){  
            path.mkdirs();  
            }  
            // 保存文件  
            File file = new File(path,xmlfilename);  
            // 创建文件输入输出流  
            FileOutputStream fos = new FileOutputStream(file);  
            // 写入文件内容  
            fos.write(xmlfilebytes);  
            fos.flush();  
            // 关闭文件输入输出流  
            fos.close();  
        } catch (Exception e) {  
        e.printStackTrace();  
        }  
    }  
}
