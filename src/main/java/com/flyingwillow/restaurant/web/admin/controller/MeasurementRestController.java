package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Measurement;
import com.flyingwillow.restaurant.service.IMeasurementService;
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
public class MeasurementRestController {

    @Autowired
    private IMeasurementService measurementService;

    @RequestMapping(value = "/measurement", method = RequestMethod.GET)
    public ResponseEntity<DataTableResponse<Measurement>> listMeasurement(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Measurement> list = null;
        Integer total = 0;
        if(null==param){
            list = measurementService.getMeasurementList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = measurementService.getMeasurementCount(null);
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

            list = measurementService.getMeasurementList(query,param.getStart(),param.getLength());
            total = measurementService.getMeasurementCount(query);

        }

        if(null==list){
            return new ResponseEntity<DataTableResponse<Measurement>>(HttpStatus.NO_CONTENT);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            DataTableResponse<Measurement> response = new DataTableResponse<Measurement>(null!=param?param.getDraw():1,total,total,list);
            return new ResponseEntity<DataTableResponse<Measurement>>(response,headers,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.GET)
    public ResponseEntity<Measurement> getMeasurement(@PathVariable Integer measurementId){

        if(null==measurementId){
            return new ResponseEntity<Measurement>(HttpStatus.BAD_REQUEST);
        }

        Measurement measurement = measurementService.getMeasurementById(measurementId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Measurement>(measurement,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.PUT)
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable Integer measurementId, Measurement measurement){

        if(null==measurementId){
            return new ResponseEntity<Measurement>(HttpStatus.BAD_REQUEST);
        }

        measurement.setId(measurementId);
        measurementService.updateMeasurement(measurement);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Measurement>(measurement,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/measurement", method = RequestMethod.POST)
    public ResponseEntity<Void> createMeasurement(Measurement measurement, UriComponentsBuilder ucBuilder){

        measurementService.saveMeasurement(measurement);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/measurement/{id}").buildAndExpand(measurement.getId()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/measurement", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMeasurement(@RequestBody List<Integer> measurementIds){
        if(null==measurementIds||measurementIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        measurementService.deleteMeasurementByIds(measurementIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.DELETE)
    public ResponseEntity<Measurement> deleteMeasurement(@PathVariable Integer measurementId){
        if(null==measurementId){
            return new ResponseEntity<Measurement>(HttpStatus.BAD_REQUEST);
        }
        measurementService.deleteMeasurement(measurementId);
        return new ResponseEntity<Measurement>(HttpStatus.OK);
    }

}
