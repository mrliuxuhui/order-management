package com.flyingwillow.restaurant.web.admin.vo;

import com.flyingwillow.restaurant.domain.Menu;

/**
 * Created by 刘旭辉 on 2017/9/24.
 */
public class MenuVO {

    private Integer id;
    private String name;
    private String text;//name
    private String img;
    private Float price;
    private Integer unit;
    private String unitName;
    private Integer categoryId;
    private String categoryName;

    public MenuVO(){

    }
    public MenuVO(Menu menu){
        this.setId(menu.getId()).setName(menu.getName()).setText(menu.getName());
        this.setImg(menu.getImg()).setPrice(menu.getPrice()).setUnit(menu.getUnit()).setCategoryId(menu.getCategoryId());
        if(null!=menu.getFoodCategory()){
            this.setCategoryName(menu.getFoodCategory().getName());
        }
        if(null!=menu.getMeasurement()){
            this.setUnitName(menu.getMeasurement().getCname());
        }
    }

    public Integer getId() {
        return id;
    }

    public MenuVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getText() {
        return text;
    }

    public MenuVO setText(String text) {
        this.text = text;
        return this;
    }

    public String getImg() {
        return img;
    }

    public MenuVO setImg(String img) {
        this.img = img;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public MenuVO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public MenuVO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public MenuVO setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public MenuVO setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public MenuVO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
