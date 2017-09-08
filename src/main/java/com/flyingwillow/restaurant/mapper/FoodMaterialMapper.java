package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.FoodMaterial;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface FoodMaterialMapper {

    public List<FoodMaterial> getFoodMaterialList(Map<String, Object> params);

    public void saveFoodMaterial(FoodMaterial material);

    public void updateFoodMaterial(FoodMaterial material);

    public void deleteFoodMaterial(Integer id);
}
