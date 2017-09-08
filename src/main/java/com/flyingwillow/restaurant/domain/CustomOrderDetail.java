package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class CustomOrderDetail {

    private Long id;
    private Long orderId;
    private Integer menuId;
    private Float mount;
    private Integer unit;
    private Boolean needPush;
    private Date beginTime;
    private Date completeTime;
    private Boolean delivered;
    private Date deliveredTime;
    private String water;
    private Integer status;
    private Integer del;

    private CustomOrder order;
    private Measurement measurement;
    private Menu menu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Float getMount() {
        return mount;
    }

    public void setMount(Float mount) {
        this.mount = mount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Boolean getNeedPush() {
        return needPush;
    }

    public void setNeedPush(Boolean needPush) {
        this.needPush = needPush;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Date getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(Date deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CustomOrder getOrder() {
        return order;
    }

    public void setOrder(CustomOrder order) {
        this.order = order;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
