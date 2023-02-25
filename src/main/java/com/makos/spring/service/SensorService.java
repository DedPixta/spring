package com.makos.spring.service;

import com.makos.spring.model.Sensor;

import java.util.Optional;

public interface SensorService {

    void register(Sensor sensor);

    Optional<Sensor> findByName(String name);
}
