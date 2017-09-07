package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/6.
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean exists(final String key) {
        Boolean result = (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.exists(redisTemplate.getKeySerializer().serialize(key));
            }
        });
        return result;
    }

    @Override
    public Long expire(final String key, final int seconds) {
        Long result = (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.expire(redisTemplate.getKeySerializer().serialize(key),seconds);
            }
        });
        return result;
    }

    @Override
    public String type(final String key) {
        String result = (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.type(redisTemplate.getKeySerializer().serialize(key));
            }
        });
        return result;
    }

    @Override
    public String randomKey() {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.randomKey();
            }
        });
    }

    @Override
    public String rename(final String oldkey, final String newkey) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                redisConnection.rename(serializer.serialize(oldkey),serializer.serialize(newkey));
                return null;
            }
        });
    }

    @Override
    public Boolean renamenx(final String oldkey, final String newkey) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisConnection.renameNX(serializer.serialize(oldkey),serializer.serialize(newkey));
            }
        });
    }

    @Override
    public Long del(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(redisTemplate.getKeySerializer().serialize(key));
            }
        });
    }

    @Override
    public Long del(final String... keys) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                if(null==keys||keys.length<1){
                    return 0;
                }
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                byte[][] bKeys = new byte[keys.length][];
                for(int i=0;i<keys.length;i++){
                    bKeys[i] = serializer.serialize(keys[i]);
                }
                return redisConnection.del(bKeys);
            }
        });
    }

    @Override
    public Set<String> keys(final String pattern) {
        return (Set<String>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.keys(redisTemplate.getKeySerializer().serialize(pattern));
            }
        });
    }

    @Override
    public void set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(redisTemplate.getKeySerializer().serialize(key),redisTemplate.getValueSerializer().serialize(value));
                return null;
            }
        });
    }

    @Override
    public Long setnx(String key, String value) {
        return null;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Long decrBy(String key, long integer) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, long integer) {
        return null;
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long append(String key, String value) {
        return null;
    }

    @Override
    public Long lpush(final String key, final String... strings) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                if(strings==null||strings.length<1){
                    return null;
                }
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                byte[][] values = new byte[strings.length][];
                for(int i=0;i<strings.length;i++){
                    values[i] = valueSerializer.serialize(strings[i]);
                }
                return redisConnection.lPush(redisTemplate.getKeySerializer().serialize(key),values);
            }
        });
    }

    @Override
    public Long rpush(final String key, final String... strings) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                if(strings==null||strings.length<1){
                    return null;
                }
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                byte[][] values = new byte[strings.length][];
                for(int i=0;i<strings.length;i++){
                    values[i] = valueSerializer.serialize(strings[i]);
                }
                return redisConnection.rPush(redisTemplate.getKeySerializer().serialize(key),values);
            }
        });
    }

    @Override
    public Long llen(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.lLen(redisTemplate.getKeySerializer().serialize(key));
            }
        });
    }

    @Override
    public Long sadd(String key, String... members) {
        return null;
    }

    @Override
    public String spop(String key) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public Boolean hexists(String key, String field) {
        return null;
    }

    @Override
    public Long hdel(String key, String... fields) {
        return null;
    }

    @Override
    public Set<String> hkeys(String key) {
        return null;
    }

    @Override
    public Long hlen(String key) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public <T> void setObject(final String key, final T value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(redisTemplate.getKeySerializer().serialize(key),redisTemplate.getValueSerializer().serialize(value));
                return null;
            }
        });
    }

    @Override
    public <T> Boolean setObjectNx(final String key, final T value) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(redisTemplate.getKeySerializer().serialize(key),redisTemplate.getValueSerializer().serialize(value));
            }
        });
    }

    @Override
    public <T> void setObjectEx(final String key, final int seconds, final T value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.setEx(redisTemplate.getKeySerializer().serialize(key),seconds,redisTemplate.getValueSerializer().serialize(value));
                return null;
            }
        });
    }

    @Override
    public <T> T getObject(final String key) {
        return (T) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisTemplate.getValueSerializer().deserialize(redisConnection.get(redisTemplate.getKeySerializer().serialize(key)));
            }
        });
    }

    @Override
    public <T> Boolean hSetObject(final String key, final String field, final T value) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisConnection.hSet(serializer.serialize(key),serializer.serialize(field),redisTemplate.getValueSerializer().serialize(value));
            }
        });
    }

    @Override
    public <T> T hGetObject(final String key, final String field) {
        return (T) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisTemplate.getValueSerializer().deserialize(redisConnection.hGet(serializer.serialize(key),serializer.serialize(field)));
            }
        });
    }

    @Override
    public <T> Boolean hSetObjectNx(final String key, final String field, final T value) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisConnection.hSetNX(serializer.serialize(key),serializer.serialize(field),redisTemplate.getValueSerializer().serialize(value));
            }
        });
    }

    @Override
    public Boolean hExists(final String key, final String field) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisConnection.hExists(serializer.serialize(key),serializer.serialize(field));
            }
        });
    }

    @Override
    public Long hDel(final String key, final String field) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getKeySerializer();
                return redisConnection.hDel(serializer.serialize(key),serializer.serialize(field));
            }
        });
    }

    @Override
    public Long hLen(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.hLen(redisTemplate.getKeySerializer().serialize(key));
            }
        });
    }

    @Override
    public Set<String> hKeys(final String key) {
        return (Set<String>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<byte[]> set = redisConnection.hKeys(redisTemplate.getKeySerializer().serialize(key));
                if(null==set||set.isEmpty()){
                    return null;
                }
                Set<String> result = new HashSet<String>(set.size());
                for(byte[] one : set){
                    result.add((String) redisTemplate.getKeySerializer().deserialize(one));
                }
                return result;
            }
        });
    }

    @Override
    public <T> List<T> hValues(final String key) {
        return (List<T>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                List<byte[]> set = redisConnection.hVals(redisTemplate.getKeySerializer().serialize(key));
                if(null==set||set.isEmpty()){
                    return null;
                }
                List<T> result = new ArrayList<T>(set.size());
                for(byte[] one : set){
                    result.add((T) redisTemplate.getValueSerializer().deserialize(one));
                }
                return result;
            }
        });
    }

    @Override
    public <T> Map<String, T> hGetAll(final String key) {
        return (Map<String, T>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Map<byte[],byte[]> all = redisConnection.hGetAll(redisTemplate.getKeySerializer().serialize(key));
                if(null==all||all.isEmpty()){
                    return null;
                }
                RedisSerializer keySerializer = redisTemplate.getKeySerializer();
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                Map<String,T> result = new HashMap<String,T>(all.size());
                for(Map.Entry<byte[],byte[]> entry : all.entrySet()){
                    String key = (String) keySerializer.deserialize(entry.getKey());
                    T value = (T) valueSerializer.deserialize(entry.getValue());
                    result.put(key,value);
                }
                return result;
            }
        });
    }
}
