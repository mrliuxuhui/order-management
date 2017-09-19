package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.FoodCategory;
import com.flyingwillow.restaurant.service.IFoodCategoryService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    @RequestMapping(value = "/foodCategory", method = RequestMethod.GET)
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

        if(null==list){
            return new ResponseEntity<DataTableResponse<FoodCategory>>(HttpStatus.NO_CONTENT);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            DataTableResponse<FoodCategory> response = new DataTableResponse<FoodCategory>(null!=param?param.getDraw():1,total,total,list);
            return new ResponseEntity<DataTableResponse<FoodCategory>>(response,headers,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.GET)
    public ResponseEntity<FoodCategory> getFoodCategory(@PathVariable Integer foodCategoryId){

        if(null==foodCategoryId){
            return new ResponseEntity<FoodCategory>(HttpStatus.BAD_REQUEST);
        }

        FoodCategory foodCategory = foodCategoryService.getFoodCategoryById(foodCategoryId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<FoodCategory>(foodCategory,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.PUT)
    public ResponseEntity<FoodCategory> updateFoodCategory(@PathVariable Integer foodCategoryId, FoodCategory foodCategory){

        if(null==foodCategoryId){
            return new ResponseEntity<FoodCategory>(HttpStatus.BAD_REQUEST);
        }

        foodCategory.setId(foodCategoryId);
        foodCategoryService.updateFoodCategory(foodCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<FoodCategory>(foodCategory,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/foodCategory", method = RequestMethod.POST)
    public ResponseEntity<Void> createFoodCategory(FoodCategory foodCategory, UriComponentsBuilder ucBuilder){

        foodCategoryService.saveFoodCategory(foodCategory);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/foodCategory/{id}").buildAndExpand(foodCategory.getId()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/foodCategory", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFoodCategory(@RequestBody List<Integer> foodCategoryIds){
        if(null==foodCategoryIds||foodCategoryIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        foodCategoryService.deleteFoodCategoryByIds(foodCategoryIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/foodCategory/{foodCategoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<FoodCategory> deleteFoodCategory(@PathVariable Integer foodCategoryId){
        if(null==foodCategoryId){
            return new ResponseEntity<FoodCategory>(HttpStatus.BAD_REQUEST);
        }
        foodCategoryService.deleteFoodCategory(foodCategoryId);
        return new ResponseEntity<FoodCategory>(HttpStatus.OK);
    }

}
