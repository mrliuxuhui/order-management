package com.flyingwillow.restaurant.shiro.realm;

import com.flyingwillow.restaurant.domain.Role;
import com.flyingwillow.restaurant.domain.User;
import com.flyingwillow.restaurant.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liuxuhui on 2017/9/13.
 */
public class MyShiroRealm extends AuthorizingRealm implements Realm, InitializingBean{

    private Logger logger = LogManager.getLogger(this.getClass());

    //user service
    //role service
    @Autowired
    private IUserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final String username = getUsername(principals);

        List<Role> roles = userService.getRolesByUsername(username);
        logger.debug("Load Roles[{}] by username: {}", roles, username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for (Role role : roles) {
            info.addRole(role.getName());
            for (String permission : userService.getPermissionsByRole(role.getId())) {
                info.addStringPermission(permission);
            }
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = getUsername(token);

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        User users = userService.getUserByUsername(username);
        logger.debug("Load Users[{}] by username: {}", users, username);

        SimpleAuthenticationInfo info = null;
        if (users != null) {
            info = new SimpleAuthenticationInfo(username, users.getPassword().toCharArray(), getName());
        }

        return info;
    }

    protected String getUsername(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return upToken.getUsername();
    }

    protected String getUsername(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        return (String) getAvailablePrincipal(principals);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        org.springframework.util.Assert.notNull(userService, "userService is null");
    }
}
