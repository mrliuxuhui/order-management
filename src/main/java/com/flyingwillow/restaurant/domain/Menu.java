package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class Menu {
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
    private Measurement measurement;
    private FoodCategory foodCategory;

    public Integer getId() {
        return id;
    }

    public Menu setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Menu setImg(String img) {
        this.img = img;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Menu setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public Menu setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public Menu setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Menu setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Menu setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Menu setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Menu setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public Menu setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public Menu setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
        return this;
    }
}
