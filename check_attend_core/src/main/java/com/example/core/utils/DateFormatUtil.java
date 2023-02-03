package com.example.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式工具
 *
 * @author cybxiyi
 * @since 2021/7/9 4:24 下午
 */
public class DateFormatUtil {
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    // 时间戳格式, 如20210709
    private static final SimpleDateFormat DATETIME_STAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");


    private static String format(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    public static String formatDateTimestamp(Date date) {
        return format(date, DATETIME_STAMP_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT);
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    public static String formatTime(Date date) {
        return format(date, TIME_FORMAT);
    }
}
