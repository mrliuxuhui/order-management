package com.flyingwillow.restaurant.web.admin.dto;

import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.util.web.FileUploadUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by liuxuhui on 2017/9/19.
 */
public class MenuDTO {

    private Integer id;
    private String name;
    private Float price;
    private Integer unit;
    private Integer categoryId;
    private String img;
    private String profile;

    public String getName() {
        return name;
    }

    public MenuDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public MenuDTO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public MenuDTO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public MenuDTO setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getImg() {
        return img;
    }

    public MenuDTO setImg(String img) {
        this.img = img;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public MenuDTO setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public Menu toMenu(){
        return new Menu().setId(id).setName(this.name).setPrice(this.price)
                .setUnit(this.unit).setCategoryId(this.categoryId)
                .setProfile(profile).setImg(this.img);
    }
}
