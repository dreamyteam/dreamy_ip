//package com.dreamy.service.cache;
//
//import com.dreamy.utils.CacheUtil;
//import com.dreamy.utils.ResourcesLoad;
//
//import java.util.List;
//
///**
// * Created by wangyongxing on 16/4/1.
// */
//public abstract class DefaultCacheManager {
//    /**
//     * 缓存对象
//     */
//    private static CacheService cacheService = null;
//    /**
//     * index key
//     */
//    private final String fetchCache = getClass().getName();
//    protected final String fetchCacheIndexKey = fetchCache + "_index_key";
//
//    static {
//        if (ResourcesLoad.getDevMode()) {
//            //开发模式
//            cacheService = new LocalCache();
//        } else {
//            cacheService = new RedisCache();
//        }
//    }
//
//
//    /**
//     * 清除缓存
//     */
//    public Boolean clear() {
//        try {
//            List<Object> keys = cacheService.getList(fetchCacheIndexKey);
//            if (keys != null) {
//                cacheService.remove(fetchCacheIndexKey);
//                for (Object key : keys) {
//                    if (key instanceof String) {
//                        cacheService.remove(key.toString());
//                    }
//                }
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static CacheService getCacheService() {
//        return cacheService;
//    }
//}