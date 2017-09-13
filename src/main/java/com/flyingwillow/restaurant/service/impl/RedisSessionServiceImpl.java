package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.service.IRedisService;
import com.flyingwillow.restaurant.service.IRedisSessionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuxuhui on 2017/9/13.
 */
@Service
public class RedisSessionServiceImpl implements IRedisSessionService{

    private final static String REDIS_SHIRO_SESSION = "REDIS-SHIRO-SESSION:";
    private final static String REDIS_SHIRO_SESSION_ALL = "REDIS-SHIRO-SESSION:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private final static String REDIS_SHIRO_MAP_KEY = "REDIS-SHIRO-SESSION-MAP";


    @Autowired
    private IRedisService redisService;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            String key = buildRedisSessionKey(session.getId());
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            redisService.setObjectEx(key,expireTime.intValue(),session);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteSession(Serializable sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("session id is empty");
        }
        String key = buildRedisSessionKey(sessionId);
        redisService.del(key);
    }

    @Override
    public Session getSession(Serializable sessionId) {
        if (sessionId == null)
            throw new NullPointerException("session id is empty");
        String key = buildRedisSessionKey(sessionId);
        return redisService.getObject(key);
    }

    @Override
    public Collection<Session> getAllSessions() {

        Set<String> keys = redisService.keys(REDIS_SHIRO_SESSION_ALL);
        if(null==keys||keys.isEmpty()){
            return null;
        }

        HashSet<Session> sessions = new HashSet<>(keys.size());

        for(String key : keys){
            sessions.add((Session) redisService.getObject(key));
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }
}
