package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;

import com.centit.app.po.ColorAndValue;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCommonType;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.service.OaCommonTypeManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * 会议室 TODO Class description should be added
 * 
 * @author lq
 * @create 2014-6-16
 * @version
 */

public class OaMeetinfoAction extends OACommonBizAction<OaMeetinfo>{
    private static final Log log = LogFactory.getLog(OaMeetinfoAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private OaMeetinfoManager oaMeetinfoMag;

    public void setOaMeetinfoManager(OaMeetinfoManager basemgr) {
        oaMeetinfoMag = basemgr;
        this.setBaseEntityManager(oaMeetinfoMag);
    }

    private OaCommonTypeManager oaCommonTypeManager;

    private List<OaMeetApply> oaMeetApplys;

    private List<FUnitinfo> units;
    
    private List<FUserinfo> users;

    private List<OaCommonType> oaCommonTypeList;

    private List<Map<String, String>> json;
    
    private String showTag; // 区别会议室列表与会议室安排列表页面操作显示
    
    private String hideReturnBtn = "F";//是否显示返回按钮
    
    private String curUrl;
    private OaMeetApplyManager oaMeetApplyManager;
    public List<Map<String, String>> getJson() {
        return json;
    }

    public void setJson(List<Map<String, String>> json) {
        this.json = json;
    }

    public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
        this.oaMeetApplyManager = oaMeetApplyManager;
    }

    /**
     * 附件材料文件，供上传使用
     */
    private File stuffFile;

    private String stuffFileFileName;

    private InputStream inputStream;

    public List<OaMeetApply> getNewOaMeetApplys() {
        return this.oaMeetApplys;
    }

    public void setNewOaMeetApplys(List<OaMeetApply> oaMeetApplys) {
        this.oaMeetApplys = oaMeetApplys;
    }

    
    /**
     * 会议申请时候 提供ajax调用，获取当前会议室的基本信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public String checkMeetinfo(){
        String result = "";
        try { 
           /* Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("djid", object.getDjid());
            List<OaMeetinfo> oaMeetinfolist = oaMeetinfoMag.listObjectsWithOutLOB(filterMap);*/
        OaMeetinfo oaMeetinfo= oaMeetinfoMag.getObjectByIdWithOutLOB(object.getDjid());
        if(oaMeetinfo==null){
            result="false";
        }else{
            result="基础配置："+ (StringUtils.isBlank(oaMeetinfo.getBasicConfiguration())?"":oaMeetinfo.getBasicConfiguration())+"  附属设备："+(StringUtils.isBlank(oaMeetinfo.getAccessoryEquipment())?"":oaMeetinfo.getAccessoryEquipment());
        }
        }catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }
        return null;
    }
    /**
     * 会议室列表，默认查询在用的会议室
     * 
     * @return
     */
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);

            
         // 区别会议室列表与会议室安排列表页面操作显示
            if("F".equals(showTag)){
             // isuse启用标识 ，默认查询在用会议室
              filterMap.put("isuse", "T");
                
            }else{
                filterMap.put("NP_isuse", true);//默认查询启用，和无使用状态的资源信息
                if (("true").equals(filterMap.get("isBoth"))) {
                    filterMap.remove("NP_isuse");
                }  
            }
         
            /*
             * 这边去除了equ中的获取树形某个父节点下所有资源类型 默认为会议室为平级存在
             */

            objList = oaMeetinfoMag.listObjects(filterMap, pageDesc);
            //getMeetTypeList();// 可选择会议室类型
           // showChart(objList);//20151124 用户暂时不需要展示此项进度条内容
            // 返回页面查询痕迹
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
     * 一段时间内的使用情况图形展示（当前月）
     * 
     * @param objList
     * @return
     */
    public String showChart(List<OaMeetinfo> objList) {
        Date beginTime = DatetimeOpt.truncateToMonth(DatetimeOpt
                .currentUtilDate());// 月初
        Date endTime = DatetimeOpt.addDays(
                DatetimeOpt.seekEndOfMonth(DatetimeOpt.currentUtilDate()), 1);// 月末
        Date currentTime = DatetimeOpt.currentUtilDate();
        double current = (Double.parseDouble(String.valueOf(currentTime
                .getTime() - beginTime.getTime())))
                / (Double.parseDouble(String.valueOf(endTime.getTime()
                        - beginTime.getTime()))) * 100;

        if (objList != null) {
            for (OaMeetinfo oaMeetinfo : objList) {// 遍历各固定资产
                oaMeetApplys = oaMeetApplyManager.getOaMeetinfoList(
                        beginTime, endTime, oaMeetinfo.getDjid());
                String color;
                double value;
                Date endTimeTemp = beginTime;// 存放上一条记录的endTime，起始时间
                List<ColorAndValue> chartList = new ArrayList<ColorAndValue>();
                if (oaMeetApplys != null) {
                    ColorAndValue colorAndValue = new ColorAndValue();
                    for (OaMeetApply oaMeetApply : oaMeetApplys) {
                        if (oaMeetApply.getEndTime() != null
                                && oaMeetApply.getEndTime().getTime() <= currentTime
                                        .getTime()) {
                            if (oaMeetApply.getEndTime().getTime() < beginTime
                                    .getTime()) {
                            }// 使用时间在申请时间之前且，使用时间不在统计范围内
                            else if (oaMeetApply.getBegTime().getTime() < beginTime
                                    .getTime()) {// 第一条记录开始时间小于统计时间，结束时间在统计范围内

                                // 空闲区域
                                color = "line free";
                                colorAndValue = new ColorAndValue();
                                colorAndValue.setColor(color);
                                colorAndValue.setValue(0.0);
                                colorAndValue.setBeginTime(endTimeTemp);
                                colorAndValue.setEndTime(endTimeTemp);
                                colorAndValue.setUsing("空闲");
                                chartList.add(colorAndValue);
                                endTimeTemp = beginTime;
                                value = (Double.parseDouble(String
                                        .valueOf(oaMeetApply.getEndTime()
                                                .getTime()
                                                - endTimeTemp.getTime())))
                                        / (Double.parseDouble(String
                                                .valueOf(endTime.getTime()
                                                        - beginTime.getTime())))
                                        * 100;// 百分比
                                // 在用
                                color = "line apply";
                                colorAndValue = new ColorAndValue();
                                colorAndValue.setColor(color);
                                colorAndValue.setValue(value);
                                colorAndValue.setBeginTime(endTimeTemp);
                                colorAndValue.setEndTime(oaMeetApply.getEndTime());
                                colorAndValue.setUsing("使用");
                                colorAndValue.setUser(oaMeetApply.getCreater());
                                chartList.add(colorAndValue);
                                endTimeTemp = oaMeetApply.getEndTime();

                            } else {
                                if (oaMeetApply.getBegTime().getTime() < endTimeTemp
                                        .getTime()) {// 判断使用的起始时间是在最后一段使用时间之前，还是之后。之前则空闲区取0，申请计算的起始时间从使用结束时间算起（页面添加申请验证）
                                    // 空闲区域
                                    color = "line free";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(0.0);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(endTimeTemp);
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getEndTime().getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 在用
                                    color = "line apply";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getEndTime());
                                    colorAndValue.setUsing("使用");
                                    colorAndValue.setUser(oaMeetApply.getCreater());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply.getEndTime();
                                } else if (oaMeetApply.getBegTime()
                                        .getTime() >= endTimeTemp.getTime()) {
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getBegTime().getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 空闲区域
                                    color = "line free";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getBegTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply.getBegTime();
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getEndTime().getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 在用
                                    color = "line apply";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getEndTime());
                                    colorAndValue.setUsing("使用");
                                    colorAndValue.setUser(oaMeetApply.getCreater());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply.getEndTime();
                                }
                            }
                        } else if (oaMeetApply.getIsUse() == null// 根据是否判断是否使用
                                && oaMeetApply.getPlanEndTime().getTime() >= currentTime
                                        .getTime()) {
                            if (oaMeetApply.getPlanEndTime().getTime() >= currentTime
                                    .getTime()) {// 排除今天之前只申请未使用的
                                if (oaMeetApply.getPlanBegTime().getTime() < endTimeTemp
                                        .getTime()) {// 判断申请的起始时间是在最后一段使用时间之前，还是之后。之前则空闲区取0，申请计算的起始时间从使用结束时间算起（页面添加申请验证）
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getPlanEndTime().getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 申请
                                    if (value > 100) {
                                        value = 100;
                                    }
                                    color = "line occupy";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getPlanEndTime());
                                    colorAndValue.setUsing("申请");
                                    colorAndValue.setUser(oaMeetApply.getCreater());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply
                                            .getPlanEndTime();
                                } else if (oaMeetApply.getPlanEndTime()
                                        .getTime() <= endTime.getTime()) {
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getPlanBegTime()
                                                    .getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 空闲区域
                                    color = "line free";

                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getPlanBegTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply
                                            .getPlanBegTime();
                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getPlanEndTime().getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 申请
                                    color = "line occupy";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getPlanEndTime());
                                    colorAndValue.setUsing("申请");
                                    colorAndValue.setUser(oaMeetApply.getCreater());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply
                                            .getPlanEndTime();

                                } else if (oaMeetApply.getPlanEndTime()
                                        .getTime() > endTime.getTime()) {// 最后一条记录结束时间在统计范围外

                                    value = (Double.parseDouble(String
                                            .valueOf(oaMeetApply
                                                    .getPlanBegTime()
                                                    .getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 空闲区域
                                    color = "line free";

                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(oaMeetApply
                                            .getPlanBegTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply
                                            .getPlanBegTime();

                                    value = (Double.parseDouble(String
                                            .valueOf(endTime.getTime()
                                                    - endTimeTemp.getTime())))
                                            / (Double
                                                    .parseDouble(String.valueOf(endTime
                                                            .getTime()
                                                            - beginTime
                                                                    .getTime())))
                                            * 100;// 百分比
                                    // 申请
                                    color = "line occupy";
                                    colorAndValue = new ColorAndValue();
                                    colorAndValue.setColor(color);
                                    colorAndValue.setValue(value);
                                    colorAndValue.setBeginTime(endTimeTemp);
                                    colorAndValue.setEndTime(endTime);
                                    colorAndValue.setUsing("申请");
                                    colorAndValue.setUser(oaMeetApply.getCreater());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = oaMeetApply
                                            .getPlanEndTime();
                                }
                            }
                        }
                    }

                    if (endTimeTemp.getTime() <= endTime.getTime()) { // 最后空闲区域
                        value = (Double.parseDouble(String.valueOf(endTime
                                .getTime() - endTimeTemp.getTime())))
                                / (Double.parseDouble(String.valueOf(endTime
                                        .getTime() - beginTime.getTime())))
                                * 100;// 百分比
                        color = "line free";
                        colorAndValue = new ColorAndValue();
                        colorAndValue.setColor(color);
                        colorAndValue.setValue(value);
                        colorAndValue.setBeginTime(endTimeTemp);
                        colorAndValue.setEndTime(endTime);
                        colorAndValue.setUsing("空闲");
                        chartList.add(colorAndValue);

                    }
                    value = current;
                    colorAndValue = new ColorAndValue();
                    colorAndValue.setValue(value);
                    chartList.add(colorAndValue);

                    oaMeetinfo.setChartList(chartList);
                }
            }
        }
        return "";
    }
    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            if (StringUtils.isBlank(object.getDjid())) {// 录入人员,录入时间
                                                        // 在新增是加入，且维护时不修改
//                object.setIsuse("T");
                object.setCreater(user.getUsercode());
                object.setCreatetime(DatetimeOpt.currentUtilDate());
            }
            // 读取上传文件（），存在MeetinfPicture字段
            if (stuffFile != null) {
                byte[] bbuf = null;

                FileInputStream fis = new FileInputStream(stuffFile);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setMeetinfPicture(bbuf);
                object.setMeetinfPictureName(stuffFileFileName);
            }
             super.save();
//             oaMeetinfoMag.saveObject(object);
             
             return SUCCESS;
//            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 禁用 isuse字段修改為F
     */
    public String unInUse() {
        object = oaMeetinfoMag.getObjectById(object.getDjid());
        object.setIsuse("F");
        oaMeetinfoMag.saveObject(object);
        return this.list();
    }

    /**
     * 啟用。
     */
    public String inUse() {
        object = oaMeetinfoMag.getObjectById(object.getDjid());
        object.setIsuse("T");
        oaMeetinfoMag.saveObject(object);
        return this.list();

    }

    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        // 获取全部在用的部门
        units = CodeRepositoryUtil.getAllUnits("T");

        getMeetTypeList();// 可选择会议室类型
        super.edit();
       
        
        if(StringUtils.isNotBlank(object.getResponsibleDep())){
            //获取人员列表
            users = CodeRepositoryUtil
                    .getSortedUnitUsers(object.getResponsibleDep());
        }
      
        return EDIT;
    }
    /**
     * 会议室信息查看
     */
    @Override
    public String view(){
        super.view();
        Map<String, Object> filterMapHysLX = new HashMap<String, Object>();
        filterMapHysLX.put("state", "T");
        filterMapHysLX.put("no", object.getMeetingType());
        filterMapHysLX.put("publicType", "meeting");
        oaCommonTypeList = oaCommonTypeManager.listObjects(filterMapHysLX);
        return VIEW;
    }
  
    /**
     * 获取会议室类型列表
     */
    public void getMeetTypeList() {

        // 可选择会议室类型
        Map<String, Object> filterMapHysLX = new HashMap<String, Object>();
        filterMapHysLX.put("state", "T");
        filterMapHysLX.put("publicType", "meeting");
        oaCommonTypeList = oaCommonTypeManager.listObjects(filterMapHysLX);
    }

    /**
     * 根据部门编号获取人员列表 级联下拉框(未做完)
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
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String getUserImgFromByte() throws Exception {
        OaMeetinfo info = baseEntityManager.getObjectById(object.getDjid());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            // 输出图片
            out.write(info.getMeetinfPicture());
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

    /**
     * 下载文件，未用到
     * 
     * @return
     * @throws IOException
     */
    public String downStuffInfo() throws IOException {
        OaMeetinfo info = baseEntityManager.getObjectById(object.getDjid());
        if (null == info) {

            return "download";
        }
        String fn = info.getMeetinfPictureName();
        try {

            if (info.getMeetinfPicture() != null) {
                inputStream = new ByteArrayInputStream(info.getMeetinfPicture());
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";

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
//        object = oaMeetinfoMag.getObject(object);
        // 用于初始化查看页面默认显示
        curUrl="/oa/oaMeetApply!calendarView.do?show_type="+showTag;
        jspInfo = new OptJspInfo();
        jspInfo.setTitle("会议室办理查看");
        jspInfo.setFrameList(frameList);

        return "meetingView";
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
        stuffsFrameInfo.setFrameSrc("/oa/oaMeetinfo!getAllCaseView.do?djId="
                + djid );
        stuffsFrameInfo.setFrameHeight("1300px");
        return stuffsFrameInfo;
    }

    /**
     * 用于展示查看详细信息Lab标签内容
     * 
     * @return
     */
    
    public String getAllCaseView() {
       
        object = oaMeetinfoMag.getObject(object);
        // 用于初始化查看页面默认显示
        curUrl="/oa/oaMeetApply!calendarView.do?meetingNo="
                + object.getDjid();
//        request.setAttribute("nodeId", nodeId);
        return "meetingView";
    }

    /**
     *会议室选择页面
     * @return
     */
    public String selectList(){
        this.list();
        return "selectList";
        
    }
    
    

    public String delete() {
        oaMeetinfoMag.deleteObject(object);
        return this.list();
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
            oaMeetinfoMag.deleteObject(object);    
         }
        }
        return this.list();
    }
    
    
    /**
     * 初始化登记页面材料上传控件
     * @param string
     */
    private void initparam(String modecode) {
        // TODO Auto-generated method stub
        moduleParam=generalModuleParamManager.getObjectById(modecode);
    }
    
    public List<FUnitinfo> getUnits() {
        return units;
    }

    public void setUnits(List<FUnitinfo> units) {
        this.units = units;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStuffFileFileName() {
        return stuffFileFileName;
    }

    public void setStuffFileFileName(String stuffFileFileName) {
        this.stuffFileFileName = stuffFileFileName;
    }

    public File getStuffFile() {
        return stuffFile;
    }

    public void setStuffFile(File stuffFile) {
        this.stuffFile = stuffFile;
    }

    public OaCommonTypeManager getOaCommonTypeManager() {
        return oaCommonTypeManager;
    }

    public void setOaCommonTypeManager(OaCommonTypeManager oaCommonTypeManager) {
        this.oaCommonTypeManager = oaCommonTypeManager;
    }

    public List<OaCommonType> getOaCommonTypeList() {
        return oaCommonTypeList;
    }

    public void setOaCommonTypeList(List<OaCommonType> oaCommonTypeList) {
        this.oaCommonTypeList = oaCommonTypeList;
    }
    public List<FUserinfo> getUsers() {
        return users;
    }

    public void setUsers(List<FUserinfo> users) {
        this.users = users;
    }

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    public List<OaMeetApply> getOaMeetApplys() {
        return oaMeetApplys;
    }

    public void setOaMeetApplys(List<OaMeetApply> oaMeetApplys) {
        this.oaMeetApplys = oaMeetApplys;
    }

    public String getHideReturnBtn() {
        return hideReturnBtn;
    }

    public void setHideReturnBtn(String hideReturnBtn) {
        this.hideReturnBtn = hideReturnBtn;
    }
    
    
}
