package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.FoodCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IFoodCategoryService {
    public List<FoodCategory> getFoodCategoryList(Map<String, Object> params, int page, int size);

    public Integer getFoodCategoryCount(Map<String, Object> params);

    public FoodCategory getFoodCategoryById(Integer categoryId);

    public void saveFoodCategory(FoodCategory category);

    public void updateFoodCategory(FoodCategory category);

    public void deleteFoodCategory(Integer id);

    public void deleteFoodCategoryByIds(List<Integer> idList);
}
