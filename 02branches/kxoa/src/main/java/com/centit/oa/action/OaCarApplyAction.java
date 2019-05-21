package com.centit.oa.action;

import java.io.File;
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
import org.springframework.core.task.TaskExecutor;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaCarinfo;
import com.centit.oa.po.OaDriverInfo;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaCarinfoManager;
import com.centit.oa.service.OaDriverInfoManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
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
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;

public class OaCarApplyAction extends OACommonBizAction<OaCarApply> {
    private static final Log log = LogFactory.getLog(OaCarApplyAction.class);



    private static final long serialVersionUID = 1L;
    private OaCarApplyManager oaCarApplyMag;

    public void setOaCarApplyManager(OaCarApplyManager basemgr) {
        oaCarApplyMag = basemgr;
        this.setBaseEntityManager(oaCarApplyMag);
    }

    private OptJspInfo jspInfo;
    private List<OptNewlyIdea> optNewlyIdeaList;

    private OptNewlyIdeaManager optNewlyIdeaManager;

    private OaMeetinfoManager oaMeetinfoManager;
    
    private OaCarinfoManager oaCarinfoManager;
    private OaDriverInfoManager oaDriverInfoManager;
    
    
    private OaSuperviseManager oaSuperviseManager;
    private OaRemindInformationManager oaRemindInformationManager;
    private AddressBookRelectionManager addressBookRelectionManager;
    private String curUrl;
    private VOptBaseListManager vOptBaseListManager;

    private FlowDefine flowDefine;

    private String flowCode;

    private List<FUnitinfo> unitlist;// 法制办部门

    private List<OaMeetinfo> oaMeetinfolist;// 法制办部门
    private String show_type;// 区别查看页面是否显示新增按钮
    private String viewtype; // 区别查看页面是否有返回按钮

    private Object json;
    
    private List<OaCarinfo> oaCarinfolist;// 法制办部门

    private  List<OaSupervise> oasuplist;
    
    private WfActionTaskDao actionTaskDao;
    
    private InnermsgManager innermsgManager;//邮件通知
    
    
    private OptPdfInfoManager optPdfInfoManager;
    
    private TaskExecutor taskExecutor;
    
    public List<OaSupervise> getOasuplist() {
        return oasuplist;
    }
    public void setOasuplist(List<OaSupervise> oasuplist) {
        this.oasuplist = oasuplist;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }
    public void setOaRemindInformationManager(
            OaRemindInformationManager oaRemindInformationManager) {
        this.oaRemindInformationManager = oaRemindInformationManager;
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
            if ("mipSelf".equals(this.show_type)) {
                filterMap.put("mipSelf",user.getUsercode());//自己所有数据+别人已安排数据
            }
            
            if ("self".equals(this.show_type)) {
                filterMap.put("self",user.getUsercode());//自己所有数据+别人提交流程数据
            }
           
            if ("F".equals(this.show_type)) {// 登记的查询list只获取自己的申请信息
                filterMap.put("creater",user.getUsercode());//自己数据
            }
           
            if("T".equals(this.show_type)){//申请查看页面，查看已经提交的历史申请情况（全部人员的）
                filterMap.put("NP_bizstate",true);  //所有提交流程数据         
            } 
            
            //默认查询当前月份第一天开始的所有数据
            if(StringUtils.isBlank((String)filterMap.get("docpTimeBegin"))&&StringUtils.isBlank((String)filterMap.get("docpTimeEnd"))){
                filterMap.put("docpTimeBegin",DateUtil.getCurrentMonthOfDay() );
//                filterMap.put("EndTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            
            
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaCarApplyMag.listObjects(filterMap, pageDesc);           
            totalRows = pageDesc.getTotalRows();
            
            setbackSearchColumn(filterMap);
            if ("C".equals(filterMap.get("bizstate"))) {
                return "listdjid";
            } else {
                //return LIST;
                return "listV2";
            }

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
            object.setDjId(oaCarApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HYS打头+时间戳
        String no = "CAR-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        object.setApplyNo(no);
        
        // 新添加申请时，默认将系统登录人作为申请人
        FUserDetail user = (FUserDetail) getLoginUser();
        if(StringUtils.isBlank(object.getCreater())){
            object.setCreater(user.getUsercode());
        }
        
        // 新添加申请时，默认将当前时间作为申请时间
        if(null == object.getCreatetime()){
            object.setCreatetime(new Date());
        }
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("T");

        
        //历史办理记录
        if(null!=curNodeInstId){
            object.setOptProcInfo( optProcInfoManager.getObjectByDjidAndNodeInstId(object.getDjId(), curNodeInstId,user.getUsercode() ));
        }
        
        // 会议室列表:启用
        // Map<String, Object> filterMapHys = new HashMap<String, Object>();
        // filterMapHys.put("isuse", "T");
        // oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);

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
            object.setDjId(oaCarApplyMag.getNextKey());
        }
        // 生成申请编号：编号规则以HYS打头+时间戳
        String no = "CAR-"
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
                        .currentTimeMillis()));
        object.setApplyNo(no);
        // 部门列表
        unitlist = sysUnitManager.getAllSubUnits("T");

        
        // 新添加申请时，默认将系统登录人作为申请人
        FUserDetail user = (FUserDetail) getLoginUser();
        if(StringUtils.isBlank(object.getCreater())){
            object.setCreater(user.getUsercode());
        }
        
        return EDIT;
    }
    
    
    /**
     * 车辆安排
     * 
     * @return
     */
    public String doMeeting() {
        this.edit();
        super.generalOpt();
        return "carplan";
    }

    /**
     * 车辆回退登记
     * 
     * @return
     */
    public String doReEdit() {
        this.edit();
        super.generalOpt();
        return "carReEdit";
    }
    
    private UserbizoptLogManager userbizoptLogManager;
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    /**
     * 车辆使用反馈
     * 
     * @return
     */
    public String doBack() {
        
      //保存查看日志
        FUserDetail user = (FUserDetail) getLoginUser();
        OaCarApply o=oaCarApplyMag.getObjectById(object.getDjId());
        UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(o.getTransaffairname(),o.getDjId()),curNodeInstId);
        userbizoptLogManager.saveUserbizoptLog(u, user);
        
        this.edit();
        super.generalOpt();
        return "carBack";
    }

    /**
     * 保存车辆-反馈特殊节点信息
     * 
     * @return
     */
    public String savedoBack() {
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(object.getDjId());
        if (oaCarApply != null) {
            // 提交流程保存办结信息
            object.setSolvenote(object.getTodoremark());
            object.setBizstate("C");
            object.setBiztype("C");
            // 提交反馈时跟新isuse的状态置为3
            object.setState("3");
            object.setIsUse("T");
            object.setSolvetime(DatetimeOpt.currentUtilDate());
            oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
            object = oaCarApply;
        }
        oaCarApplyMag.saveObject(object);
        return "refreshTasks";
    }

    /**
     * 提交车辆-使用反馈特殊节点信息
     * 
     * @return
     */
    public String submitdoBack() {
         
        this.savedoBack();
        // 保存过程日志信息
        saveIdeaInfo("车辆使用反馈");
        submitNode();
        createPDF();
        return "refreshTasks";
    }

    /**
     * 保存车辆-安排特殊节点信息
     * 
     * @return
     */
    public String savedoMeeting() {        
      
            if("N".equals(object.getRange_type())){//如果是工会内部有车情况
                this.saveInnerCar();
            }else{//外租车情况
                this.saveOutCar();           
            }
       

        return "refreshTasks";
    }
    /**
     * 保存租车信息
     */
    private void saveOutCar(){
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(object.getDjId());
        if(null!=object.getOptProcInfo()&&"T".equals(object.getOptProcInfo().getIdeacode())){
        
            
        //1、保存或者更新外租车信息
          if(StringUtils.isNotBlank(object.getCardjid())){//车辆主键不为空时候，表示之前已经存在，所以是更新
              OaCarinfo oaCarinfo=oaCarinfoManager.getObjectById(object.getCardjid());              
              oaCarinfo.setCarno(object.getCarno());
              
              //存储外租车司机信息，状态和原系统的做了区别。state=W 表示外租车司机
              OaDriverInfo oaDriverInfo =new OaDriverInfo();
              oaDriverInfo.setNo(oaDriverInfoManager.getNextKey());
              FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
              oaDriverInfo.setCreater(user.getUsercode());
              oaDriverInfo.setCreatertime(DatetimeOpt.currentUtilDate());
              oaDriverInfo.setState("W");//备注成外租车
              oaDriverInfo.setTelephone(object.getDrTelephone());
              oaDriverInfo.setName(object.getDriver());
              oaDriverInfoManager.saveObject(oaDriverInfo);
              
              oaCarinfo.setDriver(oaDriverInfo.getNo());
              oaCarinfo.setBrand(object.getBrand());
              oaCarinfo.setModelType(object.getModelType()); 
              oaCarinfo.setRangeType("W");//备注成外租车
              oaCarinfo.setIsuse("W");//备注成外租车
              oaCarinfoManager.saveObject(oaCarinfo);
              
          }else{//车辆主键为空，时候需要和现有车辆比对车牌号是否存在，如果已经存在相同的车牌，则更新，否则新增；
              Map<String, Object> filterMap=new HashMap<String, Object>();
              filterMap.put("carno", object.getCarno());
              List<OaCarinfo> ois=oaCarinfoManager.listObjects(filterMap);
              OaCarinfo oaCarinfo;
              if(ois!=null && ois.size()>0){
                  oaCarinfo=ois.get(0);
                  oaCarinfo.setCarno(object.getCarno());
                  oaCarinfo.setBrand(object.getBrand());
                  oaCarinfo.setModelType(object.getModelType());  
              }else{
                 oaCarinfo = new OaCarinfo();
                 oaCarinfo.setDjid(oaCarApplyMag.getNextKey());
                 oaCarinfo.setCarno(object.getCarno());
                 oaCarinfo.setBrand(object.getBrand());
                 oaCarinfo.setModelType(object.getModelType());  
              }
              
            //存储外租车司机信息，状态和原系统的做了区别。state=W 表示外租车司机
              OaDriverInfo oaDriverInfo =new OaDriverInfo();
              oaDriverInfo.setNo(oaDriverInfoManager.getNextKey());
              FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
              oaDriverInfo.setCreater(user.getUsercode());
              oaDriverInfo.setCreatertime(DatetimeOpt.currentUtilDate());
              oaDriverInfo.setState("W");//备注成外租车
              oaDriverInfo.setTelephone(object.getDrTelephone());
              oaDriverInfo.setName(object.getDriver());
              oaDriverInfoManager.saveObject(oaDriverInfo);
              
              oaCarinfo.setRangeType("W");//备注成外租车
              oaCarinfo.setIsuse("W");//备注成外租车
              oaCarinfo.setDriver(oaDriverInfo.getNo());
              oaCarinfoManager.saveObject(oaCarinfo);
          }
                 
            
        //2、保存车辆申请记录情况；        
        if (oaCarApply != null) {
            oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
            object = oaCarApply;
            FUserDetail user = (FUserDetail) getLoginUser();
            object.setState("2");//已调配
            object.setDoCreater(user.getUsercode());
            object.setDoDepno(user.getPrimaryUnit());
            object.setDoTime(DatetimeOpt.currentUtilDate());
            object.setCpBegtime(object.getDoBegTime());
            object.setCpEndtime(object.getDoEndTime());
            
        } 
        }else if("F".equals(object.getOptProcInfo().getIdeacode())){
            if (oaCarApply != null) {
                oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
                object = oaCarApply;
                FUserDetail user = (FUserDetail) getLoginUser();
                object.setState("4");//不同意
                object.setDoCreater(user.getUsercode());
                object.setDoDepno(user.getPrimaryUnit());
                object.setDoTime(DatetimeOpt.currentUtilDate());
                object.setCpBegtime(object.getDoBegTime());
                object.setCpEndtime(object.getDoEndTime());
            }
        }
        oaCarApplyMag.saveObject(object);
    }
    
    /**
     * 保存内部车辆信息
     */
    private void saveInnerCar(){
        
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(object.getDjId());
        
        if(null!=object.getOptProcInfo()&&"T".equals(object.getOptProcInfo().getIdeacode())){
            
            if (oaCarApply != null) {
                oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
                object = oaCarApply;
                FUserDetail user = (FUserDetail) getLoginUser();
                object.setState("2");//已调配
                object.setDoCreater(user.getUsercode());
                object.setDoDepno(user.getPrimaryUnit());
                object.setDoTime(DatetimeOpt.currentUtilDate());
                object.setCpBegtime(object.getDoBegTime());
                object.setCpEndtime(object.getDoEndTime());
                object.setRange_type("N");
            } 
            //存在冲突 且可以被抢占
            if (!isExist()){
                if(isExistCanReplace()){
                    FUserDetail fuser = ((FUserDetail) getLoginUser());
                    List<OaCarApply> applylist = oaCarApplyMag.getApplylist(object.getDjId(),
                            object.getMeetingNo(), object.getDoBegTime(),
                            object.getDoEndTime(), "2");
                    if (applylist != null && applylist.size() > 0) {
                        for(OaCarApply meet:applylist){
                            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(meet.getDjId());
                            OaRemindInformation oaRemindInformation = new OaRemindInformation();
                            AddressBookRelection o= new AddressBookRelection();
                            String no = oaRemindInformationManager.getNextkey();
                            oaRemindInformation.setNo(no);
                            oaRemindInformation.setCreater(fuser.getUsercode());
                            oaRemindInformation.setFrequency(Long.parseLong("5"));
                            oaRemindInformation.setCreatetime(DatetimeOpt.currentUtilDate());
                            oaRemindInformation.setDjid(meet.getDjId());
                            oaRemindInformation.setTitle("关于[申请编号为"+meet.getApplyNo()+"的车辆预定]已被占用，请您及时关注!");
                            oaRemindInformation.setBegtime(DatetimeOpt.currentUtilDate());
                            oaRemindInformation.setEndtime(DatetimeOpt.currentUtilDate());
                            oaRemindInformation.setThesign("1");
                            oaRemindInformation.setUsercode(meet.getCreater());
                            oaRemindInformation.setReType("CARSQ");
                            oaRemindInformationManager.saveObject(oaRemindInformation);
                            o.setAddrbookid(no);
                            o.setBizType("0");
                            o.setShareman(meet.getCreater());
                            addressBookRelectionManager.saveObject(o);
                            
                            meet.setBizstate("C");// 业务状态
                            meet.setBiztype("C");// 业务类别
                            meet.setSolvestatus("Z");
                            meet.setState("5");//state=5标记为被占用
                            oaCarApplyMag.saveObject(meet);
                            flowManager.stopInstance(optBaseInfo.getFlowInstId(),fuser.getUsercode(), "抢占车辆申请");
                            
                        }
                    }
                }
            }
        }else if("F".equals(object.getOptProcInfo().getIdeacode())){
            if (oaCarApply != null) {
                oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
                object = oaCarApply;
                FUserDetail user = (FUserDetail) getLoginUser();
                object.setState("4");//不同意
                object.setDoCreater(user.getUsercode());
                object.setDoDepno(user.getPrimaryUnit());
                object.setDoTime(DatetimeOpt.currentUtilDate());
                object.setCpBegtime(object.getDoBegTime());
                object.setCpEndtime(object.getDoEndTime());
            }
        }
        
        oaCarApplyMag.saveObject(object);
    }
    /**
     * 提交车辆-安排特殊节点信息
     * 
     * @return
     */
    public String submitdoMeeting() {
    
       
        // 保存过程日志信息
        saveIdeaInfo("车辆安排");
        
        this.savedoMeeting();
        
        submitNode();
        
        createPDF();
        return "refreshTasks";
    }

    /**
     * 保存车辆登记特殊节点信息
     * 
     * @return
     */
    public String saveReEdit() {
        FUserDetail fuser = ((FUserDetail) getLoginUser());
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(object.getDjId());// 业务数据

        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (oaCarApply != null) {// 跟新业务数据
            object.setCreater(fuser.getUsercode());
            object.setDepno(fuser.getPrimaryUnit());
            oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
            object = oaCarApply;
        }
        if (optBaseInfo != null) {// 更新流程业务数据
            optBaseInfo.setDjId(object.getDjId());
         // 添加申请名称
            this.setTransaffairname(object, optBaseInfo);
//            optBaseInfo.setTransaffairname("关于【" + object.getReqRemark()
//                    + "】的申请");
            optBaseInfo.setTransAffairNo(object.getDjId());
            optBaseInfo.setCreatedate(object.getCreatetime());
            optBaseInfo.setCreateuser(object.getCreater());
            optBaseInfoManager.saveObject(optBaseInfo);

        }
        oaCarApplyMag.saveObject(object);

        return "refreshTasks";
    }

    /**
     * 提交车辆登记申请特殊节点信息
     * 
     * @return
     */
    public String submitReEdit() {
        
        this.saveReEdit();
        // 保存过程日志信息
        saveIdeaInfo("回退登记申请");
        submitNode();
        createPDF();
        return "refreshTasks";
    }

    /**
     * 通用业务框架属性车辆信息查看
     */

    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        // 查看办件业务数据信息
        frameList.add(getBizDataViewFrame(object.getDjId()));
        
        frameList.add(super.getCommonStuffFrame(object.getDjId(),
                null, null));// 附件
        
        // 用于展示查看详细信息Lab标签内容 原先tab方式显示
//      frameList.add(this.getAllViewFrame(object.getDjId()));
      
      //frameList 页面列表显示
//      frameList=getAllInfoListFrame(frameList,object.getDjId());

        jspInfo = new OptJspInfo();
//        jspInfo.setTitle("车辆办理查看");
        jspInfo.setFrameList(frameList);
        
        // 用于查看流程图
        OaCarApply apply = oaCarApplyMag.getObject(object);
        if(null != apply){
            object.setFlowInstId(apply.getFlowInstId());
        }
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
           if("GRBGBJCK".equals(fromMenu)){
               neighbouringDjId = vOptBaseListManager.findNeighbouringDjId(filterMap, object.getDjId());
           }
       }
      
       if(neighbouringDjId != null && neighbouringDjId.size()==2){
           object.setPrevDjId(neighbouringDjId.get(0));
           object.setNextDjId(neighbouringDjId.get(1));
       }
       setbackSearchColumn(filterMap);
    }
    private List<OptHtmlFrameInfo> getAllInfoListFrame(List<OptHtmlFrameInfo> frameList,String djId) {
        
        frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
        frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
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
     * 
     * @param djid
     * @return
     */
    @SuppressWarnings("unused")
    private OptHtmlFrameInfo getAllViewFrame(String djid) {
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("AllInfoFrame");
        stuffsFrameInfo.setFrameSrc("/oa/oaCarApply!getAllCaseView.do?djId="
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
        object = oaCarApplyMag.getObject(object);

        if (null == object.getFlowInstId()) {
            object.setFlowInstId((long) 9999999);
        }
        //督办催办信息
        filterMap.clear();
        filterMap.put("supDjid", object.getDjId());
        oasuplist=oaSuperviseManager.listObjects(filterMap);
        request.setAttribute("oasuplist", oasuplist);
        request.setAttribute("flowInstId", object.getFlowInstId());
        request.setAttribute("nodeId", nodeId);
        return "AllmilitarycaseView";
    }

    /**
     * 车辆业务数据查看
     * 
     * @param djid
     * @return
     */
    private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameLegend("车辆申请信息");
        viewFrameInfo.setFrameSrc("/oa/oaCarApply!view.do?viewtype=T&djId="
                + djid);
        viewFrameInfo.setFrameHeight("700px");
        return viewFrameInfo;
    }

    /**
     * 添加申请名称——形式：关于【xxxx年xx月xx日n人使用会议室】的申请
     * @param object
     * @param optBaseInfo
     */
    public void setTransaffairname(OaCarApply object, OptBaseInfo optBaseInfo){
        
        StringBuilder s = new StringBuilder("关于【");
        if(null != object && null != optBaseInfo){
            
            Date createTime = object.getCreatetime();
            if(null != createTime){
                s.append(DatetimeOpt.getYear(createTime) + "年" + DatetimeOpt.getMonth(createTime)
                        + "月" + DatetimeOpt.getDay(createTime) + "日");
            }
            Long l = object.getMeetingPersionsNum();
            if(null != l){
                s.append(l + "人");
            }
        }
        s.append("使用会议室】的申请");
        optBaseInfo.setTransaffairname(s.toString());
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
            object.setCreatetime(DatetimeOpt.currentUtilDate());
            // 申请state=6标记为暂存
            object.setState("6");
            object.setCpBegtime(object.getPlanBegTime());
            object.setCpEndtime(object.getPlanEndTime());
            
            oaCarApplyMag.saveObject(object);

            object = oaCarApplyMag.getObjectById(object.getDjId());

            OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                    .getDjId());
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                
                // 添加申请名称
                this.setTransaffairname(object, optBaseInfo);
                /*optBaseInfo.setTransaffairname("关于【" + object.getReqRemark()
                        + "】的申请");*/
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                optBaseInfo.setOrgname(fuser.getUnitName());
          
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(DatetimeOpt.currentUtilDate());
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
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(object.getDjId());
        if (oaCarApply != null) {
            oaCarApplyMag.copyObjectNotNullProperty(oaCarApply, object);
            object = oaCarApply;
        }
        object.setCreater(fuser.getUsercode());
        object.setDepno(fuser.getPrimaryUnit());
        FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
        object.setOptid(flowDescribe.getOptId());
        object.setBiztype("F");//
        object.setBizstate("F");
        object.setSolvestatus("W");//申请状态w：办理中 T：同意F:不同意
        object.setFlowcode(flowCode);
        object.setIsUse("F");
        object.setCreatetime(new Date());//登记日期
        // 申请isuse状态为1
        object.setState("1");
        object.setCpBegtime(object.getPlanBegTime());
        object.setCpEndtime(object.getPlanEndTime());

        oaCarApplyMag.saveObject(object);
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                .getDjId());
        if (optBaseInfo == null) {
            optBaseInfo = new OptBaseInfo();
            optBaseInfo.setDjId(object.getDjId());
            optBaseInfo.setTransaffairname(object.getTransaffairname());
            optBaseInfo.setOptId(flowDescribe.getOptId());
            optBaseInfo.setFlowCode(flowCode);
            optBaseInfo.setBiztype("F");//
            optBaseInfo.setBizstate("F");
            optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
            optBaseInfo.setOrgname(fuser.getUnitName());
      
            optBaseInfo.setTransAffairNo(object.getDjId());
            optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
            optBaseInfo.setCreateuser(object.getCreater());
            optBaseInfoManager.saveObject(optBaseInfo);
        }
        if (object != null) {
            FlowInstance flowInst = flowEngine.createInstance(flowCode,object.getTransaffairname(), object.getDjId(),
                    fuser.getUsercode(), fuser.getPrimaryUnit());

            long flowInstId = flowInst.getFlowInstId();
            long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
            this.setFlowInstId(flowInstId);
            curNodeInstId = nodeInstId;
            curFlowInstId = flowInstId;

            object.setFlowInstId(flowInstId);
            object.setBiztype("W");// 等待审核
            object.setBizstate("W");
            oaCarApplyMag.saveObject(object);

            optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
            optBaseInfo.setFlowInstId(flowInstId);

            optBaseInfo.setBiztype("W");// 等待审核
            optBaseInfo.setBizstate("W");
            optBaseInfoManager.saveObject(optBaseInfo);
            //将登记人员纳入办件角色
            flowEngine.assignFlowWorkTeam(flowInstId,"jbr",fuser.getUsercode(),"用车登记人员");
            // 将登记部门纳入权限引擎
            // flowEngine.assignFlowOrganize(flowInstId, "FZBM",
            // fuser.getPrimaryUnit());
            
            saveIdeaInfo();
            
            
           
        }
        createPDF();
       // submitNode();
        return "refreshTasks";
    }

    /**
     * 保存用车发起步骤过程日志信息
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

        optIdeaInfo.setTransidea("用车申请");
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(0L);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename("用车申请");
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea("用车申请");

        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

    }
    
    /**
     * 保存过程日志信息
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
        optIdeaInfo.setTransidea(null!=object.getOptProcInfo()?object.getOptProcInfo().getTransidea():null);
        optIdeaInfo.setIdeacode(null!=object.getOptProcInfo()?object.getOptProcInfo().getIdeacode():null);
        
        
        OptProcInfo procInfo = new OptProcInfo();
        procInfo.setNodeInstId(curNodeInstId);
        procInfo.setDjId(object.getDjId());
        procInfo.setNodename(nodename);
        procInfo.setTransdate(new Date(System.currentTimeMillis()));
        procInfo.setNodeinststate("N");
        procInfo.setUnitcode(loginInfo.getPrimaryUnit());
        procInfo.setUsercode(loginInfo.getUsercode());
        procInfo.setTransidea(null!=object.getOptProcInfo()?object.getOptProcInfo().getTransidea():"提交");
        procInfo.setIdeacode(null!=object.getOptProcInfo()?object.getOptProcInfo().getIdeacode():null);
        procInfo.setNodeCode(nodeCode);
        optProcInfoManager.saveObject(procInfo);
        optProcInfoManager.saveIdeaInfo(optIdeaInfo, procInfo);

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
            // 部门列表
            unitlist = sysUnitManager.getAllSubUnits("1");
            filterMap.put("biztype", "C");

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaCarApplyMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            return "cardjid";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String delete() {
        super.delete();

        return "delete";
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
     * 时间段申请时间段是否可以被抢占
     * 
     * @return
     * @throws IOException
     */
    public String isTReplace() throws IOException {
        json = true;

        if (! isExistCanReplace()) {
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
        List<OaCarApply> applylist = new ArrayList<OaCarApply>();
     
        // 调配时的验证(传入时间字段不一样)
        if (null != object.getDoBegTime() && null != object.getDoEndTime()) {
            applylist = oaCarApplyMag.getApplylist(object.getDjId(),
                    object.getCardjid(), object.getDoBegTime(),
                    object.getDoEndTime(), "2");
        }

        if (applylist != null && applylist.size() > 0) {
            return false;

        } else {
            return true;

        }

    }

    /**
     * 时间冲突且冲突数据已进入使用状态的记录不能被抢占
     * 判断申请时间或者调配时间段是能被抢占 优先级：同一条申请，若已被调配则以调配时间为占用时间，否则按申请时间
     * 如果djId已存在，在编辑验证时需要排除当前记录
     * 
     * @return
     */
    private boolean isExistCanReplace() {
        List<OaCarApply> applylist = new ArrayList<OaCarApply>();
     
        // 调配时的验证(传入时间字段不一样)
        if (null != object.getDoBegTime() && null != object.getDoEndTime()) {
            applylist = oaCarApplyMag.getApplylist(object.getDjId(),
                    object.getCardjid(), object.getDoBegTime(),
                    object.getDoEndTime(), "2");
        }

        if (applylist != null && applylist.size() > 0) {
            for(OaCarApply apply:applylist){
                if(new Date().getTime()-apply.getCpBegtime().getTime()>0 && apply.getCpEndtime().getTime()-new Date().getTime()>0){
                    return false;
                }
            }
            return true;
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

        values.put("oaCarApplyManager", "车辆申请");

        inst.put("oaCarApplyManager", true);

        // 会议室列表:启用(oaMeetinfolist未用到)
//        Map<String, Object> filterMapCL = new HashMap<String, Object>();
//        filterMapCL.put("isuse", "T");
//        oaCarinfolist = oaCarinfoManager.listObjects(filterMapCL);

        request.setAttribute("values", values);
        request.setAttribute("inst", inst);

        return "calendarView";
    }


    public List<OaCarinfo> getOaCarinfolist() {
        return oaCarinfolist;
    }

    public void setOaCarinfolist(List<OaCarinfo> oaCarinfolist) {
        this.oaCarinfolist = oaCarinfolist;
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
        Date  start= new Date(
                Long.parseLong((String) params.get("start")) * 1000);
        Date end = new Date(
                Long.parseLong((String) params.get("end")) * 1000 - 1000);
        String cardjid = (String) params.get("cardjid");
        
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("docpTimeBegin",DatetimeOpt.convertDateToString( start,"yyyy-MM-dd HH:mm:ss")); 
        filterMap.put("docpTimeEnd",DatetimeOpt.convertDateToString( end,"yyyy-MM-dd HH:mm:ss")); 
       
        filterMap.put("mipSelf",((FUserDetail) super.getLoginUser()).getUsercode());//自己所有+别人已安排数据
        
        List<OaCarApply> applylist =oaCarApplyMag.listObjects(filterMap);         

        
      /*  List<OaCarApply> applylist = oaCarApplyMag.getApplylist(null,
                cardjid, start, end, "1,2");*/

        List<Map<String, Object>> jsonResult = new ArrayList<Map<String, Object>>();
        getJsonResult(applylist, jsonResult, params);

        String resultJson = JSONArray.fromObject(jsonResult).toString();

        if (StringUtils.isNotBlank(resultJson)) {
            ServletActionContext.getResponse().getWriter().print(resultJson);
        }
    }

    private void getJsonResult(List<OaCarApply> meetApplyList,
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
            List<OaCarApply> meetApplyList, Map<String, Object> params) {
        String contextPath = (String) params.get("contextPath");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (OaCarApply data : meetApplyList) {
            Map<String, Object> task = new HashMap<String, Object>();
            // 用户owner
            String owner = CodeRepositoryUtil.getValue("usercode",
                    data.getCreater());

            task.put("id", data.getDjId());
            String title ="";
             title +=CodeRepositoryUtil.getValue("meetState",  data.getState());
             title +="\n"+ '[' + owner + "] " ;
             title +=null==data.getCarno()?"":data.getCarno();
             title +=null==data.getTransaffairname()?"":"\n"+ data.getTransaffairname();
             title +=null==data.getMeetingPersions()?"":"\n"+ data.getMeetingPersions();

            task.put("title", title);
           
          
            // 申请未确认
            if (StringUtils.isNotBlank(data.getState())
                    && "1".equals(data.getState())) {
                task.put("tasktag", "yellow");
            }
            // 确认后按调配时间显示
            if (StringUtils.isNotBlank(data.getState())
                    && "2".equals(data.getState())) {
                task.put("tasktag", "green");
            }
          //6暂存
            if (StringUtils.isNotBlank(data.getState())
                    && "6".equals(data.getState())) {
                task.put("tasktag", "orange");
            }
            
            
            //其他  3已使用  4不同意 //5抢占//7取消
            if (StringUtils.isNotBlank(data.getState())
                    &&( "3".equals(data.getState())||"4".equals(data.getState())||"5".equals(data.getState())||"7".equals(data.getState()))) {
                task.put("tasktag", "blue");
            }
            
            task.put("start", DatetimeOpt.convertDateToString(
                    data.getCpBegtime(), "yyyy-MM-dd HH:mm:ss"));
            task.put("end", DatetimeOpt.convertDateToString(
                    data.getCpEndtime(), "yyyy-MM-dd HH:mm:ss"));
            
            // 使用记录
           /* if (StringUtils.isNotBlank(data.getState())
                    && "3".equals(data.getState())) {
                task.put("tasktag", "purple");
                task.put("start", DatetimeOpt.convertDateToString(
                        data.getBegTime(), "yyyy-MM-dd HH:mm:ss"));
                task.put("end", DatetimeOpt.convertDateToString(
                        data.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

            }*/
            task.put("textColor","black");

            // 可编辑
            task.put("editable", true);

            task.put("allDay", false);

            String url = "/oa/oaCarApply!generalOptView.do?djId="
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
    /**
     * 会议室时间查看
     * 
     * @param djid
     * @return
     */
    public static OptHtmlFrameInfo getBizTimeViewFrame(String djId) {
        OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
        viewFrameInfo.setFrameId("viewFrame");
        viewFrameInfo.setFrameLegend("事项信息");
        /*viewFrameInfo
                .setFrameSrc("/wwd/srPermitApply!viewItem.do?djId=" + djid);*/
        viewFrameInfo
        .setFrameSrc("/oa/oaCarApply!viewDateFrame.do?djId=" + djId);
        viewFrameInfo.setFrameHeight("300px");
        return viewFrameInfo;
    }
    public String viewDateFrame(){ 
        String djId = (String)request.getParameter("djId");
        OaCarApply oaCarApply = oaCarApplyMag.getObjectById(djId);
        request.setAttribute("oaCarApply", oaCarApply);
        return "oaCarApplyviewDateFrame";
    }
    
    /**
     * 督查督办信息
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
    @SuppressWarnings("unused")
    private void dcdbInfo(String nodeCode) {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super.getFlowInstId());
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("supDjid", optBaseInfo.getDjId());
        filterMap.put("nodeCode",nodeCode);
        filterMap.put("state","0");//该环节督办已发起
        dcdblist=oaSuperviseManager.listObjects(filterMap);
    
    }
    
    
    /**
     * 取消车辆申请
     * 1.修carApply状态state 为7取消
     * 2.修改流程状态为结束
     * 3.发送通知给相关人员：流程参与人员，通知类型：提醒
     */
     public String cancleApply(){
         FUserDetail user = ((FUserDetail) getLoginUser());
         if(user==null){
             return "login";}
         else{
             if (null!=object &&StringUtils.isNotEmpty(object.getDjId())) {
                 OaCarApply apply = oaCarApplyMag.getObjectById(object.getDjId());
                 OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                         .getDjId());
                 apply.setState("7");//取消
                 apply.setBizstate("C");//业务状态
                 apply.setBiztype("C");//业务类别
                 apply.setSolvestatus("C");//申请状态-取消
                 oaCarApplyMag.saveObject(apply);
                 flowManager.stopInstance(optBaseInfo.getFlowInstId(), user.getUsercode(), "取消车辆申请");
                 
          
             //1.获取人员
             getUservalues("cancle");
             
             String mesgTitle="与您相关的一项用车申请["+apply.getTransaffairname()+"]已经被取消,请您及时关注。";
             String mesgContent=apply.getTransaffairname()+"于"+DatetimeOpt.convertDatetimeToString(apply.getCpBegtime())+"到"+DatetimeOpt.convertDatetimeToString(apply.getCpEndtime())+"的用车申请，现在已经被取消。";
             //2.输送邮件数据
             this.sendInnermesg(userValue,mesgTitle,mesgContent);
             
             //4.发送提醒
             oaRemindInformationManager.sendOaRemindInformation(apply.getDjId(), user.getUsercode(), userValue, mesgTitle, mesgContent);
          
             } 
         }
         createPDF();
         return list();
     }
     
     private void sendInnermesg(String userValue,String title,String content) {
         if (StringUtils.isNotBlank(userValue)) {
             OaCarApply apply=oaCarApplyMag.getObjectById(object.getDjId());//业务数据       
             Innermsg innermsg=new Innermsg();
             innermsg.setMsgcode(innermsgManager.getNextKey());
             innermsg.setMsgtype("P");//类型个人邮件
             innermsg.setSender("系统发起");
             innermsg.setSenddate(new Date());
             innermsg.setReceiveUserCode(userValue);
             innermsg.setTo(userValue);
             innermsg.setReceivename("");
             innermsg.setMsgcontent(content);
             innermsg.setMsgtitle(title);
             innermsg.setMailtype(Innermsg.MAIL_TYPE_O);//发件箱标记
             innermsgManager.saveMsg(innermsg);
         }            
     }
     private String userValue;// 获取人员usercode
     /*
      * 获取会议相关人员
      * 1.与会领导
      * 2.参会单位(按岗位过滤)
      * 3.办件参与人员（不包括自己）
      * type domeeting  1,2
      *      cancle 1,2,3
      */
     private void getUservalues(String type) {
         OaCarApply apply = oaCarApplyMag.getObjectById(object.getDjId());
         FUserDetail user = ((FUserDetail) getLoginUser());
         if(null!=user&&null!=user.getUsercode()){
             if (StringUtils.isNotBlank(object.getDjId())) {
                 userValue="";
                
                 if("cancle".equals(type)){
                   //流程参与人员
                     List<OptIdeaInfo> optIdeaInfos=optProcInfoManager.listIdeaLogsByDjId(apply.getDjId());
                     if(optIdeaInfos!=null && optIdeaInfos.size()>0){    
                         for(int i=0;i<optIdeaInfos.size();i++){
                             if(!user.getUsercode().equals(optIdeaInfos.get(i).getUsercode())){
                           String value=optIdeaInfos.get(i).getUsercode()+",";
                           userValue+=value;
                             }
                         }
                     } 
                     
                     //   下一步办理人员
                     List<VUserTaskList> tasks=actionTaskDao.getTasksByFlowid(apply.getFlowInstId());
                     if(null!=tasks&&tasks.size()>0)
                     for (VUserTaskList task : tasks) {
                         if(!user.getUsercode().equals(task.getUserCode())){
                             String value=task.getUserCode()+",";
                             userValue+=value;
                               }
                     }
                 }
              
                 
                 
                
                 if(StringUtils.isNotBlank(userValue)){
                     userValue=userValue.substring(0, userValue.length()-1); 
                     }
                } 
             
         }

     }
     
    /**
     * 用车申请审批不涉及手写签批合并图层
     */
     private  void createPDF(){
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
             final String formHtmlUrl = optPdfInfoManager.getCARFormHtmlUrl(contextPath,userCode, object.getDjId());
             taskExecutor.execute(new Runnable(){
                 @Override
                 public void run() {
                     createPDF(object, "用车申请", 0L,userCode,exePath,formHtmlUrl);
                 }
             });
         }
     }
     
     /**
      * 新增是生成pdf
      * @param oaCarApply
      * @param nodeName
      * @param nodeInstId
      * @param userCode
      * @param exeToolpath
      * @param formHtmlUrl
      */
    private void createPDF(OaCarApply oaCarApply,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
        OptPdfInfo optPdfInfo = null;
        try {
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<车辆申请djId:"+oaCarApply.getDjId()+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           String pdfStorePath = optPdfInfoManager.assignPdfStorePathOnDisk(oaCarApply.getDjId(), nodeInstId);
           File pdfStoreFile = new File(pdfStorePath);
            //创建pdf
           optPdfInfo = optPdfInfoManager.createPDF(oaCarApply.getDjId(),nodeInstId,formHtmlUrl);
            //转成swf
           optPdfInfoManager.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
            //加密
           optPdfInfo = optPdfInfoManager.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
           
           optPdfInfo.setUserCode(userCode);
           optPdfInfo.setBizType(String.valueOf(optPdfInfoManager.getBizTypeForPdf(oaCarApply.getDjId())));
           optPdfInfo.setNodeName(nodeName);
           optPdfInfo.setNodeInstId(nodeInstId);
           optPdfInfo.setId(optPdfInfoManager.getNextLongSequence());
           optPdfInfoManager.saveObject(optPdfInfo);
           log.info("<<<<<<<<<<<<<<<<<<<<<<<<车辆申请djId:"+oaCarApply.getDjId()+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        } catch (Exception e) {
            log.error("生成PDF异常："+e.getMessage());
        } finally{
            if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
                optPdfInfoManager.clearTempFile(optPdfInfo.getTempDirPath());
            }
        }
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
    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }
    public OaSuperviseManager getOaSuperviseManager() {
        return oaSuperviseManager;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public OaCarinfoManager getOaCarinfoManager() {
        return oaCarinfoManager;
    }

    public void setOaCarinfoManager(OaCarinfoManager oaCarinfoManager) {
        this.oaCarinfoManager = oaCarinfoManager;
    }
    public String getUserValue() {
        return userValue;
    }
    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }
    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }
    public InnermsgManager getInnermsgManager() {
        return innermsgManager;
    }
    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }
    public VOptBaseListManager getvOptBaseListManager() {
        return vOptBaseListManager;
    }
    public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
        this.vOptBaseListManager = vOptBaseListManager;
    }
    public void setOaDriverInfoManager(OaDriverInfoManager oaDriverInfoManager) {
        this.oaDriverInfoManager = oaDriverInfoManager;
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
