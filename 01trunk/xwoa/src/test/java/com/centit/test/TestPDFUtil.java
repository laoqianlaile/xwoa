package com.centit.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.centit.sys.util.IdGen;
import com.centit.webservice.util.PDFUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.itextpdf.text.DocumentException;

public class TestPDFUtil {
    @Test
   public void testHtmlToPdfByItext(){
       try {
        PDFUtil.convertHtmlToPdf("d://fgw.html", IdGen.uuid()+".pdf");
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
   }
    
   
    @Test
   public void testMergingPdf() throws IOException, DocumentException{
       List<String> paths = new ArrayList<String>();
        paths.add("d://fgw.pdf");
        paths.add("d://会议纪要.pdf");
        try {
            PDFUtil.mergingPdf(paths, "合并.pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   
    @Test
   public void testCopyAndAesDecrypt() throws FileNotFoundException, DocumentException, IOException{
        PDFUtil.encryptPdf("d://会议纪要.pdf", "d://会议纪要加密.pdf", "123");
   }
    @Test
   public void testConvertPdfToSwf(){
       try {
        PDFUtil.convertPdfToSwf("D:/pdf2swf.exe", 
                   "D:/oa.home/upload/pdf/87c862b7e3564323a8c0448069cd0f44.pdf", 
                   "D:/oa.home/upload/pdf/87c862b7e3564323a8c0448069cd0f44.swf");
    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }
  
    @Test
  public void testMergingPdf4Temp(){
      List<String> pdfPaths = Lists.newArrayList("D:\\oa.home\\upload\\temp\\pdf\\lu.pdf",
              "D:\\oa.home\\upload\\temp\\pdf\\luz.pdf");
      List<Map<String,String>> importPageRangeArr = new ArrayList<Map<String,String>>();
      Map<String,String> range = Maps.newHashMap();
      range.put("1", "1");
      importPageRangeArr.add(range);
      Map<String,String> range2 = Maps.newHashMap();
      range2.put("1", "~");
      importPageRangeArr.add(range2);
      try {
       String p = PDFUtil.mergingPdf4Temp(pdfPaths, importPageRangeArr);
       System.out.println(p);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (DocumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }
  
}
