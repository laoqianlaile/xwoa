package com.centit.powerruntime.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;

@SuppressWarnings("deprecation")
public class BizCommUtil {

    /**
     * 截取业务流水前缀字符
     * 
     * @param str
     *            业务流水
     * @param n
     *            截取出现0 的次数，也就是从第几个0之前截取，默认为第一个；
     * @return
     */
    public static final String getPrefix4Biz(String str, int n) {
        int i = 0;
        int s = 0;
        while (i++ < n) {
            s = str.indexOf("0", s + 1);
            if (s == -1) {
                return str;
            }
        }
        return str.substring(0, s);
    }

    /**
     * 根据djid判断查看基本信息进去那个方法名主要用于区分收发文与其他流程的查看差异
     * 
     * @return
     */
    public static String getViewMethod(String djid) {
        String methodName = "";
        String type = StringUtils.substringBefore(djid, "0");
        if ("SW".equals(type)) {
            methodName = "generalOptView.do";
        } else if ("FW".equals(type)) {
            methodName = "generalOptView.do";
        } else {
            methodName = "view.do";
        }

        return methodName;

    }
    /**
     * 运用poi导出excel表
     * 
     * @param titleName
     * @param titles
     * @param list
     * @throws IOException
     */
    public static void doPoi2Excel(String titleName, String[] titles,
            List<Object[]> list) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
      /*  // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode(titleName, "utf-8") + ".xls");
        response.setContentType("application/x-download; charset=utf-8");*/

        HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象
        HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象
        for (int i = 0; i <= titles.length; i++) {
            sheet.setColumnWidth((short) i, (short) 4500); // 默认设置宽度
        }

        // 设置样式
        HSSFCellStyle titleStyle = workBook.createCellStyle(); // 创建样式对象
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION); // 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        // 设置字体
        HSSFFont titleFont = workBook.createFont(); // 创建字体对象
        titleFont.setFontHeightInPoints((short) 15); // 设置字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 设置粗体
        titleFont.setFontName("黑体"); // 设置为黑体字
        titleStyle.setFont(titleFont);
        titleStyle.setWrapText(false);

        // 合并单元格操作
        // sheet.addMergedRegion(new Region(0, (short) 1, 0, (short) 9));
        // sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
        // 单元格的长度是标题长度-1
        int regionLeng = titles.length - 1;
        // sheet.addMergedRegion(new Region(0, (short) 1, 0, (short)
        // regionLeng));
        HSSFRow row = null;
        HSSFCell cell = null;
        // row = sheet.createRow(0);
        // cell = row.createCell((short) 0);
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
        // cell.setCellValue(titleName);
        // cell.setCellStyle(titleStyle);

        // 设置表文样式
        HSSFCellStyle tableStyle = workBook.createCellStyle();
        tableStyle.setBorderBottom((short) 1);
        tableStyle.setBorderTop((short) 1);
        tableStyle.setBorderLeft((short) 1);
        tableStyle.setBorderRight((short) 1);
        tableStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // tableStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        // tableStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        tableStyle.setWrapText(true);
        // 设置表文字体
        HSSFFont tableFont = workBook.createFont();
        tableFont.setFontHeightInPoints((short) 11); // 设置字体大小
        tableFont.setFontName("宋体"); // 设置为黑体字
        tableStyle.setFont(tableFont);

        row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) 0);

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理

            cell.setCellValue(titleName);
            cell.setCellStyle(tableStyle);
        }

        row = sheet.createRow(1);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) i);
            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
            cell.setCellValue("");
            cell.setCellStyle(tableStyle);
        }
        // poi中合并单元格的方法addMergedRegion已废弃，代替CellRangeAddress
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 1, 0,
                (short) regionLeng));
        row = sheet.createRow(2);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) i);

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
            cell.setCellValue(titles[i]);
            cell.setCellStyle(tableStyle);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 3);
            Object[] objs = (Object[]) list.get(i);
            for (int j = 0; j < titles.length; j++) {
                cell = row.createCell((short) j);
                // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
                String cellValue = String.valueOf(objs[j]);
                if (StringUtils.isEmpty(cellValue) || "null".equals(cellValue)) {
                    cellValue = "";
                }
                cell.setCellValue(cellValue);
                cell.setCellStyle(tableStyle);

                sheet.autoSizeColumn((short) j);
            }
        }
        titleName = titleName + ".xls";
        String agent = ServletActionContext.getRequest().getHeader("USER-AGENT");
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0) {//火狐
            titleName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(titleName.getBytes("UTF-8")))) + "?=";
        }else {//其他浏览器
            titleName = URLEncoder.encode(titleName, "utf-8");
        }
        
        // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + titleName);
        response.setContentType("application/x-download; charset=utf-8");

        workBook.write(response.getOutputStream());
    }

    /**
     * 运用poi导出excel表
     * 支持指定字段合并单元格行数
     * @param titleName
     * @param titles
     * @param list
     * @throws IOException
     */
    public static void doPoi2MergedRegionExcel(String titleName, String[] titles,
            List<Object[]> list ,int renLenth) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode(titleName, "utf-8") + ".xls");
        response.setContentType("application/x-download; charset=utf-8");

        HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象
        HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象
        for (int i = 0; i <= titles.length; i++) {
            sheet.setColumnWidth((short) i, (short) 4500); // 默认设置宽度
        }

        // 设置样式
        HSSFCellStyle titleStyle = workBook.createCellStyle(); // 创建样式对象
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION); // 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        // 设置字体
        HSSFFont titleFont = workBook.createFont(); // 创建字体对象
        titleFont.setFontHeightInPoints((short) 15); // 设置字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 设置粗体
        titleFont.setFontName("黑体"); // 设置为黑体字
        titleStyle.setFont(titleFont);
        titleStyle.setWrapText(false);
        
        // 单元格的长度是标题长度-1
        int regionLeng = titles.length - 1;       
        HSSFRow row = null;
        HSSFCell cell = null;       

        // 设置表文样式
        HSSFCellStyle tableStyle = workBook.createCellStyle();
        tableStyle.setBorderBottom((short) 1);
        tableStyle.setBorderTop((short) 1);
        tableStyle.setBorderLeft((short) 1);
        tableStyle.setBorderRight((short) 1);
        tableStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        tableStyle.setWrapText(true);
        // 设置表文字体
        HSSFFont tableFont = workBook.createFont();
        tableFont.setFontHeightInPoints((short) 11); // 设置字体大小
        tableFont.setFontName("宋体"); // 设置为黑体字
        tableStyle.setFont(tableFont);

        row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) 0);

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理

            cell.setCellValue(titleName);
            cell.setCellStyle(tableStyle);
            
        }

        row = sheet.createRow(1);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) i);
            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
            cell.setCellValue("");
            cell.setCellStyle(tableStyle);
        }
        // poi中合并单元格的方法addMergedRegion已废弃，代替CellRangeAddress
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 1, 0,
                (short) regionLeng));
        row = sheet.createRow(2);
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell((short) i);

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
            cell.setCellValue(titles[i]);
            cell.setCellStyle(tableStyle);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 3);
            Object[] objs = (Object[]) list.get(i);
            for (int j = 0; j < titles.length; j++) {
                cell = row.createCell((short) j);
                // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
                String cellValue = String.valueOf(objs[j]);
                if (StringUtils.isEmpty(cellValue) || "null".equals(cellValue)) {
                    cellValue = "";
                }
                cell.setCellValue(cellValue);
                cell.setCellStyle(tableStyle);

                sheet.autoSizeColumn((short) j);
            }
        }

        workBook.write(response.getOutputStream());
    }
    /**
     * @Title: exportExcel
     * @Description: 导出Excel的方法
     * @author: evan @ 2017-06-13
     * @param workbook 
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet)
     * @param sheetTitle  （sheet的名称）
     * @param headers    （表格的标题）
     * @param result   （表格的数据）
     * @param out  （输出流）
     * @throws Exception
     */
    public static void exportExcel(HSSFWorkbook workbook, int sheetNum,
            String sheetTitle, String[] headers, List<List<String>> result) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);//设置字段背景颜色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);
        
        // 设置表文样式
        HSSFCellStyle tableStyle = workbook.createCellStyle();
        tableStyle.setBorderBottom((short) 1);
        tableStyle.setBorderTop((short) 1);
        tableStyle.setBorderLeft((short) 1);
        tableStyle.setBorderRight((short) 1);
        tableStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        tableStyle.setWrapText(true);
        // 设置表文字体
        HSSFFont tableFont = workbook.createFont();
        tableFont.setFontHeightInPoints((short) 10); // 设置字体大小
        tableFont.setFontName("Arial"); // 设置为字体
        tableStyle.setFont(tableFont);
        
        if(headers!=null){
        // 单元格的长度是标题长度-1
        int regionLeng = headers.length - 1;    
        HSSFRow row = null;
        HSSFCell cell = null;  
        // 产生表格标题行
        row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell((short) 0);
            cell.setCellValue(sheetTitle);
            cell.setCellStyle(tableStyle);
            
        }
        row = sheet.createRow(2);
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell((short) i);
        
            cell.setCellStyle(tableStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
            
        }
        row = sheet.createRow(3);
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell((short) i);
        
            cell.setCellStyle(style);
            
            if(i>0&&i%2!=0){
                cell.setCellValue("上午");
            }else if(i>0&&i%2==0){
                cell.setCellValue("下午");  
            }else{
                cell.setCellValue("日期");  
            }
            if(i>0&&i%2==0){
                String[] columns = Columns.getColumnLabels(i);
                String[] columns2 = Columns.getColumnLabels(i+1);
                //合并单元格**
               sheet.addMergedRegion(CellRangeAddress.valueOf("$"+columns[columns.length-1]+"$3:$"+columns2[columns2.length-1]+"$3"));  
            }
        }
        
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 4;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str.toString());
                    cell.setCellStyle(tableStyle);
                    sheet.setColumnWidth((short) cellIndex, (short)(18*128));//设置列宽**
                    cellIndex++;
                }
                index++;
            }
        }
            
        // poi中合并单元格的方法addMergedRegion已废弃，代替CellRangeAddress
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 1, 0,
                (short) regionLeng)); 
        }
    }
  /**
   * 根据Excel模板导出数据
   * @param srcPath 模板路径 一定是服务访问的路径，工具不检查是否可用
   * @param excelName 导出Excel弹窗附件名称
   * @param titleName 表格第一行大标题
   * @param list 
   * @param rowNum 初始赋值行
   * @throws IOException
   */
    public static void doPoi2ExcelByTemplate(String srcPath, String excelName,
            String titleName, List<Object[]> list,int rowNum) throws IOException {
        // excel模板路径
        File fi = new File(srcPath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
        // 读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        // 读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);
        // 在相应的单元格进行赋值
        HSSFRow row = null;
        HSSFCell cell = null;
     // 设置表文样式
        HSSFCellStyle tableStyle = wb.createCellStyle();
        tableStyle.setBorderBottom((short) 1);
        tableStyle.setBorderTop((short) 1);
        tableStyle.setBorderLeft((short) 1);
        tableStyle.setBorderRight((short) 1);
        tableStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // tableStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        // tableStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        tableStyle.setWrapText(true);
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + rowNum);
            Object[] objs = (Object[]) list.get(i);
            for (int j = 0; j < objs.length; j++) {
                cell = row.createCell((short) j);
                // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理
                String cellValue = String.valueOf(objs[j]);
                if (StringUtils.isEmpty(cellValue) || "null".equals(cellValue)) {
                    cellValue = "";
                }
                cell.setCellValue(cellValue);
                cell.setCellStyle(tableStyle);

                sheet.autoSizeColumn((short) j);
            }
        }
        // 大标题
        HSSFCell cell3 = sheet.getRow(0).getCell(0);
        cell3.setCellValue(titleName);// 表格第一行大标题
        // 修改模板内容导出新模板
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode(excelName, "utf-8") + ".xls");// 导出Excel弹窗附件名称
        response.setContentType("application/x-download; charset=utf-8");
        wb.write(response.getOutputStream());
    }

    /**
     * 根据人员代码设置人员名称
     * 
     * @param usercodes
     *            ————一串人员代码，由逗号隔开
     * @return 返回人员名称，也由逗号隔开
     */
    public static String getUsernamesFromUsercodes(String usercodes) {

        String usernames = null;
        if (StringUtils.isNotBlank(usercodes)) {
            usernames = "";
            String[] codesArray = usercodes.split(",");
            for (int i = 0; i < codesArray.length; i++) {
                usernames += CodeRepositoryUtil.getValue("usercode",
                        codesArray[i]) + ",";
            }
            usernames = usernames.substring(0, usernames.length() - 1);
        }
        return usernames;
    }

    /**
     * 方法用途：在办理页面，显示对应业务查看View方法对应的页面； 根据djid判断查看基本信息进去那个方法名主要用于区分收发文与其他流程的查看差异
     * 
     * @return
     */
    public static String getViewOfMethod(String djid) {
        String methodName = "";
        String type = StringUtils.substringBefore(djid, "0");
        if ("SW".equals(type)) {
            methodName = "viewIncomeDoc.do";
        } else if ("FW".equals(type)) {
            methodName = "viewDispatchDocInfo.do";
        } else {
            methodName = "view.do";
        }

        return methodName;

    }

    /**
     * 对于某些关键字或者某些词汇进行过滤替换的功能
     * 
     * @param map
     * @param name
     * @return
     */
    public static String replace(Map map, String name) {
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = (String) map.get(key);
            if (name.contains(key)) {
                name = name.replace(key, value);// 对于符合map中的key值实现替换功能

            }
        }
        return name;
    }

    /**
     * 判断办件是否超期——N：未超期，I：三天内，即将超期，O：已超期
     * 
     * @param o
     * @return
     */
    public static String getOverDueState(Date toBeanfinishedDate) {

        Date current = new Date();
        String overDueType = "N";
        if (null != toBeanfinishedDate) {

            String s1 = DatetimeOpt.convertDateToString(current, "yyyy-MM-dd");
            String s2 = DatetimeOpt.convertDateToString(toBeanfinishedDate,
                    "yyyy-MM-dd");

            if (s1.equals(s2)) {
                overDueType = "I";
            } else {
                if (current.getTime() > toBeanfinishedDate.getTime()) {
                    overDueType = "O";
                } else if (DatetimeOpt
                        .calcSpanDays(current, toBeanfinishedDate) <= 3) {
                    overDueType = "I";
                }
            }
        }
        return overDueType;
    }

}
