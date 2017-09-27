package com.flyingwillow.restaurant.web.waiter.dto;

import java.util.List;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class OrderDTO {

    private Integer tableNo;
    private List<DetailDTO> details;

    public Integer getTableNo() {
        return tableNo;
    }

    public OrderDTO setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
        return this;
    }

    public List<DetailDTO> getDetails() {
        return details;
    }

    public OrderDTO setDetails(List<DetailDTO> details) {
        this.details = details;
        return this;
    }
}
