package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.domain.CustomOrderDetail;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.ICustomOrderDetailService;
import com.flyingwillow.restaurant.service.ICustomOrderService;
import com.flyingwillow.restaurant.service.IMenuService;
import com.flyingwillow.restaurant.service.ITxCustomOrderService;
import com.flyingwillow.restaurant.web.admin.dto.CheckoutDTO;
import com.flyingwillow.restaurant.web.waiter.dto.DetailDTO;
import com.flyingwillow.restaurant.web.waiter.dto.OrderDTO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuxuhui on 2017/9/28.
 */
@Service
public class TxCustomOrderServiceImpl implements ITxCustomOrderService {

    @Autowired
    private ICustomOrderService customOrderService;
    @Autowired
    private ICustomOrderDetailService customOrderDetailService;
    @Autowired
    private IMenuService menuService;

    @Override
    public void txCreateOrder(OrderDTO orderDTO) {
        if(null==orderDTO){
            return;
        }

        CustomOrder order = orderDTO.toOrder();

        //create order
        customOrderService.saveCustomOrder(order);
        if(null==order.getId()){
            throw new IllegalStateException("创建订单失败");
        }

        if(null==orderDTO.getDetails()||orderDTO.getDetails().isEmpty()){
            return;
        }
        Float totalPrice = 0f;
        for(DetailDTO detailDTO : orderDTO.getDetails()){

            Integer menuId = detailDTO.getMenuId();
            if(null==menuId){
                continue;
            }
            Menu menu = menuService.getMenuById(menuId);
            if(null==detailDTO.getUnit()){
                detailDTO.setUnit(menu.getUnit());
            }
            detailDTO.setPrice(menu.getPrice());

            CustomOrderDetail detail = detailDTO.toDetail();
            detail.setOrderId(order.getId());
            customOrderDetailService.saveCustomOrderDetail(detail);
            totalPrice += detailDTO.getMount()*detailDTO.getPrice();
        }

        //update price
        customOrderService.updateOrderTotalPrice(order.getId(),totalPrice);
    }

    @Override
    public void saveOrderDetail(Integer orderId, DetailDTO detailDTO) {

        if(null==orderId||null==detailDTO){
            return;
        }
        Integer menuId = detailDTO.getMenuId();
        Menu menu = menuService.getMenuById(menuId);

        Float total = 0f;
        if(null==detailDTO.getUnit()){
            detailDTO.setUnit(menu.getUnit());
        }
        detailDTO.setPrice(menu.getPrice());
        CustomOrderDetail detail = detailDTO.toDetail();
        detail.setOrderId(orderId.longValue());
        detail.setWater(SecurityUtils.getSubject().getPrincipal().toString());
        customOrderDetailService.saveCustomOrderDetail(detail);
        total = detailDTO.getMount()*detailDTO.getPrice();
        customOrderService.txIncrementTotalPrice(orderId.longValue(),total);

    }

    @Override
    public void checkoutOrder(Integer orderId, CheckoutDTO checkoutDTO) {
        checkoutDTO.setOrderId(orderId);
        CustomOrder order = checkoutDTO.toOrderUpdate();
        order.setCheckOperator(SecurityUtils.getSubject().getPrincipal().toString());
        customOrderService.updateCustomOrder(order);
    }
}
