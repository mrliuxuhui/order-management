package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.CustomOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/8.
 */
public interface CustomOrderMapper {

    public List<CustomOrder> getCustomOrderList(Map<String, Object> params);

    public Integer getCustomOrderCount(Map<String, Object> params);

    public CustomOrder getCustomOrderById(Map<String,Object> params);

    public List<Map<String,Object>> getOrderNumbersByTableNo(Integer tableNo);

    public Integer getOrderSerialNumber();

    public List<Integer> getOrderedTableNumbers();

    public List<CustomOrder> getCustomOrderByTableNo(Integer tableNo);
    public Integer getCustomOrderCountByTableNo(Integer tableNo);

    public void saveCustomOrder(CustomOrder order);

    public void updateCustomOrder(CustomOrder order);

    public void updateOrderTotalPrice(HashMap<String,Object> params);

    public void deleteCustomOrderByNumber(String number);

    public void deleteCustomOrder(String id);
}
