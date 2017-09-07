package com.flyingwillow.restaurant.domain;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class PurchaseDetail {
    private Long id;
    private Integer purchaseId;
    private Integer materialId;
    private Float expectMount;
    private Float actualMount;
    private Integer unit;
    private Integer providerId;
    private Float price;
    private Float totalPrice;
    private Float actualPrice;
    private Integer status;
    private Integer del;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Float getExpectMount() {
        return expectMount;
    }

    public void setExpectMount(Float expectMount) {
        this.expectMount = expectMount;
    }

    public Float getActualMount() {
        return actualMount;
    }

    public void setActualMount(Float actualMount) {
        this.actualMount = actualMount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
