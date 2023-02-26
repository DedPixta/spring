package com.makos.spring.service;

import com.makos.spring.dto.SensorDTO;

import java.util.Optional;

public interface SensorService {

    void register(SensorDTO sensorDTO);

    Optional<SensorDTO> findByName(String name);
}
