package com.makos.spring.service.impl;

import com.makos.spring.model.Measurement;
import com.makos.spring.repository.MeasurementRepository;
import com.makos.spring.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    @Override
    public void add(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public int countRainyDays() {
        return measurementRepository.countByRaining(true);
    }
}
