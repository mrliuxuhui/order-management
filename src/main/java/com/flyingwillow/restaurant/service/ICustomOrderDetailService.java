package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface ICustomOrderDetailService {
    public List<CustomOrderDetail> getCustomOrderDetailList(Map<String, Object> params, int start, int size);

    public Integer getCustomOrderDetailCount(Map<String, Object> params);

    public CustomOrderDetail getCustomOrderDetailById(Integer detailId);

    public List<CustomOrderDetail> getCustomOrderDetailsByOrder(Integer orderId);
    public Integer getCustomOrderDetailCountByOrder(Integer orderId);

    public List<CustomOrderDetail> getCustomOrderDetailsByOrder(String orderNumber);
    public Integer getCustomOrderDetailCountByOrder(String orderNumber);

    public void saveCustomOrderDetail(CustomOrderDetail detail);

    public void updateCustomOrderDetail(CustomOrderDetail detail);

    public void updateCustomOrderDetailMount(Integer detailId, Float mount);
    public void updateOrderDetailDeliverMount(Integer detailId, Float mount, Float deliverMount);

    public void deleteCustomOrderDetailByOId(Integer orderId);

    public void deleteCustomOrderDetail(Integer id);

}
