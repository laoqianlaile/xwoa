package com.centit.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.centit.powerruntime.util.Columns;

public class ExportExcelUtils {
    @SuppressWarnings({ "unchecked", "static-access" })
    public static void main(String[] args) {
        try {     
            OutputStream out = new FileOutputStream("D:\\test.xls");
            List<List<String>> data = new ArrayList<List<String>>();
            for (int i = 1; i < 5; i++) {
                List rowData = new ArrayList();
                rowData.add("6月"+String.valueOf(i)+"日");
                rowData.add("--");
                rowData.add("18:23:42");
                rowData.add("08:23:32");
                rowData.add("17:23:72");
                data.add(rowData);
            }
            String[] headers = {"","安军伟","", "杨轩",""};
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            eeu.exportExcel(workbook, 0, "开发一部", headers, data, out);
            eeu.exportExcel(workbook, 1, "开发二部", headers, data, out);
            eeu.exportExcel(workbook, 2, "开发三部", headers, data, out);
            //原理就是将所有的数据一起写入，然后再关闭输入流。        
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
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
			String sheetTitle, String[] headers, List<List<String>> result,
			OutputStream out) throws Exception {
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
        tableFont.setFontHeightInPoints((short) 11); // 设置字体大小
        tableFont.setFontName("宋体"); // 设置为字体
        tableStyle.setFont(tableFont);
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
			if(i>0&&i%2==0){
			    String[] columns = Columns.getColumnLabels(i);
			    String[] columns2 = Columns.getColumnLabels(i+1);
			    //合并单元格**
			   sheet.addMergedRegion(CellRangeAddress.valueOf("$"+columns[columns.length-1]+"$3:$"+columns2[columns2.length-1]+"$3"));  
			}
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
					sheet.setColumnWidth((short) cellIndex, (short)(30*128));//设置列宽**
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
