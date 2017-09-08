package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/8.
 */
public interface CustomOrderDetailMapper {

    public List<CustomOrderDetail> getCustomOrderDetailList(Map<String,Object> params);

    public void saveCustomOrderDetail(CustomOrderDetail detail);

    public void updateCustomOrderDetail(CustomOrderDetail detail);

    public void deleteCustomOrderDetailByOId(Integer orderId);

    public void deleteCustomOrderDetail(Integer id);
}
