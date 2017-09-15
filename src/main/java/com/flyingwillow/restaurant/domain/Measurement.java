package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class Measurement {

    private Integer id;
    private String cname;
    private String ename;
    private Date createTime;
    private Date updateTime;
    private String operator;

    public Integer getId() {
        return id;
    }

    public Measurement setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public Measurement setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public String getEname() {
        return ename;
    }

    public Measurement setEname(String ename) {
        this.ename = ename;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Measurement setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Measurement setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Measurement setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
