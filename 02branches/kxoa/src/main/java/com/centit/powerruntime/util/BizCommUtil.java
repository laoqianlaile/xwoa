package com.centit.powerruntime.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
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
     * @param titleName
     * @param titles
     * @param list
     * @throws IOException
     */
    public static void doPoi2Excel(String titleName, String[] titles,
            List<Object[]> list) throws IOException {
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

        // 合并单元格操作
//        sheet.addMergedRegion(new Region(0, (short) 1, 0, (short) 9));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
        // 单元格的长度是标题长度-1
        int regionLeng=titles.length-1;
//         sheet.addMergedRegion(new Region(0, (short) 1, 0, (short)
//         regionLeng));
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
        //poi中合并单元格的方法addMergedRegion已废弃，代替CellRangeAddress
        sheet.addMergedRegion(new CellRangeAddress(0, (short)1, 0, (short)
                regionLeng));
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
     * 根据人员代码设置人员名称
     * @param usercodes————一串人员代码，由逗号隔开
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
     * 方法用途：在办理页面，显示对应业务查看View方法对应的页面；
     * 根据djid判断查看基本信息进去那个方法名主要用于区分收发文与其他流程的查看差异
     * @return
     */
    public static String getViewOfMethod(String djid){
        String methodName="";
        String type=StringUtils.substringBefore(djid, "0");
        if("SW".equals(type)){
            methodName="viewIncomeDoc.do";
        }else if("FW".equals(type)){
            methodName="viewDispatchDocInfo.do";
        }else{
            methodName="view.do";
        }
        
        return methodName;
        
    }
    
    
    /**
     * 对于某些关键字或者某些词汇进行过滤替换的功能
     * @param map
     * @param name
     * @return
     */
    public static String replace(Map map,String name) {
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = (String) map.get(key);
            if (name.contains(key)) {
                name=name.replace(key, value);//对于符合map中的key值实现替换功能
                
            }
        }
        return name;
    }
    
    
    /**
     * 判断办件是否超期——N：未超期，I：三天内，即将超期，O：已超期
     * @param o
     * @return
     */
    public static String getOverDueState(Date toBeanfinishedDate){
        
        Date current = new Date();
        String overDueType = "N";
        if(null != toBeanfinishedDate){
            
            String s1 = DatetimeOpt.convertDateToString(current, "yyyy-MM-dd");
            String s2 = DatetimeOpt.convertDateToString(toBeanfinishedDate, "yyyy-MM-dd");
            
           if(s1.equals(s2)){
               overDueType = "I";
           }else {
               if(current.getTime() > toBeanfinishedDate.getTime()){
                   overDueType = "O";
               }else if(DatetimeOpt.calcSpanDays(current, toBeanfinishedDate) <=3 ){
                   overDueType = "I";
               }
           }
        }
        return overDueType;
    }


}
