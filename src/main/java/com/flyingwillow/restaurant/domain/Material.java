package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class Material {
    private Integer id;
    private String name;
    private String img;
    private Float price;
    private Integer unit;
    private String profile;
    private Integer categoryId;
    private Date createTime;
    private Date updateTime;
    private String operator;
    private MaterialCategory materialCategory;
    private Measurement measurement;

    public Integer getId() {
        return id;
    }

    public Material setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Material setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Material setImg(String img) {
        this.img = img;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Material setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public Material setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public Material setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Material setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Material setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Material setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Material setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public MaterialCategory getMaterialCategory() {
        return materialCategory;
    }

    public Material setMaterialCategory(MaterialCategory materialCategory) {
        this.materialCategory = materialCategory;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public Material setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }
}
