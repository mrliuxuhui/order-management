package com.flyingwillow.restaurant.shiro.cache;

import com.flyingwillow.restaurant.service.IRawRedisService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public class RedisCacheManager implements CacheManager,Destroyable {

    // fast lookup by name map
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    private IRawRedisService rawRedisService;

    /**
     * The Redis key prefix for caches
     */
    private String keyPrefix;

    public IRawRedisService getRawRedisService() {
        return rawRedisService;
    }

    public void setRawRedisService(IRawRedisService rawRedisService) {
        this.rawRedisService = rawRedisService;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(null==rawRedisService){
            throw new NullPointerException("rawRedisService is null.");
        }
        Cache c = caches.get(name);

        if (c == null) {

            // create a new cache instance
            if(StringUtils.isNotBlank(keyPrefix)){
                c = new RedisCache<K, V>(keyPrefix,rawRedisService);
            }else{
                c = new RedisCache<K,V>(rawRedisService);
            }

            // add it to the cache collection
            caches.put(name, c);
        }
        return c;
    }

    @Override
    public void destroy() throws Exception {

    }
}
