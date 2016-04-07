package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/5.
 */
public interface CacheUtil<T> {
    /**
     *
     * @return
     */
    public T get();

    public String key();
}
