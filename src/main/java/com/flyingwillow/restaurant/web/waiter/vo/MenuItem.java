package com.flyingwillow.restaurant.web.waiter.vo;

import com.flyingwillow.restaurant.domain.Menu;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class MenuItem {

    private Integer id;
    private String name;
    private String img;
    private Float price;
    private Integer unit;
    private String unitName;

    public MenuItem(){

    }

    public MenuItem(Menu menu){
        this.setId(menu.getId()).setName(menu.getName()).setImg(menu.getImg());
        this.setPrice(menu.getPrice());
        this.setUnit(menu.getUnit());
        this.setUnitName(null==menu.getMeasurement()?"":menu.getMeasurement().getCname());
    }

    public Integer getId() {
        return id;
    }

    public MenuItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public MenuItem setImg(String img) {
        this.img = img;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public MenuItem setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public MenuItem setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public MenuItem setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }
}
