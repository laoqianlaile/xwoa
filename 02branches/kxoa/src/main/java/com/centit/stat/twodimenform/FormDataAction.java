package com.centit.stat.twodimenform;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.stat.po.QueryCondition;
import com.centit.support.compiler.Formula;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.HtmlFormUtils;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FormDataAction extends BaseAction implements ModelDriven<FormDataModel>{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(FormDataAction.class);
    private FormDataModel fromObj;
    private FormDataManager dataManager;
    private SysUnitManager sysUnitManager;
    
    private SysUserManager sysUserManager;
    private String allowSelectAll = "T";//控制下拉框是否可以选择全部，就是id为空时，该条件不参加sql过滤
    private String allowExport = "F";//是否允许导出
    
    public String getAllowSelectAll() {
        return allowSelectAll;
    }

    public void setAllowSelectAll(String allowSelectAll) {
        this.allowSelectAll = allowSelectAll;
    }
    
    public String getAllowExport() {
        return allowExport;
    }

    public void setAllowExport(String allowExport) {
        this.allowExport = allowExport;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    private JSONObject jsonFormData;
    protected Integer totalRows;
    
    public FormDataAction()
    {
        fromObj = new FormDataModel();
        jsonFormData = new JSONObject();
    }

    @Override
    public FormDataModel getModel() {
        return fromObj;
    }

    public FormDataModel getFromObj() {
        return fromObj;
    }

    public void setFromObj(FormDataModel fromObj) {
        this.fromObj = fromObj;
    }

    public void setDataManager(FormDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public JSONObject getJsonFormData() {
        return jsonFormData;
    }

    public void setJsonFormData(JSONObject jsonFormData) {
        this.jsonFormData = jsonFormData;
    }
    
    public Integer getTotalRows() {
        return totalRows;
    }
    
    @SuppressWarnings("unchecked")
    private void collectParams() throws Exception {

        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> datas = new HashMap<String, Object>();
        for (QueryCondition cond : fromObj.getConditions()) {
            String sName = cond.getCondName();
            String style = cond.getCondStyle();

            // 通过SQL查询数字
            if (StringUtils.equals("S", style)) {
                String sql = cond.getCondType();
                //add by lay 2015-12-04 过滤条件没有拼接上去
                if("YGBJTJTLD".equals(fromObj.getModelName())){
                    sql = searchSqlJoint(cond); 
                    allowSelectAll = "F";
                }
                //end add
                List<Object[]> data = dataManager.findDataBySql(sql);
                datas.put(sName, data);
            }

            Object oValue = paramMap.get(sName);
            if (oValue != null) {
                String sValue = new String(HtmlFormUtils.getParameterString(oValue));
                //String sValue = new String(StringBaseOpt.getParameterString(oValue).getBytes("iso-8859-1"),"utf-8");
//                String sValue = URLDecoder.decode(StringBaseOpt.getParameterString(oValue));
                cond.setCondValue(sValue);
            }

            setDefaultValue(cond);
            setMonthQueryCondition(cond);
            
        }

        request.setAttribute("datas", datas);
    }
    
    private String searchSqlJoint(QueryCondition cond){
        if(StringUtils.isBlank(cond.getCondType())){
            return "";
        }
        String sql = cond.getCondType().toLowerCase();
        String orderBy = "";
        String where = "";
        String basesql = "";
        if(sql.indexOf("order by") > -1){
            orderBy = sql.substring(sql.indexOf("order by"));
        }
        if(sql.indexOf("where") > -1){
            if(sql.indexOf("order by") > -1){
                where = sql.substring(sql.indexOf("where"), sql.indexOf("order by"));    
            }else{
                where = sql.substring(sql.indexOf("where"));
            }
            basesql = sql.substring(0, sql.indexOf("where"));
        }
        FUserDetail loguser = (FUserDetail) getLoginUser();
        //管理员和厅领导看所有部门
        String filter = " and (unitcode = '"+loguser.getPrimaryUnit()+ "' or '001801'='"+loguser.getPrimaryUnit()+"' or '1' = '"+loguser.getPrimaryUnit()+"')";
        
        return basesql.trim() +" " + where.trim() +" "+ filter.trim()+" " + orderBy.trim();
    }
    /**
     * 覆盖默认值
     * 
     * @param cond
     */
    private void setDefaultValue(QueryCondition cond) {
        String value = cond.getCondDefault();
        
        if (StringUtils.isBlank(value)) return;
        if (!StringUtils.isBlank(cond.getCondValue())) return;
        
        if (value.startsWith("{") && value.endsWith("}")) {
            value = value.substring(1, value.length() - 1);
        }
        
        FUserDetail loguser = (FUserDetail) getLoginUser();
        
        if ("usercode".equals(value)) {
            cond.setCondValue(loguser.getUsercode());
        }
        else if ("unitcode".equals(value)) {
            cond.setCondValue(sysUnitManager.getObjectById(loguser.getPrimaryUnit()).getDepno());
        }else if("dep".equals(value)){
           /* //厅领导显示所有部门统计数据
            if(getTopUnitCode().equals(loguser.getPrimaryUnit())){
                cond.setCondDefault(null);
            }else{*/
                cond.setCondValue(loguser.getPrimaryUnit());
//            }
        }
    }
    
//    // 获取厅领导部门编号
//    public String getTopUnitCode() {
//        String unitcode="";
//        FDatadictionary fd = CodeRepositoryUtil.getDataPiece("SYSPARAM", "TopUnitCode");
//        if (null != fd) {
//            unitcode = fd.getDatavalue();
//        }
//
//        return unitcode;
//    }
    
    
    /**
     * 如果QueryCondition是月份、年份，并且没有设置默认值，则自动填充当前月份、年份作为其值
     * 
     * @param cond QueryCondition
     */
    private static void setMonthQueryCondition(QueryCondition cond) {
        String conditionStyle = cond.getCondStyle();
        Formula formula = new Formula();
        
        // 如果有值则不赋予现在时间
        if (!StringUtils.isBlank(cond.getCondValue())) {
            return;
        }
        
        // 有默认值按默认值计算
        if (!StringUtils.isBlank(cond.getCondDefault())) {
            cond.setCondValue(formula.calculate(cond.getCondDefault()));
            return;
        }
        
        if ("M".equals(conditionStyle)) {
            Date date = new Date();
            cond.setCondValue(String.valueOf(DatetimeOpt.getMonth(date)));
        }
        
        if ("Y".equals(conditionStyle)) {
            Date date = new Date();
            cond.setCondValue(String.valueOf(DatetimeOpt.getYear(date)));
        }
        
        if ("D".equals(conditionStyle)) {
            Date date = new Date();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            cond.setCondValue(sdf.format(date));
        }
    }
    
   /* private static String getParameterString(Object v)
    {
        if(v == null)
            return null;
        if(v instanceof String[])
        {
            String strArr[] = (String[])(String[])v;
            String str = "";
            if(strArr.length > 0) {
                for(String temp: strArr) {
                    str += temp + ","; 
                }
                
                return str.substring(0, str.lastIndexOf(","));
            }
            else
                return null;
        }
        if(v instanceof String)
            return (String)v;
        else
            return v.toString();
    }*/
    
    /**
     * 从数据库中取元数据
     * 
     * @param pagination
     */
    @SuppressWarnings("unchecked")
    private void queryDatabase(boolean pagination){
        fromObj.copyModelMetaData( dataManager.getDataModel(fromObj.getModelName()));
        
        Map<Object,Object> paramMap = request.getParameterMap();
        Object oValue = paramMap.get("resultName");
        if(oValue!=null){
            String sValue = HtmlFormUtils.getParameterString( oValue);
            if(sValue!=null && !"".equals(sValue))
                fromObj.setResultName(sValue);
        }
     
        //设置分页信息
        if(pagination){
            Limit limit=ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit); 
            fromObj.setRetStartPos(pageDesc.getRowStart());
            fromObj.setRetMaxSize(pageDesc.getPageSize());
        }
 
        totalRows = 0;

        //获取参数
        try {
            collectParams();
        } catch (Exception e) {
            log.error("获取参数失败："+e.getMessage());
            e.printStackTrace();
        }
        
        String modelType = fromObj.getModelType();
        String additionrow = fromObj.getAdditionRow();
        
        // 是否需要合计
        boolean needSum = StringUtils.equals("1", additionrow);
        
        // 普通二维报表
        if ("2".equals(modelType) || StringUtils.isEmpty(modelType)) {
            totalRows = dataManager.queryFormData(fromObj, needSum, pagination);
        }
        
        // 同比报表 环比报表
        else if ("3".equals(modelType) || "4".equals(modelType)) {
            totalRows = dataManager.queryCompareData(fromObj, needSum);
        }
        
        // 交叉表
        else if ("5".equals(modelType)) {
            totalRows = dataManager.queryCrossData(fromObj, needSum);
        }
        
        else {
            totalRows = dataManager.queryFormData(fromObj, needSum, pagination);
        }
        
        FUserDetail loguser = (FUserDetail) this.getLoginUser();
        if(loguser!=null){
            fromObj.makeParamByFormat(loguser.getUsercode(),loguser.getPrimaryUnit());
            request.setAttribute("primaryUnit",loguser.getPrimaryUnit());
        }else{
            fromObj.makeParamByFormat("","");
            request.setAttribute("primaryUnit", "1");
        }     
        
    }
    

    
    /**
     * 统计
     * 
     * @return
     */
    public String doStat(){
        queryDatabase(false);
        jsonFormData = fromObj.toJsonData();
        
        String isCollapse = request.getParameter("isCollapse");
        String isThLarge = request.getParameter("isThLarge");
        
        request.setAttribute("isCollapse", isCollapse);
        request.setAttribute("isThLarge", isThLarge);
        
        return "twodimenform";
    }
    
    /**
     * 查询
     * 
     * @return
     */
    public String doQuery(){
        queryDatabase(true);
        return "twodimenform";
    }
    
    public String doAjaxStat(){
        queryDatabase(false);
        jsonFormData = fromObj.toJsonData();
        return "ajaxJsonData";
    }
    
    public String doExport() {
        String formName = fromObj.getFormName();
        if (StringBaseOpt.isNvl(formName))
            formName = "统计报表";
        // 获得页面table元素里面的数据
        // 存入的格式 0,0,1,1,值#0,1,1,2,值#0,2,1,1,值
        // 1,0,1,1,值#1,1,1,1,null#1,2,1,1,值
        // *第一个参数是当前行，第二个参数是当前列，第三个参数是当前行所跨的行，第四个参数是当前行所跨的列，第五个参数是当前单元格的值
        //Object o = request.getAttribute("dataInfo");
        String dataInfo = fromObj.getExportData();// session.get("dataInfo").toString();

        // 去掉最后1个#
        if (dataInfo != null && !"".equals(dataInfo)) {
            String lastString = dataInfo.substring(dataInfo.length() - 1,
                    dataInfo.length());
            if ("#".equals(lastString)) {
                dataInfo = dataInfo.substring(0, dataInfo.length() - 1);
            }
        }

        // 通过|号分割，获得每个td里面的详细信息
        String[] list = dataInfo.split("#");

        // 创建hssfWorkbook工作簿
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(formName);
        sheet.setZoom(100, 100);
        
        //sheet.setDefaultRowHeightInPoints(24f); // doesn't work. POI's bug, fixed on revision r1243240.

        // 求最大行与最大列
        int col = 0;
        int temp = -1; // 零时变量只是, 判断读到几行了
        int tempMaxCell = 0; // table中的最大列数

        int maxCell = 0; // 最大列数
        int maxRow = 0; // 总共的行数

        // 求出总行数与最大的列数
        for (int i = 0; i < list.length; i++) {
            String zhi = list[i];
            String[] value = zhi.split(",");
            int rowNumber = Integer.parseInt(value[0]); // 当前所处列的编号
            int colspan = Integer.parseInt(value[3]); // 跨的列数

            if (rowNumber != temp) {
                maxRow++;
                temp = rowNumber;
                if (maxCell < tempMaxCell) {
                    maxCell = tempMaxCell;
                }
                tempMaxCell = 0;
            }
            tempMaxCell += colspan;
        }

        // 值数据的封装
        String[][] database = new String[maxRow][maxCell];
        // 颜色数据的封装
        //String[][] colors = new String[maxRow][maxCell];

        Integer[][] rowsInfo = new Integer[maxRow][maxCell];

        int countNum = 0;

        //
        for (int i = 0; i < list.length; i++) {
            String values = list[i];
            String[] value = values.split(",");
            int rowNumber = Integer.parseInt(value[0]); // 行编号
            int colNumber = Integer.parseInt(value[1]); // 列编号
            int rowspan = Integer.parseInt(value[2]); // 所跨的行数
            int colspan = Integer.parseInt(value[3]); // 所跨的列数

            if (rowNumber != temp) {
                temp = rowNumber;
                col = 0;
                countNum = 0;
            }

            // 既跨行也跨列的合并方法
            if (rowspan != 1 && colspan != 1) {
                sheet.addMergedRegion(new CellRangeAddress(rowNumber,
                        rowNumber + rowspan - 1, colNumber, colNumber + colspan
                                - 1));
                for (int j = 0; j < rowspan; j++) {
                    for (int k = 0; k < colspan; k++) {
                        rowsInfo[rowNumber + j][col + k] = 1111;
                    }
                }
                rowsInfo[rowNumber][col] = null;
            // 只跨行的单元格合并方法
            } else if (rowspan != 1) {
                sheet.addMergedRegion(new CellRangeAddress(rowNumber,
                        rowNumber + rowspan - 1, col, col));
                for (int j = 1; j < rowspan; j++) {
                    rowsInfo[rowNumber + j][col] = 1111;
                }
            // 只跨列的单元格合并方法
            } else if (colspan != 1) {
                if (rowsInfo[rowNumber][col] != null && rowsInfo[rowNumber][col] == 1111) {
                    sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, col + 1, col + colspan));
                    for (int j = 1; j < colspan; j++)
                        rowsInfo[rowNumber][col + j] = 2222;
                } else {
                    sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, col, col + colspan - 1));
                    for (int j = 1; j < colspan; j++)
                        rowsInfo[rowNumber][col + j] = 2222;
                }
            }

            database[rowNumber][countNum] = values.substring(values.lastIndexOf(",")+1, values.length());
            
            col = col + colspan;
            countNum++;
        }

        CellStyle style = workbook.createCellStyle();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        
        Font bodyFont = workbook.createFont();
        bodyFont.setFontHeightInPoints((short) 11);
        style.setFont(bodyFont);

        CellStyle styleHead = workbook.createCellStyle();
        styleHead.cloneStyleFrom(style);
        
        styleHead.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        styleHead.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        styleHead.setFont(headerFont);

        for (int i = 0; i < maxRow; i++) {
            Row row = sheet.createRow(i);
            int count = 0;
            for (int j = 0; j < maxCell; j++) {
                if (rowsInfo[i][j] == null) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(database[i][count]);
                    if (request.getParameter("headrows")!=null &&i < Integer.parseInt(request.getParameter("headrows")))
                        cell.setCellStyle(styleHead);
                    else
                        cell.setCellStyle(style);
                    count++;
                }
            }
        }
        
        //设置行高列宽
        for (int i=0;i<maxRow;i++){
            Row row = sheet.getRow(i);
            row.setHeightInPoints(24f);
            for (int j=0;j<maxCell;j++){
                if (rowsInfo[i][j] != null)
                    continue;
                Cell cell = row.getCell(j);
                if (cell != null){
                    int cellLength = cell.getStringCellValue().getBytes().length;
                    if (sheet.getColumnWidth(j)< cellLength*220)
                        sheet.setColumnWidth((short)j, (short)cellLength*220);
                }
            }
        }
        
        // 保存成xls文件
       /* try {
            FileOutputStream fileoutputstream = new FileOutputStream(
                    "D:/exceltext.xls");
            hssfworkbook.write(fileoutputstream);
            fileoutputstream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        
        try {
            ActionContext ctx = ActionContext.getContext();
            HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(formName+".xls", "UTF-8"));// 设定输出文件头
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型

            OutputStream output = response.getOutputStream();
            output.flush();
            workbook.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Output is closed ");
        }
        return null;
    }
    
    /**
     * 统计考勤
     * @return
     */
    public String doStatKQ(){
        queryDatabase(false);
        jsonFormData = fromObj.toJsonData();
        
        String isCollapse = request.getParameter("isCollapse");
        String isThLarge = request.getParameter("isThLarge");
        
        String auditDate = request.getParameter("auditDate");
        
        request.setAttribute("isCollapse", isCollapse);
        request.setAttribute("isThLarge", isThLarge);
        request.setAttribute("auditDate", auditDate);
        
        FUserDetail user=(FUserDetail) getLoginUser();
        if ("001843".equals(user.getPrimaryUnit())) {//人事科
            boolean isKz = false;
            List<FRoleinfo> roleinfoList = sysUserManager.getSysRolesByUsid(user.getUsercode());
            if (roleinfoList != null && !roleinfoList.isEmpty()) {
                for (FRoleinfo roleinfo : roleinfoList) {
                    if ("G-OAKZ".equals(roleinfo.getRolecode())) {
                        isKz = true;
                        break;
                    }
                }
            }
            if (isKz) {
                request.setAttribute("unitList", this.ruturnUnits());
                request.setAttribute("rskUnit", "1");
            }
        }
        
        return "twodimenform";
    }
    
    private  List<FUnitinfo> ruturnUnits() {
        
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
}

