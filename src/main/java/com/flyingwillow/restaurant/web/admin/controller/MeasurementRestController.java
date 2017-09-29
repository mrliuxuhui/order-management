package com.flyingwillow.restaurant.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Measurement;
import com.flyingwillow.restaurant.service.IMeasurementService;
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
public class MeasurementRestController {

    @Autowired
    private IMeasurementService measurementService;

    @RequestMapping(value = "/measurement", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<Measurement>> listMeasurement(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Measurement> list = null;
        Integer total = 0;
        if(null==param){
            list = measurementService.getMeasurementList(null, (Constants.PAGE_START-1)*Constants.PAGE_LENGTH,Constants.PAGE_LENGTH);
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
        DataTableResponse<Measurement> response = new DataTableResponse<Measurement>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<Measurement>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<Measurement>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Measurement> getMeasurement(@PathVariable Integer measurementId){

        if(null==measurementId){
            return new ResponseEntity<Measurement>(new Measurement(),HttpStatus.BAD_REQUEST);
        }

        Measurement measurement = measurementService.getMeasurementById(measurementId);

        return new ResponseEntity<Measurement>(measurement,HttpStatus.OK);

    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.PUT,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable Integer measurementId,
                                                           Measurement measurement) throws IOException {

        if(null==measurementId){
            return new ResponseEntity<Measurement>(new Measurement(),HttpStatus.BAD_REQUEST);
        }

        measurement.setId(measurementId);
        measurementService.updateMeasurement(measurement);
        return new ResponseEntity<Measurement>(measurement,HttpStatus.OK);
    }

    @RequestMapping(value = "/measurement", method = RequestMethod.POST,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> createMeasurement(Measurement measurement) throws IOException {

        measurementService.saveMeasurement(measurement);

        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/measurement", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMeasurement(@RequestBody String measurementIds){
        if(null==measurementIds||measurementIds.isEmpty()){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : measurementId is empty."),HttpStatus.BAD_REQUEST);
        }
        JSONArray list = JSON.parseArray(measurementIds);
        measurementService.deleteMeasurementByIds(list.toJavaList(Integer.class));
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    @RequestMapping(value = "/measurement/{measurementId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMeasurement(@PathVariable Integer measurementId){
        if(null==measurementId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : measurementId is empty."),HttpStatus.BAD_REQUEST);
        }
        measurementService.deleteMeasurement(measurementId);
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

}
