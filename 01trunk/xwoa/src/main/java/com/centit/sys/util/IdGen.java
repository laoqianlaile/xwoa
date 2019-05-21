package com.centit.sys.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author Ghost
 * @create 2016年2月18日
 * @version
 */
public class IdGen {
    private static SecureRandom random = new SecureRandom();
    private static final String CH = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//数字+字母组合
    private static final String NU = "0123456789";//数字
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 使用SecureRandom随机生成Long. 
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
    
    /**
     * 随机产生指定位数的字符串
     * @param count
     * @return
     */
    public static String randomString(int count){
        char[] rands = new char[count]; 
        for (int i = 0; i < rands.length; i++) 
        { 
            int rand = (int) (Math.random() * CH.length()); 
            rands[i] = CH.charAt(rand); 
        } 
       
      return new String(rands);
    }
    /**
     * 随机产生指定位数的数字组合
     * @param count
     * @return
     */
    public static String randomNumber(int count){
        char[] rands = new char[4]; 
        for (int i = 0; i < rands.length; i++) 
        { 
            int rand = (int) (Math.random() * NU.length()); 
            rands[i] = NU.charAt(rand); 
        } 
       System.out.println(new String(rands));
      return new String(rands);
    }
}
