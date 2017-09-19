package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Provider;
import com.flyingwillow.restaurant.service.IProviderService;
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
public class ProviderRestController {

    @Autowired
    private IProviderService providerService;

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
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

        if(null==list){
            return new ResponseEntity<DataTableResponse<Provider>>(HttpStatus.NO_CONTENT);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            DataTableResponse<Provider> response = new DataTableResponse<Provider>(null!=param?param.getDraw():1,total,total,list);
            return new ResponseEntity<DataTableResponse<Provider>>(response,headers,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.GET)
    public ResponseEntity<Provider> getProvider(@PathVariable Integer providerId){

        if(null==providerId){
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);
        }

        Provider provider = providerService.getProviderById(providerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Provider>(provider,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.PUT)
    public ResponseEntity<Provider> updateProvider(@PathVariable Integer providerId, Provider provider){

        if(null==providerId){
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);
        }

        provider.setId(providerId);
        providerService.updateProvider(provider);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Provider>(provider,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.POST)
    public ResponseEntity<Void> createProvider(Provider provider, UriComponentsBuilder ucBuilder){

        providerService.saveProvider(provider);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/provider/{id}").buildAndExpand(provider.getId()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProvider(@RequestBody List<Integer> providerIds){
        if(null==providerIds||providerIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        providerService.deleteProviderByIds(providerIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/provider/{providerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteProvider(@PathVariable Integer providerId){
        if(null==providerId){
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);
        }
        providerService.deleteProvider(providerId);
        return new ResponseEntity<Provider>(HttpStatus.OK);
    }

}
