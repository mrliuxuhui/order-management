package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 * 菜品食材用量表
 */
public class FoodMaterial {

    private Integer id;
    private Integer menuId;
    private Integer materialId;
    private Float mount;
    private Integer unit;
    private Date createTime;
    private Date updateTime;
    private String operator;

    public Integer getId() {
        return id;
    }

    public FoodMaterial setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public FoodMaterial setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public FoodMaterial setMaterialId(Integer materialId) {
        this.materialId = materialId;
        return this;
    }

    public Float getMount() {
        return mount;
    }

    public FoodMaterial setMount(Float mount) {
        this.mount = mount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public FoodMaterial setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FoodMaterial setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FoodMaterial setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public FoodMaterial setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
