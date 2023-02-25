package com.makos.spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private double value;

    @NotNull
    @Column(name = "raining")
    private boolean raining;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Sensor sensor;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;
}
