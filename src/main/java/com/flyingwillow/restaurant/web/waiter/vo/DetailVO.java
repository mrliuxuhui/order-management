package com.flyingwillow.restaurant.web.waiter.vo;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;

/**
 * Created by liuxuhui on 2017/9/28.
 */
public class DetailVO {

    private Integer detailId;
    private Integer menuId;
    private String name;
    private String img;
    private Float price;
    private Integer unit;
    private String unitName;
    private Float mount;
    private Float priceSum;

    public DetailVO() {
    }

    public DetailVO(CustomOrderDetail detail){
        this.detailId = detail.getId().intValue();
        this.menuId = detail.getMenuId();
        this.unit = detail.getUnit();
        this.mount = detail.getMount();
        this.priceSum = detail.getPriceSum();
        this.name = null==detail.getMenu()?"":detail.getMenu().getName();
        this.img = null==detail.getMenu()?"":detail.getMenu().getImg();
        this.price = null==detail.getMenu()?0f:detail.getMenu().getPrice();
        this.unitName = null==detail.getMenu()?"":detail.getMeasurement().getCname();
    }

    public Integer getDetailId() {
        return detailId;
    }

    public DetailVO setDetailId(Integer detailId) {
        this.detailId = detailId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public DetailVO setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public DetailVO setImg(String img) {
        this.img = img;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public DetailVO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public DetailVO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public DetailVO setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public Float getMount() {
        return mount;
    }

    public DetailVO setMount(Float mount) {
        this.mount = mount;
        return this;
    }

    public Float getPriceSum() {
        return priceSum;
    }

    public DetailVO setPriceSum(Float priceSum) {
        this.priceSum = priceSum;
        return this;
    }
}
