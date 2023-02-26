package com.makos.spring.util.validator;

import com.makos.spring.dto.MeasurementDTO;
import com.makos.spring.dto.SensorDTO;
import com.makos.spring.service.SensorService;
import org.springframework.lang.NonNull;
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
    public boolean supports(@NonNull Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        MeasurementDTO measurement = (MeasurementDTO) o;

        Optional<SensorDTO> sensorFromDB = sensorService.findByName(measurement.getSensor().getName());

        if(sensorFromDB.isEmpty()) {
            errors.rejectValue("sensor", "","Sensor with this name not found!");
        }
    }
}
