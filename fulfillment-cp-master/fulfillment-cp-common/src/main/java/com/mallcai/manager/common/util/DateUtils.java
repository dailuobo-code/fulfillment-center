package com.mallcai.manager.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangjingcheng
 */
public class DateUtils {

    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前时间的 下一天 凌晨时间
     *
     * @return
     */
    public static Date getEarlyMorning() {
        Calendar c = Calendar.getInstance();
        String currentDateString = DATE_FORMAT.format(new Date());
        Date date;
        try {
            date = DATE_FORMAT.parse(currentDateString);
        } catch (Exception e) {
            throw new RuntimeException("获取当前时间的下一天凌晨时间出错");
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天凌晨时间
     *
     * @return
     */
    public static Date getCurrentDayEarlyMorning() {
        Calendar c = Calendar.getInstance();
        String currentDateString = DATE_FORMAT.format(new Date());
        Date date;
        try {
            date = DATE_FORMAT.parse(currentDateString);
        } catch (Exception e) {
            throw new RuntimeException("获取当前时间的下一天凌晨时间出错");
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Date getCurrentDayLastTime() {
        Calendar c = Calendar.getInstance();
        String currentDateString = DATE_FORMAT.format(new Date());
        Date date;
        try {
            date = DATE_FORMAT.parse(currentDateString);
        } catch (Exception e) {
            throw new RuntimeException("获取当前时间的下一天凌晨时间出错");
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
}
