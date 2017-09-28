package com.flyingwillow.restaurant.web.waiter.dto;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class DetailDTO {

    private Integer menuId;
    private Float mount;
    private Integer unit;
    private Float price;

    public Integer getMenuId() {
        return menuId;
    }

    public DetailDTO setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Float getMount() {
        return null==mount?0f:mount;
    }

    public DetailDTO setMount(Float mount) {
        this.mount = mount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public DetailDTO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Float getPrice() {
        return null==price?0f:price;
    }

    public DetailDTO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public CustomOrderDetail toDetail(){
        CustomOrderDetail detail = new CustomOrderDetail();
        detail.setMenuId(this.menuId).setUnit(this.unit).setMount(mount);
        detail.setDelivered(false).setStatus(DetailOrderStatus.CREATED.getStatus());
        return detail;
    }
}
