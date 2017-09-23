package com.flyingwillow.restaurant.web.admin.dto;

/**
 * Created by 刘旭辉 on 2017/9/23.
 */
public class CheckoutDTO {
    private Integer orderId;
    private String payment;
    private Float receivables;//应收
    private Float receipts;//实收
    private Float changes;//找零

    public Integer getOrderId() {
        return orderId;
    }

    public CheckoutDTO setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public CheckoutDTO setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public Float getReceivables() {
        return receivables;
    }

    public CheckoutDTO setReceivables(Float receivables) {
        this.receivables = receivables;
        return this;
    }

    public Float getReceipts() {
        return receipts;
    }

    public CheckoutDTO setReceipts(Float receipts) {
        this.receipts = receipts;
        return this;
    }

    public Float getChanges() {
        return changes;
    }

    public CheckoutDTO setChanges(Float changes) {
        this.changes = changes;
        return this;
    }
}
