package com.centit.oa.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.OaMeetroomApply;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.po.VoaMeetMinute;
import com.centit.oa.service.OaMeetMinutesManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaMeetroomApplyManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeInstance;

public class OaMeetroomApplyAction extends OACommonBizAction<OaMeetroomApply> {
    private static final Log log = LogFactory
            .getLog(OaMeetroomApplyAction.class);
    private static final long serialVersionUID = 1L;
    private OaMeetroomApplyManager oaMeetroomApplyMag;

    public void setOaMeetroomApplyManager(OaMeetroomApplyManager basemgr) {
        oaMeetroomApplyMag = basemgr;
        this.setBaseEntityManager(oaMeetroomApplyMag);
    }


    private OptJspInfo jspInfo;
    private List<OptNewlyIdea> optNewlyIdeaList;

    private OptNewlyIdeaManager optNewlyIdeaManager;

    private OaMeetinfoManager oaMeetinfoManager;
    private OaMeetMinutesManager oaMeetMinutesManager;
    private OaSuperviseManager oaSuperviseManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    

    private String curUrl;

    private FlowDefine flowDefine;

    private String flowCode;

    private List<FUnitinfo> unitlist;// 法制办部门

    private List<OaMeetinfo> oaMeetinfolist;// 法制办部门
    private String show_type;// 区别查看页面是否显示新增按钮
    private String viewtype; // 区别查看页面是否有返回按钮

    private Object json;
    private String userValue;// 获取参会人员usercode
    private List<VoaMeetMinute> meetlist;

    private  List<OaSupervise> oasuplist;
    public List<OaSupervise> getOasuplist() {
        return oasuplist;
    }
    public void setOasuplist(List<OaSupervise> oasuplist) {
        this.oasuplist = oasuplist;
    }

  
  

    /**
     * 列表数据
     */
    public String list() {
        try {

            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // 部门列表
            unitlist = sysUnitManager.getAllSubUnits("1");
    
            if ("F".equals(this.show_type)) {// 登记的查询list
                filterMap.put("biztype", "F");
            }
            // 会议室列表:启用
            Map<String, Object> filterMapHys = new HashMap<String, Object>();
            filterMapHys.put("isuse", "T");

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetroomApplyMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            return LIST;

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 列表数据
     */
    public String selectlist() {
        try {

            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            filterMap.put("biztype", "C");

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetroomApplyMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            return "listdjid";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 列表数据
     */
    public String selectmeet() {
        try {

            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("biztype", "C");
            filterMap.put("state", "3");
            filterMap.put("invalid", DatetimeOpt.convertDatetimeToString(new Date()));//排除失效过期记录
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetroomApplyMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            return "meetlist";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
     
    @Override
    public String edit() {
        super.edit();
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(oaMeetroomApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HYS打头+时间戳
        String no = "HYS-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        object.setApplyNo(no);
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("1");
 
        // 会议室列表:启用
        Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);

        return EDIT;
    }

    /**
     * 日历展示部分发起申请
     * @return
     */
  
    public String addNew() {
        object.setPlanBegTime(object.getPlanBegTime());
        object.setPlanEndTime(object.getPlanEndTime());
        object.setMeetingNo(object.getMeetingNo());
        
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(oaMeetroomApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HYS打头+时间戳
        String no = "HYS-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        object.setApplyNo(no);
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("1");

        // 会议室列表:启用
        Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);

        return EDIT;
    }

    /**
     * 会议室安排
     * 
     * @return
     */
    public String doMeeting() {
        this.edit();
        super.generalOpt();
        return "doMeetrooming";
    }

    /**
     * 会议室回退登记
     * 
     * @return
     */
    public String doReEdit() {
        this.edit();
        super.generalOpt();
        return "doReEditroom";
    }

    /**
     * 会议室使用反馈
     * 
     * @return
     */
    public String doBack() {
        this.edit();
        super.generalOpt();
        return "doBack";
    }

    /**
     * 保存会议室-反馈特殊节点信息
     * 
     * @return
     */
    public String savedoBack() {
        OaMeetroomApply oaMeetApply = oaMeetroomApplyMag
                .getObjectById(object.getDjId());
        if (oaMeetApply != null) {
            // 提交流程保存办结信息
            object.setSolvenote(object.getTodoremark());
            object.setSolvetime(new Date());
            object.setBizstate("C");
            object.setBiztype("C");
            // 提交反馈时跟新isuse的状态置为3
            object.setState("3");
            oaMeetroomApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
        }
        oaMeetroomApplyMag.saveObject(object);
        return "refreshTasks";
    }

    /**
     * 提交会议室-使用反馈特殊节点信息
     * 
     * @return
     */
    public String submitdoBack() {
   
        this.savedoBack();
        // 保存过程日志信息
        saveIdeaInfo("会议室使用反馈");
        submitNode();
     
        return "refreshTasks";
    }

    /**
     * 保存会议室-安排特殊节点信息
     * 
     * @return
     */
    public String savedoMeeting() {
        OaMeetroomApply oaMeetApply = oaMeetroomApplyMag
                .getObjectById(object.getDjId());
        if (oaMeetApply != null) {
         // 提交流程保存办结信息
            object.setSolvenote(object.getTodoremark());
            object.setBizstate("C");
            object.setBiztype("C");
            // 提交反馈时跟新isuse的状态置为3
            object.setState("3");
            oaMeetroomApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
        }

        oaMeetroomApplyMag.saveObject(object);

        return "refreshTasks";
    }

    /**
     * 提交会议室-安排特殊节点信息
     * 
     * @return
     */
    public String submitdoMeeting() {
            
        this.savedoMeeting();
        // 保存过程日志信息
        saveIdeaInfo("会议室安排");
        submitNode();

        return "refreshTasks";
    }
    

    /**
     * 保存会议室登记特殊节点信息
     * 
     * @return
     */
    public String saveReEdit() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        OaMeetroomApply oaMeetApply = oaMeetroomApplyMag
                .getObjectById(object.getDjId());// 业务数据

        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (oaMeetApply != null) {// 跟新业务数据
            object.setCreater(fuser.getUsercode());
            object.setDepno(fuser.getPrimaryUnit());
            oaMeetroomApplyMag.copyObjectNotNullProperty(oaMeetApply, object);
            object = oaMeetApply;
        }
        if (optBaseInfo != null) {// 更新流程业务数据
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setTransaffairname("关于【会议室】的申请");
            optBaseInfo.setTransAffairNo(object.getDjId());
            optBaseInfo.setCreatedate(object.getCreatetime());
            optBaseInfo.setCreateuser(object.getCreater());
            optBaseInfoManager.saveObject(optBaseInfo);

        }
        oaMeetroomApplyMag.saveObject(object);

        return "refreshTasks";
    }

    /**
     * 提交会议室登记申请特殊节点信息
     * 
     * @return
     */
    public String submitReEdit() {
      

        this.saveReEdit();
        // 保存过程日志信息
        saveIdeaInfo("回退登记申请");
        submitNode();
       
        return "refreshTasks";
    }

    /**
     * 通用业务框架属性会议室信息查看
     */

    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        // 查看办件业务数据信息
        frameList.add(getBizDataViewFrame(object.getDjId()));
        // 用于展示查看详细信息Lab标签内容
        frameList.add(this.getAllViewFrame(object.getDjId()));

        jspInfo = new OptJspInfo();
        jspInfo.setTitle("会议室办理查看");
        jspInfo.setFrameList(frameList);

        return "generalOptView";
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getAllViewFrame(String djid) {
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("AllInfoFrame");
        stuffsFrameInfo.setFrameSrc("/oa/oaMeetroomApply!getAllCaseView.do?djId="
                + djid + "&nodeInstId=" + curNodeInstId);
        stuffsFrameInfo.setFrameHeight("300px");
        return stuffsFrameInfo;
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
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
        curUrl = "/powerruntime/generalOperator!listIdeaLogs.do?djId="
                + object.getDjId();
        if (optNewlyIdeaList != null && optNewlyIdeaList.size() > 0
                && nodeId != 0) {
            for (OptNewlyIdea bean : optNewlyIdeaList) {
                if (bean.getNodeid() == nodeId) {
                    curUrl = bean.getUrl();
                    break;
                }
            }
        }
        object = oaMeetroomApplyMag.getObject(object);

        if (null == object.getFlowinstid()) {
            object.setFlowinstid((long) 9999999);
        }
        
    
        //督办催办信息
        filterMap.clear();
        filterMap.put("supDjid", object.getDjId());
        oasuplist=oaSuperviseManager.listObjects(filterMap);
        request.setAttribute("oasuplist", oasuplist);
        
        request.setAttribute("flowInstId", object.getFlowinstid());
        request.setAttribute("nodeId", nodeId);
        return "AllmilitarycaseView";
    }

    /**
     * 会议室业务数据查看
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameSrc("/oa/oaMeetroomApply!view.do?viewtype=T&djId="
                + djid);
        viewFrameInfo.setFrameHeight("700px");
        return viewFrameInfo;
    }

    /**
     * 保存业务数据
     * 
     * @return
     */

    public String savePermitReg() {
        try {
            // 保存
            FUserDetail fuser = ((FUserDetail) getLoginUser());

            object.setCreater(fuser.getUsercode());
            object.setDepno(fuser.getPrimaryUnit());
            FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
            object.setOptid(flowDescribe.getOptId());
            object.setFlowcode(flowCode);
            object.setBiztype("F");//
            object.setBizstate("F");
            // 申请isuse状态为1
            object.setState("1");
          
            oaMeetroomApplyMag.saveObject(object);

            object = oaMeetroomApplyMag.getObjectById(object.getDjId());

            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setTransaffairname("关于【会议室】的申请");
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                optBaseInfo.setOrgname(fuser.getUnitName());
          
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(object.getCreatetime());
                optBaseInfo.setCreateuser(object.getCreater());
                optBaseInfoManager.saveObject(optBaseInfo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return this.list();
    }

    /**
     * 保持并提交流程
     * 
     * @return
     */
    public String saveAndSubmit() {
        // 保存
        FUserDetail fuser = ((FUserDetail) getLoginUser());

        object.setCreater(fuser.getUsercode());
        object.setDepno(fuser.getPrimaryUnit());
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        object.setOptid(flowDescribe.getOptId());
        object.setBiztype("F");//
        object.setBizstate("F");
        object.setSolvestatus("W");// 申请状态w：办理中 T：同意F:不同意
        object.setFlowcode(flowCode);
        // 申请isuse状态为1
        object.setState("1");
        oaMeetroomApplyMag.saveObject(object);

        object = oaMeetroomApplyMag.getObjectById(object.getDjId());

       
        if (object != null) {
            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setTransaffairname("关于【会议室】的申请");
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                optBaseInfo.setOrgname(fuser.getUnitName());
          
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(object.getCreatetime());
                optBaseInfo.setCreateuser(object.getCreater());
                optBaseInfoManager.saveObject(optBaseInfo);
            }
            
            
            
            FlowInstance flowInst = flowEngine.createInstance(flowCode, "关于【会议室】的申请", object.getDjId(),
                    fuser.getUsercode(), fuser.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;
            curFlowInstId = flowInstId;

            object.setFlowinstid(flowInstId);
            object.setBiztype("W");// 等待审核
            object.setBizstate("W");
           
            oaMeetroomApplyMag.saveObject(object);

            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);

            // 将登记部门纳入权限引擎
            // flowEngine.assignFlowOrganize(flowInstId, "FZBM",
            // fuser.getPrimaryUnit());
        }
        submitNode();
        return this.list();
    }

    /**
     * 保存回退登记骤过程日志信息
     */
    private String nodeCode;

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
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）
        optIdeaInfo.setTransidea(nodename);

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(curNodeInstId);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename(nodename);
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea(nodename);
        procInfo.setNodeCode(nodeCode);
        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }

    /**
     * 查看
     * 
     */
    private OaMeetroomApply oaMeetApply;

    //private String contextPath;

    public OaMeetroomApply getOaMeetApply() {
        return oaMeetApply;
    }
    public void setOaMeetApply(OaMeetroomApply oaMeetApply) {
        this.oaMeetApply = oaMeetApply;
    }
    public String view() {
        super.view();
        oaMeetApply = oaMeetroomApplyMag.getObjectById(object.getDjId());

        return "view";
    }

   
    public String delete() {
        super.delete();

        return this.list();
    }

    /**
     * 查看某一会议室的历史安排记录
     * 
     * @return
     * @throws
     */

    public String meetplan() {

        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("NP_", true);

        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList = oaMeetroomApplyMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();

        return "meetplan";
    }

    /**
     * 时间段是否空闲
     * 
     * @return
     * @throws IOException
     */
    public String isTFree() throws IOException {
        json = true;

        if (!isExist()) {
            json = false;
            return "json";
        }
        return "json";

    }

    /**
     * 验证申请时间是否空闲 （未用：）validate时使用
     * 
     * @throws IOException
     */
    public void isFree() throws IOException {

        ServletActionContext.getResponse().getWriter().print(isExist());

    }

    /**
     * 判断申请时间或者调配时间段是否空闲 优先级：同一条申请，若已被调配则以调配时间为占用时间，否则按申请时间
     * 如果djId已存在，在编辑验证时需要排除当前记录
     * 
     * @return
     */
    private boolean isExist() {
        List<OaMeetroomApply> applylist = new ArrayList<OaMeetroomApply>();
        // 申请时的验证
        if (null != object.getPlanBegTime() && null != object.getPlanEndTime()) {
            applylist = oaMeetroomApplyMag.getApplylist(object.getDjId(),
                    object.getMeetingNo(), object.getPlanBegTime(),
                    object.getPlanEndTime(), "1,2");
        }
        // 调配时的验证(传入时间字段不一样)
        if (null != object.getDoBegTime() && null != object.getDoEndTime()) {
            applylist = oaMeetroomApplyMag.getApplylist(object.getDjId(),
                    object.getMeetingNo(), object.getDoBegTime(),
                    object.getDoEndTime(), "1,2");
        }

        if (applylist != null && applylist.size() > 0) {
            return false;

        } else {
            return true;

        }

    }

    /**
     * 列表显示在日历上 默认查询时间
     */
    public String calendarView() {
        // 在会议室里instance只有一个值，为方便查询，从calendar中copy代码出来并简化
        // String instance = request.getParameter("instance");
        // List<String> instances = Arrays.asList(instance.split(","));
        Map<String, Boolean> inst = new HashMap<String, Boolean>();

        Map<String, String> values = new LinkedHashMap<String, String>();

        values.put("oaMeetroomApplyMag", "会议室申请");

        inst.put("oaMeetroomApplyMag", true);

        // 会议室列表:启用(oaMeetinfolist未用到)
        Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);

        request.setAttribute("values", values);
        request.setAttribute("inst", inst);

        return "calendarView";
    }

    @SuppressWarnings("unchecked")
    public void ajax() throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }

        params.put("usercode",
                ((FUserDetail) super.getLoginUser()).getUsercode());
        params.put("contextPath", request.getContextPath());

        // 获得开始结束时间
        Date start = new Date(
                Long.parseLong((String) params.get("start")) * 1000);
        Date end = new Date(
                Long.parseLong((String) params.get("end")) * 1000 - 1000);
        String meetingNo = (String) params.get("meetingNo");

        List<OaMeetroomApply> applylist = oaMeetroomApplyMag.getApplylist(null,
                meetingNo, start, end, "1,2,3");

        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(applylist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }

    private void getJsonResult(List<OaMeetroomApply> meetApplyList,
            List<Map<String, Object>> jsonResult, Map<String, Object> params) {

        // 添加实例，操作相关实现类接口
        List<Map<String, Object>> result = putDataToMap(meetApplyList, params);

        jsonResult.addAll(result);
    }

    /**
     * 将数据转换为日历控件所需要的Json数据格式
     * 
     * @param tasklist
     * @return
     */
    @SuppressWarnings("static-method")
    private List<Map<String, Object>> putDataToMap(
            List<OaMeetroomApply> meetApplyList, Map<String, Object> params) {
        String contextPath = (String) params.get("contextPath");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (OaMeetroomApply data : meetApplyList) {
            Map<String, Object> task = new HashMap<String, Object>();
            // 用户owner
            String owner = CodeRepositoryUtil.getValue("usercode",
                    data.getCreater());

            task.put("id", data.getDjId());

            String title = '[' + owner + "] " + data.getDjId()
                    + data.getMeetingNo();
            if (30 < title.length()) {
                title = title.substring(0, 30) + "...";
            }

            task.put("title", title);

            // 显示标记颜色 申请未确认
            if (StringUtils.isNotBlank(data.getState())
                    && "1".equals(data.getState())) {
                task.put("tasktag", "yellow");

                task.put("start", DatetimeOpt.convertDateToString(
                        data.getPlanBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getPlanEndTime(), "yyyy-MM-dd HH:mm:ss"));

                // 确认后按调配时间显示
            }
            if (StringUtils.isNotBlank(data.getState())
                    && "2".equals(data.getState())) {
                task.put("tasktag", "green");
                task.put("start", DatetimeOpt.convertDateToString(
                        data.getDoBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getDoEndTime(), "yyyy-MM-dd HH:mm:ss"));

            }// 使用记录
            if (StringUtils.isNotBlank(data.getState())
                    && "3".equals(data.getState())) {
                task.put("tasktag", "purple");
                task.put("start", DatetimeOpt.convertDateToString(
                        data.getBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

            }
            task.put("textColor","black");

            // 可编辑
     
            task.put("editable", true);

            task.put("allDay", false);

            String url = "/oa/oaMeetroomApply!generalOptView.do?djId="
                    + data.getDjId() + "&nodeInstId=0";// dijd

            if (StringUtils.isNotBlank(url)) {
                StringBuilder sb = new StringBuilder();
                // List<String> ignore = Arrays.asList("contextPath",
                // "usercode");
                // for (Entry<String, Object> entry : params.entrySet()) {
                // if (ignore.contains(entry.getKey())) {
                // continue;
                // }
                // sb.append('&' + entry.getKey() + '=' + entry.getValue());
                // }
                task.put("url", contextPath + url + sb);
            }
            result.add(task);
        }

        return result;
    }
    
  

    /*********************************** getter()、setter() ****************************************/

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }

    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
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

    public FlowDefine getFlowDefine() {
        return flowDefine;
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }

    public static Log getLog() {
        return log;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
        this.optNewlyIdeaManager = optNewlyIdeaManager;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public OaMeetinfoManager getOaMeetinfoManager() {
        return oaMeetinfoManager;
    }

    public void setOaMeetinfoManager(OaMeetinfoManager oaMeetinfoManager) {
        this.oaMeetinfoManager = oaMeetinfoManager;
    }

    public List<OaMeetinfo> getOaMeetinfolist() {
        return oaMeetinfolist;
    }

    public void setOaMeetinfolist(List<OaMeetinfo> oaMeetinfolist) {
        this.oaMeetinfolist = oaMeetinfolist;
    }

    public OaMeetMinutesManager getOaMeetMinutesManager() {
        return oaMeetMinutesManager;
    }

    public void setOaMeetMinutesManager(OaMeetMinutesManager oaMeetMinutesManager) {
        this.oaMeetMinutesManager = oaMeetMinutesManager;
    }
    public List<VoaMeetMinute> getMeetlist() {
        return meetlist;
    }

    public void setMeetlist(List<VoaMeetMinute> meetlist) {
        this.meetlist = meetlist;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }
    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }
    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }
    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
 
    
}
