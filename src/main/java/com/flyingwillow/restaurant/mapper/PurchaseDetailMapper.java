package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.PurchaseDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/8.
 */
public interface PurchaseDetailMapper {

    public List<PurchaseDetail> getPurchaseDetailList(Map<String, Object> params);

    public void savePurchaseDetail(PurchaseDetail detail);

    public void updatePurchaseDetail(PurchaseDetail detail);

    public void deletePurchaseDetailByPId(Integer orderId);

    public void deletePurchaseDetail(Integer id);
}
