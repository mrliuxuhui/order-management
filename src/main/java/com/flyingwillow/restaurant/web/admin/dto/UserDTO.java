package com.flyingwillow.restaurant.web.admin.dto;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by liuxuhui on 2017/9/15.
 */
public class UserDTO {

    private String username;
    private String password;
    private Boolean rememberme;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getRememberme() {
        return rememberme;
    }

    public UserDTO setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
        return this;
    }

    public UsernamePasswordToken token(){

        return new UsernamePasswordToken(username,password,null==rememberme?false:rememberme);
    }
}
