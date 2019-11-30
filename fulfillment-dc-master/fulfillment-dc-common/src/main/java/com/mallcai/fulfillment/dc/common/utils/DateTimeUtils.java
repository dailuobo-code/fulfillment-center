package com.mallcai.fulfillment.dc.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期/时间工具类
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class DateTimeUtils {

    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 获取指定时间 前 n 天日期
     */
    public static Date getPrevDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        return calendar.getTime();
    }

    /**
     * 格式化为某一天的起点
     */
    public static Date formatDateStart(Date date) {
        if (null == date) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * 格式化为某一天的终点
     */
    public static Date formatDateEnd(Date date) {
        if (null == date) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }

    public static String getNowDateStr() {
        return formatDate(getNow(), DATE_PATTERN);
    }

    public static String getDateStr(Date date) {
        return formatDate(date, DATE_PATTERN);
    }

    /**
     * Date to String
     */
    public static String formatDate(Date date, String formatStyle) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
            String formatDate = sdf.format(date);
            return formatDate;
        } else {
            return "";
        }
    }

    /**
     * 获取现在时刻
     */
    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 增加指定天数
     */
    public static Date addDate(Date beginDate, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }

    /**
     * 将LocalDateTime转换成Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date localDate2Date(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime2Date(localDateTime);
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

}
