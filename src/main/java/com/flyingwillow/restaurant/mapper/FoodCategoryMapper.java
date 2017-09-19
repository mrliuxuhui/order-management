package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.FoodCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface FoodCategoryMapper {

    public List<FoodCategory> getFoodCategoryList(Map<String, Object> params);

    public Integer getFoodCategoryCount(Map<String, Object> params);

    public FoodCategory getFoodCategoryById(Integer categoryId);

    public void saveFoodCategory(FoodCategory category);

    public void updateFoodCategory(FoodCategory category);

    public void deleteFoodCategory(Integer id);

    public void deleteFoodCategoryByIds(Map<String,Object> param);
}
