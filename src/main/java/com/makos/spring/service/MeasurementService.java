package com.makos.spring.service;

import com.makos.spring.dto.MeasurementDTO;

import java.util.List;

public interface MeasurementService {

    void add(MeasurementDTO measurementDTO);

    List<MeasurementDTO> findAll();

    int countRainyDays();
}
