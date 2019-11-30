package com.mallcai.fulfillment.pe.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期工具类
 * @author: chentao
 * @create: 2019-08-23 21:23:12
 */
public final class DateUtil {

    public static final long                           ONE_DAY                    = 86400000;

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String                         DATETIME_FORMAT            = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> dateTimeThreadLocal        = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    DATETIME_FORMAT);
        }
    };
    /**
     * yyyyMMddHHmmss
     */
    public static final String                         LDATETIME_FORMAT           = "yyyyMMddHHmmss";

    private static final ThreadLocal<SimpleDateFormat> lDateTimeThreadLocal       = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    LDATETIME_FORMAT);
        }
    };

    /**
     * yyyyMMddHHmmss
     */
    public static final String                         LLDATETIME_FORMAT          = "yyyyMMddHHmmssSSS";

    private static final ThreadLocal<SimpleDateFormat> llDateTimeThreadLocal      = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    LLDATETIME_FORMAT);
        }
    };
    /**
     * yyyy-MM-dd
     */
    public static final String                         DATE_FORMAT                = "yyyy-MM-dd";

    private static final ThreadLocal<SimpleDateFormat> dateThreadLocal            = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    DATE_FORMAT);
        }
    };
    /**
     * yyyyMMdd
     */
    public static final String                         LDATE_FORMAT               = "yyyyMMdd";

    private static final ThreadLocal<SimpleDateFormat> lDateThreadLocal           = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    LDATE_FORMAT);
        }
    };

    /**
     * HHmmss
     */
    public static final String                         LSHORTTIME_FORMAT          = "HHmmss";

    private static final ThreadLocal<SimpleDateFormat> lShortTimeThreadLocal      = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    LSHORTTIME_FORMAT);
        }
    };
    /**
     * HH:mm:ss
     */
    public static final String                         SHORTTIME_FORMAT           = "HH:mm:ss";

    public static final String                         SHORTTIME_RORMAT_S           = "HHmmssSSS";

    private static final ThreadLocal<SimpleDateFormat> shortTimeThreadLocal       = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    SHORTTIME_FORMAT);
        }
    };

    public static final String                         YEAR_MONTH_FORMAT          = "yyyyMM";

    private static final ThreadLocal<SimpleDateFormat> yearMonthFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    YEAR_MONTH_FORMAT);
        }
    };




    /**
     * yyyy-MM-dd
     */
    public static final String                         XDATE_FORMAT                = "yyyy/MM/dd";

    private static final ThreadLocal<SimpleDateFormat> xdateThreadLocal            = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    XDATE_FORMAT);
        }
    };


    public static SimpleDateFormat getYearMonthDate() {
        return yearMonthFormatThreadLocal.get();
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public static int getLdate(Date date) {
        return Integer.parseInt(formatLShortDate(date));
    }

    /**
     * @param date
     * @return HHmmss
     */
    public static int getLShortTime(Date date) {
        return Integer.parseInt(formatLShortTime(date));
    }

    /**
     * @param date
     * @return yyyyMMddHHmmss
     */
    public static long getLDateTime(Date date) {
        return Long.parseLong(formatLDateTime(date));
    }

    public static boolean isBefore(Date date1, Date date2) {
        return date2.getTime() - date1.getTime() > 0;
    }

    public static boolean isEqual(Date date1, Date date2) {
        return date2.getTime() - date1.getTime() == 0;
    }

    public static boolean isBetween(Date date, Date dateFrom, Date dateTo){
        return date.getTime() >= dateFrom.getTime() && date.getTime() <= dateTo.getTime();
    }

    private static Date add(Date date, int zoom, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (amount == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(zoom, amount);
        return cal.getTime();
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    private static Date paser(DateFormat format, String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误:" + dateString);
        }
    }

    public static String format(DateFormat format, Date date) {
        if (date == null)
            return null;
        return format.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date parseDateTime(String date) {
        return paser(dateTimeThreadLocal.get(), date);
    }

    public static Date parseLDateTime(String date) {
        return paser(lDateTimeThreadLocal.get(), date);
    }

    public static Date parseXDateTime(String date){
        return paser(xdateThreadLocal.get(),date);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return paser(dateThreadLocal.get(), date);
    }

    public static Date parseLDate(String date) {
        return paser(lDateThreadLocal.get(), date);
    }

    public static Date parseDate(String date, String format) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return paser(new SimpleDateFormat(format), date);
    }

    /**
     * @param date
     * @return HH:mm:ss
     */
    public static String formatShortTime(Date date) {
        return format(shortTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return format(dateTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(dateThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyy/MM/dd
     */
    public static String formatXDate(Date date) {
        return format(xdateThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return format
     */
    public static String formatDate(Date date, String format) {
        return format(new SimpleDateFormat(format), date);
    }

    /**
     *
     * @param date
     * @return  yyyyMM
     */
    public static String formateLYearMonth(Date date){return format(yearMonthFormatThreadLocal.get(),date);}

    /**
     * @param date
     * @return yyyyMMdd
     */
    public static String formatLDate(Date date) {
        return format(lDateThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyyMMddHHmmss
     */
    public static String formatLDateTime(Date date) {
        return format(lDateTimeThreadLocal.get(), date);
    }

    /**
     *
     * @param date
     * @return  yyyyMMddHHmmssSSS
     */
    public static String formatLLDateTime(Date date) {
        return format(llDateTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return HHmmss
     */
    public static String formatLShortTime(Date date) {
        return format(lShortTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public static String formatLShortDate(Date date) {
        return format(lDateThreadLocal.get(), date);
    }

    public static Date yesterdayStart() {
        return new Date(todayStart().getTime() - ONE_DAY);
    }

    public static Date yesterdayEnd() {
        return new Date(todayStart().getTime() - 1);
    }

    public static boolean isYesterday(Date startDay) {
        boolean isYesterday = false;
        Date yesterdayStart = yesterdayStart();
        Date yesterdayEnd = yesterdayEnd();
        if (yesterdayStart.getTime() <= startDay.getTime() && yesterdayEnd.getTime() >= startDay.getTime()) {
            isYesterday = true;
        }
        return isYesterday;
    }

    /**
     * The very beginning of today.
     *
     * @return
     */
    public static Date todayStart() {
        return dayStart(new Date());
    }

    public static Date todayEnd() {
        return dayEnd(new Date());
    }

    public static Date dayStart(Date someday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(someday);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 时间间隔(天数),date1-date2
     * @param date1
     * @param date2
     * @return 负数表示date1在date2前
     */
    public static int intervalDays(Date date1, Date date2) {
        Calendar cNow = org.apache.commons.lang3.time.DateUtils.toCalendar(dayStart(date1));
        Calendar cReturnDate = org.apache.commons.lang3.time.DateUtils.toCalendar(dayStart(date2));
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / ONE_DAY);
    }

    public static Date dayEnd(Date someday) {
        return new Date(dayStart(someday).getTime() + ONE_DAY - 1);
    }

    public static Date zero() {
        return new Date(0);
    }

    public static Date firstDayOfMonth(Date date) {
        Calendar calendar = DateUtils.toCalendar(dayStart(date));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 对时间做月份差值计算，获取计算时间那月的第一天
     *
     * @param date  基准时间
     * @param month 多少月
     * @return yyyy-MM-dd
     */
    public static Date getFirstDayOfMonthWithAddMonths(Date date, int month) {
        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     *
     * 功能描述: 获取与某天间隔多少天的零点
     *
     * @param someDay：某天
     * @param interval：间隔天数，负数表示在某天之前，正数表示在某天之后
     * @return
     * @see [相关类/方法](可选)
     * @since V1.0
     */
    public static Date getDayStartIntervalToSomeDay(Date someDay, int interval){

        Calendar cal = Calendar.getInstance();

        cal.setTime(someDay);
        cal.add(Calendar.DAY_OF_MONTH, interval);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static String getIntervalToSomeDay(Date someDay, int interval){
        return formatLDate(getDayStartIntervalToSomeDay(someDay,interval));
    }

    /**
     * 获取 leftDate - rightDate 的天数 (忽略小时、分钟数等)
     * @param leftDate
     * @param rightDate
     * @return
     */
    public static int getDateIntervalDays(Date leftDate,Date rightDate){
        return (int) ((dayStart(leftDate).getTime() - dayStart(rightDate).getTime())/(24*3600*1000));
    }

    public static Date parseLong2Date(Long timestamp, String sFmt) {
        if ((timestamp == null) || timestamp == 0) {
            return null;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat(sFmt);
            Long time = new Long(timestamp);
            String d = format.format(time);

            return format.parse(d);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date getStartOfDate(Date date) {
        return DateUtil.parseDate(DateUtil.formatDate(date,DATE_FORMAT));
    }

    public static Date parseLDateWithStartIntervalToSomeDay(Date date, int interval){
        return parseDate(formatLDate(getDayStartIntervalToSomeDay(date, interval)), DateUtil.LDATE_FORMAT);
    }

    public static Date now() {
        return new Date();
    }

    // 获取当前0点时间
    public static Date getCurrentZeroTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
