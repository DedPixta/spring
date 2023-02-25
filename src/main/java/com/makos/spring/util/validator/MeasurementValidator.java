package com.makos.spring.util.validator;

import com.makos.spring.model.Measurement;
import com.makos.spring.model.Sensor;
import com.makos.spring.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        Optional<Sensor> sensorFromDB = sensorService.findByName(measurement.getSensor().getName());

        if(sensorFromDB.isEmpty()) {
            errors.rejectValue("sensor", "","Sensor with this name not found!");
        }
    }
}
