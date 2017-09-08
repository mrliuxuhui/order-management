package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.CustomOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/8.
 */
public interface CustomOrderMapper {

    public List<CustomOrder> getCustomOrderList(Map<String, Object> params);

    public void saveCustomOrder(CustomOrder detail);

    public void updateCustomOrder(CustomOrder detail);

    public void deleteCustomOrderByNumber(String orderId);

    public void deleteCustomOrder(Integer id);
}
