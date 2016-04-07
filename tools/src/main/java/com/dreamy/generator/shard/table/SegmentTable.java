package com.dreamy.generator.shard.table;

/**
 * Created by wangyongxing on 16/3/31.
 */

import com.dreamy.utils.DataSource.ShardType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SegmentTable {
    /**
     * 数据库表名
     *
     * @return
     */
    String table() default "";

    /**
     * 分表策略，默认取模
     *
     * @return
     */
    ShardType shardType() default ShardType.MOD;

    /**
     * 分表字段
     *
     * @return
     */
    String shardBy() default "";

    /**
     * 分表数量
     *
     * @return
     */
    int tableNum() default 0;

    /**
     * 全局自曾字段对应key
     *
     * @return
     */
    String shardKey() default "";
}

