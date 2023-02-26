package com.makos.spring.service.impl;

import com.makos.spring.dto.MeasurementDTO;
import com.makos.spring.exception.SensorNotFoundException;
import com.makos.spring.mapper.MeasurementMapper;
import com.makos.spring.model.Measurement;
import com.makos.spring.model.Sensor;
import com.makos.spring.repository.MeasurementRepository;
import com.makos.spring.repository.SensorRepository;
import com.makos.spring.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    @Override
    public void add(MeasurementDTO measurementDTO) {
        Measurement measurement = MeasurementMapper.INSTANCE.mapToMeasurement(measurementDTO);
        Optional<Sensor> sensorOptional = sensorRepository.findByName(measurement.getSensor().getName());
        Sensor sensor = sensorOptional.orElseThrow(SensorNotFoundException::new);
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    @Override
    public List<MeasurementDTO> findAll() {
        return measurementRepository.findAll()
                .stream()
                .map(MeasurementMapper.INSTANCE::mapToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int countRainyDays() {
        return measurementRepository.countByRaining(true);
    }
}
