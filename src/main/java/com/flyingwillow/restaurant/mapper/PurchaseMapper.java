package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Purchase;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/8.
 */
public interface PurchaseMapper {

    public List<Purchase> getPurchaseList(Map<String, Object> params);

    public void savePurchase(Purchase detail);

    public void updatePurchase(Purchase detail);

    public void deletePurchaseByNumber(String orderId);

    public void deletePurchase(Integer id);
}
