package com.makos.spring.models;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "name should be contain Firstname and Lastname")
    @Size(min = 2, max = 100, message = "name should be greater 2 and lower 100 characters")
    private String fullName;

    @Min(value = 1800, message = "birth year should be greater than 1800")
    @Max(value = 2023, message = "birth year should be lower than 2023")
    private int birthYear;

}
