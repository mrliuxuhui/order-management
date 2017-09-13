package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/12.
 */
public class Role {
    private Integer id;
    private String name;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
