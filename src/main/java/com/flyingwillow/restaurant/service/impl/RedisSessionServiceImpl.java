package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.service.IRawRedisService;
import com.flyingwillow.restaurant.service.IRedisService;
import com.flyingwillow.restaurant.service.IRedisSessionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
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

    private final Charset charset = Charset.forName("UTF8");

    @Autowired
    private IRawRedisService redisService;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            String key = buildRedisSessionKey(session.getId());
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            redisService.setex(serializeKey(key),expireTime.intValue(),serializeSession(session));
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
        redisService.del(serializeKey(key));
    }

    @Override
    public Session getSession(Serializable sessionId) {
        if (sessionId == null)
            throw new NullPointerException("session id is empty");
        String key = buildRedisSessionKey(sessionId);
        return deserializeSession(redisService.get(serializeKey(key)));
    }

    @Override
    public Collection<Session> getAllSessions() {

        Set<byte[]> keys = redisService.keys(serializeKey(REDIS_SHIRO_SESSION_ALL));
        if(null==keys||keys.isEmpty()){
            return null;
        }

        HashSet<Session> sessions = new HashSet<>(keys.size());

        for(byte[] key : keys){
            sessions.add((Session) deserializeSession(redisService.get(key)));
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    private <T> byte[] serializeSession(T session){
        ObjectOutputStream out = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(outputStream);
            out.writeObject(session);
            out.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> T deserializeSession(byte[] bytes){

        ObjectInputStream ois = null;

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(inputStream);

            Object result = ois.readObject();

            return (T) result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(null!=ois){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private byte[] serializeKey(String key){
        return key == null?null:key.getBytes(charset);
    }

    private String deserializeKey(byte[] bytes){
        return (bytes == null ? null : new String(bytes, charset));
    }
}
