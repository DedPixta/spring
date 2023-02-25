package com.makos.spring.util.validator;

import com.makos.spring.model.Sensor;
import com.makos.spring.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        Optional<Sensor> sensorFromDB = sensorService.findByName(sensor.getName());

        if(sensorFromDB.isPresent()) {
            errors.rejectValue("name", "","Sensor with this name already exists!");
        }
    }
}
