package com.centit.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.dao.CodeBook;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.efile.mgt.EfileManager;
import com.centit.oa.po.OaMeetingmaterial;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.po.OaMeetingmaterialinfosId;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.oa.service.OaMeetingmaterialManager;
import com.centit.oa.service.OaMeetingmaterialinfosManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.action.GeneralOperatorAction;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserFilterEngine;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class OaMeetingmaterialAction extends
        OACommonBizAction<OaMeetingmaterial> {
    private static final Log log = LogFactory
            .getLog(OaMeetingmaterialAction.class);
    private static final long serialVersionUID = 1L;
    private OaMeetingmaterialManager oaMeetingmaterialMag;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager;
    private OptStuffInfoManager optStuffInfoManager;
    private OaInformationAttachmentManager oaInformationAttachmentManager;
    private InputStream stuffStream;//供下载使用
    private String filename;
    private List<OaMeetingmaterial> OaMeetings;
    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public OaInformationAttachmentManager getOaInformationAttachmentManager() {
        return oaInformationAttachmentManager;
    }

    public void setOaInformationAttachmentManager(
            OaInformationAttachmentManager oaInformationAttachmentManager) {
        this.oaInformationAttachmentManager = oaInformationAttachmentManager;
    }

    public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }

    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }

    public OaMeetingmaterialinfosManager getOaMeetingmaterialinfosManager() {
        return oaMeetingmaterialinfosManager;
    }

    public void setOaMeetingmaterialinfosManager(
            OaMeetingmaterialinfosManager oaMeetingmaterialinfosManager) {
        this.oaMeetingmaterialinfosManager = oaMeetingmaterialinfosManager;
    }

    private String showTag;

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public void setOaMeetingmaterialManager(OaMeetingmaterialManager basemgr) {
        oaMeetingmaterialMag = basemgr;
        this.setBaseEntityManager(oaMeetingmaterialMag);
    }

    /**
     * 列表数据
     */
    public String list() {
        try {

            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);

            if ("F".equals(showTag)) {
                // isuse启用标识
                filterMap.put("isuse", "T");

            } else {
                filterMap.put("NP_isuse", true);// 默认查询启用，和无使用状态的资源信息
                if (("true").equals(filterMap.get("isBoth"))) {
                    filterMap.remove("NP_isuse");
                }
            }
            /*if(StringUtils.isNotBlank((String)filterMap.get("begTime")) || StringUtils.isNotBlank((String)filterMap.get("endTime"))){
                objList = oaMeetingmaterialMag.listOaMeetingmaterial(filterMap, pageDesc);
            }else{
                objList = oaMeetingmaterialMag.listObjects(filterMap, pageDesc);
            }*/
          //默认查询当前月份第一天到现在的记录
            /*if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("begTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay());
                filterMap.put("endTime", DatetimeOpt.convertDateToString(DatetimeOpt.addDays(new Date(),30),"yyyy-MM-dd"));
            }*/
            objList = oaMeetingmaterialMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            // initUsers();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    public String viewList(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);

        //默认查询当前月份第一天到现在的记录
        /*if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("begTime"))){
            filterMap.put("begTime",DateUtil.getCurrentMonthOfDay());
            filterMap.put("endTime", DatetimeOpt.convertDateToString(DatetimeOpt.addDays(new Date(),30),"yyyy-MM-dd"));
        }*/
        FUserDetail user = ((FUserDetail) getLoginUser());
        filterMap.put("meetingAttendeesCodes",user.getUsercode());
        
        objList = oaMeetingmaterialMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "viewList";
    }
    /**
     * 保存会议资料
     * 重点在附件和人员关联部分
     * 每一个参会人员有一条对应的附件记录存储，一条关联会议主表的记录
     * 
     * 本代码的重点在组合标识的运用，因为附件变化，和参与人变化都牵动到参与汇总表
     */
    public String save(){
        try {
            //待删除的汇总记录标识,标识组成为【参与人编码-初始附件编码】
            String removedMark = "";
            //带新增的汇总记录标识,标识组成为【参与人编码-初始附件编码】
            String addedMark = "";
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            
           //从数据库中检索是否存在
            OaMeetingmaterial oaMeetingmaterial = oaMeetingmaterialMag
                    .getObjectById(object.getDjId());
            if (oaMeetingmaterial != null) {//修改操作
                object.setMotifytime(DatetimeOpt.currentUtilDate());
            }else{//新增操作
                object.setCreatetime(DatetimeOpt.currentUtilDate());
                object.setCreater(user.getUsercode());
                object.setIsuse("F");
            }
            super.save();
            
           //获取已上传的附件列表
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("djId", object.getDjId());
            filterMap.put("nodeinstid", "0");
            List<OptStuffInfo> stuffs = optStuffInfoManager.listObjects(filterMap);
            
            //获取材料分发情况的组合标识 【参与人编码-初始附件编码】
            String oldMark = "";
            List<OaMeetingmaterialinfos> infos = oaMeetingmaterialinfosManager.findListByDjId(object.getDjId());
            if(infos!=null && !infos.isEmpty()){
                for(OaMeetingmaterialinfos info : infos){
                    oldMark = oldMark+ ","+(info.getMeetingAttendee()+"-"+info.getInitalStuffId());
                }
                oldMark = oldMark.substring(1);
            }
           //页面传过来的分发情况的组合标识 【参与人编码-初始附件编码】
            String newMark = "";
            if(StringUtils.isNotBlank(object.getMeetingAttendeesCodes())){
                String [] usercodeArr = object.getMeetingAttendeesCodes().split(",");
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
                    OaMeetingmaterialinfos info = oaMeetingmaterialinfosManager.findObjectBy(object.getDjId(), mark[0], mark[1]);
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
                    
                    cidbean.setDjId(object.getDjId());
                    cidbean.setMeetingAttendee(mark[0]);
                    cidbean.setStuffId(stuffId);
                    
                    infosbean.setCid(cidbean);
                    oaMeetingmaterialinfosManager.saveObject(infosbean);
                }
            }
          
            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 代会保存
     * @return
     */
    public String saveProxy(){
        try {
            //带新增的汇总记录标识,标识组成为【参与人编码-初始附件编码】
            String addedMark = "";
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
           //从数据库中检索是否存在
            OaMeetingmaterial oaMeetingmaterial = oaMeetingmaterialMag.getObjectById(object.getDjId());
            
            if(null ==oaMeetingmaterial){
                return viewList();
            }
            String addcodes = "";
            if(StringUtils.isNotBlank(object.getMeetingProxyCodes()) && StringUtils.isNotBlank(object.getMeetingProxys())){
                String meetattnames = oaMeetingmaterial.getMeetingAttendees();
                String meetattcodes = oaMeetingmaterial.getMeetingAttendeesCodes();
                String []codes = object.getMeetingProxyCodes().split(",");
                for(String code : codes){
                    if(!meetattcodes.contains(code)){
                        meetattcodes = meetattcodes + "," + code;
                        addcodes = addcodes +","+ code;
                    }
                }
                String []names = object.getMeetingProxys().split(",");
                for(String name : names){
                    if(!meetattnames.contains(name)){
                        meetattnames = meetattnames + "," + name;
                    }
                }
                oaMeetingmaterial.setMeetingAttendees(meetattnames);
                oaMeetingmaterial.setMeetingAttendeesCodes(meetattcodes);
            }
            super.save();
            
          //获取已上传的附件列表
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("djId", object.getDjId());
            filterMap.put("nodeinstid", "0");
            List<OptStuffInfo> stuffs = optStuffInfoManager.listObjects(filterMap);
            
            //获取材料分发情况的组合标识 【参与人编码-初始附件编码】
            String oldMark = "";
            List<OaMeetingmaterialinfos> infos = oaMeetingmaterialinfosManager.findListByDjId(object.getDjId());
            if(infos!=null && !infos.isEmpty()){
                for(OaMeetingmaterialinfos info : infos){
                    oldMark = oldMark+ ","+(info.getMeetingAttendee()+"-"+info.getInitalStuffId());
                }
                oldMark = oldMark.substring(1);
            }
           //页面传过来的分发情况的组合标识 【参与人编码-初始附件编码】
            String newMark = "";
            if(StringUtils.isNotBlank(addcodes)){
                String [] usercodeArr = addcodes.substring(1).split(",");
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
                    
                    cidbean.setDjId(object.getDjId());
                    cidbean.setMeetingAttendee(mark[0]);
                    cidbean.setStuffId(stuffId);
                    
                    infosbean.setCid(cidbean);
                    oaMeetingmaterialinfosManager.saveObject(infosbean);
                }
            }
          
            return this.viewList();
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
     * 禁用 isuse字段修改為F
     */
    public String unInUse() {
        object = oaMeetingmaterialMag.getObjectById(object.getDjId());
        object.setIsuse("F");
        oaMeetingmaterialMag.saveObject(object);
        return this.list();
    }

    /**
     * 啟用。
     */
    public String inUse() {
        object = oaMeetingmaterialMag.getObjectById(object.getDjId());
        object.setIsuse("T");
        oaMeetingmaterialMag.saveObject(object);
        return this.list();

    }

    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        initUsers();
        if (StringUtils.isBlank(object.getDjId())) {
            object.setDjId(oaMeetingmaterialMag.getDjId());
            super.edit();
            this.initparam("HYZL");// 通用配置信息
            return EDIT;
        } else {
            super.edit();
            this.initparam("HYZL");// 通用配置信息
            return "editplus";
        }

    }
    
    //查询关联议题
    public String queryMeet() {
        try{
            String id = request.getParameter("id");
            if(StringUtils.isNotBlank(id)){
                OaMeetings = new ArrayList<OaMeetingmaterial>();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("mId", id);
                map.put(CodeBook.SELF_ORDER_BY," orderId asc" );
                OaMeetings = oaMeetingmaterialMag.listObjects(map);
            }
            return "query";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 会议中新增议题
     */
    public String editNew() {
        try {
        
            initUsers();
            String mId = request.getParameter("mId");
            if (StringUtils.isBlank(object.getDjId())) {
                object.setDjId(oaMeetingmaterialMag.getDjId());
                super.edit();
                request.setAttribute("mId", mId);
                this.initparam("HYZL");// 通用配置信息
                return "editNew";
            } else {
                String djId =new String(object.getDjId().getBytes("ISO-8859-1"),"utf-8");
                this.initparam("HYZL");// 通用配置信息
                request.setAttribute("mId", mId);
                object.setDjId(djId);
                super.edit();
               // object = oaMeetingmaterialMag.getObjectById(djId);
                
                return "editYC";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 查看会议议程详情
     */
    public String view() {
        super.view();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("djId", object.getDjId());
        List<OptStuffInfo> docAttachments = optStuffInfoManager.getStuffInfoList(object.getDjId(),0L);
        
        request.setAttribute("docAttachments", docAttachments);

        //查询参会修改后的会议材料列表
        List<OaMeetingmaterialinfos> oaMeetingmaterialinfos=oaMeetingmaterialinfosManager.listObjects(filterMap);
        if(oaMeetingmaterialinfos!=null && oaMeetingmaterialinfos.size()>0){
            for(OaMeetingmaterialinfos bean:oaMeetingmaterialinfos){
                OptStuffInfo optStuffInfoBeg=optStuffInfoManager.getObjectById(bean.getInitalStuffId());
                OptStuffInfo optStuffInfoEnd=optStuffInfoManager.getObjectById(bean.getCid().getStuffId());
                bean.setOptStuffInfoBeg(optStuffInfoBeg);
                bean.setOptStuffInfoEnd(optStuffInfoEnd);
            }
        }
        request.setAttribute("oaMeetingmaterialinfos", oaMeetingmaterialinfos);
        return VIEW;
    }
    
    /**
     * 会议编辑页面查看议程
     */
    public String viewNew() {
        super.view();
        @SuppressWarnings("unchecked")
        String djId = request.getParameter("djId");
        
        List<OptStuffInfo> docAttachments = optStuffInfoManager.getStuffInfoList(djId,0L);
        request.setAttribute("docAttachments", docAttachments);
        
        object = oaMeetingmaterialMag.getObjectById(djId);
        return "viewNew";
    }
    
    /**
     * 查看详情-会议参与者查看入口
     */
    public String viewInfo() {
        super.view();
        initUsers();
        @SuppressWarnings("unchecked")
        
        Map<Object, Object> paramMap = request.getParameterMap();
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
        //查询原始会议材料列表
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("djId", object.getDjId());
        filterMap.put("MEETING_ATTENDEE",user.getUsercode());
        /*List<OptStuffInfo> docAttachments = optStuffInfoManager.getStuffInfoList(object.getDjId(),0L);        
        request.setAttribute("docAttachments", docAttachments);*/
        
        //查询参会修改后的会议材料列表
        List<OaMeetingmaterialinfos> oaMeetingmaterialinfos=oaMeetingmaterialinfosManager.listObjects(filterMap);
        if(oaMeetingmaterialinfos!=null && oaMeetingmaterialinfos.size()>0){
            for(OaMeetingmaterialinfos bean:oaMeetingmaterialinfos){
                OptStuffInfo optStuffInfoBeg=optStuffInfoManager.getObjectById(bean.getInitalStuffId());
                OptStuffInfo optStuffInfoEnd=optStuffInfoManager.getObjectById(bean.getCid().getStuffId());
                bean.setOptStuffInfoBeg(optStuffInfoBeg);
                bean.setOptStuffInfoEnd(optStuffInfoEnd);
            }
        }
        request.setAttribute("oaMeetingmaterialinfos", oaMeetingmaterialinfos);
        return "viewInfo";
    }
    
    /**
     * 批量删除功能
     * 
     * @return
     */
    public String delete() {
        String id = request.getParameter("djId");
        String meetingAttendee = request.getParameter("meetingAttendee");
        if (StringUtils.isNotEmpty(id)) {
         // 存放删除字段
            object.setDjId(id);
                  
           if (StringUtils.isNotEmpty(meetingAttendee)) {
                for (String username : meetingAttendee.split(",")) {
              
                    if (!username.isEmpty()) { 
                        List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosManager.findStuffIdByCode(username, object.getDjId());
                        if(stuffIdList.size()>0){
                            for(int i =0;i<stuffIdList.size();i++){
                                oaMeetingmaterialinfosManager.deleteObjectById(stuffIdList.get(i).getCid());
                            }   
                        }

                    }
                }
            }
            //删除关联材料

            optStuffInfoManager.deleteObjectBanInfo(id);
            oaMeetingmaterialMag.deleteObjectById(object.getDjId());
        }
        return this.list();
    }
    
    /**
     * 会议编辑删除议程
     * 
     * @return
     */
    public String deleteYC() {
        String id = request.getParameter("djId");
        OaMeetingmaterial oameet =null;
        if(StringUtils.isNotBlank(id)){
            oameet = oaMeetingmaterialMag.getObjectById(id);
        }
        if(oameet != null){
            
            if (StringUtils.isNotEmpty(id)) {
                // 存放删除字段
                object.setDjId(id);
                
                if (StringUtils.isNotEmpty(oameet.getMeetingAttendeesCodes())) {
                    for (String username : oameet.getMeetingAttendeesCodes().split(",")) {
                        
                        if (!username.isEmpty()) { 
                            List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosManager.findStuffIdByCode(username, object.getDjId());
                            if(stuffIdList.size()>0){
                                for(int i =0;i<stuffIdList.size();i++){
                                    oaMeetingmaterialinfosManager.deleteObjectById(stuffIdList.get(i).getCid());
                                }   
                            }
                            
                        }
                    }
                }
                //删除关联材料
                
                optStuffInfoManager.deleteObjectBanInfo(id);
                oaMeetingmaterialMag.deleteObjectById(object.getDjId());
            }
        }
        return "deleteYC";
    }
    
    /**
     * 批量删除功能
     * 
     * @return
     */
    public String deleteIds() {
        // 批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
            for (String djid : ids.split(",")) {
                // 存放作修改字段
                object.setDjId(djid);
                oaMeetingmaterialMag.deleteObject(object);
            }
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
     * 初始化登记页面材料上传控件
     * 
     * @param string
     */
    private void initparam(String modecode) {

        super.moduleCode = modecode;
        super.generalOpt();
    }
    
    //附件下载--服务器端下载
    public String downLocalStuffInfo() throws IOException {
        
        OptStuffInfo attachment = optStuffInfoManager.getObjectById(request.getParameter("no"));
        if (null == attachment) {
            return "download";
        }
        String absolutePath = SysParametersUtils.getInfosHome() + attachment.getStuffpath();
        File file = new File(absolutePath);
        
        
        InputStream stuffStream = FileUtils.openInputStream(file);
       
        try {
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(attachment.getStuffname().getBytes("GBK"), "ISO8859-1"));
        
        return "download";
    }
    //跳转页面至下载页
    public String meetingDownFile(){
        
        List<OptStuffInfo> docAttachments =  new ArrayList<OptStuffInfo>();
        String attendCode = request.getParameter("meetingAttendee");
        String djId = request.getParameter("djId");
        OptStuffInfo meetingStuff = new OptStuffInfo(); 
        List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosManager.findStuffIdByCode(attendCode, djId);
        if(stuffIdList.size()>0){
            for(int i =0;i<stuffIdList.size();i++){
                meetingStuff= optStuffInfoManager.getObjectById(stuffIdList.get(i).getCid().getStuffId());
                docAttachments.add(meetingStuff);
            }   
        }
        
        request.setAttribute("docAttachments", docAttachments);
        return "meetingDown";
    }

    public List<OaMeetingmaterial> getOaMeetings() {
        return OaMeetings;
    }

    public void setOaMeetings(List<OaMeetingmaterial> oaMeetings) {
        OaMeetings = oaMeetings;
    }
    
}
