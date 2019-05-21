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
	
/*	 //获取当月第一天
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
    
    /**
     * 获取当前时间是第几周
     * @return
     */
    public static int getWeekNoOfYear() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    /**
     * 根据年份和周，获取该周第一天日期
     * 以周日为每周第一天
     * @param date
     * @return
     */

    public static Date getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.getTime();
    }

    /**
     * 根据年份和周，获取该周最后一天日期
     *  以周日为每周第一天
     * @param date
     * @return
     */
    public static Date getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }
    /**
     * 获取一年最大周数，也是共多少周
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.YEAR, year);
       return ca.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
}
