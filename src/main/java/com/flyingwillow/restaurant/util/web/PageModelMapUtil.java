package com.flyingwillow.restaurant.util.web;

import java.util.HashMap;

/**
 * Created by 刘旭辉 on 2017/9/20.
 */
public class PageModelMapUtil {

    private final static HashMap<String,String> map = new HashMap<>();

    public static HashMap<String,String> getMap(){
        if(!map.isEmpty()){
            return map;
        }
        //init
        map.put("menu","/basic/menu");
        map.put("material","/basic/material");
        map.put("materialCategory","/basic/materialCategory");
        map.put("foodCategory","/basic/foodCategory");
        map.put("measurement","/basic/measurement");
        map.put("provider","/basic/provider");
        map.put("customorders","/order/order");
        map.put("orderdetail","/order/detail");

        return map;
    }
}
