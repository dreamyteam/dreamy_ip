package com.dreamy.generator.shard.table;

/**
 * Created by wangyongxing on 16/3/31.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PartitionedTable {
    /**
     * 数据源
     *
     * @return
     */
    String value() default "default";
}

