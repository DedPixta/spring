package com.makos.spring.mapper;

import com.makos.spring.dto.MeasurementDTO;
import com.makos.spring.dto.SensorDTO;
import com.makos.spring.model.Measurement;
import com.makos.spring.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    MeasurementDTO mapToMeasurementDTO(Measurement measurement);
    Measurement mapToMeasurement(MeasurementDTO measurementDTO);
    SensorDTO mapToSensorDTO(Sensor sensor);
    Sensor mapToSensor(SensorDTO sensorDTO);
}
