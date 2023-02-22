package com.makos.spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "secure_person")
public class SecurePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "pass")
    private String pass;

    @Min(value = 1800, message = "Birth year should be greater than 1800")
    @Max(value = 2023, message = "Birth year should be lower than 2023")
    @Column(name = "birth_year")
    private int birthYear;
}
