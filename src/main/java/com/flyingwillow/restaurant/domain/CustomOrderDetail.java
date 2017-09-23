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
    private Float deliverMount;
    private Float priceSum;
    private String water;
    private Integer status;
    private Integer del;

    private CustomOrder order;
    private Measurement measurement;
    private Menu menu;

    public Long getId() {
        return id;
    }

    public CustomOrderDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public CustomOrderDetail setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public CustomOrderDetail setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Float getMount() {
        return mount;
    }

    public CustomOrderDetail setMount(Float mount) {
        this.mount = mount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public CustomOrderDetail setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Boolean getNeedPush() {
        return needPush;
    }

    public CustomOrderDetail setNeedPush(Boolean needPush) {
        this.needPush = needPush;
        return this;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public CustomOrderDetail setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public CustomOrderDetail setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public CustomOrderDetail setDelivered(Boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public Date getDeliveredTime() {
        return deliveredTime;
    }

    public CustomOrderDetail setDeliveredTime(Date deliveredTime) {
        this.deliveredTime = deliveredTime;
        return this;
    }

    public String getWater() {
        return water;
    }

    public CustomOrderDetail setWater(String water) {
        this.water = water;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CustomOrderDetail setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDel() {
        return del;
    }

    public CustomOrderDetail setDel(Integer del) {
        this.del = del;
        return this;
    }

    public CustomOrder getOrder() {
        return order;
    }

    public CustomOrderDetail setOrder(CustomOrder order) {
        this.order = order;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public CustomOrderDetail setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public CustomOrderDetail setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public Float getDeliverMount() {
        return deliverMount;
    }

    public CustomOrderDetail setDeliverMount(Float deliverMount) {
        this.deliverMount = deliverMount;
        return this;
    }

    public Float getPriceSum() {
        return priceSum;
    }

    public CustomOrderDetail setPriceSum(Float priceSum) {
        this.priceSum = priceSum;
        return this;
    }
}
