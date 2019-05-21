package com.centit.sys.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 自定义el表达式
 * 
 * @author lay
 * @create 2016年3月29日
 * @version
 */
public class PageExpressionUtil {
    /**
     * 获取文件名后缀
     * @param fileName
     * @return
     */
   public static String getFileSuffix(String fileName){
      if(StringUtils.isEmpty(fileName))
          return "";
      String suffix = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
      return suffix;
   }
   
   /**
    * 除法运算，向上取整
    * @param d1
    * @param d2
    * @return
    */
   public static String roundupAfterDivide(String strD1, String strD2){
       String res = "0";
       if(StringUtils.isBlank(strD1)||StringUtils.isBlank(strD2)){
           return res;
       }
       int d1 = Integer.parseInt(strD1);
       int d2 = Integer.parseInt(strD2);
      if(d2 == 0 || d1 == 0){
          return res;
      }
      if(d1%d2 == 0){
          res = String.valueOf(d1/d2);
      }else{
          res = String.valueOf(d1/d2 + 1);
      }
      return res;
   }
   
   /**
    * 让Hmtl字符原样显示在浏览器上
    * @param s
    * @return
    */
   public static String convertHtmlTo(String s){
       if(s!=null){
           return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
       }
       return s;
   }
   
}
