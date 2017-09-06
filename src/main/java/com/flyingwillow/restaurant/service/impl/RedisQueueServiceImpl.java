package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.service.IRedisQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by liuxuhui on 2017/9/6.
 */
@Service
public class RedisQueueServiceImpl implements IRedisQueueService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void publish(String channel, Object message) {

        redisTemplate.convertAndSend(channel, message);
    }
}
