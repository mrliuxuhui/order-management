package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface MenuMapper {

    public List<Menu> getMenuList(Map<String, Object> params);

    public Integer getMenuCount(Map<String, Object> params);

    public Menu getMenuById(Integer menuId);

    public void saveMenu(Menu measurement);

    public void updateMenu(Menu measurement);

    public void deleteMenu(Integer id);

    public void deleteMenuByIds(Map<String,Object> param);
}
