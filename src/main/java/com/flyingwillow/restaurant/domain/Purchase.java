package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class Purchase {

    private Integer id;
    private String number;
    private String title;
    private Date expectDate;
    private String operator;
    private Float totalPrice;
    private Float actualPrice;
    private Integer status;
    private Date completeTime;
    private Float completePercent;
    private Integer del;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public Purchase setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Purchase setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Purchase setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getExpectDate() {
        return expectDate;
    }

    public Purchase setExpectDate(Date expectDate) {
        this.expectDate = expectDate;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Purchase setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public Purchase setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public Purchase setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Purchase setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public Purchase setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public Float getCompletePercent() {
        return completePercent;
    }

    public Purchase setCompletePercent(Float completePercent) {
        this.completePercent = completePercent;
        return this;
    }

    public Integer getDel() {
        return del;
    }

    public Purchase setDel(Integer del) {
        this.del = del;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Purchase setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
