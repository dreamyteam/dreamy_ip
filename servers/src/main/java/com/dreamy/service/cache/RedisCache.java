package com.dreamy.service.cache;

import com.dreamy.utils.ConstUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class RedisCache implements CacheService {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

    private int timeout = ConstUtils.Session.USERSESSION_TIMEOUT;
    @Resource
    private RedisClientTemplate redisClientTemplate;

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private String buildKey(String id) {
        return prefix == null ? id : prefix + id;
    }

    private String buildRemenberKey(String id) {
        return prefix == null ? id : prefix + "rm_" + id;
    }

    @Override
    public Boolean put(String key, Object o) throws Exception {
        if (StringUtils.isNotEmpty(key) && o != null) {
            try {
                redisClientTemplate.set(buildKey(key), o.toString());
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public Object get(String key) throws Exception {
        if (StringUtils.isNotEmpty(key)) {
            try {
                Object s = redisClientTemplate.get(buildKey(key));
                if (s == null) {
                    s= redisClientTemplate.get(buildRemenberKey(key));
                }
                return s;
            } catch (Throwable e) {
                log.error("get usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public Boolean remove(String key) throws Exception {
        redisClientTemplate.del(key);
        return true;

    }

    @Override
    public Boolean contains(String key) throws Exception {
        return  redisClientTemplate.exists(key);

    }

    @Override
    public Boolean putList(String key, Object data) throws Exception {
        redisClientTemplate.lpush(key,data.toString());
        return true;

    }

    @Override
    public List<Object> getList(String key) throws Exception {
        return null;
    }

    @Override
    public Boolean clearAll() throws Exception {
        return true;
    }

    public void clear(String key){
        if (StringUtils.isNotEmpty(key)) {
            try {
                redisClientTemplate.del(key);
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
    }
}
