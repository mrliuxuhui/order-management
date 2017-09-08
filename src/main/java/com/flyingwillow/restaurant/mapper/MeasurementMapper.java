package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Measurement;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface MeasurementMapper {

    public List<Measurement> getMeasurementList(Map<String,Object> params);

    public void saveMeasurement(Measurement measurement);

    public void updateMeasurement(Measurement measurement);

    public void deleteMeasurement(Integer id);
}
