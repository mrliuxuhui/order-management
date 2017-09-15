package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.mapper.FoodCategoryMapper;
import com.flyingwillow.restaurant.service.IFoodCategoryService;
import com.flyingwillow.restaurant.util.web.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
@Service
public class FoodCategoryServiceImpl implements IFoodCategoryService{

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Override
    public List<FoodCategory> getFoodCategoryList(Map<String, Object> params, int page, int size) {
        page = page>0?page:1;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",(page-1)*size);
        params.put("size",size);
        return foodCategoryMapper.getFoodCategoryList(params);
    }

    @Override
    public Integer getFoodCategoryCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return foodCategoryMapper.getFoodCategoryCount(params);
    }

    @Override
    public FoodCategory getFoodCategoryById(Integer categoryId) {
        return foodCategoryMapper.getFoodCategoryById(categoryId);
    }

    @Override
    public void saveFoodCategory(FoodCategory category) {
        foodCategoryMapper.saveFoodCategory(category);
    }

    @Override
    public void updateFoodCategory(FoodCategory category) {
        foodCategoryMapper.updateFoodCategory(category);
    }

    @Override
    public void deleteFoodCategory(Integer id) {
        foodCategoryMapper.deleteFoodCategory(id);
    }

    @Override
    public void deleteFoodCategoryByIds(List<Integer> idList) {
        foodCategoryMapper.deleteFoodCategoryByIds(idList);
    }
}
