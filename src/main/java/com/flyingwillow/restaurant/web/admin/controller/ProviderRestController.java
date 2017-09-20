package com.flyingwillow.restaurant.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Provider;
import com.flyingwillow.restaurant.service.IProviderService;
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
public class ProviderRestController {

    @Autowired
    private IProviderService providerService;

    @RequestMapping(value = "/provider", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<Provider>> listProvider(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Provider> list = null;
        Integer total = 0;
        if(null==param){
            list = providerService.getProviderList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = providerService.getProviderCount(null);
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

            list = providerService.getProviderList(query,param.getStart(),param.getLength());
            total = providerService.getProviderCount(query);

        }
        DataTableResponse<Provider> response = new DataTableResponse<Provider>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<Provider>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<Provider>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Provider> getProvider(@PathVariable Integer providerId){

        if(null==providerId){
            return new ResponseEntity<Provider>(new Provider(),HttpStatus.BAD_REQUEST);
        }

        Provider provider = providerService.getProviderById(providerId);

        return new ResponseEntity<Provider>(provider,HttpStatus.OK);

    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.PUT,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Provider> updateProvider(@PathVariable Integer providerId,
                                                         Provider provider) throws IOException {

        if(null==providerId){
            return new ResponseEntity<Provider>(new Provider(),HttpStatus.BAD_REQUEST);
        }

        provider.setId(providerId);
        providerService.updateProvider(provider);
        return new ResponseEntity<Provider>(provider,HttpStatus.OK);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.POST,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> createProvider(Provider provider) throws IOException {

        providerService.saveProvider(provider);

        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteProvider(@RequestBody String providerIds){
        if(null==providerIds||providerIds.isEmpty()){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : providerId is empty."),HttpStatus.BAD_REQUEST);
        }
        JSONArray list = JSON.parseArray(providerIds);
        providerService.deleteProviderByIds(list.toJavaList(Integer.class));
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteProvider(@PathVariable Integer providerId){
        if(null==providerId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : providerId is empty."),HttpStatus.BAD_REQUEST);
        }
        providerService.deleteProvider(providerId);
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

}
