package com.flyingwillow.restaurant.shiro.session;

import com.flyingwillow.restaurant.service.IRedisSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public class MySessionListener implements SessionListener {


    private IRedisSessionService redisSessionService;

    public IRedisSessionService getRedisSessionService() {
        return redisSessionService;
    }

    public void setRedisSessionService(IRedisSessionService redisSessionService) {
        this.redisSessionService = redisSessionService;
    }

    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {
        redisSessionService.deleteSession(session.getId());
    }
}
