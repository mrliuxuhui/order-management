package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.mapper.MenuMapper;
import com.flyingwillow.restaurant.service.IMenuService;
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
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(Map<String, Object> params, int start, int size) {
        start = start>0?start:0;
        size = size>0?size: Constants.PAGE_LENGTH;
        if(null==params){
            params = new HashMap<String,Object>();
        }
        params.put("start",start);
        params.put("size",size);
        return menuMapper.getMenuList(params);
    }

    @Override
    public Integer getMenuCount(Map<String, Object> params) {
        params = null==params?(new HashMap<String,Object>(0)):params;
        return menuMapper.getMenuCount(params);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuMapper.getMenuById(menuId);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }

    @Override
    public void deleteMenuByIds(List<Integer> idList) {
        Map<String,Object> param = new HashMap<>(1);
        param.put("idList",idList);
        menuMapper.deleteMenuByIds(param);
    }
}
