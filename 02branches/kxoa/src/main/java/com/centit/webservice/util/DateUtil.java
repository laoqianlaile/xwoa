package com.centit.webservice.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.centit.support.utils.DatetimeOpt;

public class DateUtil {
    private static String datetimePattern = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 依据起始时间 时间周期 时间周期类型 推算结束时间
     * @param starttime
     * @param cycletime  时间周期（1,2,3）
     * @param cycletimetype 时间周期类型（0全部、1周、2月、3季度、4年）
     * @return
     * @throws ParseException 
     * @throws NumberFormatException 
     */
    public static final long getTime(String starttime, String cycletime,
            String cycletimetype) throws NumberFormatException, ParseException {
        Date time=null;
       
        if(StringUtils.isNotBlank(cycletime)&&StringUtils.isNotBlank(cycletimetype))
        {
            if(StringUtils.isNumeric(cycletime)&&StringUtils.isNumeric(cycletimetype)){
                
                if("1".equals(cycletimetype)){
                   time=DatetimeOpt.addDays(convertToDate(starttime,datetimePattern), Integer.valueOf(cycletimetype)*Integer.valueOf(cycletime)*7); 
                }
                else if("2".equals(cycletimetype)){
                    time=DatetimeOpt.addMonths(convertToDate(starttime,datetimePattern), Integer.valueOf(cycletimetype)*Integer.valueOf(cycletime)); 
                 }
                else if("3".equals(cycletimetype)){
                    time=DatetimeOpt.addMonths(convertToDate(starttime,datetimePattern), Integer.valueOf(cycletimetype)*Integer.valueOf(cycletime)*3); 
                 }
                else if("4".equals(cycletimetype)){
                    time=DatetimeOpt.addYears(convertToDate(starttime,datetimePattern), Integer.valueOf(cycletimetype)*Integer.valueOf(cycletime)*3); 
                 }
            }
        }
        
        return time.getTime();
    }
    
    
    /**
     * 时间戳转string格式的时间
     * @param mill
     * @param aMask
     * @return
     */
    public static final String convertToString(long mill,String aMask){
        String strs="";
        Date date = null;    
        SimpleDateFormat sdf = null;
        try {
        date=new Date(mill);
        sdf = new SimpleDateFormat(aMask) ;    
        strs=sdf.format(date);
        } catch (Exception e) {
        e.printStackTrace();
        }
        return strs;
        }
    /**
     * 时间戳转string格式的时间
     * @param mill
     * @param aMask
     * @return
     */
    public static final String convertToString(String mill,String aMask){
        return convertToString(Long.valueOf(mill),aMask);
    }
 
    /**
     * 时间戳转Date格式的时间
     * @param mill
     * @param aMask
     * @return
     */
    public static final Date convertToDate(long mill,String aMask){
        Date strs=null;
        Date date = null;    
        SimpleDateFormat sdf = null;
        try {
        date=new Date(mill);
        sdf = new SimpleDateFormat(aMask) ;    
        strs=sdf.parse(sdf.format(date));
        } catch (Exception e) {
        e.printStackTrace();
        }
        return strs;
        }
    /**
     * 时间戳转Date格式的时间
     * @param mill
     * @param aMask
     * @return
     */
    public static final Date convertToDate(String mill,String aMask){
        if(StringUtils.isNotBlank(mill))
            return convertToDate(Long.valueOf(mill),aMask);
        else 
            return null;
    }
    public static void main(String[] args) {
        convertToDate("","yyyy-mm-dd");
    }
    /**
     * java.sql.Timestamp转换成java.util.Date
     * 
     * @param java
     *            .sql.Timestamp
     */
    public static final java.util.Date timestamp2Date(java.sql.Timestamp timestamp) {
        java.util.Date date = new java.util.Date(timestamp.getTime());
        return date;
    }
    
    /**
     * java.util.Date转换成java.sql.Timestamp
     * 
     * @param java
     *            .util.Date "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd HH:mm:ss.SSS"
     */
    public static final java.sql.Timestamp date2Timestamp(Date date) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }
    
    /**
     * 日期字符串转换成Timestamp日期
     * 
     * @param dateStr
     *            -日期字符串 "yyyy-MM-dd HH:mm:ss" 示例："2008-09-28 13:41:20"
     */
    public static final Timestamp dateStr2Timestamp(String dateStr) {
        Timestamp date = Timestamp.valueOf(dateStr);
        return date;
    }
    
    public static java.util.Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
