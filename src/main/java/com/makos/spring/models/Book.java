package com.makos.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "name should be contain Firstname and Lastname")
    private String author;

    @Min(value = 0, message = "Year should be greater 0")
    @Max(value = 2023, message = "Year should be lower 2023")
    private int year;

//    @NotEmpty(message = "Author should not be empty")
//    private Person author;

}
