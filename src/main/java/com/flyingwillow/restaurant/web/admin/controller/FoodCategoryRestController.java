package com.flyingwillow.restaurant.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.service.IFoodCategoryService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import com.flyingwillow.restaurant.util.web.FileUploadUtil;
import com.flyingwillow.restaurant.web.admin.vo.JsonResponseStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@RestController
@RequestMapping("/admin/api")
public class FoodCategoryRestController {

    @Autowired
    private IFoodCategoryService foodCategoryService;

    @RequestMapping(value = "/foodCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<FoodCategory>> listFoodCategory(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<FoodCategory> list = null;
        Integer total = 0;
        if(null==param){
            list = foodCategoryService.getFoodCategoryList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = foodCategoryService.getFoodCategoryCount(null);
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

            list = foodCategoryService.getFoodCategoryList(query,param.getStart(),param.getLength());
            total = foodCategoryService.getFoodCategoryCount(query);

        }
        DataTableResponse<FoodCategory> response = new DataTableResponse<FoodCategory>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<FoodCategory>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<FoodCategory>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<FoodCategory> getFoodCategory(@PathVariable Integer foodCategoryId){

        if(null==foodCategoryId){
            return new ResponseEntity<FoodCategory>(new FoodCategory(),HttpStatus.BAD_REQUEST);
        }

        FoodCategory foodCategory = foodCategoryService.getFoodCategoryById(foodCategoryId);

        return new ResponseEntity<FoodCategory>(foodCategory,HttpStatus.OK);

    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.PUT,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<FoodCategory> updateFoodCategory(@PathVariable Integer foodCategoryId,
                                           FoodCategory foodCategory) throws IOException {

        if(null==foodCategoryId){
            return new ResponseEntity<FoodCategory>(new FoodCategory(),HttpStatus.BAD_REQUEST);
        }

        foodCategory.setId(foodCategoryId);
        foodCategoryService.updateFoodCategory(foodCategory);
        return new ResponseEntity<FoodCategory>(foodCategory,HttpStatus.OK);
    }

    @RequestMapping(value = "/foodCategory", method = RequestMethod.POST,
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> createFoodCategory(@RequestBody FoodCategory foodCategory) throws IOException {

        foodCategoryService.saveFoodCategory(foodCategory);

        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/foodCategory", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteFoodCategory(@RequestBody String foodCategoryIds){
        if(null==foodCategoryIds||foodCategoryIds.isEmpty()){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : foodCategoryId is empty."),HttpStatus.BAD_REQUEST);
        }
        JSONArray list = JSON.parseArray(foodCategoryIds);
        foodCategoryService.deleteFoodCategoryByIds(list.toJavaList(Integer.class));
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteFoodCategory(@PathVariable Integer foodCategoryId){
        if(null==foodCategoryId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : foodCategoryId is empty."),HttpStatus.BAD_REQUEST);
        }
        foodCategoryService.deleteFoodCategory(foodCategoryId);
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

}
