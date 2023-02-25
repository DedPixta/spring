package com.makos.spring.service.impl;

import com.makos.spring.model.Sensor;
import com.makos.spring.repository.SensorRepository;
import com.makos.spring.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    @Override
    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
