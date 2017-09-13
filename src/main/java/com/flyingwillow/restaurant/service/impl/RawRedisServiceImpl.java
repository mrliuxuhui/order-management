package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.service.IRawRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/13.
 */
@Service
public class RawRedisServiceImpl implements IRawRedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean exists(final byte[] key) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.exists(key);
            }
        });
    }

    @Override
    public Long expire(final byte[] key, final int seconds) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.expire(key,seconds);
            }
        });
    }

    @Override
    public String type(final byte[] key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.type(key);
            }
        });
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
    public String rename(final byte[] oldkey, final byte[] newkey) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.rename(oldkey, newkey);
                return null;
            }
        });
    }

    @Override
    public Boolean renamenx(final byte[] oldkey, final byte[] newkey) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.renameNX(oldkey, newkey);
            }
        });
    }

    @Override
    public Long del(final byte[] key) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(key);
            }
        });
    }

    @Override
    public Long del(final byte[]... keys) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(keys);
            }
        });
    }

    @Override
    public Set<byte[]> keys(final byte[] pattern) {
        return (Set<byte[]>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.keys(pattern);
            }
        });
    }

    @Override
    public void set(final byte[] key, final byte[] value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(key, value);
                return null;
            }
        });
    }

    @Override
    public Long setnx(final byte[] key, final byte[] value) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(key, value);
            }
        });
    }

    @Override
    public void setex(final byte[] key, final int seconds, final byte[] value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.setEx(key,seconds,value);
                return null;
            }
        });
    }

    @Override
    public byte[] get(final byte[] key) {
        return (byte[]) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.get(key);
            }
        });
    }
}
