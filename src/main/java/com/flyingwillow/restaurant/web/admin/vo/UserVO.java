package com.flyingwillow.restaurant.web.admin.vo;

import com.flyingwillow.restaurant.domain.User;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/19.
 */
public class UserVO {

    private String username;
    private String name;
    private String img;
    private String gender;
    private Date lastLoginTime;

    public UserVO(){

    }
    public UserVO(User user){
        if(null!=user){
            this.setUsername(user.getUsername()).setName(user.getName())
                    .setImg(user.getImg()).setGender(user.getGender())
                    .setLastLoginTime(user.getUpdateTime());
        }
    }

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public UserVO setImg(String img) {
        this.img = img;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserVO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public UserVO setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }
}
