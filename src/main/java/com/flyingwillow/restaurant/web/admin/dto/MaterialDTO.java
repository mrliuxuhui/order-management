package com.flyingwillow.restaurant.web.admin.dto;

import com.flyingwillow.restaurant.domain.Material;
import com.flyingwillow.restaurant.util.web.FileUploadUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by liuxuhui on 2017/9/19.
 */
public class MaterialDTO {

    private Integer id;
    private String name;
    private Float price;
    private Integer unit;
    private Integer categoryId;
    private String imgPath;
    private String profile;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public MaterialDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public MaterialDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public MaterialDTO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Integer getUnit() {
        return unit;
    }

    public MaterialDTO setUnit(Integer unit) {
        this.unit = unit;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public MaterialDTO setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getImgPath() {
        return imgPath;
    }

    public MaterialDTO setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public MaterialDTO setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public Material toMaterial(){
        return new Material().setId(id).setName(this.name).setPrice(this.price)
                .setUnit(this.unit).setCategoryId(this.categoryId)
                .setProfile(profile).setImg(this.imgPath);
    }
}
