package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.Material;
import com.flyingwillow.restaurant.service.IMaterialService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@RestController
@RequestMapping("/admin/api")
public class MaterialRestController {

    @Autowired
    private IMaterialService materialService;

    @RequestMapping(value = "/material", method = RequestMethod.GET)
    public ResponseEntity<DataTableResponse<Material>> listMaterial(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Material> list = null;
        Integer total = 0;
        if(null==param){
            list = materialService.getMaterialList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = materialService.getMaterialCount(null);
        }else{

        }

        if(null==list){
            return new ResponseEntity<DataTableResponse<Material>>(HttpStatus.NO_CONTENT);
        }else{
            DataTableResponse<Material> response = new DataTableResponse<Material>(param.getDraw(),total,total,list);
            return new ResponseEntity<DataTableResponse<Material>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/material/{materialId}", method = RequestMethod.GET)
    public ResponseEntity<Material> getMaterial(@PathVariable Integer materialId){

        if(null==materialId){
            return new ResponseEntity<Material>(HttpStatus.BAD_REQUEST);
        }

        Material material = materialService.getMaterialById(materialId);

        return new ResponseEntity<Material>(material,HttpStatus.OK);

    }

    @RequestMapping(value = "/material/{materialId}", method = RequestMethod.PUT)
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer materialId, @RequestBody Material material){

        if(null==materialId){
            return new ResponseEntity<Material>(HttpStatus.BAD_REQUEST);
        }
        material.setId(materialId);
        materialService.updateMaterial(material);
        return new ResponseEntity<Material>(material,HttpStatus.OK);
    }

    @RequestMapping(value = "/material", method = RequestMethod.POST)
    public ResponseEntity<Void> createMaterial(@RequestBody Material material, UriComponentsBuilder ucBuilder){

        materialService.saveMaterial(material);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/material/{id}").buildAndExpand(material.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/material", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMaterial(@RequestBody List<Integer> materialIds){
        if(null==materialIds||materialIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        materialService.deleteMaterialByIds(materialIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/material/{materialId}", method = RequestMethod.DELETE)
    public ResponseEntity<Material> deleteMaterial(@PathVariable Integer materialId){
        if(null==materialId){
            return new ResponseEntity<Material>(HttpStatus.BAD_REQUEST);
        }
        materialService.deleteMaterial(materialId);
        return new ResponseEntity<Material>(HttpStatus.OK);
    }

}