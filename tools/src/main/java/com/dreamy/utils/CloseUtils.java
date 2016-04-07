package com.dreamy.utils;

import java.io.Closeable;

/**
 * Created by wangyongxing on 16/4/1.
 */
public final class CloseUtils {

    /**
     * 继承Closeable的对象执行关闭操作
     *
     * @param closeable
     */
    public static void closeQuiet(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }
}
