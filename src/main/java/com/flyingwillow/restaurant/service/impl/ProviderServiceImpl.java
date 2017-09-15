package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.Provider;
import com.flyingwillow.restaurant.mapper.ProviderMapper;
import com.flyingwillow.restaurant.service.IProviderService;
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
public class ProviderServiceImpl implements IProviderService{

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> getProviderList(Map<String, Object> params, int page, int size) {
        page = page>0?page:1;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",(page-1)*size);
        params.put("size",size);
        return providerMapper.getProviderList(params);
    }

    @Override
    public Integer getProviderCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return providerMapper.getProviderCount(params);
    }

    @Override
    public Provider getProviderById(Integer providerId) {
        return providerMapper.getProviderById(providerId);
    }

    @Override
    public void saveProvider(Provider provider) {
        providerMapper.saveProvider(provider);
    }

    @Override
    public void updateProvider(Provider provider) {
        providerMapper.updateProvider(provider);
    }

    @Override
    public void deleteProvider(Integer id) {
        providerMapper.deleteProvider(id);
    }

    @Override
    public void deleteProviderByIds(List<Integer> idList) {
        providerMapper.deleteProviderByIds(idList);
    }
}
