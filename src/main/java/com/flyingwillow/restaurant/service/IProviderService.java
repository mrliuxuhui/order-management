package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.Provider;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IProviderService {
    public List<Provider> getProviderList(Map<String, Object> params, int page, int size);

    public Integer getProviderCount(Map<String, Object> params);

    public Provider getProviderById(Integer providerId);

    public void saveProvider(Provider provider);

    public void updateProvider(Provider provider);

    public void deleteProvider(Integer id);

    public void deleteProviderByIds(List<Integer> idList);
}
