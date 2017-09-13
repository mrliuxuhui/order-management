package com.flyingwillow.restaurant.shiro.session;

import com.flyingwillow.restaurant.service.IRedisSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public class MyShiroSessionDao extends AbstractSessionDAO {

    private IRedisSessionService redisSessionService;

    public IRedisSessionService getRedisSessionService() {
        return redisSessionService;
    }

    public void setRedisSessionService(IRedisSessionService redisSessionService) {
        this.redisSessionService = redisSessionService;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        getRedisSessionService().saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return getRedisSessionService().getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        getRedisSessionService().saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(null==session){
            throw new NullPointerException("Session is null.");
        }
        getRedisSessionService().deleteSession(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return getRedisSessionService().getAllSessions();
    }
}
