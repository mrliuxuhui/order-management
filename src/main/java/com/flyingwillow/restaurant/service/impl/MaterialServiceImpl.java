package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.Material;
import com.flyingwillow.restaurant.mapper.MaterialMapper;
import com.flyingwillow.restaurant.service.IMaterialService;
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
public class MaterialServiceImpl implements IMaterialService{

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> getMaterialList(Map<String, Object> params, int start, int size) {
        start = start>0?start:1;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",start);
        params.put("size",size);
        return materialMapper.getMaterialList(params);
    }

    @Override
    public Integer getMaterialCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return materialMapper.getMaterialCount(params);
    }

    @Override
    public Material getMaterialById(Integer materialId) {
        return materialMapper.getMaterialById(materialId);
    }

    @Override
    public void saveMaterial(Material material) {
        materialMapper.saveMaterial(material);
    }

    @Override
    public void updateMaterial(Material material) {
        materialMapper.updateMaterial(material);
    }

    @Override
    public void deleteMaterial(Integer id) {
        materialMapper.deleteMaterial(id);
    }

    @Override
    public void deleteMaterialByIds(List<Integer> idList) {
        Map<String,Object> param = new HashMap<>(1);
        param.put("idList",idList);
        materialMapper.deleteMaterialByIds(param);
    }
}
