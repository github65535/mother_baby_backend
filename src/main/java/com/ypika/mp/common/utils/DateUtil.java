package com.ypika.mp.common.utils;

import com.ypika.mp.domain.exception.UserDefinedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    //时间格式
    public static final String FORMAT_SHORT = "yyyy-MM-dd";
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_STRING = "yyyy年MM月dd日";
    public static final String FORMAT_MONTH = "yyyy年MM月";

    //每月30天
    public static final Integer THIRTY_DAY = 30;


    /**
     * 根据时间格式得到当前时间
     *
     * @param patten 时间格式
     * @return 当前时间字符串
     */
    public static String getNowDate(String patten) {
        return new SimpleDateFormat(patten).format(new Date());
    }

    /**
     * 得到yyyy-MM-dd HH:mm:ss 字符串时间
     *
     * @return 字符串时间
     */
    public static String getDefaultDate() {
        return new SimpleDateFormat(FORMAT_LONG).format(new Date());
    }

    /**
     * 当前时间的时间戳
     *
     * @return 时间戳
     */
    public static Long getLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * 根据date得到yyyy-MM-dd HH:mm:ss 字符串时间
     *
     * @param date 时间
     * @return 字符串时间
     */
    public static String getDefaultDate(Date date) {
        return new SimpleDateFormat(FORMAT_LONG).format(date);
    }

    /**
     * 两个时间所差小时数
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 分钟数
     */
    public static long diffHours(Date date1, Date date2) {
        return diffMinute(date1, date2) / 60;
    }


    /**
     * 两个时间所差分钟数
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 分钟数
     */
    public static long diffMinute(Date date1, Date date2) {
        return diffSecond(date1, date2) / 60;
    }

    /**
     * 两个时间所差秒数
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 秒数
     */
    public static long diffSecond(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 1000;
    }

    /**
     * 当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 转换时间格式
     *
     * @param date    字符串时间
     * @param pattern 时间格式
     * @return date时间
     */
    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 时间类型转换yyyy-MM-dd HH:mm:ss 字符串时间
     *
     * @param date date时间
     * @return 字符串时间
     */
    public static String parseDateToStr(Date date) {
        if (StrUtil.isNull(date)) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_LONG).format(date);
    }

    /**
     * 根据时间格式转换时间
     *
     * @param date   date时间
     * @param format 时间格式
     * @return 字符串时间
     */
    public static String parseDateToStr(Date date, String format) {
        if (StrUtil.isNull(date)) {
            return "";
        }
        if (StrUtil.isNull(format)) {
            format = FORMAT_LONG;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 字符串时间转换date
     *
     * @param time       字符串时间
     * @param timeFormat 时间格式
     * @return date时间
     */
    public static Date parseToDate(String time, String timeFormat) {
        try {
            return new SimpleDateFormat(timeFormat).parse(time);
        } catch (Exception e) {
            throw new UserDefinedException("时间格式化出错");
        }
    }

    /**
     * 时间戳转换date时间
     *
     * @param millis 时间戳
     * @return date时间
     */
    public static Date timeStamp2Date(Long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return c.getTime();
    }

    /**
     * 时间戳转换字符串时间
     *
     * @param millis  时间戳
     * @param formats 时间格式
     * @return 字符串时间
     */
    public static String timeStamp2DateString(Long millis, String formats) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return new SimpleDateFormat(formats).format(c.getTime());
    }

    /**
     * 时长转分钟
     *
     * @param timeLength 时长
     * @return 分钟
     */
    public static String getTimeByTimeLength(Integer timeLength) {
        String time;
        int hh;
        int mm;
        int ss;
        if (timeLength < 3600) {
            mm = timeLength / 60;
            ss = timeLength % 60;
            time = mm + "'" + ss;
        } else {
            hh = timeLength / 60 / 60;
            mm = timeLength / 60 % 60;
            ss = timeLength % 60;
            time = hh + "'" + mm + "'" + ss;
        }
        return time;
    }

    /**
     * 指定日期增加天数
     *
     * @param date     date时间
     * @param dayCount 天数
     * @return date时间
     */
    public static Date addDay(Date date, int dayCount) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_YEAR, dayCount);
        return instance.getTime();
    }

    /**
     * 计算两个时间相差天数
     *
     * @param beginDate 开始时间
     * @param endDate    结束时间
     * @return 相差天数
     */
    public static Integer getIntervalDays(Date beginDate, Date endDate) {
        if (StrUtil.isNull(beginDate) || StrUtil.isNull(endDate)) {
            return null;
        }
        return Math.toIntExact((endDate.getTime() / 86400000L - beginDate.getTime() / 86400000L));
    }

    /**
     * 两天的相差数
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 相差数
     */
    public static Integer subtractDay(Date begin, Date end) {
        return getIntervalDays(begin, end);
    }

    /**
     * @return
     * @Description: 获取当天剩余秒数
     */

    public static long getRemainSecondsOneDay() {
        Date currentDate = new Date();
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(Calendar.DAY_OF_MONTH, 1);
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        long seconds = (midnight.getTime().getTime() - currentDate.getTime()) / 1000;
        return seconds;
    }

    /**
     * @return
     * @Description: 获取距离当前到某一天结束剩余秒数
     */

    public static long getRemainSecondsOneDay(int day) {
        Date currentDate = new Date();
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(Calendar.DAY_OF_MONTH, day + 1);
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        long seconds = (midnight.getTime().getTime() - currentDate.getTime()) / 1000;
        return seconds;
    }


    /**
     * @return
     * @Description: 获取距离下一天结束剩余秒数
     */

    public static long getRemainSecondsNextDay() {
        Date currentDate = new Date();
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(Calendar.DAY_OF_MONTH, 2);
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        long seconds = (midnight.getTime().getTime() - currentDate.getTime()) / 1000;
        return seconds;
    }

    /**
     * 获取当天凌晨0点0分0秒Date
     *
     * @return
     */
    public static Date getTodayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
    }

    /**
     * 获取当天晚上23点59分59秒Date
     *
     * @return
     */
    public static Date getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date endOfDate = calendar.getTime();
        return endOfDate;
    }

    /**
     * 获取当月的天数
     *
     * @param time
     * @return
     */
    public static int getDaysOfMonth(String time) {
        Date date = parseToDate(time, "yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到昨天的日期（"yyyy-MM-dd"）
     *
     * @return 昨天的日期
     */
    public static String getYesterdayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat(FORMAT_SHORT).format(calendar.getTime());
    }


    /**
     * 获取指定日期晚上23点59分59秒Date
     *
     * @return
     */
    public static Date getSomeDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date endOfDate = calendar.getTime();
        return endOfDate;
    }
}
