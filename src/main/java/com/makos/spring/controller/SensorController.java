package com.makos.spring.controller;

import com.makos.spring.exception.SensorNotCreatedException;
import com.makos.spring.model.Sensor;
import com.makos.spring.service.SensorService;
import com.makos.spring.util.ErrorExtractor;
import com.makos.spring.util.ExceptionResponse;
import com.makos.spring.util.validator.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid Sensor sensor,
                                               BindingResult bindingResult) {
        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            String errors = ErrorExtractor.extract(bindingResult);
            throw new SensorNotCreatedException(errors);
        }
        sensorService.register(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(SensorNotCreatedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
