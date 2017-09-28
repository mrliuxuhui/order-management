package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.web.admin.dto.CheckoutDTO;
import com.flyingwillow.restaurant.web.waiter.dto.OrderDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface ICustomOrderService {
    public List<CustomOrder> getCustomOrderList(Map<String, Object> params, int start, int size);
    public List<CustomOrder> getCustomOrderList(Map<String, Object> params, Boolean showAll, int start, int size);

    public Integer getCustomOrderCount(Map<String, Object> params);
    public Integer getCustomOrderCount(Map<String, Object> params, Boolean showAll);

    public CustomOrder getCustomOrderById(Integer orderId);

    public List<Map<String,Object>> getOrderNumbersByTableNo(Integer tableNo);

    public Integer getOrderSerialNumber();

    public List<Integer> getOrderedTableNumbers();

    public void saveCustomOrder(CustomOrder order);

    public CustomOrder saveCustomOrder(Integer tableNo);

    public void updateCustomOrder(CustomOrder order);

    public void updateOrderTotalPrice(Long orderId, Float totalPrice);

    public void txIncrementTotalPrice(Long orderId, Float totalPrice);

    public void deleteCustomOrderByNumber(String number);

    public void checkoutOrder(Integer orderId, CheckoutDTO checkoutDTO);

}
