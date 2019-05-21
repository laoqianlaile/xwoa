package com.centit.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.centit.webservice.util.AsposeWordsUtil;

public class TestAsposeWordsUtil {
    @Test
    public void testWordToPdf(){
             try {
                AsposeWordsUtil.convertToPdf("d://luz.doc", "luz.pdf",true);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    
    @Test
    public void testMergingWords(){
        List<String> list = new ArrayList<String>();
        list.add("d://拟文单.doc");
        list.add("d://会议纪要.doc");
        try {
            AsposeWordsUtil.mergingWord(list, "合并.doc", true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testConvertToPng(){
        try {
            AsposeWordsUtil.convertToTransparentPngImage("d:/luz.docx", "d:/1.png");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void testGetPageCount(){
        try{
            int i = AsposeWordsUtil.getDocPageCount("D:/oa.home/upload/temp/word/测试1.doc");
            System.out.println(i);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
