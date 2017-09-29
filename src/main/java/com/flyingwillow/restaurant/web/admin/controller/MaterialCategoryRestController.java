package com.flyingwillow.restaurant.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.MaterialCategory;
import com.flyingwillow.restaurant.service.IMaterialCategoryService;
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
public class MaterialCategoryRestController {

    @Autowired
    private IMaterialCategoryService materialCategoryService;

    @RequestMapping(value = "/materialCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<MaterialCategory>> listMaterialCategory(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<MaterialCategory> list = null;
        Integer total = 0;
        if(null==param){
            list = materialCategoryService.getMaterialCategoryList(null, (Constants.PAGE_START-1)*Constants.PAGE_LENGTH,Constants.PAGE_LENGTH);
            total = materialCategoryService.getMaterialCategoryCount(null);
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

            list = materialCategoryService.getMaterialCategoryList(query,param.getStart(),param.getLength());
            total = materialCategoryService.getMaterialCategoryCount(query);

        }
        DataTableResponse<MaterialCategory> response = new DataTableResponse<MaterialCategory>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<MaterialCategory>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<MaterialCategory>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<MaterialCategory> getMaterialCategory(@PathVariable Integer materialCategoryId){

        if(null==materialCategoryId){
            return new ResponseEntity<MaterialCategory>(new MaterialCategory(),HttpStatus.BAD_REQUEST);
        }

        MaterialCategory materialCategory = materialCategoryService.getMaterialCategoryById(materialCategoryId);

        return new ResponseEntity<MaterialCategory>(materialCategory,HttpStatus.OK);

    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.PUT,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<MaterialCategory> updateMaterialCategory(@PathVariable Integer materialCategoryId,
                                                           MaterialCategory materialCategory) throws IOException {

        if(null==materialCategoryId){
            return new ResponseEntity<MaterialCategory>(new MaterialCategory(),HttpStatus.BAD_REQUEST);
        }

        materialCategory.setId(materialCategoryId);
        materialCategoryService.updateMaterialCategory(materialCategory);
        return new ResponseEntity<MaterialCategory>(materialCategory,HttpStatus.OK);
    }

    @RequestMapping(value = "/materialCategory", method = RequestMethod.POST,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> createMaterialCategory(MaterialCategory materialCategory) throws IOException {

        materialCategoryService.saveMaterialCategory(materialCategory);

        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/materialCategory", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMaterialCategory(@RequestBody String materialCategoryIds){
        if(null==materialCategoryIds||materialCategoryIds.isEmpty()){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : materialCategoryId is empty."),HttpStatus.BAD_REQUEST);
        }
        JSONArray list = JSON.parseArray(materialCategoryIds);
        materialCategoryService.deleteMaterialCategoryByIds(list.toJavaList(Integer.class));
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMaterialCategory(@PathVariable Integer materialCategoryId){
        if(null==materialCategoryId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : materialCategoryId is empty."),HttpStatus.BAD_REQUEST);
        }
        materialCategoryService.deleteMaterialCategory(materialCategoryId);
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

}
