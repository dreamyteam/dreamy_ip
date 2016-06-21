package com.dreamy.utils;

import org.joda.time.DateTime;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Description: 时间工具类
 */
public class TimeUtils {

    public static final String FORMAT_YMDA = "yyyy-MM-dd";

    /**
     * 按照一定格式将时间转换成字符串格式
     *
     * @param pattern 字符串格式默认yyyy/MM/dd
     * @param date    时间
     * @return
     */
    public static String toString(String pattern, Date date) {
        return new SimpleDateFormat(pattern == null ? "yyyy/MM/dd" : pattern).format(date == null ? new Date() : date);
    }


    public static Date getDate(Date date) {
        String time = toString(FORMAT_YMDA, date);

        return getDateByStr(time, FORMAT_YMDA);
    }

    /**
     * 时间字符串转换为date类型
     *
     * @param date    字符串时间
     * @param pattern 字符串格式
     * @return
     */
    public static Date getDateByStr(String date, String pattern) {
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(pattern)) {
            try {
                return new SimpleDateFormat(pattern).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 时间转换进秒转换为（hh:mm:ss）格式
     *
     * @param seconds 时间（单位秒）
     * @return
     */
    public static String secToTime(long seconds) {
        String timeStr = ConstStrings.EMPTY;
        long hour = 0l;
        long minute = 0l;
        long second = 0l;
        if (seconds <= 0)
            return "00:00:00";
        else {
            minute = seconds / 60;
            if (minute < 60) {
                second = seconds % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = seconds - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String secToTime(int seconds) {
        return secToTime((long) seconds);
    }

    public static String unitFormat(int i) {
        return unitFormat((long) i);
    }

    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + i;
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 转换（hh:mm:ss）的时间为秒数
     *
     * @param timeStr
     */
    public static int timeStrToSec(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return 0;
        }
        Integer sec = 0;
        int timeStep = 1;
        String[] times = timeStr.split(":");
        int len = times.length;
        for (int i = 0; i < len; i++) {
            sec += (Integer.valueOf(times[len - i - 1]) * timeStep);
            timeStep = (i + 1) * 60;
        }
        return sec;
    }

    /**
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withTime(23, 59, 59, 999).toDate();
    }

    /**
     * 获取指定日期前后天数的最迟时间
     *
     * @param date
     * @param delta 前后日期差
     * @return
     */
    public static Date ceiling(Date date, int delta) {
        if (date == null) {
            return null;
        }
        return new DateTime(getDateEnd(date)).plusDays(delta).toDate();
    }

    public static Date ceiling(Date date) {
        if (date == null) {
            return null;
        }
        return getDateEnd(date);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal;
    }

    /**
     * 获取距离今天前后几天的最迟时间
     *
     * @param delta
     * @return
     */
    public static Date ceiling(int delta) {
        return ceiling(new Date(), delta);
    }

    /**
     * 获取指定日期前后天数的最早时间
     *
     * @param date
     * @param delta 前后天数差
     * @return
     */
    public static Date floor(Date date, int delta) {
        return new DateTime(getStartDateOfDay(date)).plusDays(delta).toDate();
    }

    /**
     * 获取最早时间
     *
     * @param
     * @return
     */
    public static Date getStartDateOfDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 获取与当天前后几天的最早时间
     *
     * @param delta
     * @return
     */
    public static Date floor(int delta) {
        return floor(new Date(), delta);
    }

    /**
     * 把时间清零，获得当前日期的清零时间
     *
     * @param date
     * @return
     */
    public static Date floor(Date date) {
        return getStartDateOfDay(date);
    }

    /**
     * 获取与指定日期前后几天的最早时间
     *
     * @param delta
     * @return
     */
    public static Date floorer(Date date, int delta) {
        return floor(date, delta);
    }


    /**
     * 获取是星期几,用0,1,3，4，5，6，7表示
     */
    public static int getWeekOfDate(Date date) {
        return (new DateTime(date).getDayOfWeek());
    }

    /**
     * 比较两个日期是否为在同一月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMoth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return Boolean.FALSE;
        }
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);
        if (d1.monthOfYear().get() == d2.monthOfYear().get()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断两日期是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return Boolean.FALSE;
        }
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);
        if (d1.dayOfMonth().get() == d2.dayOfMonth().get()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取当前时间前后几天
     *
     * @param delta 前后天数，支持正负数
     * @return
     */
    public static Date appointed(int delta) {
        Calendar cal = getCalendar(new Date());
        if (delta != 0) {
            cal.add(Calendar.DATE, delta);
        }
        return cal.getTime();
    }

    /**
     * 获取
     *
     * @param statisDate
     * @return
     */
    public static Date getStatisDate(Date statisDate) {
        return new DateTime(statisDate == null ? new Date() : statisDate).plusDays(-1).withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 把时间date是时分秒归零
     *
     * @param date
     * @return
     */
    public static Date toDayTime(Date date) {
        if (date == null) {
            date = new Date();
        }
        return new DateTime(date).withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 判断指定时间是否是周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        int index = new DateTime(date).getDayOfWeek();
        if (index == 6 || index == 7) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断指定时间是否是周末
     *
     * @param
     * @return
     */
    public static String getYearMonthDay(DateTime dt) {
        String m = "";
        String d = "";

        int year = dt.getYear();
        int month = dt.getMonthOfYear();
        int day = dt.getDayOfMonth();
        if (month < 10) {
            m = "0" + month;

        } else {
            m = String.valueOf(month);
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = String.valueOf(day);
        }
        return year + m + d;

    }

    /**
     * @param date
     * @param pattern
     * @param locale
     * @return Date
     * @Description: 根据方言和模式解析日期字符串
     * @author yaojf
     * @date 2015年5月20日 下午4:16:19
     */
    public static Date getDateByStrAndLocale(String date, String pattern,
                                             Locale locale) {
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(pattern)
                && locale != null) {
            try {
                return new SimpleDateFormat(pattern, locale).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param date
     * @param pattern
     * @param locale
     * @return String
     * @Description: 根据方言和模式解析日期
     * @author yaojf
     * @date 2015年5月21日 上午11:23:29
     */
    public static String getStrByDateAndLocale(Date date, String pattern,
                                               Locale locale) {
        if (date != null && StringUtils.isNotEmpty(pattern)
                && locale != null) {
            DateFormat dateFormat = new SimpleDateFormat(pattern, locale);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateFormat.format(date);
        }
        return null;
    }

    public static boolean hourCompareForLess(String a, String b) {
        if (HHmmToMinute(a) < HHmmToMinute(b)) {
            return true;
        }
        return false;
    }

    /**
     * 将时间格式为hh:mm的字符串转换成分钟数
     *
     * @param time
     * @return
     */
    public static int HHmmToMinute(String time) {
        if (StringUtils.isNotEmpty(time)) {
            String[] strs = time.split(":");
            if (strs.length == 2) {
                return Integer.valueOf(strs[0]) * 60 + Integer.valueOf(strs[1]);
            }
        }
        return 0;
    }


    public static boolean check(Date date1, Date date2) {
        boolean result = false;

        if (date1.getTime() > date2.getTime()) {
            result = true;
        } else if (date1.getTime() < date2.getTime()) {
            result = false;

        }
        return result;

    }

    public static long diff(Date date1, Date date2) {
        long result = 0;
        result = date2.getTime() - date1.getTime();
        return result;

    }

    public static Date transferLongToDate(Long millSec) {
        Date date = new Date(millSec);
        return date;
    }


    public static void main(String[] args) {
        System.out.println(transferLongToDate(System.currentTimeMillis()));
//        // DateTime dt = new DateTime();
//        // System.out.println("" + dt.getMonthOfYear());
//        // System.out.println(getDateByStr("2015-04-20","yy-mm-dd"));
//        // Date a=new Date();
//        // System.out.println(a);
//        // System.out.println((getDateByStr(null, "yyyyMMdd")));
////		System.out.println(HHmmToMinute("10:02"));
//        System.out.println(FileUtils.readFile(new File("/Users/jared/.duotin_monitor/monitor.properties")));
    }

}
