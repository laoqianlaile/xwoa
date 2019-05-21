package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.service.OaMeetMinutesManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.service.OaUnitsLeaderManager;
import com.centit.powerruntime.service.OptNewlyIdeaManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;
import com.centit.workflow.FlowInstance;
import com.centit.workflow.NodeInstance;
import com.centit.workflow.sample.po.WfTeam;

public class OaSuperviseAction extends OACommonBizAction<OaSupervise> {
    private static final Log log = LogFactory.getLog(OaSuperviseAction.class);
    private static final long serialVersionUID = 1L;
    private OaSuperviseManager oaSuperviseMag;

    public void setOaSuperviseManager(OaSuperviseManager basemgr) {
        oaSuperviseMag = basemgr;
        this.setBaseEntityManager(oaSuperviseMag);
    }
        private SysUserManager sysUserManager;
        private SysUnitManager sysUnitManager;
        private OptJspInfo jspInfo;
        private List<OptNewlyIdea> optNewlyIdeaList;
        private OptNewlyIdeaManager optNewlyIdeaManager;
        private OaMeetinfoManager oaMeetinfoManager;
        private OaMeetMinutesManager oaMeetMinutesManager;
        private VOptBaseListManager vOptBaseListManager;
        private OaUnitsLeaderManager oaUnitsLeaderManager;
        private OaPowerrolergroupManager oaPowerrolergroupManager;
        
        private String curUrl;
        private String superUrl;
        private FlowDefine flowDefine;
        private String flowCode;

        private String viewtype; // 区别查看页面是否有返回按钮
        private List<OaSupervise>  numlist;
        
        
        private String queryUnderUnit;//(列表类别)按职务等级查询列表
        private FUserunit userUnit;// 用户单位
        private String userRank=null;
        private List<FUnitinfo> unitList;
        
        

        /**
         * 列表数据
         */
        public String list() {
            try {
                @SuppressWarnings("unchecked")
                Map<Object, Object> paramMap = request.getParameterMap();
                resetPageParam(paramMap);
                Map<String, Object> filterMap = convertSearchColumn(paramMap);
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                filterMap.put("creater", fuser.getUsercode());
                Limit limit = ExtremeTableUtils.getLimit(request);
                PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
                if ("F".equals(this.show_type)) {// 登记的查询list
                    filterMap.put("biztype", "F");
                }
                if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                    filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                    filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
                }
                
                
                userRank=getUserRankByUsercode(fuser.getUsercode());//控制页面显示内容:全机构(部门下拉框)、全处室（checkboox）
                //列表页面添加职务分类查看 ：厅长查看机构所有查看，处长查看本处室
                if("true".equals(queryUnderUnit)){//(列表类别)按职务等级查询列表--默认显示自己的
                    
                    userUnit =sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
                    if(StringUtils.equals(userRank, "CZ")){
                      filterMap.put("queryUnderUnit","true");//页面传参
                        filterMap.put("unitcode",userUnit.getUnitcode());
                    }else  if(StringUtils.equals(userRank, "TZ")){
                      filterMap.put("queryUnderUnit","true");//部门编码从页面传入queryUnderUnit
//                    filterMap.put("unitcode",userUnit.getUnitcode());//部门编码从页面传入s_unitcode
                    }
                }else{
                    filterMap.put("usercode", fuser.getUsercode());
                }
                unitList=unitList();//科级部门
                
                numlist = oaSuperviseMag.listOaSupervise(filterMap,pageDesc);
                
                
                
                
//                numlist = oaSuperviseMag.listObjects(filterMap,pageDesc);
                totalRows = pageDesc.getTotalRows();
                setbackSearchColumn(filterMap);
                //判断某一个督办件是否超时或是否提醒
                
                if(numlist.size()>0){
                    for(int i=0;i<numlist.size();i++){
                        OaSupervise oa=numlist.get(i);
                        if(oa.getLimittime()!=null){
                            Date nowtime=(new Date(System.currentTimeMillis()));
                            //比较日期，
                           //flag=true  超时；flag=false 提醒
                            boolean flag = oa.getLimittime().before( nowtime);
                       
                          //warntype 0 提醒 1 超时
                            if(flag ){
                                if( "W".equals(oa.getBizstate())){
                                oa.setWarnType("1");
                                objList.add(oa);
                                }else{
                                    objList.add(oa); 
                                }
                            }else{
                                if( "W".equals(oa.getBizstate())){
                                    oa.setWarnType("0");
                                    objList.add(oa);
                                    }else{
                                        objList.add(oa); 
                                    }                
                            }
                            
                        }
                    }
                    
                }else{
                    objList.addAll(numlist);
                }
                //return LIST;
                return "listV2";
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
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
         * 编辑
         */
        private List<VoadDcdbNum> selectList;


        @Override
        public String edit() {
            super.edit();
            if (StringUtils.isBlank(object.getDjId())) {
                object.setDjId(oaSuperviseMag.getNextKey());
            }   
            //不是从督办流程发起的督办方法入口
            String supdjid=request.getParameter("supDjid");
            if(StringUtils.isNotBlank(supdjid)){
                object.setSupDjid(supdjid);
               //根据supdjid获取对应数据
     
                selectList=oaSuperviseMag.getdcdbnum(supdjid); 
                if(selectList!=null && selectList.size()>0){
                object.setSuperviseUsers(selectList.get(0).getUserCode());
                object.setNodecode(selectList.get(0).getNodeCode());
                object.setTitle(selectList.get(0).getFlowOptName());
                }
            }
            //提醒人员 2016-3-31
            VOptBaseList vo= vOptBaseListManager.getObjectByDjId(object.getSupDjid());
            if(vo!=null){
                Map<String, String> nameMap = new HashMap<String, String>();
                String usercode=vo.getBizusercodes();
                String name=vo.getBizusernames();
                if(StringUtils.isNotBlank(usercode)&&!"-".equals(usercode)){
                    String[] usercodes=usercode.split(",");
                    String[] names=name.split(",");
                    for(int i=0;i<usercodes.length;i++){
                        Map<String,String> leadercode=oaUnitsLeaderManager.getLeadercode(usercodes[i]);
                        if(leadercode!=null){
                            for(Map.Entry<String, String> entry:leadercode.entrySet()){ 
                                nameMap.put(entry.getKey(), entry.getValue());
                           }   
                        } 
                    }
                }
                if(!nameMap.isEmpty()){
                    request.setAttribute("txnames", nameMap);
                }else{
                    initUsers();
                }
            }
            return EDIT;
       
            
        }
        /*
         * 获取人员
         */
        public void initUsers() {
            // JSONArray userjson
            // =oaPowerrolergroupManager.putAllUserTree(getLoginUserCode());
            JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
            request.setAttribute("userjson", userjson);
            
            request.setAttribute("stationUsers", oaPowerrolergroupManager.getStationtUsersTree(null));
            request.setAttribute("unitLeaderuserjson", oaPowerrolergroupManager.getUnitLeaderUsersTree());
        }
        
        
        /**
         * 通用业务框架属性会议室信息查看
         */

        public String generalOptView() {
            List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
            // 查看办件业务数据信息
            frameList.add(getBizDataViewFrame(object.getDjId()));
            
            frameList.add(super.getCommonStuffFrame(object.getDjId(),
                    null, null));// 附件
            
            // 用于展示查看详细信息Lab标签内容 原先tab方式显示
//            frameList.add(this.getAllViewFrame(object.getDjId()));
            
            //frameList 页面列表显示
            //frameList=getAllInfoListFrame(frameList,object.getDjId());
            jspInfo = new OptJspInfo();
//            jspInfo.setTitle("督办查看");
            jspInfo.setFrameList(frameList);
            //从首页进入
            if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
                request.setAttribute("dashboard", request.getParameter("dashboard"));
            }

            return "generalOptView";
        }
        
        private List<OptHtmlFrameInfo> getAllInfoListFrame(List<OptHtmlFrameInfo> frameList,String djId) {
            
            frameList.add(GeneralOperatorAction.getIdeaListFrame(djId));
            frameList.add(GeneralOperatorAction.getStuffListFrame(djId));
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
            String itemType = null;
            if(StringUtils.isNotEmpty(djid)){
                itemType = djid.replaceAll("[0-9]+", "");
            }
            OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
            stuffsFrameInfo.setFrameId("AllInfoFrame");
            stuffsFrameInfo.setFrameSrc("/oa/oaSupervise!getAllCaseView.do?djId="
                    + djid + "&nodeInstId=" + curNodeInstId
                    +"&itemType=" + itemType);
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
            object = oaSuperviseMag.getObject(object);

            if (null == object.getFlowInstId()) {
                object.setFlowInstId((long) 9999999);
            }
            
          
            request.setAttribute("flowInstId", object.getFlowInstId());
            request.setAttribute("nodeId", nodeId);
            return "dbinfoView";
        }

        /**
         * 督办数据查看
         * 
         * @param djid
         * @return
         */
        private OptHtmlFrameInfo getBizDataViewFrame(String djid) {
            OptHtmlFrameInfo viewFrameInfo = new OptHtmlFrameInfo();
            viewFrameInfo.setFrameId("viewFrame");
            viewFrameInfo.setFrameLegend("督办基本信息");
            viewFrameInfo.setFrameSrc("/oa/oaSupervise!view.do?viewtype=T&djId="
                    + djid);
            viewFrameInfo.setFrameHeight("700px");
            return viewFrameInfo;
        }

        /**
         * 督办业务信息查看，其中本页面包含2各部分：
         * 1、业务基本信息
         * 此部分显示被督办的业务信息：所有涉及工作流的方面的业务；
         * 业务信息 po封装在督办信息实体中，根据督办类型来判断；
         *  //7公文8车辆9会议室10签报11事权
         * 2、督办信息
         */
        @Override
        public String view(){
            super.view();
            //TODO 此处封装被督办业务信息.../oa/oaMeetroomApply!view.do?djId=
            if(StringUtils.isNotBlank(object.getSupDjid())){
                
            superUrl=CodeRepositoryUtil.getValue("optType", StringUtils.substringBefore(object.getSupDjid(), "0"))+"!"+getViewMethod(object.getSupDjid())+"?djId=" + object.getSupDjid();
          
            }
          return VIEW;
        }
        /**
         * 根据djid判断查看基本信息进去那个方法名主要用于区分收发文与其他流程的查看差异
         * @return
         */
        public String getViewMethod(String djid){
            String methodName="";
            String type=StringUtils.substringBefore(djid, "0");
            if("SW".equals(type)){
                methodName="registerDoOrReadView.do";
            }else if("FW".equals(type)){
                methodName="viewDispatchDocInfo.do";
            }else{
                methodName="view.do";
            }
            
            return methodName;
            
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
                object.setCreatertime(new Date(System.currentTimeMillis()));
                object.setCreater(fuser.getUsercode());
                object.setSuperviseDepno(fuser.getPrimaryUnit());
                FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
                object.setOptid(flowDescribe.getOptId());
                object.setFlowcode(flowCode);
                object.setBiztype("F");//
                object.setBizstate("F");
             
                //督办类型-根据supdjid来判断自动调用接口塞值
                object.setSuperviseType(BizCommUtil.getPrefix4Biz(object.getSupDjid(),1));
                oaSuperviseMag.saveObject(object);

                object = oaSuperviseMag.getObjectById(object.getDjId());

                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());
                if (optBaseInfo == null) {
                    optBaseInfo = new OptBaseInfo();
                    optBaseInfo.setDjId(object.getDjId());
                    optBaseInfo.setTransaffairname("关于【" + object.getTitle()
                            + "】的督办");
                    optBaseInfo.setOptId(flowDescribe.getOptId());
                    optBaseInfo.setBiztype("F");//
                    optBaseInfo.setBizstate("F");
                    optBaseInfo.setOrgname(fuser.getPrimaryUnit());
                    optBaseInfo.setOrgcode(fuser.getPrimaryUnit());
                    optBaseInfo.setFlowCode(flowCode);
                    optBaseInfo.setTransAffairNo(object.getDjId());
                    optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                    optBaseInfo.setCreateuser(object.getCreater());
                    optBaseInfoManager.saveObject(optBaseInfo);
                }

            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            return this.list();
        }

   
   private List<WfTeam> WfTeamlist;

        /**
         * 保持并提交流程
         * 
         * @return
         */
        public String saveAndSubmit() {
            // 保存
            FUserDetail fuser = ((FUserDetail) getLoginUser());

            object.setCreatertime(new Date(System.currentTimeMillis()));
            object.setCreater(fuser.getUsercode());
            object.setSuperviseDepno(fuser.getPrimaryUnit());
            FlowDescribe flowDescribe = flowDefine.getFlowDefObject(flowCode);
            object.setOptid(flowDescribe.getOptId());
            object.setBiztype("F");//
            object.setBizstate("F");
            object.setFlowcode(flowCode);
            // 督办发起 状态置为0
            object.setState("0");
            object.setSuperviseType(BizCommUtil.getPrefix4Biz(object.getSupDjid(),1));
            oaSuperviseMag.saveObject(object);

            object = oaSuperviseMag.getObjectById(object.getDjId());

            if(object!=null){
                OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object
                        .getDjId());
            
           
            if (optBaseInfo == null) {
                optBaseInfo = new OptBaseInfo();
                optBaseInfo.setDjId(object.getDjId());
                optBaseInfo.setTransaffairname("关于【" + object.getTitle()
                        + "】的督办催办");
                optBaseInfo.setOptId(flowDescribe.getOptId());
                optBaseInfo.setFlowCode(flowCode);
                optBaseInfo.setBiztype("F");//
                optBaseInfo.setBizstate("F");
                optBaseInfo.setOrgname(fuser.getPrimaryUnit());
                optBaseInfo.setTransAffairNo(object.getDjId());
                optBaseInfo.setCreatedate(new Date(System.currentTimeMillis()));
                optBaseInfo.setCreateuser(object.getCreater());
                optBaseInfoManager.saveObject(optBaseInfo);
            }
          
                FlowInstance flowInst = flowEngine.createInstance(flowCode, "关于【"
                        + object.getTitle() + "】的督办", object.getDjId(),
                        fuser.getUsercode(), fuser.getPrimaryUnit());

                long flowInstId = flowInst.getFlowInstId();
                long nodeInstId = flowInst.getFirstNodeInstance().getNodeInstId();
                this.setFlowInstId(flowInstId);
                curNodeInstId = nodeInstId;
                curFlowInstId = flowInstId;
                
               //查看流程工作小组的是否存在该环节的办件角色 存在则删除flowInstId对应的角色
                WfTeamlist=flowEngine.viewFlowWorkTeamList(flowInstId, "dbhf");
                if(WfTeamlist!=null&&WfTeamlist.size()>0){   
                    
                   flowEngine.deleteFlowWorkTeam(flowInstId,  "dbhf");       
              
                //将督办业务的环节操作人员塞进流程工作小组最为督办回复人员
                String[] values = object.getSuperviseUsers().split(",");// 分割字符串
                    for (int i = 0; i < values.length; i++) {
                     
                        flowEngine.assignFlowWorkTeam(flowInstId, "dbhf",values[i] );
                    }
                }else{
                    //将督办业务的环节操作人员塞进流程工作小组最为督办回复人员
                    String[] values = object.getSuperviseUsers().split(",");// 分割字符串
                        for (int i = 0; i < values.length; i++) {
                         
                            flowEngine.assignFlowWorkTeam(flowInstId, "dbhf",values[i] );
                        }
                }
                
                object.setFlowInstId(flowInstId);
                object.setBiztype("W");// 等待审核
                object.setBizstate("W");
               
                oaSuperviseMag.saveObject(object);

                optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
                optBaseInfo.setFlowInstId(flowInstId);

                optBaseInfo.setBiztype("W");// 等待审核
                optBaseInfo.setBizstate("W");
                optBaseInfo.setDbType("2");//督办状态初始1
                optBaseInfoManager.saveObject(optBaseInfo);

                // 将登记部门纳入权限引擎
                // flowEngine.assignFlowOrganize(flowInstId, "FZBM",
                // fuser.getPrimaryUnit());
           
            }
            return this.list();
        }

        
        
        private String initsuperviseType(String supDjid) {
            //1许可 2处罚 3投诉 4 预报警 5 办理部门 6 办理人员7公文8车辆9会议室10签报11事权12其他
             String returnType = "";
             if (supDjid.indexOf("HYS") != -1) {// 会议室
                 returnType = "9";
             }else if (supDjid.indexOf("CAR") != -1) {// 车辆
                 returnType = "8";
             }else if (supDjid.indexOf("QB") != -1) {// 签报
                 returnType = "10";
             }else if (supDjid.indexOf("FW") != -1) {// 公文
                 returnType = "7";
             }else if (supDjid.indexOf("SW") != -1) {// 公文
                 returnType = "7";
             }else if (supDjid.indexOf("SQ") != -1) {//事权
                 returnType = "11";
             }
             // TODO ...

             return returnType;
             
         } 
        
       /**
        * 某一督办流水号的督办次数列表
        *  
        **/
        private List<OaSupervise> superviselist;

        public String superviselist(){
         try {
             @SuppressWarnings("unchecked")
             Map<Object, Object> paramMap = request.getParameterMap();
             resetPageParam(paramMap);
             Map<String, Object> filterMap = convertSearchColumn(paramMap);
             Limit limit = ExtremeTableUtils.getLimit(request);
             PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);      
             superviselist = oaSuperviseMag.listObjects(filterMap,pageDesc);
             totalRows = pageDesc.getTotalRows();             
             return "suplist";
         } catch (Exception e) {
             e.printStackTrace();
             return ERROR;
         }
      
     }
        /**
         * 供其他模块调用督查督办列表
         * @return
         */
        public String superviseFramelist(){
            superviselist();
            return "supFramelist"; 
        }   
        
        public static OptHtmlFrameInfo getSupListFrame(String djId) {
            OptHtmlFrameInfo procFrameInfo = new OptHtmlFrameInfo();
            procFrameInfo.setFrameId("superviseFrame");
            procFrameInfo
                    .setFrameSrc("/oa/oaSupervise!superviseFramelist.do?s_supDjid="
                            + djId);
            procFrameInfo.setFrameHeight("300px");
            return procFrameInfo;
        }
       /*************************setter/getter*****************************/
        private String show_type;

        public SysUserManager getSysUserManager() {
            return sysUserManager;
        }

        public void setSysUserManager(SysUserManager sysUserManager) {
            this.sysUserManager = sysUserManager;
        }

        public SysUnitManager getSysUnitManager() {
            return sysUnitManager;
        }

        public void setSysUnitManager(SysUnitManager sysUnitManager) {
            this.sysUnitManager = sysUnitManager;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
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

        public OptNewlyIdeaManager getOptNewlyIdeaManager() {
            return optNewlyIdeaManager;
        }

        public void setOptNewlyIdeaManager(OptNewlyIdeaManager optNewlyIdeaManager) {
            this.optNewlyIdeaManager = optNewlyIdeaManager;
        }

        public OaMeetinfoManager getOaMeetinfoManager() {
            return oaMeetinfoManager;
        }

        public void setOaMeetinfoManager(OaMeetinfoManager oaMeetinfoManager) {
            this.oaMeetinfoManager = oaMeetinfoManager;
        }

        public OaMeetMinutesManager getOaMeetMinutesManager() {
            return oaMeetMinutesManager;
        }

        public void setOaMeetMinutesManager(OaMeetMinutesManager oaMeetMinutesManager) {
            this.oaMeetMinutesManager = oaMeetMinutesManager;
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

        public String getViewtype() {
            return viewtype;
        }

        public void setViewtype(String viewtype) {
            this.viewtype = viewtype;
        }

        public List<VoadDcdbNum> getSelectList() {
            return selectList;
        }

        public void setSelectList(List<VoadDcdbNum> selectList) {
            this.selectList = selectList;
        }
        public List<OaSupervise> getSuperviselist() {
            return superviselist;
        }

        public void setSuperviselist(List<OaSupervise> superviselist) {
            this.superviselist = superviselist;
        }

        public String getSuperUrl() {
            return superUrl;
        }

        public void setSuperUrl(String superUrl) {
            this.superUrl = superUrl;
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

        public List<FUnitinfo> getUnitList() {
            return unitList;
        }

        public void setUnitList(List<FUnitinfo> unitList) {
            this.unitList = unitList;
        }

        public void setvOptBaseListManager(VOptBaseListManager vOptBaseListManager) {
            this.vOptBaseListManager = vOptBaseListManager;
        }

        public void setOaUnitsLeaderManager(OaUnitsLeaderManager oaUnitsLeaderManager) {
            this.oaUnitsLeaderManager = oaUnitsLeaderManager;
        }

        public void setOaPowerrolergroupManager(
                OaPowerrolergroupManager oaPowerrolergroupManager) {
            this.oaPowerrolergroupManager = oaPowerrolergroupManager;
        }
        
        
    }
