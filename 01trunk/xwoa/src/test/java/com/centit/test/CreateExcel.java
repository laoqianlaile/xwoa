package com.centit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;


/**
 * 
 * POI根据Excel模板导出数据，测试用例
 * 
 * @author hx
 * @create 2017年2月8日
 * @version
 */
public class CreateExcel {
    public static void main(String[] args) throws FileNotFoundException, IOException {
      //excel模板路径
        File fi=new File("D:\\访客团统计.xls");
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
        //读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        //读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);
        //在相应的单元格进行赋值
        HSSFCell cell = sheet.getRow(3).getCell(3);
        cell.setCellValue("测试");
        HSSFCell cell2 = sheet.getRow(3).getCell(2);
        cell2.setCellValue("数据");
        HSSFCell cell3 = sheet.getRow(0).getCell(0);
        cell3.setCellValue("徐圩新区来访客团统计");  
        //修改模板内容导出新模板
        //FileOutputStream out = new FileOutputStream("D:/export.xls");
      /*  wb.write(out);
        out.close(); */
        
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode("徐圩新区来访客团统计", "utf-8") + ".xls");
        response.setContentType("application/x-download; charset=utf-8"); 
        wb.write(response.getOutputStream());
        
    }
}

