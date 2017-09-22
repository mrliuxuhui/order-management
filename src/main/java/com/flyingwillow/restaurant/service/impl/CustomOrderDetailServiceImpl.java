package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.CustomOrderDetail;
import com.flyingwillow.restaurant.mapper.CustomOrderDetailMapper;
import com.flyingwillow.restaurant.service.ICustomOrderDetailService;
import com.flyingwillow.restaurant.util.web.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/21.
 */
@Service
public class CustomOrderDetailServiceImpl implements ICustomOrderDetailService{

    @Autowired
    private CustomOrderDetailMapper customOrderDetailMapper;

    @Override
    public List<CustomOrderDetail> getCustomOrderDetailList(Map<String, Object> params, int start, int size) {
        start = start>0?start:0;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",start);
        params.put("size",size);
        return customOrderDetailMapper.getCustomOrderDetailList(params);
    }

    @Override
    public Integer getCustomOrderDetailCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return customOrderDetailMapper.getCustomOrderDetailCount(params);
    }

    @Override
    public CustomOrderDetail getCustomOrderDetailById(Integer detailId) {
        return customOrderDetailMapper.getCustomOrderDetailById(detailId);
    }

    @Override
    public List<CustomOrderDetail> getCustomOrderDetailsByOrder(Integer orderId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("orderId",orderId);
        return getCustomOrderDetailList(params,0,1000);
    }

    @Override
    public Integer getCustomOrderDetailCountByOrder(Integer orderId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("orderId",orderId);
        return getCustomOrderDetailCount(params);
    }

    @Override
    public List<CustomOrderDetail> getCustomOrderDetailsByOrder(String orderNumber) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("orderNumber",orderNumber);
        return getCustomOrderDetailList(params,0,1000);
    }

    @Override
    public Integer getCustomOrderDetailCountByOrder(String orderNumber) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("orderNumber",orderNumber);
        return getCustomOrderDetailCount(params);
    }

    @Override
    public void saveCustomOrderDetail(CustomOrderDetail detail) {
        customOrderDetailMapper.saveCustomOrderDetail(detail);
    }

    @Override
    public void updateCustomOrderDetail(CustomOrderDetail detail) {
        customOrderDetailMapper.updateCustomOrderDetail(detail);
    }

    @Override
    public void updateCustomOrderDetailMount(Integer detailId, Float mount) {
        CustomOrderDetail detail = new CustomOrderDetail().setId(detailId.longValue()).setMount(mount);
        customOrderDetailMapper.updateCustomOrderDetail(detail);
    }

    @Override
    public void updateOrderDetailDeliverMount(Integer detailId, Float mount, Float deliverMount) {
        if(null==mount&&null==deliverMount){
            return;
        }
        CustomOrderDetail detail = new CustomOrderDetail().setId(detailId.longValue());
        if(null!=mount){
            detail.setMount(mount);
        }
        if(null!=deliverMount){
            detail.setDeliverMount(deliverMount);
        }
        customOrderDetailMapper.updateCustomOrderDetail(detail);
    }

    @Override
    public void deleteCustomOrderDetailByOId(Integer orderId) {
        customOrderDetailMapper.deleteCustomOrderDetailByOId(orderId);
    }

    @Override
    public void deleteCustomOrderDetail(Integer id) {
        customOrderDetailMapper.getCustomOrderDetailById(id);
    }
}
