package com.centit.dispatchdoc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.IncomeDocTask;
import com.centit.dispatchdoc.po.IncomeProject;
import com.centit.dispatchdoc.po.SubprocessProject;
import com.centit.dispatchdoc.service.IODocTasksManager;
import com.centit.dispatchdoc.service.IncomeProjectManager;
import com.centit.dispatchdoc.service.SubprocessProjectManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptWritdef;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.service.OptWritdefManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.sample.optmodel.BaseWFEntityAction;
import com.centit.workflow.sample.po.WfFlowInstance;
	

public class SubprocessProjectAction  extends BaseWFEntityAction<SubprocessProject>  {
	private static final Log log = LogFactory.getLog(SubprocessProjectAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	private SysUnitManager sysUnitManager;//部门Service
	private SysUserManager sysUserManager;
	private String parentUnit;//父部门ID
	private String flowCode;
	private OptProcInfoManager optProcInfoManager;
	private OptBaseInfoManager optBaseInfoManager;
	private OptWritdefManager optWritdefManager;
	
	private String optId;
	private FunctionManager functionManager;
	 private IODocTasksManager ioDocTasksManager;
	
	 private JSONObject result;
	 private List<SubprocessProject> subprocessProjectList;
    public JSONObject getResult() {
        return result;
    }

    public List<SubprocessProject> getSubprocessProjectList() {
        return subprocessProjectList;
    }

    public void setSubprocessProjectList(
            List<SubprocessProject> subprocessProjectList) {
        this.subprocessProjectList = subprocessProjectList;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public void setIoDocTasksManager(IODocTasksManager ioDocTasksManager) {
        this.ioDocTasksManager = ioDocTasksManager;
    }

    public void setIncomeProjectManager(IncomeProjectManager incomeProjectManager) {
        this.incomeProjectManager = incomeProjectManager;
    }


    private IncomeProjectManager incomeProjectManager;
	
	public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public void setOptWritdefManager(OptWritdefManager optWritdefManager) {
        this.optWritdefManager = optWritdefManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
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

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }


    private String unitsJson;//部门JSON
	private static final long serialVersionUID = 1L;
	private SubprocessProjectManager subprocessProjectManager;
	public void setSubprocessProjectManager(SubprocessProjectManager basemgr)
	{
	    subprocessProjectManager = basemgr;
		this.setBaseEntityManager(subprocessProjectManager);
	}

	public String edit(){
	    FUserDetail user = ((FUserDetail)getLoginUser());
	    Long  preinstid  = object.getFlowInstId();
	    Long prenodeinstid = object.getNodeInstId();
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        if(dept!=null){
            
            unitsJson = sysUnitManager.getAllSubUnitsJSON(dept.getUnitcode());
            
            FUnitinfo unit = sysUnitManager.getObjectById(dept.getUnitcode());
            
            parentUnit = unit.getParentunit();
            
         } else {
             unitsJson = "{}";
         }
        String supDjId = object.getSupDjId();
       // System.out.println(object.getSupDjId() + "-----------" + object.getNodeInstId());
        OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(supDjId);
        IncomeProject incomeInfo = incomeProjectManager.getObjectById(supDjId);
        object.setPreinstid(object.getFlowInstId());
        object.setPrenodeinstid(object.getNodeInstId());
        this.getPermitInfo();
        if(baseInfo != null && baseInfo.getCreatedate() != null){
            object.setAccpetDate(DatetimeOpt.convertSqlDate(baseInfo.getCreatedate()));
            object.setHeadunitcode(baseInfo.getOrgcode());
            object.setSupDjId(supDjId);
            object.setProjectUnitName(incomeInfo.getProjectUnitName());
            object.setProjectName(incomeInfo.getProjectName());
            object.setProjectType(incomeInfo.getProjectType());
        }
        object.setPreinstid(preinstid);
        object.setPrenodeinstid(prenodeinstid);
        //System.out.println(object.getPreinstid() + "-----------" + object.getPrenodeinstid());
        
        return EDIT;
	}
	 /**
     * 查询许可登记信息
     */
    private void getPermitInfo() {

        object = subprocessProjectManager.getObjectById(object.getDjId());

        if (object != null) {
           
            // System.out.println(optBaseInfoJson);
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            object.setOptBaseInfo(optBaseInfo);
            
            optId = optBaseInfo.getOptId();

      
        } else {
            object = new SubprocessProject();
            // 生成登记编号
            object.setDjId(subprocessProjectManager.generateNextDjId());

            OptBaseInfo optBase = new OptBaseInfo();
            optBase.setOptId(optId);
           
            // 生成办件编号：编号规则以JS0113打头+时间戳
            optBase.setTransAffairNo("JS0113-"
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(
                            System.currentTimeMillis())));
            optBase.setFlowCode(request.getParameter("flowCode"));
            //optBase.setFlowInstId(12l);
            object.setOptBaseInfo(optBase);
        }
    /*    List<SeaRouteInfo> rrList = new ArrayList<SeaRouteInfo>();
        rrList = seaRouteInfoManager.listObjects();
        this.genSelectList(sriList, rrList, 28);*/
        // 根据业务编码，获取流程编码
        FOptinfo optInfo = functionManager.getObjectById(optId);
        if (optInfo != null) {
            flowCode = "000500";
        }
    }

	 public String savePermitReg() {
	     try {

	            OptBaseInfo optBaseInfo = object.getOptBaseInfo();
	            OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object
	                    .getDjId());
	            object.setDjId(subprocessProjectManager.generateNextDjId());
	            FUserDetail user = ((FUserDetail)getLoginUser());
	            VUserUnits fuerunit = new VUserUnits();
	            fuerunit.setUnitCode(user.getPrimaryUnit());
	            fuerunit.setUserCode(user.getUsercode());
	            fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
	            if (baseInfo == null) {
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
	                optBaseInfo.setTransAffairNo(object.getDjId());
	                OptBaseInfo sInfo = optBaseInfoManager.getObjectById(object.getSupDjId());
	                String processType = object.getProcessType();
	                if(!StringBaseOpt.isNvl(processType)){
	                    processType = CodeRepositoryUtil.getDataPiece("sub_processType", processType)== null ? "" :CodeRepositoryUtil.getDataPiece("sub_processType", processType).getDatavalue();;
	                }
	                optBaseInfo.setTransaffairname("关于"+sInfo.getTransaffairname()+"的"+processType+"子流程");
	                object.setCreateDate(new Date(System.currentTimeMillis()));
	                object.setUpdateDate(new Date(System.currentTimeMillis()));

	            } else {
	                SubprocessProject subprocessProject = subprocessProjectManager
	                        .getObjectById(object.getDjId());
	                subprocessProjectManager.copyObjectNotNullProperty(subprocessProject,
	                        object);
	                object = subprocessProject;
	                optBaseInfoManager.copyObjectNotNullProperty(baseInfo,
	                        optBaseInfo);
	                optBaseInfo = baseInfo;
	            }
	            object.setCreateDate(new Date(System.currentTimeMillis()));// 设置申请登记时间（当前系统时间）
	            object.setUpdateDate(new Date(System.currentTimeMillis()));// 设置申请登记时间（当前系统时间）
	            FUserDetail loginInfo = (FUserDetail) getLoginUser();
	            object.setCreateUserCode(loginInfo.getUsercode());
	            if (StringUtils.isBlank(optBaseInfo.getCaseNo())) {
	                // 保存案号：根据权力类型从格式文书案号管理处取出对应的文书格式。
	                OptWritdef owf = optWritdefManager.getObjectByTempType("1");// 1:许可类
	                String caseNo = "";
	                if (owf != null) {
	                    caseNo = this.getWritCodeByWritcodemodel(owf.getWritcode());
	                }
	                optBaseInfo.setCaseNo(caseNo);
	            }
	            optBaseInfoManager.saveObject(optBaseInfo);
	            subprocessProjectManager.saveObject(object);
	           

	        } catch (Exception e) {
	            log.error(e.getMessage());
	            e.printStackTrace();
	        }
	        return "refreshTasks";//
	    }
	 
	 
	 public String saveAndSubmitPermit() {

	        OptBaseInfo optBaseInfo = object.getOptBaseInfo();
	        object.setOptBaseInfo(optBaseInfo);
	        if (optBaseInfo == null || optBaseInfo.getFlowInstId() == null
	                || "".equals(optBaseInfo.getFlowInstId().toString())) {
	            FUserDetail fuser = ((FUserDetail) getLoginUser());

	            flowCode = "000500";

	            FlowInstance flowInst = flowEngine.createInstance(flowCode,
	                    "",
	                    "", fuser.getUsercode(),
	                    fuser.getPrimaryUnit());
	           
	            long flowInstId = flowInst.getFlowInstId();
	            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
	            // object.setFlowInstId(flowInstId);
	            this.setFlowInstId(flowInstId);
	            curNodeInstId = nodeInstId;

	            //设置父流程节点信息
	           WfFlowInstance inst = (WfFlowInstance) flowEngine.getFlowInstById(flowInstId);
	           inst.setPreInstId(object.getPreinstid());
	           inst.setPreNodeInstId(object.getPrenodeinstid());
	            object.getOptBaseInfo().setFlowInstId(flowInstId);
	            object.getOptBaseInfo().setBizstate("T");
	        }

	        savePermitReg();
	        saveIdeaInfo();
	        return "refreshTasks";
	    }
	 @SuppressWarnings("unchecked")
    public String isOver(){
	     String supDjId = object.getSupDjId();
	     Map<Object, Object> paramMap = request.getParameterMap();
         resetPageParam(paramMap);
         Map<String, Object> filterMap = convertSearchColumn(paramMap);
         Map<String, String> map = new HashMap<String, String>();
         Limit limit = ExtremeTableUtils.getLimit(request);
         PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
         filterMap.put("supDjId", supDjId);
         subprocessProjectList = subprocessProjectManager.listObjects(filterMap, pageDesc);
         if(subprocessProjectList.size() > 0)
             map.put("isOk", "1");
         if(subprocessProjectList.size() == 0)
             map.put("isOk", "0");
         if(subprocessProjectList.size() > 0){
             List<String> notOverList = subprocessProjectManager.getOptBaseNotOverList(supDjId);
             if(notOverList.size() > 0){
                 map.put("isOk", "2");
             }
         }
        result = JSONObject.fromObject(map);    
	        
	        
	        
	        return "isOver";
	   }
	 public String view(){
	     if (object == null) {
             return LIST;
         }
	     String djId = object.getDjId();
	     object = subprocessProjectManager.getObjectById(djId);
	     IncomeDocTask baseInfo = ioDocTasksManager.getIncomeDocTaskByDjId(object.getSupDjId());
	     object.setIncomeDocTask(baseInfo);
	     return "view";
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

	        OptProcInfo procInfo = new OptProcInfo();
	        procInfo.setNodeInstId(curNodeInstId);
	        procInfo.setDjId(object.getDjId());
	        procInfo.setNodename("申请");
	        procInfo.setTransdate(new Date(System.currentTimeMillis()));
	        procInfo.setNodeinststate("N");
	        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
	        procInfo.setUsercode(loginInfo.getUsercode());
	        OptProcInfo procInfo1 = new OptProcInfo();
	        procInfo1.copyNotNullProperty(procInfo);
	        System.out.println(procInfo.getNodeInstId());
	        optProcInfoManager.saveObject(procInfo1);
	        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

	    }
	    /**
	     * 根据文书模版格式组装返回文书案号
	     * 
	     * @param writcodemodel
	     * @return
	     */
	    private String getWritCodeByWritcodemodel(String writcodemodel) {
	        String writCode = "";
	        if (StringUtils.isNotBlank(writcodemodel)) {
	            StringBuffer sb = new StringBuffer();

	            writCode = writcodemodel;

	            String codemodelLike = "";// 用于查询
	            if (writCode.indexOf("$year$") != -1) {
	                int first = writCode.indexOf("$year$");
	                sb.append(writCode.substring(0, first));
	                sb.append(DatetimeOpt.convertDateToString(
	                        new Date(System.currentTimeMillis()), "").substring(0,
	                        4));
	                sb.append(writCode.substring(first + 6, writCode.length()));
	                writCode = sb.toString();
	                sb.delete(0, sb.length());
	            }
	            if (writCode.indexOf("$Y2$") != -1) {
	                int first = writCode.indexOf("$Y2$");
	                sb.append(writCode.substring(0, first));
	                sb.append(DatetimeOpt
	                        .convertDateToString(
	                                new Date(System.currentTimeMillis()), "")
	                        .substring(0, 4).substring(2));
	                sb.append(writCode.substring(first + 4, writCode.length()));
	                writCode = sb.toString();
	                sb.delete(0, sb.length());
	            }

	            if (writCode.indexOf("$N") != -1) {
	                int firstBegin = writCode.indexOf("$N");
	                int firstEnd = firstBegin + 2;
	                int secondBegin = writCode.indexOf("$", firstEnd);
	                int nunber = Integer.parseInt(writCode.substring(firstEnd,
	                        secondBegin));
	                sb.append(writCode.substring(0, firstBegin));
	                String zero = "";
	                String model = "";
	                for (int i = 0; i < nunber; i++) {
	                    if (StringUtils.isBlank(model)) {
	                        model = "_";
	                    } else {
	                        model = model + "_";
	                    }
	                    if (StringUtils.isBlank(zero)) {
	                        zero = "0";
	                    } else {
	                        zero = zero + "0";
	                    }
	                }
	                codemodelLike = sb.toString();
	                // String codeModel = sb.toString()+ model+
	                // writCode.substring(secondBegin + 1, writCode.length());
	                int index = optBaseInfoManager.getNumOfsameModel(
	                        codemodelLike,
	                        DatetimeOpt.convertDateToString(
	                                new Date(System.currentTimeMillis()), "")
	                                .substring(0, 4)) + 1;
	                sb.append(this.endReplace(zero, String.valueOf(index))).append(
	                        writCode.substring(secondBegin + 1, writCode.length()));
	                writCode = sb.toString();
	            }

	        }
	        return writCode;

	    }
	    private String endReplace(String str, String replace) {
	        return replace == null || str == null ? str : str.substring(0,
	                str.length() - replace.length())
	                + replace;
	    }
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
