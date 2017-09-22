package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.CustomOrder;
import com.flyingwillow.restaurant.domain.CustomOrderDetail;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.ICustomOrderDetailService;
import com.flyingwillow.restaurant.service.ICustomOrderService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import com.flyingwillow.restaurant.web.admin.vo.JsonResponseStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/20.
 */
@RestController
@RequestMapping("/admin/api")
public class CustomOrderRestController {

    @Autowired
    private ICustomOrderDetailService customOrderDetailService;
    @Autowired
    private ICustomOrderService customOrderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<CustomOrder>> listOrders(@RequestParam(required = false)String dataTableParam,
                                                            @RequestParam(required = false) Boolean showAll){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<CustomOrder> list = null;
        Integer total = 0;
        if(null==param){
            list = customOrderService.getCustomOrderList(null, showAll,Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = customOrderService.getCustomOrderCount(null,showAll);
        }else{

            String search = param.getSearch();
            HashMap<String,Object> query = new HashMap<>();
            if(StringUtils.isNotBlank(search)){
                if(search.matches("\\d+")){
                    query.put("id",Integer.parseInt(search));
                }else{
                    query.put("name",search);
                }
            }

            List<FieldOrder> orders = param.getOrder();
            if(null!=orders&&orders.size()>0){
                query.put("orders",orders);
            }

            list = customOrderService.getCustomOrderList(query,showAll,param.getStart(),param.getLength());
            total = customOrderService.getCustomOrderCount(query,showAll);

        }

        DataTableResponse<CustomOrder> response = new DataTableResponse<CustomOrder>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<CustomOrder>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<CustomOrder>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getOrderDetail(@PathVariable Integer orderId){

        if(null==orderId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"缺少必要参数"),HttpStatus.BAD_REQUEST);
        }

        List<CustomOrderDetail> detailList = customOrderDetailService.getCustomOrderDetailsByOrder(orderId);
        Integer total = customOrderDetailService.getCustomOrderDetailCountByOrder(orderId);

        return new ResponseEntity<List<CustomOrderDetail>>(detailList,HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderNumber}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getOrderDetail(@PathVariable String orderNumber){

        if(StringUtils.isBlank(orderNumber)){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"缺少必要参数"),HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<List<CustomOrderDetail>>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/order/numbers", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity searchOrderNumber(@RequestParam String tableNo){

    }

    @RequestMapping(value = "/order/detail/{detailId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getOrderDetailById(@PathVariable Integer detailId){

    }

    @RequestMapping(value = "/order/{orderId}/detail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity add2Order(@PathVariable Integer orderId){

    }

    @RequestMapping(value = "/order/detail/{detailId}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity updateDetail(@PathVariable Integer detailId){

    }

    @RequestMapping(value = "/order/detail/{detailId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity updateDetail(@PathVariable Integer detailId){

    }
}
