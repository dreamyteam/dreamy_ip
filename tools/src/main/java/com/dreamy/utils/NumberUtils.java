package com.dreamy.utils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/5/5.
 */
public class NumberUtils {
    private static final Pattern PATTERN_NUMBER = Pattern.compile("^[0-9]+$");

    /**
     * 对象转换为int类型
     *
     * @param value
     *            对象
     * @return
     */
    public static int parseIntQuietly(Object value) {
        return parseIntQuietly(value, 0);
    }

    /**
     * 对象转换为int
     *
     * @param value
     *            对象
     * @param def
     *            默认值
     * @return
     */
    public static int parseIntQuietly(Object value, int def) {
        if (value != null) {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            try {
                return Integer.valueOf(value.toString());
            } catch (Throwable e) {
            }
        }

        return def;
    }

    /**
     * 字符串小数转换成int
     *
     * @param decimal
     * @return
     */
    public static int valueOf(String decimal) {
        int index = decimal.indexOf(".");
        if (index > 0) {
            decimal = decimal.substring(0, index);
        }
        return Integer.valueOf(decimal);
    }

    /**
     * 判断字符串是不是全部由数字组成
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return PATTERN_NUMBER.matcher(str).find();
    }


    /**
     * 格式化浮点数
     * @param format
     * @param decimal
     * @return
     */
    public static String numberFormat(DecimalFormat format, Double decimal) {
        if (format == null || decimal == null) {
            throw new IllegalArgumentException("格式化类型和数据不能为空");
        }
        return format.format(decimal);
    }

    /**
     * 产生min－max之间的随机数
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max) {
        return new Random().nextInt(max-min+1)+min;
    }

    public static void main(String[] args) {
        System.out.println(randomInt(50000,100000));
    }
}
