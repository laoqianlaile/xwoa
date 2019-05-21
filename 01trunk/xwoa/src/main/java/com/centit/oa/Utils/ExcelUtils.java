package com.centit.oa.Utils;

import com.centit.oa.po.OaArrangeweek;
import com.centit.oa.po.OaWorkSummary;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
/**
 *@author：luo_c
 *@Description:  excel 工具类
 *@Date:15:02 2017/10/30 0030
 *@class:ExcelUtils
 */
public class ExcelUtils {

    /**
     * 设置标题样式
     * @param workbook
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getHeadStyle(HSSFWorkbook workbook){

        HSSFCellStyle style = workbook.createCellStyle();

        //设置上下左右四个边框宽度

        style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);


        //设置单元格背景色



        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        //设置字体格式

        HSSFFont font = workbook.createFont();


        font.setFontHeightInPoints((short)20);

        font.setBoldweight(font.BOLDWEIGHT_BOLD);



        //将字体格式设置到HSSFCellStyle上

        style.setFont(font);


        return style;
    }

    /**
    *@author：luo_c
    *@Description: 针对一周安排的导出
    *@Date:15:05 2017/10/30 0030
    *@class:ExcelUtils
    */
    public static void doPoi2Excel(String titleName, List<OaWorkSummary> workSummaries) throws IOException {
        String[] titles = null;
        if(titleName.equals("A")){
            titleName = "委领导一周安排";
            titles = new String[]{"序号", "时间","","活动类型",  "地点", "工作内容", "参加领导", "参加人员"};// 列头
        }else{
            titleName = "各处室一周安排";
            titles  = new String[]{"序号","时间","", "活动类型",   "地点", "工作内容", "处室负责人",  "参加人员", "责任处室"};// 列头
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象
        HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.createRow(0);
        //获取表头样式
        HSSFCellStyle headStyle =  getHeadStyle(workBook);
        //合并单元格
        Region region = new   Region(0,     (short)0,   1,   (short)  8);
        //解决后边框没有的问题
        setRegionStyle(sheet,region,headStyle);
        sheet.addMergedRegion(region);
        cell = row.createCell(0);
        //设置表头名
        cell.setCellValue(titleName);
        cell.setCellStyle(headStyle);

        //设置宽度
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 13000);
        sheet.setColumnWidth(4, 13000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 10000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        sheet.setColumnWidth(9, 5000);
        row = sheet.createRow(2);
        //获取标题样式
        HSSFCellStyle titleStyle =  createCellStyle(workBook);
        //获取表文样式
        HSSFCellStyle tableStyle  = getStyle(workBook);
        boolean isTrue = false;
        //输出标题
        for (int i = 0; i < titles.length; i++) {
            if(i == 1){
                //合并时间
             //   sheet.addMergedRegion(new CellRangeAddress(2,2,1,2));//纵向：合并第二列的第1行和第2行第
                 region = new   Region(2,     (short)1,   2,   (short)  2);
                setRegionStyle(sheet,region,titleStyle);
                sheet.addMergedRegion(region);

                isTrue = true;
            }
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(titleStyle);
            if(isTrue){
                i++;
                isTrue = false;
            }
        }

        int index = 0;
        for (OaWorkSummary str : workSummaries) {
            row = sheet.createRow(index + 3);
                cell = row.createCell(0);
                cell.setCellValue(index+1);
                cell.setCellStyle(tableStyle);
                //获取list
                int size = str.getArrangeweekJsons().size();
                if(size == 0){
                    //日期
                    String date =  str.getValue();
                    cell = row.createCell(1);
                    cell.setCellValue(date.replace("<br/>",""));
                    cell.setCellStyle(tableStyle);
                    //如果没有时间
                    cell = row.createCell(2);
                    cell.setCellValue("-");
                    cell.setCellStyle(tableStyle);
                    //合并单元格
                     region = new   Region(index+3,     (short)3,   index+3,   (short)   8);
                    setRegionStyle(sheet,region,tableStyle);
                    sheet.addMergedRegion(region);
                  //  sheet.addMergedRegion(new CellRangeAddress(index +3,index +3,3,7));//纵向：合并第二列的第1行和第2行第
                    cell = row.createCell(3);
                    //没有安排
                    cell.setCellValue("暂无工作安排");
                    cell.setCellStyle(tableStyle);
                    index ++;
                }else{
                    int count = index;
                    //合并单元格
                     region = new   Region(index+3,     (short)1,   index+2+size,   (short)   1);
                  //  Region region =sheet.addMergedRegion(new CellRangeAddress(index+3 ,index+2  +size ,1,1));
                      setRegionStyle(sheet,region,tableStyle);
                    sheet.addMergedRegion(region);
                    int flag = 0;
                    for (OaArrangeweek week : str.getArrangeweekJsons()) {
                        row = sheet.createRow(index + 3 + flag);

                        cell = row.createCell(0);
                        //序号
                        cell.setCellValue(count+++(1));
                        cell.setCellStyle(tableStyle);
                        //日期
                        String date =  str.getValue();
                        cell = row.createCell(1);
                        cell.setCellValue(date.replace("<br/>",""));
                        cell.setCellStyle(tableStyle);

                        //时间
                        cell = row.createCell(2);
                       String time =  week.getShowTimeValue();
                       if(isNull(time)){
                            cell.setCellValue(time.replace("<br/>",""));
                       }else{
                            cell.setCellValue("");
                       }
                        cell.setCellStyle(tableStyle);
                        cell = row.createCell(3);
                      String itemType =   week.getItemtype();//活动类型
                        if(isNull(itemType)){
                            cell.setCellValue(itemType);
                        }else{
                            cell.setCellValue("");
                        }
                        cell.setCellStyle(tableStyle);
                        cell = row.createCell(4);
                        //地址
                       String address =  week.getAddress();
                        if(isNull(address)){
                            cell.setCellValue(address);
                        }else{
                            cell.setCellValue("");
                        }
                        cell.setCellStyle(tableStyle);
                        //备注
                        cell = row.createCell(5);
                        String remark =  week.getRemark();
                        if(isNull(remark)){
                            cell.setCellValue(remark);
                        }else{
                            cell.setCellValue("");
                        }
                        cell.setCellStyle(tableStyle);

                        //参加领导
                        if(titleName.equals("A")){
                            cell = row.createCell(6);
                            String leaders = week.getAttendleaders();
                            if(isNull(leaders)){
                                cell.setCellValue(leaders);
                            }else{
                                cell.setCellValue("");
                            }
                            cell.setCellStyle(tableStyle);
                            //参加人员
                            cell = row.createCell(7);
                            String users = week.getAttendusers();
                            if(isNull(users)){
                                cell.setCellValue(users);
                            }else{
                                cell.setCellValue("");
                            }
                            cell.setCellStyle(tableStyle);
                        }else{
                            //处室负责人
                            cell = row.createCell(6);
                            String leaders = week.getAttendleaders();
                            if(isNull(leaders)){
                                cell.setCellValue(leaders);
                            }else{
                                cell.setCellValue("");
                            }
                            cell.setCellStyle(tableStyle);
                            //参加人员
                            cell = row.createCell(7);
                            String users = week.getAttendusers();
                            if(isNull(users)){
                                cell.setCellValue(users);
                            }else{
                                cell.setCellValue("");
                            }
                            cell.setCellStyle(tableStyle);
                            //部门
                            cell = row.createCell(8);
                            String depname = week.getDepname();
                            if(isNull(depname)){
                                cell.setCellValue(depname);
                            }else{
                                cell.setCellValue("");
                            }
                            cell.setCellStyle(tableStyle);
                        }
                        flag ++;
                    }
                    //坐标要加上合并单元格的长度
                    index = index  +size;
            }

        }
        //文件名
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
     * 判断是否为空或者  是否为字符串null
     * @param cellValue
     * @return
     */
    public static boolean isNull(String cellValue){
        if (StringUtils.isEmpty(cellValue) || "null".equals(cellValue)) {
            return false;
        }
        return true;
    }

    /**
     * 获取表文样式
     * @param workBook
     * @return
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workBook) {
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
        tableStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        tableStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        return tableStyle;
    }

    /**
     * 创建标题样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook){

        HSSFCellStyle style = workbook.createCellStyle();

        //设置上下左右四个边框宽度

        style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);

        style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);

        //设置上下左右四个边框颜色


        //设置单元格背景色

        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //设置字体格式

        HSSFFont font = workbook.createFont();

        font.setFontName("幼圆");

        font.setFontHeightInPoints((short)18);

        font.setBoldweight(font.BOLDWEIGHT_BOLD);


        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        //将字体格式设置到HSSFCellStyle上

        style.setFont(font);

        //设置单元格居中方式

        return style;

    }

    /**
     * 解决后边距遗失的问题
     * @param sheet
     * @param region
     * @param cs
     */
    public static void setRegionStyle(HSSFSheet sheet, Region region, HSSFCellStyle cs) {
        for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        OutputStream fis = new FileOutputStream(new File("d:\\root\\excel\\11.xls"));
        //通过构造函数传参
        HSSFWorkbook workbook = new HSSFWorkbook();
        //获取工作表
        HSSFSheet sheet = workbook.createSheet();
        //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
        HSSFRow row =  null;
        HSSFCell cell = null;
        //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
        for (int i = 0; i < 10; i++) {
            row = sheet.createRow(i);
            cell =  row.createCell(i);
            cell.setCellValue(i);
            cell.setCellStyle(createCellStyle(workbook));
        }
        sheet.addMergedRegion(new CellRangeAddress(1,1,2,3));//纵向：合并第二列的第1行和第2行第
        //设置单元格的值,即C1的值(第一行,第三列)
         cell.setCellValue("2");
         workbook.write(fis);
         fis.close();
    }



}
