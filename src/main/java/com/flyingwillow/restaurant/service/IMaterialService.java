package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.Material;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IMaterialService {
    public List<Material> getMaterialList(Map<String, Object> params, int page, int size);

    public Integer getMaterialCount(Map<String, Object> params);

    public Material getMaterialById(Integer materialId);

    public void saveMaterial(Material material);

    public void updateMaterial(Material material);

    public void deleteMaterial(Integer id);

    public void deleteMaterialByIds(List<Integer> idList);
}
