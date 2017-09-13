package com.flyingwillow.restaurant.shiro.cache;

import com.flyingwillow.restaurant.service.IRawRedisService;
import com.flyingwillow.restaurant.util.serializer.KryoSerializeUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public class RedisCache<K,V> implements Cache<K,V> {


    private final static String DEFAULT_PREFIX = "SHIRO-REDIS-CACHE:";
    private String prefix = DEFAULT_PREFIX;

    private IRawRedisService rawRedisService;
    private StringRedisSerializer keySerializer = new StringRedisSerializer();

    public RedisCache(IRawRedisService rawRedisService) {
        this.rawRedisService = rawRedisService;
    }

    public RedisCache(String prefix, IRawRedisService rawRedisService) {
        this.prefix = prefix;
        this.rawRedisService = rawRedisService;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public IRawRedisService getRawRedisService() {
        return rawRedisService;
    }

    public void setRawRedisService(IRawRedisService rawRedisService) {
        this.rawRedisService = rawRedisService;
    }

    @Override
    public V get(K key) throws CacheException {
        try {
            byte[] v = rawRedisService.get(serializeKey(key));
            return KryoSerializeUtil.deserialize(v);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CacheException(e);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        try {
            rawRedisService.set(serializeKey(key), KryoSerializeUtil.serialize(value));
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        try {
            V previous = get(key);
            rawRedisService.del(serializeKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            Set<byte[]> keys = rawRedisService.keys(getKeyPattern().getBytes());
            rawRedisService.del(keys.toArray(new byte[keys.size()][]));
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        try {
            Set<byte[]> keys = rawRedisService.keys(getKeyPattern().getBytes());
            return keys.size();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set<K> keys() {
        try {
            Set<byte[]> keys = rawRedisService.keys(getKeyPattern().getBytes());
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            } else {
                Set<K> newKeys = new HashSet<K>();
                for (byte[] key : keys) {
                    newKeys.add((K) key);
                }
                return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            Set<byte[]> keys = rawRedisService.keys(getKeyPattern().getBytes());
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    V value = get((K) key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            }else {
                return null;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    private byte[] serializeKey(K key){
        if (key instanceof String) {
            String preKey = this.prefix + key;
            return preKey.getBytes();
        } else if(key instanceof PrincipalCollection){
            String preKey = this.prefix + key.toString();
            return preKey.getBytes();
        }else{
            return KryoSerializeUtil.serialize(key);
        }
    }


    private String getKeyPattern(){
        return this.getPrefix()+"*";
    }
}
