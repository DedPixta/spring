package com.makos.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    private double value;
    private boolean raining;
    private SensorDTO sensor;
}
