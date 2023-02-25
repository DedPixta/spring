package com.makos.spring.service;

import com.makos.spring.model.Measurement;

import java.util.List;

public interface MeasurementService {

    void add(Measurement measurement);

    List<Measurement> findAll();

    int countRainyDays();
}
