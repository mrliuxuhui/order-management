package com.flyingwillow.restaurant.web.waiter.controller;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.domain.CustomOrderDetail;
import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.ICustomOrderDetailService;
import com.flyingwillow.restaurant.service.ICustomOrderService;
import com.flyingwillow.restaurant.service.IFoodCategoryService;
import com.flyingwillow.restaurant.service.IMenuService;
import com.flyingwillow.restaurant.service.ITxCustomOrderService;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import com.flyingwillow.restaurant.util.web.TableNumberUtil;
import com.flyingwillow.restaurant.web.admin.dto.CheckoutDTO;
import com.flyingwillow.restaurant.web.admin.vo.JsonResponseStatus;
import com.flyingwillow.restaurant.web.waiter.dto.DetailDTO;
import com.flyingwillow.restaurant.web.waiter.dto.OrderDTO;
import com.flyingwillow.restaurant.web.waiter.vo.MenuGroup;
import com.flyingwillow.restaurant.web.waiter.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private ITxCustomOrderService txCustomOrderService;

    // "/order/{orderId}" get
    @RequestMapping(value = "/order/table/{tableNo}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getOrderByTableNo(@PathVariable Integer tableNo){

        CustomOrder order = customOrderService.getCustomOrderByTableNo(tableNo);
        if(null==order){
            return new ResponseEntity(JsonResponseStatus.buildFailResponse(404,"此桌未点餐"), HttpStatus.BAD_REQUEST);
        }

        List<CustomOrderDetail> list = customOrderDetailService.getCustomOrderDetailsByOrder(order.getId().intValue());

        OrderVO orderVO = new OrderVO(order,list);

        return new ResponseEntity(orderVO,HttpStatus.OK);
    }

    // "/order" create  request body {tableNo:n, details:[]}
    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO){

        if(customOrderService.getCustomOrderCountByTableNo(orderDTO.getTableNo())>0){
            return new ResponseEntity(JsonResponseStatus.buildFailResponse(500,"此桌重复点餐"),HttpStatus.CONFLICT);
        }

        txCustomOrderService.txCreateOrder(orderDTO);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }


    // "/order/detail/{detailId}/" 增加量 put
    @RequestMapping(value = "/order/detail/{detailId}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity updateOrderDetail(@PathVariable Integer detailId, @RequestParam Float mount){

        customOrderDetailService.updateCustomOrderDetailMount(detailId, mount);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    // "/order/{orderId}/"  增加菜品  post
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity addOrderDetail(@PathVariable Integer orderId, @RequestBody DetailDTO detailDTO){

        txCustomOrderService.saveOrderDetail(orderId, detailDTO);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    // "/order/detail/{detailId}"  delete
    @RequestMapping(value = "/order/detail/{detailId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteOrderDetail(@PathVariable Integer detailId){

        customOrderDetailService.deleteCustomOrderDetail(detailId);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    // "/order/deliver"  上菜 put
    @RequestMapping(value = "/order/detail/{detailId}/delivered", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity deliveredOrderDetail(@PathVariable Integer detailId){

        customOrderDetailService.deleteCustomOrderDetail(detailId);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    // "/order/{orderId}/check" put
    @RequestMapping(value = "/order/{orderId}/checkout", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity checkoutOrder(@PathVariable Integer orderId, @RequestBody CheckoutDTO checkoutDTO){

        txCustomOrderService.checkoutOrder(orderId, checkoutDTO);

        return new ResponseEntity(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

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
