package com.flyingwillow.restaurant.domain;

import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public class CustomOrder {
    private Long id;
    private String number;
    private Integer tableNo;
    private Date createTime;
    private Date receiveTime;
    private String receiver;
    private Date completeTime;
    private String completeOperator;
    private Float totalPrice;
    private Float actualPrice;
    private Boolean isChecked;
    private Date checkTime;
    private String checkOperator;
    private Boolean pushed;
    private Boolean rush;
    private Integer del;

    public Long getId() {
        return id;
    }

    public CustomOrder setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public CustomOrder setNumber(String number) {
        this.number = number;
        return this;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public CustomOrder setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CustomOrder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public CustomOrder setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public CustomOrder setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public CustomOrder setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public String getCompleteOperator() {
        return completeOperator;
    }

    public CustomOrder setCompleteOperator(String completeOperator) {
        this.completeOperator = completeOperator;
        return this;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public CustomOrder setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public CustomOrder setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public CustomOrder setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
        return this;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public CustomOrder setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
        return this;
    }

    public Boolean getPushed() {
        return pushed;
    }

    public CustomOrder setPushed(Boolean pushed) {
        this.pushed = pushed;
        return this;
    }

    public Boolean getRush() {
        return rush;
    }

    public CustomOrder setRush(Boolean rush) {
        this.rush = rush;
        return this;
    }

    public Integer getDel() {
        return del;
    }

    public CustomOrder setDel(Integer del) {
        this.del = del;
        return this;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public CustomOrder setChecked(Boolean checked) {
        isChecked = checked;
        return this;
    }
}
