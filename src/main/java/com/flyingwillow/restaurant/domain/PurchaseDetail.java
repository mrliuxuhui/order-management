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

    private Purchase purchase;
    private Material material;
    private Measurement measurement;
    private Provider provider;

    public Long getId() {
        return id;
    }

    public PurchaseDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public PurchaseDetail setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
        return this;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public PurchaseDetail setMaterialId(Integer materialId) {
        this.materialId = materialId;
        return this;
    }

    public Float getExpectMount() {
        return expectMount;
    }

    public PurchaseDetail setExpectMount(Float expectMount) {
        this.expectMount = expectMount;
        return this;
    }

    public Float getActualMount() {
        return actualMount;
    }

    public PurchaseDetail setActualMount(Float actualMount) {
        this.actualMount = actualMount;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public PurchaseDetail setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public PurchaseDetail setProviderId(Integer providerId) {
        this.providerId = providerId;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public PurchaseDetail setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public PurchaseDetail setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public PurchaseDetail setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PurchaseDetail setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDel() {
        return del;
    }

    public PurchaseDetail setDel(Integer del) {
        this.del = del;
        return this;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public PurchaseDetail setPurchase(Purchase purchase) {
        this.purchase = purchase;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public PurchaseDetail setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public PurchaseDetail setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public Provider getProvider() {
        return provider;
    }

    public PurchaseDetail setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }
}
