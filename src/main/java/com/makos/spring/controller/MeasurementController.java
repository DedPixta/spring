package com.makos.spring.controller;

import com.makos.spring.dto.MeasurementDTO;
import com.makos.spring.exception.MeasurementNotCreatedException;
import com.makos.spring.exception.SensorNotFoundException;
import com.makos.spring.service.MeasurementService;
import com.makos.spring.util.ErrorExtractor;
import com.makos.spring.util.ExceptionResponse;
import com.makos.spring.util.validator.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<MeasurementDTO> getAll() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public int countRainyDays() {
        return measurementService.countRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        measurementValidator.validate(measurementDTO, bindingResult);

        if(bindingResult.hasErrors()) {
            String errors = ErrorExtractor.extract(bindingResult);
            throw new MeasurementNotCreatedException(errors);
        }
        measurementService.add(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(MeasurementNotCreatedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(SensorNotFoundException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
