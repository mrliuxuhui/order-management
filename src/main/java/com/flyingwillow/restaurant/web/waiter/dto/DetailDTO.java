package com.flyingwillow.restaurant.web.waiter.dto;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class DetailDTO {

    private Integer menuId;
    private Float mount;

    public Integer getMenuId() {
        return menuId;
    }

    public DetailDTO setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Float getMount() {
        return mount;
    }

    public DetailDTO setMount(Float mount) {
        this.mount = mount;
        return this;
    }
}
