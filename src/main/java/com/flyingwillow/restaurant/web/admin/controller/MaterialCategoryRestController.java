package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.MaterialCategory;
import com.flyingwillow.restaurant.service.IMaterialCategoryService;
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
public class MaterialCategoryRestController {

    @Autowired
    private IMaterialCategoryService materialCategoryService;

    @RequestMapping(value = "/materialCategory", method = RequestMethod.GET)
    public ResponseEntity<DataTableResponse<MaterialCategory>> listMaterialCategory(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<MaterialCategory> list = null;
        Integer total = 0;
        if(null==param){
            list = materialCategoryService.getMaterialCategoryList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
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

        if(null==list){
            return new ResponseEntity<DataTableResponse<MaterialCategory>>(HttpStatus.NO_CONTENT);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            DataTableResponse<MaterialCategory> response = new DataTableResponse<MaterialCategory>(null!=param?param.getDraw():1,total,total,list);
            return new ResponseEntity<DataTableResponse<MaterialCategory>>(response,headers,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.GET)
    public ResponseEntity<MaterialCategory> getMaterialCategory(@PathVariable Integer materialCategoryId){

        if(null==materialCategoryId){
            return new ResponseEntity<MaterialCategory>(HttpStatus.BAD_REQUEST);
        }

        MaterialCategory materialCategory = materialCategoryService.getMaterialCategoryById(materialCategoryId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<MaterialCategory>(materialCategory,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.PUT)
    public ResponseEntity<MaterialCategory> updateMaterialCategory(@PathVariable Integer materialCategoryId, MaterialCategory materialCategory){

        if(null==materialCategoryId){
            return new ResponseEntity<MaterialCategory>(HttpStatus.BAD_REQUEST);
        }

        materialCategory.setId(materialCategoryId);
        materialCategoryService.updateMaterialCategory(materialCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<MaterialCategory>(materialCategory,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/materialCategory", method = RequestMethod.POST)
    public ResponseEntity<Void> createMaterialCategory(MaterialCategory materialCategory, UriComponentsBuilder ucBuilder){

        materialCategoryService.saveMaterialCategory(materialCategory);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/materialCategory/{id}").buildAndExpand(materialCategory.getId()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/materialCategory", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMaterialCategory(@RequestBody List<Integer> materialCategoryIds){
        if(null==materialCategoryIds||materialCategoryIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        materialCategoryService.deleteMaterialCategoryByIds(materialCategoryIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/materialCategory/{materialCategoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<MaterialCategory> deleteMaterialCategory(@PathVariable Integer materialCategoryId){
        if(null==materialCategoryId){
            return new ResponseEntity<MaterialCategory>(HttpStatus.BAD_REQUEST);
        }
        materialCategoryService.deleteMaterialCategory(materialCategoryId);
        return new ResponseEntity<MaterialCategory>(HttpStatus.OK);
    }

}
