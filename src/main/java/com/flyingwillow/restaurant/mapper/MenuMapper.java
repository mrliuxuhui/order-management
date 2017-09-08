package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface MenuMapper {

    public List<Menu> getMenuList(Map<String, Object> params);

    public void saveMenu(Menu measurement);

    public void updateMenu(Menu measurement);

    public void deleteMenu(Integer id);

    public void deleteMenuByIds(List<Integer> idList);
}
