package com.centit.oa.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.app.po.ColorAndValue;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.EquipmentInfo;
import com.centit.oa.po.EquipmentUsing;
import com.centit.oa.service.EquipmentInfoManager;
import com.centit.oa.service.EquipmentUsingManager;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.ExportExcelUtil;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FDatadictionaryId;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.CodeRepositoryUtilExtend;
import com.centit.sys.service.DictionaryManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;

public class EquipmentInfoAction extends BaseEntityDwzAction<EquipmentInfo> {
    private static final Log log = LogFactory.getLog(EquipmentInfoAction.class);
    private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl
            .getSysOptLog("DICTSET");
    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private static final String equ = "equipment";

    private EquipmentInfoManager equipmentInfoMag;
    private EquipmentUsingManager equipmentUsingMag;
    private DictionaryManager dictManger;
    private CodeRepositoryManager codeRepo;

    private String[] fdesc;
    private String reportDatas;
    private String s_supEquipmentType;
    private String s_equipmentType;
    private List<Map<String, String>> options;
    private List<FDatadictionary> dictDetails;

    private FDatadictionaryId id;
    private FDatadictionary datadictionary;
    private FDatacatalog catalog;

    private Object json;

    private List<FUserinfo> userinfos;
    
    private List<Map<String, String>> jsonDatas;

    public List<Map<String, String>> getJsonDatas() {
        return jsonDatas;
    }

    public void setJsonDatas(List<Map<String, String>> jsonDatas) {
        this.jsonDatas = jsonDatas;
    }

    public List<FUserinfo> getUserinfos() {
        return userinfos;
    }

    public void setUserinfos(List<FUserinfo> userinfos) {
        this.userinfos = userinfos;
    }

    public FDatacatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(FDatacatalog catalog) {
        this.catalog = catalog;
    }

    public FDatadictionary getDatadictionary() {
        return datadictionary;
    }

    public void setDatadictionary(FDatadictionary datadictionary) {
        this.datadictionary = datadictionary;
    }

    public FDatadictionaryId getId() {
        return id;
    }

    public void setId(FDatadictionaryId id) {
        this.id = id;
    }

    public List<FDatadictionary> getDictDetails() {
        return dictDetails;
    }

    public void setDictDetails(List<FDatadictionary> dictDetails) {
        this.dictDetails = dictDetails;
    }

    public DictionaryManager getDictManger() {
        return dictManger;
    }

    public void setDictManger(DictionaryManager dictManger) {
        this.dictManger = dictManger;
    }

    public List<Map<String, String>> getOptions() {
        return options;
    }

    public void setOptions(List<Map<String, String>> options) {
        this.options = options;
    }

    public String getReportDatas() {
        return reportDatas;
    }

    public void setReportDatas(String reportDatas) {
        this.reportDatas = reportDatas;
    }

    public void setEquipmentUsingManager(EquipmentUsingManager equipmentUsingMag) {
        this.equipmentUsingMag = equipmentUsingMag;
    }

    public void setEquipmentInfoManager(EquipmentInfoManager basemgr) {
        equipmentInfoMag = basemgr;
        this.setBaseEntityManager(equipmentInfoMag);
    }

    public void setCodeRepoManager(CodeRepositoryManager crm) {
        this.codeRepo = crm;
    }

    public String getS_supEquipmentType() {
        return s_supEquipmentType;
    }

    public void setS_supEquipmentType(String s_supEquipmentType) {
        this.s_supEquipmentType = s_supEquipmentType;
    }

    public String getS_equipmentType() {
        return s_equipmentType;
    }

    public void setS_equipmentType(String s_equipmentType) {
        this.s_equipmentType = s_equipmentType;
    }

    private List<EquipmentUsing> equipmentUsings;

    public List<EquipmentUsing> getNewEquipmentUsings() {
        return this.equipmentUsings;
    }

    public void setNewEquipmentUsings(List<EquipmentUsing> equipmentUsings) {
        this.equipmentUsings = equipmentUsings;
    }

    /**
     * 固定资产列表，默认查询在用的固定资产
     * 
     * @return
     */
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
            filterMap.put("equipmentState", "T");
            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("equipmentState");
            }
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
            String extraCode = null;
            // s_equipmentType用来标识资源种类：会议室，车辆，资源
            if (StringUtils.isEmpty(s_equipmentType)) {
                extraCode = s_supEquipmentType;
            } else {
                extraCode = s_equipmentType;
            }
            String shql = "";
            StringBuffer types = new StringBuffer();
            if (!StringUtils.isEmpty(extraCode)) {
                List<FDatadictionary> l = CodeRepositoryUtilExtend
                        .getTreeDictionaryStartBy(equ, extraCode, true);
                for (FDatadictionary f : l) {
                    types.append(", '" + f.getDatacode() + "'");
                }
                if (!StringUtils.isBlank(types.toString())) {
                    String str = types.substring(1, types.length());
                    shql = "  From EquipmentInfo where equipmentType in( "
                            + str + ")  ";
                }
            }
            putTree(equ);
            objList = baseEntityManager.listObjects(shql, filterMap, pageDesc);
            showChart(objList);
            setbackSearchColumn(filterMap);
            jsonDate((String)filterMap.get("s_applicant"));
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 资产类别维护
     * 
     * @return
     */
    public String equipmentInfoType() {
        try {
            putTree(equ);
            return "equipmentInfoType";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 弹出在用资产页面
     * 
     * @return
     */
    public String listSmall() {

        list();
        return "listSmall";
    }

    public String applicantList() {

        list();
        return "applicantList";
    }

    private static String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    }

    @SuppressWarnings("unchecked")
    public String frameList() {
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());
        super.list();
        showChart(objList);
        setbackSearchColumn(filterMap);
        return "frameList";
    }

    /**
     * 资产信息及使用详情列表
     * 
     * @return
     */
    public String detailList() {
        PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
//        EquipmentInfo info = new EquipmentInfo();
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());

        EquipmentInfo info = baseEntityManager.getObject(object);
        filterMap.put("equipmentId", object.getEquipmentId());
        if (object.getEquipmentId() != null) {
            equipmentUsings = equipmentUsingMag
                    .listObjects(filterMap, pageDesc);
        }
        request.setAttribute("info", info);
        request.setAttribute("equipmentUsings", equipmentUsings);
        setbackSearchColumn(filterMap);
        putTree(equ);
        jsonDate((String)filterMap.get("applicant"));
        totalRows = pageDesc.getTotalRows();
        return "detailList";
    }

    public String applicantDetailList() {
        detailList();
        return "applicantDetailList";
    }

    public String save() {
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息\
        if (object.getEquipmentId() == null) {// 录入人员,录入时间 在新增是加入，且维护时不修改
            object.setEquipmentState("T");
            object.setRecorder(user.getUsercode());
            object.setRecordeDate(DatetimeOpt.currentUtilDate());
        }

        return super.save();
    }

    public String edit() {
        // List<FDatadictionary> subUnits = CodeRepositoryUtilExtend
        // .getTreeDictionaryStartBy(equ, s_supEquipmentType, true);
        putTree(equ);

       super.edit();
       jsonDate(object.getEquipmentCharge());
        
        return EDIT;
    }

    public void jsonDate(String usercode){
        
        JSONArray ja = new JSONArray();
        jsonDatas = new ArrayList<Map<String, String>>();
        if (StringUtils.isNotEmpty(usercode)) {
        String[] usercodes = usercode.split(",");
        for (String s : usercodes) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("id", s);
        m.put("showName",CodeRepositoryUtil.getValue("usercode",s));
        jsonDatas.add(m);
        ja.addAll(jsonDatas);
            }
        }
       request.setAttribute("populate", ja.toString()); 
    }
    
    public String view() {

        putTree(equ);

        return super.view();
    }

    public String delete() {
        deletedMessage();
        return super.delete();
    }

    /**
     * 注销编辑
     * 
     * @return
     */

    public String discardEdit() {
        super.edit();
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
        object.setDiscardDate(DatetimeOpt.currentUtilDate());// 注销时间 系统时间
        object.setDiscardOperator(user.getUsercode());// 注销经办人 当前人员
        putTree(equ);
        return "discardEdit";
    }

    /**
     * 固定资产类别列表字典项编辑
     * 
     * @return
     */

    public String dictionaryEdit() {

        try {
            String catalogCode = request.getParameter("catalogcode");
            String dataCode = request.getParameter("datacode");
            String extracode = request.getParameter("extracode");
            id = new FDatadictionaryId();

            if (StringUtils.isNotBlank(catalogCode)) {
                id.setCatalogcode(catalogCode);
            }
            if (StringUtils.isNotBlank(dataCode)) {
                id.setDatacode(dataCode);
            }

            datadictionary = dictManger.findById(id);
            if (datadictionary == null) {
                datadictionary = new FDatadictionary();
                datadictionary.setId(id);
                datadictionary.setExtracode(extracode);// 这边为添加下级菜单
            }
            putTree(equ);
            catalog = dictManger.getObjectById(id.getCatalogcode());
            fdesc = dictManger.getFieldsDesc(catalog.getFielddesc(),
                    catalog.getCatalogtype());

            // 编辑时的下拉框
            if (datadictionary.getDatacode() != null) {
                List<FDatadictionary> unitinfos = unitNames(extracode,
                        datadictionary.getCatalogcode(),
                        datadictionary.getDatacode());

                ServletActionContext.getContext().put("unitNames", unitinfos);
                ServletActionContext.getContext().put("unitNamesJson",
                        JSONArray.fromObject(unitinfos).toString());
            }
            return "dictionaryEdit";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public List<FDatadictionary> unitNames(String extraCode,
            String catalogCode, String datacode) {

        List<FDatadictionary> allUnits = CodeRepositoryUtilExtend
                .getTreeDictionaryStartBy(catalogCode, extraCode, true);

        List<FDatadictionary> subUnits = CodeRepositoryUtilExtend
                .getTreeDictionaryStartBy(catalogCode, datacode, true);
        List<FDatadictionary> units = new ArrayList<FDatadictionary>();

        for (FDatadictionary obj : allUnits) {

            // 新增时列出所有的机构名,编辑时只添加除本身及其下属的机构
            if (!datacode.equals(obj.getDatacode()) && !subUnits.contains(obj)) {
                units.add(obj);
            }
        }
        return units;
    }

    /**
     * 固定资产类别列表字典项编辑保存
     * 
     * @return
     */
    public String dictionarySave() {
        try {
            String optContent = null;
            String oldValue = null;

            // 页面验证datadictionary.getId()..getDatacode() name 改为datacode
            String dataCode = request.getParameter("datacode");
            if (StringUtils.isNotBlank(dataCode)) {
                datadictionary.setDatacode(dataCode);
            }
            FDatadictionary desobj = dictManger
                    .findById(datadictionary.getId());

            if (desobj != null) {
                oldValue = desobj.display();

                desobj.copyNotNullProperty(datadictionary);
                datadictionary = desobj;
            } else {
                datadictionary.setDatatag("T");
                datadictionary.setDatastyle("U");
            }

            dictManger.saveCditms(datadictionary);
            savedMessage();

            // 刷新缓存中的字典
            codeRepo.refreshAll();

            optContent = datadictionary.display();

            SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(),
                    datadictionary.getId().toString(), optContent, oldValue);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }

        return this.equipmentInfoType();

    }

    /**
     * 验证数据字典主键唯一性
     * 
     * @throws IOException
     */
    public void isExist() throws IOException {

        String dataCode = request.getParameter("datacode");
        id = new FDatadictionaryId();

        id.setCatalogcode(equ);
        if (StringUtils.isNotBlank(dataCode)) {
            id.setDatacode(dataCode);
        }

        datadictionary = dictManger.findById(id);
        if (datadictionary != null) {
            ServletActionContext.getResponse().getWriter().print(false);
        } else {
            ServletActionContext.getResponse().getWriter().print(true);
        }

    }

    /**
     * 验证数据字典主键唯一性
     * 
     * @throws IOException
     */
    public String isDExist() throws IOException {

        String dataCode = request.getParameter("datacode");
        id = new FDatadictionaryId();

        id.setCatalogcode(equ);
        if (StringUtils.isNotBlank(dataCode)) {
            id.setDatacode(dataCode);
        }

        datadictionary = dictManger.findById(id);
        if (datadictionary != null) {
            json = dataCode + "数据代码已存在。";
            return "json";
        }

        json = true;

        return "json";
    }

    /**
     * 固定资产类别列表字典项编删除
     * 
     * @return
     */
    public String dictionaryDelete() {

        try {
            String catalogCode = request.getParameter("catalogcode");
            String dataCode = request.getParameter("datacode");
            id = new FDatadictionaryId();
            if (StringUtils.isNotBlank(catalogCode)) {
                id.setCatalogcode(catalogCode);
            }
            if (StringUtils.isNotBlank(dataCode)) {
                id.setDatacode(dataCode);
            }
            FDatadictionary datadictionary = dictManger.findById(id);
             equipmentInfoMag.delAndUpdateDic(id);

            setS_equipmentType(datadictionary.getExtracode());// 更换页面选中项
//            //删除操作
//            dictManger.deleteCditms(id);
            FDatacatalog dbobject = dictManger.getObjectById(id
                    .getCatalogcode());

            if (dbobject == null) {
                saveError("entity.missing");
            }

         // 刷新缓存中的字典
            codeRepo.refreshAll();
            
            SYS_OPT_LOG
                    .log(((FUserinfo) this.getLoginUser()).getUsercode(),
                            id.toString(),
                            "删除字典代码 [" + dbobject.getCatalogcode() + "]");

        } catch (Exception e) {
            log.error(e.getMessage());
            saveError(e.getMessage());
        }
       
        return this.equipmentInfoType();
    }

    @Override
    public InputStream getExportExcelFile() {
        list();
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());
        filterMap.put("equipmentState", "T");
        if (("true").equals(filterMap.get("isBoth"))) {
            filterMap.remove("equipmentState");
        }
        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");
        // 默认排序equipmentCode-asc降序
        if (StringUtils.isBlank(orderField)
                && StringUtils.isBlank(orderDirection)) {
            filterMap
                    .put(CodeBook.SELF_ORDER_BY, "equipmentCode" + " " + "asc");
        }
        if (!StringUtils.isBlank(orderField)
                && !StringUtils.isBlank(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                    + orderDirection);
        }
        String extraCode = null;

        if (StringUtils.isEmpty(s_equipmentType)) {
            extraCode = s_supEquipmentType;
        } else {
            extraCode = s_equipmentType;
        }
        String shql = "";
        StringBuffer types = new StringBuffer();
        if (!StringUtils.isEmpty(extraCode)) {
            List<FDatadictionary> l = CodeRepositoryUtilExtend
                    .getTreeDictionaryStartBy(equ, extraCode, true);
            for (FDatadictionary f : l) {
                types.append(", '" + f.getDatacode() + "'");
            }
            if (!StringUtils.isBlank(types.toString())) {
                String str = types.substring(1, types.length());
                shql = "  From EquipmentInfo where equipmentType in( " + str
                        + ")  ";
            }
        }
        objList = baseEntityManager.listObjects(shql, filterMap);
        // List<EquipmentInfo> infos = baseEntityManager.listObjects();//
        // 获取需要生成Excel的数据
        if (objList != null) {
            for (EquipmentInfo o : objList) {
                o.setEquipmentCharge(CodeRepositoryUtil.getValue("usercode",
                        o.getEquipmentCharge()));
                o.setEquipmentState(CodeRepositoryUtil.getValue(
                        "equipmentState", o.getEquipmentState()));
                o.setEquipmentType(CodeRepositoryUtil.getValue("equipment",
                        o.getEquipmentType()));
            }
        }
        String[] header = { "固定资产名称", "固定资产类别类别", "资产负责人", "资产初始价值", "年折旧比率",
                "固定资产状态", "投入使用时间", "注销时间" };// 列头
        String[] property = { "equipmentName", "equipmentType",
                "equipmentCharge", "equipmentPrice", "yearlyDepreciation",
                "equipmentState", "inuseTime", "discardDate" };
        return ExportExcelUtil.generateExcel(objList, header, property);// 获取导出Excel的字节流
    }

    @Override
    public String exportExcel() {
        try {
            exportFileName = new String("固定资产列表.xls".getBytes("GBK"),
                    "ISO8859-1");// 将导出Excel的文件名进行ISO8859-1编码，否则中文文件名会乱码
        } catch (UnsupportedEncodingException e) {
        }
        return "exportExcel";
    }

    /**
     * 级联下拉框
     * 
     * @return
     */
    public String options() {
        String value = request.getParameter("value");
        options = new ArrayList<Map<String, String>>();
        if (StringUtils.isBlank(value)) {
            return "options";
        }
        List<FDatadictionary> retDictionary= CodeRepositoryUtilExtend.getTreeDictionaryStartBy("e",
                value, false);
        for (FDatadictionary dt : retDictionary) {
            Map<String, String> op = new HashMap<String, String>();
            op.put("key", dt.getId().getDatacode());
            op.put("value", dt.getDatavalue());
            options.add(op);
        }
        return "options";
    }

    /**
     * 一段时间内的使用情况图形展示（当前月）
     * 
     * @param objList
     * @return
     */
    public String showChart(List<EquipmentInfo> objList) {
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
            for (EquipmentInfo EquipmentInfo : objList) {// 遍历各固定资产
                equipmentUsings = equipmentUsingMag.getEquipmentUsingList(
                        beginTime, endTime, EquipmentInfo.getEquipmentId());
                String color;
                double value;
                Date endTimeTemp = beginTime;// 存放上一条记录的endTime，起始时间
                List<ColorAndValue> chartList = new ArrayList<ColorAndValue>();
                if (equipmentUsings != null) {
                    ColorAndValue colorAndValue = new ColorAndValue();
                    for (EquipmentUsing EquipmentUsing : equipmentUsings) {
                        if (EquipmentUsing.getEndTime() != null
                                && EquipmentUsing.getEndTime().getTime() <= currentTime
                                        .getTime()) {
                            if (EquipmentUsing.getEndTime().getTime() < beginTime
                                    .getTime()) {
                            }// 使用时间在申请时间之前且，使用时间不在统计范围内
                            else if (EquipmentUsing.getBeginTime().getTime() < beginTime
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
                                        .valueOf(EquipmentUsing.getEndTime()
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
                                colorAndValue.setEndTime(EquipmentUsing
                                        .getEndTime());
                                colorAndValue.setUsing("使用");
                                colorAndValue.setUser(EquipmentUsing
                                        .getApplicant());
                                chartList.add(colorAndValue);
                                endTimeTemp = EquipmentUsing.getEndTime();

                            } else {
                                if (EquipmentUsing.getBeginTime().getTime() < endTimeTemp
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
                                            .valueOf(EquipmentUsing
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getEndTime());
                                    colorAndValue.setUsing("使用");
                                    colorAndValue.setUser(EquipmentUsing
                                            .getApplicant());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing.getEndTime();
                                } else if (EquipmentUsing.getBeginTime()
                                        .getTime() >= endTimeTemp.getTime()) {
                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
                                                    .getBeginTime().getTime()
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getBeginTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing.getBeginTime();
                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getEndTime());
                                    colorAndValue.setUsing("使用");
                                    colorAndValue.setUser(EquipmentUsing
                                            .getApplicant());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing.getEndTime();
                                }
                            }
                        } else if (EquipmentUsing.getFeedbackTime() == null// 根据反馈时间判断是否使用
                                && EquipmentUsing.getPlanEndTime().getTime() >= currentTime
                                        .getTime()) {
                            if (EquipmentUsing.getPlanEndTime().getTime() >= currentTime
                                    .getTime()) {// 排除今天之前只申请未使用的
                                if (EquipmentUsing.getPlanBeginTime().getTime() < endTimeTemp
                                        .getTime()) {// 判断申请的起始时间是在最后一段使用时间之前，还是之后。之前则空闲区取0，申请计算的起始时间从使用结束时间算起（页面添加申请验证）
                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getPlanEndTime());
                                    colorAndValue.setUsing("申请");
                                    colorAndValue.setUser(EquipmentUsing
                                            .getApplicant());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing
                                            .getPlanEndTime();
                                } else if (EquipmentUsing.getPlanEndTime()
                                        .getTime() <= endTime.getTime()) {
                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
                                                    .getPlanBeginTime()
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getPlanBeginTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing
                                            .getPlanBeginTime();
                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getPlanEndTime());
                                    colorAndValue.setUsing("申请");
                                    colorAndValue.setUser(EquipmentUsing
                                            .getApplicant());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing
                                            .getPlanEndTime();

                                } else if (EquipmentUsing.getPlanEndTime()
                                        .getTime() > endTime.getTime()) {// 最后一条记录结束时间在统计范围外

                                    value = (Double.parseDouble(String
                                            .valueOf(EquipmentUsing
                                                    .getPlanBeginTime()
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
                                    colorAndValue.setEndTime(EquipmentUsing
                                            .getPlanBeginTime());
                                    colorAndValue.setUsing("空闲");
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing
                                            .getPlanBeginTime();

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
                                    colorAndValue.setUser(EquipmentUsing
                                            .getApplicant());
                                    chartList.add(colorAndValue);
                                    endTimeTemp = EquipmentUsing
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

                    EquipmentInfo.setChartList(chartList);
                }
            }
        }
        return "";
    }

    /**
     * 树形结构
     * 
     * @param catalogCode
     */
    public void putTree(String catalogCode) {
        FDatacatalog dbobject = dictManger.getObjectById(catalogCode);
        setFdesc(dictManger.getFieldsDesc(dbobject.getFielddesc(),
                dbobject.getCatalogtype()));
        dictDetails = CodeRepositoryUtilExtend.getTreeDictionaryStartBy(
                catalogCode, s_supEquipmentType, true);
        ParentChild<FDatadictionary> c = new Algorithm.ParentChild<FDatadictionary>() {
            public boolean parentAndChild(FDatadictionary p, FDatadictionary c) {
                return p.getDatacode().equals(c.getExtracode());
            }
        };

        Algorithm.sortAsTree(dictDetails, c);
        List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(dictDetails, c);
        ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
        totalRows = optIndex.size();
    }

    public String[] getFdesc() {
        return fdesc;
    }

    public void setFdesc(String[] fdesc) {
        this.fdesc = fdesc;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public String selectUser() {

        List<FUserinfo> temp = CodeRepositoryUtil.getAllUsers("T");
         jsonDatas = new ArrayList<Map<String, String>>();
        userinfos = new ArrayList<FUserinfo>();
//
        String matchInfo = request.getParameter("q");
        String matchCount = "5";

        int count = (null == matchInfo || "".equals(matchInfo)) ? 0 : Integer
                .parseInt(matchCount);

        if (null == matchInfo || "".equals(matchInfo)) {
            return "userinfos";
        }

        matchInfo = matchInfo.toLowerCase();
        int index = 0;
        for (FUserinfo user : temp) {
            if (index >= count)
                break;

            if (user.getUsername().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            } else if (user.getLoginname().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            }  else if (user.getUsercode().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            }
        }
        for (FUserinfo userinfo : userinfos) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userinfo.getUsercode());
            m.put("name", userinfo.getUsername()+"["+userinfo.getUsercode()+"]"+"["+userinfo.getLoginname()+"]");
            m.put("showName", userinfo.getUsername());
            jsonDatas.add(m);
        }



        return "userinfos";
    }
    
    private static FUserinfo copyUserInfo(FUserinfo info) {
        FUserinfo temp = new FUserinfo();
        
        temp.setUsercode(info.getUsercode());
        temp.setUsername(info.getUsername());
//        temp.setNameFisrtLetter(info.getNameFisrtLetter());
        temp.setLoginname(info.getLoginname());
        
        return temp;
    }
}
