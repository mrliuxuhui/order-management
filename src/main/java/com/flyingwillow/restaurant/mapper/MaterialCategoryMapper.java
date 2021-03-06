package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.MaterialCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface MaterialCategoryMapper {

    public List<MaterialCategory> getMaterialCategoryList(Map<String, Object> params);

    public Integer getMaterialCategoryCount(Map<String, Object> params);

    public MaterialCategory getMaterialCategoryById(Integer categoryId);

    public void saveMaterialCategory(MaterialCategory category);

    public void updateMaterialCategory(MaterialCategory category);

    public void deleteMaterialCategory(Integer id);

    public void deleteMaterialCategoryByIds(Map<String,Object> param);
}
