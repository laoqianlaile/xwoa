package com.centit.oa.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.FieldCache.LongParser;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.dao.CodeBook;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.efile.mgt.EfileManager;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetingApply;
import com.centit.oa.po.OaMeetingmaterial;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.po.OaMeetingmaterialinfosId;
import com.centit.oa.po.VOaMeetingMaterialApply;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetingApplyManager;
import com.centit.oa.service.OaMeetingmaterialManager;
import com.centit.oa.service.OaMeetingmaterialinfosManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.SysParametersUtils;



public class OaMeetingApplyAction extends OACommonBizAction<OaMeetingApply> {
    private static final Log log = LogFactory.getLog(OaMeetingApplyAction.class);
    private static final long serialVersionUID = 1L;
    
    private OaMeetingApplyManager oaMeetingApplyManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private OaMeetingmaterialManager oaMeetingmaterialManager;
    private OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager;
    private OptStuffInfoManager optStuffInfoManager;
    private List<OaMeetingApply> meetingList;
    private List<OaMeetingmaterial> oaMeetingmaterialList;
    private List<OaMeetingmaterial> listmeet;
    private List<OaMeetingmaterial> listAllmeet;
    //private List<OaMeetingmaterial> OaMeetingmaterialList;
    private List<OptStuffInfo> stuffList = new ArrayList<OptStuffInfo>();
    
    private OaSmssendManager oaSmssendManager;
    /**
     * 查询会议列表数据
     */
    public String list() {
        try {

            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            filterMap.put(CodeBook.SELF_ORDER_BY," meetApplytime desc" );//以会议时间倒叙排序
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaMeetingApplyManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            // initUsers();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 我的会议 ,会议详情
     */
    public String view() {
        @SuppressWarnings("unchecked")
        String id = request.getParameter("mId");
        object = oaMeetingApplyManager.getObjectById(id);
       /* if(StringUtils.isNotBlank(id)){
            Map<String, Object> map = new HashMap<String, Object>();
            FUserDetail user = ((FUserDetail) getLoginUser());
            map.put("mId", id);
            map.put(CodeBook.SELF_ORDER_BY," orderId asc" );
            userRank=getUserRankByUsercode(user.getUsercode());
            if(StringUtils.isBlank(userRank)){//证明是一般人员
                map.put("meetingAttendees",user.getUsername());
            }else{//主要领导
                //不需要做过滤，全部能看到
            }
            listmeet = oaMeetingmaterialManager.listObjects(map);
           if(listmeet !=null && listmeet.size()>0){
                for(OaMeetingmaterial oa :listmeet){
                    List<OptStuffInfo> docAttachments = optStuffInfoManager.getStuffInfoList(oa.getDjId(),0L);
                    stuffList.addAll(docAttachments);
                }
            }
        }*/
        
      //能看到所有议程 但是只有参加了该议程才能查看详情 领导除外
        if(StringUtils.isNotBlank(id)){
            FUserDetail user = ((FUserDetail) getLoginUser());
            userRank=getUserRankByUsercode(user.getUsercode());
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("mId", id);
            //查询该会议下所有议程
            listAllmeet = oaMeetingmaterialManager.listObjects(map1);
            //先判断登录人的职位
            if(StringUtils.isBlank(userRank)){//证明是一般人员
                map1.put("meetingAttendees",user.getUsername());
                //查询当前人员参加的所有议程
                listmeet = oaMeetingmaterialManager.listObjects(map1);
                //判断所有议程里 哪些是当前人员参加的议程
                for(OaMeetingmaterial oaMeetingma:listAllmeet) {
                    for(OaMeetingmaterial oaMeetingmaPerson:listmeet) {
                        if(oaMeetingma.getDjId().equals(oaMeetingmaPerson.getDjId())) {
                            oaMeetingma.setFlagRight("1");//有查看议程详情的权限
                            break;
                        }
                    }
                    
                }
            }else{//主要领导
                for(OaMeetingmaterial oaMeetingma:listAllmeet) {
                            oaMeetingma.setFlagRight("1");//有查看该会议下面所有议程的权限
                    }
                    
            }
            
        }
        return "view";
    }
    /**
     * 查询我的会议，会议列表数据
     */
    public String oaMeetingList(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put(CodeBook.SELF_ORDER_BY," meetApplytime desc" );//以会议时间倒叙排序
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        FUserDetail user = ((FUserDetail) getLoginUser());
        userRank=getUserRankByUsercode(user.getUsercode());
        if(StringUtils.isBlank(userRank)){//证明是一般人员
            filterMap.put("meeting_Attendees",user.getUsername());
        }else{//主要领导
            filterMap.put("attendLeaderName",user.getUsername());
        }
        meetingList = oaMeetingApplyManager.oaMeetingList(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "oaMeetingList";
    }
    private String userRank = null;
    /**
     * 根据usercode 获取用户行政职务
     * 
     * @param usercode
     * @return
     */
    private String getUserRankByUsercode(String usercode) {
        List<FUserunit> userUnits =sysUserManager.getSysUnitsByUserId(usercode);
        if(userUnits!=null && userUnits.size()>0){
            for(FUserunit info:userUnits){
                String userUnitRank=info.getUserrank();
                if("LD".equals(userUnitRank)){
                    userRank="LD";//主要领导
                    break;
                }else{
                    userRank=null;//其他人员
                }
            }
           
        }else{
            userRank=null;
        }
        return userRank;
    }
    /**
     * 会议资料,会议详情
     */
    public String oaMeetingView(){
        @SuppressWarnings("unchecked")
        String id = request.getParameter("mId");
        object = oaMeetingApplyManager.getObjectById(id);
        //查询关联议程
        if(StringUtils.isNotBlank(id)){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mId", id);
            map.put(CodeBook.SELF_ORDER_BY," orderId asc" );
            listmeet = oaMeetingmaterialManager.listObjects(map);
            if(listmeet !=null && listmeet.size()>0){
                for(OaMeetingmaterial oa :listmeet){
                    List<OptStuffInfo> docAttachments = optStuffInfoManager.getStuffInfoList(oa.getDjId(),0L);
                    stuffList.addAll(docAttachments);
                }
            }
        }
        return "viewperson";
    }
    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        this.initparam("HYDJ");//归档手动登记通用配置信息
        if (StringUtils.isBlank(object.getMId())) {
            super.edit();
            object.setMId(oaMeetingApplyManager.getDjId());
            return EDIT;
        } else {
              super.edit();
        return "editplus";
        }

    }
    
    private void initparam(String modecode) {
        // TODO Auto-generated method stub
        super.moduleCode=modecode;
        super.generalOpt();
    }
    
    /**
     * 会议保存
     */
    public String save(){
        try {
           
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            
            OaMeetingApply oameet = oaMeetingApplyManager.getObjectById(object.getMId());
            if(oameet !=null){
                object.setMotifytime(new Date());
                object.setCreatetime(oameet.getCreatetime());
                object.setCreater(user.getUsercode());
                if(StringUtils.isNotEmpty(object.getReleteCode())){
                    String []code = object.getReleteCode().split(",");
                    for(String decode : code){
                        deleteYC(decode);
                    }
                }
            }else{
                object.setCreatetime(new Date());
                object.setCreater(user.getUsercode());
                object.setMotifytime(new Date());
                //删除需要删除的议程
                if(StringUtils.isNotEmpty(object.getReleteCode())){
                    String []code = object.getReleteCode().split(",");
                    for(String decode : code){
                        deleteYC(decode);
                    }
                }
            }
            object.setReleteCode(null);
            super.save();
            if(object != null){//保存领导
                saveLeader(object);
            }
            
            
            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public void deleteYC(String decode) {
        OaMeetingmaterial oameet =null;
        if(StringUtils.isNotBlank(decode)){
            oameet = oaMeetingmaterialManager.getObjectById(decode);
        }
        if(oameet != null){
            
            if (StringUtils.isNotEmpty(decode)) {
                // 存放删除字段
                if (StringUtils.isNotEmpty(oameet.getMeetingAttendeesCodes())) {
                    for (String username : oameet.getMeetingAttendeesCodes().split(",")) {
                        
                        if (!username.isEmpty()) { 
                            List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosManager.findStuffIdByCode(username, decode);
                            if(stuffIdList.size()>0){
                                for(int i =0;i<stuffIdList.size();i++){
                                    oaMeetingmaterialinfosManager.deleteObjectById(stuffIdList.get(i).getCid());
                                }   
                            }
                            
                        }
                    }
                }
                //删除关联材料
                
                optStuffInfoManager.deleteObjectBanInfo(decode);
                oaMeetingmaterialManager.deleteObjectById(decode);
            }
        }
    }
    
    /**
     * 批量删除功能
     * 
     * @return
     */
    public String delete() {
        String id = request.getParameter("mId");
        if (StringUtils.isNotEmpty(id)) {
         // 存放删除字段
            object.setMId(id);
            String  meetingAttendee;  
            OaMeetingApply oameet = oaMeetingApplyManager.getObjectById(id);
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mId", id);
            List<OaMeetingmaterial> list = oaMeetingmaterialManager.listObjects(map);
            
            if(list !=null && list.size()>0){
                for(OaMeetingmaterial master : list){
                    meetingAttendee =  master.getMeetingAttendees();
                    if (StringUtils.isNotEmpty(meetingAttendee)) {
                        //删除议程附件关联
                        for (String username : meetingAttendee.split(",")) {
                      
                            if (!username.isEmpty()) { 
                                List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosManager.findStuffIdByCode(username, object.getMId());
                                if(stuffIdList.size()>0){
                                    for(int i =0;i<stuffIdList.size();i++){
                                        oaMeetingmaterialinfosManager.deleteObjectById(stuffIdList.get(i).getCid());
                                    }   
                                }

                            }
                        }
                    }
                    oaMeetingmaterialManager.deleteObjectById(master.getDjId());
                    optStuffInfoManager.deleteObjectBanInfo(master.getDjId());
                }
            }
            
            oaMeetingApplyManager.deleteObjectById(object.getMId());
        }
        return this.list();
    }
    
    
    
    /*
     * 获取人员
     */
    public void initUsers() {
        // JSONArray userjson
        // =oaPowerrolergroupManager.putAllUserTree(getLoginUserCode());
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);

        request.setAttribute("stationUsers",
                oaPowerrolergroupManager.getStationtUsersTree(null));
        request.setAttribute("unitLeaderuserjson",
                oaPowerrolergroupManager.getUnitLeaderUsersTree());
    }
    /**
     * 保存委领各议程对应的附件功能
     * @return
     * applyInfo不要传null
     */
    public String saveLeader(OaMeetingApply applyInfo){
        try {
            //待删除的汇总记录标识,标识组成为【参与人编码-初始附件编码】
            String removedMark = "";
            if(StringUtils.isNotBlank(applyInfo.getAttendLeaderCode())){
                String addedMark =applyInfo.getAttendLeaderCode();//带新增的汇总记录标识,标识组成为【参与人编码-初始附件编码】
                String addcodes=applyInfo.getAttendLeaderCode();//参会领导
            List<OaMeetingmaterial> OaMeetingmaterialList = oaMeetingmaterialManager.findListByDjId(applyInfo.getMId());//获取当前会议下面所有的议程列表信息
            HashSet<String> attender = new HashSet<String>();//去重方法
            if(OaMeetingmaterialList!=null && OaMeetingmaterialList.size()>0){//获取每一个议程下面的附件
                //查询议程列表获取参与人员并去重
                for(OaMeetingmaterial materInfo:OaMeetingmaterialList){
                    String attendercodes=materInfo.getMeetingAttendeesCodes();
                    //定义一个新变量用于去重参会人员
                    String [] attendercode = attendercodes.split(",");
                    for(int i = 0; i < attendercode.length; i++){
                        attender.add(attendercode[i]);
                    }
                    //获取已上传的附件列表
                    Map<String,Object> filterMap = new HashMap<String,Object>();
                    filterMap.put("djId", materInfo.getDjId());
                    filterMap.put("nodeinstid", "0");
                    List<OptStuffInfo> stuffs = optStuffInfoManager.listObjects(filterMap);
                    //获取材料分发情况的组合标识 【参与人编码-初始附件编码】
                    String oldMark = "";
                    List<OaMeetingmaterialinfos> infos = oaMeetingmaterialinfosManager.findListByDjId(materInfo.getDjId());
                    if(infos!=null && !infos.isEmpty()){
                        for(OaMeetingmaterialinfos info : infos){
                            String temp=getUserRankByUsercode(info.getMeetingAttendee());
                            if("LD".equals(temp)){//领导
                                oldMark = oldMark+ ","+(info.getMeetingAttendee()+"-"+info.getInitalStuffId()); 
                            }
                        }
                        if(!StringUtils.isBlank(oldMark)){//非空
                            oldMark = oldMark.substring(1);
                        }
                    }
                   //页面传过来的分发情况的组合标识 【参与人编码-初始附件编码】
                    String newMark = "";
                    if(StringUtils.isNotBlank(addcodes)){
                        String [] usercodeArr = addcodes.substring(0).split(",");
                        if(stuffs!=null && !stuffs.isEmpty()){
                            for(String usercode : usercodeArr){
                                for(OptStuffInfo stuff : stuffs){
                                    newMark = newMark + "," + (usercode + "-" + stuff.getStuffid());
                                } 
                            }
                            newMark = newMark.substring(1);
                        }
                    }
                    addedMark = getNotExistItem(newMark, oldMark);//获取新增的
                    removedMark = getNotExistItem(oldMark, newMark);//删除的
                    //会议资料分发汇总删除
                    if(StringUtils.isNotBlank(removedMark)){
                        String[] removedMarkArr = removedMark.split(",");
                        for(String temRemovedMark:removedMarkArr){
                            String[]mark = temRemovedMark.split("-");
                            OaMeetingmaterialinfos info = oaMeetingmaterialinfosManager.findObjectBy(materInfo.getDjId(), mark[0], mark[1]);
                            oaMeetingmaterialinfosManager.deleteObject(info);
                            //删除磁盘上的文件
                            OptStuffInfo stuffInfo = optStuffInfoManager.getObjectById(info.getCid().getStuffId());
                            optStuffInfoManager.deleteObject(stuffInfo);
                            String absolutePath = SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
                            EfileManager.remove(absolutePath);
                        }
                    }
                   
                    if(StringUtils.isNotBlank(addedMark)){
                        String[] addedMarkArr = addedMark.split(",");
                        for(String temAddedMark:addedMarkArr){
                            String[]mark = temAddedMark.split("-");
                            //重新构造一个附件记录
                            OptStuffInfo stuffInfo = optStuffInfoManager.getObjectById(mark[1]);
                            OptStuffInfo newStuff = new OptStuffInfo();
                            newStuff.copyNotNullProperty(stuffInfo);
                            newStuff.setNodeInstId(null);
                            //获取附件id
                            String stuffId = GeneralOperatorAction.getUUID();
                            newStuff.setStuffid(stuffId);
                            
                            String filePath =  SysParametersUtils.getWorkflowAffixHome() + stuffInfo.getStuffpath();
                            String fileDir = (new File(filePath)).getParent();
                            //复制一份文件
                            String newFilePath = EfileManager.copyFileToDir(filePath, fileDir);
                            newStuff.setStuffpath(newFilePath.replace(SysParametersUtils.getWorkflowAffixHome(), ""));
                            optStuffInfoManager.saveObject(newStuff);
                            
                            //添加汇总关联
                            OaMeetingmaterialinfos infosbean = new OaMeetingmaterialinfos();
                            OaMeetingmaterialinfosId cidbean = new OaMeetingmaterialinfosId();
                            infosbean.setIsback("F");
                            infosbean.setCreatetime(DatetimeOpt.currentUtilDate()); 
                            infosbean.setInitalStuffId(mark[1]);
                            
                            cidbean.setDjId(materInfo.getDjId());
                            cidbean.setMeetingAttendee(mark[0]);
                            cidbean.setStuffId(stuffId);
                            
                            infosbean.setCid(cidbean);
                            oaMeetingmaterialinfosManager.saveObject(infosbean);
                        }
                    }
                }
                sendMsg(addcodes);//领导发送短信
                String addcode =   StringUtils.join(attender, ",");
                sendMsgattender(addcode);//参与人员发送短信
            }
          }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 获取在 newItems中不在oldItems中的项
     * @param newItems 以逗号分隔的
     * @param oldItems 以逗号分隔的
     * @return
     */
     private String getNotExistItem(String newItems,String oldItems){
         String result = "";
         if(StringUtils.isNotBlank(newItems) && oldItems!=null){
             String[] itemArray = newItems.split(",");
             for(String item : itemArray){
                 if((","+item+",").indexOf(","+oldItems+",")==-1){
                     result += ","+item;
                 }
             }
             if(result.length()>0){
                 result = result.substring(1);
             }
         }
         return result;
     }
     
     /**
      * 发送短信(会议资料)--领导
      * @param receptcode
      */
     public void sendMsg(String receptcode){
         
         String isSendMessage = request.getParameter("isSendMessage");
         if(StringUtils.isNotBlank(isSendMessage) && "T".equals(isSendMessage) && StringUtils.isNotBlank(receptcode)){
             
             
             for(String receptcode1:object.getAttendLeaderCode().split(",")){//发送领导                    
             String remindContent = "{username}，您好，请于{meetApplytime}，在{meetApplyAddress}地点，参加{title}会议";
             String s = CodeRepositoryUtil.getValue("sendMSgMod", "meetingapply");
             if(StringUtils.isNotBlank(s) && !"remind".equals(s)){
                 remindContent = s;
             }
             
             remindContent = StringUtils.replace(remindContent, "{username}", CodeRepositoryUtil.getValue("usercode", receptcode1));
             remindContent = StringUtils.replace(remindContent, "{meetApplytime}",DatetimeOpt.convertDateToString(new Date(),"yyyy年MM月dd日hh点mm分"));
             remindContent = StringUtils.replace(remindContent, "{meetApplyAddress}", object.getMeetApplyAddress());
             remindContent = StringUtils.replace(remindContent, "{title}", object.getMeetApplyName());
             oaSmssendManager.saveMsgs(receptcode1, getLoginUserCode(),remindContent,"HYZL");
             oaSmssendManager.executeSendMsg();
             }
         }
     }
     
     
     /**
      * 发送短信(会议资料)--参与人员
      * @param attender
      */
     public void sendMsgattender(String receptcode){
         
         String isSendMessage = request.getParameter("isSendMessage");
         if(StringUtils.isNotBlank(isSendMessage) && "T".equals(isSendMessage)){
             for(String receptcode2:receptcode.split(",")){//发送参与人员                 
             String remindContent = "{username}，您好，请于{meetApplytime}，在{meetApplyAddress}地点，参加{title}会议";
             String s = CodeRepositoryUtil.getValue("sendMSgMod", "meetingapply");
             if(StringUtils.isNotBlank(s) && !"remind".equals(s)){
                 remindContent = s;
             }
             
             remindContent = StringUtils.replace(remindContent, "{username}", CodeRepositoryUtil.getValue("usercode", receptcode2));
             remindContent = StringUtils.replace(remindContent, "{meetApplytime}",DatetimeOpt.convertDateToString(new Date(),"yyyy年MM月dd日hh点mm分"));
             remindContent = StringUtils.replace(remindContent, "{meetApplyAddress}", object.getMeetApplyAddress());
             remindContent = StringUtils.replace(remindContent, "{title}", object.getMeetApplyName());
             oaSmssendManager.saveMsgs(receptcode2, getLoginUserCode(),remindContent,"HYZL");
             oaSmssendManager.executeSendMsg();
             }
         }
     }
     
     /*
      * 当前登录人员usercode
      */
     @SuppressWarnings("unused")
     private String getLoginUserCode() {
         return ((FUserinfo) this.getLoginUser()).getUsercode();
     }

     
    public com.centit.oa.service.OaMeetingApplyManager getOaMeetingApplyManager() {
        return oaMeetingApplyManager;
    }

    public void setOaMeetingApplyManager(com.centit.oa.service.OaMeetingApplyManager basemgr) {
        oaMeetingApplyManager = basemgr;
        this.setBaseEntityManager(oaMeetingApplyManager);
    }
    
    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public OaMeetingmaterialManager getOaMeetingmaterialManager() {
        return oaMeetingmaterialManager;
    }

    public void setOaMeetingmaterialManager(
            OaMeetingmaterialManager oaMeetingmaterialManager) {
        this.oaMeetingmaterialManager = oaMeetingmaterialManager;
    }

    public OaMeetingmaterialinfosManager getOaMeetingmaterialinfosManager() {
        return oaMeetingmaterialinfosManager;
    }

    public void setOaMeetingmaterialinfosManager(
            OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager) {
        this.oaMeetingmaterialinfosManager = oaMeetingmaterialinfosManager;
    }

    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public List<OaMeetingApply> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<OaMeetingApply> meetingList) {
        this.meetingList = meetingList;
    }

    public List<OaMeetingmaterial> getOaMeetingmaterialList() {
        return oaMeetingmaterialList;
    }

    public void setOaMeetingmaterialList(
            List<OaMeetingmaterial> meetingmaterialList) {
        oaMeetingmaterialList = meetingmaterialList;
    }

    public List<OaMeetingmaterial> getListmeet() {
        return listmeet;
    }

    public void setListmeet(List<OaMeetingmaterial> listmeet) {
        this.listmeet = listmeet;
    }

    public List<OptStuffInfo> getStuffList() {
        return stuffList;
    }

    public void setStuffList(List<OptStuffInfo> stuffList) {
        this.stuffList = stuffList;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public void setListAllmeet(List<OaMeetingmaterial> listAllmeet) {
        this.listAllmeet = listAllmeet;
    }
    
    public List<OaMeetingmaterial> getListAllmeet() {
        return listAllmeet;
    }

    public OaSmssendManager getOaSmssendManager() {
        return oaSmssendManager;
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

  
    
}
