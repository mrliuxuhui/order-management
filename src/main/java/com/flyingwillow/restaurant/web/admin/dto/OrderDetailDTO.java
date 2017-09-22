package com.flyingwillow.restaurant.web.admin.dto;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;

/**
 * Created by 刘旭辉 on 2017/9/22.
 */
public class OrderDetailDTO {

    private Integer orderId;
    private Integer menuId;
    private Float mount;
    private Integer unit;

    public Integer getOrderId() {
        return orderId;
    }

    public OrderDetailDTO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public OrderDetailDTO setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Float getMount() {
        return mount;
    }

    public OrderDetailDTO setMount(Float mount) {
        this.mount = mount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public OrderDetailDTO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public CustomOrderDetail toOderDetail(){
        return new CustomOrderDetail().setMenuId(this.menuId)
                .setMount(this.mount).setOrderId(this.orderId.longValue())
                .setUnit(this.unit);
    }
}
