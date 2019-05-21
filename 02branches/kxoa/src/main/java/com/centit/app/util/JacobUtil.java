package com.centit.app.util;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobUtil {
    public static final int WORD_HTML = 8;
    public static final int WORD_TXT = 7;
    public static final int EXCEL_HTML = 44;
    public static final int WDFORMAT_WEBARCHIVE = 9;
    public static final int EXFORMAT_WEBARCHIVE = 45;
    
    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。  
    static final int wdFormatPDF = 17;// word转PDF 格式  
    static final int ppSaveAsPDF = 32;// ppt 转PDF 格式


    
    public static void wordToPDF(String docfile, String toFile) {    
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word    
        try {    
            app.setProperty("Visible", new Variant(false));    
            Dispatch docs = app.getProperty("Documents").toDispatch();    
            Dispatch doc = Dispatch.invoke(    
                    docs,    
                    "Open",    
                    Dispatch.Method,    
                    new Object[] { docfile, new Variant(false),    
                            new Variant(true) }, new int[1]).toDispatch();    
            //new Variant(type)，这里面的type的决定另存为什么类型的文件  
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {    
                    toFile, new Variant(17) }, new int[1]);    
            Variant f = new Variant(false);    
            Dispatch.call(doc, "Close", f);    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            app.invoke("Quit", new Variant[] {});    
        }    
    }
    
    public static void ppt2pdf(String source,String target){  
        System.out.println("启动PPT");  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        try {  
            app = new ActiveXComponent("Powerpoint.Application");  
            Dispatch presentations = app.getProperty("Presentations").toDispatch();  
            System.out.println("打开文档" + source);  
            Dispatch presentation = Dispatch.call(presentations,//  
                    "Open",   
                    source,// FileName  
                    true,// ReadOnly  
                    true,// Untitled 指定文件是否有标题。  
                    false // WithWindow 指定文件是否可见。  
                    ).toDispatch();  
  
            System.out.println("转换文档到PDF " + target);  
            File tofile = new File(target);  
            if (tofile.exists()) {  
                tofile.delete();  
            }  
            Dispatch.call(presentation,//  
                    "SaveAs", //  
                    target, // FileName  
                    ppSaveAsPDF);  
  
            Dispatch.call(presentation, "Close");  
            long end = System.currentTimeMillis();  
            System.out.println("转换完成..用时：" + (end - start) + "ms.");  
        } catch (Exception e) {  
            System.out.println("========Error:文档转换失败：" + e.getMessage());  
        } finally {  
            if (app != null) app.invoke("Quit");  
        }  
    }  
  
    public static void excel2pdf(String source, String target) {  
        System.out.println("启动Excel");  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)  
        try {  
        app.setProperty("Visible", false);  
        Dispatch workbooks = app.getProperty("Workbooks").toDispatch();  
        System.out.println("打开文档" + source);  
        Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method, new Object[]{source, new Variant(false),new Variant(false)}, new int[3]).toDispatch();  
        Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {  
        target, new Variant(57), new Variant(false),  
        new Variant(57), new Variant(57), new Variant(false),  
        new Variant(true), new Variant(57), new Variant(true),  
        new Variant(true), new Variant(true) }, new int[1]);  
        Variant f = new Variant(false);  
        System.out.println("转换文档到PDF " + target);  
        Dispatch.call(workbook, "Close", f);  
        long end = System.currentTimeMillis();  
        System.out.println("转换完成..用时：" + (end - start) + "ms.");  
        } catch (Exception e) {  
            System.out.println("========Error:文档转换失败：" + e.getMessage());  
        }finally {  
            if (app != null){  
                app.invoke("Quit", new Variant[] {});  
            }  
        }  
    } 
    

    public static void wordToHtml(String docfile, String htmlfile) {
        // 启动word
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        try { // 设置word不可见
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开word文件
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[] { docfile, new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch();
            // 作为html格式保存到临时文件
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    htmlfile, new Variant(WDFORMAT_WEBARCHIVE) }, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(doc, "Close", f);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[] {});
        }
    }
    
    



    /**
     * EXCEL转HTML
     * 
     * @param xlsfile
     *            EXCEL文件全路径
     * @param htmlfile
     *            转换后HTML存放路径
     */
    public static void excelToHtml(String xlsfile, String htmlfile) {
        // 启动excel
        ActiveXComponent app = new ActiveXComponent("Excel.Application");
        try {
            // 设置excel不可见
            app.setProperty("Visible", new Variant(false));
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            // 打开excel文件
            Dispatch excel = Dispatch.invoke(
                    excels,
                    "Open",
                    Dispatch.Method,
                    new Object[] { xlsfile, new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch();
            // 作为html格式保存到临时文件
            Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {
                    htmlfile, new Variant(EXFORMAT_WEBARCHIVE) }, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(excel, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[] {});
        }
    }
    
    public static void pptToHtml(String s, String s1) {  
/*        ComThread.InitSTA();  
        ActiveXComponent activexcomponent = new ActiveXComponent(  
                "PowerPoint.Application");  
        String s2 = s;  
        String s3 = s1;  
        boolean flag = false;  
        try {  
            Dispatch dispatch = activexcomponent.getProperty("Presentations")  
                    .toDispatch();  
            Dispatch dispatch1 = Dispatch.call(dispatch, "Open", s2,  
                    new Variant(-1), new Variant(-1), new Variant(0))  
                    .toDispatch();  
            Dispatch.call(dispatch1, "SaveAs", s3, new Variant(12));  
            Variant variant = new Variant(-1);  
            Dispatch.call(dispatch1, "Close");  
            flag = true;*/  
        
        ActiveXComponent xl = new ActiveXComponent("Powerpoint.Application");
        try {
            Dispatch.put(xl, "Visible", new Variant(true));
 //打开一个PPT，显示窗口，PPT的不显示就会报错，狂晕！
            Object workbooks = xl.getProperty("Presentations").toDispatch();
            Object workbook = Dispatch.call((Dispatch) workbooks, "Open",
                   s).toDispatch();
            Dispatch.invoke((Dispatch) workbook, "SaveAs", Dispatch.Method,
                   new Object[] { s1, new Variant(20) }, new int[1]);
        
        } catch (Exception exception) {  
            System.out.println("|||" + exception.toString());  
        } finally {  
            xl.invoke("Quit", new Variant[] {});
            xl = null; 
        }  
        //return flag;  
    }  

    public static void main(String[] args) {

       //JacobUtil.pptToHtml("d:/2/Test.ppt", "d:/1/Test.mht");
//        for (int i = 18; i < 36; i++) {     
//            //些路径test为实际存在的目录，s后面为要另存为的文件名  
//            String toFile="d:\\1\\s"+i;  
//            excelToPDF("d:/2/附件1——旅游人员名.xlsx", toFile,i);  
//        }
        
        JacobUtil.ppt2pdf("d:/2/Test.ppt", "d:/1/Test.pdf");
    }
}
