package com.flyingwillow.restaurant.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/6.
 */
public interface IRedisService {

    /****基本操作*****/
    public Boolean exists(String key);
    public Long expire(String key, int seconds);
    public  String	type(String key);
    public String randomKey();
    public String rename(String oldkey, String newkey);
    public Boolean renamenx(String oldkey, String newkey);
    public Long del(String key);
    public Long del(String... keys);
    public Set<String> keys( String pattern);

    /***basic type**/
    public void set(String key, String value);
    public Long setnx(String key,  String value);
    public String setex(String key,  int seconds,  String value);
    public String get(String key);
    public Long decrBy(String key,  long integer);
    public Long decr(String key);
    public Long incrBy(String key,  long integer);
    public Double incrByFloat(String key,  double value);
    public Long incr(String key);
    public Long append(String key,  String value);

    /**list operation**/
    public Long lpush(String key,  String... strings);
    public Long rpush(String key,  String... strings);
    public Long llen(String key);

    /**set operation***/
    public Long sadd( String key,  String... members);
    public String spop( String key);

    /**map operation***/
    public String hget( String key,  String field);
    public Long hsetnx( String key,  String field,  String value);
    public Long hset( String key,  String field,  String value);
    public Boolean hexists( String key,  String field);
    public Long hdel( String key,  String... fields);
    public Set<String> hkeys( String key);
    public Long hlen( String key);
    public List<String> hvals( String key);
    public Map<String, String> hgetAll( String key);

    /***byte ****/
    public <T> void setObject( String key,  T value);
    public <T> Boolean setObjectNx( String key,  T value);
    public <T> void setObjectEx( String key,  int seconds,   T value);
    public <T> T getObject( String key);

    public <T> Boolean hSetObject(String key,  String field,  T value);
    public <T> T hGetObject(String key,  String field);
    public <T> Boolean hSetObjectNx(String key,  String field,  T value);
    public Boolean hExists(String key,  String field);
    public Long hDel(String key,  String field);
    public Long hLen(String key);
    public Set<String> hKeys(String key);
    public <T> List<T> hValues(String key);
    public <T> Map<String, T> hGetAll(String key);
}
