package com.centit.dispatchdoc.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OptLeaderShip;
import com.centit.dispatchdoc.po.SignedReport;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.service.OptLeaderShipManager;
import com.centit.dispatchdoc.service.SignedReportManager;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeInstance;
	

public class SignedReportAction  extends IODocCommonBizAction<SignedReport> implements ServletResponseAware {
	private static final Log log = LogFactory.getLog(SignedReportAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private SignedReportManager signedReportMag;

 
    public void setSignedReportManager(SignedReportManager basemgr) {
        signedReportMag = basemgr;
        this.setBaseEntityManager(signedReportMag);
    }

	  private VsuppowerinusingManager powerMgr;
	    
	    private JSONObject json;
	    private String unitsJson; //部门JSON
	    private String nodeOptUrl;
	    
	    private List<VIncomeDocList> incomeDocList;
	    private OptFlowNoInfoManager optFlowNoInfoManager;
	    
	   // private File incomeDocFile_;
	   // private String incomeDocFile_FileName;
	    private List<OptLeaderShip> optLeaderShipList;
	    private OptLeaderShipManager optLeaderShipManager;
	    private String show_type;//区别查看列表页面是否显示新增按钮,or编辑页面是否有返回按钮
	    
	    private String curUrl;
	    private List<OptNewlyIdea> optNewlyIdeaList;
	    private OptNewlyIdeaManager optNewlyIdeaManager;
	    private String optId;
	    private String powerName;
	    private String flowCode;
	    private String nextOptUrl;
	    private String itemId;
	    private Long flowInstId;
	    private OaSuperviseManager oaSuperviseManager;
	    private  List<OaSupervise> oasuplist;
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



    /**
     * 签报登记--开始
     * @return
     */
    public String registerDoOrReadEdit() {
        try {
           
            if (StringUtils.isBlank(object.getDjId())) {
               
               object.setDjId(signedReportMag.getNextkey("QB"));   //签报流水号
                   
            } else {
                object=signedReportMag.getObjectById(object.getDjId());                
            }
            if(object!=null && null==object.getDraftDate()){
                object.setDraftDate(new Date());
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
                optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genFWH("SWBH", this.getWjlx(), null, fuser.getPrimaryUnit(), null));
                
                optBaseInfo.setPowerid(object.getItemId());
                optBaseInfo.setPowername(vOrgSupPower.getItemName());
            }                
            object.setOptBaseInfo(optBaseInfo);  
            Map<String, Object> optMap = new HashMap<String, Object>();
            optMap.put("djId", object.getDjId());
            optLeaderShipList = optLeaderShipManager.listObjects(optMap);
            genGroupId();//自动加载材料分组
            
            moduleParam = new  GeneralModuleParam ();
            moduleParam.setHasDocument("T");
            moduleParam.setDocumentType("zw");
            super.initTemplateConfig();
            
        } catch (Exception e) {
            e.printStackTrace();            
            return ERROR;
        }
        //根据flowinstid来判断是新登记还是流程中拟稿
        String flowInstId=request.getParameter("flowInstId");
        if(StringUtils.isNotBlank(flowInstId)){
            return "ngForm";
        }else{
            return "signedReportForm";
        }
    }
	
    
    /**
     * 保存：基本信息
     * @return
     */
    public String saveRegisterDoOrRead(){
        try {
            if ("T".equals(object.getOptBaseInfo().getBizstate()) || null == object.getOptBaseInfo().getFlowInstId() 
                    || "".equals(object.getOptBaseInfo().getFlowInstId().toString())) {
                
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
                object.setDraftDate(new Date(System.currentTimeMillis()));
         
                //optBaseInfo.setBizstate("N");
                //保存流程的相关信息进业务表
                object.setFlowcode(flowCode);
                object.setBizstate("F");// 未提交标志
                object.setBiztype("F");// 未提交标志
                object.setOptid(optId);
            }else {
                SignedReport signedReport=signedReportMag.getObjectById(object.getDjId()); 
                signedReportMag.copyObjectNotNullProperty(signedReport, object);
                object = signedReport;
                optBaseInfoManager.copyObjectNotNullProperty(baseInfo,optBaseInfo);
                optBaseInfo = baseInfo;
                optBaseInfo.setDjId(object.getDjId());
            }
            
        
            FUserDetail fuser = ((FUserDetail) getLoginUser());
//            if (StringUtils.isBlank(optBaseInfo.getAcceptArchiveNo())) {
//                optBaseInfo.setAcceptArchiveNo(optFlowNoInfoManager.genRecordFWH("QBBH", this.getWjlx(), null, fuser.getPrimaryUnit()));
//            }
            if(baseInfo==null){
        
            optBaseInfo.setAcceptArchiveNo(object.getSignedReportNo());
            // 保存权力事项
            String powerId = object.getItemId();
            VOrgSupPower power = suppowerManager.getSupPowerInfo(powerId, fuser.getPrimaryUnit());
            optBaseInfo.setPowerid(powerId);
            optBaseInfo.setPowername(power.getItemName());
            optBaseInfo.setTransaffairname(object.getSignedReportTitle());

            VUserUnits fuerunit = new VUserUnits();
            fuerunit.setUnitCode(fuser.getPrimaryUnit());
            fuerunit.setUserCode(fuser.getUsercode());
            
            fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
            
            optBaseInfo.setOrgcode(fuerunit.getUnitCode());
            optBaseInfo.setOrgname(fuerunit.getUnitName());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setOptId(optId);
            object.setItemId(powerId);
            object.setDjId(object.getDjId()); // 非空字段，暂时设置一致
            object.setDraftDate(new Date(System.currentTimeMillis()));
            }
       
            signedReportMag.saveObject(object);
            optBaseInfoManager.saveObject(optBaseInfo);
            } else {//这边是从退回编辑流程过来的
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
              
                    optBaseInfo.setTransaffairname(object.getSignedReportTitle());
               
                if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
                    flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
                }
                
                SignedReport signedReport=signedReportMag.getObjectById(object.getDjId()); 
                signedReportMag.copyObjectNotNullProperty(signedReport, object);
                object = signedReport;
                signedReportMag.saveObject(object);
            }
            return this.registerDoOrReadEdit();
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存信息出错，详见系统日志。");
            return ERROR;
        }
    }
    
    
    /**
     * 保存并提交基础业务数据:行政许可业务数据\行政基础信息表\7类申请事项
     * @return
     */
    public String saveAndSubmitRegisterDoOrRead() {  
        
        this.saveRegisterDoOrRead();
    
         object=signedReportMag.getObjectById(object.getDjId());
            
       
      
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
            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;

            object.setFlowinstid(flowInstId);
            object.setBizstate("T");
            object.setBiztype("W");
            //提交流程时update流程状态
            signedReportMag.saveObject(object);
            
            optBaseInfo.setFlowInstId(flowInstId);
            optBaseInfo.setBizstate("T");
            optBaseInfo.setBiztype("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            
        }
        
      
        saveIdeaInfo("签报流程发起");      
        return this.list();
     //  return this.nextStep();
    }
    //登记页面的办理意见提交流程时保存进日志信息
    private String transcontent;
    
    
    public String getTranscontent() {
        return transcontent;
    }

    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }

    /**
     * 保存收文发起步骤过程日志信息
     */
    public void saveIdeaInfo(String nodename) {
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
        procInfo.setNodeInstId(curNodeInstId);
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
    
    /**
     * 通用业务框架属性信息查看
     */
     
     public String generalOptView() {
         List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
         // 查看办件业务数据信息
         frameList.add(getBizDataViewFrame(object.getDjId()));
         // 用于展示查看详细信息Lab标签内容
         frameList.add(this.getAllViewFrame(object.getDjId()));
         
         
         jspInfo = new OptJspInfo();
//         jspInfo.setTitle("签报查看");
         jspInfo.setFrameList(frameList);
         return "generalOptView";
     }
    
     /**
      * 用于展示查看详细信息Lab标签内容
      * @param djid
      * @return
      */
     private OptHtmlFrameInfo getAllViewFrame(String djid) {
         OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
         stuffsFrameInfo.setFrameId("viewStuffsFrame");
         stuffsFrameInfo
                .setFrameSrc("/dispatchdoc/signedReport!getAllCaseView.do?djId="
                        + djid + "&nodeInstId=" + curNodeInstId);
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
            object = signedReportMag.getObject(object);
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
            return "AllmilitarycaseView";
        }
     /**
      * 签报业务数据查看
      * @param djid
      * @return
     */
     private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
         OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
         viewFrameInfo.setFrameId("viewFrame");   
         viewFrameInfo
            .setFrameSrc("/dispatchdoc/signedReport!view.do?vewtype=T&djId=" + djid);
         viewFrameInfo.setFrameHeight("700px");
         return viewFrameInfo;
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
    public String list() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = signedReportMag.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 保存签报流程拟稿信息
     * 
     */
    public String savenginfo(){
        try {
     
           //这边是从退回编辑流程过来的
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
              
                    optBaseInfo.setTransaffairname(object.getSignedReportTitle());
               
                if (null != optBaseInfo.getFlowInstId() && optBaseInfo.getFlowInstId() > 0) {
                    flowEngine.updateFlowInstOptInfo(optBaseInfo.getFlowInstId(), optBaseInfo.getTransaffairname(), optBaseInfo.getDjId());
                }
                
                SignedReport signedReport=signedReportMag.getObjectById(object.getDjId()); 
                signedReportMag.copyObjectNotNullProperty(signedReport, object);
                object = signedReport;
                signedReportMag.saveObject(object);
            
            return null;
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "保存办（阅办）件信息出错，详见系统日志。");
            return ERROR;
        }
    }
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
    /*********************************** getter()、setter() ****************************************/

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

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

	   public JSONObject getJson() {
            return json;
        }

        public void setJson(JSONObject json) {
            this.json = json;
        }

        public String getUnitsJson() {
            return unitsJson;
        }

        public void setUnitsJson(String unitsJson) {
            this.unitsJson = unitsJson;
        }

        public String getNodeOptUrl() {
            return nodeOptUrl;
        }

        public void setNodeOptUrl(String nodeOptUrl) {
            this.nodeOptUrl = nodeOptUrl;
        }

        public List<VIncomeDocList> getIncomeDocList() {
            return incomeDocList;
        }

        public void setIncomeDocList(List<VIncomeDocList> incomeDocList) {
            this.incomeDocList = incomeDocList;
        }

        public List<OptLeaderShip> getOptLeaderShipList() {
            return optLeaderShipList;
        }

        public void setOptLeaderShipList(List<OptLeaderShip> optLeaderShipList) {
            this.optLeaderShipList = optLeaderShipList;
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

        public List<OptNewlyIdea> getOptNewlyIdeaList() {
            return optNewlyIdeaList;
        }

        public void setOptNewlyIdeaList(List<OptNewlyIdea> optNewlyIdeaList) {
            this.optNewlyIdeaList = optNewlyIdeaList;
        }

        public OptNewlyIdeaManager getOptNewlyIdeaManager() {
            return optNewlyIdeaManager;
        }

        public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
            this.optNewlyIdeaManager = optNewlyIdeaManager;
        }
        public void setVsuppowerinusingManager(
                VsuppowerinusingManager vsuppowerinusingManager) {
            this.powerMgr = vsuppowerinusingManager;
        }
        public void setOptFlowNoInfoManager(OptFlowNoInfoManager optFlowNoInfoManager) {
            this.optFlowNoInfoManager = optFlowNoInfoManager;
        }

        public void setOptLeaderShipManager(OptLeaderShipManager optLeaderShipManager) {
            this.optLeaderShipManager = optLeaderShipManager;
        }

        public Long getFlowInstId() {
                return flowInstId;
            }

       public void setFlowInstId(Long flowInstId) {
                this.flowInstId = flowInstId;
            }

}
