package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.FoodSupply;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface FoodSupplyMapper {

    public List<FoodSupply> getFoodSupplyList(Map<String, Object> params);

    public void saveFoodSupply(FoodSupply supply);

    public void updateFoodSupply(FoodSupply supply);

    public void deleteFoodSupply(Integer id);
}
