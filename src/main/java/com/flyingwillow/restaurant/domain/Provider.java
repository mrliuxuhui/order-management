package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class Provider {
    private Integer id;
    private String name;
    private String address;
    private String contactor;
    private String tel;
    private Integer regionId;
    private Integer cityId;
    private Integer provinceId;
    private Date createTime;
    private Date updateTime;
    private String operator;

    public Integer getId() {
        return id;
    }

    public Provider setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Provider setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Provider setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContactor() {
        return contactor;
    }

    public Provider setContactor(String contactor) {
        this.contactor = contactor;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Provider setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public Provider setRegionId(Integer regionId) {
        this.regionId = regionId;
        return this;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Provider setCityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public Provider setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Provider setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Provider setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Provider setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
