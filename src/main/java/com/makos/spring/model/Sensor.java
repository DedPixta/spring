package com.makos.spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min=3, max = 30, message = "Sensor's name should be between 3 and 30 characters")
    @Column(name = "name")
    private String name;

}
