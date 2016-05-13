package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/1.
 */
public final class ObjectUtils {

    /**
     * 对象为空时赋默认值
     *
     * @param value
     *            目标对象
     * @param def
     *            默认值
     * @return
     */
    public static <T> T nullToDefault(T value, T def) {
        return value == null ? def : value;
    }

}