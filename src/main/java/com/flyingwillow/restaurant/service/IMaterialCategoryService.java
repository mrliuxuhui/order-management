package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.MaterialCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IMaterialCategoryService {
    public List<MaterialCategory> getMaterialCategoryList(Map<String, Object> params, int page, int size);

    public Integer getMaterialCategoryCount(Map<String, Object> params);

    public MaterialCategory getMaterialCategoryById(Integer categoryId);

    public void saveMaterialCategory(MaterialCategory category);

    public void updateMaterialCategory(MaterialCategory category);

    public void deleteMaterialCategory(Integer id);

    public void deleteMaterialCategoryByIds(List<Integer> idList);
}
