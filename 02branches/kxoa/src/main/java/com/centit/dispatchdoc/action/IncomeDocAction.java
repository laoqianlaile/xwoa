package com.centit.dispatchdoc.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;
import org.springframework.core.task.TaskExecutor;

import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.bbs.util.IPUtil;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.IncomeProject;
import com.centit.dispatchdoc.po.OptLeaderShip;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.IncomeProjectManager;
import com.centit.dispatchdoc.service.OptLeaderShipManager;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.dispatchdoc.service.VIncomeDocListManager;
import com.centit.oa.action.OaBizBindInfoAction;
import com.centit.oa.action.OaSuperviseAction;
import com.centit.oa.po.OaArchive;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaArchiveManager;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.OaUnitIncomedocManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.oa.service.VoaUnitArchiveIncomedocManager;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
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
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.VOptProcCollectionManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.OptFlowNoInfo;
import com.centit.sys.po.OptFlowNoInfoId;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.ExtraFlowManager;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.FlowManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.ManageActionLog;
import com.centit.workflow.NodeInstance;

/**
 * 
 * 收文流程业务操作ACTION
 * 
 * @author hx
 * @create 2014-9-26
 * @version
 */
public class IncomeDocAction extends IODocCommonBizAction<IncomeDoc> implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private IncomeDocManager incomeDocMgr;
    private VIncomeDocListManager incomeDocListMgr;
    private IncomeProjectManager incomeProjectMgr;
   
    
    private VsuppowerinusingManager powerMgr;
    @SuppressWarnings("unused")
    private VOptBaseListManager vOptBaseListManager;
    private VOptProcCollectionManager VOptProcCollectionMag;
    private GeneralModuleParamManager generalModuleParamManager;

    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }

    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }

    private OaSmssendManager oaSmssendManager;
    
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

    private JSONObject json;
    private String unitsJson; //部门JSON
    private String nodeOptUrl;
    private List<Map<String, String>> jsonList;
    
    private List<VIncomeDocList> incomeDocList;
    private OptFlowNoInfoManager optFlowNoInfoManager;
    private FlowDefine flowDefine;

    private File incomeDocFile_;
    private String incomeDocFile_FileName;
    private List<OptLeaderShip> optLeaderShipList;
    private OptLeaderShipManager optLeaderShipManager;
    private OaSuperviseManager oaSuperviseManager;
    private OaBizBindInfoManager oaBizBindInfoManager;
    private  List<OaSupervise> oasuplist;
    private List<FDatadictionary> datadictionary;
    private List<FUnitinfo> unitlist;// 查询部门
    
    private FUserunit userUnit;// 用户单位
    private String userRank=null;
    private String queryUnderUnit;//(列表类别)按职务等级查询列表
    private List<FUnitinfo> unitList;
    
    private GeneralModuleParam moduleParam;
    /**
     * RTX相关
     */
    private RtxInfoManager rtxInfoManager;  
    
    private String acceptArchiveNoNum;//流水号
    private OptProcCollectionManager optProcCollectionManager;//办件收藏
    private OaUnitIncomedocManager oaUnitIncomedocMag;
    private VoaUnitArchiveIncomedocManager voaUnitArchiveIncomedocMag;
    private OaArchiveManager oaArchiveMag;
    private IdeaTempletManager ideaTempletManager;
    public void setOaArchiveManager(OaArchiveManager basemgr) {
        oaArchiveMag = basemgr;
    }

    public void setVoaUnitArchiveIncomedocManager(VoaUnitArchiveIncomedocManager basemgr)
    {
        voaUnitArchiveIncomedocMag = basemgr;
    }

    public void setOaUnitIncomedocManager(OaUnitIncomedocManager basemgr)
    {
        oaUnitIncomedocMag = basemgr;
    }
    public void setOptProcCollectionManager(
            OptProcCollectionManager optProcCollectionManager) {
        this.optProcCollectionManager = optProcCollectionManager;
    }
    public String getAcceptArchiveNoNum() {
        return acceptArchiveNoNum;
    }
    public void setAcceptArchiveNoNum(String acceptArchiveNoNum) {
        this.acceptArchiveNoNum = acceptArchiveNoNum;
    }
    public void setOaBizBindInfoManager(OaBizBindInfoManager oaBizBindInfoManager) {
        this.oaBizBindInfoManager = oaBizBindInfoManager;
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

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public void setOptLeaderShipManager(OptLeaderShipManager optLeaderShipManager) {
        this.optLeaderShipManager = optLeaderShipManager;
    }
    public List<OptLeaderShip> getOptLeaderShipList() {
        return optLeaderShipList;
    }
    public void setOptLeaderShipList(List<OptLeaderShip> optLeaderShipList) {
        this.optLeaderShipList = optLeaderShipList;
    }
    public FlowDefine getFlowDefine() {
        return flowDefine;
    }
    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }
    
    public void setIdeaTempletManager(IdeaTempletManager ideaTempletManager) {
        this.ideaTempletManager = ideaTempletManager;
    }


    /**
     * 办（阅办）件放置此处----开始
     */
    private String optId;
    private String powerName;
    private String flowCode;
    private String nextOptUrl;
    private String itemId;
    
    
    private String show_type;//区别查看列表页面是否显示新增按钮,or编辑页面是否有返回按钮
    
    private String curUrl;
    private List<OptNewlyIdea> optNewlyIdeaList;
    private OptNewlyIdeaManager optNewlyIdeaManager;
    private boolean isVailViewPower;//是否验证查看权限
    private OptPdfInfoManager optPdfInfoManager;
    private TaskExecutor taskExecutor;
    /*-------Temp 收文  --------*/
    
   

    /**
     * 根据查询表单查询(未提交收文)
     * hll 20131227
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            //FUserDetail user = (FUserDetail) getLoginUser();
            //FUserunit dept = sysUserManager.getUserPrimaryUnit(user      .getUsercode());
           // String sParentUnit = dept.getUnitcode();
            //unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
           
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
//            filterMap.put("usercode", loginUser.getUsercode());
//            filterMap.put("itemSource", "N");
//            filterMap.put("bizstate", "F");
//            filterMap.put(CodeBook.SELF_ORDER_BY, "update_date desc");
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DateUtil.AddDays(DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"),1));
            }
           /* if(StringUtils.isBlank((String)filterMap.get("begUpdateTime"))&&StringUtils.isBlank((String)filterMap.get("endUpdateTime"))){
                filterMap.put("begUpdateTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endUpdateTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }*/
            
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
            incomeDocList = incomeDocListMgr.listIncomeDocList(filterMap,pageDesc);
            // 查看办件列表中，设置在办件的超时提醒
            for(VIncomeDocList o : incomeDocList){
                if("W".equals(o.getBizstate())){
                    IncomeDoc doc = incomeDocMgr.getObjectById(o.getDjId());
                    
                    if(null != doc){
                        o.setOverdueType(BizCommUtil.getOverDueState(doc.getToBeanfinishedDate()));
                    }
                }
            }
            unitList=unitList();//科级部门
            
//            incomeDocList = incomeDocListMgr.listObjects(" FROM VIncomeDocList WHERE 1=1", filterMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            //return LIST;
            return "listV1";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 添加组织范围：单位/部门
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listV2() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            FUserunit funit = sysUserManager.getUserPrimaryUnit(loginUser
                    .getUsercode());
            if(StringUtils.isNotBlank(request.getParameter("s_bmsw"))||StringUtils.isNotBlank((String)filterMap.get("bmsw"))){
                filterMap.put("unitcode",funit.getUnitcode());
            }
            incomeDocList = incomeDocListMgr.listIncomeDocListV2(filterMap,
                    pageDesc);
            totalRows = pageDesc.getTotalRows();
            filterMap.put("fromMenu", "TSW");
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            //return "listV2";
            return "listV3";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 根据查询表单查询(未提交收文)
     * dk 2016-4-7 去除分页
     * @return
     */
    @SuppressWarnings("unchecked")
    public void listForExcel() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
           
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DateUtil.AddDays(DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"),1));
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
                }
            }else{
                filterMap.put("usercode", loginUser.getUsercode());
            }
            incomeDocList = incomeDocListMgr.listIncomeDocForExcel(filterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 办（阅办）件登记--开始
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String registerDoOrReadEdit() {
        try {
           
            if (StringUtils.isBlank(object.getDjId())) {
                //根据itemid判断是sw还是nbsq来定义流水号
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                VOrgSupPower power = suppowerManager.getSupPowerInfo(object.getItemId(), fuser.getPrimaryUnit()); 
                 
                if("SQ".equals(power.getItemType())){
                       object.setDjId(incomeDocMgr.getNextkey("SQ"));   //内部事权流水号
                   }else if("GW".equals(power.getItemType())){
                    object.setDjId(incomeDocMgr.getNextkey("SW"));    //收文流水号
                   }
            } else {
                object=incomeDocMgr.getObjectById(object.getDjId());                
            }
            //add by lay 2015-11-11
            if(object == null){
                return "registerDoOrReadEdit";
            }
            //end add
            if(object.getIncomeDate()==null){
                object.setIncomeDate(new Date());
            }
            //初始化基本信息
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
           
            //编辑时取出optbasinfo中的flowcode和optid以防丢失
            if(optBaseInfo!=null){
                flowCode=optBaseInfo.getFlowCode();
                optId=optBaseInfo.getOptId();
            }
            if(optBaseInfo==null){
                optBaseInfo=new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                
                VOrgSupPower vOrgSupPower=suppowerManager.getSupPowerInfo(object.getItemId(), fuser.getPrimaryUnit());
                if(StringUtils.isBlank(vOrgSupPower.getItemId())){
                    super.postAlertMessage("操作失败，请检查权力部门配置！", response);
                    return null;
                }
                
                flowCode=vOrgSupPower.getOptFlowCode();
                if(StringUtils.isBlank(flowCode)){
                    super.postAlertMessage("操作失败，请检查流程与权力部门配置！", response);
                    return null;
                }
                
                //收文编号规则配置好再打开
               // optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genFWH(object.getIncomeDocType()==null?"SWXZ":object.getIncomeDocType(), object.getIncomeDeptType()==null?"XZ31":object.getIncomeDeptType(), DatetimeOpt.currentUtilDate(), fuser.getPrimaryUnit(), null));
               

                optBaseInfo.setPowerid(object.getItemId());
                optBaseInfo.setPowername(vOrgSupPower.getItemName());
            }                
            object.setOptBaseInfo(optBaseInfo);  
            String createUser = object.getOptBaseInfo().getCreateuser();
            request.setAttribute("createUser", createUser);
            //Map<String, Object> optMap = new HashMap();
            //optMap.put("djId", object.getDjId());
           // optLeaderShipList = optLeaderShipManager.listObjects(optMap);
            genGroupId();
            moduleParam = generalModuleParamManager.getObjectById("sw_yd");
            
            // 用于页面输入框输入自动匹配的数据
            this.getMatchedData();
                    
        } catch (Exception e) {
            e.printStackTrace();            
            return ERROR;
        }
        
        return "registerDoOrReadEdit";
    }
    
    public String option() {

        String incomeDocType = request.getParameter("datacode");
     
        jsonList = new ArrayList<Map<String, String>>();
        if (StringUtils.isBlank(incomeDocType)) {
            return "options";
        }

      //  Map<String, String> temp = new HashMap<String, String>();
//        temp.put("key", "");
//        temp.put("value", "--请选择--");
//        jsonList.add(temp);
       
            List<FDatadictionary> datadictionary = CodeRepositoryUtil
                    .getDictionary(incomeDocType);

            for (FDatadictionary data : datadictionary) {

                Map<String, String> op = new HashMap<String, String>();

                op.put("key", data.getDatacode());
                op.put("value", data.getValue());

                jsonList.add(op);
   
            }
      
        return "options";

    }
    
    /**
     *动态生成发文号
     * 
     * @return
     */
    public String changeSwh() {
        json = new JSONObject();
        FUserDetail fuser = (FUserDetail) getLoginUser();
        try {
            String incomeDocType = request.getParameter("incomeDocType");
            String incomeDeptType = request.getParameter("incomeDeptType");
            String swdate = request.getParameter("swdate");
            Date swtime = DatetimeOpt.convertStringToDate(swdate);
            //收文编号规则配置好再打开
           String swh = optFlowNoInfoManager.genFWH(incomeDocType, incomeDeptType, swtime,
                    fuser.getPrimaryUnit(), null);
            //测试使用
//            String swh=incomeDocType+incomeDeptType+"11";

            json.put("status", swh);
        } catch (Exception e) {
            log.info(e);
            json.put("status", "");
        }

        return "ajaxResult";
    }
    
    /**
     * 保存：办（阅办）件以及基本信息
     * @return
     */
    public String saveRegisterDoOrRead(){
        try {
            if ("T".equals(object.getOptBaseInfo().getBizstate()) ||
                    null == object.getOptBaseInfo().getFlowInstId() || 
                    "".equals(object.getOptBaseInfo().getFlowInstId().toString())
                    ) {
                
            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            
            if (baseInfo == null) {
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setFlowInstId(super.getFlowInstId());
                if (optBaseInfo.getFlowInstId() == null || "".equals(optBaseInfo.getFlowInstId())) {
                  //  optBaseInfo.setBiztype("F");// 未提交标志
                    optBaseInfo.setBizstate("F");// 未提交标志
                }
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                optBaseInfo.setCreateuser(((FUserDetail) getLoginUser()).getUsercode());
                object.setCreateDate(new Date(System.currentTimeMillis()));
                //optBaseInfo.setBizstate("N");

            }else {
                IncomeDoc incomeDoc=incomeDocMgr.getObjectById(object.getDjId()); 
                incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
                object = incomeDoc;
                optBaseInfoManager.copyObjectNotNullProperty(baseInfo,optBaseInfo);
                optBaseInfo = baseInfo;
                optBaseInfo.setDjId(object.getDjId());
            }
            
            if ("T".equals(optBaseInfo.getBizstate())) {
                object.setOperateState("2");
            } else {
                object.setOperateState("1");
            }
            FUserDetail fuser = ((FUserDetail) getLoginUser());
          /*  if (StringUtils.isBlank(optBaseInfo.getAcceptArchiveNo())) {
                //IncomeDocType：文书分类 IncomeDeptType：来文单位分类
            	//根据上面字段 生成对应收文编号，编号规则在数据库字典项中配置；
            	optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genRecordFWH(object.getIncomeDocType(),object.getIncomeDeptType(), null, fuser.getPrimaryUnit()));
            }*/
            
            // 保存权力事项
            String powerId = object.getItemId();
            VOrgSupPower power = suppowerManager.getSupPowerInfo(powerId, fuser.getPrimaryUnit());
            optBaseInfo.setPowerid(powerId);
            optBaseInfo.setPowername(power.getItemName());
            if(StringUtils.isBlank(object.getIncomeDocTitle())){
                optBaseInfo.setTransaffairname(object.getProjectName());
            }else{
                optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
            }
            
            VUserUnits fuerunit = new VUserUnits();
            fuerunit.setUnitCode(fuser.getPrimaryUnit());
            fuerunit.setUserCode(fuser.getUsercode());
            
            fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
            
            optBaseInfo.setOrgcode(fuerunit.getUnitCode());
            optBaseInfo.setOrgname(fuerunit.getUnitName());
            optBaseInfo.setFlowCode(flowCode);
            //根据流程代码获取optid
            FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
            optBaseInfo.setOptId(flowDescribe.getOptId());
            //保存缓急程度
            optBaseInfo.setCriticalLevel(object.getCriticalLevel());
            
            object.setInternalNo(object.getDjId()); // 非空字段，暂时设置一致
            object.setUpdateDate(new Date(System.currentTimeMillis()));
            //保存信息时存入权力类型
            object.setApplyItemType(power.getItemType());
            incomeDocMgr.saveObject(object);
            optBaseInfoManager.saveObject(optBaseInfo);
            
            object.setAcceptNo(optBaseInfo.getAcceptArchiveNo());
            
            
            } else {//这边是从退回编辑流程过来的
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
                if(StringUtils.isBlank(object.getIncomeDocTitle())){
                    optBaseInfo.setTransaffairname(object.getProjectName());
                }else{
                    optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
                }
                if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
                    flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
                }
                
                IncomeDoc incomeDoc=incomeDocMgr.getObjectById(object.getDjId()); 
                incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
                object = incomeDoc;
                incomeDocMgr.saveObject(object);
            }
            if(StringUtils.isNotBlank(request.getParameter("save")))
                return this.list();
            else
                return null;
          //  return this.registerDoOrReadEdit();
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存收文信息出错，详见系统日志。");
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    /**
     * 保存并提交基础业务数据:行政许可业务数据\行政基础信息表\7类申请事项
     * @return
     */
    public String saveAndSubmitRegisterDoOrRead() {  
        
        this.saveRegisterDoOrRead();
      //  OptBaseInfo optBaseInfo = object.getOptBaseInfo();
         object=incomeDocMgr.getObjectById(object.getDjId());
            
      
         Long flowInstId = null;
       // if (optBaseInfo==null || null == optBaseInfo.getFlowInstId() || "".equals(optBaseInfo.getFlowInstId())) {
            if (object != null) {
             OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());    
                
            FUserDetail fuser = ((FUserDetail) getLoginUser());        
            //当flowCode为空时，取flowecode有问题
           if (StringUtils.isBlank(flowCode)) {
                flowCode = suppowerManager.getFlowCodeByOrgItem(object.getItemId(), fuser.getPrimaryUnit());
            }
            FlowInstance flowInst = flowEngine.createInstance(
                    flowCode, optBaseInfo.getTransaffairname(),
                    object.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            // 发短信
            Set<Long> nextNode = new HashSet<Long>();
            nextNode.add(curNodeInstId);
            // 收文登记模块代码
            FUserDetail user = (FUserDetail)getLoginUser();
            if(null != user){
                oaSmssendManager.saveFlowMsgs(request.getParameter("isSendMessage"), user.getUsercode(), nextNode);
                oaSmssendManager.executeSendMsg();
            }
            
            object.setOptBaseInfo(optBaseInfo);
            object.getOptBaseInfo().setFlowInstId(flowInstId);
            object.getOptBaseInfo().setBizstate("W");
            object.getOptBaseInfo().setBiztype("W");
            //将收文登记人员纳入办件角色，为后续文书分发 节点 流程办理做前置准备
            flowEngine.assignFlowWorkTeam(flowInstId,"wsdj",fuser.getUsercode(),"文书室人员");
        }
            
      
          //start by dk 2016-01-08
            //取得当前流水池中流水号
           /*String lsh=optFlowNoInfoManager.genLsH(object.getIncomeDocType(), object.getIncomeDeptType(), object.getIncomeDate(), "year");
           //判断当前流水号是否大于流水号池中的流水号，如果大于，将流水号保存进流水号池
           if(Integer.parseInt(acceptArchiveNoNum)>=Integer.parseInt(lsh))
           {
               FDatadictionary data = CodeRepositoryUtil.getDataPiece(object.getIncomeDocType(), object.getIncomeDeptType());
               OptFlowNoInfoId noId = new OptFlowNoInfoId("xtzwhgz",  DatetimeOpt.truncateToYear(object.getIncomeDate()), data.getDatadesc());
               OptFlowNoInfo noInfo =optFlowNoInfoManager.getObjectById(noId);
               if (noInfo == null) {
                   noInfo = new OptFlowNoInfo(noId, Long.parseLong(acceptArchiveNoNum), DatetimeOpt.currentUtilDate());
               } else{
                   noInfo.setCurNo(Long.parseLong(acceptArchiveNoNum));
                   noInfo.setLastCodeDate(DatetimeOpt.currentUtilDate());
                   noInfo.setCuryear(String.valueOf(DatetimeOpt.getYear(DatetimeOpt.currentUtilDate())));
               }
               optFlowNoInfoManager.saveObject(noInfo);
           }*/
          //end
            
            
        saveIdeaInfo();
        
      //向Rtx推送消息
        List<VuserTaskListOA> list=optBaseInfoManager.getTasksByNodeInstId(curNodeInstId);
        this.SendRtxMsn(object.getDjId(),list);
       // return this.nextStep();
        
      //生成pdf
        if("true".equals(SysParametersUtils.getParameters("generatePdfForDocEnable", "false"))){
            final String userCode = ((FUserDetail) getLoginUser()).getUsercode();
            final String exePath = optPdfInfoManager.getPdf2SwfToolPath(request);
          //"***/anonymous***"代表匿名访问
            String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
            if(contextPath.endsWith("/")){
                contextPath = contextPath.substring(0, contextPath.length()-1);
            }
            contextPath = contextPath + request.getContextPath();
            final String formHtmlUrl = optPdfInfoManager.getSWFormHtmlUrl(contextPath,userCode, object.getDjId());
            taskExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    createPDF(object, "阅办文登记", 0L,userCode,exePath,formHtmlUrl);
                }
            });
        }
        return this.list();
    }
    
    private void createPDF(IncomeDoc incomeDoc,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        OptPdfInfo optPdfInfo = null;
        try {
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<收文文登记办件djId:"+incomeDoc.getDjId()+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(incomeDoc.getDjId(), nodeInstId);
           File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(incomeDoc.getDjId(),nodeInstId,formHtmlUrl);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
            //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(incomeDoc.getDjId())));
           optPdfInfo.setNodeName(nodeName);
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<收文登记办件djId:"+incomeDoc.getDjId()+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        } catch (Exception e) {
            log.error("生成PDF异常："+e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
    }
    /**
     * 发文编号重复性验证
     * by dk 2016-01-06
     * @return
     */
    public String acceptArchiveNoVali(){
        json = new JSONObject();
        boolean t=false;
        String djId=request.getParameter("djId");
        String acceptArchiveNo=request.getParameter("acceptArchiveNo");
        try {
            List<OptBaseInfo> opt=optBaseInfoManager.listOptBaseInfo( acceptArchiveNo);
                if(opt.size()<=0){//未查询到数据，验证通过 
                    json.put("msg", "T");
                }else{//查询到数据
                    for(OptBaseInfo o:opt){
                        if(djId.equals(o.getDjId())){//编辑
                            json.put("msg", "T");
                            t=true;
                            break;
                        }
                    }
                    if(!t)
                    json.put("msg", "F");
                }
            
            
        } catch (Exception e) {
            log.info(e);
            json.put("msg", "");
        }
        return "ajaxResult";
    }
    
    /**
     * 关联事项信息查看
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
         // 用于展示查看详细信息Lab标签内容 原先tab方式显示
         //frameList.add(this.getAllViewFrame(object.getDjId()));
         frameList.add(super.getCommonStuffFrame(object.getDjId(), null, null));
         //frameList 页面列表显示新的和LAb方式区别
        // frameList=getAllInfoListFrame(frameList,object.getDjId());
       
         jspInfo = new OptJspInfo();
//         jspInfo.setTitle("收文查看");
         jspInfo.setFrameList(frameList);
         
         request.setAttribute("startDjid", request.getParameter("startDjid"));
       //添加可收藏操作
         OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(object.getDjId());
         request.setAttribute("optBaseInfo",optBaseInfo);
         request.setAttribute("flowInstId", optBaseInfo.getFlowInstId());
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
            if("SWBJSCLB".equals(fromMenu)){//收文办件收藏过来的查看
                neighbouringDjId = VOptProcCollectionMag.findNeighbouringDjId(filterMap, object.getDjId());
            }
            if("SWKFYDCK".equals(fromMenu) || "SWKFYDPZ".equals(fromMenu)){//收文开放阅读查看
                neighbouringDjId = oaUnitIncomedocMag.findNeighbouringDjId(filterMap, object.getDjId());
            }
            if("SWDGD".equals(fromMenu)){//收文待归档
                neighbouringDjId = voaUnitArchiveIncomedocMag.findNeighbouringDjId(filterMap, object.getDjId());
            }
            if("TSW".equals(fromMenu)){//厅收文
                neighbouringDjId = incomeDocListMgr.findNeighbouringDjId2(filterMap, object.getDjId());
            }
            if("YGDHZ".equals(fromMenu)){//已归档汇总
                neighbouringDjId = oaArchiveMag.findNeighbouringDjId(filterMap, object.getDjId());
            } 
        }else{
            neighbouringDjId = incomeDocListMgr.findNeighbouringDjId(filterMap, object.getDjId());
        }
       
        if(neighbouringDjId != null && neighbouringDjId.size()==2){
            object.setPrevDjId(neighbouringDjId.get(0));
            object.setNextDjId(neighbouringDjId.get(1));
        }
        setbackSearchColumn(filterMap);
     }
 @SuppressWarnings("unused")
private List<OptHtmlFrameInfo> getAllInfoListFrame(List<OptHtmlFrameInfo> frameList,String djId) {
         
         frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
         frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
         frameList.add(OaBizBindInfoAction.getBizBindListFrame(djId, "E","nodelete"));//关联事项
         //督办催办信息
         Map<String, Object> filterMap = new HashMap<String, Object>();
         filterMap.clear();
         filterMap.put("supDjid", object.getDjId());
         oasuplist=oaSuperviseManager.listObjects(filterMap);
         if(null!=oasuplist&&oasuplist.size()>0){
             frameList.add(OaSuperviseAction.getSupListFrame( object.getDjId())) ;
         }
         return frameList;  
     }
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
                .setFrameSrc("/dispatchdoc/incomeDoc!getAllCaseView.do?djId="
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
            object = incomeDocMgr.getObject(object);
            //add modify by lay 2015-11-11 防空判断
            if(object!=null){
            //end add
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
                boolean flag = this.getOaBizInfolist(object.getDjId());
                request.setAttribute("flag", flag);
                request.setAttribute("flowInstId", inBaseInfo.getFlowInstId());
                request.setAttribute("nodeId", nodeId);
            }
            return "AllmilitarycaseView";
        }
     /**
      * 收文业务数据查看
      * @param djid
      * @return
     */
     private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
         OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
         viewFrameInfo.setFrameId("viewFrame");  
         viewFrameInfo.setFrameLegend("拟文单");
         viewFrameInfo
            .setFrameSrc("/dispatchdoc/incomeDoc!registerDoOrReadView.do?vewtype=T&djId=" + djid);
         viewFrameInfo.setFrameHeight("220px");
         return viewFrameInfo;
     }     
    
    /*-------Temp 收文   end--------*/
    
     /**
      * 导出Excel 根据查询条件 导出当前结果
      * 
      * @throws IOException
      */
     public void exportExcelByPo() throws IOException {
         this.listForExcel();
         List<VIncomeDocList> objectList =new ArrayList<VIncomeDocList>();
         objectList = incomeDocList;         
         
         List<Object[]> chosedList = new ArrayList<Object[]>() ;
      // 获取需要生成Excel的数据
         if (objectList != null) {
             int i = 1;
             for (VIncomeDocList o : objectList) {                
                 Object[] temp= new Object[6];
                 temp[0]=String.valueOf(i++);
                 temp[1]=o.getAcceptArchiveNo();
                 temp[2]=o.getTransaffairname();
                 temp[3]=o.getIncomeDeptName();
                 temp[4]=o.getIncomeDocNo();
                 temp[5]=DatetimeOpt.convertDateToString(
                         o.getIncomeDate(), "yyyy年MM月dd日");               
                 chosedList.add(temp);
                
             }
         }
         String[] header = {"序号","阅办文号","文件标题","发文单位","发文字号","阅办文时间"};// 列头
        BizCommUtil.doPoi2Excel("阅办文信息", header, chosedList);
     }
     
     /**
      * 导出Excel 根据查询条件 导出当前结果
      * 
      * @throws IOException
      */
     public void exportExcelByPoV2() throws IOException {
         this.listV2();
         List<VIncomeDocList> objectList =new ArrayList<VIncomeDocList>();
         objectList = incomeDocList;         
         
         List<Object[]> chosedList = new ArrayList<Object[]>() ;
      // 获取需要生成Excel的数据
         if (objectList != null) {
             int i = 1;
             for (VIncomeDocList o : objectList) {                
                 Object[] temp= new Object[6];
                 temp[0]=String.valueOf(i++);
                 temp[1]=o.getAcceptArchiveNo();
                 temp[2]=o.getTransaffairname();
                 temp[3]=o.getIncomeDeptName();
                 temp[4]=o.getIncomeDocNo();
                 temp[5]=DatetimeOpt.convertDateToString(
                         o.getIncomeDate(), "yyyy年MM月dd日");               
                 chosedList.add(temp);
                
             }
         }
         String[] header = {"序号","阅办文号","文件标题","发文单位","发文字号","阅办文时间"};// 列头
        BizCommUtil.doPoi2Excel("阅办文信息", header, chosedList);
     }
     
     
    /**
     * 获取incomeDoc、optBaseInfo和incomeProject信息
     * @author hll 2013-11-22 epowersd
     */
    public void getIncomeDocAndProjectInfo() {
        IncomeDoc incomeDoc = null;
        
        if (StringUtils.isNotBlank(object.getDjId())) {
            incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
        }
        
        if (null == incomeDoc) { // 新增
            incomeDoc = new IncomeDoc();
            incomeDoc.setDjId(incomeDocMgr.getNextkey("SW"));
            incomeDoc.setApplyItemType(object.getApplyItemType());
        }
        incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
        incomeDoc.setOptBaseInfo(object.getOptBaseInfo());
        incomeDoc.setIncomeProject(object.getIncomeProject());
        object = incomeDoc;
        object.setOptBaseInfo(this.getOptBaseInfoByDjId());
        //object.setIncomeProject(this.getIncomeProjectInfo());
    }
    
    
    /**
     * 获取incomeProject信息
     * @author hll 2013-11-22 epowersd
     */
    public IncomeProject getIncomeProjectInfo() {
        IncomeProject incomeProject = null;
        
        if (StringUtils.isNotBlank(object.getDjId())) {
            incomeProject = incomeProjectMgr.getObjectById(object.getDjId());
        }
        
        if (null == incomeProject) {
            incomeProject = new IncomeProject();
            incomeProject.setDjId(object.getDjId());
        }
        
        return incomeProject;
    }
    
    
    /**
     * 获取optBaseInfo信息（通过djId获取）
     * @author hll 2013-11-27 epowersd
     */
    public OptBaseInfo getOptBaseInfoByDjId() {
        OptBaseInfo optBaseInfo = null;
        
        if (StringUtils.isNotBlank(object.getDjId())) {
            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
        }
        
        if (null == optBaseInfo){
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setOptId(object.getOptBaseInfo().getOptId());
            optBaseInfo.setFlowCode(object.getOptBaseInfo().getFlowCode());
            optBaseInfo.setPowerid(object.getOptBaseInfo().getPowerid());
        }
        
        if (StringUtils.isBlank(optBaseInfo.getTransAffairNo())) {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            
            String transAffairNo = "";
            
            try {
                transAffairNo = optFlowNoInfoManager.genFWH("DJH", "1", null, fuser.getPrimaryUnit(), null);
            } catch (Exception e) {
                
            }
            
            optBaseInfo.setTransAffairNo(transAffairNo); // 登记号
        }
        
        return optBaseInfo;
    }
    
    
    /**
     * 登记页面（编辑）
     * @author hll 2013-12-6 epowersd
     * @return
     */
    public String edit() {
        try {
            this.getIncomeDocAndProjectInfo();
            
            // 添加默认日期为当天
            Date today = new Date(System.currentTimeMillis());
            if (null == object.getDocCreateDate()) {
                object.setDocCreateDate(today);
            }
            if (null == object.getIncomeDate()) {
                object.setIncomeDate(today);
            }
            
            genGroupId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return EDIT;
    }
    
    
    /**
     * 重新登记
     * @return
     */
    public String reEdit() {
        try {
            this.getIncomeDocAndProjectInfo();
            
            // 添加默认日期为当天
            Date today = new Date(System.currentTimeMillis());
            if (null == object.getDocCreateDate()) {
                object.setDocCreateDate(today);
            }
            if (null == object.getIncomeDate()) {
                object.setIncomeDate(today);
            }
            
            genGroupId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return "reEdit";
    }
    
    
    public String checkProjectExist() {
        json = new JSONObject();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("notDjId", object.getDjId());
            paramMap.put("incomeDocNo", new String(object.getIncomeDocNo().getBytes("ISO-8859-1"), "GBK").trim());
            paramMap.put("incomeDeptName", new String(object.getIncomeDeptName().getBytes("ISO-8859-1"), "GBK").trim());
            
            List<IncomeDoc> incomeDocList = incomeDocMgr.listObjects(paramMap);
            
            json.put("status", incomeDocList.isEmpty() ? "none" : "exist");
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        }
        
        return "ajaxResult";
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
    
    
    public String viewIncomeDocAndProjectInfo() {
        this.view();
        
        return "view";
    }
    
    
    /**
     * 查看页面
     */
    public String view() {
        try {
            this.getIncomeDocAndProjectInfo();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return "view";
    }
    
    
    /**
     * 保存建设项目登记界面
     * @author hll 2013-11-23 epowersd
     * @return
     */
    public String saveIncomeDocAndProjectInfo() {
        if ("W".equals(object.getOptBaseInfo().getBizstate()) || "T".equals(object.getOptBaseInfo().getBizstate()) 
                || null == object.getOptBaseInfo().getFlowInstId()
                || "".equals(object.getOptBaseInfo().getFlowInstId().toString())
                ) {
            if ("W".equals(object.getOptBaseInfo().getBizstate())) {
                object.getOptBaseInfo().setBizstate("W");
            }
            
            IncomeProject incomeProject = object.getIncomeProject();
            
            if (null != incomeProject) {
                IncomeProject project = incomeProjectMgr.getObjectById(object.getDjId());
                
                if (null == project) {
                    incomeProject.setDjId(object.getDjId());
                } else {
                    incomeProjectMgr.copyObjectNotNullProperty(project, incomeProject);
                    
                    incomeProject = project;
                    incomeProject.setDjId(object.getDjId());
                }
            }
            
            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            
            if (null == baseInfo) {
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setFlowInstId(super.getFlowInstId());
                if (null == optBaseInfo.getFlowInstId() || "".equals(optBaseInfo.getFlowInstId())) {
                    optBaseInfo.setBizstate("F");
                }
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                optBaseInfo.setCreateuser(((FUserDetail) getLoginUser()).getUsercode());
                object.setCreateDate(new Date(System.currentTimeMillis()));
            } else {
                IncomeDoc incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
                incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
                object = incomeDoc;
                
                optBaseInfoManager.copyObjectNotNullProperty(baseInfo, optBaseInfo);
                optBaseInfo = baseInfo;
                optBaseInfo.setDjId(object.getDjId());
            }
            
            if (StringUtils.isBlank(object.getItemId())) {
                object.setItemId(optBaseInfo.getPowerid());
            } else {
                optBaseInfo.setPowerid(object.getItemId());
            }
            object.setInternalNo(object.getDjId()); // 非空字段，暂时设置一致
            if ("T".equals(optBaseInfo.getBizstate())) {
                object.setOperateState("2");
            } else {
                object.setOperateState("1");
            }
            object.setUpdateDate(new Date(System.currentTimeMillis()));
            
            // 保存权力事项
            optBaseInfo.setPowerid(object.getItemId());
            // 在optBaseInfo表中替换新的项目名称或者文件标题
            if (StringUtils.isNotBlank(object.getProjectName())) {
                optBaseInfo.setTransaffairname(object.getProjectName());
            } else {
                optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
            }
            if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
                flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
            }
            
            if (null == optBaseInfo.getCreatedate()) {
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            }
            
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            optBaseInfo.setOrgcode(loginUser.getPrimaryUnit());
            optBaseInfo.setOrgname(CodeRepositoryUtil.getUnitInfoByCode(loginUser.getPrimaryUnit()).getUnitname());
            
            if (StringUtils.isBlank(optBaseInfo.getAcceptArchiveNo())) {
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                optBaseInfo.setTransAffairNo(optFlowNoInfoManager.genRecordFWH("DJH", null, fuser.getPrimaryUnit()));
//                optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genRecordFWH("SLH", null, fuser.getPrimaryUnit()));
            }
            
            if(incomeDocFile_!=null){
                try {
                    FileInputStream fis = new FileInputStream(incomeDocFile_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setIncomeDocFile(bbuf);
                        object.setIncomeDocFileName(incomeDocFile_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            if (StringUtils.isBlank(object.getItemId())) {
                object.setItemId(optBaseInfo.getPowerid());
            }
            
            incomeDocMgr.saveObject(object);
            
            if ("W".equalsIgnoreCase(object.getItemSource())) {
                optBaseInfo.setIsUpload("0");
            }
            optBaseInfoManager.saveObject(optBaseInfo);
            if (null != incomeProject) {
                incomeProjectMgr.saveObject(incomeProject);
            } else {
                incomeProjectMgr.deleteObjectById(object.getDjId());
            }
        }
        
        return this.edit();
    }
    
    
    /**
     * 重新登记后的提交
     * @return
     */
    public String reSubmitIncomeDocAndProjectInfo() {
        OptBaseInfo optBaseInfo = this.getOptBaseInfoByDjId();
        optBaseInfoManager.copyObjectNotNullProperty(optBaseInfo, object.getOptBaseInfo());
        object.setOptBaseInfo(optBaseInfo);
        
        saveIncomeDocAndProjectInfo();
        saveIdeaInfo();
        super.submitNode();
        
        return "refreshIncomeTasks";
    }
    
    
    /**
     * 重新登记后的保存
     * @return
     */
    public String reSaveIncomeDocAndProjectInfo() {
        OptBaseInfo optBaseInfo = this.getOptBaseInfoByDjId();
        optBaseInfoManager.copyObjectNotNullProperty(optBaseInfo, object.getOptBaseInfo());
        object.setOptBaseInfo(optBaseInfo);
        
        saveIncomeDocAndProjectInfo();
        
        return "refreshIncomeTasks";
    }
    
    
    /**
     * 保存建设项目登记并进入流程
     * @author hll 2013-11-23 epowersd
     * @return
     */
    public String submitIncomeDocAndProjectInfo() {
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        optBaseInfo.setDjId(object.getDjId());
        
        if (null == optBaseInfo.getFlowInstId() || "".equals(optBaseInfo.getFlowInstId().toString())) {
            if (StringUtils.isNotBlank(object.getProjectName())) {
                optBaseInfo.setTransaffairname(object.getProjectName());
            } else {
                optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
            }
            
            if (StringUtils.isBlank(optBaseInfo.getTransAffairNo())) {
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                optBaseInfo.setTransAffairNo(optFlowNoInfoManager.genFWH("DJH", "1", null, fuser.getPrimaryUnit(), null));
            }
            
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String flowCode = optBaseInfo.getFlowCode();
            if (flowCode == null || flowCode.trim().length() == 0) {
                flowCode = suppowerManager.getFlowCodeByOrgItem(optBaseInfo.getPowerid(), fuser.getPrimaryUnit());
            }
            FlowInstance flowInst = flowEngine.createInstance(
            		flowCode, optBaseInfo.getTransaffairname(),
                    optBaseInfo.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            object.getOptBaseInfo().setBizstate("W");
        }
        
        saveIncomeDocAndProjectInfo();
        saveIdeaInfo();
        
        return "refreshIncomeTasks";
    }
    
    /* epowersd end */
    
    
    private InputStream inputStream;
    private String filename;
    /**
     * 正文材料下载
     * @return
     */
    public String downloadstuff() {
        IncomeDoc incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
        String fn = incomeDoc.getIncomeDocFileName();
            
        if (incomeDoc.getIncomeDocFile() == null){
            super.postAlertMessage("附件为空", response);
            return null;
        }
        try {
            if (incomeDoc.getIncomeDocFile() != null) {
                inputStream = new ByteArrayInputStream(incomeDoc.getIncomeDocFile());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        try {
            this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            
        return "download";
    }
    
    
    /**
     * 收文子流程：1、收文后，获取收文信息封装并流转到发文界面
     *             2、提交发文信息进入发文流程、
     * @return
     */
    public String toDispatchDocFlow(){
        // 封装全部对应的收发文信息
        return "startDispatchDoc";
    }
    
    
    public String saveIncomeDocReg(){
        
        try {
            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            
            if (baseInfo == null) {
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setFlowInstId(super.getFlowInstId());
                if (optBaseInfo.getFlowInstId() == null
                        || "".equals(optBaseInfo.getFlowInstId().toString())) {
                    optBaseInfo.setBizstate("F");
                }
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                optBaseInfo.setCreateuser(((FUserDetail) getLoginUser())
                        .getUsercode());
                //optBaseInfo.setBizstate("N");
            } else {
                IncomeDoc incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
                incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
                object = incomeDoc;
                optBaseInfoManager.copyObjectNotNullProperty(baseInfo, optBaseInfo);
                optBaseInfo = baseInfo;
            }
            
            // 收文业务数据保存
            
            optBaseInfoManager.saveObject(optBaseInfo);
            
            //incomeDocManage.saveObject(object);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return this.edit();
    }
    
    
    public String saveIncomeDoc(){
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
        
        if (baseInfo == null) {
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setFlowInstId(super.getFlowInstId());
            if (optBaseInfo.getFlowInstId() == null || "".equals(optBaseInfo.getFlowInstId().toString())) {
                optBaseInfo.setBizstate("F");
            }
            optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            optBaseInfo.setCreateuser(((FUserDetail) getLoginUser()).getUsercode());
            //optBaseInfo.setBizstate("N");
            object.setCreateDate(new Date(System.currentTimeMillis()));
        } else {
            IncomeDoc incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
            incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
            object = incomeDoc;
            
            optBaseInfoManager.copyObjectNotNullProperty(baseInfo, optBaseInfo);
            optBaseInfo = baseInfo;
        }
             
        object.setInternalNo(object.getDjId());
        object.setUpdateDate(new Date(System.currentTimeMillis()));
        
        incomeDocMgr.saveObject(object);
        optBaseInfoManager.saveObject(optBaseInfo);
                
        return this.edit();
    }

    
    /**
     * 保存并提交收文信息
     * 
     * @return
     */
    public String saveAndSubmitIncomeDoc() {

        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        if (optBaseInfo.getFlowInstId() == null || "".equals(optBaseInfo.getFlowInstId().toString())) {
            FUserDetail fuser = ((FUserDetail) getLoginUser());

            String flowCode = optBaseInfo.getFlowCode();
            
            if (flowCode == null || flowCode.trim().length() == 0) {
                flowCode = suppowerManager.getFlowCodeByOrgItem(optBaseInfo.getPowerid(), fuser.getPrimaryUnit());
            }
            FlowInstance flowInst = flowEngine.createInstance(flowCode,
                    optBaseInfo.getTransaffairname(),
                    optBaseInfo.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            object.getOptBaseInfo().setBizstate("W");
        }

        saveIncomeDoc();
        saveIdeaInfo();
        
      //向Rtx推送消息
        List<VuserTaskListOA> list=optBaseInfoManager.getTasksByNodeInstId(curNodeInstId);
        this.SendRtxMsn(object.getDjId(),list);
        return "refreshIncomeTasks";
    }

    /**
     * 向RTX信息表插入待发送的rtx待办信息
     * @param djId
     * @param list
     */
    private boolean SendRtxMsn(String djId, List<VuserTaskListOA> list) {
            boolean returnvalue=true;
            if(list!=null && list.size()>0){
            //只有在现场环境才发送消息
            String messwitch = CodeRepositoryUtil.getValue("SYSPARAM",
                    "MESSWITCH");    
            if(StringUtils.isBlank(messwitch) || "F".equals(messwitch)){
                returnvalue=false;
            }
            if (returnvalue) {

                FUserDetail loginInfo = (FUserDetail) getLoginUser();
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());          

                String url = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + request.getContextPath();
                url += "/page/rtx/signauth.jsp?url=dispatchdoc/vuserTaskListOA!list.do";

                for (VuserTaskListOA task : list) {
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
            }
            return returnvalue;
    }
    /**
     * 保存收文发起步骤过程日志信息
     */
    public void saveIdeaInfo() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();

        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());

        FUnitinfo fUnitinfo = sysUnitManager.getObjectById(loginInfo.getPrimaryUnit().trim());
        if (fUnitinfo == null) {
            fUnitinfo = new FUnitinfo();
        }
        optIdeaInfo.setUnitname(fUnitinfo.getUnitname());

        optIdeaInfo.setTransidea("阅办文登记");
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(0L);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename("阅办文登记");
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea("阅办文登记");

        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }
    
    
    public String viewIncomeDocInfo() {
        try {
            object = incomeDocMgr.getObjectById(object.getDjId());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return VIEW;
    }
    
    /**
     * 查询同步申报数据
     * @return
     */
    public String listSyncIncomeDoc(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("itemSource", "W");
        filterMap.put("bizstate", "W");
        String isGwdj = request.getParameter("isGwdj");
        filterMap.put("1".equals(isGwdj) ? "NP_gw" : "NP_sp", true);
        
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        incomeDocList = incomeDocListMgr.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "syncIncomeDoc";
    }
    
    @Override
    public String delete() {
        try {
          /*  FUserDetail fuser=(FUserDetail)getLoginUser();
            if(fuser==null){
                return "login";
            }
            //办件验证，保存日志
            //fuser.setUsercode("S0001033");
            if(listVOptBaseListVail(object.getDjId(), fuser)!=null){
            return "useroptlogError"; 
            }*/
            super.delete();
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            IncomeDoc incomeDoc = incomeDocMgr.getObjectById(object.getDjId());
         //   IncomeProject incomeProject = incomeProjectMgr.getObjectById(object.getDjId());
            
            List<OptStuffInfo> stuffInfoList = optProcInfoManager.listStuffsByDjId(object.getDjId());
            
        
            if (null != incomeDoc) {
                incomeDocMgr.deleteObjectById(object.getDjId());
            }
//            if (null != incomeProject) {
//                incomeProjectMgr.deleteObject(incomeProject);
//            }
            if(stuffInfoList!=null){
            for (int i=0; i<stuffInfoList.size(); i++) {
                optProcInfoManager.deleteStuffInfo(stuffInfoList.get(i));
            }
            }
            if (null != optBaseInfo) {
                optBaseInfoManager.deleteObject(optBaseInfo);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        
        return this.list();
    }
    
    /**
     * 根据流程编号设定的收文编号，（这块急用，写的 有点恶心，后面再改一下吧
     * @return
     */
    private String getWjlx(){
        String wjlx = "1";
        if (flowCode.equals("000536")) {
            wjlx = "4";
        } else if (flowCode.equals("000535")) {
            wjlx = "2";
        } else if (flowCode.equals("000533")) {
            wjlx = "3";
        } else if (flowCode.equals("000711")) {
            wjlx = "5";
        } else if (flowCode.equals("000811")) {
            wjlx = "6";
        } else if (flowCode.equals("000530")) {
            wjlx = "8";
        }
        return wjlx;
    }
    
   
    
   /* public String editShip(){
        object.setShip(optLeaderShipManager.getObjectById(object.getShip().getNo()));
        
        return "editShip";
    }*/
    
    
  /*  public String deleteShip(){
        String result = "";
        try {
            optLeaderShipManager.deleteObjectById(object.getShip().getNo());
            
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }
        
        return null;
    }*/
    
    
   /* public String saveShip(){
        String result = "";
        try {
            OptLeaderShip ship = object.getShip();
            
            if(StringUtils.isBlank(ship.getNo())){
                ship.setNo(optLeaderShipManager.getNextkey());
            }
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            ship.setCreateUserCode(fuser.getUsercode());
            ship.setCreateDate(new Date(System.currentTimeMillis()));
            optLeaderShipManager.saveObject(ship);
            
            result = ship.getNo();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }
        
        return null;
    }*/
    
    private UserbizoptLogManager userbizoptLogManager;
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    public String registerDoOrReadView() {
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
        
        //查看权限验证 false 不验证 true 验证 
        isVailViewPower=vOptBaseListManager.isVailViewPower(object.getDjId(), usercode);
        request.setAttribute("isVailViewPower", isVailViewPower);
        
        
        
        //保存查看日志
        UserbizoptLog l=userbizoptLogManager.listObject(object.getDjId(), usercode);
        if(l==null){
            object=incomeDocMgr.getObjectById(object.getDjId());
            
            UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(object.getIncomeDocTitle(), object.getDjId()));
            if(fuser == null){
                FUserinfo userInfo = CodeRepositoryUtil.getUserInfoByCode(usercode);
                fuser = sysUserManager.loadUserByUsername(userInfo.getLoginname());
                fuser.setLoginip(IPUtil.getIRealIPAddr(request));
            }
          
            userbizoptLogManager.saveUserbizoptLog(u,fuser);
        }
        this.registerDoOrReadEdit();
        
        // 设置在办件的超时提醒
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("djId", object.getDjId());
        List<VIncomeDocList> docList = incomeDocListMgr.listObjects(filterMap);
        if(null != docList && !docList.isEmpty()){
            VIncomeDocList doc = docList.get(0); 
            if(null != doc && "W".equals(doc.getBizstate())){
                // 设置办件超期状态
                object.setOverdueType(BizCommUtil.getOverDueState(object.getToBeanfinishedDate()));
            }
            request.setAttribute("bizstate", doc.getBizstate());
        }
      //归档信息
        Map<String,Object> cdtn = new HashMap<String,Object>();
        cdtn.put("no", object.getDjId());
        List<OaArchive> oaArchives = oaArchiveMag.listObjects(cdtn);
        if(oaArchives==null|| oaArchives.size()==0){
            request.setAttribute("hasArchived", false);
        }else{
            request.setAttribute("hasArchived", true);
            request.setAttribute("titanic",oaArchives.get(0).getTitanic());
            request.setAttribute("archtiveDate", DateUtil.date2String(oaArchives.get(0).getFilingdate(), "yyyy.MM.dd"));
        }
        
        //动态加载意见
        writeIdeaModuleToRequestAttribute(object.getDjId(),isVailViewPower);
        return "registerDoOrReadView";
    }
    
    private void writeIdeaModuleToRequestAttribute(String djId,boolean needValid){
        String isPdf = request.getParameter("isPdf");
        boolean showIdeaContent = false;
        //如果需要验证，那么检测是否有查看意见的权限  
        boolean hasViewPower = CodeRepositoryUtil.checkUserOptPower("DOCXZ","DocViewAll");
        if(!needValid || (needValid && hasViewPower)){
            showIdeaContent = true;
        }
        List<OptIdeaInfo> ideas= ideaTempletManager.loadAllAvailableIdeas(djId,showIdeaContent);
       
   //     如果是生成PDF    保留 SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);//内网地址 
   //                  否则  只保留上下文 /oa_szgh
        String relaceTag=SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);
        String contextPath="T".equals(isPdf)?relaceTag:"";
        if(null!=ideas&&ideas.size()>0){
            for(OptIdeaInfo idea:ideas){
                    idea.setTranscontent( idea.getTranscontent().replace(relaceTag, contextPath)) ;
            }
        }
        
        request.setAttribute("ideas", ideas);
    }
  
    public String registerPiece() {
        getPermitInfo();
        return "registerPiece";
    }
    
    public String registerPieceView() {
        getPermitInfo();
        return "registerPieceView";
    }
    
    
    private void getPermitInfo() {
        object = incomeDocMgr.getObjectById(object.getDjId());

        if (object != null) {
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            object.setOptBaseInfo(optBaseInfo);
            optId = optBaseInfo.getOptId();
        } else {
            object = new IncomeDoc();
            // 生成登记编号
            object.setDjId(incomeDocMgr.getNextkey("SW"));

            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setOptId(request.getParameter("optId"));
           
            // 生成办件编号：编号规则以SD0113打头+时间戳
            String no = "SD0113-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
            optBase.setTransAffairNo(no);
            optBase.setFlowCode(request.getParameter("flowCode"));
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            optBase.setAcceptArchiveNo(optFlowNoInfoManager.genFWH("SWBH", this.getWjlx(), null, fuser.getPrimaryUnit(), null));
            object.setOptBaseInfo(optBase);
            object.setItemId(request.getParameter("itemId"));
            object.setInternalNo(object.getDjId());
        }
    }


   
    
    /*********************公文回退节点********************************/
    
    /**
     * 公文回退编辑页面
     * @return
     */
    public String registerGWEdit(){
        this.registerDoOrReadEdit();        
        return "registerGWEdit";
    }
    
    
    /**
     * 保存回退后重新编辑后的公文信息
     * @return
     */
    public String saveGW(){        
        this.saveRegisterDoOrRead();
        return "refreshTasks";
    }
    
    /**
     * 提交回退后重新编辑后的回退后重新编辑后的公文信息
     * @return
     */
    public String saveAndSubmitGW(){     
        saveGW();
        saveIdeaInfo();    
        submitNode();
        return this.nextStep();
    }
    
    /***********************公文回退节点********************************/
    
    /**
     * 保存：办（阅办）件以及基本信息
     * @return
     */
    public String saveRegisterPiece(){
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(fuser.getPrimaryUnit());
        fuerunit.setUserCode(fuser.getUsercode());
        
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        try {
            if ("T".equals(object.getOptBaseInfo().getBizstate()) || null == object.getOptBaseInfo().getFlowInstId() 
                    || "".equals(object.getOptBaseInfo().getFlowInstId().toString())) {
                OptBaseInfo optBaseInfo = object.getOptBaseInfo();
                optId = optBaseInfo.getOptId();
                OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
                
                if (baseInfo == null) {
                    optBaseInfo.setDjId(object.getDjId());
                    optBaseInfo.setFlowInstId(super.getFlowInstId());
                    if (optBaseInfo.getFlowInstId() == null || "".equals(optBaseInfo.getFlowInstId())) {
                        optBaseInfo.setBiztype("F");// 未提交标志
                    }
                    optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                    optBaseInfo.setCreateuser(((FUserDetail) getLoginUser())
                            .getUsercode());
                    object.setCreateDate(new Date(System.currentTimeMillis()));
                    //optBaseInfo.setBizstate("N");
    
                } else {
                    IncomeDoc incomeDoc=incomeDocMgr.getObjectById(object.getDjId()); 
                    incomeDocMgr.copyObjectNotNullProperty(incomeDoc, object);
                    object = incomeDoc;
                    optBaseInfoManager.copyObjectNotNullProperty(baseInfo, optBaseInfo);
                    optBaseInfo = baseInfo;
                    optBaseInfo.setDjId(object.getDjId());
                }
                
                
                if ("T".equals(optBaseInfo.getBizstate())) {
                    object.setOperateState("2");
                } else {
                    object.setOperateState("1");
                }
                
                if (StringUtils.isBlank(optBaseInfo.getAcceptArchiveNo())) {
                    optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genRecordFWH("SWBH", this.getWjlx(), null, fuser.getPrimaryUnit()));
                }
                
                // 保存权力事项
                String powerId = object.getItemId();
                VOrgSupPower power = suppowerManager.getSupPowerInfo(powerId, fuser.getPrimaryUnit());
                optBaseInfo.setPowerid(powerId);
                optBaseInfo.setPowername(power.getItemName());
    //            optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
                if (StringUtils.isBlank(object.getIncomeDocTitle())){
                    optBaseInfo.setTransaffairname(object.getProjectName());
                } else {
                    optBaseInfo.setTransaffairname(object.getIncomeDocTitle());
                }
                if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
                    flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
                }
                
                optBaseInfo.setOrgcode(fuerunit.getUnitCode());
                optBaseInfo.setOrgname(fuerunit.getUnitName());
                optBaseInfo.setHeadunitcode(fuerunit.getUnitCode());
                optBaseInfo.setHeadusercode(fuerunit.getUserCode());
      
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setOptId(optId);
                
                object.setInternalNo(object.getDjId()); // 非空字段，暂时设置一致
                object.setUpdateDate(new Date(System.currentTimeMillis()));
                
                incomeDocMgr.saveObject(object);
                optBaseInfoManager.saveObject(optBaseInfo);
            }
            
            return this.registerDoOrReadEdit();
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存办（阅办）件信息出错，详见系统日志。");
            return ERROR;
        }
    }
    
    
    /**
     * 保存并提交基础业务数据:行政许可业务数据\行政基础信息表\7类申请事项
     * 
     * @return
     */
    public String saveAndSubmitRegisterPiece() {        
        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
        
        if (null == optBaseInfo.getFlowInstId() || "".equals(optBaseInfo.getFlowInstId().toString())) {
            FUserDetail fuser = ((FUserDetail) getLoginUser());        
            
            if (StringUtils.isBlank(flowCode)) {
                flowCode = suppowerManager.getFlowCodeByOrgItem(object.getItemId(), fuser.getPrimaryUnit());
            }
            FlowInstance flowInst = flowEngine.createInstance(
                    flowCode, object.getIncomeDocTitle(),
                    object.getDjId(), fuser.getUsercode(),
                    fuser.getPrimaryUnit());
            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.getOptBaseInfo().setFlowInstId(flowInstId);
            object.getOptBaseInfo().setBizstate("W");
            object.getOptBaseInfo().setBiztype("W");
        }
        
        this.saveRegisterPiece();
        saveIdeaInfo();      
        return this.nextStep();
    }
    
    
    /**
     * 如果下一步骤含本人，直接进入下一步骤； 如果不含本人，提示办理完毕，返回待办件列表
     */
    public String nextStep() {
        NodeInstance nit = flowEngine.getNodeInstById(curNodeInstId);
        FlowInstance inst = super.getFlowManager().getFlowInstance(nit.getFlowInstId());

        long nextNodeInstId = 0l;
        for (NodeInstance nodeInst : inst.getActiveNodeInstances()) {
            String url = super.getFlowManager().getNodeOptUrl(
                    nodeInst.getNodeInstId(),
                    ((FUserDetail) getLoginUser()).getUsercode());
            if (url != null && nextNodeInstId < nodeInst.getNodeInstId()
                    && !"".equals(url.trim())) {
                nextNodeInstId = nodeInst.getNodeInstId();
                nextOptUrl = "/" + url;
            }
        }
        
        return nextNodeInstId > 0l ? "gotoNext" : "refreshTasks";
    }
    
    
    public String byj_officeBranch(){
        this.view();
        // 初始化页面form对应的actionName、提交方法以及保存方法名
        super.initalGenneralOpt("ioDocTasksExcute", "submitOpt", "saveOpt");
        this.generalOpt();
        request.setAttribute("iszbfb", request.getParameter("iszbfb"));
        request.setAttribute("isxb", request.getParameter("isxb"));
        return "byj_officeBranch";
    }
    
    
    public String getPieceFrame(){
        this.view();
        // 初始化页面form对应的actionName、提交方法以及保存方法名
        super.initalGenneralOpt("ioDocTasksExcute", "submitOpt", "saveOpt");
        FUserDetail fUserDetail = (FUserDetail) getLoginUser();
        Set<String> users = SysUserFilterEngine.calcOperators(
                "D(U)" ,
                fUserDetail.getPrimaryUnit(), null, null, null,
                fUserDetail.getUsercode(), null);
        JSONArray userjson = new JSONArray();
        if (users != null) {
            for (String user : users) {
                String username = CodeRepositoryUtil.getValue("usercode", user);
                JSONObject usermap = new JSONObject();
                usermap.put("name", username);
                usermap.put("nodeID", user);
                usermap.put("belongId", "-1");
                usermap.put("levle", 2);
                userjson.add(usermap);
            }
        }
        
        FUserunit dept = sysUserManager.getUserPrimaryUnit(fUserDetail.getUsercode());
        if (dept != null){
            unitsJson = sysUnitManager.getAllUnitsJSON(dept.getUnitcode());
        } else {
            unitsJson = "{}";
        }
        request.setAttribute("userjson", userjson);
        return "pieceFrame";
    }
    
    
    /**
     * 查看业务信息
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String viewIncomeDoc(){
        this.view();
        OptBaseInfo optBaseInfo=optBaseInfoManager.getObjectById(object.getDjId());
        object.setOptBaseInfo(optBaseInfo);
        Map<String, Object> optMap = new HashMap();
        optMap.put("djId", object.getDjId());
        //optLeaderShipList = optLeaderShipManager.listObjects(optMap);
        return "viewIncomeDoc";
    }

    /**
     * 办（阅办）件---结束
     */
    
    /* manager的set方法，以及参数的get/set方法 */
    /**
     * 查询并管理某流程实例下的所有节点信息
     * @return String
     */
    private FlowManager flowManager;
    private List<NodeInstance> nodeList;
    private List<ManageActionLog> flowLogList;
    private FlowInstance fobject;
    private List<FlowNodeInfo> flowNodeList;
    protected Integer nd_totalRows;
    protected Integer wf_totalRows;
    private ExtraFlowManager extraFlowManager;
    private String timeLimit;

   

    public String listFlowInstNodes(){
        Long flowInstId=Long.parseLong(request.getParameter("flowInstId"));
        fobject = flowManager.getFlowInstance(flowInstId);
        nodeList = flowManager.listFlowInstNodes(flowInstId);
        flowLogList = flowManager.listManageActionLog(flowInstId);
        
        
        FlowInstance flowInst = flowEngine.getFlowInstById(flowInstId);
        
        FlowDescribe flow = flowDefine.getFlowDefObject(flowInst.getFlowCode());
        Set<FlowNodeInfo> nodeSet = flow.getFlowNodes();
        flowNodeList = new ArrayList<FlowNodeInfo>(nodeSet);
        nd_totalRows = nodeList.size();
        wf_totalRows = flowLogList.size();
        request.setAttribute("flowInstId", flowInstId);
        return "SWListNodes";
    }
    
    /*
     * 暂挂一个流程实例
     */
    public String suspendInstance() {
        String mangerUserCode = ((FUserDetail)getLoginUser())
                .getUsercode();
        String  admindesc =  request.getParameter("stopDesc"); 
        Long flowInstId=Long.parseLong(request.getParameter("flowInstId"));
        flowManager.suspendInstance(flowInstId,mangerUserCode, admindesc);
        
        if(extraFlowManager !=null){
            extraFlowManager.suspendInstance(flowInstId, mangerUserCode, admindesc);
        }
        request.setAttribute("flowInstId", flowInstId);
        return "SWRefreshNodes";
    }
    
    /*
     * 唤醒一个暂挂流程实例
     */
    public String awakeInstance() {
        String mangerUserCode =((FUserDetail)getLoginUser()).getUsercode();
        String authDesc =  request.getParameter("authDesc");
        Long flowInstId=Long.parseLong(request.getParameter("flowInstId"));
        if (timeLimit != null && !"".equals(timeLimit.trim())) {
            flowManager.activizeExpireInstance(flowInstId, timeLimit,
                    mangerUserCode, authDesc);
            if(extraFlowManager !=null){
                extraFlowManager.activizeExpireInstance(flowInstId,timeLimit, mangerUserCode, authDesc);
            }
        } else {
            flowManager.activizeInstance(flowInstId, mangerUserCode,
                    authDesc);
            if(extraFlowManager !=null){
                extraFlowManager.activizeInstance(flowInstId, mangerUserCode, authDesc);
            }
        }
        request.setAttribute("flowInstId", flowInstId);
        return "SWRefreshNodes";
    }
    
    /*
     * 终止止一个流程实例
     */
    public String stopInstance() {
        String mangerUserCode = ((FUserDetail)getLoginUser())
                .getUsercode();
        String  admindesc =  request.getParameter("stopDesc");   
        Long flowInstId=Long.parseLong(request.getParameter("flowInstId"));
        flowManager.stopInstance(flowInstId, mangerUserCode, admindesc);
        
        if(extraFlowManager !=null){
            extraFlowManager.stopInstance(flowInstId, mangerUserCode, admindesc);
        }
        request.setAttribute("flowInstId", flowInstId);
        return "SWRefreshNodes";
    }
    /**
     * 暂挂一个节点实例
     */
    public String suspendNodeInst() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.suspendNodeInstance(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 强制一个流程节点前进到下一个节点
     */
    public String forceCommit() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.forceCommit(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 回滚一个流程节点到上一节点
     */
    public String rollbackOpt() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.rollbackOpt(nodeInstId, ((FUserDetail)getLoginUser())
                    .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 针对一个正在运行的节点实例强制游离
    */
    public String forceDissociate() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.forceDissociateRuning(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }

    /**
     * 针对一个正在运行且被强制游离的节点实例，结束游离状态
    */
    public String stopDissociate() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.stopDissociateNodeInst(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 从这个节点重新运行该流程，包括已经结束的流程
     * @return
     */
    public String resetToCurrent(){
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.resetFlowToThisNode(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode()); 
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 针对一个完成的节点实例，创建游离节点
    */
    public String createDissociate() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        flowManager.createDissociateNodeInst(nodeInstId, ((FUserDetail)getLoginUser())
                .getUsercode());
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    /**
     * 唤醒一个暂挂节点实例
     */
    public String awakeNodeInst() {
        Long nodeInstId=Long.parseLong(request.getParameter("nodeInstId"));
        String mangerUserCode =((FUserDetail)getLoginUser()).getUsercode();
        if (timeLimit != null) {
            flowManager.activizeExpireNodeInstance(nodeInstId, timeLimit,
                    mangerUserCode);
        } else {
            flowManager.activizeNodeInstance(nodeInstId, mangerUserCode);
        }
        request.setAttribute("nodeInstId", nodeInstId);
        request.setAttribute("flowInstId", Long.parseLong(request.getParameter("flowInstId")));
        return "SWRefreshNodes";
    }
    
    /*
     * 查询出所有发文单位
     */
    public void getMatchedData(){
        
        StringBuffer sbDept = new StringBuffer();
        StringBuffer sbAccept = new StringBuffer();

        List<String> deptList = incomeDocMgr.getIncomeDeptNames();
        List<String> acceptList = incomeDocMgr.getIncomeAcceptNos();
        
        if (null != deptList && !deptList.isEmpty()) {
            for (String s : deptList) {
                sbDept.append(StringUtils.trim(s) + ",");
            }
        }
        
        if (null != acceptList && !acceptList.isEmpty()) {
            for (String s : acceptList) {
                sbAccept.append(StringUtils.trim(s) + ",");
            }
        }
        
        if(sbDept.length() > 0){
            sbDept = new StringBuffer(sbDept.substring(0, sbDept.length() - 1));
        }
        if(sbAccept.length() > 0){
            sbAccept = new StringBuffer(sbAccept.substring(0, sbAccept.length() - 1));
        }
        request.setAttribute("incomeDeptNames", sbDept.toString());
        request.setAttribute("incomeAcceptNos", sbAccept.toString());
        
    }
    
    public void setIncomeDocManager(IncomeDocManager basemgr) {
        this.incomeDocMgr = basemgr;
        super.setBaseEntityManager(basemgr);
    }

    public void setIncomeProjectManager(IncomeProjectManager incomeProjectMgr) {
        this.incomeProjectMgr = incomeProjectMgr;
    }

    public void setIncomeDocListManager(VIncomeDocListManager incomeDocListMgr) {
        this.incomeDocListMgr = incomeDocListMgr;
    }

    public List<VIncomeDocList> getIncomeDocList() {
        return incomeDocList;
    }

    public void setOptFlowNoInfoManager(OptFlowNoInfoManager optFlowNoInfoManager) {
        this.optFlowNoInfoManager = optFlowNoInfoManager;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getNextOptUrl() {
        return nextOptUrl;
    }

    public void setNextOptUrl(String nextOptUrl) {
        this.nextOptUrl = nextOptUrl;
    }

    public void setIncomeDocList(List<VIncomeDocList> incomeDocList) {
        this.incomeDocList = incomeDocList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }

    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.powerMgr = vsuppowerinusingManager;
    }

    public String getNodeOptUrl() {
        return nodeOptUrl;
    }

    public void setNodeOptUrl(String nodeOptUrl) {
        this.nodeOptUrl = nodeOptUrl;
    }

    public File getIncomeDocFile_() {
        return incomeDocFile_;
    }

    public void setIncomeDocFile_(File incomeDocFile_) {
        this.incomeDocFile_ = incomeDocFile_;
    }

    public String getIncomeDocFile_FileName() {
        return incomeDocFile_FileName;
    }

    public void setIncomeDocFile_FileName(String incomeDocFile_FileName) {
        this.incomeDocFile_FileName = incomeDocFile_FileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
    
    public String getShow_type() {
        return show_type;
    }


    public void setShow_type(String show_type) {
        this.show_type = show_type;
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
    public List<Map<String, String>> getJsonList() {
        return jsonList;
    }
    public void setJsonList(List<Map<String, String>> jsonList) {
        this.jsonList = jsonList;
    }
    public List<FDatadictionary> getDatadictionary() {
        return datadictionary;
    }
    public void setDatadictionary(List<FDatadictionary> datadictionary) {
        this.datadictionary = datadictionary;
    }
    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }
    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
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
    public String getQueryUnderUnit() {
        return queryUnderUnit;
    }
    public void setQueryUnderUnit(String queryUnderUnit) {
        this.queryUnderUnit = queryUnderUnit;
    }
    public List<FUnitinfo> getUnitList() {
        return unitList;
    }
    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }
    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }
    public void setVOptProcCollectionManager(
            VOptProcCollectionManager vOptProcCollectionMag) {
        VOptProcCollectionMag = vOptProcCollectionMag;
    }
    
    
    public FlowManager getFlowManager() {
        return flowManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public List<NodeInstance> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<NodeInstance> nodeList) {
        this.nodeList = nodeList;
    }

    public List<ManageActionLog> getFlowLogList() {
        return flowLogList;
    }

    public void setFlowLogList(List<ManageActionLog> flowLogList) {
        this.flowLogList = flowLogList;
    }

    public FlowInstance getFobject() {
        return fobject;
    }

    public void setFobject(FlowInstance fobject) {
        this.fobject = fobject;
    }


    public List<FlowNodeInfo> getFlowNodeList() {
        return flowNodeList;
    }

    public void setFlowNodeList(List<FlowNodeInfo> flowNodeList) {
        this.flowNodeList = flowNodeList;
    }

    public Integer getNd_totalRows() {
        return nd_totalRows;
    }

    public void setNd_totalRows(Integer nd_totalRows) {
        this.nd_totalRows = nd_totalRows;
    }

    public Integer getWf_totalRows() {
        return wf_totalRows;
    }

    public void setWf_totalRows(Integer wf_totalRows) {
        this.wf_totalRows = wf_totalRows;
    }
    public ExtraFlowManager getExtraFlowManager() {
        return extraFlowManager;
    }

    public void setExtraFlowManager(ExtraFlowManager extraFlowManager) {
        this.extraFlowManager = extraFlowManager;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }
    public void setOptPdfInfoManager(OptPdfInfoManager optPdfInfoManager) {
        this.optPdfInfoManager = optPdfInfoManager;
    }
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public GeneralModuleParam getModuleParam() {
        return moduleParam;
    }

    public void setModuleParam(GeneralModuleParam moduleParam) {
        this.moduleParam = moduleParam;
    }

}