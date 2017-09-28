package com.flyingwillow.restaurant.web.waiter.vo;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.domain.CustomOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/28.
 */
public class OrderVO {

    private Integer oderId;
    private String number;
    private Integer tableNo;
    private Float totalPrice;
    private List<DetailVO> details;

    public OrderVO() {
    }

    public OrderVO(CustomOrder order, List<CustomOrderDetail> details){

        this.oderId = order.getId().intValue();
        this.number = order.getNumber();
        this.tableNo = order.getTableNo();
        this.totalPrice = order.getTotalPrice();
        if(null==details||details.isEmpty()){
            return;
        }
        this.details = new ArrayList<>(details.size());
        for(CustomOrderDetail detail : details){
            this.details.add(new DetailVO(detail));
        }
    }

    public Integer getOderId() {
        return oderId;
    }

    public OrderVO setOderId(Integer oderId) {
        this.oderId = oderId;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public OrderVO setNumber(String number) {
        this.number = number;
        return this;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public OrderVO setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
        return this;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public OrderVO setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<DetailVO> getDetails() {
        return details;
    }

    public OrderVO setDetails(List<DetailVO> details) {
        this.details = details;
        return this;
    }
}
