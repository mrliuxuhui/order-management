package com.flyingwillow.restaurant.service;

import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public interface IRawRedisService {

    /****基本操作*****/
    public Boolean exists(byte[] key);
    public Long expire(byte[] key, int seconds);
    public  String	type(byte[] key);
    public String randomKey();
    public String rename(byte[] oldkey, byte[] newkey);
    public Boolean renamenx(byte[] oldkey, byte[] newkey);
    public Long del(byte[] key);
    public Long del(byte[]... keys);
    public Set<byte[]> keys(byte[] pattern);

    /***basic type**/
    public void set(byte[] key, byte[] value);
    public Long setnx(byte[] key,  byte[] value);
    public void setex(byte[] key,  int seconds,  byte[] value);
    public byte[] get(byte[] key);

}
