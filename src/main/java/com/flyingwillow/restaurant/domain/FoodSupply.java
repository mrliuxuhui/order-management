package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class FoodSupply {
    private Integer id;
    private Integer menuId;
    private Integer mount;
    private Integer unit;
    private Integer remaining;
    private Date supplyDate;
    private Date createTime;
    private String operator;

    public Integer getId() {
        return id;
    }

    public FoodSupply setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public FoodSupply setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Integer getMount() {
        return mount;
    }

    public FoodSupply setMount(Integer mount) {
        this.mount = mount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public FoodSupply setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public FoodSupply setRemaining(Integer remaining) {
        this.remaining = remaining;
        return this;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public FoodSupply setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FoodSupply setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public FoodSupply setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
