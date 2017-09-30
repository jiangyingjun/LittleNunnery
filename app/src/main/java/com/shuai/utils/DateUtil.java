package com.shuai.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String dateFormat(String sdate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = null;
        try {
            dateString = formatter.format(formatter.parse(sdate.replace("/", "-")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }


    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr;
        if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 将指定模板的时间类型转化成long型的
     *
     * @param sdate
     * @param fomat 将要进行转化的字符串的模板
     * @return
     * @throws ParseException
     */

    public static long dateToLong(String sdate, String fomat) throws ParseException {
//		Date d = new Date();
//		long t = d.getTime();
//
//
        // 将字符串类型转化成Date类型
        SimpleDateFormat sdf = new SimpleDateFormat(fomat);
        Date d2 = sdf.parse(sdate.replace("/", "-"));// 将String to Date类型
        long t3 = d2.getTime();
        return t3;
    }


    /**
     * 将long时间 转化为格式化时间
     *
     * @return
     */
    public static String getStandardTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return time <= 0 ? sdf.format(new Date()) : sdf.format(new Date(time));
    }

    /**
     * 将long时间 转化为格式化时间
     *
     * @return
     */
    public static String getFormatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return time <= 0 ? sdf.format(new Date()) : sdf.format(new Date(time));
    }

    /**
     * 得到当前的系统的时间 ，并按指定模板输出
     *
     * @return yyyy/MM/dd HH:mm:ss  yyyy-MM-dd HH:mm:ss:SS
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * 得到当前的系统的时间 ，并按指定模板输出
     *
     * @return yyyy
     */
    public static String getCurrentDateYear(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(new Date());
    }

    /**
     * 获取当天时间的0点时间
     *
     * @return
     */
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }


    /**
     * 获取当天时间24点时间
     *
     * @return
     */
    public static long getTimesnight() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 比较某时间点是否在某时间段
     */
    public static boolean commpareTime(long time, long startTime, long endTime) {

        if (time > startTime && time < endTime) {
            return true;
        }
        return false;

    }

    /**
     * 两个时间段相隔是否在一定的时间内，如两个时间是否相隔20分钟
     *
     * @param time      相隔的时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean intervalTime(long time, long startTime, long endTime) {
        if (startTime - endTime >= time) {
            return true;
        }
        return false;
    }


    /**
     * 获取系统当前时间(毫秒)
     *
     * @return
     */
    public static long getSystemTime() {
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }


    /**
     * 将标准时间格式转换为毫秒
     *
     * @param str
     * @return
     */
    public static long changeMilles(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millionSeconds;
        if (!TextUtils.isEmpty(str))
            try {
                millionSeconds = sdf.parse(str.replace("/", "-")).getTime();
                return millionSeconds;
            } catch (ParseException e) {
                e.printStackTrace();
            }// 毫秒
        return -1;
    }

}
