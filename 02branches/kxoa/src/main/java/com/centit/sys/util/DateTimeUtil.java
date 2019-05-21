package com.centit.sys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.centit.support.utils.DatetimeOpt;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class DateTimeUtil {
    /**
     * 
     * @param beginDate
     * @param endDate
     * @return 计算这个周期中的天数, 包括 beginTime，endTime,并且和endDate 比较得出今天，昨天，本周，上周，本月，上月
     */
    public static String smartCalcSpanDays(java.util.Date beginDate,
            java.util.Date endDate) {
        String defaultDateTag = "今天";// 返回字符串 下个月 ，下周，明天，今天，昨天，本周，上周，本月，上月
        int spanDays = DatetimeOpt.calcSpanDays(
                DatetimeOpt.truncateToDay(beginDate),
                DatetimeOpt.truncateToDay(endDate));// 算出俩个日期的时间差yyyy-mm-dd
        int nWeekDay = DatetimeOpt.getDayOfWeek(endDate);// 一周中的第几天，所以他会受到
                                                         // 第一天是星期几 的影响。
        if (nWeekDay == 0) {// 周日为0
            nWeekDay = 7;
        }

        // int bWeekDay= DatetimeOpt.getDayOfWeek(beginDate);
        if (beginDate.getTime() - endDate.getTime() < 0) {
            // spanDays 过去的时间
            if (spanDays > 0 && spanDays < 4) {// 昨天，前天
                defaultDateTag = getSpanDaysPased(spanDays);
            } else if (spanDays > 0 && spanDays <= nWeekDay) {// 本周
                defaultDateTag = getDayOfWeekShort(beginDate);
            } else if (spanDays > nWeekDay && spanDays < (nWeekDay + 8)) {// 上周
                defaultDateTag = "上周" + getDayOfWeekSimple(beginDate);
            } else if (spanDays >= (nWeekDay + 8)) {
                defaultDateTag = "更早";
            }
        } else if (beginDate.getTime() - endDate.getTime() >= 0) {
            // spanDays 未来的时间
            if (spanDays > 0 && spanDays < 4) {// 明天，后天
                defaultDateTag = getSpanDaysFuture(spanDays);
            } else if (spanDays > 0 && spanDays <= (8 - nWeekDay)) {// 本周
                defaultDateTag = getDayOfWeekShort(beginDate);
            } else if (spanDays > (8 - nWeekDay) && spanDays < (15 - nWeekDay)) {// 本周
                defaultDateTag = "下周" + getDayOfWeekSimple(beginDate);
            } else if (spanDays >= (15 - nWeekDay)) {
                defaultDateTag = "";
            }

        }

        return defaultDateTag;
    }

    /***
     * 未来某一天
     * 
     * @param spanDays
     */
    public static String getSpanDaysFuture(int spanDays) {
        String dateTag = "今天";// 返回字符串 下个月 ，下周，明天，今天，昨天，本周，上周，本月，上月
        if (spanDays == 1) {
            dateTag = "今天";
        }
        if (spanDays == 2) {
            dateTag = "明天";
        }
        if (spanDays == 3) {
            dateTag = "后天";
        }
        return dateTag;
    }

    /***
     * 過去某一天
     * 
     * @param spanDays
     */
    public static String getSpanDaysPased(int spanDays) {
        String dateTag = "今天";// 返回字符串 下个月 ，下周，明天，今天，昨天，本周，上周，本月，上月
        if (spanDays == 1) {
            dateTag = "今天";
        }
        if (spanDays == 2) {
            dateTag = "昨天";
        }
        if (spanDays == 3) {
            dateTag = "前天";
        }
        return dateTag;
    }

    public static String getDayOfWeekSimple(java.util.Date date) {
        String[] weeklist = { "日", "一", "二", "三", "四", "五", "六", "", };
        return weeklist[DatetimeOpt.getDayOfWeek(date)];
    }

    public static String getDayOfWeekShort(java.util.Date date) {
        String[] weeklist = { "周日", "周一", "周二", "周三", "周四", "周五", "周六", "", };
        return weeklist[DatetimeOpt.getDayOfWeek(date)];
    }

    /**
     * 周一，下周一，上周一
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String getDecOfWeek(java.util.Date beginDate,
            java.util.Date endDate) {
        String[] weeklist = { "周日", "周一", "周二", "周三", "周四", "周五", "周六", "", };
        return weeklist[DatetimeOpt.getDayOfWeek(beginDate)];
    }

    public static String smartDays(java.util.Date beginDate,
            java.util.Date endDate) {
        String defaultDateTag = "今天";// 返回字符串 下个月 ，下周，明天，今天，昨天，本周，上周，本月，上月
        int spanDays = DatetimeOpt.calcSpanDays(beginDate, endDate);// 算出俩个日期的时间差
        int nWeekDay = DatetimeOpt.getDayOfWeek(endDate);// 一周中的第几天，所以他会受到
                                                         // 第一天是星期几 的影响。

        // int bWeekDay= DatetimeOpt.getDayOfWeek(beginDate);
        if (beginDate.getTime() - endDate.getTime() < 0) {
            // spanDays 过去的时间
            if (spanDays > 0 && spanDays < 4) {// 昨天，前天
                defaultDateTag = getSpanDaysPased(spanDays);
            } else if (spanDays > 0 && spanDays <= nWeekDay) {// 本周
                defaultDateTag = getDayOfWeekShort(beginDate);
            } else if (spanDays > nWeekDay && spanDays < (nWeekDay + 7)) {// 上周
                defaultDateTag = "上周" + getDayOfWeekSimple(beginDate);
            } else if (spanDays >= (nWeekDay + 7)) {
                defaultDateTag = "更早";
            }
        } else if (beginDate.getTime() - endDate.getTime() >= 0) {
            // spanDays 未来的时间
            if (spanDays > 0 && spanDays < 4) {// 明天，后天
                defaultDateTag = getSpanDaysFuture(spanDays);
            } else if (spanDays > 0 && spanDays <= (7 - nWeekDay)) {// 本周
                defaultDateTag = getDayOfWeekShort(beginDate);
            } else if (spanDays > (7 - nWeekDay) && spanDays < (14 - nWeekDay)) {// 本周
                defaultDateTag = "下周" + getDayOfWeekSimple(beginDate);
            } else if (spanDays >= (14 - nWeekDay)) {
                defaultDateTag = "更早";
            }

        }

        return defaultDateTag;
    }

    public static String getWeekFirstDate() {
        Calendar cal = Calendar.getInstance();
        if ("周日".equals(getDayOfWeekShort(new Date(System.currentTimeMillis())))) {
            cal.add(Calendar.DATE, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    public static String getWeekLastDate() {
        if ("周日".equals(getDayOfWeekShort(new Date(System.currentTimeMillis())))) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System
                    .currentTimeMillis()));
        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
            cal.add(Calendar.WEEK_OF_YEAR, 1);// 增加一个星期，才是我们中国人理解的本周日的日期
            return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        }
    }
}
