package com.centit.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaCarinfo;
import com.centit.oa.po.OaCommonType;
import com.centit.oa.po.OaDriverInfo;
import com.centit.oa.service.OaCarinfoManager;
import com.centit.oa.service.OaCommonTypeManager;
import com.centit.oa.service.OaDriverInfoManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

public class OaCarinfoAction extends BaseEntityDwzAction<OaCarinfo> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaCarinfoAction.class);
    private static final long serialVersionUID = 1L;
    private OaCarinfoManager oaCarinfoMag;
    private OaCommonTypeManager oaCommonTypeManager;
    private OaDriverInfoManager oaDriverInfoManager;
    
    
    
    private File uploadFile_;
    private String uploadFile_FileName;
    

    private List<OaCommonType> oaCommonTypeList;
    private List<OaDriverInfo> oaDriverInfoList;

     private List<Map<String, String>> json;
    
     private List<FUnitinfo> units;
     
     private List<FUserinfo> users;
     
     private String curUrl;

     public String getCurUrl() {
        return curUrl;
    }
    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    private String showTag; // 区别会议室列表与会议室安排列表页面操作显示

    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            if (StringUtils.isBlank(object.getDjid())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatetime(DatetimeOpt.currentUtilDate());
            }
            object.setMotifytime(DatetimeOpt.currentUtilDate());
            // 读取上传文件（），存在CarPicture字段
            if (uploadFile_ != null) {
                byte[] bbuf = null;

                FileInputStream fis = new FileInputStream(uploadFile_);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setCarPicture(bbuf);
                object.setCarPictureName(uploadFile_FileName);
            }
            return super.save();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String getUserImgFromByte() throws Exception {
        OaCarinfo info = baseEntityManager.getObjectById(object.getDjid());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            // 输出图片
            out.write(info.getCarPicture());
            out.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (null != out)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        try {
      
            Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
            if(filterMap.get("rangeType")==null){
                filterMap.put("rangeType", "N");
                filterMap.put("s_rangeType", "N");
            }
            if("N".equals(filterMap.get("rangeType").toString())){
         // 区别车辆列表与车辆安排列表页面操作显示
            if("F".equals(showTag)){
             // isuse启用标识 ，默认查询在用车辆
              filterMap.put("isuse", "T");
                
            }else{
                filterMap.put("NP_isuse", true);//默认查询启用，和无使用状态的车辆信息
                if (("true").equals(filterMap.get("isBoth"))) {
                    filterMap.remove("NP_isuse");
                }  
            }
            }
            
           // getCarTypeList();// 可选择汽车类型
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
            objList = baseEntityManager.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            //return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     *车辆选择页面
     * @return
     */
    public String selectList(){
        showTag="F";
        this.list();
        return "selectList";
        
    }
    /**
     *外租车车辆选择页面
     * @return
     */
    public String outselectList(){
        try {
            
            Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
             // isuse启用标识 ，默认查询在用车辆
              filterMap.put("rangeType", "W");  
           // getCarTypeList();// 可选择汽车类型
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
            objList = baseEntityManager.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            return "outselectList";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        
        
    }
    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        // 获取全部在用的部门
        units = CodeRepositoryUtil.getAllUnits("T");

        //getCarTypeList();// 可选择车辆类型
        
        getDriverList();// 司机列表
        super.edit();
       
        
        if(StringUtils.isNotBlank(object.getResponsibleDep())){
            //获取人员列表
            users = CodeRepositoryUtil
                    .getSortedUnitUsers(object.getResponsibleDep());
        }
      
        return EDIT;
    }

    
    /**
     * 获取会议室类型列表
     */
    public void getCarTypeList() {

        // 可选择会议室类型
        Map<String, Object> filterMapCL= new HashMap<String, Object>();
        filterMapCL.put("state", "T");
        filterMapCL.put("publicType", "cars");
        oaCommonTypeList=oaCommonTypeManager.listObjects(filterMapCL);
    }
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String djid : ids.split(",")) {
            //存放作修改字段
            object.setDjid(djid);
            super.delete();    
         }
        }
        return this.list();
    } 
    
    /**
     * 获取司机列表
     */
    public void getDriverList() {
        // 司机列表
        Map<String, Object> filterMapSJ= new HashMap<String, Object>();
        filterMapSJ.put("state", "T");
        oaDriverInfoList=oaDriverInfoManager.listObjects(filterMapSJ);
    }
    
    /**
     * 根据部门编号获取人员列表 级联下拉框
     * 
     * @param unitcode
     *            type='UN'获取部门 type='US'获取人员
     * @return
     */
    public String option() {

        String unitcode = request.getParameter("unitcode");
        String type = request.getParameter("type");
        json = new ArrayList<Map<String, String>>();
        if (StringUtils.isBlank(unitcode)) {

            return "options";
        }

        Map<String, String> temp = new HashMap<String, String>();
        temp.put("key", "");
        temp.put("value", "--请选择--");
        json.add(temp);
        if ("UN".equals(type)) {
            List<FUserinfo> users = CodeRepositoryUtil
                    .getSortedUnitUsers(unitcode);

            for (FUserinfo data : users) {

                Map<String, String> op = new HashMap<String, String>();

                op.put("key", data.getUsercode());
                op.put("value", data.getUsername());

                json.add(op);

            }
        } else if ("US".equals(type)) {
            List<FUserinfo> users = CodeRepositoryUtil
                    .getSortedUnitUsers(unitcode);

            for (FUserinfo data : users) {

                Map<String, String> op = new HashMap<String, String>();

                op.put("key", data.getUsercode());
                op.put("value", data.getUsername());

                json.add(op);

            }
        }

        return "options";

    }
    /**
     * 通用业务框架属性会议室信息查看
     */
    private OptJspInfo jspInfo;
    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }
    
    public String generalOptView() {
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();
        // 查看办件业务数据信息
      //  frameList.add(getBizDataViewFrame(object.getDjId()));
        // 用于展示查看详细信息Lab标签内容
//        frameList.add(this.getAllViewFrame(object.getDjid()));
//        object = oaCarinfoMag.getObject(object);
        // 用于初始化查看页面默认显示
        curUrl="/oa/oaCarApply!calendarView.do";
        jspInfo = new OptJspInfo();
        jspInfo.setTitle("用车申请信息");
        jspInfo.setFrameList(frameList);

        return "carView";
    }
    public List<OaCommonType> getOaCommonTypeList() {
        return oaCommonTypeList;
    }

    public void setOaCommonTypeList(List<OaCommonType> oaCommonTypeList) {
        this.oaCommonTypeList = oaCommonTypeList;
    }
    
    public void setOaCarinfoManager(OaCarinfoManager basemgr) {
        oaCarinfoMag = basemgr;
        this.setBaseEntityManager(oaCarinfoMag);
    }

    private List<OaCarApply> oaCarApplys;

    public List<OaCarApply> getNewOaCarApplys() {
        return this.oaCarApplys;
    }

    public void setNewOaCarApplys(List<OaCarApply> oaCarApplys) {
        this.oaCarApplys = oaCarApplys;
    }
    
    public OaCommonTypeManager getOaCommonTypeManager() {
        return oaCommonTypeManager;
    }

    public void setOaCommonTypeManager(OaCommonTypeManager oaCommonTypeManager) {
        this.oaCommonTypeManager = oaCommonTypeManager;
    }
    
    public List<Map<String, String>> getJson() {
        return json;
    }

    public void setJson(List<Map<String, String>> json) {
        this.json = json;
    }
    
    public List<FUnitinfo> getUnits() {
        return units;
    }

    public void setUnits(List<FUnitinfo> units) {
        this.units = units;
    }
    
    public List<FUserinfo> getUsers() {
        return users;
    }

    public void setUsers(List<FUserinfo> users) {
        this.users = users;
    }
    
    public File getUploadFile_() {
        return uploadFile_;
    }

    public void setUploadFile_(File uploadFile_) {
        this.uploadFile_ = uploadFile_;
    }

    public String getUploadFile_FileName() {
        return uploadFile_FileName;
    }

    public void setUploadFile_FileName(String uploadFile_FileName) {
        this.uploadFile_FileName = uploadFile_FileName;
    }
    public OaDriverInfoManager getOaDriverInfoManager() {
        return oaDriverInfoManager;
    }
    public void setOaDriverInfoManager(OaDriverInfoManager oaDriverInfoManager) {
        this.oaDriverInfoManager = oaDriverInfoManager;
    }
    public List<OaDriverInfo> getOaDriverInfoList() {
        return oaDriverInfoList;
    }
    public void setOaDriverInfoList(List<OaDriverInfo> oaDriverInfoList) {
        this.oaDriverInfoList = oaDriverInfoList;
    }
    public String getShowTag() {
        return showTag;
    }
    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

}
