package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class FoodCategory {
    private Integer id;
    private String name;
    private String profile;
    private Date createTime;
    private Date updateTime;
    private String operator;

    public Integer getId() {
        return id;
    }

    public FoodCategory setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FoodCategory setName(String name) {
        this.name = name;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public FoodCategory setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FoodCategory setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FoodCategory setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public FoodCategory setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
