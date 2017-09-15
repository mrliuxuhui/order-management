package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.Measurement;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public interface IMeasurementService {
    public List<Measurement> getMeasurementList(Map<String, Object> params, int page, int size);

    public Integer getMeasurementCount(Map<String, Object> params);

    public Measurement getMeasurementById(Integer measurementId);

    public void saveMeasurement(Measurement measurement);

    public void updateMeasurement(Measurement measurement);

    public void deleteMeasurement(Integer id);

    public void deleteMeasurementByIds(List<Integer> idList);
}
