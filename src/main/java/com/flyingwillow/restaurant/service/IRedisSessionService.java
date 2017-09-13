package com.flyingwillow.restaurant.service;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public interface IRedisSessionService {

    /**
     * 存储Session
     * @param session
     */
    void saveSession(Session session);
    /**
     * 删除session
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);
    /**
     * 获取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);
    /**
     * 获取所有sessoin
     * @return
     */
    Collection<Session> getAllSessions();
}
