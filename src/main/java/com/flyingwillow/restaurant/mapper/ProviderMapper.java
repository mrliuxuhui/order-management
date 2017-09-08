package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Provider;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface ProviderMapper {

    public List<Provider> getProviderList(Map<String, Object> params);

    public void saveProvider(Provider provider);

    public void updateProvider(Provider provider);

    public void deleteProvider(Integer id);
}
