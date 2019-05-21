package com.centit.sys.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.support.utils.DatetimeOpt;

public class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    /* 日期格式 */
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    
    private static final String DATE_PATTERNWITHNODAY = "yyyy-MM";
    /* 时间格式 */
    private static final String TIME_PATTERN = "HH:mm:ss";
    /* 日期时间格式 */
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期-"yyyy-MM-dd"
     */
    public static final String getCurrentDate() {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        return df.format(date);
    }

    /**
     * 获取当前时间-"HH:mm:ss"
     */
    public static final String getCurrentTime() {
        DateFormat df = new SimpleDateFormat(TIME_PATTERN);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        return df.format(date);
    }

    /**
     * 获取当前日期和时间-"yyyy-MM-dd HH:mm:ss"
     */
    public static final String getCurrentDateTime() {
        DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        return df.format(date);
    }
    
    /**
     * 使用当前时间作为编号
     */
    public static final String getNumUseTime() {
        DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String time = df.format(date);
        time = time.replace("-", "");
        time = time.replace(":", "");
        time = time.replace(" ", "");
        return time;
    }

    /**
     * 日期字符串转换成日期
     * 
     * @param dateStr
     *            -日期字符串 示例："2008-09-28"、"2008-9-28"
     */
    public static final Date dateStr2Date(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 日期字符串转换成日期
     * 
     * @param dateStr
     *            -日期字符串 示例："2008-09"、"2008-9"
     */
    public static final Date dateStr2DateWithNoDay(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERNWITHNODAY);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期时间字符串转换成日期时间
     * 
     * @param dateStr
     *            -日期时间字符串，可以不带时间，返回的时间则为"00:00:00"。

     *            示例："2008-9-28 01:01:01"、"2008-09-28"、"2008-9-28"
     */
    public static final Date dateStr2DateTime(String dateStr) {
        // 如果只有日期，添加时间。

        if (8 == dateStr.length() || 9 == dateStr.length() || 10 == dateStr.length()) {
            dateStr += " 00:00:00";
        }
        DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期字符串转换成SQL日期
     * 
     * @param dateStr
     *            -日期字符串 "yyyy-MM-dd" 示例："2008-09-28"、"2008-9-28"
     */
    public static final java.sql.Date dateStr2SqlDate(String dateStr) {
        java.sql.Date date = java.sql.Date.valueOf(dateStr);
        return date;
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

    /**
     * 日期转换成字符串
     * 
     * @param date
     *            -日期
     * @param pattern
     *            -返回的字符串格式，如果为空，则为"yyyy-MM-dd"。

     */
    public static final String date2String(Date date, String pattern) {
        String sdfPattern = "";
        if (pattern == null) {
            sdfPattern = DATE_PATTERN;
        } else if ("".equals(pattern)) {
            sdfPattern = DATE_PATTERN;
        } else {
            sdfPattern = pattern;
        }
        DateFormat df = new SimpleDateFormat(sdfPattern);
        return df.format(date);
    }

    /**
     * java.util.Date转换成java.sql.Date
     * 
     * @param java
     *            .util.Date "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
     */
    public static final java.sql.Date date2SqlDate(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    /**
     * java.util.Date转换成java.sql.Timestamp
     * 
     * @param java
     *            .util.Date "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
     */
    public static final java.sql.Timestamp date2Timestamp(Date date) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * java.sql.Date转换成java.util.Date
     * 
     * @param java
     *            .sql.Date
     */
    public static final java.util.Date sqlDate2Date(java.sql.Date sqlDate) {
        java.util.Date date = new java.util.Date(sqlDate.getTime());
        return date;
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
     * 计算两个日期之间的天数差
     * 
     * @param beginDateStr
     *            -开始日期字符串，格式"yyyy-MM-dd"。

     * @param endDateStr
     *            -结束日期字符串，格式"yyyy-MM-dd"。

     */
    public static final long difDays(String beginDateStr, String endDateStr) {
        long days = 0;
        if( beginDateStr != null && !"".equals(beginDateStr) && endDateStr != null && !"".equals(endDateStr) ) {
            Date beginDate = DateUtil.dateStr2Date(beginDateStr);
            Date endDate = DateUtil.dateStr2Date(endDateStr);
            long beginDateLong = beginDate.getTime();
            long endDateLong = endDate.getTime();
            days = (long) ((endDateLong - beginDateLong) / (1000 * 60 * 60 * 24));
        }
        return days;
    }
    
    /**
     * 计算两个日期之间的月数差
     * 
     * @param beginDateStr
     *            -开始日期字符串，格式"yyyy-MM"。


     * @param endDateStr
     *            -结束日期字符串，格式"yyyy-MM"。


     */
    @SuppressWarnings("deprecation")
    public static final long difMonths(String beginDateStr, String endDateStr) {
        long month = 0;
        if( beginDateStr != null && !"".equals(beginDateStr) && endDateStr != null && !"".equals(endDateStr) ) {
            Date beginDate = DateUtil.dateStr2DateWithNoDay(beginDateStr);
            Date endDate = DateUtil.dateStr2DateWithNoDay(endDateStr);
            long beginDateYear = beginDate.getYear();
            long endDateYear = endDate.getYear();
            long beginDateMonth = beginDate.getMonth();
            long endDateMonth = endDate.getMonth();
            if(beginDateYear < endDateYear) {
                endDateMonth += 12; 
            }
            
            month = (long) ((endDateMonth - beginDateMonth));
        }
        return month;
    }

    /**
     * 计算两个日期时间之间的小时差
     * 
     * @param beginDateStr
     *            -开始日期时间字符串，格式"yyyy-MM-dd HH:mm:ss"。

     * @param endDateStr
     *            -结束日期时间字符串，格式"yyyy-MM-dd HH:mm:ss"。

     */
    public static final long difHours(String beginDateTimeStr, String endDateTimeStr) {
        long hours = 0;
        Date beginDateTime = DateUtil.dateStr2DateTime(beginDateTimeStr);
        Date endDateTime = DateUtil.dateStr2DateTime(endDateTimeStr);
        long beginDateTimeLong = beginDateTime.getTime();
        long endDateTimeLong = endDateTime.getTime();
        hours = (long) ((endDateTimeLong - beginDateTimeLong) / (1000 * 60 * 60));
        return hours;
    }
    
    /**
     * 计算两个日期时间之间的分钟差
     * 
     * @param beginDateStr
     *            -开始日期时间字符串，格式"yyyy-MM-dd HH:mm:ss"。


     * @param endDateStr
     *            -结束日期时间字符串，格式"yyyy-MM-dd HH:mm:ss"。


     */
    public static final long difMinutes(String beginDateTimeStr, String endDateTimeStr) {
        long minutes = 0;
        Date beginDateTime = DateUtil.dateStr2DateTime(beginDateTimeStr);
        Date endDateTime = DateUtil.dateStr2DateTime(endDateTimeStr);
        long beginDateTimeLong = beginDateTime.getTime();
        long endDateTimeLong = endDateTime.getTime();
        minutes = (long) ((endDateTimeLong - beginDateTimeLong) / (1000 * 60));
        return minutes;
    }

    /**
     * 计算日期前后N个月的日期

     * 
     * @param dateStr
     *            -参考时间，即需要计算的日期。

     * @param difMonth
     *            -月份间隔，正数（后面的月份）、负数（前面的月份）
     */
    public static final String AddMonths(String dateStr, int difMonths) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String returnDate = "";
        try {
            Date date = df.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, difMonths);
            date = cal.getTime();
            returnDate = df.format(date);
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }
        return returnDate;
    }

    /**
     * 计算日期前后N天的日期
     * 
     * @param dateStr
     *            -参考时间，即需要计算的日期。

     * @param difMonth
     *            -月份间隔，正数（后面的月份）、负数（前面的月份）
     */
    public static final String AddDays(String dateStr, int difDays) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String returnDate = "";
        try {
            Date date = df.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, difDays);
            date = cal.getTime();
            returnDate = df.format(date);
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }
        return returnDate;
    }
    /**
     * 计算当期月份的下一个月
     * @param aDate - the date pattern the string is in
     * @param aMask  a formatted string representation of the date
     * @return
     */
    public static final String convertDateToString( Date aDate,String aMask) {
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            String sMask = (aMask ==null || "".equals(aMask))?"yyyy-MM-dd":aMask;
            SimpleDateFormat df = new SimpleDateFormat(sMask);
            returnValue = df.format(aDate);
        }
        return AddDays(returnValue,1);
    }
    //计算前一天
    public static final String convertPrevDateToString( Date aDate,String aMask) {
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            String sMask = (aMask ==null || "".equals(aMask))?"yyyy-MM-dd":aMask;
            SimpleDateFormat df = new SimpleDateFormat(sMask);
            returnValue = df.format(aDate);
        }
        return AddDays(returnValue,-1);
    }
    public static final String AddMinutes(String dateStr, int difMinutes) {
        DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
        String returnDate = "";
        try {
            Date date = df.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, difMinutes);
            date = cal.getTime();
            returnDate = df.format(date);
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }
        return returnDate;
    }
    
    public static final String AddHours(String dateStr, int difMinutes) {
        DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
        String returnDate = "";
        try {
            Date date = df.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, difMinutes);
            date = cal.getTime();
            returnDate = df.format(date);
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }
        return returnDate;
    }
    
    //获取上个月最后一天

    public static String getPreviousMonthEnd() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);// 减一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天

        lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天

        str = sdf.format(lastDate.getTime());
        return str;
      }
    
/*   //获取当月第一天
       public static String getCurrentMonthOfDay() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
            lastDate.set(Calendar.HOUR_OF_DAY, 0);
            lastDate.set(Calendar.MINUTE, 0);
            lastDate.set(Calendar.SECOND, 0);
            str = sdf.format(lastDate.getTime());
            return str;
      }*/
       
      /**
     * 获取当年的第一天
     * 
     * @param year
     * @return
     */
    public static String getCurrentYearOfDay() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    } 
      /**
     * 获取当年的第一天
     * 
     * @param year
     * @return
     */
    public static String getCurrentMonthOfDay() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        
        long moths=difMonths(getCurrentYearOfDay(), DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
        if(moths<=5){
            currentYear=currentYear-1;
        }
        return getYearFirst(currentYear);
    }
 // 当前日期向前推N个月
    public static String getCurrentMonthforwoard(int month) {
        return AddMonths(
                DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"), -month);
    }
    /**
     * 获取当年的最后一天
     * 
     * @param year
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * 
     * @param year
     *            年份
     * @return Date
     */
    public static String getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return DatetimeOpt.convertDateToString(currYearFirst, "yyyy-MM-dd");
    }

    /**
     * 获取某年最后一天日期
     * 
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }
    
      //获取当月第一天精确到秒
       public static String getCurrentMonthOfDayTime() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
            lastDate.set(Calendar.HOUR_OF_DAY, 0);
            lastDate.set(Calendar.MINUTE, 0);
            lastDate.set(Calendar.SECOND, 0);
            str = sdf.format(lastDate.getTime());
            return str;
      }
        

    /**
     * 获取该月的最后一天

     * 
     * @param
     * datebegin-需要计算的日期,"yyyy-MM-dd"或者"yyyy-MM"，例如"2008-1-1"、"2008-1"、"2008-01"
     * @return 该月的最后一天 示例：MoveMonthEnd("2008-1-1") 返回"2008-01-31"
     *         MoveMonthEnd("2008-1")、MoveMonthEnd("2008-01") 返回"2008-01-31"
     * 
     * modify by 梁大伟

     */
    public static String MoveMonthEnd(String datebegin) { // 函数返回:该月的最后一天

        if (6 == datebegin.length() || 7 == datebegin.length()) {
            datebegin += "-01";
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datebegin);
            datebegin = new SimpleDateFormat("yyyy-MM-dd").format(date);
            int year = Integer.valueOf(datebegin.substring(0, 4)).intValue();
            int month = Integer.valueOf(datebegin.substring(5, 7)).intValue();
            int day;
            switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                day = 31;
                break;
            }
            case 2: {
                if (year % 4 == 0 && year % 100 != 0)
                    day = 29;
                else
                    day = 28;
                break;
            }
            default:
                day = 30;
            }
            String addDate = datebegin.substring(0, 7) + "-"
                    + String.valueOf(day);
            return addDate;
        } catch (Exception e) {
            System.out.println("无法计算.AddYear函数出错。" + e.getMessage());
            return null;
        }
    }
    
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }
    
    public static String getXzbh(String xzjc) {
        if (StringUtils.isNotBlank(xzjc)) {
            if (xzjc.startsWith("玄")) {
                return "320103";//玄武区
            } else if (xzjc.startsWith("白")) {
                return "320104";//白下区
            } else if (xzjc.startsWith("鼓")) {
                return "320107";//鼓楼区
            } else if (xzjc.startsWith("建")) {
                return "320106";//建邺区
            } else if (xzjc.startsWith("雨")) {
                return "";
            } else if (xzjc.startsWith("江")) {
                return "";
            } else if (xzjc.startsWith("六")) {
                return "";
            } else if (xzjc.startsWith("栖")) {
                return "";
            } else if (xzjc.startsWith("大")) {
                return "";
            } else if (xzjc.startsWith("高")) {
                return "320102";//高新开发区
            } else if (xzjc.startsWith("化")) {
                return "";
            } else if (xzjc.startsWith("原")) {
                return "320130";
            } else if (xzjc.startsWith("玄")) {
                return "320105";//秦淮区
            } else if (xzjc.startsWith("玄")) {
                return "";//
            }
        }
        return "";
    }
    
    public static String[] getSearch(String search) {
        String facilityname = search;
        String xzjc = "";
        String xzjc2 = "";
        if (search.contains("玄武")) {
            xzjc2 += "or (o.facilityxzjc like '%玄武%') ";
            facilityname = facilityname.replace("玄武区", "");
            facilityname = facilityname.replace("玄武", "");
        }
        if (search.contains("建邺")) {
            xzjc2 += "or ( o.facilityxzjc like '%建邺%') ";
            facilityname = facilityname.replace("建邺区", "");
            facilityname = facilityname.replace("建邺", "");
        }
        if (search.contains("浦口")) {
            xzjc2 += "or ( o.facilityxzjc like '%浦口%') ";
            facilityname = facilityname.replace("原浦口区", "");
            facilityname = facilityname.replace("浦口区", "");
            facilityname = facilityname.replace("浦口", "");
        }
        if (search.contains("六合")) {
            xzjc2 += "or ( o.facilityxzjc like '%六合%') ";
            facilityname = facilityname.replace("六合区", "");
            facilityname = facilityname.replace("六合", "");
        }
        if (search.contains("秦淮")) {
            xzjc2 += "or ( o.facilityxzjc like '%秦淮%') ";
            facilityname = facilityname.replace("秦淮区", "");
            facilityname = facilityname.replace("秦淮", "");
        }
        if (search.contains("白下")) {
            xzjc2 += "or ( o.facilityxzjc like '%白下%') ";
            facilityname = facilityname.replace("白下区", "");
            facilityname = facilityname.replace("白下", "");
        }
        if (search.contains("开发区")) {
            xzjc2 += "or ( o.facilityxzjc like '%开发区%') ";
            facilityname = facilityname.replace("经济开发区", "");
            facilityname = facilityname.replace("开发区", "");
        }
        if (search.contains("化工")) {
            xzjc2 += "or ( o.facilityxzjc like '%化工%') ";
            facilityname = facilityname.replace("化工", "");
        }
        if (search.contains("园区")) {
            xzjc2 += "or ( o.facilityxzjc like '%园区%') ";
            facilityname = facilityname.replace("园区", "");
        }
        if (search.contains("高新区")) {
            xzjc2 += "or ( o.facilityxzjc like '%高新区%') ";
            facilityname = facilityname.replace("高新区", "");
        }
        if (search.contains("大厂区")) {
            xzjc2 += "or ( o.facilityxzjc like '%大厂区%') ";
            facilityname = facilityname.replace("原大厂区", "");
            facilityname = facilityname.replace("大厂区", "");
        }
        
        if (search.contains("排水")) {
            xzjc2 += "or ( o.facilityxzjc like '%排水%') ";
            facilityname = facilityname.replace("市排水公司", "");
            facilityname = facilityname.replace("排水处", "");
        }
        
        if (search.contains("建设工程")) {
            xzjc2 += "or ( o.facilityxzjc like '%建设工程%') ";
            facilityname = facilityname.replace("市建设工程管理处", "");
            facilityname = facilityname.replace("建设工程", "");
        }
        
        if (search.contains("市政工程")) {
            xzjc2 += "or ( o.facilityxzjc like '%市政工程%') ";
            facilityname = facilityname.replace("市市政工程管理处", "");
            facilityname = facilityname.replace("市政工程", "");
        }
        
        if (search.contains("隧桥公司")) {
            xzjc2 += "or ( o.facilityxzjc like '%隧桥公司%') ";
            facilityname = facilityname.replace("市隧桥公司", "");
            facilityname = facilityname.replace("隧桥公司", "");
        }
        
        if (search.contains("路灯管理处")) {
            xzjc2 += "or ( o.facilityxzjc like '%路灯管理处%') ";
            facilityname = facilityname.replace("市路灯管理处", "");
            facilityname = facilityname.replace("路灯管理处", "");
        }
        
        if (search.contains("管线")) {
            xzjc2 += "or ( o.facilityxzjc like '%管线%') ";
            facilityname = facilityname.replace("城北厂外管线", "");
            facilityname = facilityname.replace("仙林厂外管线", "");
            facilityname = facilityname.replace("城东厂外管线", "");
            facilityname = facilityname.replace("江心洲厂外管线", "");
            facilityname = facilityname.replace("管线", "");
        }
        
        if (search.contains("锁金村地区")) {
            xzjc2 += "or ( o.facilityxzjc like '%锁金村地区%') ";
            facilityname = facilityname.replace("锁金村地区", "");
        }
        
        if (StringUtils.isNotBlank(xzjc2)) {
            xzjc2 = xzjc2.substring(2);
            xzjc2 = " and (" + xzjc2 + ")";
        }
        
        if (search.contains("道路")) {
            xzjc += "or ( o.flagname like '%道路%') ";
            facilityname = facilityname.replace("道路", "");
        }
        if (search.contains("桥梁")) {
            xzjc += "or ( o.flagname like '%桥梁%') ";
            facilityname = facilityname.replace("桥梁", "");
        }
        if (search.contains("河道")) {
            xzjc += "or ( o.flagname like '%河道%') ";
            facilityname = facilityname.replace("河道", "");
        }
        if (search.contains("水闸")) {
            xzjc += "or ( o.flagname like '%水闸%') ";
            facilityname = facilityname.replace("水闸", "");
        }
        if (search.contains("泵站")) {
            xzjc += "or ( o.flagname like '%泵站%') ";
            facilityname = facilityname.replace("泵站", "");
        }
        if (search.contains("排水")) {
            xzjc += "or ( o.flagname like '%排水%') ";
            facilityname = facilityname.replace("排水", "");
        }
        if (search.contains("管道")) {
            xzjc += "or ( o.flagname like '%管道%') ";
            facilityname = facilityname.replace("管道", "");
        }
        if (search.contains("路灯")) {
            xzjc += "or ( o.flagname like '%路灯%') ";
            facilityname = facilityname.replace("路灯", "");
        }
        if (search.contains("主次干道")) {
            xzjc += "or ( o.flagname like '%主次干道%') ";
            facilityname = facilityname.replace("主次干道", "");
        }
        if (search.contains("背街小巷")) {
            xzjc += "or ( o.flagname like '%背街小巷%') ";
            facilityname = facilityname.replace("背街小巷", "");
        }
        if (search.contains("隧道")) {
            xzjc += "or ( o.flagname like '%隧道%') ";
            facilityname = facilityname.replace("隧道", "");
        }
        if (StringUtils.isNotBlank(xzjc)) {
            xzjc = xzjc.substring(2);
            xzjc = " and (" + xzjc + ")";
        }
        if (StringUtils.isNotBlank(xzjc2)) {
            xzjc += xzjc2;
        }
        return new String[]{facilityname, xzjc};
    }
    
    /**
     * 检查是否date2在date1日期以前
     * 
     * @param d1
     * @param d2
     * @return true date2在date1前
     */
    public static boolean isBefore(Date d1, Date d2) {
        boolean ret = false;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c2.before(c1)) {
            ret = true;
        }
        return ret;
    }
    /**
     * d2是否在d1之后
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isAfter(Date d1, Date d2) {
        boolean ret = false;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c2.after(c1)) {
            ret = true;
        }
        return ret;
    }
}
