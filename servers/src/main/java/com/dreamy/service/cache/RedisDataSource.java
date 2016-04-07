package com.dreamy.service.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by wangyongxing on 16/4/5.
 */
public interface RedisDataSource {
    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}