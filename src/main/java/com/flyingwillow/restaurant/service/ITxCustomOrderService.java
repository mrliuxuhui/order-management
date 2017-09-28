package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.web.admin.dto.CheckoutDTO;
import com.flyingwillow.restaurant.web.waiter.dto.DetailDTO;
import com.flyingwillow.restaurant.web.waiter.dto.OrderDTO;

/**
 * Created by liuxuhui on 2017/9/28.
 */
public interface ITxCustomOrderService {

    /**
     * 创建菜单，按事务提交
     * */
    public void txCreateOrder(OrderDTO orderDTO);

    /**
     * 新增菜品
     * */
    public void saveOrderDetail(Integer orderId, DetailDTO detailDTO);

    /**
     * 结单
     * */
    public void checkoutOrder(Integer orderId, CheckoutDTO checkoutDTO);
}
