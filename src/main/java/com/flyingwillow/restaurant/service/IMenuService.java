package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IMenuService {

    public List<Menu> searchMenuByKeywords(String query, int start, int size);

    public List<Menu> getMenuList(Map<String, Object> params, int start, int size);

    public Integer getMenuCount(Map<String, Object> params);

    public Menu getMenuById(Integer menuId);

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);

    public void deleteMenu(Integer id);

    public void deleteMenuByIds(List<Integer> idList);
}
