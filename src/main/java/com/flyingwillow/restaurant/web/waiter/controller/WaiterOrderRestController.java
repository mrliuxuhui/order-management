package com.flyingwillow.restaurant.web.waiter.controller;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.ICustomOrderDetailService;
import com.flyingwillow.restaurant.service.ICustomOrderService;
import com.flyingwillow.restaurant.service.IFoodCategoryService;
import com.flyingwillow.restaurant.service.IMenuService;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import com.flyingwillow.restaurant.util.web.TableNumberUtil;
import com.flyingwillow.restaurant.web.waiter.vo.MenuGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/22.
 */
@RestController
@RequestMapping("/waiter")
public class WaiterOrderRestController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private ICustomOrderService customOrderService;
    @Autowired
    private ICustomOrderDetailService customOrderDetailService;
    @Autowired
    private IFoodCategoryService foodCategoryService;


    // "/order" create  request body {tableNo:n, details:[]}



    // "/order/detail/{detailId}/" 增加量 put

    // "/order/{orderId}/"  增加菜品  post

    // "/order/detail/{detailId}"  delete

    // "/order/deliver"  上菜 put

    // "/order/{orderId}" get

    // "/order/{orderId}/check" put

    // get menu list
    @RequestMapping(value = "/menu/grouped", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getGroupedMenus(){

        Map<String,Object> result = new HashMap<>(2);

        List<FoodCategory> categories = foodCategoryService.getFoodCategoryList(null,0,100);
        if(null!=categories&&categories.size()>0){
            List<MenuGroup> groups = new ArrayList<>(categories.size());
            for(FoodCategory category : categories){
                List<Menu> list = menuService.getMenuListByCategory(category.getId());
                MenuGroup group = new MenuGroup(category,list);
                groups.add(group);
            }

            result.put("groups",groups);
        }

        return new ResponseEntity(result, HttpStatus.OK);

    }

    //获取桌号列表
    @RequestMapping(value = "/tableNo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getTableNumbers(@RequestParam Integer type){
        type = null==type?0:type;

        List<Integer> listAll = TableNumberUtil.getAllTableNumberList();
        List<Integer> listOrdered = customOrderService.getOrderedTableNumbers();

        if(1==type){
            return new ResponseEntity(listOrdered,HttpStatus.OK);
        }else {

            return new ResponseEntity(listAll.removeAll(listOrdered),HttpStatus.OK);
        }

    }

}
