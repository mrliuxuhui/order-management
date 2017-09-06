package com.flyingwillow.restaurant.service;

/**
 * Created by liuxuhui on 2017/9/6.
 */
public interface IRedisQueueService {

    public void publish(String channel, Object message);

}
