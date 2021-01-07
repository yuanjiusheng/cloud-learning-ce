package com.yjs.cloud.learning.learn.common.util;

import com.yjs.cloud.learning.learn.common.web.GlobalException;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具
 * @author Bill.lai
 * @since 2019-07-03
 */
public class DateUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * localDate转String
     * @param datetime 时间
     * @return 转换后的时间
     */
    public static LocalDateTime parse(String datetime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return LocalDateTime.parse(datetime, df);
    }

    /**
     * localDate转String
     * @param date 时间
     * @return 转换后的时间
     */
    public static LocalDate parseLocalDate(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(yyyy_MM_dd);
        return LocalDate.parse(date, df);
    }

    /**
     * localDate转String
     * @param localDate 时间
     * @return 转换后的时间
     */
    public static String format(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(yyyy_MM_dd);
        return df.format(localDate);
    }

    /**
     * localDateTime转String
     * @param localDateTime 时间
     * @return 转换后的时间
     */
    public static String format(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return df.format(localDateTime);
    }

    /**
     * 时间转字符串
     * @param date 时间
     * @param pattern 格式
     * @return 转换后的字符串
     */
    public static String parseString(Date date, String... pattern) {
        //如果pattern为空
        if(pattern == null || pattern.length == 0){
            pattern = new String[]{DEFAULT_PATTERN};
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern[0]);
        //转换成字符串
        return formatter.format(date);
    }

    /**
     * 字符串转时间
     * @param value 字符串时间
     * @param pattern 格式
     * @return 转换后的时间
     */
    public static Date parseDate(String value, String... pattern) {
        //如果pattern为空
        if(pattern == null || pattern.length == 0){
            pattern = new String[]{DEFAULT_PATTERN};
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern[0]);
        ParsePosition pos = new ParsePosition(0);
        //转换成时间
        return formatter.parse(value, pos);
    }

    /**
     * 格式转换
     * @param date 时间
     * @param pattern 格式
     * @return 转换后的时间
     */
    public static Date format(Date date, String pattern){
        return parseDate(parseString(date, pattern), pattern);
    }

    /**
     * 加减日期
     * @param date 时间
     * @param amount 加减天数 +5为加5天 -5减五天
     * @param pattern 时间格式化格式
     * @return 处理后的时间
     */
    public static Date add(Date date, int amount, String... pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        //如果pattern为空
        if(pattern == null || pattern.length == 0){
            pattern = new String[]{DEFAULT_PATTERN};
        }
        return format(calendar.getTime(), pattern[0]);
    }

    /**
     * 时间格式化转换
     * @param date 时间
     * @param pattern 时间格式
     * @return 转换后的时间
     */
    public static Long parseLong(Date date, String... pattern) {
        //如果pattern为空
        if(pattern == null || pattern.length == 0){
            pattern = new String[]{yyyyMMdd};
        }
        String dateString = parseString(date, pattern[0]);
        if(StringUtils.isEmpty(dateString)){
            throw new GlobalException("时间转换错误");
        }
        return Long.valueOf(dateString);
    }

    /**
     * 两个时间的时间差
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 时间差
     */
    public static Long diff(Date startDate, Date endDate){
        if(startDate == null){
            return 0L;
        }
        if(endDate == null){
            return 0L;
        }
        return ((endDate.getTime() - startDate.getTime()) / (24*3600*1000));
    }

}
