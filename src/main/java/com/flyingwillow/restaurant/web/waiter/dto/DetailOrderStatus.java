package com.flyingwillow.restaurant.web.waiter.dto;

/**
 * Created by liuxuhui on 2017/9/28.
 */
public enum DetailOrderStatus {

    CREATED(0),
    COOKING(1),
    COMPLETED(2),
    DELIVERED(3);

    private Integer status;
    DetailOrderStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
