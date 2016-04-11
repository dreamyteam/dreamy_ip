package com.dreamy.service.cache;

import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Service
public class RedisCache implements CacheService {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

    @Resource
    private RedisClientTemplate redisClientTemplate;


    @Override
    public Boolean put(String key, Object o) {
        if (StringUtils.isNotEmpty(key) && o != null) {
            try {
                redisClientTemplate.set(key, o.toString());
            } catch (Throwable e) {
                log.error("set usersession error :" + e);
            }
        }
        return null;
    }

    @Override
    public void set(String key, Object value, int expire) {
        redisClientTemplate.setex(key,expire,value.toString());

    }

    @Override
    public Object get(String key) throws Exception {
        if (StringUtils.isNotEmpty(key)) {
            try {
                Object s = redisClientTemplate.get(key);
                if (s == null) {
                    s= redisClientTemplate.get(key);
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
