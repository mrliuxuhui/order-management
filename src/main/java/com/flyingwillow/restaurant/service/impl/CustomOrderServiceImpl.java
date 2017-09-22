package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.mapper.CustomOrderMapper;
import com.flyingwillow.restaurant.service.ICustomOrderService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.SerialNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/21.
 */
@Service
public class CustomOrderServiceImpl implements ICustomOrderService {

    @Autowired
    private CustomOrderMapper customOrderMapper;

    @Override
    public List<CustomOrder> getCustomOrderList(Map<String, Object> params, int start, int size) {
        start = start>0?start:0;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",start);
        params.put("size",size);
        return customOrderMapper.getCustomOrderList(params);
    }

    @Override
    public List<CustomOrder> getCustomOrderList(Map<String, Object> params, Boolean showAll, int start, int size) {
        start = start>0?start:0;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",start);
        params.put("size",size);
        if(showAll==null||showAll==false){//显示未结订单
            params.put("isChecked",false);
        }
        return customOrderMapper.getCustomOrderList(params);
    }

    @Override
    public Integer getCustomOrderCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return customOrderMapper.getCustomOrderCount(params);
    }

    @Override
    public Integer getCustomOrderCount(Map<String, Object> params, Boolean showAll) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        if(showAll==null||showAll==false){//显示未结订单
            params.put("isChecked",false);
        }
        return customOrderMapper.getCustomOrderCount(params);
    }

    @Override
    public CustomOrder getCustomOrderById(Integer menuId) {
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("id",menuId);
        return customOrderMapper.getCustomOrderById(params);
    }

    @Override
    public List<Map<String,Object>> getOrderNumbersByTableNo(Integer tableNo) {
        return customOrderMapper.getOrderNumbersByTableNo(tableNo);
    }

    @Override
    public Integer getOrderSerialNumber() {
        return customOrderMapper.getOrderSerialNumber();
    }

    @Override
    public void saveCustomOrder(CustomOrder order) {
        String number = SerialNumberGenerator.getSerialNumber("ORD",getOrderSerialNumber());
        order.setNumber(number);
        order.setCreateTime(new Date());
        customOrderMapper.saveCustomOrder(order);
    }

    @Override
    public void updateCustomOrder(CustomOrder order) {
        customOrderMapper.updateCustomOrder(order);
    }

    @Override
    public void deleteCustomOrderByNumber(String number) {
        customOrderMapper.deleteCustomOrderByNumber(number);
    }
}
