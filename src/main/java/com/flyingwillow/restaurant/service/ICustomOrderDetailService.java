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

    public void saveCustomOrderDetail(CustomOrderDetail detail);

    public void updateCustomOrderDetail(CustomOrderDetail detail);

    public void deleteCustomOrderDetailByOId(Integer orderId);

    public void deleteCustomOrderDetail(Integer id);

}
