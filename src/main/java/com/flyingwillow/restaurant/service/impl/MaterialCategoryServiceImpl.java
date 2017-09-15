package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.MaterialCategory;
import com.flyingwillow.restaurant.mapper.MaterialCategoryMapper;
import com.flyingwillow.restaurant.service.IMaterialCategoryService;
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
public class MaterialCategoryServiceImpl implements IMaterialCategoryService{

    @Autowired
    private MaterialCategoryMapper materialCategoryMapper;

    @Override
    public List<MaterialCategory> getMaterialCategoryList(Map<String, Object> params, int page, int size) {
        page = page>0?page:1;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",(page-1)*size);
        params.put("size",size);
        return materialCategoryMapper.getMaterialCategoryList(params);
    }

    @Override
    public Integer getMaterialCategoryCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return materialCategoryMapper.getMaterialCategoryCount(params);
    }

    @Override
    public MaterialCategory getMaterialCategoryById(Integer categoryId) {
        return materialCategoryMapper.getMaterialCategoryById(categoryId);
    }

    @Override
    public void saveMaterialCategory(MaterialCategory category) {
        materialCategoryMapper.saveMaterialCategory(category);
    }

    @Override
    public void updateMaterialCategory(MaterialCategory category) {
        materialCategoryMapper.updateMaterialCategory(category);
    }

    @Override
    public void deleteMaterialCategory(Integer id) {
        materialCategoryMapper.deleteMaterialCategory(id);
    }

    @Override
    public void deleteMaterialCategoryByIds(List<Integer> idList) {
        materialCategoryMapper.deleteMaterialCategoryByIds(idList);
    }
}
