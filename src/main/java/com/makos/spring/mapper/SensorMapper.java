package com.makos.spring.mapper;

import com.makos.spring.dto.SensorDTO;
import com.makos.spring.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SensorMapper {
    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    SensorDTO mapToSensorDTO(Sensor sensor);
    Sensor mapToSensor(SensorDTO sensorDTO);
}
