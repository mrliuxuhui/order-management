package com.flyingwillow.restaurant.web.waiter.vo;

import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.domain.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class MenuGroup {

    private Integer id;
    private String name;
    private List<MenuItem> list;

    public MenuGroup(){

    }

    public MenuGroup(FoodCategory foodCategory, List<Menu> menuList){

        this.id = foodCategory.getId();
        this.name = foodCategory.getName();
        this.list = new ArrayList<>(null==menuList?0:menuList.size());
        if(null!=menuList){
            for(Menu menu : menuList){
                if(this.id != menu.getCategoryId()){
                    continue;
                }
                this.list.add(new MenuItem(menu));
            }
        }

    }

    public Integer getId() {
        return id;
    }

    public MenuGroup setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuGroup setName(String name) {
        this.name = name;
        return this;
    }

    public MenuGroup addMenuItem(MenuItem item){
        if(null==list){
            list = new ArrayList<>();
        }
        list.add(item);
        return this;
    }

    public MenuGroup addAllMenuItems(List<MenuItem> items){
        if(null==list){
            list = new ArrayList<>();
        }
        list.addAll(items);
        return this;
    }

    public List<MenuItem> getList() {
        return list;
    }

    public MenuGroup setList(List<MenuItem> list) {
        this.list = list;
        return this;
    }
}
