package com.makos.spring.util.validator;

import com.makos.spring.dto.SensorDTO;
import com.makos.spring.service.SensorService;
import org.springframework.lang.NonNull;
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
    public boolean supports(@NonNull Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        SensorDTO sensor = (SensorDTO) o;

        Optional<SensorDTO> sensorFromDB = sensorService.findByName(sensor.getName());

        if(sensorFromDB.isPresent()) {
            errors.rejectValue("name", "","Sensor with this name already exists!");
        }
    }
}
