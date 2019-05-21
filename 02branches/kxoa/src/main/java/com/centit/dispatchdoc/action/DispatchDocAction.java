package com.centit.dispatchdoc.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.ApplyUnitInfo;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.DispatchDocTask;
import com.centit.dispatchdoc.po.DocRelative;
import com.centit.dispatchdoc.po.DocRelativeId;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.po.VDispatchDocList;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.service.ApplyUnitInfoManager;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.DocRelativeManager;
import com.centit.dispatchdoc.service.DocSendManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.dispatchdoc.service.VDispatchDocListManager;
import com.centit.dispatchdoc.service.VIncomeDocListManager;
import com.centit.oa.po.OaArchive;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaArchiveManager;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.oa.service.VoaUnitArchiveDispatchdocManager;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.po.OptProcCollectionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.powerruntime.service.VOptProcCollectionManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;
import com.centit.workflow.sample.po.WfOrganize;

public class DispatchDocAction extends IODocCommonBizAction<DispatchDoc> {
    private static final long serialVersionUID = 1L;
    private DispatchDocManager dispatchDocMgr;
    private IncomeDocManager incomeDocMgr;
    private DocRelativeManager docRelativeMgr;
    private DocSendManager docSendMgr;
    private ApplyUnitInfoManager applyUnitInfoMgr;
    private VIncomeDocListManager incomeDocListManager;
    private VDispatchDocListManager dispatchDocListManager;
    private List<VIncomeDocList> incomeDocList;
    private List<DispatchDocTask> vUserTasksList;
    private String unitsJson;//部门JSON
    private String parentUnit;//父部门ID
    private String optId;
    private SuppowerManager suppowerManager;
    private String roleCode;
    private String peopleRoleCode;// 会签处室人员对应办件角色
    private String selUnitCode;
    private String selUnitName;
    private List<VDispatchDocList> dispatchDocList;
    private List<FUnitinfo> unitList; // 会签单位列表
    List<DocRelative> docRelativeList;
    // 关联的收文集合
    private List<VDispatchDocList> vDispatchDocList;
    // 收发文关联frame类型（编辑/查看）
    private String docRelativeFrameType;
    private String orgCode;
    private String orgName;
    private VsuppowerinusingManager powerMgr;
    private OptStuffInfoManager optStuffInfoManager;
    private OaBizBindInfoManager oaBizBindInfoManager;
    //登记页面的办理意见提交流程时保存进日志信息
    private String transcontent;
    private String startDjId;
    private Long nodeId; 

    private String show_type;//区别查看页面是否显示新增按钮,or编辑页面是否有返回按钮
  
    private String curUrl;
    private List<OptNewlyIdea> optNewlyIdeaList;
    private OptNewlyIdeaManager optNewlyIdeaManager;
    private OaSuperviseManager oaSuperviseManager;
    private  List<OaSupervise> oasuplist;
    private OptProcCollectionManager optProcCollectionManager;//办件收藏
    private VOptProcCollectionManager VOptProcCollectionMag;

    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    
    private VOptBaseListManager vOptBaseListManager;

    private VoaUnitArchiveDispatchdocManager voaUnitArchiveDispatchdocMag;
    private OaArchiveManager oaArchiveMag;
    
    private IdeaTempletManager ideaTempletManager;
    public void setOaArchiveManager(OaArchiveManager basemgr) {
        oaArchiveMag = basemgr;
    }
    
    /**
     * RTX相关
     */
    private RtxInfoManager rtxInfoManager;     
    private String queryUnderUnit;//(列表类别)按职务等级查询列表
    private FUserunit userUnit;// 用户单位
    private String userRank=null;
    
    /**
     * 发文关联action需要的参数
     */
    private String d_djId;
    private String d_nodeInstId;
    private String d_itemtype;
    
    
    private boolean isVailViewPower;//是否验证查看权限
    
    public String getD_djId() {
        return d_djId;
    }
    public void setD_djId(String d_djId) {
        this.d_djId = d_djId;
    }
    public String getD_nodeInstId() {
        return d_nodeInstId;
    }
    public void setD_nodeInstId(String d_nodeInstId) {
        this.d_nodeInstId = d_nodeInstId;
    }
    public String getD_itemtype() {
        return d_itemtype;
    }
    public void setD_itemtype(String d_itemtype) {
        this.d_itemtype = d_itemtype;
    }

    private OaSmssendManager oaSmssendManager;
   
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
   
    
    
    
     /**--------Temp 发文 lq-----------   **/
    
    /**
     * 根据查询表单查询(未提交发文)，该查询的是视图V_DISPATCH_DOC_LIST，其中已经对通过收文进入登记拟文的记录进行了过滤
     * hll 20131227
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
           
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            
//            filterMap.put("usercode", loginUser.getUsercode());
            
//            filterMap.put(CodeBook.SELF_ORDER_BY, "update_date desc");
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            
            userRank=getUserRankByUsercode(loginUser.getUsercode());//控制页面显示内容:全机构(部门下拉框)、全科室（checkboox）
            //列表页面添加职务分类查看 ：处长查看机构所有查看，科长查看本科室
            if(StringUtils.isNotBlank(queryUnderUnit)){//(列表类别)按职务等级查询列表--默认显示自己的
                
                userUnit =sysUserManager.getUserPrimaryUnit(loginUser.getUsercode());
                if(StringUtils.equals(userRank, "CZ")){
                    filterMap.put("queryUnderUnit","true");//页面传参
                    filterMap.put("unitcode",userUnit.getUnitcode());
                }else  if(StringUtils.equals(userRank, "TZ")){
                    filterMap.put("queryUnderUnit","true");//部门编码从页面传入queryUnderUnit
//                  filterMap.put("unitcode",userUnit.getUnitcode());//部门编码从页面传入s_unitcode
                }
                
                
            }else{
                filterMap.put("usercode", loginUser.getUsercode());
            }
            dispatchDocList = dispatchDocListManager.listDispatchDoc(filterMap,
                    pageDesc);
            // 查看办件列表中，设置在办件的超时提醒
            for(VDispatchDocList o : dispatchDocList){
                if("W".equals(o.getBizstate())){
                    DispatchDoc doc = dispatchDocMgr.getObjectById(o.getDjId());
                    
                    if(null != doc){
                        o.setOverdueType(BizCommUtil.getOverDueState(doc.getToBeanfinishedDate()));                        
                    }
                }
            }
            unitList=unitList();//科级部门
            
//            dispatchDocList = dispatchDocListManager.listObjects(" FROM VDispatchDocList where 1=1", filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            //return LIST;
            return "listV1";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    public String listV2() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
           
            
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            //只显示已办结
            filterMap.put("bizstate", "C");
            FUserDetail user = ((FUserDetail) getLoginUser());
            FUserunit funit = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            if(StringUtils.isNotBlank(request.getParameter("s_bmfw"))||StringUtils.isNotBlank((String)filterMap.get("bmfw"))){
                filterMap.put("unitcode",funit.getUnitcode());
            }
            dispatchDocList = dispatchDocListManager.listDispatchDocV2(filterMap, pageDesc);
            
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            //return "listV2";
            return "listV3";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 无分页查询
     * @return
     */
    @SuppressWarnings("unchecked")
    public void listForExcel() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
           
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            
            userRank=getUserRankByUsercode(loginUser.getUsercode());//控制页面显示内容:全机构(部门下拉框)、全科室（checkboox）
            //列表页面添加职务分类查看 ：处长查看机构所有查看，科长查看本科室
            if(StringUtils.isNotBlank(queryUnderUnit)){//(列表类别)按职务等级查询列表--默认显示自己的
                
                userUnit =sysUserManager.getUserPrimaryUnit(loginUser.getUsercode());
                if(StringUtils.equals(userRank, "CZ")){
                    filterMap.put("queryUnderUnit","true");//页面传参
                    filterMap.put("unitcode",userUnit.getUnitcode());
                }else  if(StringUtils.equals(userRank, "TZ")){
                    filterMap.put("queryUnderUnit","true");//部门编码从页面传入queryUnderUnit
//                  filterMap.put("unitcode",userUnit.getUnitcode());//部门编码从页面传入s_unitcode
                }
                
                
            }else{
                filterMap.put("usercode", loginUser.getUsercode());
            }
            dispatchDocList = dispatchDocListManager.listDispatchDocForExcel(filterMap);
            
            // 查看办件列表中，设置在办件的超时提醒
            for(VDispatchDocList o : dispatchDocList){
                if("W".equals(o.getBizstate())){
                    DispatchDoc doc = dispatchDocMgr.getObjectById(o.getDjId());
                    
                    if(null != doc){
                        o.setOverdueType(BizCommUtil.getOverDueState(doc.getToBeanfinishedDate()));                        
                    }
                }
            }
            unitList=unitList();//科级部门
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 导出Excel 根据查询条件 导出当前结果
     * 
     * @throws IOException
     */
    public void exportExcelByPo() throws IOException {
        this.listForExcel();
        List<VDispatchDocList> objectList =new ArrayList<VDispatchDocList>();
        objectList = dispatchDocList;         
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objectList != null) {
            int i = 1;
            for (VDispatchDocList o : objectList) {                
                Object[] temp= new Object[9];
                temp[0]=String.valueOf(i++);
                temp[1]=o.getDispatchDocNo();
                temp[2]=o.getDispatchDocTitle();
                temp[6]=o.getMainNotifyUnit();
                temp[4]=o.getDraftUserName();
                temp[5]=CodeRepositoryUtil.getValue("usercode", o.getDispatchUser()) ;
                temp[3]=DatetimeOpt.convertDateToString(
                        o.getCreateDate(), "yyyy年MM月dd日");
                temp[7]=o.getNodename();
                temp[8]=o.getBizusernames();
                chosedList.add(temp);
               
            }
        }
        String[] header = {"序号","拟发文号","文件标题","拟发文时间","经办人","签发人","主送单位","办理步骤","办理人员"};// 列头
        //String[] header = {"序号","发文号","文件标题","主送单位","经办人","签发人","发文时间"};// 列头
       BizCommUtil.doPoi2Excel("拟发文信息", header, chosedList);
    }
    
    /**
     * 导出Excel 根据查询条件 导出当前结果
     * 
     * @throws IOException
     */
    public void exportExcelByPoV2() throws IOException {
        this.listV2();
        List<VDispatchDocList> objectList =new ArrayList<VDispatchDocList>();
        objectList = dispatchDocList;         
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objectList != null) {
            int i = 1;
            for (VDispatchDocList o : objectList) {                
                Object[] temp= new Object[9];
                temp[0]=String.valueOf(i++);
                temp[1]=o.getDispatchDocNo();
                temp[2]=o.getDispatchDocTitle();
                temp[6]=o.getMainNotifyUnit();
                temp[4]=o.getDraftUserName();
                temp[5]=CodeRepositoryUtil.getValue("usercode", o.getDispatchUser()) ;
                temp[3]=DatetimeOpt.convertDateToString(
                        o.getCreateDate(), "yyyy年MM月dd日"); 
                temp[7]=o.getNodename();
                temp[8]=o.getBizusernames();
                chosedList.add(temp);
               
            }
        }
        String[] header = {"序号","拟发文号","文件标题","拟发文时间","经办人","签发人","主送单位","办理步骤","办理人员"};// 列头
        //String[] header = {"序号","发文号","文件标题","主送单位","经办人","签发人","发文时间"};// 列头
       BizCommUtil.doPoi2Excel("拟发文信息", header, chosedList);
    }
    
    
    /**
     * 根据usercode 获取用户行政职务
     * @param usercode
     * @return
     */
    private String getUserRankByUsercode(String  usercode){
        
        
        userUnit =sysUserManager.getUserPrimaryUnit(usercode);
        
        if(null==userUnit){
            userRank=null;
        }else
        {
            String userUnitRank=userUnit.getUserrank();
            if("ZT".equals(userUnitRank)||"FT".equals(userUnitRank)){
            userRank="TZ";//厅长级别
            }else if("ZC".equals(userUnitRank)||"FC".equals(userUnitRank)){
            userRank="CZ";//处长级别
            }else{
            userRank=null;//其他（科员，办事员等）--/科长
            }
        }
        return userRank;
        
    }
    
    /**
     *  //只获取科室一级的部门
     * @return
     */
    private List<FUnitinfo> unitList(){
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
     *  //只获取科室一级的部门
     * @return
     */
    private String ruturnUnitsJson() {
        return unit2JSON(unitList());
    }
    /**
     * 返回部门JSON
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
     * 编辑
     * @return
     */
    public String registerEdit(){
        FUserDetail user = ((FUserDetail)getLoginUser());
      if(user==null){
          return "login";
      }
        
      // 2016-4-11 登记页面会签处室直接选择会签处室经办人 beg
      /* 
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        if(dept!=null){
            
            unitsJson = sysUnitManager.getAllUnitsJSON("1");
            
            FUnitinfo unit = sysUnitManager.getObjectById(dept.getUnitcode());
            
            parentUnit = unit.getParentunit();
            
         } else {
             unitsJson = "{}";
         }*/
       //2016-4-11 登记页面会签处室直接选择会签处室经办人 end
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(user.getPrimaryUnit());
        fuerunit.setUserCode(user.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
     
        this.getPermitInfo();
                
        moduleParam = generalModuleParamManager.getObjectById("fw_dj");// "XKSL"     ---处室负责人  
        this.setFlowInstId(super.getFlowInstId());
        super.initTeamUsersConfig();
        super.initTemplateConfig();       
        
        //2016-4-11 登记页面会签处室直接选择会签处室经办人 beg
        // FW_CSSH 选择会签部门 办公室秘书核稿选择会签部门
        GeneralModuleParam moduleParam2 = generalModuleParamManager
                .getObjectById( "FW_CSSH_exp2" );// 扩展
        request.setAttribute("moduleParam2" , moduleParam2);
        // 第二种办件角色权限表达式 --发文处室负责人审核 指定会签人员
        super.initTeamUsersConfig(moduleParam2, "2");
       //2016-4-11 登记页面会签处室直接选择会签处室经办人 end
        
        
        object.setDraftUserName(StringUtils.isBlank(object.getDraftUserName())?user.getUsername():object.getDraftUserName());
        object.setOptUnitName(fuerunit.getUnitName());  
       
        genGroupId();
        
        //展示保存时候，获取暂存时候的办理人员意见信息
        OptProcInfo optprocinfo=optProcInfoManager.getObjectByNodeInstIdAndDjId(0L, object.getDjId());
        if(optprocinfo!=null){
            transcontent=optprocinfo.getTranscontent();
        }
        //事项关联初始化
        String startDjId = (String)request.getParameter("djId");
        String sd=request.getParameter("startDjId");
        String itemtype=request.getParameter("itemtype");
        if(StringUtils.isNotBlank(sd)){
            startDjId=sd;
            request.setAttribute("sd", sd);
        }
        if(StringUtils.isNotBlank(startDjId)){
            String nodeInstId = (String)request.getParameter("nodeInstId");
            if(StringUtils.isNotBlank(nodeInstId)){
                Long nodeId = Long.parseLong(nodeInstId);
                request.setAttribute("nodeId", nodeId);
                request.setAttribute("nodeInstId", nodeId);
            }
            request.setAttribute("startDjId", startDjId);
            request.setAttribute("itemtype",itemtype );
        }
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        return "register";
    }
    
    public String registerView(){
        this.registerEdit();
        if(object.getFlowInstId()!=null){
        List<WfOrganize> hqUnitCodeSet = flowEngine.viewFlowOrganizeList(object.getFlowInstId(), "zbcshq");
        StringBuffer orgNameBuffer = new StringBuffer();
        if(null!=hqUnitCodeSet&&hqUnitCodeSet.size()>0){
        for (WfOrganize wfOrganize : hqUnitCodeSet) {
            FUnitinfo unit = sysUnitManager.getObjectById(wfOrganize.getUnitCode());
            if (null != unit) {
                orgNameBuffer.append(unit.getUnitname()).append(",");
            }
        }
        } 
       
        selUnitName = orgNameBuffer.toString();
        selUnitName = StringUtils.isBlank(selUnitName) ? "" : selUnitName.substring(0, selUnitName.length()-1);
        }
        return "registerView";
    }
    
    
    
    /**
     * 发文流程发起时保存
     * 
     * @return
     */
    public String saveDispatchDoc() {
        this.saveCommon();
        saveIdeaInfo("拟发文发起",0L);
        //super.subStrUsers()
        return this.list();
    } 
    
    private void createPDF(DispatchDoc dispatchDoc,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        OptPdfInfo optPdfInfo = null;
        try {
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<发文登记办件djId:"+dispatchDoc.getDjId()+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(dispatchDoc.getDjId(), nodeInstId);
           File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(dispatchDoc.getDjId(),nodeInstId,formHtmlUrl);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
            //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(dispatchDoc.getDjId())));
           optPdfInfo.setNodeName(nodeName);
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<发文登记办件djId:"+dispatchDoc.getDjId()+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        } catch (Exception e) {
            log.error("生成PDF异常："+e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
    }
    
    /**
     * 保存并提交发文信息
     * 
     * @return
     */
    public String saveAndSubmitDispatchDoc() {
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        Long flowInstId = optBaseInfo.getFlowInstId();
        if (optBaseInfo.getFlowInstId() == null
                || "".equals(optBaseInfo.getFlowInstId().toString())) {
            String flowCode = optBaseInfo.getFlowCode();
            if (flowCode == null || flowCode.trim().length() == 0) {
                flowCode = suppowerManager.getFlowCodeByOrgItem(
                        optBaseInfo.getPowerid(), fuser.getPrimaryUnit());
            }
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    optBaseInfo.getTransaffairname(),
                    optBaseInfo.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            object.getOptBaseInfo().setBizstate("W");//表示提交等待办理
            
            /**
             * 主动发文、代拟发文 登记时，设定会签汇总办件角色
             */
            super.setFlowInstId(flowInstId);
            
//            super.saveTeamRolepeople(fuser.getUsercode(), "csnw");
            /*
             * 设定会签汇总办件角色
             */
            super.saveTeamRolepeople(fuser.getUsercode(), "fwjbr");
        }
           
        saveDispatchDoc();
        //saveIdeaInfo("发文发起");
        //连续提交节点，直接到科室负责人手里办理
        Set<Long> nextNode = submitNode();
        
        // 发送短信给下一步操作人
        oaSmssendManager.saveFlowMsgs(request.getParameter("isSendMessage"),
                fuser.getUsercode(), nextNode);
        oaSmssendManager.executeSendMsg();
        //启动发文
        String sd=request.getParameter("sd");  
        if(StringUtils.isNotBlank(sd)){
            optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            OaBizBindInfo oaBizBindInfo = new OaBizBindInfo();
            oaBizBindInfo.setStartDjid(sd);
            oaBizBindInfo.setBizType(optBaseInfo.getBiztype());
            oaBizBindInfo.setCreatetime(optBaseInfo.getCreatedate());
            oaBizBindInfo.setCreateuser(optBaseInfo.getCreateuser());
            oaBizBindInfo.setNo(oaBizBindInfoManager.getNextNO("GL"));
            oaBizBindInfo.setNodeinstid(nodeId);
            NodeInstance nodeInst = flowEngine.getNodeInstById(nodeId);
            FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                    .getNodeId());
            String nodeName = nodeInfo.getNodeName();
            oaBizBindInfo.setNodename(nodeName);
            oaBizBindInfo.setEndDjid(object.getDjId());
            oaBizBindInfoManager.saveObject(oaBizBindInfo);
            //跳转至查看已关联发文 dk 2015-12-29
            d_djId=sd;
            d_nodeInstId=nodeId.toString();
            d_itemtype="FW";
            return "refreshAptStartEntrance";
        }

           
        
      //向Rtx推送消息
        saveSms(nextNode);
        
      //开启一个线程
        if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
            final String userCode = fuser.getUsercode();
            final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
            //"***/anonymous***"代表匿名访问
            String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
            if(contextPath.endsWith("/")){
                contextPath = contextPath.substring(0, contextPath.length()-1);
            }
            contextPath = contextPath + request.getContextPath();
            String djId=null==optBaseInfo.getDjId()?object.getDjId():optBaseInfo.getDjId();
            final String formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(contextPath,userCode, djId, String.valueOf(flowInstId));
            
            taskExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    createPDF(object, "拟发文发起", 0L,userCode,exePath,formHtmlUrl);
                }
            });
        }
        return "refreshTasks";
    }

    /****************************** RTX相关内容 ************************************/
    private WfActionTaskDao actionTaskDao;
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
     * 通用业务框架属性执法监督信息查看
     */
     
     public String generalOptView() {
         
         //登录用户信息
         FUserDetail fuser = ((FUserDetail) getLoginUser());
         if(fuser==null){
             return "login";
         }
         
         //查看权限验证 false 不验证 true 验证 
         isVailViewPower=vOptBaseListManager.isVailViewPower(object.getDjId(), fuser.getUsercode());
         request.setAttribute("isVailViewPower", isVailViewPower);
         
         List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
         // 查看办件业务数据信息
         frameList.add(getBizDataViewFrame(object.getDjId()));
         
         frameList.add(super.getCommonStuffFrame(object.getDjId(),
                 null, null));// 附件
         boolean flag=this.getOaBizInfolist(object.getDjId());
         request.setAttribute("hasRelSubject", flag);
         // 用于展示查看详细信息Lab标签内容 原先tab方式显示
       // frameList.add(this.getAllViewFrame(object.getDjId()));
         
         //frameList 页面列表显示
         //frameList=getAllInfoListFrame(frameList,object.getDjId());
         jspInfo = new OptJspInfo();
//         jspInfo.setTitle("发文查看");
         jspInfo.setFrameList(frameList);
         
         request.setAttribute("startDjid", request.getParameter("startDjid"));
        //添加可收藏操作
         OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(object.getDjId());
         object.setFlowInstId(optBaseInfo.getFlowInstId());
         request.setAttribute("optBaseInfo",optBaseInfo);
         OptProcCollection collection = optProcCollectionManager
                 .getObjectById(new OptProcCollectionId(object.getDjId(),
                         ((FUserDetail) getLoginUser())==null?"000000":((FUserDetail) getLoginUser()).getUsercode()));
         if (collection != null && "T".equals(collection.getIsatt())) {
             request.setAttribute("collstatus", "recoll");
         } else {
             request.setAttribute("collstatus", "coll");
         }
       //从首页进入
         if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
             request.setAttribute("dashboard", request.getParameter("dashboard"));
         }
         //添加相邻项
         setNeighbouringDjId();
       //--------------测试
         final String userCode = fuser.getUsercode();
         final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
         //"***/anonymous***"代表匿名访问
         String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
         if(contextPath.endsWith("/")){
             contextPath = contextPath.substring(0, contextPath.length()-1);
         }
         contextPath = contextPath + request.getContextPath();
         
         final String formHtmlUrl = optPdfInfoManager.getFWFormHtmlUrl(contextPath,userCode, object.getDjId(), String.valueOf(optBaseInfo.getFlowInstId()));
         
         taskExecutor.execute(new Runnable(){
             @Override
             public void run() {
                 createPDF(object, "拟发文发起", 0L,userCode,exePath,formHtmlUrl);
             }
         });
         return "generalOptView";
     }

     /**
      * 设置列表页中上一条记录和下一条记录的djId
      */
     @SuppressWarnings("unchecked")
    private void setNeighbouringDjId(){
         //将列表的请求参数
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        List<String> neighbouringDjId = null;
        String fromMenu = filterMap.get("fromMenu") == null ? null : filterMap.get("fromMenu").toString();
        if(fromMenu!=null){
            if("FWBJSCLB".equals(fromMenu)){
                neighbouringDjId = VOptProcCollectionMag.findNeighbouringDjId(filterMap, object.getDjId());
            }
            if("FWDGD".equals(fromMenu)){
                neighbouringDjId = voaUnitArchiveDispatchdocMag.findNeighbouringDjId(filterMap, object.getDjId());
            }
            if("YGDHZ".equals(fromMenu)){//已归档汇总
                neighbouringDjId = oaArchiveMag.findNeighbouringDjId(filterMap, object.getDjId());
            } 
            if("GRBGBJCK".equals(fromMenu)){
                neighbouringDjId = vOptBaseListManager.findNeighbouringDjId(filterMap, object.getDjId());
            }
        }else{
            neighbouringDjId = dispatchDocListManager.findNeighbouringDjId(filterMap, object.getDjId());
        }
       
        if(neighbouringDjId != null && neighbouringDjId.size()==2){
            object.setPrevDjId(neighbouringDjId.get(0));
            object.setNextDjId(neighbouringDjId.get(1));
        }
        setbackSearchColumn(filterMap);
     }
    /* private List<OptHtmlFrameInfo> getAllInfoListFrame(List<OptHtmlFrameInfo> frameList,String djId) {
         
         frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
         frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
         //查关联的源信息
         frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId,"S","nodelete"));//主动发起的关联
         frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId,"E","nodelete"));//被其他事项关联
         //督办催办信息
         Map<String, Object> filterMap = new HashMap<String, Object>();
         filterMap.clear();
         filterMap.put("supDjid", object.getDjId());
         oasuplist=oaSuperviseManager.listObjects(filterMap);
         if(null!=oasuplist&&oasuplist.size()>0){
             frameList.add(OaSuperviseAction.getSupListFrame( object.getDjId())) ;
         }
         return frameList;  
     }*/
    
     /**
      * 用于展示查看详细信息Lab标签内容
      * @param djid
      * @return
      */
    private OptHtmlFrameInfo getAllViewFrame(String djid) {
        String itemType = null;
        if(StringUtils.isNotEmpty(djid)){
            itemType = djid.replaceAll("[0-9]+", "");
        }
         OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
         stuffsFrameInfo.setFrameId("AllInfoFrame");
         stuffsFrameInfo
                .setFrameSrc("/dispatchdoc/dispatchDoc!getAllCaseView.do?djId="
                        + djid + "&nodeInstId=" + curNodeInstId
                        + "&itemType=" + itemType);
         stuffsFrameInfo.setFrameHeight("300px");
         return stuffsFrameInfo;
     } 
     /**
      * 用于展示查看详细信息Lab标签内容
      * @return
     */
        public String getAllCaseView() {
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("djId", object.getDjId());
            filterMap.put("isdisplay", "1");
            optNewlyIdeaList = optNewlyIdeaManager.listObjects(filterMap);
            long nodeId = (long) 0;
            if (curNodeInstId != null && curNodeInstId != 0) {
                NodeInstance nodeInst = flowEngine.getNodeInstById(curNodeInstId);
                nodeId = nodeInst.getNodeId();
            }
            // 用于初始化查看页面默认显示
            setCurUrl("/powerruntime/generalOperator!listIdeaLogs.do?djId="
                    + object.getDjId());
            if (optNewlyIdeaList != null && optNewlyIdeaList.size() > 0
                    && nodeId != 0) {
                for (OptNewlyIdea bean : optNewlyIdeaList) {
                    if (bean.getNodeid() == nodeId) {
                        setCurUrl(bean.getUrl());
                        break;
                    }
                }
            }
          //查询关联业务表中是否存在当前办件的关联信息
            Boolean flag=this.getOaBizInfolist(object.getDjId());
            if(!flag){
                optNewlyIdeaList.add(optNewly_getBizDataViewFrame(object.getDjId()));
            }else{
                optNewlyIdeaList.add(optNewly_getBizDataViewFrame(object.getDjId()));
                optNewlyIdeaList.add(optNewly_genOaBizBindInfoFrame(object.getDjId()));
                //发文关联事务标签标识 by dk 2016-01-13
                request.setAttribute("flag", flag);
            }
           
            object = dispatchDocMgr.getObject(object);
            //add by lay 2015-11-11 空指针报错,这里添加判断
            if(object != null){
             //end modify
                OptBaseInfo inBaseInfo = optBaseInfoManager
                        .getObjectById(object.getDjId());
                if (null == inBaseInfo.getFlowInstId()) {
                    inBaseInfo.setFlowInstId((long) 9999999);
                }
                
                //督办催办信息
                filterMap.clear();
                filterMap.put("supDjid", object.getDjId());
                oasuplist=oaSuperviseManager.listObjects(filterMap);
                request.setAttribute("oasuplist", oasuplist);
               
                request.setAttribute("flowInstId", inBaseInfo.getFlowInstId());
                request.setAttribute("nodeId", nodeId);
            }
            
            return "AllmilitarycaseView";
        }
        /**
         * 封装关联业务信息
         */
        private OptNewlyIdea optNewly_genOaBizBindInfoFrame(String djId) {
            OptNewlyIdea newlyIdea = new OptNewlyIdea();
            newlyIdea.setNodeid((long) 2);
            newlyIdea.setDjId(djId);
            newlyIdea.setNodename("关联业务信息");    
            newlyIdea.setUrl("/oa/oaBizBindInfo!listbiz4tab.do?djid=" + djId);
            return newlyIdea;
        }

        /**
         * 发我业务信息查看
         * @param djId 发文业务编号
         * @return
         */
        private OptNewlyIdea optNewly_getBizDataViewFrame(String djId) {
        // TODO Auto-generated method stub
            OptNewlyIdea newlyIdea = new OptNewlyIdea();
            newlyIdea.setNodeid((long) 1);
            newlyIdea.setDjId(djId);
            newlyIdea.setNodename("发文业务信息");
            newlyIdea.setUrl("/dispatchdoc/dispatchDoc!registerView.do?vewtype=T&djId=" + djId);
            return newlyIdea;
    }

        /**
         *关联事项信息查看
         */
        @SuppressWarnings("unchecked")
        public Boolean getOaBizInfolist(String djId){
            List<OaBizBindInfo> sobjectlist=new ArrayList<OaBizBindInfo>();//主动关联
            List<OaBizBindInfo> eobjectlist=new ArrayList<OaBizBindInfo>();//被动关联
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if(StringUtils.isNotBlank(djId)){//s_startDjid非空时，查相关主动关联的事项
                filterMap.put("startDjid", djId);
                PageDesc pageDesc = makePageDesc();
                sobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
                filterMap.remove("startDjid");
                filterMap.put("endDjid", djId);
                eobjectlist = oaBizBindInfoManager.listObjects(filterMap, pageDesc);
            }
            if((sobjectlist != null && sobjectlist.size() >= 1)||(eobjectlist != null && eobjectlist.size() >= 1)){
                return true;
            }else{
                return false;
            }
        }
     /**
      * 发文业务数据查看
      * @param djid
      * @return
     */
     private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
         OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
         viewFrameInfo.setFrameId("viewFrame");   
         viewFrameInfo.setFrameLegend("公文阅批单");
         viewFrameInfo
            .setFrameSrc("/dispatchdoc/dispatchDoc!viewDispatchDocInfo.do?vewtype=T&djId=" + djid);
         viewFrameInfo.setFrameHeight("700px");
         return viewFrameInfo;
     }    
    /**--------Temp 发文 lq End-----------   **/
    
    
    
    /**
     * 
     * 未提交编辑
     * 
     * @return
     */
    public String edit() {
        try {
            request.setAttribute("flowCode", optBaseInfoManager.getObjectById(object.getDjId()).getFlowCode());
            
            optCommonBizJson = dispatchDocMgr.getOptBaseInfoJSONByDjId(object.getDjId());
            /**
             * 根据是否可以生产公文 ，确定需要编辑的文档模板
             */
            moduleParam = new  GeneralModuleParam ();
            moduleParam.setHasDocument("T");
            moduleParam.setDocumentType("zw");
            super.initTemplateConfig();
            
            return "edit";
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }
    
    
    
    
    private void saveOptStuffFileName() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("djId", object.getDjId());
        paramMap.put("archivetype", "zw");
        List<OptStuffInfo> stuffInfoList = optStuffInfoManager.listObjects(paramMap);
        
        for (int i=0; i<stuffInfoList.size(); i++) {
            OptStuffInfo stuffInfo = stuffInfoList.get(i);
            stuffInfo.setFilename(object.getDispatchDocTitle() + ".doc");
            
            optStuffInfoManager.saveObject(stuffInfo);
        }
    }
    
    
    /**
     * 获取optBaseInfo信息
     * @author hll 2013-12-2 epowersd
     */
    public OptBaseInfo getOptBaseInfoByDjId(){
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
        if(null == optBaseInfo){
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(object.getDjId());
        }
        return optBaseInfo;
    }
    
    /**
     * 
     * 获取dispatchDoc,incomeProject以及其他信息
     * @author hll 2013-12-3 epowersd
     * @return
     */
    public void getDispatchDocAndProjectInfo() {
        String djId = object.getDjId();
        if(super.getFlowInstId()==null){
            OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(djId);
            super.setFlowInstId(optBaseInfo.getFlowInstId());
        }
        DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(djId);
        
        if (null != dispatchDoc) {
            dispatchDocMgr.copyObjectNotNullProperty(dispatchDoc, object);
            
            object = dispatchDoc;
        } else {
            object.setDraftDate(DatetimeOpt.currentUtilDate());
            object.setDraftUserName(((FUserDetail) getLoginUser()).getUsername());
        }
        
        /* 会签处室开始 */
        String unitString = "";
        
        Set<String> unitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "zbcshq");//会签处室
        StringBuffer orgNameBuffer = new StringBuffer();
        if (!unitCodeSet.isEmpty()) {
            object.setIsCountersign("1");
            
            StringBuffer unitStringBuffer = new StringBuffer("");
            
            for (String unitCode : unitCodeSet) {
                FUnitinfo unit = sysUnitManager.getObjectById(unitCode);
                unitStringBuffer.append(unitCode).append(",");
                orgNameBuffer.append(unit.getUnitname()).append(",");
            }
            
            unitString = unitStringBuffer.substring(0, unitStringBuffer.length() - 1);
        }
        selUnitName = orgNameBuffer.toString();
        selUnitName = StringUtils.isBlank(selUnitName) ? "" : selUnitName.substring(0, selUnitName.length()-1);
        
        request.setAttribute("editIsCountersign", unitCodeSet.isEmpty() ? "" : "1");
        
        selUnitCode = unitString;
        request.setAttribute("unitString", unitString);
        request.setAttribute("selUnitName", selUnitName);
        request.setAttribute("selUnitCode", selUnitCode);
        
        
        //saveFb2Hq();
        
        List<FUnitinfo> hqUnitList = new ArrayList<FUnitinfo>();
        Set<String> hqUnitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "zbcshq"); // 已选择的会签单位
        
        if (!hqUnitCodeSet.isEmpty()) { // 获取所有的会签单位
            for (String unitCode : hqUnitCodeSet) {
                hqUnitList.add(sysUnitManager.getObjectById(unitCode));
            }
        }
        
        if (unitCodeSet.isEmpty()) { // 如果没有会办分办单位，则重置所有会签单位状态
            for (int i=0; i<hqUnitList.size(); i++) {
                hqUnitList.get(i).setIsvalid("");
            }
        } else { // 如果存在会办分办单位，则将会办分办单位设置状态位“T”，其余为空
            for (int i=0; i<hqUnitList.size(); i++) {
                hqUnitList.get(i).setIsvalid("");
                for (String unitCode : unitCodeSet) {
                    if (hqUnitList.get(i).getUnitcode().equals(unitCode)) {
                        hqUnitList.get(i).setIsvalid("T");
                        break;
                    }
                }
            }
        }
        
        unitList = hqUnitList;
        /* 会签处室结束 */
        
        /* 主办处室选择开始 */
        String unitName = "";
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
        
        if (StringUtils.isNotBlank(optBaseInfo.getHeadunitcode())) {
            unitName = sysUnitManager.getObjectById(optBaseInfo.getHeadunitcode()).getUnitname();
        }
        
        request.setAttribute("zbcs", unitName);
        object.setOptUnitName(unitName);
        /* 主办处室选择结束 */
        
        
        
//        request.setAttribute("incomeDocList", getRelativeIncomes(djId)); /* 关联收文 */
        
        /* 主送、抄送单位开始 */
        
        /*
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("djId", object.getDjId());
        paramMap.put("unitType", "1");
        List<DocSend> docSendList = docSendMgr.listObjects(paramMap); // 查询所有已经存在的单位
        
        String mainUnitCode = "";
        String mainUnitName = "";
        String otherUnitCode = "";
        String otherUnitName = "";
        
        if(null!=docSendList&&docSendList.size()>0){
        for (int i=0; i<docSendList.size(); i++) {
            DocSend docSend = docSendList.get(i);
            ApplyUnitInfo applyUnit = applyUnitInfoMgr.getObjectById(docSend.getUnitcode());
            if ("1".equals(docSend.getSendType())) { // 主送单位只有一个
                mainUnitCode += applyUnit.getUnitcode() + ",";
                mainUnitName += applyUnit.getUnitname() + ",";
            } else if ("2".equals(docSend.getSendType())) {
                otherUnitCode += applyUnit.getUnitcode() + ",";
                otherUnitName += applyUnit.getUnitname() + ",";
            }
        }
        }
        mainUnitCode = StringUtils.isBlank(mainUnitCode) ? mainUnitCode : mainUnitCode.substring(0, mainUnitCode.length()-1);
        mainUnitName = StringUtils.isBlank(mainUnitName) ? mainUnitName : mainUnitName.substring(0, mainUnitName.length()-1);
        otherUnitCode = StringUtils.isBlank(otherUnitCode) ? otherUnitCode : otherUnitCode.substring(0, otherUnitCode.length()-1);
        otherUnitName = StringUtils.isBlank(otherUnitName) ? otherUnitName : otherUnitName.substring(0, otherUnitName.length()-1);
        request.setAttribute("mainUnitCode", mainUnitCode);
        request.setAttribute("mainUnitName", mainUnitName);
        request.setAttribute("otherUnitCode", otherUnitCode);
        request.setAttribute("otherUnitName", otherUnitName);
        */
        /* 主送、抄送单位结束 */
    }
    
    
    public List<IncomeDoc> getRelativeIncomes(String dispatchNo) {
        docRelativeList = docRelativeMgr.getObjectsByDispatchNo(dispatchNo);
        
        if (!docRelativeList.isEmpty()) {
            for (int i=docRelativeList.size()-1; i>=0; i--) {
                if (docRelativeList.get(i).getIncomeNo().equals(docRelativeList.get(i).getDispatchNo())) {
                    docRelativeList.remove(i); // 移除当前收文(项目登记)
                    break;
                }
            }
        }
        
        List<IncomeDoc> incomeDocList = new ArrayList<IncomeDoc>();
        if (!docRelativeList.isEmpty()) {
            for (int i=0; i<docRelativeList.size(); i++) {
                IncomeDoc incomeDoc = incomeDocMgr.getObjectById(docRelativeList.get(i).getCid().getIncomeNo());
                incomeDoc.setOptBaseInfo(optBaseInfoManager.getObjectById(incomeDoc.getDjId()));
                incomeDocList.add(incomeDoc);
            }
        }
        
        return incomeDocList;
    }
    
    
    /**
     * 
     * 建设项目流程，项目登记转拟文
     * @author hll 2013-12-3 epowersd
     * @return
     */
    public String registerProjectEdit() {
        try {
            //String djId = object.getDjId();
            
         /*   if (null != incomeDocMgr.getObjectById(djId)) {
                DocRelativeId cid = new DocRelativeId();
                cid.setIncomeNo(djId);
                cid.setDispatchNo(djId);
                
                if (null == docRelativeMgr.getObjectById(cid)) {
                    DocRelative docRelative = new DocRelative();
                    DocRelativeId id = new DocRelativeId();
                    id.setDispatchNo(djId);
                    id.setIncomeNo(djId);
                    docRelative.setCid(id);
                    docRelative.setRelativeType("B");
                  
                    docRelativeMgr.saveObject(docRelative);
                    
                    Set<String> unitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "hbcsfb");
                    
                    if (!unitCodeSet.isEmpty()) {
                        for (String unitCode : unitCodeSet) {
                            flowEngine.assignFlowOrganize(super.curFlowInstId, "zbcshq", unitCode);
                        }
                    }
                }
                
                object.setIncomeNo(djId);
            }*/
            
            this.getDispatchDocAndProjectInfo();
            
            optCommonBizJson = dispatchDocMgr.getOptBaseInfoJSONByDjId(object.getDjId());
            /**
             * 根据是否可以生产公文 ，确定需要编辑的文档模板
             */
            moduleParam = new  GeneralModuleParam ();
            moduleParam.setHasDocument("T");
            moduleParam.setDocumentType("zw");
            super.initTemplateConfig();
//            FUserDetail user = ((FUserDetail)getLoginUser());
//            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            request.setAttribute("unitsJson", sysUnitManager.getAllUnitsJSON("1"));
            
            return "edit";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ERROR;
        }
    }
    
   
    
    private void getPermitInfo() {

        object = dispatchDocMgr.getObjectById(object.getDjId());

        if (object != null) {
           
            // System.out.println(optBaseInfoJson);
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            object.setOptBaseInfo(optBaseInfo);
            
            optId = optBaseInfo.getOptId();

      
        } else {
            object = new DispatchDoc();
            // 生成登记编号
            object.setDjId(dispatchDocMgr.getNextkey());

            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setOptId(request.getParameter("optId"));
           
            // 生成办件编号：编号规则以JTHD打头+时间戳
            String no = "XJ0113-"
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(
                            System.currentTimeMillis()));
            optBase.setTransAffairNo(no);
            optBase.setFlowCode(request.getParameter("flowCode"));
            //optBase.setFlowInstId(12l);
            //获取权利编码
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            VOrgSupPower vOrgSupPower=suppowerManager.getSupPowerInfo(request.getParameter("itemId"), fuser.getPrimaryUnit());
            if(StringUtils.isBlank(vOrgSupPower.getItemId())){
                //modify by lay 2015-11-11 还没有返回到页面，先将流打到前台了
                //super.postAlertMessage("操作失败，请检查权力部门配置！", response);
                super.saveMessage("操作失败，请检查权力部门配置！");
                //end modify
            }
            
            optBase.setPowerid(vOrgSupPower.getItemId());
            optBase.setPowername(vOrgSupPower.getItemName());
            object.setOptBaseInfo(optBase);
            object.setItemId(request.getParameter("itemId"));
            object.setInternalNo(no);
        }
    }
    
    
    public String saveFb2Hq() {
        List<String> orgCodeList = new ArrayList<String>();
        String orgCode = request.getParameter("orgCode");
        if (StringUtils.isNotBlank(orgCode)) {
            String[] orgCodes = orgCode.split(",");
            for (int i=0; i<orgCodes.length; i++) {
                orgCodeList.add(orgCodes[i]);
            }
        }
        Set<String> fbUnitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "hbcsfb");
        Set<String> hqUnitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "zbcshq");
        
        List<String> addList = new ArrayList<String>();
        List<String> deleteList = new ArrayList<String>();
        
        for (String unitCode : hqUnitCodeSet) {
            if (!fbUnitCodeSet.contains(unitCode) && !orgCodeList.contains(unitCode)) {
                deleteList.add(unitCode);
            }
        }
        for (String unitCode : orgCodeList) {
            if (!fbUnitCodeSet.contains(unitCode) && !hqUnitCodeSet.contains(unitCode)) {
                addList.add(unitCode);
            }
        }
        for (String unitCode : fbUnitCodeSet) {
            if (!hqUnitCodeSet.contains(unitCode)) {
                addList.add(unitCode);
            }
        }
        
        Set<String> set = new HashSet<String>();
        set.addAll(addList);
        flowEngine.assignFlowOrganize(super.getFlowInstId(), "zbcshq", set);
        
        for (String unitCode : deleteList) {
            flowEngine.deleteFlowOrganize(super.getFlowInstId(), "zbcshq", unitCode);
        }
        
        hqUnitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "zbcshq");
        
        StringBuffer orgCodeBuffer = new StringBuffer();
        StringBuffer orgNameBuffer = new StringBuffer();
        
        for (String unitCode : hqUnitCodeSet) {
            FUnitinfo unit = sysUnitManager.getObjectById(unitCode);
            if (null != unit) {
                orgCodeBuffer.append(unit.getUnitcode()).append(",");
                orgNameBuffer.append(unit.getUnitname()).append(",");
            }
        }
       
        selUnitCode = orgCodeBuffer.toString();
        selUnitName = orgNameBuffer.toString();
        
        selUnitCode = StringUtils.isBlank(selUnitCode) ? "" : selUnitCode.substring(0, selUnitCode.length()-1);
        selUnitName = StringUtils.isBlank(selUnitName) ? "" : selUnitName.substring(0, selUnitName.length()-1);
        
        return "";
    }
    
    
    /**
     * 用于在选择主送单位、抄送单位时，对手动输入的结果进行查找匹配（整理其中查找所得的项，弃没有查找的结果，对于多个匹配项时，只取第一个）
     * @return
     */
    public String getSelectedUnitsAjax() {
        String responseText = "";
        try {
            String names = request.getParameter("unitNames");
            String[] unitNames = new String(names.getBytes("ISO-8859-1"), "GBK").split(",");
            
            List<String> unitCodeList = new ArrayList<String>();
            List<String> unitNameList = new ArrayList<String>();
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            for (int i=0; i<unitNames.length; i++) {
                paramMap.put("unitname", unitNames[i]);
                List<ApplyUnitInfo> unitList = applyUnitInfoMgr.listObjects(paramMap);
                
                if (!unitList.isEmpty() && !unitCodeList.contains(unitList.get(0).getUnitcode())) {
                    unitCodeList.add(unitList.get(0).getUnitcode());
                    unitNameList.add(unitList.get(0).getUnitname());
                }
            }
            
            responseText = unitCodeList.isEmpty() ? "" : Arrays.toString(unitCodeList.toArray()) + "," + Arrays.toString(unitNameList.toArray());
            
        } catch (Exception e) {
            log.info(e);
        } finally {
            super.ajaxResponseText(response, responseText);
        }
        
        return null;
    }
    

    public String saveDispatchDocByIncomeDoc() {
        String retVal = request.getParameter("retVal");
        try {
            DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(object.getDjId());
            OptBaseInfo optBaseInfo = this.getOptBaseInfoByDjId();
            
            if (null == dispatchDoc) {
                object.setCreateDate(new Date(System.currentTimeMillis()));
                object.setItemId(optBaseInfo.getPowerid());
            } else {
                dispatchDocMgr.copyObjectNotNullProperty(dispatchDoc, object);
                
                object = dispatchDoc;
            }
            
            object.setInternalNo(object.getDjId());
            object.setUpdateDate(new Date(System.currentTimeMillis()));
            
            dispatchDocMgr.saveObject(object);
            //拟文修改时候更新基本信息表办件名称
            optBaseInfo.setTransaffairname(object.getDispatchDocTitle());
            optBaseInfoManager.saveObject(optBaseInfo);
            this.saveOptStuffFileName();            

        } catch (Exception e) {
            e.printStackTrace();
//            log.error(e.getMessage());
        }
        if ("view".equals(retVal)) {
            this.viewDispatchDocInfo();
            
            return VIEW;
        }
        
        return null;
    }
    
    public String saveAndSubmitFW(){
        saveFW();
        saveIdeaInfo("修改登记",0L);    
        submitNode();
        return "refreshTasks";
    }
    
    public String saveFW(){        
        this.saveCommon();
        return "refreshTasks";
    }
    
    public String registerFWEdit(){
        FUserDetail user = ((FUserDetail)getLoginUser());
        
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        if(dept!=null){
            
            unitsJson = sysUnitManager.getAllUnitsJSON("1");
            
            FUnitinfo unit = sysUnitManager.getObjectById(dept.getUnitcode());
            
            parentUnit = unit.getParentunit();
            
         } else {
             unitsJson = "{}";
         }
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(user.getPrimaryUnit());
        fuerunit.setUserCode(user.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        String unitString = "";
        
        Set<String> unitCodeSet = flowEngine.viewFlowOrganize(super.getFlowInstId(), "zbcshq");
        StringBuffer orgNameBuffer = new StringBuffer();
        if (!unitCodeSet.isEmpty()) {
            object.setIsCountersign("1");
            
            StringBuffer unitStringBuffer = new StringBuffer("");
            
            for (String unitCode : unitCodeSet) {
                FUnitinfo unit = sysUnitManager.getObjectById(unitCode);
                unitStringBuffer.append(unitCode).append(",");
                orgNameBuffer.append(unit.getUnitname()).append(",");
            }
            
            unitString = unitStringBuffer.substring(0, unitStringBuffer.length() - 1);
        }
        selUnitName = orgNameBuffer.toString();
        selUnitName = StringUtils.isBlank(selUnitName) ? "" : selUnitName.substring(0, selUnitName.length()-1);
        selUnitCode = unitString;
        
        this.getPermitInfo();
        moduleParam = new  GeneralModuleParam ();
        moduleParam.setHasDocument("T");
        moduleParam.setDocumentType("zw");
        super.initTemplateConfig();
        object.setDraftUserName(StringUtils.isBlank(object.getDraftUserName())?user.getUsername():object.getDraftUserName());
        object.setOptUnitName(fuerunit.getUnitName());
        genGroupId();
        return "registerFWEdit";
    }
    
    
    public String viewDispatchDocInfo() {
        try {
            //登录用户信息
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            //该参数用来从容器之外的请求访问的，请求不是从浏览器发出来的，虽然我们可以劫持session，但是webservice访问时，
             //不知道哪个session是有效的，所以这里还是手动配置权限框架不拦截1、spring security 2、AccessFilter
            String usercode = request.getParameter("usercode");
            if(fuser==null && StringUtils.isBlank(usercode)){
                return "login";
            }else{
                if(StringUtils.isBlank(usercode)){
                    usercode = fuser.getUsercode();    
                }
            }
            
            //基本信息
            DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(object.getDjId());
            if (dispatchDoc != null) {
                baseEntityManager.copyObject(object, dispatchDoc);
                object.setOptBaseInfo(super.getOptBaseInfo());
            }else{
                dispatchDoc = new DispatchDoc();
            }
            List<WfOrganize> hqUnitCodeSet =new ArrayList<WfOrganize>();
            if(super.getFlowInstId()==null){
                OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(object.getDjId());
                if(null!=optBaseInfo){
                    super.setFlowInstId(optBaseInfo.getFlowInstId());
                }
            }else{
                hqUnitCodeSet = flowEngine.viewFlowOrganizeList(super.getFlowInstId(), "zbcshq");    
            }
            
            StringBuffer orgNameBuffer = new StringBuffer();
            if(null!=hqUnitCodeSet&&hqUnitCodeSet.size()>0){
            for (WfOrganize wfOrganize : hqUnitCodeSet) {
                FUnitinfo unit = sysUnitManager.getObjectById(wfOrganize.getUnitCode());
                if (null != unit) {
                    orgNameBuffer.append(unit.getUnitname()).append(",");
                }
            }
            } 
           
            selUnitName = orgNameBuffer.toString();
            selUnitName = StringUtils.isBlank(selUnitName) ? "" : selUnitName.substring(0, selUnitName.length()-1);
            
            request.setAttribute("hqIdeacode", (null == hqUnitCodeSet || hqUnitCodeSet.isEmpty()) ? "T" : "H");
            
            request.setAttribute("lhIdeacode", "1".equals(dispatchDoc.getIsUnionDispatch()) ? "H" : "T");
            
            // 设置在办件的超时提醒
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("djId", object.getDjId());
            List<VDispatchDocList> list = dispatchDocListManager.listObjects(filterMap);
            if(null != list && !list.isEmpty()){
                VDispatchDocList doc = list.get(0);
                if("W".equals(doc.getBizstate())){
                    object.setOverdueType(BizCommUtil.getOverDueState(object.getToBeanfinishedDate()));                    
                }
                request.setAttribute("bizstate", doc.getBizstate());
            }
          //归档信息
            Map<String,Object> cdtn = new HashMap<String,Object>();
            cdtn.put("no", object.getDjId());
            List<OaArchive> oaArchives = oaArchiveMag.listObjects(cdtn);
            if(oaArchives==null || oaArchives.size()==0){
                request.setAttribute("hasArchived", false);
            }else{
                request.setAttribute("hasArchived", true);
                request.setAttribute("titanic",oaArchives.get(0).getTitanic());
                request.setAttribute("archtiveDate", DateUtil.date2String(oaArchives.get(0).getFilingdate(), "yyyy.MM.dd"));
            }
          
            //加载意见和附件
            //判断是否需要验证
            isVailViewPower=vOptBaseListManager.isVailViewPower(object.getDjId(), usercode);
            writeIdeaModuleToRequestAttribute(object.getDjId(),isVailViewPower);
            wirteStuffInfoToRequestAttrivute(object.getDjId(),isVailViewPower);
            return VIEW;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ERROR;
        }
    }
    
    public String viewDispatchDocInfoTemplate() {
        viewDispatchDocInfo();
        return "viewTemplate";
        
    }
    private void writeIdeaModuleToRequestAttribute(String djId,boolean needValid){
        String isPdf = request.getParameter("isPdf");
        //定义需要获取的流程节点，这一步可以配置的，暂时先这样，需要好好设计设计
        final String[]MODULE_CODE_ARR = new String[]{"fw_dj","FW_CSSH","FW_LDQF","FW_BGSFS","FW_FGLDHQ","FW_BGSMS","FW_BGSFS","FW_PB","FW_ZWH"};
      
        //如果需要验证，那么检测是否有查看意见的权限  
        boolean hasViewPower = CodeRepositoryUtil.checkUserOptPower("DOCXZ","DocViewAll");
        if(!needValid || (needValid && hasViewPower)){
           for(String moduleCode : MODULE_CODE_ARR){
                   String idea = ideaTempletManager.getIdea(djId, moduleCode);
                   //     如果是生成PDF    保留 SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);//内网地址 
                   //                  否则  只保留上下文 /oa_szgh
                   String relaceTag=SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
                   String contextPath="T".equals(isPdf)?relaceTag:"";
                        
                    
                   request.setAttribute(moduleCode+"_IDEA", idea.replace(relaceTag, contextPath));
           }
        }
    }
    
    private void wirteStuffInfoToRequestAttrivute(String djId,boolean needValid){
        boolean hasViewPower = CodeRepositoryUtil.checkUserOptPower("DOCXZ","DocViewStuff");
        if(!needValid || (isVailViewPower && hasViewPower)){
            List<OptStuffInfo> optStuffs = optProcInfoManager.listStuffsByDjId(djId);
            if(optStuffs != null){
                request.setAttribute("optStuffs", optStuffs);
            }
        }
    }
    /**
     * 收文子流程：1、收文后，获取收文信息封装并流转到发文界面 2、提交发文信息进入发文流程、
     * 
     * @return
     */
    public String toDispatchDocFlow() {
        String incomeNo = object.getDjId();
        object.setDjId(dispatchDocMgr.getNextkey());

        request.setAttribute("incomeNo", incomeNo);
        return "IODocSubFlow";
    }

    /**
     * 查询收发文关系表，列出关联的收文
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String docRelativeList() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        // 发文号
        String dispatchNo = request.getParameter("dispatchNo");
        request.setAttribute("dispatchNo", dispatchNo);

        PageDesc pageDesc = makePageDesc();

        incomeDocList = incomeDocListManager.getDocRelativeList(dispatchNo);

        totalRows = pageDesc.getTotalRows();

        request.setAttribute("docRelativeFrameType", docRelativeFrameType);

        this.pageDesc = pageDesc;
        return "cList";
    }

    public String docRelativeSave() {
        JSONObject json = new JSONObject();

        try {
            String dispatchNo = request.getParameter("dispatchNo");
            String incomeNo = request.getParameter("incomeNo");
            
            List<String> incomeNoList = new ArrayList<String>();
            
            String[] incomeNos = null;
            if (incomeNo.indexOf(",") > -1) {
                incomeNos = incomeNo.split(",");
                for (int i=0; i<incomeNos.length; i++) {
                    incomeNoList.add(incomeNos[i]);
                }
            } else {
                incomeNoList.add(incomeNo);
            }

            List<DocRelative> docRelativeList = docRelativeMgr.getObjectsByDispatchNo(dispatchNo);
            
            for (int i=docRelativeList.size()-1; i>=0; i--) {
                DocRelativeId id = docRelativeList.get(i).getCid();
                if (id.getDispatchNo().equals(id.getIncomeNo())) { // 移除
                    docRelativeList.remove(i);
                }
                
                for (int j=incomeNoList.size()-1; j>=0; j--) {
                    if (id.getIncomeNo().equals(incomeNoList.get(j))) {
                        incomeNoList.remove(j);
                    }
                }
            }

            List<DocRelative> docRelativeAddList = new ArrayList<DocRelative>();
            for (int i=incomeNoList.size()-1; i>=0; i--) {
                if (StringUtils.isNotBlank(incomeNoList.get(i))) {
                    DocRelative docRelative = new DocRelative();
                    DocRelativeId cid = new DocRelativeId();
                    cid.setDispatchNo(dispatchNo);
                    cid.setIncomeNo(incomeNoList.get(i));
                    docRelative.setCid(cid);
                    docRelative.setRelativeType("A");

                    docRelativeAddList.add(docRelative);
                }
            }

            for (int i=docRelativeList.size()-1; i >= 0; i--) {
                docRelativeMgr.deleteObject(docRelativeList.get(i));
            }
            
            for (int i=docRelativeAddList.size()-1; i>=0; i--) {
                docRelativeMgr.saveObject(docRelativeAddList.get(i));
            }

            json.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }
    
    public String getDocRelatives() {
        JSONObject json = new JSONObject();

        try {
            String dispatchNo = request.getParameter("dispatchNo");
            
            List<IncomeDoc> incomeDocList = this.getRelativeIncomes(dispatchNo);
            
            JSONArray jsonArr = new JSONArray();
            for (int i=0; i<incomeDocList.size(); i++) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("dId", incomeDocList.get(i).getDjId());
                String transAffairNo = incomeDocList.get(i).getOptBaseInfo().getTransAffairNo();
                jsonObj.put("tNo", StringUtils.isBlank(transAffairNo) ? "" : transAffairNo);
                String transaffairname = incomeDocList.get(i).getOptBaseInfo().getTransaffairname();
                jsonObj.put("tName", StringUtils.isBlank(transaffairname) ? "" : transaffairname);
                jsonArr.add(jsonObj);
            }
            json.put("jsonData", jsonArr.toString());
            json.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }
    
    public String deleteDocRelative() {
        JSONObject json = new JSONObject();

        try {
            DocRelativeId cid = new DocRelativeId();
            cid.setDispatchNo(request.getParameter("dispatchNo"));
            cid.setIncomeNo(request.getParameter("incomeNo"));
            
            docRelativeMgr.deleteObjectById(cid);
            
            json.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }

    /**
     * 查询收发文关系表，列出收文
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String incomeDocList() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        String dispatchNo = request.getParameter("dispatchNo");

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put(CodeBook.SELF_ORDER_BY, "update_date desc");
        PageDesc pageDesc = makePageDesc();
        incomeDocList = incomeDocListManager.listObjects("From VIncomeDocList where djId<>'" + dispatchNo + "'", filterMap, pageDesc);
        
        totalRows = pageDesc.getTotalRows();

        request.setAttribute("dispatchNo", dispatchNo);

        this.pageDesc = pageDesc;
        return "rList";
    }

    /**
     * 删除收发文关系
     * 
     * @return
     */
    public String docRelativeDel() {
        JSONObject json = new JSONObject();

        try {
            DocRelativeId id = new DocRelativeId();
            id.setDispatchNo(request.getParameter("dispatchNo"));
            id.setIncomeNo(request.getParameter("incomeNo"));

            DocRelative docRelative = docRelativeMgr.getObjectById(id);

            if (StringUtils.isNotBlank(docRelative.getCid().getDispatchNo())
                    && StringUtils.isNotBlank(docRelative.getCid()
                            .getIncomeNo())) {
                docRelativeMgr.deleteObject(docRelative);
            }

            json.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", "failed");
        } finally {
            super.ajaxResponseJson(response, json);
        }

        return null;
    }
   //办件删除
   public String deleteObject(){
       
      return  this.delete();
   }
   
    public String delete() {
        try {
            super.delete();
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(object.getDjId());
         //   List<DocRelative> docRelativeList = docRelativeMgr.getObjectsByDispatchNo(object.getDjId());
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("djId", object.getDjId());
          // List<DocSend> docSendList = docSendMgr.listObjects(paramMap);
            List<OptStuffInfo> stuffInfoList = optProcInfoManager.listStuffsByDjId(object.getDjId());
            
       
            if (dispatchDoc != null) {
                dispatchDocMgr.deleteObject(dispatchDoc);
            }
//            if (docRelativeList != null) {
//                for (int i=0; i<docRelativeList.size(); i++) {
//                    docRelativeMgr.deleteObject(docRelativeList.get(i));
//                }
//            }
//            for (int i=0; i<docSendList.size(); i++) {
//                docSendMgr.deleteObject(docSendList.get(i));
//            }
            if(stuffInfoList!=null && stuffInfoList.size()>0){
            for (int i=0; i<stuffInfoList.size(); i++) {
                optProcInfoManager.deleteStuffInfo(stuffInfoList.get(i));
            }
            }
            if (optBaseInfo != null) {
                optBaseInfoManager.deleteObject(optBaseInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        
        return this.list();
    }

    


    /**
     * 已提交编辑
     * 
     * @return
     */
    public String editDispatchDocInfo() {
        try {
            if (LIST.equals(editDispatchDoc())) {
                return LIST;
            } else {
                // 查询关联的收文
                docRelativeList();
                return "procEdit";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 
     * @return
     */
    public String editDispatchDoc() {

        DispatchDoc o = baseEntityManager.getObject(object);
        if (object == null) {

            return LIST;
        }
        if (o != null)
            baseEntityManager.copyObject(object, o);
        object.setOptBaseInfo(super.optBaseInfoManager.getObjectById(o
                .getDjId()));
        this.initCommonBizJSON(o);
        return "fEdit";

    }

    public String startDispatchDoc() {

        String djId = dispatchDocMgr.getNextkey();
        object.setDjId(djId);
      
        /**
         * 根据是否可以生产公文 ，确定需要编辑的文档模板
         */
        moduleParam = new  GeneralModuleParam ();
        moduleParam.setHasDocument("T");
        moduleParam.setDocumentType("zw");
        super.initTemplateConfig();
        
        return "startDispatchDoc";
    }

    /**
     * 办理步骤时保存
     * 
     * @return
     */
    public String saveTransDispatch() {
        this.saveCommon();
        return "procEdit";
    }



    private void saveCommon() {
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        FUserDetail user = ((FUserDetail)getLoginUser());
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(user.getPrimaryUnit());
        fuerunit.setUserCode(user.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        // 创建基本信息
        //System.out.println(baseInfo+"========"+baseInfo.getDjId());
        if (baseInfo == null || baseInfo.getCreatedate() == null) {
            // String djId = dispatchDocMag.getNextkey();
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setFlowInstId(super.getFlowInstId());
            if (optBaseInfo.getFlowInstId() == null
                    || "".equals(optBaseInfo.getFlowInstId().toString())) {
                optBaseInfo.setBizstate("F");// 未提交标志
            }
            optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            optBaseInfo.setCreateuser(fuerunit.getUserCode());
            optBaseInfo.setOrgcode(fuerunit.getUnitCode());
            optBaseInfo.setOrgname(fuerunit.getUnitName());
            optBaseInfo.setHeadunitcode(fuerunit.getUnitCode());
            optBaseInfo.setHeadusercode(fuerunit.getUserCode());
            optBaseInfo.setFlowCode(object.getOptBaseInfo().getFlowCode());
//            optBaseInfo.setTransAffairNo(object.getDjId());
            object.setCreateDate(new Date(System.currentTimeMillis()));
            object.setUpdateDate(new Date(System.currentTimeMillis()));
        } else {
            // 修改基本信息
            DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(object
                    .getDjId());
            dispatchDocMgr.copyObjectNotNullProperty(dispatchDoc, object);

            object = dispatchDoc;

            optBaseInfoManager.copyObjectNotNullProperty(baseInfo, optBaseInfo);
            optBaseInfo = baseInfo;

            object.setUpdateDate(new Date(System.currentTimeMillis()));
        }
        //flowEngine.assignFlowWorkTeam();

        // 部门内部事项编号
        object.setInternalNo(optBaseInfo.getDjId());

        // 权力编码
        object.setItemId(optBaseInfo.getPowerid());
       
        object.setFlowInstId(super.curFlowInstId);
        
       
        
        dispatchDocMgr.saveObject(object);
        this.saveOptStuffFileName();

        optBaseInfo.setTransaffairname(object.getDispatchDocTitle());
        //保存缓急程度
        optBaseInfo.setCriticalLevel(object.getCriticalLevel());
        optBaseInfoManager.saveObject(optBaseInfo);
        
        if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
            flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
            flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
        }
        
     //   flowEngine.deleteFlowOrganize(super.getFlowInstId(), roleCode);
        
        if(null!=request.getParameter("orgCode")){
            String[] orgCodes = request.getParameter("orgCode").split(",");
            if (orgCodes != null && orgCodes.length > 0 && super.getFlowInstId() != null) {
                flowEngine.assignFlowOrganize(super.getFlowInstId(), roleCode, new HashSet<String>(Arrays.asList(orgCodes)));
            }
        }
        
        // 清空会签人员
        if (null != optBaseInfo.getFlowInstId()
                && optBaseInfo.getFlowInstId() > 0) {
            flowEngine.deleteFlowWorkTeam(super.getFlowInstId(), peopleRoleCode);
        }
        // 会签人员纳入工作组
        if (null != request.getParameter("userCodes")) {
            String[] userCodes = request.getParameter("userCodes").split(",");
            if (userCodes != null && userCodes.length > 0
                    && super.getFlowInstId() != null) {
                flowEngine.assignFlowWorkTeam(super.getFlowInstId(),
                        peopleRoleCode,
                        new HashSet<String>(Arrays.asList(userCodes)));

            }
        }
        
     // 办件角色纳入权限引擎
        teamRoleCode = request.getParameter("teamRoleCode");
        if (null != optBaseInfo.getFlowInstId()
                && optBaseInfo.getFlowInstId() > 0 && null!=teamRoleCode) {
            super.saveTeamRolepeople();
        }
    }

    private String ideacode;

    public String getIdeacode() {
        return ideacode;
    }

    public void setIdeacode(String ideacode) {
        this.ideacode = ideacode;
    }

    /**
     * 收发文子流程提交，发文作为子流程，需要组织并提交收文的节点，然后进入子流程
     * 
     * @return
     */
    public String submitAsSubFlow() {

        // 提交收文节点
        Set<Long> parentNode = super.submitNode();

        // 系统自动创建流程实例
        if (parentNode != null && parentNode.size() > 0) {
            Long parentNodeInstId = parentNode.iterator().next();

            // 根据收文子流程节点的实例编号获取子流程节点信息（* 可获取子流程实例编号getSubFlowInstId）
            NodeInstance nodeInst = flowEngine
                    .getNodeInstById(parentNodeInstId);

            /************** 构建发文基本信息数据begin **************************/
            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setFlowInstId(nodeInst.getSubFlowInstId());
            optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            optBaseInfo.setCreateuser(((FUserDetail) getLoginUser())
                    .getUsercode());
            optBaseInfo.setBizstate("T");
            object.setCreateDate(new Date(System.currentTimeMillis()));
            /************** 构建发文基本信息数据end **************************/

            /******************** 建立收发文关联begin **************************/
            DocRelative drObj = new DocRelative();
            drObj.setDispatchNo(object.getDjId());

            // 根据收文流程实例号获取当前收文办件基本信息
            OptBaseInfo baseInfo = optBaseInfoManager
                    .getOptBaseByFlowId(nodeInst.getFlowInstId());
            drObj.setIncomeNo(baseInfo.getDjId());

            List<DocRelative> docRelatives = new ArrayList<DocRelative>();
            docRelatives.add(drObj);
            object.replaceDocRelatives(docRelatives);
            /******************** 建立收发文关联end **************************/

            // 设置djId
            //startDispatchDoc();
            // 部门内部事项编号
            object.setInternalNo(optBaseInfo.getDjId());
            // 权力编码
            object.setItemId(optBaseInfo.getPowerid());

            dispatchDocMgr.saveObject(object);
            this.saveOptStuffFileName();
            optBaseInfoManager.saveObject(optBaseInfo);

            // 根据子流程获取流程实例
            // WfFlowInstance dbflowINST = (WfFlowInstance) flowEngine
            // .getFlowInstById(nodeInst.getSubFlowInstId());

            // 保存过程信息
            saveIdeaInfo("收文发起",0L);
        }
        return "refreshTasks";
    }


    /**
     * 保存收文发起步骤过程日志信息
     */
    public void saveIdeaInfo(String nodename,Long nodeInstId) {
        
        FUserDetail loginInfo = (FUserDetail) getLoginUser();

        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        FUnitinfo fUnitinfo = sysUnitManager.getObjectById(loginInfo
                .getPrimaryUnit().trim());
        if (fUnitinfo == null) {
            fUnitinfo = new FUnitinfo();
        }
        optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

        optIdeaInfo.setTransidea(nodename);
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(nodeInstId);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename(nodename);
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTranscontent(this.transcontent);
        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }
    
    /* spring manager*/
    public void setDispatchDocManager(DispatchDocManager basemgr) {
        dispatchDocMgr = basemgr;
        this.setBaseEntityManager(dispatchDocMgr);
    }
    
    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocMgr = incomeDocManager;
    }
    
    public void setDocRelativeManager(DocRelativeManager docRelativeManager) {
        this.docRelativeMgr = docRelativeManager;
    }
    
    /* params */
    public List<FUnitinfo> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }
        
    public List<DocRelative> getDocRelativeList() {
        return docRelativeList;
    }

    public void setDocRelativeList(List<DocRelative> docRelativeList) {
        this.docRelativeList = docRelativeList;
    }
    
    
    
    /* end */


    public List<DispatchDocTask> getvUserTasksList() {
        return vUserTasksList;
    }

    public void setvUserTasksList(List<DispatchDocTask> vUserTasksList) {
        this.vUserTasksList = vUserTasksList;
    }

    public List<VDispatchDocList> getvDispatchDocList() {
        return vDispatchDocList;
    }

    public void setvDispatchDocList(List<VDispatchDocList> vDispatchDocList) {
        this.vDispatchDocList = vDispatchDocList;
    }

    

    public String getDocRelativeFrameType() {
        return docRelativeFrameType;
    }

    public void setDocRelativeFrameType(String docRelativeFrameType) {
        this.docRelativeFrameType = docRelativeFrameType;
    }

    public void setIncomeDocListManager(
            VIncomeDocListManager incomeDocListManager) {
        this.incomeDocListManager = incomeDocListManager;
    }

    public List<VIncomeDocList> getIncomeDocList() {
        return incomeDocList;
    }

    public void setIncomeDocList(List<VIncomeDocList> incomeDocList) {
        this.incomeDocList = incomeDocList;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setDocSendManager(DocSendManager docSendManager) {
        this.docSendMgr = docSendManager;
    }
    
    public void setApplyUnitInfoManager(ApplyUnitInfoManager applyUnitInfoManager) {
        this.applyUnitInfoMgr = applyUnitInfoManager;
    }

    public String getSelUnitCode() {
        return selUnitCode;
    }

    public void setSelUnitCode(String selUnitCode) {
        this.selUnitCode = selUnitCode;
    }

    public String getSelUnitName() {
        return selUnitName;
    }

    public void setSelUnitName(String selUnitName) {
        this.selUnitName = selUnitName;
    }
    
    public void setvDispatchDocListManager(
            VDispatchDocListManager vDispatchDocListManager) {
        this.dispatchDocListManager = vDispatchDocListManager;
    }

    public List<VDispatchDocList> getDispatchDocList() {
        return dispatchDocList;
    }

    public void setDispatchDocList(List<VDispatchDocList> dispatchDocList) {
        this.dispatchDocList = dispatchDocList;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(String parentUnit) {
        this.parentUnit = parentUnit;
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }
    
    public void setVsuppowerinusingManager(VsuppowerinusingManager powerMgr) {
        this.powerMgr = powerMgr;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }
    
    /**
     * 获取材料组id（通过权力编号）,并添加权力名称和流程编号
     * @return
     */
    public void genGroupId() {
        String itemId = StringUtils.isNotBlank(object.getOptBaseInfo().getPowerid()) ? object.getOptBaseInfo().getPowerid() : object.getItemId();
        
        // 根据权力编号获取权力类型
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("equalItemId", itemId);
        List<Vsuppowerinusing> powerList = powerMgr.listObjects(paramMap);
        
        if (StringUtils.isBlank(object.getOptBaseInfo().getPowername())) {
            object.getOptBaseInfo().setPowername(powerList.get(0).getItemName());
        }
        
        if (StringUtils.isBlank(object.getOptBaseInfo().getFlowCode())) {
            object.getOptBaseInfo().setFlowCode(powerList.get(0).getOptFlowCode());
        }
        
        request.setAttribute("groupid", powerList.get(0).getGroup_id());
    }
    
    
    public String getDispatchDocTitleByDjId() {
        String result = "";
        try {
            DispatchDoc dispatchDoc = dispatchDocMgr.getObjectById(object.getDjId());
            result = (null == dispatchDoc ? "" : dispatchDoc.getDispatchDocTitle());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }
        
        return null;
    }
   
    public String getCurUrl() {
        return curUrl;
    }


    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }
    
    
    public OptNewlyIdeaManager getOptNewlyIdeaManager() {
        return optNewlyIdeaManager;
    }

    public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
        this.optNewlyIdeaManager = optNewlyIdeaManager;
    }
    
    
    public void setOaBizBindInfoManager(OaBizBindInfoManager oaBizBindInfoManager) {
        this.oaBizBindInfoManager = oaBizBindInfoManager;
    }

    public String getShow_type() {
        return show_type;
    }


    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public String getStartDjId() {
        return startDjId;
    }

    public void setStartDjId(String startDjId) {
        this.startDjId = startDjId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getQueryUnderUnit() {
        return queryUnderUnit;
    }

    public void setQueryUnderUnit(String queryUnderUnit) {
        this.queryUnderUnit = queryUnderUnit;
    }

    public FUserunit getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(FUserunit userUnit) {
        this.userUnit = userUnit;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }
    public String getTranscontent() {
        return transcontent;
    }
    public List<OaSupervise> getOasuplist() {
        return oasuplist;
    }
    public void setOasuplist(List<OaSupervise> oasuplist) {
        this.oasuplist = oasuplist;
    }
    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }
    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }
    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }
    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }
    public void setOptProcCollectionManager(
            OptProcCollectionManager optProcCollectionManager) {
        this.optProcCollectionManager = optProcCollectionManager;
    }
    public void setVOptProcCollectionManager(
            VOptProcCollectionManager vOptProcCollectionMag) {
        VOptProcCollectionMag = vOptProcCollectionMag;
    }

    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }
    public boolean isVailViewPower() {
        return isVailViewPower;
    }
    public void setVailViewPower(boolean isVailViewPower) {
        this.isVailViewPower = isVailViewPower;
    }

    public void setVoaUnitArchiveDispatchdocManager(VoaUnitArchiveDispatchdocManager basemgr)
    {
        voaUnitArchiveDispatchdocMag = basemgr;
    }
    public void setPeopleRoleCode(String peopleRoleCode) {
        this.peopleRoleCode = peopleRoleCode;
    }
    public String getPeopleRoleCode() {
        return peopleRoleCode;
    }
    public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
        this.optPdfInfoManager = optPdfInfoManager;
    }
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    public IdeaTempletManager getIdeaTempletManager() {
        return ideaTempletManager;
    }
    public void setIdeaTempletManager(IdeaTempletManager ideaTempletManager) {
        this.ideaTempletManager = ideaTempletManager;
    }
    
}
